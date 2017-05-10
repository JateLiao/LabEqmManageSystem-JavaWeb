/**
 * 借入借出
 */
/*加载DataGrid*/
$(function() {
	loadLendOutList();
});

function loadLendOutList(){
	$('#lendOutList').datagrid({
		title : '我的借出设备记录',
		iconCls : 'icon-large-smartart',
		width : '100%',
		height: 385,
		rownumbers: true, //显示序号与否
		pageSize : 10,//默认选择的分页是每页5行数据  
		pageList : [ 10, 20, 30 ],//可以选择的分页集合  
		beforePageText : '第',//页数文本框前显示的汉字 
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		striped : true,//设置为true将交替显示行背景。  
		collapsible : true,//显示可折叠按钮 
		url : basePath + "/eqmOperate/lendOutPage.htm",
		emptyMsg: '暂无数据！',
		loadMsg : '加载中......',
		fitColumns : true,//允许表格自动缩放，以适应父容器  
		pagination : true,//分页  
		rownumbers : false,//行数
		draggable : true,
		remoteSort : true,
		queryParams : { 
			'lenderId' : parent.adminId,
			'eqmName' : $('#eqmNameText').val(),
			'propertyNo' : $('#propertyNoText').val(),
			'handleStatus' :getStatusText(),
			'lendDate' : $('#lendDateText').datetimebox('getValue'),
			'planReturnDate' : $('#returnDateText').datetimebox('getValue')
		},
		frozenColumns:[[
		                {field: 'ck' ,checkbox: true },
		             ]],
		columns : [ [
		             {field : 'keyID', hidden: true},
		             {field : 'adminId', hidden:true},
		             {field : 'propertyNo',title : '设备资产号',width : '15%',align : 'center', sortable:true},
		             {field : 'eqmName',title : '设备名',width : '15%',align : 'center', sortable:true}, 
		             {field : 'handleStatus', hidden:true}, 
		             {field : 'handleStatusName',title : '处理状态', width : '10%', align : 'center' , sortable:true, formatter:function(value,row,index){
		            	 if (value == '未处理') {
		            		 return '<font color="red">' + value + '</font>';
		            	 } else if (value == '已借出'){
		            		 return '<font color="green">' + value + '</font>';
		            	 } else if (value == '已拒绝') {
		            		 return '<font color="#0000f2">' + value + '</font>';
		            	 } else {
							return "";
						}
		             }},
		             {field : 'adminName',title : '审核管理员', width : '12%', align : 'center' , sortable:true},
		             {field : 'lendDate',title : '借出时间',width : '11%',align : 'center', sortable:true}, 
		             {field : 'planReturnDate',title : '计划规划时间', width : '11%', align : 'center' , sortable:true},
		             {field : 'actualReturnDate',title : '实际归还时间', width : '11%', align : 'center' },
		             {field : 'handleReason', title : '处理备注', width : '20%', align : 'center'},
		             {field : 'application', title : '借出用途', width : '20%', align : 'center' }
		           ] ] ,
		toolbar : [{
			id:'returnBtn',
			text : '设备归还',
			iconCls : 'icon-undo',
			handler: function(){
				returnEqm();
			}
		}] 
	});
}

function getStatusText(){
	var value = $('#handleStatusText').combobox('getValue');
	switch (value) {
		case '-请选择状态-': return ''; break;
		case '审批中': return '0'; break;
		case '已借出': return '1'; break;
		case '已归还': return '2'; break;
		case '已拒绝': return '3'; break;
		default: break;
	}
}
function clearLendSearch(){
	$('#eqmNameText').textbox("setValue", "");
	$('#propertyNoText').textbox("setValue", "");
	$('#lendDateText').datebox("setValue", "");
	$('#returnDateText').datebox("setValue", "");
	$('#handleStatusText').combobox('setValue', '-请选择状态-')
}

/*归还设备*/
function returnEqm(){
	var row = $('#lendOutList').datagrid('getSelections');
	if (row.length < 1) {
		$('#lendOutList').datagrid('reload');
		parentLayerAlert('请选择要归还的设备！', '提示', 0, 'layui-layer-molv');
		return;
	}
	var pNoParam = "";
	var idParam = "";
	for (var int = 0; int < row.length; int++) {
		if (row[int].handleStatus != '1') {
			parentLayerAlert('只有 <font color="red">已借出</font> 的设备才可归还，请核对已选中的记录！', '提示', 0, 'layui-layer-molv');
			return; 
		}
		pNoParam += row[int].propertyNo + ",";
		idParam += row[int].keyID + ",";
	}
	
	parent.layer.open({
		title: '提示',
		skin: 'layui-layer-molv', //样式类名
		icon: 3,
	    content: '您确定要归还选中的 </font> <font color="red" size="4">'+ row.length +'</font> 个设备？',
	    btn: ['确认', '取消'],
	    yes: function(){
	    	$.ajax({
	    		type: 'POST',
	    		url: basePath + "/eqmOperate/returnEqm.htm?pNoParam=" + pNoParam + "&idParam=" + idParam,
	    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    		success: function(data){
	    			if(data == 'success'){
	    				$('#lendOutList').datagrid('reload');
	    				parentLayerAlert('<font color="red" size="2">归还成功！</font>', '提示', 6, 'layui-layer-molv');
	    				return;
	    			} else { //操作失败 
	    				parentLayerAlert('<font color="red" size="2">归还失败！</font>', '提示', 5, 'layui-layer-molv');
	    				return;
	    			}
	    		},
	    		error: function(){
	    			parentLayerAlert('<font color="red" size="2">归还失败！</font>', '提示', 5, 'layui-layer-molv');
	    		},
	    		failure: function(){
	    			parentLayerAlert('<font color="red" size="2">归还失败！</font>', '提示', 5, 'layui-layer-molv');
	    		}
	    	});
	    },
	    cancle: function (index){ layer.close(index);}
	});  
} 