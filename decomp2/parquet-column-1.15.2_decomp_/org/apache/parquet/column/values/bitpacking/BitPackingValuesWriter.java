package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.bytes.CapacityByteArrayOutputStream;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.io.ParquetEncodingException;

public class BitPackingValuesWriter extends ValuesWriter {
   private CapacityByteArrayOutputStream out;
   private BitPacking.BitPackingWriter bitPackingWriter;
   private int bitsPerValue;

   public BitPackingValuesWriter(int bound, int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
      this.bitsPerValue = BytesUtils.getWidthFromMaxInt(bound);
      this.out = new CapacityByteArrayOutputStream(initialCapacity, pageSize, allocator);
      this.init();
   }

   private void init() {
      this.bitPackingWriter = BitPacking.getBitPackingWriter(this.bitsPerValue, this.out);
   }

   public void writeInteger(int v) {
      try {
         this.bitPackingWriter.write(v);
      } catch (IOException e) {
         throw new ParquetEncodingException(e);
      }
   }

   public long getBufferedSize() {
      return this.out.size();
   }

   public BytesInput getBytes() {
      try {
         this.bitPackingWriter.finish();
         return BytesInput.from(this.out);
      } catch (IOException e) {
         throw new ParquetEncodingException(e);
      }
   }

   public void reset() {
      this.out.reset();
      this.init();
   }

   public void close() {
      this.out.close();
   }

   public long getAllocatedSize() {
      return (long)this.out.getCapacity();
   }

   public String memUsageString(String prefix) {
      return this.out.memUsageString(prefix);
   }

   public Encoding getEncoding() {
      return Encoding.BIT_PACKED;
   }
}
