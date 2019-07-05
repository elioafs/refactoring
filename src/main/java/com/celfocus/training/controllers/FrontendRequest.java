package com.celfocus.training.controllers;

import java.text.SimpleDateFormat;
import java.util.Objects;

import com.celfocus.training.domain.iteminfo.ItemInfo;
import com.celfocus.training.domain.shoppingcart.ShoppingCart;
import com.celfocus.training.domain.user.User;
import com.celfocus.training.service.AppService;
import com.celfocus.training.service.frontend.html.HtmlFrontendRequest;
import com.celfocus.training.service.frontend.xml.XmlFrontendRequest;
import com.celfocus.training.util.Utils;

public class FrontendRequest {

	private static final String DATE_FORMAT = "DD/MM/YYYY";

	enum FormatTypes {
		XML, HTML
	}

	private HtmlFrontendRequest htmlFrontendRequest;
	private XmlFrontendRequest xmlFrontendRequest;
	private AppService appService;

	public FrontendRequest() {
		htmlFrontendRequest = new HtmlFrontendRequest();
		xmlFrontendRequest = new XmlFrontendRequest();
		appService = new AppService();
	}

	/**
	 * This method returns the User, in the wanted fronted format.
	 * 
	 * @param type
	 *            The frontend format type.
	 * @param user
	 *            The user which will be rendered
	 * @return The text in the wanted format with the user information.
	 * @throws Exception
	 */
	public String getFrontendUser(String type, User user) throws Exception {
		switch (FormatTypes.valueOf(type)) {
		case HTML:
			return htmlFrontendRequest.getFrontendUser(user);
		case XML:
			return xmlFrontendRequest.getFrontendUser(user);
		default:
			return "";
		}
	}

	/**
	 * This method returns the Shopping Cart in the wanted fronted format.
	 * 
	 * @param type
	 *            The frontend format type.
	 * @param shoppingCart
	 *            The shpping cart which will be rendered
	 * @return The text in the wanted format with the shopping cart information.
	 * @throws Exception
	 */
	public String getFrontendShoppingCart(String type, ShoppingCart shoppingCart) throws Exception {
		switch (FormatTypes.valueOf(type)) {
		case HTML:
			return htmlFrontendRequest.getFrontendShoppingCart(shoppingCart);
		case XML:
			return xmlFrontendRequest.getFrontendShoppingCart(shoppingCart);
		default:
			return "";
		}
	}

	/**
	 * This method returns the shopping cart Item in the wanted fronted format.
	 * 
	 * @param type
	 *            The frontend format type.
	 * @param itemInfo
	 *            The Item which will be rendered
	 * @return The text in the wanted format with the Item information.
	 * @throws Exception
	 */
	public String getFrontendItem(String type, ItemInfo itemInfo) throws Exception {
		switch (FormatTypes.valueOf(type)) {
		case HTML:
			return htmlFrontendRequest.getFrontendItemInfo(itemInfo);
		case XML:
			return xmlFrontendRequest.getFrontendItemInfo(itemInfo);
		default:
			return "";
		}
	}

	/**
	 * Create or update a User.
	 * 
	 * @param name
	 *            Mandatory - The name of the user
	 * @param birthDateStr
	 *            Mandatory - The user birth date, in String format.
	 * @throws Exception
	 */
	public User upsertUser(String name, String birthDate) throws Exception {
		Objects.requireNonNull(name);
		return appService.upsertUser(name, Utils.toDate(birthDate, new SimpleDateFormat(DATE_FORMAT)));
	}

	/**
	 * Method to delete the user.
	 * 
	 * @param userName
	 *            The user name.
	 * @throws Exception
	 */
	public void deleteUser(String userName) throws Exception {
		appService.deleteUser(userName);
	}

	/**
	 * Add Item info to Shopping Cart
	 * 
	 * @param userName
	 *            The name of the user that owns the shopping cart.
	 * @param itemName
	 *            The item name.
	 * @param quantity
	 *            The quantity of the item.
	 * @throws Exception
	 *             If inpupt is null or an error occurs
	 */
	public void addItemToShoppingCart(String userName, String itemName, int quantity) throws Exception {
		Objects.requireNonNull(userName);
		Objects.requireNonNull(itemName);
		appService.addItemToUserShoppingCart(userName, Utils.convertItemName(itemName), quantity);
	}

	public void createItemInfo(String itemName, double value) {
		appService.createItemInfo(Utils.convertItemName(itemName), value);
	}

	public void removeItemFromUser(String userName, String itemName) {
		appService.removeItemFromUser(userName, itemName);
	}

}
