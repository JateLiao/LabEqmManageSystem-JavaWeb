<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath = request.getContextPath();
%>
<script type="text/javascript">
	var basePath = '<%=basePath%>';
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备借出申请</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/bootstrap/datagrid.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/color.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Resource/css/equipment/equipmentList.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Resource/frameworks/layer2.1/skin/layer.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Resource/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Resource/frameworks/layer2.1/layer.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Resource/js/Utils.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Resource/js/equipment/eqmLendOut.js"></script>
</head>
<body>
	<div class="eqm_list_lend_main">
		<div class="eqm_list_center_myLend">
			<!-- 搜索设备 -->
			<div id="searchDiv" class="eqm_list_search_lend_div">
				<span>设备名：<input id="eqmNameText" class="easyui-textbox"
					style="width: 125px;"></span> <span style="margin-left: 30px;">
					托管人：<input id="managerText" name="managerText"
					class="easyui-textbox" style="width: 125px;"> <input
					id="managerIdText" type="hidden">
				</span> <span style="margin-left: 30px;"> 设备状态： <select
					id="eqmStatus_search" class="easyui-combobox" editable="false"
					style="width: 110px;">
						<option>-请选择状态-</option>
						<option>使用中</option>
						<option>库存中</option>
						<option>维修中</option>
						<option>报废</option>
						<option>其他</option>
				</select>
				</span> <span style="margin-left: 40px;"> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" style="width: 75px;"
					onclick="loadEquipmentList();">筛选</a>
				</span> <span style="padding-left: 60px;"> <a
					href="javascript:void(0)" onclick="clearSearchBox();"
					style="text-decoration: none;">清空条件</a>
				</span>
			</div>
			<div id="eqmList" class="eqm_list_eqmList"></div>
		</div>
	</div>
	<!-- 借出设备 -->
	<div id="lendOutDiv">
		<div id="lendOut" style="width: 100%; display: none;">
			<div class="eqm_list_add_eqm_line" style="font-size: 13px;">
				<span>设备名称：</span> <input id="eqmNameLend" disabled="disabled"
					style="width: 150px; color: red; font-size: 15px; font-weight: 600; border: none; background: white;">
				<span style="margin-left: 10px;">设备资产号：</span> <input
					id="propertyNoLend" disabled="disabled"
					style="width: 130px; color: red; font-size: 15px; font-weight: 600; border: none; background: white;">
			</div>
			<div class="eqm_list_add_eqm_line">
				<span>借出日期：</span> <input id="dateLend" class="easyui-datebox"
					editable="false" style="width: 130px; float: left;"> <span
					style="margin-left: 30px;">预计归还日期：</span> <input
					id="planReturnDateLend" class="easyui-datebox" editable="false"
					style="width: 130px;">
			</div>
			<div class="eqm_list_add_eqm_line">
				<span>借出用途：</span> <input id="applicationLend"
					class="easyui-textbox" data-options="multiline:true"
					style="width: 380px; height: 80px;">
			</div>
			<div style="margin-top: 30px;">
				<a href="javascript:void(0)" id="cancelBtn_Lend"
					class="easyui-linkbutton" iconCls="icon-cancel"
					style="width: 95px; margin-right: 30px; float: right;">取消</a> <a
					href="javascript:void(0)" id="okBtn_Lend" class="easyui-linkbutton"
					iconCls="icon-ok"
					style="width: 95px; margin-right: 30px; float: right;">借出</a>
			</div>
		</div>
	</div>
	<!-- 报修设备 -->
	<div id="repairApplyDiv">
		<div id="repairApply" style="width: 100%; display: none;">
			<div class="eqm_list_add_eqm_line" style="font-size: 13px;">
				<span>设备名称：</span> <input id="eqmNameRepair" disabled="disabled"
					style="width: 150px; color: red; font-size: 15px; font-weight: 600; border: none; background: white;">
				<span style="margin-left: 10px;">设备资产号：</span> 
					<input id="propertyNoRepair" disabled="disabled"
					style="width: 130px; color: red; font-size: 15px; font-weight: 600; border: none; background: white;">
			</div>
			<div class="eqm_list_add_eqm_line">
				<span>报修理由：</span> <input id="repairReason"
					class="easyui-textbox" data-options="multiline:true"
					style="width: 380px; height: 80px;">
			</div>
			<div style="margin-top: 20px;">
				<a href="javascript:void(0)" id="cancelBtn_Repair"
					class="easyui-linkbutton" iconCls="icon-cancel"
					style="width: 95px; margin-right: 30px; float: right;">取消</a> <a
					href="javascript:void(0)" id="okBtn_Repair" class="easyui-linkbutton"
					iconCls="icon-ok"
					style="width: 95px; margin-right: 30px; float: right;">报修</a>
			</div>
		</div>
	</div>

	<!-- 报废设备 -->
	<div id="scrapApplyDiv">
		<div id="scrapApply" style="width: 100%; display: none;">
			<div class="eqm_list_add_eqm_line" style="font-size: 13px;">
				<span>设备名称：</span> <input id="eqmNameScrap" disabled="disabled"
					style="width: 150px; color: red; font-size: 15px; font-weight: 600; border: none; background: white;">
				<span style="margin-left: 10px;">设备资产号：</span> 
					<input id="propertyNoScrap" disabled="disabled"
					style="width: 130px; color: red; font-size: 15px; font-weight: 600; border: none; background: white;">
			</div>
			<div class="eqm_list_add_eqm_line">
				<span>报废理由：</span> <input id="scrapReason"
					class="easyui-textbox" data-options="multiline:true"
					style="width: 380px; height: 80px;">
			</div>
			<div style="margin-top: 20px;">
				<a href="javascript:void(0)" id="cancelBtn_Scrap"
					class="easyui-linkbutton" iconCls="icon-cancel"
					style="width: 95px; margin-right: 30px; float: right;">取消</a> <a
					href="javascript:void(0)" id="okBtn_Scrap" class="easyui-linkbutton"
					iconCls="icon-ok"
					style="width: 95px; margin-right: 30px; float: right;">报废</a>
			</div>
		</div>
	</div>
</body>
</html>