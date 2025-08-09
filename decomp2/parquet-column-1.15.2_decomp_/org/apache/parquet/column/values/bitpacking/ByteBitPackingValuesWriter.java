package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.io.ParquetEncodingException;

public class ByteBitPackingValuesWriter extends ValuesWriter {
   private final Packer packer;
   private final int bitWidth;
   private ByteBasedBitPackingEncoder encoder;

   public ByteBitPackingValuesWriter(int bound, Packer packer) {
      this.packer = packer;
      this.bitWidth = BytesUtils.getWidthFromMaxInt(bound);
      this.encoder = new ByteBasedBitPackingEncoder(this.bitWidth, packer);
   }

   public void writeInteger(int v) {
      try {
         this.encoder.writeInt(v);
      } catch (IOException e) {
         throw new ParquetEncodingException(e);
      }
   }

   public Encoding getEncoding() {
      return Encoding.BIT_PACKED;
   }

   public BytesInput getBytes() {
      try {
         return this.encoder.toBytes();
      } catch (IOException e) {
         throw new ParquetEncodingException(e);
      }
   }

   public void reset() {
      this.encoder = new ByteBasedBitPackingEncoder(this.bitWidth, this.packer);
   }

   public long getBufferedSize() {
      return this.encoder.getBufferSize();
   }

   public long getAllocatedSize() {
      return this.encoder.getAllocatedSize();
   }

   public String memUsageString(String prefix) {
      return this.encoder.memUsageString(prefix);
   }
}
