package com.ctoedu.message.controller;

import com.ctoedu.message.model.RpTransactionMessage;
import com.ctoedu.message.service.RpTransactionMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/message")
public class RpTransactionMessageController {
    @Autowired
    private RpTransactionMessageService rpTransactionMessageService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addMessage(RpTransactionMessage message) {
        return rpTransactionMessageService.addRpTransactionMessage(message);
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllMessage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {

        return rpTransactionMessageService.findAllMessage(pageNum, pageSize);
    }
}
