package org.apache.spark.ml.recommendation;

import scala.math.Ordering;
import scala.reflect.ClassTag;

public class ALS$UncompressedInBlockSort$mcI$sp extends ALS.UncompressedInBlockSort {
   public ALS.KeyWrapper newKey() {
      return this.newKey$mcI$sp();
   }

   public ALS.KeyWrapper newKey$mcI$sp() {
      return new ALS$KeyWrapper$mcI$sp(this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$evidence$9, this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$ord);
   }

   public ALS.KeyWrapper getKey(final ALS.UncompressedInBlock data, final int pos, final ALS.KeyWrapper reuse) {
      return this.getKey$mcI$sp(data, pos, reuse);
   }

   public ALS.KeyWrapper getKey$mcI$sp(final ALS.UncompressedInBlock data, final int pos, final ALS.KeyWrapper reuse) {
      return reuse == null ? (new ALS$KeyWrapper$mcI$sp(this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$evidence$9, this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$ord)).setKey$mcI$sp(data.srcIds$mcI$sp()[pos]) : reuse.setKey$mcI$sp(data.srcIds$mcI$sp()[pos]);
   }

   public ALS.KeyWrapper getKey(final ALS.UncompressedInBlock data, final int pos) {
      return this.getKey$mcI$sp(data, pos);
   }

   public ALS.KeyWrapper getKey$mcI$sp(final ALS.UncompressedInBlock data, final int pos) {
      return this.getKey$mcI$sp(data, pos, (ALS.KeyWrapper)null);
   }

   public void swap(final ALS.UncompressedInBlock data, final int pos0, final int pos1) {
      this.swap$mcI$sp(data, pos0, pos1);
   }

   public void swap$mcI$sp(final ALS.UncompressedInBlock data, final int pos0, final int pos1) {
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$swapElements(data.srcIds$mcI$sp(), pos0, pos1);
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$swapElements(data.dstEncodedIndices(), pos0, pos1);
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$swapElements(data.ratings(), pos0, pos1);
   }

   public void copyRange(final ALS.UncompressedInBlock src, final int srcPos, final ALS.UncompressedInBlock dst, final int dstPos, final int length) {
      this.copyRange$mcI$sp(src, srcPos, dst, dstPos, length);
   }

   public void copyRange$mcI$sp(final ALS.UncompressedInBlock src, final int srcPos, final ALS.UncompressedInBlock dst, final int dstPos, final int length) {
      System.arraycopy(src.srcIds$mcI$sp(), srcPos, dst.srcIds$mcI$sp(), dstPos, length);
      System.arraycopy(src.dstEncodedIndices(), srcPos, dst.dstEncodedIndices(), dstPos, length);
      System.arraycopy(src.ratings(), srcPos, dst.ratings(), dstPos, length);
   }

   public ALS.UncompressedInBlock allocate(final int length) {
      return this.allocate$mcI$sp(length);
   }

   public ALS.UncompressedInBlock allocate$mcI$sp(final int length) {
      return new ALS$UncompressedInBlock$mcI$sp((int[])this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$evidence$9.newArray(length), new int[length], new float[length], this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$evidence$9, this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockSort$$ord);
   }

   public void copyElement(final ALS.UncompressedInBlock src, final int srcPos, final ALS.UncompressedInBlock dst, final int dstPos) {
      this.copyElement$mcI$sp(src, srcPos, dst, dstPos);
   }

   public void copyElement$mcI$sp(final ALS.UncompressedInBlock src, final int srcPos, final ALS.UncompressedInBlock dst, final int dstPos) {
      dst.srcIds$mcI$sp()[dstPos] = src.srcIds$mcI$sp()[srcPos];
      dst.dstEncodedIndices()[dstPos] = src.dstEncodedIndices()[srcPos];
      dst.ratings()[dstPos] = src.ratings()[srcPos];
   }

   public ALS$UncompressedInBlockSort$mcI$sp(final ClassTag evidence$9, final Ordering ord) {
      super(evidence$9, ord);
   }
}
