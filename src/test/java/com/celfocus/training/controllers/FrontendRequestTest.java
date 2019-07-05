package com.celfocus.training.controllers;

import java.util.ArrayList;

import com.celfocus.training.domain.iteminfo.ItemInfo;
import com.celfocus.training.domain.shoppingcart.ShoppingCart;
import com.celfocus.training.domain.user.User;
import com.celfocus.training.util.Utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FrontendRequestTest extends TestCase {

	FrontendRequest request = new FrontendRequest();

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public FrontendRequestTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(FrontendRequestTest.class);
	}

	/**
	 * Test
	 * 
	 * @throws Exception
	 */
	public void testApp() throws Exception {

		// Test User create
		User user = request.upsertUser("user01", "03/04/1992");
		assertNotNull(user);

		// Test User update
		user = request.upsertUser(user.getName(), "06/14/1992");
		assertNotNull(user);
		assertTrue(!Utils.toString(user.getBirthDate(), "DD/MM/YYYY").equals("03/04/1992"));

		request.addItemToShoppingCart(user.getName(), "item1", 1);
		request.addItemToShoppingCart(user.getName(), "item2", 2);

		// Test add new user
		User user2 = request.upsertUser("user02", "01/01/1955");
		assertNotNull(user2);

		// Test create and delete item
		String item03 = "item03";
		request.createItemInfo(item03, 10.0);
		request.createItemInfo("item04", 20.0);

		request.addItemToShoppingCart(user2.getName(), item03, 3);
		request.addItemToShoppingCart(user2.getName(), item03, 2);

		request.removeItemFromUser(user2.getName(), "baditemname");
		request.removeItemFromUser(user2.getName(), item03);

		// Test delete
		request.deleteUser(user.getName());
		request.deleteUser(user2.getName());

		// Test older user
		User user4 = request.upsertUser("user04", "05/03/1940");
		assertNotNull(user4);

		request.addItemToShoppingCart(user4.getName(), "item04", 2);

		try {
			request.addItemToShoppingCart("user03", "item3", 3);
			fail("Exception should be thrown");
		} catch (NullPointerException e) {
			assertTrue(true);
		}

		String htmlUser = request.getFrontendUser("HTML", user);
		assertNotNull(htmlUser);
		assertTrue(htmlUser.contains(user.getName()));

		String xmlUser = request.getFrontendUser("XML", user);
		assertNotNull(xmlUser);
		assertTrue(xmlUser.contains(user.getName()));

		ShoppingCart dummyCart = new ShoppingCart(user, new ArrayList<>());

		String htmlCart = request.getFrontendShoppingCart("HTML", dummyCart);
		assertNotNull(htmlCart);
		String xmlCart = request.getFrontendShoppingCart("XML", dummyCart);
		assertNotNull(xmlCart);

		ItemInfo dummyInfo = new ItemInfo("dummyInfo", 10.0);

		String htmlItem = request.getFrontendItem("HTML", dummyInfo);
		assertNotNull(htmlItem);
		String xmlItem = request.getFrontendItem("XML", dummyInfo);
		assertNotNull(xmlItem);

		String emptyUser = request.getFrontendUser("something", user);
		assertTrue("".equals(emptyUser));
		String emptyShoppingCart = request.getFrontendShoppingCart("something", dummyCart);
		assertTrue("".equals(emptyShoppingCart));
		String emptyItem = request.getFrontendItem("something", dummyInfo);
		assertTrue("".equals(emptyItem));

	}
}
