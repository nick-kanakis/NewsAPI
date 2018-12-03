package com.nkanakis.newsAPI.repository.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ARTICLE")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "HEADER", nullable = false, unique = true)
    private String header;
    @Column(name = "DESCRIPTION", nullable = false)
    private String desc;
    @Column(name = "PUBLICATION_DATE", nullable = false)
    private LocalDate publicationDate;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinTable(name = "ARTICLE_AUTHOR",
            joinColumns = {@JoinColumn(name = "ARTICLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
    private List<Author> authors;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinTable(name = "ARTICLE_KEYWORD",
            joinColumns = {@JoinColumn(name = "ARTICLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "KEYWORD_ID")})
    private List<Keyword> keywords;
}
