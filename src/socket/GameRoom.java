package socket;

import java.util.ArrayList;

public class GameRoom {
	private String roomId;
	private String roomName;
	private String roomMaker;
	private int nowIn;
	private int maxIn;
	
	private ArrayList<Player> playerlist;
	
	public GameRoom() {
		playerlist = new ArrayList<Player>();
		nowIn = 0;
		maxIn = 2;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public String getRoomMaker() {
		return roomMaker;
	}

	public void setRoomMake(String roomMaker) {
		this.roomMaker = roomMaker;
	}
	
	public int getNowIn() {
		return nowIn;
	}

	public void setNowIn(int nowIn) {
		this.nowIn = nowIn;
	}

	public int getMaxIn() {
		return maxIn;
	}

	public void setMaxIn(int maxIn) {
		this.maxIn = maxIn;
	}
	
	public ArrayList<Player> getPlayerlist() {
		return playerlist;
	}

	public void setPlayerlist(ArrayList<Player> playerlist) {
		this.playerlist = playerlist;
	}
	
	public void RoomOut(Player player) {
		for(int i=0;i<playerlist.size();i++) {
			if(playerlist.get(i).getId().equals(player.getId())) { // �ش� �÷��̾ ã����
				playerlist.remove(i);
				return;
			}
		}
		System.out.println("Error: Player is not exist - RoomOut()");
	}
	
	public ArrayList<String> getPlayerInfoArray() {
		ArrayList<String> playerinfo = new ArrayList<String>();
		for(int i=0;i<playerlist.size();i++) {
			playerinfo.add(playerlist.get(i).getLevel()+"#"+playerlist.get(i).getNickname()+"#"+playerlist.get(i).getProfile());
		}
		return playerinfo;
	}
	
	public void changeRoomMaker() {
		if(playerlist.size()==0) return;
		for(int i=0;i<playerlist.size();i++) { // �� �����̰� �����ϸ� ���� ���� ���
			if(playerlist.get(i).getNickname().equalsIgnoreCase(this.roomMaker))
				return;
		}
		this.roomMaker = playerlist.get(0).getNickname(); // �� �����̰� ������ ���� ������� ���� ����
	}
}
