package com.example.demo.domain.blogpost;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

/**
 * Example Test
 */
@SpringBootTest
@AutoConfigureMockMvc
class BlogPostServiceImplTest {
    @Autowired
    private BlogPostService blogPostService;

    @MockBean
    private BlogPostRepository blogPostRepository;

    @Test
    void findAllByUserId_shouldCallDB () {
        UUID userId = UUID.randomUUID();
        List<BlogPost> blogPostList = List.of(new BlogPost());

        given(blogPostRepository.findAllByUserId(userId)).willReturn(blogPostList);

        Assertions.assertEquals(blogPostList, blogPostService.findAllByUserId(userId));
    }
}