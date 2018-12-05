package com.nkanakis.newsAPI.controller;

import com.nkanakis.newsAPI.controller.dto.ArticleDTO;
import com.nkanakis.newsAPI.controller.mapper.ArticleMapper;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.service.EditorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(value = "Editor API", description = "CUD Operations on articles, accessible only by editors")
@Slf4j
@RequestMapping(path = "/editor")
public class EditorController {
    //todo add security?

    @Autowired
    private EditorService editorService;

    @Autowired
    private ArticleMapper mapper;

    @PostMapping
    @ApiOperation(value = "Creates article", response = ArticleDTO.class)
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO article) {
        Article createdArticle = editorService.createArticle(mapper.toEntity(article));
        ArticleDTO articleDTO = mapper.toDTO(createdArticle);
        articleDTO.add(linkTo(methodOn(ArticleController.class).getArticleById(articleDTO.getArticleId())).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(articleDTO);
    }

    @PutMapping
    @ApiOperation(value = "Updates existing article", response = ArticleDTO.class)
    public ResponseEntity<ArticleDTO> updateArticle(@Valid @RequestBody ArticleDTO article) {
        Article updatedArticle = editorService.updateArticle(mapper.toEntity(article));
        if (updatedArticle == null) {
            log.info("Id: {} does not exist, failed to update article", article.getId());
            throw new IllegalArgumentException("Could not update article please review provided id");
        }
        ArticleDTO articleDTO = mapper.toDTO(updatedArticle);
        articleDTO.add(linkTo(methodOn(ArticleController.class).getArticleById(articleDTO.getArticleId())).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(articleDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes article")
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        if (!editorService.deleteArticle(id)) {
            log.info("Id: {} does not exist, failed to dete article", id);
            throw new IllegalArgumentException("Could not update article please review provided id");
        }
        return ResponseEntity.ok().build();
    }

}
