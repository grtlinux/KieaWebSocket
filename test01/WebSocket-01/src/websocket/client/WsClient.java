package websocket.client;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WsClient {

	private static Object waitLock = new Object();
	
	@OnMessage
	public void onMessage(String message) {
		// the new USB rate arrives from the websocket server side
		System.out.println("Received msg: " + message);
	}
	
	private static void wait4TerminateSignal() {
		synchronized(waitLock) {
			try {
				waitLock.wait();
			} catch (InterruptedException e) {}
		}
	}
	
	public static void main(String[] args) {
		WebSocketContainer container = null;
		Session session = null;
		
		try {
			// Tyrus is plugged via ServiceLoader API. See notes above
			container = ContainerProvider.getWebSocketContainer();
			
			// WSI is the context-root of my web.app
			// ratesrv is the path given in the ServerEndpoint annotation on server
			session=container.connectToServer(WsClient.class, URI.create("ws://localhost/WebSocket-01/ratesrv"));
			
			wait4TerminateSignal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				try { session.close(); } catch (Exception e) {}
			}
		}
	}
}
