package com.example.demo.domain.blogpost;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, String> {
}
