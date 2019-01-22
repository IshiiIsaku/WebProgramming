<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"  href="css/bootstrap.css" >
<title>ユーザ削除確認</title>
</head>
<body>
<p class="text-right"><li>${userInfo.name}さん</li></p>
<div class="text-right" ><a style="color:red" href="logoutServlet">ログアウト</a></div>
<h1 class="text-center">ユーザ削除確認</h1>

<form action="UserDeleteServlet" method="post">
	<dl>
	<dd>
	<p>ログインID:${user.loginId}</p>
	<input type="hidden"name="loginId" value="${user.loginId}">
	</dd>
	<dd>
	<p>を本当に削除してよろしいでしょうか。</p>
	</dd>

	<dd>
	<input type="submit"  value="OK">
	</dd>
	</dl>
</form>

<form action="UserListServlet" method="get">

<dl>
<dd>
<input type="submit" value="キャンセル">
</dd>
</dl>


</form>





</body>
</html>