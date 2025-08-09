package org.apache.parquet.column.values.bytestreamsplit;

import org.apache.parquet.io.api.Binary;

public class ByteStreamSplitValuesReaderForFLBA extends ByteStreamSplitValuesReader {
   public ByteStreamSplitValuesReaderForFLBA(int length) {
      super(length);
   }

   public Binary readBytes() {
      return Binary.fromConstantByteBuffer(this.decodedDataBuffer, this.nextElementByteOffset(), this.elementSizeInBytes);
   }
}
