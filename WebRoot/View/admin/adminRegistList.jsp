<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath = request.getContextPath();
%>
<script type="text/javascript">
	var basePath = '<%=basePath %>';
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员列表</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/Resource/frameworks/easyui-1.4.4/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/Resource/frameworks/easyui-1.4.4/themes/bootstrap/datagrid.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/Resource/frameworks/easyui-1.4.4/themes/color.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/Resource/frameworks/easyui-1.4.4/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/Resource/css/admin/adminList.css" /> 
<link rel="stylesheet" type="text/css" href="<%=basePath%>/Resource/frameworks/layer2.1/skin/layer.css">
<script type="text/javascript" src="<%=basePath%>/Resource/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>/Resource/frameworks/easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/Resource/frameworks/easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/Resource/frameworks/layer2.1/layer.js"></script> 
<script type="text/javascript" src="<%=basePath%>/Resource/js/Utils.js"></script> 
<script type="text/javascript" src="<%=basePath%>/Resource/js/admin/adminRegistHandle.js"></script>
</head>
<body>
<div class="admin_list_main" >
	<div class="admin_list_center">
		<!-- 搜索 -->
		<div id="searchDiv" class="admin_list_search_div" >
			<span>
				管理员工号：<input id="adminIdText" class="easyui-textbox"  style="width: 135px;">
			</span>
			<span style="margin-left: 50px;">
				管理员姓名：<input id="adminNameText" class="easyui-textbox"  style="width: 135px;">
			</span>
			<span style="margin-left: 50px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  style="width: 85px;"
				onclick="loadAdminList();">筛选</a>
			</span>
			<span style=" padding-left: 80px;">
				<a href="javascript:void(0)" onclick="clearSearchBox();" style="text-decoration: none;">清空条件</a>
			</span>
		</div>
		<div id="registList" class="admin_list_adminList"></div>
	</div>
</div> 
</body>
</html>