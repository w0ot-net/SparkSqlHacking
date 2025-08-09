package org.apache.parquet.column.values;

import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.io.api.Binary;

public abstract class ValuesWriter implements AutoCloseable {
   public abstract long getBufferedSize();

   public abstract BytesInput getBytes();

   public abstract Encoding getEncoding();

   public abstract void reset();

   public void close() {
   }

   public DictionaryPage toDictPageAndClose() {
      return null;
   }

   public void resetDictionary() {
   }

   public abstract long getAllocatedSize();

   public void writeByte(int value) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void writeBoolean(boolean v) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void writeBytes(Binary v) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void writeInteger(int v) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void writeLong(long v) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void writeDouble(double v) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public void writeFloat(float v) {
      throw new UnsupportedOperationException(this.getClass().getName());
   }

   public abstract String memUsageString(String var1);
}
