package com.nkanakis.newsAPI.repository.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document
public class Article {

    @Id
    private String id;
    private String header;
    private String desc;
    private LocalDate publicationDate;
    private List<Author> authors;
    private List<String> keywords;
}
