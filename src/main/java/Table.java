import java.util.concurrent.CountDownLatch;

public class Table implements Runnable{
    private int PhilosophersAmount = 2;
    private Philosopher[] philosophers;
    private Fork[] forks;
    private CountDownLatch cdl;

    public Table() {
        this.philosophers = new Philosopher[PhilosophersAmount];;
        this.forks = new Fork[PhilosophersAmount];
        this.cdl = cdl;
        initialisation();
    }

    @Override
    public void run() {
        try {
            startToThink();
            cdl.await();
        } catch (InterruptedException e){
            throw new RuntimeException();
        }
        System.out.println("Заседание окончено");

    }

    public void startLunch(){
        for (Philosopher philosopher  : philosophers) {
            philosopher.start();
        }
    }

    private void startToThink() {

    }

    public synchronized boolean takeForks(int rightFork, int leftFork) {
        if (!forks[rightFork].forkCondition() && !forks[leftFork].forkCondition()) {
            forks[rightFork].setForkCondition(true);
            forks[leftFork].setForkCondition(true);
            return true;
        }
        return false;
    }

    private void initialisation() {
        for (int i = 0; i < PhilosophersAmount; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < PhilosophersAmount; i++) {
            philosophers[i] = new Philosopher("Философ" + i, i, i + 1, cdl, this);
        }
    }

    public void putForks(int rightFork, int leftFork) {
        forks[rightFork].setForkCondition(false);
        forks[leftFork].setForkCondition(false);
    }
}
