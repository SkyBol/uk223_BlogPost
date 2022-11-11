import { Button, Typography } from "@mui/material";
import {  useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import BlogPostService from "../../Services/BlogPostService";
import UserService from "../../Services/UserService";
import { BlogPost } from "../../types/models/BlogPost.model";
import { User } from '../../types/models/User.model';
import BlogCard from "../BlogCard/BlogCard";
import Header from "../Header/Header";


const UserPage = () => {
    const { userId } = useParams();
    const [user, setUser] = useState<User>();
    const [blogs, setBlogs] = useState<BlogPost[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        UserService.getUser(userId)
        .then((res) => setUser(res.data))
        .catch((error) => console.log(error));
    }, [userId])

    useEffect(() => {
        BlogPostService.getAllByAuthor(userId).then((res) => setBlogs(res.data)).catch((error) => console.log(error));
    }, [userId]);

    return(
        <div>
            <Header/>
            <Typography sx={{fontSize: 'h2.fontSize', ml: 10}}>Welcome back, {user?.firstName}</Typography>
            <Button variant="contained" sx={{ml: 10}} onClick={() => navigate(`/${user?.id}/edit`)}>Edit account</Button>
            <Typography sx={{fontSize: 'h2.fontSize', ml: 10}}>My Blog Posts</Typography>
            {blogs.length > 0 ? 
                blogs.map((blog) => {
                    return(
                        <BlogCard
                            key={blog.id}
                            blogPost={blog}
                            isHomePage={false}
                        />
                    );
                })
                : <Typography>No blog posts yet</Typography>    
            }
        </div>
    );
}

export default UserPage;