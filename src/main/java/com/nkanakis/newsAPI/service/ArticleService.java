package com.nkanakis.newsAPI.service;

import com.nkanakis.newsAPI.repository.model.Article;

import java.time.LocalDate;
import java.util.List;

public interface ArticleService {

    Article getArticleById(String id);

    List<Article> getArticlesByAuthor(String firstName, String lastName);

    List<Article> getArticlesByKeyword(String keyword);

    List<Article> getArticlesByPeriod(LocalDate start, LocalDate end);
}
