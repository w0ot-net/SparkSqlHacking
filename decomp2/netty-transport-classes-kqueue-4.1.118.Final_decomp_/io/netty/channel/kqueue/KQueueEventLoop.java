package io.netty.channel.kqueue;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.EventLoopTaskQueueFactory;
import io.netty.channel.SelectStrategy;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.channel.SingleThreadEventLoop.ChannelsReadOnlyIterator;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.IovArray;
import io.netty.util.IntSupplier;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

final class KQueueEventLoop extends SingleThreadEventLoop {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(KQueueEventLoop.class);
   private static final AtomicIntegerFieldUpdater WAKEN_UP_UPDATER = AtomicIntegerFieldUpdater.newUpdater(KQueueEventLoop.class, "wakenUp");
   private static final int KQUEUE_WAKE_UP_IDENT = 0;
   private static final int KQUEUE_MAX_TIMEOUT_SECONDS = 86399;
   private final boolean allowGrowing;
   private final FileDescriptor kqueueFd;
   private final KQueueEventArray changeList;
   private final KQueueEventArray eventList;
   private final SelectStrategy selectStrategy;
   private final IovArray iovArray = new IovArray();
   private final IntSupplier selectNowSupplier = new IntSupplier() {
      public int get() throws Exception {
         return KQueueEventLoop.this.kqueueWaitNow();
      }
   };
   private final IntObjectMap channels = new IntObjectHashMap(4096);
   private volatile int wakenUp;
   private volatile int ioRatio = 50;

   KQueueEventLoop(EventLoopGroup parent, Executor executor, int maxEvents, SelectStrategy strategy, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory taskQueueFactory, EventLoopTaskQueueFactory tailTaskQueueFactory) {
      super(parent, executor, false, newTaskQueue(taskQueueFactory), newTaskQueue(tailTaskQueueFactory), rejectedExecutionHandler);
      this.selectStrategy = (SelectStrategy)ObjectUtil.checkNotNull(strategy, "strategy");
      this.kqueueFd = Native.newKQueue();
      if (maxEvents == 0) {
         this.allowGrowing = true;
         maxEvents = 4096;
      } else {
         this.allowGrowing = false;
      }

      this.changeList = new KQueueEventArray(maxEvents);
      this.eventList = new KQueueEventArray(maxEvents);
      int result = Native.keventAddUserEvent(this.kqueueFd.intValue(), 0);
      if (result < 0) {
         this.cleanup();
         throw new IllegalStateException("kevent failed to add user event with errno: " + -result);
      }
   }

   private static Queue newTaskQueue(EventLoopTaskQueueFactory queueFactory) {
      return queueFactory == null ? newTaskQueue0(DEFAULT_MAX_PENDING_TASKS) : queueFactory.newTaskQueue(DEFAULT_MAX_PENDING_TASKS);
   }

   void add(AbstractKQueueChannel ch) {
      assert this.inEventLoop();

      AbstractKQueueChannel old = (AbstractKQueueChannel)this.channels.put(ch.fd().intValue(), ch);

      assert old == null || !old.isOpen();

   }

   void evSet(AbstractKQueueChannel ch, short filter, short flags, int fflags) {
      assert this.inEventLoop();

      this.changeList.evSet(ch, filter, flags, fflags);
   }

   void remove(AbstractKQueueChannel ch) throws Exception {
      assert this.inEventLoop();

      int fd = ch.fd().intValue();
      AbstractKQueueChannel old = (AbstractKQueueChannel)this.channels.remove(fd);
      if (old != null && old != ch) {
         this.channels.put(fd, old);

         assert !ch.isOpen();
      } else if (ch.isOpen()) {
         ch.unregisterFilters();
      }

   }

   IovArray cleanArray() {
      this.iovArray.clear();
      return this.iovArray;
   }

   protected void wakeup(boolean inEventLoop) {
      if (!inEventLoop && WAKEN_UP_UPDATER.compareAndSet(this, 0, 1)) {
         this.wakeup();
      }

   }

   private void wakeup() {
      Native.keventTriggerUserEvent(this.kqueueFd.intValue(), 0);
   }

   private int kqueueWait(boolean oldWakeup) throws IOException {
      if (oldWakeup && this.hasTasks()) {
         return this.kqueueWaitNow();
      } else {
         long totalDelay = this.delayNanos(System.nanoTime());
         int delaySeconds = (int)Math.min(totalDelay / 1000000000L, 86399L);
         int delayNanos = (int)(totalDelay % 1000000000L);
         return this.kqueueWait(delaySeconds, delayNanos);
      }
   }

   private int kqueueWaitNow() throws IOException {
      return this.kqueueWait(0, 0);
   }

   private int kqueueWait(int timeoutSec, int timeoutNs) throws IOException {
      int numEvents = Native.keventWait(this.kqueueFd.intValue(), this.changeList, this.eventList, timeoutSec, timeoutNs);
      this.changeList.clear();
      return numEvents;
   }

