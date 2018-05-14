<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>城市信息</title>
  </head>
  <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
  <body>
  <div class="container">
  <span style="color:red;"><c:if test="${empty _error =='false'}"><c:out value="${_error}" escapeXml="false"></c:out> </c:if></span>
	<form class="" action="" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<div class="form-group">城市名称：<input type="text" class="form-control" name="name" value="${date.name}"></div>
			<div class="form-group">国家编码：<input type="text" class="form-control" name="countryCode" value="${date.countrycode}"></div>
			<div class="form-group">地区名称：<input type="text" class="form-control" name="district" value="${date.district}"></div>
			<div class="form-group">人口数量：<input type="text" class="form-control" name="population" value="${date.population}"></div>
			<div class="form-group">城市图片：<input type="file" class="form-control-file" name="imgfile" value="${date.img}"></div>
			<div><input type="hidden" name="submit" value="yes"><button class="btn btn-success" type="submit">提交</button>
			<a href="/user/findAll"></a><button class="btn btn-success" type="submit" style="display:inline-block;margin-left:100px;">
				查询数据
			</button></a></div>
		</div>
	</form>
	<c:if test="${insertdate==1}" ><c:out value="插入城市数据成功" escapeXml="false"></c:out> </c:if>
	<c:if test="${updatedate==1}" ><c:out value="更新城市数据成功" escapeXml="false"></c:out> </c:if>
	</div>
  </body>
  
</html>