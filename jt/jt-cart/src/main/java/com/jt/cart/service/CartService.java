package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.service.BaseService;
@Service
public class CartService extends BaseService<Cart> {
	@Autowired
	private CartMapper cartMapper;
	public List<Cart> queryMyCart(Long userId){
		List<Cart> cartList = cartMapper.queryMycart(userId);
		return cartList;
	}
	public void saveCart(Cart cart) {
		/*1.判断商品是否存在于购物车
		 * 2.如果不存在，新增
		 * 3.如果存在，修改其数量，在数量上增加
		 */
		Cart param = new Cart();
		param.setUserId(cart.getUserId());
		param.setItemId(cart.getItemId());
		Cart oldCart = super.queryByWhere(param);
		if (null==oldCart) {
			//不存在对应用户和对应商品的购物车
			//新增
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insertSelective(cart);
		}else {
			oldCart.setNum(oldCart.getNum()+cart.getNum());
			oldCart.setUpdated(new Date());
			cartMapper.updateByPrimaryKeySelective(oldCart);
		}
	}
	//更新
	public void updateNum(Cart cart){
		cart.setUpdated(new Date());
		cartMapper.updateNum(cart);
	}
}
