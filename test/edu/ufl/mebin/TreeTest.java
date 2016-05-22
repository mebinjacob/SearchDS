package edu.ufl.mebin;

import org.junit.Test;
import static org.junit.Assert.*;
public class TreeTest {

	private Tree tree = new Tree();
	@Test
	public void insertTest(){
		boolean insert = tree.insert(1L);
		assertEquals(true, insert);
		assertEquals(true, tree.contains(1L));
		
		assertEquals(true, tree.insert(2L));
		assertEquals(true, tree.contains(1L));
		
		assertEquals(false, tree.insert(1L));
		assertEquals(true, tree.contains(1L));
	}

}
