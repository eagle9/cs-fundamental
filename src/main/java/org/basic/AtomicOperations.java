package org.basic;

import java.util.concurrent.atomic.*;

public class AtomicOperations {
    volatile long counter = 0L;
    public static void main(String[] args) {
        /*

        read and assign of all primitive types except long and double

        read and assign of all volatile long and double

        read and assign of all reference assignment

        atomic classes


         */

        //volatile long counter = 0L;  volatile can not be local variable
        AtomicInteger i = new AtomicInteger(  );

    }
}
