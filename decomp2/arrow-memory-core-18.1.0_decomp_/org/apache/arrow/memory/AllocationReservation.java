package org.apache.arrow.memory;

public interface AllocationReservation extends AutoCloseable {
   /** @deprecated */
   @Deprecated(
      forRemoval = true
   )
   boolean add(int var1);

   boolean add(long var1);

   /** @deprecated */
   @Deprecated(
      forRemoval = true
   )
   boolean reserve(int var1);

   boolean reserve(long var1);

   ArrowBuf allocateBuffer();

   int getSize();

   long getSizeLong();

   boolean isUsed();

   boolean isClosed();

   void close();
}
