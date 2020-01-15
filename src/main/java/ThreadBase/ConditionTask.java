package ThreadBase;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 使用reentrantlock 锁 == notify wait
public class ConditionTask {
}
class QueueTask{

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String s){
        lock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }

}
