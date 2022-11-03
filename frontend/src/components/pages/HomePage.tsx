import logo from '../../logo1.png';
import BlogPostService from '../../Services/BlogPostService';
import RecipeReviewCard from '../BlogCard/BlogCard';
import ResponsiveAppBar from '../Header/ResponsiveAppBar';


export default function HomePage() {
  BlogPostService.getAllBlogPosts().then((res) => {
    console.log(res); // ToDo: Remove
  })

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
