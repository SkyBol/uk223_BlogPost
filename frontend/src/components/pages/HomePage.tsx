import logo from '../../logo1.png';
import BlogPostService from '../../Services/BlogPostService';

export default function HomePage() {
  BlogPostService.getAllBlogPosts().then((res) => {
    console.log(res);
  })

  return (
    <div>
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
