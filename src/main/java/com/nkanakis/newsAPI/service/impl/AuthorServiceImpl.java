package com.nkanakis.newsAPI.service.impl;

import com.nkanakis.newsAPI.repository.ArticleRepository;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article createOrUpdateArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(String id) {
        articleRepository.deleteById(id);
    }
}
