package org.glassfish.jersey.internal.jsr166;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import sun.misc.Contended;
import sun.misc.Unsafe;

public class SubmissionPublisher implements Flow.Publisher, SubmittableFlowPublisher, AutoCloseable {
   static final int BUFFER_CAPACITY_LIMIT = 1073741824;
   private static final Executor ASYNC_POOL = (Executor)(ForkJoinPool.getCommonPoolParallelism() > 1 ? ForkJoinPool.commonPool() : new ThreadPerTaskExecutor());
   BufferedSubscription clients;
   volatile boolean closed;
   volatile Throwable closedException;
   final Executor executor;
   final BiConsumer onNextHandler;
   final int maxBufferCapacity;

   static final int roundCapacity(int cap) {
      int n = cap - 1;
      n |= n >>> 1;
      n |= n >>> 2;
      n |= n >>> 4;
      n |= n >>> 8;
      n |= n >>> 16;
      return n <= 0 ? 1 : (n >= 1073741824 ? 1073741824 : n + 1);
   }

   public SubmissionPublisher(Executor executor, int maxBufferCapacity, BiConsumer handler) {
      if (executor == null) {
         throw new NullPointerException();
      } else if (maxBufferCapacity <= 0) {
         throw new IllegalArgumentException("capacity must be positive");
      } else {
         this.executor = executor;
         this.onNextHandler = handler;
         this.maxBufferCapacity = roundCapacity(maxBufferCapacity);
      }
   }

   public SubmissionPublisher(Executor executor, int maxBufferCapacity) {
      this(executor, maxBufferCapacity, (BiConsumer)null);
   }

   public SubmissionPublisher() {
      this(ASYNC_POOL, Flow.defaultBufferSize(), (BiConsumer)null);
   }

   public void subscribe(Flow.Subscriber subscriber) {
      if (subscriber == null) {
         throw new NullPointerException();
      } else {
         BufferedSubscription<T> subscription = new BufferedSubscription(subscriber, this.executor, this.onNextHandler, this.maxBufferCapacity);
         synchronized(this) {
            BufferedSubscription<T> b = this.clients;
            BufferedSubscription<T> pred = null;

            while(true) {
               if (b == null) {
                  subscription.onSubscribe();
                  Throwable ex;
                  if ((ex = this.closedException) != null) {
                     subscription.onError(ex);
                  } else if (this.closed) {
                     subscription.onComplete();
                  } else if (pred == null) {
                     this.clients = subscription;
                  } else {
                     pred.next = subscription;
                  }
                  break;
               }

               BufferedSubscription<T> next = b.next;
               if (b.isDisabled()) {
                  b.next = null;
                  if (pred == null) {
                     this.clients = next;
                  } else {
                     pred.next = next;
                  }
               } else {
                  if (subscriber.equals(b.subscriber)) {
                     b.onError(new IllegalStateException("Duplicate subscribe"));
                     break;
                  }

                  pred = b;
               }

               b = next;
            }

         }
      }
   }

   public int submit(Object item) {
      if (item == null) {
         throw new NullPointerException();
      } else {
         int lag = 0;
         boolean complete;
         synchronized(this) {
            complete = this.closed;
            BufferedSubscription<T> b = this.clients;
            if (!complete) {
               BufferedSubscription<T> pred = null;
               BufferedSubscription<T> r = null;

               BufferedSubscription<T> next;
               for(BufferedSubscription<T> rtail = null; b != null; b = next) {
                  next = b.next;
                  int stat = b.offer(item);
                  if (stat < 0) {
                     b.next = null;
                     if (pred == null) {
                        this.clients = next;
                     } else {
                        pred.next = next;
                     }
                  } else {
                     if (stat > lag) {
                        lag = stat;
                     } else if (stat == 0) {
                        b.nextRetry = null;
                        if (rtail == null) {
                           r = b;
                        } else {
                           rtail.nextRetry = b;
                        }

                        rtail = b;
                     }

                     pred = b;
                  }
               }

               for(; r != null; r = next) {
                  next = r.nextRetry;
                  r.nextRetry = null;
                  int stat = r.submit(item);
                  if (stat > lag) {
                     lag = stat;
                  } else if (stat < 0 && this.clients == r) {
                     this.clients = r.next;
                  }
               }
            }
         }

         if (complete) {
            throw new IllegalStateException("Closed");
         } else {
            return lag;
         }
      }
   }

