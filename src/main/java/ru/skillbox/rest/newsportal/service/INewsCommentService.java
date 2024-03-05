package ru.skillbox.rest.newsportal.service;

import ru.skillbox.rest.newsportal.model.NewsComment;

import java.util.List;

public interface INewsCommentService {

    List<NewsComment> findAll();

    NewsComment findById(Long id);

    NewsComment save(NewsComment newsComment);

    NewsComment update(NewsComment newsComment);

    void deleteById(Long id);
}
