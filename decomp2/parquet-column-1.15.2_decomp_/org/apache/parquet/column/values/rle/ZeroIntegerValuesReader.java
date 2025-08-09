package org.apache.parquet.column.values.rle;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.column.values.ValuesReader;

public class ZeroIntegerValuesReader extends ValuesReader {
   public int readInteger() {
      return 0;
   }

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      this.updateNextOffset(0);
   }

   public void skip() {
   }

   public void skip(int n) {
   }
}
