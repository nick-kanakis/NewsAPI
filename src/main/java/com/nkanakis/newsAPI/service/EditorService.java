package com.nkanakis.newsAPI.service;

import com.nkanakis.newsAPI.repository.model.Article;

public interface EditorService {

    Article createArticle(Article article);

    Article updateArticle(Article article);

    void deleteArticle(String id);
}
