package org.apache.datasketches.quantiles;

import java.util.Comparator;
import java.util.Objects;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;

public final class ItemsUnion {
   final int maxK_;
   final Comparator comparator_;
   ItemsSketch gadget_;
   Class clazz_;

   private ItemsUnion(int maxK, Comparator comparator, ItemsSketch gadget) {
      Objects.requireNonNull(gadget, "Gadjet sketch must not be null.");
      Objects.requireNonNull(comparator, "Comparator must not be null.");
      this.maxK_ = maxK;
      this.comparator_ = comparator;
      this.gadget_ = gadget;
      this.clazz_ = gadget.clazz;
      this.gadget_.classicQisSV = null;
   }

   public static ItemsUnion getInstance(Class clazz, Comparator comparator) {
      ItemsSketch<T> emptySk = ItemsSketch.getInstance(clazz, comparator);
      return new ItemsUnion(128, comparator, emptySk);
   }

   public static ItemsUnion getInstance(Class clazz, int maxK, Comparator comparator) {
      ItemsSketch<T> emptySk = ItemsSketch.getInstance(clazz, maxK, comparator);
      return new ItemsUnion(maxK, comparator, emptySk);
   }

   public static ItemsUnion getInstance(Class clazz, Memory srcMem, Comparator comparator, ArrayOfItemsSerDe serDe) {
      ItemsSketch<T> gadget = ItemsSketch.getInstance(clazz, srcMem, comparator, serDe);
      return new ItemsUnion(gadget.getK(), gadget.getComparator(), gadget);
   }

   public static ItemsUnion getInstance(ItemsSketch sketch) {
      return new ItemsUnion(sketch.getK(), sketch.getComparator(), ItemsSketch.copy(sketch));
   }

   public void union(ItemsSketch sketchIn) {
      this.gadget_ = updateLogic(this.maxK_, this.comparator_, this.gadget_, sketchIn);
   }

   public void union(Memory srcMem, ArrayOfItemsSerDe serDe) {
      ItemsSketch<T> that = ItemsSketch.getInstance(this.clazz_, srcMem, this.comparator_, serDe);
      this.gadget_ = updateLogic(this.maxK_, this.comparator_, this.gadget_, that);
   }

   public void update(Object dataItem) {
      if (dataItem != null) {
         if (this.gadget_ == null) {
            this.gadget_ = ItemsSketch.getInstance(this.clazz_, this.maxK_, this.comparator_);
         }

         this.gadget_.update(dataItem);
      }
   }

   public ItemsSketch getResult() {
      return this.gadget_ == null ? ItemsSketch.getInstance(this.clazz_, this.maxK_, this.comparator_) : ItemsSketch.copy(this.gadget_);
   }

   public ItemsSketch getResultAndReset() {
      if (this.gadget_ == null) {
         return null;
      } else {
         ItemsSketch<T> hqs = this.gadget_;
         this.gadget_ = null;
         return hqs;
      }
   }

   public void reset() {
      this.gadget_ = null;
   }

   public boolean isEmpty() {
      return this.gadget_ == null || this.gadget_.isEmpty();
   }

   public boolean isDirect() {
      return this.gadget_ != null && this.gadget_.isDirect();
   }

   public int getMaxK() {
      return this.maxK_;
   }

   public int getEffectiveK() {
      return this.gadget_ != null ? this.gadget_.getK() : this.maxK_;
   }

   public String toString() {
      return this.toString(true, false);
   }

   public String toString(boolean sketchSummary, boolean dataDetail) {
      StringBuilder sb = new StringBuilder();
      String thisSimpleName = this.getClass().getSimpleName();
      int maxK = this.getMaxK();
      String kStr = String.format("%,d", maxK);
      sb.append(Util.LS).append("### Quantiles ").append(thisSimpleName).append(Util.LS);
      sb.append("   maxK                         : ").append(kStr);
      if (this.gadget_ == null) {
         sb.append(ItemsSketch.getInstance(this.clazz_, this.maxK_, this.comparator_).toString());
         return sb.toString();
      } else {
         sb.append(this.gadget_.toString(sketchSummary, dataDetail));
         return sb.toString();
      }
   }

   public byte[] toByteArray(ArrayOfItemsSerDe serDe) {
      if (this.gadget_ == null) {
         ItemsSketch<T> sketch = ItemsSketch.getInstance(this.clazz_, this.maxK_, this.comparator_);
         return sketch.toByteArray(serDe);
      } else {
         return this.gadget_.toByteArray(serDe);
      }
   }

   static ItemsSketch updateLogic(int myMaxK, Comparator comparator, ItemsSketch myQS, ItemsSketch other) {
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

      ItemsSketch<T> ret = null;
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
               ret = ItemsSketch.getInstance(other.getClassOfT(), myMaxK, comparator);
               int otherCnt = other.getBaseBufferCount();
               Object[] combBuf = other.getCombinedBuffer();

               for(int i = 0; i < otherCnt; ++i) {
                  ret.update(combBuf[i]);
               }
            } else {
               ret = myMaxK < other.getK() ? other.downSample(myMaxK) : ItemsSketch.copy(other);
            }
            break;
         case 3:
            assert other != null;

            assert myQS != null;

            if (!other.isEstimationMode()) {
               ret = myQS;
               int otherCnt = other.getBaseBufferCount();
               Object[] combBuf = other.getCombinedBuffer();

               for(int i = 0; i < otherCnt; ++i) {
                  ret.update(combBuf[i]);
               }
            } else if (myQS.getK() <= other.getK()) {
               ItemsMergeImpl.mergeInto(other, myQS);
               ret = myQS;
            } else {
               ret = ItemsSketch.copy(other);
               ItemsMergeImpl.mergeInto(myQS, ret);
            }
            break;
         case 4:
            assert other != null;

            ret = ItemsSketch.getInstance(other.getClassOfT(), Math.min(myMaxK, other.getK()), comparator);
      }

      return ret;
   }
}
