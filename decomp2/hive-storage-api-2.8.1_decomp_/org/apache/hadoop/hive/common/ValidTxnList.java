package org.apache.hadoop.hive.common;

public interface ValidTxnList {
   String VALID_TXNS_KEY = "hive.txn.valid.txns";

   void removeException(long var1);

   boolean isTxnValid(long var1);

   RangeResponse isTxnRangeValid(long var1, long var3);

   String writeToString();

   void readFromString(String var1);

   long getHighWatermark();

   long[] getInvalidTransactions();

   boolean isTxnAborted(long var1);

   RangeResponse isTxnRangeAborted(long var1, long var3);

   Long getMinOpenTxn();

   public static enum RangeResponse {
      NONE,
      SOME,
      ALL;
   }
}
