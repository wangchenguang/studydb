package com.wangchg.study.db.service.impl;

import com.wangchg.study.db.exception.SecKillCloseException;
import com.wangchg.study.db.exception.SecKillOpenException;
import com.wangchg.study.db.mapper.GiftLogMapper;
import com.wangchg.study.db.mapper.GiftMapper;
import com.wangchg.study.db.mapper.GiftNumMapper;
import com.wangchg.study.db.model.Gift;
import com.wangchg.study.db.model.GiftLog;
import com.wangchg.study.db.model.GiftNum;
import com.wangchg.study.db.service.GiftService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {
    private static final Logger Log = LoggerFactory.getLogger(GiftServiceImpl.class);
    private static final int STEP = 2000;

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private GiftNumMapper giftNumMapper;

    @Autowired
    private GiftLogMapper giftLogMapper;

    @Autowired
    private GiftMapper giftMapper;

    @Transactional
    public void batchInsertGift(List<Gift> gifts) {
        int i = 1;
        GiftMapper giftMapper = sqlSession.getMapper(GiftMapper.class);
        Iterator<Gift> iterator = gifts.iterator();
        while (iterator.hasNext()) {
            Gift gift = iterator.next();
            giftMapper.insertGift(gift);
            iterator.remove();
            if (i % STEP == 0) {
                giftMapper.flush();
                Log.info("GIFT 当前存储到{}", i);
            }
            i++;
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void executeSecKill(Long id, String mobile) {
        Date now = new Date();
        GiftNum giftNum = giftNumMapper.queryGiftNumById(id);
        long nowTime = now.getTime();
        if (nowTime > giftNum.getEndTime().getTime()) {
            throw new SecKillCloseException();
        }
        if (nowTime < giftNum.getStartTime().getTime()) {
            throw new SecKillOpenException();
        }
        if (giftNum.getNum() <= 0) {
            throw new SecKillCloseException();
        }
        Gift gift = giftMapper.selectRandGift();
        giftMapper.loseEfficacyById(gift.getId());
        GiftLog giftLog = new GiftLog();
        giftLog.setGiftId(gift.getId());
        giftLog.setMobile(mobile);
        giftNumMapper.reduceNumber(id);
        giftLogMapper.insertGiftLog(giftLog);
    }
}
