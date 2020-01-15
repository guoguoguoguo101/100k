package TimerandSchedule;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest extends TimerTask {

    private String name;
    TimerTest(String name){
        super();
        this.name = name;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("execute"+name);

    }


    public static void main(String[] args) {
        Timer timer = new Timer();
        long delay1 = 1 * 1000;
        long period1 = 1000;
        timer.schedule(new TimerTest("bobo"),delay1,period1);

        long delay2 = 1 * 2000;
        long period2 = 2000;
        timer.schedule(new TimerTest("nana"),delay2,period2);
    }
}
