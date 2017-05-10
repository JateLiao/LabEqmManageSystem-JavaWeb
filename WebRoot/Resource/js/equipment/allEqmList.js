var isModify = false;  //修改或者添加的标识，默认为添加
var eqmGrid = null;

/*加载DataGrid*/
$(function() {
	loadEquipmentList();
});

function loadEquipmentList(){
	eqmGrid = $('#eqmList').datagrid({
		title : '所有设备',
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
		url : "../../equipments/page.htm",
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
			'eqmStatus' : getSearchEqmStatus(),
			'startDate' : $('#startDate').datetimebox('getValue') ,
			'endDate' : $('#endDate').datetimebox('getValue') 
		},
		columns : [ [
		             {field : 'keyID', hidden: true},
		             {field : 'propertyNo',title : '设备资产号',width : '15%',align : 'center', sortable:true},
		             {field : 'eqmName',title : '设备名',width : '15%',align : 'center', sortable:true}, 
		             {field : 'eqmClass', title : '设备类别', width : '15%', align : 'center', sortable:true},
		             {field : 'manager',title : '托管人', width : '10%', align : 'center' , sortable:true},
		             {field : 'eqmStatus',title : '设备状态', width : '10%', align : 'center' , sortable:true,
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
			id:'importBtn',
			text : 'Excel导出',
			iconCls : 'icon-save',
			handler : function() {
				expotEquipmentExcel();
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

//批量导出设备为excel
function expotEquipmentExcel(){
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
		    location.href= basePath + "/equipments/exportEquipmentExcel.htm" + param; 
		},
		cancel : function(index){
			parent.layer.closeAll();
		}
	});
}

/*清除筛选条件*/
function clearSearchBox() {
	$('#managerText').textbox("setValue", "");
	$('#propertyNoText').textbox("setValue", "");
	$('#eqmNameText').textbox("setValue", "");
	$('#startDate').datebox("setValue", "");
	$('#endDate').datebox("setValue", "");
	$('#eqmStatus').combobox('setValue', '-请选择状态-')
}

/*获取搜索设备状态*/
function getSearchEqmStatus(){
	if($('#eqmStatus_search').combobox('getValue') == '-请选择状态-'){
		return "";
	} 
	return $('#eqmStatus_search').combobox('getValue');
}

function getQueryParam(){
    var options = $('#eqmList').datagrid('getPager').data("pagination").options;  
    var current = options.pageNumber;  // 当前页码
    var pageSize = options.pageSize; // 每页数量

	var param = "?page=" + current + "&rows=" + pageSize + "&";
    
	if (!isNullOrEmpty($('#propertyNoText').val())) {
		param += "propertyNo=" + $('#propertyNoText').val() + "&";
	}
	if (!isNullOrEmpty($('#eqmNameText').val())) {
		param += "eqmName=" + $('#eqmNameText').val() + "&";
	}
	if (!isNullOrEmpty($('#managerText').val())) {
		param += "manager=" + $('#managerText').val() + "&";
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