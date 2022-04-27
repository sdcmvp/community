package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

//装配容器需要注解
//访问数据库的bean
@Repository
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}
