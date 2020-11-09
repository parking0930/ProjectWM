window.onload = function(){
}
var status = 0;
function purchase(item){
	if(item == "nickname"){
		$("#black_background").fadeIn(500);
		$("#popup_contents").fadeIn(500);
	}else if(item=="levelup"){
		location.href = "./purchase?item="+item;
	}
}
function exitPop(){
		$("#black_background").fadeOut(500);
		$("#popup_contents").fadeOut(500);
}
function checkNick(){
	var nickname = document.getElementById("changenick").value;
	$.ajax({
         url:'./checknick',
         type:'post',
         data: {changeNick:nickname}
      }).done(function(data){
		if(data.trim()=="O"){
			alert("사용 가능한 닉네임입니다.");
			status = 1;
		}else{
			alert("사용 불가능한 닉네임입니다.");
			status = 0;
		}
      });
}
function submit_nick(){
	if(status==1){
		document.getElementById("frm").submit();
	}else{
		alert("닉네임 중복 검사 후 시도하세요.");
	}
}