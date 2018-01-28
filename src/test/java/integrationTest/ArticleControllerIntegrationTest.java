package integrationTest;

import com.article.controller.ArticleController;
import com.article.controller.MainController;
import com.article.entity.Article;
import com.article.services.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MainController.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class ArticleControllerIntegrationTest extends Reference {

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
        mockMvc
                .perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void getArticle() throws Exception {
        Article article = new Article(1, "article", "articleCategory",new Date());
        mockMvc.perform(get("/api/article/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.title", is(article.getTitle())))
                .andExpect(jsonPath("$.category", is(article.getCategory())));
    }

    @Test
    public void updateArticle() throws Exception {

        mockMvc.perform(get("/api/article/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void addArticle() throws Exception {
        Article article = new Article(1,"article", "articleCategory",new Date());
        mockMvc.perform(
                post("/api/addArticle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(article)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteArticle() throws Exception {
        Article article = new Article(1,"article", "articleCategory",new Date());
        mockMvc.perform(
                post("/api/articleDelete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(article)))
                .andExpect(status().isOk());
    }
}
