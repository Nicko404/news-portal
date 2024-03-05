package ru.skillbox.rest.newsportal.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.rest.newsportal.mapper.NewsMapper;
import ru.skillbox.rest.newsportal.model.News;
import ru.skillbox.rest.newsportal.service.INewsService;
import ru.skillbox.rest.newsportal.web.model.request.NewsFilter;
import ru.skillbox.rest.newsportal.web.model.request.UpsertNewsRequest;
import ru.skillbox.rest.newsportal.web.model.response.NewsListResponse;
import ru.skillbox.rest.newsportal.web.model.response.NewsResponse;
import ru.skillbox.rest.newsportal.web.model.response.SingleNewsResponse;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final INewsService newsService;
    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter filter) {
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(newsService.findAll(filter)));
    }

    @GetMapping("/filter")
    public ResponseEntity<NewsListResponse> filterBy(@Valid NewsFilter filter) {
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(
                newsService.filterBy(filter)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleNewsResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(newsMapper.newsToSingleNews(newsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> save(@RequestBody @Valid UpsertNewsRequest request) {
        News savedNews = newsService.save(newsMapper.requestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.newsToResponse(savedNews));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable("id") Long id, @RequestBody @Valid UpsertNewsRequest request) {
        News updatedNews = newsService.update(newsMapper.requestToNews(id, request));
        return ResponseEntity.ok(newsMapper.newsToResponse(updatedNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
