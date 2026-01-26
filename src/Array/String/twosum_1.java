package Array.String;

/*
 * Dry run example (step-by-step)
nums = [2, 7, 11, 15]
target = 9

Iteration 1
i = 0
nums[i] = 2
com = 9 - 2 = 7
map = {}


7 not in map

store: {2=0}

Iteration 2
i = 1
nums[i] = 7
com = 9 - 7 = 2
map = {2=0}


2 is in map

map.get(2) = 0 (index of number 2)

i = 1 (index of number 7)
 * 
 */

import java.util.HashMap;
import java.util.Map;

public class twosum_1 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{}; // LeetCode guarantees a solution
    }

    // For local testing
    public static void main(String[] args) {
    	twosum_1 ts = new twosum_1();
        int[] result = ts.twoSum(new int[]{2, 7, 11, 15}, 9);

        System.out.println(result[0] + ", " + result[1]);
    }
}



