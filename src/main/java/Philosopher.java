import java.util.concurrent.CountDownLatch;


public class Philosopher extends Thread{

    private String name;
    private int leftFork;
    private int rightFork;
    private int count = 0;
    private CountDownLatch cdl;
    private Table table;

    public Philosopher(String name, int leftFork, int rightFork, CountDownLatch cdl, Table table) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.cdl = cdl;
        this.table = table;
    }

    @Override
    public void run() throws InterruptedException {
        while (count < 3) {
            think();
            eat();
        }
        System.out.println(name + " теперь сыт");
        cdl.countDown();
    }

    private void think() {
        try{
            Thread.sleep(3000);
            System.out.println(name + " задумался");
        }catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    private void eat() {
        try{
            if(table.takeForks(rightFork, leftFork)) {
                System.out.println(name + "ест");
                Thread.sleep(3000);
                System.out.println(name + "поел");
                table.putForks(rightFork, leftFork);
                count ++;
            }
        }catch (InterruptedException e){
            e.fillInStackTrace();
        }
    }
}
