package tasks.collections;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomLinkedListTest {
    @Test
    public void sizeTest() {
        List<Integer> l = new CustomLinkedList<Integer>();
        assertEquals(l.size(), 0);
        l.add(1);
        assertEquals(l.size(), 1);
        l.add(2);
        assertEquals(l.size(), 2);
        l.add(0);
        l.add(0);
        l.add(0);
        assertEquals(l.size(), 5);
        l.remove(0);
        assertEquals(l.size(), 4);
    }

    @Test
    public void addGetRemoveTest() {
        List<String> l = new CustomLinkedList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        l.add("5");
        assertEquals("1", l.get(0));
        assertEquals("2", l.get(1));
        assertEquals("3", l.get(2));
        assertEquals("4", l.get(3));
        assertEquals("5", l.get(4));
        assertEquals("1", l.get(0));
        assertEquals("2", l.get(1));
        assertEquals("3", l.get(2));
        assertEquals("4", l.get(3));
        assertEquals("5", l.get(4));
        assertEquals("2", l.remove(1));
        assertEquals("3", l.remove(1));
        assertEquals("4", l.remove(1));
        assertEquals("5", l.remove(1));
        assertEquals(l.size(), 1);
    }
}