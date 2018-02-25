package com.ctoedu.message.service;

import com.ctoedu.message.model.RpTransactionMessage;

import java.util.List;

public interface RpTransactionMessageService {

    int addRpTransactionMessage(RpTransactionMessage message);

    List<RpTransactionMessage> findAllMessage(int pageNum, int pageSize);
}
