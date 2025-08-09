package org.apache.datasketches.memory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.datasketches.memory.internal.BaseWritableBufferImpl;

public interface Buffer extends Positional {
   static Buffer wrap(ByteBuffer byteBuffer) {
      return wrap(byteBuffer, byteBuffer.order());
   }

   static Buffer wrap(ByteBuffer byteBuffer, ByteOrder byteOrder) {
      return BaseWritableBufferImpl.wrapByteBuffer(byteBuffer, true, byteOrder, (MemoryRequestServer)null);
   }

   default Buffer duplicate() {
      return this.duplicate(this.getTypeByteOrder());
   }

   Buffer duplicate(ByteOrder var1);

   default Buffer region() {
      return this.region(this.getPosition(), this.getEnd() - this.getPosition(), this.getTypeByteOrder());
   }

   Buffer region(long var1, long var3, ByteOrder var5);

   default Memory asMemory() {
      return this.asMemory(this.getTypeByteOrder());
   }

   Memory asMemory(ByteOrder var1);

   boolean getBoolean();

   boolean getBoolean(long var1);

   byte getByte();

   byte getByte(long var1);

   void getByteArray(byte[] var1, int var2, int var3);

   char getChar();

   char getChar(long var1);

   void getCharArray(char[] var1, int var2, int var3);

   double getDouble();

   double getDouble(long var1);

   void getDoubleArray(double[] var1, int var2, int var3);

   float getFloat();

   float getFloat(long var1);

   void getFloatArray(float[] var1, int var2, int var3);

   int getInt();

   int getInt(long var1);

   void getIntArray(int[] var1, int var2, int var3);

   long getLong();

   long getLong(long var1);

   void getLongArray(long[] var1, int var2, int var3);

   short getShort();

   short getShort(long var1);

   void getShortArray(short[] var1, int var2, int var3);

   int compareTo(long var1, long var3, Buffer var5, long var6, long var8);
}
