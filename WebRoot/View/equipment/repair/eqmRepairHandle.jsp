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
<title>报修处理</title>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/js/equipment/repairHandle.js"></script>
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
		<div id="searchDiv" class="eqm_list_search_lend_div" >
			<span>设备名：<input id="eqmNameText" class="easyui-textbox" style="width: 125px;"></span>
			<span style="margin-left: 40px;">
				设备资产号：<input id="propertyNoText" name="managerText" class="easyui-textbox" style="width: 125px;">
			</span>
			<span style="margin-left: 40px;">
				报修日期：<input id="repairDateText"   class="easyui-datebox" editable="false" style="width: 130px;">
			</span>
			<span style="margin-left: 40px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" style="width: 75px;"
				   onclick="loadRepairList();">筛选</a>
			</span>
			<span style=" padding-left: 30px;">
				<a href="javascript:void(0)" onclick="clearRepairSearch();" style="text-decoration: none;">清空条件</a>
			</span>
		</div>
		<div id="repairList" class="eqm_list_eqmList" ></div>
	</div>
	
	<div id="repairHandleDiv">
		<div id="repairhandle" style="width: 100%; display: none;">
			<div class="eqm_list_add_eqm_line">
				<span>报修处理结果：</span>
				<select id="handleResult" class="easyui-combobox" data-options="editable:false,panelHeight:'135px'" style="width: 110px; ">
					<option>-请选择-</option>
					<option>维修成功</option>
					<option>维修失败</option>
				</select>
			</div>
			<div class="eqm_list_add_eqm_line">
				<span>处理说明：</span>
				<input id="handleReason" class="easyui-textbox" data-options="multiline:true" style="width: 380px; height: 80px;" >
			</div>
			<div style="margin-top: 20px;">
				<a href="javascript:void(0)" id="cancelBtn" class="easyui-linkbutton" iconCls="icon-cancel" 
					style="width: 95px; margin-right: 30px; float: right;">取消</a>
				<a href="javascript:void(0)" id="okBtn" class="easyui-linkbutton" iconCls="icon-ok" 
					style="width: 95px; margin-right: 30px; float: right;">确定</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>