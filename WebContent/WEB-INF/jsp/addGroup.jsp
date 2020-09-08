<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<%String txt=(String)application.getAttribute("log"); %> <%//アプリケーションスコープの取得 %>

<!DOCTYPE html>
<html>
<head>
<title>新規グループ</title>
</head>
<body>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">新規グループ</h1>
	</div>
	
	<div class="table-responsive"> 
		<form action="/WebSystem/addgroup" method="POST">
			<div>
				<label>グループID：</label>
				<input type="text" name="groupid" required>
			</div>
		
			<div>
				<label>グループ名：</label>
				<input type="text" name="groupname" required>
			</div>
			
			<input type="submit" value="グループ作成">
		</form>
		
	</div>
	
	<div class="table-responsive">
		<%=txt %>
	</div>
	
</body>
</html>