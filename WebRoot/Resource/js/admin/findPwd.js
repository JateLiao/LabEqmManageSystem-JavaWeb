/**
 * 找回密码
 */ 
$(function(){
	keyDownListener();
});

/**
 * 找回密码
 */
function findPwd(){
	if (inputCheck()) {
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : "../../admin/FindPwd.htm?param=" + getParam(),
			success : function(data) {
				if (data == 'success') {
					parent.layer.open({
						title: '提示',
						content: '密码成功找回！新密码将通过<font color="red" size="2">邮件</font>和<font color="red" size="2">短信</font>的形式发送，请及时登录系统修改密码！',
						icon: 6,
						skin: 'layui-layer-molv',
						btn: ['确定'],
						yes: function () {parent.layer.closeAll();}
					}); 
				} else if (data == 'email_tel_not_match') {
					parentLayerAlert('<font color="red" size="2">邮件或联系电话与账号不匹配，请仔细核对！</font>', '提示', 2, 'layui-layer-molv');
				} else {
					parentLayerAlert('<font color="red" size="2">找回失败！</font>', '提示', 2, 'layui-layer-molv');
				}
			},
			error : function() {
				parentLayerAlert('<font color="red" size="2">找回失败！</font>', '提示', 2, 'layui-layer-molv'); 
			},
			failure : function() {
				parentLayerAlert('<font color="red" size="2">找回失败！</font>', '提示', 2, 'layui-layer-molv');
			}
		});
	} else{
		parentLayerAlert('<font color="red" size="2">请完整填写所需信息！</font>', '提示', 0, 'layui-layer-molv');
	}
}

/**
 * 输入检查
 */
function inputCheck(){
	if(isNullOrEmpty($('#adminId').val())){
		return false;
	}
	if(isNullOrEmpty($('#adminTel').val())){
		return false;
	}
	if(isNullOrEmpty($('#adminEmail').val())){
		return false;
	}
	
	return true;
}

/**
 * json参数拼接
 */
function getParam(){
	var param = "";
	
	if(!isNullOrEmpty($('#adminId').val())){
		param += "{\"adminID\":\"" + $('#adminId').val() + "\"";
	} 
	if(!isNullOrEmpty($('#adminTel').val())){
		param += ",\"adminTel\":\"" +  $('#adminTel').val() + "\"";
	}
	if(!isNullOrEmpty($('#adminEmail').val())){
		param += ",\"adminEmail\":\"" +  $('#adminEmail').val() + "\"}";
	} 
	
	return param;
}

/*回车按键监听*/
function keyDownListener(){
	$(document).keydown(function (event) { 
        if (event.keyCode == 13) { // 回车键
            findPwd();
        }
    });
}