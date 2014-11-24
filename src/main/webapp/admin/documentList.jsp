<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic DataGrid - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="../asset/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../asset/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../asset/easyui/demo.css">
<script type="text/javascript" src="../asset/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="../asset/easyui/jquery.easyui.min.js"></script>
	
</head>
<body>
	<script type="text/javascript">
		function documentAdd(){
			var url='/mis/admin/document/openEdit.do?docChannel=${docChannel}';
			openDocEdit(url,'新增文档');
		}
		function openDocEdit(url,title){
			$('#docEditWin').window("open");
			$('#docEditWin').window("refresh",url);	
			
			/* $('#docEditWin').window({
			      width:600,
			      height:400,
			      href:url,
			      title:title,
			      cache:false,
			      onClose:onDocCloseHandler,
			      modal:true
			      }); */
		}

		function documentEdit(){
			var row = $('#docList').datagrid('getSelected');
			if (!row){
				$.messager.alert('提示', '请选择记录', 'info');
				return;
			}
			var url='/mis/admin/document/openEdit.do?docChannel=${docChannel}&docId='+row.docId;
			openDocEdit(url,"文档维护");
		}
		
		function onDocCloseHandler(){
			 //$('#docList').datagrid('load',{}); 
			$('#docList').datagrid('reload');
	     }
		function documentRemove(){
			var row = $('#docList').datagrid('getSelected');
			$.messager.progress();	// display the progress bar
			var url='/mis/admin/document/delete.do?docId='+row.docId;
			$.ajax({
			   type:"post",
			   url:url,
			   success: function(data) {
				$('#docList').datagrid('reload');
				$.messager.progress('close');
				// alert('删除成功!');
			  }
			});
		}
		function documentRemoveConfirm(){
			var row = $('#docList').datagrid('getSelected');
			if (!row){
				$.messager.alert('提示', '请选择记录', 'info');
				return;
			}
			$.messager.confirm('警告','是否确定要删除记录?',function(r){
				if (r){
					documentRemove();
				 }
			});

			
		}
		//文档列表右键菜单 
		function onRowContextMenu(e, rowIndex, rowData){
		   $('#docList').datagrid('selectRow',rowIndex);
		   e.preventDefault();
		    $('#docListRowContextMenu').menu('show', {
		        left:e.pageX,
		        top:e.pageY
		    });		
		}
	</script>
	<table id="docList" fit="true"  class="easyui-datagrid"   toolbar="#toolbar" rownumbers="true"
			data-options="onDblClickCell:documentEdit,singleSelect:true,pagination:true,url:'/mis/admin/document/list.do?chanId=${docChannel}',method:'post',onRowContextMenu: onRowContextMenu">
		<thead>
			<tr>
				<th data-options="field:'docTitle',width:1000">标题</th>
				<th data-options="field:'dateCreated',width:1000">创建时间</th>
				<!-- th data-options="field:'docStatus',width:100">状态</th -->
				<!--th data-options="field:'docId',width:80">主键</th -->
				<!--th data-options="field:'docChannel',width:60,align:'center'">分类</th -->
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="documentAdd()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="documentEdit()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="documentRemoveConfirm()">删除</a>
	</div>
	<!-- 文档列表右键菜单 -->
	<div id="docListRowContextMenu" class="easyui-menu" style="width:120px;">
		<div onClick="documentAdd()" data-options="iconCls:'icon-add'">新增</div>
		<div onClick="documentEdit()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onClick="documentRemoveConfirm()" data-options="iconCls:'icon-remove'">删除</div>
	</div>
	<!-- 维护弹出窗口 -->
	
	 <div id="docEditWin" class="easyui-window" title="My Window" style="width:1000px;height:600px"
    data-options="iconCls:'icon-save',modal:true,closed:true,minimizable:false,collapsible:false,title:'文档明细',onClose:onDocCloseHandler">
    Window Content
    </div>
</body>
</html>