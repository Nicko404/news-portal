package ru.skillbox.rest.newsportal.web.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.rest.newsportal.validation.NewsCategoryValid;

@Data
@NoArgsConstructor
@NewsCategoryValid
public class NewsCategoryFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String categoryName;
}
