/**
 * 管理员列表
 */  

$(function() {
	clearSearchBox();
	loadAdminList();
});

function loadAdminList(){
	$('#registList').datagrid({
		title : '已注册待审批管理员列表',
		iconCls : 'icon-large-smartart',
		width : '100%',
		height: 390,
		rownumbers: true, //显示序号与否
		pageSize : 10,//默认选择的分页是每页5行数据  
		pageList : [ 10, 20, 30],//可以选择的分页集合  
		beforePageText : '第',//页数文本框前显示的汉字 
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		striped : true,//设置为true将交替显示行背景。  
		collapsible : true,//显示可折叠按钮  
		url : basePath + "/admin/registList.htm",
		emptyMsg: '暂无数据！',
		loadMsg : '加载中......',
		fitColumns : true,//允许表格自动缩放，以适应父容器  
		pagination : true,//分页  
		rownumbers : false,//行数
		draggable : true,
		remoteSort : true,
		queryParams : {
			'adminName' : $('#adminNameText').val(),
			'adminID' : $('#adminIdText').val()
		}, 
		frozenColumns:[[
		                {field: 'ck' ,checkbox: true },
		             ]],
		columns : [ [
		             {field : 'adminID',title : '管理员工号',width : '13%',align : 'center', sortable:true},
		             {field : 'adminName',title : '管理员姓名',width : '10%',align : 'center', sortable:true}, 
		             {field : 'adminLevel', title : '注册角色', width : '15%', align : 'center', sortable:true, formatter:function(value,row,index){
		            	 switch (value) { 
		            	 	case 0: case "0": return "<font color='red'>学生</font>"; 
		            	 	case 1: case "1": return "<font color='red'>普通教职工</font>"; 
		            	 	case 2: case "2": return "<font color='red'>实验室管理员</font>";
		            	 	case 3: case "3": return "<font color='red'>设备管理员</font>";
		            	 	case 99: case "99": return "<font color='red'>超级管理员</font>"; 
		            	 	default: break; 
		            	 }
		             }},
		             {field : 'adminCollege',title : '学院',width : '15%',align : 'center', sortable:true}, 
		             {field : 'adminIDCardNo',title : '身份证号',width : '20%',align : 'center', sortable:true}, 
		             {field : 'adminTel',title : '联系电话', width : '12%', align : 'center' , sortable:true},
		             {field : 'adminEmail',title : '邮箱', width : '20%', align : 'center' },
		             {field : 'description', title : '备注', width : '15%', align : 'center', sortable:true}
		           ] ],
		toolbar : [{
			id:'passBtn',
			text : '同意注册',
			iconCls : 'icon-edit',
			handler : function() {
				var row = $('#registList').datagrid('getSelections');
				registHandle(row, true);
			}
		},'-',{
			id:'refuseBtn',
			text : '拒绝注册',
			iconCls : 'icon-redo',
			handler : function() {
				var row = $('#registList').datagrid('getSelections');
				registHandle(row, false);
			}
		}]
	});
}

/*注册处理*/
function registHandle(row, isPass){
	if (row == null || row.length < 1) {
		parentLayerAlert('<font color="red" size="2">至少选择一个管理员!</font>', '提示', 0, 'layui-layer-molv');
		return;
	} 
	
	if (row[0].adminID == parent.adminId || row[0].adminLevel == '99') {
		parentLayerAlert('不能操作自己或者其他高级管理员的权限！', '提示', 0, 'layui-layer-molv');
		return;
	}  
	
	var ids = "";
	var flag = isPass ? "1" : "0";
	var showInfo = isPass ? "通过" : "拒绝";
	for (var int = 0; int < row.length; int++) {
		ids += row[int].adminID + ",";
	} 
	 
	parent.layer.open({
		title: '提示',
		skin: 'layui-layer-molv', //样式类名
		icon: 3,
	    content: '您确定 <font color="red">' + showInfo + '</font> 选中管理员的注册？',
	    btn: ['确定', '取消'],
	    yes: function(){ 
	    	$.ajax({
				type : "POST",
				url : basePath + "/admin/registHandle.htm?adminID=" + ids + "&flag=" + flag,
				success : function(data) {
					if (data == 'success') {
						parentLayerAlert(showInfo + '成功！', '提示', 1, 'layui-layer-molv');
					} else {
						parentLayerAlert(showInfo + '失败！', '提示', 2, 'layui-layer-molv');
					}
					$('#registList').datagrid('reload');
					return;
				},
				error : function() {
					parentLayerAlert(showInfo + '失败！', '提示', 2, 'layui-layer-molv');
				},
				failure : function() {
					parentLayerAlert(showInfo + '失败！', '提示', 2, 'layui-layer-molv');
				}
	    	});
	    },
	    cancel: function(index){ //或者使用btn2
	    	//var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);
	    	// parent.layer.closeAll(); 
	    }
	}); 

} 

/*清除筛选条件*/
function clearSearchBox() {
	$('#adminNameText').textbox("setValue", "");
	$('#adminIdText').textbox("setValue", ""); 
}

