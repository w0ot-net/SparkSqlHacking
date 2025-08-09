package org.apache.spark.util.collection.unsafe.sort;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

final class UnsafeSorterSpillMerger {
   private int numRecords = 0;
   private final PriorityQueue priorityQueue;

   UnsafeSorterSpillMerger(RecordComparator recordComparator, PrefixComparator prefixComparator, int numSpills) {
      Comparator<UnsafeSorterIterator> comparator = (left, right) -> {
         int prefixComparisonResult = prefixComparator.compare(left.getKeyPrefix(), right.getKeyPrefix());
         return prefixComparisonResult == 0 ? recordComparator.compare(left.getBaseObject(), left.getBaseOffset(), left.getRecordLength(), right.getBaseObject(), right.getBaseOffset(), right.getRecordLength()) : prefixComparisonResult;
      };
      this.priorityQueue = new PriorityQueue(numSpills, comparator);
   }

   public void addSpillIfNotEmpty(UnsafeSorterIterator spillReader) throws IOException {
      if (spillReader.hasNext()) {
         spillReader.loadNext();
         this.priorityQueue.add(spillReader);
         this.numRecords += spillReader.getNumRecords();
      }

   }

   public UnsafeSorterIterator getSortedIterator() throws IOException {
      return new UnsafeSorterIterator() {
         private UnsafeSorterIterator spillReader;

         public int getNumRecords() {
            return UnsafeSorterSpillMerger.this.numRecords;
         }

         public long getCurrentPageNumber() {
            throw new UnsupportedOperationException();
         }

         public boolean hasNext() {
            return !UnsafeSorterSpillMerger.this.priorityQueue.isEmpty() || this.spillReader != null && this.spillReader.hasNext();
         }

         public void loadNext() throws IOException {
            if (this.spillReader != null && this.spillReader.hasNext()) {
               this.spillReader.loadNext();
               UnsafeSorterSpillMerger.this.priorityQueue.add(this.spillReader);
            }

            this.spillReader = (UnsafeSorterIterator)UnsafeSorterSpillMerger.this.priorityQueue.remove();
         }

         public Object getBaseObject() {
            return this.spillReader.getBaseObject();
         }

         public long getBaseOffset() {
            return this.spillReader.getBaseOffset();
         }

         public int getRecordLength() {
            return this.spillReader.getRecordLength();
         }

         public long getKeyPrefix() {
            return this.spillReader.getKeyPrefix();
         }
      };
   }
}
