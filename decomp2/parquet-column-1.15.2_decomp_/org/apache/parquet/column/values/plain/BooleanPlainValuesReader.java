package org.apache.parquet.column.values.plain;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.column.values.bitpacking.ByteBitPackingValuesReader;
import org.apache.parquet.column.values.bitpacking.Packer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BooleanPlainValuesReader extends ValuesReader {
   private static final Logger LOG = LoggerFactory.getLogger(BooleanPlainValuesReader.class);
   private ByteBitPackingValuesReader in;

   public BooleanPlainValuesReader() {
      this.in = new ByteBitPackingValuesReader(1, Packer.LITTLE_ENDIAN);
   }

   public boolean readBoolean() {
      return this.in.readInteger() != 0;
   }

   public void skip() {
      this.in.readInteger();
   }

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      LOG.debug("init from page at offset {} for length {}", stream.position(), stream.available());
      this.in.initFromPage(valueCount, stream);
   }

   /** @deprecated */
   @Deprecated
   public int getNextOffset() {
      return this.in.getNextOffset();
   }
}
