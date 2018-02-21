package unitTest.controller;

import com.article.controller.UserController;
import com.article.entity.user.Role;
import com.article.entity.user.User;
import com.article.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import unitTest.Reference;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerUnitTest extends Reference {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void getUsers() throws Exception {
        List<User> users = Collections.singletonList(
                new User("admin@gmail.com", "123456", Role.USER, "admin", true));

        when(userService.getUserList()).thenReturn(users);

        mockMvc
                .perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(userService, times(1)).getUserList();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getUser() throws Exception {
        User user = new User("1", "admin@gmail.com", "123456", Role.USER, "admin", true);

        when(userService.getUser(any(User.class))).thenReturn(user);
        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk())
                .andReturn();
        verify(userService, times(1)).getUser(any(User.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("admin@gmail.com", "123456", Role.USER, "admin", true);
        when(userService.userExists(any(User.class))).thenReturn(false);
        when(userService.addUser(any(User.class))).thenReturn(true);
        mockMvc.perform(
                post("/api/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());
        verify(userService, times(1)).addUser(any(User.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testHelloUser() throws Exception {
        mockMvc.perform(
                get("/api/helloUser")
        ).andExpect(
                status().isOk()
        );
    }
}
