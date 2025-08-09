package org.apache.curator.shaded.com.google.common.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
class CompactLinkedHashMap extends CompactHashMap {
   private static final int ENDPOINT = -2;
   @CheckForNull
   @VisibleForTesting
   transient long[] links;
   private transient int firstEntry;
   private transient int lastEntry;
   private final boolean accessOrder;

   public static CompactLinkedHashMap create() {
      return new CompactLinkedHashMap();
   }

   public static CompactLinkedHashMap createWithExpectedSize(int expectedSize) {
      return new CompactLinkedHashMap(expectedSize);
   }

   CompactLinkedHashMap() {
      this(3);
   }

   CompactLinkedHashMap(int expectedSize) {
      this(expectedSize, false);
   }

   CompactLinkedHashMap(int expectedSize, boolean accessOrder) {
      super(expectedSize);
      this.accessOrder = accessOrder;
   }

   void init(int expectedSize) {
      super.init(expectedSize);
      this.firstEntry = -2;
      this.lastEntry = -2;
   }

   int allocArrays() {
      int expectedSize = super.allocArrays();
      this.links = new long[expectedSize];
      return expectedSize;
   }

   Map createHashFloodingResistantDelegate(int tableSize) {
      return new LinkedHashMap(tableSize, 1.0F, this.accessOrder);
   }

   @CanIgnoreReturnValue
   Map convertToHashFloodingResistantImplementation() {
      Map<K, V> result = super.convertToHashFloodingResistantImplementation();
      this.links = null;
      return result;
   }

   private int getPredecessor(int entry) {
      return (int)(this.link(entry) >>> 32) - 1;
   }

   int getSuccessor(int entry) {
      return (int)this.link(entry) - 1;
   }

   private void setSuccessor(int entry, int succ) {
      long succMask = 4294967295L;
      this.setLink(entry, this.link(entry) & ~succMask | (long)(succ + 1) & succMask);
   }

   private void setPredecessor(int entry, int pred) {
      long predMask = -4294967296L;
      this.setLink(entry, this.link(entry) & ~predMask | (long)(pred + 1) << 32);
   }

   private void setSucceeds(int pred, int succ) {
      if (pred == -2) {
         this.firstEntry = succ;
      } else {
         this.setSuccessor(pred, succ);
      }

      if (succ == -2) {
         this.lastEntry = pred;
      } else {
         this.setPredecessor(succ, pred);
      }

   }

   void insertEntry(int entryIndex, @ParametricNullness Object key, @ParametricNullness Object value, int hash, int mask) {
      super.insertEntry(entryIndex, key, value, hash, mask);
      this.setSucceeds(this.lastEntry, entryIndex);
      this.setSucceeds(entryIndex, -2);
   }

   void accessEntry(int index) {
      if (this.accessOrder) {
         this.setSucceeds(this.getPredecessor(index), this.getSuccessor(index));
         this.setSucceeds(this.lastEntry, index);
         this.setSucceeds(index, -2);
         this.incrementModCount();
      }

   }

   void moveLastEntry(int dstIndex, int mask) {
      int srcIndex = this.size() - 1;
      super.moveLastEntry(dstIndex, mask);
      this.setSucceeds(this.getPredecessor(dstIndex), this.getSuccessor(dstIndex));
      if (dstIndex < srcIndex) {
         this.setSucceeds(this.getPredecessor(srcIndex), dstIndex);
         this.setSucceeds(dstIndex, this.getSuccessor(srcIndex));
      }

      this.setLink(srcIndex, 0L);
   }

   void resizeEntries(int newCapacity) {
      super.resizeEntries(newCapacity);
      this.links = Arrays.copyOf(this.requireLinks(), newCapacity);
   }

   int firstEntryIndex() {
      return this.firstEntry;
   }

   int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
      return indexBeforeRemove >= this.size() ? indexRemoved : indexBeforeRemove;
   }

   Set createEntrySet() {
      class EntrySetImpl extends CompactHashMap.EntrySetView {
         public Spliterator spliterator() {
            return Spliterators.spliterator(this, 17);
         }
      }

      return new EntrySetImpl();
   }

   Set createKeySet() {
      class KeySetImpl extends CompactHashMap.KeySetView {
         public @Nullable Object[] toArray() {
            return ObjectArrays.toArrayImpl(this);
         }

         public Object[] toArray(Object[] a) {
            return ObjectArrays.toArrayImpl(this, a);
         }

         public Spliterator spliterator() {
            return Spliterators.spliterator(this, 17);
         }
      }

      return new KeySetImpl();
   }

   Collection createValues() {
      class ValuesImpl extends CompactHashMap.ValuesView {
         public @Nullable Object[] toArray() {
            return ObjectArrays.toArrayImpl(this);
         }

         public Object[] toArray(Object[] a) {
            return ObjectArrays.toArrayImpl(this, a);
         }

         public Spliterator spliterator() {
            return Spliterators.spliterator(this, 16);
         }
      }

      return new ValuesImpl();
   }

   public void clear() {
      if (!this.needsAllocArrays()) {
         this.firstEntry = -2;
         this.lastEntry = -2;
         if (this.links != null) {
            Arrays.fill(this.links, 0, this.size(), 0L);
         }

         super.clear();
      }
   }

   private long[] requireLinks() {
      return (long[])Objects.requireNonNull(this.links);
   }

   private long link(int i) {
      return this.requireLinks()[i];
   }

   private void setLink(int i, long value) {
      this.requireLinks()[i] = value;
   }
}
