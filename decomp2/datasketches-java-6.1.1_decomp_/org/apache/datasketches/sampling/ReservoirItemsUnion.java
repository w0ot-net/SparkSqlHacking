package org.apache.datasketches.sampling;

import java.util.ArrayList;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public final class ReservoirItemsUnion {
   private ReservoirItemsSketch gadget_;
   private final int maxK_;

   private ReservoirItemsUnion(int maxK) {
      this.maxK_ = maxK;
   }

   public static ReservoirItemsUnion newInstance(int maxK) {
      return new ReservoirItemsUnion(maxK);
   }

   public static ReservoirItemsUnion heapify(Memory srcMem, ArrayOfItemsSerDe serDe) {
      Family.RESERVOIR_UNION.checkFamilyID(srcMem.getByte(2L));
      int numPreLongs = PreambleUtil.extractPreLongs(srcMem);
      int serVer = PreambleUtil.extractSerVer(srcMem);
      boolean isEmpty = (PreambleUtil.extractFlags(srcMem) & 4) != 0;
      int maxK = PreambleUtil.extractMaxK(srcMem);
      boolean preLongsEqMin = numPreLongs == Family.RESERVOIR_UNION.getMinPreLongs();
      boolean preLongsEqMax = numPreLongs == Family.RESERVOIR_UNION.getMaxPreLongs();
      if (!preLongsEqMin && !preLongsEqMax) {
         throw new SketchesArgumentException("Possible corruption: Non-empty union with only " + Family.RESERVOIR_UNION.getMinPreLongs() + "preLongs");
      } else {
         if (serVer != 2) {
            if (serVer != 1) {
               throw new SketchesArgumentException("Possible Corruption: Ser Ver must be 2: " + serVer);
            }

            short encMaxK = PreambleUtil.extractEncodedReservoirSize(srcMem);
            maxK = ReservoirSize.decodeValue(encMaxK);
         }

         ReservoirItemsUnion<T> riu = new ReservoirItemsUnion(maxK);
         if (!isEmpty) {
            int preLongBytes = numPreLongs << 3;
            Memory sketchMem = srcMem.region((long)preLongBytes, srcMem.getCapacity() - (long)preLongBytes);
            riu.update(sketchMem, serDe);
         }

         return riu;
      }
   }

   public int getMaxK() {
      return this.maxK_;
   }

   public void update(ReservoirItemsSketch sketchIn) {
      if (sketchIn != null) {
         ReservoirItemsSketch<T> ris = sketchIn.getK() <= this.maxK_ ? sketchIn : sketchIn.downsampledCopy(this.maxK_);
         boolean isModifiable = sketchIn != ris;
         if (this.gadget_ == null) {
            this.createNewGadget(ris, isModifiable);
         } else {
            this.twoWayMergeInternal(ris, isModifiable);
         }

      }
   }

   public void update(Memory mem, ArrayOfItemsSerDe serDe) {
      if (mem != null) {
         ReservoirItemsSketch<T> ris = ReservoirItemsSketch.heapify(mem, serDe);
         ris = ris.getK() <= this.maxK_ ? ris : ris.downsampledCopy(this.maxK_);
         if (this.gadget_ == null) {
            this.createNewGadget(ris, true);
         } else {
            this.twoWayMergeInternal(ris, true);
         }

      }
   }

   public void update(Object datum) {
      if (datum != null) {
         if (this.gadget_ == null) {
            this.gadget_ = ReservoirItemsSketch.newInstance(this.maxK_);
         }

         this.gadget_.update(datum);
      }
   }

   public void update(long n, int k, ArrayList input) {
      ReservoirItemsSketch<T> ris = ReservoirItemsSketch.newInstance(input, n, ResizeFactor.X8, k);
      ris = ris.getK() <= this.maxK_ ? ris : ris.downsampledCopy(this.maxK_);
      if (this.gadget_ == null) {
         this.createNewGadget(ris, true);
      } else {
         this.twoWayMergeInternal(ris, true);
      }

   }

   void reset() {
      this.gadget_.reset();
   }

   public ReservoirItemsSketch getResult() {
      return this.gadget_ != null ? this.gadget_.copy() : null;
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe) {
      return this.gadget_ != null && this.gadget_.getNumSamples() != 0 ? this.toByteArray(serDe, this.gadget_.getValueAtPosition(0).getClass()) : this.toByteArray(serDe, (Class)null);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      String thisSimpleName = this.getClass().getSimpleName();
      sb.append(Util.LS);
      sb.append("### ").append(thisSimpleName).append(" SUMMARY: ").append(Util.LS);
      sb.append("   Max k: ").append(this.maxK_).append(Util.LS);
      if (this.gadget_ == null) {
         sb.append("   Gadget is null").append(Util.LS);
      } else {
         sb.append("   Gadget summary: ").append(this.gadget_.toString());
      }

      sb.append("### END UNION SUMMARY").append(Util.LS);
      return sb.toString();
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe, Class clazz) {
      boolean empty = this.gadget_ == null;
      byte[] gadgetBytes = this.gadget_ != null ? this.gadget_.toByteArray(serDe, clazz) : null;
      int preLongs;
      int outBytes;
      if (empty) {
         preLongs = Family.RESERVOIR_UNION.getMinPreLongs();
         outBytes = 8;
      } else {
         preLongs = Family.RESERVOIR_UNION.getMaxPreLongs();
         outBytes = (preLongs << 3) + gadgetBytes.length;
      }

      byte[] outArr = new byte[outBytes];
      WritableMemory mem = WritableMemory.writableWrap(outArr);
      PreambleUtil.insertPreLongs(mem, preLongs);
      PreambleUtil.insertSerVer(mem, 2);
      PreambleUtil.insertFamilyID(mem, Family.RESERVOIR_UNION.getID());
      if (empty) {
         PreambleUtil.insertFlags(mem, 4);
      } else {
         PreambleUtil.insertFlags(mem, 0);
      }

      PreambleUtil.insertMaxK(mem, this.maxK_);
      if (!empty) {
         int preBytes = preLongs << 3;
         mem.putByteArray((long)preBytes, gadgetBytes, 0, gadgetBytes.length);
      }

      return outArr;
   }

   private void createNewGadget(ReservoirItemsSketch sketchIn, boolean isModifiable) {
      if (sketchIn.getK() < this.maxK_ && sketchIn.getN() <= (long)sketchIn.getK()) {
         this.gadget_ = ReservoirItemsSketch.newInstance(this.maxK_);
         this.twoWayMergeInternal(sketchIn, isModifiable);
      } else {
         this.gadget_ = isModifiable ? sketchIn : sketchIn.copy();
      }

   }

   private void twoWayMergeInternal(ReservoirItemsSketch sketchIn, boolean isModifiable) {
      if (sketchIn.getN() <= (long)sketchIn.getK()) {
         this.twoWayMergeInternalStandard(sketchIn);
      } else if (this.gadget_.getN() < (long)this.gadget_.getK()) {
         ReservoirItemsSketch<T> tmpSketch = this.gadget_;
         this.gadget_ = isModifiable ? sketchIn : sketchIn.copy();
         this.twoWayMergeInternalStandard(tmpSketch);
      } else if (sketchIn.getImplicitSampleWeight() < (double)this.gadget_.getN() / (double)(this.gadget_.getK() - 1)) {
         this.twoWayMergeInternalWeighted(sketchIn);
      } else {
         ReservoirItemsSketch<T> tmpSketch = this.gadget_;
         this.gadget_ = isModifiable ? sketchIn : sketchIn.copy();
         this.twoWayMergeInternalWeighted(tmpSketch);
      }

   }

   private void twoWayMergeInternalStandard(ReservoirItemsSketch source) {
      assert source.getN() <= (long)source.getK();

      int numInputSamples = source.getNumSamples();

      for(int i = 0; i < numInputSamples; ++i) {
         this.gadget_.update(source.getValueAtPosition(i));
      }

   }

   private void twoWayMergeInternalWeighted(ReservoirItemsSketch source) {
      assert this.gadget_.getN() >= (long)this.gadget_.getK();

      int numSourceSamples = source.getK();
      double sourceItemWeight = (double)source.getN() / (double)numSourceSamples;
      double rescaled_prob = (double)this.gadget_.getK() * sourceItemWeight;
      double targetTotal = (double)this.gadget_.getN();
      int tgtK = this.gadget_.getK();

      for(int i = 0; i < numSourceSamples; ++i) {
         targetTotal += sourceItemWeight;

         assert rescaled_prob < targetTotal;

         double rescaled_flip = targetTotal * SamplingUtil.rand().nextDouble();
         if (rescaled_flip < rescaled_prob) {
            int slotNo = SamplingUtil.rand().nextInt(tgtK);
            this.gadget_.insertValueAtPosition(source.getValueAtPosition(i), slotNo);
         }
      }

      long checkN = (long)Math.floor((double)0.5F + targetTotal);
      this.gadget_.forceIncrementItemsSeen(source.getN());

      assert checkN == this.gadget_.getN();

   }
}
