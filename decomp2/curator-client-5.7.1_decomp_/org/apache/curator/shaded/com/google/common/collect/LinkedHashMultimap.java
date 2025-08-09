package org.apache.curator.shaded.com.google.common.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public final class LinkedHashMultimap extends LinkedHashMultimapGwtSerializationDependencies {
   private static final int DEFAULT_KEY_CAPACITY = 16;
   private static final int DEFAULT_VALUE_SET_CAPACITY = 2;
   @VisibleForTesting
   static final double VALUE_SET_LOAD_FACTOR = (double)1.0F;
   @VisibleForTesting
   transient int valueSetCapacity = 2;
   private transient ValueEntry multimapHeaderEntry;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 1L;

   public static LinkedHashMultimap create() {
      return new LinkedHashMultimap(16, 2);
   }

   public static LinkedHashMultimap create(int expectedKeys, int expectedValuesPerKey) {
      return new LinkedHashMultimap(Maps.capacity(expectedKeys), Maps.capacity(expectedValuesPerKey));
   }

   public static LinkedHashMultimap create(Multimap multimap) {
      LinkedHashMultimap<K, V> result = create(multimap.keySet().size(), 2);
      result.putAll(multimap);
      return result;
   }

   private static void succeedsInValueSet(ValueSetLink pred, ValueSetLink succ) {
      pred.setSuccessorInValueSet(succ);
      succ.setPredecessorInValueSet(pred);
   }

   private static void succeedsInMultimap(ValueEntry pred, ValueEntry succ) {
      pred.setSuccessorInMultimap(succ);
      succ.setPredecessorInMultimap(pred);
   }

   private static void deleteFromValueSet(ValueSetLink entry) {
      succeedsInValueSet(entry.getPredecessorInValueSet(), entry.getSuccessorInValueSet());
   }

   private static void deleteFromMultimap(ValueEntry entry) {
      succeedsInMultimap(entry.getPredecessorInMultimap(), entry.getSuccessorInMultimap());
   }

   private LinkedHashMultimap(int keyCapacity, int valueSetCapacity) {
      super(Platform.newLinkedHashMapWithExpectedSize(keyCapacity));
      CollectPreconditions.checkNonnegative(valueSetCapacity, "expectedValuesPerKey");
      this.valueSetCapacity = valueSetCapacity;
      this.multimapHeaderEntry = LinkedHashMultimap.ValueEntry.newHeader();
      succeedsInMultimap(this.multimapHeaderEntry, this.multimapHeaderEntry);
   }

   Set createCollection() {
      return Platform.newLinkedHashSetWithExpectedSize(this.valueSetCapacity);
   }

   Collection createCollection(@ParametricNullness Object key) {
      return new ValueSet(key, this.valueSetCapacity);
   }

   @CanIgnoreReturnValue
   public Set replaceValues(@ParametricNullness Object key, Iterable values) {
      return super.replaceValues(key, values);
   }

   public Set entries() {
      return super.entries();
   }

   public Set keySet() {
      return super.keySet();
   }

   public Collection values() {
      return super.values();
   }

   Iterator entryIterator() {
      return new Iterator() {
         ValueEntry nextEntry;
         @CheckForNull
         ValueEntry toRemove;

         {
            this.nextEntry = LinkedHashMultimap.this.multimapHeaderEntry.getSuccessorInMultimap();
         }

         public boolean hasNext() {
            return this.nextEntry != LinkedHashMultimap.this.multimapHeaderEntry;
         }

         public Map.Entry next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               ValueEntry<K, V> result = this.nextEntry;
               this.toRemove = result;
               this.nextEntry = this.nextEntry.getSuccessorInMultimap();
               return result;
            }
         }

         public void remove() {
            Preconditions.checkState(this.toRemove != null, "no calls to next() since the last call to remove()");
            LinkedHashMultimap.this.remove(this.toRemove.getKey(), this.toRemove.getValue());
            this.toRemove = null;
         }
      };
   }

   Spliterator entrySpliterator() {
      return Spliterators.spliterator(this.entries(), 17);
   }

   Iterator valueIterator() {
      return Maps.valueIterator(this.entryIterator());
   }

   Spliterator valueSpliterator() {
      return CollectSpliterators.map(this.entrySpliterator(), Map.Entry::getValue);
   }

   public void clear() {
      super.clear();
      succeedsInMultimap(this.multimapHeaderEntry, this.multimapHeaderEntry);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeInt(this.keySet().size());

      for(Object key : this.keySet()) {
         stream.writeObject(key);
      }

      stream.writeInt(this.size());

      for(Map.Entry entry : this.entries()) {
         stream.writeObject(entry.getKey());
         stream.writeObject(entry.getValue());
      }

   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.multimapHeaderEntry = LinkedHashMultimap.ValueEntry.newHeader();
      succeedsInMultimap(this.multimapHeaderEntry, this.multimapHeaderEntry);
      this.valueSetCapacity = 2;
      int distinctKeys = stream.readInt();
      Map<K, Collection<V>> map = Platform.newLinkedHashMapWithExpectedSize(12);

      for(int i = 0; i < distinctKeys; ++i) {
         K key = (K)stream.readObject();
         map.put(key, this.createCollection(key));
      }

      int entries = stream.readInt();

      for(int i = 0; i < entries; ++i) {
         K key = (K)stream.readObject();
         V value = (V)stream.readObject();
         ((Collection)Objects.requireNonNull((Collection)map.get(key))).add(value);
      }

      this.setMap(map);
   }

   @VisibleForTesting
   static final class ValueEntry extends ImmutableEntry implements ValueSetLink {
      final int smearedValueHash;
      @CheckForNull
      ValueEntry nextInValueBucket;
      @CheckForNull
      ValueSetLink predecessorInValueSet;
      @CheckForNull
      ValueSetLink successorInValueSet;
      @CheckForNull
      ValueEntry predecessorInMultimap;
      @CheckForNull
      ValueEntry successorInMultimap;

      ValueEntry(@ParametricNullness Object key, @ParametricNullness Object value, int smearedValueHash, @CheckForNull ValueEntry nextInValueBucket) {
         super(key, value);
         this.smearedValueHash = smearedValueHash;
         this.nextInValueBucket = nextInValueBucket;
      }

      static ValueEntry newHeader() {
         return new ValueEntry((Object)null, (Object)null, 0, (ValueEntry)null);
      }

      boolean matchesValue(@CheckForNull Object v, int smearedVHash) {
         return this.smearedValueHash == smearedVHash && org.apache.curator.shaded.com.google.common.base.Objects.equal(this.getValue(), v);
      }

      public ValueSetLink getPredecessorInValueSet() {
         return (ValueSetLink)Objects.requireNonNull(this.predecessorInValueSet);
      }

      public ValueSetLink getSuccessorInValueSet() {
         return (ValueSetLink)Objects.requireNonNull(this.successorInValueSet);
      }

      public void setPredecessorInValueSet(ValueSetLink entry) {
         this.predecessorInValueSet = entry;
      }

      public void setSuccessorInValueSet(ValueSetLink entry) {
         this.successorInValueSet = entry;
      }

      public ValueEntry getPredecessorInMultimap() {
         return (ValueEntry)Objects.requireNonNull(this.predecessorInMultimap);
      }

      public ValueEntry getSuccessorInMultimap() {
         return (ValueEntry)Objects.requireNonNull(this.successorInMultimap);
      }

      public void setSuccessorInMultimap(ValueEntry multimapSuccessor) {
         this.successorInMultimap = multimapSuccessor;
      }

      public void setPredecessorInMultimap(ValueEntry multimapPredecessor) {
         this.predecessorInMultimap = multimapPredecessor;
      }
   }

   @VisibleForTesting
   final class ValueSet extends Sets.ImprovedAbstractSet implements ValueSetLink {
      @ParametricNullness
      private final Object key;
      @VisibleForTesting
      @Nullable LinkedHashMultimap.ValueEntry[] hashTable;
      private int size = 0;
      private int modCount = 0;
      private ValueSetLink firstEntry;
      private ValueSetLink lastEntry;

      ValueSet(@ParametricNullness Object key, int expectedValues) {
         this.key = key;
         this.firstEntry = this;
         this.lastEntry = this;
         int tableSize = Hashing.closedTableSize(expectedValues, (double)1.0F);
         ValueEntry<K, V>[] hashTable = new ValueEntry[tableSize];
         this.hashTable = hashTable;
      }

      private int mask() {
         return this.hashTable.length - 1;
      }

      public ValueSetLink getPredecessorInValueSet() {
         return this.lastEntry;
      }

      public ValueSetLink getSuccessorInValueSet() {
         return this.firstEntry;
      }

      public void setPredecessorInValueSet(ValueSetLink entry) {
         this.lastEntry = entry;
      }

      public void setSuccessorInValueSet(ValueSetLink entry) {
         this.firstEntry = entry;
      }

      public Iterator iterator() {
         return new Iterator() {
            ValueSetLink nextEntry;
            @CheckForNull
            ValueEntry toRemove;
            int expectedModCount;

            {
               this.nextEntry = ValueSet.this.firstEntry;
               this.expectedModCount = ValueSet.this.modCount;
            }

            private void checkForComodification() {
               if (ValueSet.this.modCount != this.expectedModCount) {
                  throw new ConcurrentModificationException();
               }
            }

            public boolean hasNext() {
               this.checkForComodification();
               return this.nextEntry != ValueSet.this;
            }

            @ParametricNullness
            public Object next() {
               if (!this.hasNext()) {
                  throw new NoSuchElementException();
               } else {
                  ValueEntry<K, V> entry = (ValueEntry)this.nextEntry;
                  V result = (V)entry.getValue();
                  this.toRemove = entry;
                  this.nextEntry = entry.getSuccessorInValueSet();
                  return result;
               }
            }

            public void remove() {
               this.checkForComodification();
               Preconditions.checkState(this.toRemove != null, "no calls to next() since the last call to remove()");
               ValueSet.this.remove(this.toRemove.getValue());
               this.expectedModCount = ValueSet.this.modCount;
               this.toRemove = null;
            }
         };
      }

      public void forEach(Consumer action) {
         Preconditions.checkNotNull(action);

         for(ValueSetLink<K, V> entry = this.firstEntry; entry != this; entry = entry.getSuccessorInValueSet()) {
            action.accept(((ValueEntry)entry).getValue());
         }

      }

      public int size() {
         return this.size;
      }

      public boolean contains(@CheckForNull Object o) {
         int smearedHash = Hashing.smearedHash(o);

         for(ValueEntry<K, V> entry = this.hashTable[smearedHash & this.mask()]; entry != null; entry = entry.nextInValueBucket) {
            if (entry.matchesValue(o, smearedHash)) {
               return true;
            }
         }

         return false;
      }

      public boolean add(@ParametricNullness Object value) {
         int smearedHash = Hashing.smearedHash(value);
         int bucket = smearedHash & this.mask();
         ValueEntry<K, V> rowHead = this.hashTable[bucket];

         for(ValueEntry<K, V> entry = rowHead; entry != null; entry = entry.nextInValueBucket) {
            if (entry.matchesValue(value, smearedHash)) {
               return false;
            }
         }

         ValueEntry<K, V> newEntry = new ValueEntry(this.key, value, smearedHash, rowHead);
         LinkedHashMultimap.succeedsInValueSet(this.lastEntry, newEntry);
         LinkedHashMultimap.succeedsInValueSet(newEntry, this);
         LinkedHashMultimap.succeedsInMultimap(LinkedHashMultimap.this.multimapHeaderEntry.getPredecessorInMultimap(), newEntry);
         LinkedHashMultimap.succeedsInMultimap(newEntry, LinkedHashMultimap.this.multimapHeaderEntry);
         this.hashTable[bucket] = newEntry;
         ++this.size;
         ++this.modCount;
         this.rehashIfNecessary();
         return true;
      }

      private void rehashIfNecessary() {
         if (Hashing.needsResizing(this.size, this.hashTable.length, (double)1.0F)) {
            ValueEntry<K, V>[] hashTable = new ValueEntry[this.hashTable.length * 2];
            this.hashTable = hashTable;
            int mask = hashTable.length - 1;

            for(ValueSetLink<K, V> entry = this.firstEntry; entry != this; entry = entry.getSuccessorInValueSet()) {
               ValueEntry<K, V> valueEntry = (ValueEntry)entry;
               int bucket = valueEntry.smearedValueHash & mask;
               valueEntry.nextInValueBucket = hashTable[bucket];
               hashTable[bucket] = valueEntry;
            }
         }

      }

      @CanIgnoreReturnValue
      public boolean remove(@CheckForNull Object o) {
         int smearedHash = Hashing.smearedHash(o);
         int bucket = smearedHash & this.mask();
         ValueEntry<K, V> prev = null;

         for(ValueEntry<K, V> entry = this.hashTable[bucket]; entry != null; entry = entry.nextInValueBucket) {
            if (entry.matchesValue(o, smearedHash)) {
               if (prev == null) {
                  this.hashTable[bucket] = entry.nextInValueBucket;
               } else {
                  prev.nextInValueBucket = entry.nextInValueBucket;
               }

               LinkedHashMultimap.deleteFromValueSet(entry);
               LinkedHashMultimap.deleteFromMultimap(entry);
               --this.size;
               ++this.modCount;
               return true;
            }

            prev = entry;
         }

         return false;
      }

      public void clear() {
         Arrays.fill(this.hashTable, (Object)null);
         this.size = 0;

         for(ValueSetLink<K, V> entry = this.firstEntry; entry != this; entry = entry.getSuccessorInValueSet()) {
            ValueEntry<K, V> valueEntry = (ValueEntry)entry;
            LinkedHashMultimap.deleteFromMultimap(valueEntry);
         }

         LinkedHashMultimap.succeedsInValueSet(this, this);
         ++this.modCount;
      }
   }

   private interface ValueSetLink {
      ValueSetLink getPredecessorInValueSet();

      ValueSetLink getSuccessorInValueSet();

      void setPredecessorInValueSet(ValueSetLink entry);

      void setSuccessorInValueSet(ValueSetLink entry);
   }
}
