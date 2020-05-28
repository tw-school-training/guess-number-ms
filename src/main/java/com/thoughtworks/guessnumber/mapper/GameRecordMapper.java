package com.thoughtworks.guessnumber.mapper;

import com.thoughtworks.guessnumber.entity.GameRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameRecordMapper {

    void save(GameRecord gameRecord);

    List<GameRecord> findGameRecordsByRound(String round);
}
