<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.GroupMasterInfo"%>	
<%@page import="dao.GroupDAO"%>  <% //DB検索に関するクラスインポート %>
<%@page import="java.util.List" %> 

<% String masterid=(String)session.getAttribute("userid"); //セッションスコープからインスタンス取得 %>
<% String groupday=(String)session.getAttribute("groupday"); %>
<% String groupplace=(String)session.getAttribute("groupplace"); %>
 
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%//グループ一覧 %>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">管理グループ一覧</h1>
	</div>
	
	<div class="table-responsive">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>チーム名</th>
				<th>名前</th>
				<th>日付</th>
				<th>時刻</th>
				<th>場所</th>
				<th>理由</th>
				<th>備考</th>
			</tr>
		</thead>
		<tbody>
		
			<%//グループ内一覧 %>
			<% GroupDAO groupDao= new GroupDAO(); //GroupDAOインスタンス生成 %>
			<% List<GroupMasterInfo> groupList=groupDao.GroupMaster(masterid); //MoveDAO内findAll(テーブル全検索)をリストに格納%> 
			<% 
				for(GroupMasterInfo group : groupList){ //リスト内に格納されてるMoveInfoクラスを返す
			%>
			
				<%//各クラスごとに格納されてる要素取得 %>
				<tr>
				 	<td><%=group.getTeamname() %></td>
				 	<td><%=group.getName() %></td>
					<td><%=group.getDay()%></td>
					<td><%=group.getStime()%> ~ <%=group.getFtime()%></td>
					<td><%=group.getPlace()%></td>
					<td><%=group.getReason()%></td>
					<td><%=group.getOther()%></td>
				</tr>
			<% 		
				}
			%>
		</tbody>
	</table>
	</div>
	
	
	
	<%//検索 %>
	<div
	　　class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">検索</h1>
	</div>

	<div class="table-responsive">
		<form action="/WebSystem/searchgroup" method="post">
			<label>日付検索：</label>
			<input type="text" name="day">
			<label>場所検索：</label>
			<input type="text" name="place">
		
			<input type="submit" value="検索">
	</form>
	
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>チーム名</th>
				<th>名前</th>
				<th>日付</th>
				<th>時刻</th>
				<th>場所</th>
				<th>理由</th>
				<th>備考</th>
			</tr>
		</thead>
		
		<tbody>
			<%//グループ内一覧 %>
			<% List<GroupMasterInfo> searchList=groupDao.SearchGroupMaster(masterid, groupday, groupplace); //MoveDAO内findAll(テーブル全検索)をリストに格納%> 
			<p>検索内容 -> 日付：<%=groupday%>　　場所：<%=groupplace%></p>
			<% 
				for(GroupMasterInfo group : searchList){ //リスト内に格納されてるMoveInfoクラスを返す
			%>
				<%//各クラスごとに格納されてる要素取得 %>
				<tr>
				 	<td><%=group.getTeamname() %></td>
				 	<td><%=group.getName() %></td>
					<td><%=group.getDay()%></td>
					<td><%=group.getStime()%> ~ <%=group.getFtime()%></td>
					<td><%=group.getPlace()%></td>
					<td><%=group.getReason()%></td>
					<td><%=group.getOther()%></td>
				</tr>
			<% 	 		
				}
			%>
		</tbody>
	</table>
	
	</div>
</body>
</html>