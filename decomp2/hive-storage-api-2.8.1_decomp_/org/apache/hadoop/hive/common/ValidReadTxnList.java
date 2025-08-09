package org.apache.hadoop.hive.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hive.common.util.SuppressFBWarnings;

public class ValidReadTxnList implements ValidTxnList {
   private static final int MIN_RANGE_LENGTH = 5;
   protected long[] exceptions;
   protected BitSet abortedBits;
   private long minOpenTxn;
   protected long highWatermark;

   public ValidReadTxnList() {
      this(new long[0], new BitSet(), Long.MAX_VALUE, Long.MAX_VALUE);
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "Ref external obj for efficiency"
   )
   public ValidReadTxnList(long[] exceptions, BitSet abortedBits, long highWatermark, long minOpenTxn) {
      this.minOpenTxn = Long.MAX_VALUE;
      if (exceptions.length > 0) {
         this.minOpenTxn = minOpenTxn;
      }

      this.exceptions = exceptions;
      this.abortedBits = abortedBits;
      this.highWatermark = highWatermark;
   }

   public ValidReadTxnList(String value) {
      this.minOpenTxn = Long.MAX_VALUE;
      this.readFromString(value);
   }

   public void removeException(long txnId) {
      this.exceptions = ArrayUtils.remove(this.exceptions, Arrays.binarySearch(this.exceptions, txnId));
   }

   public boolean isTxnValid(long txnid) {
      if (txnid > this.highWatermark) {
         return false;
      } else {
         return Arrays.binarySearch(this.exceptions, txnid) < 0;
      }
   }

   public ValidTxnList.RangeResponse isTxnRangeValid(long minTxnId, long maxTxnId) {
      if (minTxnId > this.highWatermark) {
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
         buf.append(':');
      } else {
         StringBuilder open = new StringBuilder();
         StringBuilder abort = new StringBuilder();
         long abortedMin = -1L;
         long abortedMax = -1L;
         long openedMin = -1L;
         long openedMax = -1L;

         for(int i = 0; i < this.exceptions.length; ++i) {
            if (this.abortedBits.get(i)) {
               if (abortedMax + 1L == this.exceptions[i]) {
                  ++abortedMax;
               } else {
                  this.writeTxnRange(abort, abortedMin, abortedMax);
                  abortedMin = abortedMax = this.exceptions[i];
               }
            } else if (openedMax + 1L == this.exceptions[i]) {
               ++openedMax;
            } else {
               this.writeTxnRange(open, openedMin, openedMax);
               openedMin = openedMax = this.exceptions[i];
            }
         }

         this.writeTxnRange(abort, abortedMin, abortedMax);
         this.writeTxnRange(open, openedMin, openedMax);
         buf.append(':');
         buf.append(open);
         buf.append(':');
         buf.append(abort);
      }

      return buf.toString();
   }

   private void writeTxnRange(StringBuilder builder, long txnMin, long txnMax) {
      if (txnMax >= 0L) {
         if (builder.length() > 0) {
            builder.append(',');
         }

         if (txnMin == txnMax) {
            builder.append(txnMin);
         } else if (txnMin + 5L - 1L > txnMax) {
            for(long txn = txnMin; txn <= txnMax; ++txn) {
               builder.append(txn);
               if (txn != txnMax) {
                  builder.append(',');
               }
            }
         } else {
            builder.append(txnMin).append('-').append(txnMax);
         }
      }

   }

   public void readFromString(String src) {
      if (StringUtils.isEmpty(src)) {
         this.highWatermark = Long.MAX_VALUE;
         this.exceptions = new long[0];
         this.abortedBits = new BitSet();
      } else {
         String[] values = src.split(":");
         this.highWatermark = Long.parseLong(values[0]);
         this.minOpenTxn = Long.parseLong(values[1]);
         List<Long> openTxns = new ArrayList();
         List<Long> abortedTxns = new ArrayList();
         if (values.length == 3) {
            if (!values[2].isEmpty()) {
               openTxns = this.readTxnListFromRangeString(values[2]);
            }
         } else if (values.length > 3) {
            if (!values[2].isEmpty()) {
               openTxns = this.readTxnListFromRangeString(values[2]);
            }

            if (!values[3].isEmpty()) {
               abortedTxns = this.readTxnListFromRangeString(values[3]);
            }
         }

         this.exceptions = new long[openTxns.size() + abortedTxns.size()];
         this.abortedBits = new BitSet(this.exceptions.length);
         int exceptionIndex = 0;
         int openIndex = 0;
         int abortIndex = 0;

         while(openIndex < openTxns.size() || abortIndex < abortedTxns.size()) {
            if (abortIndex != abortedTxns.size() && (openIndex >= openTxns.size() || (Long)openTxns.get(openIndex) >= (Long)abortedTxns.get(abortIndex))) {
               this.abortedBits.set(exceptionIndex);
               this.exceptions[exceptionIndex++] = (Long)abortedTxns.get(abortIndex++);
            } else {
               this.exceptions[exceptionIndex++] = (Long)openTxns.get(openIndex++);
            }
         }

      }
   }

   private List readTxnListFromRangeString(String txnListString) {
      List<Long> txnList = new ArrayList();

      for(String txnRange : txnListString.split(",")) {
         if (txnRange.indexOf(45) < 0) {
            txnList.add(Long.parseLong(txnRange));
         } else {
            String[] parts = txnRange.split("-");
            long txn = Long.parseLong(parts[0]);
            long txnEnd = Long.parseLong(parts[1]);

            while(txn <= txnEnd) {
               txnList.add(txn++);
            }
         }
      }

      return txnList;
   }

   public long getHighWatermark() {
      return this.highWatermark;
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "Expose internal rep for efficiency"
   )
   public long[] getInvalidTransactions() {
      return this.exceptions;
   }

   public Long getMinOpenTxn() {
      return this.minOpenTxn == Long.MAX_VALUE ? null : this.minOpenTxn;
   }

   public boolean isTxnAborted(long txnid) {
      int index = Arrays.binarySearch(this.exceptions, txnid);
      return index >= 0 && this.abortedBits.get(index);
   }

   public ValidTxnList.RangeResponse isTxnRangeAborted(long minTxnId, long maxTxnId) {
      if (this.highWatermark < minTxnId) {
         return ValidTxnList.RangeResponse.NONE;
      } else {
         int count = 0;

         for(int i = this.abortedBits.nextSetBit(0); i >= 0; i = this.abortedBits.nextSetBit(i + 1)) {
            long abortedTxnId = this.exceptions[i];
            if (abortedTxnId > maxTxnId) {
               break;
            }

            if (abortedTxnId >= minTxnId && abortedTxnId <= maxTxnId) {
               ++count;
            }
         }

         if (count == 0) {
            return ValidTxnList.RangeResponse.NONE;
         } else {
            return (long)count == maxTxnId - minTxnId + 1L ? ValidTxnList.RangeResponse.ALL : ValidTxnList.RangeResponse.SOME;
         }
      }
   }
}
