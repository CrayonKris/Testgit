package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Order;
import cn.tedu.entity.OrderInfo;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.SaleInfo;
import cn.tedu.exception.MsgException;

public interface OrderService {
	/**添加订单相关信息（向orders添加一条，向orderitem表中
	 * 添加oiList.size()条数据，修改products表对应商品的库存）
	 * @param order：封装了订单信息的对象
	 * @param oiList：封装了订单项相关信息对象的集合对象
	 * @throws MsgException遇到添加的商品库存不足抛出异常
	 */
	void addOrder (Order order, List<OrderItem> oiList) throws MsgException;
/**
 * 根据用户ID查询对应用户的全部订单信息，订单列表查询，
 * @param userId
 * @return 对应用户的全部订单信息
 */
	List<OrderInfo> findOrderInfosByUid(int userId);
	/**
	 * 根据订单ID删除该订单相关信息，并还原该订单购买的所用商品库存
	 * @param oid 商品订单ID 
	 * @throws MsgException 删除非“未支付”订单抛出异常
	 */
void deleteOrderByOid(String oid) throws MsgException;
/**
 * 根据订单ID获取对应订单的相关信息（来自于orders）
 * @param oid:订单ID
 * @return 返回对应的订单
 */
	Order findOrderByOid(String oid);
	/**
	 * 修改订单支付状态  0表示未支付，1表示已支付
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
