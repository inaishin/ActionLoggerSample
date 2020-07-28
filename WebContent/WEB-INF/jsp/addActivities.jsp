<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/WebSystem/move" method="POST">
		<div>
			<label>日付：</label>
			<input type="date" name="day" required>
		</div>
		
		<div>
			<label>開始時刻：</label>
			<input type="time" name="starttime" required>
		</div>
		
		<div>
			<label>終了時刻：</label>
			<input type="time" name="finishtime" required >
		</div>
		
		<div>
			<label>場所：</label>
			<input type="text" name="place">
		</div>
		<div>
			<label>理由：</label>
			<input type="text" name="reason">
		</div>
		<div>
			<label>備考：</label>
			<input type="text" name="other">
		</div>
		
		<div>
			<input type="submit" value="登録">
		</div>
	</form>
</body>
</html>