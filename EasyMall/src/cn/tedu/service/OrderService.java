package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Order;
import cn.tedu.entity.OrderInfo;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.SaleInfo;
import cn.tedu.exception.MsgException;

public interface OrderService {
	/**��Ӷ��������Ϣ����orders���һ������orderitem����
	 * ���oiList.size()�����ݣ��޸�products���Ӧ��Ʒ�Ŀ�棩
	 * @param order����װ�˶�����Ϣ�Ķ���
	 * @param oiList����װ�˶����������Ϣ����ļ��϶���
	 * @throws MsgException������ӵ���Ʒ��治���׳��쳣
	 */
	void addOrder (Order order, List<OrderItem> oiList) throws MsgException;
/**
 * �����û�ID��ѯ��Ӧ�û���ȫ��������Ϣ�������б��ѯ��
 * @param userId
 * @return ��Ӧ�û���ȫ��������Ϣ
 */
	List<OrderInfo> findOrderInfosByUid(int userId);
	/**
	 * ���ݶ���IDɾ���ö��������Ϣ������ԭ�ö��������������Ʒ���
	 * @param oid ��Ʒ����ID 
	 * @throws MsgException ɾ���ǡ�δ֧���������׳��쳣
	 */
void deleteOrderByOid(String oid) throws MsgException;
/**
 * ���ݶ���ID��ȡ��Ӧ�����������Ϣ��������orders��
 * @param oid:����ID
 * @return ���ض�Ӧ�Ķ���
 */
	Order findOrderByOid(String oid);
	/**
	 * �޸Ķ���֧��״̬  0��ʾδ֧����1��ʾ��֧��
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
