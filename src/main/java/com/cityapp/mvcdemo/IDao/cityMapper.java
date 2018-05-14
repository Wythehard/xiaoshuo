package com.cityapp.mvcdemo.IDao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cityapp.mvcdemo.domain.city;
@Repository
public interface cityMapper {
  
    int deleteByPrimaryKey(Integer id);


    int insert(city record);

    int insertSelective(city record);

    city selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(city record);

    int updateByPrimaryKey(city record);
    
    List<city> selectAll();
    List<city> selectAllPerPage(@Param(value="district")String district,@Param(value="countryCode")String countryCode,@Param(value="startPos")Integer startPos,@Param(value="pageSize")Integer pageSize );
    List<city> selectAllCount(@Param(value="district")String district,@Param(value="countryCode")String countryCode);

}