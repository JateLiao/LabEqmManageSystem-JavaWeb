var isModify = false;  //修改或者添加的标识，默认为添加
var eqmWin = null; // 设备列表窗口
var managerId = '1'; // getUrlPram('managerId');
var managerName = '罗斯'; //getUrlPram('managerName');

/*加载DataGrid*/
$(function() {
	loadEquipmentList();
});

function loadEquipmentList(){
	eqmWin = $('#eqmList').datagrid({
		title : '设备列表',
		iconCls : 'icon-large-smartart',
		width : '100%',
		height: 410,
		rownumbers: true, //显示序号与否
		pageSize : 10,//默认选择的分页是每页5行数据  
		pageList : [ 10, 20, 30 ],//可以选择的分页集合  
		beforePageText : '第',//页数文本框前显示的汉字 
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		striped : true,//设置为true将交替显示行背景。  
		collapsible : true,//显示可折叠按钮  
		url : basePath + "/equipments/page.htm",
		loadMsg : '加载中......',
		fitColumns : true,//允许表格自动缩放，以适应父容器  
		pagination : true,//分页  
		rownumbers : false,//行数
		draggable : true,
		remoteSort : true,
		queryParams : {
			'eqmName' : $('#eqmNameText').val(),
			'manager' : $('#managerText').val(),
			'eqmStatus' : getSearchEqmStatus(),
		},
		frozenColumns:[[
		                {field: 'ck' ,checkbox: true },
		             ]],
		columns : [ [
		             {field : 'keyID', hidden: true},
		             {field : 'propertyNo',title : '设备资产号',width : '18%',align : 'center', sortable:true},
		             {field : 'eqmName',title : '设备名',width : '15%',align : 'center', sortable:true}, 
		             {field : 'eqmStatus',title : '设备状态', width : '10%', align : 'center' , sortable:true, formatter:function(value,row,index){
		            	 if (value == '已借出') {
		            		 return '<font color="red">' + value + '</font>';
		            	 } else {
							return value;
		            	 }
		             }},
		             {field : 'eqmClass', title : '设备类别', width : '15%', align : 'center', sortable:true},
		             {field : 'manager',title : '托管人', width : '10%', align : 'center' , sortable:true},
		             {field : 'managerId', hidden: true},
		             {field : 'eqmPrice',title : '采购价格',width : '10%',align : 'center', sortable:true}, 
		             {field : 'buyTime',title : '采购时间', width : '10%', align : 'center' , sortable:true},
		             {field : 'buyStaff',title : '采购人', width : '10%', align : 'center' },
		             {field : 'college', title : '所属学院', width : '15%', align : 'center', sortable:true},
		             {field : 'eqmLab', title : '设备所在实验室', width : '15%', align : 'center', sortable:true},
		             {field : 'eqmFactory',title : '生产厂商',width : '10%',align : 'center', sortable:true}, 
		             {field : 'eqmType',title : '设备型号',width : '10%',align : 'center', sortable:true}, 
		             {field : 'description', title : '备注', width : '15%', align : 'center', sortable:true}
		           ] ],
		toolbar : [{
			id:'lendBtn',
			text : '设备借用',
			iconCls : 'icon-add',
			handler : function() {
				var row = $('#eqmList').datagrid('getSelections');
				lendOutApplyWindow(row);
			}
		}, '-',{
			id:'repairBtn',
			text : '设备报修',
			iconCls : 'icon-edit',
			handler: function(){
				var row = $('#eqmList').datagrid('getSelections');
				repairApplyWindow(row);
			}
		}, '-',{
			id:'repairBtn',
			text : '设备报废',
			iconCls : 'icon-scrap',
			handler: function(){
				var row = $('#eqmList').datagrid('getSelections');
				scrapApplyWindow(row);
			}
		}]
	});
}

