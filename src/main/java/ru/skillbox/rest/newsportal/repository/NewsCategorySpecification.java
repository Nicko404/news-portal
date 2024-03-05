package ru.skillbox.rest.newsportal.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.rest.newsportal.model.NewsCategory;
import ru.skillbox.rest.newsportal.web.model.request.NewsCategoryFilter;

import java.util.Objects;

public interface NewsCategorySpecification {

    static Specification<NewsCategory> withFilter(NewsCategoryFilter newsCategoryFilter) {
        return Specification.where(byCategoryName(newsCategoryFilter.getCategoryName()));
    }

    static Specification<NewsCategory> byCategoryName(String categoryName) {
        return (root, query, cb) -> {
            if (Objects.isNull(categoryName)) return null;

            return cb.equal(root.get("categoryName"), categoryName);
        };
    }
}
