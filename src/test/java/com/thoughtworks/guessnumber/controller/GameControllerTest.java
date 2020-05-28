package com.thoughtworks.guessnumber.controller;

import com.thoughtworks.guessnumber.entity.GameRecord;
import com.thoughtworks.guessnumber.service.GameService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    public void should_return_game_record_response_when_guess() throws Exception {
        GameRecord gameRecord = buildGameRecord();
        Mockito.when(gameService.guess(Mockito.anyString())).thenReturn(gameRecord);

        mockMvc.perform(MockMvcRequestBuilders.get("/guess").queryParam("user-answer", "1234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userGuess", Matchers.is(gameRecord.getUserGuess())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.compareResult", Matchers.is(gameRecord.getCompareResult())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.leftTimes", Matchers.is(gameRecord.getLeftTimes())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isWinning", Matchers.is(gameRecord.isWinning())));
    }

    @Test
    public void should_return_guess_record_response_when_find_guess_history() throws Exception {
        List<GameRecord> gameRecords = List.of(
                buildGameRecord()
        );

        Mockito.when(gameService.findGameRecords()).thenReturn(gameRecords);

        mockMvc.perform(MockMvcRequestBuilders.get("/find-guess-history"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(gameRecords.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userGuess", Matchers.is(gameRecords.get(0).getUserGuess())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].compareResult", Matchers.is(gameRecords.get(0).getCompareResult())));
    }

    private GameRecord buildGameRecord() {
        return GameRecord.builder()
                .round("gameRound")
                .userGuess("1234")
                .leftTimes(4)
                .compareResult("1A2B")
                .isWinning(false)
                .id("id")
                .build();
    }

}
