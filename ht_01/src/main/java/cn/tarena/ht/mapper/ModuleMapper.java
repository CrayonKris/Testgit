package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.tarena.ht.pojo.Module;

public interface ModuleMapper {
	public List<Module> findAll();

	public void changeState(@Param("state")Integer i, @Param("moduleIds")String[] moduleIds);

	public void delete(String[] moduleIds);

	@Insert("insert into module_p (module_id,name,parent_id,ctype,state,remark,order_no) values"
			+ " (#{moduleId},#{name},#{parentmodule.moduleId},#{ctype},#{state},#{remark},#{orderNo})")
	public void save(Module module);

	public Module findOne(String moduleId);

	@Update("update module_p set name=#{name},parent_id=#{parentmodule.moduleId},ctype=#{ctype},state=#{state},remark=#{remark},order_no=#{orderNo} where module_id=#{moduleId}")
	public void update(Module module);

	@Select("select module_id from role_module_p where role_id=#{roleId} ")
	public List<String> findModuleByRoleId(String roleId);

	public void deleteRoleModule(String[] moduleIds);
}
