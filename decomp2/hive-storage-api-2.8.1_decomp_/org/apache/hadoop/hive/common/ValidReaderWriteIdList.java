package org.apache.hadoop.hive.common;

import java.util.Arrays;
import java.util.BitSet;
import org.apache.hive.common.util.SuppressFBWarnings;

public class ValidReaderWriteIdList implements ValidWriteIdList {
   private String tableName;
   protected long[] exceptions;
   protected BitSet abortedBits;
   private long minOpenWriteId;
   protected long highWatermark;

   public ValidReaderWriteIdList() {
      this((String)null, new long[0], new BitSet(), Long.MAX_VALUE, Long.MAX_VALUE);
   }

   public ValidReaderWriteIdList(String tableName, long[] exceptions, BitSet abortedBits, long highWatermark) {
      this(tableName, exceptions, abortedBits, highWatermark, Long.MAX_VALUE);
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "Ref external obj for efficiency"
   )
   public ValidReaderWriteIdList(String tableName, long[] exceptions, BitSet abortedBits, long highWatermark, long minOpenWriteId) {
      this.minOpenWriteId = Long.MAX_VALUE;
      this.tableName = tableName;
      if (exceptions.length > 0) {
         this.minOpenWriteId = minOpenWriteId;
      }

      this.exceptions = exceptions;
      this.abortedBits = abortedBits;
      this.highWatermark = highWatermark;
   }

   public ValidReaderWriteIdList(String value) {
      this.minOpenWriteId = Long.MAX_VALUE;
      this.readFromString(value);
   }

   public boolean isWriteIdValid(long writeId) {
      if (writeId > this.highWatermark) {
         return false;
      } else {
         return Arrays.binarySearch(this.exceptions, writeId) < 0;
      }
   }

   public boolean isValidBase(long writeId) {
      return writeId < this.minOpenWriteId && writeId <= this.highWatermark;
   }

   public ValidWriteIdList.RangeResponse isWriteIdRangeValid(long minWriteId, long maxWriteId) {
      if (minWriteId > this.highWatermark) {
         return ValidWriteIdList.RangeResponse.NONE;
      } else if (this.exceptions.length > 0 && this.exceptions[0] > maxWriteId) {
         return ValidWriteIdList.RangeResponse.ALL;
      } else {
         long count = Math.max(0L, maxWriteId - this.highWatermark);

         for(long txn : this.exceptions) {
            if (minWriteId <= txn && txn <= maxWriteId) {
               ++count;
            }
         }

         if (count == 0L) {
            return ValidWriteIdList.RangeResponse.ALL;
         } else if (count == maxWriteId - minWriteId + 1L) {
            return ValidWriteIdList.RangeResponse.NONE;
         } else {
            return ValidWriteIdList.RangeResponse.SOME;
         }
      }
   }

   public String toString() {
      return this.writeToString();
   }

   public String writeToString() {
      StringBuilder buf = new StringBuilder();
      if (this.tableName == null) {
         buf.append("null");
      } else {
         buf.append(this.tableName);
      }

      buf.append(':');
      buf.append(this.highWatermark);
      buf.append(':');
      buf.append(this.minOpenWriteId);
      if (this.exceptions.length == 0) {
         buf.append(':');
         buf.append(':');
      } else {
         StringBuilder open = new StringBuilder();
         StringBuilder abort = new StringBuilder();

         for(int i = 0; i < this.exceptions.length; ++i) {
            if (this.abortedBits.get(i)) {
               if (abort.length() > 0) {
                  abort.append(',');
               }

               abort.append(this.exceptions[i]);
            } else {
               if (open.length() > 0) {
                  open.append(',');
               }

               open.append(this.exceptions[i]);
            }
         }

         buf.append(':');
         buf.append(open);
         buf.append(':');
         buf.append(abort);
      }

      return buf.toString();
   }

   public void readFromString(String src) {
      if (src != null && src.length() != 0) {
         String[] values = src.split(":");
         this.tableName = values[0];
         if (this.tableName.equalsIgnoreCase("null")) {
            this.tableName = null;
         }

         this.highWatermark = Long.parseLong(values[1]);
         this.minOpenWriteId = Long.parseLong(values[2]);
         String[] openWriteIds = new String[0];
         String[] abortedWriteIds = new String[0];
         if (values.length < 4) {
            openWriteIds = new String[0];
            abortedWriteIds = new String[0];
         } else if (values.length == 4) {
            if (!values[3].isEmpty()) {
               openWriteIds = values[3].split(",");
            }
         } else {
            if (!values[3].isEmpty()) {
               openWriteIds = values[3].split(",");
            }

            if (!values[4].isEmpty()) {
               abortedWriteIds = values[4].split(",");
            }
         }

         this.exceptions = new long[openWriteIds.length + abortedWriteIds.length];
         int i = 0;

         for(String open : openWriteIds) {
            this.exceptions[i++] = Long.parseLong(open);
         }

         for(String abort : abortedWriteIds) {
            this.exceptions[i++] = Long.parseLong(abort);
         }

         Arrays.sort(this.exceptions);
         this.abortedBits = new BitSet(this.exceptions.length);

         for(String abort : abortedWriteIds) {
            int index = Arrays.binarySearch(this.exceptions, Long.parseLong(abort));
            this.abortedBits.set(index);
         }
      } else {
         this.highWatermark = Long.MAX_VALUE;
         this.exceptions = new long[0];
         this.abortedBits = new BitSet();
      }

   }

   public String getTableName() {
      return this.tableName;
   }

   public long getHighWatermark() {
      return this.highWatermark;
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "Expose internal rep for efficiency"
   )
   public long[] getInvalidWriteIds() {
      return this.exceptions;
   }

   public Long getMinOpenWriteId() {
      return this.minOpenWriteId == Long.MAX_VALUE ? null : this.minOpenWriteId;
   }

   public boolean isWriteIdAborted(long writeId) {
      int index = Arrays.binarySearch(this.exceptions, writeId);
      return index >= 0 && this.abortedBits.get(index);
   }

   public ValidWriteIdList.RangeResponse isWriteIdRangeAborted(long minWriteId, long maxWriteId) {
      if (this.highWatermark < minWriteId) {
         return ValidWriteIdList.RangeResponse.NONE;
      } else {
         int count = 0;

         for(int i = this.abortedBits.nextSetBit(0); i >= 0; i = this.abortedBits.nextSetBit(i + 1)) {
            long abortedTxnId = this.exceptions[i];
            if (abortedTxnId > maxWriteId) {
               break;
            }

            if (abortedTxnId >= minWriteId && abortedTxnId <= maxWriteId) {
               ++count;
            }
         }

         if (count == 0) {
            return ValidWriteIdList.RangeResponse.NONE;
         } else {
            return (long)count == maxWriteId - minWriteId + 1L ? ValidWriteIdList.RangeResponse.ALL : ValidWriteIdList.RangeResponse.SOME;
         }
      }
   }

   public ValidReaderWriteIdList updateHighWatermark(long value) {
      return new ValidReaderWriteIdList(this.tableName, this.exceptions, this.abortedBits, value, this.minOpenWriteId);
   }
}
