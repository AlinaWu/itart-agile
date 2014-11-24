<%@include file="../public/common/common.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>imageBrowse.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<script type="text/javascript">
	  	function funCallback(funcNum,fileUrl){
	  		var parentWindow = ( window.parent == window ) ? window.opener : window.parent;
	  		parentWindow.CKEDITOR.tools.callFunction(funcNum, fileUrl);
	  		window.close();
	  	}
  	</script>
  	<c:forEach var="image" items="${images}" varStatus="status">   
		<img src="${path}/${image.href}" border="0" width="160" height="150" title="${image.title}" onclick="funCallback(${callback},'${path}/${image.href}')"/>
	</c:forEach> 
  </body>
</html:html>
