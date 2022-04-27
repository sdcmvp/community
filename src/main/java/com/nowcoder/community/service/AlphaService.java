package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//业务组件Service
//这个bean有容器来管理，不仅是创建他，还希望容器来管理初始化以及销毁的方法
@Service
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService() {
        System.out.println("实例化AlphaService");
    }

    //@PostConstruct表示会在构造器之后调用
    @PostConstruct
    public void init() {
        System.out.println("初始化AlphaService");
    }

    //销毁前调用，不然看不到
    @PreDestroy
    public void destroy() {
        System.out.println("销毁AlphaService");
    }


    public String find() {
       return alphaDao.select();
    }

}
