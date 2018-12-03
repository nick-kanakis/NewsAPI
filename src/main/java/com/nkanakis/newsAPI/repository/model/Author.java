package com.nkanakis.newsAPI.repository.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @ManyToMany(mappedBy = "authors")
    List<Article> articles;
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
}
