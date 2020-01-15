package ThreadBase;

public class Threadinterrupet {



    public static void main(String[] args)  throws  Exception{

        Thread t1 = new hello();
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}

class hello extends Thread{
    public  void  run(){
        int i = 0;
        while (!isInterrupted()){
            System.out.println("hellohello"+">>>>>>>>>"+i);
            i++;
        }

    }
}

