package com.example.springexample;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class WebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRepository itemRepository;

    //check that we get index page on "/"
    @Test
    public void getIndexPageTest() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Example web page.")));
    }

    //check that we get itemAddForm page on "/items/add"
    @Test
    public void getItemAddFormPageTest() throws Exception {
        this.mockMvc.perform(get("/items/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Name")))
                .andExpect(content().string(containsString("Role")));
    }
}
