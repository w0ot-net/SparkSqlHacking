package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
class CompactHashMap extends AbstractMap implements Serializable {
   private static final Object NOT_FOUND = new Object();
   @VisibleForTesting
   static final double HASH_FLOODING_FPP = 0.001;
   private static final int MAX_HASH_BUCKET_LENGTH = 9;
   @CheckForNull
   private transient Object table;
   @CheckForNull
   @VisibleForTesting
   transient int[] entries;
   @CheckForNull
   @VisibleForTesting
   transient @Nullable Object[] keys;
   @CheckForNull
   @VisibleForTesting
   transient @Nullable Object[] values;
   private transient int metadata;
   private transient int size;
   @LazyInit
   @CheckForNull
   private transient Set keySetView;
   @LazyInit
   @CheckForNull
   private transient Set entrySetView;
   @LazyInit
   @CheckForNull
   private transient Collection valuesView;

   public static CompactHashMap create() {
      return new CompactHashMap();
   }

   public static CompactHashMap createWithExpectedSize(int expectedSize) {
      return new CompactHashMap(expectedSize);
   }

   CompactHashMap() {
      this.init(3);
   }

   CompactHashMap(int expectedSize) {
      this.init(expectedSize);
   }

   void init(int expectedSize) {
      Preconditions.checkArgument(expectedSize >= 0, "Expected size must be >= 0");
      this.metadata = Ints.constrainToRange(expectedSize, 1, 1073741823);
   }

   boolean needsAllocArrays() {
      return this.table == null;
   }

   @CanIgnoreReturnValue
   int allocArrays() {
      Preconditions.checkState(this.needsAllocArrays(), "Arrays already allocated");
      int expectedSize = this.metadata;
      int buckets = CompactHashing.tableSize(expectedSize);
      this.table = CompactHashing.createTable(buckets);
      this.setHashTableMask(buckets - 1);
      this.entries = new int[expectedSize];
      this.keys = new Object[expectedSize];
      this.values = new Object[expectedSize];
      return expectedSize;
   }

   @CheckForNull
   @VisibleForTesting
   Map delegateOrNull() {
      return this.table instanceof Map ? (Map)this.table : null;
   }

   Map createHashFloodingResistantDelegate(int tableSize) {
      return new LinkedHashMap(tableSize, 1.0F);
   }

   @CanIgnoreReturnValue
   Map convertToHashFloodingResistantImplementation() {
      Map<K, V> newDelegate = this.createHashFloodingResistantDelegate(this.hashTableMask() + 1);

      for(int i = this.firstEntryIndex(); i >= 0; i = this.getSuccessor(i)) {
         newDelegate.put(this.key(i), this.value(i));
      }

      this.table = newDelegate;
      this.entries = null;
      this.keys = null;
      this.values = null;
      this.incrementModCount();
      return newDelegate;
   }

   private void setHashTableMask(int mask) {
      int hashTableBits = 32 - Integer.numberOfLeadingZeros(mask);
      this.metadata = CompactHashing.maskCombine(this.metadata, hashTableBits, 31);
   }

   private int hashTableMask() {
      return (1 << (this.metadata & 31)) - 1;
   }

   void incrementModCount() {
      this.metadata += 32;
   }

