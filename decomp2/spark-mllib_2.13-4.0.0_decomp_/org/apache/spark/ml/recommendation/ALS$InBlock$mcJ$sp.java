package org.apache.spark.ml.recommendation;

import scala.Predef.;
import scala.reflect.ClassTag;

public class ALS$InBlock$mcJ$sp extends ALS.InBlock {
   public final long[] srcIds$mcJ$sp;

   public long[] srcIds$mcJ$sp() {
      return this.srcIds$mcJ$sp;
   }

   public long[] srcIds() {
      return this.srcIds$mcJ$sp();
   }

   public long[] copy$default$1() {
      return this.copy$default$1$mcJ$sp();
   }

   public long[] copy$default$1$mcJ$sp() {
      return this.srcIds();
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$InBlock$mcJ$sp(final long[] srcIds$mcJ$sp, final int[] dstPtrs, final int[] dstEncodedIndices, final float[] ratings, final ClassTag evidence$2) {
      super((Object)null, dstPtrs, dstEncodedIndices, ratings, evidence$2);
      this.srcIds$mcJ$sp = srcIds$mcJ$sp;
      .MODULE$.require(dstEncodedIndices.length == this.size());
      .MODULE$.require(dstPtrs.length == scala.runtime.ScalaRunTime..MODULE$.array_length(this.srcIds()) + 1);
   }
}
