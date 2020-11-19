package socket;

import java.util.ArrayList;

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
	
	public boolean isRoomExist(String roomId) {
		for(int i=0;i<roomlist.size();i++) {
			if(roomlist.get(i).getRoomId().equals(roomId))
				return true;
		}
		return false;
	}
	
	public Player SearchPlayer(String nickname) {
		for(int i=0;i<roomlist.size();i++) {
			for(int j=0;j<roomlist.get(i).getPlayerlist().size();j++) {
				if(roomlist.get(i).getPlayerlist().get(j).getNickname().equals(nickname)) {
					return roomlist.get(i).getPlayerlist().get(j);
				}
			}
		}
		return null;
	}
}
