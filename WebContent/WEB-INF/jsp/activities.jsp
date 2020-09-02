<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="model.MoveInfo"%>	
<%@page import="dao.MoveDAO"%>  <% //DB検索に関するクラスインポート %>
<%@page import="java.util.List" %>

<% String userid=(String)session.getAttribute("userid"); //セッションスコープからインスタンス取得 %>
<% String day=(String)session.getAttribute("day"); //セッションスコープからインスタンス取得 %>
<% String place=(String)session.getAttribute("place"); //セッションスコープからインスタンス取得 %>


<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">行動記録</h1>
</div>

<div class="table-responsive">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>日付</th>
				<th>時刻</th>
				<th>場所</th>
				<th>理由</th>
				<th>備考</th>
			</tr>
		</thead>
		<tbody>
			<%//活動記録一覧 %>
			<% MoveDAO moveDao= new MoveDAO(); //MoveDAOインスタンス生成 %>
			<% List<MoveInfo> moveList=moveDao.findAll(userid); //MoveDAO内findAll(テーブル全検索)をリストに格納%> 
			<% 
				for(MoveInfo move : moveList){ //リスト内に格納されてるMoveInfoクラスを返す
			%>
				<%//各クラスごとに格納されてる要素取得 %>
				<tr>
					<td><%=move.getDay()%></td>
					<td><%=move.getStarttime()%> ~ <%=move.getFinishtime()%></td>
					<td><%=move.getPlace()%></td>
					<td><%=move.getReason()%></td>
					<td><%=move.getOther()%></td>
				</tr>
			<% 		
				}
			%>
		</tbody>
	</table>
	
	
	
<%//検索 %>
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">検索</h1>
</div>

<div class="table-responsive">
	<form action="/WebSystem/searchmove" method="post">
		<label>日付検索：</label>
		<input type="text" name="day">
		<label>場所検索：</label>
		<input type="text" name="place">
		
		<input type="submit" value="検索">
	</form>
	
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>日付</th>
				<th>時刻</th>
				<th>場所</th>
				<th>理由</th>
				<th>備考</th>
			</tr>
		</thead>
		
		<tbody>
			<%//検索結果一覧 %> 
			<% List<MoveInfo> searchList=moveDao.Search(userid,day,place); //MoveDAO内serchList(検索)をリストに格納%> 
			<p>検索内容 -> 日付：<%=day%>　　場所：<%=place%></p>
			<% 
				for(MoveInfo move : searchList){ //リスト内に格納されてるMoveInfoクラスを返す
			%>
				<%//各クラスごとに格納されてる要素取得 %>
				
				<tr>
					<td><%=move.getDay()%></td>
					<td><%=move.getStarttime()%> ~ <%=move.getFinishtime()%></td>
					<td><%=move.getPlace()%></td>
					<td><%=move.getReason()%></td>
					<td><%=move.getOther()%></td>
				</tr>
			<% 		
				}
			%>
		</tbody>
	</table>
</div>
	
	
</div>
