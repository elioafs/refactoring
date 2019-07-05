package com.celfocus.training.domain.shoppingcart;

import java.util.List;

import com.celfocus.training.domain.shoppingcart.item.ShoppingCartItem;
import com.celfocus.training.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingCart {

	private User user;
	private List<ShoppingCartItem> items;

}
