package ru.skillbox.rest.newsportal.web.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertNewsCategoryRequest {

    @NotBlank(message = "Название категории должно быть указано!")
    private String categoryName;

}
