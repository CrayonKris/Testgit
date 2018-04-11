package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Product;

public interface ProdService {

	/**
	 * ��ѯȫ����Ʒ
	 * @return ȫ����Ʒ��Ӧ���϶���
	 * */
	List<Product> findAll();

	/**�޸���Ʒ�Ŀ��
	 * @param id:id
	 * @param pnum���޸ĺ����Ʒ����
	 * @return true����ʾ�޸ĳɹ� false��ʾ�޸Ĺ�ʧ��
	 * 
	 * */
	boolean changePnum(String id, int pnum);

	/**ɾ����Ʒ
	 * @param id:��Ʒid
	 * @return true ��ʾɾ���ɹ� false ��ʾɾ��ʧ��
	 * */
	boolean deleteProdById(String id);

	/**���ݹؼ��ֲ�ѯ������������Ʒ
	 * @param name:��Ʒ���ƵĹؼ���
	 * @param cate:��Ʒ����ؼ���
	 * @param min :�۸��������Сֵ
	 * @param max:�۸���������ֵ
	 * @return ������ӵ���Ʒ����
	 * */
	public List<Product> findAllByKey(String name, String cate, Double min, Double max);

	/**������ƷID��ѯ��Ʒ����ϸ��Ϣ�Ķ���
	 * @param id:��ƷID
	 * @return
	 */
	public Product findProdById(String id);

}
