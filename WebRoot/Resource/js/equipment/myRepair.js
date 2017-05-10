/**
 * 报修
 */
/*加载DataGrid*/
$(function() {
	loadRepairList();
});

function loadRepairList(){
	$('#repairList').datagrid({
		title : '我的设备报修记录',
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
		url : basePath + "/eqmOperate/repairPage.htm",
		emptyMsg: '暂无数据！',
		loadMsg : '加载中......',
		fitColumns : true,//允许表格自动缩放，以适应父容器  
		pagination : true,//分页  
		rownumbers : false,//行数
		draggable : true,
		remoteSort : true,
		queryParams : { 
			'applyId' : parent.adminId,
			'eqmName' : $('#eqmNameText').val(),
			'propertyNo' : $('#propertyNoText').val(),
			'applyDate' : $('#repairDateText').datetimebox('getValue')
		},
		columns : [ [
		             {field : 'keyID', hidden: true},
		             {field : 'eqmName',title : '设备名',width : '15%',align : 'center', sortable:true}, 
		             {field : 'propertyNo',title : '设备资产号',width : '15%',align : 'center', sortable:true},
		             {field : 'applyId', hidden:true},
		             /*{field : 'applyName',title : '报修人',width : '10%',align : 'center', sortable:true},
		             {field : 'applyLevel',title : '报修人角色',width : '10%',align : 'center', sortable:true},*/
		             {field : 'applyDate',title : '报修日期',width : '10%',align : 'center', sortable:true},
		             {field : 'adminId', hidden:true},
		             {field : 'adminName',title : '设备管理人',width : '10%',align : 'center', sortable:true},
		             {field : 'handleStatus', hidden:true},
		             {field : 'handleStatusName',title : '处理状态', width : '10%', align : 'center' , sortable:true, formatter:function(value,row,index){
		            	 if (value == '未处理') {
	            			 return '<font color="red">' + value + '</font>';
	            		 } else if (value == '已处理') {
	            			 return '<font color="green">' + value + '</font>';
	            		 } else {
	            			 return "";
	            		 }
		             }},
		             {field : 'repairReason', title : '报修说明', width : '20%', align : 'center' },
		             {field : 'handleReason', title : '处理备注', width : '20%', align : 'center'}
		           ] ] 
	});
} 
function clearRepairSearch(){
	$('#eqmNameText').textbox("setValue", "");
	$('#propertyNoText').textbox("setValue", "");
	$('#repairDateText').datebox("setValue", "");
} 