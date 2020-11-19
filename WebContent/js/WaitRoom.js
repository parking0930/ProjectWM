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

$('#chat_textinput').keydown(function(key){
	if(key.keyCode==13)
	{
		if($('#chat_textinput').val()!='')
		{
			$('#chat_textinput').val('');
			$('#chat_textinput').focus();
		}
	}
});