package org.apache.spark.util.collection.unsafe.sort;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import javax.annotation.Nullable;
import org.apache.spark.TaskContext;
import org.apache.spark.memory.MemoryConsumer;
import org.apache.spark.memory.SparkOutOfMemoryError;
import org.apache.spark.memory.TaskMemoryManager;
import org.apache.spark.unsafe.Platform;
import org.apache.spark.unsafe.UnsafeAlignedOffset;
import org.apache.spark.unsafe.array.LongArray;
import org.apache.spark.unsafe.memory.MemoryBlock;
import org.apache.spark.util.collection.Sorter;

public final class UnsafeInMemorySorter {
   private final MemoryConsumer consumer;
   private final TaskMemoryManager memoryManager;
   @Nullable
   private final Comparator sortComparator;
   @Nullable
   private final PrefixComparators.RadixSortSupport radixSortSupport;
   private LongArray array;
   private int pos;
   private int nullBoundaryPos;
   private int usableCapacity;
   private long initialSize;
   private long totalSortTimeNanos;

   public UnsafeInMemorySorter(MemoryConsumer consumer, TaskMemoryManager memoryManager, RecordComparator recordComparator, PrefixComparator prefixComparator, int initialSize, boolean canUseRadixSort) {
      this(consumer, memoryManager, recordComparator, prefixComparator, consumer.allocateArray((long)initialSize * 2L), canUseRadixSort);
   }

   public UnsafeInMemorySorter(MemoryConsumer consumer, TaskMemoryManager memoryManager, RecordComparator recordComparator, PrefixComparator prefixComparator, LongArray array, boolean canUseRadixSort) {
      this.pos = 0;
      this.nullBoundaryPos = 0;
      this.usableCapacity = 0;
      this.totalSortTimeNanos = 0L;
      this.consumer = consumer;
      this.memoryManager = memoryManager;
      this.initialSize = array.size();
      if (recordComparator != null) {
         this.sortComparator = new SortComparator(recordComparator, prefixComparator, memoryManager);
         if (canUseRadixSort && prefixComparator instanceof PrefixComparators.RadixSortSupport) {
            PrefixComparators.RadixSortSupport radixSortSupport = (PrefixComparators.RadixSortSupport)prefixComparator;
            this.radixSortSupport = radixSortSupport;
         } else {
            this.radixSortSupport = null;
         }
      } else {
         this.sortComparator = null;
         this.radixSortSupport = null;
      }

      this.array = array;
      this.usableCapacity = this.getUsableCapacity();
   }

   private int getUsableCapacity() {
      return (int)((double)this.array.size() / (this.radixSortSupport != null ? (double)2.0F : (double)1.5F));
   }

   public long getInitialSize() {
      return this.initialSize;
   }

   public void freeMemory() {
      if (this.consumer != null) {
         if (this.array != null) {
            this.consumer.freeArray(this.array);
         }

         this.array = null;
         this.usableCapacity = 0;
      }

      this.pos = 0;
      this.nullBoundaryPos = 0;
   }

   public int numRecords() {
      return this.pos / 2;
   }

   public long getSortTimeNanos() {
      return this.totalSortTimeNanos;
   }

   public long getMemoryUsage() {
      return this.array == null ? 0L : this.array.size() * 8L;
   }

   public boolean hasSpaceForAnotherRecord() {
      return this.pos + 1 < this.usableCapacity;
   }

   public void expandPointerArray(LongArray newArray) {
      if (this.array != null) {
         if (newArray.size() < this.array.size()) {
            throw new SparkOutOfMemoryError("_LEGACY_ERROR_TEMP_3301", new HashMap());
         }

         Platform.copyMemory(this.array.getBaseObject(), this.array.getBaseOffset(), newArray.getBaseObject(), newArray.getBaseOffset(), (long)this.pos * 8L);
         this.consumer.freeArray(this.array);
      }

      this.array = newArray;
      this.usableCapacity = this.getUsableCapacity();
   }

   public void insertRecord(long recordPointer, long keyPrefix, boolean prefixIsNull) {
      if (!this.hasSpaceForAnotherRecord()) {
         throw new IllegalStateException("There is no space for new record");
      } else {
         if (prefixIsNull && this.radixSortSupport != null) {
            this.array.set(this.pos, this.array.get(this.nullBoundaryPos));
            ++this.pos;
            this.array.set(this.pos, this.array.get(this.nullBoundaryPos + 1));
            ++this.pos;
            this.array.set(this.nullBoundaryPos, recordPointer);
            ++this.nullBoundaryPos;
            this.array.set(this.nullBoundaryPos, keyPrefix);
            ++this.nullBoundaryPos;
         } else {
            this.array.set(this.pos, recordPointer);
            ++this.pos;
            this.array.set(this.pos, keyPrefix);
            ++this.pos;
         }

      }
   }

