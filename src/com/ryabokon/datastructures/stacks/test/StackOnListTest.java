package com.ryabokon.datastructures.stacks.test;

import org.junit.Before;

import com.ryabokon.datastructures.stacks.StackOnList;

public class StackOnListTest extends AbstractStackTest {
	@Before
	public void initStacks() {
		stack = new StackOnList<>();
	}

}