   void accessEntry(int index) {
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(@ParametricNullness Object key, @ParametricNullness Object value) {
      if (this.needsAllocArrays()) {
         this.allocArrays();
      }

      Map<K, V> delegate = this.delegateOrNull();
      if (delegate != null) {
         return delegate.put(key, value);
      } else {
         int[] entries = this.requireEntries();
         Object[] keys = this.requireKeys();
         Object[] values = this.requireValues();
         int newEntryIndex = this.size;
         int newSize = newEntryIndex + 1;
         int hash = Hashing.smearedHash(key);
         int mask = this.hashTableMask();
         int tableIndex = hash & mask;
         int next = CompactHashing.tableGet(this.requireTable(), tableIndex);
         if (next == 0) {
            if (newSize > mask) {
               mask = this.resizeTable(mask, CompactHashing.newCapacity(mask), hash, newEntryIndex);
            } else {
               CompactHashing.tableSet(this.requireTable(), tableIndex, newEntryIndex + 1);
            }
         } else {
            int hashPrefix = CompactHashing.getHashPrefix(hash, mask);
            int bucketLength = 0;

            int entryIndex;
            int entry;
            do {
               entryIndex = next - 1;
               entry = entries[entryIndex];
               if (CompactHashing.getHashPrefix(entry, mask) == hashPrefix && Objects.equal(key, keys[entryIndex])) {
                  V oldValue = (V)values[entryIndex];
                  values[entryIndex] = value;
                  this.accessEntry(entryIndex);
                  return oldValue;
               }

               next = CompactHashing.getNext(entry, mask);
               ++bucketLength;
            } while(next != 0);

            if (bucketLength >= 9) {
               return this.convertToHashFloodingResistantImplementation().put(key, value);
            }

            if (newSize > mask) {
               mask = this.resizeTable(mask, CompactHashing.newCapacity(mask), hash, newEntryIndex);
            } else {
               entries[entryIndex] = CompactHashing.maskCombine(entry, newEntryIndex + 1, mask);
            }
         }

         this.resizeMeMaybe(newSize);
         this.insertEntry(newEntryIndex, key, value, hash, mask);
         this.size = newSize;
         this.incrementModCount();
         return null;
      }
   }

   void insertEntry(int entryIndex, @ParametricNullness Object key, @ParametricNullness Object value, int hash, int mask) {
      this.setEntry(entryIndex, CompactHashing.maskCombine(hash, 0, mask));
      this.setKey(entryIndex, key);
      this.setValue(entryIndex, value);
   }

   private void resizeMeMaybe(int newSize) {
      int entriesSize = this.requireEntries().length;
      if (newSize > entriesSize) {
         int newCapacity = Math.min(1073741823, entriesSize + Math.max(1, entriesSize >>> 1) | 1);
         if (newCapacity != entriesSize) {
            this.resizeEntries(newCapacity);
         }
      }

   }

   void resizeEntries(int newCapacity) {
      this.entries = Arrays.copyOf(this.requireEntries(), newCapacity);
      this.keys = Arrays.copyOf(this.requireKeys(), newCapacity);
      this.values = Arrays.copyOf(this.requireValues(), newCapacity);
   }

   @CanIgnoreReturnValue
   private int resizeTable(int oldMask, int newCapacity, int targetHash, int targetEntryIndex) {
      Object newTable = CompactHashing.createTable(newCapacity);
      int newMask = newCapacity - 1;
      if (targetEntryIndex != 0) {
         CompactHashing.tableSet(newTable, targetHash & newMask, targetEntryIndex + 1);
      }

      Object oldTable = this.requireTable();
      int[] entries = this.requireEntries();

      int oldEntry;
      for(int oldTableIndex = 0; oldTableIndex <= oldMask; ++oldTableIndex) {
         for(int oldNext = CompactHashing.tableGet(oldTable, oldTableIndex); oldNext != 0; oldNext = CompactHashing.getNext(oldEntry, oldMask)) {
            int entryIndex = oldNext - 1;
            oldEntry = entries[entryIndex];
            int hash = CompactHashing.getHashPrefix(oldEntry, oldMask) | oldTableIndex;
            int newTableIndex = hash & newMask;
            int newNext = CompactHashing.tableGet(newTable, newTableIndex);
            CompactHashing.tableSet(newTable, newTableIndex, oldNext);
            entries[entryIndex] = CompactHashing.maskCombine(hash, newNext, newMask);
         }
      }

      this.table = newTable;
      this.setHashTableMask(newMask);
      return newMask;
   }

   private int indexOf(@CheckForNull Object key) {
      if (this.needsAllocArrays()) {
         return -1;
      } else {
         int hash = Hashing.smearedHash(key);
         int mask = this.hashTableMask();
         int next = CompactHashing.tableGet(this.requireTable(), hash & mask);
         if (next == 0) {
            return -1;
         } else {
            int hashPrefix = CompactHashing.getHashPrefix(hash, mask);

            do {
               int entryIndex = next - 1;
               int entry = this.entry(entryIndex);
               if (CompactHashing.getHashPrefix(entry, mask) == hashPrefix && Objects.equal(key, this.key(entryIndex))) {
                  return entryIndex;
               }

               next = CompactHashing.getNext(entry, mask);
            } while(next != 0);

            return -1;
         }
      }
   }

   public boolean containsKey(@CheckForNull Object key) {
      Map<K, V> delegate = this.delegateOrNull();
      return delegate != null ? delegate.containsKey(key) : this.indexOf(key) != -1;
   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      Map<K, V> delegate = this.delegateOrNull();
      if (delegate != null) {
         return delegate.get(key);
      } else {
         int index = this.indexOf(key);
         if (index == -1) {
            return null;
         } else {
            this.accessEntry(index);
            return this.value(index);
         }
      }
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object remove(@CheckForNull Object key) {
      Map<K, V> delegate = this.delegateOrNull();
      if (delegate != null) {
         return delegate.remove(key);
      } else {
         Object oldValue = this.removeHelper(key);
         return oldValue == NOT_FOUND ? null : oldValue;
      }
   }

   private @Nullable Object removeHelper(@CheckForNull Object key) {
      if (this.needsAllocArrays()) {
         return NOT_FOUND;
      } else {
         int mask = this.hashTableMask();
         int index = CompactHashing.remove(key, (Object)null, mask, this.requireTable(), this.requireEntries(), this.requireKeys(), (Object[])null);
         if (index == -1) {
            return NOT_FOUND;
         } else {
            Object oldValue = this.value(index);
            this.moveLastEntry(index, mask);
            --this.size;
            this.incrementModCount();
            return oldValue;
         }
      }
   }

   void moveLastEntry(int dstIndex, int mask) {
      Object table = this.requireTable();
      int[] entries = this.requireEntries();
      Object[] keys = this.requireKeys();
      Object[] values = this.requireValues();
      int srcIndex = this.size() - 1;
      if (dstIndex < srcIndex) {
         Object key = keys[srcIndex];
         keys[dstIndex] = key;
         values[dstIndex] = values[srcIndex];
         keys[srcIndex] = null;
         values[srcIndex] = null;
         entries[dstIndex] = entries[srcIndex];
         entries[srcIndex] = 0;
         int tableIndex = Hashing.smearedHash(key) & mask;
         int next = CompactHashing.tableGet(table, tableIndex);
         int srcNext = srcIndex + 1;
         if (next == srcNext) {
            CompactHashing.tableSet(table, tableIndex, dstIndex + 1);
         } else {
            int entryIndex;
            int entry;
            do {
               entryIndex = next - 1;
               entry = entries[entryIndex];
               next = CompactHashing.getNext(entry, mask);
            } while(next != srcNext);

            entries[entryIndex] = CompactHashing.maskCombine(entry, dstIndex + 1, mask);
         }
      } else {
         keys[dstIndex] = null;
         values[dstIndex] = null;
         entries[dstIndex] = 0;
      }

   }

   int firstEntryIndex() {
      return this.isEmpty() ? -1 : 0;
   }

   int getSuccessor(int entryIndex) {
      return entryIndex + 1 < this.size ? entryIndex + 1 : -1;
   }

   int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
      return indexBeforeRemove - 1;
   }

   public void replaceAll(BiFunction function) {
      Preconditions.checkNotNull(function);
      Map<K, V> delegate = this.delegateOrNull();
      if (delegate != null) {
         delegate.replaceAll(function);
      } else {
         for(int i = 0; i < this.size; ++i) {
            this.setValue(i, function.apply(this.key(i), this.value(i)));
         }
      }

   }

   public Set keySet() {
      return this.keySetView == null ? (this.keySetView = this.createKeySet()) : this.keySetView;
   }

   Set createKeySet() {
      return new KeySetView();
   }

   Iterator keySetIterator() {
      Map<K, V> delegate = this.delegateOrNull();
      return (Iterator)(delegate != null ? delegate.keySet().iterator() : new Itr() {
         @ParametricNullness
         Object getOutput(int entry) {
            return CompactHashMap.this.key(entry);
         }
      });
   }

   public void forEach(BiConsumer action) {
      Preconditions.checkNotNull(action);
      Map<K, V> delegate = this.delegateOrNull();
      if (delegate != null) {
         delegate.forEach(action);
      } else {
         for(int i = this.firstEntryIndex(); i >= 0; i = this.getSuccessor(i)) {
            action.accept(this.key(i), this.value(i));
         }
      }

   }

   public Set entrySet() {
      return this.entrySetView == null ? (this.entrySetView = this.createEntrySet()) : this.entrySetView;
   }

   Set createEntrySet() {
      return new EntrySetView();
   }

   Iterator entrySetIterator() {
      Map<K, V> delegate = this.delegateOrNull();
      return (Iterator)(delegate != null ? delegate.entrySet().iterator() : new Itr() {
         Map.Entry getOutput(int entry) {
            return CompactHashMap.this.new MapEntry(entry);
         }
      });
   }

   public int size() {
      Map<K, V> delegate = this.delegateOrNull();
      return delegate != null ? delegate.size() : this.size;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public boolean containsValue(@CheckForNull Object value) {
      Map<K, V> delegate = this.delegateOrNull();
      if (delegate != null) {
         return delegate.containsValue(value);
      } else {
         for(int i = 0; i < this.size; ++i) {
            if (Objects.equal(value, this.value(i))) {
               return true;
            }
         }

         return false;
      }
   }

   public Collection values() {
      return this.valuesView == null ? (this.valuesView = this.createValues()) : this.valuesView;
   }

   Collection createValues() {
      return new ValuesView();
   }

   Iterator valuesIterator() {
      Map<K, V> delegate = this.delegateOrNull();
      return (Iterator)(delegate != null ? delegate.values().iterator() : new Itr() {
         @ParametricNullness
         Object getOutput(int entry) {
            return CompactHashMap.this.value(entry);
         }
      });
   }

   public void trimToSize() {
      if (!this.needsAllocArrays()) {
         Map<K, V> delegate = this.delegateOrNull();
         if (delegate != null) {
            Map<K, V> newDelegate = this.createHashFloodingResistantDelegate(this.size());
            newDelegate.putAll(delegate);
            this.table = newDelegate;
         } else {
            int size = this.size;
            if (size < this.requireEntries().length) {
               this.resizeEntries(size);
            }

            int minimumTableSize = CompactHashing.tableSize(size);
            int mask = this.hashTableMask();
            if (minimumTableSize < mask) {
               this.resizeTable(mask, minimumTableSize, 0, 0);
            }

         }
      }
   }

   public void clear() {
      if (!this.needsAllocArrays()) {
         this.incrementModCount();
         Map<K, V> delegate = this.delegateOrNull();
         if (delegate != null) {
            this.metadata = Ints.constrainToRange(this.size(), 3, 1073741823);
            delegate.clear();
            this.table = null;
            this.size = 0;
         } else {
            Arrays.fill(this.requireKeys(), 0, this.size, (Object)null);
            Arrays.fill(this.requireValues(), 0, this.size, (Object)null);
            CompactHashing.tableClear(this.requireTable());
            Arrays.fill(this.requireEntries(), 0, this.size, 0);
            this.size = 0;
         }

      }
   }

   @J2ktIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeInt(this.size());
      Iterator<Map.Entry<K, V>> entryIterator = this.entrySetIterator();

      while(entryIterator.hasNext()) {
         Map.Entry<K, V> e = (Map.Entry)entryIterator.next();
         stream.writeObject(e.getKey());
         stream.writeObject(e.getValue());
      }

   }

   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      int elementCount = stream.readInt();
      if (elementCount < 0) {
         throw new InvalidObjectException("Invalid size: " + elementCount);
      } else {
         this.init(elementCount);

         for(int i = 0; i < elementCount; ++i) {
            K key = (K)stream.readObject();
            V value = (V)stream.readObject();
            this.put(key, value);
         }

      }
   }

