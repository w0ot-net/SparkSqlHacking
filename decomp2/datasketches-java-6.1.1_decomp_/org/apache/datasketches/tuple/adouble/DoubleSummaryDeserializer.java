package org.apache.datasketches.tuple.adouble;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.DeserializeResult;
import org.apache.datasketches.tuple.SummaryDeserializer;

public class DoubleSummaryDeserializer implements SummaryDeserializer {
   public DeserializeResult heapifySummary(Memory mem) {
      return DoubleSummary.fromMemory(mem);
   }
}
