package com.example.demo.domain.blogpost;

import java.util.Set;

public interface BlogPostService {
    BlogPost create(BlogPost newEntity);

    Set<BlogPost> getAll();

    BlogPost getById(String blogId);

    BlogPost updateById(String blogId, BlogPost newEntity);

    void deleteById(String blogId);
}
