package org.apache.parquet.column.values.bytestreamsplit;

public class ByteStreamSplitValuesReaderForFloat extends ByteStreamSplitValuesReader {
   public ByteStreamSplitValuesReaderForFloat() {
      super(4);
   }

   public float readFloat() {
      return this.decodedDataBuffer.getFloat(this.nextElementByteOffset());
   }
}
