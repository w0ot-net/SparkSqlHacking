package org.apache.spark.ml.recommendation;

import scala.Predef.;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

public class ALS$UncompressedInBlockBuilder$mcI$sp extends ALS.UncompressedInBlockBuilder {
   public ALS$UncompressedInBlockBuilder$mcI$sp add(final int dstBlockId, final int[] srcIds, final int[] dstLocalIndices, final float[] ratings) {
      return this.add$mcI$sp(dstBlockId, srcIds, dstLocalIndices, ratings);
   }

   public ALS$UncompressedInBlockBuilder$mcI$sp add$mcI$sp(final int dstBlockId, final int[] srcIds, final int[] dstLocalIndices, final float[] ratings) {
      int sz = srcIds.length;
      .MODULE$.require(dstLocalIndices.length == sz);
      .MODULE$.require(ratings.length == sz);
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockBuilder$$srcIds().$plus$plus$eq(.MODULE$.genericWrapArray(srcIds));
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockBuilder$$ratings().$plus$plus$eq(.MODULE$.wrapFloatArray(ratings));

      for(int j = 0; j < sz; ++j) {
         this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockBuilder$$dstEncodedIndices().$plus$eq(BoxesRunTime.boxToInteger(this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockBuilder$$encoder.encode(dstBlockId, dstLocalIndices[j])));
      }

      return this;
   }

   public ALS.UncompressedInBlock build() {
      return this.build$mcI$sp();
   }

   public ALS.UncompressedInBlock build$mcI$sp() {
      return new ALS$UncompressedInBlock$mcI$sp((int[])this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockBuilder$$srcIds().result(), (int[])this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockBuilder$$dstEncodedIndices().result(), (float[])this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockBuilder$$ratings().result(), this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockBuilder$$evidence$6, this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlockBuilder$$ord);
   }

   public ALS$UncompressedInBlockBuilder$mcI$sp(final ALS.LocalIndexEncoder encoder, final ClassTag evidence$6, final Ordering ord) {
      super(encoder, evidence$6, ord);
      Statics.releaseFence();
   }
}
