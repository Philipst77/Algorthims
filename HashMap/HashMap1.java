package HashMap;

import java.util.HashMap;

public class HashMap1 {
    class Solution {
        public int[] twoSum(int[] nums, int target) {

            HashMap<Integer, Integer> map = new HashMap<>(); // the key in this problem is a number from array nums
                                                             // and the value is the index of each num in nums arrays

            for (int i = 0; i < nums.length; i++) { // going through nums array
                int num = nums[i]; // setting each index of each num in nums array to num var
                int diff = target - num; // the target passed + some index which is in num var adds up to are target

                if (map.containsKey(diff)) { // if the hash map has a value that is diffs value in some key value pair
                                             // of the hashmap
                    return new int[] { map.get(diff), i }; // we automatically return a new array which returns diff val
                                                           // and the current index
                }
                if (!map.containsKey(i)) { // if statement checking current index of num value is in the hashmap as a
                                           // key
                    // if not add number and index of it in the hash map num as key i as val
                    map.put(num, i);
                }
            }
            return new int[] {};
        }
    }

}
/*
 * Given an array of integers nums and an integer target, return the indices i
 * and j such that nums[i] + nums[j] == target and i != j.
 * 
 * You may assume that every input has exactly one pair of indices i and j that
 * satisfy the condition.
 * 
 * Return the answer with the smaller index firs
 * 
 * 
 * 
 */
