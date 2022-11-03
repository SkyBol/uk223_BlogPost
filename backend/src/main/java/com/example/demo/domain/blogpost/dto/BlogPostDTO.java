package com.example.demo.domain.blogpost.dto;

import com.example.demo.core.generic.ExtendedDTO;
import com.example.demo.domain.user.User;

import java.util.UUID;

public class BlogPostDTO extends ExtendedDTO {

    private String title;

    private String text;

    private String category;

    private User author;

    public BlogPostDTO() {}

    public BlogPostDTO(UUID id, String title, String text, String category, User author) {
        super(id);
        this.title = title;
        this.text = text;
        this.category = category;
        this.author = author;
    }

    public void setTitle(String title) {this.title = title;}
    public void setText(String text) {this.text = text;}
    public void setCategory(String category) {this.category = category;}
    public void setAuthor(User author) {this.author = author;}

    public String getTitle() {return title;}
    public String getText() {return text;}
    public String getCategory() {return category;}
    public User getAuthor() {return author;}
}
