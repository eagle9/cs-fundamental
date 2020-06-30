package com.lol;

/**
 *Pick a random decimal number between 100 and 999 and convert it to binary,
 * octal, and hexadecimal while showing work. That's it!
 */
public class HW613 {
    public static void main(String[] args) {

    }
    private static String convert(int n, int b) {
        StringBuilder sb = new StringBuilder(  );

        int mask = 0;
        for (int i = 0; i < b; i++) {
            mask |= 1 << i;
        }
        while (n > 0) {
            int digit = n & mask;

            n = n>>b;
            if (digit < 10) {
                sb.insert( 0, digit );
            }else {
                char c = (char)('A' + (digit-10));
                sb.insert( 0, c );
            }

        }
        return sb.toString();
    }
}
