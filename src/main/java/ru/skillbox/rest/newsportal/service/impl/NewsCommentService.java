package ru.skillbox.rest.newsportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.rest.newsportal.aop.OnlyOwnerCanDo;
import ru.skillbox.rest.newsportal.exception.EntityNotFoundException;
import ru.skillbox.rest.newsportal.model.NewsComment;
import ru.skillbox.rest.newsportal.repository.DatabaseNewsCommentRepository;
import ru.skillbox.rest.newsportal.service.INewsCommentService;
import ru.skillbox.rest.newsportal.utils.BeanUtils;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsCommentService implements INewsCommentService {

    private final DatabaseNewsCommentRepository newsCommentRepository;

    @Override
    public List<NewsComment> findAll() {
        return newsCommentRepository.findAll();
    }

    @Override
    public NewsComment findById(Long id) {
        return newsCommentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Комментарий с ID {0} не найден!", id)
                ));
    }

    @Override
    public NewsComment save(NewsComment newsComment) {

        return newsCommentRepository.save(newsComment);
    }

    @Override
    @OnlyOwnerCanDo
    public NewsComment update(NewsComment newsComment) {
        NewsComment existedNewsComment = findById(newsComment.getId());

        BeanUtils.copyNonNullProperties(newsComment, existedNewsComment);

        return newsCommentRepository.save(existedNewsComment);
    }

    @Override
    @OnlyOwnerCanDo
    public void deleteById(Long id) {
        newsCommentRepository.deleteById(id);
    }
}
