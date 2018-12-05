package com.nkanakis.newsAPI.controller;

import com.nkanakis.newsAPI.controller.dto.ArticleDTO;
import com.nkanakis.newsAPI.controller.mapper.ArticleMapper;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Api(value = "Article API", description = "Article retrieval operations")
@RequestMapping(path = "/articles")
public class ArticleController {

    @Autowired
    private ArticleMapper mapper;

    @Autowired
    private ArticleService articleService;

    @GetMapping(path = "/byId/{id}")
    @ApiOperation(value = "Retrieves Article by given id", response = ArticleDTO.class)
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable final String id) {
        ArticleDTO article = mapper.toDTO(articleService.getArticleById(id));
        return ResponseEntity.ok(article);
    }

    @GetMapping(path = "/byAuthor")
    @ApiOperation(value = "Retrieves Article by author first & last name", response = ArticleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ArticleDTO>> getArticlesByAuthor(@RequestParam final String firstName, @RequestParam final String lastName) {
        List<Article> articlesByAuthor = articleService.getArticlesByAuthor(firstName, lastName);
        return ResponseEntity.ok(mapper.toDTOs(articlesByAuthor));
    }

    @GetMapping(path = "/byKeyword/{keyword}")
    @ApiOperation(value = "Retrieves Article by author name", response = ArticleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ArticleDTO>> getArticlesByKeyword(@PathVariable final String keyword) {
        List<Article> articlesByKeyword = articleService.getArticlesByKeyword(keyword);
        return ResponseEntity.ok(mapper.toDTOs(articlesByKeyword));
    }

    @GetMapping(path = "/byPeriod")
    @ApiOperation(value = "Retrieves ArticleDTO within a given period", notes = "If no start/end period is specified, returns all Articles from the start of time/until now",
            response = ArticleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ArticleDTO>> getArticlesByPeriod(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (startDate == null)
            startDate = LocalDate.ofEpochDay(0);
        if (endDate == null)
            endDate = LocalDate.now();
        List<Article> articlesByPeriod = articleService.getArticlesByPeriod(startDate, endDate);
        return ResponseEntity.ok(mapper.toDTOs(articlesByPeriod));
    }

}
