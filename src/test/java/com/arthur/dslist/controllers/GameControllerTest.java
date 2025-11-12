package com.arthur.dslist.controllers;

import com.arthur.dslist.dto.GameDTO;
import com.arthur.dslist.entities.Game;
import com.arthur.dslist.services.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    void findByIdShouldReturnOkAndJson() throws Exception {
        Game g = new Game();
        g.setId(1L);
        g.setTitle("Test Game");
        GameDTO dto = new GameDTO(g); // construtor existente no projeto

        Mockito.when(gameService.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/games/1").accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.title").value("Test Game"));
    }
}

