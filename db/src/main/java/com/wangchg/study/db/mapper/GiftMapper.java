package com.wangchg.study.db.mapper;

import com.wangchg.study.db.model.Gift;
import org.apache.ibatis.annotations.Flush;

import java.util.List;

public interface GiftMapper {
    void insertGift(Gift gift);

    @Flush
    List<Gift> flush();

    Gift selectRandGift();

    int loseEfficacyById(Long id);
}