   private Object requireTable() {
      return java.util.Objects.requireNonNull(this.table);
   }

   private int[] requireEntries() {
      return (int[])java.util.Objects.requireNonNull(this.entries);
   }

   private @Nullable Object[] requireKeys() {
      return java.util.Objects.requireNonNull(this.keys);
   }

   private @Nullable Object[] requireValues() {
      return java.util.Objects.requireNonNull(this.values);
   }

   private Object key(int i) {
      return this.requireKeys()[i];
   }

   private Object value(int i) {
      return this.requireValues()[i];
   }

   private int entry(int i) {
      return this.requireEntries()[i];
   }

   private void setKey(int i, Object key) {
      this.requireKeys()[i] = key;
   }

   private void setValue(int i, Object value) {
      this.requireValues()[i] = value;
   }

   private void setEntry(int i, int value) {
      this.requireEntries()[i] = value;
   }

   private abstract class Itr implements Iterator {
      int expectedMetadata;
      int currentIndex;
      int indexToRemove;

      private Itr() {
         this.expectedMetadata = CompactHashMap.this.metadata;
         this.currentIndex = CompactHashMap.this.firstEntryIndex();
         this.indexToRemove = -1;
      }

      public boolean hasNext() {
         return this.currentIndex >= 0;
      }

      @ParametricNullness
      abstract Object getOutput(int entry);

