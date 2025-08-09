package org.apache.datasketches.tuple.strings;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.DeserializeResult;
import org.apache.datasketches.tuple.SummaryDeserializer;

public class ArrayOfStringsSummaryDeserializer implements SummaryDeserializer {
   public DeserializeResult heapifySummary(Memory mem) {
      return fromMemory(mem);
   }

   static DeserializeResult fromMemory(Memory mem) {
      ArrayOfStringsSummary nsum = new ArrayOfStringsSummary(mem);
      int totBytes = mem.getInt(0L);
      return new DeserializeResult(nsum, totBytes);
   }
}
