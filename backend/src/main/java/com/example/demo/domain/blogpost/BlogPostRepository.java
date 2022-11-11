package com.example.demo.domain.blogpost;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BlogPostRepository extends CrudRepository<BlogPost, UUID> {
    // Should have worked after https://stackoverflow.com/questions/45430202/spring-jpa-method-to-find-entities-with-beforeandequal-a-date-and-afterandequal, but doesn't
    @Query(value = "SELECT * FROM blog_post WHERE creation_time < ?1 ORDER BY creation_time LIMIT ?2", nativeQuery = true)
    List<BlogPost> findByGreaterThanCreationTimeAndWithLimit(LocalDateTime creationTime, long limit);

    @Query(value = "SELECT * FROM blog_post ORDER BY creation_time LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<BlogPost> findByLimitAndWithOffset(long limit, long offset);

    @Query(nativeQuery = true, value = "SELECT bp.* FROM blog_post bp WHERE bp.user_author_id = :authorId")
    List<BlogPost> findAllByAuthorId(UUID authorId);
}
