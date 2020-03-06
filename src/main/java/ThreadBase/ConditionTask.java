package ThreadBase;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 使用reentrantlock 锁 == notify wait
public class ConditionTask {

    public static void main(String[] args) {
        QueueTask q = new QueueTask();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
            while (true){
                try {
                    q.addTask("ttttzzz");
                    System.out.println("放入tttzz");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        String cc = q.getTask();
                        System.out.println(cc+"输出");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
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
