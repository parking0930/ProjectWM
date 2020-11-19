package socket;

import java.util.ArrayList;

public class GameRoom {
	private String roomId;
	private ArrayList<Player> playerlist;
	
	public GameRoom() {
		playerlist = new ArrayList<Player>();
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public ArrayList<Player> getPlayerlist() {
		return playerlist;
	}

	public void setPlayerlist(ArrayList<Player> playerlist) {
		this.playerlist = playerlist;
	}
	public void RoomOut(String nickname) {
		//°­Åð
	}
	public void RoomJoin(Player p) {
		playerlist.add(p);
	}
}
