<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- 登録確認ページ -->

<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger ユーザー登録確認</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta name="theme-color" content="#563d7c">
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Action
			Logger</a>
	</nav>
	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-adduser" 
				action="/WebSystem/addmoveconfirm" method="post">
				
				<h4 h3 mb-3 font-weight-normal>活動登録</h4>
				<div class="mb-3">
					日付　: ${moveToAdd.day}　<!-- セッションスコープuserToAdd属性内に保存されているuserインスタンス取得 -->
				</div>
				
				<div class="mb-3">
					開始時刻 : ${moveToAdd.starttime} <!-- EL式でスコープに保存されてるインスタンス取得, ${属性名.プロパティ} -->
				</div>
				<div class="mb-3">
					終了時刻 : ${moveToAdd.finishtime}
				</div>
				<div class="mb-3">
					場所 : ${moveToAdd.place}
				</div>
				<div class="mb-3">
					理由　: ${moveToAdd.reason}
				</div>
				
				<div class="mb-3">
					理由　: ${moveToAdd.other}
				</div>
				
				<input type="submit" value="登録">
			</form>
		</div>
	</div>
</body>
</html>