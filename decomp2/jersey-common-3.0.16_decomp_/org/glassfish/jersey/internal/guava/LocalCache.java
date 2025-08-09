package org.glassfish.jersey.internal.guava;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

class LocalCache extends AbstractMap implements ConcurrentMap {
   private static final int MAXIMUM_CAPACITY = 1073741824;
   private static final int MAX_SEGMENTS = 65536;
   private static final int CONTAINS_VALUE_RETRIES = 3;
   private static final int DRAIN_THRESHOLD = 63;
   private static final int DRAIN_MAX = 16;
   private static final Logger logger = Logger.getLogger(LocalCache.class.getName());
   private static final ValueReference UNSET = new ValueReference() {
      public Object get() {
         return null;
      }

      public int getWeight() {
         return 0;
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
         return false;
      }

      public Object waitForValue() {
         return null;
      }

      public void notifyNewValue(Object newValue) {
      }
   };
   private static final Queue DISCARDING_QUEUE = new AbstractQueue() {
      public boolean offer(Object o) {
         return true;
      }

      public Object peek() {
         return null;
      }

      public Object poll() {
         return null;
      }

      public int size() {
         return 0;
      }

      public Iterator iterator() {
         return Iterators.emptyIterator();
      }
   };
   private final int segmentMask;
   private final int segmentShift;
   private final Segment[] segments;
   private final int concurrencyLevel;
   private final Equivalence keyEquivalence;
   private final Equivalence valueEquivalence;
   private final Strength keyStrength;
   private final Strength valueStrength;
   private final long maxWeight;
   private final long expireAfterAccessNanos;
   private final long expireAfterWriteNanos;
   private final long refreshNanos;
   private final Queue removalNotificationQueue;
   private final Ticker ticker;
   private final EntryFactory entryFactory;
   private final CacheLoader defaultLoader;
   private Set keySet;
   private Collection values;
   private Set entrySet;

