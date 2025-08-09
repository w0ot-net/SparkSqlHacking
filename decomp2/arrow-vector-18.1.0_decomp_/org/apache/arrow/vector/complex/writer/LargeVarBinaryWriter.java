package org.apache.arrow.vector.complex.writer;

import java.nio.ByteBuffer;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.holders.LargeVarBinaryHolder;

public interface LargeVarBinaryWriter extends BaseWriter {
   void write(LargeVarBinaryHolder var1);

   void writeLargeVarBinary(long var1, long var3, ArrowBuf var5);

   void writeLargeVarBinary(byte[] var1);

   void writeLargeVarBinary(byte[] var1, int var2, int var3);

   void writeLargeVarBinary(ByteBuffer var1);

   void writeLargeVarBinary(ByteBuffer var1, int var2, int var3);
}
