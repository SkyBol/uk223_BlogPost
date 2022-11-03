package com.example.demo.domain.blogpost;

import java.util.List;
import java.util.UUID;

public interface BlogPostService {
    BlogPost create(BlogPost newEntity);

    List<BlogPost> getAll();

    BlogPost getById(UUID blogId);

    BlogPost updateById(UUID blogId, BlogPost newEntity);

    void deleteById(UUID blogId);
}
