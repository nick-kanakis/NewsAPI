package com.nkanakis.newsAPI.controller;

import com.nkanakis.newsAPI.controller.dto.ArticleDTO;
import com.nkanakis.newsAPI.controller.mapper.ArticleMapper;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Editor API", description = "CUD Operations on articles, accessible only by editors")
@RequestMapping(path = "/editor")
public class EditorController {
    //todo add security?

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ArticleMapper mapper;

    @PostMapping
    @ApiOperation(value = "Creates article", response = ArticleDTO.class)
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO article) {
        return ResponseEntity.ok(createOrUpdateArticle(createOrUpdateArticle(article)));
    }

    @PutMapping
    @ApiOperation(value = "Updates existing article", response = ArticleDTO.class)
    public ResponseEntity<ArticleDTO> updateArticle(@Valid @RequestBody ArticleDTO article) {
        return ResponseEntity.ok(createOrUpdateArticle(createOrUpdateArticle(article)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes article")
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        authorService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    private ArticleDTO createOrUpdateArticle(ArticleDTO article) {
        Article updatedArticle = authorService.createOrUpdateArticle(mapper.toEntity(article));
        return mapper.toDTO(updatedArticle);
    }
}
