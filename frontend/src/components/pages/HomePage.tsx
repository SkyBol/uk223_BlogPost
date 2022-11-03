import React from 'react';
import logo from '../../logo1.png';
import ResponsiveAppBar from '../Header/ResponsiveAppBar';


export default function HomePage() {
  return (
    <div>
    <ResponsiveAppBar/>
      <img
        src={logo}
        style={{ filter: 'invert(100%)' }}
        className='App-logo'
        alt='logo'
      />
      <p>PlaceHolder</p>
    </div>
  );
}