   public int offer(Object item, BiPredicate onDrop) {
      return this.doOffer(0L, item, onDrop);
   }

   public int offer(Object item, long timeout, TimeUnit unit, BiPredicate onDrop) {
      return this.doOffer(unit.toNanos(timeout), item, onDrop);
   }

   final int doOffer(long nanos, Object item, BiPredicate onDrop) {
      if (item == null) {
         throw new NullPointerException();
      } else {
         int lag = 0;
         int drops = 0;
         boolean complete;
         synchronized(this) {
            complete = this.closed;
            BufferedSubscription<T> b = this.clients;
            if (!complete) {
               BufferedSubscription<T> pred = null;
               BufferedSubscription<T> r = null;

               BufferedSubscription<T> next;
               for(BufferedSubscription<T> rtail = null; b != null; b = next) {
                  next = b.next;
                  int stat = b.offer(item);
                  if (stat < 0) {
                     b.next = null;
                     if (pred == null) {
                        this.clients = next;
                     } else {
                        pred.next = next;
                     }
                  } else {
                     if (stat > lag) {
                        lag = stat;
                     } else if (stat == 0) {
                        b.nextRetry = null;
                        if (rtail == null) {
                           r = b;
                        } else {
                           rtail.nextRetry = b;
                        }

                        rtail = b;
                     } else if (stat > lag) {
                        lag = stat;
                     }

                     pred = b;
                  }
               }

               for(; r != null; r = next) {
                  next = r.nextRetry;
                  r.nextRetry = null;
                  int stat = nanos > 0L ? r.timedOffer(item, nanos) : r.offer(item);
                  if (stat == 0 && onDrop != null && onDrop.test(r.subscriber, item)) {
                     stat = r.offer(item);
                  }

                  if (stat == 0) {
                     ++drops;
                  } else if (stat > lag) {
                     lag = stat;
                  } else if (stat < 0 && this.clients == r) {
                     this.clients = r.next;
                  }
               }
            }
         }

         if (complete) {
            throw new IllegalStateException("Closed");
         } else {
            return drops > 0 ? -drops : lag;
         }
      }
   }

   public void close() {
      if (!this.closed) {
         BufferedSubscription<T> b;
         synchronized(this) {
            b = this.clients;
            this.clients = null;
            this.closed = true;
         }

         while(b != null) {
            BufferedSubscription<T> next = b.next;
            b.next = null;
            b.onComplete();
            b = next;
         }
      }

   }

   public void closeExceptionally(Throwable error) {
      if (error == null) {
         throw new NullPointerException();
      } else {
         if (!this.closed) {
            BufferedSubscription<T> b;
            synchronized(this) {
               b = this.clients;
               this.clients = null;
               this.closed = true;
               this.closedException = error;
            }

            while(b != null) {
               BufferedSubscription<T> next = b.next;
               b.next = null;
               b.onError(error);
               b = next;
            }
         }

      }
   }

   public boolean isClosed() {
      return this.closed;
   }

   public Throwable getClosedException() {
      return this.closedException;
   }

   public boolean hasSubscribers() {
      boolean nonEmpty = false;
      if (!this.closed) {
         BufferedSubscription<T> next;
         synchronized(this) {
            for(BufferedSubscription<T> b = this.clients; b != null; b = this.clients = next) {
               next = b.next;
               if (!b.isDisabled()) {
                  nonEmpty = true;
                  break;
               }

               b.next = null;
            }
         }
      }

      return nonEmpty;
   }

