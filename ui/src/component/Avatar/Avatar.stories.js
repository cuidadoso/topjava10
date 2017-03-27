/**
 * Created by Alejandro on 26.03.2017.
 */
import React from 'react';
import { storiesOf } from '@kadira/storybook';
import { withKnobs, text, boolean, number } from '@kadira/storybook-addon-knobs';

import Avatar from './Avatar';

const initialImage = "https://commons.wikimedia.org/wiki/File:Loading_icon.gif";
const srcImage = "http://i.imgur.com/52gMRxK.png";
const fallbackImage = "http://i.imgur.com/gh4u70G.png";
const alt = "Same image should be here.";

const stories = storiesOf('Avatar', module);
stories.addDecorator(withKnobs);

stories.add('with source image and alt text', () => (
    <Avatar src={text('srcImage', srcImage)}
            fallback={text('fallbackImage', fallbackImage)}
            initial={text('initialImage', initialImage)}
            alt={text('alt', alt)} />
)).add('with fallback image', () => (
    <Avatar src={text('srcImage', 'No image.')}
            fallback={text('fallbackImage', fallbackImage)}
            initial={text('initialImage', initialImage)}
            alt={text('alt', alt)} />
)).add('without image', () =>(
    <Avatar src={text('srcImage', 'No image.')}
            fallback={text('fallbackImage', 'No image.')}
            initial={text('initialImage', initialImage)}
            alt={text('alt', alt)}/>
));
