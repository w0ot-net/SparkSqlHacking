package org.apache.hadoop.hive.common;

import java.util.HashMap;
import java.util.Map;

public class ValidTxnWriteIdList {
   public static final String VALID_TABLES_WRITEIDS_KEY = "hive.txn.tables.valid.writeids";
   private Long txnId;
   private Map tablesValidWriteIdList = new HashMap();

   public ValidTxnWriteIdList(Long txnId) {
      this.txnId = txnId;
   }

   public ValidTxnWriteIdList(String value) {
      this.readFromString(value);
   }

   public String toString() {
      return this.writeToString();
   }

   public void addTableValidWriteIdList(ValidWriteIdList validWriteIds) {
      this.tablesValidWriteIdList.put(validWriteIds.getTableName(), validWriteIds);
   }

   public ValidWriteIdList getTableValidWriteIdList(String fullTableName) {
      return this.tablesValidWriteIdList.containsKey(fullTableName) ? (ValidWriteIdList)this.tablesValidWriteIdList.get(fullTableName) : null;
   }

   public boolean isEmpty() {
      return this.tablesValidWriteIdList.isEmpty();
   }

   private void readFromString(String src) {
      if (src != null && src.length() != 0) {
         String[] tblWriteIdStrList = src.split("\\$");

         assert tblWriteIdStrList.length >= 1;

         this.txnId = Long.parseLong(tblWriteIdStrList[0]);

         for(int index = 1; index < tblWriteIdStrList.length; ++index) {
            String tableStr = tblWriteIdStrList[index];
            ValidWriteIdList validWriteIdList = new ValidReaderWriteIdList(tableStr);
            this.addTableValidWriteIdList(validWriteIdList);
         }

      }
   }

   private String writeToString() {
      StringBuilder buf = new StringBuilder(this.txnId.toString());
      int index = 0;

      for(Map.Entry entry : this.tablesValidWriteIdList.entrySet()) {
         if (index < this.tablesValidWriteIdList.size()) {
            buf.append('$');
         }

         buf.append(((ValidWriteIdList)entry.getValue()).writeToString());
         ++index;
      }

      return buf.toString();
   }
}
