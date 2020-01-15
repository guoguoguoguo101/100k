package ThreadBase;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTask {


}
class point{
    private final StampedLock lock = new StampedLock(); //乐观锁
    private double x;
    private double y;

    public void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp); // 释放写锁
        }
    }

    public double distanceFromOrigin(){

        long stamp = lock.tryOptimisticRead(); //获得一个乐观读锁
        double currentX = x;
        double currentY = y; //非原子操作

        if (!lock.validate(stamp)){
            stamp = lock.readLock(); //如果在读取的时候产生了 写操作  再次获得悲观锁 重新读取
            try {
                 currentX = x;
                 currentY = y; //非原子操作
            }finally {
        lock.unlockRead(stamp); //释放悲观锁
            }
        }

         return Math.sqrt(currentX * currentX + currentY * currentY);


    }

}
