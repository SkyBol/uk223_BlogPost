package com.example.demo.domain.blogpost;

import com.example.demo.domain.blogpost.dto.BlogPostDTO;
import com.example.demo.domain.blogpost.dto.BlogPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RequestMapping("/blog")
@RestController
public class BlogPostController {

    private final BlogPostService service;
    private final BlogPostMapper blogPostMapper;

    @Autowired
    public BlogPostController(BlogPostService service, BlogPostMapper blogPostMapper) {
        this.service = service;
        this.blogPostMapper = blogPostMapper;
    }

    @PostMapping("")
    public ResponseEntity<BlogPostDTO> createBlog(@Valid @RequestBody BlogPostDTO blogPostDTO) {
        return ResponseEntity.ok(blogPostMapper.toDTO(service.create(blogPostMapper.fromDTO(blogPostDTO))));
    }

    @GetMapping("")
    public ResponseEntity<List<BlogPostDTO>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(blogPostMapper::toDTO).toList());
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogPostDTO> getBlog(@PathVariable("blogId") String blogId) {
        return ResponseEntity.ok(blogPostMapper.toDTO(service.getById(UUID.fromString(blogId))));
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<BlogPostDTO> updateBlog(@PathVariable("blogId") String blogId,
                                               @Valid @RequestBody BlogPostDTO blogPost) {
        return ResponseEntity.ok(blogPostMapper.toDTO(service.updateById(UUID.fromString(blogId), blogPostMapper.fromDTO(blogPost))));
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteById(@PathVariable("blogId") String blogId) {
        service.deleteById(UUID.fromString(blogId));
        return ResponseEntity.ok().build();
    }
}
