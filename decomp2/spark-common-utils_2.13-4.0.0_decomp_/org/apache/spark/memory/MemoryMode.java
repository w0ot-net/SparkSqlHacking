package org.apache.spark.memory;

import org.apache.spark.annotation.Private;

@Private
public enum MemoryMode {
   ON_HEAP,
   OFF_HEAP;

   // $FF: synthetic method
   private static MemoryMode[] $values() {
      return new MemoryMode[]{ON_HEAP, OFF_HEAP};
   }
}
