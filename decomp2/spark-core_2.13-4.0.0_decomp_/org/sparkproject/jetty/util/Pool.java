package org.sparkproject.jetty.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.component.DumpableCollection;
import org.sparkproject.jetty.util.thread.AutoLock;

@ManagedObject
public class Pool implements AutoCloseable, Dumpable {
   private static final Logger LOGGER = LoggerFactory.getLogger(Pool.class);
   private final List entries;
   private final int maxEntries;
   private final StrategyType strategyType;
   private final AutoLock lock;
   private final ThreadLocal cache;
   private final AtomicInteger nextIndex;
   private volatile boolean closed;
   /** @deprecated */
   @Deprecated
   private volatile int maxUsage;
   /** @deprecated */
   @Deprecated
   private volatile int maxMultiplex;

   public Pool(StrategyType strategyType, int maxEntries) {
      this(strategyType, maxEntries, false);
   }

   public Pool(StrategyType strategyType, int maxEntries, boolean cache) {
      this.entries = new CopyOnWriteArrayList();
      this.lock = new AutoLock();
      this.maxUsage = -1;
      this.maxMultiplex = -1;
      this.maxEntries = maxEntries;
      this.strategyType = (StrategyType)Objects.requireNonNull(strategyType);
      this.cache = cache ? new ThreadLocal() : null;
      this.nextIndex = strategyType == Pool.StrategyType.ROUND_ROBIN ? new AtomicInteger() : null;
   }

   @ManagedAttribute("The number of reserved entries")
   public int getReservedCount() {
      return (int)this.entries.stream().filter(Entry::isReserved).count();
   }

   @ManagedAttribute("The number of idle entries")
   public int getIdleCount() {
      return (int)this.entries.stream().filter(Entry::isIdle).count();
   }

   @ManagedAttribute("The number of in-use entries")
   public int getInUseCount() {
      return (int)this.entries.stream().filter(Entry::isInUse).count();
   }

   @ManagedAttribute("The number of closed entries")
   public int getClosedCount() {
      return (int)this.entries.stream().filter(Entry::isClosed).count();
   }

   @ManagedAttribute("The maximum number of entries")
   public int getMaxEntries() {
      return this.maxEntries;
   }

   /** @deprecated */
   @ManagedAttribute("The default maximum multiplex count of entries")
   @Deprecated
   public int getMaxMultiplex() {
      return this.maxMultiplex == -1 ? 1 : this.maxMultiplex;
   }

   /** @deprecated */
   @Deprecated
   protected int getMaxMultiplex(Object pooled) {
      return this.getMaxMultiplex();
   }

   /** @deprecated */
   @Deprecated
   public final void setMaxMultiplex(int maxMultiplex) {
      if (maxMultiplex < 1) {
         throw new IllegalArgumentException("Max multiplex must be >= 1");
      } else {
         try (AutoLock l = this.lock.lock()) {
            if (this.closed) {
               return;
            }

            Stream var10000 = this.entries.stream();
            Objects.requireNonNull(MonoEntry.class);
            if (var10000.anyMatch(MonoEntry.class::isInstance)) {
               throw new IllegalStateException("Pool entries do not support multiplexing");
            }

            this.maxMultiplex = maxMultiplex;
         }

      }
   }

   /** @deprecated */
   @ManagedAttribute("The default maximum usage count of entries")
   @Deprecated
   public int getMaxUsageCount() {
      return this.maxUsage;
   }

   /** @deprecated */
   @Deprecated
   protected int getMaxUsageCount(Object pooled) {
      return this.getMaxUsageCount();
   }

   /** @deprecated */
   @Deprecated
   public final void setMaxUsageCount(int maxUsageCount) {
      if (maxUsageCount == 0) {
         throw new IllegalArgumentException("Max usage count must be != 0");
      } else {
         List<Closeable> copy;
         try (AutoLock l = this.lock.lock()) {
            if (this.closed) {
               return;
            }

            Stream var10000 = this.entries.stream();
            Objects.requireNonNull(MonoEntry.class);
            if (var10000.anyMatch(MonoEntry.class::isInstance)) {
               throw new IllegalStateException("Pool entries do not support max usage");
            }

            this.maxUsage = maxUsageCount;
            copy = (List)this.entries.stream().filter((entry) -> entry.isIdleAndOverUsed() && this.remove(entry) && entry.pooled instanceof Closeable).map((entry) -> (Closeable)entry.pooled).collect(Collectors.toList());
         }

         copy.forEach(IO::close);
      }
   }

