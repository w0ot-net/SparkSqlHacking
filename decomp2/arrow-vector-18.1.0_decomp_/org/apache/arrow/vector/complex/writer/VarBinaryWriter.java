package org.apache.arrow.vector.complex.writer;

import java.nio.ByteBuffer;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.holders.VarBinaryHolder;

public interface VarBinaryWriter extends BaseWriter {
   void write(VarBinaryHolder var1);

   void writeVarBinary(int var1, int var2, ArrowBuf var3);

   void writeVarBinary(byte[] var1);

   void writeVarBinary(byte[] var1, int var2, int var3);

   void writeVarBinary(ByteBuffer var1);

   void writeVarBinary(ByteBuffer var1, int var2, int var3);
}
