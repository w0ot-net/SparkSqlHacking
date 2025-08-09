package org.apache.hadoop.hive.common.io.encoded;

public interface MemoryBufferOrBuffers {
   MemoryBuffer getSingleBuffer();

   MemoryBuffer[] getMultipleBuffers();
}
