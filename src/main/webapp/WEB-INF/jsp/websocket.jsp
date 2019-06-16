<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>My WebSocket</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/websocket.js"></script>
</head>
<body>
Test for the websocket
<br/>
<input id="message" type="text"/>
<button onclick="sendMessage()">Send Message</button>
<button onclick="closeWebSocket()">Close WebSocket</button>
<div id="context"></div>
</body>
</html>