package org.apache.parquet.column.values.bytestreamsplit;

public class ByteStreamSplitValuesReaderForLong extends ByteStreamSplitValuesReader {
   public ByteStreamSplitValuesReaderForLong() {
      super(8);
   }

   public long readLong() {
      return this.decodedDataBuffer.getLong(this.nextElementByteOffset());
   }
}
