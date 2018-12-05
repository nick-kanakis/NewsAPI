package com.nkanakis.newsAPI.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "Article components")
public class ArticleDTO extends ResourceSupport {

    @JsonProperty(value = "id")
    private String articleId;
    @NotBlank
    private String header;
    @JsonProperty(value = "description")
    @NotBlank
    private String desc;
    @JsonProperty(value = "publication_date")
    private LocalDate publicationDate;

    @NotEmpty
    private List<String> authors;
    private List<String> keywords;

}
