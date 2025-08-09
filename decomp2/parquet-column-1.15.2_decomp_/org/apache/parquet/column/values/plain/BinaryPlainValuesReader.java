package org.apache.parquet.column.values.plain;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.api.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryPlainValuesReader extends ValuesReader {
   private static final Logger LOG = LoggerFactory.getLogger(BinaryPlainValuesReader.class);
   private ByteBufferInputStream in;

   public Binary readBytes() {
      try {
         int length = BytesUtils.readIntLittleEndian(this.in);
         return Binary.fromConstantByteBuffer(this.in.slice(length));
      } catch (RuntimeException | IOException e) {
         throw new ParquetDecodingException("could not read bytes at offset " + this.in.position(), e);
      }
   }

   public void skip() {
      try {
         int length = BytesUtils.readIntLittleEndian(this.in);
         this.in.skipFully((long)length);
      } catch (RuntimeException | IOException e) {
         throw new ParquetDecodingException("could not skip bytes at offset " + this.in.position(), e);
      }
   }

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      LOG.debug("init from page at offset {} for length {}", stream.position(), (long)stream.available() - stream.position());
      this.in = stream.remainingStream();
   }
}
