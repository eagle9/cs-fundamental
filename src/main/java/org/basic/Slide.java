package org.basic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
/*
XXCACACADD
CACBBA
*/
public class Slide {
    public static String sub(String s) {
        char [] c = s.toCharArray();
        int maxLen = 0;
        int start = 0;
        int left = 0, right =0;
        //expand right, check condition at most two distinct chars inside the window
        Map<Character,Integer> window = new HashMap<Character,Integer>();
        while (right < c.length) {
            //expand to right
            while (right < c.length && window.size() <=2) {
                int count = window.getOrDefault(c[right],0);
                window.put(c[right], count+1);
                //satify the result
                if (window.size()  <= 2 && (right - left +1 > maxLen)) {
                    start = left;
                    maxLen = right -left +1;
                }
                right++;
            }
            //size > 2, contract left
            while (left < c.length && window.size() > 2) {
                int count = window.get(c[left]);
                if (count -1 == 0) {
                    window.remove(c[left]);
                }else {//count down
                    window.put(c[left], count-1);
                }
                left++;
            }
            //now window size <=2
        }
        return s.substring(start, start+maxLen); //
    }
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger( Slide.class.getName() );
        logger.info("hello");
        String s = "CCACBBA";
        System.out.println(s + "," + sub(s));

        String s3 = "ccaabbb";
        String res = sub(s3);
        System.out.println(s3 + "," + sub(s3)+ "," + " expect aabbb");

        String s2 = "eceba";
        System.out.println(s2 + "," + sub(s2) + "," + " expect ece");



/*
		Example 1: Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:
Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
*/
    }
}
