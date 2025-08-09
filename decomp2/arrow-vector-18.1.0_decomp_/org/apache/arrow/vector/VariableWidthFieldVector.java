package org.apache.arrow.vector;

import java.nio.ByteBuffer;
import org.apache.arrow.memory.ReusableBuffer;

public interface VariableWidthFieldVector extends VariableWidthVector, FieldVector, VectorDefinitionSetter {
   void set(int var1, byte[] var2);

   void set(int var1, byte[] var2, int var3, int var4);

   void set(int var1, ByteBuffer var2, int var3, int var4);

   void setSafe(int var1, byte[] var2);

   void setSafe(int var1, byte[] var2, int var3, int var4);

   void setSafe(int var1, ByteBuffer var2, int var3, int var4);

   byte[] get(int var1);

   void read(int var1, ReusableBuffer var2);

   int getLastSet();

   void setLastSet(int var1);

   int getValueLength(int var1);

   void fillEmpties(int var1);

   void setValueLengthSafe(int var1, int var2);
}
