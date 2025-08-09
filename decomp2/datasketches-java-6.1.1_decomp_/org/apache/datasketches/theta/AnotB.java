package org.apache.datasketches.theta;

import org.apache.datasketches.common.Family;
import org.apache.datasketches.memory.WritableMemory;

public abstract class AnotB extends SetOperation {
   public Family getFamily() {
      return Family.A_NOT_B;
   }

   public abstract void setA(Sketch var1);

   public abstract void notB(Sketch var1);

   public abstract CompactSketch getResult(boolean var1);

   public abstract CompactSketch getResult(boolean var1, WritableMemory var2, boolean var3);

   public CompactSketch aNotB(Sketch skA, Sketch skB) {
      return this.aNotB(skA, skB, true, (WritableMemory)null);
   }

   public abstract CompactSketch aNotB(Sketch var1, Sketch var2, boolean var3, WritableMemory var4);
}
