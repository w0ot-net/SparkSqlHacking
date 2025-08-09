package org.apache.spark.ml.recommendation;

import scala.Predef.;
import scala.reflect.ClassTag;

public class ALS$InBlock$mcI$sp extends ALS.InBlock {
   public final int[] srcIds$mcI$sp;

   public int[] srcIds$mcI$sp() {
      return this.srcIds$mcI$sp;
   }

   public int[] srcIds() {
      return this.srcIds$mcI$sp();
   }

   public int[] copy$default$1() {
      return this.copy$default$1$mcI$sp();
   }

   public int[] copy$default$1$mcI$sp() {
      return this.srcIds();
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$InBlock$mcI$sp(final int[] srcIds$mcI$sp, final int[] dstPtrs, final int[] dstEncodedIndices, final float[] ratings, final ClassTag evidence$2) {
      super((Object)null, dstPtrs, dstEncodedIndices, ratings, evidence$2);
      this.srcIds$mcI$sp = srcIds$mcI$sp;
      .MODULE$.require(dstEncodedIndices.length == this.size());
      .MODULE$.require(dstPtrs.length == scala.runtime.ScalaRunTime..MODULE$.array_length(this.srcIds()) + 1);
   }
}
