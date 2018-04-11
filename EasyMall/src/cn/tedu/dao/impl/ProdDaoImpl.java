package cn.tedu.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.dao.ProdDao;
import cn.tedu.entity.Product;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.BeanListHandler;
import cn.tedu.utils.DaoUtils;

public class ProdDaoImpl implements ProdDao {

	public List<Product> findAll() {
		// �������϶������������ѯ����ȫ����Ʒ
		List<Product> list = null;
		// ��дsql���
		String sql = "select * from products";
		// ִ�в�ѯ
		try {
			list = DaoUtils.query(sql, new BeanListHandler<Product>(
					Product.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ����list�����
		return list;
	}

	public int changePnum(String id, int pnum) {
		// ��дsql���
		String sql = "update products set pnum=? where id=?";
		try {
			return DaoUtils.update(sql, pnum, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// ����˳��ʹ����޹غ�ռλ���й�
			return 0;
		}

	}

	public int deletProdById(String id) {
		String sql = "delete from products where id=?";
		try {
			return DaoUtils.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public List<Product> findAllByKey(String name, String cate, Double min,
			Double max) {
		// 1.��дsql���
		String sql = "select * from products where name like ? and category like ?";
		// 2.����min��max��̬ƴдsql����ִ�в�ѯ����
		BeanListHandler<Product> blh = new BeanListHandler<Product>(
				Product.class);
		try {
			if (min == null && max == null) {

				return DaoUtils.query(sql, blh, "%"+name+"%", "%"+cate+"%");
			}
			if (min!=null&&max==null) {
				sql+=" and price>=?";
				return DaoUtils.query(sql, blh, "%"+name+"%", "%"+cate+"%",min);
			}
			if (min==null&&max!=null) {
				sql+=" and price<=?";
				return DaoUtils.query(sql, blh, "%"+name+"%", "%"+cate+"%",max);
			}else{
				sql+=" and price>=? and price<=?";
				return DaoUtils.query(sql, blh, "%"+name+"%", "%"+cate+"%",min,max);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Product>();
		}
	}

	public Product findProdById(String id) {
		String sql="select * from products where id=?";
		try {
			return DaoUtils.query(sql, new BeanHandler<Product>(Product.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Product findProdById(Connection con, String product_id) {
		String sql="select * from products where id=?";
		try {
			return DaoUtils.query(con, sql,new BeanHandler<Product>(Product.class),product_id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int changePnum(Connection con, String id, int pnum) {
		// ��дsql���
				String sql = "update products set pnum=? where id=?";
				try {
					return DaoUtils.update(con,sql, pnum, id);
				} catch (SQLException e) {
					e.printStackTrace();
					return 0;
				}
	}

	public int updatePnum(Connection con, String pid,
			int buynum) {
		String sql="update products set pnum=pnum+? where id=?";
		try {
			DaoUtils.update(con, sql, buynum,pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
