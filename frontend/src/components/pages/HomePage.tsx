import { useEffect, useState } from 'react';
import logo from '../../logo1.png';
import BlogPostService from '../../Services/BlogPostService';
import { BlogPost } from '../../types/models/BlogPost.model';
import RecipeReviewCard from '../BlogCard/BlogCard';
import ResponsiveAppBar from '../Header/ResponsiveAppBar';


export default function HomePage() {
  const [blogs, setBlogs] = useState<BlogPost[]>([])

  useEffect(() => {
    BlogPostService.getAllBlogPosts().then((res) => {
    setBlogs(res.data);
    })
  }, []);
  

  return (
    <div>
    <ResponsiveAppBar/>
    {
      blogs.map((blog) => {
        return (
          <RecipeReviewCard 
            id={blog.id} 
            title={blog.title} 
            text={blog.text}
            author={blog.author}/>
        )
      })
    }
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