   public int getNumberOfSubscribers() {
      int count = 0;
      if (!this.closed) {
         synchronized(this) {
            BufferedSubscription<T> pred = null;

            BufferedSubscription<T> next;
            for(BufferedSubscription<T> b = this.clients; b != null; b = next) {
               next = b.next;
               if (b.isDisabled()) {
                  b.next = null;
                  if (pred == null) {
                     this.clients = next;
                  } else {
                     pred.next = next;
                  }
               } else {
                  pred = b;
                  ++count;
               }
            }
         }
      }

      return count;
   }

   public Executor getExecutor() {
      return this.executor;
   }

   public int getMaxBufferCapacity() {
      return this.maxBufferCapacity;
   }

   public List getSubscribers() {
      ArrayList<Flow.Subscriber<? super T>> subs = new ArrayList();
      synchronized(this) {
         BufferedSubscription<T> pred = null;

         BufferedSubscription<T> next;
         for(BufferedSubscription<T> b = this.clients; b != null; b = next) {
            next = b.next;
            if (b.isDisabled()) {
               b.next = null;
               if (pred == null) {
                  this.clients = next;
               } else {
                  pred.next = next;
               }
            } else {
               subs.add(b.subscriber);
            }
         }

         return subs;
      }
   }

   public boolean isSubscribed(Flow.Subscriber subscriber) {
      if (subscriber == null) {
         throw new NullPointerException();
      } else {
         if (!this.closed) {
            synchronized(this) {
               BufferedSubscription<T> pred = null;

               BufferedSubscription<T> next;
               for(BufferedSubscription<T> b = this.clients; b != null; b = next) {
                  next = b.next;
                  if (b.isDisabled()) {
                     b.next = null;
                     if (pred == null) {
                        this.clients = next;
                     } else {
                        pred.next = next;
                     }
                  } else {
                     if (subscriber.equals(b.subscriber)) {
                        return true;
                     }

                     pred = b;
                  }
               }
            }
         }

         return false;
      }
   }

   public long estimateMinimumDemand() {
      long min = Long.MAX_VALUE;
      boolean nonEmpty = false;
      synchronized(this) {
         BufferedSubscription<T> pred = null;

         BufferedSubscription<T> next;
         for(BufferedSubscription<T> b = this.clients; b != null; b = next) {
            next = b.next;
            int n;
            if ((n = b.estimateLag()) < 0) {
               b.next = null;
               if (pred == null) {
                  this.clients = next;
               } else {
                  pred.next = next;
               }
            } else {
               long d;
               if ((d = b.demand - (long)n) < min) {
                  min = d;
               }

               nonEmpty = true;
               pred = b;
            }
         }
      }

      return nonEmpty ? min : 0L;
   }

   public int estimateMaximumLag() {
      int max = 0;
      synchronized(this) {
         BufferedSubscription<T> pred = null;

         BufferedSubscription<T> next;
         for(BufferedSubscription<T> b = this.clients; b != null; b = next) {
            next = b.next;
            int n;
            if ((n = b.estimateLag()) < 0) {
               b.next = null;
               if (pred == null) {
                  this.clients = next;
               } else {
                  pred.next = next;
               }
            } else {
               if (n > max) {
                  max = n;
               }

               pred = b;
            }
         }

         return max;
      }
   }

   public CompletableFuture consume(Consumer consumer) {
      if (consumer == null) {
         throw new NullPointerException();
      } else {
         CompletableFuture<Void> status = new CompletableFuture();
         this.subscribe(new ConsumerSubscriber(status, consumer));
         return status;
      }
   }

   private static final class ThreadPerTaskExecutor implements Executor {
      private ThreadPerTaskExecutor() {
      }

      public void execute(Runnable r) {
         (new Thread(r)).start();
      }
   }

   private static final class ConsumerSubscriber implements Flow.Subscriber {
      final CompletableFuture status;
      final Consumer consumer;
      Flow.Subscription subscription;

      ConsumerSubscriber(CompletableFuture status, Consumer consumer) {
         this.status = status;
         this.consumer = consumer;
      }

