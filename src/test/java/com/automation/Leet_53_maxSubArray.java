package com.automation;

public class Leet_53_maxSubArray
{

    public static void main(String[] args) {
        int[] num = {-2,1,-3,4,-1,2,1,-5,4};

        int max = maxSubArray(num);
        System.out.println(max);
    }

    public static int maxSubArray(int[] nums)
    {
        int currentSum = nums[0];
        int maxSum= nums[0];


        for (int i=1; i<nums.length ; i++){

            currentSum = currentSum + nums[i];
            maxSum = Math.max(currentSum, maxSum);
            if (currentSum<0){
                currentSum = 0;
            }

        }

        return  maxSum;
    }
}
