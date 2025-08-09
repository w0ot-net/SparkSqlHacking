package org.apache.spark.unsafe.memory;

public interface MemoryAllocator {
   boolean MEMORY_DEBUG_FILL_ENABLED = Boolean.parseBoolean(System.getProperty("spark.memory.debugFill", "false"));
   byte MEMORY_DEBUG_FILL_CLEAN_VALUE = -91;
   byte MEMORY_DEBUG_FILL_FREED_VALUE = 90;
   MemoryAllocator UNSAFE = new UnsafeMemoryAllocator();
   MemoryAllocator HEAP = new HeapMemoryAllocator();

   MemoryBlock allocate(long var1) throws OutOfMemoryError;

   void free(MemoryBlock var1);
}
