$(document).ready(function(){
	document.getElementById("menuslider").style.display="none";
	$("#menubar ul").hover(function(){
			$("#menuslider").slideDown(100);
	});
	
	$("#menuslider").hover(function(){
		$("#menuslider").slideDown();
	});
		
	$("#menuslider").mouseleave(function(){
			$("#menuslider").slideUp(100);
	});
});
