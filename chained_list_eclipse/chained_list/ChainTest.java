package chained_list;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChainTest {
	
	@Test
    public void testAdd() throws Exception {

		Chain<String> chain = new Chain<>();
		
		assertTrue(chain.isEmpty());
		
		chain.add("A");
		chain.add("B");
		chain.add("C");
		chain.add("D");
		chain.add("E");
		
		assertFalse(chain.isEmpty());
		assertEquals(5, chain.getSize());
		
		StringBuilder sb = new StringBuilder();
		Chain<String>.ChainItem<String> ptr = chain.getHead();
		
		while (ptr != null) {
			sb.append(ptr.getData());
			ptr = ptr.getNext();
		}
		assertEquals("ABCDE", sb.toString());
    }
	
	@Test
    public void testRemove() throws Exception {

		Chain<String> chain = new Chain<>();
		chain.add("A");
		chain.add("B");
		chain.add("C");
		chain.add("D");
		chain.add("E");

		chain.remove(1);
		chain.remove("D");
		
		StringBuilder sb = new StringBuilder();
		Chain<String>.ChainItem<String> ptr = chain.getHead();
		
		while (ptr != null) {
			sb.append(ptr.getData());
			ptr = ptr.getNext();
		}
		assertEquals("ACE", sb.toString());
    }
	
    public void testInsert() throws Exception {

		Chain<String> chain = new Chain<>();
		chain.add("A");
		chain.add("B", 0);
		chain.add("C");
		chain.add("D");
		
		StringBuilder sb = new StringBuilder();
		Chain<String>.ChainItem<String> ptr = chain.getHead();
		
		while (ptr != null) {
			sb.append(ptr.getData());
			ptr = ptr.getNext();
		}
		assertEquals("BACD", sb.toString());
    }

	@Test(expected=IndexOutOfBoundsException.class)
    public void testRemoveOutOfBound() throws Exception {

		Chain<String> chain = new Chain<>();
		chain.add("A");
		chain.add("B");
		chain.add("C");
		chain.add("D");
		chain.add("E");

		chain.remove(7);
    }
	
	@Test(expected=IndexOutOfBoundsException.class)
    public void testAddOutOfBound() throws Exception {

		Chain<String> chain = new Chain<>();
		chain.add("A");
		chain.add("B");
		chain.add("C");
		chain.add("D");
		chain.add("E", 7);
    }
	
	@Test()
    public void testRemoveUntilEmpty() throws Exception {

		Chain<String> chain = new Chain<>();
		chain.add("A");
		chain.add("B");
		chain.add("C");
		chain.add("D");
		chain.add("E");

		while (!chain.isEmpty()) {
			chain.remove(0);
		}
		assertNull(chain.getHead());
    }
	
	@Test()
    public void testAscending() throws Exception {

		Chain<String> chain = new Chain<>();
		chain.add("D");
		chain.add("A");
		chain.add("E");
		chain.add("C");
		chain.add("B");
		chain.add("G");
		chain.add("F");
		chain.add("I");
		chain.add("H");
		
		chain.sortAscending();
		
		StringBuilder sb = new StringBuilder();
		Chain<String>.ChainItem<String> ptr = chain.getHead();
		
		while (ptr != null) {
			sb.append(ptr.getData());
			ptr = ptr.getNext();
		}
		assertEquals("ABCDEFGHI", sb.toString());
    }
	
	@Test()
    public void testDescending() throws Exception {

		Chain<String> chain = new Chain<>();
		chain.add("D");
		chain.add("A");
		chain.add("E");
		chain.add("C");
		chain.add("B");
		chain.add("G");
		chain.add("F");
		chain.add("I");
		chain.add("H");
		
		chain.sortDescending();
		
		StringBuilder sb = new StringBuilder();
		Chain<String>.ChainItem<String> ptr = chain.getHead();
		
		while (ptr != null) {
			sb.append(ptr.getData());
			ptr = ptr.getNext();
		}
		assertEquals("IHGFEDCBA", sb.toString());
    }
}
