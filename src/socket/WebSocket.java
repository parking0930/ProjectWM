package socket;

import java.io.IOException;
import java.util.ArrayList;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
@ServerEndpoint("/WebSocket")
public class WebSocket {
	private static RoomManage roomManage = new RoomManage();
    //private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
    	// �޽��� ����(������:#123#)  > ��, �̺�Ʈ, �ؽ�Ʈ �� 
    	String[] strData = message.split("#123#");
    	String roomId = strData[0];
    	String event = strData[1];
    	String textData = strData[2];
    	int Makestate = 0; // �� ��������� üũ
    	if(event.equals("add")) { // add �̺�Ʈ �߻� ��
    		if(!roomManage.isRoomExist(roomId)) { // �ش� ���� ������ �ȵǾ��ִٸ� ����
        		GameRoom gameRoom = new GameRoom();
    			gameRoom.setRoomId(roomId);
    			if(!roomId.equals("Hello")) { // WaitRoom�� �ƴϸ�
    				gameRoom.setRoomName(textData.split("#").length!=5?"���� ����":textData.split("#")[4]); // �� ���� ����
    				System.out.println("���ο� ��("+roomId+" : "+gameRoom.getRoomName()+")�� �����Ǿ����ϴ�.");
    				Makestate = 1;
    			}else {System.out.println("����(WaitRoom)�� �����մϴ�.");}
    			roomManage.AddRoom(gameRoom);
    		}
    		String[] playerinfo = textData.split("#"); // id, nickname, level, profile, �� ����(����) ��
    		Player player = new Player();
    		player.setRoomId(roomId);player.setId(playerinfo[0]);player.setNickname(playerinfo[1]);player.setLevel(playerinfo[2]);player.setProfile(playerinfo[3]);player.setSession(session);
    		if(!roomId.equals("Hello") && roomManage.getRoomById(roomId).getNowIn()==roomManage.getRoomById(roomId).getMaxIn()) {
    			// ������ �ƴϰ� �� �ο��� �� á����.. > ���� �������� ��
    			this.sendMessageById(roomId, "nowFull", "");
    		}else {
        		roomManage.AddPlayer(roomId, player);
        		roomManage.getRoomById(roomId).changeRoomMaker();
        		if(roomId.equals("Hello")){ // WaitRoom�� ���, Max�� ���⿡ ������
            		this.sendMessageById(roomId, "add", roomManage.getRoomById(roomId).getPlayerInfoArray().toString());
        		}else { // ���ӹ��� ��� �����ο�, �ִ��ο�, �ο������� ������ ����
        			this.sendMessageById(roomId, "add", roomManage.getRoomById(roomId).getNowIn()+"@"
        					+roomManage.getRoomById(roomId).getMaxIn()+"@"
        					+roomManage.getRoomById(roomId).getPlayerInfoArray().toString());
        			if(roomManage.getRoomById(roomId).getNowIn()==roomManage.getRoomById(roomId).getMaxIn()) {
        				// ���� �� �ο��� á�ٸ� ���� ����
        				this.sendMessageById(roomId, "start", "");
        				String[] job = new String[]{"���Ǿ�", "�ù�", "�ǻ�", "����", "�ù�"};
        				for(int i=0;i<job.length;i++) // ���� ����
                        {
                            int r = (int)(Math.random()*job.length);
                            String tmp = job[r];
                            job[r] = job[0];
                            job[0] = tmp;
                        }
        				int count = 0;
        				for(Player p:roomManage.getRoomById(roomId).getPlayerlist()) {
        					// ���� ����
        					this.sendMessageById(roomId, "job", p.getNickname()+"@"+job[count]);
        					count++;
        				}
        			}
        		}
    		}
    		if(Makestate==1) { // �̹� �̺�Ʈ�� ���� ������ٸ� ���ΰ�ħ
    			this.sendRefresh();
    		}
    	}else if(event.equals("chat")){
    		this.sendMessageById(roomId, "chat", textData);
    	}else if(event.equals("welcome"))
    	{
    		this.sendMessageById(roomId, "welcome", textData);
    		if(roomId.equals("Hello")){
    			this.sendRefresh();
    		}
    		
    	}else if(event.equals("create"))
    	{
			this.sendMessageById("Hello", "create", roomManage.setRoomId()+"#"+textData);
    	}
    }
    
    @OnOpen
    public void onOpen(Session session) throws IOException {
    	
    }
    
    @OnClose
    public void onClose(Session session) throws IOException {
        //clients.remove(session);
    	Player player = roomManage.getPlayerBySession(session);
    	if(player!=null) { // �ش� �÷��̾ ã������
    		roomManage.playerOut(player);
    		roomManage.getRoomById(player.getRoomId()).setNowIn(roomManage.getRoomById(player.getRoomId()).getNowIn()-1); // �ο� 1�� ����
    		if(player.getRoomId().equals("Hello")){ // WaitRoom�� ���, Max�� ���⿡ ������
        		this.sendMessageById(player.getRoomId(), "out", roomManage.getRoomById(player.getRoomId()).getPlayerInfoArray().toString());
    		}else { // ���ӹ��� ��� �����ο�, �ִ��ο�, �ο������� ������ ����
    			this.sendMessageById(player.getRoomId(), "out", roomManage.getRoomById(player.getRoomId()).getNowIn()+"@"
    					+roomManage.getRoomById(player.getRoomId()).getMaxIn()+"@"
    					+roomManage.getRoomById(player.getRoomId()).getPlayerInfoArray().toString());
    		}
    		this.sendMessageById(player.getRoomId(), "bye", "�ý���"+"#"+"settings.png"+"#"+player.getNickname()+"���� �����ϼ̽��ϴ�.");
    	}else {System.out.println("Error: �÷��̾ �������� ���� - onClose()");}
		roomManage.getRoomById(player.getRoomId()).changeRoomMaker();
        roomManage.deleteRoom(); // ����� ���� �� ����
		this.sendRefresh();
    }
    
    public void sendMessageById(String roomId, String event, String textData) throws IOException {
    	ArrayList<Player> playerlist = roomManage.getRoomById(roomId).getPlayerlist();
    	if(playerlist.size()==0)
    		return;
    	for(int i=0;i<playerlist.size();i++) { //�ش� �� �ο� ����ŭ �ݺ�
    		playerlist.get(i).getSession().getBasicRemote().sendText(roomId+"#123#"+event+"#123#"+textData);
		}
    }
    public void sendRefresh() throws IOException {
    	if(roomManage.getRoomById("Hello")==null)
    		return;
    	this.sendMessageById("Hello", "deleteAll", "");
    	for(int i=roomManage.getRoomlist().size()-1;i>=0;i--) {
			if(!roomManage.getRoomlist().get(i).getRoomId().equals("Hello")) { // Hello ���� �ƴϸ�(WaitRoom�� ������)
        		this.sendMessageById("Hello", "addRoom", roomManage.getRoomlist().get(i).getRoomId()+"#"
        				+roomManage.getRoomlist().get(i).getRoomName()+"#"+roomManage.getRoomlist().get(i).getRoomMaker()+"#"
        				+Integer.toString(roomManage.getRoomlist().get(i).getNowIn())+"#"
        				+Integer.toString(roomManage.getRoomlist().get(i).getMaxIn())
        				); // �� ��ȣ, ����, ����, ������, �ִ������� ��
			}
		}
    }
}