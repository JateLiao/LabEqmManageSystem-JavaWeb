/**
 * 首页
 */ 
$(function() {
//	if (!isLoginUp(basePath, adminId)) {
//		
//	}
	if (adminId == "") {
		layer.open({
			title: '提示',
			icon: 0,
			skin: 'layui-layer-molv', //样式类名
			content: '<font color="red" size="2">未登录或登录已过期，请重新登录!</font>',
			btn: ['确定'],
			closeBtn: 0,
			yes: function(){
				layer.closeAll();
				window.location.href = basePath + '/View/login.html';
			}
		});
		return;
	}
	
	initTopInfo();
	showMenu(adminLevel);
});


/**
 * 退出登录
 */
function loginOut(){ 
	layer.open({
		title: '提示',
		skin: 'layui-layer-molv', //样式类名
		icon: 3,
	    content: '<font size="2">您确定要退出登录吗？</font>',
	    btn: ['确定', '取消'],
	    yes: function(){
	    	layer.closeAll();
	    	$.ajax({
				type : "POST",
				url : basePath + "/admin/LoginOut.htm?adminId=" + adminId,
				success : function(data) {
					if (data != 'success') {
						layer.alert('<font color="red" size="2">退出失败，请重试!</font>', {
							skin: 'layui-layer-molv', //样式类名
				            icon: 2
				        }); 
						return;
					}
					window.location.href = basePath + "/View/login.html";
				},
				error : function() {
					layer.alert('<font color="red" size="2">退出异常，请重新登录!</font>', {
						skin: 'layui-layer-molv', //样式类名
			            icon: 2
			        }); 
				},
				failure : function() {
					layer.alert('<font color="red" size="2">退出异常，请重新登录!</font>', {
						skin: 'layui-layer-molv', //样式类名
			            icon: 2
			        }); 
				}
	    	});
	    },
	    cancel: function(index){ //或者使用btn2
	    	layer.closeAll(); 
	    }
	}); 
} 

/*管理员列表*/
function showAdminList(){
	layer.tab({
		area: ['1000px', '505px'],
		maxmin: true, 
		tab: [{
			title : '管理员列表',
			content : '<iframe src="'+ basePath + '/View/admin/adminList.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>' 
		},{
			title : '管理员注册审核',
			content : '<iframe src="'+ basePath + '/View/admin/adminRegistList.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>' 
		}]
	}); 
}

/*设备列表*/
function showAllEqmList(){ 
//	$('#showEqmList').attr("href", basePath + '/View/equipment/allEqmList.jsp');
//	$(window.frames["showContent"].document).find("#managerText").removeAttr("disabled");
	
	$('#showContent').attr("src",basePath + '/View/equipment/allEqmList.jsp');  
}

/*我的托管设备*/
function showMyEqmList(){
//	$('#myEqmList').attr("href", encodeURI(basePath + '/View/equipment/myEqmList.jsp?managerId=' 
//			+ adminId + '&managerName=' + adminName));
	
	$('#showContent').attr("src", encodeURI(basePath + '/View/equipment/myEqmList.jsp?managerId=' 
			+ adminId + '&managerName=' + adminName));
}

//借出设备
function lendOutWindow(){
	layer.tab({
		area: ['1000px', '515px'],
		maxmin: true, 
		tab: [{
			title : '设备借出',
			content : '<iframe src="'+ basePath + '/View/equipment/lendout/eqmLendOut.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>' 
		},{
			title : '我的借出',
			content : '<iframe src="'+ basePath + '/View/equipment/lendout/myLendOut.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>'
		}]
	});
}

//报修设备
function repairWindow(){
	layer.tab({
		area: ['1000px', '515px'],
		maxmin: true, 
		tab: [{
			title : '设备报修',
			content : '<iframe src="'+ basePath + '/View/equipment/repair/eqmRepair.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>' 
		},{
			title: '我的报修',
			content : '<iframe src="'+ basePath + '/View/equipment/repair/myRepair.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true"></iframe>'
		}]
	});
}

//报废设备
function scrapWindow(){
	layer.tab({
		area: ['1000px', '515px'],
		maxmin: true, 
		tab: [{
			title : '设备报废',
			content : '<iframe src="'+ basePath + '/View/equipment/scrap/eqmScrap.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>' 
		},{
			title: '我的报废',
			content : '<iframe src="'+ basePath + '/View/equipment/scrap/myScrap.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true"></iframe>'
		}]
	});
}

