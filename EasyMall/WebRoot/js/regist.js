/*注册表单的js校验*/
function checkForm(){
	//非空校验
	var res1=checkNull("username","用户名不能为空！");
	var res2=checkNull("password","密码不能为空！");
	var res3=checkNull("password2","确认密码不能为空！");
	var res4=checkNull("nickname","昵称不能为空！");
	var res5=checkNull("email","邮箱不能为空！");
	var res6=checkNull("valistr","验证码不能为空！");
	//两次密码是否一致
	var res7=checkPassword("password","两次密码不一致");
	var res8=checkEmail("email","邮箱格式不正确~~");
	//邮箱格式是否正确
	return res1 && res2 && res3 && res4 && res5 && res6 && res7 && res8;
}
/*邮箱格式是否正确*/
function checkEmail(name,msg){
	//获取邮箱
	var email=$("input[name='"+name+"']").val().trim();
	var regExp=/^\w+@\w+(\.[a-z]+)+$/;
	checkNull("email","邮箱不能为空！");
	if (email!="" && !regExp.test(email)) {
		setMsg(name,msg);
		return false;
	}
	return true;
}
//检查两次密码是否一致
function checkPassword(name,msg){
	//获取密码
	var psw1=$("input[name='"+name+"']").val().trim();
	var psw2=$("input[name='"+name+"2']").val().trim();
	checkNull("password2","确认密码不能为空");
	if (psw1!="" && psw2!="" && psw1!=psw2) {
		setMsg(name+"2",msg);
		return false;
	}
	return true;
}

//检查输入项是否为空
function checkNull(name,msg){
	var value =$("input[name='"+name+"']").val().trim();
	//清空上一次的提示消息
	setMsg(name,"");
	if(value==""){
		setMsg(name, msg);
		return false;
	}
	return true;
}


//在校验失败时设置错误提示消息
function setMsg(name, msg) {
	var $oInp =$("input[name='"+name+"']");
	var $oSpan =$oInp.nextAll("span");
	$oSpan.html(msg);
	$oSpan.css("color","red");
	
}