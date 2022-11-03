package com.example.demo.domain.blogpost;

import com.example.demo.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "blog_post")
public class BlogPost {
    @Size(min = 1, max = 100)
    private String title;

    @Size(min = 0, max = 1000)
    private String text;

    @Size(min = 0, max = 50)
    private String category;

    @OneToMany
    private User author;


    public BlogPost() {
    }

    public BlogPost(String title, String text, String category, User author) {
        this.title = title;
        this.text = text;
        this.category = category;
        this.author = author;
    }

    public BlogPost setTitle(String title) {this.title = title; return this;}
    public BlogPost setText(String text) {this.text = text; return this;}
    public BlogPost setCategory(String category) {this.category = category; return this;}
    public BlogPost setAuthor(User author) {this.author = author; return this;}

    public String getTitle() {return title;}
    public String getText() {return text;}
    public String getCategory() {return category;}
    public User getAuthor() {return author;}
}
