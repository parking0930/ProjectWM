var timerElement = document.getElementById("nowTime");
function NowTime() {
    var date = new Date();
    var month = date.getMonth();
    var clockDate = date.getDate();
    var day = date.getDay();
    var week = ['일', '월', '화', '수', '목', '금', '토'];
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    timerElement.innerText = `${month+1}/${clockDate}(${week[day]}) ` +
    `${hours < 10 ? `0${hours}` : hours}시 ${minutes < 10 ? `0${minutes }`  : minutes }분(${seconds < 10 ? `0${seconds }`  : seconds }s)`;
}

function NowTimeinit() {
	NowTime();
	setInterval(NowTime, 1000);
}

NowTimeinit();

function showPopup(){
	$("#black_background").fadeIn(500);
	$("#popup_contents").fadeIn(500);
}
function hidePopup(){
	$("#black_background").fadeOut(500);
	$("#popup_contents").fadeOut(500);
}
