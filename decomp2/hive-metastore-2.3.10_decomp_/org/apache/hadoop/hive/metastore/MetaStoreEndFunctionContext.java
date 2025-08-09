package org.apache.hadoop.hive.metastore;

public class MetaStoreEndFunctionContext {
   private final boolean success;
   private final Exception e;
   private final String inputTableName;

   public MetaStoreEndFunctionContext(boolean success, Exception e, String inputTableName) {
      this.success = success;
      this.e = e;
      this.inputTableName = inputTableName;
   }

   public MetaStoreEndFunctionContext(boolean success) {
      this(success, (Exception)null, (String)null);
   }

   public boolean isSuccess() {
      return this.success;
   }

   public Exception getException() {
      return this.e;
   }

   public String getInputTableName() {
      return this.inputTableName;
   }
}
