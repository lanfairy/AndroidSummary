package com.lanfairy.elly.androidsummary;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TreeMap;

public class LeetCode350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)){
                list.add(num);
                if(map.get(num)-1==0){
                    map.remove(num);
                }else {
                    map.put(num, map.get(num)-1);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }
    private static int partition(int[] arr, int l, int r){

        int v = arr[l];

        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for( int i = l + 1 ; i <= r ; i ++ )
            if( arr[i]-v < 0 ){
                j ++;
                swap(arr, j, i);
            }

        swap(arr, l, j);

        return j;
    }
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    @Test
    public void test() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2,1,3,1,4};
        int[] res = intersect(nums1, nums2);
int[] num3 = {6, 9, 6,3, 7, 4, 1, 9};
int pos = partition(num3,0, num3.length-1);
        //1 3 4 6 9 7 9
        //
        //          i
        //      j
        //1 3 4 6 9 7 9
    }
}