package socket;

import java.util.ArrayList;

import javax.websocket.Session;

public class RoomManage {
	private ArrayList<GameRoom> roomlist;
	
	public RoomManage() {
		roomlist = new ArrayList<GameRoom>();
	}
	
	public ArrayList<GameRoom> getRoomlist() {
		return roomlist;
	}

	public void setRoomlist(ArrayList<GameRoom> roomlist) {
		this.roomlist = roomlist;
	}
	
	public void AddRoom(GameRoom g) {
		roomlist.add(g);
	}
	
	public void AddPlayer(String roomId, Player player) {
		for(int i=0;i<roomlist.size();i++) {
			if(roomlist.get(i).getRoomId().equals(roomId)) {
				GameRoom gameRoom = roomlist.get(i);
				gameRoom.getPlayerlist().add(player);
				gameRoom.setNowIn(gameRoom.getNowIn()+1);
				return;
			}
		}
		System.out.println("Error : Room is not exist - AddPlayer");
	}
	
	public boolean isRoomExist(String roomId) {
		for(int i=0;i<roomlist.size();i++) {
			if(roomlist.get(i).getRoomId().equals(roomId))
				return true;
		}
		return false;
	}
	
	public Player getPlayerBySession(Session session) {
		for(int i=0;i<roomlist.size();i++) {
			for(int j=0;j<roomlist.get(i).getPlayerlist().size();j++) {
				if(roomlist.get(i).getPlayerlist().get(j).getSession().equals(session)) {
					return roomlist.get(i).getPlayerlist().get(j);
				}
			}
		}
		return null;
	}
	
	public GameRoom getRoomById(String roomId) {
		for(int i=0;i<roomlist.size();i++) {
			if(roomlist.get(i).getRoomId().equals(roomId))
				return roomlist.get(i);
		}
		return null;
	}
	
	public void playerOut(Player player) {
		for(int i=0;i<roomlist.size();i++) {
			if(roomlist.get(i).getRoomId().equals(player.getRoomId())) { // �÷��̾ ���� ���� ã��
				roomlist.get(i).RoomOut(player);
			}
		}
	}
	
	public void deleteRoom() { // �濡 ����� ������ ���� ����
		for(int i=0;i<roomlist.size();i++) {
			if(roomlist.get(i).getPlayerlist().size()==0) {
				System.out.println(roomlist.get(i).getRoomId()+" ���� �����˴ϴ�.");
				roomlist.remove(i);
			}
		}
	}
	
	public String setRoomId() {
		if(roomlist.size() == 1) return "1"; // WaitRoom�ۿ� ���� ���¸� 1���� ����
		if(roomlist.get(roomlist.size()-1).getRoomId().equals("Hello")) { // ������ ���� WaitRoom �̸�
			int roomId = Integer.parseInt(roomlist.get(roomlist.size()-2).getRoomId())+1;
			return Integer.toString(roomId); // ������ ���� WaitRoom�� �ƴϸ�
		}else {
			int roomId = Integer.parseInt(roomlist.get(roomlist.size()-1).getRoomId())+1;
			return Integer.toString(roomId); // ������ ���� WaitRoom�� �ƴϸ�
		}
	}
}
