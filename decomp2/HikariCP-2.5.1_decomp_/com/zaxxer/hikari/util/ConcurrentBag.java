package com.zaxxer.hikari.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcurrentBag implements AutoCloseable {
   private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentBag.class);
   private final QueuedSequenceSynchronizer synchronizer;
   private final CopyOnWriteArrayList sharedList;
   private final boolean weakThreadLocals;
   private final ThreadLocal threadList;
   private final IBagStateListener listener;
   private final AtomicInteger waiters;
   private volatile boolean closed;

   public ConcurrentBag(IBagStateListener listener) {
      this.listener = listener;
      this.weakThreadLocals = this.useWeakThreadLocals();
      this.waiters = new AtomicInteger();
      this.sharedList = new CopyOnWriteArrayList();
      this.synchronizer = new QueuedSequenceSynchronizer();
      if (this.weakThreadLocals) {
         this.threadList = new ThreadLocal();
      } else {
         this.threadList = new ThreadLocal() {
            protected List initialValue() {
               return new FastList(IConcurrentBagEntry.class, 16);
            }
         };
      }

   }

   public IConcurrentBagEntry borrow(long timeout, TimeUnit timeUnit) throws InterruptedException {
      List<Object> list = (List)this.threadList.get();
      if (this.weakThreadLocals && list == null) {
         list = new ArrayList(16);
         this.threadList.set(list);
      }

      for(int i = list.size() - 1; i >= 0; --i) {
         Object entry = list.remove(i);
         T bagEntry = (T)(this.weakThreadLocals ? (IConcurrentBagEntry)((WeakReference)entry).get() : (IConcurrentBagEntry)entry);
         if (bagEntry != null && bagEntry.compareAndSet(0, 1)) {
            return bagEntry;
         }
      }

      timeout = timeUnit.toNanos(timeout);
      Future<Boolean> addItemFuture = null;
      long startTime = System.nanoTime();
      long originTimeout = timeout;
      this.waiters.incrementAndGet();

      try {
         while(true) {
            long startSeq = this.synchronizer.currentSequence();

            for(IConcurrentBagEntry bagEntry : this.sharedList) {
               if (bagEntry.compareAndSet(0, 1)) {
                  if (this.waiters.get() > 1 && addItemFuture == null) {
                     this.listener.addBagItem();
                  }

                  IConcurrentBagEntry var14 = bagEntry;
                  return var14;
               }
            }

            if (startSeq >= this.synchronizer.currentSequence()) {
               if (addItemFuture == null || addItemFuture.isDone()) {
                  addItemFuture = this.listener.addBagItem();
               }

               timeout = originTimeout - (System.nanoTime() - startTime);
               if (timeout <= 10000L || !this.synchronizer.waitUntilSequenceExceeded(startSeq, timeout)) {
                  return null;
               }
            }
         }
      } finally {
         this.waiters.decrementAndGet();
      }
   }

   public void requite(IConcurrentBagEntry bagEntry) {
      bagEntry.setState(0);
      List<Object> threadLocalList = (List)this.threadList.get();
      if (threadLocalList != null) {
         threadLocalList.add(this.weakThreadLocals ? new WeakReference(bagEntry) : bagEntry);
      }

      this.synchronizer.signal();
   }

   public void add(IConcurrentBagEntry bagEntry) {
      if (this.closed) {
         LOGGER.info("ConcurrentBag has been closed, ignoring add()");
         throw new IllegalStateException("ConcurrentBag has been closed, ignoring add()");
      } else {
         this.sharedList.add(bagEntry);
         this.synchronizer.signal();
      }
   }

   public boolean remove(IConcurrentBagEntry bagEntry) {
      if (!bagEntry.compareAndSet(1, -1) && !bagEntry.compareAndSet(-2, -1) && !this.closed) {
         LOGGER.warn("Attempt to remove an object from the bag that was not borrowed or reserved: {}", bagEntry);
         return false;
      } else {
         boolean removed = this.sharedList.remove(bagEntry);
         if (!removed && !this.closed) {
            LOGGER.warn("Attempt to remove an object from the bag that does not exist: {}", bagEntry);
         }

         return removed;
      }
   }

   public void close() {
      this.closed = true;
   }

   public List values(int state) {
      return (List)this.sharedList.stream().filter((e) -> e.getState() == state).collect(Collectors.toList());
   }

   public List values() {
      return (List)this.sharedList.clone();
   }

   public boolean reserve(IConcurrentBagEntry bagEntry) {
      return bagEntry.compareAndSet(0, -2);
   }

   public void unreserve(IConcurrentBagEntry bagEntry) {
      if (bagEntry.compareAndSet(-2, 0)) {
         this.synchronizer.signal();
      } else {
         LOGGER.warn("Attempt to relinquish an object to the bag that was not reserved: {}", bagEntry);
      }

   }

   public int getPendingQueue() {
      return this.synchronizer.getQueueLength();
   }

   public int getCount(int state) {
      return (int)this.sharedList.stream().filter((e) -> e.getState() == state).count();
   }

   public int size() {
      return this.sharedList.size();
   }

   public void dumpState() {
      this.sharedList.forEach((entry) -> LOGGER.info(entry.toString()));
   }

   private boolean useWeakThreadLocals() {
      try {
         if (System.getProperty("com.zaxxer.hikari.useWeakReferences") != null) {
            return Boolean.getBoolean("com.zaxxer.hikari.useWeakReferences");
         } else {
            return this.getClass().getClassLoader() != ClassLoader.getSystemClassLoader();
         }
      } catch (SecurityException var2) {
         return true;
      }
   }

   public interface IBagStateListener {
      Future addBagItem();
   }

   public interface IConcurrentBagEntry {
      int STATE_NOT_IN_USE = 0;
      int STATE_IN_USE = 1;
      int STATE_REMOVED = -1;
      int STATE_RESERVED = -2;

      boolean compareAndSet(int var1, int var2);

      void setState(int var1);

      int getState();
   }
}
