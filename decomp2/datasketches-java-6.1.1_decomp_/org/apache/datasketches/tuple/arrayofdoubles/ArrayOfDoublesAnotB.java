package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.memory.WritableMemory;

public abstract class ArrayOfDoublesAnotB {
   ArrayOfDoublesAnotB() {
   }

   public abstract void update(ArrayOfDoublesSketch var1, ArrayOfDoublesSketch var2);

   public abstract ArrayOfDoublesCompactSketch getResult();

   public abstract ArrayOfDoublesCompactSketch getResult(WritableMemory var1);
}
