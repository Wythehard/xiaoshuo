package com.cityapp.mvcdemo.service.impl;
import com.cityapp.mvcdemo.domain.city;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cityapp.mvcdemo.IDao.cityMapper;
import com.cityapp.mvcdemo.service.IcityService;

@Service("cityService")
public class cityServiceImpl implements IcityService {
	@Resource
	private cityMapper cityDao;
	
	public int insert(city acity)
	{
		return this.cityDao.insert(acity);	
	}

	public city getcityById(int cityId) {
		return this.cityDao.selectByPrimaryKey(cityId);
	}
	public int delete(int cityId) {
		return this.cityDao.deleteByPrimaryKey(cityId);
	}

	public int update(city acity) {
		return this.cityDao.updateByPrimaryKey(acity);
	}

	public List<city> selectAll() {
		
		return this.cityDao.selectAll();
	}

	public List<city> selectAllPerPage(String district, String countryCode, int startPos, int pageSize) {
		return this.cityDao.selectAllPerPage( district,  countryCode,  startPos,  pageSize);
	}

	public List<city> selectAllCount(String district, String countryCode) {
		return this.cityDao.selectAllCount( district,  countryCode);
	}

	
	
}
