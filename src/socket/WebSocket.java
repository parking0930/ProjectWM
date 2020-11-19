package socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
@ServerEndpoint("/WebSocket")
public class WebSocket {
	private static RoomManage roomManage = new RoomManage();
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
    	// 메시지 형식(구분자:#123#)  > 방, 이벤트, 텍스트 순 
    	String[] strData = message.split("#123#");
    	String roomId = strData[0];
    	String event = strData[1];
    	String text = strData[2];
        synchronized(clients) {
            for(Session client : clients) {
            	if(event.equals("add")) { // add 이벤트 발생 시
            		GameRoom gameRoom = new GameRoom();
            		if(!roomManage.isRoomExist(roomId)) { // 해당 방이 생성이 안되어있다면
            			gameRoom.setRoomId(roomId);
            			roomManage.AddRoom(gameRoom);
            		}
            		
                	client.getBasicRemote().sendText("추가합니다.");
            	}else {
            		
            	}
            }
        }
    }
    
    @OnOpen
    public void onOpen(Session session) throws IOException {
    	//session.getBasicRemote().sendText("Hello#123#add#123#");
        clients.add(session);
    }
    
    @OnClose
    public void onClose(Session session) {
    	System.out.println("유저 나감");
        clients.remove(session);
    }
}