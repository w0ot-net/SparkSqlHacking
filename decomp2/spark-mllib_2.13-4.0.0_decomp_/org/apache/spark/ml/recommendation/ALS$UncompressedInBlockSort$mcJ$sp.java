package org.apache.spark.ml.recommendation;

import scala.math.Ordering;
import scala.reflect.ClassTag;

public class ALS$UncompressedInBlockSort$mcJ$sp extends ALS.UncompressedInBlockSort {
   public ALS.KeyWrapper newKey() {
      return this.newKey$mcJ$sp();
   }

   public ALS.KeyWrapper newKey$mcJ$sp() {
      return new ALS$KeyWrapper$mcJ$sp(this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$evidence$9, this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$ord);
   }

   public ALS.KeyWrapper getKey(final ALS.UncompressedInBlock data, final int pos, final ALS.KeyWrapper reuse) {
      return this.getKey$mcJ$sp(data, pos, reuse);
   }

   public ALS.KeyWrapper getKey$mcJ$sp(final ALS.UncompressedInBlock data, final int pos, final ALS.KeyWrapper reuse) {
      return reuse == null ? (new ALS$KeyWrapper$mcJ$sp(this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$evidence$9, this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$ord)).setKey$mcJ$sp(data.srcIds$mcJ$sp()[pos]) : reuse.setKey$mcJ$sp(data.srcIds$mcJ$sp()[pos]);
   }

   public ALS.KeyWrapper getKey(final ALS.UncompressedInBlock data, final int pos) {
      return this.getKey$mcJ$sp(data, pos);
   }

   public ALS.KeyWrapper getKey$mcJ$sp(final ALS.UncompressedInBlock data, final int pos) {
      return this.getKey$mcJ$sp(data, pos, (ALS.KeyWrapper)null);
   }

   public void swap(final ALS.UncompressedInBlock data, final int pos0, final int pos1) {
      this.swap$mcJ$sp(data, pos0, pos1);
   }

   public void swap$mcJ$sp(final ALS.UncompressedInBlock data, final int pos0, final int pos1) {
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$swapElements(data.srcIds$mcJ$sp(), pos0, pos1);
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$swapElements(data.dstEncodedIndices(), pos0, pos1);
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$swapElements(data.ratings(), pos0, pos1);
   }

   public void copyRange(final ALS.UncompressedInBlock src, final int srcPos, final ALS.UncompressedInBlock dst, final int dstPos, final int length) {
      this.copyRange$mcJ$sp(src, srcPos, dst, dstPos, length);
   }

   public void copyRange$mcJ$sp(final ALS.UncompressedInBlock src, final int srcPos, final ALS.UncompressedInBlock dst, final int dstPos, final int length) {
      System.arraycopy(src.srcIds$mcJ$sp(), srcPos, dst.srcIds$mcJ$sp(), dstPos, length);
      System.arraycopy(src.dstEncodedIndices(), srcPos, dst.dstEncodedIndices(), dstPos, length);
      System.arraycopy(src.ratings(), srcPos, dst.ratings(), dstPos, length);
   }

   public ALS.UncompressedInBlock allocate(final int length) {
      return this.allocate$mcJ$sp(length);
   }

   public ALS.UncompressedInBlock allocate$mcJ$sp(final int length) {
      return new ALS$UncompressedInBlock$mcJ$sp((long[])this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$evidence$9.newArray(length), new int[length], new float[length], this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$evidence$9, this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$ord);
   }

   public void copyElement(final ALS.UncompressedInBlock src, final int srcPos, final ALS.UncompressedInBlock dst, final int dstPos) {
      this.copyElement$mcJ$sp(src, srcPos, dst, dstPos);
   }

   public void copyElement$mcJ$sp(final ALS.UncompressedInBlock src, final int srcPos, final ALS.UncompressedInBlock dst, final int dstPos) {
      dst.srcIds$mcJ$sp()[dstPos] = src.srcIds$mcJ$sp()[srcPos];
      dst.dstEncodedIndices()[dstPos] = src.dstEncodedIndices()[srcPos];
      dst.ratings()[dstPos] = src.ratings()[srcPos];
   }

   public ALS$UncompressedInBlockSort$mcJ$sp(final ClassTag evidence$9, final Ordering ord) {
      super(evidence$9, ord);
   }
}
