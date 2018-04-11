package cn.tedu.dao;

import java.sql.Connection;
import java.util.List;

import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.SaleInfo;

public interface OrderDao {
/**
 * 向order表中添加一条订单信息的数据
 * @param con
 * @param order
 */
	void addOrder(Connection con, Order order);
	/**向orderItem表中添加一条订单项目信息的数据
	 * @param con：数据库连接对象
	 * @param item：封装了订单项目相关信息的对象
	 */
	void addOrderItem(Connection con, OrderItem item);
	/**
	 * 根据用户id从orders表查询userId对应的全部订单
	 * @param userId
	 * @return
	 */
	List<Order> findOrdersByUid(int userId);
	/**
	 * 根据用户id从orderitem表中查询userId对应的全部订单项目
	 * 该查询涉及到两张表（orders和orderitem）的关联查询
	 * @param userId 用户ID
	 * @return userId对应的全部订单项目
	 */
	List<OrderItem> findOrderItemsByUid(int userId);
	/**
	 * 根据订单的ID查询订单的详细信息(事务版)
	 * @param con 数据库连接对象
	 * @param oid 订单的ID
	 * @return 对应订单详细信息
	 */
	Order findOrderByOid(Connection con, String oid);
	/**
	 * 根据订单ID查询对应 下的所有订单项
	 * @param con 数据库连接对象
	 * @param oid 订单id
	 * @return 对应订单下的全部订单项
	 */
	List<OrderItem> findOrderItemsByOid(Connection con, String oid);
	/**
	 * 根据用户ID从orderitem表中删除对应订单下的全部订单项
	 * @param con 数据库连接对象
	 * @param oid 订单ID
	 */
	void deleteOrderItemsByOid(Connection con, String oid);
	/**
	 * 根据订单ID删除对应订单下的全部订单信息
	 * @param con 数据库连接对象
	 * @param oid 订单ID
	 */
	void deleteOrderByOid(Connection con, String oid);
	/**
	 * 根据订单的ID查询订单的详细信息(事务版)
	 * @param con 数据库连接对象
	 * @param oid 订单的ID
	 * @return 对应订单详细信息
	 */
	Order findOrderByOid(String oid);
	/**
	 * 修改订单的支付状态
	 * @param oid
	 * @param paystate
	 */
	void changePaystate(String oid, int paystate);
	/**
	 * 查询全部的销售榜单列表
	 * @return 全部的销售榜单列表
	 */
	List<SaleInfo> findSaleInfos();

}
