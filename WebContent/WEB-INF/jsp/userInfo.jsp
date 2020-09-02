<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@page import="model.UserInfo"%>	
<%@page import="dao.UserDAO"%>  <% //DB検索に関するクラスインポート %>
<%@page import="java.util.List" %>

<% String userid=(String)session.getAttribute("userid"); //セッションスコープからインスタンス取得 %>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>

<body>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">プロフィール確認</h1>
	</div>
	
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>ユーザーID</th>
				<th>氏名</th>
				<th>住所</th>
				<th>電話番号</th>
				<th>メールアドレス</th>
			</tr>
		</thead>
		
		<tbody>
			<%//活動記録一覧 %>
			<% UserDAO userdao= new UserDAO(); //MoveDAOインスタンス生成 %>
			<% List<UserInfo> userList=userdao.find(userid); //MoveDAO内findAll(テーブル全検索)をリストに格納%> 
			<% 
				for(UserInfo user : userList){ //リスト内に格納されてるMoveInfoクラスを返す
			%>
				<%//各クラスごとに格納されてる要素取得 %>
				<tr>
					<td><%=user.getUserId()%></td>
					<td><%=user.getName()%></td>
					<td><%=user.getAddress()%></td>
					<td><%=user.getTel()%></td>
					<td><%=user.getEmail()%></td>
				</tr>
			<% 		
				}
			%>
		</tbody>
	</table>

</body>
</html>