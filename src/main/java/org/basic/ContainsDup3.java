package org.basic;

import java.util.TreeSet;

public class ContainsDup3 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int [] nums = {1,2,3,1};
        int k = 3, t = 0;
        boolean res = s.containsNearbyAlmostDuplicate(nums, k, t  );
        System.out.println("res=" + res);
    }

    /*

k is the time window, within the window, value diff <= t
for each window O(N) windows, window size is k, check diff, k^2 time
if found any violation, return false
passing all tests, return true

improve?
for the k values in the window
  track min and max, O(k), if max - min > t, false
improve more?
  sliding window, remove left and add right, O(1) time to get updated sorted number in the window

  bingo
*/
    static class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (nums.length == 0) return false;
            TreeSet<Integer> window = new TreeSet<>();
        /*
        for (int i = 0; i <=k && i < nums.length; i++) {
            window.add(nums[i]);
        }
        */

            for (int i = 0; i < nums.length; i++) {

                if (i <=k) { //0 --- k
                    window.add(nums[i]);

                }else { //i = k+1
                    window.remove(nums[i-k-1]); //remove left
                    window.add(nums[i]);
                }
                int min = window.first();
                int max = window.last(); //logk
                System.out.println("min=" + min + " max=" + max);
                if (max - min > t) return false;




            }

            //passing all tests
            return true;
        }
    }
}
