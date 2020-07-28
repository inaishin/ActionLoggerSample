<%@ page import="model.MoveInfo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@page import="dao.MoveDAO"%>  <% //DB検索に関するクラスインポート %>
<%@page import="java.util.List" %>

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
		
			<% MoveDAO moveDao= new MoveDAO(); //MoveDAOインスタンス生成 %>
			<% List<MoveInfo> moveList=moveDao.findAll(); //MoveDAO内findAll(テーブル全検索)をリストに格納%> 
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
</div>
