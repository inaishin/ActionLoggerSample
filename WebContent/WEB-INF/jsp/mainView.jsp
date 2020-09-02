<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.UserInfo" %>
<%
	String username=(String)session.getAttribute("username"); 
%>
<%
	String view = request.getParameter("view");
%>

<!-- 画面上部の表示とサイドメニュー,メインの表示指定 -->

<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Dashboard Template · Bootstrap</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta name="theme-color" content="#563d7c">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Action
			Logger</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed"
			type="button" data-toggle="collapse" data-target="#sidebarMenu"
			aria-controls="sidebarMenu" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"></li>
			<li class="nav-item text-nowrap"><a class="nav-link" href="/WebSystem/logout"><%=username %>
					- ログアウト</a></li>
		</ul>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
				<!-- サイドバーの中身をインポート -->
				<jsp:include page="/WEB-INF/jsp/sidebar.jsp" />
			</nav>
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
				<!-- コンテンツエリアの中身をインポート -->
				<% //活動記録表示ページ
					if (view != null && view.equals("activities")) {
				%>
					<jsp:include page="/WEB-INF/jsp/activities.jsp" />
				
				<% //活動記録登録ページ
					} 
						else if (view != null && view.equals("addActivities")) {
				%>
						<jsp:include page="/WEB-INF/jsp/addActivities.jsp" />
				
				<% //新規グループページ
					} 
						else if (view != null && view.equals("addGroup")) {
				%>	
						<jsp:include page="/WEB-INF/jsp/addGroup.jsp" />
				
				<% //グループ参加ページ
					} 
						else if (view != null && view.equals("joinGroup")) {
				%>	
						<jsp:include page="/WEB-INF/jsp/joinGroup.jsp" />
					
				<% //プロフィール確認ページ
					} 
						else if (view != null && view.equals("userInfo")) {
				%>	
						<jsp:include page="/WEB-INF/jsp/userInfo.jsp" />
				
				<% //管理ユーザー：グループ参加
					} 
						else if (view != null && view.equals("joinGroupMasterUser")) {
				%>	
						<jsp:include page="/WEB-INF/jsp/joinGroupMasterUser.jsp" />
					
				<% //管理ユーザー：グループ一覧
					} 
						else if (view != null && view.equals("memberList")) {
				%>	
						<jsp:include page="/WEB-INF/jsp/memberList.jsp" />
					
				<%
					} 
						else {
				%>
						<jsp:include page="/WEB-INF/jsp/stateView.jsp" />
				<%
					}
				%>
			</main>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"
		integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd"
		crossorigin="anonymous"></script>

</body>
</html>
