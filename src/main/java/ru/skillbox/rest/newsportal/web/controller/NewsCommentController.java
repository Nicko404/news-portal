package ru.skillbox.rest.newsportal.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.rest.newsportal.mapper.NewsCommentMapper;
import ru.skillbox.rest.newsportal.model.NewsComment;
import ru.skillbox.rest.newsportal.service.INewsCommentService;
import ru.skillbox.rest.newsportal.web.model.request.UpsertNewsCommentRequest;
import ru.skillbox.rest.newsportal.web.model.response.NewsCommentResponse;

@RestController
@RequestMapping("/api/news-comment")
@RequiredArgsConstructor
public class NewsCommentController {

    private final INewsCommentService newsCommentService;
    private final NewsCommentMapper newsCommentMapper;


    @GetMapping("/{id}")
    public ResponseEntity<NewsCommentResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(newsCommentMapper.newsCommentToResponse(newsCommentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsCommentResponse> save(@RequestBody @Valid UpsertNewsCommentRequest request) {
        NewsComment savedNewsComment = newsCommentService.save(newsCommentMapper.requestToNewsComment(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsCommentMapper.newsCommentToResponse(savedNewsComment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsCommentResponse> update(@PathVariable("id") Long id, @RequestBody @Valid UpsertNewsCommentRequest request) {
        NewsComment updatedNewsComment = newsCommentService.update(newsCommentMapper.requestToNewsComment(id, request));
        return ResponseEntity.ok(newsCommentMapper.newsCommentToResponse(updatedNewsComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        newsCommentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
