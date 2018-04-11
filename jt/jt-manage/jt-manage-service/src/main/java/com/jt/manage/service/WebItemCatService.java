package com.jt.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jt.common.vo.ItemCatData;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.pojo.ItemCat;

@Service
public class WebItemCatService extends BaseService<ItemCat> {
	
/*	public ItemCatResult queryItemCat(){
	//准备返回给controller的对象
	ItemCatResult result = new ItemCatResult();
	//获取所有的ItemCat封装到一个list中
	//然后对list的数据进行加工处理
	List<ItemCat> cats = super.queryAll();
	//这里我们继续引入一个map对象，配套一个list循环拼接的方式完成
	//这种3层结构的封装ItemCatData
	Map<Long, List<ItemCat>> map = new HashMap<Long,List<ItemCat>>();
	//第一步的封装，将一个父类对应id作为key保存在map中，value保存了这个ID的下一级分类的ItemCat
	for (ItemCat itemCat : cats) {
		//从当前的itemCat中获取的parentId
		Long parentId = itemCat.getParentId();
		//判断当前获取的parentId 在map中有没有
		if (!map.containsKey(parentId)) {
			map.put(parentId, new ArrayList<ItemCat>(0));
		}
		map.get(parentId).add(itemCat);
	}
	//构建三级菜单结构
	List<ItemCat> itemCatList1 = map.get(0L);
	//为一级菜单构建所有子菜单
	List<ItemCatData> itemCatDataList1 = new ArrayList<ItemCatData>();
	for (ItemCat itemCat1 : itemCatList1) {
		//添加数据 u,n,i
		ItemCatData itemCatData1 = new ItemCatData();
		itemCatData1.setUrl("/products/"+itemCat1.getId()+".html");
		itemCatData1.setName("<a href='/products/"+itemCat1.getId()+".html'>"
				+ itemCat1.getName()+"</a>");
		//遍历二级菜单，构建二级结构
		List<ItemCatData> itemCatDataList2 = new ArrayList<ItemCatData>();
		for (ItemCat itemCat2 : map.get(itemCat1.getId())) {
			ItemCatData itemCatData2 = new ItemCatData();
			itemCatData2.setUrl("/products/"+itemCat2.getId()+".html");
			itemCatData2.setName("<a href='/products/"+itemCat2.getId()+".html'>"
					+ itemCat2.getName()+"</a>");
			//遍历三 级菜单  构建三级结构
			List<String> itemCatDataList3 = new ArrayList<String>();
			for (ItemCat itemCat3 : map.get(itemCat2.getId())) {
				itemCatDataList3.add("/products/"+itemCat3.getId()+".html"+itemCat3.getName());
			}
			itemCatData2.setItems(itemCatDataList3);
			itemCatDataList2.add(itemCatData2);
		}
		itemCatData1.setItems(itemCatDataList2);
		itemCatDataList1.add(itemCatData1);//首页只能返回14条数据
		if (itemCatDataList1.size()>=14) {
			break;
		}
		
	}
	result.setItemCats(itemCatDataList1);
	return result;
	}*/
	public ItemCatResult queryItemCat() {
		//准备返回给controller的对象
		ItemCatResult result=new ItemCatResult();
		//获取所有的itemCat,封装到一个list中
		//然后对这个list的数据进行加工处理
		List<ItemCat> cats=super.queryAll();
		//这里我们继续引入一个map对象,配合一个list循环拼接的方式完成
		//这种3层结构的封装.ItemCatData
		Map<Long,List<ItemCat>> map=new HashMap<Long,List<ItemCat>>();
		//第一步的封装,将一个父类对应id作为key保存在map中,value保存了这个
		//id对应的下一级分类的ItemCat
		for (ItemCat itemCat : cats) {
			//从当前的itemCat中获取parentID
			Long parentId=itemCat.getParentId();
			//判断当前获取的parentId,在map中有没有,
			if(!map.containsKey(parentId)){
				map.put(parentId, new ArrayList<ItemCat>(0));
			}
			map.get(parentId).add(itemCat);	
		}
		//构建三级菜单结构
		List<ItemCat> itemCatList1=map.get(0L);
		//为一级菜单构建所有子菜单
		List<ItemCatData> itemCatDataList1=new ArrayList<ItemCatData>();
		for(ItemCat itemCat1:itemCatList1){
			//添加数据,u n i
	ItemCatData itemCatData1 =new ItemCatData();
	itemCatData1.setUrl("/products/"+itemCat1.getId()+".html");
	itemCatData1.setName("<a href='/products/"+itemCat1.getId()+".html'>"+
	itemCat1.getName()+"</a>");
	//做i
	//遍历二级菜单,构建二级结构
	List<ItemCatData> itemCatDataList2=new ArrayList<ItemCatData>();
	for(ItemCat itemCat2 : map.get(itemCat1.getId())){
		ItemCatData itemCatData2 =new ItemCatData();
		itemCatData2.setUrl("/products/"+itemCat2.getId()+".html");
		itemCatData2.setName("<a href='/products/"+itemCat2.getId()+".html'>"+
		itemCat2.getName()+"</a>");
		//遍历三级菜单,构建三级结构
		List<String> itemCatDataList3=new ArrayList<String>();
		for(ItemCat itemCat3:map.get(itemCat2.getId())){
			itemCatDataList3.add("/products/"+itemCat3.getId()+".html|"+
			itemCat3.getName());
		}
		itemCatData2.setItems(itemCatDataList3);
		itemCatDataList2.add(itemCatData2);
		}
		itemCatData1.setItems(itemCatDataList2);
		itemCatDataList1.add(itemCatData1);//首页的菜单只能返回14条数据
		if(itemCatDataList1.size()>14) {
			break;}
		}
		result.setItemCats(itemCatDataList1);
		return result;
	}

}
