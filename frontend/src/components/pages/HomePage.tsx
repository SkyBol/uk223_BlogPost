import React from 'react';
import logo from '../../logo1.png';
import RecipeReviewCard from '../BlogCard/BlogCard';
import ResponsiveAppBar from '../Header/ResponsiveAppBar';


export default function HomePage() {
  return (
    <div>
    <ResponsiveAppBar/>
    <RecipeReviewCard id={''} title={''} text={''}  date={new Date()} author= {{
        id: '',
        email: '',
        firstName: '',
        lastName: '',
        roles: []
      }}/>
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
