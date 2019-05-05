<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="../../../../resources/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../../../resources/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../../../../resources/easyui/demo/demo.css">
    <script type="text/javascript" src="../../../../resources/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../../../../resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        function onSearch() {
            var opts = $("#dg").datagrid("options");
            opts.url = "./list";

            var userName = $("#userName").val();
            var note = $("#note").val();

            var params = {};
            if (userName != null && userName.trim() !== '') {
                params.userName = userName;
            }

            if (note != null && note.trim() !== '') {
                params.note = note;
            }

            $("#dg").datagrid('load', params);
        }
    </script>
</head>

<body>
<div style="margin: 20px 0;"></div>
<div class="easyui-layout" style="width: 100%; height: 350px">
    <div data-options="region:'north'" style="height: 50px">
        <form id="searchForm" method="post">
            <table>
                <tr>
                    <td>UserName:</td>
                    <td><input id="userName" name="userName" class="easyui-textbox" data-options="prompt:'Please enter user name...'" style="width: 100%; height: 32px;"></td>
                    <td>Note:</td>
                    <td><input id="note" name="note" class="easyui-textbox" data-options="prompt:'Please enter note...'" style="width: 100%; height: 32px;"></td>
                    <td><a href="#" class="easyui-linkbutton" data-options="iconCls: 'icon-search'" style="width: 80px" onclick="onSearch()">Search</a></td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center', title:'User List', iconCls:'icon-ok'">
        <table id="dg" class="easyui-datagrid" data-options="border:false, singleSelect:true, fit:true, fitColumn:true">
            <thead>
            <tr>
                <th data-options="field:'id'" width="80">Id</th>
                <th data-options="field:'userName'" width="100">User Name</th>
                <th data-options="field:'note'" width="80">Note</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.userName}</td>
                    <td>${user.note}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>

</html>