<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>用户信息</title>
</head>
<body>
<table>
    <tr>
        <td>Id</td>
        <td>${user.id}</td>
    </tr>
    <tr>
        <td>Username</td>
        <td>${user.username}</td>
    </tr>
    <tr>
        <td>Note</td>
        <td>${user.note}</td>
    </tr>
</table>
</body>
</html>