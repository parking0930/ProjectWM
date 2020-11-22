var webSocket = new WebSocket('ws://localhost:8181/ProjectWM/WebSocket');

webSocket.onerror = function(event) {
	onError(event)
};

webSocket.onopen = function(event) {
	onOpen(event)
};

webSocket.onmessage = function(event) {
	onMessage(event);
};

window.onload = function () {
	var chat = document.getElementById('chat');
	var textarea = document.getElementById('text_area');
}

$('#chat_textinput').keydown(function(key){
	if(key.keyCode==13)
	{
		if($('#chat_textinput').val()!='')
		{
			webSocket.send("Hello#123#chat#123#"+level+"#"+nickname+"#"+profile+"#"+$('#chat_textinput').val());
			$('#chat_textinput').val('');
			$('#chat_textinput').focus();
		}
	}
});

$('#roomtitle_box').keydown(function(key){
	if(key.keyCode==13)
	{
		roomCreate();
	}
});
//////////////////소켓 통신 부분//////////////////
var playerLevel = [];
var playerNickname = [];
var playerProfile = [];

function onMessage(message) {
	var eventData = getData(message.data);
	var roomId = eventData[0];
	var event = eventData[1];
	var textData = eventData[2];
	if(roomId=="Hello") // Hello 방(WaitRoom)에 온 메시지 이면
	{
		if(event=="add" || event=="out")
		{
			setPlayer(textData);
			addPlayer();
		}else if(event=="chat")
		{
			var tmpLevel = textData.split("#")[0].trim();
			var tmpNick = textData.split("#")[1].trim();
			var tmpProfile = textData.split("#")[2].trim();
			var chat = textData.split("#")[3].trim();
			if(tmpNick==nickname)
			{
				$('#chat_inner').append(`<div class="player_chat_info">
										<div class="player_chat_img_div">
											<img class="player_chat_img" src="image/profile/`+tmpProfile+`">
										</div>
										<div class="player_chat_top">
										Lv.`+tmpLevel+` `+tmpNick+`
										</div>
									</div>
									<div class="player_chat_my">`+
										chat
									+`</div>`);
			}else
			{
				$('#chat_inner').append(`<div class="player_chat_info">
										<div class="player_chat_img_div">
											<img class="player_chat_img" src="image/profile/`+tmpProfile+`">
										</div>
										<div class="player_chat_top">
										Lv.`+tmpLevel+` `+tmpNick+`
										</div>
									</div>
									<div class="player_chat_you">`+
										chat
									+`</div>`);
			}
			$('#chat_inner').scrollTop($('#chat_inner')[0].scrollHeight);
		}else if(event=="welcome" || event=="bye")
		{
			var tmpNick = textData.split("#")[0].trim();
			var tmpProfile = textData.split("#")[1].trim();
			var chat = textData.split("#")[2].trim();
			$('#chat_inner').append(`<div class="player_chat_info">
										<div class="player_chat_img_div">
											<img class="player_chat_img" src="image/profile/`+tmpProfile+`">
										</div>
										<div class="player_chat_top">`
										+tmpNick+
										`</div>
									</div>
									<div class="player_chat_sys">`+
										chat
									+`</div>`);
			$('#chat_inner').scrollTop($('#chat_inner')[0].scrollHeight);
		}else if(event=="create")
		{
			var getRoomId = textData.split("#")[0].trim();
			var getNickname = textData.split("#")[1].trim();
			var roomName = $('#roomtitle_box').val();
			if(getNickname==nickname)
			{
				location.href = "./GameRoom.jsp?id="+getRoomId+"&name="+roomName;
			}
		}else if(event=="addRoom")
		{
			// 방 번호, 제목, 방장, 접속자, 최대접속자
			console.log(textData);
			var textsplit = textData.split("#");
			var getRoomId = textsplit[0].trim();
			var getRoomName = textsplit[1].trim();
			var getRoomMaker = textsplit[2].trim();
			var getNow = textsplit[3].trim();
			var getMax = textsplit[4].trim();
			$('#room_board > tbody:last').append(`<tr class="tr_style">
													<td style="width:80px;">`+getRoomId+`</td>
													<td style="width:450px;"><a href="./GameRoom.jsp?id=`+getRoomId+`">`+getRoomName+`</a></td>
													<td style="width:170px;">`+getRoomMaker+`</td>
													<td style="width:80px;">`+getNow+`/`+getMax+`</td>
												</tr>`);
		}else if(event=="deleteAll")
		{
			$('#room_board > tbody:last > tr').empty();
		}
	}
}
function onOpen(event) {
	var myinfo = id+"#"+nickname+"#"+level+"#"+profile;
	webSocket.send("Hello#123#add#123#"+myinfo);
	webSocket.send("Hello#123#welcome#123#"+"시스템"+"#"+"settings.png"+"#"+nickname+"님이 입장하셨습니다.");
}
function onError(event) {
	
}

function TextToArray(strData){
	strArray = strData.substring(1, strData.length-1).split(",");
	return strArray;
}

function getData(message){
	var splitData = message.split("#123#");// 0: 방 이름, 1: 이벤트, 2: 데이터
	return splitData;
}

function setPlayer(textData){
	var playerlist = TextToArray(textData);
	playerLevel = [];
	playerNickname = [];
	playerProfile = [];
	for(var i=0;i<playerlist.length;i++){
		playerLevel[i] = playerlist[i].split("#")[0].trim();
		playerNickname[i] = playerlist[i].split("#")[1].trim();
		playerProfile[i] = playerlist[i].split("#")[2].trim();
	}
}

function addPlayer(){
	$('#connector_inner').empty();
	for(var i=0;i<playerNickname.length;i++)
	{
		$('#connector_inner').append('<div class="player_connect">· Lv.'+playerLevel[i]+' '+playerNickname[i]+'</div>');
	}
	$('#connect_count').html("# 접속자 - "+playerNickname.length+"명");
}

function roomCreate(){
	webSocket.send("Hello#123#create#123#"+nickname);
}