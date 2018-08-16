package com.wangchg.study.db.controller;

import com.wangchg.study.db.model.Gift;
import com.wangchg.study.db.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gift")
public class GiftController {
    @Autowired
    private GiftService giftService;

    @GetMapping
    public List<Gift> get() {
        return null;
    }

}
