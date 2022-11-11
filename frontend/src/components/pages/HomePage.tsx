import { Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import logo from '../../logo1.png';
import BlogPostService from '../../Services/BlogPostService';
import { BlogPost } from '../../types/models/BlogPost.model';
import BlogCard from '../BlogCard/BlogCard';
import Header from '../Header/Header';

export default function HomePage() {
  const [blogs, setBlogs] = useState<BlogPost[]>([])

  useEffect(() => {
    BlogPostService.getAllBlogPosts().then((res) => {
    setBlogs(res.data);
    })
  }, []);

  return (
    <div>
    <Header/>
    <Typography sx={{ml: 10, fontSize: 'h2.fontSize'}}>Blogs</Typography>
    {
      blogs.map((blog) => {
        return (
          <BlogCard
            key={blog.id}
            blogPost={blog}
            isHomePage={true}
          />
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
