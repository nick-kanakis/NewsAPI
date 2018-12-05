package com.nkanakis.newsAPI;

import com.nkanakis.newsAPI.repository.ArticleRepository;
import com.nkanakis.newsAPI.repository.model.Article;
import com.nkanakis.newsAPI.repository.model.Author;
import de.flapdoodle.embed.process.collections.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class NewsAPIIT {

    private final String CREATE_ARTICLE = "{ \"header\": \"My header\", \"description\": \"Desc...\", \"publication_date\": \"2018-08-04\", \"authors\": [ \"Nick Kanakis\", \"George Pavlov\"], \"keywords\": [ \"key1\", \"key2\" ] }";
    private final String UPDATE_ARTICLE = "{ \"id\": \"A123\",\"header\": \"My header 2\", \"description\": \"Desc...\", \"publication_date\": \"2018-08-04\", \"authors\": [ \"Nick Kanakis\", \"George Pavlov\"], \"keywords\": [ \"key1\", \"key2\" ] }";
    private final String UPDATE__NON_EXISTING_ARTICLE = "{ \"id\": \"A124\",\"header\": \"My header 2\", \"description\": \"Desc...\", \"publication_date\": \"2018-08-04\", \"authors\": [ \"Nick Kanakis\", \"George Pavlov\"], \"keywords\": [ \"key1\", \"key2\" ] }";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ArticleRepository articleRepository;

    @Before
    public void setup() {
        Article article = new Article();
        article.setId("A123");
        article.setHeader("My header");
        article.setDesc("Desc...");
        article.setPublicationDate(LocalDate.of(2018, 8, 4));
        article.setKeywords(Collections.newArrayList("key1", "key2"));
        article.setAuthors(Collections.newArrayList(new Author("Nick", "Kanakis"), new Author("George", "Pavlov")));
        articleRepository.deleteAll();
        articleRepository.save(article);
    }

    @Test
    public void createArticle() throws Exception {
        articleRepository.deleteAll();
        mockMvc.perform(post("/editor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_ARTICLE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".id").isNotEmpty())
                .andExpect(jsonPath(".header").value("My header"))
                .andExpect(jsonPath(".authors").isNotEmpty());
    }

    @Test
    public void updateArticle() throws Exception {
        mockMvc.perform(put("/editor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UPDATE_ARTICLE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".id").value("A123"))
                .andExpect(jsonPath(".header").value("My header 2"))
                .andExpect(jsonPath(".authors").isNotEmpty());
    }

    @Test
    public void updateNonExistingArticle() throws Exception {
        mockMvc.perform(put("/editor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UPDATE__NON_EXISTING_ARTICLE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteArticle() throws Exception {
        mockMvc.perform(delete("/editor/A123"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNonExistingArticle() throws Exception {
        mockMvc.perform(delete("/editor/A124"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getArticleById() throws Exception {
        mockMvc.perform(get("/articles/byId/A123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value("A123"))
                .andExpect(jsonPath(".header").value("My header"))
                .andExpect(jsonPath(".authors").isNotEmpty());
    }

    @Test
    public void getArticleByNonExistingId() throws Exception {
        mockMvc.perform(get("/articles/A124"))
                .andExpect(content().string(""));
    }

    @Test
    public void getArticleByAuthor() throws Exception {
        mockMvc.perform(get("/articles/byAuthor")
                .param("firstName", "Nick")
                .param("lastName", "Kanakis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value("A123"))
                .andExpect(jsonPath(".header").value("My header"))
                .andExpect(jsonPath(".authors").isNotEmpty());
    }

    @Test
    public void getArticleByNonExistingAuthor() throws Exception {
        mockMvc.perform(get("/articles/byAuthor")
                .param("firstName", "No")
                .param("lastName", "Name"))
                .andExpect(content().string("[]"));
    }

    @Test
    public void getArticleByKeyword() throws Exception {


        mockMvc.perform(get("/articles/byKeyword/key1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value("A123"))
                .andExpect(jsonPath(".header").value("My header"))
                .andExpect(jsonPath(".authors").isNotEmpty());
    }

    @Test
    public void getArticleByNonExistingKeyword() throws Exception {
        mockMvc.perform(get("/articles/byKeyword/key3"))
                .andExpect(content().string("[]"));
    }

    @Test
    public void getArticleByPeriod() throws Exception {
        mockMvc.perform(get("/articles/byPeriod")
                .param("startDate", "2018-01-01")
                .param("endPeriod", "2019-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value("A123"))
                .andExpect(jsonPath(".header").value("My header"))
                .andExpect(jsonPath(".authors").isNotEmpty());
    }

    @Test
    public void getArticleByPeriodShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/articles/byPeriod")
                .param("startDate", "2015-01-01")
                .param("endDate", "2016-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void getArticleByPeriodWithoutPeriodSpecified() throws Exception {
        mockMvc.perform(get("/articles/byPeriod"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".id").value("A123"))
                .andExpect(jsonPath(".header").value("My header"))
                .andExpect(jsonPath(".authors").isNotEmpty());
    }
}
