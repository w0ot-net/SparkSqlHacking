package org.apache.parquet.column.values.deltastrings;

import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.delta.DeltaBinaryPackingValuesWriterForInteger;
import org.apache.parquet.column.values.deltalengthbytearray.DeltaLengthByteArrayValuesWriter;
import org.apache.parquet.io.api.Binary;

public class DeltaByteArrayWriter extends ValuesWriter {
   private ValuesWriter prefixLengthWriter;
   private ValuesWriter suffixWriter;
   private byte[] previous;

   public DeltaByteArrayWriter(int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
      this.prefixLengthWriter = new DeltaBinaryPackingValuesWriterForInteger(128, 4, initialCapacity, pageSize, allocator);
      this.suffixWriter = new DeltaLengthByteArrayValuesWriter(initialCapacity, pageSize, allocator);
      this.previous = new byte[0];
   }

   public long getBufferedSize() {
      return this.prefixLengthWriter.getBufferedSize() + this.suffixWriter.getBufferedSize();
   }

   public BytesInput getBytes() {
      return BytesInput.concat(new BytesInput[]{this.prefixLengthWriter.getBytes(), this.suffixWriter.getBytes()});
   }

   public Encoding getEncoding() {
      return Encoding.DELTA_BYTE_ARRAY;
   }

   public void reset() {
      this.prefixLengthWriter.reset();
      this.suffixWriter.reset();
      this.previous = new byte[0];
   }

   public void close() {
      this.prefixLengthWriter.close();
      this.suffixWriter.close();
   }

   public long getAllocatedSize() {
      return this.prefixLengthWriter.getAllocatedSize() + this.suffixWriter.getAllocatedSize();
   }

   public String memUsageString(String prefix) {
      prefix = this.prefixLengthWriter.memUsageString(prefix);
      return this.suffixWriter.memUsageString(prefix + "  DELTA_STRINGS");
   }

   public void writeBytes(Binary v) {
      int i = 0;
      byte[] vb = v.getBytes();
      int length = this.previous.length < vb.length ? this.previous.length : vb.length;

      for(i = 0; i < length && this.previous[i] == vb[i]; ++i) {
      }

      this.prefixLengthWriter.writeInteger(i);
      this.suffixWriter.writeBytes(v.slice(i, vb.length - i));
      this.previous = vb;
   }
}
