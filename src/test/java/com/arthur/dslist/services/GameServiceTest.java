package com.arthur.dslist.services;

import com.arthur.dslist.dto.GameDTO;
import com.arthur.dslist.entities.Game;
import com.arthur.dslist.projections.GameMinProjection;
import com.arthur.dslist.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    void shouldReturnGameDTOWhenIdExists() {
        Game e = new Game();
        e.setId(1L);
        e.setTitle("Test Game");
        when(gameRepository.findById(1L)).thenReturn(Optional.of(e));

        GameDTO dto = gameService.findById(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getTitle()).isEqualTo("Test Game");
        verify(gameRepository, times(1)).findById(1L);
    }

    @Test
    void shouldReturnListOfGameMinDTOByListId() {
        GameMinProjection p = new GameMinProjection() {
            public Long getId() { return 1L; }
            public String getTitle() { return "GTA"; }
            public Integer getGameYear() { return 2004; }
            public String getImgUrl() { return null; }
            public String getShortDescription() { return null; }
            public Integer getPosition() { return 0; }
        };
        when(gameRepository.searchByList(10L)).thenReturn(List.of(p));

        var result = gameService.findByGameList(10L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        verify(gameRepository).searchByList(10L);
    }
}

