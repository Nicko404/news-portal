package ru.skillbox.rest.newsportal.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ru.skillbox.rest.newsportal.model.NewsComment;
import ru.skillbox.rest.newsportal.service.IClientService;
import ru.skillbox.rest.newsportal.service.INewsService;
import ru.skillbox.rest.newsportal.web.model.request.UpsertNewsCommentRequest;

public abstract class NewsCommentMapperDelegate implements NewsCommentMapper {

    @Autowired
    private IClientService clientService;

    @Autowired
    private INewsService newsService;

    @Override
    public NewsComment requestToNewsComment(UpsertNewsCommentRequest request) {
        NewsComment newsComment = new NewsComment();

        newsComment.setCommentary(request.getCommentary());
        newsComment.setClient(clientService.findById(request.getClientId()));
        newsComment.setNews(newsService.findById(request.getNewsId()));

        return newsComment;
    }

    @Override
    public NewsComment requestToNewsComment(Long newsCommentId, UpsertNewsCommentRequest request) {
        NewsComment newsComment = requestToNewsComment(request);
        newsComment.setId(newsCommentId);
        return newsComment;
    }
}
