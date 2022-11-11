import { Grid, Paper, TextField, Button } from "@mui/material";
import { Formik, Form } from "formik";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import * as Yup from 'yup';
import BlogPostService from "../../Services/BlogPostService";
import UserService from "../../Services/UserService";
import { BlogPost } from "../../types/models/BlogPost.model";
import { User } from "../../types/models/User.model";


const validationSchema = Yup.object().shape({
    title: Yup.string().required().max(100),
    text: Yup.string().required().max(1000),
    category: Yup.string().required().max(50),
});


const EditPostPage = () => {
    const { userId } = useParams();
    const { blogPostId } = useParams();
    const navigate = useNavigate();
    const [user, setUser] = useState<User>({
        id: '',
        email: '',
        firstName: '',
        lastName: '',
        roles: []});

    const [blogPost, setBlogPost] = useState<BlogPost>({
        id: '',
        title: '',
        text: '',
        category: '',
        author: {id: '',
        firstName: '',
        lastName: '',
        email: '',
        roles: [],},
        creationTime: undefined,
        editTime: undefined,
    });

    useEffect(() => {
        UserService.getUser(userId)
        .then((res) => setUser(res.data))
        .catch((error) => console.log(error));
    }, [userId])

    useEffect(() => {
        BlogPostService.getBlogPost(blogPostId).then((res) => setBlogPost(res.data));
    }, [blogPostId]);

    const paperStyle = {
        padding: 20,
        width: '50%',
        margin: '20px auto',
    };

    const buttonStyle = {
        margin: '20px 0',
    };

    const initialValues = {
        id: blogPost.id,
    title: blogPost.title,
    text: blogPost.text,
    category: blogPost.category,
    author: blogPost.author,
    creationTime: blogPost.creationTime,
    editTime: blogPost.editTime,
    }

    const handleSubmit = (blog: BlogPost) => {
        blog.author = user;
        BlogPostService.updateBlogPost(blog.id, blog).then(() => {alert("Blog Post updated"); navigate('/')})
    };

return (
    <Grid>
            <Paper style={paperStyle}>
                <Grid>
                    <h1>Update a Post</h1>
                </Grid>
                <Formik
                    initialValues={initialValues}
                    onSubmit={handleSubmit}
                    enableReinitialize
                    validationSchema={validationSchema}>
                        {(props) => (
                            <Form onSubmit={props.handleSubmit}>
                                <TextField
                                    label='Title'
                                    id='title'
                                    placeholder="Enter title"
                                    fullWidth
                                    sx={{ mb: '10px' }}
                                    required
                                    onChange={props.handleChange}
                                    onBlur={props.handleBlur}
                                    value={props.values.title}
                                />
                                {props.errors.title && (
                                    <div id="feedback">{props.errors.title}</div>
                                )}

                                <TextField
                                    label='Text'
                                    id='text'
                                    placeholder="Enter text"
                                    fullWidth
                                    sx={{ mb: '10px' }}
                                    required
                                    multiline
                                    rows={5}
                                    onChange={props.handleChange}
                                    onBlur={props.handleBlur}
                                    value={props.values.text}
                                />
                                {props.errors.text && (
                                    <div id="feedback">{props.errors.text}</div>
                                )}

                                <TextField
                                    label='Category'
                                    id='category'
                                    placeholder="Enter category"
                                    fullWidth
                                    sx={{ mb: '10px' }}
                                    required
                                    onChange={props.handleChange}
                                    onBlur={props.handleBlur}
                                    value={props.values.category}
                                />
                                {props.errors.category && (
                                    <div id="feedback">{props.errors.category}</div>
                                )}

                                <Button
                                type="submit"
                                color="primary"
                                variant="contained"
                                style={buttonStyle}>
                                    Submit
                                </Button>
                            </Form>
                        )}
                </Formik>
            </Paper>
        </Grid>
);
}

export default EditPostPage;