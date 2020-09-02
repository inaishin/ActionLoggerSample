<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<title>活動記録登録</title>
</head>
<body>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">活動記録登録</h1>
	</div>


	 <div class="table-responsive">
	 
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
		
	</div>
</body>
</html>