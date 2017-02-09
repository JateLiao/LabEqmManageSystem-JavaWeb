/**
 * 注册
 */

function regist(){ 
	if (inputCheck()) {
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : "../../admin/Regist.htm?param=" + getParam(),
			success : function(data) {
					if (data == 'success') { 
						parent.layer.open({
							title: '提示',
							content: '<font color="red" size="2">注册成功！</font>',
							skin: 'layui-layer-molv', //样式类名
				            icon: 6,
				            closeBtn: 0,
				            btn: ['确定'],
				            yes: function(){ parent.layer.closeAll(); }
						});
					} else if (data == 'failed'){
						parentLayerAlert('<font color="red" size="2">注册失败！</font>', '提示', 2, 'layui-layer-molv');
					} else if (data == 'param_error'){
						parentLayerAlert('<font color="red" size="2">参数有误，注册失败，请仔细检查填写的参数！</font>', '提示', 2, 'layui-layer-molv');
					} else if (data == 'existed_id'){
						parentLayerAlert('<font color="red" size="2">该工号已被注册！</font>', '提示', 2, 'layui-layer-molv');
					} else if (data == 'not_normal_staff'){
						parentLayerAlert('<font color="red" size="2">只有普通教职工才能注册为管理员！</font>', '提示', 2, 'layui-layer-molv');
					}
			},
			error : function() {
				parent.layer.closeAll();
				parentLayerAlert('<font color="red" size="2">注册失败！</font>', '提示', 2, 'layui-layer-molv');
			},
			failure : function() {
				parent.layer.closeAll();
				parentLayerAlert('<font color="red" size="2">注册失败！</font>', '提示', 2, 'layui-layer-molv');
			}
		});
	}
}

/**
 * 输入检查
 */
function inputCheck(){
	if(isNullOrEmpty($('#adminId').val()) || isNullOrEmpty($('#adminName').val()) || isNullOrEmpty($('#pwd').val()) || isNullOrEmpty($('#pwdAgain').val())
			|| isNullOrEmpty($('#adminTel').val()) || isNullOrEmpty($('#adminEmail').val())
			|| isNullOrEmpty(getLevel())){
		parentLayerAlert('<font color="red" size="2">*</font> 标为必填项！', '提示', 0, 'layui-layer-molv');
		return false;
	}
	if (trim($('#pwd').val()) != trim($('#pwdAgain').val())) {
		parentLayerAlert('<font color="red" size="2">密码输入不一致</font>', '提示', 0, 'layui-layer-molv');
		return false;
	}
	if (!isEmail(trim($('#adminEmail').val()))) {
		parentLayerAlert('<font color="red" size="2">请输入正确的邮箱</font>', '提示', 0, 'layui-layer-molv');
		return false;
	}
	if (!isMobileTel(trim($('#adminTel').val()))) {
		parentLayerAlert('<font color="red" size="2">请输入正确的联系号码</font>', '提示', 0, 'layui-layer-molv');
		return false;
	}
	
	return true; 
}

/*取消注册*/
function cancelRegist(){
	parent.layer.closeAll();
}

/**
 * 构造参数
 * @returns {String}
 */
function getParam(){
	var param = "{";
	
	if(trim($('#adminId').val()) != ""){
		param += "\"adminID\":\"" + trim($('#adminId').val()) + "\"";
	}
	if(trim($('#adminName').val()) != ""){
		param += ",\"adminName\":\"" + trim($('#adminName').val()) + "\"";
	}
	if(trim($('#pwd').val()) != ""){
		param += ",\"passWord\":\"" +  trim($('#pwd').val()) + "\"";
	} 
	if(trim($('#adminIDCardNo').val()) != ""){
		param += ",\"adminIDCardNo\":" +  trim($('#adminIDCardNo').val());
	}
	if(trim($('#adminBirthday').datebox('getValue')) != ""){ 
		param += ",\"adminBirthday\":\"" +  $('#adminBirthday').datetimebox('getValue') + " 00:00:00" + "\"";
	}
	if(trim(getSex()) != ""){
		param += ",\"adminSex\":\"" +  getSex() + "\"";
	}
	if(trim($('#adminAddress').val()) != ""){
		param += ",\"adminAddress\":\"" +  trim($('#adminAddress').val()) + "\"";
	}
	if(trim($('#adminNativPlace').val()) != ""){
		param += ",\"adminNativPlace\":\"" +  trim($('#adminNativPlace').val()) + "\"";
	}
	if(trim($('#adminDept').val()) != ""){
		param += ",\"adminDept\":\"" +  trim($('#adminDept').val()) + "\"";
	} 
	if(trim(getCollege()) != ""){ // 学院
		param += ",\"adminCollege\":\"" +  getCollege() + "\"";
	}
	if(trim(getLevel()) != ""){ // 学院
		param += ",\"adminLevel\":\"" +  getLevel() + "\"";
	}
	if(trim($('#adminTel').val()) != ""){
		param += ",\"adminTel\":\"" +  trim($('#adminTel').val()) + "\"";
	}
	if(trim($('#adminEmail').val()) != ""){
		param += ",\"adminEmail\":\"" +  trim($('#adminEmail').val()) + "\"";
	} 
	param += ",\"tmp1\":\"0\"}";
	
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

function getLevel(){
	var value = $('#adminLevel').combobox('getValue');
	if(value == '-请选择-'){
		return "";
	} else if (value == '实验室管理员') {
		return '2';
	} else if (value == '设备管理员') {
		return '3';
	} else {
		return "";
	}
}