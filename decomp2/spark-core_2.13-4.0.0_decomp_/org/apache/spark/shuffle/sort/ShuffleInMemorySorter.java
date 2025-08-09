package org.apache.spark.shuffle.sort;

import java.util.Comparator;
import org.apache.spark.memory.MemoryConsumer;
import org.apache.spark.unsafe.Platform;
import org.apache.spark.unsafe.array.LongArray;
import org.apache.spark.unsafe.memory.MemoryBlock;
import org.apache.spark.util.collection.Sorter;
import org.apache.spark.util.collection.unsafe.sort.RadixSort;

final class ShuffleInMemorySorter {
   private static final SortComparator SORT_COMPARATOR = new SortComparator();
   private final MemoryConsumer consumer;
   private LongArray array;
   private final boolean useRadixSort;
   private int pos = 0;
   private int usableCapacity = 0;
   private final int initialSize;

   ShuffleInMemorySorter(MemoryConsumer consumer, int initialSize, boolean useRadixSort) {
      this.consumer = consumer;

      assert initialSize > 0;

      this.initialSize = initialSize;
      this.useRadixSort = useRadixSort;
      this.array = consumer.allocateArray((long)initialSize);
      this.usableCapacity = this.getUsableCapacity();
   }

   private int getUsableCapacity() {
      return (int)((double)this.array.size() / (this.useRadixSort ? (double)2.0F : (double)1.5F));
   }

   public void free() {
      if (this.array != null) {
         this.consumer.freeArray(this.array);
         this.array = null;
      }

   }

   public int numRecords() {
      return this.pos;
   }

   public void reset() {
      this.pos = 0;
      if (this.consumer != null) {
         this.consumer.freeArray(this.array);
         this.array = null;
         this.usableCapacity = 0;
         this.array = this.consumer.allocateArray((long)this.initialSize);
         this.usableCapacity = this.getUsableCapacity();
      }

   }

   public void expandPointerArray(LongArray newArray) {
      assert newArray.size() > this.array.size();

      Platform.copyMemory(this.array.getBaseObject(), this.array.getBaseOffset(), newArray.getBaseObject(), newArray.getBaseOffset(), (long)this.pos * 8L);
      this.consumer.freeArray(this.array);
      this.array = newArray;
      this.usableCapacity = this.getUsableCapacity();
   }

   public boolean hasSpaceForAnotherRecord() {
      return this.pos < this.usableCapacity;
   }

   public long getMemoryUsage() {
      return this.array.size() * 8L;
   }

   public void insertRecord(long recordPointer, int partitionId) {
      if (!this.hasSpaceForAnotherRecord()) {
         throw new IllegalStateException("There is no space for new record");
      } else {
         this.array.set(this.pos, PackedRecordPointer.packPointer(recordPointer, partitionId));
         ++this.pos;
      }
   }

   public ShuffleSorterIterator getSortedIterator() {
      int offset = 0;
      if (this.useRadixSort) {
         offset = RadixSort.sort(this.array, (long)this.pos, 5, 7, false, false);
      } else {
         MemoryBlock unused = new MemoryBlock(this.array.getBaseObject(), this.array.getBaseOffset() + (long)this.pos * 8L, (this.array.size() - (long)this.pos) * 8L);
         LongArray buffer = new LongArray(unused);
         Sorter<PackedRecordPointer, LongArray> sorter = new Sorter(new ShuffleSortDataFormat(buffer));
         sorter.sort(this.array, 0, this.pos, SORT_COMPARATOR);
      }

      return new ShuffleSorterIterator(this.pos, this.array, offset);
   }

   private static final class SortComparator implements Comparator {
      public int compare(PackedRecordPointer left, PackedRecordPointer right) {
         return Integer.compare(left.getPartitionId(), right.getPartitionId());
      }
   }

   public static final class ShuffleSorterIterator {
      private final LongArray pointerArray;
      private final int limit;
      final PackedRecordPointer packedRecordPointer = new PackedRecordPointer();
      private int position = 0;

      ShuffleSorterIterator(int numRecords, LongArray pointerArray, int startingPosition) {
         this.limit = numRecords + startingPosition;
         this.pointerArray = pointerArray;
         this.position = startingPosition;
      }

      public boolean hasNext() {
         return this.position < this.limit;
      }

      public void loadNext() {
         this.packedRecordPointer.set(this.pointerArray.get(this.position));
         ++this.position;
      }
   }
}
