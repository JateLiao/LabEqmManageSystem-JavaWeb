var isModify = false;  //修改或者添加的标识，默认为添加
var eqmGrid = null; // 设备列表窗口
var managerId = parent.adminId; //getUrlPram('managerId');
var managerName = parent.adminName; //getUrlPram('managerName');
var basePath = parent.basePath;

/*加载DataGrid*/
$(function() {
	initMyEqmList();
});

/*我的设备初始化*/
function initMyEqmList(){
	$('#managerText').textbox('setValue', managerName);  
	$('#managerIdText').val(managerId); 
	$('#managerText').textbox({editable:false, disabled:true });
	loadEquipmentList();
}

function loadEquipmentList(){
	eqmGrid = $('#eqmList').datagrid({
		title : '我的托管设备',
		iconCls : 'icon-large-smartart',
		width : '99%',
		height: 515,
		rownumbers: true, //显示序号与否
		pageSize : 15,//默认选择的分页是每页5行数据  
		pageList : [ 15, 30, 50 ],//可以选择的分页集合  
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
			'propertyNo' : $('#propertyNoText').val(),
			'eqmName' : $('#eqmNameText').val(),
			'manager' : $('#managerText').val(),
			'managerId' : $('#managerIdText').val(),
			'eqmStatus' : getSearchEqmStatus(),
			'startDate' : $('#startDate').datetimebox('getValue') ,
			'endDate' : $('#endDate').datetimebox('getValue') 
		},
		frozenColumns:[[
		                {field: 'ck' ,checkbox: true },
		             ]],
		columns : [ [
		             {field : 'keyID', hidden: true},
		             {field : 'propertyNo',title : '设备资产号',width : '16%',align : 'center', sortable:true},
		             {field : 'eqmName',title : '设备名',width : '15%',align : 'center', sortable:true}, 
		             {field : 'eqmClass', title : '设备类别', width : '15%', align : 'center', sortable:true},
		             {field : 'manager',title : '托管人', width : '10%', align : 'center' , sortable:true},
		             {field : 'eqmStatus',title : '设备状态', width : '10%', align : 'center' , sortable:true ,
		            	 formatter:function(value,row,index){
			            	 if (value == '使用中') {
			            		 return '<font color="#008000">' + value + '</font>';
			            	 } else if (value == '库存中') { 
			            		 return '<font color="#004080">' + value + '</font>';
			            	 } else if (value == '维修中') { 
			            		 return '<font color="#e37200">' + value + '</font>';
			            	 } else if (value == '已报废') { 
			            		 return '<font color="#ff0000">' + value + '</font>';
			            	 } else if (value == '已借出') { 
			            		 return '<font color="#0000a2">' + value + '</font>';
			            	 } else {
								return value;
			            	 }
		            	 }
		             },
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
			id:'addBtn',
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				isModify = false;
				clearAddBox();
				addOrModifyEquipmentWindow(null);
			}
		}, '-',{
			id:'modiftBtn',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				isModify = true;
				var row = $('#eqmList').datagrid('getSelections');
				if (row == null || row.length < 1) {
					parentLayerAlert('<font color="red" size="2">修改至少选择一行!</font>', '提示', 0, 'layui-layer-molv');
					return;
				}
				if (row.length > 1) {
					parentLayerAlert('<font color="red" size="2">每次只能修改一行数据！</font>', '提示', 0, 'layui-layer-molv');
					return;
				}
				if (row[0].keyID == "" || row[0].keyID == null) {
					parentLayerAlert('<font color="red" size="2">无数据，无法修改！</font>', '提示', 0, 'layui-layer-molv');
					return;
				}

				addOrModifyEquipmentWindow(row); // 打开窗口

			}
		},'-', {
			id:'addBatchBtn',
			text : 'Excel导入',
			iconCls : 'icon-import-batch',
			handler : function() {
				importEquipmentWindow();
			}
		}, '-', {
			id:'importBtn',
			text : 'Excel导出',
			iconCls : 'icon-save',
			handler : function() {
				exportExcel();
			}
		},'-',{
			id:'exportBtn',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				deleteEquipment();
			}
		},'-',{
			id:'printBtn',
			text : '打印',
			iconCls : 'icon-print',
			handler : function() {
				parent.layer.open({
					title: '消息',
					skin: 'layui-layer-molv', //样式类名
					icon: 3,
				    content: '<font size="2">您确定要打印该页面吗？</font>',
				    btn: ['确定', '取消'],
				    yes: function(){
				    	parent.layer.closeAll();
				    	window.print();
				    },
				    cancel: function(index){ //或者使用btn2  //按钮【按钮二】的回调
				    	parent.layer.closeAll(); 
				    }
				}); 
			}
		} ]
	});
}  

