<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="model.UserInfo" %>
<%
	String userid=(String)session.getAttribute("userid"); //セッションスコープからインスタンス取得
	String username=(String)session.getAttribute("username"); 
%>

<%@page import="dao.MoveDAO"%>  <% //DB検索に関するクラスインポート %>
<%@ page import="model.MoveInfo"%>
<%@page import="java.util.List" %>

<!-- トップページ -->
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Dashboard</h1>
      </div>
      <h3>ユーザー情報</h3>
      <div class="table-responsive">
        <table class="table table-borderless table-sm">
           <tbody>
            <tr class="d-flex">
              <th scope="row" class="col-2 text-right">ユーザー名</tk>
              <td><%=userid%></td>
            </tr>
            <tr class="d-flex">
              <th scope="row" class="col-2 text-right">氏名</th>
              <td><%=username %></td>
            </tr>
            <tr class="d-flex">
              <th scope="row" class="col-2 text-right">参加グループ</th>
              <td>grp01</td>
            </tr>
            <tr class="d-flex">
              <th scope="row" class="col-2 text-right"></th>
              <td>grp02</td>
            </tr>
            <tr class="d-flex">
              <th scope="row" class="col-2 text-right">管理グループ</th>
              <td>KBC ITE19</td>
            </tr>
            <tr class="d-flex">
              <th scope="row" class="col-2 text-right"></th>
              <td>KBC 教職員</td>
            </tr>
          </tbody>
        </table>
	  </div>      
      <h3>最近の行動記録</h3>
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
					<td><%=move.getStarttime()%></td>
					<td><%=move.getFinishtime()%></td>
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
