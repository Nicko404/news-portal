package ru.skillbox.rest.newsportal.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<News> newsList = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<NewsComment> comments = new ArrayList<>();

    public void addNews(News news) {
        news.setClient(this);
        newsList.add(news);
    }

    public void addComment(NewsComment newsComment) {
        newsComment.setClient(this);
        comments.add(newsComment);
    }
}
