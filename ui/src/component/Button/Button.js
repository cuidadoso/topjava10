import React, { Component, PropTypes as pt } from 'react';
import './Button.css';

class Button extends Component {
    render() {
        return (
            <div>
              <button className='button-style' onClick={this.props.onClick}>
                  {this.props.children}
              </button>
            </div>
        );
    }
}

Button.displayName = 'Button';
Button.propTypes = {
  children: pt.string.isRequired,
  onClick: pt.func,
};

export default Button;
