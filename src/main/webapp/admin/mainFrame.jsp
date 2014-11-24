<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Complex Layout - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="../asset/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../asset/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../asset/easyui/demo/demo.css">
<script type="text/javascript" src="../asset/common/js/jquery-1.9.1.min.js"></script>

<script type="text/javascript"
	src="../asset/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
;(function($){
　　/* 　 var ajax=$.ajax;
　　　 $.ajax=function(s){
	 	 var old = s.success; 
		 s.success = function(data, textStatus, jqXHR) { 
			 //如果失败统一提示
			 if(data!=undefined&&data=='loginTimeout'){//如果有定义success
				$.messager.alert('错误', "登陸超時，請重新登陸!", 'error');
			 }else{
				 old(data, textStatus, jqXHR); 
			 }
	     } 
　　　　　ajax(s);
　　　 } */
 	//重寫jquery中的form
	 var formSubmit=$.fn.form.methods.submit;
	 $.fn.form.methods.submit=function(jq,callBack){
	 	 var _success=callBack.success;
	 	 callBack.success=function(data){
	 		 if(data!=undefined&&data=='loginTimeout'){
	 			 $.messager.alert('错误', "登陸超時，請重新登陸!", 'error');
	 		 }else{
	 			 _success(data);
	 		 }
	 	 }
	 	 formSubmit(jq,callBack);
	 }
 
})(jQuery);

	var type="add";
	function channelAdd(){
		type="add";
		$('#editWin').window('open');
		var nodel=$('#channelTree').tree('getSelected');
		var url='/mis/admin/channel/add.do?chanId='+nodel.id;
		$('#editWin').window('refresh', url);
		$("#editWin").panel({title:'分类维护'});
	}
	function channelEdit(){
		type="edit";
		$('#editWin').window('open');
		var nodel=$('#channelTree').tree('getSelected');
		var url='/mis/admin/channel/get.do?chanId='+nodel.id;
		$('#editWin').window('refresh', url);
		$("#editWin").panel({title:'分类维护'});
	}
	function removeConfirm(remove){
		$.messager.confirm('警告','是否确定要删除记录?',function(r){
			if (r){
				remove();
			 }
		});
	}
	function channelRemove(){
		var selectNode=$('#channelTree').tree('getSelected');
		var pnode=$('#channelTree').tree('getParent', selectNode.target);
		var url='/mis/admin/channel/delete.do?chanId='+selectNode.id+'&chanParentId='+pnode.id;
		$.ajax({
		   type:"get",
		   url:url,
		   success: function(data) {
		    var data = eval('(' + data + ')'); // change the JSON string to javascript object
		    if(null==data.data||data.data==""){
		    	$('#channelTree').tree('reload', pnode.target);
		    }else{
		    	var ppnode=$('#channelTree').tree('getParent', pnode.target);
		    	$('#channelTree').tree('reload', ppnode.target);
		    }
		    
		  }
	
		});
	}
	function onCloseHandler(){
		if(type=="add"){
			addReload();
		}else{
			editReload();
		}
     }
	function editReload(){
		var selectNode = $('#channelTree').tree('getSelected');
		var pnode=$('#channelTree').tree('getParent', selectNode.target);
   	 	$('#channelTree').tree('reload', pnode.target);
	}
	function addReload(){
		var selectNode = $('#channelTree').tree('getSelected');
        var isLeaf = $('#channelTree').tree('isLeaf', selectNode.target);
        if(isLeaf){//如果是叶子节点，则刷新父节点
        	 var pnode=$('#channelTree').tree('getParent', selectNode.target);
        	 $('#channelTree').tree('reload', pnode.target);
        }else{
        	  $('#channelTree').tree('reload',selectNode.target);
        }
	}
	function dbClickHandler(node){
		var isLeaf = $('#channelTree').tree('isLeaf', node.target);
		if(isLeaf){//如果是叶子节点时
			$('#contentPanel').panel("open");
			$('#contentPanel').panel("refresh",'/mis/admin/document/open.do?chanId='+node.id);	
			
		}
	}

	function openChangePassword(){
		$('#changePasswordWin').window('open');
		var url='/mis/login/openChangePassword.do';
		$('#changePasswordWin').window('refresh', url);
		$("#changePasswordWin").panel({title:'修改密碼'});
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'后台管理',split:true" style="height:100px;">
		<div style="height:30px;text-align:right">
			<a href="#" onclick="openChangePassword()">修改密码</a>
			<a href="/mis/login/loginout.do" onclick="loginout()">退出</a>
		</div>
	</div>
	<!-- 左侧分类菜单 start-->
	<div data-options="region:'west',split:true" title="分类"
		style="width:180px;">
		<ul id="channelTree" class="easyui-tree"
			data-options=" 
			url: '/mis/admin/channel/tree.do',
			method: 'post',
			animate: true,
			onDblClick:dbClickHandler,
			onContextMenu: function(e,node){
				e.preventDefault();
				$(this).tree('select',node.target);
				$('#mm').menu('show',{
				left: e.pageX,
				top: e.pageY
				});
        	}
        "></ul>
		<!-- 右键菜单 start-->
		<div id="mm" class="easyui-menu" style="width:120px;">
			<div onclick="channelAdd()" data-options="iconCls:'icon-add'">新增</div>
			<div onclick="channelEdit()" data-options="iconCls:'icon-edit'">编辑</div>
			<div onclick="removeConfirm(channelRemove)" data-options="iconCls:'icon-remove'">删除</div>
		</div>
		<!-- 右键菜单 end-->
	</div>
	<!-- 维护弹出窗口 -->
	<div id="editWin" class="easyui-window" title="Basic Window" data-options="iconCls:'icon-save',modal:true,closed:true,minimizable:false,collapsible:false,onClose:onCloseHandler" style="width:400px;height:300px;padding:10px;">
	</div>
	<!-- 维护弹出窗口 -->
	<div id="changePasswordWin" class="easyui-window" title="Basic Window" data-options="iconCls:'icon-save',modal:true,closed:true,minimizable:false,collapsible:false" style="width:400px;height:210px;padding:10px;">
	</div>
	
	<!-- 左侧分类菜单 end-->
	
	<!-- 主体 start-->
	<div id="contentPanel" class="easyui-panel" 
		data-options="region:'center',title:'文档',iconCls:'icon-ok'">
	</div>
	<!-- 主体 end-->
</body>
</html>