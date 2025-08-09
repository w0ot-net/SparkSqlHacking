package org.apache.curator.framework.recipes.leader;

import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.utils.CloseableExecutorService;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaderSelector implements Closeable {
   private final Logger log;
   private final CuratorFramework client;
   private final LeaderSelectorListener listener;
   private final CloseableExecutorService executorService;
   private final InterProcessMutex mutex;
   private final AtomicReference state;
   private final AtomicBoolean autoRequeue;
   private Future ourTask;
   private Thread ourThread;
   private volatile boolean hasLeadership;
   private volatile String id;
   @VisibleForTesting
   volatile CountDownLatch debugLeadershipLatch;
   volatile CountDownLatch debugLeadershipWaitLatch;
   private static final ThreadFactory defaultThreadFactory = ThreadUtils.newThreadFactory("LeaderSelector");
   @VisibleForTesting
   volatile AtomicInteger failedMutexReleaseCount;

   public LeaderSelector(CuratorFramework client, String leaderPath, LeaderSelectorListener listener) {
      this(client, leaderPath, new CloseableExecutorService(Executors.newSingleThreadExecutor(defaultThreadFactory), true), listener);
   }

   /** @deprecated */
   @Deprecated
   public LeaderSelector(CuratorFramework client, String leaderPath, ThreadFactory threadFactory, Executor executor, LeaderSelectorListener listener) {
      this(client, leaderPath, new CloseableExecutorService(wrapExecutor(executor), true), listener);
   }

   public LeaderSelector(CuratorFramework client, String leaderPath, ExecutorService executorService, LeaderSelectorListener listener) {
      this(client, leaderPath, new CloseableExecutorService(executorService), listener);
   }

   public LeaderSelector(CuratorFramework client, String leaderPath, CloseableExecutorService executorService, LeaderSelectorListener listener) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.state = new AtomicReference(LeaderSelector.State.LATENT);
      this.autoRequeue = new AtomicBoolean(false);
      this.ourTask = null;
      this.ourThread = null;
      this.id = "";
      this.debugLeadershipLatch = null;
      this.debugLeadershipWaitLatch = null;
      this.failedMutexReleaseCount = null;
      Preconditions.checkNotNull(client, "client cannot be null");
      PathUtils.validatePath(leaderPath);
      Preconditions.checkNotNull(listener, "listener cannot be null");
      this.client = client;
      this.listener = new WrappedListener(this, listener);
      this.hasLeadership = false;
      this.executorService = executorService;
      this.mutex = new InterProcessMutex(client, leaderPath) {
         protected byte[] getLockNodeBytes() {
            return LeaderSelector.this.id.length() > 0 ? LeaderSelector.getIdBytes(LeaderSelector.this.id) : null;
         }
      };
   }

   static byte[] getIdBytes(String id) {
      try {
         return id.getBytes("UTF-8");
      } catch (UnsupportedEncodingException e) {
         throw new Error(e);
      }
   }

   public void autoRequeue() {
      this.autoRequeue.set(true);
   }

   public void setId(String id) {
      Preconditions.checkNotNull(id, "id cannot be null");
      this.id = id;
   }

   public String getId() {
      return this.id;
   }

   public void start() {
      Preconditions.checkState(this.state.compareAndSet(LeaderSelector.State.LATENT, LeaderSelector.State.STARTED), "Cannot be started more than once");
      Preconditions.checkState(!this.executorService.isShutdown(), "Already started");
      Preconditions.checkState(!this.hasLeadership, "Already has leadership");
      this.client.getConnectionStateListenable().addListener(this.listener);
      this.requeue();
   }

   public boolean requeue() {
      Preconditions.checkState(this.state.get() == LeaderSelector.State.STARTED, "close() has already been called");
      return this.internalRequeue();
   }

   private synchronized boolean internalRequeue() {
      if (this.ourTask == null && this.state.get() == LeaderSelector.State.STARTED) {
         this.ourTask = this.executorService.submit(new Callable() {
            public Void call() throws Exception {
               LeaderSelector.this.doWorkLoop();
               return null;
            }
         });
         return true;
      } else {
         return false;
      }
   }

   public synchronized void close() {
      Preconditions.checkState(this.state.compareAndSet(LeaderSelector.State.STARTED, LeaderSelector.State.CLOSED), "Already closed or has not been started");
      this.client.getConnectionStateListenable().removeListener(this.listener);
      this.executorService.close();
      this.ourTask = null;
   }

   public Collection getParticipants() throws Exception {
      Collection<String> participantNodes = this.mutex.getParticipantNodes();
      return getParticipants(this.client, participantNodes);
   }

   static Collection getParticipants(CuratorFramework client, Collection participantNodes) throws Exception {
      ImmutableList.Builder<Participant> builder = ImmutableList.builder();
      boolean isLeader = true;

      for(String path : participantNodes) {
         Participant participant = participantForPath(client, path, isLeader);
         if (participant != null) {
            builder.add(participant);
            isLeader = false;
         }
      }

      return builder.build();
   }

   public Participant getLeader() throws Exception {
      Collection<String> participantNodes = this.mutex.getParticipantNodes();
      return getLeader(this.client, participantNodes);
   }

   static Participant getLeader(CuratorFramework client, Collection participantNodes) throws Exception {
      Participant result = null;
      if (participantNodes.size() > 0) {
         Iterator<String> iter = participantNodes.iterator();

         while(iter.hasNext()) {
            result = participantForPath(client, (String)iter.next(), true);
            if (result != null) {
               break;
            }
         }
      }

      if (result == null) {
         result = new Participant();
      }

      return result;
   }

   public boolean hasLeadership() {
      return this.hasLeadership;
   }

   private synchronized void taskStarted() {
      this.ourThread = Thread.currentThread();
   }

   private synchronized boolean taskDone() {
      this.ourTask = null;
      this.ourThread = null;
      boolean leadership = this.hasLeadership;
      if (leadership) {
         this.hasLeadership = false;
      }

      if (this.autoRequeue.get()) {
         this.internalRequeue();
      }

      return leadership;
   }

   private synchronized void cancelElection() {
      if (this.ourThread != null) {
         this.ourThread.interrupt();
      }

   }

   public synchronized void interruptLeadership() {
      if (this.hasLeadership) {
         this.cancelElection();
      }

   }

   private static Participant participantForPath(CuratorFramework client, String path, boolean markAsLeader) throws Exception {
      try {
         byte[] bytes = (byte[])client.getData().forPath(path);
         String thisId = new String(bytes, "UTF-8");
         return new Participant(thisId, markAsLeader);
      } catch (KeeperException.NoNodeException var5) {
         return null;
      }
   }

   @VisibleForTesting
   void doWork() throws Exception {
      this.taskStarted();
      this.hasLeadership = false;
      boolean var32 = false;

      try {
         var32 = true;
         this.mutex.acquire();
         this.hasLeadership = true;

         try {
            if (this.debugLeadershipLatch != null) {
               this.debugLeadershipLatch.countDown();
            }

            if (this.debugLeadershipWaitLatch != null) {
               this.debugLeadershipWaitLatch.await();
            }

            this.listener.takeLeadership(this.client);
            var32 = false;
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
         } catch (Throwable e) {
            ThreadUtils.checkInterrupted(e);
            var32 = false;
         }
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         throw e;
      } finally {
         if (var32) {
            if (this.taskDone()) {
               boolean wasInterrupted = Thread.interrupted();

               try {
                  this.mutex.release();
               } catch (Exception e) {
                  if (this.failedMutexReleaseCount != null) {
                     this.failedMutexReleaseCount.incrementAndGet();
                  }

                  ThreadUtils.checkInterrupted(e);
                  this.log.error("The leader threw an exception", e);
               } finally {
                  if (wasInterrupted) {
                     Thread.currentThread().interrupt();
                  }

               }
            }

         }
      }

      if (this.taskDone()) {
         boolean wasInterrupted = Thread.interrupted();

         try {
            this.mutex.release();
         } catch (Exception e) {
            if (this.failedMutexReleaseCount != null) {
               this.failedMutexReleaseCount.incrementAndGet();
            }

            ThreadUtils.checkInterrupted(e);
            this.log.error("The leader threw an exception", e);
         } finally {
            if (wasInterrupted) {
               Thread.currentThread().interrupt();
            }

         }
      }

   }

   private void doWorkLoop() throws Exception {
      KeeperException exception = null;

      try {
         this.doWork();
      } catch (KeeperException.ConnectionLossException e) {
         exception = e;
      } catch (KeeperException.SessionExpiredException e) {
         exception = e;
      } catch (InterruptedException var5) {
         Thread.currentThread().interrupt();
      }

      if (exception != null && !this.autoRequeue.get()) {
         throw exception;
      }
   }

   private static ExecutorService wrapExecutor(final Executor executor) {
      return new AbstractExecutorService() {
         private volatile boolean isShutdown = false;
         private volatile boolean isTerminated = false;

         public void shutdown() {
            this.isShutdown = true;
         }

         public List shutdownNow() {
            return Lists.newArrayList();
         }

         public boolean isShutdown() {
            return this.isShutdown;
         }

         public boolean isTerminated() {
            return this.isTerminated;
         }

         public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException();
         }

         public void execute(Runnable command) {
            try {
               executor.execute(command);
            } finally {
               this.isShutdown = true;
               this.isTerminated = true;
            }

         }
      };
   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }

   private static class WrappedListener implements LeaderSelectorListener {
      private final LeaderSelector leaderSelector;
      private final LeaderSelectorListener listener;

      public WrappedListener(LeaderSelector leaderSelector, LeaderSelectorListener listener) {
         this.leaderSelector = leaderSelector;
         this.listener = listener;
      }

      public void takeLeadership(CuratorFramework client) throws Exception {
         this.listener.takeLeadership(client);
      }

      public void stateChanged(CuratorFramework client, ConnectionState newState) {
         try {
            this.listener.stateChanged(client, newState);
         } catch (CancelLeadershipException var4) {
            this.leaderSelector.cancelElection();
         }

      }
   }
}
