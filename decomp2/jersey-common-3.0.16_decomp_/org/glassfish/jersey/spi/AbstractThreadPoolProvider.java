package org.glassfish.jersey.spi;

import jakarta.ws.rs.core.Configuration;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.innate.VirtualThreadUtil;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;
import org.glassfish.jersey.internal.util.ExtendedLogger;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.process.JerseyProcessingUncaughtExceptionHandler;

public abstract class AbstractThreadPoolProvider implements AutoCloseable {
   private static final ExtendedLogger LOGGER;
   public static final int DEFAULT_TERMINATION_TIMEOUT = 5000;
   private final String name;
   private final AtomicBoolean closed;
   private final LazyValue lazyExecutorServiceProvider;

   protected AbstractThreadPoolProvider(String name) {
      this(name, (Configuration)null);
   }

   protected AbstractThreadPoolProvider(String name, Configuration configuration) {
      this.closed = new AtomicBoolean(false);
      this.name = name;
      this.lazyExecutorServiceProvider = Values.lazy((Value)(() -> this.createExecutor(this.getCorePoolSize(), this.createThreadFactory(configuration), this.getRejectedExecutionHandler())));
   }

   protected final ThreadPoolExecutor getExecutor() {
      if (this.isClosed()) {
         throw new IllegalStateException(LocalizationMessages.THREAD_POOL_EXECUTOR_PROVIDER_CLOSED());
      } else {
         return (ThreadPoolExecutor)this.lazyExecutorServiceProvider.get();
      }
   }

   protected abstract ThreadPoolExecutor createExecutor(int var1, ThreadFactory var2, RejectedExecutionHandler var3);

   protected int getTerminationTimeout() {
      return 5000;
   }

   protected int getCorePoolSize() {
      return Runtime.getRuntime().availableProcessors();
   }

   protected RejectedExecutionHandler getRejectedExecutionHandler() {
      return (r, executor) -> {
      };
   }

   protected ThreadFactory getBackingThreadFactory() {
      return null;
   }

   private ThreadFactory createThreadFactory(Configuration configuration) {
      ThreadFactoryBuilder factoryBuilder = (new ThreadFactoryBuilder()).setNameFormat(this.name + "-%d").setThreadFactory(VirtualThreadUtil.withConfig(configuration).getThreadFactory()).setUncaughtExceptionHandler(new JerseyProcessingUncaughtExceptionHandler());
      ThreadFactory backingThreadFactory = this.getBackingThreadFactory();
      if (backingThreadFactory != null) {
         factoryBuilder.setThreadFactory(backingThreadFactory);
      }

      return factoryBuilder.build();
   }

   public final boolean isClosed() {
      return this.closed.get();
   }

   protected void onClose() {
   }

   public final void close() {
      if (this.closed.compareAndSet(false, true)) {
         try {
            this.onClose();
         } finally {
            if (this.lazyExecutorServiceProvider.isInitialized()) {
               AccessController.doPrivileged(shutdownExecutor(this.name, (ExecutorService)this.lazyExecutorServiceProvider.get(), this.getTerminationTimeout(), TimeUnit.MILLISECONDS));
            }

         }

      }
   }

   private static PrivilegedAction shutdownExecutor(String executorName, ExecutorService executorService, int terminationTimeout, TimeUnit terminationTimeUnit) {
      return () -> {
         if (!executorService.isShutdown()) {
            executorService.shutdown();
         }

         if (executorService.isTerminated()) {
            return null;
         } else {
            boolean terminated = false;
            boolean interrupted = false;

            try {
               terminated = executorService.awaitTermination((long)terminationTimeout, terminationTimeUnit);
            } catch (InterruptedException e) {
               if (LOGGER.isDebugLoggable()) {
                  LOGGER.log(LOGGER.getDebugLevel(), "Interrupted while waiting for thread pool executor " + executorName + " to shutdown.", (Throwable)e);
               }

               interrupted = true;
            }

            try {
               if (!terminated) {
                  List<Runnable> cancelledTasks = executorService.shutdownNow();

                  for(Runnable cancelledTask : cancelledTasks) {
                     if (cancelledTask instanceof Future) {
                        ((Future)cancelledTask).cancel(true);
                     }
                  }

                  if (LOGGER.isDebugLoggable()) {
                     LOGGER.debugLog("Thread pool executor {0} forced-shut down. List of cancelled tasks: {1}", executorName, cancelledTasks);
                  }
               }
            } finally {
               if (interrupted) {
                  Thread.currentThread().interrupt();
               }

            }

            return null;
         }
      };
   }

   static {
      LOGGER = new ExtendedLogger(Logger.getLogger(AbstractThreadPoolProvider.class.getName()), Level.FINEST);
   }
}
