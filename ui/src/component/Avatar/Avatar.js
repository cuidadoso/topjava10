/**
 * Created by Alejandro on 26.03.2017.
 */
import React, { Component, PropTypes as pt } from 'react';
import ReactImageFallback from "react-image-fallback";
import './Avatar.css';

class Avatar extends Component {
    static propType = {
        children:   pt.string,
        src:        pt.string.isRequired,
        fallback:   pt.string.isRequired,
        initial:    pt.string.isRequired,
        alt:        pt.string,
        className:  pt.string,
    };
    static defaultProps = {
        className: 'avatar-image',
    };

    render() {
        return (
            <div>
                {this.props.children}
                <ReactImageFallback
                    src={this.props.src}
                    fallbackImage={this.props.fallback}
                    initialImage={this.props.initial}
                    alt={this.props.alt}
                    className={this.props.className} />
            </div>
        );
    }
}

Avatar.displayName = 'Avatar';
Avatar.propTypes = {
};
Avatar.defaultProps = {
    className: 'avatar-image',
};

export default Avatar;