package com.jt.web.serivce;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.User;

@Service
public class UserService {
	@Autowired
	private HttpClientService httpClientService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	public String doRegister(User user) throws Exception{
		//发起http请求的连接地址
		String url="http://sso.jt.com/user/register";
		//封装http请求的数据
		Map<String, String> params =new HashMap<String, String>();
		//准备参数
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("phone", user.getPhone());
		params.put("email", user.getEmail());
		String jsonData = httpClientService.doPost(url, params,"utf-8");
		//截取
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		String username = jsonNode.get("data").asText();
		return username;
	}
	public String doLogin(User user) throws Exception{
		//调用httpclient
		String url="http://sso.jt.com/user/login";
		//封装传递的数据--> 一个是u,一个是p
		Map<String, String> params = new HashMap<String,String>();
		params.put("u", user.getUsername());
		params.put("p", user.getPassword());
		String jsonData = httpClientService.doPost(url, params,"utf-8");
		//从sysResult结果中截取ticket
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		String ticket = jsonNode.get("data").asText();
		return ticket;
	}
}
