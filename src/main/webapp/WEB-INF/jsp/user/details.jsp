<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>
        User Details
    </title>
</head>
<body>
<center>
    <table border="1">
        <tr>
            <td>Label</td>
            <td>Value</td>
        </tr>
        <tr>
            <td>ID</td>
            <td><c:out value="${user.id}"></c:out></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><c:out value="${user.userName}"></c:out></td>
        </tr>
        <tr>
            <td>Note</td>
            <td><c:out value="${user.note}"></c:out></td>
        </tr>
    </table>
</center>
</body>
</html>