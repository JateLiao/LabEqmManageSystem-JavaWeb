/**
 * 批量导入导出设备
 */ 

function getFile(obj, inputName) {
	var file_name = $(obj).val();
	$("input[name='" + inputName + "']").val(file_name);
}


/*导入设备*/
function importEquipment() { 
	var fileName = $('#copyFile').val();
	
	if (fileName == "") {
		parent.layer.alert('请选择要导入的文件！',
			{
				icon: 0,
				skin: 'layui-layer-molv' //样式类名 
			});

		return; 
	} 
	
	var extensionName = trim(getFileExtensionName(fileName).toLowerCase());
	if (extensionName != '.xls' && extensionName != '.xlsx') {
		parentLayerAlert('请选择微软Office制作的Excel文件（<br><font color="red">*.xls，*.xlsx</font>格式）进行导入！', 
				'提示', 0,  'layui-layer-molv');
			return; 
		}

	$('#excelForm').ajaxSubmit({
		url : '../../equipments/importEquipment.htm',
		type : 'post',
		beforeSend : function() { 
			parent.layer.msg('<font color="red" size="2">导入中，请稍后</font>', 
					{
						icon: 16,
						time: 2000000, //2000s后自动关闭
						shadeClose: false,
						shade: [0.8, '#848484']
					}
			);  
		},
		success : function(data) {
			if (data == 'success') {
				parent.layer.open({
					title: '提示',
					content: '设备导入成功！',
					btn:['确认'],
					closeBtn: 0,
					yes: function(){
						parent.layer.closeAll();
					}
				});
				return;
			} else { 
				parentLayerAlert('设备导入失败，请核对是否按照规范进行数据录入和导入！', '提示', 2, 'layui-layer-molv'); 
				return;
			}
		},
		failure : function() { 
			parentLayerAlert('设备导入失败！', '提示', 2, 'layui-layer-molv');
		},
		error : function() { 
			parentLayerAlert('设备导入失败！', '提示', 2, 'layui-layer-molv');
		}
	});
}

/* 获取路径全名 */
function getFullPath(obj) {
	if (obj) {
		// Internet Explorer
		if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
			obj.select();
			return document.selection.createRange().text;
		}
		
		// Firefox
		else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
			if (obj.files) {
				return obj.files.item(0).getAsDataURL();
			}
			return obj.value;
		}
		return obj.value;
	}
}

/*获取文件后缀名*/
function getFileExtensionName(filename){  
	var index = filename.lastIndexOf(".");
	var length = filename.length;
	var extensionName = filename.substring(index, length); //后缀名  
	
	return extensionName;
} 