      public final void onSubscribe(Flow.Subscription subscription) {
         this.subscription = subscription;
         this.status.whenComplete((v, e) -> subscription.cancel());
         if (!this.status.isDone()) {
            subscription.request(Long.MAX_VALUE);
         }

      }

      public final void onError(Throwable ex) {
         this.status.completeExceptionally(ex);
      }

      public final void onComplete() {
         this.status.complete((Object)null);
      }

      public final void onNext(Object item) {
         try {
            this.consumer.accept(item);
         } catch (Throwable ex) {
            this.subscription.cancel();
            this.status.completeExceptionally(ex);
         }

      }
   }

   static final class ConsumerTask extends ForkJoinTask implements Runnable, CompletableFuture.AsynchronousCompletionTask {
      final BufferedSubscription consumer;

      ConsumerTask(BufferedSubscription consumer) {
         this.consumer = consumer;
      }

      public final Void getRawResult() {
         return null;
      }

      public final void setRawResult(Void v) {
      }

      public final boolean exec() {
         this.consumer.consume();
         return false;
      }

      public final void run() {
         this.consumer.consume();
      }
   }

   @Contended
   private static final class BufferedSubscription implements Flow.Subscription, ForkJoinPool.ManagedBlocker {
      long timeout;
      volatile long demand;
      int maxCapacity;
      int putStat;
      volatile int ctl;
      volatile int head;
      int tail;
      Object[] array;
      Flow.Subscriber subscriber;
      Executor executor;
      BiConsumer onNextHandler;
      volatile Throwable pendingError;
      volatile Thread waiter;
      Object putItem;
      BufferedSubscription next;
      BufferedSubscription nextRetry;
      static final int ACTIVE = 1;
      static final int CONSUME = 2;
      static final int DISABLED = 4;
      static final int ERROR = 8;
      static final int SUBSCRIBE = 16;
      static final int COMPLETE = 32;
      static final long INTERRUPTED = -1L;
      static final int DEFAULT_INITIAL_CAP = 32;
      private static final Unsafe U = UnsafeAccessor.getUnsafe();
      private static final long CTL;
      private static final long TAIL;
      private static final long HEAD;
      private static final long DEMAND;
      private static final int ABASE;
      private static final int ASHIFT;

      BufferedSubscription(Flow.Subscriber subscriber, Executor executor, BiConsumer onNextHandler, int maxBufferCapacity) {
         this.subscriber = subscriber;
         this.executor = executor;
         this.onNextHandler = onNextHandler;
         this.maxCapacity = maxBufferCapacity;
         this.array = new Object[maxBufferCapacity < 32 ? (maxBufferCapacity < 2 ? 2 : maxBufferCapacity) : 32];
      }

      public String toString() {
         return this.subscriber != null ? this.subscriber.toString() : super.toString();
      }

      final boolean isDisabled() {
         return this.ctl == 4;
      }

      final int estimateLag() {
         int n;
         return this.ctl == 4 ? -1 : ((n = this.tail - this.head) > 0 ? n : 0);
      }

      final int offer(Object item) {
         int h = this.head;
         int t = this.tail;
         Object[] a = this.array;
         int cap;
         int size;
         int stat;
         if (a != null && (cap = a.length) > 0 && cap >= (size = t + 1 - h)) {
            a[cap - 1 & t] = item;
            this.tail = t + 1;
            stat = size;
         } else {
            stat = this.growAndAdd(a, item);
         }

         return stat > 0 && (this.ctl & 3) != 3 ? this.startOnOffer(stat) : stat;
      }

