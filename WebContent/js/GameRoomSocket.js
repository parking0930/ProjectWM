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
	
}

$('#chat_text_all').keydown(function(key){
	if(key.keyCode==13)
	{
		if($('#chat_text_all').val()!='')
		{
			var roomId = getParameter("id");
			webSocket.send(roomId+"#123#chat#123#"+level+"#"+nickname+"#"+profile+"#"+$('#chat_text_all').val());
			$('#chat_text_all').val('');
			$('#chat_text_all').focus();
		}
	}
});
//////////////////소켓 통신 부분//////////////////
var playerLevel = [];
var playerNickname = [];
var playerProfile = [];
var fontstate = 0; // 0은 낮, 1은 밤
var myjob = "";

function onMessage(message) {
	var eventData = getData(message.data);
	var roomId = eventData[0];
	var event = eventData[1];
	var textData = eventData[2];
	if(roomId==getParameter("id")) // 이 방에 온 메시지이면
	{
		if(event=="add" || event=="out")
		{
			//  현재인원, 최대인원, 인원데이터 순
			var textsplit = textData.split("@");
			setPlayer(textsplit[2]);
			addPlayer(textsplit[0], textsplit[1]);
		}else if(event=="chat")
		{
			var tmpLevel = textData.split("#")[0].trim();
			var tmpNick = textData.split("#")[1].trim();
			var tmpProfile = textData.split("#")[2].trim();
			var chat = textData.split("#")[3].trim();
			
			$('#chat_all_show').append(`<div class="player_chat_info">
										<div class="player_chat_img_div">
											<img class="player_chat_img" src="image/profile/`+tmpProfile+`">
										</div>
										<div class="player_chat_top">
											Lv.`+tmpLevel+` `+tmpNick+`
										</div>
									</div>
									<div class="player_chat_all">`+
										chat
									+`</div>`);
			$('#chat_all_show').scrollTop($('#chat_all_show')[0].scrollHeight);
			if(fontstate==0) $('.player_chat_top').css({color:"black"});
			else $('.player_chat_top').css({color:"white"});
		}else if(event=="welcome" || event=="bye")
		{
			var tmpNick = textData.split("#")[0].trim();
			var tmpProfile = textData.split("#")[1].trim();
			var chat = textData.split("#")[2].trim();
			$('#chat_all_show').append(`<div class="player_chat_info">
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
			$('#chat_all_show').scrollTop($('#chat_all_show')[0].scrollHeight);
		}else if(event=="nowFull"){
			location.replace("./WaitRoom.jsp");
		}else if(event=="start"){
			setTimeout(gameStart,1000);
		}else if(event=="job"){
			var textsplit = textData.split("@");
			var receiveNick = textsplit[0];
			if(receiveNick==nickname){
				myjob = textsplit[1];
			}
		}
	}
}
function onOpen(event) {
	var myinfo = id+"#"+nickname+"#"+level+"#"+profile;
	var roomId = getParameter("id");
	if(roomId=="")	location.replace("./WaitRoom.jsp");
	var roomName = getParameter("name");
	webSocket.send(roomId+"#123#add#123#"+myinfo+"#"+roomName);
	webSocket.send(roomId+"#123#welcome#123#"+"시스템"+"#"+"settings.png"+"#"+nickname+"님이 입장하셨습니다.");
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

function addPlayer(NowIn, MaxIn){
	$('#connector_inner').empty();
	for(var i=0;i<playerNickname.length;i++)
	{
		$('#connector_inner').append('<div class="player_connect">· Lv.'+playerLevel[i]+' '+playerNickname[i]+'</div>');
	}
	$('#connect_count').html("# 접속자 - "+NowIn+"/"+MaxIn);
}

///////////////////////////////////////////////////////////////////////////
function getParameter(input){
	var params = location.search.substr(location.search.indexOf("?")+1);
	var result = "";
	params = params.split("&");
	for(var i=0;i<params.length;i++){
		tmp = params[i].split("=");
		if(tmp[0]==input) result = tmp[1];
	}
	return decodeURI(result);
}

function showNight(){
	fontstate = 1;
	$("#background_div").fadeIn(2000);
	$('.player_chat_top').css({color:"white"});
	document.getElementById('my_info_text').style.color = "white";
	document.getElementById('my_info_real').style.color = "white";
	document.getElementById('nowTime').style.color = "white";
	document.getElementById('exit_btn_font').style.color = "white";
	document.getElementById('chat_die_top').style.background = "white";
	document.getElementById('chat_die_font').style.color = "black";
	setTimeout(function(){
		sysMessage("밤이 되었습니다.");
		$('.player_chat_top').css({color:"white"});
		setTimeout(function(){
			sysMessage("당신의 직업은 '"+myjob+"' 입니다.");
		},1000);
	},2000);
}
function hideNight(){
	fontstate = 0;
	$("#background_div").fadeOut(2000);
	$('.player_chat_top').css({color:"black"});
	document.getElementById('my_info_text').style.color = "black";
	document.getElementById('my_info_real').style.color = "black";
	document.getElementById('nowTime').style.color = "black";
	document.getElementById('exit_btn_font').style.color = "black";
	document.getElementById('chat_die_top').style.background = "black";
	document.getElementById('chat_die_font').style.color = "white";
}

function gameStart(){
	sysMessage("게임에 충분한 인원이 모였습니다.");
	setTimeout(function(){
		sysMessage("잠시후 게임이 시작됩니다.");
	},2000);
	count = 5;
	timerId = setInterval(function(){
		count -= 1;
		if(count==0){
			clearInterval(timerId);
			$('#chat_all_show').empty();
			setTimeout(showNight,1000);
		}
	}, 1000);
}

function sysMessage(msg){
	$('#chat_all_show').append(`<div class="player_chat_info">
										<div class="player_chat_img_div">
											<img class="player_chat_img" src="image/profile/settings.png">
										</div>
										<div class="player_chat_top">
											시스템
										</div>
									</div>
									<div class="player_chat_sys">`+
										msg
									+`</div>`);
	$('#chat_all_show').scrollTop($('#chat_all_show')[0].scrollHeight);
	if(fontstate==0) $('.player_chat_top').css({color:"black"});
	else $('.player_chat_top').css({color:"white"});
}