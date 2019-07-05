package com.celfocus.training.domain.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.celfocus.training.domain.AbstractDao;
import com.celfocus.training.util.Utils;

public class ShoppingCartDao implements AbstractDao<ShoppingCart> {

	private List<ShoppingCart> shoppingCarts = new ArrayList<>();

	@Override
	public List<ShoppingCart> getAll() {
		return shoppingCarts;
	}

	@Override
	public ShoppingCart create(ShoppingCart shoppingCart) {
		Objects.requireNonNull(shoppingCart);
		shoppingCarts.add(shoppingCart);
		return shoppingCart;
	}

	@Override
	public void delete(String userName) {
		ShoppingCart cart = findByUserName(userName);
		if (cart != null) {
			shoppingCarts.remove(cart);
		}
	}

	public boolean existsShoppingCart(String username) {
		return findByUserName(username) != null;
	}

	public ShoppingCart findByUserName(String userName) {
		if (Utils.isNullOrEmpty(userName)) {
			return null;
		}
		for (ShoppingCart cart : shoppingCarts) {
			if (userName.equals(cart.getUser().getName())) {
				return cart;
			}
		}
		return null;
	}

}
