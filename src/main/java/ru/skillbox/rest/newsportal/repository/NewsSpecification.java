package ru.skillbox.rest.newsportal.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.rest.newsportal.model.News;
import ru.skillbox.rest.newsportal.web.model.request.NewsFilter;

import java.time.Instant;
import java.util.Objects;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter newsFilter) {
        return Specification.where(byTitle(newsFilter.getTitle()))
                .and(byCategoryId(newsFilter.getCategoryId()))
                .and(byClientId(newsFilter.getClientId()))
                .and(byCreatedAtBefore(newsFilter.getCreatedBefore()))
                .and(byUpdatedAtBefore(newsFilter.getUpdatedBefore()));
    }

    static Specification<News> byTitle(String title) {
        return (root, query, cb) -> {
            if (Objects.isNull(title)) return null;

            return cb.equal(root.get("title"), title);
        };
    }

    static Specification<News> byCategoryId(Long categoryId) {
        return (root, query, cb) -> {
            if (Objects.isNull(categoryId)) return null;

            return cb.equal(root.get("category").get("id"), categoryId);
        };
    }

    static Specification<News> byClientId(Long clientId) {
        return (root, query, cb) -> {
            if (Objects.isNull(clientId)) return null;

            return cb.equal(root.get("client").get("id"), clientId);
        };
    }

    static Specification<News> byCreatedAtBefore(Instant createdBefore) {
        return (root, query, cb) -> {
          if (Objects.isNull(createdBefore)) return null;

          return cb.lessThanOrEqualTo(root.get("createdAt"), createdBefore);
        };
    }

    static Specification<News> byUpdatedAtBefore(Instant updatedBefore) {
        return (root, query, cb) -> {
            if (Objects.isNull(updatedBefore)) return null;

            return cb.lessThanOrEqualTo(root.get("updatedAt"), updatedBefore);
        };
    }
}
