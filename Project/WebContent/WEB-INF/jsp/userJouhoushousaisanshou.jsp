<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報詳細参照</title>
<link rel="stylesheet" type="text/css"  href="css/bootstrap.css" >
</head>
<body>
<p class="text-right"><li>${userInfo.name}さん</li></p>
<a style="color:red"  href="logoutServlet">ログアウト</a>
<h1 class="text-center">ユーザ情報詳細参照</h1>

<form action="" method="post">
<dl>
<dt>ログインID</dt>
<dd>
<p>${user.loginId}</p>
</dd>

<dt>ユーザ名</dt>
<dd>
<p><c:out value="${user.name}"></c:out></p>
</dd>

<dt>生年月日</dt>
<dd>
<p><c:out value="${user.birthDate}"></c:out></p>
</dd>

<dt>登録日時</dt>
<dd>
<p><c:out value="${user.createDate}"></c:out></p>
</dd>

<dt>更新日時</dt>
<dd>
<p><c:out value="${user.updateDate}"></c:out></p>
</dd>
</dl>

</form>
<FORM>
<INPUT type="button" value="戻る" onClick="history.go(-1)">
</FORM>
</body>
</html>