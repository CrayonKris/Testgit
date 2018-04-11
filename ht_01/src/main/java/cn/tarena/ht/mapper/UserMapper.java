package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.tarena.ht.pojo.User;

public interface UserMapper {
	public List<User> findAll();

	public void changeState(@Param("state")int i, @Param("userIds")String[] userIds);

	public void delete(String[] userIds);

	public User findOne(String userId);

	public void saveUser(User user);

	public void update(User user);

	@Select("select role_id from role_user_p where user_id=#{userId} ")
	public List<String> findRoles(String userId);

	@Delete("delete from role_user_p where user_id=#{userId}")
	public void deleteRoles(String userId);

	public void saveUserAndRole(@Param("userId")String userId, @Param("roleIds")String[] roleIds);

	public User login(@Param("username")String username, @Param("password")String password);

	public void deleteUserDept(String[] deptIds);
	
}
