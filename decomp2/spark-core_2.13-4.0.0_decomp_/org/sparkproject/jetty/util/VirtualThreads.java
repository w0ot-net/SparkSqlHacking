package org.sparkproject.jetty.util;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VirtualThreads {
   private static final Logger LOG = LoggerFactory.getLogger(VirtualThreads.class);
   private static final Executor executor = probeVirtualThreadExecutor();
   private static final Method isVirtualThread = probeIsVirtualThread();

   private static Executor probeVirtualThreadExecutor() {
      try {
         return (Executor)Executors.class.getMethod("newVirtualThreadPerTaskExecutor").invoke((Object)null);
      } catch (Throwable var1) {
         return null;
      }
   }

   private static Method probeIsVirtualThread() {
      try {
         return Thread.class.getMethod("isVirtual");
      } catch (Throwable var1) {
         return null;
      }
   }

   private static Method getIsVirtualThreadMethod() {
      return isVirtualThread;
   }

   private static void warn() {
      LOG.warn("Virtual thread support is not available (or not enabled via --enable-preview) in the current Java runtime ({})", System.getProperty("java.version"));
   }

   public static boolean areSupported() {
      return executor != null;
   }

   /** @deprecated */
   @Deprecated(
      forRemoval = true
   )
   public static void executeOnVirtualThread(Runnable task) {
      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Starting in virtual thread: {}", task);
         }

         getDefaultVirtualThreadsExecutor().execute(task);
      } catch (Throwable x) {
         warn();
         throw new UnsupportedOperationException(x);
      }
   }

   public static boolean isVirtualThread() {
      try {
         return (Boolean)getIsVirtualThreadMethod().invoke(Thread.currentThread());
      } catch (Throwable var1) {
         warn();
         return false;
      }
   }

   public static Executor getDefaultVirtualThreadsExecutor() {
      return executor;
   }

   public static Executor getVirtualThreadsExecutor(Executor executor) {
      return executor instanceof Configurable ? ((Configurable)executor).getVirtualThreadsExecutor() : null;
   }

   public static boolean isUseVirtualThreads(Executor executor) {
      if (executor instanceof Configurable) {
         return ((Configurable)executor).getVirtualThreadsExecutor() != null;
      } else {
         return false;
      }
   }

   private VirtualThreads() {
   }

   public interface Configurable {
      default Executor getVirtualThreadsExecutor() {
         return null;
      }

      default void setVirtualThreadsExecutor(Executor executor) {
         if (executor != null && !VirtualThreads.areSupported()) {
            VirtualThreads.warn();
            throw new UnsupportedOperationException();
         }
      }

      /** @deprecated */
      @Deprecated(
         forRemoval = true
      )
      default boolean isUseVirtualThreads() {
         return this.getVirtualThreadsExecutor() != null;
      }

      /** @deprecated */
      @Deprecated(
         forRemoval = true
      )
      default void setUseVirtualThreads(boolean useVirtualThreads) {
         this.setVirtualThreadsExecutor(useVirtualThreads ? VirtualThreads.executor : null);
      }
   }
}
