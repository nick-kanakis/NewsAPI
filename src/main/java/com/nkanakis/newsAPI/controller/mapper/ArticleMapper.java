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

    /**
     * Maps Article entity to ArticleDTO.
     * <p>
     * In case provided object is null mapper returns an new empty object.
     *
     * @param article entity article.
     * @return dto version of article.
     */
    public ArticleDTO toDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();
        if (article == null) {
            log.error("Article is null could not be transformed to ArticleDTO, returning empty object");
            return dto;
        }
        BeanUtils.copyProperties(article, dto);
        dto.setArticleId(article.getId());
        List<String> authors = article.getAuthors().stream()
                .map(a -> a.getFirstname() + " " + a.getLastname())
                .collect(Collectors.toList());
        dto.setAuthors(authors);

        return dto;
    }

    /**
     * Maps ArticleDTO to Article entity.
     * <p>
     * In case provided object is null mapper returns an new empty object.
     *
     * @param dto dto version of article.
     * @return article entity
     */
    public Article toEntity(ArticleDTO dto) {
        Article article = new Article();
        if (dto == null) {
            log.error("ArticleDTO is null could not be transformed to Article, returning empty object");
            return article;
        }
        BeanUtils.copyProperties(dto, article);
        article.setId(dto.getArticleId());

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

    /**
     * Maps a list of articles to a list of ArticleDTOs.
     * <p>
     * Uses {@link #toDTO(Article)} method.
     *
     * @param articles list of Article entities.
     * @return list of article DTOs
     */
    public List<ArticleDTO> toDTOs(List<Article> articles) {
        return articles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
