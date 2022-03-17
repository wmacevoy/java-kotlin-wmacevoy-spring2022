package com.example.threading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Locker implements AutoCloseable {
    ReentrantLock lock;
    Locker(ReentrantLock lock) {
        this.lock = lock;
        lock.lock();
    }
    Locker(ReentrantLock lock, long timeout) {
        this.lock = null;
        try {
            if (timeout > 0 ? lock.tryLock(timeout, TimeUnit.MILLISECONDS) : lock.tryLock()) {
                this.lock = lock;
            }
        } catch (InterruptedException e) {
        }
    }

    boolean locked() { return lock != null; }
    void unlock() {
        if (lock != null) { lock.unlock(); }
        lock = null;
    }
    @Override
    public void close() {
        unlock();
    }
}
