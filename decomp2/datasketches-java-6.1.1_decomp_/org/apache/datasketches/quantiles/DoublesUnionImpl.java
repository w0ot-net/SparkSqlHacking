package org.apache.datasketches.quantiles;

import java.util.Objects;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class DoublesUnionImpl extends DoublesUnionImplR {
   private DoublesUnionImpl(int maxK) {
      super(maxK);
   }

   static DoublesUnionImpl heapInstance(int maxK) {
      return new DoublesUnionImpl(maxK);
   }

   static DoublesUnionImpl directInstance(int maxK, WritableMemory dstMem) {
      Objects.requireNonNull(dstMem);
      DirectUpdateDoublesSketch sketch = DirectUpdateDoublesSketch.newInstance(maxK, dstMem);
      DoublesUnionImpl union = new DoublesUnionImpl(maxK);
      union.maxK_ = maxK;
      union.gadget_ = sketch;
      return union;
   }

   static DoublesUnionImpl heapifyInstance(DoublesSketch sketch) {
      Objects.requireNonNull(sketch);
      int k = sketch.getK();
      DoublesUnionImpl union = new DoublesUnionImpl(k);
      union.maxK_ = k;
      union.gadget_ = DoublesUtil.copyToHeap(sketch);
      return union;
   }

   static DoublesUnionImpl heapifyInstance(Memory srcMem) {
      Objects.requireNonNull(srcMem);
      HeapUpdateDoublesSketch sketch = HeapUpdateDoublesSketch.heapifyInstance(srcMem);
      DoublesUnionImpl union = new DoublesUnionImpl(sketch.getK());
      union.gadget_ = sketch;
      return union;
   }

   static DoublesUnionImpl wrapInstance(WritableMemory mem) {
      Objects.requireNonNull(mem);
      DirectUpdateDoublesSketch sketch = DirectUpdateDoublesSketch.wrapInstance(mem);
      DoublesUnionImpl union = new DoublesUnionImpl(sketch.getK());
      union.gadget_ = sketch;
      return union;
   }

   public void union(DoublesSketch sketchIn) {
      Objects.requireNonNull(sketchIn);
      this.gadget_ = updateLogic(this.maxK_, this.gadget_, sketchIn);
      this.gadget_.doublesSV = null;
   }

   public void union(Memory mem) {
      Objects.requireNonNull(mem);
      this.gadget_ = updateLogic(this.maxK_, this.gadget_, DoublesSketch.wrap(mem));
      this.gadget_.doublesSV = null;
   }

   public void update(double quantile) {
      if (this.gadget_ == null) {
         this.gadget_ = HeapUpdateDoublesSketch.newInstance(this.maxK_);
      }

      this.gadget_.update(quantile);
      this.gadget_.doublesSV = null;
   }

   public UpdateDoublesSketch getResultAndReset() {
      if (this.gadget_ == null) {
         return null;
      } else {
         UpdateDoublesSketch ds = this.gadget_;
         this.gadget_ = null;
         return ds;
      }
   }

   public void reset() {
      this.gadget_ = null;
   }

   static UpdateDoublesSketch updateLogic(int myMaxK, UpdateDoublesSketch myQS, DoublesSketch other) {
      int sw1 = myQS == null ? 0 : (myQS.isEmpty() ? 4 : 8);
      sw1 |= other == null ? 0 : (other.isEmpty() ? 1 : 2);
      int outCase = 0;
      switch (sw1) {
         case 0:
            outCase = 0;
            break;
         case 1:
            outCase = 4;
            break;
         case 2:
            outCase = 2;
         case 3:
         case 7:
         default:
            break;
         case 4:
            outCase = 1;
            break;
         case 5:
            outCase = 1;
            break;
         case 6:
            outCase = 3;
            break;
         case 8:
            outCase = 1;
            break;
         case 9:
            outCase = 1;
            break;
         case 10:
            outCase = 3;
      }

      UpdateDoublesSketch ret = null;
      switch (outCase) {
         case 0:
         default:
            break;
         case 1:
            ret = myQS;
            break;
         case 2:
            assert other != null;

            if (!other.isEstimationMode()) {
               ret = HeapUpdateDoublesSketch.newInstance(myMaxK);
               DoublesSketchAccessor otherAccessor = DoublesSketchAccessor.wrap(other);

               for(int i = 0; i < otherAccessor.numItems(); ++i) {
                  ret.update(otherAccessor.get(i));
               }
            } else {
               ret = (UpdateDoublesSketch)(myMaxK < other.getK() ? other.downSampleInternal(other, myMaxK, (WritableMemory)null) : DoublesUtil.copyToHeap(other));
            }
            break;
         case 3:
            assert other != null;

            assert myQS != null;

            if (!other.isEstimationMode()) {
               ret = myQS;
               DoublesSketchAccessor otherAccessor = DoublesSketchAccessor.wrap(other);

               for(int i = 0; i < otherAccessor.numItems(); ++i) {
                  ret.update(otherAccessor.get(i));
               }
            } else if (myQS.getK() <= other.getK()) {
               DoublesMergeImpl.mergeInto(other, myQS);
               ret = myQS;
            } else if (myQS.isEmpty()) {
               if (myQS.hasMemory()) {
                  WritableMemory mem = myQS.getMemory();
                  other.putMemory(mem, false);
                  ret = DirectUpdateDoublesSketch.wrapInstance(mem);
               } else {
                  ret = DoublesUtil.copyToHeap(other);
               }
            } else {
               UpdateDoublesSketch tmp = DoublesSketch.builder().setK(other.getK()).build();
               DoublesMergeImpl.downSamplingMergeInto(myQS, tmp);
               ret = myQS.hasMemory() ? DoublesSketch.builder().setK(other.getK()).build(myQS.getMemory()) : DoublesSketch.builder().setK(other.getK()).build();
               DoublesMergeImpl.mergeInto(tmp, ret);
               DoublesMergeImpl.mergeInto(other, ret);
            }
            break;
         case 4:
            ret = HeapUpdateDoublesSketch.newInstance(myMaxK);
      }

      return ret;
   }
}
