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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

class MapMakerInternalMap extends AbstractMap implements ConcurrentMap, Serializable {
   private static final int MAXIMUM_CAPACITY = 1073741824;
   private static final int MAX_SEGMENTS = 65536;
   private static final int CONTAINS_VALUE_RETRIES = 3;
   private static final int DRAIN_THRESHOLD = 63;
   private static final int DRAIN_MAX = 16;
   private static final ValueReference UNSET = new ValueReference() {
      public Object get() {
         return null;
      }

      public ReferenceEntry getEntry() {
         return null;
      }

      public ValueReference copyFor(ReferenceQueue queue, Object value, ReferenceEntry entry) {
         return this;
      }

      public boolean isComputingReference() {
         return false;
      }

      public void clear(ValueReference newValue) {
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
   private static final Logger logger = Logger.getLogger(MapMakerInternalMap.class.getName());
   private static final long serialVersionUID = 5L;
   private final transient int segmentMask;
   private final transient int segmentShift;
   private final transient Segment[] segments;
   private final int concurrencyLevel;
   private final Equivalence keyEquivalence;
   private final Equivalence valueEquivalence;
   private final Strength keyStrength;
   private final Strength valueStrength;
   private final int maximumSize;
   private final long expireAfterAccessNanos;
   private final long expireAfterWriteNanos;
   private final Queue removalNotificationQueue;
   private final MapMaker.RemovalListener removalListener;
   private final transient EntryFactory entryFactory;
   private final Ticker ticker;
   private transient Set keySet;
   private transient Collection values;
   private transient Set entrySet;

   private MapMakerInternalMap(MapMaker builder) {
      this.concurrencyLevel = Math.min(builder.getConcurrencyLevel(), 65536);
      this.keyStrength = builder.getKeyStrength();
      this.valueStrength = builder.getValueStrength();
      this.keyEquivalence = builder.getKeyEquivalence();
      this.valueEquivalence = this.valueStrength.defaultEquivalence();
      builder.getClass();
      this.maximumSize = -1;
      this.expireAfterAccessNanos = builder.getExpireAfterAccessNanos();
      this.expireAfterWriteNanos = builder.getExpireAfterWriteNanos();
      this.entryFactory = MapMakerInternalMap.EntryFactory.getFactory(this.keyStrength, this.expires(), this.evictsBySize());
      this.ticker = builder.getTicker();
      this.removalListener = builder.getRemovalListener();
      this.removalNotificationQueue = (Queue)(this.removalListener == GenericMapMaker.NullListener.INSTANCE ? discardingQueue() : new ConcurrentLinkedQueue());
      int initialCapacity = Math.min(builder.getInitialCapacity(), 1073741824);
      if (this.evictsBySize()) {
         initialCapacity = Math.min(initialCapacity, this.maximumSize);
      }

      int segmentShift = 0;

      int segmentCount;
      for(segmentCount = 1; segmentCount < this.concurrencyLevel && (!this.evictsBySize() || segmentCount * 2 <= this.maximumSize); segmentCount <<= 1) {
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
         int maximumSegmentSize = this.maximumSize / segmentCount + 1;
         int remainder = this.maximumSize % segmentCount;

         for(int i = 0; i < this.segments.length; ++i) {
            if (i == remainder) {
               --maximumSegmentSize;
            }

            this.segments[i] = this.createSegment(segmentSize, maximumSegmentSize);
         }
      } else {
         for(int i = 0; i < this.segments.length; ++i) {
            this.segments[i] = this.createSegment(segmentSize, -1);
         }
      }

   }

   private static ValueReference unset() {
      return UNSET;
   }

   private static ReferenceEntry nullEntry() {
      return MapMakerInternalMap.NullEntry.INSTANCE;
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

   private static void connectExpirables(ReferenceEntry previous, ReferenceEntry next) {
      previous.setNextExpirable(next);
      next.setPreviousExpirable(previous);
   }

   private static void nullifyExpirable(ReferenceEntry nulled) {
      ReferenceEntry<K, V> nullEntry = nullEntry();
      nulled.setNextExpirable(nullEntry);
      nulled.setPreviousExpirable(nullEntry);
   }

   private static void connectEvictables(ReferenceEntry previous, ReferenceEntry next) {
      previous.setNextEvictable(next);
      next.setPreviousEvictable(previous);
   }

   private static void nullifyEvictable(ReferenceEntry nulled) {
      ReferenceEntry<K, V> nullEntry = nullEntry();
      nulled.setNextEvictable(nullEntry);
      nulled.setPreviousEvictable(nullEntry);
   }

   boolean evictsBySize() {
      return this.maximumSize != -1;
   }

   boolean expires() {
      return this.expiresAfterWrite() || this.expiresAfterAccess();
   }

   private boolean expiresAfterWrite() {
      return this.expireAfterWriteNanos > 0L;
   }

   boolean expiresAfterAccess() {
      return this.expireAfterAccessNanos > 0L;
   }

   boolean usesKeyReferences() {
      return this.keyStrength != MapMakerInternalMap.Strength.STRONG;
   }

   boolean usesValueReferences() {
      return this.valueStrength != MapMakerInternalMap.Strength.STRONG;
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

   private Segment createSegment(int initialCapacity, int maxSegmentSize) {
      return new Segment(this, initialCapacity, maxSegmentSize);
   }

   private Object getLiveValue(ReferenceEntry entry) {
      if (entry.getKey() == null) {
         return null;
      } else {
         V value = (V)entry.getValueReference().get();
         if (value == null) {
            return null;
         } else {
            return this.expires() && this.isExpired(entry) ? null : value;
         }
      }
   }

   boolean isExpired(ReferenceEntry entry) {
      return this.isExpired(entry, this.ticker.read());
   }

   boolean isExpired(ReferenceEntry entry, long now) {
      return now - entry.getExpirationTime() > 0L;
   }

   void processPendingNotifications() {
      MapMaker.RemovalNotification<K, V> notification;
      while((notification = (MapMaker.RemovalNotification)this.removalNotificationQueue.poll()) != null) {
         try {
            this.removalListener.onRemoval(notification);
         } catch (Exception e) {
            logger.log(Level.WARNING, "Exception thrown by removal listener", e);
         }
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

   public int size() {
      Segment<K, V>[] segments = this.segments;
      long sum = 0L;

      for(int i = 0; i < segments.length; ++i) {
         sum += (long)segments[i].count;
      }

      return Ints.saturatedCast(sum);
   }

   public Object get(Object key) {
      if (key == null) {
         return null;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).get(key, hash);
      }
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
         Segment<K, V>[] segments = this.segments;
         long last = -1L;

         for(int i = 0; i < 3; ++i) {
            long sum = 0L;

            for(Segment segment : segments) {
               int c = segment.count;
               AtomicReferenceArray<ReferenceEntry<K, V>> table = segment.table;

               for(int j = 0; j < table.length(); ++j) {
                  for(ReferenceEntry<K, V> e = (ReferenceEntry)table.get(j); e != null; e = e.getNext()) {
                     V v = (V)segment.getLiveValue(e);
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
      return ks != null ? ks : (this.keySet = new KeySet());
   }

   public Collection values() {
      Collection<V> vs = this.values;
      return vs != null ? vs : (this.values = new Values());
   }

   public Set entrySet() {
      Set<Map.Entry<K, V>> es = this.entrySet;
      return es != null ? es : (this.entrySet = new EntrySet());
   }

   static enum Strength {
      STRONG {
         ValueReference referenceValue(Segment segment, ReferenceEntry entry, Object value) {
            return new StrongValueReference(value);
         }

         Equivalence defaultEquivalence() {
            return Equivalence.equals();
         }
      };

      private Strength() {
      }

      abstract ValueReference referenceValue(Segment var1, ReferenceEntry var2, Object var3);

      abstract Equivalence defaultEquivalence();
   }

   static enum EntryFactory {
      STRONG {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new StrongEntry(key, hash, next);
         }
      },
      STRONG_EXPIRABLE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new StrongExpirableEntry(key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyExpirableEntry(original, newEntry);
            return newEntry;
         }
      },
      STRONG_EVICTABLE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new StrongEvictableEntry(key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyEvictableEntry(original, newEntry);
            return newEntry;
         }
      },
      STRONG_EXPIRABLE_EVICTABLE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new StrongExpirableEvictableEntry(key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyExpirableEntry(original, newEntry);
            this.copyEvictableEntry(original, newEntry);
            return newEntry;
         }
      },
      WEAK {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new WeakEntry(segment.keyReferenceQueue, key, hash, next);
         }
      },
      WEAK_EXPIRABLE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new WeakExpirableEntry(segment.keyReferenceQueue, key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyExpirableEntry(original, newEntry);
            return newEntry;
         }
      },
      WEAK_EVICTABLE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new WeakEvictableEntry(segment.keyReferenceQueue, key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyEvictableEntry(original, newEntry);
            return newEntry;
         }
      },
      WEAK_EXPIRABLE_EVICTABLE {
         ReferenceEntry newEntry(Segment segment, Object key, int hash, ReferenceEntry next) {
            return new WeakExpirableEvictableEntry(segment.keyReferenceQueue, key, hash, next);
         }

         ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            this.copyExpirableEntry(original, newEntry);
            this.copyEvictableEntry(original, newEntry);
            return newEntry;
         }
      };

      static final int EXPIRABLE_MASK = 1;
      static final int EVICTABLE_MASK = 2;
      static final EntryFactory[][] factories = new EntryFactory[][]{{STRONG, STRONG_EXPIRABLE, STRONG_EVICTABLE, STRONG_EXPIRABLE_EVICTABLE}, new EntryFactory[0], {WEAK, WEAK_EXPIRABLE, WEAK_EVICTABLE, WEAK_EXPIRABLE_EVICTABLE}};

      private EntryFactory() {
      }

      static EntryFactory getFactory(Strength keyStrength, boolean expireAfterWrite, boolean evictsBySize) {
         int flags = (expireAfterWrite ? 1 : 0) | (evictsBySize ? 2 : 0);
         return factories[keyStrength.ordinal()][flags];
      }

      abstract ReferenceEntry newEntry(Segment var1, Object var2, int var3, ReferenceEntry var4);

      ReferenceEntry copyEntry(Segment segment, ReferenceEntry original, ReferenceEntry newNext) {
         return this.newEntry(segment, original.getKey(), original.getHash(), newNext);
      }

      void copyExpirableEntry(ReferenceEntry original, ReferenceEntry newEntry) {
         newEntry.setExpirationTime(original.getExpirationTime());
         MapMakerInternalMap.connectExpirables(original.getPreviousExpirable(), newEntry);
         MapMakerInternalMap.connectExpirables(newEntry, original.getNextExpirable());
         MapMakerInternalMap.nullifyExpirable(original);
      }

      void copyEvictableEntry(ReferenceEntry original, ReferenceEntry newEntry) {
         MapMakerInternalMap.connectEvictables(original.getPreviousEvictable(), newEntry);
         MapMakerInternalMap.connectEvictables(newEntry, original.getNextEvictable());
         MapMakerInternalMap.nullifyEvictable(original);
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

      public long getExpirationTime() {
         return 0L;
      }

      public void setExpirationTime(long time) {
      }

      public ReferenceEntry getNextExpirable() {
         return this;
      }

      public void setNextExpirable(ReferenceEntry next) {
      }

      public ReferenceEntry getPreviousExpirable() {
         return this;
      }

      public void setPreviousExpirable(ReferenceEntry previous) {
      }

      public ReferenceEntry getNextEvictable() {
         return this;
      }

      public void setNextEvictable(ReferenceEntry next) {
      }

      public ReferenceEntry getPreviousEvictable() {
         return this;
      }

      public void setPreviousEvictable(ReferenceEntry previous) {
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

      public long getExpirationTime() {
         throw new UnsupportedOperationException();
      }

      public void setExpirationTime(long time) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextExpirable() {
         throw new UnsupportedOperationException();
      }

      public void setNextExpirable(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousExpirable() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousExpirable(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextEvictable() {
         throw new UnsupportedOperationException();
      }

      public void setNextEvictable(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousEvictable() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousEvictable(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }
   }

   static class StrongEntry implements ReferenceEntry {
      final Object key;
      final int hash;
      final ReferenceEntry next;
      volatile ValueReference valueReference = MapMakerInternalMap.unset();

      StrongEntry(Object key, int hash, ReferenceEntry next) {
         this.key = key;
         this.hash = hash;
         this.next = next;
      }

      public Object getKey() {
         return this.key;
      }

      public long getExpirationTime() {
         throw new UnsupportedOperationException();
      }

      public void setExpirationTime(long time) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextExpirable() {
         throw new UnsupportedOperationException();
      }

      public void setNextExpirable(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousExpirable() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousExpirable(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextEvictable() {
         throw new UnsupportedOperationException();
      }

      public void setNextEvictable(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousEvictable() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousEvictable(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }

      public ValueReference getValueReference() {
         return this.valueReference;
      }

      public void setValueReference(ValueReference valueReference) {
         ValueReference<K, V> previous = this.valueReference;
         this.valueReference = valueReference;
         previous.clear(valueReference);
      }

      public int getHash() {
         return this.hash;
      }

      public ReferenceEntry getNext() {
         return this.next;
      }
   }

   static final class StrongExpirableEntry extends StrongEntry implements ReferenceEntry {
      volatile long time = Long.MAX_VALUE;
      ReferenceEntry nextExpirable = MapMakerInternalMap.nullEntry();
      ReferenceEntry previousExpirable = MapMakerInternalMap.nullEntry();

      StrongExpirableEntry(Object key, int hash, ReferenceEntry next) {
         super(key, hash, next);
      }

      public long getExpirationTime() {
         return this.time;
      }

      public void setExpirationTime(long time) {
         this.time = time;
      }

      public ReferenceEntry getNextExpirable() {
         return this.nextExpirable;
      }

      public void setNextExpirable(ReferenceEntry next) {
         this.nextExpirable = next;
      }

      public ReferenceEntry getPreviousExpirable() {
         return this.previousExpirable;
      }

      public void setPreviousExpirable(ReferenceEntry previous) {
         this.previousExpirable = previous;
      }
   }

   static final class StrongEvictableEntry extends StrongEntry implements ReferenceEntry {
      ReferenceEntry nextEvictable = MapMakerInternalMap.nullEntry();
      ReferenceEntry previousEvictable = MapMakerInternalMap.nullEntry();

      StrongEvictableEntry(Object key, int hash, ReferenceEntry next) {
         super(key, hash, next);
      }

      public ReferenceEntry getNextEvictable() {
         return this.nextEvictable;
      }

      public void setNextEvictable(ReferenceEntry next) {
         this.nextEvictable = next;
      }

      public ReferenceEntry getPreviousEvictable() {
         return this.previousEvictable;
      }

      public void setPreviousEvictable(ReferenceEntry previous) {
         this.previousEvictable = previous;
      }
   }

   static final class StrongExpirableEvictableEntry extends StrongEntry implements ReferenceEntry {
      volatile long time = Long.MAX_VALUE;
      ReferenceEntry nextExpirable = MapMakerInternalMap.nullEntry();
      ReferenceEntry previousExpirable = MapMakerInternalMap.nullEntry();
      ReferenceEntry nextEvictable = MapMakerInternalMap.nullEntry();
      ReferenceEntry previousEvictable = MapMakerInternalMap.nullEntry();

      StrongExpirableEvictableEntry(Object key, int hash, ReferenceEntry next) {
         super(key, hash, next);
      }

      public long getExpirationTime() {
         return this.time;
      }

      public void setExpirationTime(long time) {
         this.time = time;
      }

      public ReferenceEntry getNextExpirable() {
         return this.nextExpirable;
      }

      public void setNextExpirable(ReferenceEntry next) {
         this.nextExpirable = next;
      }

      public ReferenceEntry getPreviousExpirable() {
         return this.previousExpirable;
      }

      public void setPreviousExpirable(ReferenceEntry previous) {
         this.previousExpirable = previous;
      }

      public ReferenceEntry getNextEvictable() {
         return this.nextEvictable;
      }

      public void setNextEvictable(ReferenceEntry next) {
         this.nextEvictable = next;
      }

      public ReferenceEntry getPreviousEvictable() {
         return this.previousEvictable;
      }

      public void setPreviousEvictable(ReferenceEntry previous) {
         this.previousEvictable = previous;
      }
   }

   static class WeakEntry extends WeakReference implements ReferenceEntry {
      final int hash;
      final ReferenceEntry next;
      volatile ValueReference valueReference = MapMakerInternalMap.unset();

      WeakEntry(ReferenceQueue queue, Object key, int hash, ReferenceEntry next) {
         super(key, queue);
         this.hash = hash;
         this.next = next;
      }

      public Object getKey() {
         return this.get();
      }

      public long getExpirationTime() {
         throw new UnsupportedOperationException();
      }

      public void setExpirationTime(long time) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextExpirable() {
         throw new UnsupportedOperationException();
      }

      public void setNextExpirable(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousExpirable() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousExpirable(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getNextEvictable() {
         throw new UnsupportedOperationException();
      }

      public void setNextEvictable(ReferenceEntry next) {
         throw new UnsupportedOperationException();
      }

      public ReferenceEntry getPreviousEvictable() {
         throw new UnsupportedOperationException();
      }

      public void setPreviousEvictable(ReferenceEntry previous) {
         throw new UnsupportedOperationException();
      }

      public ValueReference getValueReference() {
         return this.valueReference;
      }

      public void setValueReference(ValueReference valueReference) {
         ValueReference<K, V> previous = this.valueReference;
         this.valueReference = valueReference;
         previous.clear(valueReference);
      }

      public int getHash() {
         return this.hash;
      }

      public ReferenceEntry getNext() {
         return this.next;
      }
   }

   static final class WeakExpirableEntry extends WeakEntry implements ReferenceEntry {
      volatile long time = Long.MAX_VALUE;
      ReferenceEntry nextExpirable = MapMakerInternalMap.nullEntry();
      ReferenceEntry previousExpirable = MapMakerInternalMap.nullEntry();

      WeakExpirableEntry(ReferenceQueue queue, Object key, int hash, ReferenceEntry next) {
         super(queue, key, hash, next);
      }

      public long getExpirationTime() {
         return this.time;
      }

      public void setExpirationTime(long time) {
         this.time = time;
      }

      public ReferenceEntry getNextExpirable() {
         return this.nextExpirable;
      }

      public void setNextExpirable(ReferenceEntry next) {
         this.nextExpirable = next;
      }

      public ReferenceEntry getPreviousExpirable() {
         return this.previousExpirable;
      }

      public void setPreviousExpirable(ReferenceEntry previous) {
         this.previousExpirable = previous;
      }
   }

   static final class WeakEvictableEntry extends WeakEntry implements ReferenceEntry {
      ReferenceEntry nextEvictable = MapMakerInternalMap.nullEntry();
      ReferenceEntry previousEvictable = MapMakerInternalMap.nullEntry();

      WeakEvictableEntry(ReferenceQueue queue, Object key, int hash, ReferenceEntry next) {
         super(queue, key, hash, next);
      }

      public ReferenceEntry getNextEvictable() {
         return this.nextEvictable;
      }

      public void setNextEvictable(ReferenceEntry next) {
         this.nextEvictable = next;
      }

      public ReferenceEntry getPreviousEvictable() {
         return this.previousEvictable;
      }

      public void setPreviousEvictable(ReferenceEntry previous) {
         this.previousEvictable = previous;
      }
   }

   static final class WeakExpirableEvictableEntry extends WeakEntry implements ReferenceEntry {
      volatile long time = Long.MAX_VALUE;
      ReferenceEntry nextExpirable = MapMakerInternalMap.nullEntry();
      ReferenceEntry previousExpirable = MapMakerInternalMap.nullEntry();
      ReferenceEntry nextEvictable = MapMakerInternalMap.nullEntry();
      ReferenceEntry previousEvictable = MapMakerInternalMap.nullEntry();

      WeakExpirableEvictableEntry(ReferenceQueue queue, Object key, int hash, ReferenceEntry next) {
         super(queue, key, hash, next);
      }

      public long getExpirationTime() {
         return this.time;
      }

      public void setExpirationTime(long time) {
         this.time = time;
      }

      public ReferenceEntry getNextExpirable() {
         return this.nextExpirable;
      }

      public void setNextExpirable(ReferenceEntry next) {
         this.nextExpirable = next;
      }

      public ReferenceEntry getPreviousExpirable() {
         return this.previousExpirable;
      }

      public void setPreviousExpirable(ReferenceEntry previous) {
         this.previousExpirable = previous;
      }

      public ReferenceEntry getNextEvictable() {
         return this.nextEvictable;
      }

      public void setNextEvictable(ReferenceEntry next) {
         this.nextEvictable = next;
      }

      public ReferenceEntry getPreviousEvictable() {
         return this.previousEvictable;
      }

      public void setPreviousEvictable(ReferenceEntry previous) {
         this.previousEvictable = previous;
      }
   }

   static final class StrongValueReference implements ValueReference {
      final Object referent;

      StrongValueReference(Object referent) {
         this.referent = referent;
      }

      public Object get() {
         return this.referent;
      }

      public ReferenceEntry getEntry() {
         return null;
      }

      public ValueReference copyFor(ReferenceQueue queue, Object value, ReferenceEntry entry) {
         return this;
      }

      public boolean isComputingReference() {
         return false;
      }

      public void clear(ValueReference newValue) {
      }
   }

   static class Segment extends ReentrantLock {
      final MapMakerInternalMap map;
      final int maxSegmentSize;
      final ReferenceQueue keyReferenceQueue;
      final ReferenceQueue valueReferenceQueue;
      final Queue recencyQueue;
      final AtomicInteger readCount = new AtomicInteger();
      final Queue evictionQueue;
      final Queue expirationQueue;
      volatile int count;
      int modCount;
      int threshold;
      volatile AtomicReferenceArray table;

      Segment(MapMakerInternalMap map, int initialCapacity, int maxSegmentSize) {
         this.map = map;
         this.maxSegmentSize = maxSegmentSize;
         this.initTable(this.newEntryArray(initialCapacity));
         this.keyReferenceQueue = map.usesKeyReferences() ? new ReferenceQueue() : null;
         this.valueReferenceQueue = map.usesValueReferences() ? new ReferenceQueue() : null;
         this.recencyQueue = (Queue)(!map.evictsBySize() && !map.expiresAfterAccess() ? MapMakerInternalMap.discardingQueue() : new ConcurrentLinkedQueue());
         this.evictionQueue = (Queue)(map.evictsBySize() ? new EvictionQueue() : MapMakerInternalMap.discardingQueue());
         this.expirationQueue = (Queue)(map.expires() ? new ExpirationQueue() : MapMakerInternalMap.discardingQueue());
      }

      AtomicReferenceArray newEntryArray(int size) {
         return new AtomicReferenceArray(size);
      }

      void initTable(AtomicReferenceArray newTable) {
         this.threshold = newTable.length() * 3 / 4;
         if (this.threshold == this.maxSegmentSize) {
            ++this.threshold;
         }

         this.table = newTable;
      }

      ReferenceEntry newEntry(Object key, int hash, ReferenceEntry next) {
         return this.map.entryFactory.newEntry(this, key, hash, next);
      }

      ReferenceEntry copyEntry(ReferenceEntry original, ReferenceEntry newNext) {
         if (original.getKey() == null) {
            return null;
         } else {
            ValueReference<K, V> valueReference = original.getValueReference();
            V value = (V)valueReference.get();
            if (value == null && !valueReference.isComputingReference()) {
               return null;
            } else {
               ReferenceEntry<K, V> newEntry = this.map.entryFactory.copyEntry(this, original, newNext);
               newEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, value, newEntry));
               return newEntry;
            }
         }
      }

      void setValue(ReferenceEntry entry, Object value) {
         ValueReference<K, V> valueReference = this.map.valueStrength.referenceValue(this, entry, value);
         entry.setValueReference(valueReference);
         this.recordWrite(entry);
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

      void recordRead(ReferenceEntry entry) {
         if (this.map.expiresAfterAccess()) {
            this.recordExpirationTime(entry, this.map.expireAfterAccessNanos);
         }

         this.recencyQueue.add(entry);
      }

      void recordLockedRead(ReferenceEntry entry) {
         this.evictionQueue.add(entry);
         if (this.map.expiresAfterAccess()) {
            this.recordExpirationTime(entry, this.map.expireAfterAccessNanos);
            this.expirationQueue.add(entry);
         }

      }

      void recordWrite(ReferenceEntry entry) {
         this.drainRecencyQueue();
         this.evictionQueue.add(entry);
         if (this.map.expires()) {
            long expiration = this.map.expiresAfterAccess() ? this.map.expireAfterAccessNanos : this.map.expireAfterWriteNanos;
            this.recordExpirationTime(entry, expiration);
            this.expirationQueue.add(entry);
         }

      }

      void drainRecencyQueue() {
         ReferenceEntry<K, V> e;
         while((e = (ReferenceEntry)this.recencyQueue.poll()) != null) {
            if (this.evictionQueue.contains(e)) {
               this.evictionQueue.add(e);
            }

            if (this.map.expiresAfterAccess() && this.expirationQueue.contains(e)) {
               this.expirationQueue.add(e);
            }
         }

      }

      void recordExpirationTime(ReferenceEntry entry, long expirationNanos) {
         entry.setExpirationTime(this.map.ticker.read() + expirationNanos);
      }

      void tryExpireEntries() {
         if (this.tryLock()) {
            try {
               this.expireEntries();
            } finally {
               this.unlock();
            }
         }

      }

      void expireEntries() {
         this.drainRecencyQueue();
         if (!this.expirationQueue.isEmpty()) {
            long now = this.map.ticker.read();

            ReferenceEntry<K, V> e;
            while((e = (ReferenceEntry)this.expirationQueue.peek()) != null && this.map.isExpired(e, now)) {
               if (!this.removeEntry(e, e.getHash(), MapMaker.RemovalCause.EXPIRED)) {
                  throw new AssertionError();
               }
            }

         }
      }

      void enqueueNotification(ReferenceEntry entry, MapMaker.RemovalCause cause) {
         this.enqueueNotification(entry.getKey(), entry.getHash(), entry.getValueReference().get(), cause);
      }

      void enqueueNotification(Object key, int hash, Object value, MapMaker.RemovalCause cause) {
         if (this.map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE) {
            MapMaker.RemovalNotification<K, V> notification = new MapMaker.RemovalNotification(key, value, cause);
            this.map.removalNotificationQueue.offer(notification);
         }

      }

      boolean evictEntries() {
         if (this.map.evictsBySize() && this.count >= this.maxSegmentSize) {
            this.drainRecencyQueue();
            ReferenceEntry<K, V> e = (ReferenceEntry)this.evictionQueue.remove();
            if (!this.removeEntry(e, e.getHash(), MapMaker.RemovalCause.SIZE)) {
               throw new AssertionError();
            } else {
               return true;
            }
         } else {
            return false;
         }
      }

      ReferenceEntry getFirst(int hash) {
         AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
         return (ReferenceEntry)table.get(hash & table.length() - 1);
      }

      ReferenceEntry getEntry(Object key, int hash) {
         if (this.count != 0) {
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
         }

         return null;
      }

      ReferenceEntry getLiveEntry(Object key, int hash) {
         ReferenceEntry<K, V> e = this.getEntry(key, hash);
         if (e == null) {
            return null;
         } else if (this.map.expires() && this.map.isExpired(e)) {
            this.tryExpireEntries();
            return null;
         } else {
            return e;
         }
      }

      Object get(Object key, int hash) {
         V value;
         try {
            ReferenceEntry<K, V> e = this.getLiveEntry(key, hash);
            if (e != null) {
               value = (V)e.getValueReference().get();
               if (value != null) {
                  this.recordRead(e);
               } else {
                  this.tryDrainReferenceQueues();
               }

               Object var5 = value;
               return var5;
            }

            value = (V)null;
         } finally {
            this.postReadCleanup();
         }

         return value;
      }

      boolean containsKey(Object key, int hash) {
         boolean var4;
         try {
            if (this.count == 0) {
               boolean var8 = false;
               return var8;
            }

            ReferenceEntry<K, V> e = this.getLiveEntry(key, hash);
            if (e != null) {
               var4 = e.getValueReference().get() != null;
               return var4;
            }

            var4 = false;
         } finally {
            this.postReadCleanup();
         }

         return var4;
      }

      Object put(Object key, int hash, Object value, boolean onlyIfAbsent) {
         this.lock();

         try {
            this.preWriteCleanup();
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
                        this.recordLockedRead(e);
                        Object var20 = entryValue;
                        return var20;
                     }

                     ++this.modCount;
                     this.enqueueNotification(key, hash, entryValue, MapMaker.RemovalCause.REPLACED);
                     this.setValue(e, value);
                     Object var19 = entryValue;
                     return var19;
                  }

                  ++this.modCount;
                  this.setValue(e, value);
                  if (!valueReference.isComputingReference()) {
                     this.enqueueNotification(key, hash, entryValue, MapMaker.RemovalCause.COLLECTED);
                     newCount = this.count;
                  } else if (this.evictEntries()) {
                     newCount = this.count + 1;
                  }

                  this.count = newCount;
                  Object var13 = null;
                  return var13;
               }
            }

            ++this.modCount;
            ReferenceEntry<K, V> newEntry = this.newEntry(key, hash, first);
            this.setValue(newEntry, value);
            table.set(index, newEntry);
            if (this.evictEntries()) {
               newCount = this.count + 1;
            }

            this.count = newCount;
            Object var18 = null;
            return var18;
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
            this.preWriteCleanup();
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
                        this.enqueueNotification(key, hash, entryValue, MapMaker.RemovalCause.REPLACED);
                        this.setValue(e, newValue);
                        boolean var21 = true;
                        return var21;
                     }

                     this.recordLockedRead(e);
                     boolean var20 = false;
                     return var20;
                  }

                  if (this.isCollected(valueReference)) {
                     int newCount = this.count - 1;
                     ++this.modCount;
                     this.enqueueNotification(entryKey, hash, entryValue, MapMaker.RemovalCause.COLLECTED);
                     ReferenceEntry<K, V> newFirst = this.removeFromChain(first, e);
                     newCount = this.count - 1;
                     table.set(index, newFirst);
                     this.count = newCount;
                  }

                  boolean var19 = false;
                  return var19;
               }
            }

            boolean var17 = false;
            return var17;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      Object replace(Object key, int hash, Object newValue) {
         this.lock();

         try {
            this.preWriteCleanup();
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
                     this.enqueueNotification(key, hash, entryValue, MapMaker.RemovalCause.REPLACED);
                     this.setValue(e, newValue);
                     Object var19 = entryValue;
                     return var19;
                  }

                  if (this.isCollected(valueReference)) {
                     int newCount = this.count - 1;
                     ++this.modCount;
                     this.enqueueNotification(entryKey, hash, entryValue, MapMaker.RemovalCause.COLLECTED);
                     ReferenceEntry<K, V> newFirst = this.removeFromChain(first, e);
                     newCount = this.count - 1;
                     table.set(index, newFirst);
                     this.count = newCount;
                  }

                  Object var18 = null;
                  return var18;
               }
            }

            Object var16 = null;
            return var16;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      Object remove(Object key, int hash) {
         this.lock();

         try {
            this.preWriteCleanup();
            int newCount = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> valueReference = e.getValueReference();
                  V entryValue = (V)valueReference.get();
                  MapMaker.RemovalCause cause;
                  if (entryValue != null) {
                     cause = MapMaker.RemovalCause.EXPLICIT;
                  } else {
                     if (!this.isCollected(valueReference)) {
                        Object var19 = null;
                        return var19;
                     }

                     cause = MapMaker.RemovalCause.COLLECTED;
                  }

                  ++this.modCount;
                  this.enqueueNotification(entryKey, hash, entryValue, cause);
                  ReferenceEntry<K, V> newFirst = this.removeFromChain(first, e);
                  newCount = this.count - 1;
                  table.set(index, newFirst);
                  this.count = newCount;
                  Object var13 = entryValue;
                  return var13;
               }
            }

            Object var18 = null;
            return var18;
         } finally {
            this.unlock();
            this.postWriteCleanup();
         }
      }

      boolean remove(Object key, int hash, Object value) {
         this.lock();

         try {
            this.preWriteCleanup();
            int newCount = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & table.length() - 1;
            ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

            for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  ValueReference<K, V> valueReference = e.getValueReference();
                  V entryValue = (V)valueReference.get();
                  MapMaker.RemovalCause cause;
                  if (this.map.valueEquivalence.equivalent(value, entryValue)) {
                     cause = MapMaker.RemovalCause.EXPLICIT;
                  } else {
                     if (!this.isCollected(valueReference)) {
                        boolean var20 = false;
                        return var20;
                     }

                     cause = MapMaker.RemovalCause.COLLECTED;
                  }

                  ++this.modCount;
                  this.enqueueNotification(entryKey, hash, entryValue, cause);
                  ReferenceEntry<K, V> newFirst = this.removeFromChain(first, e);
                  newCount = this.count - 1;
                  table.set(index, newFirst);
                  this.count = newCount;
                  boolean var14 = cause == MapMaker.RemovalCause.EXPLICIT;
                  return var14;
               }
            }

            boolean var19 = false;
            return var19;
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
               if (this.map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE) {
                  for(int i = 0; i < table.length(); ++i) {
                     for(ReferenceEntry<K, V> e = (ReferenceEntry)table.get(i); e != null; e = e.getNext()) {
                        if (!e.getValueReference().isComputingReference()) {
                           this.enqueueNotification(e, MapMaker.RemovalCause.EXPLICIT);
                        }
                     }
                  }
               }

               for(int i = 0; i < table.length(); ++i) {
                  table.set(i, (Object)null);
               }

               this.clearReferenceQueues();
               this.evictionQueue.clear();
               this.expirationQueue.clear();
               this.readCount.set(0);
               ++this.modCount;
               this.count = 0;
            } finally {
               this.unlock();
               this.postWriteCleanup();
            }
         }

      }

      ReferenceEntry removeFromChain(ReferenceEntry first, ReferenceEntry entry) {
         this.evictionQueue.remove(entry);
         this.expirationQueue.remove(entry);
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
         this.enqueueNotification(entry, MapMaker.RemovalCause.COLLECTED);
         this.evictionQueue.remove(entry);
         this.expirationQueue.remove(entry);
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
                  this.enqueueNotification(e.getKey(), hash, e.getValueReference().get(), MapMaker.RemovalCause.COLLECTED);
                  ReferenceEntry<K, V> newFirst = this.removeFromChain(first, e);
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
                  this.enqueueNotification(key, hash, valueReference.get(), MapMaker.RemovalCause.COLLECTED);
                  ReferenceEntry<K, V> newFirst = this.removeFromChain(first, e);
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

      boolean removeEntry(ReferenceEntry entry, int hash, MapMaker.RemovalCause cause) {
         int newCount = this.count - 1;
         AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
         int index = hash & table.length() - 1;
         ReferenceEntry<K, V> first = (ReferenceEntry)table.get(index);

         for(ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
            if (e == entry) {
               ++this.modCount;
               this.enqueueNotification(e.getKey(), hash, e.getValueReference().get(), cause);
               ReferenceEntry<K, V> newFirst = this.removeFromChain(first, e);
               newCount = this.count - 1;
               table.set(index, newFirst);
               this.count = newCount;
               return true;
            }
         }

         return false;
      }

      boolean isCollected(ValueReference valueReference) {
         if (valueReference.isComputingReference()) {
            return false;
         } else {
            return valueReference.get() == null;
         }
      }

      Object getLiveValue(ReferenceEntry entry) {
         if (entry.getKey() == null) {
            this.tryDrainReferenceQueues();
            return null;
         } else {
            V value = (V)entry.getValueReference().get();
            if (value == null) {
               this.tryDrainReferenceQueues();
               return null;
            } else if (this.map.expires() && this.map.isExpired(entry)) {
               this.tryExpireEntries();
               return null;
            } else {
               return value;
            }
         }
      }

      void postReadCleanup() {
         if ((this.readCount.incrementAndGet() & 63) == 0) {
            this.runCleanup();
         }

      }

      void preWriteCleanup() {
         this.runLockedCleanup();
      }

      void postWriteCleanup() {
         this.runUnlockedCleanup();
      }

      void runCleanup() {
         this.runLockedCleanup();
         this.runUnlockedCleanup();
      }

      void runLockedCleanup() {
         if (this.tryLock()) {
            try {
               this.drainReferenceQueues();
               this.expireEntries();
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

   static final class EvictionQueue extends AbstractQueue {
      final ReferenceEntry head = new AbstractReferenceEntry() {
         ReferenceEntry nextEvictable = this;
         ReferenceEntry previousEvictable = this;

         public ReferenceEntry getNextEvictable() {
            return this.nextEvictable;
         }

         public void setNextEvictable(ReferenceEntry next) {
            this.nextEvictable = next;
         }

         public ReferenceEntry getPreviousEvictable() {
            return this.previousEvictable;
         }

         public void setPreviousEvictable(ReferenceEntry previous) {
            this.previousEvictable = previous;
         }
      };

      public boolean offer(ReferenceEntry entry) {
         MapMakerInternalMap.connectEvictables(entry.getPreviousEvictable(), entry.getNextEvictable());
         MapMakerInternalMap.connectEvictables(this.head.getPreviousEvictable(), entry);
         MapMakerInternalMap.connectEvictables(entry, this.head);
         return true;
      }

      public ReferenceEntry peek() {
         ReferenceEntry<K, V> next = this.head.getNextEvictable();
         return next == this.head ? null : next;
      }

      public ReferenceEntry poll() {
         ReferenceEntry<K, V> next = this.head.getNextEvictable();
         if (next == this.head) {
            return null;
         } else {
            this.remove(next);
            return next;
         }
      }

      public boolean remove(Object o) {
         ReferenceEntry<K, V> e = (ReferenceEntry)o;
         ReferenceEntry<K, V> previous = e.getPreviousEvictable();
         ReferenceEntry<K, V> next = e.getNextEvictable();
         MapMakerInternalMap.connectEvictables(previous, next);
         MapMakerInternalMap.nullifyEvictable(e);
         return next != MapMakerInternalMap.NullEntry.INSTANCE;
      }

      public boolean contains(Object o) {
         ReferenceEntry<K, V> e = (ReferenceEntry)o;
         return e.getNextEvictable() != MapMakerInternalMap.NullEntry.INSTANCE;
      }

      public boolean isEmpty() {
         return this.head.getNextEvictable() == this.head;
      }

      public int size() {
         int size = 0;

         for(ReferenceEntry<K, V> e = this.head.getNextEvictable(); e != this.head; e = e.getNextEvictable()) {
            ++size;
         }

         return size;
      }

      public void clear() {
         ReferenceEntry<K, V> next;
         for(ReferenceEntry<K, V> e = this.head.getNextEvictable(); e != this.head; e = next) {
            next = e.getNextEvictable();
            MapMakerInternalMap.nullifyEvictable(e);
         }

         this.head.setNextEvictable(this.head);
         this.head.setPreviousEvictable(this.head);
      }

      public Iterator iterator() {
         return new AbstractSequentialIterator(this.peek()) {
            protected ReferenceEntry computeNext(ReferenceEntry previous) {
               ReferenceEntry<K, V> next = previous.getNextEvictable();
               return next == EvictionQueue.this.head ? null : next;
            }
         };
      }
   }

   static final class ExpirationQueue extends AbstractQueue {
      final ReferenceEntry head = new AbstractReferenceEntry() {
         ReferenceEntry nextExpirable = this;
         ReferenceEntry previousExpirable = this;

         public long getExpirationTime() {
            return Long.MAX_VALUE;
         }

         public void setExpirationTime(long time) {
         }

         public ReferenceEntry getNextExpirable() {
            return this.nextExpirable;
         }

         public void setNextExpirable(ReferenceEntry next) {
            this.nextExpirable = next;
         }

         public ReferenceEntry getPreviousExpirable() {
            return this.previousExpirable;
         }

         public void setPreviousExpirable(ReferenceEntry previous) {
            this.previousExpirable = previous;
         }
      };

      public boolean offer(ReferenceEntry entry) {
         MapMakerInternalMap.connectExpirables(entry.getPreviousExpirable(), entry.getNextExpirable());
         MapMakerInternalMap.connectExpirables(this.head.getPreviousExpirable(), entry);
         MapMakerInternalMap.connectExpirables(entry, this.head);
         return true;
      }

      public ReferenceEntry peek() {
         ReferenceEntry<K, V> next = this.head.getNextExpirable();
         return next == this.head ? null : next;
      }

      public ReferenceEntry poll() {
         ReferenceEntry<K, V> next = this.head.getNextExpirable();
         if (next == this.head) {
            return null;
         } else {
            this.remove(next);
            return next;
         }
      }

      public boolean remove(Object o) {
         ReferenceEntry<K, V> e = (ReferenceEntry)o;
         ReferenceEntry<K, V> previous = e.getPreviousExpirable();
         ReferenceEntry<K, V> next = e.getNextExpirable();
         MapMakerInternalMap.connectExpirables(previous, next);
         MapMakerInternalMap.nullifyExpirable(e);
         return next != MapMakerInternalMap.NullEntry.INSTANCE;
      }

      public boolean contains(Object o) {
         ReferenceEntry<K, V> e = (ReferenceEntry)o;
         return e.getNextExpirable() != MapMakerInternalMap.NullEntry.INSTANCE;
      }

      public boolean isEmpty() {
         return this.head.getNextExpirable() == this.head;
      }

      public int size() {
         int size = 0;

         for(ReferenceEntry<K, V> e = this.head.getNextExpirable(); e != this.head; e = e.getNextExpirable()) {
            ++size;
         }

         return size;
      }

      public void clear() {
         ReferenceEntry<K, V> next;
         for(ReferenceEntry<K, V> e = this.head.getNextExpirable(); e != this.head; e = next) {
            next = e.getNextExpirable();
            MapMakerInternalMap.nullifyExpirable(e);
         }

         this.head.setNextExpirable(this.head);
         this.head.setPreviousExpirable(this.head);
      }

      public Iterator iterator() {
         return new AbstractSequentialIterator(this.peek()) {
            protected ReferenceEntry computeNext(ReferenceEntry previous) {
               ReferenceEntry<K, V> next = previous.getNextExpirable();
               return next == ExpirationQueue.this.head ? null : next;
            }
         };
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
         this.nextSegmentIndex = MapMakerInternalMap.this.segments.length - 1;
         this.nextTableIndex = -1;
         this.advance();
      }

      public abstract Object next();

      final void advance() {
         this.nextExternal = null;
         if (!this.nextInChain()) {
            if (!this.nextInTable()) {
               while(this.nextSegmentIndex >= 0) {
                  this.currentSegment = MapMakerInternalMap.this.segments[this.nextSegmentIndex--];
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
         boolean var4;
         try {
            K key = (K)entry.getKey();
            V value = (V)MapMakerInternalMap.this.getLiveValue(entry);
            if (value == null) {
               var4 = false;
               return var4;
            }

            this.nextExternal = MapMakerInternalMap.this.new WriteThroughEntry(key, value);
            var4 = true;
         } finally {
            this.currentSegment.postReadCleanup();
         }

         return var4;
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
         CollectPreconditions.checkRemove(this.lastReturned != null);
         MapMakerInternalMap.this.remove(this.lastReturned.getKey());
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

   final class WriteThroughEntry extends AbstractMapEntry {
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
         V oldValue = (V)MapMakerInternalMap.this.put(this.key, newValue);
         this.value = newValue;
         return oldValue;
      }
   }

   final class EntryIterator extends HashIterator {
      public Map.Entry next() {
         return this.nextEntry();
      }
   }

   private final class KeySet extends AbstractSet {
      private KeySet() {
      }

      public Iterator iterator() {
         return MapMakerInternalMap.this.new KeyIterator();
      }

      public int size() {
         return MapMakerInternalMap.this.size();
      }

      public boolean isEmpty() {
         return MapMakerInternalMap.this.isEmpty();
      }

      public boolean contains(Object o) {
         return MapMakerInternalMap.this.containsKey(o);
      }

      public boolean remove(Object o) {
         return MapMakerInternalMap.this.remove(o) != null;
      }

      public void clear() {
         MapMakerInternalMap.this.clear();
      }
   }

   private final class Values extends AbstractCollection {
      private Values() {
      }

      public Iterator iterator() {
         return MapMakerInternalMap.this.new ValueIterator();
      }

      public int size() {
         return MapMakerInternalMap.this.size();
      }

      public boolean isEmpty() {
         return MapMakerInternalMap.this.isEmpty();
      }

      public boolean contains(Object o) {
         return MapMakerInternalMap.this.containsValue(o);
      }

      public void clear() {
         MapMakerInternalMap.this.clear();
      }
   }

   private final class EntrySet extends AbstractSet {
      private EntrySet() {
      }

      public Iterator iterator() {
         return MapMakerInternalMap.this.new EntryIterator();
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
               V v = (V)MapMakerInternalMap.this.get(key);
               return v != null && MapMakerInternalMap.this.valueEquivalence.equivalent(e.getValue(), v);
            }
         }
      }

      public boolean remove(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            Object key = e.getKey();
            return key != null && MapMakerInternalMap.this.remove(key, e.getValue());
         }
      }

      public int size() {
         return MapMakerInternalMap.this.size();
      }

      public boolean isEmpty() {
         return MapMakerInternalMap.this.isEmpty();
      }

      public void clear() {
         MapMakerInternalMap.this.clear();
      }
   }

   interface ReferenceEntry {
      ValueReference getValueReference();

      void setValueReference(ValueReference var1);

      ReferenceEntry getNext();

      int getHash();

      Object getKey();

      long getExpirationTime();

      void setExpirationTime(long var1);

      ReferenceEntry getNextExpirable();

      void setNextExpirable(ReferenceEntry var1);

      ReferenceEntry getPreviousExpirable();

      void setPreviousExpirable(ReferenceEntry var1);

      ReferenceEntry getNextEvictable();

      void setNextEvictable(ReferenceEntry var1);

      ReferenceEntry getPreviousEvictable();

      void setPreviousEvictable(ReferenceEntry var1);
   }

   interface ValueReference {
      Object get();

      ReferenceEntry getEntry();

      ValueReference copyFor(ReferenceQueue var1, Object var2, ReferenceEntry var3);

      void clear(ValueReference var1);

      boolean isComputingReference();
   }
}
