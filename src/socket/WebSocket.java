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
    	// �޽��� ����(������:#123#)  > ��, �̺�Ʈ, �ؽ�Ʈ �� 
    	String[] strData = message.split("#123#");
    	String roomId = strData[0];
    	String event = strData[1];
    	String text = strData[2];
        synchronized(clients) {
            for(Session client : clients) {
            	if(event.equals("add")) { // add �̺�Ʈ �߻� ��
            		GameRoom gameRoom = new GameRoom();
            		if(!roomManage.isRoomExist(roomId)) { // �ش� ���� ������ �ȵǾ��ִٸ�
            			gameRoom.setRoomId(roomId);
            			roomManage.AddRoom(gameRoom);
            		}
            		
                	client.getBasicRemote().sendText("�߰��մϴ�.");
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
    	System.out.println("���� ����");
        clients.remove(session);
    }
}