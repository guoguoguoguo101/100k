package ThreadPools;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class sheduled {

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(4);  //用于 反复触发的线程池
      //  ses.schedule(new ask("onetime"), 1, TimeUnit.SECONDS);  //1 秒后执行一次任务
        ses.scheduleWithFixedDelay(new ask("fixed-delay"), 2, 3, TimeUnit.SECONDS);  //delay 是指 运行线程后 延迟 时间
        ses.scheduleAtFixedRate(new ask("boy"),0,3,TimeUnit.SECONDS);               //fixedrate 是指运行的线程和间隔 + 起来 等于 period


       // ses.shutdown();
    }



}

class ask implements Runnable{
    public final String name;

    ask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("反复横跳罢了！冲冲冲"+name);
    }
}
