package org.apache.datasketches.tuple.aninteger;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.DeserializeResult;
import org.apache.datasketches.tuple.SummaryDeserializer;

public class IntegerSummaryDeserializer implements SummaryDeserializer {
   public DeserializeResult heapifySummary(Memory mem) {
      return IntegerSummary.fromMemory(mem);
   }
}
