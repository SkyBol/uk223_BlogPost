import api from '../config/Api';
import { BlogPost } from '../types/models/BlogPost.model';

const BlogPostService = {
    createBlogPost: () => {
        return api.post(`/blog/`);
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