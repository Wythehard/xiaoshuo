<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchAll.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">


  </head>
  
  <body>
  <div class="container">
  

   		
      <form method="post" class="form-group" action="user/findAll" >  
      <div class=" mb-3"><a href="/user/insert"><button type="button" class="btn btn-secondary"> 插入数据</button></a></div>
        <div class="form-group row"><label class="col-2 ">ID</label><div class="col-3"><input class="form-control" name="id" type="text" > </div></div>
        <div class="form-group row"><label class="col-2 ">国家代码（模糊查询）</label><div class="col-3"><input class="form-control" name="countryCode" type="text" > </div></div>
        <div class="form-group row"><label class="col-2 ">地区名称（模糊查询）</label><div class="col-3"><input class="form-control" name="district" type="text" ></div> </div>
        <div class="row"><input class="offset-md-4 btn btn-success" value="查询" type="submit"> </div>
      </form>  
      <table  class="table  table-striped table-bordered"  >  
         <thead>  
                     <tr align="center">  
                           <th colspan="10"  >地区信息管理</td>  
                     </tr>        
                    <tr align="center">
                      <th>城市ID</th>
                      <th>城市名称</th>
                      <th>国家代码</th>
                      <th>地区</th>
                      <th>人数</th>
                      <th>图片地址</t>
                      <th>操作</th>
                    </tr>
         </thead>  
         <tbody>  
            
            <c:forEach items="${result}" var="c">  
              
              <tr>  
                <td>  ${c.id}  </td>  
                <td> ${c.name} </td>  
                <td> ${c.countrycode} </td>  
                <td> ${c.district} </td>  
                <td> ${c.population} </td>  
                <td> ${c.img} </td>  
                <td><a href="user/delete?id=${c.id}">删除</a> <a href="user/update?id=${c.id}">更新</a> &nbsp;&nbsp;&nbsp;<a href="${c.img}">查看图片</a></td>  
            </tr>  
              
            </c:forEach>  
         </tbody>  
      </table>  
      <div style="margin-left:300px;">
      <c:if test="${page.isHasPre()}"><a href="user/findAll?page=${page.pageNow-1}&countryCode=${countryCode}&district=${district}">上一页</a></c:if>
      <c:if test="${page.isHasNext()}"><a href="user/findAll?page=${page.pageNow+1}&countryCode=${countryCode}&district=${district}">下一页</a></c:if>
      </div>
    
    </div>
  </body>
</html>
