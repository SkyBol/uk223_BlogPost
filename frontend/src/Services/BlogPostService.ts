import api from '../config/Api';
import { BlogPost } from '../types/models/BlogPost.model';
import { CreatePost } from '../types/models/CreatePost.model';

const BlogPostService = {
    createBlogPost: (blog: CreatePost) => {
        return api.post(`/blog/create`, blog).catch((error) => console.log(error));
    },
    getAllBlogPosts: () => {
        return api.get(`/blog/`);
    },
    getBlogPost: (id : string) => {
        return api.get(`/blog/${id}`);
    },
    updateBlogPost: (id : string, blogPost : BlogPost) => {
        return api.post(`/blog/${id}`, {...blogPost});
    },
    deleteBlogPost: (id : string) => {
        return api.post(`/blog/${id}`);
    },
}

export default BlogPostService;