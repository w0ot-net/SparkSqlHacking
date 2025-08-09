package org.apache.hadoop.hive.common;

import org.apache.hadoop.hive.common.io.DiskRangeList;

public class DiskRangeInfo {
   private DiskRangeList head;
   private DiskRangeList tail = null;
   private long totalLength;

   public DiskRangeInfo(int indexBaseOffset) {
      this.totalLength = (long)indexBaseOffset;
   }

   public void addDiskRange(DiskRangeList diskRange) {
      if (this.tail == null) {
         this.head = this.tail = diskRange;
      } else {
         this.tail = this.tail.insertAfter(diskRange);
      }

      this.totalLength += (long)diskRange.getLength();
   }

   public DiskRangeList getDiskRanges() {
      return this.head;
   }

   public long getTotalLength() {
      return this.totalLength;
   }
}