      private int growAndAdd(Object[] a, Object item) {
         boolean alloc;
         int cap;
         int stat;
         if ((this.ctl & 12) != 0) {
            cap = 0;
            stat = -1;
            alloc = false;
         } else if (a != null && (cap = a.length) > 0) {
            U.fullFence();
            int h = this.head;
            int t = this.tail;
            int size = t + 1 - h;
            if (cap >= size) {
               a[cap - 1 & t] = item;
               this.tail = t + 1;
               stat = size;
               alloc = false;
            } else if (cap >= this.maxCapacity) {
               stat = 0;
               alloc = false;
            } else {
               stat = cap + 1;
               alloc = true;
            }
         } else {
            cap = 0;
            stat = 1;
            alloc = true;
         }

         if (alloc) {
            int newCap = cap > 0 ? cap << 1 : 1;
            if (newCap <= cap) {
               stat = 0;
            } else {
               Object[] newArray = null;

               try {
                  newArray = new Object[newCap];
               } catch (Throwable var15) {
               }

               if (newArray == null) {
                  if (cap > 0) {
                     this.maxCapacity = cap;
                  }

                  stat = 0;
               } else {
                  this.array = newArray;
                  int t = this.tail;
                  int newMask = newCap - 1;
                  if (a != null && cap > 0) {
                     int mask = cap - 1;

                     for(int j = this.head; j != t; ++j) {
                        long k = ((long)(j & mask) << ASHIFT) + (long)ABASE;
                        Object x = U.getObjectVolatile(a, k);
                        if (x != null && U.compareAndSwapObject(a, k, x, (Object)null)) {
                           newArray[j & newMask] = x;
                        }
                     }
                  }

                  newArray[t & newMask] = item;
                  this.tail = t + 1;
               }
            }
         }

         return stat;
      }

      final int submit(Object item) {
         int stat;
         if ((stat = this.offer(item)) == 0) {
            this.putItem = item;
            this.timeout = 0L;
            this.putStat = 0;
            if ((stat = this.putStat) == 0) {
               try {
                  ForkJoinPool.managedBlock(this);
               } catch (InterruptedException var4) {
                  this.timeout = -1L;
               }

               stat = this.putStat;
            }

            if (this.timeout < 0L) {
               Thread.currentThread().interrupt();
            }
         }

         return stat;
      }

      final int timedOffer(Object item, long nanos) {
         int stat;
         if ((stat = this.offer(item)) == 0 && (this.timeout = nanos) > 0L) {
            this.putItem = item;
            this.putStat = 0;
            if ((stat = this.putStat) == 0) {
               try {
                  ForkJoinPool.managedBlock(this);
               } catch (InterruptedException var6) {
                  this.timeout = -1L;
               }

               stat = this.putStat;
            }

            if (this.timeout < 0L) {
               Thread.currentThread().interrupt();
            }
         }

         return stat;
      }

      private int startOnOffer(int stat) {
         while(true) {
            Executor e;
            int c;
            if ((c = this.ctl) != 4 && (e = this.executor) != null) {
               if ((c & 1) != 0) {
                  if ((c & 2) == 0 && !U.compareAndSwapInt(this, CTL, c, c | 2)) {
                     continue;
                  }
               } else if (this.demand != 0L && this.tail != this.head) {
                  if (!U.compareAndSwapInt(this, CTL, c, c | 3)) {
                     continue;
                  }

                  try {
                     e.execute(new ConsumerTask(this));
                  } catch (Error | RuntimeException ex) {
                     while(((c = this.ctl) & 4) == 0 && (c & 1) != 0 && !U.compareAndSwapInt(this, CTL, c, c & -2)) {
                     }

                     throw ex;
                  }
               }
               break;
            }

            stat = -1;
            break;
         }

         return stat;
      }

      private void signalWaiter(Thread w) {
         this.waiter = null;
         LockSupport.unpark(w);
      }

      private void detach() {
         Thread w = this.waiter;
         this.executor = null;
         this.subscriber = null;
         this.pendingError = null;
         this.signalWaiter(w);
      }

      final void onError(Throwable ex) {
         while(true) {
            int c;
            if (((c = this.ctl) & 12) == 0) {
               if ((c & 1) != 0) {
                  this.pendingError = ex;
                  if (!U.compareAndSwapInt(this, CTL, c, c | 8)) {
                     continue;
                  }
               } else {
                  if (!U.compareAndSwapInt(this, CTL, c, 4)) {
                     continue;
                  }

                  Flow.Subscriber<? super T> s = this.subscriber;
                  if (s != null && ex != null) {
                     try {
                        s.onError(ex);
                     } catch (Throwable var5) {
                     }
                  }

                  this.detach();
               }
            }

            return;
         }
      }

