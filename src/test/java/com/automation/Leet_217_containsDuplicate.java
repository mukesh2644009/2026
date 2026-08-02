package com.automation;

import java.util.HashSet;
import java.util.Set;

public class Leet_217_containsDuplicate
{
    public static void main(String[] args) {

        int[] a = {1,2,4};
        boolean b  = containsDuplicate(a);
        System.out.println(b);
    }
    public static boolean containsDuplicate(int[] nums) {

        Set<Integer> seen = new HashSet<>();

        for(int i=0; i< nums.length; i++){
            if(seen.contains(nums[i])) {
                return true;
            }
            seen.add(nums[i]);
        }
        return false;

    }
}
