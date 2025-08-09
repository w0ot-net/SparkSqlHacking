package org.apache.spark.ml.recommendation;

import scala.Predef.;
import scala.reflect.ClassTag;

public class ALS$RatingBlock$mcJ$sp extends ALS.RatingBlock {
   public final long[] srcIds$mcJ$sp;
   public final long[] dstIds$mcJ$sp;

   public long[] srcIds$mcJ$sp() {
      return this.srcIds$mcJ$sp;
   }

   public long[] srcIds() {
      return this.srcIds$mcJ$sp();
   }

   public long[] dstIds$mcJ$sp() {
      return this.dstIds$mcJ$sp;
   }

   public long[] dstIds() {
      return this.dstIds$mcJ$sp();
   }

   public long[] copy$default$1() {
      return this.copy$default$1$mcJ$sp();
   }

   public long[] copy$default$1$mcJ$sp() {
      return this.srcIds();
   }

   public long[] copy$default$2() {
      return this.copy$default$2$mcJ$sp();
   }

   public long[] copy$default$2$mcJ$sp() {
      return this.dstIds();
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$RatingBlock$mcJ$sp(final long[] srcIds$mcJ$sp, final long[] dstIds$mcJ$sp, final float[] ratings, final ClassTag evidence$3) {
      super((Object)null, (Object)null, ratings, evidence$3);
      this.srcIds$mcJ$sp = srcIds$mcJ$sp;
      this.dstIds$mcJ$sp = dstIds$mcJ$sp;
      .MODULE$.require(scala.runtime.ScalaRunTime..MODULE$.array_length(this.dstIds()) == scala.runtime.ScalaRunTime..MODULE$.array_length(this.srcIds()));
      .MODULE$.require(ratings.length == scala.runtime.ScalaRunTime..MODULE$.array_length(this.srcIds()));
   }
}
