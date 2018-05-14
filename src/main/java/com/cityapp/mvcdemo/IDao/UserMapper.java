package com.cityapp.mvcdemo.IDao;

import org.springframework.stereotype.Repository;

import com.cityapp.mvcdemo.domain.User;
@Repository
public interface UserMapper {
 
    int deleteByPrimaryKey(Integer id);

    int insert(User record);
    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}