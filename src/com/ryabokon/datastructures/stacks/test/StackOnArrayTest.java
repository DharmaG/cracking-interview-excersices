package com.ryabokon.datastructures.stacks.test;

import org.junit.Before;

import com.ryabokon.datastructures.stacks.StackOnArray;

public class StackOnArrayTest extends AbstractStackTest {
	@Before
	public void initStacks() {
		stack = new StackOnArray<>();
	}

}
