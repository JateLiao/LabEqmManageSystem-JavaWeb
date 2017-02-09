var admin = null; //管理员登录账号
var password = null; //密码
var validCode = null; //验证码

/*加载DataGrid*/
$(function() {
	createValidCode(); // 生成验证码
	keyDownListener(); // 按键监听
});

/* 登录 */
function login() { 
	if(user_inputCheck()){
		var params = "../admin/login.htm?admin=" + admin + "&password=" + password + "&validCode=" + validCode;
		$.ajax({
			type: 'POST',
			url: params,
			dataType: 'json',
			success: function(data){
				if (data == 'success') { 
					window.location.href = "main.jsp";
					return;
				} else if (data == 'id_or_pwd_error') {
					parentLayerAlert('<font color="red" size="2">账号或密码错误，登录失败！</font>', '提示', 2, 'layui-layer-molv');
					return;
				} else if (data == "ValidCode_Error") { //验证码输入错误
					parentLayerAlert('<font color="red" size="2">验证码输入错误！</font>', '提示', 2, 'layui-layer-molv');
					changeValicode(); // 输入错误则更换验证码
					return;
				} else if (data == 'no_pass') {
					parentLayerAlert('<font color="red" size="2">注册未审核，不能登录！</font>', '提示', 2, 'layui-layer-molv');
					return;
				} else if (data == 'refused') {
					parentLayerAlert('<font color="red" size="2">注册被管理员拒绝，请重新注册！</font>', '提示', 2, 'layui-layer-molv');
					return;
				}
			},
			error: function(){
				layer.open({
					title: '错误',
					skin: 'layui-layer-molv', //样式类名
					content: '<font color="red">登录异常，登录失败！</font>',
					icon: 2
				});  
			}
		});
	}
}

/*输入校验*/
function user_inputCheck() {
	admin = trim($("#admin").val());
	password = trim($("#password").val());
	validCode = trim($("#validCode").val())
	
	if (admin == "" || password == "") {
		layer.open({
			title: '提示',
			shift: 6,
			skin: 'layui-layer-molv', //样式类名
			content: '用户名或密码不能为空<font style="font-weight: bold;">！</font>',
			icon: 0
		});
		return false;
	} else if (validCode == "") {
		layer.open({
			title: '提示',
			shift: 6,
			skin: 'layui-layer-molv', //样式类名
			content: '验证码不能为空！',
			icon: 0
		});
		return false;
	}else {
		return true;
	}
}

/*回车按键监听*/
function keyDownListener(){
	$(document).keydown(function (event) { 
        if (event.keyCode == 13) { // 回车键
            login();
        }
    });
}

/*验证码生成*/
function createValidCode(){
	/*验证码*/
	$("#imgCode").attr("src", "../admin/validate.htm?rmd=" + new Date().getTime())// 如果需要点击图片改变图片的内容，则必须添加时间搓
	.click(
		function() {
			$(this).attr("src", "../admin/validate.htm?rmd=" + new Date().getTime());
		});
}

/*更换验证码*/
function changeValicode(){
	$("#imgCode").attr("src", "../admin/validate.htm?rmd=" + new Date().getTime());
}

/* 注册 */
function toRegist() {
	layer.open({
		type : 2,
		title : '管理员注册',
		skin: 'layui-layer-molv', //样式类名
		shadeClose : true,
		/*shade : false,*/
		maxmin : false, // 开启最大化最小化按钮
		area : [ '325px', '620px' ],
		content : ['admin/regist.html', 'no']
	});
}

/*找回密码*/
function toFindPwd() {
	layer.open({
		title : '密码找回', 
		type : 2,
		skin: 'layui-layer-molv', //样式类名 
		shadeClose : true,
		maxmin : true, // 开启最大化最小化按钮
		area : [ '410px', '285px' ],
		content : 'admin/findPwd.html' 
	});
}


/*测试：数据生成*/
function create(){
	$.ajax({
		type: 'POST',
		url: '../test/createHugeData.htm'
	});
}



