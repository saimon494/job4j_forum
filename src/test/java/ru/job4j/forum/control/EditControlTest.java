package ru.job4j.forum.control;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class EditControlTest {

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    private User initUser() {
        return User.of("user", "12345");
    }

    @Test
    public void whenGetEditPageWithoutAuth() throws Exception {
        this.mockMvc.perform(get("/post/edit"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void whenGetEditPageWithAuthWithoutPost() throws Exception {
        User user = initUser();
        this.mockMvc.perform(get("/post/edit")
                        .sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("post/edit"))
                .andExpect(model().attributeDoesNotExist("post"));
    }

    @Test
    @WithMockUser
    public void whenCreatePost() throws Exception {
        this.mockMvc.perform(post("/post/create")
                        .param("name", "New topic")
                        .param("description", "New description"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(postService).createPost(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getName(), is("New topic"));
        MatcherAssert.assertThat(argument.getValue().getDescription(), is("New description"));
    }
}
