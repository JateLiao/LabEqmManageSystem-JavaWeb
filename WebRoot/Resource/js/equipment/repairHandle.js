/**
 * 报修
 */
/*加载DataGrid*/
$(function() {
	loadRepairList();
});

function loadRepairList(){
	$('#repairList').datagrid({
		title : '我的设备->报修记录',
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
			'adminId' : parent.adminId,
			'eqmName' : $('#eqmNameText').val(),
			'propertyNo' : $('#propertyNoText').val(),
			'applyDate' : $('#repairDateText').datetimebox('getValue')
		},
		frozenColumns:[[
		                {field: 'ck' ,checkbox: true },
		             ]],
		columns : [ [
		             {field : 'keyID', hidden: true},
		             {field : 'eqmName',title : '设备名',width : '15%',align : 'center', sortable:true}, 
		             {field : 'propertyNo',title : '设备资产号',width : '15%',align : 'center', sortable:true},
		             {field : 'applyId', hidden:true},
		             {field : 'applyName',title : '报修人',width : '10%',align : 'center', sortable:true},
		             {field : 'applyLevel',title : '报修人角色',width : '10%',align : 'center', sortable:true},
		             {field : 'applyDate',title : '报修日期',width : '10%',align : 'center', sortable:true},
		             {field : 'adminId', hidden:true},
		             {field : 'adminName',title : '设备管理人',width : '10%',align : 'center', sortable:true},
		             {field : 'handleStatus', hidden:true},
		             {field : 'handleStatusName',title : '处理状态', width : '10%', align : 'center' , sortable:true, 
		            	 formatter:function(value,row,index){
		            		 if (value == '未处理') {
		            			 return '<font color="red">' + value + '</font>';
		            		 } else if (value == '已处理') {
		            			 return '<font color="green">' + value + '</font>';
		            		 } else {
		            			 return "";
		            		 }
		            	 }
		             },
		             {field : 'repairReason', title : '报修说明', width : '20%', align : 'center' },
		             {field : 'handleReason', title : '处理备注', width : '20%', align : 'center'}
		           ] ] ,
		 toolbar:[{
				id:'repairHandleBtn',
				text : '报修处理',
				iconCls : 'icon-layers',
				handler: function(){
					var row = $('#repairList').datagrid('getSelections');
					repairHandleWindow(row);
				}
			
		 }]
	});
} 

function repairHandleWindow(row){
	if (row.length < 1) {
		parentLayerAlert('请选择要处理的记录！', '提示', 0, 'layui-layer-molv');
		return;
	} else if (row.length > 1) {
		parentLayerAlert('每次操作只能处理一条记录！', '提示', 0, 'layui-layer-molv');
		return;
	} else if (row[0].handleStatus != '0') {
		parentLayerAlert('只有 <font color="red">未处理</font> 的申请记录才可处理，请核对已选中的记录！', '提示', 0, 'layui-layer-molv');
		return;
	}
	
	$('#repairHandleDiv').window({
		title: '报修处理',
		width : 500,
		height : 230,
		modal : true,
		draggable : true,
		onBeforeOpen:function(){ //窗体打开之前触发
			$("#repairhandle").css('display','block');
		},
		onBeforeDestroy:function(){
			$("#repairhandle").css('display','none'); 
		}
	});

	$('#okBtn').bind('click', function(){
		repairHandle(row);
    });
    $('#cancelBtn').bind('click', function(){
    	$('#repairHandleDiv').window('close');
    	$('#handleResult').combobox('setValue', '-请选择-'); 
    	$('#handleReason').textbox('setValue', ''); 
    });
}

function repairHandle(row){
	var reason = $('#handleReason').textbox('getValue');
	if (isNullOrEmpty(getRepairResult())) {
		parentLayerAlert('请选择处理结果！', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (isNullOrEmpty(reason)) {
		parentLayerAlert('处理说明未填写！', '提示', 0, 'layui-layer-molv');
		return;
	}
	$.ajax({
		type: 'POST',
		url: basePath + "/eqmOperate/repairHandle.htm?keyId=" + row[0].keyID + 
								"&handleReason=" + reason + 
								"&propertyNo=" + row[0].propertyNo + 
								"&repairResult=" + getRepairResult(),
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success: function(data){
			if(data == 'success'){
				$('#repairList').datagrid('reload');
		    	$('#repairHandleDiv').window('close');
				parentLayerAlert('<font color="red" size="2">处理成功！</font>', '提示', 1, 'layui-layer-molv');
		    	$('#handleResult').combobox('setValue', '-请选择-'); 
		    	$('#handleReason').textbox('setValue', ''); 
				return;
			} else { //操作失败 
				parentLayerAlert('<font color="red" size="2">处理失败！</font>', '提示', 5, 'layui-layer-molv');
				return;
			}
		},
		error: function(){
			parentLayerAlert('<font color="red" size="2">处理失败！</font>', '提示', 5, 'layui-layer-molv');
		},
		failure: function(){
			parentLayerAlert('<font color="red" size="2">处理失败！</font>', '提示', 5, 'layui-layer-molv');
		}
	});
}

function clearRepairSearch(){
	$('#eqmNameText').textbox("setValue", "");
	$('#propertyNoText').textbox("setValue", "");
	$('#repairDateText').datebox("setValue", "");
} 

function getRepairResult(){
	var value = $('#handleResult').combobox('getValue');
	if (value == '-请选择-') {
		return "";
	} else if (value == '维修成功') {
		return "1";
	} else {
		return "2";
	}
}