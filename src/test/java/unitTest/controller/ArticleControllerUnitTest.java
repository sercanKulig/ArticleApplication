package unitTest.controller;

import com.article.controller.ArticleController;
import com.article.entity.Article;
import com.article.services.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import unitTest.Reference;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleControllerUnitTest extends Reference {

    private MockMvc mockMvc;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(articleController)
                .build();
    }

    @Test
    public void getArticles() throws Exception {
        List<Article> articles = Collections.singletonList(
                new Article("article","articleCategory",new Date()));
        when(articleService.getAllArticleList()).thenReturn(articles);
        this.mockMvc
                .perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(articleService, times(1)).getAllArticleList();
        verifyNoMoreInteractions(articleService);
    }

    @Test
    public void getArticle() throws Exception {
        Article article = new Article(1, "article", "articleCategory",new Date());

        when(articleService.getArticle(article.getArticleId())).thenReturn(article);
        mockMvc.perform(get("/api/article/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.title", is(article.getTitle())))
                .andExpect(jsonPath("$.category", is(article.getCategory())));
        verify(articleService, times(1)).getArticle(1);
        verifyNoMoreInteractions(articleService);
    }

    @Test
    public void updateArticle() throws Exception {
        Article article = new Article(1, "article", "articleCategory",new Date());

        when(articleService.updateArticle(article,1)).thenReturn(true);
        mockMvc.perform(get("/api/article/{id}", 1))
                .andExpect(status().isOk());
        verify(articleService, times(1)).getArticle(1);
        verifyNoMoreInteractions(articleService);
    }

    @Test
    public void addArticle() throws Exception {
        Article article = new Article(1,"article", "articleCategory",new Date());
        when(articleService.articleExist(article)).thenReturn(true);
        when(articleService.addArticle(any(Article.class))).thenReturn(true);
        mockMvc.perform(
                post("/api/addArticle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(article)))
                .andExpect(status().isOk());
        verify(articleService, times(1)).addArticle(any(Article.class));
        verifyNoMoreInteractions(articleService);
    }

    @Test
    public void deleteArticle() throws Exception {
        Article article = new Article(1,"article", "articleCategory",new Date());
        when(articleService.deleteArticle(article.getArticleId())).thenReturn(true);
        mockMvc.perform(
                post("/api/articleDelete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(article)))
                .andExpect(status().isOk());
        verify(articleService, times(1)).deleteArticle(article.getArticleId());
        verifyNoMoreInteractions(articleService);
    }
}