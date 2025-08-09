package org.sparkproject.guava.cache;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Equivalence;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Stopwatch;
import org.sparkproject.guava.base.Ticker;
import org.sparkproject.guava.collect.AbstractSequentialIterator;
import org.sparkproject.guava.collect.ImmutableMap;
import org.sparkproject.guava.collect.ImmutableSet;
import org.sparkproject.guava.collect.Maps;
import org.sparkproject.guava.collect.Sets;
import org.sparkproject.guava.primitives.Ints;
import org.sparkproject.guava.util.concurrent.ExecutionError;
import org.sparkproject.guava.util.concurrent.Futures;
import org.sparkproject.guava.util.concurrent.ListenableFuture;
import org.sparkproject.guava.util.concurrent.MoreExecutors;
import org.sparkproject.guava.util.concurrent.SettableFuture;
import org.sparkproject.guava.util.concurrent.UncheckedExecutionException;
import org.sparkproject.guava.util.concurrent.Uninterruptibles;

@GwtCompatible(
   emulated = true
)
class LocalCache extends AbstractMap implements ConcurrentMap {
   static final int MAXIMUM_CAPACITY = 1073741824;
   static final int MAX_SEGMENTS = 65536;
   static final int CONTAINS_VALUE_RETRIES = 3;
   static final int DRAIN_THRESHOLD = 63;
   static final int DRAIN_MAX = 16;
   static final Logger logger = Logger.getLogger(LocalCache.class.getName());
   final int segmentMask;
   final int segmentShift;
   final Segment[] segments;
   final int concurrencyLevel;
   final Equivalence keyEquivalence;
   final Equivalence valueEquivalence;
   final Strength keyStrength;
   final Strength valueStrength;
   final long maxWeight;
   final Weigher weigher;
   final long expireAfterAccessNanos;
   final long expireAfterWriteNanos;
   final long refreshNanos;
   final Queue removalNotificationQueue;
   final RemovalListener removalListener;
   final Ticker ticker;
   final EntryFactory entryFactory;
   final AbstractCache.StatsCounter globalStatsCounter;
   @CheckForNull
   final CacheLoader defaultLoader;
   static final ValueReference UNSET = new ValueReference() {
      @CheckForNull
      public Object get() {
         return null;
      }

      public int getWeight() {
         return 0;
      }

      @CheckForNull
      public ReferenceEntry getEntry() {
         return null;
      }

      public ValueReference copyFor(ReferenceQueue queue, @CheckForNull Object value, ReferenceEntry entry) {
         return this;
      }

      public boolean isLoading() {
         return false;
      }

      public boolean isActive() {
         return false;
      }

      @CheckForNull
      public Object waitForValue() {
         return null;
      }

      public void notifyNewValue(Object newValue) {
      }
   };
   static final Queue DISCARDING_QUEUE = new AbstractQueue() {
      public boolean offer(Object o) {
         return true;
      }

      @CheckForNull
      public Object peek() {
         return null;
      }

      @CheckForNull
      public Object poll() {
         return null;
      }

      public int size() {
         return 0;
      }

      public Iterator iterator() {
         return ImmutableSet.of().iterator();
      }
   };
   @LazyInit
   @CheckForNull
   @RetainedWith
   Set keySet;
   @LazyInit
   @CheckForNull
   @RetainedWith
   Collection values;
   @LazyInit
   @CheckForNull
   @RetainedWith
   Set entrySet;

   LocalCache(CacheBuilder builder, @CheckForNull CacheLoader loader) {
      this.concurrencyLevel = Math.min(builder.getConcurrencyLevel(), 65536);
      this.keyStrength = builder.getKeyStrength();
      this.valueStrength = builder.getValueStrength();
      this.keyEquivalence = builder.getKeyEquivalence();
      this.valueEquivalence = builder.getValueEquivalence();
      this.maxWeight = builder.getMaximumWeight();
      this.weigher = builder.getWeigher();
      this.expireAfterAccessNanos = builder.getExpireAfterAccessNanos();
      this.expireAfterWriteNanos = builder.getExpireAfterWriteNanos();
      this.refreshNanos = builder.getRefreshNanos();
      this.removalListener = builder.getRemovalListener();
      this.removalNotificationQueue = (Queue)(this.removalListener == CacheBuilder.NullListener.INSTANCE ? discardingQueue() : new ConcurrentLinkedQueue());
      this.ticker = builder.getTicker(this.recordsTime());
      this.entryFactory = LocalCache.EntryFactory.getFactory(this.keyStrength, this.usesAccessEntries(), this.usesWriteEntries());
      this.globalStatsCounter = (AbstractCache.StatsCounter)builder.getStatsCounterSupplier().get();
      this.defaultLoader = loader;
      int initialCapacity = Math.min(builder.getInitialCapacity(), 1073741824);
      if (this.evictsBySize() && !this.customWeigher()) {
         initialCapacity = (int)Math.min((long)initialCapacity, this.maxWeight);
      }

      int segmentShift = 0;

      int segmentCount;
      for(segmentCount = 1; segmentCount < this.concurrencyLevel && (!this.evictsBySize() || (long)segmentCount * 20L <= this.maxWeight); segmentCount <<= 1) {
         ++segmentShift;
      }

      this.segmentShift = 32 - segmentShift;
      this.segmentMask = segmentCount - 1;
      this.segments = this.newSegmentArray(segmentCount);
      int segmentCapacity = initialCapacity / segmentCount;
      if (segmentCapacity * segmentCount < initialCapacity) {
         ++segmentCapacity;
      }

      int segmentSize;
      for(segmentSize = 1; segmentSize < segmentCapacity; segmentSize <<= 1) {
      }

      if (this.evictsBySize()) {
         long maxSegmentWeight = this.maxWeight / (long)segmentCount + 1L;
         long remainder = this.maxWeight % (long)segmentCount;

         for(int i = 0; i < this.segments.length; ++i) {
            if ((long)i == remainder) {
               --maxSegmentWeight;
            }

            this.segments[i] = this.createSegment(segmentSize, maxSegmentWeight, (AbstractCache.StatsCounter)builder.getStatsCounterSupplier().get());
         }
      } else {
         for(int i = 0; i < this.segments.length; ++i) {
            this.segments[i] = this.createSegment(segmentSize, -1L, (AbstractCache.StatsCounter)builder.getStatsCounterSupplier().get());
         }
      }

   }

   boolean evictsBySize() {
      return this.maxWeight >= 0L;
   }

   boolean customWeigher() {
      return this.weigher != CacheBuilder.OneWeigher.INSTANCE;
   }

   boolean expires() {
      return this.expiresAfterWrite() || this.expiresAfterAccess();
   }

   boolean expiresAfterWrite() {
      return this.expireAfterWriteNanos > 0L;
   }

   boolean expiresAfterAccess() {
      return this.expireAfterAccessNanos > 0L;
   }

   boolean refreshes() {
      return this.refreshNanos > 0L;
   }

   boolean usesAccessQueue() {
      return this.expiresAfterAccess() || this.evictsBySize();
   }

   boolean usesWriteQueue() {
      return this.expiresAfterWrite();
   }

   boolean recordsWrite() {
      return this.expiresAfterWrite() || this.refreshes();
   }

   boolean recordsAccess() {
      return this.expiresAfterAccess();
   }

   boolean recordsTime() {
      return this.recordsWrite() || this.recordsAccess();
   }

   boolean usesWriteEntries() {
      return this.usesWriteQueue() || this.recordsWrite();
   }

   boolean usesAccessEntries() {
      return this.usesAccessQueue() || this.recordsAccess();
   }

   boolean usesKeyReferences() {
      return this.keyStrength != LocalCache.Strength.STRONG;
   }

   boolean usesValueReferences() {
      return this.valueStrength != LocalCache.Strength.STRONG;
   }

   static ValueReference unset() {
      return UNSET;
   }

   static ReferenceEntry nullEntry() {
      return LocalCache.NullEntry.INSTANCE;
   }

   static Queue discardingQueue() {
      return DISCARDING_QUEUE;
   }

   static int rehash(int h) {
      h += h << 15 ^ -12931;
      h ^= h >>> 10;
      h += h << 3;
      h ^= h >>> 6;
      h += (h << 2) + (h << 14);
      return h ^ h >>> 16;
   }

   @VisibleForTesting
   ReferenceEntry newEntry(Object key, int hash, @CheckForNull ReferenceEntry next) {
      Segment<K, V> segment = this.segmentFor(hash);
      segment.lock();

      ReferenceEntry var5;
      try {
         var5 = segment.newEntry(key, hash, next);
      } finally {
         segment.unlock();
      }

      return var5;
   }

   @VisibleForTesting
   ReferenceEntry copyEntry(ReferenceEntry original, ReferenceEntry newNext) {
      int hash = original.getHash();
      return this.segmentFor(hash).copyEntry(original, newNext);
   }

   @VisibleForTesting
   ValueReference newValueReference(ReferenceEntry entry, Object value, int weight) {
      int hash = entry.getHash();
      return this.valueStrength.referenceValue(this.segmentFor(hash), entry, Preconditions.checkNotNull(value), weight);
   }

   int hash(@CheckForNull Object key) {
      int h = this.keyEquivalence.hash(key);
      return rehash(h);
   }

   void reclaimValue(ValueReference valueReference) {
      ReferenceEntry<K, V> entry = valueReference.getEntry();
      int hash = entry.getHash();
      this.segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
   }

   void reclaimKey(ReferenceEntry entry) {
      int hash = entry.getHash();
      this.segmentFor(hash).reclaimKey(entry, hash);
   }

   @VisibleForTesting
   boolean isLive(ReferenceEntry entry, long now) {
      return this.segmentFor(entry.getHash()).getLiveValue(entry, now) != null;
   }

   Segment segmentFor(int hash) {
      return this.segments[hash >>> this.segmentShift & this.segmentMask];
   }

   Segment createSegment(int initialCapacity, long maxSegmentWeight, AbstractCache.StatsCounter statsCounter) {
      return new Segment(this, initialCapacity, maxSegmentWeight, statsCounter);
   }

   @CheckForNull
   Object getLiveValue(ReferenceEntry entry, long now) {
      if (entry.getKey() == null) {
         return null;
      } else {
         V value = (V)entry.getValueReference().get();
         if (value == null) {
            return null;
         } else {
            return this.isExpired(entry, now) ? null : value;
         }
      }
   }

   boolean isExpired(ReferenceEntry entry, long now) {
      Preconditions.checkNotNull(entry);
      if (this.expiresAfterAccess() && now - entry.getAccessTime() >= this.expireAfterAccessNanos) {
         return true;
      } else {
         return this.expiresAfterWrite() && now - entry.getWriteTime() >= this.expireAfterWriteNanos;
      }
   }

   static void connectAccessOrder(ReferenceEntry previous, ReferenceEntry next) {
      previous.setNextInAccessQueue(next);
      next.setPreviousInAccessQueue(previous);
   }

   static void nullifyAccessOrder(ReferenceEntry nulled) {
      ReferenceEntry<K, V> nullEntry = nullEntry();
      nulled.setNextInAccessQueue(nullEntry);
      nulled.setPreviousInAccessQueue(nullEntry);
   }

   static void connectWriteOrder(ReferenceEntry previous, ReferenceEntry next) {
      previous.setNextInWriteQueue(next);
      next.setPreviousInWriteQueue(previous);
   }

   static void nullifyWriteOrder(ReferenceEntry nulled) {
      ReferenceEntry<K, V> nullEntry = nullEntry();
      nulled.setNextInWriteQueue(nullEntry);
      nulled.setPreviousInWriteQueue(nullEntry);
   }

   void processPendingNotifications() {
      RemovalNotification<K, V> notification;
      while((notification = (RemovalNotification)this.removalNotificationQueue.poll()) != null) {
         try {
            this.removalListener.onRemoval(notification);
         } catch (Throwable e) {
            logger.log(Level.WARNING, "Exception thrown by removal listener", e);
         }
      }

   }

   final Segment[] newSegmentArray(int ssize) {
      return new Segment[ssize];
   }

   public void cleanUp() {
      for(Segment segment : this.segments) {
         segment.cleanUp();
      }

   }

   public boolean isEmpty() {
      long sum = 0L;
      Segment<K, V>[] segments = this.segments;

      for(Segment segment : segments) {
         if (segment.count != 0) {
            return false;
         }

         sum += (long)segment.modCount;
      }

      if (sum != 0L) {
         for(Segment segment : segments) {
            if (segment.count != 0) {
               return false;
            }

            sum -= (long)segment.modCount;
         }

         return sum == 0L;
      } else {
         return true;
      }
   }

