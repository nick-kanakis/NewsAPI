package com.nkanakis.newsAPI.service;

import com.nkanakis.newsAPI.repository.model.Article;

public interface AuthorService {

    Article createOrUpdateArticle(Article article);

    void deleteArticle(String id);
}
