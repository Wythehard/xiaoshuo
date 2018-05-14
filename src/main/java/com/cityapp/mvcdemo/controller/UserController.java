package com.cityapp.mvcdemo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.cityapp.mvcdemo.domain.city;
import com.cityapp.mvcdemo.helper.ExportExcel;
import com.cityapp.mvcdemo.helper.page;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import com.cityapp.mvcdemo.domain.User;
import com.cityapp.mvcdemo.service.IUserService;
import com.cityapp.mvcdemo.service.IcityService;
import com.cityapp.mvcdemo.service.impl.cityServiceImpl;
//import com.cityapp.mvcdemo.service.impl.cityServiceImpl;
import com.fasterxml.jackson.annotation.JsonRawValue;


@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	@Autowired
	/*
	 * @Resource(name = "singleTransactionsService")
		private SingleTransactionsService singleTransactionsService;
		SingleTransactionsServiceImpl

		@Service("singleTransactionsService")

	 */
	private IcityService cityService;


	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}

	@RequestMapping("/delete")
	public String deleteCity(HttpServletRequest request,Model model){
		try{
			int cityId = Integer.parseInt(request.getParameter("id"));

			String pathRoot = "/Users/wulinzhen/mavenwork/cityapp/src/main/webapp";
	        String path="";
	        city city = this.cityService.getcityById(cityId);
	        path=city.getImg();
	        try{
	        	File dfile = new File(pathRoot+path);
	        	dfile.delete();
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());
	        }

	        int result = this.cityService.delete(city.getId());
			model.addAttribute("result", result);
			return "delete";
		}catch (Exception e){
			return "error";
		}
	}

	@RequestMapping("/update")
	public String updateCity(HttpServletRequest request,Model model)
	{
		String  _error = "";
		int cityId = Integer.parseInt(request.getParameter("id"));
		city city = this.cityService.getcityById(cityId);
		String  cityName = request.getParameter("name");
		String  countryCode = request.getParameter("countryCode");
		String  district = request.getParameter("district");
		int population =0;
		if(cityName == null) {
			_error +="城市名称不能为空！<br />";
		}
		if(request.getParameter("population") != null)
		{
			try {
				 population = Integer.parseInt(request.getParameter("population"));
				if(population < 0)
				{
					_error += "人口数请输入正整数！<br />";
				}
			} catch(Exception e) {
				_error += "人口数请输入整数！<br />";
			}
		} else {
			_error += "人口数不能为空！<br />";
		}
		if(countryCode == null) {
			_error +="城市名称不能为空！<br />";
		} else {
			//应该正则匹配的。
			if(countryCode.length()!=3){
				_error += "请输入正确的国家代码！<br />";
			}
		}
		String img = "";


		if(_error != "")
		{
			if(request.getParameter("submit")==null)
			{
				_error="";
			}
			model.addAttribute("_error", _error);
			model.addAttribute("date", city);
		} else {
			city acity = new city();
			acity.setId(cityId);
			acity.setName(cityName);
			acity.setCountrycode(countryCode);
			acity.setDistrict(district);
			acity.setPopulation(population);
			acity.setImg(img);
			int i = this.cityService.update(acity);
			model.addAttribute("updatedate", i);
		}
		return "showUser";
	}

	@RequestMapping("/insert")
	public String insertCity(HttpServletRequest request,Model model)
	{
		MultipartFile file=null;
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext())
            {
                //一次遍历所有文件
                file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="E:/springUpload"+file.getOriginalFilename();
                    //上传
                    try{
                    	file.transferTo(new File(path));
                    } catch (Exception e){
                    	e.printStackTrace();
                    }
                }
            }
        }


		String  _error = "";
		String  cityName = request.getParameter("name");
		String  countryCode = request.getParameter("countryCode");
		String  district = request.getParameter("district");
		int population =0;
		if(cityName == null) {
			_error +="城市名称不能为空！<br />";
		}
		if(request.getParameter("population") != null)
		{
			try {
				 population = Integer.parseInt(request.getParameter("population"));
				if(population < 0)
				{
					_error += "人口数请输入正整数！<br />";
				}
			} catch(Exception e) {
				_error += "人口数请输入整数！<br />";
			}
		} else {
			_error += "人口数不能为空！<br />";
		}
		if(countryCode == null) {
			_error +="城市名称不能为空！<br />";
		} else {
			//应该正则匹配的。
			if(countryCode.length()!=3){
				_error += "请输入正确的国家代码！<br />";
			}
		}
		String img = "";
		//获得物理路径webapp所在路径
        String pathRoot = "/Users/wulinzhen/mavenwork/cityapp/src/main/webapp";
        String path="";

        if(file!=null && !file.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            path="/static/images/"+uuid+"."+imageName;
            try{
            	file.transferTo(new File(pathRoot+path));
            } catch(Exception e )
            {
            	System.out.println(e.getMessage());
            }
            img=path;
        }


		if(_error != "")
		{
			if(request.getParameter("submit")==null)
			{
				_error="";
			}
			model.addAttribute("_error", _error);
		} else {
			city acity = new city();
			acity.setName(cityName);
			acity.setCountrycode(countryCode);
			acity.setDistrict(district);
			acity.setPopulation(population);
			acity.setImg(img);
			int i = this.cityService.insert(acity);
			model.addAttribute("insertdate", i);
		}
		return "showUser";
	}

	@RequestMapping("/findAll")
	public String findCity(HttpServletRequest request,Model model)
	{
		String pageNow = request.getParameter("page");
        page page = null;
        List<city> result = new ArrayList<city>();
        String district = null;
        String countryCode=null;

        if(request.getParameter("id")!=null && request.getParameter("id").length()>0)
        {
        	result.add(this.cityService.getcityById(Integer.parseInt(request.getParameter("id"))));
        	model.addAttribute("result",result);
        	return "searchAll";
        }
        if(request.getParameter("district")!=null)
        {

        	district= '%'+request.getParameter("district")+'%';
        	if(request.getParameter("district").equals("%%%%"))
        	{
        		district="%%";
        	}
        }
        else
        {
        	district="%%";
        }
        if(request.getParameter("countryCode")!=null)
        {

        	countryCode = '%'+request.getParameter("countryCode")+'%';
        	if(request.getParameter("countryCode").equals("%%%%"))
        	{
        		countryCode="%%";
        	}
        }
        else {
        	countryCode="%%";
        }
        int totalCount = this.cityService.selectAllCount(district, countryCode).size();
        if (pageNow != null) {
            page = new page(totalCount, Integer.parseInt(pageNow));
            result = this.cityService.selectAllPerPage(district,countryCode,page.getStartPos(), page.getPageSize());
        } else {
            page = new page(totalCount, 1);
            result = this.cityService.selectAllPerPage(district,countryCode,page.getStartPos(), page.getPageSize());
        }
        model.addAttribute("result", result);
        model.addAttribute("page", page);
        model.addAttribute("district", URLEncoder.encode(district));
        model.addAttribute("countryCode", URLEncoder.encode(countryCode));
        return "searchAll";
	}

	@RequestMapping("/apiFindAll")
	@ResponseBody

	public String apiFindCity(HttpServletRequest request,Model model)
	{
		String pageNow = request.getParameter("page");
        page page = null;
        List<city> result = new ArrayList<city>();
        String district = null;
        String countryCode=null;

        if(request.getParameter("id")!=null && request.getParameter("id").length()>0)
        {
        	result.add(this.cityService.getcityById(Integer.parseInt(request.getParameter("id"))));
        	JSONArray jsonStu = JSONArray.fromObject(result);
        	return jsonStu.toString();
        }
        if(request.getParameter("district")!=null)
        {
        	district= '%'+request.getParameter("district")+'%';
        }
        else
        {
        	district="%%";
        }
        if(request.getParameter("countryCode")!=null)
        {
        	countryCode = '%'+request.getParameter("countryCode")+'%';
        }
        else {
        	countryCode="%%";
        }
        int totalCount = this.cityService.selectAllCount(district, countryCode).size();
        if (pageNow != null) {
            page = new page(totalCount, Integer.parseInt(pageNow));
            result = this.cityService.selectAllPerPage(district,countryCode,page.getStartPos(), page.getPageSize());
        } else {
            page = new page(totalCount, 1);
            result = this.cityService.selectAllPerPage(district,countryCode,page.getStartPos(), page.getPageSize());
        }
        	JSONArray jsonStu = JSONArray.fromObject(result);
        	return jsonStu.toString();
	}




	@RequestMapping("/exportExcel")
	public String exportExcel(HttpServletRequest request,Model model)
	{
		String pageNow = request.getParameter("page");
        page page = null;
        List<city> result = new ArrayList<city>();
        String district = null;
        String countryCode=null;
        if(request.getParameter("district")!=null)
        {
        	district= '%'+request.getParameter("district")+'%';
        }
        else
        {
        	district="%%";
        }
        if(request.getParameter("countryCode")!=null)
        {
        	countryCode = '%'+request.getParameter("countryCode")+'%';
        }
        else {
        	countryCode="%%";
        }
        int totalCount = this.cityService.selectAllCount(district, countryCode).size();

        return "searchAll";
	}



	@RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        //需要导入alibaba的fastjson包

		String district = null;
        String countryCode=null;
        if(request.getParameter("district")!=null)
        {
        	district= '%'+request.getParameter("district")+'%';
        }
        else
        {
        	district="%%";
        }
        if(request.getParameter("countryCode")!=null)
        {
        	countryCode = '%'+request.getParameter("countryCode")+'%';
        }
        else {
        	countryCode="%%";
        }
        List<city> userlList = this.cityService.selectAllCount(district, countryCode);
        ExportExcel<city> ee= new ExportExcel<city>();
        String[] headers = { "ID", "Name", "CountryCode", "District","Population","img"};
        String fileName = "地区信息表";
        ee.exportExcel(headers,userlList,fileName,response);
	}
}
