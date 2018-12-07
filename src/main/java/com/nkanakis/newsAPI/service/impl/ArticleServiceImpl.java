package com.nkanakis.newsAPI.service.impl;

import com.nkanakis.newsAPI.repository.ArticleRepository;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.repository.model.Author;
import com.nkanakis.newsAPI.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * Returns article by provided id.
     *
     * @param id articles ID.
     * @return stored article.
     * @throws RuntimeException in case data are unavailable.
     */
    @Override
    public Article getArticleById(String id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.orElseThrow(() -> new RuntimeException("Data unavailable"));
    }

    @Override
    public List<Article> getArticlesByAuthor(String firstName, String lastName) {
        return articleRepository.findByAuthorsContaining(new Author(firstName, lastName));
    }

    @Override
    public List<Article> getArticlesByKeyword(String keyword) {
        return articleRepository.findByKeywordsContaining(keyword);
    }

    @Override
    public List<Article> getArticlesByPeriod(LocalDate start, LocalDate end) {
        return articleRepository.findByPublicationDateBetween(start, end);
    }
}
