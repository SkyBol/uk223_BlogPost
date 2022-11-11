import { Button, Grid, Paper, TextField } from "@mui/material";
import { Form, Formik } from "formik";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import * as Yup from 'yup';
import BlogPostService from "../../Services/BlogPostService";
import UserService from "../../Services/UserService";
import { CreatePost } from "../../types/models/CreatePost.model";
import { User } from "../../types/models/User.model";

const validationSchema = Yup.object().shape({
    title: Yup.string().required().max(100),
    text: Yup.string().required().max(1000),
    category: Yup.string().required().max(50),
});

const CreatePostPage = () => {
    const { userId } = useParams();
    const navigate = useNavigate();
    const [user, setUser] = useState<User>({
        id: '',
        email: '',
        firstName: '',
        lastName: '',
        roles: []});

    useEffect(() => {
        UserService.getUser(userId)
        .then((res) => setUser(res.data))
        .catch((error) => console.log(error));
    }, [userId])

    const paperStyle = {
        padding: 20,
        width: '50%',
        margin: '20px auto',
    };

    const buttonStyle = {
        margin: '20px 0',
    };

    const handleSubmit = (blog: CreatePost) => {
        blog.author = user;
        BlogPostService.createBlogPost(blog).then(() => alert("Blog posted")).catch((error) => console.log(error));
        navigate("/");
    };


    return(
        <Grid>
            <Paper style={paperStyle}>
                <Grid>
                    <h1>Create a Post</h1>
                </Grid>
                <Formik
                    initialValues={{
                        title: '',
                        text: '',
                        category: '',
                        author: user,
                    }}
                    onSubmit={handleSubmit}
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

export default CreatePostPage;