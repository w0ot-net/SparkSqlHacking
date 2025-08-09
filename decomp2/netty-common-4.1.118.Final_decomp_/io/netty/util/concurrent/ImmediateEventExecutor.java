package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public final class ImmediateEventExecutor extends AbstractEventExecutor {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(ImmediateEventExecutor.class);
   public static final ImmediateEventExecutor INSTANCE = new ImmediateEventExecutor();
   private static final FastThreadLocal DELAYED_RUNNABLES = new FastThreadLocal() {
      protected Queue initialValue() throws Exception {
         return new ArrayDeque();
      }
   };
   private static final FastThreadLocal RUNNING = new FastThreadLocal() {
      protected Boolean initialValue() throws Exception {
         return false;
      }
   };
   private final Future terminationFuture;

   private ImmediateEventExecutor() {
      this.terminationFuture = new FailedFuture(GlobalEventExecutor.INSTANCE, new UnsupportedOperationException());
   }

   public boolean inEventLoop() {
      return true;
   }

   public boolean inEventLoop(Thread thread) {
      return true;
   }

   public Future shutdownGracefully(long quietPeriod, long timeout, TimeUnit unit) {
      return this.terminationFuture();
   }

   public Future terminationFuture() {
      return this.terminationFuture;
   }

   /** @deprecated */
   @Deprecated
   public void shutdown() {
   }

   public boolean isShuttingDown() {
      return false;
   }

   public boolean isShutdown() {
      return false;
   }

   public boolean isTerminated() {
      return false;
   }

   public boolean awaitTermination(long timeout, TimeUnit unit) {
      return false;
   }

   public void execute(Runnable command) {
      ObjectUtil.checkNotNull(command, "command");
      if (!(Boolean)RUNNING.get()) {
         RUNNING.set(true);

         try {
            command.run();
         } catch (Throwable cause) {
            logger.info("Throwable caught while executing Runnable {}", command, cause);
         } finally {
            Queue delayedRunnables = (Queue)DELAYED_RUNNABLES.get();

            Runnable runnable;
            while((runnable = (Runnable)delayedRunnables.poll()) != null) {
               try {
                  runnable.run();
               } catch (Throwable cause) {
                  logger.info("Throwable caught while executing Runnable {}", runnable, cause);
               }
            }

            RUNNING.set(false);
         }
      } else {
         ((Queue)DELAYED_RUNNABLES.get()).add(command);
      }

   }

   public Promise newPromise() {
      return new ImmediatePromise(this);
   }

   public ProgressivePromise newProgressivePromise() {
      return new ImmediateProgressivePromise(this);
   }

   static class ImmediatePromise extends DefaultPromise {
      ImmediatePromise(EventExecutor executor) {
         super(executor);
      }

      protected void checkDeadLock() {
      }
   }

   static class ImmediateProgressivePromise extends DefaultProgressivePromise {
      ImmediateProgressivePromise(EventExecutor executor) {
         super(executor);
      }

      protected void checkDeadLock() {
      }
   }
}
