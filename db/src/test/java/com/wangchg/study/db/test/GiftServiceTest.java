package com.wangchg.study.db.test;


import com.google.common.collect.Lists;
import com.wangchg.study.db.model.Gift;
import com.wangchg.study.db.service.GiftService;
import com.wangchg.study.db.test.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-bootstrap.xml"})
public class GiftServiceTest {
    private static final Logger Log = LoggerFactory.getLogger(GiftServiceTest.class);
    @Autowired
    private GiftService giftService;

    /**
     * 测试并发
     */
    @Test
    public void testGetGift() {
        final long id = 1;
        ExecutorService executor = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 3000; i++) {
            executor.execute(new SecKillTask(id, giftService));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.info("执行完毕");
    }

    /**
     * 生成测试数据
     */
    @Test
    public void testBatchInsertGift() {
        List<Gift> giftList = Lists.newArrayList();
        for (int i = 0; i < 3000; i++) {
            Gift gift = new Gift();
            gift.setKey(UUID.randomUUID().toString());
            giftList.add(gift);
        }
        long startTime = System.currentTimeMillis();
        giftService.batchInsertGift(giftList);
        long endTime = System.currentTimeMillis();
        Log.info("执行时间：{}", (endTime - startTime) / 1000);
    }

    static class SecKillTask implements Runnable {
        private Long id;
        private GiftService giftService;

        public SecKillTask(Long id, GiftService giftService) {
            this.id = id;
            this.giftService = giftService;
        }

        @Override
        public void run() {
            String mobile = Utils.getTel();
            giftService.executeSecKill(id, mobile);
        }
    }

}
