package ru.skillbox.rest.newsportal.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.rest.newsportal.model.NewsComment;
import ru.skillbox.rest.newsportal.web.model.request.UpsertNewsCommentRequest;
import ru.skillbox.rest.newsportal.web.model.response.NewsCommentResponse;


@DecoratedWith(NewsCommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsCommentMapper {

    NewsComment requestToNewsComment(UpsertNewsCommentRequest request);

    @Mapping(source = "newsCommentId", target = "id")
    NewsComment requestToNewsComment(Long newsCommentId, UpsertNewsCommentRequest request);

    NewsCommentResponse newsCommentToResponse(NewsComment newsComment);
}
