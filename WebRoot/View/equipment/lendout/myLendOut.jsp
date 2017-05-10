<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>我的借出</title>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/js/equipment/myLendOut.js"></script>
<style type="text/css">
body input{ 
	padding-bottom: 3px ! important;
	padding-top: 3px ! important;
}
</style>
</head>
<body>
<div class="eqm_list_lend_main" >
	<div class="eqm_list_center_myLend">
		<!-- 搜索设备 -->
		<div id="searchDiv" class="eqm_list_search_my_lend_div" >
			<span>设备名：<input id="eqmNameText" class="easyui-textbox" style="width: 125px;"></span>
			<span style="margin-left: 60px;">
				设备资产号：<input id="propertyNoText" name="managerText" class="easyui-textbox" style="width: 125px;">
			</span>
			<span style="margin-left: 60px;">
				处理状态：
				<select id="handleStatusText" class="easyui-combobox" data-options="editable:false,panelHeight:'135px'" style="width: 110px; ">
					<option>-请选择状态-</option>
					<option>审批中</option>
					<option>已借出</option>
					<option>已归还</option>
				    <option>已拒绝</option>
				</select>
			</span>
			<div style="margin-top: 5px; float: left; margin-bottom: 7px;">
				<span>
					借出日期：<input id="lendDateText" class="easyui-datebox" editable="false" style="width: 130px;">
				</span>
				<span style="margin-left: 50px;">
					计划归还日期：<input id="returnDateText"   class="easyui-datebox" editable="false" style="width: 130px;">
				</span>
				<span style="margin-left: 70px;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" style="width: 75px;"
					   onclick="loadLendOutList();">筛选</a>
				</span>
				<span style=" padding-left: 30px;">
					<a href="javascript:void(0)" onclick="clearLendSearch();" style="text-decoration: none;">清空条件</a>
				</span>
			</div>
		</div>
		<div id="lendOutList" class="eqm_list_eqmList" ></div>
	</div>
</div>
</body>
</html>