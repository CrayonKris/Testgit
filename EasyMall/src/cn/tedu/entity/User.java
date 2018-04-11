package cn.tedu.entity;

import cn.tedu.exception.MsgException;
import cn.tedu.utils.WebUtils;

public class User {
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String password2;
	private String valistr;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void check() throws MsgException {
		// >>非空校验
		if (WebUtils.isNull(username)) {
			// 如果用户名为空，跳转回注册页面，提示用户“用户名不能为空”---带信息到页面只用转发
			throw new MsgException("用户名不能为空！");
		}
		if (WebUtils.isNull(password)) {
			throw new MsgException("密码不能为空！");

		}
		if (WebUtils.isNull(nickname)) {
			throw new MsgException("昵称不能为空!");
		}
		if (WebUtils.isNull(email)) {
			throw new MsgException("邮箱不能为空!");
		}
		if (WebUtils.isNull(valistr)) {
			throw new MsgException("验证码不能为空!");
		}

		// >>两次密码是否一致校验
		if (!password.equals(password2)) {
			throw new MsgException("两次密码不一致");
		}
		// >>邮箱格式是否正确
		// 邮箱正则："^\\w+@\\w+(\\.[a-z]+)+$"
		if (!email.matches("^\\w+@\\w+(\\.[a-z]+)+$")) {
			throw new MsgException("邮箱格式不正确！");
		}
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getValistr() {
		return valistr;
	}

	public void setValistr(String valistr) {
		this.valistr = valistr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
