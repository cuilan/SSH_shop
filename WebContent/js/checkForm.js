/**
 * 校验注册表单
 * 
 * @returns {Boolean}
 */
function return_checkForm() {
	// 校验用户名
	var username = document.getElementById("username").value.trim();
	if (username == null || username == "") {
		alert("用户名不能为空！");
		return false;
	}
	// 校验密码
	var password = document.getElementById("password").value.trim();
	if (password == null || password == "") {
		alert("密码不能为空！");
		return false;
	}
	// 校验确认密码
	var repassword = document.getElementById("repassword").value.trim();
	if (repassword != password) {
		alert("两次密码不一致！");
		return false;
	}
	// 校验邮箱
	var email = document.getElementById("email").value.trim();
	if (email == null || email == "") {
		alert("请输入您的邮箱！");
		return false;
	}
	// 校验真实姓名
	var true_name = document.getElementById("true_name").value.trim();
	if (true_name == null || true_name == "") {
		alert("请输入您的真实姓名！");
		return false;
	}
	// 校验电话
	var phone = document.getElementById("phone").value.trim();
	if (phone == null || phone == "") {
		alert("请输入您的电话号码！");
		return false;
	}
	// 校验地址
	var addr = document.getElementById("addr").value.trim();
	if (addr == null || addr == "") {
		alert("请输入您的真实住址！");
		return false;
	}
	return true;
}

/**
 * AJAX校验用户是否已经存在
 */
function checkUsername() {
	// 获取文本框的值
	var username = document.getElementById("username").value.trim();
	// 1.创建异步交互对象
	var xhr = ajaxXmlHttp();
	// 2.设置监听
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				document.getElementById("span1").innerHTML = xhr.responseText;
			}
		}
	}
	// 3.打开连接，添加时间戳
	xhr.open("GET",
			"${pageContext.request.contextPath}/user_findByName.action?time="
					+ new Date().getTime() + "&username=" + username, true);
	// 4.发送
	xhr.send(null);
}

/**
 * 获取AJAX对象
 * 
 * @returns {___anonymous1373_1379}
 */
function ajaxXmlHttp() {
	var xmlHttp;
	try {
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
	return xmlHttp;
}

/**
 * 实现点击换验证码功能
 */
function change() {
	var img1 = document.getElementById("checkImg");
	img1.src = "${pageContext.request.contextPath}/checkImg.action?time=" + new Date().getTime();
}

/**
 * 校验登陆表单
 */
function return_checkLoginForm() {
	// 校验用户名
	var username = document.getElementById("username").value.trim();
	if (username == null || username == "") {
		alert("用户名不能为空！");
		return false;
	}
	// 校验密码
	var password = document.getElementById("password").value.trim();
	if (password == null || password == "") {
		alert("密码不能为空！");
		return false;
	}
}
