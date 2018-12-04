package com.nkanakis.newsAPI.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Article {

    private Long id;
    private String header;
    private String desc;
    private LocalDate publicationDate;
    private List<Author> authors;
    private List<String> keywords;
}
