package com.example.demo.domain.blogpost;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Log4j2
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    private BlogPostRepository repository;

    @Override
    public BlogPost create(BlogPost newEntity) {
        return repository.save(newEntity);
    }

    @Override
    public Set<BlogPost> getAll() {
        return (Set<BlogPost>) repository.findAll();
    }

    @Override
    public BlogPost getById(String blogId) {
        Optional<BlogPost> post = repository.findById(blogId);
        if (post.isEmpty()) {
            log.warn("BlogPost with {} not found", blogId);
            throw new NoSuchElementException();
        }
        return post.get();
    }

    @Override
    public BlogPost updateById(String blogId, BlogPost newEntity) {

    }

    @Override
    public void deleteById(String blogId) {
        repository.deleteById(blogId);
    }
}
