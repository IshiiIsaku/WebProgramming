<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ一覧</title>
<link rel="stylesheet" type="text/css"  href="css/bootstrap.css" >
</head>
<body>
<li>${userInfo.name}さん</li>
<a style="color:red" href="logoutServlet">ログアウト</a>
<h1 class="text-center">ユーザ一覧</h1>
<a  href="userShinkitourokuServlet">新規登録</a>
<form action="UserListServlet" method="post">
<dl>
<dt>ログインID</dt>
<dd>
<input type="text" name="loginId">
</dd>

<dt>ユーザ名</dt>
<dd>
<input type="text" name="userName">
</dd>

<dt>生年月日</dt>
<dd>
<input type="date" name="birthday"value="年/月/日"><p>～</p><input type="date" name="birthday1" value="年/月/日">
</dd>

<dd>
<input type="submit" value="検索">
</dd>
</dl>
</form>


<table class="table-striped tbody tr:nth-of-type(odd)" border="1">
               <thead>
                 <tr>
                   <th>ログインID</th>
                   <th>ユーザ名</th>
                   <th>生年月日</th>
                   <th></th>
                 </tr>
               </thead>
               <tbody>
                 <c:forEach var="user" items="${userList}" >
                   <tr>
                     <td>${user.loginId}</td>
                     <td>${user.name}</td>
                     <td>${user.birthDate}</td>
                     <!-- ログインボタンの表示制御を行う -->
                     <td>

                           <a class="btn btn-primary" href="UserJouhoushousaisanshou?id=${user.loginId}">詳細</a>

                       <c:if test="${userInfo.loginId=='admin' || user.loginId==userInfo.loginId}">
                           <a class="btn btn-success" href="UserUpdateServlet?id=${user.loginId}">更新</a>
                      	</c:if>

                      	<c:if test="${userInfo.loginId=='admin'}">
                      		<a class="btn btn-danger" href ="UserDeleteServlet?id=${user.loginId}">削除</a>
                     	</c:if>
                     </td>
                   </tr>
                 </c:forEach>
               </tbody>
             </table>

</body>
</html>







