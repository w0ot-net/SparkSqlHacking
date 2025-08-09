package org.apache.parquet.column.values.rle;

import java.io.IOException;
import java.util.Objects;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.io.ParquetEncodingException;

public class RunLengthBitPackingHybridValuesWriter extends ValuesWriter {
   protected final RunLengthBitPackingHybridEncoder encoder;

   public RunLengthBitPackingHybridValuesWriter(int bitWidth, int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
      this(new RunLengthBitPackingHybridEncoder(bitWidth, initialCapacity, pageSize, allocator));
   }

   protected RunLengthBitPackingHybridValuesWriter(RunLengthBitPackingHybridEncoder encoder) {
      this.encoder = (RunLengthBitPackingHybridEncoder)Objects.requireNonNull(encoder);
   }

   public void writeInteger(int v) {
      try {
         this.encoder.writeInt(v);
      } catch (IOException e) {
         throw new ParquetEncodingException(e);
      }
   }

   public void writeBoolean(boolean v) {
      this.writeInteger(v ? 1 : 0);
   }

   public long getBufferedSize() {
      return this.encoder.getBufferedSize();
   }

   public long getAllocatedSize() {
      return this.encoder.getAllocatedSize();
   }

   public BytesInput getBytes() {
      try {
         BytesInput rle = this.encoder.toBytes();
         return BytesInput.concat(new BytesInput[]{BytesInput.fromInt(Math.toIntExact(rle.size())), rle});
      } catch (IOException e) {
         throw new ParquetEncodingException(e);
      }
   }

   public Encoding getEncoding() {
      return Encoding.RLE;
   }

   public void reset() {
      this.encoder.reset();
   }

   public void close() {
      this.encoder.close();
   }

   public String memUsageString(String prefix) {
      return String.format("%s RunLengthBitPackingHybrid %d bytes", prefix, this.getAllocatedSize());
   }
}
