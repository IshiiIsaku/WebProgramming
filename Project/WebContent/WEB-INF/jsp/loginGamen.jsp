<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"  href="css/bootstrap.css" >
<title>ログイン画面</title>
</head>
<body>
<a style="color:red">
<c:if test="${errMsg != null}" >
	    <div>
		  ${errMsg}
		</div>
	</c:if>
</a>
<h1 class="text-center">ログイン画面</h1>
<form  action="loginServlet" method="post">

<dl>

<dt>ログインID</dt>
<dd>
<input type="text" name="loginId">
</dd>

<dt>パスワード</dt>
<dd>
<input type="password" name="password">
</dd>


<dd>
<input type="submit" class="btn btn-primary"value="ログイン">
</dd>


</dl>
</form>
</body>
</html>


