package org.apache.thrift.server;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TThreadedSelectorServer extends AbstractNonblockingServer {
   private static final Logger LOGGER = LoggerFactory.getLogger(TThreadedSelectorServer.class.getName());
   private AcceptThread acceptThread;
   private final Set selectorThreads = new HashSet();
   private final ExecutorService invoker;
   private final Args args;

   public TThreadedSelectorServer(Args args) {
      super(args);
      args.validate();
      this.invoker = args.executorService == null ? createDefaultExecutor(args) : args.executorService;
      this.args = args;
   }

   protected boolean startThreads() {
      try {
         for(int i = 0; i < this.args.selectorThreads; ++i) {
            this.selectorThreads.add(new SelectorThread(this.args.acceptQueueSizePerThread));
         }

         this.acceptThread = new AcceptThread((TNonblockingServerTransport)this.serverTransport_, this.createSelectorThreadLoadBalancer(this.selectorThreads));

         for(SelectorThread thread : this.selectorThreads) {
            thread.start();
         }

         this.acceptThread.start();
         return true;
      } catch (IOException e) {
         LOGGER.error("Failed to start threads!", e);
         return false;
      }
   }

   protected void waitForShutdown() {
      try {
         this.joinThreads();
      } catch (InterruptedException e) {
         LOGGER.error("Interrupted while joining threads!", e);
      }

      this.gracefullyShutdownInvokerPool();
   }

   protected void joinThreads() throws InterruptedException {
      this.acceptThread.join();

      for(SelectorThread thread : this.selectorThreads) {
         thread.join();
      }

   }

   public void stop() {
      this.stopped_ = true;
      this.stopListening();
      if (this.acceptThread != null) {
         this.acceptThread.wakeupSelector();
      }

      if (this.selectorThreads != null) {
         for(SelectorThread thread : this.selectorThreads) {
            if (thread != null) {
               thread.wakeupSelector();
            }
         }
      }

   }

   protected void gracefullyShutdownInvokerPool() {
      this.invoker.shutdown();
      long timeoutMS = this.args.stopTimeoutUnit.toMillis((long)this.args.stopTimeoutVal);

      long newnow;
      for(long now = System.currentTimeMillis(); timeoutMS >= 0L; now = newnow) {
         try {
            this.invoker.awaitTermination(timeoutMS, TimeUnit.MILLISECONDS);
            break;
         } catch (InterruptedException var8) {
            newnow = System.currentTimeMillis();
            timeoutMS -= newnow - now;
         }
      }

   }

   protected boolean requestInvoke(AbstractNonblockingServer.FrameBuffer frameBuffer) {
      Runnable invocation = this.getRunnable(frameBuffer);
      if (this.invoker != null) {
         try {
            this.invoker.execute(invocation);
            return true;
         } catch (RejectedExecutionException rx) {
            LOGGER.warn("ExecutorService rejected execution!", rx);
            return false;
         }
      } else {
         invocation.run();
         return true;
      }
   }

   protected Runnable getRunnable(AbstractNonblockingServer.FrameBuffer frameBuffer) {
      return new Invocation(frameBuffer);
   }

   protected static ExecutorService createDefaultExecutor(Args options) {
      return options.workerThreads > 0 ? Executors.newFixedThreadPool(options.workerThreads) : null;
   }

   private static BlockingQueue createDefaultAcceptQueue(int queueSize) {
      return (BlockingQueue)(queueSize == 0 ? new LinkedBlockingQueue() : new ArrayBlockingQueue(queueSize));
   }

   protected SelectorThreadLoadBalancer createSelectorThreadLoadBalancer(Collection threads) {
      return new SelectorThreadLoadBalancer(threads);
   }

   public static class Args extends AbstractNonblockingServer.AbstractNonblockingServerArgs {
      public int selectorThreads = 2;
      private int workerThreads = 5;
      private int stopTimeoutVal = 60;
      private TimeUnit stopTimeoutUnit;
      private ExecutorService executorService;
      private int acceptQueueSizePerThread;
      private AcceptPolicy acceptPolicy;

      public Args(TNonblockingServerTransport transport) {
         super(transport);
         this.stopTimeoutUnit = TimeUnit.SECONDS;
         this.executorService = null;
         this.acceptQueueSizePerThread = 4;
         this.acceptPolicy = TThreadedSelectorServer.Args.AcceptPolicy.FAST_ACCEPT;
      }

      public Args selectorThreads(int i) {
         this.selectorThreads = i;
         return this;
      }

      public int getSelectorThreads() {
         return this.selectorThreads;
      }

      public Args workerThreads(int i) {
         this.workerThreads = i;
         return this;
      }

      public int getWorkerThreads() {
         return this.workerThreads;
      }

      public int getStopTimeoutVal() {
         return this.stopTimeoutVal;
      }

      public Args stopTimeoutVal(int stopTimeoutVal) {
         this.stopTimeoutVal = stopTimeoutVal;
         return this;
      }

      public TimeUnit getStopTimeoutUnit() {
         return this.stopTimeoutUnit;
      }

      public Args stopTimeoutUnit(TimeUnit stopTimeoutUnit) {
         this.stopTimeoutUnit = stopTimeoutUnit;
         return this;
      }

      public ExecutorService getExecutorService() {
         return this.executorService;
      }

      public Args executorService(ExecutorService executorService) {
         this.executorService = executorService;
         return this;
      }

      public int getAcceptQueueSizePerThread() {
         return this.acceptQueueSizePerThread;
      }

      public Args acceptQueueSizePerThread(int acceptQueueSizePerThread) {
         this.acceptQueueSizePerThread = acceptQueueSizePerThread;
         return this;
      }

      public AcceptPolicy getAcceptPolicy() {
         return this.acceptPolicy;
      }

      public Args acceptPolicy(AcceptPolicy acceptPolicy) {
         this.acceptPolicy = acceptPolicy;
         return this;
      }

      public void validate() {
         if (this.selectorThreads <= 0) {
            throw new IllegalArgumentException("selectorThreads must be positive.");
         } else if (this.workerThreads < 0) {
            throw new IllegalArgumentException("workerThreads must be non-negative.");
         } else if (this.acceptQueueSizePerThread <= 0) {
            throw new IllegalArgumentException("acceptQueueSizePerThread must be positive.");
         }
      }

      public static enum AcceptPolicy {
         FAIR_ACCEPT,
         FAST_ACCEPT;
      }
   }

   protected class AcceptThread extends Thread {
      private final TNonblockingServerTransport serverTransport;
      private final Selector acceptSelector;
      private final SelectorThreadLoadBalancer threadChooser;

      public AcceptThread(TNonblockingServerTransport serverTransport, SelectorThreadLoadBalancer threadChooser) throws IOException {
         this.serverTransport = serverTransport;
         this.threadChooser = threadChooser;
         this.acceptSelector = SelectorProvider.provider().openSelector();
         this.serverTransport.registerSelector(this.acceptSelector);
      }

      public void run() {
         try {
            if (TThreadedSelectorServer.this.eventHandler_ != null) {
               TThreadedSelectorServer.this.eventHandler_.preServe();
            }

            while(!TThreadedSelectorServer.this.stopped_) {
               this.select();
            }
         } catch (Throwable t) {
            TThreadedSelectorServer.LOGGER.error("run() on AcceptThread exiting due to uncaught error", t);
         } finally {
            try {
               this.acceptSelector.close();
            } catch (IOException e) {
               TThreadedSelectorServer.LOGGER.error("Got an IOException while closing accept selector!", e);
            }

            TThreadedSelectorServer.this.stop();
         }

      }

      public void wakeupSelector() {
         this.acceptSelector.wakeup();
      }

      private void select() {
         try {
            this.acceptSelector.select();
            Iterator<SelectionKey> selectedKeys = this.acceptSelector.selectedKeys().iterator();

            while(!TThreadedSelectorServer.this.stopped_ && selectedKeys.hasNext()) {
               SelectionKey key = (SelectionKey)selectedKeys.next();
               selectedKeys.remove();
               if (key.isValid()) {
                  if (key.isAcceptable()) {
                     this.handleAccept();
                  } else {
                     TThreadedSelectorServer.LOGGER.warn("Unexpected state in select! " + key.interestOps());
                  }
               }
            }
         } catch (IOException e) {
            TThreadedSelectorServer.LOGGER.warn("Got an IOException while selecting!", e);
         }

      }

      private void handleAccept() {
         final TNonblockingTransport client = this.doAccept();
         if (client != null) {
            final SelectorThread targetThread = this.threadChooser.nextThread();
            if (TThreadedSelectorServer.this.args.acceptPolicy != TThreadedSelectorServer.Args.AcceptPolicy.FAST_ACCEPT && TThreadedSelectorServer.this.invoker != null) {
               try {
                  TThreadedSelectorServer.this.invoker.submit(new Runnable() {
                     public void run() {
                        AcceptThread.this.doAddAccept(targetThread, client);
                     }
                  });
               } catch (RejectedExecutionException rx) {
                  TThreadedSelectorServer.LOGGER.warn("ExecutorService rejected accept registration!", rx);
                  client.close();
               }
            } else {
               this.doAddAccept(targetThread, client);
            }
         }

      }

      private TNonblockingTransport doAccept() {
         try {
            return this.serverTransport.accept();
         } catch (TTransportException tte) {
            TThreadedSelectorServer.LOGGER.warn("Exception trying to accept!", tte);
            return null;
         }
      }

      private void doAddAccept(SelectorThread thread, TNonblockingTransport client) {
         if (!thread.addAcceptedConnection(client)) {
            client.close();
         }

      }
   }

   protected class SelectorThread extends AbstractNonblockingServer.AbstractSelectThread {
      private final BlockingQueue acceptedQueue;
      private int SELECTOR_AUTO_REBUILD_THRESHOLD;
      private long MONITOR_PERIOD;
      private int jvmBug;

      public SelectorThread() throws IOException {
         this(new LinkedBlockingQueue());
      }

      public SelectorThread(int maxPendingAccepts) throws IOException {
         this(TThreadedSelectorServer.createDefaultAcceptQueue(maxPendingAccepts));
      }

      public SelectorThread(BlockingQueue acceptedQueue) throws IOException {
         this.SELECTOR_AUTO_REBUILD_THRESHOLD = 512;
         this.MONITOR_PERIOD = 1000L;
         this.jvmBug = 0;
         this.acceptedQueue = acceptedQueue;
      }

      public boolean addAcceptedConnection(TNonblockingTransport accepted) {
         try {
            this.acceptedQueue.put(accepted);
         } catch (InterruptedException e) {
            TThreadedSelectorServer.LOGGER.warn("Interrupted while adding accepted connection!", e);
            return false;
         }

         this.selector.wakeup();
         return true;
      }

      public void run() {
         try {
            while(!TThreadedSelectorServer.this.stopped_) {
               this.select();
               this.processAcceptedConnections();
               this.processInterestChanges();
            }

            for(SelectionKey selectionKey : this.selector.keys()) {
               this.cleanupSelectionKey(selectionKey);
            }

            return;
         } catch (Throwable t) {
            TThreadedSelectorServer.LOGGER.error("run() on SelectorThread exiting due to uncaught error", t);
         } finally {
            try {
               this.selector.close();
            } catch (IOException e) {
               TThreadedSelectorServer.LOGGER.error("Got an IOException while closing selector!", e);
            }

            TThreadedSelectorServer.this.stop();
         }

      }

      private void select() {
         try {
            this.doSelect();
            Iterator<SelectionKey> selectedKeys = this.selector.selectedKeys().iterator();

            while(!TThreadedSelectorServer.this.stopped_ && selectedKeys.hasNext()) {
               SelectionKey key = (SelectionKey)selectedKeys.next();
               selectedKeys.remove();
               if (!key.isValid()) {
                  this.cleanupSelectionKey(key);
               } else if (key.isReadable()) {
                  this.handleRead(key);
               } else if (key.isWritable()) {
                  this.handleWrite(key);
               } else {
                  TThreadedSelectorServer.LOGGER.warn("Unexpected state in select! " + key.interestOps());
               }
            }
         } catch (IOException e) {
            TThreadedSelectorServer.LOGGER.warn("Got an IOException while selecting!", e);
         }

      }

      private void doSelect() throws IOException {
         long beforeSelect = System.currentTimeMillis();
         int selectedNums = this.selector.select();
         long afterSelect = System.currentTimeMillis();
         if (selectedNums == 0) {
            ++this.jvmBug;
         } else {
            this.jvmBug = 0;
         }

         long selectedTime = afterSelect - beforeSelect;
         if (selectedTime >= this.MONITOR_PERIOD) {
            this.jvmBug = 0;
         } else if (this.jvmBug > this.SELECTOR_AUTO_REBUILD_THRESHOLD) {
            TThreadedSelectorServer.LOGGER.warn("In {} ms happen {} times jvm bug; rebuilding selector.", this.MONITOR_PERIOD, this.jvmBug);
            this.rebuildSelector();
            this.selector.selectNow();
            this.jvmBug = 0;
         }

      }

      private synchronized void rebuildSelector() {
         Selector oldSelector = this.selector;
         if (oldSelector != null) {
            Selector newSelector = null;

            try {
               newSelector = Selector.open();
               TThreadedSelectorServer.LOGGER.warn("Created new Selector.");
            } catch (IOException e) {
               TThreadedSelectorServer.LOGGER.error("Create new Selector error.", e);
            }

            for(SelectionKey key : oldSelector.selectedKeys()) {
               if (key.isValid() || key.readyOps() != 0) {
                  SelectableChannel channel = key.channel();
                  Object attachment = key.attachment();

                  try {
                     if (attachment == null) {
                        channel.register(newSelector, key.readyOps());
                     } else {
                        channel.register(newSelector, key.readyOps(), attachment);
                     }
                  } catch (ClosedChannelException e) {
                     TThreadedSelectorServer.LOGGER.error("Register new selector key error.", e);
                  }
               }
            }

            this.selector = newSelector;

            try {
               oldSelector.close();
            } catch (IOException e) {
               TThreadedSelectorServer.LOGGER.error("Close old selector error.", e);
            }

            TThreadedSelectorServer.LOGGER.warn("Replace new selector success.");
         }
      }

      private void processAcceptedConnections() {
         while(true) {
            if (!TThreadedSelectorServer.this.stopped_) {
               TNonblockingTransport accepted = (TNonblockingTransport)this.acceptedQueue.poll();
               if (accepted != null) {
                  this.registerAccepted(accepted);
                  continue;
               }
            }

            return;
         }
      }

      protected AbstractNonblockingServer.FrameBuffer createFrameBuffer(TNonblockingTransport trans, SelectionKey selectionKey, AbstractNonblockingServer.AbstractSelectThread selectThread) throws TTransportException {
         return (AbstractNonblockingServer.FrameBuffer)(TThreadedSelectorServer.this.processorFactory_.isAsyncProcessor() ? TThreadedSelectorServer.this.new AsyncFrameBuffer(trans, selectionKey, selectThread) : TThreadedSelectorServer.this.new FrameBuffer(trans, selectionKey, selectThread));
      }

      private void registerAccepted(TNonblockingTransport accepted) {
         SelectionKey clientKey = null;

         try {
            clientKey = accepted.registerSelector(this.selector, 1);
            AbstractNonblockingServer.FrameBuffer frameBuffer = this.createFrameBuffer(accepted, clientKey, this);
            clientKey.attach(frameBuffer);
         } catch (TTransportException | IOException e) {
            TThreadedSelectorServer.LOGGER.warn("Failed to register accepted connection to selector!", e);
            if (clientKey != null) {
               this.cleanupSelectionKey(clientKey);
            }

            accepted.close();
         }

      }
   }

   protected static class SelectorThreadLoadBalancer {
      private final Collection threads;
      private Iterator nextThreadIterator;

      public SelectorThreadLoadBalancer(Collection threads) {
         if (threads.isEmpty()) {
            throw new IllegalArgumentException("At least one selector thread is required");
         } else {
            this.threads = Collections.unmodifiableList(new ArrayList(threads));
            this.nextThreadIterator = this.threads.iterator();
         }
      }

      public SelectorThread nextThread() {
         if (!this.nextThreadIterator.hasNext()) {
            this.nextThreadIterator = this.threads.iterator();
         }

         return (SelectorThread)this.nextThreadIterator.next();
      }
   }
}
