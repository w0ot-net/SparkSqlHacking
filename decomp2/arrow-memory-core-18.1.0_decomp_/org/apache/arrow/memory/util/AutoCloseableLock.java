package org.apache.arrow.memory.util;

import java.util.concurrent.locks.Lock;

public class AutoCloseableLock implements AutoCloseable {
   private final Lock lock;

   public AutoCloseableLock(Lock lock) {
      this.lock = lock;
   }

   public AutoCloseableLock open() {
      this.lock.lock();
      return this;
   }

   public void close() {
      this.lock.unlock();
   }
}
