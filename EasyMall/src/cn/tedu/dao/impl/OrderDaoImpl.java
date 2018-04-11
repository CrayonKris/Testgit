package cn.tedu.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.tedu.dao.OrderDao;
import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.SaleInfo;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.BeanListHandler;
import cn.tedu.utils.DaoUtils;

public class OrderDaoImpl implements OrderDao {

	public void addOrder(Connection con, Order order) {
		String sql="insert into orders(id,money,paystate,ordertime," +
				"receiverinfo,user_id) values (?,?,?,?,?,?)";
		try {
			DaoUtils.update(con,sql, order.getId(),order.getMoney(),order.getPaystate(),
					order.getOrdertime(),order.getReceiverinfo(),order.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addOrderItem(Connection con, OrderItem item) {
		String sql="insert into orderitem(order_id,product_id,buynum)" +
				" values(?,?,?)";
		try {
			DaoUtils.update(con,sql,item.getOrder_id(),item.getProduct_id(),item.getBuynum());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Order> findOrdersByUid(int userId) {
		String sql="select * from orders where user_id=?";
		try {
		 return DaoUtils.query(sql, new BeanListHandler<Order>(Order.class), userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Order>();
		}
	}

	public List<OrderItem> findOrderItemsByUid(int userId) {
		String sql="select oi.* from orders od,orderitem oi" +
				" where od.id=oi.order_id and od.user_id=?";
		try {
			return DaoUtils.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<OrderItem>();//∑¿÷πø’÷∏’Î≥ˆœ÷
		}
	}

	public Order findOrderByOid(Connection con, String oid) {
		String sql="select * from orders where id=?";
		try {
			return DaoUtils.query(con, sql,new BeanHandler<Order>(Order.class),oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public List<OrderItem> findOrderItemsByOid(Connection con, String oid) {
		String sql="select * from orderitem where order_id=?";
		try {
			return DaoUtils.query(con, sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<OrderItem>();
		}
	}

	public void deleteOrderItemsByOid(Connection con, String oid) {
		String sql="delete from orderitem where order_id=?";
		try {
			DaoUtils.update(con, sql, oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteOrderByOid(Connection con, String oid) {
		String sql="delete from orders where id=?";
		try {
			DaoUtils.update(con, sql, oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Order findOrderByOid(String oid) {
		String sql="select * from orders where id=?";
		try {
			return DaoUtils.query(sql,new BeanHandler<Order>(Order.class),oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void changePaystate(String oid, int paystate) {
		String sql="update orders set paystate=? where id=?";
		try {
			DaoUtils.update(sql, paystate,oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<SaleInfo> findSaleInfos() {
		String sql="SELECT pd.id prod_id,pd.name prod_name," +
				"SUM(oi.buynum) sale_num" +
				" FROM orderitem oi,products pd,orders od" +
				" WHERE oi.product_id=pd.id" +
				" AND od.id = oi.order_id" +
				" AND od.paystate=1" +
				" GROUP BY pd.id" +
				" ORDER BY sale_num DESC";
		try {
			return DaoUtils.query(sql, new BeanListHandler<SaleInfo>(SaleInfo.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<SaleInfo>();
		}
	}

}