      private void startOrDisable() {
         Executor e;
         if ((e = this.executor) != null) {
            int c;
            try {
               e.execute(new ConsumerTask(this));
            } catch (Throwable ex) {
               while((c = this.ctl) != 4 && (c & 1) != 0) {
                  if (U.compareAndSwapInt(this, CTL, c, c & -2)) {
                     this.onError(ex);
                     break;
                  }
               }
            }
         }

      }

      final void onComplete() {
         while(true) {
            int c;
            if ((c = this.ctl) != 4) {
               if (!U.compareAndSwapInt(this, CTL, c, c | 35)) {
                  continue;
               }

               if ((c & 1) == 0) {
                  this.startOrDisable();
               }
            }

            return;
         }
      }

      final void onSubscribe() {
         while(true) {
            int c;
            if ((c = this.ctl) != 4) {
               if (!U.compareAndSwapInt(this, CTL, c, c | 19)) {
                  continue;
               }

               if ((c & 1) == 0) {
                  this.startOrDisable();
               }
            }

            return;
         }
      }

      public void cancel() {
         while(true) {
            int c;
            if ((c = this.ctl) != 4) {
               if ((c & 1) != 0) {
                  if (!U.compareAndSwapInt(this, CTL, c, c | 10)) {
                     continue;
                  }
               } else {
                  if (!U.compareAndSwapInt(this, CTL, c, 4)) {
                     continue;
                  }

                  this.detach();
               }
            }

            return;
         }
      }

      public void request(long n) {
         if (n > 0L) {
            while(true) {
               long prev = this.demand;
               long d;
               if ((d = prev + n) < prev) {
                  d = Long.MAX_VALUE;
               }

               int c;
               if (U.compareAndSwapLong(this, DEMAND, prev, d)) {
                  while((c = this.ctl) != 4) {
                     if ((c & 1) != 0) {
                        if ((c & 2) != 0 || U.compareAndSwapInt(this, CTL, c, c | 2)) {
                           return;
                        }
                     } else {
                        int h;
                        if ((h = this.head) != this.tail) {
                           if (U.compareAndSwapInt(this, CTL, c, c | 3)) {
                              this.startOrDisable();
                              return;
                           }
                        } else if (this.head == h && this.tail == h) {
                           return;
                        }
                     }

                     if (this.demand == 0L) {
                        return;
                     }
                  }
                  break;
               }
            }
         } else if (n < 0L) {
            this.onError(new IllegalArgumentException("negative subscription request"));
         }

      }

      public final boolean isReleasable() {
         T item = (T)this.putItem;
         if (item != null) {
            if ((this.putStat = this.offer(item)) == 0) {
               return false;
            }

            this.putItem = null;
         }

         return true;
      }

      public final boolean block() {
         T item = (T)this.putItem;
         if (item != null) {
            this.putItem = null;
            long nanos = this.timeout;
            long deadline = nanos > 0L ? System.nanoTime() + nanos : 0L;

            while((this.putStat = this.offer(item)) == 0) {
               if (Thread.interrupted()) {
                  this.timeout = -1L;
                  if (nanos > 0L) {
                     break;
                  }
               } else {
                  if (nanos > 0L && (nanos = deadline - System.nanoTime()) <= 0L) {
                     break;
                  }

                  if (this.waiter == null) {
                     this.waiter = Thread.currentThread();
                  } else {
                     if (nanos > 0L) {
                        LockSupport.parkNanos(this, nanos);
                     } else {
                        LockSupport.park(this);
                     }

                     this.waiter = null;
                  }
               }
            }
         }

         this.waiter = null;
         return true;
      }

