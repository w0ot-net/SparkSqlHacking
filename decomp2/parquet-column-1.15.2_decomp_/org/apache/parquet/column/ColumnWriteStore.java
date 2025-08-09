package org.apache.parquet.column;

public interface ColumnWriteStore extends AutoCloseable {
   ColumnWriter getColumnWriter(ColumnDescriptor var1);

   void flush();

   void endRecord();

   long getAllocatedSize();

   long getBufferedSize();

   String memUsageString();

   void close();

   default boolean isColumnFlushNeeded() {
      return false;
   }
}
