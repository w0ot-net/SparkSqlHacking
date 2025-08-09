package org.apache.parquet.column.values.bloomfilter;

import java.nio.ByteBuffer;

public interface HashFunction {
   long hashBytes(byte[] var1);

   long hashByteBuffer(ByteBuffer var1);
}
