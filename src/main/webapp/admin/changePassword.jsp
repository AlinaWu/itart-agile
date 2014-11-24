<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Complex Layout - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="../asset/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../asset/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../asset/easyui/demo.css">
<script type="text/javascript" src="../asset/common/js/jquery-1.9.1.min.js"></script>

<script type="text/javascript"
	src="../asset/easyui/jquery.easyui.min.js"></script>

<style type="text/css">
body {TEXT-ALIGN: center;}
</style>
</head>
<body >
	<div  class="easyui-panel" 
	style="padding:5px;background:#fafafa;">
		<form id="changePasswordForm" method="post">
			<input name="userName" type="hidden" value="${userName}"/>
			<table>
				<tr>
					<td>原始密碼:</td>
					<td><input id="orgPassword" class="easyui-validatebox" type="text" style="width: 150px" name="orgPassword" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;新密碼:</td>
					<td><input id="newPassword" class="easyui-validatebox" type="password" style="width: 150px"  name="newPassword" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>重新輸入:</td>
					<td><input id="confirmPassword" class="easyui-validatebox" type="password" style="width: 150px"  name="confirmPassword" data-options="required:true"></input></td>
				</tr>
				
		 	</table>
		 </form>
	</div>
	<div style="text-align:center;padding:5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" onClick="changePasswordSubmit()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onClick="$('#changePasswordForm').form('clear');">清空</a>
	</div>
	<script type="text/javascript">
	function changePasswordSubmit(){
	    $('#changePasswordForm').form('submit', {
	    url:'/mis/login/changePassword.do',
	    success: function(data){
	    	data = eval('(' + data + ')');
			if(!data.success){
				$.messager.alert('错误',data.text, 'error');
			}else{
				$('#changePasswordForm').form('load',data.data);
			   	$.messager.alert('提示', '操作成功!', 'info');
			}
		   }
	    });
}
	</script>
</body>
</html>