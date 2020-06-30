package org.basic;

import org.apache.zookeeper.KeeperException;
import scala.Option;
import scala.collection.View;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

//go to implementation to read source code
public class CodeStudy {
    private Optional<String> find(List<String> list) {
        return list.stream().filter(x -> x.equals( "925" )).findAny();
    }
    private Optional<String> find2(List<String> list) {
        Optional<String> res = Optional.empty();
        for (String x: list) {
            if (x.equals( "925" )) return Optional.of(x);
        }
        return res;
    }
    public static void main(String[] args) {

        Stream s;
        CodeStudy app = new CodeStudy();
        Optional<String> res = app.find2(Arrays.asList( "3249", "2313", "3242" ));
        res.ifPresent( x -> System.out.println("found phone = " + x));

        TreeSet ts = new TreeSet(  );
        BitSet bs = new BitSet(  );

        final int ADDRESS_BITS_PER_WORD = 6;
        int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;
        System.out.println("BITS_PER_WORD =" + BITS_PER_WORD);
        HashSet set = new HashSet(  );
        LinkedHashSet ls = new LinkedHashSet(  );
        Thread t = new Thread(  );
        t.setPriority( Thread.MAX_PRIORITY );
        BlockingQueue q = new LinkedBlockingQueue();
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>(  );
        Collections.synchronizedMap(new HashMap<>(  ) );
        Collections.synchronizedList( new ArrayList<>(  ) );
        ReentrantLock lock = new ReentrantLock(  );
        lock.lock();
        lock.lock(); //reentrant
        try {
            System.out.println( "inside critical region" );
        } finally {
            lock.unlock();
            lock.unlock();
        }

    }
    private int value;
    public void update(int value) {
        System.out.println("before tasks");
        synchronized (this) { //rather than lock entire method
            System.out.println("small critical section");
            this.value = value;
        }
        System.out.println("other tasks");
    }
}
