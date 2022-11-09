import { Typography } from "@mui/material";
import {  useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import BlogPostService from "../../Services/BlogPostService";
import UserService from "../../Services/UserService";
import { BlogPost } from "../../types/models/BlogPost.model";
import { User } from '../../types/models/User.model';
import Header from "../Header/Header";


const UserPage = () => {
    const { userId } = useParams();
    const [user, setUser] = useState<User>();
    const [blogs, setBlogs] = useState<BlogPost[]>([]);

    useEffect(() => {
        UserService.getUser(userId)
        .then((res) => setUser(res.data))
        .catch((error) => console.log(error));
    }, [userId])

    useEffect(() => {
        BlogPostService.getAllByAuthor(userId)
        .then((res) => setBlogs(res.data))
        .catch((error) => console.log(error));
    }, [userId])

    return(
        <div>
            <Header/>
            <Typography sx={{fontSize: "h2.fontSize"}}>Hello, {user?.firstName}</Typography>
            <p>Last Name: {user?.lastName}</p>
            <p>E-Mail: {user?.email}</p>

            <Typography sx={{fontSize: "h2.fontSize"}}>My Blog Posts</Typography>
            {blogs.map((blog) => {
                return (
                    <Typography>{blog.title}</Typography>
                );
            })}
        </div>
    );
}

export default UserPage;