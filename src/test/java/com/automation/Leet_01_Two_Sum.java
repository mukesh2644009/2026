package com.automation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leet_01_Two_Sum {
    public static int[] twoSum(int[] num, int target){

        Map<Integer, Integer> seen = new HashMap<>();
        for (int i=0; i < num.length; i++){

            int comp = target - num[i];

            if(seen.containsKey(comp)) {
                return new int[]{seen.get(comp), i};
            }
            seen.put(num[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {

        int[] ans =

                twoSum(new int[] {2,7,11,15}, 9);
        System.out.println(Arrays.toString(ans));
    }
}
