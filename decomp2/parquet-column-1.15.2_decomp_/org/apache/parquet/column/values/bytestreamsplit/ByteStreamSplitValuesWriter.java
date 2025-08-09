package org.apache.parquet.column.values.bytestreamsplit;

import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.bytes.CapacityByteArrayOutputStream;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.io.api.Binary;

public abstract class ByteStreamSplitValuesWriter extends ValuesWriter {
   protected final int numStreams;
   protected final int elementSizeInBytes;
   private final CapacityByteArrayOutputStream[] byteStreams;

   public ByteStreamSplitValuesWriter(int elementSizeInBytes, int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
      if (elementSizeInBytes <= 0) {
         throw new ParquetEncodingException(String.format("Element byte size is invalid: %d", elementSizeInBytes));
      } else {
         this.numStreams = elementSizeInBytes;
         this.elementSizeInBytes = elementSizeInBytes;
         this.byteStreams = new CapacityByteArrayOutputStream[elementSizeInBytes];
         int capacityPerStream = (pageSize + this.numStreams - 1) / this.numStreams;
         int initialCapacityPerStream = (initialCapacity + this.numStreams - 1) / this.numStreams;

         for(int i = 0; i < this.numStreams; ++i) {
            this.byteStreams[i] = new CapacityByteArrayOutputStream(initialCapacityPerStream, capacityPerStream, allocator);
         }

      }
   }

   public long getBufferedSize() {
      long totalSize = 0L;

      for(CapacityByteArrayOutputStream stream : this.byteStreams) {
         totalSize += stream.size();
      }

      return totalSize;
   }

   public BytesInput getBytes() {
      BytesInput[] allInputs = new BytesInput[this.numStreams];

      for(int i = 0; i < this.numStreams; ++i) {
         allInputs[i] = BytesInput.from(this.byteStreams[i]);
      }

      return BytesInput.concat(allInputs);
   }

   public Encoding getEncoding() {
      return Encoding.BYTE_STREAM_SPLIT;
   }

   public void reset() {
      for(CapacityByteArrayOutputStream stream : this.byteStreams) {
         stream.reset();
      }

   }

   public void close() {
      for(CapacityByteArrayOutputStream stream : this.byteStreams) {
         stream.close();
      }

   }

   protected void scatterBytes(byte[] bytes) {
      if (bytes.length != this.numStreams) {
         throw new ParquetEncodingException(String.format("Number of bytes doesn't match the number of streams. Num butes: %d, Num streams: %d", bytes.length, this.numStreams));
      } else {
         for(int i = 0; i < bytes.length; ++i) {
            this.byteStreams[i].write(bytes[i]);
         }

      }
   }

   public long getAllocatedSize() {
      long totalCapacity = 0L;

      for(CapacityByteArrayOutputStream stream : this.byteStreams) {
         totalCapacity += (long)stream.getCapacity();
      }

      return totalCapacity;
   }

   public static class FloatByteStreamSplitValuesWriter extends ByteStreamSplitValuesWriter {
      public FloatByteStreamSplitValuesWriter(int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
         super(4, initialCapacity, pageSize, allocator);
      }

      public void writeFloat(float v) {
         super.scatterBytes(BytesUtils.intToBytes(Float.floatToIntBits(v)));
      }

      public String memUsageString(String prefix) {
         return String.format("%s FloatByteStreamSplitWriter %d bytes", prefix, this.getAllocatedSize());
      }
   }

   public static class DoubleByteStreamSplitValuesWriter extends ByteStreamSplitValuesWriter {
      public DoubleByteStreamSplitValuesWriter(int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
         super(8, initialCapacity, pageSize, allocator);
      }

      public void writeDouble(double v) {
         super.scatterBytes(BytesUtils.longToBytes(Double.doubleToLongBits(v)));
      }

      public String memUsageString(String prefix) {
         return String.format("%s DoubleByteStreamSplitWriter %d bytes", prefix, this.getAllocatedSize());
      }
   }

   public static class IntegerByteStreamSplitValuesWriter extends ByteStreamSplitValuesWriter {
      public IntegerByteStreamSplitValuesWriter(int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
         super(4, initialCapacity, pageSize, allocator);
      }

      public void writeInteger(int v) {
         super.scatterBytes(BytesUtils.intToBytes(v));
      }

      public String memUsageString(String prefix) {
         return String.format("%s IntegerByteStreamSplitWriter %d bytes", prefix, this.getAllocatedSize());
      }
   }

   public static class LongByteStreamSplitValuesWriter extends ByteStreamSplitValuesWriter {
      public LongByteStreamSplitValuesWriter(int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
         super(8, initialCapacity, pageSize, allocator);
      }

      public void writeLong(long v) {
         super.scatterBytes(BytesUtils.longToBytes(v));
      }

      public String memUsageString(String prefix) {
         return String.format("%s LongByteStreamSplitWriter %d bytes", prefix, this.getAllocatedSize());
      }
   }

   public static class FixedLenByteArrayByteStreamSplitValuesWriter extends ByteStreamSplitValuesWriter {
      private final int length;

      public FixedLenByteArrayByteStreamSplitValuesWriter(int length, int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
         super(length, initialCapacity, pageSize, allocator);
         this.length = length;
      }

      public final void writeBytes(Binary v) {
         assert v.length() == this.length : "Fixed Binary size " + v.length() + " does not match field type length " + this.length;

         super.scatterBytes(v.getBytesUnsafe());
      }

      public String memUsageString(String prefix) {
         return String.format("%s FixedLenByteArrayByteStreamSplitValuesWriter %d bytes", prefix, this.getAllocatedSize());
      }
   }
}