/*打开操作窗口*/
function addOrModifyEquipmentWindow(row) {
	//basePath
	$('#manager').textbox('setValue', managerName);  
	$('#manager').textbox({editable:false, disabled:true });
	$('#managerId').textbox('setValue', managerId);  
	$('#managerId').textbox({editable:false, disabled:true });
	
	var addOrModifyWindow = $('#addOrModifyEquipmentWindow').window({
		width : 600,
		height : 405,
		modal : true,
		draggable : true,
		onBeforeOpen:function(){ //窗体打开之前触发
			$("#add").css('display','block');
		},
		onBeforeDestroy:function(){
			$("#add").css('display','none'); 
		}
	});
	
	$("#okBtn").unbind("click");
	$('#okBtn').bind('click', function(){
    	addOrModifyEquipment();
    });
	$('#resetBtn').bind('click', function(){
		initModify(row);
    });
    $('#cancelBtn').bind('click', function(){
    	$('#addOrModifyEquipmentWindow').window('close');
    });
    
	if (isModify) { //修改
		$('#addOrModifyEquipmentWindow').panel({title:"&nbsp;修改设备信息"});
		$('#addOrModifyEquipmentWindow').height(410);
		$('#resetBtn').show();
		$('#propertyNo').textbox({editable:false, disabled:true });
		
		/*修改按钮的样式*/
		$('#okBtn').linkbutton({text:'修改' });
		
		/*组件赋值*/
		initModify(row);
	}else{ //添加
		$('#addOrModifyEquipmentWindow').panel({title:"&nbsp;添加设备"});
		$('#addOrModifyEquipmentWindow').height(395);
		$('#resetBtn').hide();
		
		$('#propertyNo').textbox({editable:true, disabled:false }); 
		
		/*修改按钮的样式*/
		$('#okBtn').linkbutton({text:'添加' });
	}
} 

/*添加或者修改设备*/
function addOrModifyEquipment(){
	var param = getParam();
	var url = "";
	
	if(isModify){ //修改
		url = basePath + "/equipments/updateEquipment.htm?param=" + param;
	} else{ //添加
		$('#manager').textbox("setValue", "");
		$('#managerId').textbox("setValue", "");
		url = basePath + "/equipments/addEquipment.htm?param=" + param;
	}
	$.ajax({
		type: 'POST',
		url: url,
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success: function(data){
			if(data != 'success'){ //操作失败
				$('#addOrModifyEquipmentWindow').window('close');
				parentLayerAlert('<font color="red" size="2">' + data +'</font>', '提示', 2, 'layui-layer-molv');
				return;
			}
			$('#addOrModifyEquipmentWindow').window('close');
			if (isModify) {
				parentLayerAlert('<font color="red" size="2">更新设备成功！</font>', '提示', 1, 'layui-layer-molv');
			}else{
				parentLayerAlert('<font color="red" size="2">添加设备成功！</font>', '提示', 1, 'layui-layer-molv');
			}
			 
			$('#eqmList').datagrid('reload');
		},
		error: function(){
			parentLayerAlert('<font color="red" size="2">操作失败！</font>', '提示', 2, 'layui-layer-molv');
		} 
	});
}

/*初始化修改窗体*/
function initModify(row){
	$('#eqmKeyID').val(row[0].keyID);
	$('#propertyNo').textbox('setValue', row[0].propertyNo);
	$('#eqmName').textbox('setValue',row[0].eqmName);
	$('#eqmType').textbox('setValue',row[0].eqmType);
	$('#eqmClass').textbox('setValue',row[0].eqmClass);
	$('#eqmFactory').textbox('setValue',row[0].eqmFactory);
	$('#eqmPrice').textbox('setValue',row[0].eqmPrice);
	$('#buyTime').textbox('setValue',row[0].buyTime);
	$('#college').textbox('setValue',row[0].college);
	$('#eqmLab').textbox('setValue',row[0].eqmLab);
	$('#eqmStatus').textbox('setValue',row[0].eqmStatus);
	$('#manager').textbox('setValue',row[0].manager);
	$('#managerId').textbox('setValue',row[0].managerId);
	$('#buyStaff').textbox('setValue',row[0].buyStaff);
	$('#description').textbox('setValue',row[0].description);
}

