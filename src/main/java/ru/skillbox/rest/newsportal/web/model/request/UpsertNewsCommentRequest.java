package ru.skillbox.rest.newsportal.web.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertNewsCommentRequest {

    @NotBlank(message = "Комментарий должен содержать текст!")
    @Size(min = 3, max = 300, message = "Комментарий не может быть меньше {min} и больше {max}!")
    private String commentary;

    @NotNull(message = "ID клиента должен быть указан!")
    @Positive(message = "ID клиента должно быть положительным!")
    private Long clientId;

    @NotNull(message = "ID новости должно быть указано!")
    @Positive(message = "ID новости должно быть положительным!")
    private Long newsId;
}
