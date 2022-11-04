package com.example.demo.domain.blogpost.dto;

import com.example.demo.domain.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class BlogPostExtendedDTO extends BlogPostDTO {

    private LocalDateTime creationTime;

    private LocalDateTime editTime;

    public BlogPostExtendedDTO() {}

    public BlogPostExtendedDTO(UUID id, String title, String text, String category, User author, LocalDateTime creationTime, LocalDateTime editTime) {
        super(id, title, text, category, author);
        this.creationTime = creationTime;
        this.editTime = editTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {this.creationTime = creationTime;}
    public void setEditTime(LocalDateTime editTime) {this.editTime = editTime;}

    public LocalDateTime getCreationTime() {return creationTime;}
    public LocalDateTime getEditTime() {return editTime;}
}
