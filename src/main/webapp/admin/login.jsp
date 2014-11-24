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
<script type="text/javascript">
  function changeImg() {
   var imgSrc = $("#imgObj");
   var src = imgSrc.attr("src");
   imgSrc.attr("src", chgUrl(src));
  }
  //时间戳  
  //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳  
  function chgUrl(url) {
   var timestamp = (new Date()).valueOf();
   url = url.substring(0, 18);
   if ((url.indexOf("&") >= 0)) {
    url = url + "×tamp=" + timestamp;
   } else {
    url = url + "?timestamp=" + timestamp;
   }
   return url;
  }
  function docSubmitForm(){
	  $('#loginForm').form('submit', {
	  url:'/mis/login/check.do',
	  success: function(data){
		data = eval('(' + data + ')');
		if(!data.success){
			changeImg();
			$.messager.alert('错误',data.text, 'error');
		}else{
			//window.open ('/mis/login/mainFrame.do');
			 window.location.href="/mis/login/mainFrame.do";  
		//	location.href("/mis/login/mainFrame.do")
		}
	   
	  }
	  });
	}
 </script>
<style type="text/css">
body {TEXT-ALIGN: center;}
</style>
</head>
<body >
	<div  class="easyui-panel"  title="用户登陆" 
	style="padding:5px;background:#fafafa;width:390px;height:150px">
		<form id="loginForm" method="post">
			<table>
				<tr>
					<td>用户名:</td>
					<td><input id="userName" class="easyui-validatebox" type="text" style="width: 150px" name="userName" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;密码:</td>
					<td><input id="userPassword" class="easyui-validatebox" type="password" style="width: 150px"  name="userPassword" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>验证码:</td>
					<td><input id="code" class="easyui-validatebox" type="text" style="width: 150px"  name="code" data-options="required:true"></input>	
						<img id="imgObj" alt="验证码" src="/mis/login/code.do" />
   						<a href="#" onclick="changeImg()">换一张</a>
   					</td>
				</tr>
				
		 	</table>
		 </form>
	</div>
	<div style="text-align:center;padding:5px;width:390px;height:150px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onClick="docSubmitForm()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onClick="docClearForm()">清空</a>
	</div>
</body>
</html>