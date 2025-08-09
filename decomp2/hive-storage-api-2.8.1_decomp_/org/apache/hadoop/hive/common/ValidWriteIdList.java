package org.apache.hadoop.hive.common;

public interface ValidWriteIdList {
   String VALID_WRITEIDS_KEY = "hive.txn.valid.writeids";

   boolean isWriteIdValid(long var1);

   boolean isValidBase(long var1);

   RangeResponse isWriteIdRangeValid(long var1, long var3);

   String writeToString();

   void readFromString(String var1);

   String getTableName();

   long getHighWatermark();

   long[] getInvalidWriteIds();

   boolean isWriteIdAborted(long var1);

   RangeResponse isWriteIdRangeAborted(long var1, long var3);

   Long getMinOpenWriteId();

   public static enum RangeResponse {
      NONE,
      SOME,
      ALL;
   }
}
