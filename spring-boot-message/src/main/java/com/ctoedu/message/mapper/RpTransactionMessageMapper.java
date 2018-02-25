package com.ctoedu.message.mapper;

import com.ctoedu.message.model.RpTransactionMessage;

import java.util.List;

public interface RpTransactionMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(RpTransactionMessage record);

    int insertSelective(RpTransactionMessage record);

    RpTransactionMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RpTransactionMessage record);

    int updateByPrimaryKeyWithBLOBs(RpTransactionMessage record);

    int updateByPrimaryKey(RpTransactionMessage record);

    //查询所有消息
    List<RpTransactionMessage> selectAllMessage();
}