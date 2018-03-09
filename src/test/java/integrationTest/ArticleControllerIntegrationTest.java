package integrationTest;

import com.article.MainController;
import com.article.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import unitTest.Reference;

import java.util.Date;

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

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getArticles() throws Exception {
        String username = "admin";
        String password = "123456";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        String token = response.substring(104,252);

        mockMvc
                .perform(get("/api/articles")
                        .header("Authorization",token)
                .header("Accept-Language", "eng"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void getArticle() throws Exception {
        String username = "admin";
        String password = "123456";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        String token = response.substring(104,252);

        mockMvc.perform(get("/api/article/{id}", 1)
                .header("Authorization",token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void updateArticle() throws Exception {
        String username = "admin";
        String password = "123456";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        String token = response.substring(104,252);
        mockMvc.perform(get("/api/article/{id}", 1)
                .header("Authorization",token))
                .andExpect(status().isOk());
    }

    @Test
    public void addArticle() throws Exception {
        String username = "admin";
        String password = "123456";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        String token = response.substring(104,252);
        mockMvc.perform(
                post("/api/addArticle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Article("article", "articleCategory",new Date())))
                        .header("Authorization",token))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteArticle() throws Exception {
        String username = "admin";
        String password = "123456";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        String token = response.substring(104,252);
        mockMvc.perform(
                post("/api/articleDelete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Article(1,"article", "articleCategory",new Date())))
                        .header("Authorization",token))
                .andExpect(status().isOk());
    }
}
