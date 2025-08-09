package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Objects;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class HashBiMap extends Maps.IteratorBasedAbstractMap implements BiMap, Serializable {
   private static final double LOAD_FACTOR = (double)1.0F;
   private transient @Nullable HashBiMap.BiEntry[] hashTableKToV;
   private transient @Nullable HashBiMap.BiEntry[] hashTableVToK;
   @CheckForNull
   @Weak
   private transient BiEntry firstInKeyInsertionOrder;
   @CheckForNull
   @Weak
   private transient BiEntry lastInKeyInsertionOrder;
   private transient int size;
   private transient int mask;
   private transient int modCount;
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient BiMap inverse;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   public static HashBiMap create() {
      return create(16);
   }

   public static HashBiMap create(int expectedSize) {
      return new HashBiMap(expectedSize);
   }

   public static HashBiMap create(Map map) {
      HashBiMap<K, V> bimap = create(map.size());
      bimap.putAll(map);
      return bimap;
   }

   private HashBiMap(int expectedSize) {
      this.init(expectedSize);
   }

   private void init(int expectedSize) {
      CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
      int tableSize = Hashing.closedTableSize(expectedSize, (double)1.0F);
      this.hashTableKToV = this.createTable(tableSize);
      this.hashTableVToK = this.createTable(tableSize);
      this.firstInKeyInsertionOrder = null;
      this.lastInKeyInsertionOrder = null;
      this.size = 0;
      this.mask = tableSize - 1;
      this.modCount = 0;
   }

   private void delete(BiEntry entry) {
      int keyBucket = entry.keyHash & this.mask;
      BiEntry<K, V> prevBucketEntry = null;

      for(BiEntry<K, V> bucketEntry = this.hashTableKToV[keyBucket]; bucketEntry != entry; bucketEntry = bucketEntry.nextInKToVBucket) {
         prevBucketEntry = bucketEntry;
      }

      if (prevBucketEntry == null) {
         this.hashTableKToV[keyBucket] = entry.nextInKToVBucket;
      } else {
         prevBucketEntry.nextInKToVBucket = entry.nextInKToVBucket;
      }

      int valueBucket = entry.valueHash & this.mask;
      prevBucketEntry = null;

      for(BiEntry<K, V> bucketEntry = this.hashTableVToK[valueBucket]; bucketEntry != entry; bucketEntry = bucketEntry.nextInVToKBucket) {
         prevBucketEntry = bucketEntry;
      }

      if (prevBucketEntry == null) {
         this.hashTableVToK[valueBucket] = entry.nextInVToKBucket;
      } else {
         prevBucketEntry.nextInVToKBucket = entry.nextInVToKBucket;
      }

      if (entry.prevInKeyInsertionOrder == null) {
         this.firstInKeyInsertionOrder = entry.nextInKeyInsertionOrder;
      } else {
         entry.prevInKeyInsertionOrder.nextInKeyInsertionOrder = entry.nextInKeyInsertionOrder;
      }

      if (entry.nextInKeyInsertionOrder == null) {
         this.lastInKeyInsertionOrder = entry.prevInKeyInsertionOrder;
      } else {
         entry.nextInKeyInsertionOrder.prevInKeyInsertionOrder = entry.prevInKeyInsertionOrder;
      }

      --this.size;
      ++this.modCount;
   }

   private void insert(BiEntry entry, @CheckForNull BiEntry oldEntryForKey) {
      int keyBucket = entry.keyHash & this.mask;
      entry.nextInKToVBucket = this.hashTableKToV[keyBucket];
      this.hashTableKToV[keyBucket] = entry;
      int valueBucket = entry.valueHash & this.mask;
      entry.nextInVToKBucket = this.hashTableVToK[valueBucket];
      this.hashTableVToK[valueBucket] = entry;
      if (oldEntryForKey == null) {
         entry.prevInKeyInsertionOrder = this.lastInKeyInsertionOrder;
         entry.nextInKeyInsertionOrder = null;
         if (this.lastInKeyInsertionOrder == null) {
            this.firstInKeyInsertionOrder = entry;
         } else {
            this.lastInKeyInsertionOrder.nextInKeyInsertionOrder = entry;
         }

         this.lastInKeyInsertionOrder = entry;
      } else {
         entry.prevInKeyInsertionOrder = oldEntryForKey.prevInKeyInsertionOrder;
         if (entry.prevInKeyInsertionOrder == null) {
            this.firstInKeyInsertionOrder = entry;
         } else {
            entry.prevInKeyInsertionOrder.nextInKeyInsertionOrder = entry;
         }

         entry.nextInKeyInsertionOrder = oldEntryForKey.nextInKeyInsertionOrder;
         if (entry.nextInKeyInsertionOrder == null) {
            this.lastInKeyInsertionOrder = entry;
         } else {
            entry.nextInKeyInsertionOrder.prevInKeyInsertionOrder = entry;
         }
      }

      ++this.size;
      ++this.modCount;
   }

   @CheckForNull
   private BiEntry seekByKey(@CheckForNull Object key, int keyHash) {
      for(BiEntry<K, V> entry = this.hashTableKToV[keyHash & this.mask]; entry != null; entry = entry.nextInKToVBucket) {
         if (keyHash == entry.keyHash && Objects.equal(key, entry.key)) {
            return entry;
         }
      }

      return null;
   }

   @CheckForNull
   private BiEntry seekByValue(@CheckForNull Object value, int valueHash) {
      for(BiEntry<K, V> entry = this.hashTableVToK[valueHash & this.mask]; entry != null; entry = entry.nextInVToKBucket) {
         if (valueHash == entry.valueHash && Objects.equal(value, entry.value)) {
            return entry;
         }
      }

      return null;
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.seekByKey(key, Hashing.smearedHash(key)) != null;
   }

   public boolean containsValue(@CheckForNull Object value) {
      return this.seekByValue(value, Hashing.smearedHash(value)) != null;
   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      return Maps.valueOrNull(this.seekByKey(key, Hashing.smearedHash(key)));
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(@ParametricNullness Object key, @ParametricNullness Object value) {
      return this.put(key, value, false);
   }

   @CheckForNull
   private Object put(@ParametricNullness Object key, @ParametricNullness Object value, boolean force) {
      int keyHash = Hashing.smearedHash(key);
      int valueHash = Hashing.smearedHash(value);
      BiEntry<K, V> oldEntryForKey = this.seekByKey(key, keyHash);
      if (oldEntryForKey != null && valueHash == oldEntryForKey.valueHash && Objects.equal(value, oldEntryForKey.value)) {
         return value;
      } else {
         BiEntry<K, V> oldEntryForValue = this.seekByValue(value, valueHash);
         if (oldEntryForValue != null) {
            if (!force) {
               throw new IllegalArgumentException("value already present: " + value);
            }

            this.delete(oldEntryForValue);
         }

         BiEntry<K, V> newEntry = new BiEntry(key, keyHash, value, valueHash);
         if (oldEntryForKey != null) {
            this.delete(oldEntryForKey);
            this.insert(newEntry, oldEntryForKey);
            oldEntryForKey.prevInKeyInsertionOrder = null;
            oldEntryForKey.nextInKeyInsertionOrder = null;
            return oldEntryForKey.value;
         } else {
            this.insert(newEntry, (BiEntry)null);
            this.rehashIfNecessary();
            return null;
         }
      }
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object forcePut(@ParametricNullness Object key, @ParametricNullness Object value) {
      return this.put(key, value, true);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   private Object putInverse(@ParametricNullness Object value, @ParametricNullness Object key, boolean force) {
      int valueHash = Hashing.smearedHash(value);
      int keyHash = Hashing.smearedHash(key);
      BiEntry<K, V> oldEntryForValue = this.seekByValue(value, valueHash);
      BiEntry<K, V> oldEntryForKey = this.seekByKey(key, keyHash);
      if (oldEntryForValue != null && keyHash == oldEntryForValue.keyHash && Objects.equal(key, oldEntryForValue.key)) {
         return key;
      } else if (oldEntryForKey != null && !force) {
         throw new IllegalArgumentException("key already present: " + key);
      } else {
         if (oldEntryForValue != null) {
            this.delete(oldEntryForValue);
         }

         if (oldEntryForKey != null) {
            this.delete(oldEntryForKey);
         }

         BiEntry<K, V> newEntry = new BiEntry(key, keyHash, value, valueHash);
         this.insert(newEntry, oldEntryForKey);
         if (oldEntryForKey != null) {
            oldEntryForKey.prevInKeyInsertionOrder = null;
            oldEntryForKey.nextInKeyInsertionOrder = null;
         }

         if (oldEntryForValue != null) {
            oldEntryForValue.prevInKeyInsertionOrder = null;
            oldEntryForValue.nextInKeyInsertionOrder = null;
         }

         this.rehashIfNecessary();
         return Maps.keyOrNull(oldEntryForValue);
      }
   }

   private void rehashIfNecessary() {
      BiEntry<K, V>[] oldKToV = this.hashTableKToV;
      if (Hashing.needsResizing(this.size, oldKToV.length, (double)1.0F)) {
         int newTableSize = oldKToV.length * 2;
         this.hashTableKToV = this.createTable(newTableSize);
         this.hashTableVToK = this.createTable(newTableSize);
         this.mask = newTableSize - 1;
         this.size = 0;

         for(BiEntry<K, V> entry = this.firstInKeyInsertionOrder; entry != null; entry = entry.nextInKeyInsertionOrder) {
            this.insert(entry, entry);
         }

         ++this.modCount;
      }

   }

   private BiEntry[] createTable(int length) {
      return new BiEntry[length];
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object remove(@CheckForNull Object key) {
      BiEntry<K, V> entry = this.seekByKey(key, Hashing.smearedHash(key));
      if (entry == null) {
         return null;
      } else {
         this.delete(entry);
         entry.prevInKeyInsertionOrder = null;
         entry.nextInKeyInsertionOrder = null;
         return entry.value;
      }
   }

   public void clear() {
      this.size = 0;
      Arrays.fill(this.hashTableKToV, (Object)null);
      Arrays.fill(this.hashTableVToK, (Object)null);
      this.firstInKeyInsertionOrder = null;
      this.lastInKeyInsertionOrder = null;
      ++this.modCount;
   }

   public int size() {
      return this.size;
   }

   public Set keySet() {
      return new KeySet();
   }

   public Set values() {
      return this.inverse().keySet();
   }

   Iterator entryIterator() {
      return new Itr() {
         Map.Entry output(BiEntry entry) {
            return new null.MapEntry(entry);
         }

         class MapEntry extends AbstractMapEntry {
            private BiEntry delegate;

            MapEntry(BiEntry entry) {
               this.delegate = entry;
            }

            @ParametricNullness
            public Object getKey() {
               return this.delegate.key;
            }

            @ParametricNullness
            public Object getValue() {
               return this.delegate.value;
            }

            @ParametricNullness
            public Object setValue(@ParametricNullness Object value) {
               V oldValue = (V)this.delegate.value;
               int valueHash = Hashing.smearedHash(value);
               if (valueHash == this.delegate.valueHash && Objects.equal(value, oldValue)) {
                  return value;
               } else {
                  Preconditions.checkArgument(HashBiMap.this.seekByValue(value, valueHash) == null, "value already present: %s", value);
                  HashBiMap.this.delete(this.delegate);
                  BiEntry<K, V> newEntry = new BiEntry(this.delegate.key, this.delegate.keyHash, value, valueHash);
                  HashBiMap.this.insert(newEntry, this.delegate);
                  this.delegate.prevInKeyInsertionOrder = null;
                  this.delegate.nextInKeyInsertionOrder = null;
                  expectedModCount = HashBiMap.this.modCount;
                  if (toRemove == this.delegate) {
                     toRemove = newEntry;
                  }

                  this.delegate = newEntry;
                  return oldValue;
               }
            }
         }
      };
   }

   public void forEach(BiConsumer action) {
      Preconditions.checkNotNull(action);

      for(BiEntry<K, V> entry = this.firstInKeyInsertionOrder; entry != null; entry = entry.nextInKeyInsertionOrder) {
         action.accept(entry.key, entry.value);
      }

   }

   public void replaceAll(BiFunction function) {
      Preconditions.checkNotNull(function);
      BiEntry<K, V> oldFirst = this.firstInKeyInsertionOrder;
      this.clear();

      for(BiEntry<K, V> entry = oldFirst; entry != null; entry = entry.nextInKeyInsertionOrder) {
         this.put(entry.key, function.apply(entry.key, entry.value));
      }

   }

   public BiMap inverse() {
      BiMap<V, K> result = this.inverse;
      return result == null ? (this.inverse = new Inverse()) : result;
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      Serialization.writeMap(this, stream);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      int size = Serialization.readCount(stream);
      this.init(16);
      Serialization.populateMap(this, stream, size);
   }

   static final class BiEntry extends ImmutableEntry {
      final int keyHash;
      final int valueHash;
      @CheckForNull
      BiEntry nextInKToVBucket;
      @CheckForNull
      @Weak
      BiEntry nextInVToKBucket;
      @CheckForNull
      @Weak
      BiEntry nextInKeyInsertionOrder;
      @CheckForNull
      @Weak
      BiEntry prevInKeyInsertionOrder;

      BiEntry(@ParametricNullness Object key, int keyHash, @ParametricNullness Object value, int valueHash) {
         super(key, value);
         this.keyHash = keyHash;
         this.valueHash = valueHash;
      }
   }

   private abstract class Itr implements Iterator {
      @CheckForNull
      BiEntry next;
      @CheckForNull
      BiEntry toRemove;
      int expectedModCount;
      int remaining;

      private Itr() {
         this.next = HashBiMap.this.firstInKeyInsertionOrder;
         this.toRemove = null;
         this.expectedModCount = HashBiMap.this.modCount;
         this.remaining = HashBiMap.this.size();
      }

      public boolean hasNext() {
         if (HashBiMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else {
            return this.next != null && this.remaining > 0;
         }
      }

      public Object next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            BiEntry<K, V> entry = (BiEntry)java.util.Objects.requireNonNull(this.next);
            this.next = entry.nextInKeyInsertionOrder;
            this.toRemove = entry;
            --this.remaining;
            return this.output(entry);
         }
      }

      public void remove() {
         if (HashBiMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else if (this.toRemove == null) {
            throw new IllegalStateException("no calls to next() since the last call to remove()");
         } else {
            HashBiMap.this.delete(this.toRemove);
            this.expectedModCount = HashBiMap.this.modCount;
            this.toRemove = null;
         }
      }

      abstract Object output(BiEntry entry);
   }

   private final class KeySet extends Maps.KeySet {
      KeySet() {
         super(HashBiMap.this);
      }

      public Iterator iterator() {
         return new Itr() {
            @ParametricNullness
            Object output(BiEntry entry) {
               return entry.key;
            }
         };
      }

      public boolean remove(@CheckForNull Object o) {
         BiEntry<K, V> entry = HashBiMap.this.seekByKey(o, Hashing.smearedHash(o));
         if (entry == null) {
            return false;
         } else {
            HashBiMap.this.delete(entry);
            entry.prevInKeyInsertionOrder = null;
            entry.nextInKeyInsertionOrder = null;
            return true;
         }
      }
   }

   private final class Inverse extends Maps.IteratorBasedAbstractMap implements BiMap, Serializable {
      private Inverse() {
      }

      BiMap forward() {
         return HashBiMap.this;
      }

      public int size() {
         return HashBiMap.this.size;
      }

      public void clear() {
         this.forward().clear();
      }

      public boolean containsKey(@CheckForNull Object value) {
         return this.forward().containsValue(value);
      }

      @CheckForNull
      public Object get(@CheckForNull Object value) {
         return Maps.keyOrNull(HashBiMap.this.seekByValue(value, Hashing.smearedHash(value)));
      }

      @CheckForNull
      @CanIgnoreReturnValue
      public Object put(@ParametricNullness Object value, @ParametricNullness Object key) {
         return HashBiMap.this.putInverse(value, key, false);
      }

      @CheckForNull
      public Object forcePut(@ParametricNullness Object value, @ParametricNullness Object key) {
         return HashBiMap.this.putInverse(value, key, true);
      }

      @CheckForNull
      public Object remove(@CheckForNull Object value) {
         BiEntry<K, V> entry = HashBiMap.this.seekByValue(value, Hashing.smearedHash(value));
         if (entry == null) {
            return null;
         } else {
            HashBiMap.this.delete(entry);
            entry.prevInKeyInsertionOrder = null;
            entry.nextInKeyInsertionOrder = null;
            return entry.key;
         }
      }

      public BiMap inverse() {
         return this.forward();
      }

      public Set keySet() {
         return new InverseKeySet();
      }

      public Set values() {
         return this.forward().keySet();
      }

      Iterator entryIterator() {
         return new Itr() {
            Map.Entry output(BiEntry entry) {
               return new null.InverseEntry(entry);
            }

            class InverseEntry extends AbstractMapEntry {
               private BiEntry delegate;

               InverseEntry(BiEntry entry) {
                  this.delegate = entry;
               }

               @ParametricNullness
               public Object getKey() {
                  return this.delegate.value;
               }

               @ParametricNullness
               public Object getValue() {
                  return this.delegate.key;
               }

               @ParametricNullness
               public Object setValue(@ParametricNullness Object key) {
                  K oldKey = (K)this.delegate.key;
                  int keyHash = Hashing.smearedHash(key);
                  if (keyHash == this.delegate.keyHash && Objects.equal(key, oldKey)) {
                     return key;
                  } else {
                     Preconditions.checkArgument(HashBiMap.this.seekByKey(key, keyHash) == null, "value already present: %s", key);
                     HashBiMap.this.delete(this.delegate);
                     BiEntry<K, V> newEntry = new BiEntry(key, keyHash, this.delegate.value, this.delegate.valueHash);
                     this.delegate = newEntry;
                     HashBiMap.this.insert(newEntry, (BiEntry)null);
                     expectedModCount = HashBiMap.this.modCount;
                     return oldKey;
                  }
               }
            }
         };
      }

      public void forEach(BiConsumer action) {
         Preconditions.checkNotNull(action);
         HashBiMap.this.forEach((k, v) -> action.accept(v, k));
      }

      public void replaceAll(BiFunction function) {
         Preconditions.checkNotNull(function);
         BiEntry<K, V> oldFirst = HashBiMap.this.firstInKeyInsertionOrder;
         this.clear();

         for(BiEntry<K, V> entry = oldFirst; entry != null; entry = entry.nextInKeyInsertionOrder) {
            this.put(entry.value, function.apply(entry.value, entry.key));
         }

      }

      Object writeReplace() {
         return new InverseSerializedForm(HashBiMap.this);
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream in) throws InvalidObjectException {
         throw new InvalidObjectException("Use InverseSerializedForm");
      }

      private final class InverseKeySet extends Maps.KeySet {
         InverseKeySet() {
            super(Inverse.this);
         }

         public boolean remove(@CheckForNull Object o) {
            BiEntry<K, V> entry = HashBiMap.this.seekByValue(o, Hashing.smearedHash(o));
            if (entry == null) {
               return false;
            } else {
               HashBiMap.this.delete(entry);
               return true;
            }
         }

         public Iterator iterator() {
            return new Itr() {
               @ParametricNullness
               Object output(BiEntry entry) {
                  return entry.value;
               }
            };
         }
      }
   }

   private static final class InverseSerializedForm implements Serializable {
      private final HashBiMap bimap;

      InverseSerializedForm(HashBiMap bimap) {
         this.bimap = bimap;
      }

      Object readResolve() {
         return this.bimap.inverse();
      }
   }
}
