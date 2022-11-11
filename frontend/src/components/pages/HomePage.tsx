import { Typography } from '@mui/material';
import { useEffect, useState } from 'react';
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
          />
        )
      })
    }
    </div>
  );
}
