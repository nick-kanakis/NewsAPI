package com.nkanakis.newsAPI.service.impl;

import com.nkanakis.newsAPI.repository.ArticleRepository;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Article article) {
        if (article.getId() != null && !articleRepository.existsById(article.getId()))
            return null;
        return articleRepository.save(article);
    }

    @Override
    public boolean deleteArticle(String id) {
        if (!articleRepository.existsById(id))
            return false;
        articleRepository.deleteById(id);
        return true;
    }
}
