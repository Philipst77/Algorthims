package HashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class HashMap2 {

    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {

            HashMap<String, List<String>> freq = new HashMap<>(); // Key: string is letterfrequence signature that
                                                                  // uniquely represent anagram group
            // Value : List<String> A list of words that share that frequency signature
            // (anagrams)

            for (String s : strs) { // going through strings in strs array
                int[] store = new int[26]; // array of size 26 to frequency of certain letters in words so the ascii val

                for (char c : s.toCharArray()) { // each index in this arr corresponds to a letter from a to z
                    // counts how many times each letter appears in the word this creates a unique
                    // signature for words
                    // that are anagrams
                    store[c - 'a']++; // so this store array holds the unique signature of every word in strs array
                }
                // and then we make each unique signature of each word a key in our hash map
                // and anagrams will have the same unique signature because they just have the
                // same letter in different order
                String key = Arrays.toString(store);

                if (!freq.containsKey(key)) {
                    freq.put(key, new ArrayList<>()); // if the key which is the unique method signature isnt in the
                                                      // hashmap
                    // we add the key and intialize the value part of the key value pair to be an
                    // array list
                }
                freq.get(key).add(s); // if the key is already in the hashmap we just add in the words that have that
                                      // unique
                // signature to that key value pair the hashmap
            }

            return new ArrayList<>(freq.values());

        }
    }

}
/*
 * Given an array of strings strs, group all anagrams together into sublists.
 * You may return the output in any order.
 * 
 * An anagram is a string that contains the exact same characters as another
 * string, but the order of the characters can be different.
 * 
 * Example 1:
 * 
 * Input: strs = ["act","pots","tops","cat","stop","hat"]
 * 
 * Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
 * 
 * 
 * 
 */