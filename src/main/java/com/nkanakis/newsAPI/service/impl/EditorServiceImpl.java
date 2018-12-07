package com.nkanakis.newsAPI.service.impl;

import com.nkanakis.newsAPI.repository.ArticleRepository;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.service.EditorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EditorServiceImpl implements EditorService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    /**
     * Updates already stored article.
     * <p>
     *
     * @param article updated Article
     * @return Updated article
     * @throws IllegalArgumentException in case article does not exist.
     */
    @Override
    public Article updateArticle(Article article) {
        if (article.getId() != null && !articleRepository.existsById(article.getId())) {
            log.info("Id: {} does not exist, failed to update article", article.getId());
            throw new IllegalArgumentException("Could not update article please review provided id");
        }
        return articleRepository.save(article);
    }

    /**
     * Delete article by id.
     *
     * @param id Articles id for deletion.
     * @throws IllegalArgumentException in case article does not exist.
     */
    @Override
    public void deleteArticle(String id) {
        if (!articleRepository.existsById(id)) {
            log.info("Id: {} does not exist, failed to dete article", id);
            throw new IllegalArgumentException("Could not update article please review provided id");
        }
        articleRepository.deleteById(id);
    }
}
