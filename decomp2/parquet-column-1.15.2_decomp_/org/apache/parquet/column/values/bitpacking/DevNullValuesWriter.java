package org.apache.parquet.column.values.bitpacking;

import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.io.api.Binary;

public class DevNullValuesWriter extends ValuesWriter {
   public long getBufferedSize() {
      return 0L;
   }

   public void reset() {
   }

   public void writeInteger(int v) {
   }

   public void writeByte(int value) {
   }

   public void writeBoolean(boolean v) {
   }

   public void writeBytes(Binary v) {
   }

   public void writeLong(long v) {
   }

   public void writeDouble(double v) {
   }

   public void writeFloat(float v) {
   }

   public BytesInput getBytes() {
      return BytesInput.empty();
   }

   public long getAllocatedSize() {
      return 0L;
   }

   public Encoding getEncoding() {
      return Encoding.BIT_PACKED;
   }

   public String memUsageString(String prefix) {
      return prefix + "0";
   }
}
