import React from 'react';
import ReactDOM from 'react-dom';

import App from './App';
import './index.css';

const routes = {
    path: '/',
    indexRoute: { onEnter: (nextState, replace) => replace('/') },
    childRoutes: [
        {
            path: 'users',
            component: Users,
        },
        {
            path: 'meals',
            component: Meals,
        },
    ]
};

ReactDOM.render(
  <App  routes={routes} />,
  document.getElementById('root')
);
