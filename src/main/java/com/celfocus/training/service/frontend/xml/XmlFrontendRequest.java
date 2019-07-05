package com.celfocus.training.service.frontend.xml;

import java.util.Objects;

import com.celfocus.training.domain.frontend.iteminfo.ItemInfoFrontendRequest;
import com.celfocus.training.domain.frontend.shoppingcart.ShoppingCartFrontendRequest;
import com.celfocus.training.domain.frontend.user.UserFrontendRequest;
import com.celfocus.training.domain.iteminfo.ItemInfo;
import com.celfocus.training.domain.shoppingcart.ShoppingCart;
import com.celfocus.training.domain.user.User;
import com.celfocus.training.util.Utils;

public class XmlFrontendRequest implements UserFrontendRequest, ShoppingCartFrontendRequest, ItemInfoFrontendRequest {

	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>";

	private static final String USER_TEMPLATE = XML_HEADER
			+ "<name>%s</name><birthDate>%s</birthDate><isSenior>%s</isSenior>";
	private static final String SHOPPING_CART_TEMPLATE = XML_HEADER + "<user>%s</user><items>%s</items>";
	private static final String ITEM_INFO_TEMPLATE = "<name>%s</name><value>%s</value>";

	@Override
	public String getFrontendUser(User user) {
		Objects.requireNonNull(user);
		return String.format(USER_TEMPLATE, user.getName(), Utils.toString(user.getBirthDate(), DATE_FORMAT),
				user.isSenior());
	}

	@Override
	public String getFrontendShoppingCart(ShoppingCart shoppingCart) {
		Objects.requireNonNull(shoppingCart);
		return String.format(SHOPPING_CART_TEMPLATE, shoppingCart.getUser().getName(), shoppingCart.getItems());
	}

	@Override
	public String getFrontendItemInfo(ItemInfo itemInfo) {
		Objects.requireNonNull(itemInfo);
		return String.format(ITEM_INFO_TEMPLATE, itemInfo.getName(), itemInfo.getValue());
	}

}
