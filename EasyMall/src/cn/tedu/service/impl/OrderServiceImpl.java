package cn.tedu.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tedu.dao.OrderDao;
import cn.tedu.dao.ProdDao;
import cn.tedu.entity.Order;
import cn.tedu.entity.OrderInfo;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.Product;
import cn.tedu.entity.SaleInfo;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;
import cn.tedu.utils.DaoUtils;

public class OrderServiceImpl implements OrderService {

	//创建Order对象以及ProdDao对象
	private ProdDao prodDao=BasicFactory.getFactory().getInstance(ProdDao.class);
	private OrderDao orderDao=BasicFactory.getFactory().getInstance(OrderDao.class);
	private Connection con=null;
	public void addOrder(Order order, List<OrderItem> oiList) throws MsgException{

		try {
			//1.获取数据库连接
			con=DaoUtils.getConn();
			//2.开启事务
			con.setAutoCommit(false);
			//2.添加向orders表中添加一条数据的方法
			orderDao.addOrder(con, order);
			//3.遍历oiList,逐一进行操作
			for (OrderItem item : oiList) {
				//4判断库存是否存在，不足抛出自定义异常
				//4.1根据商品id查询商品的详细信息
				Product prod =prodDao.findProdById(con, item.getProduct_id());
				//4.2判断库存是否充足
				if (prod.getPnum()<item.getBuynum()) {
					throw new MsgException("库存不足！id="+prod.getId()+",name"+prod.getName()
							+",pnum"+prod.getPnum());
				}else{
					prodDao.changePnum(con, prod.getId(), prod.getPnum()-item.getBuynum());
				}
				//6向orderitem表中添加一条信息
				orderDao.addOrderItem(con,item);
			}
			//7提交事务
			con.commit();
		} catch (MsgException me) {
			//7回滚事务
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//接着将e1抛给OrderAddServlet
			throw me;
		} catch (SQLException e) {
			e.printStackTrace();
			//7回滚事务
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			//8关闭数据库连接
			DaoUtils.close(con, null, null);
		}
		
	}
	public List<OrderInfo> findOrderInfosByUid(int userId) {
		//1.定义集合对象保存结果集
		List<OrderInfo> resultList=new ArrayList<OrderInfo>();
		//2.从orders表查询userId对应全部订单
		List<Order> orderList=orderDao.findOrdersByUid(userId);
		//3.从orderitem表查询userId对应全部订单向项
		List<OrderItem> itemList=orderDao.findOrderItemsByUid(userId);
		//4.遍历orderList
		for (Order order : orderList) {
			//5.创建orderinfo对象
			OrderInfo info=new OrderInfo();
			//6.为info的属性赋值
			//6.1为属性order赋值
			info.setOrder(order);
			//6.2为属性map赋值
			//6.2.1创建Map<Product,Integer>map
			Map<Product,Integer> map=new HashMap<Product, Integer>();
			//6.3遍历itemList
			for (OrderItem item : itemList) {
				if (item.getOrder_id().equals(order.getId())) {
					Product prod=prodDao.findProdById(item.getProduct_id());
					map.put(prod, item.getBuynum());
				}
			}
			//6.2.3将map保存到info的map属性
			info.setMap(map);
			//7.将info添加到resultList中
			resultList.add(info);
		}
		return resultList;
	}
	public void deleteOrderByOid(String oid) throws MsgException {
		//1.1获取数据库连接
		try {
			con=DaoUtils.getConn();
			//1.2开启事务
			con.setAutoCommit(false);
			//2.
			//2.1
			Order order=orderDao.findOrderByOid(con,oid);
			//2.2如果不是未支付的订单抛出自定义异常
			if (order==null) {
				throw new MsgException("该订单已被删除！");
			}
			if (order.getPaystate()!=0) {
				throw new MsgException("只有未支付的订单才能删除！");
			}
			//3.根据订单查询该订单下所有的订单项
			List<OrderItem> itemList=orderDao.findOrderItemsByOid(con,oid);
			//4遍历itemList，还原每一个商品的库存
			for (OrderItem item : itemList) {
				prodDao.updatePnum(con,item.getProduct_id(),item.getBuynum());
			}
			//5.根据订单ID删除orderitem表中所有的订单项
			orderDao.deleteOrderItemsByOid(con,oid);
			//6.根据订单ID删除orders表中的一条记录
			orderDao.deleteOrderByOid(con,oid);
			//7.提交事务
			con.commit();
		} catch(MsgException me){
			try {
				//8.回滚事务
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//9.将自定义异常继续抛给调用该方法处
			throw me;
		}catch (SQLException e) {
			e.printStackTrace();
			//回滚事务
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DaoUtils.close(con, null, null);
		}
		
	}
	public Order findOrderByOid(String oid) {
		return orderDao.findOrderByOid(oid);
	}
	public void changePaystate(String oid, int paystate) {
		orderDao.changePaystate(oid,paystate);
	}
	public List<SaleInfo> findSaleInfos() {
		return orderDao.findSaleInfos();
	}
}
