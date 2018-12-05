package com.nkanakis.newsAPI.controller;

import com.nkanakis.newsAPI.controller.dto.ArticleDTO;
import com.nkanakis.newsAPI.controller.mapper.ArticleMapper;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.service.EditorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Editor API", description = "CUD Operations on articles, accessible only by editors")
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
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(createdArticle));
    }

    @PutMapping
    @ApiOperation(value = "Updates existing article", response = ArticleDTO.class)
    public ResponseEntity<ArticleDTO> updateArticle(@Valid @RequestBody ArticleDTO article) {
        Article updatedArticle = editorService.updateArticle(mapper.toEntity(article));
        if (updatedArticle == null)
            throw new IllegalArgumentException("Could not update article please review provided id");
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(updatedArticle));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes article")
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        if (!editorService.deleteArticle(id))
            throw new IllegalArgumentException("Could not update article please review provided id");
        return ResponseEntity.ok().build();
    }

}
