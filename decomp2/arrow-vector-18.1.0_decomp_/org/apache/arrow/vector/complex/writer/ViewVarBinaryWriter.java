package org.apache.arrow.vector.complex.writer;

import java.nio.ByteBuffer;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.holders.ViewVarBinaryHolder;

public interface ViewVarBinaryWriter extends BaseWriter {
   void write(ViewVarBinaryHolder var1);

   void writeViewVarBinary(int var1, int var2, ArrowBuf var3);

   void writeViewVarBinary(byte[] var1);

   void writeViewVarBinary(byte[] var1, int var2, int var3);

   void writeViewVarBinary(ByteBuffer var1);

   void writeViewVarBinary(ByteBuffer var1, int var2, int var3);
}
