package com.example.demo.domain.blogpost;

import com.example.demo.domain.blogpost.dto.BlogPostDTO;
import com.example.demo.domain.blogpost.dto.BlogPostExtendedDTO;
import com.example.demo.domain.blogpost.dto.BlogPostExtendedMapper;
import com.example.demo.domain.blogpost.dto.BlogPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(blogPostExtendedMapper.toDTO(service.create(blogPostMapper.fromDTO(blogPostDTO))));
    }

    @GetMapping("")
    public ResponseEntity<List<BlogPostExtendedDTO>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    @GetMapping("/page")
    public ResponseEntity<List<BlogPostExtendedDTO>> getAllWithPageAndLimit(@PathParam("page") long page, @PathParam("limit") long limit) {
        return ResponseEntity.ok(service.getAllFromPageWithLimit(page, limit).stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    @GetMapping("/{blogId}/getNext")
    public ResponseEntity<List<BlogPostExtendedDTO>> getAllWithLimitAfterId(@PathVariable("blogId") String blogId, @PathParam("limit") long limit) {
        return ResponseEntity.ok(service.getAllWithLimitAfterId(UUID.fromString(blogId), limit).stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BlogPostExtendedDTO>> findAllByAuthorId(@PathVariable("authorId") String authorId) {
        return ResponseEntity.ok(service.findAllByAuthorId(UUID.fromString(authorId)).stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogPostExtendedDTO> getBlog(@PathVariable("blogId") String blogId) {
        return ResponseEntity.ok(blogPostExtendedMapper.toDTO(service.getById(UUID.fromString(blogId))));
    }

    @PutMapping("/{blogId}")
    @PreAuthorize("hasAuthority('BLOGPOST_UPDATE')")
    public ResponseEntity<BlogPostExtendedDTO> updateBlog(@PathVariable("blogId") String blogId,
                                               @Valid @RequestBody BlogPostDTO blogPost) {
        return ResponseEntity.ok(blogPostExtendedMapper.toDTO(service.updateById(UUID.fromString(blogId), blogPostMapper.fromDTO(blogPost))));
    }

    @DeleteMapping("/{blogId}")
    @PreAuthorize("hasAuthority('BLOGPOST_DELETE')")
    public ResponseEntity<Void> deleteById(@PathVariable("blogId") String blogId) {
        service.deleteById(UUID.fromString(blogId));
        return ResponseEntity.ok().build();
    }
}
