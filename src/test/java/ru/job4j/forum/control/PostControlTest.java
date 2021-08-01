package ru.job4j.forum.control;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.PostService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private PostService postService;

    @MockBean
    private MessageService messageService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenGetPostPage() throws Exception {
        Mockito.doReturn(new Post()).when(postService).findById(1);
        this.mockMvc.perform(get("/post?postId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"));
    }

    @Test
    @WithMockUser
    public void whenSavePostMessage() throws Exception {
        this.mockMvc.perform(post("/message/create")
                        .param("text", "ok")
                        .param("postId", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Message> argumentMessage = ArgumentCaptor.forClass(Message.class);
        verify(messageService).save(argumentMessage.capture(), eq(1));
        MatcherAssert.assertThat(argumentMessage.getValue().getText(), is("ok"));
    }
}


