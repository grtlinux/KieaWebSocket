package org.tain.websocket.test;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketTest {

	@OnOpen
	public void handleOpen() {
		System.out.println("client is now connected...");
	}
	
	@OnMessage
	public String handleMessage(String message) {
		System.out.println("receive from client: " + message);
		String replyMessage = "echo your message '" + message + "'";
		System.out.println("send to client: " + replyMessage);
		return replyMessage;
	}
	
	@OnClose
	public void handleClose() {
		System.out.println("client is now disconnected...");
	}
	
	@OnError
	public void handleError(Throwable e) {
		e.printStackTrace();
	}
}
