package ru.skillbox.rest.newsportal.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertClientRequest {

    @NotBlank(message = "Имя клиента должно быть указано!")
    private String name;

    @NotBlank(message = "Пароль должен быть указан!")
    @Size(min = 7, max = 30, message = "Пароль должен быть не меньше {min} и не больше {max}!")
    private String password;

    @NotBlank(message = "E-mail должен быть указан!")
    private String email;
}