   long longSize() {
      Segment<K, V>[] segments = this.segments;
      long sum = 0L;

      for(Segment segment : segments) {
         sum += (long)segment.count;
      }

      return sum;
   }

   public int size() {
      return Ints.saturatedCast(this.longSize());
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object get(@CheckForNull Object key) {
      if (key == null) {
         return null;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).get(key, hash);
      }
   }

   @CanIgnoreReturnValue
   Object get(Object key, CacheLoader loader) throws ExecutionException {
      int hash = this.hash(Preconditions.checkNotNull(key));
      return this.segmentFor(hash).get(key, hash, loader);
   }

   @CheckForNull
   public Object getIfPresent(Object key) {
      int hash = this.hash(Preconditions.checkNotNull(key));
      V value = (V)this.segmentFor(hash).get(key, hash);
      if (value == null) {
         this.globalStatsCounter.recordMisses(1);
      } else {
         this.globalStatsCounter.recordHits(1);
      }

      return value;
   }

   @CheckForNull
   public Object getOrDefault(@CheckForNull Object key, @CheckForNull Object defaultValue) {
      V result = (V)this.get(key);
      return result != null ? result : defaultValue;
   }

   Object getOrLoad(Object key) throws ExecutionException {
      return this.get(key, this.defaultLoader);
   }

   ImmutableMap getAllPresent(Iterable keys) {
      int hits = 0;
      int misses = 0;
      ImmutableMap.Builder<K, V> result = ImmutableMap.builder();

      for(Object key : keys) {
         V value = (V)this.get(key);
         if (value == null) {
            ++misses;
         } else {
            result.put(key, value);
            ++hits;
         }
      }

      this.globalStatsCounter.recordHits(hits);
      this.globalStatsCounter.recordMisses(misses);
      return result.buildKeepingLast();
   }

   ImmutableMap getAll(Iterable keys) throws ExecutionException {
      int hits = 0;
      int misses = 0;
      Map<K, V> result = Maps.newLinkedHashMap();
      Set<K> keysToLoad = Sets.newLinkedHashSet();

      for(Object key : keys) {
         V value = (V)this.get(key);
         if (!result.containsKey(key)) {
            result.put(key, value);
            if (value == null) {
               ++misses;
               keysToLoad.add(key);
            } else {
               ++hits;
            }
         }
      }

      ImmutableMap var16;
      try {
         if (!keysToLoad.isEmpty()) {
            try {
               Map<K, V> newEntries = this.loadAll(Collections.unmodifiableSet(keysToLoad), this.defaultLoader);

               for(Object key : keysToLoad) {
                  V value = (V)newEntries.get(key);
                  if (value == null) {
                     throw new CacheLoader.InvalidCacheLoadException("loadAll failed to return a value for " + key);
                  }

                  result.put(key, value);
               }
            } catch (CacheLoader.UnsupportedLoadingOperationException var13) {
               for(Object key : keysToLoad) {
                  --misses;
                  result.put(key, this.get(key, this.defaultLoader));
               }
            }
         }

         var16 = ImmutableMap.copyOf(result);
      } finally {
         this.globalStatsCounter.recordHits(hits);
         this.globalStatsCounter.recordMisses(misses);
      }

      return var16;
   }

   @CheckForNull
   Map loadAll(Set keys, CacheLoader loader) throws ExecutionException {
      Preconditions.checkNotNull(loader);
      Preconditions.checkNotNull(keys);
      Stopwatch stopwatch = Stopwatch.createStarted();
      boolean success = false;

      Map<K, V> result;
      try {
         Map<K, V> map = loader.loadAll(keys);
         result = map;
         success = true;
      } catch (CacheLoader.UnsupportedLoadingOperationException e) {
         success = true;
         throw e;
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         throw new ExecutionException(e);
      } catch (RuntimeException e) {
         throw new UncheckedExecutionException(e);
      } catch (Exception e) {
         throw new ExecutionException(e);
      } catch (Error e) {
         throw new ExecutionError(e);
      } finally {
         if (!success) {
            this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
         }

      }

      if (result == null) {
         this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
         throw new CacheLoader.InvalidCacheLoadException(loader + " returned null map from loadAll");
      } else {
         stopwatch.stop();
         boolean nullsPresent = false;

         for(Map.Entry entry : result.entrySet()) {
            K key = (K)entry.getKey();
            V value = (V)entry.getValue();
            if (key != null && value != null) {
               this.put(key, value);
            } else {
               nullsPresent = true;
            }
         }

         if (nullsPresent) {
            this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
            throw new CacheLoader.InvalidCacheLoadException(loader + " returned null keys or values from loadAll");
         } else {
            this.globalStatsCounter.recordLoadSuccess(stopwatch.elapsed(TimeUnit.NANOSECONDS));
            return result;
         }
      }
   }

