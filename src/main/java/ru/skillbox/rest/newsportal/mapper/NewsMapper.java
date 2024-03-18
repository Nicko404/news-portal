package ru.skillbox.rest.newsportal.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.rest.newsportal.model.News;
import ru.skillbox.rest.newsportal.web.model.request.UpsertNewsRequest;
import ru.skillbox.rest.newsportal.web.model.response.NewsListResponse;
import ru.skillbox.rest.newsportal.web.model.response.NewsResponse;
import ru.skillbox.rest.newsportal.web.model.response.SingleNewsResponse;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {

    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, UpsertNewsRequest request);

    NewsResponse newsToResponse(News news);

    SingleNewsResponse newsToSingleNews(News news);

    List<NewsResponse> newsListToResponseList(List<News> newslist);

    default NewsListResponse newsListToNewsListResponse(List<News> newsList) {
        NewsListResponse response = new NewsListResponse();

        response.setNews(newsList.stream()
                .map(this::newsToResponse)
                .collect(Collectors.toList()));

        return response;
    }
}
