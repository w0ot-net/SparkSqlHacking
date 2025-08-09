package org.apache.hadoop.hive.common;

import java.util.Arrays;
import java.util.BitSet;

public class ValidCompactorWriteIdList extends ValidReaderWriteIdList {
   public ValidCompactorWriteIdList() {
   }

   public ValidCompactorWriteIdList(String tableName, long[] abortedWriteIdList, BitSet abortedBits, long highWatermark) {
      this(tableName, abortedWriteIdList, abortedBits, highWatermark, Long.MAX_VALUE);
   }

   public ValidCompactorWriteIdList(String tableName, long[] abortedWriteIdList, BitSet abortedBits, long highWatermark, long minOpenWriteId) {
      super(tableName, abortedWriteIdList, abortedBits, highWatermark, minOpenWriteId);
      if (this.exceptions.length > 0) {
         int idx = Arrays.binarySearch(this.exceptions, highWatermark);
         int lastElementPos;
         if (idx < 0) {
            int insertionPoint = -idx - 1;
            lastElementPos = insertionPoint - 1;
         } else {
            lastElementPos = idx;
         }

         this.exceptions = Arrays.copyOf(this.exceptions, lastElementPos + 1);
      }
   }

   public ValidCompactorWriteIdList(String value) {
      super(value);
   }

   public ValidWriteIdList.RangeResponse isWriteIdRangeValid(long minWriteId, long maxWriteId) {
      return this.highWatermark >= maxWriteId ? ValidWriteIdList.RangeResponse.ALL : ValidWriteIdList.RangeResponse.NONE;
   }

   public boolean isWriteIdAborted(long writeId) {
      return Arrays.binarySearch(this.exceptions, writeId) >= 0;
   }
}
