package ru.skillbox.rest.newsportal.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsCategoryListResponse {

    private List<NewsCategoryResponse> newsCategoryList = new ArrayList<>();

}
