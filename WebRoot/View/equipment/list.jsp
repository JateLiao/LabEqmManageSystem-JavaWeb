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
<title>设备列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/default/datagrid.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/color.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/css/equipment/equipmentList.css" /> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Resource/frameworks/layer2.1/skin/layer.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/frameworks/easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/frameworks/layer2.1/layer.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/js/Utils.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/Resource/js/equipment/equipment.js"></script>
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
					 <input id="managerIdText" type="hidden">
			</span> 
			<span style="margin-left: 30px;">
				设备状态：
				<select id="eqmStatus_search" class="easyui-combobox" editable="false"  style="width: 110px; ">
					<option>-请选择状态-</option>
					<option>使用中</option>
					<option>库存中</option>
					<option>维修中</option>
				    <option>报废</option>
					<option>其他</option>
				</select>
			</span> 
			<div style="margin-top: 8px; float: left;">
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
<!-- 添加/修改 -->
<div id="addOrModifyEquipmentWindow">
	<div id="add" style="display: none; margin-top: 10px;">
		<div class="eqm_list_add_eqm_line_propertyNo">
			<span>设备资产号：</span><input id="propertyNo"  class="easyui-textbox" style="width: 170px; height: ">
			<input id="eqmKeyID" type="hidden">
		</div>
		<div class="eqm_list_add_eqm_line">
			<span>设备名称：</span><input id="eqmName" class="easyui-textbox" required="true" style="width: 150px;">
			<span style="margin-left: 60px;">生产厂商：</span><input id="eqmFactory" class="easyui-textbox" style="width: 150px;">
		</div>
		<div class="eqm_list_add_eqm_line">
			<span>设备类别：</span><input id="eqmClass" class="easyui-textbox" style="width: 150px;">
			<span style="margin-left: 60px;">设备型号：</span><input id="eqmType" class="easyui-textbox" style="width: 150px;">
		</div>
		<div class="eqm_list_add_eqm_line">
			<span>托管人：</span><input id="manager" class="easyui-textbox" style="width: 100px;">
			<span style="margin-left: 25px;">托管人账号：</span><input id="managerId" class="easyui-textbox" style="width: 100px;">
			<span style="margin-left: 25px;">设备状态：</span>
				<select id="eqmStatus" class="easyui-combobox" data-options="editable:false,panelHeight:'170px'" style="width: 100px; ">
						<option>-请选择-</option>
						<option>使用中</option>
						<option>库存中</option>
						<option>维修中</option>
						<option>报废</option>
						<option>其他</option>
				</select>
		</div>
		<div class="eqm_list_add_eqm_line"> 
			<span>采购人：</span><input id="buyStaff" class="easyui-textbox" style="width: 100px;">
			<span style="margin-left: 25px;">购入价格：</span><input id="eqmPrice" class="easyui-textbox" style="width: 100px;">
			<span style="margin-left: 25px;">采购日期：</span><input id="buyTime"  class="easyui-datebox" editable="false" style="width: 110px;">
		</div>
		<div class="eqm_list_add_eqm_line">
			<span>设备所属学院：</span>
			<select id="college" class="easyui-combobox" data-options="editable:false,panelHeight:'180px'"  style="width: 140px;">
				<option selected="selected">-请选择学院-</option>
				<option>经济与管理学院</option>
				<option>计算机科学学院</option>
				<option>体育学院</option>
				<option>美术学院</option>
				<option>舞蹈学院</option>
				<option>服装与设计艺术学院</option>
				<option>物理与电子工程学院</option>
				<option>商学院</option>
				<option>新闻与传媒学院</option>
				<option>文学院</option>
				<option>外国语学院</option>
				<option>生命与科学学院</option>
				<option>工学院</option>
				<option>教育科学学院</option>
				<option>数学与软件学院</option>
				<option>生命与科学学院</option>
				<option>影视与传媒学院</option>
				<option>地理与资源学院</option>
				<option>基础教育学院</option>
				<option>职业技术学院</option>
				<option>应用技术学院</option>
				<option>国际教育学院</option>
				<option>政治教育学院</option>
				<option>教师教育学院</option>
				<option>MBA教育中心</option>
			</select>
			<span style="margin-left: 20px;">设备所在实验室：</span><input id="eqmLab" class="easyui-textbox" style="width: 188px;">
		</div>
		<div class="eqm_list_add_eqm_line">
			<span>备注：</span>
			<input id="description" class="easyui-textbox" data-options="multiline:true" style="width: 495px; height: 100px;" >
		</div>
		
		<div style="margin-top: 20px;">
			<a href="javascript:void(0)" id="cancelBtn" class="easyui-linkbutton" iconCls="icon-cancel" 
				style="width: 95px; margin-right: 30px; float: right;">取消</a>
			<a href="javascript:void(0)" id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload" 
				style="width: 95px; margin-right: 30px; display: none; float: right;">重置</a>
			<a href="javascript:void(0)" id="okBtn" class="easyui-linkbutton" iconCls="icon-ok" 
				style="width: 95px; margin-right: 30px; float: right;">添加</a>
		</div>
	</div>
</div> 
</body>
</html>