package ru.job4j.forum.control;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class RegControlTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetRegPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    public void whenGetRegPageWithError() throws Exception {
        this.mockMvc.perform(get("/reg").param("error", "true"))
                .andExpect(status().isOk())
                .andExpect(view().name("reg"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    public void whenSaveRegUser() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "user")
                        .param("password", "123"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userService).save(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getUsername(), is("user"));
        MatcherAssert.assertThat(argument.getValue().getPassword(), is("123"));
    }
}
