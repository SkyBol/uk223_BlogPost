package com.example.demo.domain.blogpost.dto;

import com.example.demo.core.generic.ExtendedDTO;

import java.util.UUID;

public class BlogPostDTO extends ExtendedDTO {

    private String title;

    private String text;

    private String category;

    private String userId;

    public BlogPostDTO() {}
    public BlogPostDTO(String title, String text, String category, String userId) {
        this.title = title;
        this.text = text;
        this.category = category;
        this.userId = userId;
    }
    public BlogPostDTO(UUID id, String title, String text, String category, String userId) {
        super(id);
        this.title = title;
        this.text = text;
        this.category = category;
        this.userId = userId;
    }

    public void setTitle(String title) {this.title = title;}
    public void setText(String text) {this.text = text;}
    public void setCategory(String category) {this.category = category;}
    public void setUserId(String userId) {this.userId = userId;}

    public String getTitle() {return title;}
    public String getText() {return text;}
    public String getCategory() {return category;}
    public String getUserId() {return userId;}
}
