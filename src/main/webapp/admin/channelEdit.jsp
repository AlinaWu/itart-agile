<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Form - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css"
	href="../asset/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		href="../asset/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../asset/easyui/demo.css">
	<script type="text/javascript" src="../asset/easyui/jquery.min.js"></script>
	<script type="text/javascript"
		src="../asset/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		 
	</script>
</head>
<body>
		<div>
		    <form id="ff" method="post">
		    	<input name="chanId" type="hidden" value="${data.chanId}"/>
		    	<input name="chanParentId"  type="hidden" value="${data.chanParentId}"/>
		    	<table>
		    		<tr>
		    			<td>分类名:</td>
		    			<td><input class="easyui-validatebox" type="text" value="${data.chanName}" name="chanName" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>排序:</td>
		    			<td><input class="easyui-validatebox" type="text" value="${data.chanSeq}" name="chanSeq" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>状态:</td>
		    			<td><input class="easyui-validatebox" type="text" value="${data.chanStatus}" name="chanStatus" data-options="required:true"></input></td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
	    </div>
	<script>
		function submitForm(){
			    $('#ff').form('submit', {
			    url:'/mis/channel/save.do',
			    success: function(data){
			      	$('#ff').form('load',data);
				   	$.messager.alert('Info', '操作成功!', 'info');
				   }
			    });
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>