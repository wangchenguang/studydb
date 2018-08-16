package com.wangchg.study.db.mapper;

import com.wangchg.study.db.model.GiftNum;

public interface GiftNumMapper {
    int reduceNumber(Long id);

    GiftNum queryGiftNumById(Long id);
}
