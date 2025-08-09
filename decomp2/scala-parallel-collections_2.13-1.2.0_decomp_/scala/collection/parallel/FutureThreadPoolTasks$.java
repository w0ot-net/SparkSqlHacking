package scala.collection.parallel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public final class FutureThreadPoolTasks$ {
   public static final FutureThreadPoolTasks$ MODULE$ = new FutureThreadPoolTasks$();
   private static final int numCores = Runtime.getRuntime().availableProcessors();
   private static final AtomicLong tcount = new AtomicLong(0L);
   private static final ExecutorService defaultThreadPool = Executors.newCachedThreadPool();

   public int numCores() {
      return numCores;
   }

   public AtomicLong tcount() {
      return tcount;
   }

   public ExecutorService defaultThreadPool() {
      return defaultThreadPool;
   }

   private FutureThreadPoolTasks$() {
   }
}
