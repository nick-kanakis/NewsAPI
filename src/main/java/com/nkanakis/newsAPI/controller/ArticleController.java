package com.nkanakis.newsAPI.controller;

import com.nkanakis.newsAPI.controller.dto.ArticleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Api(value = "Article API", description = "Article retrieval operations")
@RequestMapping(path = "/articles")
public class ArticleController {
    //todo: sanitize/validate input

    @GetMapping(path = "/byId/{id}")
    @ApiOperation(value = "Retrieves Article by given id", response = ArticleDTO.class)
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable final long id) {

        return null;
    }

    @GetMapping(path = "/byAuthor/{author}")
    @ApiOperation(value = "Retrieves Article by author name", response = ArticleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ArticleDTO>> getArticlesByAuthor(@PathVariable final String author) {

        return null;
    }

    @GetMapping(path = "/byKeyword/{keyword}")
    @ApiOperation(value = "Retrieves Article by author name", response = ArticleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ArticleDTO>> getArticlesByKeyword(@PathVariable final String keyword) {
        return null;
    }

    //todo: fix note
    @GetMapping(path = "/byPeriod")
    @ApiOperation(value = "Retrieves ArticleDTO within a given period", notes = "If no start/end period is specified, returns all Articles from the start of time/until now",
            response = ArticleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ArticleDTO>> getArticlesByPeriod(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return null;
    }

}
