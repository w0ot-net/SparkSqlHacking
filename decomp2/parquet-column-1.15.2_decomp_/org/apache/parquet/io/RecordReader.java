package org.apache.parquet.io;

public abstract class RecordReader {
   public abstract Object read();

   public boolean shouldSkipCurrentRecord() {
      return false;
   }
}
