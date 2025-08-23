/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * compute how much water it can trap after raining.
 * 
 * Link: https://leetcode.com/problems/trapping-rain-water/description/?envType=problem-list-v2&envId=stack
 * Author: AAYANK SINGHAI (MT2025001)
 */



public class TrappingRainWater {
    public int trap(int[] height){   
        int totalWaterAccumulated = 0;
        int[] leftMax = computePrefixMax(height);
        int[] rightMax = computeSuffixMax(height);

        for(int i = 0; i < height.length; i++){
            if(height[i] < leftMax[i] && height[i] < rightMax[i]){
                totalWaterAccumulated += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
        }
        return totalWaterAccumulated;
    }

    public static int[] computePrefixMax(int[] height){
        int[] prefixMax = new int[height.length];
        prefixMax[0] = height[0];

        for(int i = 1; i < height.length; i++){
            prefixMax[i] = Math.max(prefixMax[i-1], height[i]);
        }
        return prefixMax;
    }

    public static int[] computeSuffixMax(int[] height){
        int[] suffixMax = new int[height.length];
        suffixMax[height.length-1] = height[height.length-1];
        for(int i = height.length-2; i >= 0; i--){
            suffixMax[i] = Math.max(suffixMax[i+1], height[i]);
        }
        return suffixMax;
    }
}
