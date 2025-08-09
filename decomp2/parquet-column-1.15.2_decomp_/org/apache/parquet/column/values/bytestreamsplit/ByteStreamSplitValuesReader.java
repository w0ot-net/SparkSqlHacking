package org.apache.parquet.column.values.bytestreamsplit;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.io.ParquetDecodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ByteStreamSplitValuesReader extends ValuesReader {
   private static final Logger LOG = LoggerFactory.getLogger(ByteStreamSplitValuesReader.class);
   protected final int elementSizeInBytes;
   protected ByteBuffer decodedDataBuffer;
   private int indexInStream;
   private int valuesCount;

   protected ByteStreamSplitValuesReader(int elementSizeInBytes) {
      this.elementSizeInBytes = elementSizeInBytes;
      this.indexInStream = 0;
      this.valuesCount = 0;
   }

   protected int nextElementByteOffset() {
      if (this.indexInStream >= this.valuesCount) {
         throw new ParquetDecodingException("Byte-stream data was already exhausted.");
      } else {
         int offset = this.indexInStream * this.elementSizeInBytes;
         ++this.indexInStream;
         return offset;
      }
   }

   private byte[] decodeData(ByteBuffer encoded, int valuesCount) {
      assert encoded.limit() == valuesCount * this.elementSizeInBytes;

      byte[] decoded = new byte[encoded.limit()];
      int destByteIndex = 0;

      for(int srcValueIndex = 0; srcValueIndex < valuesCount; ++srcValueIndex) {
         for(int stream = 0; stream < this.elementSizeInBytes; ++destByteIndex) {
            decoded[destByteIndex] = encoded.get(srcValueIndex + stream * valuesCount);
            ++stream;
         }
      }

      assert destByteIndex == decoded.length;

      return decoded;
   }

   public void initFromPage(int valuesCount, ByteBufferInputStream stream) throws ParquetDecodingException, IOException {
      LOG.debug("init from page at offset {} for length {}", stream.position(), stream.available());
      if (stream.available() % this.elementSizeInBytes != 0) {
         String errorMessage = String.format("Invalid ByteStreamSplit stream, total length: %d bytes, element size: %d bytes.", stream.available(), this.elementSizeInBytes);
         throw new ParquetDecodingException(errorMessage);
      } else {
         this.valuesCount = stream.available() / this.elementSizeInBytes;
         if (valuesCount < this.valuesCount) {
            String errorMessage = String.format("Invalid ByteStreamSplit stream, num values upper bound (w/ nulls): %d, num encoded values: %d", valuesCount, this.valuesCount);
            throw new ParquetDecodingException(errorMessage);
         } else {
            int totalSizeInBytes = stream.available();
            ByteBuffer encodedData = stream.slice(totalSizeInBytes).slice();
            byte[] decodedData = this.decodeData(encodedData, this.valuesCount);
            this.decodedDataBuffer = ByteBuffer.wrap(decodedData).order(ByteOrder.LITTLE_ENDIAN);
            this.indexInStream = 0;
         }
      }
   }

   public void skip() {
      this.skip(1);
   }

   public void skip(int n) {
      if (n >= 0 && this.indexInStream + n <= this.valuesCount) {
         this.indexInStream += n;
      } else {
         String errorMessage = String.format("Cannot skip this many elements. Current index: %d. Skip %d. Total number of elements: %d", this.indexInStream, n, this.valuesCount);
         throw new ParquetDecodingException(errorMessage);
      }
   }
}
