package com.jt.cart.mapper;

import java.util.List;

import com.jt.cart.pojo.Cart;
import com.jt.common.mapper.SysMapper;

public interface CartMapper extends SysMapper<Cart> {
	public List<Cart> queryMycart(Long userId);
	public void updateNum(Cart cart);
}
