package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;
/**
 *
 * @author andrii
 */
public class PrefixMatches {
    //make a dictionary of words of length >= 2
    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int i = 0;
        for (String s: strings) {
            if (s.length() > 2) {
                this.trie.add(new Tuple(s, s.length()));
                i += 1;
            }
        }
        return i;
    }

    public boolean contains(String word) {
        //is the word in dictionary
        return this.trie.contains(word);
    }

    public boolean delete(String word) {
        //delete the word from dictionary
        return this.trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        //if pref >= 2 symbols - return all words with that prefix
        return this.trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        /*if pref >= 2 symbols - return words of k different lengths
        starting from length(pref) or 3 if length(pref) == 2
        that start with pref*/
        /*Example:
        Dictionary:
        	abc 3
        	abcd 4
            abce 4
            abcde 4
            abcdef 6

        pref='ab'
        k=2 -> 'abc', 'abcd', 'abce'

        pref = 'abc'
        k=1 -> 'abc'
        k=2 -> 'abc', 'abcd', 'abce'
        k=3 -> 'abc', 'abcd', 'abce', 'abcde'
        k=4 -> 'abc', 'abcd', 'abce', 'abcde', 'abcdef'
        */
        return this.trie.wordsWithPrefix(pref, k);
    }

    public int size() {
        //number of words in dictionary
        return this.trie.size();
    }
}
