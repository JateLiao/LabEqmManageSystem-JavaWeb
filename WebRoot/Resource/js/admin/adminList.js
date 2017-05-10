/**
 * 管理员列表
 */  

$(function() {
	clearSearchBox();
	loadAdminList();
});

function loadAdminList(){
	$('#adminList').datagrid({
		title : '管理员列表',
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
		url : basePath + "/admin/page.htm",
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
		onLoadSuccess: function (data) {
            if (data.total == 0) {
                //添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                $(this).datagrid('appendRow', { itemid: '<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'itemid', colspan: 4 })
                //隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui datagrid没有提供相关方法隐藏导航条
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
        },
		frozenColumns:[[
		                {field: 'ck' ,checkbox: true },
		             ]],
		columns : [ [
		             {field : 'adminID',title : '管理员工号',width : '13%',align : 'center', sortable:true},
		             {field : 'adminName',title : '管理员姓名',width : '13%',align : 'center', sortable:true}, 
		             {field : 'adminLevel', title : '管理员角色', width : '15%', align : 'center', sortable:true, formatter:function(value,row,index){
		            	 switch (value) { 
		            	 	case 0: case "0": return "<font color='red'>学生</font>"; 
		            	 	case 1: case "1": return "<font color='red'>普通教职工</font>"; 
		            	 	case 2: case "2": return "<font color='red'>实验室管理员</font>";
		            	 	case 3: case "3": return "<font color='red'>设备管理员</font>";
		            	 	case 99: case "99": return "<font color='red'>超级管理员</font>"; 
		            	 	default: break; 
		            	 }
		             }},
		             {field : 'adminSex',title : '性别', width : '8%', align : 'center' , sortable:true},
		             {field : 'adminBirthday',title : '出生年月日',width : '10%',align : 'center', sortable:true},
		             {field : 'adminCollege',title : '学院',width : '15%',align : 'center', sortable:true}, 
		             {field : 'adminIDCardNo',title : '身份证号',width : '20%',align : 'center', sortable:true}, 
		             {field : 'adminTel',title : '联系电话', width : '12%', align : 'center' , sortable:true},
		             {field : 'adminEmail',title : '邮箱', width : '20%', align : 'center' },
		             {field : 'adminNativePlace', title : '籍贯', width : '10%', align : 'center', sortable:true}, 
		             {field : 'adminAddress', title : '家庭住址', width : '25%', align : 'center', sortable:true},
		             {field : 'description', title : '备注', width : '15%', align : 'center', sortable:true}
		           ] ],
		toolbar : [{
			id:'modiftBtn',
			text : '角色修改',
			iconCls : 'icon-edit',
			handler : function() {
				var row = $('#adminList').datagrid('getSelections');
				modifyPermissionWindow(row);
			}
		},'-',{
			id:'exportBtn',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				deleteAdmin();
			}
		}]
	});
} 

