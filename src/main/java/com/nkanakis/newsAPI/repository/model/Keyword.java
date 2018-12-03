package com.nkanakis.newsAPI.repository.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "KEYWORD")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {

    @ManyToMany(mappedBy = "keywords")
    List<Article> articles;
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "KEYWORD", nullable = false)
    private String keyword;
}
