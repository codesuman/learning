import java.util.concurrent.atomic.AtomicInteger;

public class SimpleOddEvenPrinter {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(1);

        new Thread(() -> {
            while(counter.get() <= 10) {
                synchronized (counter) {
                    if(counter.get() % 2 != 0) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " : " + counter.getAndIncrement());
                        counter.notify();
                    }
                }
            }
        }, "Even Thread").start();

        new Thread(() -> {
            while(counter.get() <= 10) {
                synchronized (counter) {
                    if(counter.get() % 2 == 0) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " : " + counter.getAndIncrement());
                        counter.notify();
                    }
                }
            }
        }, "Odd Thread").start();
    }
}