/**
 * Created by apyreev on 28-Mar-17.
 */
const express = require('express');
const fs = require('fs');
const router = express.Router();
const app = express();

const PROD_ENV = (process.env.NODE_ENV === 'production');
const ctxRoot = process.argv[2];
if (!ctxRoot) {
    console.error('Error!');
    console.error('Must pass context root as second arg: node server.js <MyContextRoot>');
    process.exit();
}
process.env.CONTEXT_ROOT = ctxRoot;

const baseURL = `http://localhost:8080/${ctxRoot}/`;

const url = require('url');
function getOptions(proxyUrl) {
    const options = url.parse(proxyUrl);
    options.cookieRewrite = true;
    return options;
}

if (PROD_ENV) {
    app.use('/dist', express.static('dist'));
} else {
    const webpack = require('webpack');
    const config = require('./webpack.config');
    const compiler = webpack(config);

    app.use(require('webpack-dev-middleware')(compiler, {
        noInfo: true,
        publicPath: config.output.publicPath
    }));

    app.use(require('webpack-hot-middleware')(compiler));
}

const proxy = require('proxy-middleware');
router.use('/', proxy(getOptions(baseURL)));

app.get('/', (req, res) => {
    res.redirect(`http://localhost:8088/${ctxRoot}?src=express`);
});

app.use(`/${ctxRoot}/`, router);

app.listen(8088);
console.log('Navigate to http://localhost:8088');
console.log('in your browser for development.\n');
console.log('Make sure that your tomcat server is started on port 8080.\n');
console.log('Service calls will be proxied to');
console.log(`http://localhost:8080/${ctxRoot}`);

