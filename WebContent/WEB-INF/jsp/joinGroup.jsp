<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.GroupInfo"%>
<%@page import="dao.GroupDAO"%>  <% //DB検索に関するクラスインポート %>
<%@page import="java.util.List" %>
<%String txt=(String)application.getAttribute("memberlog"); %> <%//アプリケーションスコープの取得 %>
    
 <%//グループ一覧/参加ページ %>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">グループ一覧</h1>
	</div>
	
	<dvi>
		<table class="table table-striped table-sm">
			<thead>
				<tr>
					<th>グループＩＤ<th>
					<th>グループ名</th>
				</tr>
			</thead>
		
			<tbody>
				<%//活動記録一覧 %>
				<% GroupDAO groupdao= new GroupDAO(); //GroupDAOインスタンス生成 %>
				<% List<GroupInfo> groupList=groupdao.findAll(); //GroupDAO内findAll(テーブル全検索)をリストに格納%> 
				<% 
					for(GroupInfo group : groupList){ //リスト内に格納されてるGroupInfoクラスを返す
				%>
					<%//各クラスごとに格納されてる要素取得 %>
					<tr>
						<td><%=group.getGroupid()%></td>
						<td><%=group.getGroupname()%></td>
					</tr>
				<% 		
					}
				%>
			</tbody>
		</table>
	
	
	
	<%//登録 %>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">グループ参加</h1>
	</div>
		
	<div class="table-responsive">
		<form action="/WebSystem/addgroupmember" method="post">
			<label>グループID：</label>
			<input type="text" name="groupid">
		
			<input type="submit" value="参加">
		</form>
	</dvi>
	
	<div class="table-responsive">
		<p>ログ：<%=txt %></p>
	</div>
	
	
	

</body>
</html>