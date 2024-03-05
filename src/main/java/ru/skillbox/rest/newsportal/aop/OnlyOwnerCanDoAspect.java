package ru.skillbox.rest.newsportal.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.skillbox.rest.newsportal.exception.NoNeedParameterException;
import ru.skillbox.rest.newsportal.exception.OnlyOwnerCanDoException;
import ru.skillbox.rest.newsportal.model.News;
import ru.skillbox.rest.newsportal.model.NewsComment;
import ru.skillbox.rest.newsportal.service.INewsCommentService;
import ru.skillbox.rest.newsportal.service.INewsService;
import ru.skillbox.rest.newsportal.service.impl.NewsCommentService;
import ru.skillbox.rest.newsportal.service.impl.NewsService;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class OnlyOwnerCanDoAspect {

    private final INewsService newsService;
    private final INewsCommentService newsCommentService;


    @Before("@annotation(OnlyOwnerCanDo)")
    public void canBeEditOrRemoved(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getSignature().getDeclaringType();
        String methodName = joinPoint.getSignature().getName();

        if (targetClass.isNestmateOf(NewsService.class)) {
            if (methodName.equals("update")) canUpdateNews(joinPoint);
            if (methodName.equals("deleteById")) canDeleteNews(joinPoint);
        }

        else if(targetClass.isNestmateOf(NewsCommentService.class)) {
            if (methodName.equals("update")) canUpdateNewsComment(joinPoint);
            if (methodName.equals("deleteById")) canDeleteNewsComment(joinPoint);
        }
    }

    private void canUpdateNews(JoinPoint joinPoint) {
        Long id = getClientIdFromRequestParameter();
        News news = (News) joinPoint.getArgs()[0];

        if (!id.equals(news.getClient().getId())) {
            throw new OnlyOwnerCanDoException("Только владелец новости может её редактировать!");
        }
    }

    private void canDeleteNews(JoinPoint joinPoint) {
        Long id = getClientIdFromRequestParameter();
        News news = newsService.findById(Long.parseLong(joinPoint.getArgs()[0].toString()));

        if (!id.equals(news.getClient().getId())) {
            throw new OnlyOwnerCanDoException("Только владелец новости может удалить её!");
        }
    }

    private void canUpdateNewsComment(JoinPoint joinPoint) {
        Long id = getClientIdFromRequestParameter();
        NewsComment comment = (NewsComment) joinPoint.getArgs()[0];

        if (!id.equals(comment.getClient().getId())) {
            throw new OnlyOwnerCanDoException("Только владелец комментария может его редактировать!");
        }
    }

    private void canDeleteNewsComment(JoinPoint joinPoint) {
        Long id = getClientIdFromRequestParameter();
        NewsComment comment = newsCommentService.findById((Long) joinPoint.getArgs()[0]);

        if (!id.equals(comment.getClient().getId())) {
            throw new OnlyOwnerCanDoException("Только владелец комментария может его удалить!");
        }
    }

    private Long getClientIdFromRequestParameter() {
        String id = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getParameter("id");
        if (Objects.isNull(id)) throw new NoNeedParameterException("В запросе отсутствует Id пользователя.");
        return Long.parseLong(id);
    }
}
