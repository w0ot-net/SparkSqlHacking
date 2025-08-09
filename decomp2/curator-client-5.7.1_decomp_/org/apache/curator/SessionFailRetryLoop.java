package org.apache.curator;

import java.io.Closeable;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.collect.Sets;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class SessionFailRetryLoop implements Closeable {
   private final CuratorZookeeperClient client;
   private final Mode mode;
   private final Thread ourThread = Thread.currentThread();
   private final AtomicBoolean sessionHasFailed = new AtomicBoolean(false);
   private final AtomicBoolean isDone = new AtomicBoolean(false);
   private final RetryLoop retryLoop;
   private final Watcher watcher = new Watcher() {
      public void process(WatchedEvent event) {
         if (event.getState() == KeeperState.Expired) {
            SessionFailRetryLoop.this.sessionHasFailed.set(true);
            SessionFailRetryLoop.failedSessionThreads.add(SessionFailRetryLoop.this.ourThread);
         }

      }
   };
   private static final Set failedSessionThreads = Sets.newSetFromMap(Maps.newConcurrentMap());

   public static Object callWithRetry(CuratorZookeeperClient client, Mode mode, Callable proc) throws Exception {
      T result = (T)null;
      SessionFailRetryLoop retryLoop = client.newSessionFailRetryLoop(mode);
      retryLoop.start();

      try {
         while(retryLoop.shouldContinue()) {
            try {
               result = (T)proc.call();
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               retryLoop.takeException(e);
            }
         }
      } finally {
         retryLoop.close();
      }

      return result;
   }

   SessionFailRetryLoop(CuratorZookeeperClient client, Mode mode) {
      this.client = client;
      this.mode = mode;
      this.retryLoop = client.newRetryLoop();
   }

   static boolean sessionForThreadHasFailed() {
      return failedSessionThreads.contains(Thread.currentThread());
   }

   public void start() {
      Preconditions.checkState(Thread.currentThread().equals(this.ourThread), "Not in the correct thread");
      this.client.addParentWatcher(this.watcher);
   }

   public boolean shouldContinue() {
      boolean localIsDone = this.isDone.getAndSet(true);
      return !localIsDone;
   }

   public void close() {
      Preconditions.checkState(Thread.currentThread().equals(this.ourThread), "Not in the correct thread");
      failedSessionThreads.remove(this.ourThread);
      this.client.removeParentWatcher(this.watcher);
   }

   public void takeException(Exception exception) throws Exception {
      Preconditions.checkState(Thread.currentThread().equals(this.ourThread), "Not in the correct thread");
      boolean passUp = true;
      if (this.sessionHasFailed.get()) {
         switch (this.mode) {
            case RETRY:
               this.sessionHasFailed.set(false);
               failedSessionThreads.remove(this.ourThread);
               if (exception instanceof SessionFailedException) {
                  this.isDone.set(false);
                  passUp = false;
               }
            case FAIL:
         }
      }

      if (passUp) {
         this.retryLoop.takeException(exception);
      }

   }

   public static class SessionFailedException extends Exception {
      private static final long serialVersionUID = 1L;
   }

   public static enum Mode {
      RETRY,
      FAIL;
   }
}
