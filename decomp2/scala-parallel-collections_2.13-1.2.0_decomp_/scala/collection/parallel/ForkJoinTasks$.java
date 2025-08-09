package scala.collection.parallel;

import java.util.concurrent.ForkJoinPool;

public final class ForkJoinTasks$ {
   public static final ForkJoinTasks$ MODULE$ = new ForkJoinTasks$();
   private static ForkJoinPool defaultForkJoinPool;
   private static volatile boolean bitmap$0;

   private ForkJoinPool defaultForkJoinPool$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            defaultForkJoinPool = ForkJoinPool.commonPool();
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return defaultForkJoinPool;
   }

   public ForkJoinPool defaultForkJoinPool() {
      return !bitmap$0 ? this.defaultForkJoinPool$lzycompute() : defaultForkJoinPool;
   }

   private ForkJoinTasks$() {
   }
}
