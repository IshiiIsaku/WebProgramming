<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報更新</title>
<link rel="stylesheet" type="text/css"  href="css/bootstrap.css" >
</head>

<body>
	<p class="text-right">${userInfo.name}さん</p>
	<a style="color:red" href="logoutServlet">ログアウト</a>
	<h1 class="text-center">ユーザ情報更新</h1>
	<p>
	<c:if  test="${errorMsg != null}" >

			 <a style="color:red">${errorMsg}</a>

		</c:if>
	</p>

	<form action="UserUpdateServlet" method="post">
		<dl>
		<dt>ログインID</dt>
		<dd>
		<p>${user.loginId}${loginId}</p>
		<input type="hidden"name="loginId" value="${user.loginId}${loginId}">
	    </dd>

		<dt>パスワード</dt>
		<dd>
		<input type="text" name="password">
		</dd>

		<dt>パスワード（確認）</dt>
		<dd>
		<input type="text" name="password1">
		</dd>

		<dt>ユーザ名<dt>
		<dd>
		<input type="text" name="userName" value="${user.name}${userName}">
		</dd>

		<dt>生年月日</dt>
		<dd>
		<input type="text" name="birthday" value="${user.birthDate}${birthday}">
		</dd>

		<dd>
		<input type="submit" value="更新">
		</dd>

		</dl>
	</form>
	<FORM>
	<INPUT type="button" value="戻る" onClick="history.go(-1)">
	</FORM>
</body>
</html>