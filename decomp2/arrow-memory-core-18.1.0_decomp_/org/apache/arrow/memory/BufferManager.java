package org.apache.arrow.memory;

public interface BufferManager extends AutoCloseable {
   ArrowBuf replace(ArrowBuf var1, long var2);

   ArrowBuf getManagedBuffer();

   ArrowBuf getManagedBuffer(long var1);

   void close();
}
