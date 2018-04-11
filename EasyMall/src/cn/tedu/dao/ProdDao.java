package cn.tedu.dao;

import java.sql.Connection;
import java.util.List;

import cn.tedu.entity.Product;

public interface ProdDao {
	/**��ѯȫ����Ʒ
	 * @return ȫ����Ʒ�ļ��϶���
	 * */
	public List<Product> findAll();

	/**�޸���Ʒ�Ŀ��
	 * @param id:��Ʒ id
	 * @param pnum
	 * */
	public int changePnum(String id, int pnum);

	/**������Ʒidɾ����Ʒ��Ϣ
	 * @param id ��Ʒid
	 * @return Ӱ�������
	 * */
	public int deletProdById(String id);

	/**���ݹؼ��ֲ�ѯ��������������Ʒ
	 * @param name�����ƹؼ���
	 * @param cate������ؼ���
	 * @param min���۸��������Сֵ
	 * @param max���۸���������ֵ
	 * @return ����������������Ʒ
	 */
	public List<Product> findAllByKey(String name, String cate, Double min,
			Double max);

	/**������ƷID��ѯ��Ʒ��Ϣ��������棩
	 * 
	 * @param id ��ƷID
	 * @return ��Ӧ��Ʒ����Ʒ��Ϣ
	 */
	public Product findProdById(String id);
	/**������Ʒid��ѯ��Ʒ����ϸ��Ϣ������棩
	 * @param con�����ݿ����Ӷ���
	 * @param product_id����Ʒid
	 * @return ������Ʒ����ϸ��Ϣ
	 */
	public Product findProdById(Connection con, String porduct_id);
	/**�޸���Ʒ�Ŀ������������棩
	 * @param con:���ݿ����Ӷ���
	 * @param id����Ʒ��id
	 * @param pnum:�޸ĺ����Ʒ���
	* @return ������Ӱ�������
	 */
	public int changePnum(Connection con, String id, int pnum);

	/**
	 * ��ԭ��Ʒ�Ŀ��(�����)
	 * @param con ���ݿ����Ӷ���
	 * @param porduct_id ��Ʒ��ID
	 * @param order_id 
	 * @param buynum ��ԭ������
	 * @return Ӱ�������
	 */
	public int updatePnum(Connection con,String pid,
			int buynum);


}
