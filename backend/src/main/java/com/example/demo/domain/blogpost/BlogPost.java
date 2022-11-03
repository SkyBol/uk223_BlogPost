package com.example.demo.domain.blogpost;

import com.example.demo.core.generic.ExtendedEntity;
import com.example.demo.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "blog_post")
public class BlogPost extends ExtendedEntity {
    @Size(min = 1, max = 100)
    @NotNull
    private String title;

    @Size(min = 0, max = 1000)
    private String text;

    @Size(min = 0, max = 50)
    private String category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_author_id")
    @JsonIgnore
    private User author;


    public BlogPost() {}

    public BlogPost(UUID id, String title, String text, String category, User author) {
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
