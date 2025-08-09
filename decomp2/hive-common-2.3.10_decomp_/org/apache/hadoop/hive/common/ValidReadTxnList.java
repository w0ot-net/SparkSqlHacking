package org.apache.hadoop.hive.common;

import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;

public class ValidReadTxnList implements ValidTxnList {
   protected long[] exceptions;
   private long minOpenTxn;
   protected long highWatermark;

   public ValidReadTxnList() {
      this(new long[0], Long.MAX_VALUE, Long.MAX_VALUE);
   }

   public ValidReadTxnList(long[] exceptions, long highWatermark) {
      this(exceptions, highWatermark, Long.MAX_VALUE);
   }

   public ValidReadTxnList(long[] exceptions, long highWatermark, long minOpenTxn) {
      this.minOpenTxn = Long.MAX_VALUE;
      if (exceptions.length == 0) {
         this.exceptions = exceptions;
      } else {
         this.exceptions = (long[])(([J)exceptions).clone();
         Arrays.sort(this.exceptions);
         this.minOpenTxn = minOpenTxn;
         if (this.exceptions[0] <= 0L) {
            throw new IllegalArgumentException("Invalid txnid: " + this.exceptions[0] + " found");
         }
      }

      this.highWatermark = highWatermark;
   }

   public ValidReadTxnList(String value) {
      this.minOpenTxn = Long.MAX_VALUE;
      this.readFromString(value);
   }

   public boolean isTxnValid(long txnid) {
      if (this.highWatermark < txnid) {
         return false;
      } else {
         return Arrays.binarySearch(this.exceptions, txnid) < 0;
      }
   }

   public boolean isValidBase(long txnid) {
      return this.minOpenTxn > txnid && txnid <= this.highWatermark;
   }

   public ValidTxnList.RangeResponse isTxnRangeValid(long minTxnId, long maxTxnId) {
      if (this.highWatermark < minTxnId) {
         return ValidTxnList.RangeResponse.NONE;
      } else if (this.exceptions.length > 0 && this.exceptions[0] > maxTxnId) {
         return ValidTxnList.RangeResponse.ALL;
      } else {
         long count = Math.max(0L, maxTxnId - this.highWatermark);

         for(long txn : this.exceptions) {
            if (minTxnId <= txn && txn <= maxTxnId) {
               ++count;
            }
         }

         if (count == 0L) {
            return ValidTxnList.RangeResponse.ALL;
         } else if (count == maxTxnId - minTxnId + 1L) {
            return ValidTxnList.RangeResponse.NONE;
         } else {
            return ValidTxnList.RangeResponse.SOME;
         }
      }
   }

   public String toString() {
      return this.writeToString();
   }

   public String writeToString() {
      StringBuilder buf = new StringBuilder();
      buf.append(this.highWatermark);
      buf.append(':');
      buf.append(this.minOpenTxn);
      if (this.exceptions.length == 0) {
         buf.append(':');
      } else {
         for(long except : this.exceptions) {
            buf.append(':');
            buf.append(except);
         }
      }

      return buf.toString();
   }

   public void readFromString(String src) {
      if (src != null && src.length() != 0) {
         String[] values = src.split(":");
         this.highWatermark = Long.parseLong(values[0]);
         this.minOpenTxn = Long.parseLong(values[1]);
         this.exceptions = new long[values.length - 2];

         for(int i = 2; i < values.length; ++i) {
            this.exceptions[i - 2] = Long.parseLong(values[i]);
         }
      } else {
         this.highWatermark = Long.MAX_VALUE;
         this.exceptions = new long[0];
      }

   }

   public long getHighWatermark() {
      return this.highWatermark;
   }

   public long[] getInvalidTransactions() {
      return this.exceptions;
   }

   @VisibleForTesting
   public long getMinOpenTxn() {
      return this.minOpenTxn;
   }
}
