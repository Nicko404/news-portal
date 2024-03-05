package ru.skillbox.rest.newsportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.rest.newsportal.model.News;
import ru.skillbox.rest.newsportal.model.NewsComment;

public interface DatabaseNewsCommentRepository extends JpaRepository<NewsComment, Long> {

    Integer countByNews(News news);
}
