package org.apache.spark.ml.recommendation;

import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

public class ALS$RatingBlockBuilder$mcJ$sp extends ALS.RatingBlockBuilder {
   public ALS$RatingBlockBuilder$mcJ$sp add(final ALS.Rating r) {
      return this.add$mcJ$sp(r);
   }

   public ALS$RatingBlockBuilder$mcJ$sp add$mcJ$sp(final ALS.Rating r) {
      this.size_$eq(this.size() + 1);
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$srcIds().$plus$eq(BoxesRunTime.boxToLong(r.user$mcJ$sp()));
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$dstIds().$plus$eq(BoxesRunTime.boxToLong(r.item$mcJ$sp()));
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$ratings().$plus$eq(BoxesRunTime.boxToFloat(r.rating()));
      return this;
   }

   public ALS$RatingBlockBuilder$mcJ$sp merge(final ALS.RatingBlock other) {
      return this.merge$mcJ$sp(other);
   }

   public ALS$RatingBlockBuilder$mcJ$sp merge$mcJ$sp(final ALS.RatingBlock other) {
      this.size_$eq(this.size() + other.srcIds$mcJ$sp().length);
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$srcIds().$plus$plus$eq(.MODULE$.genericWrapArray(other.srcIds$mcJ$sp()));
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$dstIds().$plus$plus$eq(.MODULE$.genericWrapArray(other.dstIds$mcJ$sp()));
      this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$ratings().$plus$plus$eq(.MODULE$.wrapFloatArray(other.ratings()));
      return this;
   }

   public ALS.RatingBlock build() {
      return this.build$mcJ$sp();
   }

   public ALS.RatingBlock build$mcJ$sp() {
      return new ALS$RatingBlock$mcJ$sp((long[])this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$srcIds().result(), (long[])this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$dstIds().result(), (float[])this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$ratings().result(), this.org$apache$spark$ml$recommendation$ALS$RatingBlockBuilder$$evidence$4);
   }

   public ALS$RatingBlockBuilder$mcJ$sp(final ClassTag evidence$4) {
      super(evidence$4);
      Statics.releaseFence();
   }
}
