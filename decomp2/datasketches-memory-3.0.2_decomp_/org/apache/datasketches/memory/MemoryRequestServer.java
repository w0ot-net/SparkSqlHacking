package org.apache.datasketches.memory;

public interface MemoryRequestServer {
   WritableMemory request(WritableMemory var1, long var2);

   default void requestClose(WritableMemory memToClose) {
      this.requestClose(memToClose, (WritableMemory)null);
   }

   void requestClose(WritableMemory var1, WritableMemory var2);
}