   private void processReady(int ready) {
      for(int i = 0; i < ready; ++i) {
         short filter = this.eventList.filter(i);
         short flags = this.eventList.flags(i);
         int fd = this.eventList.fd(i);
         if (filter != Native.EVFILT_USER && (flags & Native.EV_ERROR) == 0) {
            AbstractKQueueChannel channel = (AbstractKQueueChannel)this.channels.get(fd);
            if (channel == null) {
               logger.warn("events[{}]=[{}, {}] had no channel!", new Object[]{i, this.eventList.fd(i), filter});
            } else {
               AbstractKQueueChannel.AbstractKQueueUnsafe unsafe = (AbstractKQueueChannel.AbstractKQueueUnsafe)channel.unsafe();
               if (filter == Native.EVFILT_WRITE) {
                  unsafe.writeReady();
               } else if (filter == Native.EVFILT_READ) {
                  unsafe.readReady(this.eventList.data(i));
               } else if (filter == Native.EVFILT_SOCK && (this.eventList.fflags(i) & Native.NOTE_RDHUP) != 0) {
                  unsafe.readEOF();
               }

               if ((flags & Native.EV_EOF) != 0) {
                  unsafe.readEOF();
               }
            }
         } else {
            assert filter != Native.EVFILT_USER || filter == Native.EVFILT_USER && fd == 0;
         }
      }

   }

   protected void run() {
      while(true) {
         try {
            int strategy = this.selectStrategy.calculateStrategy(this.selectNowSupplier, this.hasTasks());
            switch (strategy) {
               case -3:
               case -1:
                  strategy = this.kqueueWait(WAKEN_UP_UPDATER.getAndSet(this, 0) == 1);
                  if (this.wakenUp == 1) {
                     this.wakeup();
                  }
               default:
                  int ioRatio = this.ioRatio;
                  if (ioRatio == 100) {
                     try {
                        if (strategy > 0) {
                           this.processReady(strategy);
                        }
                     } finally {
                        this.runAllTasks();
                     }
                  } else {
                     long ioStartTime = System.nanoTime();
                     boolean var32 = false;

                     try {
                        var32 = true;
                        if (strategy > 0) {
                           this.processReady(strategy);
                           var32 = false;
                        } else {
                           var32 = false;
                        }
                     } finally {
                        if (var32) {
                           long ioTime = System.nanoTime() - ioStartTime;
                           this.runAllTasks(ioTime * (long)(100 - ioRatio) / (long)ioRatio);
                        }
                     }

                     long ioTime = System.nanoTime() - ioStartTime;
                     this.runAllTasks(ioTime * (long)(100 - ioRatio) / (long)ioRatio);
                  }

                  if (this.allowGrowing && strategy == this.eventList.capacity()) {
                     this.eventList.realloc(false);
                  }
               case -2:
            }
         } catch (Error e) {
            throw e;
         } catch (Throwable t) {
            handleLoopException(t);
         } finally {
            label355: {
               try {
                  if (!this.isShuttingDown()) {
                     break label355;
                  }

                  this.closeAll();
                  if (!this.confirmShutdown()) {
                     break label355;
                  }
               } catch (Error e) {
                  throw e;
               } catch (Throwable t) {
                  handleLoopException(t);
                  break label355;
               }

               return;
            }

         }
      }
   }

   protected Queue newTaskQueue(int maxPendingTasks) {
      return newTaskQueue0(maxPendingTasks);
   }

   private static Queue newTaskQueue0(int maxPendingTasks) {
      return maxPendingTasks == Integer.MAX_VALUE ? PlatformDependent.newMpscQueue() : PlatformDependent.newMpscQueue(maxPendingTasks);
   }

   public int getIoRatio() {
      return this.ioRatio;
   }

   public void setIoRatio(int ioRatio) {
      if (ioRatio > 0 && ioRatio <= 100) {
         this.ioRatio = ioRatio;
      } else {
         throw new IllegalArgumentException("ioRatio: " + ioRatio + " (expected: 0 < ioRatio <= 100)");
      }
   }

   public int registeredChannels() {
      return this.channels.size();
   }

   public Iterator registeredChannelsIterator() {
      assert this.inEventLoop();

      IntObjectMap<AbstractKQueueChannel> ch = this.channels;
      return (Iterator)(ch.isEmpty() ? ChannelsReadOnlyIterator.empty() : new SingleThreadEventLoop.ChannelsReadOnlyIterator(ch.values()));
   }

   protected void cleanup() {
      try {
         this.kqueueFd.close();
      } catch (IOException e) {
         logger.warn("Failed to close the kqueue fd.", e);
      } finally {
         this.iovArray.release();
         this.changeList.free();
         this.eventList.free();
      }

   }

   private void closeAll() {
      try {
         this.kqueueWaitNow();
      } catch (IOException var6) {
      }

      AbstractKQueueChannel[] localChannels = (AbstractKQueueChannel[])this.channels.values().toArray(new AbstractKQueueChannel[0]);

      for(AbstractKQueueChannel ch : localChannels) {
         ch.unsafe().close(ch.unsafe().voidPromise());
      }

   }

   private static void handleLoopException(Throwable t) {
      logger.warn("Unexpected exception in the selector loop.", t);

      try {
         Thread.sleep(1000L);
      } catch (InterruptedException var2) {
      }

   }

   static {
      KQueue.ensureAvailability();
   }
}
