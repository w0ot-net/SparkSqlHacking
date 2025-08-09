package org.apache.parquet.column.values.bloomfilter;

import java.nio.ByteBuffer;
import shaded.parquet.net.openhft.hashing.LongHashFunction;

public class XxHash implements HashFunction {
   public long hashBytes(byte[] input) {
      return LongHashFunction.xx().hashBytes(input);
   }

   public long hashByteBuffer(ByteBuffer input) {
      return LongHashFunction.xx().hashBytes(input);
   }
}
