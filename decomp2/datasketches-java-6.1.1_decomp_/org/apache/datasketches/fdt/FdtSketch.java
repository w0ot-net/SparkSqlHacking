package org.apache.datasketches.fdt;

import java.util.List;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.tuple.strings.ArrayOfStringsSketch;

public final class FdtSketch extends ArrayOfStringsSketch {
   public FdtSketch(int lgK) {
      super(lgK);
   }

   /** @deprecated */
   @Deprecated
   FdtSketch(Memory mem) {
      super(mem);
   }

   public FdtSketch(double threshold, double rse) {
      super(computeLgK(threshold, rse));
   }

   public FdtSketch(FdtSketch sketch) {
      super((ArrayOfStringsSketch)sketch);
   }

   public FdtSketch copy() {
      return new FdtSketch(this);
   }

   public void update(String[] tuple) {
      super.update(tuple, tuple);
   }

   public List getResult(int[] priKeyIndices, int limit, int numStdDev, char sep) {
      PostProcessor proc = new PostProcessor(this, new Group(), sep);
      return proc.getGroupList(priKeyIndices, numStdDev, limit);
   }

   public PostProcessor getPostProcessor() {
      return this.getPostProcessor(new Group(), '|');
   }

   public PostProcessor getPostProcessor(Group group, char sep) {
      return new PostProcessor(this, group, sep);
   }

   static int computeLgK(double threshold, double rse) {
      double v = Math.ceil((double)1.0F / (threshold * rse * rse));
      int lgK = (int)Math.ceil(Math.log(v) / Math.log((double)2.0F));
      if (lgK > 26) {
         throw new SketchesArgumentException("Requested Sketch (LgK = " + lgK + " &gt; 2^26), either increase the threshold, the rse or both.");
      } else {
         return lgK;
      }
   }
}
