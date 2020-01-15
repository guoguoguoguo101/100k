package ThreadBase;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//他会trylock 失败是返回 不会死锁
public class ReentrantLockTask {


}

class counter{
    private final Lock lock = new ReentrantLock();
    private int count;

   public void add(){
       lock.lock();
       try {
           this.count++;
       }finally {
           lock.unlock();
       }
   }
   public void dec(){
       try {
           this.count--;
       }finally {
           lock.unlock();
       }
   }


}
