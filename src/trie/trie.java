package trie;

/*Префиксное дерево
Задание: Хранит строки в виде префиксного дерева.
Корневой узел такого дерева не хранит ничего,
узлы первого уровня хранят первый символ строки,
узлы второго уровня хранят второй символ и так далее.
Методы:
1.Добавление строки в дерево.
2.Удаление строки из дерева.
3.Поиск строки в дереве.
4.Поиск строк с заданнным префиксом.
*/

import java.util.*;

public class trie {

    private TrieNode root;

    public class TrieNode {

        private HashMap<Character, TrieNode> children = new HashMap<>();
        boolean isEnd;

        public HashMap<Character, TrieNode> getChildren() {
            return children;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setChildren(HashMap<Character, TrieNode> children) {
            this.children = children;
        }

        public void setEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public TrieNode() {
        }

        private void addToPref(String path, LinkedList<String> prefOut) {
            if (isEnd)
                prefOut.add(path);

            for (Map.Entry<Character, TrieNode> node : children.entrySet()) {
                node.getValue().addToPref(path + node.getKey(), prefOut);
            }

        }
    }

    public trie() {
        root = new TrieNode();
    }

    public void add(String s) {
        HashMap<Character, TrieNode> children = root.getChildren();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            TrieNode node;
            if (children.containsKey(letter)) {
                node = children.get(letter);
            } else {
                node = new TrieNode();
                children.put(letter, node);
            }
            children = node.getChildren();

            if (i == s.length() - 1) {
                node.setEnd(true);
            }
        }
    }

    public void delete(String s) {
        delete(root, s, 0);
    }

    private boolean delete(TrieNode current, String s, int i) {
        if (i == s.length()) {
            if (!current.isEnd()) {
                return false;
            }
            current.setEnd(false);
            return current.getChildren().isEmpty();
        }
        char letter = s.charAt(i);
        TrieNode node = current.getChildren().get(letter);
        if (node == null) {
            return false;
        }
        boolean deleteNode = delete(node, s, i + 1) && !node.isEnd();
        if (deleteNode) {
            current.getChildren().remove(letter);
            return current.getChildren().isEmpty();
        }
        return false;
    }

    public boolean find(String s) {
        HashMap<Character, TrieNode> children = root.getChildren();
        TrieNode node = null;
        for (char letter : s.toCharArray()) {
            if (children.containsKey(letter)) {
                node = children.get(letter);
                children = node.getChildren();
            } else {
                node = null;
                break;
            }
        }
        if (node != null && node.isEnd()) {
            return true;
        } else {
            return false;
        }
    }

    public TrieNode findNode(String s) {
        HashMap<Character, TrieNode> children = root.getChildren();
        TrieNode a = null;
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (children.containsKey(letter)) {
                a = children.get(letter);
                children = a.children;
            } else {
                return null;
            }
        }
        return a;
    }

    public LinkedList<String> wordsWithPrefix(String pref) throws NullPointerException {
        LinkedList<String> prefOut = new LinkedList<String>();
        prefOut = new LinkedList<String>();

        TrieNode start = findNode(pref);

        for (Map.Entry<Character, TrieNode> node : start.getChildren().entrySet()) {
            node.getValue().addToPref(pref + node.getKey(), prefOut);
        }
        if (prefOut.size() > 0) {
            return prefOut;
        } else {
            throw new NullPointerException("Слов нет");
        }
    }
}

