   private LocalCache(CacheBuilder builder, CacheLoader loader) {
      this.concurrencyLevel = Math.min(builder.getConcurrencyLevel(), 65536);
      this.keyStrength = LocalCache.Strength.STRONG;
      this.valueStrength = LocalCache.Strength.STRONG;
      this.keyEquivalence = this.keyStrength.defaultEquivalence();
      this.valueEquivalence = this.valueStrength.defaultEquivalence();
      this.maxWeight = -1L;
      this.expireAfterAccessNanos = builder.getExpireAfterAccessNanos();
      this.expireAfterWriteNanos = 0L;
      this.refreshNanos = 0L;
      this.removalNotificationQueue = discardingQueue();
      this.ticker = this.recordsTime() ? Ticker.systemTicker() : CacheBuilder.NULL_TICKER;
      this.entryFactory = LocalCache.EntryFactory.getFactory(this.keyStrength, this.usesAccessEntries(), this.usesWriteEntries());
      this.defaultLoader = loader;
      int initialCapacity = Math.min(16, 1073741824);
      if (this.evictsBySize()) {
         initialCapacity = Math.min(initialCapacity, (int)this.maxWeight);
      }

      int segmentShift = 0;

      int segmentCount;
      for(segmentCount = 1; segmentCount < this.concurrencyLevel && (!this.evictsBySize() || (long)(segmentCount * 20) <= this.maxWeight); segmentCount <<= 1) {
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

            this.segments[i] = this.createSegment(segmentSize, maxSegmentWeight);
         }
      } else {
         for(int i = 0; i < this.segments.length; ++i) {
            this.segments[i] = this.createSegment(segmentSize, -1L);
         }
      }

   }

   private static ValueReference unset() {
      return UNSET;
   }

   private static ReferenceEntry nullEntry() {
      return LocalCache.NullEntry.INSTANCE;
   }

   private static Queue discardingQueue() {
      return DISCARDING_QUEUE;
   }

   private static int rehash(int h) {
      h += h << 15 ^ -12931;
      h ^= h >>> 10;
      h += h << 3;
      h ^= h >>> 6;
      h += (h << 2) + (h << 14);
      return h ^ h >>> 16;
   }

   private static void connectAccessOrder(ReferenceEntry previous, ReferenceEntry next) {
      previous.setNextInAccessQueue(next);
      next.setPreviousInAccessQueue(previous);
   }

   private static void nullifyAccessOrder(ReferenceEntry nulled) {
      ReferenceEntry<K, V> nullEntry = nullEntry();
      nulled.setNextInAccessQueue(nullEntry);
      nulled.setPreviousInAccessQueue(nullEntry);
   }

   private static void connectWriteOrder(ReferenceEntry previous, ReferenceEntry next) {
      previous.setNextInWriteQueue(next);
      next.setPreviousInWriteQueue(previous);
   }

   private static void nullifyWriteOrder(ReferenceEntry nulled) {
      ReferenceEntry<K, V> nullEntry = nullEntry();
      nulled.setNextInWriteQueue(nullEntry);
      nulled.setPreviousInWriteQueue(nullEntry);
   }

   boolean evictsBySize() {
      return this.maxWeight >= 0L;
   }

   private boolean expiresAfterWrite() {
      return this.expireAfterWriteNanos > 0L;
   }

   private boolean expiresAfterAccess() {
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

   private boolean recordsTime() {
      return this.recordsWrite() || this.recordsAccess();
   }

   private boolean usesWriteEntries() {
      return this.usesWriteQueue() || this.recordsWrite();
   }

   private boolean usesAccessEntries() {
      return this.usesAccessQueue() || this.recordsAccess();
   }

   boolean usesKeyReferences() {
      return this.keyStrength != LocalCache.Strength.STRONG;
   }

   boolean usesValueReferences() {
      return this.valueStrength != LocalCache.Strength.STRONG;
   }

   private int hash(Object key) {
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

   private Segment segmentFor(int hash) {
      return this.segments[hash >>> this.segmentShift & this.segmentMask];
   }

   private Segment createSegment(int initialCapacity, long maxSegmentWeight) {
      return new Segment(this, initialCapacity, maxSegmentWeight);
   }

   private Object getLiveValue(ReferenceEntry entry, long now) {
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

   private Segment[] newSegmentArray(int ssize) {
      return new Segment[ssize];
   }

   public boolean isEmpty() {
      long sum = 0L;
      Segment<K, V>[] segments = this.segments;

      for(int i = 0; i < segments.length; ++i) {
         if (segments[i].count != 0) {
            return false;
         }

         sum += (long)segments[i].modCount;
      }

      if (sum != 0L) {
         for(int i = 0; i < segments.length; ++i) {
            if (segments[i].count != 0) {
               return false;
            }

            sum -= (long)segments[i].modCount;
         }

         if (sum != 0L) {
            return false;
         }
      }

      return true;
   }

   private long longSize() {
      Segment<K, V>[] segments = this.segments;
      long sum = 0L;

      for(int i = 0; i < segments.length; ++i) {
         sum += (long)segments[i].count;
      }

      return sum;
   }

   public int size() {
      return Ints.saturatedCast(this.longSize());
   }

   public Object get(Object key) {
      if (key == null) {
         return null;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).get(key, hash);
      }
   }

   Object getIfPresent(Object key) {
      int hash = this.hash(Preconditions.checkNotNull(key));
      return this.segmentFor(hash).get(key, hash);
   }

   private Object get(Object key, CacheLoader loader) throws ExecutionException {
      int hash = this.hash(Preconditions.checkNotNull(key));
      return this.segmentFor(hash).get(key, hash, loader);
   }

   Object getOrLoad(Object key) throws ExecutionException {
      return this.get(key, this.defaultLoader);
   }

   public boolean containsKey(Object key) {
      if (key == null) {
         return false;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).containsKey(key, hash);
      }
   }

   public boolean containsValue(Object value) {
      if (value == null) {
         return false;
      } else {
         long now = this.ticker.read();
         Segment<K, V>[] segments = this.segments;
         long last = -1L;

         for(int i = 0; i < 3; ++i) {
            long sum = 0L;

            for(Segment segment : segments) {
               int c = segment.count;
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

   public Object put(Object key, Object value) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(value);
      int hash = this.hash(key);
      return this.segmentFor(hash).put(key, hash, value, false);
   }

   public Object putIfAbsent(Object key, Object value) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(value);
      int hash = this.hash(key);
      return this.segmentFor(hash).put(key, hash, value, true);
   }

   public void putAll(Map m) {
      for(Map.Entry e : m.entrySet()) {
         this.put(e.getKey(), e.getValue());
      }

   }

   public Object remove(Object key) {
      if (key == null) {
         return null;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).remove(key, hash);
      }
   }

   public boolean remove(Object key, Object value) {
      if (key != null && value != null) {
         int hash = this.hash(key);
         return this.segmentFor(hash).remove(key, hash, value);
      } else {
         return false;
      }
   }

   public boolean replace(Object key, Object oldValue, Object newValue) {
      Preconditions.checkNotNull(key);
      Preconditions.checkNotNull(newValue);
      if (oldValue == null) {
         return false;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).replace(key, hash, oldValue, newValue);
      }
   }

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

   public Set keySet() {
      Set<K> ks = this.keySet;
      return ks != null ? ks : (this.keySet = new KeySet(this));
   }

   public Collection values() {
      Collection<V> vs = this.values;
      return vs != null ? vs : (this.values = new Values(this));
   }

   public Set entrySet() {
      Set<Map.Entry<K, V>> es = this.entrySet;
      return es != null ? es : (this.entrySet = new EntrySet(this));
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

      abstract ValueReference referenceValue(Segment var1, ReferenceEntry var2, Object var3, int var4);

      abstract Equivalence defaultEquivalence();
   }

   static enum EntryFactory {
      STRONG {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new StrongEntry(key, hash, next);
         }
      },
      STRONG_ACCESS {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new StrongAccessEntry(key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyAccessEntry(original, newEntry);
            return newEntry;
         }
      },
      STRONG_WRITE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new StrongWriteEntry(key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyWriteEntry(original, newEntry);
            return newEntry;
         }
      },
      STRONG_ACCESS_WRITE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new StrongAccessWriteEntry(key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyAccessEntry(original, newEntry);
            this.copyWriteEntry(original, newEntry);
            return newEntry;
         }
      },
      WEAK {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new WeakEntry(segment.keyReferenceQueue, key, hash, next);
         }
      },
      WEAK_ACCESS {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new WeakAccessEntry(segment.keyReferenceQueue, key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyAccessEntry(original, newEntry);
            return newEntry;
         }
      },
      WEAK_WRITE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new WeakWriteEntry(segment.keyReferenceQueue, key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyWriteEntry(original, newEntry);
            return newEntry;
         }
      },
      WEAK_ACCESS_WRITE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new WeakAccessWriteEntry(segment.keyReferenceQueue, key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
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

      abstract ReferenceEntry newEntry(Segment var1, Object var2, int var3, ReferenceEntry var4);

      ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
         return this.newEntry(segment, original.getKey(), original.getHash(), newNext);
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
   }

   private static enum NullEntry implements ReferenceEntry {
      INSTANCE;

      public ValueReference getValueReference() {
         return null;
      }

      public void setValueReference(ValueReference valueReference) {
      }

      public ReferenceEntry getNext() {
         return null;
      }

      public int getHash() {
         return 0;
      }

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
      final ReferenceEntry next;
      volatile ValueReference valueReference = LocalCache.unset();

      StrongEntry(Object key, int hash, ReferenceEntry next) {
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
      ReferenceEntry nextAccess = LocalCache.nullEntry();
      ReferenceEntry previousAccess = LocalCache.nullEntry();

      StrongAccessEntry(Object key, int hash, ReferenceEntry next) {
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
      ReferenceEntry nextWrite = LocalCache.nullEntry();
      ReferenceEntry previousWrite = LocalCache.nullEntry();

      StrongWriteEntry(Object key, int hash, ReferenceEntry next) {
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
      ReferenceEntry nextAccess = LocalCache.nullEntry();
      ReferenceEntry previousAccess = LocalCache.nullEntry();
      volatile long writeTime = Long.MAX_VALUE;
      ReferenceEntry nextWrite = LocalCache.nullEntry();
      ReferenceEntry previousWrite = LocalCache.nullEntry();

      StrongAccessWriteEntry(Object key, int hash, ReferenceEntry next) {
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
      final ReferenceEntry next;
      volatile ValueReference valueReference = LocalCache.unset();

      WeakEntry(ReferenceQueue queue, Object key, int hash, ReferenceEntry next) {
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
      ReferenceEntry nextAccess = LocalCache.nullEntry();
      ReferenceEntry previousAccess = LocalCache.nullEntry();

      WeakAccessEntry(ReferenceQueue queue, Object key, int hash, ReferenceEntry next) {
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
      ReferenceEntry nextWrite = LocalCache.nullEntry();
      ReferenceEntry previousWrite = LocalCache.nullEntry();

      WeakWriteEntry(ReferenceQueue queue, Object key, int hash, ReferenceEntry next) {
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
      ReferenceEntry nextAccess = LocalCache.nullEntry();
      ReferenceEntry previousAccess = LocalCache.nullEntry();
      volatile long writeTime = Long.MAX_VALUE;
      ReferenceEntry nextWrite = LocalCache.nullEntry();
      ReferenceEntry previousWrite = LocalCache.nullEntry();

      WeakAccessWriteEntry(ReferenceQueue queue, Object key, int hash, ReferenceEntry next) {
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
      final LocalCache map;
      final long maxSegmentWeight;
      final ReferenceQueue keyReferenceQueue;
      final ReferenceQueue valueReferenceQueue;
      final Queue recencyQueue;
      final AtomicInteger readCount = new AtomicInteger();
      final Queue writeQueue;
      final Queue accessQueue;
      volatile int count;
      long totalWeight;
      int modCount;
      int threshold;
      volatile AtomicReferenceArray table;

      Segment(LocalCache map, int initialCapacity, long maxSegmentWeight) {
         this.map = map;
         this.maxSegmentWeight = maxSegmentWeight;
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
         if ((long)this.threshold == this.maxSegmentWeight) {
            ++this.threshold;
         }

         this.table = newTable;
      }

      ReferenceEntry newEntry(Object key, int hash, ReferenceEntry next) {
         return this.map.entryFactory.newEntry(this, Preconditions.checkNotNull(key), hash, next);
      }

      ReferenceEntry copyEntry(ReferenceEntry original, ReferenceEntry newNext) {
         if (original.getKey() == null) {
            return null;
         } else {
            ValueReference<K, V> valueReference = original.getValueReference();
            V value = (V)valueReference.get();
            if (value == null && valueReference.isActive()) {
               return null;
            } else {
               ReferenceEntry<K, V> newEntry = this.map.entryFactory.copyEntry(this, original, newNext);
               newEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, value, newEntry));
               return newEntry;
            }
         }
      }

      void setValue(ReferenceEntry entry, Object key, Object value, long now) {
         ValueReference<K, V> previous = entry.getValueReference();
         int weight = 1;
         Preconditions.checkState(weight >= 0, "Weights must be non-negative");
         ValueReference<K, V> valueReference = this.map.valueStrength.referenceValue(this, entry, value, weight);
         entry.setValueReference(valueReference);
         this.recordWrite(entry, weight, now);
         previous.notifyNewValue(value);
      }

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
                        this.enqueueNotification(entryKey, hash, valueReference, RemovalCause.COLLECTED);
                     } else {
                        if (!this.map.isExpired(e, now)) {
                           this.recordLockedRead(e, now);
                           Object var16 = value;
                           return var16;
                        }

                        this.enqueueNotification(entryKey, hash, valueReference, RemovalCause.EXPIRED);
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
            synchronized(e) {
               return this.loadSync(key, hash, loadingValueReference, loader);
            }
         } else {
            return this.waitForLoadingValue(e, key, valueReference);
         }
      }

      Object waitForLoadingValue(ReferenceEntry e, Object key, ValueReference valueReference) throws ExecutionException {
         if (!valueReference.isLoading()) {
            throw new AssertionError();
         } else {
            Preconditions.checkState(!Thread.holdsLock(e), "Recursive load of: %s", key);
            V value = (V)valueReference.waitForValue();
            if (value == null) {
               throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + key + ".");
            } else {
               long now = this.map.ticker.read();
               this.recordRead(e, now);
               return value;
            }
         }
      }

      Object loadSync(Object key, int hash, LoadingValueReference loadingValueReference, CacheLoader loader) throws ExecutionException {
         ListenableFuture<V> loadingFuture = loadingValueReference.loadFuture(key, loader);
         return this.getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
      }

      ListenableFuture loadAsync(final Object key, final int hash, final LoadingValueReference loadingValueReference, CacheLoader loader) {
         final ListenableFuture<V> loadingFuture = loadingValueReference.loadFuture(key, loader);
         loadingFuture.addListener(new Runnable() {
            public void run() {
               try {
                  Object var1 = Segment.this.getAndRecordStats(key, hash, loadingValueReference, loadingFuture);
               } catch (Throwable t) {
                  LocalCache.logger.log(Level.WARNING, "Exception thrown during refresh", t);
                  loadingValueReference.setException(t);
               }

            }
         }, MoreExecutors.directExecutor());
         return loadingFuture;
      }

      Object getAndRecordStats(Object key, int hash, LoadingValueReference loadingValueReference, ListenableFuture newValue) throws ExecutionException {
         V value = (V)null;

         Object var6;
         try {
            value = (V)Uninterruptibles.getUninterruptibly(newValue);
            if (value == null) {
               throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + key + ".");
            }

            this.storeLoadedValue(key, hash, loadingValueReference, value);
            var6 = value;
         } finally {
            if (value == null) {
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

      LoadingValueReference insertLoadingValueReference(Object key, int hash, boolean checkTime) {
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

      void drainReferenceQueues() {
         if (this.map.usesKeyReferences()) {
            this.drainKeyReferenceQueue();
         }

         if (this.map.usesValueReferences()) {
            this.drainValueReferenceQueue();
         }

      }

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

      void recordLockedRead(ReferenceEntry entry, long now) {
         if (this.map.recordsAccess()) {
            entry.setAccessTime(now);
         }

         this.accessQueue.add(entry);
      }

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

      void enqueueNotification(ReferenceEntry entry, RemovalCause cause) {
         this.enqueueNotification(entry.getKey(), entry.getHash(), entry.getValueReference(), cause);
      }

      void enqueueNotification(Object key, int hash, ValueReference valueReference, RemovalCause cause) {
         this.totalWeight -= (long)valueReference.getWeight();
         if (this.map.removalNotificationQueue != LocalCache.DISCARDING_QUEUE) {
            V value = (V)valueReference.get();
            RemovalNotification<K, V> notification = new RemovalNotification(key, value, cause);
            this.map.removalNotificationQueue.offer(notification);
         }

      }

      void evictEntries() {
         if (this.map.evictsBySize()) {
            this.drainRecencyQueue();

            while(this.totalWeight > this.maxSegmentWeight) {
               ReferenceEntry<K, V> e = this.getNextEvictable();
               if (!this.removeEntry(e, e.getHash(), RemovalCause.SIZE)) {
                  throw new AssertionError();
               }
            }

         }
      }

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
                     this.enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
                     this.setValue(e, key, value, now);
                     this.evictEntries();
                     Object var24 = entryValue;
                     return var24;
                  }

                  ++this.modCount;
                  if (valueReference.isActive()) {
                     this.enqueueNotification(key, hash, valueReference, RemovalCause.COLLECTED);
                     this.setValue(e, key, value, now);
                     newCount = this.count;
                  } else {
                     this.setValue(e, key, value, now);
                     newCount = this.count + 1;
                  }

                  this.count = newCount;
                  this.evictEntries();
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
            this.evictEntries();
            Object var23 = null;
            return var23;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

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
                        this.enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
                        this.setValue(e, key, newValue, now);
                        this.evictEntries();
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
                     ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, valueReference, RemovalCause.COLLECTED);
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
                     this.enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
                     this.setValue(e, key, newValue, now);
                     this.evictEntries();
                     Object var21 = entryValue;
                     return var21;
                  }

                  if (valueReference.isActive()) {
                     int newCount = this.count - 1;
                     ++this.modCount;
                     ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, valueReference, RemovalCause.COLLECTED);
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
                  ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, valueReference, cause);
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
                     valueReference = new WeightedStrongValueReference(newValue, 0);
                     this.enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
                     boolean var23 = false;
                     return var23;
                  }

                  ++this.modCount;
                  if (oldValueReference.isActive()) {
                     RemovalCause cause = entryValue == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED;
                     this.enqueueNotification(key, hash, oldValueReference, cause);
                     --newCount;
                  }

                  this.setValue(e, key, newValue, now);
                  this.count = newCount;
                  this.evictEntries();
                  boolean var22 = true;
                  return var22;
               }
            }

            ++this.modCount;
            ReferenceEntry<K, V> newEntry = this.newEntry(key, hash, first);
            this.setValue(newEntry, key, newValue, now);
            table.set(index, newEntry);
            this.count = newCount;
            this.evictEntries();
            boolean var20 = true;
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
                  ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, valueReference, cause);
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

      void clear() {
         if (this.count != 0) {
            this.lock();

            try {
               AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;

               for(int i = 0; i < table.length(); ++i) {
                  for(ReferenceEntry<K, V> e = (ReferenceEntry)table.get(i); e != null; e = e.getNext()) {
                     if (e.getValueReference().isActive()) {
                        this.enqueueNotification(e, RemovalCause.EXPLICIT);
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

      ReferenceEntry removeValueFromChain(ReferenceEntry first, ReferenceEntry entry, Object key, int hash, ValueReference valueReference, RemovalCause cause) {
         this.enqueueNotification(key, hash, valueReference, cause);
         this.writeQueue.remove(entry);
         this.accessQueue.remove(entry);
         if (valueReference.isLoading()) {
            valueReference.notifyNewValue((Object)null);
            return first;
         } else {
            return this.removeEntryFromChain(first, entry);
         }
      }

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

      void removeCollectedEntry(ReferenceEntry entry) {
         this.enqueueNotification(entry, RemovalCause.COLLECTED);
         this.writeQueue.remove(entry);
         this.accessQueue.remove(entry);
      }

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
                  ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference(), RemovalCause.COLLECTED);
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
                  ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, entryKey, hash, valueReference, RemovalCause.COLLECTED);
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

      boolean removeEntry(ReferenceEntry entry, int hash, RemovalCause cause) {
         int newCount = this.count - 1;
         AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
         int index = hash & table.length() - 1;
         ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

         for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
            if (e == entry) {
               ++this.modCount;
               ReferenceEntry<K, V> newFirst = this.removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference(), cause);
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

      void preWriteCleanup(long now) {
         this.runLockedCleanup(now);
      }

      void postWriteCleanup() {
      }

      void cleanUp() {
         long now = this.map.ticker.read();
         this.runLockedCleanup(now);
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
   }

   static class LoadingValueReference implements ValueReference {
      final SettableFuture futureValue;
      final Stopwatch stopwatch;
      volatile ValueReference oldValue;

      public LoadingValueReference() {
         this(LocalCache.unset());
      }

      public LoadingValueReference(ValueReference oldValue) {
         this.futureValue = SettableFuture.create();
         this.stopwatch = Stopwatch.createUnstarted();
         this.oldValue = oldValue;
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

      public boolean set(Object newValue) {
         return this.futureValue.set(newValue);
      }

      public boolean setException(Throwable t) {
         return this.futureValue.setException(t);
      }

      private ListenableFuture fullyFailedFuture(Throwable t) {
         return Futures.immediateFailedFuture(t);
      }

      public void notifyNewValue(Object newValue) {
         if (newValue != null) {
            this.set(newValue);
         } else {
            this.oldValue = LocalCache.unset();
         }

      }

      public ListenableFuture loadFuture(Object key, CacheLoader loader) {
         this.stopwatch.start();
         V previousValue = (V)this.oldValue.get();

         try {
            if (previousValue == null) {
               V newValue = (V)loader.load(key);
               return (ListenableFuture)(this.set(newValue) ? this.futureValue : Futures.immediateFuture(newValue));
            } else {
               ListenableFuture<V> newValue = loader.reload(key, previousValue);
               return newValue == null ? Futures.immediateFuture((Object)null) : Futures.transform(newValue, new Function() {
                  public Object apply(Object newValue) {
                     LoadingValueReference.this.set(newValue);
                     return newValue;
                  }
               });
            }
         } catch (Throwable t) {
            if (t instanceof InterruptedException) {
               Thread.currentThread().interrupt();
            }

            return (ListenableFuture)(this.setException(t) ? this.futureValue : this.fullyFailedFuture(t));
         }
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

      public ValueReference copyFor(ReferenceQueue queue, Object value, ReferenceEntry entry) {
         return this;
      }
   }

   static final class WriteQueue extends AbstractQueue {
      final ReferenceEntry head = new AbstractReferenceEntry() {
         ReferenceEntry nextWrite = this;
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

      public ReferenceEntry peek() {
         ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
         return next == this.head ? null : next;
      }

      public ReferenceEntry poll() {
         ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
         if (next == this.head) {
            return null;
         } else {
            this.remove(next);
            return next;
         }
      }

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
            protected ReferenceEntry computeNext(ReferenceEntry previous) {
               ReferenceEntry<K, V> next = previous.getNextInWriteQueue();
               return next == WriteQueue.this.head ? null : next;
            }
         };
      }
   }

   static final class AccessQueue extends AbstractQueue {
      final ReferenceEntry head = new AbstractReferenceEntry() {
         ReferenceEntry nextAccess = this;
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

      public ReferenceEntry peek() {
         ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
         return next == this.head ? null : next;
      }

      public ReferenceEntry poll() {
         ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
         if (next == this.head) {
            return null;
         } else {
            this.remove(next);
            return next;
         }
      }

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
            protected ReferenceEntry computeNext(ReferenceEntry previous) {
               ReferenceEntry<K, V> next = previous.getNextInAccessQueue();
               return next == AccessQueue.this.head ? null : next;
            }
         };
      }
   }

   static class LocalManualCache implements Cache, Serializable {
      private static final long serialVersionUID = 1L;
      final LocalCache localCache;

      LocalManualCache(CacheBuilder builder) {
         this(new LocalCache(builder, (CacheLoader)null));
      }

      private LocalManualCache(LocalCache localCache) {
         this.localCache = localCache;
      }

      public Object getIfPresent(Object key) {
         return this.localCache.getIfPresent(key);
      }

      public void put(Object key, Object value) {
         this.localCache.put(key, value);
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

      public Object getUnchecked(Object key) {
         try {
            return this.get(key);
         } catch (ExecutionException e) {
            throw new UncheckedExecutionException(e.getCause());
         }
      }

      public final Object apply(Object key) {
         return this.getUnchecked(key);
      }
   }

   abstract class HashIterator implements Iterator {
      int nextSegmentIndex;
      int nextTableIndex;
      Segment currentSegment;
      AtomicReferenceArray currentTable;
      ReferenceEntry nextEntry;
      WriteThroughEntry nextExternal;
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
      final Object value;

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

      public boolean equals(Object object) {
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
         throw new UnsupportedOperationException();
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
      final ConcurrentMap map;

      AbstractCacheSet(ConcurrentMap map) {
         this.map = map;
      }

      public int size() {
         return this.map.size();
      }

      public boolean isEmpty() {
         return this.map.isEmpty();
      }

      public void clear() {
         this.map.clear();
      }
   }

   final class KeySet extends AbstractCacheSet {
      KeySet(ConcurrentMap map) {
         super(map);
      }

      public Iterator iterator() {
         return LocalCache.this.new KeyIterator();
      }

      public boolean contains(Object o) {
         return this.map.containsKey(o);
      }

      public boolean remove(Object o) {
         return this.map.remove(o) != null;
      }
   }

   final class Values extends AbstractCollection {
      private final ConcurrentMap map;

      Values(ConcurrentMap map) {
         this.map = map;
      }

      public int size() {
         return this.map.size();
      }

      public boolean isEmpty() {
         return this.map.isEmpty();
      }

      public void clear() {
         this.map.clear();
      }

      public Iterator iterator() {
         return LocalCache.this.new ValueIterator();
      }

      public boolean contains(Object o) {
         return this.map.containsValue(o);
      }
   }

   final class EntrySet extends AbstractCacheSet {
      EntrySet(ConcurrentMap map) {
         super(map);
      }

      public Iterator iterator() {
         return LocalCache.this.new EntryIterator();
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

   interface ReferenceEntry {
      ValueReference getValueReference();

      void setValueReference(ValueReference var1);

      ReferenceEntry getNext();

      int getHash();

      Object getKey();

      long getAccessTime();

      void setAccessTime(long var1);

      ReferenceEntry getNextInAccessQueue();

      void setNextInAccessQueue(ReferenceEntry var1);

      ReferenceEntry getPreviousInAccessQueue();

      void setPreviousInAccessQueue(ReferenceEntry var1);

      long getWriteTime();

      void setWriteTime(long var1);

      ReferenceEntry getNextInWriteQueue();

      void setNextInWriteQueue(ReferenceEntry var1);

      ReferenceEntry getPreviousInWriteQueue();

      void setPreviousInWriteQueue(ReferenceEntry var1);
   }

   interface ValueReference {
      Object get();

      Object waitForValue() throws ExecutionException;

      int getWeight();

      ReferenceEntry getEntry();

      ValueReference copyFor(ReferenceQueue var1, Object var2, ReferenceEntry var3);

      void notifyNewValue(Object var1);

      boolean isLoading();

      boolean isActive();
   }
}
