package com.lol;

/**
 6/20 Homework: Bit-string flicking
 In case anybody is still confused about logic operators, more information can be found here:
 http://www.categories.acsl.org/wiki/index.php?title=Bit-String_Flicking
 (or you can look it up yourself). I've also attached a table of what each operation outputs for reference.

 HW Questions (Do stuff in brackets first!):

 LSHIFT-2(10011 XOR 00110) OR (LCIRC-2(RCIRC-2(10001)))
 10011
 00110  XOR, diff -> 1
 10101, LSHIFT-2(10101) = 10100
 RCIRC-2(10001) =  01100
 LCIRC-2(01100) =  10001
 final result = 10100 OR 10001
 10100
 10001  OR
 10101

 ( (RSHIFT-3(01000) XOR NOT(00110) ) AND (01101 OR RCIRC-2(10011))


 BONUS:

 What should X be to satisfy the following equation? (There are multiple answers; name as many as you want!)

 X AND 11010 = 01000
 */
public class HW620 {
    public final static int NB = 5;

    public final static int mask = getMask();
    public static int getMask() {
        int m = 0;
        for (int i = 0; i < NB; i++) {
            m |= 1 <<i;
        }
        return m;
    }
    public static String toBinaryString(int n) {
        char [] a = new char[NB];
        for (int i = 0; i < NB; i++) {
            //a[NB -1 - i] = (char)('0' + (n>>i) & 1);
            a[NB -1 - i] = ((n>>i) & 1) == 0? '0' : '1';
        }
        return new String(a);
    }

    public static int RCIRC(int bits, int k) {
        return (bits >>> k) | ( (bits << (NB-k)) & mask);
    }
    public static int LCIRC(int bits, int k) {

        return (bits >>> (NB-k)) | ( (bits << k) & mask);
    }
    public static void main(String[] args) {
        //cicular shift or ratating shift
        //RCIRC-2(10011) = 11100
        int a = 0b10011;
        int res = Integer.rotateRight( a,2 );
        System.out.println(toBinaryString( a ) + " RCIRC-2 ->" + toBinaryString( res ) );
        System.out.println(toBinaryString( a ) + " RCIRC-2 ->" + toBinaryString( RCIRC( a,2 ) ) );

        //LCIRC-3(01101) = 01011
        //01000 <<3
        //  011 >>>2
        //01011  OR
        a = 0b01101;
        System.out.println(toBinaryString( a ) + " LCIRC-3 ->" + toBinaryString( LCIRC( a,3 ) ));


        String e1 = "LSHIFT-2(10011 XOR 00110) OR (LCIRC-2(RCIRC-2(10001)))";
        int res1 =  ((0b10011 ^ 0b00110)>>>2) | LCIRC( RCIRC(0b10001 ,2 ), 2);
        System.out.println(e1 + " = " + toBinaryString( res1 ) + ", manual = 10101");

        String e2 = "( (RSHIFT-3(01000) XOR NOT(00110) ) AND (01101 OR RCIRC-2(10011))";
        int res2 = ((0b01000>>>3) ^ (~0b00110)) & (0b01101 | RCIRC(0b10011, 2 ));

        System.out.println(e2 + " = " + toBinaryString( res2 ) + ", manual = ?????");


        System.out.println("X AND 11010 = 01000, find all X");
        for (byte i = 0; i <= 0b11111; i++) {
            if ((i & 0b11010)== 0b01000) {
                System.out.println(toBinaryString( i ));
            }
        }
    }
}
