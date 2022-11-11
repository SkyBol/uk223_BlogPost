package com.example.demo.domain.blogpost;

import com.example.demo.domain.blogpost.dto.BlogPostDTO;
import com.example.demo.domain.blogpost.dto.BlogPostExtendedDTO;
import com.example.demo.domain.blogpost.dto.BlogPostExtendedMapper;
import com.example.demo.domain.blogpost.dto.BlogPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RequestMapping("/blog")
@Controller
public class BlogPostController {

    private final BlogPostService service;
    private final BlogPostMapper blogPostMapper;
    private final BlogPostExtendedMapper blogPostExtendedMapper;

    @Autowired
    public BlogPostController(BlogPostService service, BlogPostMapper blogPostMapper, BlogPostExtendedMapper blogPostExtendedMapper) {
        this.service = service;
        this.blogPostMapper = blogPostMapper;
        this.blogPostExtendedMapper = blogPostExtendedMapper;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('BLOGPOST_CREATE')")
    public ResponseEntity<BlogPostExtendedDTO> createBlog(@Valid @RequestBody BlogPostDTO blogPostDTO) {
        return ResponseEntity.ok(blogPostExtendedMapper.toDTO(service.expandedSave(blogPostMapper.fromDTO(blogPostDTO))));
    }

    @GetMapping("")
    public ResponseEntity<List<BlogPostExtendedDTO>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    @GetMapping("/page")
    public ResponseEntity<List<BlogPostExtendedDTO>> getAllWithPageAndLimit(@PathParam("page") int page, @PathParam("limit") int limit) {
        return ResponseEntity.ok(service.findAll(Pageable.ofSize(limit).withPage(page)).stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    @GetMapping("/{blogId}/getNext")
    public ResponseEntity<List<BlogPostExtendedDTO>> getAllWithLimitAfterId(@PathVariable("blogId") String blogId, @PathParam("limit") long limit) {
        return ResponseEntity.ok(service.findAllWithLimitAfterId(UUID.fromString(blogId), limit).stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BlogPostExtendedDTO>> findAllByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(service.findAllByUserId(UUID.fromString(userId)).stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogPostExtendedDTO> getBlog(@PathVariable("blogId") String blogId) {
        return ResponseEntity.ok(blogPostExtendedMapper.toDTO(service.findById(UUID.fromString(blogId))));
    }

    @PutMapping("/{blogId}")
    @PreAuthorize("hasAuthority('BLOGPOST_UPDATE') && @blogPostPermissionEvaluator.isPostForUser(authentication.principal.user, #blogId)")
    public ResponseEntity<BlogPostExtendedDTO> updateBlog(@PathVariable("blogId") String blogId, @Valid @RequestBody BlogPostDTO blogPost) {
        return ResponseEntity.ok(blogPostExtendedMapper.toDTO(service.expandedUpdateById(UUID.fromString(blogId), blogPostMapper.fromDTO(blogPost))));
    }

    @DeleteMapping("/{blogId}")
    @PreAuthorize("hasAuthority('BLOGPOST_DELETE')")
    public ResponseEntity<Void> deleteById(@PathVariable("blogId") String blogId) {
        return ResponseEntity.ok(service.deleteById(UUID.fromString(blogId)));
    }
}
