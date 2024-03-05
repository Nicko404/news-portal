package ru.skillbox.rest.newsportal.service;

import ru.skillbox.rest.newsportal.model.NewsCategory;
import ru.skillbox.rest.newsportal.web.model.request.NewsCategoryFilter;

import java.util.List;

public interface INewsCategoryService {

    List<NewsCategory> findAll(NewsCategoryFilter newsCategoryFilter);

    NewsCategory findById(Long id);

    NewsCategory save(NewsCategory newsCategory);

    NewsCategory update(NewsCategory newsCategory);

    void deleteById(Long id);
}
