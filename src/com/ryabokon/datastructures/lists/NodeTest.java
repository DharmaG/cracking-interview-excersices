package com.ryabokon.datastructures.lists;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {

	@Test
	public void testAddItems() {
		Node<String> root = new Node<>("root");
		root.add("first");
		Assert.assertEquals("root", root.get(0));
		Assert.assertEquals("first", root.get(1));
		Assert.assertEquals(2, root.size());

		root.add("second");
		Assert.assertEquals("second", root.get(2));
		Assert.assertEquals(3, root.size());
	}

	@Test
	public void testDeleteItems() {

		Node<String> root = new Node<>("root");

		root.add("first");
		root.add("second");
		root.add("third");

		Assert.assertEquals(4, root.size());
		Assert.assertEquals("root", root.get(0));
		Assert.assertEquals("first", root.get(1));
		Assert.assertEquals("second", root.get(2));
		Assert.assertEquals("third", root.get(3));

		root.delete(2);

		Assert.assertEquals(3, root.size());
		Assert.assertEquals("root", root.get(0));
		Assert.assertEquals("first", root.get(1));
		Assert.assertEquals("third", root.get(2));

		root.add("new");

		Assert.assertEquals(4, root.size());
		Assert.assertEquals("root", root.get(0));
		Assert.assertEquals("first", root.get(1));
		Assert.assertEquals("third", root.get(2));
		Assert.assertEquals("new", root.get(3));

		root.delete(0);

		Assert.assertEquals(3, root.size());
		Assert.assertEquals("first", root.get(0));
		Assert.assertEquals("third", root.get(1));
		Assert.assertEquals("new", root.get(2));

	}

	@Test
	public void testDeleteRoot() {

		Node<String> root = new Node<>("root");

		root.delete(0);

		Assert.assertEquals(0, root.size());
		root.add("new");

		Assert.assertEquals(1, root.size());
		Assert.assertEquals("new", root.get(0));

	}

}