/*修改其他管理员权限窗口*/
function modifyPermissionWindow(row){
	if (row == null || row.length < 1) {
		parentLayerAlert('<font color="red" size="2">至少选择一个管理员!</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	if (row.length > 1) {
		parentLayerAlert('<font color="red" size="2">每次只能给一个管理员修改权限！</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	
	if (row[0].adminID == parent.adminId || row[0].adminLevel == '99') {
		parentLayerAlert('不能操作自己或者其他高级管理员的权限！', '提示', 0, 'layui-layer-molv');
		return;
	}
	
	initPermissionCurrent(row[0].adminLevel); // 初始化当前权限
	
	$('#cancelBtn').bind('click', function(){
    	$('#modifyPermissionWindow').window('close');
    }); 
	
	var modifyPermissionWindow = $('#modifyPermissionWindow').window({
		title: '&nbsp;管理员权限修改',
		width : 440,
		height : 140,
		modal : true,
		draggable : true,
		onBeforeOpen:function(){ //窗体打开之前触发
			$("#modify_permission").css('display','block'); 
		},
		onBeforeDestroy:function(){
			$("#modify_permission").css('display','none'); 
		}
	});
}

/*修改权限*/
function modifyPermission(){
	var row = $('#adminList').datagrid('getSelections');
	var permissionName = $('#permissionTarget').combobox('getValue');
	
	parent.layer.open({
		title: '提示',
		skin: 'layui-layer-molv', //样式类名
		icon: 3,
	    content: '确定修改' + row[0].adminName + '的角色：<font id="font_red">' 
	    	+ $('#permissionCurrent').combobox('getValue') + '</font> --> <font id="font_red">' 
	    	+ $('#permissionTarget').combobox('getValue') + '</font>？',
	    btn: ['确定', '取消'],
	    yes: function(){
	    	// parent.layer.closeAll();
	    	var adminLevel = "";
	    	switch (permissionName) {
	    		case '实验室管理员': adminLevel = 2; break;
	    		case '设备管理员': adminLevel = 3; break;
	    		case '超级管理员': adminLevel = 99; break;
	    		default: break;
	    	} 
	    	
	    	var param = "{\"adminID\":\"" + row[0].adminID + "\",\"adminLevel\":"+adminLevel+"}";
	    	$.ajax({
	    		type : "POST",
	    		dataType : "JSON",
	    		url : basePath + "/admin/modifyPermission.htm?param=" + param  + "&saId=" + parent.adminId,
	    		success : function(data) {
	    			if (data == 'success') {
	    				$('#adminList').datagrid('reload');
	    				parent.layer.open({
	    					title: '提示',
	    					content: '<font  size="2">修改成功！</font>！',
	    					icon: 1,
	    					skin: 'layui-layer-molv',
	    					closeBtn: 0,
	    					btn: ['确认'],
	    					yes: function(index){
	    						parent.layer.close(index);
	    				    	$('#modifyPermissionWindow').window('close');
	    					}
	    				});
	    				return;
	    			} else {
	    				parentLayerAlert('<font color="red" size="2">修改失败！</font>',  '提示', 2, 'layui-layer-molv');				
	    			}
	    		},
	    		error : function() {
	    			parentLayerAlert('<font color="red" size="2">修改失败！</font>',  '提示', 2, 'layui-layer-molv');				
	    		},
	    		failure : function() {
	    			parentLayerAlert('<font color="red" size="2">修改失败！</font>',  '提示', 2, 'layui-layer-molv');				
	    		}
	    	}); 
	    },
	    cancel: function(index){ //或者使用btn2
			parent.layer.close(index);
	    }
	}); 
}

/*删除管理员*/
function deleteAdmin(){
	var row = $('#adminList').datagrid('getSelections');
	
	if (row == null || row.length < 1) {
		parentLayerAlert('<font color="red" size="2">删除至少选择一行!</font>', '提示', 0, 'layui-layer-molv');
		return;
	}
	var param = "";
	for(var i=0; i<row.length; i++){
		param += (row[i].adminID + ","); 
	}
	param = param.substring(0, param.length-1);
	
	parent.layer.open({
		title: '严重警告',
		skin: 'layui-layer-molv', //样式类名
		icon: 3,
	    content: '<font size="2">您确定要删除选中的</font> <font color="red" size="4">'+ row.length +'</font> <font size="2">个管理员</font>？',
	    btn: ['确认', '取消'],
	    yes: function(index, layero){
	    	$.ajax({
				type : "POST",
				dataType : "JSON",
				url : basePath + "/admin/delete.htm?param=" + param,
				success : function(data) {
					$('#adminList').datagrid('reload');
					if (data == 'success') {
						parent.layer.alert('<font  size="2">删除管理员成功</font>！', {
							skin: 'layui-layer-molv', //样式类名
				            icon: 1
				        });
					} else {
						parent.layer.alert('<font color="red" size="2">删除管理员失败</font>', {
							skin: 'layui-layer-molv', //样式类名
				            icon: 2
				        }); 
					}
				},
				error : function() {
					parent.layer.alert('<font color="red" size="2">删除管理员失败</font>', {
						skin: 'layui-layer-molv', //样式类名
			            icon: 2
			        }); 
				},
				failure : function() {
					parent.layer.alert('<font color="red" size="2">删除管理员失败</font>', {
						skin: 'layui-layer-molv', //样式类名
			            icon: 2
			        }); 
				}
			});
	    }, cancel: function(index){ //或者使用btn2
	    	layer.close(index);
	    }
	}); 
}   

/*清除筛选条件*/
function clearSearchBox() {
	$('#adminNameText').textbox("setValue", "");
	$('#adminIdText').textbox("setValue", ""); 
}

function initPermissionCurrent(value) {
	var text = "";
	switch (value) {
		case 0: case "0": text =  "学生";  break;
	 	case 1: case "1": text =  "普通教职工";  break;
	 	case 2: case "2": text =  "实验室管理员"; break;
	 	case 3: case "3": text =  "设备管理员"; break;
	 	case 99: case "99": text =  "超级管理员"; break;

		default: break;
	}
	
	$('#permissionCurrent').textbox('setValue', text);
}