/*借出申请*/
function lendOutApplyWindow(row){
	if (row == null || row.length < 1) {
		parentLayerAlert('<font color="red" size="2">至少选择一行!</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row.length > 1) {
		parentLayerAlert('<font color="red" size="2">每次只能借出一个设备！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row[0].keyID == "" || row[0].keyID == null) {
		parentLayerAlert('<font color="red" size="2">无数据！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row[0].eqmStatus == '已借出') {
		parentLayerAlert('<font color="red" size="2">该设备已被借出！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row[0].adminId == parent.adminId) {
		parentLayerAlert('<font color="red" size="2">无需向自己托管的设备提交借出申请！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	
	$('#lendOutDiv').window({
		title: '设备借出',
		width : 500,
		height : 285,
		modal : true,
		draggable : true,
		onBeforeOpen:function(){ //窗体打开之前触发
			$("#lendOut").css('display','block');
		},
		onBeforeDestroy:function(){
			$("#lendOut").css('display','none'); 
		}
	});

	$('#eqmNameLend').val(row[0].eqmName);
	$('#propertyNoLend').val(row[0].propertyNo);
	$('#okBtn_Lend').bind('click', function(){
    	lendOutApply(row);
    });
    $('#cancelBtn_Lend').bind('click', function(){
    	$('#lendOutDiv').window('close');
    });
}
/*借出申请      */
function lendOutApply(row){
	if (inputCheck('lend')) {
		$.ajax({
			type: 'POST',
			url: basePath + '/eqmOperate/lendoutApply.htm?param=' + getParam(row, 'lend'),
			success: function(data){
				if (data == 'success') {
					$('#lendOutDiv').window('close');
					$('#eqmList').datagrid('reload');
					clearApply();
					parentLayerAlert('<font color="red" size="2">申请成功，请等待管理员审批！</font>', '提示', 1, 'layui-layer-molv');
				} else {
					parentLayerAlert('<font color="red" size="2">申请失败，请重试！</font>', '提示', 2, 'layui-layer-molv');
					return;
				}
			},
			error: function(){
				parentLayerAlert('<font color="red" size="2">申请失败，请重试！</font>', '提示', 2, 'layui-layer-molv');
			},
			failure: function(){
				parentLayerAlert('<font color="red" size="2">申请失败，请重试！</font>', '提示', 2, 'layui-layer-molv');
			}
		});
	}
	 
}

/*报修申请*/
function repairApplyWindow(row){
	if (row == null || row.length < 1) {
		parentLayerAlert('<font color="red" size="2">至少选择一行!</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row.length > 1) {
		parentLayerAlert('<font color="red" size="2">每次只能申请报修一个设备！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row[0].keyID == "" || row[0].keyID == null) {
		parentLayerAlert('<font color="red" size="2">无数据！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row[0].eqmStatus == '已报修') {
		parentLayerAlert('<font color="red" size="2">该设备已被报修！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row[0].adminId == parent.adminId) {
		parentLayerAlert('<font color="red" size="2">无需向自己托管的设备提交报修申请！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	
	$('#repairApplyDiv').window({
		title: '设备报修',
		width : 500,
		height : 230,
		modal : true,
		draggable : true,
		onBeforeOpen:function(){ //窗体打开之前触发
			$("#repairApply").css('display','block');
		},
		onBeforeDestroy:function(){
			$("#repairApply").css('display','none'); 
		}
	});

	$('#eqmNameRepair').val(row[0].eqmName);
	$('#propertyNoRepair').val(row[0].propertyNo);
	$('#okBtn_Repair').bind('click', function(){
    	repairApply(row);
    });
    $('#cancelBtn_Repair').bind('click', function(){
    	$('#repairApplyDiv').window('close');
    });
}
/*设备报修*/
function repairApply(row){
	if (inputCheck('repair')) {
		$.ajax({
			type: 'POST',
			url: basePath + '/eqmOperate/repairApply.htm?param=' + getParam(row, 'repair'),
			success: function(data){
				if (data == 'success') {
					$('#repairApplyDiv').window('close');
					$('#eqmList').datagrid('reload');
					clearApply();
					parentLayerAlert('<font color="red" size="2">报修申请成功，管理员会对接接下来的工作！</font>', '提示', 1, 'layui-layer-molv');
				} else {
					parentLayerAlert('<font color="red" size="2">申请失败，请重试！</font>', '提示', 2, 'layui-layer-molv');
					return;
				}
			},
			error: function(){
				parentLayerAlert('<font color="red" size="2">申请失败，请重试！</font>', '提示', 2, 'layui-layer-molv');
			},
			failure: function(){
				parentLayerAlert('<font color="red" size="2">申请失败，请重试！</font>', '提示', 2, 'layui-layer-molv');
			}
		});
	}
	 
}

/*报废申请*/
function scrapApplyWindow(row){
	if (row == null || row.length < 1) {
		parentLayerAlert('<font color="red" size="2">至少选择一行!</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row.length > 1) {
		parentLayerAlert('<font color="red" size="2">每次只能申请报废一个设备！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row[0].keyID == "" || row[0].keyID == null) {
		parentLayerAlert('<font color="red" size="2">无数据！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row[0].eqmStatus == '已报废') {
		parentLayerAlert('<font color="red" size="2">该设备已被报废！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row[0].adminId == parent.adminId) {
		parentLayerAlert('<font color="red" size="2">无需向自己托管的设备提交报修申请！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	
	$('#scrapApplyDiv').window({
		title: '设备报废',
		width : 500,
		height : 230,
		modal : true,
		draggable : true,
		onBeforeOpen:function(){ //窗体打开之前触发
			$("#scrapApply").css('display','block');
		},
		onBeforeDestroy:function(){
			$("#scrapApply").css('display','none'); 
		}
	});

	$('#eqmNameScrap').val(row[0].eqmName);
	$('#propertyNoScrap').val(row[0].propertyNo);
	$('#okBtn_Scrap').bind('click', function(){
    	scrapApply(row);
    });
    $('#cancelBtn_Scrap').bind('click', function(){
    	$('#scrapApplyDiv').window('close');
    });
}
/*设备报废*/
function scrapApply(row){
	if (inputCheck('scrap')) {
		$.ajax({
			type: 'POST',
			url: basePath + '/eqmOperate/scrapApply.htm?param=' + getParam(row, 'scrap'),
			success: function(data){
				if (data == 'success') {
					$('#scrapApplyDiv').window('close');
					$('#eqmList').datagrid('reload');
					clearApply();
					parentLayerAlert('<font color="red" size="2">报废申请成功，管理员会对接接下来的工作！</font>', '提示', 1, 'layui-layer-molv');
				} else {
					parentLayerAlert('<font color="red" size="2">申请失败，请重试！</font>', '提示', 2, 'layui-layer-molv');
					return;
				}
			},
			error: function(){
				parentLayerAlert('<font color="red" size="2">申请失败，请重试！</font>', '提示', 2, 'layui-layer-molv');
			},
			failure: function(){
				parentLayerAlert('<font color="red" size="2">申请失败，请重试！</font>', '提示', 2, 'layui-layer-molv');
			}
		});
	}
	 
}


/*拼接post到后台的参数,json格式*/
function getParam(row, opt){
	var param = "";
	
	if (opt == 'lend') {
	 	param += "{\"lenderId\":\"" + parent.adminId + 
		"\",\"lenderName\":\"" + parent.adminName + 
		"\",\"eqmName\":\"" + $('#eqmNameLend').val() + 
		"\",\"propertyNo\":\"" + $('#propertyNoLend').val() + 
		"\",\"lendDate\":\"" + $('#dateLend').datetimebox('getValue') + " 00:00:00" + 
		"\",\"planReturnDate\":\"" + $('#planReturnDateLend').datetimebox('getValue') + " 00:00:00" + 
		"\",\"application\":\"" + $('#applicationLend').textbox('getValue') + 
		"\",\"lenderLevel\":\"" + parent.adminLevel +
		"\",\"adminId\":\"" + row[0].managerId +
		"\",\"adminName\":\"" + row[0].manager
	} else if (opt == 'repair') {
		param += "{\"applyId\":\"" + parent.adminId + 
		"\",\"eqmName\":\"" + $('#eqmNameRepair').val() + 
		"\",\"propertyNo\":\"" + $('#propertyNoRepair').val() +
		"\",\"adminId\":\"" + row[0].managerId +
		"\",\"adminName\":\"" + row[0].manager + 
		"\",\"applyName\":\"" + parent.adminName + 
		"\",\"applyLevel\":\"" + parent.adminLevel + 
		"\",\"repairReason\":\"" + $('#repairReason').textbox('getValue');
	} else if (opt == 'scrap') {
		param += "{\"applyId\":\"" + parent.adminId + 
		"\",\"eqmName\":\"" + $('#eqmNameRepair').val() + 
		"\",\"propertyNo\":\"" + $('#propertyNoRepair').val() +
		"\",\"adminId\":\"" + row[0].managerId +
		"\",\"adminName\":\"" + row[0].manager +
		"\",\"applyName\":\"" + parent.adminName + 
		"\",\"applyLevel\":\"" + parent.adminLevel + 
		"\",\"scrapReason\":\"" + $('#scrapReason').textbox('getValue');
	}
	
	param += "\",\"handleStatus\":\"0\",\"handleStatusName\":\"未处理\"}";
	return param;
} 

function inputCheck(value){
	if (value == 'lend') {
		if(isNullOrEmpty($('#dateLend').datebox('getValue')) || isNullOrEmpty($('#planReturnDateLend').datebox('getValue'))
				|| isNullOrEmpty($('#applicationLend').textbox('getValue'))){
			parentLayerAlert('<font color="red" size="2">请完成所有填写！</font>', '提示', 0, 'layui-layer-molv');
			return false;
		}
	} else if (value == 'repair') {
		if(isNullOrEmpty($('#repairReason').textbox('getValue'))){
			parentLayerAlert('<font color="red" size="2">请完成所有填写！</font>', '提示', 0, 'layui-layer-molv');
			return false;
		}
	} else if (value == 'scrap') {
		if(isNullOrEmpty($('#scrapReason').textbox('getValue'))){
			parentLayerAlert('<font color="red" size="2">请完成所有填写！</font>', '提示', 0, 'layui-layer-molv');
			return false;
		}
	}
	
	
	return true;
}

function clearSearchBox(){
	$('#eqmNameText').textbox("setValue", "");
	$('#managerText').textbox("setValue", "");
	$('#eqmStatus_search').combobox('setValue', '-请选择状态-')
}

function clearApply(){
	$('#dateLend').textbox('setValue','');
	$('#planReturnDateLend').textbox('setValue','');
	$('#applicationLend').textbox('setValue','')
	
	$('#repairReason').textbox('setValue','')
	$('#scrapReason').textbox('setValue','')
}

/*获取搜索设备状态*/
function getSearchEqmStatus(){
	if($('#eqmStatus_search').combobox('getValue') == '-请选择状态-'){
		return "";
	} 
	return $('#eqmStatus_search').combobox('getValue');
}