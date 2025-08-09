package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.CheckForNull;

@J2ktIncompatible
@GwtIncompatible
class MapMakerInternalMap extends AbstractMap implements ConcurrentMap, Serializable {
   static final int MAXIMUM_CAPACITY = 1073741824;
   static final int MAX_SEGMENTS = 65536;
   static final int CONTAINS_VALUE_RETRIES = 3;
   static final int DRAIN_THRESHOLD = 63;
   static final int DRAIN_MAX = 16;
   final transient int segmentMask;
   final transient int segmentShift;
   final transient Segment[] segments;
   final int concurrencyLevel;
   final Equivalence keyEquivalence;
   final transient InternalEntryHelper entryHelper;
   static final WeakValueReference UNSET_WEAK_VALUE_REFERENCE = new WeakValueReference() {
      @CheckForNull
      public DummyInternalEntry getEntry() {
         return null;
      }

      public void clear() {
      }

      @CheckForNull
      public Object get() {
         return null;
      }

      public WeakValueReference copyFor(ReferenceQueue queue, DummyInternalEntry entry) {
         return this;
      }
   };
   @LazyInit
   @CheckForNull
   transient Set keySet;
   @LazyInit
   @CheckForNull
   transient Collection values;
   @LazyInit
   @CheckForNull
   transient Set entrySet;
   private static final long serialVersionUID = 5L;

   private MapMakerInternalMap(MapMaker builder, InternalEntryHelper entryHelper) {
      this.concurrencyLevel = Math.min(builder.getConcurrencyLevel(), 65536);
      this.keyEquivalence = builder.getKeyEquivalence();
      this.entryHelper = entryHelper;
      int initialCapacity = Math.min(builder.getInitialCapacity(), 1073741824);
      int segmentShift = 0;

      int segmentCount;
      for(segmentCount = 1; segmentCount < this.concurrencyLevel; segmentCount <<= 1) {
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

      for(int i = 0; i < this.segments.length; ++i) {
         this.segments[i] = this.createSegment(segmentSize);
      }

   }

   static MapMakerInternalMap create(MapMaker builder) {
      if (builder.getKeyStrength() == MapMakerInternalMap.Strength.STRONG && builder.getValueStrength() == MapMakerInternalMap.Strength.STRONG) {
         return new MapMakerInternalMap(builder, MapMakerInternalMap.StrongKeyStrongValueEntry.Helper.instance());
      } else if (builder.getKeyStrength() == MapMakerInternalMap.Strength.STRONG && builder.getValueStrength() == MapMakerInternalMap.Strength.WEAK) {
         return new MapMakerInternalMap(builder, MapMakerInternalMap.StrongKeyWeakValueEntry.Helper.instance());
      } else if (builder.getKeyStrength() == MapMakerInternalMap.Strength.WEAK && builder.getValueStrength() == MapMakerInternalMap.Strength.STRONG) {
         return new MapMakerInternalMap(builder, MapMakerInternalMap.WeakKeyStrongValueEntry.Helper.instance());
      } else if (builder.getKeyStrength() == MapMakerInternalMap.Strength.WEAK && builder.getValueStrength() == MapMakerInternalMap.Strength.WEAK) {
         return new MapMakerInternalMap(builder, MapMakerInternalMap.WeakKeyWeakValueEntry.Helper.instance());
      } else {
         throw new AssertionError();
      }
   }

   static MapMakerInternalMap createWithDummyValues(MapMaker builder) {
      if (builder.getKeyStrength() == MapMakerInternalMap.Strength.STRONG && builder.getValueStrength() == MapMakerInternalMap.Strength.STRONG) {
         return new MapMakerInternalMap(builder, MapMakerInternalMap.StrongKeyDummyValueEntry.Helper.instance());
      } else if (builder.getKeyStrength() == MapMakerInternalMap.Strength.WEAK && builder.getValueStrength() == MapMakerInternalMap.Strength.STRONG) {
         return new MapMakerInternalMap(builder, MapMakerInternalMap.WeakKeyDummyValueEntry.Helper.instance());
      } else if (builder.getValueStrength() == MapMakerInternalMap.Strength.WEAK) {
         throw new IllegalArgumentException("Map cannot have both weak and dummy values");
      } else {
         throw new AssertionError();
      }
   }

   static WeakValueReference unsetWeakValueReference() {
      return UNSET_WEAK_VALUE_REFERENCE;
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
   InternalEntry copyEntry(InternalEntry original, InternalEntry newNext) {
      int hash = original.getHash();
      return this.segmentFor(hash).copyEntry(original, newNext);
   }

   int hash(Object key) {
      int h = this.keyEquivalence.hash(key);
      return rehash(h);
   }

   void reclaimValue(WeakValueReference valueReference) {
      E entry = (E)valueReference.getEntry();
      int hash = entry.getHash();
      this.segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
   }

   void reclaimKey(InternalEntry entry) {
      int hash = entry.getHash();
      this.segmentFor(hash).reclaimKey(entry, hash);
   }

   @VisibleForTesting
   boolean isLiveForTesting(InternalEntry entry) {
      return this.segmentFor(entry.getHash()).getLiveValueForTesting(entry) != null;
   }

   Segment segmentFor(int hash) {
      return this.segments[hash >>> this.segmentShift & this.segmentMask];
   }

   Segment createSegment(int initialCapacity) {
      return this.entryHelper.newSegment(this, initialCapacity);
   }

   @CheckForNull
   Object getLiveValue(InternalEntry entry) {
      return entry.getKey() == null ? null : entry.getValue();
   }

   final Segment[] newSegmentArray(int ssize) {
      return new Segment[ssize];
   }

   @VisibleForTesting
   Strength keyStrength() {
      return this.entryHelper.keyStrength();
   }

   @VisibleForTesting
   Strength valueStrength() {
      return this.entryHelper.valueStrength();
   }

   @VisibleForTesting
   Equivalence valueEquivalence() {
      return this.entryHelper.valueStrength().defaultEquivalence();
   }

   public boolean isEmpty() {
      long sum = 0L;
      Segment<K, V, E, S>[] segments = this.segments;

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

         return sum == 0L;
      } else {
         return true;
      }
   }

   public int size() {
      Segment<K, V, E, S>[] segments = this.segments;
      long sum = 0L;

      for(int i = 0; i < segments.length; ++i) {
         sum += (long)segments[i].count;
      }

      return Ints.saturatedCast(sum);
   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      if (key == null) {
         return null;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).get(key, hash);
      }
   }

