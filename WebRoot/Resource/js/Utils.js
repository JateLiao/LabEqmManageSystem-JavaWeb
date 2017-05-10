/**
 * JS工具包
 */ 

/*去掉字符串首尾空格*/
function trim(str){
      if (str == "" || str == null) {
         return "";
      }
      return str.replace( /(^\s*)|(\s*$)/g, "");
}

/*获取url中的参数*/
function getUrlPram(name)
  {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = decodeURI(window.location.search).substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

/* 字符串判空 */
function isNullOrEmpty(data){
	var val = trim(data);
	if (val == null || val == "") {
		return true;
	}
	return false;
}

/*值是否为‘null’*/
function isNullValue(data){
	var val = trim(data);
	if (val == null || val == "" || val == 'null') {
		return true;
	}
	return false;
}

/*是否为电话号码格式*/
function isMobileTel(data){
	var format =  /^1\d{10}$/;
	
	if (format.test(data)) {
		return true;
	} else {
		return false;
	}
}

/*是否为电子邮件格式*/
function isEmail(data){
	var format =  /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	
	if (format.test(data)) {
		return true;
	} else {
		return false;
	}
}

/*判断登录状态*/
function isLoginUp(basePath, adminId){
	if (isNullOrEmpty(adminId)) {
		alert(basePath + ":" + adminId);
		return false;
	}
	
	$.ajax({ 
		url : basePath + "/admin/IsLoginUp.htm?adminId=" + adminId,
		success : function(data) {
			if (data == 'true') {
				layer.open({
					title : '提示',
					content : '<font color="red" size="2">未登录或登录已过期，请重新登录!</font>',
					skin : 'layui-layer-molv', // 样式类名
					icon : 0,
					btn : [ '确认', '取消' ],
					yes : function(index, layero) {
						window.location.href = basePath + '/View/login.html';
					},
					cancel : function(index) { // 或者使用btn2
						// 按钮【按钮二】的回调
						layer.closeAll();
					}
				}); 
				return true;
			} else{
				return false;
			}
		},
		error : function() {
			return false;
		},
		failure : function() {
			return false;
		}
	});
}

/* 父页面消息提示 */
function parentLayerAlert(content, title, icon, skin){
	var alertObj = parent.layer.alert(content, {
		shadeClose: true,
		// closeBtn : 0,
		title: title,
		skin: skin,
		icon: icon
    }); 
	
	return alertObj;
}