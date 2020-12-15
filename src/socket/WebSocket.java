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
    	// 메시지 형식(구분자:#123#)  > 방, 이벤트, 텍스트 순 
    	String[] strData = message.split("#123#");
    	String roomId = strData[0];
    	String event = strData[1];
    	String textData = strData[2];
    	int Makestate = 0; // 방 만들었는지 체크
    	if(event.equals("add")) { // add 이벤트 발생 시
    		if(!roomManage.isRoomExist(roomId)) { // 해당 방이 생성이 안되어있다면 생성
        		GameRoom gameRoom = new GameRoom();
    			gameRoom.setRoomId(roomId);
    			if(!roomId.equals("Hello")) { // WaitRoom이 아니면
    				gameRoom.setRoomName(textData.split("#").length!=5?"제목 없음":textData.split("#")[4]); // 방 제목 설정
    				System.out.println("새로운 방("+roomId+" : "+gameRoom.getRoomName()+")이 생성되었습니다.");
    				Makestate = 1;
    			}else {System.out.println("대기방(WaitRoom)을 생성합니다.");}
    			roomManage.AddRoom(gameRoom);
    		}
    		String[] playerinfo = textData.split("#"); // id, nickname, level, profile, 방 제목(최초) 순
    		Player player = new Player();
    		player.setRoomId(roomId);player.setId(playerinfo[0]);player.setNickname(playerinfo[1]);player.setLevel(playerinfo[2]);player.setProfile(playerinfo[3]);player.setSession(session);
    		if(!roomId.equals("Hello") && roomManage.getRoomById(roomId).getNowIn()==roomManage.getRoomById(roomId).getMaxIn()) {
    			// 대기방이 아니고 방 인원이 꽉 찼으면.. > 방을 나가도록 함
    			this.sendMessageById(roomId, "nowFull", "");
    		}else {
        		roomManage.AddPlayer(roomId, player);
        		roomManage.getRoomById(roomId).changeRoomMaker();
        		if(roomId.equals("Hello")){ // WaitRoom일 경우, Max가 없기에 구분함
            		this.sendMessageById(roomId, "add", roomManage.getRoomById(roomId).getPlayerInfoArray().toString());
        		}else { // 게임방일 경우 현재인원, 최대인원, 인원데이터 순으로 보냄
        			this.sendMessageById(roomId, "add", roomManage.getRoomById(roomId).getNowIn()+"@"
        					+roomManage.getRoomById(roomId).getMaxIn()+"@"
        					+roomManage.getRoomById(roomId).getPlayerInfoArray().toString());
        			if(roomManage.getRoomById(roomId).getNowIn()==roomManage.getRoomById(roomId).getMaxIn()) {
        				// 입장 후 인원이 찼다면 게임 시작
        				this.sendMessageById(roomId, "start", "");
        				String[] job = new String[]{"마피아", "시민", "의사", "경찰", "시민"};
        				for(int i=0;i<job.length;i++) // 직업 셔플
                        {
                            int r = (int)(Math.random()*job.length);
                            String tmp = job[r];
                            job[r] = job[0];
                            job[0] = tmp;
                        }
        				int count = 0;
        				for(Player p:roomManage.getRoomById(roomId).getPlayerlist()) {
        					// 직업 배정
        					this.sendMessageById(roomId, "job", p.getNickname()+"@"+job[count]);
        					count++;
        				}
        			}
        		}
    		}
    		if(Makestate==1) { // 이번 이벤트로 방을 만들었다면 새로고침
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
    	if(player!=null) { // 해당 플레이어를 찾았으면
    		roomManage.playerOut(player);
    		roomManage.getRoomById(player.getRoomId()).setNowIn(roomManage.getRoomById(player.getRoomId()).getNowIn()-1); // 인원 1명 줄임
    		if(player.getRoomId().equals("Hello")){ // WaitRoom일 경우, Max가 없기에 구분함
        		this.sendMessageById(player.getRoomId(), "out", roomManage.getRoomById(player.getRoomId()).getPlayerInfoArray().toString());
    		}else { // 게임방일 경우 현재인원, 최대인원, 인원데이터 순으로 보냄
    			this.sendMessageById(player.getRoomId(), "out", roomManage.getRoomById(player.getRoomId()).getNowIn()+"@"
    					+roomManage.getRoomById(player.getRoomId()).getMaxIn()+"@"
    					+roomManage.getRoomById(player.getRoomId()).getPlayerInfoArray().toString());
    		}
    		this.sendMessageById(player.getRoomId(), "bye", "시스템"+"#"+"settings.png"+"#"+player.getNickname()+"님이 퇴장하셨습니다.");
    	}else {System.out.println("Error: 플레이어가 존재하지 않음 - onClose()");}
		roomManage.getRoomById(player.getRoomId()).changeRoomMaker();
        roomManage.deleteRoom(); // 사람이 없는 방 제거
		this.sendRefresh();
    }
    
    public void sendMessageById(String roomId, String event, String textData) throws IOException {
    	ArrayList<Player> playerlist = roomManage.getRoomById(roomId).getPlayerlist();
    	if(playerlist.size()==0)
    		return;
    	for(int i=0;i<playerlist.size();i++) { //해당 방 인원 수만큼 반복
    		playerlist.get(i).getSession().getBasicRemote().sendText(roomId+"#123#"+event+"#123#"+textData);
		}
    }
    public void sendRefresh() throws IOException {
    	if(roomManage.getRoomById("Hello")==null)
    		return;
    	this.sendMessageById("Hello", "deleteAll", "");
    	for(int i=roomManage.getRoomlist().size()-1;i>=0;i--) {
			if(!roomManage.getRoomlist().get(i).getRoomId().equals("Hello")) { // Hello 방이 아니면(WaitRoom은 미포함)
        		this.sendMessageById("Hello", "addRoom", roomManage.getRoomlist().get(i).getRoomId()+"#"
        				+roomManage.getRoomlist().get(i).getRoomName()+"#"+roomManage.getRoomlist().get(i).getRoomMaker()+"#"
        				+Integer.toString(roomManage.getRoomlist().get(i).getNowIn())+"#"
        				+Integer.toString(roomManage.getRoomlist().get(i).getMaxIn())
        				); // 방 번호, 제목, 방장, 접속자, 최대접속자 순
			}
		}
    }
}