      @ParametricNullness
      public Object next() {
         this.checkForConcurrentModification();
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            this.indexToRemove = this.currentIndex;
            T result = (T)this.getOutput(this.currentIndex);
            this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
            return result;
         }
      }

      public void remove() {
         this.checkForConcurrentModification();
         CollectPreconditions.checkRemove(this.indexToRemove >= 0);
         this.incrementExpectedModCount();
         CompactHashMap.this.remove(CompactHashMap.this.key(this.indexToRemove));
         this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
         this.indexToRemove = -1;
      }

      void incrementExpectedModCount() {
         this.expectedMetadata += 32;
      }

      private void checkForConcurrentModification() {
         if (CompactHashMap.this.metadata != this.expectedMetadata) {
            throw new ConcurrentModificationException();
         }
      }
   }

   class KeySetView extends Maps.KeySet {
      KeySetView() {
         super(CompactHashMap.this);
      }

      public @Nullable Object[] toArray() {
         if (CompactHashMap.this.needsAllocArrays()) {
            return new Object[0];
         } else {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.keySet().toArray() : ObjectArrays.copyAsObjectArray(CompactHashMap.this.requireKeys(), 0, CompactHashMap.this.size);
         }
      }

      public Object[] toArray(Object[] a) {
         if (CompactHashMap.this.needsAllocArrays()) {
            if (a.length > 0) {
               a[0] = null;
            }

            return a;
         } else {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.keySet().toArray(a) : ObjectArrays.toArrayImpl(CompactHashMap.this.requireKeys(), 0, CompactHashMap.this.size, a);
         }
      }

      public boolean remove(@CheckForNull Object o) {
         Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
         return delegate != null ? delegate.keySet().remove(o) : CompactHashMap.this.removeHelper(o) != CompactHashMap.NOT_FOUND;
      }

      public Iterator iterator() {
         return CompactHashMap.this.keySetIterator();
      }

      public Spliterator spliterator() {
         if (CompactHashMap.this.needsAllocArrays()) {
            return Spliterators.spliterator(new Object[0], 17);
         } else {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.keySet().spliterator() : Spliterators.spliterator(CompactHashMap.this.requireKeys(), 0, CompactHashMap.this.size, 17);
         }
      }

      public void forEach(Consumer action) {
         Preconditions.checkNotNull(action);
         Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
         if (delegate != null) {
            delegate.keySet().forEach(action);
         } else {
            for(int i = CompactHashMap.this.firstEntryIndex(); i >= 0; i = CompactHashMap.this.getSuccessor(i)) {
               action.accept(CompactHashMap.this.key(i));
            }
         }

      }
   }

   class EntrySetView extends Maps.EntrySet {
      Map map() {
         return CompactHashMap.this;
      }

      public Iterator iterator() {
         return CompactHashMap.this.entrySetIterator();
      }

      public Spliterator spliterator() {
         Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
         return delegate != null ? delegate.entrySet().spliterator() : CollectSpliterators.indexed(CompactHashMap.this.size, 17, (x$0) -> CompactHashMap.this.new MapEntry(x$0));
      }

      public boolean contains(@CheckForNull Object o) {
         Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
         if (delegate != null) {
            return delegate.entrySet().contains(o);
         } else if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            int index = CompactHashMap.this.indexOf(entry.getKey());
            return index != -1 && Objects.equal(CompactHashMap.this.value(index), entry.getValue());
         }
      }

      public boolean remove(@CheckForNull Object o) {
         Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
         if (delegate != null) {
            return delegate.entrySet().remove(o);
         } else if (o instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)o;
            if (CompactHashMap.this.needsAllocArrays()) {
               return false;
            } else {
               int mask = CompactHashMap.this.hashTableMask();
               int index = CompactHashing.remove(entry.getKey(), entry.getValue(), mask, CompactHashMap.this.requireTable(), CompactHashMap.this.requireEntries(), CompactHashMap.this.requireKeys(), CompactHashMap.this.requireValues());
               if (index == -1) {
                  return false;
               } else {
                  CompactHashMap.this.moveLastEntry(index, mask);
                  CompactHashMap.this.size--;
                  CompactHashMap.this.incrementModCount();
                  return true;
               }
            }
         } else {
            return false;
         }
      }
   }

   final class MapEntry extends AbstractMapEntry {
      @ParametricNullness
      private final Object key;
      private int lastKnownIndex;

      MapEntry(int index) {
         this.key = CompactHashMap.this.key(index);
         this.lastKnownIndex = index;
      }

      @ParametricNullness
      public Object getKey() {
         return this.key;
      }

      private void updateLastKnownIndex() {
         if (this.lastKnownIndex == -1 || this.lastKnownIndex >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.key(this.lastKnownIndex))) {
            this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
         }

      }

      @ParametricNullness
      public Object getValue() {
         Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
         if (delegate != null) {
            return NullnessCasts.uncheckedCastNullableTToT(delegate.get(this.key));
         } else {
            this.updateLastKnownIndex();
            return this.lastKnownIndex == -1 ? NullnessCasts.unsafeNull() : CompactHashMap.this.value(this.lastKnownIndex);
         }
      }

      @ParametricNullness
      public Object setValue(@ParametricNullness Object value) {
         Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
         if (delegate != null) {
            return NullnessCasts.uncheckedCastNullableTToT(delegate.put(this.key, value));
         } else {
            this.updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
               CompactHashMap.this.put(this.key, value);
               return NullnessCasts.unsafeNull();
            } else {
               V old = (V)CompactHashMap.this.value(this.lastKnownIndex);
               CompactHashMap.this.setValue(this.lastKnownIndex, value);
               return old;
            }
         }
      }
   }

   class ValuesView extends Maps.Values {
      ValuesView() {
         super(CompactHashMap.this);
      }

      public Iterator iterator() {
         return CompactHashMap.this.valuesIterator();
      }

      public void forEach(Consumer action) {
         Preconditions.checkNotNull(action);
         Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
         if (delegate != null) {
            delegate.values().forEach(action);
         } else {
            for(int i = CompactHashMap.this.firstEntryIndex(); i >= 0; i = CompactHashMap.this.getSuccessor(i)) {
               action.accept(CompactHashMap.this.value(i));
            }
         }

      }

      public Spliterator spliterator() {
         if (CompactHashMap.this.needsAllocArrays()) {
            return Spliterators.spliterator(new Object[0], 16);
         } else {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.values().spliterator() : Spliterators.spliterator(CompactHashMap.this.requireValues(), 0, CompactHashMap.this.size, 16);
         }
      }

      public @Nullable Object[] toArray() {
         if (CompactHashMap.this.needsAllocArrays()) {
            return new Object[0];
         } else {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.values().toArray() : ObjectArrays.copyAsObjectArray(CompactHashMap.this.requireValues(), 0, CompactHashMap.this.size);
         }
      }

      public Object[] toArray(Object[] a) {
         if (CompactHashMap.this.needsAllocArrays()) {
            if (a.length > 0) {
               a[0] = null;
            }

            return a;
         } else {
            Map<K, V> delegate = CompactHashMap.this.delegateOrNull();
            return delegate != null ? delegate.values().toArray(a) : ObjectArrays.toArrayImpl(CompactHashMap.this.requireValues(), 0, CompactHashMap.this.size, a);
         }
      }
   }
}
