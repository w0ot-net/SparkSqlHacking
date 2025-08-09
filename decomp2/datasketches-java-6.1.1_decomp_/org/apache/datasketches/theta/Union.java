package org.apache.datasketches.theta;

import java.nio.ByteBuffer;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public abstract class Union extends SetOperation {
   public abstract int getCurrentBytes();

   public Family getFamily() {
      return Family.UNION;
   }

   public abstract int getMaxUnionBytes();

   public abstract CompactSketch getResult();

   public abstract CompactSketch getResult(boolean var1, WritableMemory var2);

   public abstract void reset();

   public abstract byte[] toByteArray();

   public CompactSketch union(Sketch sketchA, Sketch sketchB) {
      return this.union(sketchA, sketchB, true, (WritableMemory)null);
   }

   public abstract CompactSketch union(Sketch var1, Sketch var2, boolean var3, WritableMemory var4);

   public abstract void union(Sketch var1);

   public abstract void union(Memory var1);

   public abstract void update(long var1);

   public abstract void update(double var1);

   public abstract void update(String var1);

   public abstract void update(byte[] var1);

   public abstract void update(ByteBuffer var1);

   public abstract void update(int[] var1);

   public abstract void update(char[] var1);

   public abstract void update(long[] var1);
}
