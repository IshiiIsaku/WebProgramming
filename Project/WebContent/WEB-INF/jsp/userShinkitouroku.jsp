<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ新規登録</title>
<link rel="stylesheet" type="text/css"  href="css/bootstrap.css" >
</head>
<body>
<p class="text-right"><li>${userInfo.name}さん</li></p>
<a style="color:red"  href="logoutServlet">ログアウト</a>
<h1 class="text-center">ユーザ新規登録</h1>
<p style="color:red">
<c:if test="${erMsg != null}" >
	    <div>
		 <a style="color:red"> ${erMsg}</a>
		</div>
	</c:if>
</p>
<form action="userShinkitourokuServlet" method="post">
<dl>
<dt>ログインID</dt>
<dd>
<input type="text" name="loginID"value="${loginId}">
</dd>

<dt>パスワード</dt>
<dd>
<input type="password" name="password">
</dd>

<dt>パスワード（確認）</dt>
<dd>
<input type="password" name="password1">
</dd>

<dt>ユーザ名<dt>
<dd>
<input type="text" name="userName" value="${userName}">
</dd>

<dt>生年月日</dt>
<dd>
<input type="text" name="birthday"value="${birthday}">
</dd>

<dd>
<input type="submit" value="登録">
</dd>

</dl>
</form>

<FORM>
<INPUT type="button" value="戻る" onClick="history.go(-1)">
</FORM>
</body>
</html>