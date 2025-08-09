package org.rocksdb;

import java.util.Map;

public interface WalFilter {
   void columnFamilyLogNumberMap(Map var1, Map var2);

   LogRecordFoundResult logRecordFound(long var1, String var3, WriteBatch var4, WriteBatch var5);

   String name();

   public static class LogRecordFoundResult {
      public static LogRecordFoundResult CONTINUE_UNCHANGED;
      final WalProcessingOption walProcessingOption;
      final boolean batchChanged;

      public LogRecordFoundResult(WalProcessingOption var1, boolean var2) {
         this.walProcessingOption = var1;
         this.batchChanged = var2;
      }

      static {
         CONTINUE_UNCHANGED = new LogRecordFoundResult(WalProcessingOption.CONTINUE_PROCESSING, false);
      }
   }
}
