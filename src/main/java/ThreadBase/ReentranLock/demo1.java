package ThreadBase.ReentranLock;

public class demo1 {
    public static void play() throws InterruptedException {
        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }

        String name = Thread.currentThread().getName();
        System.out.println("play111"+name);
    }
    public static void main(String[] args) throws Exception{

        Thread t1 = new Thread(){
            public void run(){
                try {
                    play();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.setName("bobo");

        Thread t2 = new Thread(){
            public void run(){
                try {
                    play();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.setName("nana");

        t1.start();
        t2.start();
    }
}
