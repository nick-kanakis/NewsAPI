package com.nkanakis.newsAPI.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ApiModel(value = "Article components")
public class ArticleDTO {

    private long id;
    private String header;
    @JsonProperty(value = "description")
    private String desc;
    @JsonProperty(value = "publication_date")
    private LocalDate publicationDate;

    private List<String> authors;
    private List<String> keywords;
}
