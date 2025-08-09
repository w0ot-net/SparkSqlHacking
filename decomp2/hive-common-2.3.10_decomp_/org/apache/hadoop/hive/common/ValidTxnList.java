package org.apache.hadoop.hive.common;

public interface ValidTxnList {
   String VALID_TXNS_KEY = "hive.txn.valid.txns";

   boolean isTxnValid(long var1);

   boolean isValidBase(long var1);

   RangeResponse isTxnRangeValid(long var1, long var3);

   String writeToString();

   void readFromString(String var1);

   long getHighWatermark();

   long[] getInvalidTransactions();

   public static enum RangeResponse {
      NONE,
      SOME,
      ALL;
   }
}
