package com.nkanakis.newsAPI.controller;

import com.nkanakis.newsAPI.controller.dto.ArticleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Editor API", description = "CUD Operations on articles, accessible only by editors")
@RequestMapping(path = "/editor")
public class EditorController {
    //todo: sanitize/validate input
    //todo add security?
    @PostMapping
    @ApiOperation(value = "Creates article", response = ArticleDTO.class)
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO article) {
        return null;
    }

    @PutMapping
    @ApiOperation(value = "Updates existing article", response = ArticleDTO.class)
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody ArticleDTO article) {
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes article")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        return null;
    }
}
