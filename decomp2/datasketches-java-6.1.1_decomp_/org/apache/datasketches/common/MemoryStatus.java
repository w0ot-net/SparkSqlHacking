package org.apache.datasketches.common;

import org.apache.datasketches.memory.Memory;

public interface MemoryStatus {
   default boolean hasMemory() {
      return false;
   }

   default boolean isDirect() {
      return false;
   }

   default boolean isSameResource(Memory that) {
      return false;
   }
}
