package ThreadBase;

import java.util.LinkedList;
import java.util.Queue;

public class WaitNoify {


}

class TaskQueue{
    public Queue<String> queue = new LinkedList();

    public synchronized  void addTask(String s){
        this.queue.add(s);
        this.notify();
    }

    public synchronized String getTask(String s) throws InterruptedException {
        while (queue.isEmpty()){
            this.wait();

        }
        return  queue.remove();
    }

}