/*excel批量导入设备*/
function importEquipmentWindow(){
	parent.layer.open({
		type : 2,
		title : '批量导入设备',
		skin: 'layui-layer-molv', //样式类名
		shadeClose : false,
		/*shade : false,*/
		maxmin : true, // 开启最大化最小化按钮
		area : [ '440px', '280px' ],
		content : 'equipment/importEquipment.jsp'
	});
}

/*批量导出设备为excel*/
function exportExcel(){
	parent.layer.open({
		icon : 3,
		title : '提示',
		skin: 'layui-layer-molv', //样式类名
		shadeClose : true,
		content : '确定导出设备列表当前页为excel文件？',
		btn : ['确定', '取消'],
		yes : function(index){
			parent.layer.closeAll();
		    var param = getQueryParam();
		    var url = basePath + "/equipments/exportEquipmentExcel.htm" + param;
		    url=encodeURI(encodeURI(url));
		    location.href = url;
		},
		cancel : function(index){
			parent.layer.closeAll();
		}
	});
}

/*删除设备*/
function deleteEquipment(){
	var row = $('#eqmList').datagrid('getSelections');
	
	if (row == null || row.length < 1) {
		parent.layer.alert('<font color="red" size="2">删除至少选择一行!</font>', {
			skin: 'layui-layer-molv', //样式类名
            icon: 0 
        });
		return;
	}
	var param = "";
	for(var i=0; i<row.length; i++){
		param += (row[i].keyID + ","); 
	}
	param = param.substring(0, param.length-1);
	
	parent.layer.open({
		title: '严重警告',
		skin: 'layui-layer-molv', //样式类名
		icon: 3,
	    content: '<font size="2">您确定要删除选中的</font> <font color="red" size="4">'+ row.length +'</font> <font size="2">条记录</font>？',
	    btn: ['确认', '取消'],
	    yes: function(index, layero){ //或者使用btn1
	        //按钮【按钮一】的回调
	    	$.ajax({
				type : "POST",
				dataType : "JSON",
				url : basePath + "/equipments/delete.htm?param=" + param,
				success : function(data) { 
					$('#eqmList').datagrid('reload');
					parentLayerAlert('<font  size="2">删除设备成功</font>！', '提示', 1, 'layui-layer-molv');
				},
				error : function() {
					parentLayerAlert('<font  size="2">删除设备失败</font>！', '提示', 2, 'layui-layer-molv');
				},
				failure : function() {
					parentLayerAlert('<font  size="2">删除设备失败</font>！', '提示', 2, 'layui-layer-molv');
				}
			});
	    },cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    	parent.layer.closeAll(); 
	    }
	}); 
}  

/*拼接post到后台的参数,json格式*/
function getParam(){
	var param = "{";
	
	if(!isNullOrEmpty($('#eqmKeyID').val())){
		param = param + "\"keyID\":\"" + $('#eqmKeyID').val() + "\"";
	}
	if(!isNullOrEmpty($('#propertyNo').val())){
		param = param + ",\"propertyNo\":\"" + $('#propertyNo').val() + "\"";
	}
	if(!isNullOrEmpty($('#eqmName').val())){
		param = param + ",\"eqmName\":\"" + $('#eqmName').val() + "\"";
	}
	if(!isNullOrEmpty($('#eqmType').val())){
		param = param + ",\"eqmType\":\"" +  $('#eqmType').val() + "\"";
	}
	if(!isNullOrEmpty($('#eqmClass').val())){
		param = param + ",\"eqmClass\":\"" +  $('#eqmClass').val() + "\"";
	}
	if(!isNullOrEmpty($('#eqmFactory').val())){
		param = param + ",\"eqmFactory\":\"" +  $('#eqmFactory').val() + "\"";
	} 
	if(!isNullOrEmpty($('#eqmPrice').val())){
		param = param + ",\"eqmPrice\":" +  $('#eqmPrice').val();
	}
	if(!isNullOrEmpty($('#buyTime').datebox('getValue'))){ 
		param = param + ",\"buyTime\":\"" +  $('#buyTime').datetimebox('getValue') + " 00:00:00" + "\"";
	}
	if(!isNullOrEmpty($('#college').combobox('getValue'))){
		param = param + ",\"college\":\"" +  $('#college').combobox('getValue') + "\"";
	}
	if(!isNullOrEmpty($('#eqmLab').val())){
		param = param + ",\"eqmLab\":\"" +  $('#eqmLab').val() + "\"";
	}
	if(!isNullOrEmpty($('#manager').val())){
		param = param + ",\"manager\":\"" +  $('#manager').val() + "\"";
	}
	if(!isNullOrEmpty($('#managerId').val())){
		param = param + ",\"managerId\":\"" +  $('#managerId').val() + "\"";
	}
	if(!isNullOrEmpty(getAddEqmStatus())){
		param = param + ",\"eqmStatus\":\"" +  getAddEqmStatus() + "\"";
	}  
	if(!isNullOrEmpty($('#buyStaff').val())){
		param = param + ",\"buyStaff\":\"" +  $('#buyStaff').val() + "\"";
	}
	if(!isNullOrEmpty($('#description').val())){
		param = param + ",\"description\":\"" +  $('#description').val() + "\"";
	}
	param += "}";
	
	if(param[1] == ","){
		param = param[0] + param.substring(2, param.length);
	}
	return param;
}

