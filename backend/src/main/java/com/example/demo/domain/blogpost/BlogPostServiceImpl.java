package com.example.demo.domain.blogpost;

import com.example.demo.domain.role.Role;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.domain.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    private BlogPostRepository repository;
    @Autowired
    private UserService userService;

    @Override
    public BlogPost create(BlogPost newEntity) {
        log.info("Attempting to save Entry {}", newEntity);
        newEntity.setCreationTime(LocalDateTime.now());
        newEntity.setUser(getCurrentUser());
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
        BlogPost post = getById(blogId);

        log.info("Attempting to find Entries following Id {} with limit {}", blogId, limit);
        List<BlogPost> foundItems = repository.findByGreaterThanCreationTimeAndWithLimit(post.getCreationTime(), limit);
        log.info("Successfully found Entries following Id {} with limit {}", blogId, limit);
        return foundItems;
    }

    @Override
    public List<BlogPost> getAllFromPageWithLimit(long page, long limit) {
        log.info("Attempting to find Entries from page {} with limit {}", page, limit);
        List<BlogPost> foundItems = (List<BlogPost>) repository.findByLimitAndWithOffset(limit, limit * page);
        log.info("Successfully found Entries from page {} with limit {}", page, limit);
        return foundItems;
    }

    @Override
    public BlogPost getById(UUID blogId) {
        log.info("Attempting to find entry with id {}", blogId);
        Optional<BlogPost> post = repository.findById(blogId);
        if (post.isEmpty()) {
            log.warn("BlogPost with ID {} not found", blogId);
            throw new NoSuchElementException("Blog Post with ID " + blogId + " not found");
        }
        log.info("Found entry with id {}", blogId);
        return post.get();
    }

    @Override
    public BlogPost updateById(UUID blogId, BlogPost newEntity) {
        // Already Covered By Tests
        BlogPost post = getById(blogId);
        if (isOwnPost(post)) {
            post.setCategory(newEntity.getCategory());
            post.setText(newEntity.getText());
            post.setTitle(newEntity.getTitle());
            post.setEditTime(LocalDateTime.now());
            log.info("Attempting to update BlogPost with id {} to {}", blogId, newEntity);
            BlogPost updatedPost = repository.save(post);
            log.info("BlogPost with id {} updated to {}", blogId, updatedPost);
            return updatedPost;
        } else {
            throw new AuthorizationServiceException("The Post ");
        }
    }

    @Override
    public void deleteById(UUID blogId) {
        log.info("Attempting to delete entry with id {}", blogId);
        repository.deleteById(blogId);
        log.info("Successfully deleted entry with id {}", blogId);
    }

    public boolean isOwnPost(BlogPost post) {
        User user = getCurrentUser();

        // If user is Admin, is automatically granted
        if (user.getRoles().stream().anyMatch((Role role) -> "ADMIN".equals(role.getName()))) {
            return true;
        } else return post.getUser().getId().equals(user.getId());
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.info("Attempting to find User with Email: {}", auth.getName());
        User user = userService.getByEmail(auth.getName());
        log.info("Found User {} with Email {}", user, auth.getName());

        return user;
    }
 }
