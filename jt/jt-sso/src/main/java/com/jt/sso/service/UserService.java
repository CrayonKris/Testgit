package com.jt.sso.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;
@Service
public class UserService extends BaseService<User> {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisService redisService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	public Boolean check(String param,Integer type){
		//构建一个传递给userMapper的map对象，将param传递给他
		Map<String, String> map=new HashMap <String, String>();
		//根据type的数值完成map参数的封装
		if (type==1) {
			//useraname
			map.put("name", "username");
		}else if(type==2) {
			map.put("name", "phone");
		}else {
			map.put("name", "email");
		}
		map.put("val", param);
		//调用check方法查询
		Integer i=userMapper.check(map);
		if (i!=0) {
			return true;
		} else {
			return false;
		}
	}
	//注册的接口方法
	public String register(User user){
		//对传递过来的数据进行处理，密码，保存在数据库中不能是明文
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		//减小数据库压力
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		//由于前台的注册邮箱和手机号没有做同时处理，这里只有一个唯一性
		//校验，email是null，不可以是null，数据库对三个字段做了唯一性校验
		//username，phone，email不能默认插入email=null
		//拿一个前台做过的唯一性校验来做email 用phone
		user.setEmail(user.getPhone());
		userMapper.insertSelective(user);
		return user.getUsername();
	}
	//用户登录
	public String login(String username,String password){
		//这里有个问题,用哪个方法查询数据
		//byWhere会对不是null的数据进行查询条件的拼接
		User _user =new User();
		_user.setUsername(username);
		//按照用户名查询
		User user=super.queryByWhere(_user);
		//逻辑判断是否有返回的user
		if (null!=user) {
			//密码的对比
			String newpassword = DigestUtils.md5Hex(password);
			if (newpassword.equals(user.getPassword())) {
				//是系统用户,登录成功,写redis
				try {
					String userJson=MAPPER.writeValueAsString(user);
					//生成key ticket
					String ticket = DigestUtils.md5Hex("JT_TICKET"
							+System.currentTimeMillis()+username);
					redisService.set(ticket, userJson);
					return ticket;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	//查询用户信息
	public String queryByTicket(String ticket){
		String userJson=redisService.get(ticket);
		return userJson;
	}
	
}
