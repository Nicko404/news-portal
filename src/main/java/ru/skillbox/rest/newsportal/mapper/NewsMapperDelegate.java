package ru.skillbox.rest.newsportal.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ru.skillbox.rest.newsportal.model.News;
import ru.skillbox.rest.newsportal.repository.DatabaseNewsCommentRepository;
import ru.skillbox.rest.newsportal.service.IClientService;
import ru.skillbox.rest.newsportal.service.INewsCategoryService;
import ru.skillbox.rest.newsportal.web.model.request.UpsertNewsRequest;
import ru.skillbox.rest.newsportal.web.model.response.NewsResponse;

import java.util.List;
import java.util.stream.Collectors;

public abstract class NewsMapperDelegate implements NewsMapper {

    @Autowired
    private IClientService clientService;

    @Autowired
    private INewsCategoryService newsCategoryService;

    @Autowired
    private DatabaseNewsCommentRepository newsCommentRepository;

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();

        news.setTitle(request.getTitle());
        news.setDescription(request.getDescription());
        news.setBody(request.getBody());
        news.setClient(clientService.findById(request.getClientId()));
        news.setCategory(newsCategoryService.findById(request.getCategoryId()));

        return news;
    }

    @Override
    public News requestToNews(Long newsId, UpsertNewsRequest request) {
        News news = requestToNews(request);
        news.setId(newsId);
        return news;
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse response = new NewsResponse();

        response.setId(news.getId());
        response.setTitle(news.getTitle());
        response.setDescription(news.getDescription());
        response.setBody(news.getBody());
        response.setCommentCount(newsCommentRepository.countByNews(news));

        return response;
    }

    @Override
    public List<NewsResponse> newsListToResponseList(List<News> newsList) {
        return newsList.stream()
                .map(this::newsToResponse)
                .collect(Collectors.toList());
    }
}
