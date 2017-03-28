const path = require('path');
const webpack = require('webpack');
const autoprefixer = require('autoprefixer');

module.exports = {
  // cheap-eval-source-map is faster, inline-source-map gives better debug experience
  devtool: 'cheap-eval-source-map',
  //devtool: 'inline-source-map',
  entry: [
    'eventsource-polyfill',
    'webpack-hot-middleware/client',
    './src/index'
  ],
  output: {
    path: path.join(__dirname, '../war/dist/'),
    filename: 'bundle.js',
    publicPath: `/${process.env.CONTEXT_ROOT}/dist/`

  },
  plugins: [
    new webpack.DefinePlugin({
      'process.env': {
        'NODE_ENV': JSON.stringify(process.env.NODE_ENV)
      }
    }),
    new webpack.ContextReplacementPlugin(/moment[\/\\]locale$/, /en/),
    new webpack.optimize.OccurenceOrderPlugin(),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin(),
    new webpack.ProvidePlugin({
      'fetch': 'imports?this=>global!exports?global.fetch!whatwg-fetch'
    })
  ],
  module: {
    loaders: [
      {
        test:	/\.json$/,
        loader: 'json'
      },
      {
        test: /\.js$/,
        loaders: ['babel'],
        exclude: /node_modules/,
      },
      { test: /\.md$/, loader: 'html!markdown' },
      { test: /\.less$/, loader: 'style!css!postcss!less' },
      { test: /\.css$/, loader: 'style!css' },
      { test: /\.woff2{0,1}$/, loader: 'url-loader?limit=10000&mimetype=application/font-woff' },
      { test: /\.(otf|eot|svg|ttf|woff|woff2)(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: 'file-loader' },
      { test: /\.(png|jpg|gif)$/, loader: 'url-loader?limit=8192'}
    ]
  },
  postcss: [autoprefixer({ browsers: ['last 2 versions'] })],
  externals: {
    'react/addons': true,
    'react/lib/ExecutionEnvironment': true,
    'react/lib/ReactContext': true
  }
};


