package io.netty.channel.epoll;

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
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

public class EpollEventLoop extends SingleThreadEventLoop {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(EpollEventLoop.class);
   private static final long EPOLL_WAIT_MILLIS_THRESHOLD = SystemPropertyUtil.getLong("io.netty.channel.epoll.epollWaitThreshold", 10L);
   private FileDescriptor epollFd;
   private FileDescriptor eventFd;
   private FileDescriptor timerFd;
   private final IntObjectMap channels = new IntObjectHashMap(4096);
   private final boolean allowGrowing;
   private final EpollEventArray events;
   private IovArray iovArray;
   private NativeDatagramPacketArray datagramPacketArray;
   private final SelectStrategy selectStrategy;
   private final IntSupplier selectNowSupplier = new IntSupplier() {
      public int get() throws Exception {
         return EpollEventLoop.this.epollWaitNow();
      }
   };
   private static final long AWAKE = -1L;
   private static final long NONE = Long.MAX_VALUE;
   private final AtomicLong nextWakeupNanos = new AtomicLong(-1L);
   private boolean pendingWakeup;
   private volatile int ioRatio = 50;
   private static final long MAX_SCHEDULED_TIMERFD_NS = 999999999L;

   EpollEventLoop(EventLoopGroup parent, Executor executor, int maxEvents, SelectStrategy strategy, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory taskQueueFactory, EventLoopTaskQueueFactory tailTaskQueueFactory) {
      super(parent, executor, false, newTaskQueue(taskQueueFactory), newTaskQueue(tailTaskQueueFactory), rejectedExecutionHandler);
      this.selectStrategy = (SelectStrategy)ObjectUtil.checkNotNull(strategy, "strategy");
      if (maxEvents == 0) {
         this.allowGrowing = true;
         this.events = new EpollEventArray(4096);
      } else {
         this.allowGrowing = false;
         this.events = new EpollEventArray(maxEvents);
      }

      this.openFileDescriptors();
   }

   public void openFileDescriptors() {
      boolean success = false;
      FileDescriptor epollFd = null;
      FileDescriptor eventFd = null;
      FileDescriptor timerFd = null;

      try {
         this.epollFd = epollFd = Native.newEpollCreate();
         this.eventFd = eventFd = Native.newEventFd();

         try {
            Native.epollCtlAdd(epollFd.intValue(), eventFd.intValue(), Native.EPOLLIN | Native.EPOLLET);
         } catch (IOException e) {
            throw new IllegalStateException("Unable to add eventFd filedescriptor to epoll", e);
         }

         this.timerFd = timerFd = Native.newTimerFd();

         try {
            Native.epollCtlAdd(epollFd.intValue(), timerFd.intValue(), Native.EPOLLIN | Native.EPOLLET);
         } catch (IOException e) {
            throw new IllegalStateException("Unable to add timerFd filedescriptor to epoll", e);
         }

         success = true;
      } finally {
         if (!success) {
            closeFileDescriptor(epollFd);
            closeFileDescriptor(eventFd);
            closeFileDescriptor(timerFd);
         }

      }

   }

   private static void closeFileDescriptor(FileDescriptor fd) {
      if (fd != null) {
         try {
            fd.close();
         } catch (Exception var2) {
         }
      }

   }

   private static Queue newTaskQueue(EventLoopTaskQueueFactory queueFactory) {
      return queueFactory == null ? newTaskQueue0(DEFAULT_MAX_PENDING_TASKS) : queueFactory.newTaskQueue(DEFAULT_MAX_PENDING_TASKS);
   }

   IovArray cleanIovArray() {
      if (this.iovArray == null) {
         this.iovArray = new IovArray();
      } else {
         this.iovArray.clear();
      }

      return this.iovArray;
   }

   NativeDatagramPacketArray cleanDatagramPacketArray() {
      if (this.datagramPacketArray == null) {
         this.datagramPacketArray = new NativeDatagramPacketArray();
      } else {
         this.datagramPacketArray.clear();
      }

      return this.datagramPacketArray;
   }

   protected void wakeup(boolean inEventLoop) {
      if (!inEventLoop && this.nextWakeupNanos.getAndSet(-1L) != -1L) {
         Native.eventFdWrite(this.eventFd.intValue(), 1L);
      }

   }

   protected boolean beforeScheduledTaskSubmitted(long deadlineNanos) {
      return deadlineNanos < this.nextWakeupNanos.get();
   }

   protected boolean afterScheduledTaskSubmitted(long deadlineNanos) {
      return deadlineNanos < this.nextWakeupNanos.get();
   }

