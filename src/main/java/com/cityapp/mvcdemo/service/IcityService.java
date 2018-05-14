package com.cityapp.mvcdemo.service;

import java.util.List;

import com.cityapp.mvcdemo.domain.city;

public interface  IcityService {
	public city getcityById(int cityId);
	public int insert(city acity);
	public int delete(int cityId);
	public int update(city acity);
	public List<city> selectAll();
	public List<city> selectAllPerPage(String district,String countryCode, int startPos,int pageSize);
	public List<city> selectAllCount(String district,String countryCode);
}
