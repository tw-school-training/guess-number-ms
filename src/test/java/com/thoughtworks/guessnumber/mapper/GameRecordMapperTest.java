package com.thoughtworks.guessnumber.mapper;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.thoughtworks.guessnumber.entity.GameRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
@SpringBootTest
@ActiveProfiles("test")
public class GameRecordMapperTest {

    @Autowired
    private GameRecordMapper gameRecordMapper;

    @Test
    @DatabaseTearDown(value = "/dbunit/GameRecordMapperTest/game_record_clear_all.xml", type = DatabaseOperation.DELETE_ALL)
    @DatabaseSetup(value = "/dbunit/GameRecordMapperTest/game_record_clear_all.xml", type = DatabaseOperation.DELETE_ALL)
    @ExpectedDatabase(value = "/dbunit/GameRecordMapperTest/should_be_the_same_as_input_when_save_given_game_record_expected_database.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void should_be_the_same_as_input_when_save_given_game_record() {
        GameRecord gameRecord = GameRecord.builder()
                .id("id")
                .compareResult("1A2B")
                .isWinning(false)
                .leftTimes(4)
                .ticket("expectedTicket")
                .userGuess("1234")
                .build();

        gameRecordMapper.save(gameRecord);

    }

}
