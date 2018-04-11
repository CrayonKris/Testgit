package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.tarena.ht.pojo.Role;

public interface RoleMapper {
	//使用@select注解形式在注解里面写sql语句 通过范围值来控制结果集的封装
	//当表的增删改查时可能会用到注解形式，如果使用了注解之后，需要把mapper映射文件里的sql去掉
	@Select("select * from role_p order by order_no")
	public List<Role> findAll();

	public void delete(String[] roleIds);

	@Insert("insert into role_p(role_id,name,remarks,order_no,create_time) "
			+ "values(#{roleId},#{name},#{remarks},#{orderNo},#{createTime})")
	public void tosave(Role role);

	@Select("select * from role_p where role_id=#{roleId}")
	public Role findOne(String roleId);

	@Update("update role_p set name=#{name},"
			+ "remarks=#{remarks},order_no=#{orderNo},create_time=#{createTime} where role_id=#{roleId}")
	public void update(Role role);

	
	public void saveRoleModule(@Param("roleId")String roleId, @Param
			("moduleIds")String[] moduleIds);
	//xml文件

	@Delete("delete from role_module_p where role_id=#{roleId}")
	public void deleteModules(String roleId);

	public void deleteByUserIds(String[] userIds);

	public void deleteUserRole(String[] roleIds);

	public void deleteModuleRole(String[] roleIds);

}
