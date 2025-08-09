package org.apache.parquet.column.values.plain;

import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.bitpacking.ByteBitPackingValuesWriter;
import org.apache.parquet.column.values.bitpacking.Packer;

public class BooleanPlainValuesWriter extends ValuesWriter {
   private ByteBitPackingValuesWriter bitPackingWriter;

   public BooleanPlainValuesWriter() {
      this.bitPackingWriter = new ByteBitPackingValuesWriter(1, Packer.LITTLE_ENDIAN);
   }

   public final void writeBoolean(boolean v) {
      this.bitPackingWriter.writeInteger(v ? 1 : 0);
   }

   public long getBufferedSize() {
      return this.bitPackingWriter.getBufferedSize();
   }

   public BytesInput getBytes() {
      return this.bitPackingWriter.getBytes();
   }

   public void reset() {
      this.bitPackingWriter.reset();
   }

   public void close() {
      this.bitPackingWriter.close();
   }

   public long getAllocatedSize() {
      return this.bitPackingWriter.getAllocatedSize();
   }

   public Encoding getEncoding() {
      return Encoding.PLAIN;
   }

   public String memUsageString(String prefix) {
      return this.bitPackingWriter.memUsageString(prefix);
   }
}
