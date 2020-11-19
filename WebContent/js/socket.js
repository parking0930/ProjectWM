window.onload = function () {
	var chat = document.getElementById('chat');
	var textarea = document.getElementById('text_area');
}
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

function onMessage(event) {
	events = event.data;
	console.log(event.data)
}
function onOpen(event) {
	var myinfo = id+"#"+nickname+"#"+level+"#"+profile;
	webSocket.send("Hello#123#add#123#"+myinfo);
}
function onError(event) {
	
}
function send(){
	webSocket.send("ㅇㅇ");
}

function TextToArray(strData){
	strArray = strData.substring(1, strData.length-1).split(",");
	return strArray;
}

function getData(message){
	var splitData = message.split("#123#");// 0: 방 이름, 1: 이벤트, 2: 데이터
	return splitData;
}