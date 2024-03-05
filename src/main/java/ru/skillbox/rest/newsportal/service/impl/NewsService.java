package ru.skillbox.rest.newsportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skillbox.rest.newsportal.aop.OnlyOwnerCanDo;
import ru.skillbox.rest.newsportal.exception.EntityNotFoundException;
import ru.skillbox.rest.newsportal.model.News;
import ru.skillbox.rest.newsportal.repository.DatabaseNewsRepository;
import ru.skillbox.rest.newsportal.repository.NewsSpecification;
import ru.skillbox.rest.newsportal.service.INewsService;
import ru.skillbox.rest.newsportal.utils.BeanUtils;
import ru.skillbox.rest.newsportal.web.model.request.NewsFilter;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService implements INewsService {

    private final DatabaseNewsRepository newsRepository;

    @Override
    public List<News> findAll(NewsFilter filter) {
        return newsRepository.findAll(NewsSpecification.withFilter(filter), PageRequest.of(
                filter.getPageNumber(), filter.getPageSize()
        )).getContent();
    }

    @Override
    public List<News> filterBy(NewsFilter filter) {
        return newsRepository.findAll(NewsSpecification.withFilter(filter),
                PageRequest.of(
                        filter.getPageNumber(), filter.getPageSize()
                )).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Новость с ID {0} не найдена!", id
                )
                ));
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    @OnlyOwnerCanDo
    public News update(News news) {
        News existedNews = findById(news.getId());

        BeanUtils.copyNonNullProperties(news, existedNews);

        return newsRepository.save(existedNews);
    }

    @Override
    @OnlyOwnerCanDo
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
