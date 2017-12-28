package sample;

import com.article.controller.UserController;
import com.article.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class UserControllerTest {

    private static final String RESOURCE_LOCATION_PATTERN = "http://localhost:9090";

    @InjectMocks
    UserController userController;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getUsers() throws Exception {
/*
        User user = mockUser("getAll");
        byte[] r1Json = toJson(user);
        mvc.perform(get("user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.surname", is(user.getSurname())))
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.roleId", is(user.getRoleId())))

        mvc.perform(get("user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());*/
    }

    private long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }

    private User mockUser(String prefix) {
        User user = new User();
        user.setUsername(prefix + "_username");
        user.setPassword(prefix + "_password");
        user.setName(prefix + "_name");
        user.setSurname(prefix + "_surname");
        user.setEmail(prefix + "_email");
        user.setRoleId(1);
        return user;
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

    private static ResultMatcher redirectedUrlPattern(final String expectedUrlPattern) {
        return new ResultMatcher() {
            public void match(MvcResult result) {
                Pattern pattern = Pattern.compile("\\A" + expectedUrlPattern + "\\z");
                /*assertTrue(pattern.matcher(result.getResponse().getRedirectedUrl()).find());*/
            }
        };
    }

}