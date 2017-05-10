<%@page import="com.sicnu.liaoshijie.labEqmMS.model.AdministratorVo"%>
<%@page import="com.sicnu.liaoshijie.labEqmMS.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	AdministratorVo adminVo = (AdministratorVo)session.getAttribute("adminVo");	
	String basePath = request.getContextPath();
%>
<% if(adminVo == null) { %>
<script type="text/javascript">
	alert('登录已过期或还未登录，请重新登录');
	window.location.href = 'login.html';
</script>
<%} %>
<script type="text/javascript">
	var basePath = '<%=basePath %>';
	var adminId = '<%=adminVo.getAdminID() %>';
	var adminName = '<%= adminVo.getAdminName() %>';
	var adminLevel = '<%=adminVo.getAdminLevel() %>';
	var adminIDCardNo = '<%=adminVo.getAdminIDCardNo() %>';
	var adminBirthday = '<%=DateUtil.dateToStringYMD(adminVo.getAdminBirthday()) %>';
	var adminAddress = '<%=adminVo.getAdminAddress() %>';
	var adminNativePlace = '<%=adminVo.getAdminNativePlace() %>';
	var admineTel = '<%=adminVo.getAdminTel() %>';
	var adminEmail = '<%=adminVo.getAdminEmail() %>';
	var adminSex = '<%=adminVo.getAdminSex() %>';
	var adminCollege = '<%=adminVo.getAdminLevel() %>';
	var adminDept = '<%=adminVo.getAdminDept() %>';
</script>	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>四川师范大学设备管理中心</title> 
<link rel="stylesheet" type="text/css" href="<%=basePath %>/Resource/css/main.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/Resource/css/menu.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/Resource/frameworks/layer2.1/skin/layer.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/Resource/frameworks/layer2.1/skin/layer.ext.css">
<script type="text/javascript" src="<%=basePath %>/Resource/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath %>/Resource/frameworks/layer2.1/layer.js"></script> 
<script type="text/javascript" src="<%=basePath %>/Resource/frameworks/layer2.1/extend/layer.ext.js"></script>
<script type="text/javascript" src="<%=basePath %>/Resource/js/Utils.js"></script> 
<script type="text/javascript" src="<%=basePath %>/Resource/js/main.js"></script>
</head>
<body>
<div class="main" >
	<div class="logo"></div>
	<div class="center">
		<div class="info_show">
			<span style="float: left; ">欢迎您，
				<a id="showName" style="color: #2ca925; font-size: 16px; font-weight: bold;"></a> 
			</span>
			<span style="margin-left: 150px;">您的角色：
				<a id="level" style=" font-size: 16px; font-weight: bold; cursor: pointer;"></a>
			</span>
			<span style="float: right;">
				<a href="javascript:void(0)" onclick="loginOut();">退出登录</a>
			</span>
		</div>
		<div class="left_show">
			<!-- <div class="null_line"></div> -->
			
			<div class="navigator">
				<div class="lanmu-content">
					<dl class="lanmu-list">
						<dt>我的操作菜单</dt>
						<dd id="d1" style="display: none;"><a id="showEqmList" name="operation"  href="javascript:void(0)" onclick="showAllEqmList()" target="showContent">查看所有设备</a></dd>
						<dd id="d2" style="display: none;"><a id="myEqmList" name="operation" href="javascript:void(0)"  onclick="showMyEqmList()" target="showContent">我的托管设备</a></dd>
						<dd id="d4" style="display: none;"><a id="adminInfo" name="operation" href="javascript:void(0)" onclick="lendOutAndReturnAndRepair_admin();">设备处理（管理员）</a></dd>
						<dd id="d5" style="display: none;"><a id="adminInfo" name="operation" href="javascript:void(0)" onclick="lendOutWindow();">设备借出/归还</a></dd>
						<dd id="d6" style="display: none;"><a id="adminInfo" name="operation" href="javascript:void(0)" onclick="repairWindow();">设备报修</a></dd>
						<dd id="d7" style="display: none;"><a id="adminInfo" name="operation" href="javascript:void(0)" onclick="scrapWindow();">设备报废</a></dd>
						<dd id="d8" style="display: none;"><a id="adminInfo" name="operation" href="javascript:void(0)" onclick="showAdminInfo();">我的个人信息</a></dd>
						<dd id="d9" style="display: none;"><a id="showAdminList" name="operation" href="javascript:void(0)" onclick="showAdminList();" target="showContent">管理员管理</a></dd>
						<dd id="d10" style="display: none;"><a name="operation" href="javascript:void(0)" onclick="aboutSystem();">关于本站</a></dd>
						<dd id="d3" style="display: none;"><a id="adminInfo" name="operation" href="javascript:void(0)" onclick="lendOutAndReturnAndRepair_user();">设备借出/归还/报修（用户）</a></dd>
						<dd id="d11" style="display: none;"><a></a></dd>
						<dd id="d12" style="display: none;"><a></a></dd>
						<dd id="d13" style="display: none;"><a></a></dd>
						<dd id="d14" style="display: none;"><a></a></dd>
						<dd id="d15" style="display: none;"><a></a></dd>
						<dd id="d16" style="display: none;"><a></a></dd>
						<dt>我的操作菜单</dt>
					</dl>
				</div>
			</div>
		</div>
		<div class="right_show">
			<iframe id="showContent" name="showContent"  width="100%" height="100%"  frameborder="no" border="0" 
			 src="<%=basePath %>/View/equipment/allEqmList.jsp" marginwidth="0" marginheight="0" allowtransparency="yes"> 
			</iframe>
		</div>
	</div>
</div>
<div id="div_permissionInfo" class="div_permissionInfo">
	<font style="font-size: 15px; font-weight: bold;">用户角色说明：</font><br><hr>
	1.<font style="margin-left: 5px; font-size: 15px; color: red;">学生/普通教职工</font>：借出/归还/报修设备；普通教职工可以注册为管理员；<br><hr>
	2.<font style="margin-left: 5px; font-size: 15px; color: red;">实验室管理员</font>：管理机房以及机房内设备；<br><hr>
	3.<font style="margin-left: 5px; font-size: 15px; color: red;">设备管理员</font>：管理维护特殊设备，如摄像机，照相机；<br><hr>
	4.<font style="margin-left: 5px; font-size: 15px; color: red;">超级管理员</font>：最高权限，可以操作其他管理员的权限。<br><hr>
</div> 
<div class="footer">
	<span><font color="red">狮子山校区：</font>成都市锦江区静安路5号 (邮编:610068)</span>
	<span style="margin-left: 15px;"><font color="red">成龙校区：</font>成都市龙泉驿区成龙大道二段1819号 (邮编:610101)</span>
	<span style="margin-left: 15px;"><font color="red">东校区：</font>成都市外东洪河中路351号 (邮编:610101)</span><br/> 
	<span>© All Right Reserved . 四川师范大学 版权所有 (四川师范大学后勤部制作与维护) 蜀ICP备05026983号</span><br/> 
	<span>开发团队：<a href="http://weibo.com/u/3174692593?is_hot=1" target="_blank">现象级吃货科技</a></span>
	<span style="margin-left: 15px;">团队联系方式-TEL：13228170623   微博：天天都是实力派吃货  QQ：374682617</span>
</div>
 
</body>
</html>