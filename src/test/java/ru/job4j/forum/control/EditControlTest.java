package ru.job4j.forum.control;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.User;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class EditControlTest {

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
}