   void add(AbstractEpollChannel ch) throws IOException {
      assert this.inEventLoop();

      int fd = ch.socket.intValue();
      Native.epollCtlAdd(this.epollFd.intValue(), fd, ch.flags);
      AbstractEpollChannel old = (AbstractEpollChannel)this.channels.put(fd, ch);

      assert old == null || !old.isOpen();

   }

   void modify(AbstractEpollChannel ch) throws IOException {
      assert this.inEventLoop();

      Native.epollCtlMod(this.epollFd.intValue(), ch.socket.intValue(), ch.flags);
   }

   void remove(AbstractEpollChannel ch) throws IOException {
      assert this.inEventLoop();

      int fd = ch.socket.intValue();
      AbstractEpollChannel old = (AbstractEpollChannel)this.channels.remove(fd);
      if (old != null && old != ch) {
         this.channels.put(fd, old);

         assert !ch.isOpen();
      } else if (ch.isOpen()) {
         Native.epollCtlDel(this.epollFd.intValue(), fd);
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

      IntObjectMap<AbstractEpollChannel> ch = this.channels;
      return (Iterator)(ch.isEmpty() ? ChannelsReadOnlyIterator.empty() : new SingleThreadEventLoop.ChannelsReadOnlyIterator(ch.values()));
   }

   private long epollWait(long deadlineNanos) throws IOException {
      if (deadlineNanos == Long.MAX_VALUE) {
         return Native.epollWait(this.epollFd, this.events, this.timerFd, Integer.MAX_VALUE, 0, EPOLL_WAIT_MILLIS_THRESHOLD);
      } else {
         long totalDelay = deadlineToDelayNanos(deadlineNanos);
         int delaySeconds = (int)Math.min(totalDelay / 1000000000L, 2147483647L);
         int delayNanos = (int)Math.min(totalDelay - (long)delaySeconds * 1000000000L, 999999999L);
         return Native.epollWait(this.epollFd, this.events, this.timerFd, delaySeconds, delayNanos, EPOLL_WAIT_MILLIS_THRESHOLD);
      }
   }

   private int epollWaitNoTimerChange() throws IOException {
      return Native.epollWait(this.epollFd, this.events, false);
   }

   private int epollWaitNow() throws IOException {
      return Native.epollWait(this.epollFd, this.events, true);
   }

   private int epollBusyWait() throws IOException {
      return Native.epollBusyWait(this.epollFd, this.events);
   }

   private int epollWaitTimeboxed() throws IOException {
      return Native.epollWait(this.epollFd, this.events, 1000);
   }

   protected void run() {
      long prevDeadlineNanos = Long.MAX_VALUE;

      while(true) {
         try {
            int strategy = this.selectStrategy.calculateStrategy(this.selectNowSupplier, this.hasTasks());
            switch (strategy) {
               case -3:
                  strategy = this.epollBusyWait();
                  break;
               case -2:
                  continue;
               case -1:
                  label680: {
                     if (this.pendingWakeup) {
                        strategy = this.epollWaitTimeboxed();
                        if (strategy != 0) {
                           break label680;
                        }

                        logger.warn("Missed eventfd write (not seen after > 1 second)");
                        this.pendingWakeup = false;
                        if (this.hasTasks()) {
                           break label680;
                        }
                     }

                     long curDeadlineNanos = this.nextScheduledTaskDeadlineNanos();
                     if (curDeadlineNanos == -1L) {
                        curDeadlineNanos = Long.MAX_VALUE;
                     }

                     this.nextWakeupNanos.set(curDeadlineNanos);

                     try {
                        if (!this.hasTasks()) {
                           if (curDeadlineNanos == prevDeadlineNanos) {
                              strategy = this.epollWaitNoTimerChange();
                           } else {
                              long result = this.epollWait(curDeadlineNanos);
                              strategy = Native.epollReady(result);
                              prevDeadlineNanos = Native.epollTimerWasUsed(result) ? curDeadlineNanos : Long.MAX_VALUE;
                           }
                        }
                     } finally {
                        if (this.nextWakeupNanos.get() == -1L || this.nextWakeupNanos.getAndSet(-1L) == -1L) {
                           this.pendingWakeup = true;
                        }

                     }
                  }
            }

            int ioRatio = this.ioRatio;
            if (ioRatio == 100) {
               try {
                  if (strategy > 0 && this.processReady(this.events, strategy)) {
                     prevDeadlineNanos = Long.MAX_VALUE;
                  }
               } finally {
                  this.runAllTasks();
               }
            } else if (strategy > 0) {
               long ioStartTime = System.nanoTime();
               boolean var37 = false;

               try {
                  var37 = true;
                  if (this.processReady(this.events, strategy)) {
                     prevDeadlineNanos = Long.MAX_VALUE;
                     var37 = false;
                  } else {
                     var37 = false;
                  }
               } finally {
                  if (var37) {
                     long ioTime = System.nanoTime() - ioStartTime;
                     this.runAllTasks(ioTime * (long)(100 - ioRatio) / (long)ioRatio);
                  }
               }

               long ioTime = System.nanoTime() - ioStartTime;
               this.runAllTasks(ioTime * (long)(100 - ioRatio) / (long)ioRatio);
            } else {
               this.runAllTasks(0L);
            }

            if (this.allowGrowing && strategy == this.events.length()) {
               this.events.increase();
            }
         } catch (Error e) {
            throw e;
         } catch (Throwable t) {
            this.handleLoopException(t);
         } finally {
            label639: {
               try {
                  if (!this.isShuttingDown()) {
                     break label639;
                  }

                  this.closeAll();
                  if (!this.confirmShutdown()) {
                     break label639;
                  }
               } catch (Error e) {
                  throw e;
               } catch (Throwable t) {
                  this.handleLoopException(t);
                  break label639;
               }

               return;
            }

         }
      }
   }

   void handleLoopException(Throwable t) {
      logger.warn("Unexpected exception in the selector loop.", t);

      try {
         Thread.sleep(1000L);
      } catch (InterruptedException var3) {
      }

   }

   private void closeAll() {
      AbstractEpollChannel[] localChannels = (AbstractEpollChannel[])this.channels.values().toArray(new AbstractEpollChannel[0]);

      for(AbstractEpollChannel ch : localChannels) {
         ch.unsafe().close(ch.unsafe().voidPromise());
      }

   }

   private boolean processReady(EpollEventArray events, int ready) {
      boolean timerFired = false;

      for(int i = 0; i < ready; ++i) {
         int fd = events.fd(i);
         if (fd == this.eventFd.intValue()) {
            this.pendingWakeup = false;
         } else if (fd == this.timerFd.intValue()) {
            timerFired = true;
         } else {
            long ev = (long)events.events(i);
            AbstractEpollChannel ch = (AbstractEpollChannel)this.channels.get(fd);
            if (ch != null) {
               AbstractEpollChannel.AbstractEpollUnsafe unsafe = (AbstractEpollChannel.AbstractEpollUnsafe)ch.unsafe();
               if ((ev & (long)(Native.EPOLLERR | Native.EPOLLOUT)) != 0L) {
                  unsafe.epollOutReady();
               }

               if ((ev & (long)(Native.EPOLLERR | Native.EPOLLIN)) != 0L) {
                  unsafe.epollInReady();
               }

               if ((ev & (long)Native.EPOLLRDHUP) != 0L) {
                  unsafe.epollRdHupReady();
               }
            } else {
               try {
                  Native.epollCtlDel(this.epollFd.intValue(), fd);
               } catch (IOException var10) {
               }
            }
         }
      }

      return timerFired;
   }

   protected void cleanup() {
      try {
         this.closeFileDescriptors();
      } finally {
         if (this.iovArray != null) {
            this.iovArray.release();
            this.iovArray = null;
         }

         if (this.datagramPacketArray != null) {
            this.datagramPacketArray.release();
            this.datagramPacketArray = null;
         }

         this.events.free();
      }

   }

   public void closeFileDescriptors() {
      label46:
      while(true) {
         if (this.pendingWakeup) {
            try {
               int count = this.epollWaitTimeboxed();
               if (count != 0) {
                  int i = 0;

                  while(true) {
                     if (i >= count) {
                        continue label46;
                     }

                     if (this.events.fd(i) == this.eventFd.intValue()) {
                        this.pendingWakeup = false;
                        continue label46;
                     }

                     ++i;
                  }
               }
            } catch (IOException var6) {
               continue;
            }
         }

         try {
            this.eventFd.close();
         } catch (IOException e) {
            logger.warn("Failed to close the event fd.", e);
         }

         try {
            this.timerFd.close();
         } catch (IOException e) {
            logger.warn("Failed to close the timer fd.", e);
         }

         try {
            this.epollFd.close();
         } catch (IOException e) {
            logger.warn("Failed to close the epoll fd.", e);
         }

         return;
      }
   }

   static {
      Epoll.ensureAvailability();
   }
}
