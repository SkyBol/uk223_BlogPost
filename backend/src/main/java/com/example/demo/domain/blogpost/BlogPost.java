package com.example.demo.domain.blogpost;

import com.example.demo.core.generic.ExtendedEntity;
import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "blog_post")
public class BlogPost extends ExtendedEntity {
    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Size(min = 0, max = 1000)
    private String text;

    @NotNull
    @Size(min = 0, max = 50)
    private String category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_author_id")
    @JsonIgnore
    private User author;

    @NotNull
    private LocalDateTime creationTime;

    private LocalDateTime editTime;


    public BlogPost() {}
    public BlogPost(UUID id, String title, String text, String category, User author, LocalDateTime creationTime, LocalDateTime editTime) {
        super(id);
        this.title = title;
        this.text = text;
        this.category = category;
        this.author = author;
        this.creationTime = creationTime;
        this.editTime = editTime;
    }

    public void setTitle(String title) {this.title = title;}
    public void setText(String text) {this.text = text;}
    public void setCategory(String category) {this.category = category;}
    public void setAuthor(User author) {this.author = author;}
    public void setCreationTime(LocalDateTime creationTime) {this.creationTime = creationTime;}
    public void setEditTime(LocalDateTime editTime) {this.editTime = editTime;}

    public String getTitle() {return title;}
    public String getText() {return text;}
    public String getCategory() {return category;}
    public User getAuthor() {return author;}
    public LocalDateTime getCreationTime() {return creationTime;}
    public LocalDateTime getEditTime() {return editTime;}

    @Override
    public String toString() {
        return "BlogPost{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", category='" + category + '\'' +
                ", author=" + author +
                '}';
    }
}