      final void consume() {
         int h = this.head;
         Flow.Subscriber<? super T> s;
         if ((s = this.subscriber) != null) {
            while(true) {
               long d = this.demand;
               int c;
               if (((c = this.ctl) & 28) == 0) {
                  Object[] a;
                  int n;
                  long i;
                  Object x;
                  if ((a = this.array) != null && h != this.tail && (n = a.length) != 0 && (x = U.getObjectVolatile(a, i = ((long)(n - 1 & h) << ASHIFT) + (long)ABASE)) != null) {
                     if (d == 0L) {
                        if (!this.checkDemand(c)) {
                           break;
                        }
                     } else if (((c & 2) != 0 || U.compareAndSwapInt(this, CTL, c, c | 2)) && U.compareAndSwapObject(a, i, x, (Object)null)) {
                        ++h;
                        U.putOrderedInt(this, HEAD, h);
                        U.getAndAddLong(this, DEMAND, -1L);
                        Thread w;
                        if ((w = this.waiter) != null) {
                           this.signalWaiter(w);
                        }

                        try {
                           s.onNext(x);
                        } catch (Throwable ex) {
                           this.handleOnNext(s, ex);
                        }
                     }
                  } else if (!this.checkEmpty(s, c)) {
                     break;
                  }
               } else if (!this.checkControl(s, c)) {
                  break;
               }
            }
         }

      }

      private boolean checkControl(Flow.Subscriber s, int c) {
         boolean stat = true;
         if ((c & 16) != 0) {
            if (U.compareAndSwapInt(this, CTL, c, c & -17)) {
               try {
                  if (s != null) {
                     s.onSubscribe(this);
                  }
               } catch (Throwable ex) {
                  this.onError(ex);
               }
            }
         } else if ((c & 8) != 0) {
            Throwable ex = this.pendingError;
            this.ctl = 4;
            if (ex != null) {
               try {
                  if (s != null) {
                     s.onError(ex);
                  }
               } catch (Throwable var6) {
               }
            }
         } else {
            this.detach();
            stat = false;
         }

         return stat;
      }

      private boolean checkEmpty(Flow.Subscriber s, int c) {
         boolean stat = true;
         if (this.head == this.tail) {
            if ((c & 2) != 0) {
               U.compareAndSwapInt(this, CTL, c, c & -3);
            } else if ((c & 32) != 0) {
               if (U.compareAndSwapInt(this, CTL, c, 4)) {
                  try {
                     if (s != null) {
                        s.onComplete();
                     }
                  } catch (Throwable var5) {
                  }
               }
            } else if (U.compareAndSwapInt(this, CTL, c, c & -2)) {
               stat = false;
            }
         }

         return stat;
      }

      private boolean checkDemand(int c) {
         boolean stat = true;
         if (this.demand == 0L) {
            if ((c & 2) != 0) {
               U.compareAndSwapInt(this, CTL, c, c & -3);
            } else if (U.compareAndSwapInt(this, CTL, c, c & -2)) {
               stat = false;
            }
         }

         return stat;
      }

      private void handleOnNext(Flow.Subscriber s, Throwable ex) {
         BiConsumer<? super Flow.Subscriber<? super T>, ? super Throwable> h;
         if ((h = this.onNextHandler) != null) {
            try {
               h.accept(s, ex);
            } catch (Throwable var5) {
            }
         }

         this.onError(ex);
      }

      static {
         try {
            CTL = U.objectFieldOffset(BufferedSubscription.class.getDeclaredField("ctl"));
            TAIL = U.objectFieldOffset(BufferedSubscription.class.getDeclaredField("tail"));
            HEAD = U.objectFieldOffset(BufferedSubscription.class.getDeclaredField("head"));
            DEMAND = U.objectFieldOffset(BufferedSubscription.class.getDeclaredField("demand"));
            ABASE = U.arrayBaseOffset(Object[].class);
            int scale = U.arrayIndexScale(Object[].class);
            if ((scale & scale - 1) != 0) {
               throw new Error("data type scale not a power of two");
            }

            ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
         } catch (ReflectiveOperationException e) {
            throw new Error(e);
         }

         Class e = LockSupport.class;
      }
   }
}
