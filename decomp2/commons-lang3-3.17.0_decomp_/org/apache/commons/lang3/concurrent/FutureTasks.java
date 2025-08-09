package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTasks {
   public static FutureTask run(Callable callable) {
      FutureTask<V> futureTask = new FutureTask(callable);
      futureTask.run();
      return futureTask;
   }

   private FutureTasks() {
   }
}
