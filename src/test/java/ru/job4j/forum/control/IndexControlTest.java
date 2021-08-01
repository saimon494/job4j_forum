package ru.job4j.forum.control;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.service.PostService;

import java.util.List;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class IndexControlTest {

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetIndexPage() throws Exception {
        Mockito.doReturn(List.of()).when(postService).findAll();
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("posts"));
    }
}
