package com.wangchg.study.db.service;

import com.wangchg.study.db.model.Gift;

import java.util.List;

public interface GiftService {
    void batchInsertGift(List<Gift> gifts);

    void executeSecKill(Long id, String mobile);
}
