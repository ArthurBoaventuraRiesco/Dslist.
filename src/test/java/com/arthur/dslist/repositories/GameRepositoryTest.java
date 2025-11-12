package com.arthur.dslist.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.arthur.dslist.projections.GameMinProjection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/testdata/minimal.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class GameRepositoryTest {

    @Autowired
    private GameRepository repository;

    @Test
    void searchByListShouldReturnOrderedResults() {
        List<GameMinProjection> list = repository.searchByList(1L);
        assertThat(list).isNotEmpty();
        assertThat(list.get(0).getPosition()).isEqualTo(0);
    }
}
