package com.nkanakis.newsAPI.controller.mapper;

import com.nkanakis.newsAPI.controller.dto.ArticleDTO;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.repository.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ArticleMapper {

    public ArticleDTO toDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();
        if (article == null) {
            log.error("Article is null could not be transformed to ArticleDTO, returning empty object");
            return dto;
        }
        BeanUtils.copyProperties(article, dto);

        List<String> authors = article.getAuthors().stream()
                .map(a -> a.getFirstname() + " " + a.getLastname())
                .collect(Collectors.toList());
        dto.setAuthors(authors);

        return dto;
    }

    public Article toEntity(ArticleDTO dto) {
        Article article = new Article();
        if (dto == null) {
            log.error("ArticleDTO is null could not be transformed to Article, returning empty object");
            return article;
        }
        BeanUtils.copyProperties(dto, article);

        List<Author> authors = dto.getAuthors().stream()
                .filter(a -> a.length() >= 3)
                .filter(a -> a.matches("[a-zA-Z]+ [a-zA-Z]+"))
                .map(a -> {
                    String[] parts = a.split(" ");
                    return new Author(parts[0], parts[1]);
                })
                .collect(Collectors.toList());
        article.setAuthors(authors);
        return article;
    }

    public List<ArticleDTO> toDTOs(List<Article> articlesByAuthor) {
        return articlesByAuthor.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
