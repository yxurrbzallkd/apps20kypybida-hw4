package ua.edu.ucu.tries;
import ua.edu.ucu.queue.Queue;

public class RWayTrie implements Trie {
    private static int AlphabetSize = 26; // radix
    private Node root; // root of trie
    private int size;
    private Queue queue;
    public static final double POSITIVE_INFINITY = 1.0 / 0.0;

	private static class Node {
		private Node[] children = new Node[AlphabetSize];
        private boolean endOfWord;

        public Node() {
            this.endOfWord = false;
            for (int i = 0; i < AlphabetSize; i++) {
                this.children[i] = null;
            }
        }
	}

	public RWayTrie() {
        this.root = new Node();
        this.size = 0;
        this.queue = new Queue();
	}	

	private void insert(String word) {
        int index;
        Node node = root;
        for (int l = 0; l < word.length(); l++)
        {
            index = word.charAt(l) - 'a'; //where to add a new child
            if (node.children[index] == null) {
                node.children[index] = new Node();
            }
            node = node.children[index];
        }
        if (!node.endOfWord) {
            this.size += 1; //insert size if haven't inserted yet
        }
        //definitely an end of word, since we just inserted it there
        node.endOfWord = true;
    }


    public void add(Tuple t) {
        //Adds a tuple (term, weight)
        //we will use (word, length of word)
        this.insert(t.term);
    }


    public boolean contains(String word) {
        int index; 
        Node node = root; 
        for (int l = 0; l < word.length(); l++) 
        {
            index = word.charAt(l) - 'a'; 
            if (node.children[index] == null) 
                return false; 
            node = node.children[index]; 
        } 

        return (node != null && node.endOfWord); 
    }

    public boolean delete(String word) {
        int index; 
        Node node = root; 
        for (int l = 0; l < word.length(); l++) 
        {
            index = word.charAt(l) - 'a';
            if (node.children[index] == null)
                return false;
            node = node.children[index];
        } 

        if (node != null) {
            node.endOfWord = false; //there is no word anymore...
            this.size -= 1; //there was such a word and now there is one word less
            return true; //deleted the word
        }
        return false; //there is no such word -> it is already deleted, we didn't do anything
    }

    public Iterable<String> words() {
        //iterator over all words
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void rightOrder(Node currentNode, String currentWord, double maxLength) {
        if (currentWord.length() >= maxLength) {
            return;
        }
        if (currentNode == null) {
            return;
        }
        if (currentNode.endOfWord) {
            queue.enqueue(currentWord);
        }
        String chr;
        Node n;
        String word;
        for (int i = 0; i < AlphabetSize; i++) {
            chr = Character.toString(i + Integer.parseInt(String.valueOf('a')));
            n = currentNode.children[i];
            word = currentWord + chr;
            rightOrder(n, word, maxLength);
            if (n != null && n.endOfWord) {
                queue.enqueue(word);
            }
        }
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        int index;
        Node node = root;
        for (int l = 0; l < pref.length(); l++)
        {
            index = pref.charAt(l) - 'a';
            if (node.children[index] == null)
                break;
            node = node.children[index];
        }

        rightOrder(node, pref, POSITIVE_INFINITY);
        return this.queue;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        int index;
        Node node = root;
        for (int l = 0; l < pref.length(); l++)
        {
            index = pref.charAt(l) - 'a';
            if (node.children[index] == null)
                break;
            node = node.children[index];
        }
        double maxLength = (pref.length()>2 ? pref.length() : 3);
        rightOrder(node, pref, maxLength+k);
        return this.queue;
    }

    public int size() {
        //number of words in Trie
        return this.size;
    }
}
