package io.vertx.core.net.impl.pool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreExecutor implements Executor {
   private final Lock lock = new ReentrantLock();
   private final Object state;

   public SemaphoreExecutor(Object state) {
      this.state = state;
   }

   public void submit(Executor.Action action) {
      this.lock.lock();
      Task post = null;

      try {
         post = action.execute(this.state);
      } finally {
         this.lock.unlock();

         while(post != null) {
            post.run();
            post = post.next();
         }

      }

   }
}
