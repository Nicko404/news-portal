package ru.skillbox.rest.newsportal.web.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.rest.newsportal.validation.NewsFilterValid;

import java.time.Instant;

@Data
@NoArgsConstructor
@NewsFilterValid
public class NewsFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String title;

    private Long clientId;

    private Long categoryId;

    private Instant createdBefore;

    private Instant updatedBefore;

}
