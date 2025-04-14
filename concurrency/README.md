The `wait()` and `notify()` (and `notifyAll()`) methods in Java are used for inter-thread communication, allowing threads to coordinate their actions based on specific conditions. They are always called within synchronized blocks or methods, as they operate on the monitor of the object.

* `wait()`:
When a thread calls wait() on an object, it releases the lock on that object and enters the waiting state. It remains in this state until another thread calls notify() or notifyAll() on the same object. The wait() method can also have a timeout, after which the thread will wake up automatically. 

* `notify()`:
This method wakes up a single thread that is waiting on the object's monitor. If multiple threads are waiting, the choice of which thread to wake up is arbitrary.

* `notifyAll()`:
This method wakes up all threads that are waiting on the object's monitor. Each thread will then compete for the lock on the object and proceed if it acquires it.

The key differences are that `wait()` causes a thread to release the lock and pause execution, while `notify()` and `notifyAll()` signal waiting threads that a condition may have changed, allowing them to resume execution and attempt to reacquire the lock.