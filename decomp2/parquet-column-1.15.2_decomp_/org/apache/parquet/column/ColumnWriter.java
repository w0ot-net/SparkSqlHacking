package org.apache.parquet.column;

import org.apache.parquet.io.api.Binary;

public interface ColumnWriter extends AutoCloseable {
   void write(int var1, int var2, int var3);

   void write(long var1, int var3, int var4);

   void write(boolean var1, int var2, int var3);

   void write(Binary var1, int var2, int var3);

   void write(float var1, int var2, int var3);

   void write(double var1, int var3, int var4);

   void writeNull(int var1, int var2);

   void close();

   long getBufferedSizeInMemory();
}
