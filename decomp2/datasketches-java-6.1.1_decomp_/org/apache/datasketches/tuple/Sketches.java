package org.apache.datasketches.tuple;

import org.apache.datasketches.memory.Memory;

public final class Sketches {
   public static Sketch createEmptySketch() {
      return new CompactSketch((long[])null, (Summary[])null, Long.MAX_VALUE, true);
   }

   public static Sketch heapifySketch(Memory mem, SummaryDeserializer deserializer) {
      SerializerDeserializer.SketchType sketchType = SerializerDeserializer.getSketchType(mem);
      return (Sketch)(sketchType == SerializerDeserializer.SketchType.QuickSelectSketch ? new QuickSelectSketch(mem, deserializer, (SummaryFactory)null) : new CompactSketch(mem, deserializer));
   }

   public static UpdatableSketch heapifyUpdatableSketch(Memory mem, SummaryDeserializer deserializer, SummaryFactory summaryFactory) {
      return new UpdatableSketch(mem, deserializer, summaryFactory);
   }
}
