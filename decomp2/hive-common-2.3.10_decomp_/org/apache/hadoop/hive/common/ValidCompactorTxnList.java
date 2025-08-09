package org.apache.hadoop.hive.common;

import java.util.Arrays;

public class ValidCompactorTxnList extends ValidReadTxnList {
   public ValidCompactorTxnList() {
   }

   public ValidCompactorTxnList(long[] abortedTxnList, long highWatermark) {
      super(abortedTxnList, highWatermark);
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

   public ValidCompactorTxnList(String value) {
      super(value);
   }

   public ValidTxnList.RangeResponse isTxnRangeValid(long minTxnId, long maxTxnId) {
      return this.highWatermark >= maxTxnId ? ValidTxnList.RangeResponse.ALL : ValidTxnList.RangeResponse.NONE;
   }
}
