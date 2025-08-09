package org.apache.arrow.memory;

public interface ReusableBuffer {
   long getLength();

   Object getBuffer();

   void set(ArrowBuf var1, long var2, long var4);

   void set(byte[] var1, long var2, long var4);
}
