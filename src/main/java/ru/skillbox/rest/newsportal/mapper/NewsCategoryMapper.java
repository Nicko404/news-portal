package ru.skillbox.rest.newsportal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skillbox.rest.newsportal.model.NewsCategory;
import ru.skillbox.rest.newsportal.web.model.request.UpsertNewsCategoryRequest;
import ru.skillbox.rest.newsportal.web.model.response.NewsCategoryListResponse;
import ru.skillbox.rest.newsportal.web.model.response.NewsCategoryResponse;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class})
public interface NewsCategoryMapper {

    NewsCategory requestToNewsCategory(UpsertNewsCategoryRequest request);

    @Mapping(source = "newsCategoryId", target = "id")
    NewsCategory requestToNewsCategory(Long newsCategoryId, UpsertNewsCategoryRequest request);

    NewsCategoryResponse newsCategoryToResponse(NewsCategory newsCategory);

    default NewsCategoryListResponse newsCategoryListToNewsCategoryListResponse(List<NewsCategory> newsCategoryList) {
        NewsCategoryListResponse newsCategoryListResponse = new NewsCategoryListResponse();

        newsCategoryListResponse.setNewsCategoryList(newsCategoryList.stream()
                .map(this::newsCategoryToResponse)
                .collect(Collectors.toList()));

        return newsCategoryListResponse;
    }
}
