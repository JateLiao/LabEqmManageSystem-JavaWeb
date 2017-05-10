<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String adminId = (String)session.getAttribute("adminId");
	String basePath = request.getContextPath();
%>
<script type="text/javascript">
	var basePath = '<%=basePath %>';
	var adminId = '<%=adminId %>';
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有设备列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/bootstrap/datagrid.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/color.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/css/equipment/equipmentList.css" /> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/layer2.1/skin/layer.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/frameworks/layer2.1/layer.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/js/Utils.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/js/equipment/allEqmList.js"></script>
</head>
<body>
<div class="eqm_list_main" >
	<div class="eqm_list_center">
		<!-- 搜索设备 -->
		<div id="searchDiv" class="eqm_list_search_div" >
			<span>资产号：<input id="propertyNoText" class="easyui-textbox" style="width: 125px;" style="width: 125px;"></span>
			<span style="margin-left: 30px;">设备名：<input id="eqmNameText" class="easyui-textbox" style="width: 125px;"></span>
			<span style="margin-left: 30px;">
				托管人：<input id="managerText" name="managerText" class="easyui-textbox" style="width: 125px;">
			</span> 
			<span style="margin-left: 30px;">
				设备状态：
				<select id="eqmStatus_search" class="easyui-combobox" editable="false"  style="width: 110px; ">
					<option>-请选择状态-</option>
					<option>使用中</option>
					<option>库存中</option>
					<option>维修中</option>
				    <option>已报废</option>
					<option>其他</option>
				</select>
			</span> 
			<div style="margin-top: 8px; margin-bottom:8px; float: left;">
				<span>
					开始日期：<input id="startDate" class="easyui-datebox" editable="false" style="width: 130px;">
				</span>
				<span style="margin-left: 30px;">
					结束日期：<input id="endDate"   class="easyui-datebox" editable="false" style="width: 130px;">
				</span>
				<span style="margin-left: 100px;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" style="width: 75px;"
					onclick="loadEquipmentList();">筛选</a>
				</span>
				<span style=" padding-left: 80px;">
					<a href="javascript:void(0)" onclick="clearSearchBox();" style="text-decoration: none;">清空条件</a>
				</span>
			</div>
		</div>
		<div id="eqmList" class="eqm_list_eqmList"></div>
	</div>
</div>
</body>
</html>