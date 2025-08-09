package org.apache.parquet.column.values.bytestreamsplit;

public class ByteStreamSplitValuesReaderForInteger extends ByteStreamSplitValuesReader {
   public ByteStreamSplitValuesReaderForInteger() {
      super(4);
   }

   public int readInteger() {
      return this.decodedDataBuffer.getInt(this.nextElementByteOffset());
   }
}
