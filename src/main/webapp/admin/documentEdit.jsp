<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Form - jQuery EasyUI Demo</title>
</head>
<body>
<div  class="easyui-panel" 
style="padding:5px;background:#fafafa;">
	<form id="docEdit" method="post">
	  	<input id="docId" name="docId" type="hidden" value="${doc.docId}"/>
		<input name="docChannel" type="hidden" hidden="hidden" value="${doc.docChannel}"/>
		<table>
			<tr>
				<td>标题:</td>
				<td><input id="docTitle" class="easyui-validatebox" type="text" style="width: 300px" value="${doc.docTitle}" name="docTitle" data-options="required:true"></input></td>
			</tr>
			<tr>
				<td colspan="2">内容:</td>
				
			</tr>
			<tr>
				<td colspan="2"><textarea name="docContent" style="height:800px;">${doc.docContent}</textarea></td>
				 <script src="/mis/asset/ckeditor/ckeditor.js"></script>
				
			</tr>
			
	 	</table>
	 </form>
</div>
	
<div style="text-align:center;padding:5px">
	<a href="javascript:void(0)" class="easyui-linkbutton" onClick="docSubmitForm()">提交</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onClick="docClearForm()">清空</a>
</div>

<div id="attachList"  class="easyui-panel" title="附件"
style="padding:5px;background:#fafafa;">
	<div id="attachPanel" style="display:none" class="easyui-panel" 
	style="padding:0px;background:#fafafa;">
		<form id="docAttach" method="post" enctype="multipart/form-data" >
			<input id="attachDocId" name="docId" type="hidden" value="${doc.docId}"/>
			<table>
				<tr>
					<td>添加附件:</td>
					<td><input type="file" style="width: 300px"  name="file"/></td>
					<td><a href="javascript:void(0)" class="easyui-linkbutton" onClick="uploadForm()">上传</a></td>
				</tr>
		 	</table>
		 </form>
	</div>
	<table id="attachTable" class="easyui-datagrid" 
			data-options="singleSelect:true,method:'get',showHeader:false,idField:'attaId'">
		<thead>
			<tr>
				<th data-options="field:'attaName'">名称</th>
				<th data-options="field:'attaId',width:180,formatter:  rowformater">操作</th>
				
			</tr>
		</thead>
	</table>
</div>
	
	 <script type="text/javascript">
		function rowformater(value,row,index)
		{
			var id="'"+row.attaId+"'";
			return '<a href="javascript:void(0)"  onclick="attachDelete(' +id +","+index+')">删除</a>'
		}
		function attachDelete(id,index){
			//$.messager.progress();	// display the progress bar
			var url='/mis/admin/attach/delete.do?attaId='+id;
			$.ajax({
			   type:"post",
			   url:url,
			   success: function(data) {
				$.messager.progress('close');
				//页面删除
				$('#attachTable').datagrid('selectRecord',id);//选中
				var row = $('#attachTable').datagrid('getSelected');
				index=$('#attachTable').datagrid('getRowIndex',row);
				$('#attachTable').datagrid('deleteRow',index);
			  }
		
			});
		}
		$(function(){ 
			$("#attachPanel").hide();
			var docId="${doc.docId}";
			if(""!=docId){//新增
				init(docId);
			}else{
				CKEDITOR.replace( 'docContent' );
			}
		}); 
		function init(docId){
			$.ajax({
				   type:"post",
				   data:'docId='+docId,
				   url:'/mis/admin/document/get.do',
				   success: function(data) {
						docLoad(data);
						CKEDITOR.replace( 'docContent' );
				  }
				});	
		}
		function docLoad(data){
			data = eval('(' + data + ')');  
	      	$('#docEdit').form('load',data);
	      	$("#attachDocId").val(data.docId);
	     	var attachments = data.attachments;  
		    $('#attachTable').datagrid({
		    	data:attachments
		    });
		    $("#attachPanel").show();
		  
		}
		function docSubmitForm(){
			    $('#docEdit').form('submit', {
			    url:'/mis/admin/document/save.do',
			    success: function(data){
			      	docLoad(data);
				   	$.messager.alert('Info', '操作成功!', 'info');
				   }
			    });
		}
		function uploadForm(){
		    $('#docAttach').form('submit', {
		    url:'/mis/admin/attach/attaUpload.do',
		    success: function(data){
		    	docLoad(data)
			   }
		    });
		}
		function docClearForm(){
			$('#docEdit').form('clear');
		}
	</script>
</body>
</html>