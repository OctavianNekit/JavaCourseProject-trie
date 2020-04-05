import static org.junit.jupiter.api.Assertions.*;

import trie.trie;
import org.junit.jupiter.api.Test;

class Tests {
    @Test
    public void Test1() {
        trie trie = new trie();
        trie.add("hello");
        trie.add("dog");
        trie.add("mother");
        trie.add("monkey");
        trie.add("hell");
        assertTrue(trie.find("hello"));
        assertFalse(trie.find("father"));
    }

    @Test
    public void Test2() {
        trie trie = new trie();
        trie.add("father");
        trie.add("mother");
        trie.add("gun");
        trie.add("funny");
        trie.add("hello");
        assertTrue(trie.find("gun"));
        assertFalse(trie.find("monkey"));
    }

    @Test
    public void DeleteTest1() {
        trie trie = new trie();
        trie.add("game");
        trie.add("gun");
        trie.add("cat");
        trie.add("dog");
        assertTrue(trie.find("gun"));
        trie.delete("gun");
        assertFalse(trie.find("gun"));
    }

    @Test
    public void DeleteTest2() {
        trie trie = new trie();
        trie.add("boat");
        trie.add("bubble");
        trie.add("note");
        trie.add("pen");
        assertTrue(trie.find("note"));
        trie.delete("note");
        assertFalse(trie.find("note"));
    }

    @Test
    public void TestWordStartPrefix1() {
    }
}