   @CheckForNull
   InternalEntry getEntry(@CheckForNull Object key) {
      if (key == null) {
         return null;
      } else {
         int hash = this.hash(key);
         return this.segmentFor(hash).getEntry(key, hash);
      }
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
         Segment<K, V, E, S>[] segments = this.segments;
         long last = -1L;

         for(int i = 0; i < 3; ++i) {
            long sum = 0L;

            for(Segment segment : segments) {
               int unused = segment.count;
               AtomicReferenceArray<E> table = segment.table;

               for(int j = 0; j < table.length(); ++j) {
                  for(E e = (E)((InternalEntry)table.get(j)); e != null; e = (E)e.getNext()) {
                     V v = (V)segment.getLiveValue(e);
                     if (v != null && this.valueEquivalence().equivalent(value, v)) {
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
   @CanIgnoreReturnValue
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

   Object writeReplace() {
      return new SerializationProxy(this.entryHelper.keyStrength(), this.entryHelper.valueStrength(), this.keyEquivalence, this.entryHelper.valueStrength().defaultEquivalence(), this.concurrencyLevel, this);
   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream in) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializationProxy");
   }

   static enum Strength {
      STRONG {
         Equivalence defaultEquivalence() {
            return Equivalence.equals();
         }
      },
      WEAK {
         Equivalence defaultEquivalence() {
            return Equivalence.identity();
         }
      };

      private Strength() {
      }

      abstract Equivalence defaultEquivalence();

      // $FF: synthetic method
      private static Strength[] $values() {
         return new Strength[]{STRONG, WEAK};
      }
   }

   abstract static class AbstractStrongKeyEntry implements InternalEntry {
      final Object key;
      final int hash;

      AbstractStrongKeyEntry(Object key, int hash) {
         this.key = key;
         this.hash = hash;
      }

      public final Object getKey() {
         return this.key;
      }

      public final int getHash() {
         return this.hash;
      }

      @CheckForNull
      public InternalEntry getNext() {
         return null;
      }
   }

   static class StrongKeyStrongValueEntry extends AbstractStrongKeyEntry implements StrongValueEntry {
      @CheckForNull
      private volatile Object value;

      private StrongKeyStrongValueEntry(Object key, int hash) {
         super(key, hash);
         this.value = null;
      }

      @CheckForNull
      public final Object getValue() {
         return this.value;
      }

      private static final class LinkedStrongKeyStrongValueEntry extends StrongKeyStrongValueEntry {
         private final StrongKeyStrongValueEntry next;

         LinkedStrongKeyStrongValueEntry(Object key, int hash, StrongKeyStrongValueEntry next) {
            super(key, hash, null);
            this.next = next;
         }

         public StrongKeyStrongValueEntry getNext() {
            return this.next;
         }
      }

      static final class Helper implements InternalEntryHelper {
         private static final Helper INSTANCE = new Helper();

         static Helper instance() {
            return INSTANCE;
         }

         public Strength keyStrength() {
            return MapMakerInternalMap.Strength.STRONG;
         }

         public Strength valueStrength() {
            return MapMakerInternalMap.Strength.STRONG;
         }

         public StrongKeyStrongValueSegment newSegment(MapMakerInternalMap map, int initialCapacity) {
            return new StrongKeyStrongValueSegment(map, initialCapacity);
         }

         public StrongKeyStrongValueEntry copy(StrongKeyStrongValueSegment segment, StrongKeyStrongValueEntry entry, @CheckForNull StrongKeyStrongValueEntry newNext) {
            StrongKeyStrongValueEntry<K, V> newEntry = this.newEntry(segment, entry.key, entry.hash, newNext);
            newEntry.value = entry.value;
            return newEntry;
         }

         public void setValue(StrongKeyStrongValueSegment segment, StrongKeyStrongValueEntry entry, Object value) {
            entry.value = value;
         }

         public StrongKeyStrongValueEntry newEntry(StrongKeyStrongValueSegment segment, Object key, int hash, @CheckForNull StrongKeyStrongValueEntry next) {
            return (StrongKeyStrongValueEntry)(next == null ? new StrongKeyStrongValueEntry(key, hash) : new LinkedStrongKeyStrongValueEntry(key, hash, next));
         }
      }
   }

   static class StrongKeyWeakValueEntry extends AbstractStrongKeyEntry implements WeakValueEntry {
      private volatile WeakValueReference valueReference;

      private StrongKeyWeakValueEntry(Object key, int hash) {
         super(key, hash);
         this.valueReference = MapMakerInternalMap.unsetWeakValueReference();
      }

      @CheckForNull
      public final Object getValue() {
         return this.valueReference.get();
      }

      public final WeakValueReference getValueReference() {
         return this.valueReference;
      }

      private static final class LinkedStrongKeyWeakValueEntry extends StrongKeyWeakValueEntry {
         private final StrongKeyWeakValueEntry next;

         LinkedStrongKeyWeakValueEntry(Object key, int hash, StrongKeyWeakValueEntry next) {
            super(key, hash, null);
            this.next = next;
         }

         public StrongKeyWeakValueEntry getNext() {
            return this.next;
         }
      }

      static final class Helper implements InternalEntryHelper {
         private static final Helper INSTANCE = new Helper();

         static Helper instance() {
            return INSTANCE;
         }

         public Strength keyStrength() {
            return MapMakerInternalMap.Strength.STRONG;
         }

         public Strength valueStrength() {
            return MapMakerInternalMap.Strength.WEAK;
         }

         public StrongKeyWeakValueSegment newSegment(MapMakerInternalMap map, int initialCapacity) {
            return new StrongKeyWeakValueSegment(map, initialCapacity);
         }

         @CheckForNull
         public StrongKeyWeakValueEntry copy(StrongKeyWeakValueSegment segment, StrongKeyWeakValueEntry entry, @CheckForNull StrongKeyWeakValueEntry newNext) {
            if (MapMakerInternalMap.Segment.isCollected(entry)) {
               return null;
            } else {
               StrongKeyWeakValueEntry<K, V> newEntry = this.newEntry(segment, entry.key, entry.hash, newNext);
               newEntry.valueReference = entry.valueReference.copyFor(segment.queueForValues, newEntry);
               return newEntry;
            }
         }

         public void setValue(StrongKeyWeakValueSegment segment, StrongKeyWeakValueEntry entry, Object value) {
            WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> previous = entry.valueReference;
            entry.valueReference = new WeakValueReferenceImpl(segment.queueForValues, value, entry);
            previous.clear();
         }

         public StrongKeyWeakValueEntry newEntry(StrongKeyWeakValueSegment segment, Object key, int hash, @CheckForNull StrongKeyWeakValueEntry next) {
            return (StrongKeyWeakValueEntry)(next == null ? new StrongKeyWeakValueEntry(key, hash) : new LinkedStrongKeyWeakValueEntry(key, hash, next));
         }
      }
   }

   static class StrongKeyDummyValueEntry extends AbstractStrongKeyEntry implements StrongValueEntry {
      private StrongKeyDummyValueEntry(Object key, int hash) {
         super(key, hash);
      }

      public final MapMaker.Dummy getValue() {
         return MapMaker.Dummy.VALUE;
      }

      private static final class LinkedStrongKeyDummyValueEntry extends StrongKeyDummyValueEntry {
         private final StrongKeyDummyValueEntry next;

         LinkedStrongKeyDummyValueEntry(Object key, int hash, StrongKeyDummyValueEntry next) {
            super(key, hash, null);
            this.next = next;
         }

         public StrongKeyDummyValueEntry getNext() {
            return this.next;
         }
      }

      static final class Helper implements InternalEntryHelper {
         private static final Helper INSTANCE = new Helper();

         static Helper instance() {
            return INSTANCE;
         }

         public Strength keyStrength() {
            return MapMakerInternalMap.Strength.STRONG;
         }

         public Strength valueStrength() {
            return MapMakerInternalMap.Strength.STRONG;
         }

         public StrongKeyDummyValueSegment newSegment(MapMakerInternalMap map, int initialCapacity) {
            return new StrongKeyDummyValueSegment(map, initialCapacity);
         }

         public StrongKeyDummyValueEntry copy(StrongKeyDummyValueSegment segment, StrongKeyDummyValueEntry entry, @CheckForNull StrongKeyDummyValueEntry newNext) {
            return this.newEntry(segment, entry.key, entry.hash, newNext);
         }

         public void setValue(StrongKeyDummyValueSegment segment, StrongKeyDummyValueEntry entry, MapMaker.Dummy value) {
         }

         public StrongKeyDummyValueEntry newEntry(StrongKeyDummyValueSegment segment, Object key, int hash, @CheckForNull StrongKeyDummyValueEntry next) {
            return (StrongKeyDummyValueEntry)(next == null ? new StrongKeyDummyValueEntry(key, hash) : new LinkedStrongKeyDummyValueEntry(key, hash, next));
         }
      }
   }

   abstract static class AbstractWeakKeyEntry extends WeakReference implements InternalEntry {
      final int hash;

      AbstractWeakKeyEntry(ReferenceQueue queue, Object key, int hash) {
         super(key, queue);
         this.hash = hash;
      }

      public final Object getKey() {
         return this.get();
      }

      public final int getHash() {
         return this.hash;
      }

      @CheckForNull
      public InternalEntry getNext() {
         return null;
      }
   }

   static class WeakKeyDummyValueEntry extends AbstractWeakKeyEntry implements StrongValueEntry {
      private WeakKeyDummyValueEntry(ReferenceQueue queue, Object key, int hash) {
         super(queue, key, hash);
      }

      public final MapMaker.Dummy getValue() {
         return MapMaker.Dummy.VALUE;
      }

      private static final class LinkedWeakKeyDummyValueEntry extends WeakKeyDummyValueEntry {
         private final WeakKeyDummyValueEntry next;

         private LinkedWeakKeyDummyValueEntry(ReferenceQueue queue, Object key, int hash, WeakKeyDummyValueEntry next) {
            super(queue, key, hash, null);
            this.next = next;
         }

         public WeakKeyDummyValueEntry getNext() {
            return this.next;
         }
      }

      static final class Helper implements InternalEntryHelper {
         private static final Helper INSTANCE = new Helper();

         static Helper instance() {
            return INSTANCE;
         }

         public Strength keyStrength() {
            return MapMakerInternalMap.Strength.WEAK;
         }

         public Strength valueStrength() {
            return MapMakerInternalMap.Strength.STRONG;
         }

         public WeakKeyDummyValueSegment newSegment(MapMakerInternalMap map, int initialCapacity) {
            return new WeakKeyDummyValueSegment(map, initialCapacity);
         }

         @CheckForNull
         public WeakKeyDummyValueEntry copy(WeakKeyDummyValueSegment segment, WeakKeyDummyValueEntry entry, @CheckForNull WeakKeyDummyValueEntry newNext) {
            K key = (K)entry.getKey();
            return key == null ? null : this.newEntry(segment, key, entry.hash, newNext);
         }

         public void setValue(WeakKeyDummyValueSegment segment, WeakKeyDummyValueEntry entry, MapMaker.Dummy value) {
         }

         public WeakKeyDummyValueEntry newEntry(WeakKeyDummyValueSegment segment, Object key, int hash, @CheckForNull WeakKeyDummyValueEntry next) {
            return (WeakKeyDummyValueEntry)(next == null ? new WeakKeyDummyValueEntry(segment.queueForKeys, key, hash) : new LinkedWeakKeyDummyValueEntry(segment.queueForKeys, key, hash, next));
         }
      }
   }

   static class WeakKeyStrongValueEntry extends AbstractWeakKeyEntry implements StrongValueEntry {
      @CheckForNull
      private volatile Object value;

      private WeakKeyStrongValueEntry(ReferenceQueue queue, Object key, int hash) {
         super(queue, key, hash);
         this.value = null;
      }

      @CheckForNull
      public final Object getValue() {
         return this.value;
      }

      private static final class LinkedWeakKeyStrongValueEntry extends WeakKeyStrongValueEntry {
         private final WeakKeyStrongValueEntry next;

         private LinkedWeakKeyStrongValueEntry(ReferenceQueue queue, Object key, int hash, WeakKeyStrongValueEntry next) {
            super(queue, key, hash, null);
            this.next = next;
         }

         public WeakKeyStrongValueEntry getNext() {
            return this.next;
         }
      }

      static final class Helper implements InternalEntryHelper {
         private static final Helper INSTANCE = new Helper();

         static Helper instance() {
            return INSTANCE;
         }

         public Strength keyStrength() {
            return MapMakerInternalMap.Strength.WEAK;
         }

         public Strength valueStrength() {
            return MapMakerInternalMap.Strength.STRONG;
         }

         public WeakKeyStrongValueSegment newSegment(MapMakerInternalMap map, int initialCapacity) {
            return new WeakKeyStrongValueSegment(map, initialCapacity);
         }

         @CheckForNull
         public WeakKeyStrongValueEntry copy(WeakKeyStrongValueSegment segment, WeakKeyStrongValueEntry entry, @CheckForNull WeakKeyStrongValueEntry newNext) {
            K key = (K)entry.getKey();
            if (key == null) {
               return null;
            } else {
               WeakKeyStrongValueEntry<K, V> newEntry = this.newEntry(segment, key, entry.hash, newNext);
               newEntry.value = entry.value;
               return newEntry;
            }
         }

         public void setValue(WeakKeyStrongValueSegment segment, WeakKeyStrongValueEntry entry, Object value) {
            entry.value = value;
         }

         public WeakKeyStrongValueEntry newEntry(WeakKeyStrongValueSegment segment, Object key, int hash, @CheckForNull WeakKeyStrongValueEntry next) {
            return (WeakKeyStrongValueEntry)(next == null ? new WeakKeyStrongValueEntry(segment.queueForKeys, key, hash) : new LinkedWeakKeyStrongValueEntry(segment.queueForKeys, key, hash, next));
         }
      }
   }

   static class WeakKeyWeakValueEntry extends AbstractWeakKeyEntry implements WeakValueEntry {
      private volatile WeakValueReference valueReference = MapMakerInternalMap.unsetWeakValueReference();

      WeakKeyWeakValueEntry(ReferenceQueue queue, Object key, int hash) {
         super(queue, key, hash);
      }

      public final Object getValue() {
         return this.valueReference.get();
      }

      public final WeakValueReference getValueReference() {
         return this.valueReference;
      }

      private static final class LinkedWeakKeyWeakValueEntry extends WeakKeyWeakValueEntry {
         private final WeakKeyWeakValueEntry next;

         LinkedWeakKeyWeakValueEntry(ReferenceQueue queue, Object key, int hash, WeakKeyWeakValueEntry next) {
            super(queue, key, hash);
            this.next = next;
         }

         public WeakKeyWeakValueEntry getNext() {
            return this.next;
         }
      }

      static final class Helper implements InternalEntryHelper {
         private static final Helper INSTANCE = new Helper();

         static Helper instance() {
            return INSTANCE;
         }

         public Strength keyStrength() {
            return MapMakerInternalMap.Strength.WEAK;
         }

         public Strength valueStrength() {
            return MapMakerInternalMap.Strength.WEAK;
         }

         public WeakKeyWeakValueSegment newSegment(MapMakerInternalMap map, int initialCapacity) {
            return new WeakKeyWeakValueSegment(map, initialCapacity);
         }

         @CheckForNull
         public WeakKeyWeakValueEntry copy(WeakKeyWeakValueSegment segment, WeakKeyWeakValueEntry entry, @CheckForNull WeakKeyWeakValueEntry newNext) {
            K key = (K)entry.getKey();
            if (key == null) {
               return null;
            } else if (MapMakerInternalMap.Segment.isCollected(entry)) {
               return null;
            } else {
               WeakKeyWeakValueEntry<K, V> newEntry = this.newEntry(segment, key, entry.hash, newNext);
               newEntry.valueReference = entry.valueReference.copyFor(segment.queueForValues, newEntry);
               return newEntry;
            }
         }

         public void setValue(WeakKeyWeakValueSegment segment, WeakKeyWeakValueEntry entry, Object value) {
            WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> previous = entry.valueReference;
            entry.valueReference = new WeakValueReferenceImpl(segment.queueForValues, value, entry);
            previous.clear();
         }

         public WeakKeyWeakValueEntry newEntry(WeakKeyWeakValueSegment segment, Object key, int hash, @CheckForNull WeakKeyWeakValueEntry next) {
            return (WeakKeyWeakValueEntry)(next == null ? new WeakKeyWeakValueEntry(segment.queueForKeys, key, hash) : new LinkedWeakKeyWeakValueEntry(segment.queueForKeys, key, hash, next));
         }
      }
   }

   static final class DummyInternalEntry implements InternalEntry {
      private DummyInternalEntry() {
         throw new AssertionError();
      }

      public DummyInternalEntry getNext() {
         throw new AssertionError();
      }

      public int getHash() {
         throw new AssertionError();
      }

      public Object getKey() {
         throw new AssertionError();
      }

      public Object getValue() {
         throw new AssertionError();
      }
   }

   static final class WeakValueReferenceImpl extends WeakReference implements WeakValueReference {
      @Weak
      final InternalEntry entry;

      WeakValueReferenceImpl(ReferenceQueue queue, Object referent, InternalEntry entry) {
         super(referent, queue);
         this.entry = entry;
      }

      public InternalEntry getEntry() {
         return this.entry;
      }

      public WeakValueReference copyFor(ReferenceQueue queue, InternalEntry entry) {
         return new WeakValueReferenceImpl(queue, this.get(), entry);
      }
   }

   abstract static class Segment extends ReentrantLock {
      @Weak
      final MapMakerInternalMap map;
      volatile int count;
      int modCount;
      int threshold;
      @CheckForNull
      volatile AtomicReferenceArray table;
      final AtomicInteger readCount = new AtomicInteger();

      Segment(MapMakerInternalMap map, int initialCapacity) {
         this.map = map;
         this.initTable(this.newEntryArray(initialCapacity));
      }

      abstract Segment self();

      @GuardedBy("this")
      void maybeDrainReferenceQueues() {
      }

      void maybeClearReferenceQueues() {
      }

      void setValue(InternalEntry entry, Object value) {
         this.map.entryHelper.setValue(this.self(), entry, value);
      }

      @CheckForNull
      InternalEntry copyEntry(InternalEntry original, InternalEntry newNext) {
         return this.map.entryHelper.copy(this.self(), original, newNext);
      }

      AtomicReferenceArray newEntryArray(int size) {
         return new AtomicReferenceArray(size);
      }

      void initTable(AtomicReferenceArray newTable) {
         this.threshold = newTable.length() * 3 / 4;
         this.table = newTable;
      }

      abstract InternalEntry castForTesting(InternalEntry entry);

      ReferenceQueue getKeyReferenceQueueForTesting() {
         throw new AssertionError();
      }

      ReferenceQueue getValueReferenceQueueForTesting() {
         throw new AssertionError();
      }

      WeakValueReference getWeakValueReferenceForTesting(InternalEntry entry) {
         throw new AssertionError();
      }

      WeakValueReference newWeakValueReferenceForTesting(InternalEntry entry, Object value) {
         throw new AssertionError();
      }

      void setWeakValueReferenceForTesting(InternalEntry entry, WeakValueReference valueReference) {
         throw new AssertionError();
      }

      void setTableEntryForTesting(int i, InternalEntry entry) {
         this.table.set(i, this.castForTesting(entry));
      }

      InternalEntry copyForTesting(InternalEntry entry, @CheckForNull InternalEntry newNext) {
         return this.map.entryHelper.copy(this.self(), this.castForTesting(entry), this.castForTesting(newNext));
      }

      void setValueForTesting(InternalEntry entry, Object value) {
         this.map.entryHelper.setValue(this.self(), this.castForTesting(entry), value);
      }

      InternalEntry newEntryForTesting(Object key, int hash, @CheckForNull InternalEntry next) {
         return this.map.entryHelper.newEntry(this.self(), key, hash, this.castForTesting(next));
      }

      @CanIgnoreReturnValue
      boolean removeTableEntryForTesting(InternalEntry entry) {
         return this.removeEntryForTesting(this.castForTesting(entry));
      }

      @CheckForNull
      InternalEntry removeFromChainForTesting(InternalEntry first, InternalEntry entry) {
         return this.removeFromChain(this.castForTesting(first), this.castForTesting(entry));
      }

      @CheckForNull
      Object getLiveValueForTesting(InternalEntry entry) {
         return this.getLiveValue(this.castForTesting(entry));
      }

      void tryDrainReferenceQueues() {
         if (this.tryLock()) {
            try {
               this.maybeDrainReferenceQueues();
            } finally {
               this.unlock();
            }
         }

      }

      @GuardedBy("this")
      void drainKeyReferenceQueue(ReferenceQueue keyReferenceQueue) {
         int i = 0;

         Reference<? extends K> ref;
         while((ref = keyReferenceQueue.poll()) != null) {
            E entry = (E)((InternalEntry)ref);
            this.map.reclaimKey(entry);
            ++i;
            if (i == 16) {
               break;
            }
         }

      }

      @GuardedBy("this")
      void drainValueReferenceQueue(ReferenceQueue valueReferenceQueue) {
         int i = 0;

         Reference<? extends V> ref;
         while((ref = valueReferenceQueue.poll()) != null) {
            WeakValueReference<K, V, E> valueReference = (WeakValueReference)ref;
            this.map.reclaimValue(valueReference);
            ++i;
            if (i == 16) {
               break;
            }
         }

      }

      void clearReferenceQueue(ReferenceQueue referenceQueue) {
         while(referenceQueue.poll() != null) {
         }

      }

      @CheckForNull
      InternalEntry getFirst(int hash) {
         AtomicReferenceArray<E> table = this.table;
         return (InternalEntry)table.get(hash & table.length() - 1);
      }

      @CheckForNull
      InternalEntry getEntry(Object key, int hash) {
         if (this.count != 0) {
            for(E e = (E)this.getFirst(hash); e != null; e = (E)e.getNext()) {
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

      @CheckForNull
      InternalEntry getLiveEntry(Object key, int hash) {
         return this.getEntry(key, hash);
      }

      @CheckForNull
      Object get(Object key, int hash) {
         V value;
         try {
            E e = (E)this.getLiveEntry(key, hash);
            if (e != null) {
               value = (V)e.getValue();
               if (value == null) {
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

            E e = (E)this.getLiveEntry(key, hash);
            var4 = e != null && e.getValue() != null;
         } finally {
            this.postReadCleanup();
         }

         return var4;
      }

      @VisibleForTesting
      boolean containsValue(Object value) {
         try {
            if (this.count != 0) {
               AtomicReferenceArray<E> table = this.table;
               int length = table.length();

               for(int i = 0; i < length; ++i) {
                  for(E e = (E)((InternalEntry)table.get(i)); e != null; e = (E)e.getNext()) {
                     V entryValue = (V)this.getLiveValue(e);
                     if (entryValue != null && this.map.valueEquivalence().equivalent(value, entryValue)) {
                        boolean var7 = true;
                        return var7;
                     }
                  }
               }
            }

            boolean var11 = false;
            return var11;
         } finally {
            this.postReadCleanup();
         }
      }

      @CheckForNull
      Object put(Object key, int hash, Object value, boolean onlyIfAbsent) {
         this.lock();

         try {
            this.preWriteCleanup();
            int newCount = this.count + 1;
            if (newCount > this.threshold) {
               this.expand();
               newCount = this.count + 1;
            }

            AtomicReferenceArray<E> table = this.table;
            int index = hash & table.length() - 1;
            E first = (E)((InternalEntry)table.get(index));

            for(E e = first; e != null; e = (E)e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  V entryValue = (V)e.getValue();
                  if (entryValue != null) {
                     if (onlyIfAbsent) {
                        Object var20 = entryValue;
                        return var20;
                     }

                     ++this.modCount;
                     this.setValue(e, value);
                     Object var19 = entryValue;
                     return var19;
                  }

                  ++this.modCount;
                  this.setValue(e, value);
                  newCount = this.count;
                  this.count = newCount;
                  Object var12 = null;
                  return var12;
               }
            }

            ++this.modCount;
            E newEntry = (E)this.map.entryHelper.newEntry(this.self(), key, hash, first);
            this.setValue(newEntry, value);
            table.set(index, newEntry);
            this.count = newCount;
            Object var18 = null;
            return var18;
         } finally {
            this.unlock();
         }
      }

      @GuardedBy("this")
      void expand() {
         AtomicReferenceArray<E> oldTable = this.table;
         int oldCapacity = oldTable.length();
         if (oldCapacity < 1073741824) {
            int newCount = this.count;
            AtomicReferenceArray<E> newTable = this.newEntryArray(oldCapacity << 1);
            this.threshold = newTable.length() * 3 / 4;
            int newMask = newTable.length() - 1;

            for(int oldIndex = 0; oldIndex < oldCapacity; ++oldIndex) {
               E head = (E)((InternalEntry)oldTable.get(oldIndex));
               if (head != null) {
                  E next = (E)head.getNext();
                  int headIndex = head.getHash() & newMask;
                  if (next == null) {
                     newTable.set(headIndex, head);
                  } else {
                     E tail = head;
                     int tailIndex = headIndex;

                     for(E e = next; e != null; e = (E)e.getNext()) {
                        int newIndex = e.getHash() & newMask;
                        if (newIndex != tailIndex) {
                           tailIndex = newIndex;
                           tail = e;
                        }
                     }

                     newTable.set(tailIndex, tail);

                     for(E e = head; e != tail; e = (E)e.getNext()) {
                        int newIndex = e.getHash() & newMask;
                        E newNext = (E)((InternalEntry)newTable.get(newIndex));
                        E newFirst = (E)this.copyEntry(e, newNext);
                        if (newFirst != null) {
                           newTable.set(newIndex, newFirst);
                        } else {
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
            AtomicReferenceArray<E> table = this.table;
            int index = hash & table.length() - 1;
            E first = (E)((InternalEntry)table.get(index));

            for(E e = first; e != null; e = (E)e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  V entryValue = (V)e.getValue();
                  if (entryValue != null) {
                     if (this.map.valueEquivalence().equivalent(oldValue, entryValue)) {
                        ++this.modCount;
                        this.setValue(e, newValue);
                        boolean var20 = true;
                        return var20;
                     }

                     boolean var19 = false;
                     return var19;
                  }

                  if (isCollected(e)) {
                     int newCount = this.count - 1;
                     ++this.modCount;
                     E newFirst = (E)this.removeFromChain(first, e);
                     newCount = this.count - 1;
                     table.set(index, newFirst);
                     this.count = newCount;
                  }

                  boolean var18 = false;
                  return var18;
               }
            }

            boolean var16 = false;
            return var16;
         } finally {
            this.unlock();
         }
      }

      @CheckForNull
      Object replace(Object key, int hash, Object newValue) {
         this.lock();

         try {
            this.preWriteCleanup();
            AtomicReferenceArray<E> table = this.table;
            int index = hash & table.length() - 1;
            E first = (E)((InternalEntry)table.get(index));

            for(E e = first; e != null; e = (E)e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  V entryValue = (V)e.getValue();
                  if (entryValue != null) {
                     ++this.modCount;
                     this.setValue(e, newValue);
                     Object var18 = entryValue;
                     return var18;
                  }

                  if (isCollected(e)) {
                     int newCount = this.count - 1;
                     ++this.modCount;
                     E newFirst = (E)this.removeFromChain(first, e);
                     newCount = this.count - 1;
                     table.set(index, newFirst);
                     this.count = newCount;
                  }

                  Object var17 = null;
                  return var17;
               }
            }

            Object var15 = null;
            return var15;
         } finally {
            this.unlock();
         }
      }

      @CheckForNull
      @CanIgnoreReturnValue
      Object remove(Object key, int hash) {
         this.lock();

         try {
            this.preWriteCleanup();
            int newCount = this.count - 1;
            AtomicReferenceArray<E> table = this.table;
            int index = hash & table.length() - 1;
            E first = (E)((InternalEntry)table.get(index));

            for(E e = first; e != null; e = (E)e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  V entryValue = (V)e.getValue();
                  if (entryValue != null || isCollected(e)) {
                     ++this.modCount;
                     E newFirst = (E)this.removeFromChain(first, e);
                     newCount = this.count - 1;
                     table.set(index, newFirst);
                     this.count = newCount;
                     Object var11 = entryValue;
                     return var11;
                  }

                  Object var10 = null;
                  return var10;
               }
            }

            Object var16 = null;
            return var16;
         } finally {
            this.unlock();
         }
      }

      boolean remove(Object key, int hash, Object value) {
         this.lock();

         try {
            this.preWriteCleanup();
            int newCount = this.count - 1;
            AtomicReferenceArray<E> table = this.table;
            int index = hash & table.length() - 1;
            E first = (E)((InternalEntry)table.get(index));

            for(E e = first; e != null; e = (E)e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  V entryValue = (V)e.getValue();
                  boolean explicitRemoval = false;
                  if (this.map.valueEquivalence().equivalent(value, entryValue)) {
                     explicitRemoval = true;
                  } else if (!isCollected(e)) {
                     boolean var19 = false;
                     return var19;
                  }

                  ++this.modCount;
                  E newFirst = (E)this.removeFromChain(first, e);
                  newCount = this.count - 1;
                  table.set(index, newFirst);
                  this.count = newCount;
                  boolean var13 = explicitRemoval;
                  return var13;
               }
            }

            boolean var18 = false;
            return var18;
         } finally {
            this.unlock();
         }
      }

      void clear() {
         if (this.count != 0) {
            this.lock();

            try {
               AtomicReferenceArray<E> table = this.table;

               for(int i = 0; i < table.length(); ++i) {
                  table.set(i, (Object)null);
               }

               this.maybeClearReferenceQueues();
               this.readCount.set(0);
               ++this.modCount;
               this.count = 0;
            } finally {
               this.unlock();
            }
         }

      }

      @CheckForNull
      @GuardedBy("this")
      InternalEntry removeFromChain(InternalEntry first, InternalEntry entry) {
         int newCount = this.count;
         E newFirst = (E)entry.getNext();

         for(E e = first; e != entry; e = (E)e.getNext()) {
            E next = (E)this.copyEntry(e, newFirst);
            if (next != null) {
               newFirst = next;
            } else {
               --newCount;
            }
         }

         this.count = newCount;
         return newFirst;
      }

      @CanIgnoreReturnValue
      boolean reclaimKey(InternalEntry entry, int hash) {
         this.lock();

         try {
            int newCount = this.count - 1;
            AtomicReferenceArray<E> table = this.table;
            int index = hash & table.length() - 1;
            E first = (E)((InternalEntry)table.get(index));

            for(E e = first; e != null; e = (E)e.getNext()) {
               if (e == entry) {
                  ++this.modCount;
                  E newFirst = (E)this.removeFromChain(first, e);
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
         }
      }

      @CanIgnoreReturnValue
      boolean reclaimValue(Object key, int hash, WeakValueReference valueReference) {
         this.lock();

         try {
            int newCount = this.count - 1;
            AtomicReferenceArray<E> table = this.table;
            int index = hash & table.length() - 1;
            E first = (E)((InternalEntry)table.get(index));

            for(E e = first; e != null; e = (E)e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  WeakValueReference<K, V, E> v = ((WeakValueEntry)e).getValueReference();
                  if (v != valueReference) {
                     boolean var18 = false;
                     return var18;
                  }

                  ++this.modCount;
                  E newFirst = (E)this.removeFromChain(first, e);
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
         }
      }

      @CanIgnoreReturnValue
      boolean clearValueForTesting(Object key, int hash, WeakValueReference valueReference) {
         this.lock();

         try {
            AtomicReferenceArray<E> table = this.table;
            int index = hash & table.length() - 1;
            E first = (E)((InternalEntry)table.get(index));

            for(E e = first; e != null; e = (E)e.getNext()) {
               K entryKey = (K)e.getKey();
               if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                  WeakValueReference<K, V, E> v = ((WeakValueEntry)e).getValueReference();
                  if (v != valueReference) {
                     boolean var16 = false;
                     return var16;
                  }

                  E newFirst = (E)this.removeFromChain(first, e);
                  table.set(index, newFirst);
                  boolean var11 = true;
                  return var11;
               }
            }

            boolean var15 = false;
            return var15;
         } finally {
            this.unlock();
         }
      }

      @GuardedBy("this")
      boolean removeEntryForTesting(InternalEntry entry) {
         int hash = entry.getHash();
         int newCount = this.count - 1;
         AtomicReferenceArray<E> table = this.table;
         int index = hash & table.length() - 1;
         E first = (E)((InternalEntry)table.get(index));

         for(E e = first; e != null; e = (E)e.getNext()) {
            if (e == entry) {
               ++this.modCount;
               E newFirst = (E)this.removeFromChain(first, e);
               newCount = this.count - 1;
               table.set(index, newFirst);
               this.count = newCount;
               return true;
            }
         }

         return false;
      }

      static boolean isCollected(InternalEntry entry) {
         return entry.getValue() == null;
      }

      @CheckForNull
      Object getLiveValue(InternalEntry entry) {
         if (entry.getKey() == null) {
            this.tryDrainReferenceQueues();
            return null;
         } else {
            V value = (V)entry.getValue();
            if (value == null) {
               this.tryDrainReferenceQueues();
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

      @GuardedBy("this")
      void preWriteCleanup() {
         this.runLockedCleanup();
      }

      void runCleanup() {
         this.runLockedCleanup();
      }

      void runLockedCleanup() {
         if (this.tryLock()) {
            try {
               this.maybeDrainReferenceQueues();
               this.readCount.set(0);
            } finally {
               this.unlock();
            }
         }

      }
   }

   static final class StrongKeyStrongValueSegment extends Segment {
      StrongKeyStrongValueSegment(MapMakerInternalMap map, int initialCapacity) {
         super(map, initialCapacity);
      }

      StrongKeyStrongValueSegment self() {
         return this;
      }

      @CheckForNull
      public StrongKeyStrongValueEntry castForTesting(@CheckForNull InternalEntry entry) {
         return (StrongKeyStrongValueEntry)entry;
      }
   }

   static final class StrongKeyWeakValueSegment extends Segment {
      private final ReferenceQueue queueForValues = new ReferenceQueue();

      StrongKeyWeakValueSegment(MapMakerInternalMap map, int initialCapacity) {
         super(map, initialCapacity);
      }

      StrongKeyWeakValueSegment self() {
         return this;
      }

      ReferenceQueue getValueReferenceQueueForTesting() {
         return this.queueForValues;
      }

      @CheckForNull
      public StrongKeyWeakValueEntry castForTesting(@CheckForNull InternalEntry entry) {
         return (StrongKeyWeakValueEntry)entry;
      }

      public WeakValueReference getWeakValueReferenceForTesting(InternalEntry e) {
         return this.castForTesting(e).getValueReference();
      }

      public WeakValueReference newWeakValueReferenceForTesting(InternalEntry e, Object value) {
         return new WeakValueReferenceImpl(this.queueForValues, value, this.castForTesting(e));
      }

      public void setWeakValueReferenceForTesting(InternalEntry e, WeakValueReference valueReference) {
         StrongKeyWeakValueEntry<K, V> entry = this.castForTesting(e);
         WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> previous = entry.valueReference;
         entry.valueReference = valueReference;
         previous.clear();
      }

      void maybeDrainReferenceQueues() {
         this.drainValueReferenceQueue(this.queueForValues);
      }

      void maybeClearReferenceQueues() {
         this.clearReferenceQueue(this.queueForValues);
      }
   }

   static final class StrongKeyDummyValueSegment extends Segment {
      StrongKeyDummyValueSegment(MapMakerInternalMap map, int initialCapacity) {
         super(map, initialCapacity);
      }

      StrongKeyDummyValueSegment self() {
         return this;
      }

      public StrongKeyDummyValueEntry castForTesting(InternalEntry entry) {
         return (StrongKeyDummyValueEntry)entry;
      }
   }

   static final class WeakKeyStrongValueSegment extends Segment {
      private final ReferenceQueue queueForKeys = new ReferenceQueue();

      WeakKeyStrongValueSegment(MapMakerInternalMap map, int initialCapacity) {
         super(map, initialCapacity);
      }

      WeakKeyStrongValueSegment self() {
         return this;
      }

      ReferenceQueue getKeyReferenceQueueForTesting() {
         return this.queueForKeys;
      }

      public WeakKeyStrongValueEntry castForTesting(InternalEntry entry) {
         return (WeakKeyStrongValueEntry)entry;
      }

      void maybeDrainReferenceQueues() {
         this.drainKeyReferenceQueue(this.queueForKeys);
      }

      void maybeClearReferenceQueues() {
         this.clearReferenceQueue(this.queueForKeys);
      }
   }

   static final class WeakKeyWeakValueSegment extends Segment {
      private final ReferenceQueue queueForKeys = new ReferenceQueue();
      private final ReferenceQueue queueForValues = new ReferenceQueue();

      WeakKeyWeakValueSegment(MapMakerInternalMap map, int initialCapacity) {
         super(map, initialCapacity);
      }

      WeakKeyWeakValueSegment self() {
         return this;
      }

      ReferenceQueue getKeyReferenceQueueForTesting() {
         return this.queueForKeys;
      }

      ReferenceQueue getValueReferenceQueueForTesting() {
         return this.queueForValues;
      }

      @CheckForNull
      public WeakKeyWeakValueEntry castForTesting(@CheckForNull InternalEntry entry) {
         return (WeakKeyWeakValueEntry)entry;
      }

      public WeakValueReference getWeakValueReferenceForTesting(InternalEntry e) {
         return this.castForTesting(e).getValueReference();
      }

      public WeakValueReference newWeakValueReferenceForTesting(InternalEntry e, Object value) {
         return new WeakValueReferenceImpl(this.queueForValues, value, this.castForTesting(e));
      }

      public void setWeakValueReferenceForTesting(InternalEntry e, WeakValueReference valueReference) {
         WeakKeyWeakValueEntry<K, V> entry = this.castForTesting(e);
         WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> previous = entry.valueReference;
         entry.valueReference = valueReference;
         previous.clear();
      }

      void maybeDrainReferenceQueues() {
         this.drainKeyReferenceQueue(this.queueForKeys);
         this.drainValueReferenceQueue(this.queueForValues);
      }

      void maybeClearReferenceQueues() {
         this.clearReferenceQueue(this.queueForKeys);
      }
   }

   static final class WeakKeyDummyValueSegment extends Segment {
      private final ReferenceQueue queueForKeys = new ReferenceQueue();

      WeakKeyDummyValueSegment(MapMakerInternalMap map, int initialCapacity) {
         super(map, initialCapacity);
      }

      WeakKeyDummyValueSegment self() {
         return this;
      }

      ReferenceQueue getKeyReferenceQueueForTesting() {
         return this.queueForKeys;
      }

      public WeakKeyDummyValueEntry castForTesting(InternalEntry entry) {
         return (WeakKeyDummyValueEntry)entry;
      }

      void maybeDrainReferenceQueues() {
         this.drainKeyReferenceQueue(this.queueForKeys);
      }

      void maybeClearReferenceQueues() {
         this.clearReferenceQueue(this.queueForKeys);
      }
   }

   static final class CleanupMapTask implements Runnable {
      final WeakReference mapReference;

      public CleanupMapTask(MapMakerInternalMap map) {
         this.mapReference = new WeakReference(map);
      }

      public void run() {
         MapMakerInternalMap<?, ?, ?, ?> map = (MapMakerInternalMap)this.mapReference.get();
         if (map == null) {
            throw new CancellationException();
         } else {
            for(Segment segment : map.segments) {
               segment.runCleanup();
            }

         }
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
      InternalEntry nextEntry;
      @CheckForNull
      WriteThroughEntry nextExternal;
      @CheckForNull
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
               if ((this.nextEntry = (InternalEntry)this.currentTable.get(this.nextTableIndex--)) == null || !this.advanceTo(this.nextEntry) && !this.nextInChain()) {
                  continue;
               }

               return true;
            }

            return false;
         }
      }

      boolean advanceTo(InternalEntry entry) {
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

   final class KeySet extends AbstractSet {
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

   final class Values extends AbstractCollection {
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

   final class EntrySet extends AbstractSet {
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
               return v != null && MapMakerInternalMap.this.valueEquivalence().equivalent(e.getValue(), v);
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

   abstract static class AbstractSerializationProxy extends ForwardingConcurrentMap implements Serializable {
      private static final long serialVersionUID = 3L;
      final Strength keyStrength;
      final Strength valueStrength;
      final Equivalence keyEquivalence;
      final Equivalence valueEquivalence;
      final int concurrencyLevel;
      transient ConcurrentMap delegate;

      AbstractSerializationProxy(Strength keyStrength, Strength valueStrength, Equivalence keyEquivalence, Equivalence valueEquivalence, int concurrencyLevel, ConcurrentMap delegate) {
         this.keyStrength = keyStrength;
         this.valueStrength = valueStrength;
         this.keyEquivalence = keyEquivalence;
         this.valueEquivalence = valueEquivalence;
         this.concurrencyLevel = concurrencyLevel;
         this.delegate = delegate;
      }

      protected ConcurrentMap delegate() {
         return this.delegate;
      }

      void writeMapTo(ObjectOutputStream out) throws IOException {
         out.writeInt(this.delegate.size());

         for(Map.Entry entry : this.delegate.entrySet()) {
            out.writeObject(entry.getKey());
            out.writeObject(entry.getValue());
         }

         out.writeObject((Object)null);
      }

      @J2ktIncompatible
      MapMaker readMapMaker(ObjectInputStream in) throws IOException {
         int size = in.readInt();
         return (new MapMaker()).initialCapacity(size).setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).concurrencyLevel(this.concurrencyLevel);
      }

      @J2ktIncompatible
      void readEntries(ObjectInputStream in) throws IOException, ClassNotFoundException {
         while(true) {
            K key = (K)in.readObject();
            if (key == null) {
               return;
            }

            V value = (V)in.readObject();
            this.delegate.put(key, value);
         }
      }
   }

   private static final class SerializationProxy extends AbstractSerializationProxy {
      private static final long serialVersionUID = 3L;

      SerializationProxy(Strength keyStrength, Strength valueStrength, Equivalence keyEquivalence, Equivalence valueEquivalence, int concurrencyLevel, ConcurrentMap delegate) {
         super(keyStrength, valueStrength, keyEquivalence, valueEquivalence, concurrencyLevel, delegate);
      }

      private void writeObject(ObjectOutputStream out) throws IOException {
         out.defaultWriteObject();
         this.writeMapTo(out);
      }

      @J2ktIncompatible
      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         in.defaultReadObject();
         MapMaker mapMaker = this.readMapMaker(in);
         this.delegate = mapMaker.makeMap();
         this.readEntries(in);
      }

      private Object readResolve() {
         return this.delegate;
      }
   }

   interface InternalEntry {
      InternalEntry getNext();

      int getHash();

      Object getKey();

      Object getValue();
   }

   interface InternalEntryHelper {
      Strength keyStrength();

      Strength valueStrength();

      Segment newSegment(MapMakerInternalMap map, int initialCapacity);

      InternalEntry newEntry(Segment segment, Object key, int hash, @CheckForNull InternalEntry next);

      InternalEntry copy(Segment segment, InternalEntry entry, @CheckForNull InternalEntry newNext);

      void setValue(Segment segment, InternalEntry entry, Object value);
   }

   interface StrongValueEntry extends InternalEntry {
   }

   interface WeakValueEntry extends InternalEntry {
      WeakValueReference getValueReference();
   }

   interface WeakValueReference {
      @CheckForNull
      Object get();

      InternalEntry getEntry();

      void clear();

      WeakValueReference copyFor(ReferenceQueue queue, InternalEntry entry);
   }
}
