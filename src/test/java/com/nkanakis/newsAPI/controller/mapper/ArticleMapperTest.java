package com.nkanakis.newsAPI.controller.mapper;

import com.nkanakis.newsAPI.controller.dto.ArticleDTO;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.repository.model.Author;
import de.flapdoodle.embed.process.collections.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ArticleMapperTest {

    @InjectMocks
    private ArticleMapper mapper;

    private ArticleDTO articleDTO;
    private Article article;

    @Before
    public void setUp() {
        articleDTO = new ArticleDTO();
        articleDTO.setHeader("MyHeader");
        articleDTO.setDesc("MyDesc");
        articleDTO.setDesc("MyText");
        articleDTO.setPublicationDate(LocalDate.of(2018, 12, 15));
        articleDTO.setKeywords(Collections.newArrayList("Key1", "Key2"));
        articleDTO.setAuthors(Collections.newArrayList("Nick Kanakis", "George Pavlov"));

        article = new Article();
        article.setHeader("MyHeader");
        article.setDesc("MyDesc");
        article.setDesc("MyText");
        article.setPublicationDate(LocalDate.of(2018, 12, 15));
        article.setKeywords(Collections.newArrayList("Key1", "Key2"));
        article.setAuthors(Collections.newArrayList(new Author("Nick", "Kanakis"), new Author("George", "Pavlov")));
    }

    @Test
    public void testToDto() {
        assertEquals(articleDTO, mapper.toDTO(article));
    }

    @Test
    public void testToEntity() {
        assertEquals(article, mapper.toEntity(articleDTO));
    }

    @Test
    public void testInvalidInputToDto() {
        article.setAuthors(new ArrayList<>());
        articleDTO.setAuthors(new ArrayList<>());
        assertEquals(article, mapper.toEntity(articleDTO));
    }

    @Test
    public void testInvalidInputToEntity() {
        articleDTO.setAuthors(Collections.newArrayList(""));
        assertEquals(mapper.toEntity(articleDTO).getAuthors().size(), 0);
        articleDTO.setAuthors(Collections.newArrayList(" "));
        assertEquals(mapper.toEntity(articleDTO).getAuthors().size(), 0);
        articleDTO.setAuthors(Collections.newArrayList(" NickKanakis"));
        assertEquals(mapper.toEntity(articleDTO).getAuthors().size(), 0);
        articleDTO.setAuthors(Collections.newArrayList(" NickKanakis "));
        assertEquals(mapper.toEntity(articleDTO).getAuthors().size(), 0);
    }
}
