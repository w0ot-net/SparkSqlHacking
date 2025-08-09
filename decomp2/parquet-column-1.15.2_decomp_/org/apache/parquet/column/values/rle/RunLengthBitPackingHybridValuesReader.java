package org.apache.parquet.column.values.rle;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.io.ParquetDecodingException;

public class RunLengthBitPackingHybridValuesReader extends ValuesReader {
   private final int bitWidth;
   private RunLengthBitPackingHybridDecoder decoder;

   public RunLengthBitPackingHybridValuesReader(int bitWidth) {
      this.bitWidth = bitWidth;
   }

   public void initFromPage(int valueCountL, ByteBufferInputStream stream) throws IOException {
      int length = BytesUtils.readIntLittleEndian(stream);
      this.decoder = new RunLengthBitPackingHybridDecoder(this.bitWidth, stream.sliceStream((long)length));
      this.updateNextOffset(length + 4);
   }

   public int readInteger() {
      try {
         return this.decoder.readInt();
      } catch (IOException e) {
         throw new ParquetDecodingException(e);
      }
   }

   public boolean readBoolean() {
      return this.readInteger() != 0;
   }

   public void skip() {
      this.readInteger();
   }
}
