package org.apache.parquet.io.api;

import org.apache.parquet.io.ParquetDecodingException;

public abstract class RecordMaterializer {
   public abstract Object getCurrentRecord();

   public void skipCurrentRecord() {
   }

   public abstract GroupConverter getRootConverter();

   public static class RecordMaterializationException extends ParquetDecodingException {
      public RecordMaterializationException() {
      }

      public RecordMaterializationException(String message, Throwable cause) {
         super(message, cause);
      }

      public RecordMaterializationException(String message) {
         super(message);
      }

      public RecordMaterializationException(Throwable cause) {
         super(cause);
      }
   }
}
