package org.apache.datasketches.sampling;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public final class VarOptItemsUnion {
   private VarOptItemsSketch gadget_;
   private final int maxK_;
   private long n_;
   private double outerTauNumer;
   private long outerTauDenom;

   private VarOptItemsUnion(int maxK) {
      this.maxK_ = maxK;
      this.n_ = 0L;
      this.outerTauNumer = (double)0.0F;
      this.outerTauDenom = 0L;
      this.gadget_ = VarOptItemsSketch.newInstanceAsGadget(maxK);
   }

   public static VarOptItemsUnion newInstance(int maxK) {
      return new VarOptItemsUnion(maxK);
   }

   public static VarOptItemsUnion heapify(Memory srcMem, ArrayOfItemsSerDe serDe) {
      Family.VAROPT_UNION.checkFamilyID(srcMem.getByte(2L));
      long n = 0L;
      double outerTauNum = (double)0.0F;
      long outerTauDenom = 0L;
      int numPreLongs = PreambleUtil.extractPreLongs(srcMem);
      int serVer = PreambleUtil.extractSerVer(srcMem);
      boolean isEmpty = (PreambleUtil.extractFlags(srcMem) & 4) != 0;
      int maxK = PreambleUtil.extractMaxK(srcMem);
      if (!isEmpty) {
         n = PreambleUtil.extractN(srcMem);
         outerTauNum = PreambleUtil.extractOuterTauNumerator(srcMem);
         outerTauDenom = PreambleUtil.extractOuterTauDenominator(srcMem);
      }

      if (serVer != 2) {
         throw new SketchesArgumentException("Possible Corruption: Ser Ver must be 2: " + serVer);
      } else {
         boolean preLongsEqMin = numPreLongs == Family.VAROPT_UNION.getMinPreLongs();
         boolean preLongsEqMax = numPreLongs == Family.VAROPT_UNION.getMaxPreLongs();
         if (!preLongsEqMin && !preLongsEqMax) {
            throw new SketchesArgumentException("Possible corruption: Non-empty union with only " + Family.VAROPT_UNION.getMinPreLongs() + "preLongs");
         } else {
            VarOptItemsUnion<T> viu = new VarOptItemsUnion(maxK);
            if (isEmpty) {
               viu.gadget_ = VarOptItemsSketch.newInstanceAsGadget(maxK);
            } else {
               viu.n_ = n;
               viu.outerTauNumer = outerTauNum;
               viu.outerTauDenom = outerTauDenom;
               int preLongBytes = numPreLongs << 3;
               Memory sketchMem = srcMem.region((long)preLongBytes, srcMem.getCapacity() - (long)preLongBytes);
               viu.gadget_ = VarOptItemsSketch.heapify(sketchMem, serDe);
            }

            return viu;
         }
      }
   }

   public void update(VarOptItemsSketch sketchIn) {
      if (sketchIn != null) {
         this.mergeInto(sketchIn);
      }

   }

   public void update(Memory mem, ArrayOfItemsSerDe serDe) {
      if (mem != null) {
         VarOptItemsSketch<T> vis = VarOptItemsSketch.heapify(mem, serDe);
         this.mergeInto(vis);
      }

   }

   public void update(ReservoirItemsSketch reservoirIn) {
      if (reservoirIn != null) {
         this.mergeReservoirInto(reservoirIn);
      }

   }

   public VarOptItemsSketch getResult() {
      if (this.gadget_.getNumMarksInH() == 0) {
         return this.simpleGadgetCoercer();
      } else {
         VarOptItemsSketch<T> tmp = this.detectAndHandleSubcaseOfPseudoExact();
         return tmp != null ? tmp : this.migrateMarkedItemsByDecreasingK();
      }
   }

   public void reset() {
      this.gadget_.reset();
      this.n_ = 0L;
      this.outerTauNumer = (double)0.0F;
      this.outerTauDenom = 0L;
   }

   public String toString() {
      assert this.gadget_ != null;

      StringBuilder sb = new StringBuilder();
      String thisSimpleName = this.getClass().getSimpleName();
      sb.append(Util.LS).append("### ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS).append("   Max k: ").append(this.maxK_).append(Util.LS).append("   Gadget summary: ").append(this.gadget_.toString()).append("### END UNION SUMMARY").append(Util.LS);
      return sb.toString();
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe) {
      assert this.gadget_ != null;

      return this.gadget_.getNumSamples() == 0 ? this.toByteArray(serDe, (Class)null) : this.toByteArray(serDe, this.gadget_.getItem(0).getClass());
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe, Class clazz) {
      boolean empty = this.gadget_.getNumSamples() == 0;
      byte[] gadgetBytes = empty ? null : this.gadget_.toByteArray(serDe, clazz);
      int preLongs;
      int outBytes;
      if (empty) {
         preLongs = Family.VAROPT_UNION.getMinPreLongs();
         outBytes = 8;
      } else {
         preLongs = Family.VAROPT_UNION.getMaxPreLongs();
         outBytes = (preLongs << 3) + gadgetBytes.length;
      }

      byte[] outArr = new byte[outBytes];
      WritableMemory mem = WritableMemory.writableWrap(outArr);
      PreambleUtil.insertPreLongs(mem, preLongs);
      PreambleUtil.insertSerVer(mem, 2);
      PreambleUtil.insertFamilyID(mem, Family.VAROPT_UNION.getID());
      if (empty) {
         PreambleUtil.insertFlags(mem, 4);
      } else {
         PreambleUtil.insertFlags(mem, 0);
      }

      PreambleUtil.insertMaxK(mem, this.maxK_);
      if (!empty) {
         PreambleUtil.insertN(mem, this.n_);
         PreambleUtil.insertOuterTauNumerator(mem, this.outerTauNumer);
         PreambleUtil.insertOuterTauDenominator(mem, this.outerTauDenom);
         int preBytes = preLongs << 3;
         mem.putByteArray((long)preBytes, gadgetBytes, 0, gadgetBytes.length);
      }

      return outArr;
   }

   double getOuterTau() {
      return this.outerTauDenom == 0L ? (double)0.0F : this.outerTauNumer / (double)this.outerTauDenom;
   }

   private void mergeInto(VarOptItemsSketch sketch) {
      long sketchN = sketch.getN();
      if (sketchN != 0L) {
         this.n_ += sketchN;
         VarOptItemsSamples<T> sketchSamples = sketch.getSketchSamples();
         Iterator<VarOptItemsSamples<T>.WeightedSample> sketchIterator = sketchSamples.getHIterator();

         while(sketchIterator.hasNext()) {
            VarOptItemsSamples<T>.WeightedSample ws = (VarOptItemsSamples.WeightedSample)sketchIterator.next();
            this.gadget_.update(ws.getItem(), ws.getWeight(), false);
         }

         sketchIterator = sketchSamples.getWeightCorrRIter();

         while(sketchIterator.hasNext()) {
            VarOptItemsSamples<T>.WeightedSample ws = (VarOptItemsSamples.WeightedSample)sketchIterator.next();
            this.gadget_.update(ws.getItem(), ws.getWeight(), true);
         }

         if (sketch.getRRegionCount() > 0) {
            double sketchTau = sketch.getTau();
            double outerTau = this.getOuterTau();
            if (this.outerTauDenom == 0L) {
               this.outerTauNumer = sketch.getTotalWtR();
               this.outerTauDenom = (long)sketch.getRRegionCount();
            } else if (sketchTau > outerTau) {
               this.outerTauNumer = sketch.getTotalWtR();
               this.outerTauDenom = (long)sketch.getRRegionCount();
            } else if (sketchTau == outerTau) {
               this.outerTauNumer += sketch.getTotalWtR();
               this.outerTauDenom += (long)sketch.getRRegionCount();
            }
         }

      }
   }

   private void mergeReservoirInto(ReservoirItemsSketch reservoir) {
      long reservoirN = reservoir.getN();
      if (reservoirN != 0L) {
         this.n_ += reservoirN;
         int reservoirK = reservoir.getK();
         if (reservoir.getN() <= (long)reservoirK) {
            for(Object item : reservoir.getRawSamplesAsList()) {
               this.gadget_.update(item, (double)1.0F, false);
            }
         } else {
            double reservoirTau = reservoir.getImplicitSampleWeight();
            double cumWeight = (double)0.0F;
            ArrayList<T> samples = reservoir.getRawSamplesAsList();

            for(int i = 0; i < reservoirK - 1; ++i) {
               this.gadget_.update(samples.get(i), reservoirTau, true);
               cumWeight += reservoirTau;
            }

            this.gadget_.update(samples.get(reservoirK - 1), (double)reservoir.getN() - cumWeight, true);
            double outerTau = this.getOuterTau();
            if (this.outerTauDenom == 0L) {
               this.outerTauNumer = (double)reservoirN;
               this.outerTauDenom = (long)reservoirK;
            } else if (reservoirTau > outerTau) {
               this.outerTauNumer = (double)reservoirN;
               this.outerTauDenom = (long)reservoirK;
            } else if (reservoirTau == outerTau) {
               this.outerTauNumer += (double)reservoirN;
               this.outerTauDenom += (long)reservoirK;
            }
         }

      }
   }

   private VarOptItemsSketch simpleGadgetCoercer() {
      assert this.gadget_.getNumMarksInH() == 0;

      return this.gadget_.copyAndSetN(true, this.n_);
   }

   private VarOptItemsSketch markMovingGadgetCoercer() {
      int resultK = this.gadget_.getHRegionCount() + this.gadget_.getRRegionCount();
      int resultH = 0;
      int resultR = 0;
      int nextRPos = resultK;
      ArrayList<T> data = new ArrayList(resultK + 1);
      ArrayList<Double> weights = new ArrayList(resultK + 1);

      for(int i = 0; i < resultK + 1; ++i) {
         data.add((Object)null);
         weights.add((Object)null);
      }

      VarOptItemsSamples<T> sketchSamples = this.gadget_.getSketchSamples();

      for(Iterator<VarOptItemsSamples<T>.WeightedSample> sketchIterator = sketchSamples.getRIterator(); sketchIterator.hasNext(); --nextRPos) {
         VarOptItemsSamples<T>.WeightedSample ws = (VarOptItemsSamples.WeightedSample)sketchIterator.next();
         data.set(nextRPos, ws.getItem());
         weights.set(nextRPos, (double)-1.0F);
         ++resultR;
      }

      double transferredWeight = (double)0.0F;
      Iterator var16 = sketchSamples.getHIterator();

      while(var16.hasNext()) {
         VarOptItemsSamples<T>.WeightedSample ws = (VarOptItemsSamples.WeightedSample)var16.next();
         if (ws.getMark()) {
            data.set(nextRPos, ws.getItem());
            weights.set(nextRPos, (double)-1.0F);
            transferredWeight += ws.getWeight();
            ++resultR;
            --nextRPos;
         } else {
            data.set(resultH, ws.getItem());
            weights.set(resultH, ws.getWeight());
            ++resultH;
         }
      }

      assert resultH + resultR == resultK;

      assert Math.abs(transferredWeight - this.outerTauNumer) < 1.0E-10;

      double resultRWeight = this.gadget_.getTotalWtR() + transferredWeight;
      long resultN = this.n_;
      data.set(resultH, (Object)null);
      weights.set(resultH, (double)-1.0F);
      return VarOptItemsSketch.newInstanceFromUnionResult(data, weights, resultK, resultN, resultH, resultR, resultRWeight);
   }

   private VarOptItemsSketch detectAndHandleSubcaseOfPseudoExact() {
      boolean condition1 = this.gadget_.getRRegionCount() == 0;
      boolean condition2 = this.gadget_.getNumMarksInH() > 0;
      boolean condition3 = (long)this.gadget_.getNumMarksInH() == this.outerTauDenom;
      if (condition1 && condition2 && condition3) {
         boolean antiCondition4 = this.thereExistUnmarkedHItemsLighterThanTarget(this.gadget_.getTau());
         return antiCondition4 ? null : this.markMovingGadgetCoercer();
      } else {
         return null;
      }
   }

   private boolean thereExistUnmarkedHItemsLighterThanTarget(double threshold) {
      for(int i = 0; i < this.gadget_.getHRegionCount(); ++i) {
         if (this.gadget_.getWeight(i) < threshold && !this.gadget_.getMark(i)) {
            return true;
         }
      }

      return false;
   }

   private VarOptItemsSketch migrateMarkedItemsByDecreasingK() {
      VarOptItemsSketch<T> gcopy = this.gadget_.copyAndSetN(false, this.n_);
      int rCount = gcopy.getRRegionCount();
      int hCount = gcopy.getHRegionCount();
      int k = gcopy.getK();

      assert gcopy.getNumMarksInH() > 0;

      assert rCount == 0 || k == hCount + rCount;

      if (rCount == 0 && hCount < k) {
         gcopy.forceSetK(hCount);
      }

      assert gcopy.getK() >= 2;

      gcopy.decreaseKBy1();

      assert gcopy.getRRegionCount() > 0;

      assert gcopy.getTau() > (double)0.0F;

      while(gcopy.getNumMarksInH() > 0) {
         assert gcopy.getK() >= 2;

         gcopy.decreaseKBy1();
      }

      gcopy.stripMarks();
      return gcopy;
   }
}
