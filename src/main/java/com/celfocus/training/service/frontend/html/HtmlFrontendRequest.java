package com.celfocus.training.service.frontend.html;

import java.util.Objects;

import com.celfocus.training.domain.frontend.iteminfo.ItemInfoFrontendRequest;
import com.celfocus.training.domain.frontend.shoppingcart.ShoppingCartFrontendRequest;
import com.celfocus.training.domain.frontend.user.UserFrontendRequest;
import com.celfocus.training.domain.iteminfo.ItemInfo;
import com.celfocus.training.domain.shoppingcart.ShoppingCart;
import com.celfocus.training.domain.shoppingcart.item.ShoppingCartItem;
import com.celfocus.training.domain.user.User;
import com.celfocus.training.util.Utils;

public class HtmlFrontendRequest implements UserFrontendRequest, ShoppingCartFrontendRequest, ItemInfoFrontendRequest {

	private static final String USER_TEMPLATE = "<div><h1>User</h1><span>%s</span><span>%s</span><span>%s</span></div>";
	private static final String SHOPPINGCART_TEMPLATE = "<div><h1>ShoppingCart</h1><span>%s</span><span>%s</span></div>";
	private static final String ITEM_INFO_TEMPLATE = "<div><h1>ItemInfo</h1><span>%s</span><span>%s</span><span>%s</span></div>";

	@Override
	public String getFrontendUser(User user) {
		Objects.requireNonNull(user);
		return String.format(USER_TEMPLATE, user.getName(), Utils.toString(user.getBirthDate(), DATE_FORMAT),
				user.isSenior());
	}

	@Override
	public String getFrontendShoppingCart(ShoppingCart shoppingCart) {
		Objects.requireNonNull(shoppingCart);

		String itemsStr = "";
		for (ShoppingCartItem item : shoppingCart.getItems()) {
			itemsStr += String.format(ITEM_INFO_TEMPLATE, item.getInfo().getName(), item.getInfo().getValue());
		}
		return String.format(SHOPPINGCART_TEMPLATE, getFrontendUser(shoppingCart.getUser()), itemsStr);
	}

	@Override
	public String getFrontendItemInfo(ItemInfo itemInfo) {
		Objects.requireNonNull(itemInfo);
		return String.format(ITEM_INFO_TEMPLATE, itemInfo.getName(), itemInfo.getValue());
	}

}
