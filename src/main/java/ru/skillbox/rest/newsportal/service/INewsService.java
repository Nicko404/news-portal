package ru.skillbox.rest.newsportal.service;

import ru.skillbox.rest.newsportal.model.News;
import ru.skillbox.rest.newsportal.web.model.request.NewsFilter;

import java.util.List;

public interface INewsService {

    List<News> findAll(NewsFilter newsFilter);

    List<News> filterBy(NewsFilter filter);

    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);

}
