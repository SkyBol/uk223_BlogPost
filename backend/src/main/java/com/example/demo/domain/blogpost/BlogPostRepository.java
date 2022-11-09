package com.example.demo.domain.blogpost;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BlogPostRepository extends CrudRepository<BlogPost, UUID> {
    List<BlogPost> findAllByGreaterThanEqualCreationTime(LocalDateTime creationTime);
}
