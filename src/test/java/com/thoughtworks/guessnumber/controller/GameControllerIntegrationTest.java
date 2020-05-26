package com.thoughtworks.guessnumber.controller;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        MockitoTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
public class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseTearDown(value = "/dbunit/GameControllerIntegrationTest/should_return_game_record_response_when_guess_tear_down.xml")
    public void should_return_game_record_response_when_guess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/guess").queryParam("user-answer", "1234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userGuess", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.compareResult", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.leftTimes", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isWinning", Matchers.notNullValue()));
    }
}
