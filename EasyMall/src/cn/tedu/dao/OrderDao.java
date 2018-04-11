package cn.tedu.dao;

import java.sql.Connection;
import java.util.List;

import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.SaleInfo;

public interface OrderDao {
/**
 * ��order�������һ��������Ϣ������
 * @param con
 * @param order
 */
	void addOrder(Connection con, Order order);
	/**��orderItem�������һ��������Ŀ��Ϣ������
	 * @param con�����ݿ����Ӷ���
	 * @param item����װ�˶�����Ŀ�����Ϣ�Ķ���
	 */
	void addOrderItem(Connection con, OrderItem item);
	/**
	 * �����û�id��orders���ѯuserId��Ӧ��ȫ������
	 * @param userId
	 * @return
	 */
	List<Order> findOrdersByUid(int userId);
	/**
	 * �����û�id��orderitem���в�ѯuserId��Ӧ��ȫ��������Ŀ
	 * �ò�ѯ�漰�����ű�orders��orderitem���Ĺ�����ѯ
	 * @param userId �û�ID
	 * @return userId��Ӧ��ȫ��������Ŀ
	 */
	List<OrderItem> findOrderItemsByUid(int userId);
	/**
	 * ���ݶ�����ID��ѯ��������ϸ��Ϣ(�����)
	 * @param con ���ݿ����Ӷ���
	 * @param oid ������ID
	 * @return ��Ӧ������ϸ��Ϣ
	 */
	Order findOrderByOid(Connection con, String oid);
	/**
	 * ���ݶ���ID��ѯ��Ӧ �µ����ж�����
	 * @param con ���ݿ����Ӷ���
	 * @param oid ����id
	 * @return ��Ӧ�����µ�ȫ��������
	 */
	List<OrderItem> findOrderItemsByOid(Connection con, String oid);
	/**
	 * �����û�ID��orderitem����ɾ����Ӧ�����µ�ȫ��������
	 * @param con ���ݿ����Ӷ���
	 * @param oid ����ID
	 */
	void deleteOrderItemsByOid(Connection con, String oid);
	/**
	 * ���ݶ���IDɾ����Ӧ�����µ�ȫ��������Ϣ
	 * @param con ���ݿ����Ӷ���
	 * @param oid ����ID
	 */
	void deleteOrderByOid(Connection con, String oid);
	/**
	 * ���ݶ�����ID��ѯ��������ϸ��Ϣ(�����)
	 * @param con ���ݿ����Ӷ���
	 * @param oid ������ID
	 * @return ��Ӧ������ϸ��Ϣ
	 */
	Order findOrderByOid(String oid);
	/**
	 * �޸Ķ�����֧��״̬
	 * @param oid
	 * @param paystate
	 */
	void changePaystate(String oid, int paystate);
	/**
	 * ��ѯȫ�������۰��б�
	 * @return ȫ�������۰��б�
	 */
	List<SaleInfo> findSaleInfos();

}
