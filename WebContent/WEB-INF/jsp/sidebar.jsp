<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<!-- サイドメニューバー -->	
	
<div class="sidebar-sticky pt-3">

	<ul class="nav flex-column">
		<li class="nav-item"><a class="nav-link active"
			href="/WebSystem/?view=dashboard"> Dashboard </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 活動記録 ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link" href="/WebSystem/?view=addActivities"> <span
				data-feather="file-text"></span> 活動記録登録
		</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/WebSystem/?view=activities"> 表示 </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== グループ ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link" href="/WebSystem/?view=addGroup"> 新規グループ </a></li>
		<li class="nav-item"><a class="nav-link" href="/WebSystem/?view=joinGroup"> グループに参加 </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== グループ管理 ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link" href="/WebSystem/?view=joinGroupMasterUser">グループに参加<br>-管理ユーザー
		</a></li>
		<li class="nav-item"><a class="nav-link" href="/WebSystem/?view=memberList">管理グループ一覧</a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== プロフィール ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link" href="/WebSystem/?view=userInfo"> プロフィール確認 </a>
		</li>
		
	</ul>


</div>

