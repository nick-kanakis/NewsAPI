package com.nkanakis.newsAPI.repository;

import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.repository.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;


public interface ArticleRepository extends MongoRepository<Article, String> {

    List<Article> findByPublicationDateBetween(LocalDate start, LocalDate end);

   List<Article> findByKeywordsContaining(String keyword);

   List<Article> findByAuthorsContaining(Author author);
}
