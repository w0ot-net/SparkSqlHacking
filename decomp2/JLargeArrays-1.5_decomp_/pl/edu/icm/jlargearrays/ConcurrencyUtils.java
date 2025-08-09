package pl.edu.icm.jlargearrays;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.commons.math3.util.FastMath;

public class ConcurrencyUtils {
   private static final ExecutorService DEFAULT_THREAD_POOL = Executors.newCachedThreadPool(new CustomThreadFactory(new CustomExceptionHandler()));
   private static ExecutorService threadPool;
   private static int nthreads;
   private static long concurrentThreshold;

   private ConcurrencyUtils() {
   }

   public static long getConcurrentThreshold() {
      return concurrentThreshold;
   }

   public static void setConcurrentThreshold(long concurrentThreshold) {
      ConcurrencyUtils.concurrentThreshold = FastMath.max(1L, concurrentThreshold);
   }

   public static int getNumberOfProcessors() {
      return Runtime.getRuntime().availableProcessors();
   }

   public static int getNumberOfThreads() {
      return nthreads;
   }

   public static void setNumberOfThreads(int n) {
      nthreads = n;
   }

   public static Future submit(Callable task) {
      if (threadPool.isShutdown() || threadPool.isTerminated()) {
         threadPool = DEFAULT_THREAD_POOL;
      }

      return threadPool.submit(task);
   }

   public static Future submit(Runnable task) {
      if (threadPool.isShutdown() || threadPool.isTerminated()) {
         threadPool = DEFAULT_THREAD_POOL;
      }

      return threadPool.submit(task);
   }

   public static void waitForCompletion(Future[] futures) throws InterruptedException, ExecutionException {
      int size = futures.length;

      for(int j = 0; j < size; ++j) {
         futures[j].get();
      }

   }

   public static void setThreadPool(ExecutorService threadPool) {
      ConcurrencyUtils.threadPool = threadPool;
   }

   public static ExecutorService getThreadPool() {
      return threadPool;
   }

   public static void shutdownThreadPoolAndAwaitTermination() {
      threadPool.shutdown();

      try {
         if (!threadPool.awaitTermination(60L, TimeUnit.SECONDS)) {
            threadPool.shutdownNow();
            if (!threadPool.awaitTermination(60L, TimeUnit.SECONDS)) {
               System.err.println("Pool did not terminate");
            }
         }
      } catch (InterruptedException var1) {
         threadPool.shutdownNow();
         Thread.currentThread().interrupt();
      }

   }

   static {
      threadPool = DEFAULT_THREAD_POOL;
      nthreads = getNumberOfProcessors();
      concurrentThreshold = 100000L;
   }

   private static class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
      private CustomExceptionHandler() {
      }

      public void uncaughtException(Thread t, Throwable e) {
         e.printStackTrace();
      }
   }

   private static class CustomThreadFactory implements ThreadFactory {
      private static final ThreadFactory DEFAULT_FACTORY = Executors.defaultThreadFactory();
      private final Thread.UncaughtExceptionHandler handler;

      CustomThreadFactory(Thread.UncaughtExceptionHandler handler) {
         this.handler = handler;
      }

      public Thread newThread(Runnable r) {
         Thread t = DEFAULT_FACTORY.newThread(r);
         t.setUncaughtExceptionHandler(this.handler);
         return t;
      }
   }
}
