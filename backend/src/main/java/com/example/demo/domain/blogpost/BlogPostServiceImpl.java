package com.example.demo.domain.blogpost;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    private BlogPostRepository repository;

    @Override
    public BlogPost create(BlogPost newEntity) {
        log.info("Attempting to save Entry {}", newEntity);
        BlogPost post = repository.save(newEntity);
        log.info("Successfully saved Entry {}", post);
        return post;
    }

    @Override
    public List<BlogPost> getAll() {
        log.info("Attempting to find All Entries");
        List<BlogPost> foundItems = (List<BlogPost>) repository.findAll();
        log.info("Successfully found All Entries");
        return foundItems;
    }
    @Override
    public List<BlogPost> getAllWithLimitAfterId(UUID blogId, long limit) {
        log.info("Attempting to find Entries following Id {} with limit {}", blogId, limit);
        List<BlogPost> foundItems = (List<BlogPost>) repository.findAll();
        log.info("Successfully found Entries following Id {} with limit {}", blogId, limit);
        return foundItems;
    }

    @Override
    public BlogPost getById(UUID blogId) {
        log.info("Attempting to find entry with id {}", blogId);
        Optional<BlogPost> post = repository.findById(blogId);
        if (post.isEmpty()) {
            log.warn("BlogPost with ID {} not found", blogId);
            throw new NoSuchElementException();
        }
        log.info("Found entry with id {}", blogId);
        return post.get();
    }

    @Override
    public BlogPost updateById(UUID blogId, BlogPost newEntity) {
        // Already Covered By Tests
        BlogPost post = getById(blogId);
        post.setCategory(newEntity.getCategory());
        post.setAuthor(newEntity.getAuthor());
        post.setText(newEntity.getText());
        post.setTitle(newEntity.getTitle());
        log.info("Attempting to update BlogPost with id {} to {}", blogId, newEntity);
        BlogPost updatedPost = repository.save(post);
        log.info("BlogPost with id {} updated to {}", blogId, updatedPost);
        return updatedPost;
    }

    @Override
    public void deleteById(UUID blogId) {
        log.info("Attempting to delete entry with id {}", blogId);
        repository.deleteById(blogId);
        log.info("Successfully deleted entry with id {}", blogId);
    }
}
