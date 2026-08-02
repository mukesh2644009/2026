package com.automation;

import java.util.Arrays;

public class Leet_238_productExceptSelf
{
    public static void main(String[] args) {

        int[] a = {1,2,3,5};
        int[] b = productExceptSelf(a);
        System.out.println(Arrays.toString(b));
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    product = product * nums[j];
                }
            }
            answer[i] = product;
        }
        return answer;
    }
}


