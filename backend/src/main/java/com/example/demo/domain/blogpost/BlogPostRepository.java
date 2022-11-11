package com.example.demo.domain.blogpost;

import com.example.demo.core.generic.ExtendedRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogPostRepository extends ExtendedRepository<BlogPost> {
    @Query(value = "SELECT * FROM blog_post WHERE creation_time < ?1 ORDER BY creation_time LIMIT ?2", nativeQuery = true)
    List<BlogPost> findByGreaterThanCreationTimeAndWithLimit(LocalDateTime creationTime, long limit);

    @Query(value = "SELECT * FROM blog_post ORDER BY creation_time LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<BlogPost> findByLimitAndWithOffset(long limit, long offset);
}
