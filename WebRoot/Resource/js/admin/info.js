/**
 * info.js
 */
var isInfoShow = -1;

$(function(){
	if ($(document).attr("title") == '个人信息') {
		isInfoShow = 0;
	} else if ($(document).attr("title") == '修改信息'){
		isInfoShow = 1;
	} else {
		isInfoShow = -1;
	}
	adminInfoInit(); 
});

/*管理员个人信息初始化*/
function adminInfoInit() {
	if (isInfoShow == 0 || isInfoShow == 1) {
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : parent.basePath + "/admin/queryInfo.htm?adminId=" + parent.adminId,
			success : function(data) {
				if (!isNullValue(parent.adminId)) {
					$('#adminId').text(parent.adminId);
				} 
				if (isInfoShow == 0) {
					if (!isNullOrEmpty(data.adminName)) {
						$('#adminName').text(data.adminName);
					}
					if (!isNullOrEmpty(data.adminIDCardNo)) {
						$('#adminIDCardNo').text(data.adminIDCardNo);
					} 
					if (!isNullOrEmpty(data.adminAddress)) {
						$('#adminAddress').text(data.adminAddress);
					}
					if (!isNullOrEmpty(data.adminBirthday)) {
						$('#adminBirthday').text(data.adminBirthday);
					}
					if (!isNullOrEmpty(data.adminSex)) {
						$('#adminSex').text(data.adminSex);
					}
					if (!isNullOrEmpty(data.adminEmail)) {
						$('#adminEmail').text(data.adminEmail);
					}
					if (!isNullOrEmpty(data.adminTel)) {
						$('#adminTel').text(data.adminTel);
					}
					if (!isNullOrEmpty(data.adminCollege)) {
						$('#adminCollege').text(data.adminCollege);
					}
					if (!isNullOrEmpty(data.adminNativePlace)) {
						$('#adminNativPlace').text(data.adminNativePlace);
					}
					if (!isNullOrEmpty(data.adminDept)) {
						$('#adminDept').text(data.adminDept);
					}
					
					switch (data.adminLevel) {
						case '1': $('#permissionCurrent').textbox('setValue', '初级'); break;
						case '2': $('#permissionCurrent').textbox('setValue', '中级'); break;
						case '99': $('#permissionCurrent').textbox('setValue', '高级'); break; 
						default: break;
					}
				} else if (isInfoShow == 1) {
					if (!isNullOrEmpty(data.adminName)) {
						$('#adminName').textbox('setValue',data.adminName);
					}
					if (!isNullOrEmpty(data.adminIDCardNo)) {
						$('#adminIDCardNo').textbox('setValue',data.adminIDCardNo);
					} 
					if (!isNullOrEmpty(data.adminAddress)) {
						$('#adminAddress').textbox('setValue',data.adminAddress);
					}
					if (!isNullOrEmpty(data.adminBirthday)) {
						$('#adminBirthday').textbox('setValue',data.adminBirthday);
					}
					if (!isNullOrEmpty(data.adminSex)) {
						$('#adminSex').textbox('setValue',data.adminSex);
					}
					if (!isNullOrEmpty(data.adminEmail)) {
						$('#adminEmail').textbox('setValue',data.adminEmail);
					}
					if (!isNullOrEmpty(data.adminTel)) {
						$('#adminTel').textbox('setValue',data.adminTel);
					}
					if (!isNullOrEmpty(data.adminCollege)) {
						$('#adminCollege').textbox('setValue', data.adminCollege);
					}
					if (!isNullOrEmpty(data.adminNativePlace)) {
						$('#adminNativePlace').textbox('setValue',data.adminNativePlace);
					}
					if (!isNullOrEmpty(data.adminDept)) {
						$('#adminDept').textbox('setValue',data.adminDept);
					}
					
					switch (data.adminLevel) {
						case '1': $('#permissionCurrent').textbox('setValue', '初级'); break;
						case '2': $('#permissionCurrent').textbox('setValue', '中级'); break;
						case '99': $('#permissionCurrent').textbox('setValue', '高级'); break; 
						default: break;
					} 
				}
			},
			error : function() {
				parentLayerAlert('<font color="red" size="2">初始化错误！</font>', '提示', 2, 'layui-layer-molv');
				parent.layer.closeAll();
			},
			failure : function() {
				parentLayerAlert('<font color="red" size="2">初始化错误！</font>', '提示', 2, 'layui-layer-molv');
				parent.layer.closeAll();
			}
		});
	}
}

