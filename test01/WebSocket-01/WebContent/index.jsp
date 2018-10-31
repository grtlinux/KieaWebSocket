<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>index.jsp</title>
<script type="text/javascript">
	var ws;
	function connect() {
		ws = new WebSocket("ws://localhost/WebSocket-01/ratesrv");
		ws.onmessage = onMessage;
	}
	
	function onMessage(evt) {
		document.getElementById("rate").innerHTML = evt.data;
	}
	
	window.addEventListener("load", connect, false);
</script>
</head>
<body>
	<h2>index.jsp: https://dzone.com/articles/sample-java-web-socket-client</h2>
	<table>
		<tr>
			<td><label id="rateLbl">Current Rate:</label></td>
			<td><label id="rate">0</label></td>
		</tr>
	</table>
</body>
</html>


