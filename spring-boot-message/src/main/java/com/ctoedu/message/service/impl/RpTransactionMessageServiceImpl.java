package com.ctoedu.message.service.impl;

import com.ctoedu.message.mapper.RpTransactionMessageMapper;
import com.ctoedu.message.model.RpTransactionMessage;
import com.ctoedu.message.service.RpTransactionMessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "rpTransactionMessageService")
public class RpTransactionMessageServiceImpl implements RpTransactionMessageService {

    @Autowired
    private RpTransactionMessageMapper rpTransactionMessageMapper;

    @Override
    public int addRpTransactionMessage(RpTransactionMessage message) {
        return rpTransactionMessageMapper.insertSelective(message);
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public List<RpTransactionMessage> findAllMessage(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<RpTransactionMessage> list = rpTransactionMessageMapper.selectAllMessage();
        /*PageInfo<RpTransactionMessage> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getTotal());*/
        return list;
    }
}
