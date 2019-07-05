package com.celfocus.training.domain.shoppingcart.item;

import com.celfocus.training.domain.iteminfo.ItemInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingCartItem {

	private ItemInfo info;
	private Integer quantity;
	private Double discount;

}