/* 借出 / 归还 / 报修 */
function lendOutAndReturnAndRepair_user(){
	layer.tab({
		area: ['1000px', '515px'],
		maxmin: true, 
		tab: [{
			title : '设备处理',
			content : '<iframe src="'+ basePath + '/View/equipment/eqmOperate.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>' 
		},{
			title : '我的借出',
			content : '<iframe src="'+ basePath + '/View/equipment/lendout/myLendOut.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>'
		},{
			title: '我的报修',
			content : '<iframe src="'+ basePath + '/View/equipment/repair/myRepair.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true"></iframe>'
		},{
			title: '我的报废',
			content : '<iframe src="'+ basePath + '/View/equipment/scrap/myScrap.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true"></iframe>'
		}]
	});
}
function lendOutAndReturnAndRepair_admin(){
	layer.tab({
		area: ['1000px', '515px'],
		maxmin: true, 
		tab: [/*{
			title : '设备处理',
			content : '<iframe src="'+ basePath + '/View/equipment/eqmOperate.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true"></iframe>'
		},*/{
			title : '借出处理',
			content : '<iframe src="'+ basePath + '/View/equipment/lendout/lendOutHandle.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>'
		},{
			title : '报修处理',
			content : '<iframe src="'+ basePath + '/View/equipment/repair/eqmRepairHandle.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>'
		},{
			title : '报废处理',
			content : '<iframe src="'+ basePath + '/View/equipment/scrap/eqmScrapHandle.jsp"'
			+ 'width="1000" height="455" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
			+ '</iframe>'
		}]
	});
}

/*管理员个人信息*/
function showAdminInfo(){
	layer.tab({
		  area: ['650px', '350px'],  
		  shadeClose: true,
		  tab : [ {
				title : '个人信息',
				content : '<iframe src="'+ basePath + '/View/admin/infoShow.html"'
				+ 'width="650" height="300" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
				+ '</iframe>' 
			}, {
				title : '信息修改',
				content : '<iframe src="'+ basePath + '/View/admin/modifyInfo.html"'
				+ 'width="650" height="300" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
				+ '</iframe>'
			}, {
				title : '修改密码',
				content : '<iframe src="'+ basePath + '/View/admin/modifyPassword.html"'
				+ 'width="650" height="300" frameborder="no" border="0"  scrolling="no" allowtransparency="true">'
				+ '</iframe>'
			}]
		});
}

/* 初始化首页顶部信息栏 */
function initTopInfo(){
	$('#showName').text(adminName);
	
	switch (adminLevel) {
	case '0': case 1: $('#level').text('学生'); break;
	case '1': case 1: $('#level').text('普通教职工'); break;
	case '2': case 2: $('#level').text('实验室管理员'); break;
	case '3': case 2: $('#level').text('设备管理员'); break;
	case '99': case 99: $('#level').text('超级管理员'); break;
	default:
		break;
	} 

	$('#level').mouseover(function(){
		layer.closeAll();
		$('#div_permissionInfo').show();
		layer.open({
			  title: false, //不显示标题 
			  type: 1, 
			  shadeClose: true,
			  area: ['510px', '270px'],
			  content: $('.div_permissionInfo'), //捕获的元素
		});
	}); 
}

/*关于本站*/
function aboutSystem(){
	layer.open({
		title: '关于本站',  
		skin: 'layui-layer-molv', //样式类名
		area: ['380px', '220px'], //宽高				
		shadeClose: true, //开启遮罩关闭  
		btn: 0,
		content: '系统简介：该系统主要提供设备管理功能。<br>'
			+ '开发成员：廖仕杰<br>测试人员：当然还是我，不然还能有谁... ...<br>'
			+ '联系方式：13228170623<br>邮箱：374682617@qq.com<br>'
	});
}

/*菜单显示控制*/
function showMenu(level){
	switch (level) {
	case '0':
	case '1':
		$('#d1').show();
		$('#d5').show();
		$('#d6').show();
		$('#d7').show();
		$('#d8').show();
		$('#d10').show();
		$('#d11').show();
		$('#d12').show();
		$('#d13').show();
		$('#d14').show();
		$('#d15').show();
		break;
	case '2':
		$('#d1').show();
		$('#d2').show();
		$('#d5').show();
		$('#d6').show();
		$('#d7').show();
		$('#d8').show();
		$('#d10').show();
		$('#d11').show();
		$('#d12').show();
		$('#d13').show();
		$('#d14').show();
		break;
	case '3':
		$('#d1').show();
		$('#d2').show();
		$('#d4').show();
		$('#d8').show();
		$('#d10').show();
		$('#d11').show();
		$('#d12').show();
		$('#d13').show();
		$('#d14').show();
		$('#d15').show();
		$('#d16').show();
		break;
	case '99':
		$('#d1').show();
		$('#d5').show();
		$('#d6').show();
		$('#d7').show();
		$('#d8').show();
		$('#d9').show();
		$('#d10').show();
		$('#d11').show();
		$('#d12').show();
		$('#d13').show();
		$('#d14').show();
		break;
	}
}