  function nextBtn()
  {
    var chk = document.getElementsByName("service");
    for(var i=0;i<chk.length;i++){
      if (chk[i].checked==false)
      {
        alert("모든 약관에 동의해야합니다.")
        return;
      }
    }
    location.replace('./registerForm.jsp');
  }