   /** @deprecated */
   @Deprecated
   public Entry reserve(int allotment) {
      try (AutoLock l = this.lock.lock()) {
         if (this.closed) {
            return null;
         } else {
            int space = this.maxEntries - this.entries.size();
            if (space <= 0) {
               return null;
            } else if (allotment >= 0 && this.getReservedCount() * this.getMaxMultiplex() >= allotment) {
               return null;
            } else {
               Pool<T>.Entry entry = this.newEntry();
               this.entries.add(entry);
               return entry;
            }
         }
      }
   }

   public Entry reserve() {
      try (AutoLock l = this.lock.lock()) {
         if (this.closed) {
            if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("{} is closed, returning null reserved entry", this);
            }

            return null;
         } else {
            int entriesSize = this.entries.size();
            if (this.maxEntries > 0 && entriesSize >= this.maxEntries) {
               if (LOGGER.isDebugEnabled()) {
                  LOGGER.debug("{} has no space: {} >= {}, returning null reserved entry", new Object[]{this, entriesSize, this.maxEntries});
               }

               return null;
            } else {
               Pool<T>.Entry entry = this.newEntry();
               this.entries.add(entry);
               if (LOGGER.isDebugEnabled()) {
                  LOGGER.debug("{} returning new reserved entry {}", this, entry);
               }

               return entry;
            }
         }
      }
   }

   private Entry newEntry() {
      return (Entry)(this.maxMultiplex < 0 && this.maxUsage < 0 ? new MonoEntry() : new MultiEntry());
   }

   public Entry acquire() {
      if (this.closed) {
         return null;
      } else {
         int size = this.entries.size();
         if (size == 0) {
            return null;
         } else {
            if (this.cache != null) {
               Pool<T>.Entry entry = (Entry)this.cache.get();
               if (entry != null && entry.tryAcquire()) {
                  return entry;
               }
            }

            int index = this.startIndex(size);

            for(int tries = size; tries-- > 0; index = (index + 1) % size) {
               try {
                  Pool<T>.Entry entry = (Entry)this.entries.get(index);
                  if (entry != null && entry.tryAcquire()) {
                     return entry;
                  }
               } catch (IndexOutOfBoundsException e) {
                  LOGGER.trace("IGNORED", e);
                  size = this.entries.size();
                  if (size == 0) {
                     break;
                  }
               }
            }

            return null;
         }
      }
   }

   private int startIndex(int size) {
      switch (this.strategyType.ordinal()) {
         case 0:
            return 0;
         case 1:
            return ThreadLocalRandom.current().nextInt(size);
         case 2:
            return (int)(Thread.currentThread().getId() % (long)size);
         case 3:
            return this.nextIndex.getAndUpdate((c) -> Math.max(0, c + 1)) % size;
         default:
            throw new IllegalArgumentException("Unknown strategy type: " + String.valueOf(this.strategyType));
      }
   }

   public Entry acquire(Function creator) {
      Pool<T>.Entry entry = this.acquire();
      if (entry != null) {
         return entry;
      } else {
         entry = this.reserve();
         if (entry == null) {
            return null;
         } else {
            T value;
            try {
               value = (T)creator.apply(entry);
            } catch (Throwable th) {
               this.remove(entry);
               throw th;
            }

            if (value == null) {
               this.remove(entry);
               return null;
            } else {
               return entry.enable(value, true) ? entry : null;
            }
         }
      }
   }

   public boolean release(Entry entry) {
      if (this.closed) {
         return false;
      } else {
         boolean released = entry.tryRelease();
         if (released && this.cache != null) {
            this.cache.set(entry);
         }

         return released;
      }
   }

   public boolean remove(Entry entry) {
      if (this.closed) {
         return false;
      } else if (!entry.tryRemove()) {
         if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Attempt to remove an object from the pool that is still in use: {}", entry);
         }

         return false;
      } else {
         boolean removed = this.entries.remove(entry);
         if (!removed && LOGGER.isDebugEnabled()) {
            LOGGER.debug("Attempt to remove an object from the pool that does not exist: {}", entry);
         }

         return removed;
      }
   }

   public boolean isClosed() {
      return this.closed;
   }

   public void close() {
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("Closing {}", this);
      }

      List<Pool<T>.Entry> copy;
      try (AutoLock l = this.lock.lock()) {
         this.closed = true;
         copy = new ArrayList(this.entries);
         this.entries.clear();
      }

      for(Entry entry : copy) {
         boolean removed = entry.tryRemove();
         if (removed) {
            if (entry.pooled instanceof Closeable) {
               IO.close((Closeable)entry.pooled);
            }
         } else if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Pooled object still in use: {}", entry);
         }
      }

   }

   public int size() {
      return this.entries.size();
   }

   public Collection values() {
      return Collections.unmodifiableCollection(this.entries);
   }

   public void dump(Appendable out, String indent) throws IOException {
      Dumpable.dumpObjects(out, indent, this, new DumpableCollection("entries", this.entries));
   }

   public String toString() {
      return String.format("%s@%x[inUse=%d,size=%d,max=%d,closed=%b]", this.getClass().getSimpleName(), this.hashCode(), this.getInUseCount(), this.size(), this.getMaxEntries(), this.isClosed());
   }

   public static enum StrategyType {
      FIRST,
      RANDOM,
      THREAD_ID,
      ROUND_ROBIN;

      // $FF: synthetic method
      private static StrategyType[] $values() {
         return new StrategyType[]{FIRST, RANDOM, THREAD_ID, ROUND_ROBIN};
      }
   }

   public abstract class Entry {
      private Object pooled;

      public boolean enable(Object pooled, boolean acquire) {
         Objects.requireNonNull(pooled);
         if (!this.isReserved()) {
            if (this.isClosed()) {
               return false;
            } else {
               throw new IllegalStateException("Entry already enabled: " + String.valueOf(this));
            }
         } else {
            this.pooled = pooled;
            if (this.tryEnable(acquire)) {
               return true;
            } else {
               this.pooled = null;
               if (this.isClosed()) {
                  return false;
               } else {
                  throw new IllegalStateException("Entry already enabled: " + String.valueOf(this));
               }
            }
         }
      }

      public Object getPooled() {
         return this.pooled;
      }

      public boolean release() {
         return Pool.this.release(this);
      }

      public boolean remove() {
         return Pool.this.remove(this);
      }

      abstract boolean tryEnable(boolean var1);

      abstract boolean tryAcquire();

      abstract boolean tryRelease();

      abstract boolean tryRemove();

      public abstract boolean isClosed();

      public abstract boolean isReserved();

      public abstract boolean isIdle();

      public abstract boolean isInUse();

      /** @deprecated */
      @Deprecated
      public boolean isOverUsed() {
         return false;
      }

      boolean isIdleAndOverUsed() {
         return false;
      }

      int getUsageCount() {
         return 0;
      }

      void setUsageCount(int usageCount) {
      }
   }

   private class MonoEntry extends Entry {
      private final AtomicInteger state = new AtomicInteger(Integer.MIN_VALUE);

      protected boolean tryEnable(boolean acquire) {
         return this.state.compareAndSet(Integer.MIN_VALUE, acquire ? 1 : 0);
      }

      boolean tryAcquire() {
         int s;
         do {
            s = this.state.get();
            if (s != 0) {
               return false;
            }
         } while(!this.state.compareAndSet(s, 1));

         return true;
      }

      boolean tryRelease() {
         int s;
         do {
            s = this.state.get();
            if (s < 0) {
               return false;
            }

            if (s == 0) {
               throw new IllegalStateException("Cannot release an already released entry");
            }
         } while(!this.state.compareAndSet(s, 0));

         return true;
      }

      boolean tryRemove() {
         this.state.set(-1);
         return true;
      }

      public boolean isClosed() {
         return this.state.get() < 0;
      }

      public boolean isReserved() {
         return this.state.get() == Integer.MIN_VALUE;
      }

      public boolean isIdle() {
         return this.state.get() == 0;
      }

      public boolean isInUse() {
         return this.state.get() == 1;
      }

      public String toString() {
         String s;
         switch (this.state.get()) {
            case Integer.MIN_VALUE:
               s = "PENDING";
               break;
            case -1:
               s = "CLOSED";
               break;
            case 0:
               s = "IDLE";
               break;
            default:
               s = "ACTIVE";
         }

         return String.format("%s@%x{%s,pooled=%s}", this.getClass().getSimpleName(), this.hashCode(), s, this.getPooled());
      }
   }

   class MultiEntry extends Entry {
      private final AtomicBiInteger state = new AtomicBiInteger(Integer.MIN_VALUE, 0);

      void setUsageCount(int usageCount) {
         this.state.getAndSetHi(usageCount);
      }

      protected boolean tryEnable(boolean acquire) {
         int usage = acquire ? 1 : 0;
         return this.state.compareAndSet(Integer.MIN_VALUE, usage, 0, usage);
      }

      boolean tryAcquire() {
         long encoded;
         int multiplexCount;
         int newUsageCount;
         do {
            encoded = this.state.get();
            int usageCount = AtomicBiInteger.getHi(encoded);
            multiplexCount = AtomicBiInteger.getLo(encoded);
            boolean closed = usageCount < 0;
            if (closed) {
               return false;
            }

            T pooled = (T)this.getPooled();
            int maxUsageCount = Pool.this.getMaxUsageCount(pooled);
            if (maxUsageCount > 0 && usageCount >= maxUsageCount) {
               return false;
            }

            int maxMultiplexed = Pool.this.getMaxMultiplex(pooled);
            if (maxMultiplexed > 0 && multiplexCount >= maxMultiplexed) {
               return false;
            }

            newUsageCount = usageCount == Integer.MAX_VALUE ? Integer.MAX_VALUE : usageCount + 1;
         } while(!this.state.compareAndSet(encoded, newUsageCount, multiplexCount + 1));

         return true;
      }

      boolean tryRelease() {
         int newMultiplexCount;
         int usageCount;
         long encoded;
         do {
            encoded = this.state.get();
            usageCount = AtomicBiInteger.getHi(encoded);
            boolean closed = usageCount < 0;
            if (closed) {
               return false;
            }

            newMultiplexCount = AtomicBiInteger.getLo(encoded) - 1;
            if (newMultiplexCount < 0) {
               throw new IllegalStateException("Cannot release an already released entry");
            }
         } while(!this.state.compareAndSet(encoded, usageCount, newMultiplexCount));

         int currentMaxUsageCount = Pool.this.getMaxUsageCount(this.getPooled());
         boolean overUsed = currentMaxUsageCount > 0 && usageCount >= currentMaxUsageCount;
         return !overUsed || newMultiplexCount != 0;
      }

      boolean tryRemove() {
         int newMultiplexCount;
         boolean removed;
         do {
            long encoded = this.state.get();
            int usageCount = AtomicBiInteger.getHi(encoded);
            int multiplexCount = AtomicBiInteger.getLo(encoded);
            newMultiplexCount = Math.max(multiplexCount - 1, 0);
            removed = this.state.compareAndSet(usageCount, -1, multiplexCount, newMultiplexCount);
         } while(!removed);

         return newMultiplexCount == 0;
      }

      public boolean isClosed() {
         return this.state.getHi() < 0;
      }

      public boolean isReserved() {
         return this.state.getHi() == Integer.MIN_VALUE;
      }

      public boolean isIdle() {
         long encoded = this.state.get();
         return AtomicBiInteger.getHi(encoded) >= 0 && AtomicBiInteger.getLo(encoded) == 0;
      }

      public boolean isInUse() {
         long encoded = this.state.get();
         return AtomicBiInteger.getHi(encoded) >= 0 && AtomicBiInteger.getLo(encoded) > 0;
      }

      public boolean isOverUsed() {
         int maxUsageCount = Pool.this.getMaxUsageCount();
         int usageCount = this.state.getHi();
         return maxUsageCount > 0 && usageCount >= maxUsageCount;
      }

      boolean isIdleAndOverUsed() {
         int maxUsageCount = Pool.this.getMaxUsageCount();
         long encoded = this.state.get();
         int usageCount = AtomicBiInteger.getHi(encoded);
         int multiplexCount = AtomicBiInteger.getLo(encoded);
         return maxUsageCount > 0 && usageCount >= maxUsageCount && multiplexCount == 0;
      }

      int getUsageCount() {
         return Math.max(this.state.getHi(), 0);
      }

      public String toString() {
         long encoded = this.state.get();
         int usageCount = AtomicBiInteger.getHi(encoded);
         int multiplexCount = AtomicBiInteger.getLo(encoded);
         String state = usageCount < 0 ? (usageCount == Integer.MIN_VALUE ? "PENDING" : "CLOSED") : (multiplexCount == 0 ? "IDLE" : "ACTIVE");
         return String.format("%s@%x{%s,usage=%d,multiplex=%d,pooled=%s}", this.getClass().getSimpleName(), this.hashCode(), state, Math.max(usageCount, 0), Math.max(multiplexCount, 0), this.getPooled());
      }
   }
}
