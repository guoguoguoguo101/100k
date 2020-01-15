package TimerandSchedule;

import java.util.concurrent.*;

public class ScheduleExecutorTest implements Runnable {
    private String name;
    ScheduleExecutorTest(String name){
        super();
        this.name = name;
    }
    @Override
    public void run() {
        if (name.equals("nono")){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name+"execute");
    }

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        long initialDelay1 = 1;
        long period1 = 1;
        service.scheduleAtFixedRate(new ScheduleExecutorTest("bobo"), initialDelay1, period1, TimeUnit.SECONDS);
        long initialDelay2 = 2;
        long period2 = 2;
        service.scheduleAtFixedRate(new ScheduleExecutorTest("nono"), initialDelay2, period2, TimeUnit.SECONDS);
    }
}