/*清除筛选条件*/
function clearSearchBox() {
	/*if (isNullOrEmpty(managerId) || isNullOrEmpty(managerName)) { // 我的设备列表不清除管理人
		$('#managerText').textbox("setValue", "");
	}*/
	$('#propertyNoText').textbox("setValue", "");
	$('#eqmNameText').textbox("setValue", "");
	$('#startDate').datebox("setValue", "");
	$('#endDate').datebox("setValue", "");
	$('#eqmStatus').combobox('setValue', '-请选择状态-')
}

/*清空添加*/
function clearAddBox(){
	$('#eqmKeyID').val("");
	$('#propertyNo').textbox('setValue',"");
	$('#eqmName').textbox('setValue',"");
	$('#eqmType').textbox('setValue',"");
	$('#eqmClass').textbox('setValue',"");
	$('#eqmFactory').textbox('setValue',"");
	$('#eqmPrice').textbox('setValue',"");
	$('#buyTime').textbox('setValue',"");
	$('#college').textbox('setValue',"-请选择学院-");
	$('#college').textbox('setValue',"");
	$('#eqmLab').textbox('setValue',"");
	$('#manager').textbox('setValue',"");
	$('#buyStaff').textbox('setValue',"");
	$('#description').textbox('setValue',"");
} 

/*获取搜索设备状态*/
function getSearchEqmStatus(){
	if($('#eqmStatus_search').combobox('getValue') == '-请选择状态-'){
		return "";
	} 
	return $('#eqmStatus_search').combobox('getValue');
}
/*获取添加设备状态*/
function getAddEqmStatus(){
	if($('#eqmStatus').combobox('getValue') == '-请选择-'){
		return "";
	} 
	return $('#eqmStatus').combobox('getValue');
}

/*get the query param*/
function getQueryParam(){
    var options = $('#eqmList').datagrid('getPager').data("pagination").options;  
    var current = options.pageNumber;  // 当前页码
    var pageSize = options.pageSize; // 每页数量

	var param = "?page=" + current + "&rows=" + pageSize + "&";
	param += "managerId=" + managerId + "&";
	param += "manager=" + managerName + "&";
    
	if (!isNullOrEmpty($('#propertyNoText').textbox('getValue'))) {
		param += "propertyNo=" + $('#propertyNoText').textbox('getValue') + "&";
	}
	if (!isNullOrEmpty($('#eqmNameText').textbox('getValue'))) {
		param += "eqmName=" + $('#eqmNameText').textbox('getValue') + "&";
	}
	if (!isNullOrEmpty($('#managerText').textbox('getValue'))) {
		param += "manager=" + managerName + "&";
	}
	if (!isNullOrEmpty($('#managerIdText').val())) {
		param += "managerId=" + managerId + "&";
	}
	if (!isNullOrEmpty(getSearchEqmStatus())) {
		param += "eqmStatus=" + getSearchEqmStatus() + "&";
	}
	if (!isNullOrEmpty($('#startDate').datetimebox('getValue'))) {
		param += "startDate=" + $('#startDate').datetimebox('getValue') + "&";
	}
	if (!isNullOrEmpty($('#endDate').datetimebox('getValue') )) {
		param += "endDate=" + $('#endDate').datetimebox('getValue') + "&";
	} 
	
	return param.substring(0, param.length - 1);
}

