package ru.skillbox.rest.newsportal.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.rest.newsportal.mapper.NewsCategoryMapper;
import ru.skillbox.rest.newsportal.model.NewsCategory;
import ru.skillbox.rest.newsportal.service.INewsCategoryService;
import ru.skillbox.rest.newsportal.web.model.request.NewsCategoryFilter;
import ru.skillbox.rest.newsportal.web.model.request.UpsertNewsCategoryRequest;
import ru.skillbox.rest.newsportal.web.model.response.NewsCategoryListResponse;
import ru.skillbox.rest.newsportal.web.model.response.NewsCategoryResponse;

@RestController
@RequestMapping("/api/news-category")
@RequiredArgsConstructor
public class NewsCategoryController {

    private final INewsCategoryService newsCategoryService;
    private final NewsCategoryMapper newsCategoryMapper;

    @GetMapping
    public ResponseEntity<NewsCategoryListResponse> findAll(@Valid NewsCategoryFilter newsCategoryFilter) {
        return ResponseEntity.ok(newsCategoryMapper
                .newsCategoryListToNewsCategoryListResponse(newsCategoryService
                        .findAll(newsCategoryFilter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(newsCategoryMapper.newsCategoryToResponse(newsCategoryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsCategoryResponse> save(@RequestBody @Valid UpsertNewsCategoryRequest request) {
        NewsCategory savedNewsCategory = newsCategoryService.save(newsCategoryMapper.requestToNewsCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsCategoryMapper.newsCategoryToResponse(savedNewsCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsCategoryResponse> update(@PathVariable("id") Long id, @RequestBody @Valid UpsertNewsCategoryRequest request) {
        NewsCategory updatedNewsCategory = newsCategoryService.update(newsCategoryMapper.requestToNewsCategory(id, request));
        return ResponseEntity.ok(newsCategoryMapper.newsCategoryToResponse(updatedNewsCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        newsCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
