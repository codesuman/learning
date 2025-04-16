import java.util.concurrent.atomic.AtomicInteger;

public class SequentialPrinter {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(1);

        for (int i = 1; i <= 10; i++) {
            new Thread(new SequentialPrintTask(i, counter), "Thread "+i).start();
        }
    }
}

class SequentialPrintTask implements Runnable {
    private final int index;
    private final AtomicInteger counter;

    public SequentialPrintTask(int index, AtomicInteger counter) {
        this.index = index;
        this.counter = counter;
    }

    @Override
    public void run() {
        while(this.counter.get() <= 100) {
            synchronized (this.counter) {
                if(this.counter.get() % 10 == index || (this.counter.get() % 10 == 0 && index == 10)) {
                    System.out.println(Thread.currentThread().getName() + " : " + this.counter.getAndIncrement());
                    this.counter.notifyAll();
                } else {
                    try {
                        this.counter.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}