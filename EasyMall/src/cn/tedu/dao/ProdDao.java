package cn.tedu.dao;

import java.sql.Connection;
import java.util.List;

import cn.tedu.entity.Product;

public interface ProdDao {
	/**查询全部商品
	 * @return 全部商品的集合对象
	 * */
	public List<Product> findAll();

	/**修改商品的库存
	 * @param id:商品 id
	 * @param pnum
	 * */
	public int changePnum(String id, int pnum);

	/**根据商品id删除商品信息
	 * @param id 商品id
	 * @return 影响的行数
	 * */
	public int deletProdById(String id);

	/**根据关键字查询符合条件所有商品
	 * @param name：名称关键字
	 * @param cate：分类关键字
	 * @param min：价格区间的最小值
	 * @param max：价格区间的最大值
	 * @return 符合条件的所有商品
	 */
	public List<Product> findAllByKey(String name, String cate, Double min,
			Double max);

	/**根据商品ID查询商品信息（无事务版）
	 * 
	 * @param id 商品ID
	 * @return 对应商品的商品信息
	 */
	public Product findProdById(String id);
	/**根据商品id查询商品的详细信息（事务版）
	 * @param con：数据库连接对象
	 * @param product_id：商品id
	 * @return 对象商品的详细信息
	 */
	public Product findProdById(Connection con, String porduct_id);
	/**修改商品的库存数量（事务版）
	 * @param con:数据库连接对象
	 * @param id：商品的id
	 * @param pnum:修改后的商品库存
	* @return ：返回影响的行数
	 */
	public int changePnum(Connection con, String id, int pnum);

	/**
	 * 还原商品的库存(事务版)
	 * @param con 数据库连接对象
	 * @param porduct_id 商品的ID
	 * @param order_id 
	 * @param buynum 还原的增量
	 * @return 影响的行数
	 */
	public int updatePnum(Connection con,String pid,
			int buynum);


}