   public UnsafeSorterIterator getSortedIterator() {
      if (this.numRecords() == 0) {
         return new SortedIterator(0, 0);
      } else {
         int offset = 0;
         long start = System.nanoTime();
         if (this.sortComparator != null) {
            if (this.radixSortSupport != null) {
               offset = RadixSort.sortKeyPrefixArray(this.array, (long)this.nullBoundaryPos, (long)(this.pos - this.nullBoundaryPos) / 2L, 0, 7, this.radixSortSupport.sortDescending(), this.radixSortSupport.sortSigned());
            } else {
               MemoryBlock unused = new MemoryBlock(this.array.getBaseObject(), this.array.getBaseOffset() + (long)this.pos * 8L, (this.array.size() - (long)this.pos) * 8L);
               LongArray buffer = new LongArray(unused);
               Sorter<RecordPointerAndKeyPrefix, LongArray> sorter = new Sorter(new UnsafeSortDataFormat(buffer));
               sorter.sort(this.array, 0, this.pos / 2, this.sortComparator);
            }
         }

         this.totalSortTimeNanos += System.nanoTime() - start;
         if (this.nullBoundaryPos > 0) {
            assert this.radixSortSupport != null : "Nulls are only stored separately with radix sort";

            LinkedList<UnsafeSorterIterator> queue = new LinkedList();
            if (this.radixSortSupport.nullsFirst()) {
               queue.add(new SortedIterator(this.nullBoundaryPos / 2, 0));
               queue.add(new SortedIterator((this.pos - this.nullBoundaryPos) / 2, offset));
            } else {
               queue.add(new SortedIterator((this.pos - this.nullBoundaryPos) / 2, offset));
               queue.add(new SortedIterator(this.nullBoundaryPos / 2, 0));
            }

            return new UnsafeExternalSorter.ChainedIterator(queue);
         } else {
            return new SortedIterator(this.pos / 2, offset);
         }
      }
   }

   private static final class SortComparator implements Comparator {
      private final RecordComparator recordComparator;
      private final PrefixComparator prefixComparator;
      private final TaskMemoryManager memoryManager;

      SortComparator(RecordComparator recordComparator, PrefixComparator prefixComparator, TaskMemoryManager memoryManager) {
         this.recordComparator = recordComparator;
         this.prefixComparator = prefixComparator;
         this.memoryManager = memoryManager;
      }

      public int compare(RecordPointerAndKeyPrefix r1, RecordPointerAndKeyPrefix r2) {
         int prefixComparisonResult = this.prefixComparator.compare(r1.keyPrefix, r2.keyPrefix);
         int uaoSize = UnsafeAlignedOffset.getUaoSize();
         if (prefixComparisonResult == 0) {
            Object baseObject1 = this.memoryManager.getPage(r1.recordPointer);
            long baseOffset1 = this.memoryManager.getOffsetInPage(r1.recordPointer) + (long)uaoSize;
            int baseLength1 = UnsafeAlignedOffset.getSize(baseObject1, baseOffset1 - (long)uaoSize);
            Object baseObject2 = this.memoryManager.getPage(r2.recordPointer);
            long baseOffset2 = this.memoryManager.getOffsetInPage(r2.recordPointer) + (long)uaoSize;
            int baseLength2 = UnsafeAlignedOffset.getSize(baseObject2, baseOffset2 - (long)uaoSize);
            return this.recordComparator.compare(baseObject1, baseOffset1, baseLength1, baseObject2, baseOffset2, baseLength2);
         } else {
            return prefixComparisonResult;
         }
      }
   }

   public final class SortedIterator extends UnsafeSorterIterator implements Cloneable {
      private final int numRecords;
      private int position;
      private int offset;
      private Object baseObject;
      private long baseOffset;
      private long keyPrefix;
      private int recordLength;
      private long currentPageNumber;
      private final TaskContext taskContext = TaskContext.get();

      private SortedIterator(int numRecords, int offset) {
         this.numRecords = numRecords;
         this.position = 0;
         this.offset = offset;
      }

      public SortedIterator clone() {
         SortedIterator iter = UnsafeInMemorySorter.this.new SortedIterator(this.numRecords, this.offset);
         iter.position = this.position;
         iter.baseObject = this.baseObject;
         iter.baseOffset = this.baseOffset;
         iter.keyPrefix = this.keyPrefix;
         iter.recordLength = this.recordLength;
         iter.currentPageNumber = this.currentPageNumber;
         return iter;
      }

      public int getNumRecords() {
         return this.numRecords;
      }

      public boolean hasNext() {
         return this.position / 2 < this.numRecords;
      }

      public void loadNext() {
         if (this.taskContext != null) {
            this.taskContext.killTaskIfInterrupted();
         }

         long recordPointer = UnsafeInMemorySorter.this.array.get(this.offset + this.position);
         this.currentPageNumber = (long)TaskMemoryManager.decodePageNumber(recordPointer);
         int uaoSize = UnsafeAlignedOffset.getUaoSize();
         this.baseObject = UnsafeInMemorySorter.this.memoryManager.getPage(recordPointer);
         this.baseOffset = UnsafeInMemorySorter.this.memoryManager.getOffsetInPage(recordPointer) + (long)uaoSize;
         this.recordLength = UnsafeAlignedOffset.getSize(this.baseObject, this.baseOffset - (long)uaoSize);
         this.keyPrefix = UnsafeInMemorySorter.this.array.get(this.offset + this.position + 1);
         this.position += 2;
      }

      public Object getBaseObject() {
         return this.baseObject;
      }

      public long getBaseOffset() {
         return this.baseOffset;
      }

      public long getCurrentPageNumber() {
         return this.currentPageNumber;
      }

      public int getRecordLength() {
         return this.recordLength;
      }

      public long getKeyPrefix() {
         return this.keyPrefix;
      }
   }
}
