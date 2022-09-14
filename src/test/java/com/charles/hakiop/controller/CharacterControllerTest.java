package com.charles.hakiop.controller;

import com.charles.hakiop.config.ConfigTestClass;
import com.charles.hakiop.model.dto.CharacterDTO;
import com.charles.hakiop.model.entity.CharacterEntity;
import com.charles.hakiop.repository.CharacterRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CharacterControllerTest extends ConfigTestClass {

    private final String urlBase = "/api/character";

    @Autowired
    private CharacterRepository characterRepository;

    @BeforeEach
    public void setUp() {
        CharacterEntity entity = new CharacterEntity();
        entity.setName(RandomStringUtils.randomAlphabetic(10));
        entity.setImage(RandomStringUtils.randomAlphabetic(10));
        characterRepository.save(entity);
    }

    @AfterEach
    public void tearDown() {}

    @Test
    @DisplayName("Should create character")
    void shouldCreateCharacter() throws Exception {
        CharacterDTO dto = new CharacterDTO();
        dto.setName("name");
        dto.setImage("image");

        mockMvc.perform(post(urlBase)
                        .content(requestBody(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get all character")
    void shouldGetAllCharacter() throws Exception {
        mockMvc.perform(get(urlBase)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get character by id 1")
    void shouldGetCharacter() throws Exception {
        mockMvc.perform(get(urlBase.concat("/1"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Put request should not allowed 405")
    void putRequestShouldNotAllowed() throws Exception {
        CharacterDTO dto = new CharacterDTO();
        dto.setName("name");
        dto.setImage("image");

        mockMvc.perform(put(urlBase)
                        .content(requestBody(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Delete request should not allowed 405")
    void deleteRequestShouldNotAllowed() throws Exception {
        CharacterDTO dto = new CharacterDTO();
        dto.setName("name");
        dto.setImage("image");

        mockMvc.perform(put(urlBase)
                        .content(requestBody(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
}
