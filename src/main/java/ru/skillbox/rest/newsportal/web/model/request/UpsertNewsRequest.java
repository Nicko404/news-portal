package ru.skillbox.rest.newsportal.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertNewsRequest {

    @NotBlank(message = "Заголовок должен быть указан!")
    private String title;

    @NotBlank(message = "Описание должно быть указано!")
    private String description;

    @NotBlank(message = "Содержание должны быть указано!")
    private String body;

    @NotNull(message = "ID клиента должно быть указано!")
    @Positive(message = "ID клиента не может быть отрицательным!")
    private Long clientId;

    @NotNull(message = "ID категории должно быть указано!")
    @Positive(message = "ID категории не может быть отрицательным!")
    private Long categoryId;
}
