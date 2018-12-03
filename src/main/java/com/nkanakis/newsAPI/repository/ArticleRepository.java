package com.nkanakis.newsAPI.repository;

import com.nkanakis.newsAPI.repository.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
