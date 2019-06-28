package com.lanfairy.elly.androidsummary;

import android.util.Log;

import org.junit.Test;

/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [3,2,2,3], val = 3,
 * <p>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 */
public class LeetCode27 {
    private static final String TAG = "LeetCode27";
    public int removeElement(int[] nums, int val) {
        int k = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i]!=val){
               nums[k++] = nums[i];
            }
        }
        for (int i = k; i < n; i++){
            nums[i] = val;
        }
        k = 0;
        for (int i = 0; i < n; i++){
            if (nums[i]!=val)k++;
            else break;
        }
        return k;
    }

    private void swap(int[] nums, int i, int j){
        nums[i] = nums[i]+nums[j];
        nums[j] = nums[i]-nums[j];
        nums[i] = nums[i]-nums[j];
    }
    @Test
    public void test(){
        int[] nums = {3,2,2,3};
       int res = removeElement(nums, 3);
       System.out.print(res);
    }
}