/*修改密码*/
function modifyPassword(){
	var content = '';
	if (isNullOrEmpty($('#oriPwd').val()) || isNullOrEmpty($('#newPwd').val()) || isNullOrEmpty($('#newPwdAgain').val())) {
		content = '请完整填写'; 
	} 
	if (trim($('#newPwd').val()) != trim($('#newPwdAgain').val())) {
		content = '密码输入不一致';
	}
	if (!isNullOrEmpty(content)) {
		parentLayerAlert(content, '提示', 0, 'layui-layer-molv');
		return;
	}
	
	var param = '{\"adminID\":\"' + parent.adminId + '\",\"passWord\":\"' + $('#newPwd').val() + '\",\"tmp1\":\"'+ $('#oriPwd').val() +'\"}';
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : parent.basePath + "/admin/modifyPassword.htm?param=" + param,
		success : function(data) {
			var content = ''; // 内容
			var icon = 0; // 图标
			if (data == 'success') {
				content = '<font color="red" size="2">修改成功！</font>';
				icon = 6;
				$('#oriPwd').val('');
				$('#newPwd').val('');
				$('#newPwdAgain').val('');
			} else if (data == 'wrong_pwd') {
				content = '<font color="red" size="2">原密码错误！</font>';
				icon = 5;
			} else {
				content = '<font color="red" size="2">修改失败！</font>';
				icon = 5;
			}
			parentLayerAlert(content, '提示', icon, 'layui-layer-molv'); 
		},
		error : function() {
			parentLayerAlert('<font color="red" size="2">修改失败！</font>', '提示', 2, 'layui-layer-molv');
		},
		failure : function() {
			parentLayerAlert('<font color="red" size="2">修改失败！</font>', '提示', 2, 'layui-layer-molv');
		}
	});
	
}

/*修改信息*/
function modifyInfo(){
	if (!inputCheck_modifyInfo()){
		parentLayerAlert('<font color="red" size="2">*</font> 标为必填项！', '警告', 0, 'layui-layer-molv');
		return;
	}

	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : parent.basePath + "/admin/modifyInfo.htm?param=" + getParam(),
		success : function(data) {
				if (data == 'success') {
					parent.layer.open({
						title: '提示',
						content: '<font color="red" size="2">修改成功！</font>',
						skin: 'layui-layer-molv', //样式类名
						icon: 6,
			            closeBtn: 0,
					    btn: ['确定'],
					    yes: function(index){ 
					    	parent.layer.close(index); 
					    }
					});
				} else {
					parentLayerAlert('<font color="red" size="2">修改失败！</font>', '提示', 2, 'layui-layer-molv');
				}
		},
		error : function() { 
			parentLayerAlert('<font color="red" size="2">修改失败！</font>', '提示', 2, 'layui-layer-molv');
		},
		failure : function() { 
			parentLayerAlert('<font color="red" size="2">修改失败！</font>', '提示', 2, 'layui-layer-molv');
		}
	});

}

/*修改信息输入检查*/
function inputCheck_modifyInfo(){
	if(isNullOrEmpty($('#adminName').val()) || isNullOrEmpty($('#adminTel').val()) || isNullOrEmpty($('#adminEmail').val())){
		return false;
	}
	
	return true; 
} 

/*修改信息，拼接参数*/
function getParam(){
	var param = "{\"adminID\":\"" + parent.adminId + "\"";
	
	if(trim($('#adminName').val()) != ""){
		param += ",\"adminName\":\"" + trim($('#adminName').val()) + "\"";
	}
	if(trim($('#adminIDCardNo').val()) != ""){
		param += ",\"adminIDCardNo\":" +  trim($('#adminIDCardNo').val());
	}
	if(trim($('#adminBirthday').datebox('getValue')) != ""){  // 生日
		param += ",\"adminBirthday\":\"" +  $('#adminBirthday').datetimebox('getValue') + " 00:00:00" + "\"";
	}
	if(trim(getSex()) != ""){ // 性别
		param += ",\"adminSex\":\"" +  getSex() + "\"";
	}
	if(trim($('#adminAddress').val()) != ""){
		param += ",\"adminAddress\":\"" +  trim($('#adminAddress').val()) + "\"";
	}
	if(trim($('#adminNativePlace').val()) != ""){
		param += ",\"adminNativePlace\":\"" +  trim($('#adminNativePlace').val()) + "\"";
	}
	if(trim($('#adminDept').val()) != ""){
		param += ",\"adminDept\":\"" +  trim($('#adminDept').val()) + "\"";
	} 
	if(trim(getCollege()) != ""){ // 学院
		param += ",\"adminCollege\":\"" +  getCollege() + "\"";
	}
	if(trim($('#adminTel').val()) != ""){
		param += ",\"adminTel\":\"" +  trim($('#adminTel').val()) + "\"";
	}
	if(trim($('#adminEmail').val()) != ""){
		param += ",\"adminEmail\":\"" +  trim($('#adminEmail').val()) + "\"";
	} 
	param += "}";
	
	if(param[1] == ","){
		param = param[0] + param.substring(2, param.length);
	}
	return param;
} 

function getSex(){
	if($('#adminSex').combobox('getValue') == '-请选择-'){
		return "";
	} 
	return $('#adminSex').combobox('getValue');
}

function getCollege(){
	if($('#adminCollege').combobox('getValue') == '-请选择-'){
		return "";
	} 
	return $('#adminCollege').combobox('getValue');
}