package com.thoughtworks.guessnumber.controller;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.thoughtworks.guessnumber.service.GameService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        MockitoTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
public class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameService gameService;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(gameService, "round", "round");
    }

    @Test
    @DatabaseTearDown(value = "/dbunit/GameControllerIntegrationTest/game_record_clear_all.xml")
    public void should_return_game_record_response_when_guess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/guess").queryParam("user-answer", "1234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userGuess", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.compareResult", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.leftTimes", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isWinning", Matchers.notNullValue()));
    }

    @Test
    @DatabaseSetup(value = "/dbunit/GameControllerIntegrationTest/should_return_guess_record_response_when_find_guess_history_setup.xml")
    @DatabaseTearDown(value = "/dbunit/GameControllerIntegrationTest/game_record_clear_all.xml")
    public void should_return_guess_record_response_when_find_guess_history() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/find-guess-history"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userGuess", Matchers.is("1234")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].compareResult", Matchers.is("1A2B")));
    }
}
