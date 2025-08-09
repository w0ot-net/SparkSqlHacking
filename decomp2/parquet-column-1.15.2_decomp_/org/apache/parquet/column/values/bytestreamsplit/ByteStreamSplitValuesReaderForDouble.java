package org.apache.parquet.column.values.bytestreamsplit;

public class ByteStreamSplitValuesReaderForDouble extends ByteStreamSplitValuesReader {
   public ByteStreamSplitValuesReaderForDouble() {
      super(8);
   }

   public double readDouble() {
      return this.decodedDataBuffer.getDouble(this.nextElementByteOffset());
   }
}
