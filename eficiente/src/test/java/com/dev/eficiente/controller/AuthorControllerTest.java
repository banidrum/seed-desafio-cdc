package com.dev.eficiente.controller;

import com.dev.eficiente.dto.AuthorDto;
import com.dev.eficiente.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void authorCreation_success() throws Exception {
        AuthorDto author = new AuthorDto();
        author.setName("Teste OK");
        author.setEmail("teste@email.com");
        author.setDescription("Simple description");

        mockMvc.perform(post("/v1/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isOk());
    }

    @Test
    void authorCreation_shouldFailIfNameIsNull() throws Exception {
        AuthorDto author = new AuthorDto();
        author.setName("");
        author.setEmail("email@ok.com");
        author.setDescription("Simple description");

        mockMvc.perform(post("/v1/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void authorCreation_shouldFailIfEmailIsNull() throws Exception {
        AuthorDto author = new AuthorDto();
        author.setName("Teste OK");
        author.setEmail("");
        author.setDescription("Simple description");

        mockMvc.perform(post("/v1/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void authorCreation_shouldFailIfDescriptionIsNull() throws Exception {
        AuthorDto author = new AuthorDto();
        author.setName("Teste OK");
        author.setEmail("email@ok.com");
        author.setDescription("");

        mockMvc.perform(post("/v1/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void authorCreation_shouldFailIfDescriptionIsLongerThan400() throws Exception {
        AuthorDto author = new AuthorDto();
        author.setName("Teste OK");
        author.setEmail("email@ok.com");
        author.setDescription("""
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        """);

        mockMvc.perform(post("/v1/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void authorCreation_shouldFailIfEmailIsInWrongFormat() throws Exception {
        AuthorDto author = new AuthorDto();
        author.setName("Teste OK");
        author.setEmail("email@ok.com");
        author.setDescription("");

        mockMvc.perform(post("/v1/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isBadRequest());
    }
}