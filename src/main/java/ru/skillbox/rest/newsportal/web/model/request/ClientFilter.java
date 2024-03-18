package ru.skillbox.rest.newsportal.web.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.rest.newsportal.validation.ClientFilterValid;

@Data
@NoArgsConstructor
@ClientFilterValid
public class ClientFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String name;


}
