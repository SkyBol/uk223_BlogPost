package com.example.demo.domain.blogpost;

import com.example.demo.domain.blogpost.dto.BlogPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Set;

@RequestMapping("/blog")
@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService service;

    @PostMapping("/")
    public ResponseEntity<BlogPost> createBlog(@Valid BlogPostDTO blogPostDTO) {

    }

    @GetMapping({"/", ""})
    public ResponseEntity<Set<BlogPost>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogPost> getBlog(@PathParam("blogId") String blogId) {
        return ResponseEntity.ok(service.getById(blogId));
    }


}
