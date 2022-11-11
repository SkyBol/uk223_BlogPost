package com.example.demo.domain.blogpost;

import com.example.demo.domain.blogpost.dto.BlogPostDTO;
import com.example.demo.domain.blogpost.dto.BlogPostExtendedDTO;
import com.example.demo.domain.blogpost.dto.BlogPostExtendedMapper;
import com.example.demo.domain.blogpost.dto.BlogPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    /**
     * Creates a new BlogPost Entry in the DataBase
     * @param blogPostDTO The Post to create
     * @return a BlogPostExtendedDTO with the data of the created object
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('BLOGPOST_CREATE')")
    public ResponseEntity<BlogPostExtendedDTO> createBlog(@Valid @RequestBody BlogPostDTO blogPostDTO) {
        return new ResponseEntity<>(blogPostExtendedMapper.toDTO(service.expandedSave(blogPostMapper.fromDTO(blogPostDTO))), HttpStatus.CREATED);
    }

    /**
     * @return all BlogPosts
     */
    @GetMapping("")
    public ResponseEntity<List<BlogPostExtendedDTO>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    /**
     * @param page The Page to get
     * @param limit The amount of BlogPosts to get
     * @return a limited amount of BlogPosts in the given Range
     */
    @GetMapping("/page")
    public ResponseEntity<List<BlogPostExtendedDTO>> getAllWithPageAndLimit(@PathParam("page") int page, @PathParam("limit") int limit) {
        return ResponseEntity.ok(service.findAll(Pageable.ofSize(limit).withPage(page)).stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    /**
     * This Function gets a limited amount of BlogPosts, which have been created before a given BlogPost
     *
     * @param blogId The ID of the given BlogPost
     * @param limit The amount of BlogPosts to get
     * @return a limited amount of BlogPosts, created before a given BlogPost
     */
    @GetMapping("/{blogId}/getNext")
    public ResponseEntity<List<BlogPostExtendedDTO>> getAllWithLimitAfterId(@PathVariable("blogId") String blogId, @PathParam("limit") long limit) {
        return ResponseEntity.ok(service.findAllWithLimitAfterId(UUID.fromString(blogId), limit).stream().map(blogPostExtendedMapper::toDTO).toList());
    }

    /**
     *
     * This functions retrieves all the BlogPosts made by the user
     *
     * @param userId The Author of the Post
     * @return
     */
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
        return new ResponseEntity<>(service.deleteById(UUID.fromString(blogId)), HttpStatus.NO_CONTENT);
    }
}
