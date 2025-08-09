package org.glassfish.jersey.innate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.glassfish.jersey.innate.virtual.LoomishExecutors;

public final class VirtualThreadSupport {
   private static final LoomishExecutors NON_VIRTUAL = new NonLoomishExecutors(Executors.defaultThreadFactory());

   private VirtualThreadSupport() {
      throw new IllegalStateException();
   }

   public static boolean isVirtualThread() {
      return false;
   }

   public static LoomishExecutors allowVirtual(boolean allow) {
      return NON_VIRTUAL;
   }

   public static LoomishExecutors allowVirtual(boolean allow, ThreadFactory threadFactory) {
      return new NonLoomishExecutors(threadFactory);
   }

   private static final class NonLoomishExecutors implements LoomishExecutors {
      private final ThreadFactory threadFactory;

      private NonLoomishExecutors(ThreadFactory threadFactory) {
         this.threadFactory = threadFactory;
      }

      public ExecutorService newCachedThreadPool() {
         return Executors.newCachedThreadPool();
      }

      public ExecutorService newFixedThreadPool(int nThreads) {
         return Executors.newFixedThreadPool(nThreads);
      }

      public ThreadFactory getThreadFactory() {
         return this.threadFactory;
      }

      public boolean isVirtual() {
         return false;
      }
   }
}
