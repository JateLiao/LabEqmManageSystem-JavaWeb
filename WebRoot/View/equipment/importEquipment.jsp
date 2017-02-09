<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath = request.getContextPath();
%>
<script type="text/javascript">
	var basePath = '<%=basePath %>';
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量导入设备</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/Resource/css/button.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/Resource/frameworks/layer2.1/skin/layer.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/Resource/css/equipment/importOrExport.css" />
<script type="text/javascript" src="<%=basePath %>/Resource/js/jquery-1.10.2.js"></script> 
<script type="text/javascript" src="<%=basePath %>/Resource/js/jquery.form.js"></script> 
<script type="text/javascript" src="<%=basePath %>/Resource/frameworks/layer2.1/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>/Resource/js/Utils.js"></script>
<script type="text/javascript" src="<%=basePath %>/Resource/js/equipment/importOrExport.js"></script>
</head>
<body>
	<form id="excelForm" name="excelForm" action="../../equipments/importEquipment.htm" method="post" enctype="multipart/form-data">
		<div class="main">
	        <div class="box">
	            <input id="copyFile" type="text" name="copyFile"  class="textbox" />
	            <a href="javascript:void(0);"  class="link">浏览</a>
	            <input type="file" name="myfiles" class="uploadFile" onchange="getFile(this,'copyFile');" />
	        </div>
	        <div class="button_import">
	        	<div style="margin-top: 30px; width:150px; margin-right: 40px; float: right;">
					<!-- <a href="#" class="button button-glow button-rounded button-raised button-primary" >开始导入</a>  -->  
	            	<input type="button" class="button button-glow button-rounded button-raised button-primary" 
	            		value="开始导入" onclick="importEquipment();" />     	
	        	</div>
	        </div> 
	        <div class="helper">
	        	注意：为保证导入成功，请严格按照附件所给样本表格的格式录入数据！
	        	<a href="<%=basePath %>/equipments/downloadDocument.htm"  >附件：标准表格.xls</a> 
	        </div>
		</div>
	</form>   
</body>
</html>