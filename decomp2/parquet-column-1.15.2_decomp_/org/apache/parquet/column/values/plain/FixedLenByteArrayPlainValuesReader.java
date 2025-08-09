package org.apache.parquet.column.values.plain;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.api.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FixedLenByteArrayPlainValuesReader extends ValuesReader {
   private static final Logger LOG = LoggerFactory.getLogger(FixedLenByteArrayPlainValuesReader.class);
   private final int length;
   private ByteBufferInputStream in;

   public FixedLenByteArrayPlainValuesReader(int length) {
      this.length = length;
   }

   public Binary readBytes() {
      try {
         return Binary.fromConstantByteBuffer(this.in.slice(this.length));
      } catch (RuntimeException | IOException e) {
         throw new ParquetDecodingException("could not read bytes at offset " + this.in.position(), e);
      }
   }

   public void skip() {
      this.skip(1);
   }

   public void skip(int n) {
      try {
         this.in.skipFully((long)(n * this.length));
      } catch (RuntimeException | IOException e) {
         throw new ParquetDecodingException("could not skip bytes at offset " + this.in.position(), e);
      }
   }

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      LOG.debug("init from page at offset {} for length {}", stream.position(), stream.available());
      this.in = stream.remainingStream();
   }
}
