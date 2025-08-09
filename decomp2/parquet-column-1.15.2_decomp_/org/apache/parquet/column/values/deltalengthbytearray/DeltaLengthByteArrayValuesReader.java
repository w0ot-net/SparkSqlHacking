package org.apache.parquet.column.values.deltalengthbytearray;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.column.values.delta.DeltaBinaryPackingValuesReader;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.api.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeltaLengthByteArrayValuesReader extends ValuesReader {
   private static final Logger LOG = LoggerFactory.getLogger(DeltaLengthByteArrayValuesReader.class);
   private ValuesReader lengthReader = new DeltaBinaryPackingValuesReader();
   private ByteBufferInputStream in;

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      LOG.debug("init from page at offset {} for length {}", stream.position(), stream.available());
      this.lengthReader.initFromPage(valueCount, stream);
      this.in = stream.remainingStream();
   }

   public Binary readBytes() {
      int length = this.lengthReader.readInteger();

      try {
         return Binary.fromConstantByteBuffer(this.in.slice(length));
      } catch (IOException var3) {
         throw new ParquetDecodingException("Failed to read " + length + " bytes");
      }
   }

   public void skip() {
      this.skip(1);
   }

   public void skip(int n) {
      int length = 0;

      for(int i = 0; i < n; ++i) {
         length += this.lengthReader.readInteger();
      }

      try {
         this.in.skipFully((long)length);
      } catch (IOException var4) {
         throw new ParquetDecodingException("Failed to skip " + length + " bytes");
      }
   }
}
