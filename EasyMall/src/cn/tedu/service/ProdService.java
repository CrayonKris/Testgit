package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Product;

public interface ProdService {

	/**
	 * 查询全部商品
	 * @return 全部商品对应集合对象
	 * */
	List<Product> findAll();

	/**修改商品的库存
	 * @param id:id
	 * @param pnum：修改后的商品数据
	 * @return true：表示修改成功 false表示修改过失败
	 * 
	 * */
	boolean changePnum(String id, int pnum);

	/**删除商品
	 * @param id:商品id
	 * @return true 表示删除成功 false 表示删除失败
	 * */
	boolean deleteProdById(String id);

	/**根据关键字查询符合条件的商品
	 * @param name:商品名称的关键字
	 * @param cate:商品分类关键字
	 * @param min :价格区间的最小值
	 * @param max:价格区间的最大值
	 * @return 符合添加的商品集合
	 * */
	public List<Product> findAllByKey(String name, String cate, Double min, Double max);

	/**根据商品ID查询商品的详细信息的对象
	 * @param id:商品ID
	 * @return
	 */
	public Product findProdById(String id);

}
