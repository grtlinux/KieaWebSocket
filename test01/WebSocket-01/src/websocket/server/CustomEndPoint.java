package websocket.server;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ratesrv")
public class CustomEndPoint {
	
	// queue holds the list of connected clients
	private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
	private static Thread rateThread; // rate publisher thread
	
	static {
		//rate publisher thread, generates a new value for USD rate every 2 seconds.
		rateThread = new Thread() {
			@Override
			public void run() {
				DecimalFormat df = new DecimalFormat("#.####");
				while (true) {
					double dbl = 2 * Math.random();
					if (queue != null) {
						sendAll("USD Rate: " + df.format(dbl));
					}
					
					try { sleep(2000); } catch (InterruptedException e) {}
				}
			}
		};
		
		rateThread.start();
	}
	
	private static void sendAll(String msg) {
		try {
			// Send the new rate to all open WebSocket sessions
			List<Session> closedSessions = new ArrayList<>();
			for (Session session : queue) {
				if (!session.isOpen()) {
					System.out.println("Closed session: " + session.getId());
					closedSessions.add(session);
				} else {
					session.getBasicRemote().sendText(msg);
				}
			}
			
			queue.removeAll(closedSessions);
			System.out.printf("Sending '%s' to %d clients...%n", msg, queue.size());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@OnMessage
	public void onMessage(Session session, String msg) {
		// provided for completeness, in out scenario clients don't send any message
		try {
			System.out.printf("received message '%s' from [%s]%n", msg, session.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		queue.add(session);
		System.out.println("New session opened: " + session.getId());
	}
	
	@OnClose
	public void onClose(Session session) {
		queue.remove(session);
		System.out.println("session closed: " + session.getId());
	}
	
	@OnError
	public void onError(Session session, Throwable th) {
		queue.remove(session);
		System.out.println("Error on session: " + session.getId());
	}
}
