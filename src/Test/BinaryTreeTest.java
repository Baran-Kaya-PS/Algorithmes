package Test;

import StructureDeDonnée.BinaryTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    private BinaryTree<Integer> bt;

    @BeforeEach
    void setUp() {
        bt = new BinaryTree<>();
        bt.insert(5);
        bt.insert(3);
        bt.insert(7);
        bt.insert(2);
        bt.insert(4);
        bt.insert(6);
        bt.insert(8);
    }
    @Test
    void testContains() {
        assertTrue(bt.contains(5), "Should find 5 as it is the root node.");
        assertTrue(bt.contains(3), "Should find 3 as it is a left child.");
        assertTrue(bt.contains(7), "Should find 7 as it is a right child.");
        assertTrue(bt.contains(2), "Should find 2 as it is a leaf node.");
        assertFalse(bt.contains(1), "Should not find 1 as it is not in the tree.");
        assertFalse(bt.contains(9), "Should not find 9 as it is not in the tree.");
    }

    @Test
    void testFindMin() {
        assertEquals(2, bt.findMin(), "The minimum value should be 2.");
    }

    @Test
    void testFindMax() {
        assertEquals(8, bt.findMax(), "The maximum value should be 8.");
    }

    // Add more tests for deletion, traversal, and other methods if needed
}
