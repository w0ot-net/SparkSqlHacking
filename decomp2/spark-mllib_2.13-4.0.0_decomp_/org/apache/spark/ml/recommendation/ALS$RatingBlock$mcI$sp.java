package org.apache.spark.ml.recommendation;

import scala.Predef.;
import scala.reflect.ClassTag;

public class ALS$RatingBlock$mcI$sp extends ALS.RatingBlock {
   public final int[] srcIds$mcI$sp;
   public final int[] dstIds$mcI$sp;

   public int[] srcIds$mcI$sp() {
      return this.srcIds$mcI$sp;
   }

   public int[] srcIds() {
      return this.srcIds$mcI$sp();
   }

   public int[] dstIds$mcI$sp() {
      return this.dstIds$mcI$sp;
   }

   public int[] dstIds() {
      return this.dstIds$mcI$sp();
   }

   public int[] copy$default$1() {
      return this.copy$default$1$mcI$sp();
   }

   public int[] copy$default$1$mcI$sp() {
      return this.srcIds();
   }

   public int[] copy$default$2() {
      return this.copy$default$2$mcI$sp();
   }

   public int[] copy$default$2$mcI$sp() {
      return this.dstIds();
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$RatingBlock$mcI$sp(final int[] srcIds$mcI$sp, final int[] dstIds$mcI$sp, final float[] ratings, final ClassTag evidence$3) {
      super((Object)null, (Object)null, ratings, evidence$3);
      this.srcIds$mcI$sp = srcIds$mcI$sp;
      this.dstIds$mcI$sp = dstIds$mcI$sp;
      .MODULE$.require(scala.runtime.ScalaRunTime..MODULE$.array_length(this.dstIds()) == scala.runtime.ScalaRunTime..MODULE$.array_length(this.srcIds()));
      .MODULE$.require(ratings.length == scala.runtime.ScalaRunTime..MODULE$.array_length(this.srcIds()));
   }
}
