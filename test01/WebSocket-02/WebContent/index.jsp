<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index.jsp</title>
</head>
<body>
	<h2>index.jsp</h2>
	<form>
		<input type="text" id="textMessage">
		<input type="button" value="Send" onclick="sendMessage()">
		<input type="button" value="Connect" onclick="connect()">
		<input type="button" value="Disconnect" onclick="disconnect()">
	</form>
	<br/>
	<textarea rows="10" cols="50" id="messageTextArea"></textarea>
	
	<script type="text/javascript">
		var webSocket = new WebSocket("ws://localhost/WebSocket-02/websocket");
		var messageTextArea = document.getElementById("messageTextArea");
		
		webSocket.onopen = function(message) {
			messageTextArea.value += "Server connect...\n";
		};
		webSocket.onclose = function(message) {
			messageTextArea.value += "Server disconnect...\n";
		};
		webSocket.onerror = function(message) {
			messageTextArea.value += "Error...\n";
		};
		webSocket.onmessage = function(message) {
			messageTextArea.value += "Receive from Server => " + message.data + "\n";
		};
		
		function sendMessage() {
			var message = document.getElementById("textMessage");
			messageTextArea.value += "Send to Server => " + message.value + "\n";
			webSocket.send(message.value);
			message.value = "";
		}
		function connect() {
			webSocket.open();
		}
		function disconnect() {
			webSocket.close();
		}
	</script>
</body>
</html>