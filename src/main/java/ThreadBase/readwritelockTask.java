package ThreadBase;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class readwritelockTask {


}

class Counter {
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();  //悲观锁 默认了他要写要读
    private final Lock rlock = rwlock.readLock();
    private final Lock wlock = rwlock.writeLock();

    private int[] counts = new int[10];

    public void inc(int index) {
        wlock.lock();
        try {
            counts[index] += 1;
        } finally {
            wlock.unlock();
        }
    }

    public int[] get() {
        rlock.lock();
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            rlock.unlock();
        }
    }
}
