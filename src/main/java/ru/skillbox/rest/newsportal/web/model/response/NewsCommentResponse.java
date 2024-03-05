package ru.skillbox.rest.newsportal.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsCommentResponse {

    private Long id;

    private String commentary;
}
