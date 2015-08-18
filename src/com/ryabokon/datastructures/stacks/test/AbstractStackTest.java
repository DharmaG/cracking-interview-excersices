package com.ryabokon.datastructures.stacks.test;

import org.junit.Assert;
import org.junit.Test;

import com.ryabokon.datastructures.stacks.Stack;

public abstract class AbstractStackTest {

	Stack<String> stack;

	@Test
	public void createStackTest() {

		stack.push("one");
		stack.push("two");
		stack.push("three");

		Assert.assertEquals("three", stack.pop());
		Assert.assertEquals("two", stack.pop());
		Assert.assertEquals("one", stack.pop());
		Assert.assertEquals(null, stack.pop());

	}

	@Test
	public void stackCapacityTest() {

		for (int i = 0; i < 32; i++) {
			stack.push(String.valueOf(i));
		}

		stack.push("resize!");
		stack.push("32");

		Assert.assertEquals("32", stack.pop());
		Assert.assertEquals("resize!", stack.pop());
		Assert.assertEquals("31", stack.pop());
	}

}
