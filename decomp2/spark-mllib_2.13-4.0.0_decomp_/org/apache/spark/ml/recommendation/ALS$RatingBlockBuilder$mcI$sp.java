package org.apache.spark.ml.recommendation;

import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

public class ALS$RatingBlockBuilder$mcI$sp extends ALS.RatingBlockBuilder {
   public ALS$RatingBlockBuilder$mcI$sp add(final ALS.Rating r) {
      return this.add$mcI$sp(r);
   }

   public ALS$RatingBlockBuilder$mcI$sp add$mcI$sp(final ALS.Rating r) {
      this.size_$eq(this.size() + 1);
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$srcIds().$plus$eq(BoxesRunTime.boxToInteger(r.user$mcI$sp()));
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$dstIds().$plus$eq(BoxesRunTime.boxToInteger(r.item$mcI$sp()));
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$ratings().$plus$eq(BoxesRunTime.boxToFloat(r.rating()));
      return this;
   }

   public ALS$RatingBlockBuilder$mcI$sp merge(final ALS.RatingBlock other) {
      return this.merge$mcI$sp(other);
   }

   public ALS$RatingBlockBuilder$mcI$sp merge$mcI$sp(final ALS.RatingBlock other) {
      this.size_$eq(this.size() + other.srcIds$mcI$sp().length);
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$srcIds().$plus$plus$eq(.MODULE$.genericWrapArray(other.srcIds$mcI$sp()));
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$dstIds().$plus$plus$eq(.MODULE$.genericWrapArray(other.dstIds$mcI$sp()));
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$ratings().$plus$plus$eq(.MODULE$.wrapFloatArray(other.ratings()));
      return this;
   }

   public ALS.RatingBlock build() {
      return this.build$mcI$sp();
   }

   public ALS.RatingBlock build$mcI$sp() {
      return new ALS$RatingBlock$mcI$sp((int[])this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$srcIds().result(), (int[])this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$dstIds().result(), (float[])this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$ratings().result(), this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$evidence$4);
   }

   public ALS$RatingBlockBuilder$mcI$sp(final ClassTag evidence$4) {
      super(evidence$4);
      Statics.releaseFence();
   }
}
