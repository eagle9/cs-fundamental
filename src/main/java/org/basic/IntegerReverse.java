package org.basic;

import java.util.ArrayList;
import java.util.List;

public class IntegerReverse {

    public static void main(String[] args) {
        new  Solution().reverse( 123 );
    }
    /*
get digit by digit

*/
    static class Solution {
        public int reverse(int x) {
            if (x == 0) return x;

            int sign = x >0? 1: -1;
            int y = x * sign;
            List<Integer> list = new ArrayList<>();
            while ( y > 0 ) {
                int digit = y % 10;

                list.add(digit);
                y = y/10;
            }
            System.out.println(list);
            //reconstruct the number from digits
            int res = 0;
            for (int i=0; i < list.size(); i++) {
                int d = list.get(i);
                System.out.println(d);
                if (res >= Integer.MAX_VALUE/10) return 0;
                res = res*10 + d;

            }
            return res*sign;
        }
    }
}
