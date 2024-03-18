package ru.skillbox.rest.newsportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skillbox.rest.newsportal.exception.EntityNotFoundException;
import ru.skillbox.rest.newsportal.model.NewsCategory;
import ru.skillbox.rest.newsportal.repository.DatabaseNewsCategoryRepository;
import ru.skillbox.rest.newsportal.repository.NewsCategorySpecification;
import ru.skillbox.rest.newsportal.service.INewsCategoryService;
import ru.skillbox.rest.newsportal.utils.BeanUtils;
import ru.skillbox.rest.newsportal.web.model.request.NewsCategoryFilter;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsCategoryService implements INewsCategoryService {

    private final DatabaseNewsCategoryRepository newsCategoryRepository;

    @Override
    public List<NewsCategory> findAll(NewsCategoryFilter newsCategoryFilter) {
        return newsCategoryRepository.findAll(NewsCategorySpecification.withFilter(
                newsCategoryFilter), PageRequest.of(
                        newsCategoryFilter.getPageNumber(), newsCategoryFilter.getPageSize()
        )).getContent();
    }

    @Override
    public NewsCategory findById(Long id) {
        return newsCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Категория с ID {0} не найдена!", id)
                ));
    }

    @Override
    public NewsCategory save(NewsCategory newsCategory) {
        return newsCategoryRepository.save(newsCategory);
    }

    @Override
    public NewsCategory update(NewsCategory newsCategory) {
        NewsCategory existedNewsCategory = findById(newsCategory.getId());

        BeanUtils.copyNonNullProperties(newsCategory, existedNewsCategory);

        return newsCategoryRepository.save(existedNewsCategory);
    }

    @Override
    public void deleteById(Long id) {
        newsCategoryRepository.deleteById(id);
    }
}