   @CheckForNull
   ReferenceEntry getEntry(@CheckForNull Object key) {
      if (key == null) {
         return null;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).getEntry(key, hash);
      }
   }

   void refresh(Object key) {
      int hash = this.hash(Preconditions.checkNotNull(key));
      this.segmentFor(hash).refresh(key, hash, this.defaultLoader, false);
   }

   public boolean containsKey(@CheckForNull Object key) {
      if (key == null) {
         return false;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).containsKey(key, hash);
      }
   }

   public boolean containsValue(@CheckForNull Object value) {
      if (value == null) {
         return false;
      } else {
         long now = this.ticker.read();
         Segment<K, V>[] segments = this.segments;
         long last = -1L;

         for(int i = 0; i < 3; ++i) {
            long sum = 0L;

            for(Segment segment : segments) {
               int unused = segment.count;
               AtomicReferenceArray<ReferenceEntry<K, V>> table = segment.table;

               for(int j = 0; j < table.length(); ++j) {
                  for(ReferenceEntry<K, V> e = (ReferenceEntry)table.get(j); e != null; e = e.getNext()) {
                     V v = (V)segment.getLiveValue(e, now);
                     if (v != null && this.valueEquivalence.equivalent(value, v)) {
                        return true;
                     }
                  }
               }

               sum += (long)segment.modCount;
            }

            if (sum == last) {
               break;
            }

            last = sum;
         }

         return false;
      }
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(Object key, Object value) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(value);
      int hash = this.hash(key);
      return this.segmentFor(hash).put(key, hash, value, false);
   }

   @CheckForNull
   public Object putIfAbsent(Object key, Object value) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(value);
      int hash = this.hash(key);
      return this.segmentFor(hash).put(key, hash, value, true);
   }

   @CheckForNull
   public Object compute(Object key, BiFunction function) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(function);
      int hash = this.hash(key);
      return this.segmentFor(hash).compute(key, hash, function);
   }

   public Object computeIfAbsent(Object key, Function function) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(function);
      return this.compute(key, (k, oldValue) -> oldValue == null ? function.apply(key) : oldValue);
   }

   @CheckForNull
   public Object computeIfPresent(Object key, BiFunction function) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(function);
      return this.compute(key, (k, oldValue) -> oldValue == null ? null : function.apply(k, oldValue));
   }

   @CheckForNull
   public Object merge(Object key, Object newValue, BiFunction function) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(newValue);
      Preconditions.checkNotNull(function);
      return this.compute(key, (k, oldValue) -> oldValue == null ? newValue : function.apply(oldValue, newValue));
   }

   public void putAll(Map m) {
      for(Map.Entry e : m.entrySet()) {
         this.put(e.getKey(), e.getValue());
      }

   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object remove(@CheckForNull Object key) {
      if (key == null) {
         return null;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).remove(key, hash);
      }
   }

   @CanIgnoreReturnValue
   public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
      if (key != null && value != null) {
         int hash = this.hash(key);
         return this.segmentFor(hash).remove(key, hash, value);
      } else {
         return false;
      }
   }

   @CanIgnoreReturnValue
   public boolean replace(Object key, @CheckForNull Object oldValue, Object newValue) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(newValue);
      if (oldValue == null) {
         return false;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).replace(key, hash, oldValue, newValue);
      }
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object replace(Object key, Object value) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(value);
      int hash = this.hash(key);
      return this.segmentFor(hash).replace(key, hash, value);
   }

   public void clear() {
      for(Segment segment : this.segments) {
         segment.clear();
      }

   }

   void invalidateAll(Iterable keys) {
      for(Object key : keys) {
         this.remove(key);
      }

   }

   public Set keySet() {
      Set<K> ks = this.keySet;
      return ks != null ? ks : (this.keySet = new KeySet());
   }

   public Collection values() {
      Collection<V> vs = this.values;
      return vs != null ? vs : (this.values = new Values());
   }

   @GwtIncompatible
   public Set entrySet() {
      Set<Map.Entry<K, V>> es = this.entrySet;
      return es != null ? es : (this.entrySet = new EntrySet());
   }

   boolean removeIf(BiPredicate filter) {
      Preconditions.checkNotNull(filter);
      boolean changed = false;

      for(Object key : this.keySet()) {
         V value = (V)this.get(key);
         if (value != null && filter.test(key, value) && this.remove(key, value)) {
            changed = true;
         }
      }

      return changed;
   }

   static enum Strength {
      STRONG {
         ValueReference referenceValue(Segment segment, ReferenceEntry entry, Object value, int weight) {
            return (ValueReference)(weight == 1 ? new StrongValueReference(value) : new WeightedStrongValueReference(value, weight));
         }

         Equivalence defaultEquivalence() {
            return Equivalence.equals();
         }
      },
      SOFT {
         ValueReference referenceValue(Segment segment, ReferenceEntry entry, Object value, int weight) {
            return (ValueReference)(weight == 1 ? new SoftValueReference(segment.valueReferenceQueue, value, entry) : new WeightedSoftValueReference(segment.valueReferenceQueue, value, entry, weight));
         }

         Equivalence defaultEquivalence() {
            return Equivalence.identity();
         }
      },
      WEAK {
         ValueReference referenceValue(Segment segment, ReferenceEntry entry, Object value, int weight) {
            return (ValueReference)(weight == 1 ? new WeakValueReference(segment.valueReferenceQueue, value, entry) : new WeightedWeakValueReference(segment.valueReferenceQueue, value, entry, weight));
         }

         Equivalence defaultEquivalence() {
            return Equivalence.identity();
         }
      };

      private Strength() {
      }

      abstract ValueReference referenceValue(Segment segment, ReferenceEntry entry, Object value, int weight);

      abstract Equivalence defaultEquivalence();

      // $FF: synthetic method
      private static Strength[] $values() {
         return new Strength[]{STRONG, SOFT, WEAK};
      }
   }

   static enum EntryFactory {
      STRONG {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, @CheckForNull ReferenceEntry next) {
            return new StrongEntry(key, hash, next);
         }
      },
      STRONG_ACCESS {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, @CheckForNull ReferenceEntry next) {
            return new StrongAccessEntry(key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext, Object key) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext, key);
            this.copyAccessEntry(original, newEntry);
            return newEntry;
         }
      },
      STRONG_WRITE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, @CheckForNull ReferenceEntry next) {
            return new StrongWriteEntry(key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext, Object key) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext, key);
            this.copyWriteEntry(original, newEntry);
            return newEntry;
         }
      },
      STRONG_ACCESS_WRITE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, @CheckForNull ReferenceEntry next) {
            return new StrongAccessWriteEntry(key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext, Object key) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext, key);
            this.copyAccessEntry(original, newEntry);
            this.copyWriteEntry(original, newEntry);
            return newEntry;
         }
      },
      WEAK {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, @CheckForNull ReferenceEntry next) {
            return new WeakEntry(segment.keyReferenceQueue, key, hash, next);
         }
      },
      WEAK_ACCESS {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, @CheckForNull ReferenceEntry next) {
            return new WeakAccessEntry(segment.keyReferenceQueue, key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext, Object key) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext, key);
            this.copyAccessEntry(original, newEntry);
            return newEntry;
         }
      },
      WEAK_WRITE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, @CheckForNull ReferenceEntry next) {
            return new WeakWriteEntry(segment.keyReferenceQueue, key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext, Object key) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext, key);
            this.copyWriteEntry(original, newEntry);
            return newEntry;
         }
      },
      WEAK_ACCESS_WRITE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, @CheckForNull ReferenceEntry next) {
            return new WeakAccessWriteEntry(segment.keyReferenceQueue, key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext, Object key) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext, key);
            this.copyAccessEntry(original, newEntry);
            this.copyWriteEntry(original, newEntry);
            return newEntry;
         }
      };

      static final int ACCESS_MASK = 1;
      static final int WRITE_MASK = 2;
      static final int WEAK_MASK = 4;
      static final EntryFactory[] factories = new EntryFactory[]{STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE};

      private EntryFactory() {
      }

      static EntryFactory getFactory(Strength keyStrength, boolean usesAccessQueue, boolean usesWriteQueue) {
         int flags = (keyStrength == LocalCache.Strength.WEAK ? 4 : 0) | (usesAccessQueue ? 1 : 0) | (usesWriteQueue ? 2 : 0);
         return factories[flags];
      }

      abstract ReferenceEntry newEntry(Segment segment, Object key, int hash, @CheckForNull ReferenceEntry next);

      ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext, Object key) {
         return this.newEntry(segment, key, original.getHash(), newNext);
      }

      void copyAccessEntry(ReferenceEntry original, ReferenceEntry newEntry) {
         newEntry.setAccessTime(original.getAccessTime());
         LocalCache.connectAccessOrder(original.getPreviousInAccessQueue(), newEntry);
         LocalCache.connectAccessOrder(newEntry, original.getNextInAccessQueue());
         LocalCache.nullifyAccessOrder(original);
      }

      void copyWriteEntry(ReferenceEntry original, ReferenceEntry newEntry) {
         newEntry.setWriteTime(original.getWriteTime());
         LocalCache.connectWriteOrder(original.getPreviousInWriteQueue(), newEntry);
         LocalCache.connectWriteOrder(newEntry, original.getNextInWriteQueue());
         LocalCache.nullifyWriteOrder(original);
      }

      // $FF: synthetic method
      private static EntryFactory[] $values() {
         return new EntryFactory[]{STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE};
      }
   }

   private static enum NullEntry implements ReferenceEntry {
      INSTANCE;

      @CheckForNull
      public ValueReference getValueReference() {
         return null;
      }

      public void setValueReference(ValueReference valueReference) {
      }

      @CheckForNull
      public ReferenceEntry getNext() {
         return null;
      }

      public int getHash() {
         return 0;
      }

      @CheckForNull
      public Object getKey() {
         return null;
      }

      public long getAccessTime() {
         return 0L;
      }

      public void setAccessTime(long time) {
      }

      public ReferenceEntry getNextInAccessQueue() {
         return this;
      }

      public void setNextInAccessQueue(ReferenceEntry next) {
      }

      public ReferenceEntry getPreviousInAccessQueue() {
         return this;
      }

      public void setPreviousInAccessQueue(ReferenceEntry previous) {
      }

      public long getWriteTime() {
         return 0L;
      }

      public void setWriteTime(long time) {
      }

      public ReferenceEntry getNextInWriteQueue() {
         return this;
      }

      public void setNextInWriteQueue(ReferenceEntry next) {
      }

      public ReferenceEntry getPreviousInWriteQueue() {
         return this;
      }

      public void setPreviousInWriteQueue(ReferenceEntry previous) {
      }

      // $FF: synthetic method
      private static NullEntry[] $values() {
         return new NullEntry[]{INSTANCE};
      }
   }

   abstract static class AbstractReferenceEntry implements ReferenceEntry {
      public ValueReference getValueReference() {
         throw new UnsupportedOperationException();
      }

      public void setValueReference(ValueReference valueReference) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNext() {
         throw new UnsupportedOperationException();
      }

      public int getHash() {
         throw new UnsupportedOperationException();
      }

      public Object getKey() {
         throw new UnsupportedOperationException();
      }

      public long getAccessTime() {
         throw new UnsupportedOperationException();
      }

      public void setAccessTime(long time) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextInAccessQueue() {
         throw new UnsupportedOperationException();
      }

      public void setNextInAccessQueue(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousInAccessQueue() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousInAccessQueue(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }

      public long getWriteTime() {
         throw new UnsupportedOperationException();
      }

      public void setWriteTime(long time) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextInWriteQueue() {
         throw new UnsupportedOperationException();
      }

      public void setNextInWriteQueue(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousInWriteQueue() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousInWriteQueue(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }
   }

   static class StrongEntry extends AbstractReferenceEntry {
      final Object key;
      final int hash;
      @CheckForNull
      final ReferenceEntry next;
      volatile ValueReference valueReference = LocalCache.unset();

      StrongEntry(Object key, int hash, @CheckForNull ReferenceEntry next) {
         this.key = key;
         this.hash = hash;
         this.next = next;
      }

      public Object getKey() {
         return this.key;
      }

      public ValueReference getValueReference() {
         return this.valueReference;
      }

      public void setValueReference(ValueReference valueReference) {
         this.valueReference = valueReference;
      }

      public int getHash() {
         return this.hash;
      }

      public ReferenceEntry getNext() {
         return this.next;
      }
   }

   static final class StrongAccessEntry extends StrongEntry {
      volatile long accessTime = Long.MAX_VALUE;
      @Weak
      ReferenceEntry nextAccess = LocalCache.nullEntry();
      @Weak
      ReferenceEntry previousAccess = LocalCache.nullEntry();

      StrongAccessEntry(Object key, int hash, @CheckForNull ReferenceEntry next) {
         super(key, hash, next);
      }

      public long getAccessTime() {
         return this.accessTime;
      }

      public void setAccessTime(long time) {
         this.accessTime = time;
      }

      public ReferenceEntry getNextInAccessQueue() {
         return this.nextAccess;
      }

      public void setNextInAccessQueue(ReferenceEntry next) {
         this.nextAccess = next;
      }

      public ReferenceEntry getPreviousInAccessQueue() {
         return this.previousAccess;
      }

      public void setPreviousInAccessQueue(ReferenceEntry previous) {
         this.previousAccess = previous;
      }
   }

   static final class StrongWriteEntry extends StrongEntry {
      volatile long writeTime = Long.MAX_VALUE;
      @Weak
      ReferenceEntry nextWrite = LocalCache.nullEntry();
      @Weak
      ReferenceEntry previousWrite = LocalCache.nullEntry();

      StrongWriteEntry(Object key, int hash, @CheckForNull ReferenceEntry next) {
         super(key, hash, next);
      }

      public long getWriteTime() {
         return this.writeTime;
      }

      public void setWriteTime(long time) {
         this.writeTime = time;
      }

      public ReferenceEntry getNextInWriteQueue() {
         return this.nextWrite;
      }

      public void setNextInWriteQueue(ReferenceEntry next) {
         this.nextWrite = next;
      }

      public ReferenceEntry getPreviousInWriteQueue() {
         return this.previousWrite;
      }

      public void setPreviousInWriteQueue(ReferenceEntry previous) {
         this.previousWrite = previous;
      }
   }

   static final class StrongAccessWriteEntry extends StrongEntry {
      volatile long accessTime = Long.MAX_VALUE;
      @Weak
      ReferenceEntry nextAccess = LocalCache.nullEntry();
      @Weak
      ReferenceEntry previousAccess = LocalCache.nullEntry();
      volatile long writeTime = Long.MAX_VALUE;
      @Weak
      ReferenceEntry nextWrite = LocalCache.nullEntry();
      @Weak
      ReferenceEntry previousWrite = LocalCache.nullEntry();

      StrongAccessWriteEntry(Object key, int hash, @CheckForNull ReferenceEntry next) {
         super(key, hash, next);
      }

      public long getAccessTime() {
         return this.accessTime;
      }

      public void setAccessTime(long time) {
         this.accessTime = time;
      }

      public ReferenceEntry getNextInAccessQueue() {
         return this.nextAccess;
      }

      public void setNextInAccessQueue(ReferenceEntry next) {
         this.nextAccess = next;
      }

      public ReferenceEntry getPreviousInAccessQueue() {
         return this.previousAccess;
      }

      public void setPreviousInAccessQueue(ReferenceEntry previous) {
         this.previousAccess = previous;
      }

      public long getWriteTime() {
         return this.writeTime;
      }

      public void setWriteTime(long time) {
         this.writeTime = time;
      }

      public ReferenceEntry getNextInWriteQueue() {
         return this.nextWrite;
      }

      public void setNextInWriteQueue(ReferenceEntry next) {
         this.nextWrite = next;
      }

      public ReferenceEntry getPreviousInWriteQueue() {
         return this.previousWrite;
      }

      public void setPreviousInWriteQueue(ReferenceEntry previous) {
         this.previousWrite = previous;
      }
   }

   static class WeakEntry extends WeakReference implements ReferenceEntry {
      final int hash;
      @CheckForNull
      final ReferenceEntry next;
      volatile ValueReference valueReference = LocalCache.unset();

      WeakEntry(ReferenceQueue queue, Object key, int hash, @CheckForNull ReferenceEntry next) {
         super(key, queue);
         this.hash = hash;
         this.next = next;
      }

      public Object getKey() {
         return this.get();
      }

      public long getAccessTime() {
         throw new UnsupportedOperationException();
      }

      public void setAccessTime(long time) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextInAccessQueue() {
         throw new UnsupportedOperationException();
      }

      public void setNextInAccessQueue(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousInAccessQueue() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousInAccessQueue(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }

      public long getWriteTime() {
         throw new UnsupportedOperationException();
      }

      public void setWriteTime(long time) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextInWriteQueue() {
         throw new UnsupportedOperationException();
      }

      public void setNextInWriteQueue(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousInWriteQueue() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousInWriteQueue(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }

      public ValueReference getValueReference() {
         return this.valueReference;
      }

      public void setValueReference(ValueReference valueReference) {
         this.valueReference = valueReference;
      }

      public int getHash() {
         return this.hash;
      }

      public ReferenceEntry getNext() {
         return this.next;
      }
   }

   static final class WeakAccessEntry extends WeakEntry {
      volatile long accessTime = Long.MAX_VALUE;
      @Weak
      ReferenceEntry nextAccess = LocalCache.nullEntry();
      @Weak
      ReferenceEntry previousAccess = LocalCache.nullEntry();

      WeakAccessEntry(ReferenceQueue queue, Object key, int hash, @CheckForNull ReferenceEntry next) {
         super(queue, key, hash, next);
      }

      public long getAccessTime() {
         return this.accessTime;
      }

      public void setAccessTime(long time) {
         this.accessTime = time;
      }

      public ReferenceEntry getNextInAccessQueue() {
         return this.nextAccess;
      }

      public void setNextInAccessQueue(ReferenceEntry next) {
         this.nextAccess = next;
      }

      public ReferenceEntry getPreviousInAccessQueue() {
         return this.previousAccess;
      }

      public void setPreviousInAccessQueue(ReferenceEntry previous) {
         this.previousAccess = previous;
      }
   }

   static final class WeakWriteEntry extends WeakEntry {
      volatile long writeTime = Long.MAX_VALUE;
      @Weak
      ReferenceEntry nextWrite = LocalCache.nullEntry();
      @Weak
      ReferenceEntry previousWrite = LocalCache.nullEntry();

      WeakWriteEntry(ReferenceQueue queue, Object key, int hash, @CheckForNull ReferenceEntry next) {
         super(queue, key, hash, next);
      }

      public long getWriteTime() {
         return this.writeTime;
      }

      public void setWriteTime(long time) {
         this.writeTime = time;
      }

      public ReferenceEntry getNextInWriteQueue() {
         return this.nextWrite;
      }

      public void setNextInWriteQueue(ReferenceEntry next) {
         this.nextWrite = next;
      }

      public ReferenceEntry getPreviousInWriteQueue() {
         return this.previousWrite;
      }

      public void setPreviousInWriteQueue(ReferenceEntry previous) {
         this.previousWrite = previous;
      }
   }

   static final class WeakAccessWriteEntry extends WeakEntry {
      volatile long accessTime = Long.MAX_VALUE;
      @Weak
      ReferenceEntry nextAccess = LocalCache.nullEntry();
      @Weak
      ReferenceEntry previousAccess = LocalCache.nullEntry();
      volatile long writeTime = Long.MAX_VALUE;
      @Weak
      ReferenceEntry nextWrite = LocalCache.nullEntry();
      @Weak
      ReferenceEntry previousWrite = LocalCache.nullEntry();

      WeakAccessWriteEntry(ReferenceQueue queue, Object key, int hash, @CheckForNull ReferenceEntry next) {
         super(queue, key, hash, next);
      }

      public long getAccessTime() {
         return this.accessTime;
      }

      public void setAccessTime(long time) {
         this.accessTime = time;
      }

      public ReferenceEntry getNextInAccessQueue() {
         return this.nextAccess;
      }

      public void setNextInAccessQueue(ReferenceEntry next) {
         this.nextAccess = next;
      }

      public ReferenceEntry getPreviousInAccessQueue() {
         return this.previousAccess;
      }

      public void setPreviousInAccessQueue(ReferenceEntry previous) {
         this.previousAccess = previous;
      }

      public long getWriteTime() {
         return this.writeTime;
      }

      public void setWriteTime(long time) {
         this.writeTime = time;
      }

      public ReferenceEntry getNextInWriteQueue() {
         return this.nextWrite;
      }

      public void setNextInWriteQueue(ReferenceEntry next) {
         this.nextWrite = next;
      }

      public ReferenceEntry getPreviousInWriteQueue() {
         return this.previousWrite;
      }

      public void setPreviousInWriteQueue(ReferenceEntry previous) {
         this.previousWrite = previous;
      }
   }

   static class WeakValueReference extends WeakReference implements ValueReference {
      final ReferenceEntry entry;

      WeakValueReference(ReferenceQueue queue, Object referent, ReferenceEntry entry) {
         super(referent, queue);
         this.entry = entry;
      }

      public int getWeight() {
         return 1;
      }

      public ReferenceEntry getEntry() {
         return this.entry;
      }

      public void notifyNewValue(Object newValue) {
      }

      public ValueReference copyFor(ReferenceQueue queue, Object value, ReferenceEntry entry) {
         return new WeakValueReference(queue, value, entry);
      }

      public boolean isLoading() {
         return false;
      }

      public boolean isActive() {
         return true;
      }

      public Object waitForValue() {
         return this.get();
      }
   }

   static class SoftValueReference extends SoftReference implements ValueReference {
      final ReferenceEntry entry;

      SoftValueReference(ReferenceQueue queue, Object referent, ReferenceEntry entry) {
         super(referent, queue);
         this.entry = entry;
      }

      public int getWeight() {
         return 1;
      }

      public ReferenceEntry getEntry() {
         return this.entry;
      }

      public void notifyNewValue(Object newValue) {
      }

      public ValueReference copyFor(ReferenceQueue queue, Object value, ReferenceEntry entry) {
         return new SoftValueReference(queue, value, entry);
      }

      public boolean isLoading() {
         return false;
      }

      public boolean isActive() {
         return true;
      }

      public Object waitForValue() {
         return this.get();
      }
   }

   static class StrongValueReference implements ValueReference {
      final Object referent;

      StrongValueReference(Object referent) {
         this.referent = referent;
      }

      public Object get() {
         return this.referent;
      }

      public int getWeight() {
         return 1;
      }

      public ReferenceEntry getEntry() {
         return null;
      }

      public ValueReference copyFor(ReferenceQueue queue, Object value, ReferenceEntry entry) {
         return this;
      }

      public boolean isLoading() {
         return false;
      }

      public boolean isActive() {
         return true;
      }

      public Object waitForValue() {
         return this.get();
      }

      public void notifyNewValue(Object newValue) {
      }
   }

   static final class WeightedWeakValueReference extends WeakValueReference {
      final int weight;

      WeightedWeakValueReference(ReferenceQueue queue, Object referent, ReferenceEntry entry, int weight) {
         super(queue, referent, entry);
         this.weight = weight;
      }

      public int getWeight() {
         return this.weight;
      }

      public ValueReference copyFor(ReferenceQueue queue, Object value, ReferenceEntry entry) {
         return new WeightedWeakValueReference(queue, value, entry, this.weight);
      }
   }

   static final class WeightedSoftValueReference extends SoftValueReference {
      final int weight;

      WeightedSoftValueReference(ReferenceQueue queue, Object referent, ReferenceEntry entry, int weight) {
         super(queue, referent, entry);
         this.weight = weight;
      }

      public int getWeight() {
         return this.weight;
      }

      public ValueReference copyFor(ReferenceQueue queue, Object value, ReferenceEntry entry) {
         return new WeightedSoftValueReference(queue, value, entry, this.weight);
      }
   }

   static final class WeightedStrongValueReference extends StrongValueReference {
      final int weight;

      WeightedStrongValueReference(Object referent, int weight) {
         super(referent);
         this.weight = weight;
      }

      public int getWeight() {
         return this.weight;
      }
   }

   static class Segment extends ReentrantLock {
      @Weak
      final LocalCache map;
      volatile int count;
      @GuardedBy("this")
      long totalWeight;
      int modCount;
      int threshold;
      @CheckForNull
      volatile AtomicReferenceArray table;
      final long maxSegmentWeight;
      @CheckForNull
      final ReferenceQueue keyReferenceQueue;
      @CheckForNull
      final ReferenceQueue valueReferenceQueue;
      final Queue recencyQueue;
      final AtomicInteger readCount = new AtomicInteger();
      @GuardedBy("this")
      final Queue writeQueue;
      @GuardedBy("this")
      final Queue accessQueue;
      final AbstractCache.StatsCounter statsCounter;

      Segment(LocalCache map, int initialCapacity, long maxSegmentWeight, AbstractCache.StatsCounter statsCounter) {
         this.map = map;
         this.maxSegmentWeight = maxSegmentWeight;
         this.statsCounter = (AbstractCache.StatsCounter)Preconditions.checkNotNull(statsCounter);
         this.initTable(this.newEntryArray(initialCapacity));
         this.keyReferenceQueue = map.usesKeyReferences() ? new ReferenceQueue() : null;
         this.valueReferenceQueue = map.usesValueReferences() ? new ReferenceQueue() : null;
         this.recencyQueue = (Queue)(map.usesAccessQueue() ? new ConcurrentLinkedQueue() : LocalCache.discardingQueue());
         this.writeQueue = (Queue)(map.usesWriteQueue() ? new WriteQueue() : LocalCache.discardingQueue());
         this.accessQueue = (Queue)(map.usesAccessQueue() ? new AccessQueue() : LocalCache.discardingQueue());
      }

      AtomicReferenceArray newEntryArray(int size) {
         return new AtomicReferenceArray(size);
      }

      void initTable(AtomicReferenceArray newTable) {
         this.threshold = newTable.length() * 3 / 4;
         if (!this.map.customWeigher() && (long)this.threshold == this.maxSegmentWeight) {
            ++this.threshold;
         }

         this.table = newTable;
      }

      @GuardedBy("this")
      ReferenceEntry newEntry(Object key, int hash, @CheckForNull ReferenceEntry next) {
         return this.map.entryFactory.newEntry(this, Preconditions.checkNotNull(key), hash, next);
      }

      @CheckForNull
      @GuardedBy("this")
      ReferenceEntry copyEntry(ReferenceEntry original, ReferenceEntry newNext) {
         K key = (K)original.getKey();
         if (key == null) {
            return null;
         } else {
            ValueReference<K, V> valueReference = original.getValueReference();
            V value = (V)valueReference.get();
            if (value == null && valueReference.isActive()) {
               return null;
            } else {
               ReferenceEntry<K, V> newEntry = this.map.entryFactory.copyEntry(this, original, newNext, key);
               newEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, value, newEntry));
               return newEntry;
            }
         }
      }

      @GuardedBy("this")
      void setValue(ReferenceEntry entry, Object key, Object value, long now) {
         ValueReference<K, V> previous = entry.getValueReference();
         int weight = this.map.weigher.weigh(key, value);
         Preconditions.checkState(weight >= 0, "Weights must be non-negative");
         ValueReference<K, V> valueReference = this.map.valueStrength.referenceValue(this, entry, value, weight);
         entry.setValueReference(valueReference);
         this.recordWrite(entry, weight, now);
         previous.notifyNewValue(value);
      }

      @CanIgnoreReturnValue
      Object get(Object key, int hash, CacheLoader loader) throws ExecutionException {
         Preconditions.checkNotNull(key);
         Preconditions.checkNotNull(loader);

         try {
            if (this.count != 0) {
               ReferenceEntry<K, V> e = this.getEntry(key, hash);
               if (e != null) {
                  long now = this.map.ticker.read();
                  V value = (V)this.getLiveValue(e, now);
                  if (value != null) {
                     this.recordRead(e, now);
                     this.statsCounter.recordHits(1);
                     Object var17 = this.scheduleRefresh(e, key, hash, value, now, loader);
                     return var17;
                  }

                  ValueReference<K, V> valueReference = e.getValueReference();
                  if (valueReference.isLoading()) {
                     Object var9 = this.waitForLoadingValue(e, key, valueReference);
                     return var9;
                  }
               }
            }

            Object ee = this.lockedGetOrLoad(key, hash, loader);
            return ee;
         } catch (ExecutionException ee) {
            Throwable cause = ee.getCause();
            if (cause instanceof Error) {
               throw new ExecutionError((Error)cause);
            } else if (cause instanceof RuntimeException) {
               throw new UncheckedExecutionException(cause);
            } else {
               throw ee;
            }
         } finally {
            this.postReadCleanup();
         }
      }

      @CheckForNull
      Object get(Object key, int hash) {
         try {
            if (this.count != 0) {
               long now = this.map.ticker.read();
               ReferenceEntry<K, V> e = this.getLiveEntry(key, hash, now);
               if (e == null) {
                  Object var12 = null;
                  return var12;
               }

               V value = (V)e.getValueReference().get();
               if (value != null) {
                  this.recordRead(e, now);
                  Object var7 = this.scheduleRefresh(e, e.getKey(), hash, value, now, this.map.defaultLoader);
                  return var7;
               }

               this.tryDrainReferenceQueues();
            }

            Object var11 = null;
            return var11;
         } finally {
            this.postReadCleanup();
         }
      }

      Object lockedGetOrLoad(Object key, int hash, CacheLoader loader) throws ExecutionException {
         ValueReference<K, V> valueReference = null;
         LoadingValueReference<K, V> loadingValueReference = null;
         boolean createNewEntry = true;
         this.lock();

         ReferenceEntry<K, V> e;
         try {
            long now = this.map.ticker.read();
            this.preWriteCleanup(now);
            int newCount = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  valueReference = e.getValueReference();
                  if (valueReference.isLoading()) {
                     createNewEntry = false;
                  } else {
                     V value = (V)valueReference.get();
                     if (value == null) {
                        this.enqueueNotification(entryKey, hash, value, valueReference.getWeight(), RemovalCause.COLLECTED);
                     } else {
                        if (!this.map.isExpired(e, now)) {
                           this.recordLockedRead(e, now);
                           this.statsCounter.recordHits(1);
                           Object var16 = value;
                           return var16;
                        }

                        this.enqueueNotification(entryKey, hash, value, valueReference.getWeight(), RemovalCause.EXPIRED);
                     }

                     this.writeQueue.remove(e);
                     this.accessQueue.remove(e);
                     this.count = newCount;
                  }
                  break;
               }
            }

            if (createNewEntry) {
               loadingValueReference = new LoadingValueReference();
               if (e == null) {
                  e = this.newEntry(key, hash, first);
                  e.setValueReference(loadingValueReference);
                  table.set(index, e);
               } else {
                  e.setValueReference(loadingValueReference);
               }
            }
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }

         if (createNewEntry) {
            Object var9;
            try {
               synchronized(e) {
                  var9 = this.loadSync(key, hash, loadingValueReference, loader);
               }
            } finally {
               this.statsCounter.recordMisses(1);
            }

            return var9;
         } else {
            return this.waitForLoadingValue(e, key, valueReference);
         }
      }

      Object waitForLoadingValue(ReferenceEntry e, Object key, ValueReference valueReference) throws ExecutionException {
         if (!valueReference.isLoading()) {
            throw new AssertionError();
         } else {
            Preconditions.checkState(!Thread.holdsLock(e), "Recursive load of: %s", key);

            Object var7;
            try {
               V value = (V)valueReference.waitForValue();
               if (value == null) {
                  throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + key + ".");
               }

               long now = this.map.ticker.read();
               this.recordRead(e, now);
               var7 = value;
            } finally {
               this.statsCounter.recordMisses(1);
            }

            return var7;
         }
      }

      @CheckForNull
      Object compute(Object key, int hash, BiFunction function) {
         ValueReference<K, V> valueReference = null;
         ComputingValueReference<K, V> computingValueReference = null;
         boolean createNewEntry = true;
         this.lock();

         K entryKey;
         try {
            long now = this.map.ticker.read();
            this.preWriteCleanup(now);
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            ReferenceEntry<K, V> e;
            for(e = first; e != null; e = e.getNext()) {
               entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  valueReference = e.getValueReference();
                  if (this.map.isExpired(e, now)) {
                     this.enqueueNotification(entryKey, hash, valueReference.get(), valueReference.getWeight(), RemovalCause.EXPIRED);
                  }

                  this.writeQueue.remove(e);
                  this.accessQueue.remove(e);
                  createNewEntry = false;
                  break;
               }
            }

            computingValueReference = new ComputingValueReference(valueReference);
            if (e == null) {
               createNewEntry = true;
               e = this.newEntry(key, hash, first);
               e.setValueReference(computingValueReference);
               table.set(index, e);
            } else {
               e.setValueReference(computingValueReference);
            }

            V newValue = (V)computingValueReference.compute(key, function);
            if (newValue == null) {
               if (!createNewEntry && !valueReference.isLoading()) {
                  this.removeEntry(e, hash, RemovalCause.EXPLICIT);
                  entryKey = (K)null;
                  return entryKey;
               }

               this.removeLoadingValue(key, hash, computingValueReference);
               entryKey = (K)null;
               return entryKey;
            }

            if (valueReference == null || newValue != valueReference.get()) {
               try {
                  entryKey = (K)this.getAndRecordStats(key, hash, computingValueReference, Futures.immediateFuture(newValue));
                  return entryKey;
               } catch (ExecutionException var18) {
                  throw new AssertionError("impossible; Futures.immediateFuture can't throw");
               }
            }

            computingValueReference.set(newValue);
            e.setValueReference(valueReference);
            this.recordWrite(e, 0, now);
            entryKey = (K)newValue;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }

         return entryKey;
      }

      Object loadSync(Object key, int hash, LoadingValueReference loadingValueReference, CacheLoader loader) throws ExecutionException {
         ListenableFuture<V> loadingFuture = loadingValueReference.loadFuture(key, loader);
         return this.getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
      }

      ListenableFuture loadAsync(final Object key, final int hash, final LoadingValueReference loadingValueReference, CacheLoader loader) {
         ListenableFuture<V> loadingFuture = loadingValueReference.loadFuture(key, loader);
         loadingFuture.addListener(() -> {
            try {
               this.getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
            } catch (Throwable t) {
               LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", t);
               loadingValueReference.setException(t);
            }

         }, MoreExecutors.directExecutor());
         return loadingFuture;
      }

      @CanIgnoreReturnValue
      Object getAndRecordStats(Object key, int hash, LoadingValueReference loadingValueReference, ListenableFuture newValue) throws ExecutionException {
         V value = (V)null;

         Object var6;
         try {
            value = (V)Uninterruptibles.getUninterruptibly(newValue);
            if (value == null) {
               throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + key + ".");
            }

            this.statsCounter.recordLoadSuccess(loadingValueReference.elapsedNanos());
            this.storeLoadedValue(key, hash, loadingValueReference, value);
            var6 = value;
         } finally {
            if (value == null) {
               this.statsCounter.recordLoadException(loadingValueReference.elapsedNanos());
               this.removeLoadingValue(key, hash, loadingValueReference);
            }

         }

         return var6;
      }

      Object scheduleRefresh(ReferenceEntry entry, Object key, int hash, Object oldValue, long now, CacheLoader loader) {
         if (this.map.refreshes() && now - entry.getWriteTime() > this.map.refreshNanos && !entry.getValueReference().isLoading()) {
            V newValue = (V)this.refresh(key, hash, loader, true);
            if (newValue != null) {
               return newValue;
            }
         }

         return oldValue;
      }

      @CheckForNull
      @CanIgnoreReturnValue
      Object refresh(Object key, int hash, CacheLoader loader, boolean checkTime) {
         LoadingValueReference<K, V> loadingValueReference = this.insertLoadingValueReference(key, hash, checkTime);
         if (loadingValueReference == null) {
            return null;
         } else {
            ListenableFuture<V> result = this.loadAsync(key, hash, loadingValueReference, loader);
            if (result.isDone()) {
               try {
                  return Uninterruptibles.getUninterruptibly(result);
               } catch (Throwable var8) {
               }
            }

            return null;
         }
      }

      @CheckForNull
      LoadingValueReference insertLoadingValueReference(final Object key, final int hash, boolean checkTime) {
         ReferenceEntry<K, V> e = null;
         this.lock();

         try {
            long now = this.map.ticker.read();
            this.preWriteCleanup(now);
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry var17 = first; var17 != null; var17 = var17.getNext()) {
               K entryKey = (K)var17.getKey();
               if (var17.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> valueReference = var17.getValueReference();
                  if (!valueReference.isLoading() && (!checkTime || now - var17.getWriteTime() >= this.map.refreshNanos)) {
                     ++this.modCount;
                     LoadingValueReference<K, V> loadingValueReference = new LoadingValueReference(valueReference);
                     var17.setValueReference(loadingValueReference);
                     LoadingValueReference var13 = loadingValueReference;
                     return var13;
                  }

                  Object var12 = null;
                  return (LoadingValueReference)var12;
               }
            }

            ++this.modCount;
            LoadingValueReference<K, V> loadingValueReference = new LoadingValueReference();
            e = this.newEntry(key, hash, first);
            e.setValueReference(loadingValueReference);
            table.set(index, e);
            LoadingValueReference var20 = loadingValueReference;
            return var20;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      void tryDrainReferenceQueues() {
         if (this.tryLock()) {
            try {
               this.drainReferenceQueues();
            } finally {
               this.unlock();
            }
         }

      }

      @GuardedBy("this")
      void drainReferenceQueues() {
         if (this.map.usesKeyReferences()) {
            this.drainKeyReferenceQueue();
         }

         if (this.map.usesValueReferences()) {
            this.drainValueReferenceQueue();
         }

      }

      @GuardedBy("this")
      void drainKeyReferenceQueue() {
         int i = 0;

         Reference<? extends K> ref;
         while((ref = this.keyReferenceQueue.poll()) != null) {
            ReferenceEntry<K, V> entry = (ReferenceEntry)ref;
            this.map.reclaimKey(entry);
            ++i;
            if (i == 16) {
               break;
            }
         }

      }

      @GuardedBy("this")
      void drainValueReferenceQueue() {
         int i = 0;

         Reference<? extends V> ref;
         while((ref = this.valueReferenceQueue.poll()) != null) {
            ValueReference<K, V> valueReference = (ValueReference)ref;
            this.map.reclaimValue(valueReference);
            ++i;
            if (i == 16) {
               break;
            }
         }

      }

      void clearReferenceQueues() {
         if (this.map.usesKeyReferences()) {
            this.clearKeyReferenceQueue();
         }

         if (this.map.usesValueReferences()) {
            this.clearValueReferenceQueue();
         }

      }

      void clearKeyReferenceQueue() {
         while(this.keyReferenceQueue.poll() != null) {
         }

      }

      void clearValueReferenceQueue() {
         while(this.valueReferenceQueue.poll() != null) {
         }

      }

      void recordRead(ReferenceEntry entry, long now) {
         if (this.map.recordsAccess()) {
            entry.setAccessTime(now);
         }

         this.recencyQueue.add(entry);
      }

      @GuardedBy("this")
      void recordLockedRead(ReferenceEntry entry, long now) {
         if (this.map.recordsAccess()) {
            entry.setAccessTime(now);
         }

         this.accessQueue.add(entry);
      }

      @GuardedBy("this")
      void recordWrite(ReferenceEntry entry, int weight, long now) {
         this.drainRecencyQueue();
         this.totalWeight += (long)weight;
         if (this.map.recordsAccess()) {
            entry.setAccessTime(now);
         }

         if (this.map.recordsWrite()) {
            entry.setWriteTime(now);
         }

         this.accessQueue.add(entry);
         this.writeQueue.add(entry);
      }

      @GuardedBy("this")
      void drainRecencyQueue() {
         ReferenceEntry<K, V> e;
         while((e = (ReferenceEntry)this.recencyQueue.poll()) != null) {
            if (this.accessQueue.contains(e)) {
               this.accessQueue.add(e);
            }
         }

      }

      void tryExpireEntries(long now) {
         if (this.tryLock()) {
            try {
               this.expireEntries(now);
            } finally {
               this.unlock();
            }
         }

      }

      @GuardedBy("this")
      void expireEntries(long now) {
         this.drainRecencyQueue();

         ReferenceEntry<K, V> e;
         while((e = (ReferenceEntry)this.writeQueue.peek()) != null && this.map.isExpired(e, now)) {
            if (!this.removeEntry(e, e.getHash(), RemovalCause.EXPIRED)) {
               throw new AssertionError();
            }
         }

         while((e = (ReferenceEntry)this.accessQueue.peek()) != null && this.map.isExpired(e, now)) {
            if (!this.removeEntry(e, e.getHash(), RemovalCause.EXPIRED)) {
               throw new AssertionError();
            }
         }

      }

      @GuardedBy("this")
      void enqueueNotification(@CheckForNull Object key, int hash, @CheckForNull Object value, int weight, RemovalCause cause) {
         this.totalWeight -= (long)weight;
         if (cause.wasEvicted()) {
            this.statsCounter.recordEviction();
         }

         if (this.map.removalNotificationQueue != LocalCache.DISCARDING_QUEUE) {
            RemovalNotification<K, V> notification = RemovalNotification.create(key, value, cause);
            this.map.removalNotificationQueue.offer(notification);
         }

      }

      @GuardedBy("this")
      void evictEntries(ReferenceEntry newest) {
         if (this.map.evictsBySize()) {
            this.drainRecencyQueue();
            if ((long)newest.getValueReference().getWeight() > this.maxSegmentWeight && !this.removeEntry(newest, newest.getHash(), RemovalCause.SIZE)) {
               throw new AssertionError();
            } else {
               while(this.totalWeight > this.maxSegmentWeight) {
                  ReferenceEntry<K, V> e = this.getNextEvictable();
                  if (!this.removeEntry(e, e.getHash(), RemovalCause.SIZE)) {
                     throw new AssertionError();
                  }
               }

            }
         }
      }

      @GuardedBy("this")
      ReferenceEntry getNextEvictable() {
         for(ReferenceEntry e : this.accessQueue) {
            int weight = e.getValueReference().getWeight();
            if (weight > 0) {
               return e;
            }
         }

         throw new AssertionError();
      }

      ReferenceEntry getFirst(int hash) {
         AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
         return (ReferenceEntry)table.get(hash & table.length() - 1);
      }

      @CheckForNull
      ReferenceEntry getEntry(Object key, int hash) {
         for(ReferenceEntry<K, V> e = this.getFirst(hash); e != null; e = e.getNext()) {
            if (e.getHash() == hash) {
               K entryKey = (K)e.getKey();
               if (entryKey == null) {
                  this.tryDrainReferenceQueues();
               } else if (this.map.keyEquivalence.equivalent(key, entryKey)) {
                  return e;
               }
            }
         }

         return null;
      }

      @CheckForNull
      ReferenceEntry getLiveEntry(Object key, int hash, long now) {
         ReferenceEntry<K, V> e = this.getEntry(key, hash);
         if (e == null) {
            return null;
         } else if (this.map.isExpired(e, now)) {
            this.tryExpireEntries(now);
            return null;
         } else {
            return e;
         }
      }

      Object getLiveValue(ReferenceEntry entry, long now) {
         if (entry.getKey() == null) {
            this.tryDrainReferenceQueues();
            return null;
         } else {
            V value = (V)entry.getValueReference().get();
            if (value == null) {
               this.tryDrainReferenceQueues();
               return null;
            } else if (this.map.isExpired(entry, now)) {
               this.tryExpireEntries(now);
               return null;
            } else {
               return value;
            }
         }
      }

      boolean containsKey(Object key, int hash) {
         boolean var6;
         try {
            if (this.count == 0) {
               boolean var10 = false;
               return var10;
            }

            long now = this.map.ticker.read();
            ReferenceEntry<K, V> e = this.getLiveEntry(key, hash, now);
            if (e != null) {
               var6 = e.getValueReference().get() != null;
               return var6;
            }

            var6 = false;
         } finally {
            this.postReadCleanup();
         }

         return var6;
      }

      @VisibleForTesting
      boolean containsValue(Object value) {
         try {
            if (this.count != 0) {
               long now = this.map.ticker.read();
               AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
               int length = table.length();

               for(int i = 0; i < length; ++i) {
                  for(ReferenceEntry<K, V> e = (ReferenceEntry)table.get(i); e != null; e = e.getNext()) {
                     V entryValue = (V)this.getLiveValue(e, now);
                     if (entryValue != null && this.map.valueEquivalence.equivalent(value, entryValue)) {
                        boolean var9 = true;
                        return var9;
                     }
                  }
               }
            }

            boolean var13 = false;
            return var13;
         } finally {
            this.postReadCleanup();
         }
      }

      @CheckForNull
      @CanIgnoreReturnValue
      Object put(Object key, int hash, Object value, boolean onlyIfAbsent) {
         this.lock();

         try {
            long now = this.map.ticker.read();
            this.preWriteCleanup(now);
            int newCount = this.count + 1;
            if (newCount > this.threshold) {
               this.expand();
               newCount = this.count + 1;
            }

            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> valueReference = e.getValueReference();
                  V entryValue = (V)valueReference.get();
                  if (entryValue != null) {
                     if (onlyIfAbsent) {
                        this.recordLockedRead(e, now);
                        Object var25 = entryValue;
                        return var25;
                     }

                     ++this.modCount;
                     this.enqueueNotification(key, hash, entryValue, valueReference.getWeight(), RemovalCause.REPLACED);
                     this.setValue(e, key, value, now);
                     this.evictEntries(e);
                     Object var24 = entryValue;
                     return var24;
                  }

                  ++this.modCount;
                  if (valueReference.isActive()) {
                     this.enqueueNotification(key, hash, entryValue, valueReference.getWeight(), RemovalCause.COLLECTED);
                     this.setValue(e, key, value, now);
                     newCount = this.count;
                  } else {
                     this.setValue(e, key, value, now);
                     newCount = this.count + 1;
                  }

                  this.count = newCount;
                  this.evictEntries(e);
                  Object var15 = null;
                  return var15;
               }
            }

            ++this.modCount;
            ReferenceEntry<K, V> newEntry = this.newEntry(key, hash, first);
            this.setValue(newEntry, key, value, now);
            table.set(index, newEntry);
            newCount = this.count + 1;
            this.count = newCount;
            this.evictEntries(newEntry);
            Object var23 = null;
            return var23;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      @GuardedBy("this")
      void expand() {
         AtomicReferenceArray<ReferenceEntry<K, V>> oldTable = this.table;
         int oldCapacity = oldTable.length();
         if (oldCapacity < 1073741824) {
            int newCount = this.count;
            AtomicReferenceArray<ReferenceEntry<K, V>> newTable = this.newEntryArray(oldCapacity << 1);
            this.threshold = newTable.length() * 3 / 4;
            int newMask = newTable.length() - 1;

            for(int oldIndex = 0; oldIndex < oldCapacity; ++oldIndex) {
               ReferenceEntry<K, V> head = (ReferenceEntry)oldTable.get(oldIndex);
               if (head != null) {
                  ReferenceEntry<K, V> next = head.getNext();
                  int headIndex = head.getHash() & newMask;
                  if (next == null) {
                     newTable.set(headIndex, head);
                  } else {
                     ReferenceEntry<K, V> tail = head;
                     int tailIndex = headIndex;

                     for(ReferenceEntry<K, V> e = next; e != null; e = e.getNext()) {
                        int newIndex = e.getHash() & newMask;
                        if (newIndex != tailIndex) {
                           tailIndex = newIndex;
                           tail = e;
                        }
                     }

                     newTable.set(tailIndex, tail);

                     for(ReferenceEntry<K, V> e = head; e != tail; e = e.getNext()) {
                        int newIndex = e.getHash() & newMask;
                        ReferenceEntry<K, V> newNext = (ReferenceEntry)newTable.get(newIndex);
                        ReferenceEntry<K, V> newFirst = this.copyEntry(e, newNext);
                        if (newFirst != null) {
                           newTable.set(newIndex, newFirst);
                        } else {
                           this.removeCollectedEntry(e);
                           --newCount;
                        }
                     }
                  }
               }
            }

            this.table = newTable;
            this.count = newCount;
         }
      }

      boolean replace(Object key, int hash, Object oldValue, Object newValue) {
         this.lock();

         try {
            long now = this.map.ticker.read();
            this.preWriteCleanup(now);
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> valueReference = e.getValueReference();
                  V entryValue = (V)valueReference.get();
                  if (entryValue != null) {
                     if (this.map.valueEquivalence.equivalent(oldValue, entryValue)) {
                        ++this.modCount;
                        this.enqueueNotification(key, hash, entryValue, valueReference.getWeight(), RemovalCause.REPLACED);
                        this.setValue(e, key, newValue, now);
                        this.evictEntries(e);
                        boolean var23 = true;
                        return var23;
                     }

                     this.recordLockedRead(e, now);
                     boolean var22 = false;
                     return var22;
                  }

                  if (valueReference.isActive()) {
                     int newCount = this.count - 1;
                     ++this.modCount;
                     ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, entryValue, valueReference, RemovalCause.COLLECTED);
                     newCount = this.count - 1;
                     table.set(index, newFirst);
                     this.count = newCount;
                  }

                  boolean var21 = false;
                  return var21;
               }
            }

            boolean var19 = false;
            return var19;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      @CheckForNull
      Object replace(Object key, int hash, Object newValue) {
         this.lock();

         try {
            long now = this.map.ticker.read();
            this.preWriteCleanup(now);
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> valueReference = e.getValueReference();
                  V entryValue = (V)valueReference.get();
                  if (entryValue != null) {
                     ++this.modCount;
                     this.enqueueNotification(key, hash, entryValue, valueReference.getWeight(), RemovalCause.REPLACED);
                     this.setValue(e, key, newValue, now);
                     this.evictEntries(e);
                     Object var21 = entryValue;
                     return var21;
                  }

                  if (valueReference.isActive()) {
                     int newCount = this.count - 1;
                     ++this.modCount;
                     ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, entryValue, valueReference, RemovalCause.COLLECTED);
                     newCount = this.count - 1;
                     table.set(index, newFirst);
                     this.count = newCount;
                  }

                  Object var20 = null;
                  return var20;
               }
            }

            Object var18 = null;
            return var18;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      @CheckForNull
      Object remove(Object key, int hash) {
         this.lock();

         try {
            long now = this.map.ticker.read();
            this.preWriteCleanup(now);
            int newCount = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> valueReference = e.getValueReference();
                  V entryValue = (V)valueReference.get();
                  RemovalCause cause;
                  if (entryValue != null) {
                     cause = RemovalCause.EXPLICIT;
                  } else {
                     if (!valueReference.isActive()) {
                        Object var21 = null;
                        return var21;
                     }

                     cause = RemovalCause.COLLECTED;
                  }

                  ++this.modCount;
                  ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, entryValue, valueReference, cause);
                  newCount = this.count - 1;
                  table.set(index, newFirst);
                  this.count = newCount;
                  Object var15 = entryValue;
                  return var15;
               }
            }

            Object var20 = null;
            return var20;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      boolean remove(Object key, int hash, Object value) {
         this.lock();

         try {
            long now = this.map.ticker.read();
            this.preWriteCleanup(now);
            int newCount = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> valueReference = e.getValueReference();
                  V entryValue = (V)valueReference.get();
                  RemovalCause cause;
                  if (this.map.valueEquivalence.equivalent(value, entryValue)) {
                     cause = RemovalCause.EXPLICIT;
                  } else {
                     if (entryValue != null || !valueReference.isActive()) {
                        boolean var22 = false;
                        return var22;
                     }

                     cause = RemovalCause.COLLECTED;
                  }

                  ++this.modCount;
                  ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, entryValue, valueReference, cause);
                  newCount = this.count - 1;
                  table.set(index, newFirst);
                  this.count = newCount;
                  boolean var16 = cause == RemovalCause.EXPLICIT;
                  return var16;
               }
            }

            boolean var21 = false;
            return var21;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      @CanIgnoreReturnValue
      boolean storeLoadedValue(Object key, int hash, LoadingValueReference oldValueReference, Object newValue) {
         this.lock();

         try {
            long now = this.map.ticker.read();
            this.preWriteCleanup(now);
            int newCount = this.count + 1;
            if (newCount > this.threshold) {
               this.expand();
               newCount = this.count + 1;
            }

            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> valueReference = e.getValueReference();
                  V entryValue = (V)valueReference.get();
                  if (oldValueReference != valueReference && (entryValue != null || valueReference == LocalCache.UNSET)) {
                     this.enqueueNotification(key, hash, newValue, 0, RemovalCause.REPLACED);
                     boolean var22 = false;
                     return var22;
                  }

                  ++this.modCount;
                  if (oldValueReference.isActive()) {
                     RemovalCause cause = entryValue == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED;
                     this.enqueueNotification(key, hash, entryValue, oldValueReference.getWeight(), cause);
                     --newCount;
                  }

                  this.setValue(e, key, newValue, now);
                  this.count = newCount;
                  this.evictEntries(e);
                  boolean var21 = true;
                  return var21;
               }
            }

            ++this.modCount;
            ReferenceEntry<K, V> newEntry = this.newEntry(key, hash, first);
            this.setValue(newEntry, key, newValue, now);
            table.set(index, newEntry);
            this.count = newCount;
            this.evictEntries(newEntry);
            boolean var20 = true;
            return var20;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      void clear() {
         if (this.count != 0) {
            this.lock();

            try {
               long now = this.map.ticker.read();
               this.preWriteCleanup(now);
               AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;

               for(int i = 0; i < table.length(); ++i) {
                  for(ReferenceEntry<K, V> e = (ReferenceEntry)table.get(i); e != null; e = e.getNext()) {
                     if (e.getValueReference().isActive()) {
                        K key = (K)e.getKey();
                        V value = (V)e.getValueReference().get();
                        RemovalCause cause = key != null && value != null ? RemovalCause.EXPLICIT : RemovalCause.COLLECTED;
                        this.enqueueNotification(key, e.getHash(), value, e.getValueReference().getWeight(), cause);
                     }
                  }
               }

               for(int i = 0; i < table.length(); ++i) {
                  table.set(i, (Object)null);
               }

               this.clearReferenceQueues();
               this.writeQueue.clear();
               this.accessQueue.clear();
               this.readCount.set(0);
               ++this.modCount;
               this.count = 0;
            } finally {
               this.unlock();
               this.postWriteCleanup();
            }
         }

      }

      @CheckForNull
      @GuardedBy("this")
      ReferenceEntry removeValueFromChain(ReferenceEntry first, ReferenceEntry entry, @CheckForNull Object key, int hash, Object value, ValueReference valueReference, RemovalCause cause) {
         this.enqueueNotification(key, hash, value, valueReference.getWeight(), cause);
         this.writeQueue.remove(entry);
         this.accessQueue.remove(entry);
         if (valueReference.isLoading()) {
            valueReference.notifyNewValue((Object)null);
            return first;
         } else {
            return this.removeEntryFromChain(first, entry);
         }
      }

      @CheckForNull
      @GuardedBy("this")
      ReferenceEntry removeEntryFromChain(ReferenceEntry first, ReferenceEntry entry) {
         int newCount = this.count;
         ReferenceEntry<K, V> newFirst = entry.getNext();

         for(ReferenceEntry<K, V> e = first; e != entry; e = e.getNext()) {
            ReferenceEntry<K, V> next = this.copyEntry(e, newFirst);
            if (next != null) {
               newFirst = next;
            } else {
               this.removeCollectedEntry(e);
               --newCount;
            }
         }

         this.count = newCount;
         return newFirst;
      }

      @GuardedBy("this")
      void removeCollectedEntry(ReferenceEntry entry) {
         this.enqueueNotification(entry.getKey(), entry.getHash(), entry.getValueReference().get(), entry.getValueReference().getWeight(), RemovalCause.COLLECTED);
         this.writeQueue.remove(entry);
         this.accessQueue.remove(entry);
      }

      @CanIgnoreReturnValue
      boolean reclaimKey(ReferenceEntry entry, int hash) {
         this.lock();

         try {
            int newCount = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               if (e == entry) {
                  ++this.modCount;
                  ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference().get(), e.getValueReference(), RemovalCause.COLLECTED);
                  newCount = this.count - 1;
                  table.set(index, newFirst);
                  this.count = newCount;
                  boolean var9 = true;
                  return var9;
               }
            }

            boolean var14 = false;
            return var14;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      @CanIgnoreReturnValue
      boolean reclaimValue(Object key, int hash, ValueReference valueReference) {
         this.lock();

         try {
            int newCount = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> v = e.getValueReference();
                  if (v != valueReference) {
                     boolean var18 = false;
                     return var18;
                  }

                  ++this.modCount;
                  ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, valueReference.get(), valueReference, RemovalCause.COLLECTED);
                  newCount = this.count - 1;
                  table.set(index, newFirst);
                  this.count = newCount;
                  boolean var12 = true;
                  return var12;
               }
            }

            boolean var17 = false;
            return var17;
         } finally {
            this.unlock();
            if (!this.isHeldByCurrentThread()) {
               this.postWriteCleanup();
            }

         }
      }

      @CanIgnoreReturnValue
      boolean removeLoadingValue(Object key, int hash, LoadingValueReference valueReference) {
         this.lock();

         try {
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> v = e.getValueReference();
                  if (v != valueReference) {
                     boolean var16 = false;
                     return var16;
                  }

                  if (valueReference.isActive()) {
                     e.setValueReference(valueReference.getOldValue());
                  } else {
                     ReferenceEntry<K, V> newFirst = this.removeEntryFromChain(first, e);
                     table.set(index, newFirst);
                  }

                  boolean var15 = true;
                  return var15;
               }
            }

            boolean var14 = false;
            return var14;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      @VisibleForTesting
      @GuardedBy("this")
      @CanIgnoreReturnValue
      boolean removeEntry(ReferenceEntry entry, int hash, RemovalCause cause) {
         int newCount = this.count - 1;
         AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
         int index = hash & table.length() - 1;
         ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

         for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
            if (e == entry) {
               ++this.modCount;
               ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference().get(), e.getValueReference(), cause);
               newCount = this.count - 1;
               table.set(index, newFirst);
               this.count = newCount;
               return true;
            }
         }

         return false;
      }

      void postReadCleanup() {
         if ((this.readCount.incrementAndGet() & 63) == 0) {
            this.cleanUp();
         }

      }

      @GuardedBy("this")
      void preWriteCleanup(long now) {
         this.runLockedCleanup(now);
      }

      void postWriteCleanup() {
         this.runUnlockedCleanup();
      }

      void cleanUp() {
         long now = this.map.ticker.read();
         this.runLockedCleanup(now);
         this.runUnlockedCleanup();
      }

      void runLockedCleanup(long now) {
         if (this.tryLock()) {
            try {
               this.drainReferenceQueues();
               this.expireEntries(now);
               this.readCount.set(0);
            } finally {
               this.unlock();
            }
         }

      }

      void runUnlockedCleanup() {
         if (!this.isHeldByCurrentThread()) {
            this.map.processPendingNotifications();
         }

      }
   }

   static class LoadingValueReference implements ValueReference {
      volatile ValueReference oldValue;
      final SettableFuture futureValue;
      final Stopwatch stopwatch;

      public LoadingValueReference() {
         this((ValueReference)null);
      }

      public LoadingValueReference(@CheckForNull ValueReference oldValue) {
         this.futureValue = SettableFuture.create();
         this.stopwatch = Stopwatch.createUnstarted();
         this.oldValue = oldValue == null ? LocalCache.unset() : oldValue;
      }

      public boolean isLoading() {
         return true;
      }

      public boolean isActive() {
         return this.oldValue.isActive();
      }

      public int getWeight() {
         return this.oldValue.getWeight();
      }

      @CanIgnoreReturnValue
      public boolean set(@CheckForNull Object newValue) {
         return this.futureValue.set(newValue);
      }

      @CanIgnoreReturnValue
      public boolean setException(Throwable t) {
         return this.futureValue.setException(t);
      }

      private ListenableFuture fullyFailedFuture(Throwable t) {
         return Futures.immediateFailedFuture(t);
      }

      public void notifyNewValue(@CheckForNull Object newValue) {
         if (newValue != null) {
            this.set(newValue);
         } else {
            this.oldValue = LocalCache.unset();
         }

      }

      public ListenableFuture loadFuture(Object key, CacheLoader loader) {
         try {
            this.stopwatch.start();
            V previousValue = (V)this.oldValue.get();
            if (previousValue == null) {
               V newValue = (V)loader.load(key);
               return (ListenableFuture)(this.set(newValue) ? this.futureValue : Futures.immediateFuture(newValue));
            } else {
               ListenableFuture<V> newValue = loader.reload(key, previousValue);
               return newValue == null ? Futures.immediateFuture((Object)null) : Futures.transform(newValue, (newResult) -> {
                  this.set(newResult);
                  return newResult;
               }, MoreExecutors.directExecutor());
            }
         } catch (Throwable var5) {
            ListenableFuture<V> result = (ListenableFuture<V>)(this.setException(var5) ? this.futureValue : this.fullyFailedFuture(var5));
            if (var5 instanceof InterruptedException) {
               Thread.currentThread().interrupt();
            }

            return result;
         }
      }

      @CheckForNull
      public Object compute(Object key, BiFunction function) {
         this.stopwatch.start();

         V previousValue;
         try {
            previousValue = (V)this.oldValue.waitForValue();
         } catch (ExecutionException var7) {
            previousValue = (V)null;
         }

         V newValue;
         try {
            newValue = (V)function.apply(key, previousValue);
         } catch (Throwable th) {
            this.setException(th);
            throw th;
         }

         this.set(newValue);
         return newValue;
      }

      public long elapsedNanos() {
         return this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
      }

      public Object waitForValue() throws ExecutionException {
         return Uninterruptibles.getUninterruptibly(this.futureValue);
      }

      public Object get() {
         return this.oldValue.get();
      }

      public ValueReference getOldValue() {
         return this.oldValue;
      }

      public ReferenceEntry getEntry() {
         return null;
      }

      public ValueReference copyFor(ReferenceQueue queue, @CheckForNull Object value, ReferenceEntry entry) {
         return this;
      }
   }

   static class ComputingValueReference extends LoadingValueReference {
      ComputingValueReference(ValueReference oldValue) {
         super(oldValue);
      }

      public boolean isLoading() {
         return false;
      }
   }

   static final class WriteQueue extends AbstractQueue {
      final ReferenceEntry head = new AbstractReferenceEntry() {
         @Weak
         ReferenceEntry nextWrite = this;
         @Weak
         ReferenceEntry previousWrite = this;

         public long getWriteTime() {
            return Long.MAX_VALUE;
         }

         public void setWriteTime(long time) {
         }

         public ReferenceEntry getNextInWriteQueue() {
            return this.nextWrite;
         }

         public void setNextInWriteQueue(ReferenceEntry next) {
            this.nextWrite = next;
         }

         public ReferenceEntry getPreviousInWriteQueue() {
            return this.previousWrite;
         }

         public void setPreviousInWriteQueue(ReferenceEntry previous) {
            this.previousWrite = previous;
         }
      };

      public boolean offer(ReferenceEntry entry) {
         LocalCache.connectWriteOrder(entry.getPreviousInWriteQueue(), entry.getNextInWriteQueue());
         LocalCache.connectWriteOrder(this.head.getPreviousInWriteQueue(), entry);
         LocalCache.connectWriteOrder(entry, this.head);
         return true;
      }

      @CheckForNull
      public ReferenceEntry peek() {
         ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
         return next == this.head ? null : next;
      }

      @CheckForNull
      public ReferenceEntry poll() {
         ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
         if (next == this.head) {
            return null;
         } else {
            this.remove(next);
            return next;
         }
      }

      @CanIgnoreReturnValue
      public boolean remove(Object o) {
         ReferenceEntry<K, V> e = (ReferenceEntry)o;
         ReferenceEntry<K, V> previous = e.getPreviousInWriteQueue();
         ReferenceEntry<K, V> next = e.getNextInWriteQueue();
         LocalCache.connectWriteOrder(previous, next);
         LocalCache.nullifyWriteOrder(e);
         return next != LocalCache.NullEntry.INSTANCE;
      }

      public boolean contains(Object o) {
         ReferenceEntry<K, V> e = (ReferenceEntry)o;
         return e.getNextInWriteQueue() != LocalCache.NullEntry.INSTANCE;
      }

      public boolean isEmpty() {
         return this.head.getNextInWriteQueue() == this.head;
      }

      public int size() {
         int size = 0;

         for(ReferenceEntry<K, V> e = this.head.getNextInWriteQueue(); e != this.head; e = e.getNextInWriteQueue()) {
            ++size;
         }

         return size;
      }

      public void clear() {
         ReferenceEntry<K, V> next;
         for(ReferenceEntry<K, V> e = this.head.getNextInWriteQueue(); e != this.head; e = next) {
            next = e.getNextInWriteQueue();
            LocalCache.nullifyWriteOrder(e);
         }

         this.head.setNextInWriteQueue(this.head);
         this.head.setPreviousInWriteQueue(this.head);
      }

      public Iterator iterator() {
         return new AbstractSequentialIterator(this.peek()) {
            @CheckForNull
            protected ReferenceEntry computeNext(ReferenceEntry previous) {
               ReferenceEntry<K, V> next = previous.getNextInWriteQueue();
               return next == WriteQueue.this.head ? null : next;
            }
         };
      }
   }

   static final class AccessQueue extends AbstractQueue {
      final ReferenceEntry head = new AbstractReferenceEntry() {
         @Weak
         ReferenceEntry nextAccess = this;
         @Weak
         ReferenceEntry previousAccess = this;

         public long getAccessTime() {
            return Long.MAX_VALUE;
         }

         public void setAccessTime(long time) {
         }

         public ReferenceEntry getNextInAccessQueue() {
            return this.nextAccess;
         }

         public void setNextInAccessQueue(ReferenceEntry next) {
            this.nextAccess = next;
         }

         public ReferenceEntry getPreviousInAccessQueue() {
            return this.previousAccess;
         }

         public void setPreviousInAccessQueue(ReferenceEntry previous) {
            this.previousAccess = previous;
         }
      };

      public boolean offer(ReferenceEntry entry) {
         LocalCache.connectAccessOrder(entry.getPreviousInAccessQueue(), entry.getNextInAccessQueue());
         LocalCache.connectAccessOrder(this.head.getPreviousInAccessQueue(), entry);
         LocalCache.connectAccessOrder(entry, this.head);
         return true;
      }

      @CheckForNull
      public ReferenceEntry peek() {
         ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
         return next == this.head ? null : next;
      }

      @CheckForNull
      public ReferenceEntry poll() {
         ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
         if (next == this.head) {
            return null;
         } else {
            this.remove(next);
            return next;
         }
      }

      @CanIgnoreReturnValue
      public boolean remove(Object o) {
         ReferenceEntry<K, V> e = (ReferenceEntry)o;
         ReferenceEntry<K, V> previous = e.getPreviousInAccessQueue();
         ReferenceEntry<K, V> next = e.getNextInAccessQueue();
         LocalCache.connectAccessOrder(previous, next);
         LocalCache.nullifyAccessOrder(e);
         return next != LocalCache.NullEntry.INSTANCE;
      }

      public boolean contains(Object o) {
         ReferenceEntry<K, V> e = (ReferenceEntry)o;
         return e.getNextInAccessQueue() != LocalCache.NullEntry.INSTANCE;
      }

      public boolean isEmpty() {
         return this.head.getNextInAccessQueue() == this.head;
      }

      public int size() {
         int size = 0;

         for(ReferenceEntry<K, V> e = this.head.getNextInAccessQueue(); e != this.head; e = e.getNextInAccessQueue()) {
            ++size;
         }

         return size;
      }

      public void clear() {
         ReferenceEntry<K, V> next;
         for(ReferenceEntry<K, V> e = this.head.getNextInAccessQueue(); e != this.head; e = next) {
            next = e.getNextInAccessQueue();
            LocalCache.nullifyAccessOrder(e);
         }

         this.head.setNextInAccessQueue(this.head);
         this.head.setPreviousInAccessQueue(this.head);
      }

      public Iterator iterator() {
         return new AbstractSequentialIterator(this.peek()) {
            @CheckForNull
            protected ReferenceEntry computeNext(ReferenceEntry previous) {
               ReferenceEntry<K, V> next = previous.getNextInAccessQueue();
               return next == AccessQueue.this.head ? null : next;
            }
         };
      }
   }

   abstract class HashIterator implements Iterator {
      int nextSegmentIndex;
      int nextTableIndex;
      @CheckForNull
      Segment currentSegment;
      @CheckForNull
      AtomicReferenceArray currentTable;
      @CheckForNull
      ReferenceEntry nextEntry;
      @CheckForNull
      WriteThroughEntry nextExternal;
      @CheckForNull
      WriteThroughEntry lastReturned;

      HashIterator() {
         this.nextSegmentIndex = LocalCache.this.segments.length - 1;
         this.nextTableIndex = -1;
         this.advance();
      }

      public abstract Object next();

      final void advance() {
         this.nextExternal = null;
         if (!this.nextInChain()) {
            if (!this.nextInTable()) {
               while(this.nextSegmentIndex >= 0) {
                  this.currentSegment = LocalCache.this.segments[this.nextSegmentIndex--];
                  if (this.currentSegment.count != 0) {
                     this.currentTable = this.currentSegment.table;
                     this.nextTableIndex = this.currentTable.length() - 1;
                     if (this.nextInTable()) {
                        return;
                     }
                  }
               }

            }
         }
      }

      boolean nextInChain() {
         if (this.nextEntry != null) {
            for(this.nextEntry = this.nextEntry.getNext(); this.nextEntry != null; this.nextEntry = this.nextEntry.getNext()) {
               if (this.advanceTo(this.nextEntry)) {
                  return true;
               }
            }
         }

         return false;
      }

      boolean nextInTable() {
         while(true) {
            if (this.nextTableIndex >= 0) {
               if ((this.nextEntry = (ReferenceEntry)this.currentTable.get(this.nextTableIndex--)) == null || !this.advanceTo(this.nextEntry) && !this.nextInChain()) {
                  continue;
               }

               return true;
            }

            return false;
         }
      }

      boolean advanceTo(ReferenceEntry entry) {
         boolean var6;
         try {
            long now = LocalCache.this.ticker.read();
            K key = (K)entry.getKey();
            V value = (V)LocalCache.this.getLiveValue(entry, now);
            if (value == null) {
               var6 = false;
               return var6;
            }

            this.nextExternal = LocalCache.this.new WriteThroughEntry(key, value);
            var6 = true;
         } finally {
            this.currentSegment.postReadCleanup();
         }

         return var6;
      }

      public boolean hasNext() {
         return this.nextExternal != null;
      }

      WriteThroughEntry nextEntry() {
         if (this.nextExternal == null) {
            throw new NoSuchElementException();
         } else {
            this.lastReturned = this.nextExternal;
            this.advance();
            return this.lastReturned;
         }
      }

      public void remove() {
         Preconditions.checkState(this.lastReturned != null);
         LocalCache.this.remove(this.lastReturned.getKey());
         this.lastReturned = null;
      }
   }

   final class KeyIterator extends HashIterator {
      public Object next() {
         return this.nextEntry().getKey();
      }
   }

   final class ValueIterator extends HashIterator {
      public Object next() {
         return this.nextEntry().getValue();
      }
   }

   final class WriteThroughEntry implements Map.Entry {
      final Object key;
      Object value;

      WriteThroughEntry(Object key, Object value) {
         this.key = key;
         this.value = value;
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public boolean equals(@CheckForNull Object object) {
         if (!(object instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> that = (Map.Entry)object;
            return this.key.equals(that.getKey()) && this.value.equals(that.getValue());
         }
      }

      public int hashCode() {
         return this.key.hashCode() ^ this.value.hashCode();
      }

      public Object setValue(Object newValue) {
         V oldValue = (V)LocalCache.this.put(this.key, newValue);
         this.value = newValue;
         return oldValue;
      }

      public String toString() {
         return this.getKey() + "=" + this.getValue();
      }
   }

   final class EntryIterator extends HashIterator {
      public Map.Entry next() {
         return this.nextEntry();
      }
   }

   abstract class AbstractCacheSet extends AbstractSet {
      public int size() {
         return LocalCache.this.size();
      }

      public boolean isEmpty() {
         return LocalCache.this.isEmpty();
      }

      public void clear() {
         LocalCache.this.clear();
      }
   }

   final class KeySet extends AbstractCacheSet {
      public Iterator iterator() {
         return LocalCache.this.new KeyIterator();
      }

      public boolean contains(Object o) {
         return LocalCache.this.containsKey(o);
      }

      public boolean remove(Object o) {
         return LocalCache.this.remove(o) != null;
      }
   }

   final class Values extends AbstractCollection {
      public int size() {
         return LocalCache.this.size();
      }

      public boolean isEmpty() {
         return LocalCache.this.isEmpty();
      }

      public void clear() {
         LocalCache.this.clear();
      }

      public Iterator iterator() {
         return LocalCache.this.new ValueIterator();
      }

      public boolean removeIf(Predicate filter) {
         Preconditions.checkNotNull(filter);
         return LocalCache.this.removeIf((k, v) -> filter.test(v));
      }

      public boolean contains(Object o) {
         return LocalCache.this.containsValue(o);
      }
   }

   final class EntrySet extends AbstractCacheSet {
      public Iterator iterator() {
         return LocalCache.this.new EntryIterator();
      }

      public boolean removeIf(Predicate filter) {
         Preconditions.checkNotNull(filter);
         return LocalCache.this.removeIf((k, v) -> filter.test(Maps.immutableEntry(k, v)));
      }

      public boolean contains(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            if (key == null) {
               return false;
            } else {
               V v = (V)LocalCache.this.get(key);
               return v != null && LocalCache.this.valueEquivalence.equivalent(e.getValue(), v);
            }
         }
      }

      public boolean remove(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            return key != null && LocalCache.this.remove(key, e.getValue());
         }
      }
   }

   static class ManualSerializationProxy extends ForwardingCache implements Serializable {
      private static final long serialVersionUID = 1L;
      final Strength keyStrength;
      final Strength valueStrength;
      final Equivalence keyEquivalence;
      final Equivalence valueEquivalence;
      final long expireAfterWriteNanos;
      final long expireAfterAccessNanos;
      final long maxWeight;
      final Weigher weigher;
      final int concurrencyLevel;
      final RemovalListener removalListener;
      @CheckForNull
      final Ticker ticker;
      final CacheLoader loader;
      @CheckForNull
      transient Cache delegate;

      ManualSerializationProxy(LocalCache cache) {
         this(cache.keyStrength, cache.valueStrength, cache.keyEquivalence, cache.valueEquivalence, cache.expireAfterWriteNanos, cache.expireAfterAccessNanos, cache.maxWeight, cache.weigher, cache.concurrencyLevel, cache.removalListener, cache.ticker, cache.defaultLoader);
      }

      private ManualSerializationProxy(Strength keyStrength, Strength valueStrength, Equivalence keyEquivalence, Equivalence valueEquivalence, long expireAfterWriteNanos, long expireAfterAccessNanos, long maxWeight, Weigher weigher, int concurrencyLevel, RemovalListener removalListener, Ticker ticker, CacheLoader loader) {
         this.keyStrength = keyStrength;
         this.valueStrength = valueStrength;
         this.keyEquivalence = keyEquivalence;
         this.valueEquivalence = valueEquivalence;
         this.expireAfterWriteNanos = expireAfterWriteNanos;
         this.expireAfterAccessNanos = expireAfterAccessNanos;
         this.maxWeight = maxWeight;
         this.weigher = weigher;
         this.concurrencyLevel = concurrencyLevel;
         this.removalListener = removalListener;
         this.ticker = ticker != Ticker.systemTicker() && ticker != CacheBuilder.NULL_TICKER ? ticker : null;
         this.loader = loader;
      }

      CacheBuilder recreateCacheBuilder() {
         CacheBuilder<K, V> builder = CacheBuilder.newBuilder().setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).valueEquivalence(this.valueEquivalence).concurrencyLevel(this.concurrencyLevel).removalListener(this.removalListener);
         builder.strictParsing = false;
         if (this.expireAfterWriteNanos > 0L) {
            builder.expireAfterWrite(this.expireAfterWriteNanos, TimeUnit.NANOSECONDS);
         }

         if (this.expireAfterAccessNanos > 0L) {
            builder.expireAfterAccess(this.expireAfterAccessNanos, TimeUnit.NANOSECONDS);
         }

         if (this.weigher != CacheBuilder.OneWeigher.INSTANCE) {
            builder.weigher(this.weigher);
            if (this.maxWeight != -1L) {
               builder.maximumWeight(this.maxWeight);
            }
         } else if (this.maxWeight != -1L) {
            builder.maximumSize(this.maxWeight);
         }

         if (this.ticker != null) {
            builder.ticker(this.ticker);
         }

         return builder;
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         in.defaultReadObject();
         CacheBuilder<K, V> builder = this.recreateCacheBuilder();
         this.delegate = builder.build();
      }

      private Object readResolve() {
         return this.delegate;
      }

      protected Cache delegate() {
         return this.delegate;
      }
   }

   static final class LoadingSerializationProxy extends ManualSerializationProxy implements LoadingCache, Serializable {
      private static final long serialVersionUID = 1L;
      @CheckForNull
      transient LoadingCache autoDelegate;

      LoadingSerializationProxy(LocalCache cache) {
         super(cache);
      }

      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         in.defaultReadObject();
         CacheBuilder<K, V> builder = this.recreateCacheBuilder();
         this.autoDelegate = builder.build(this.loader);
      }

      public Object get(Object key) throws ExecutionException {
         return this.autoDelegate.get(key);
      }

      public Object getUnchecked(Object key) {
         return this.autoDelegate.getUnchecked(key);
      }

      public ImmutableMap getAll(Iterable keys) throws ExecutionException {
         return this.autoDelegate.getAll(keys);
      }

      public Object apply(Object key) {
         return this.autoDelegate.apply(key);
      }

      public void refresh(Object key) {
         this.autoDelegate.refresh(key);
      }

      private Object readResolve() {
         return this.autoDelegate;
      }
   }

   static class LocalManualCache implements Cache, Serializable {
      final LocalCache localCache;
      private static final long serialVersionUID = 1L;

      LocalManualCache(CacheBuilder builder) {
         this(new LocalCache(builder, (CacheLoader)null));
      }

      private LocalManualCache(LocalCache localCache) {
         this.localCache = localCache;
      }

      @CheckForNull
      public Object getIfPresent(Object key) {
         return this.localCache.getIfPresent(key);
      }

      public Object get(Object key, final Callable valueLoader) throws ExecutionException {
         Preconditions.checkNotNull(valueLoader);
         return this.localCache.get(key, new CacheLoader() {
            public Object load(Object key) throws Exception {
               return valueLoader.call();
            }
         });
      }

      public ImmutableMap getAllPresent(Iterable keys) {
         return this.localCache.getAllPresent(keys);
      }

      public void put(Object key, Object value) {
         this.localCache.put(key, value);
      }

      public void putAll(Map m) {
         this.localCache.putAll(m);
      }

      public void invalidate(Object key) {
         Preconditions.checkNotNull(key);
         this.localCache.remove(key);
      }

      public void invalidateAll(Iterable keys) {
         this.localCache.invalidateAll(keys);
      }

      public void invalidateAll() {
         this.localCache.clear();
      }

      public long size() {
         return this.localCache.longSize();
      }

      public ConcurrentMap asMap() {
         return this.localCache;
      }

      public CacheStats stats() {
         AbstractCache.SimpleStatsCounter aggregator = new AbstractCache.SimpleStatsCounter();
         aggregator.incrementBy(this.localCache.globalStatsCounter);

         for(Segment segment : this.localCache.segments) {
            aggregator.incrementBy(segment.statsCounter);
         }

         return aggregator.snapshot();
      }

      public void cleanUp() {
         this.localCache.cleanUp();
      }

      Object writeReplace() {
         return new ManualSerializationProxy(this.localCache);
      }

      private void readObject(ObjectInputStream in) throws InvalidObjectException {
         throw new InvalidObjectException("Use ManualSerializationProxy");
      }
   }

   static class LocalLoadingCache extends LocalManualCache implements LoadingCache {
      private static final long serialVersionUID = 1L;

      LocalLoadingCache(CacheBuilder builder, CacheLoader loader) {
         super(new LocalCache(builder, (CacheLoader)Preconditions.checkNotNull(loader)), null);
      }

      public Object get(Object key) throws ExecutionException {
         return this.localCache.getOrLoad(key);
      }

      @CanIgnoreReturnValue
      public Object getUnchecked(Object key) {
         try {
            return this.get(key);
         } catch (ExecutionException e) {
            throw new UncheckedExecutionException(e.getCause());
         }
      }

      public ImmutableMap getAll(Iterable keys) throws ExecutionException {
         return this.localCache.getAll(keys);
      }

      public void refresh(Object key) {
         this.localCache.refresh(key);
      }

      public final Object apply(Object key) {
         return this.getUnchecked(key);
      }

      Object writeReplace() {
         return new LoadingSerializationProxy(this.localCache);
      }

      private void readObject(ObjectInputStream in) throws InvalidObjectException {
         throw new InvalidObjectException("Use LoadingSerializationProxy");
      }
   }

   interface ValueReference {
      @CheckForNull
      Object get();

      Object waitForValue() throws ExecutionException;

      int getWeight();

      @CheckForNull
      ReferenceEntry getEntry();

      ValueReference copyFor(ReferenceQueue queue, @CheckForNull Object value, ReferenceEntry entry);

      void notifyNewValue(@CheckForNull Object newValue);

      boolean isLoading();

      boolean isActive();
   }
}
