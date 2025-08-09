package org.apache.spark.ml.recommendation;

import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.collection.mutable.ArrayBuilder;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class ALS$UncompressedInBlock$mcJ$sp extends ALS.UncompressedInBlock {
   public final long[] srcIds$mcJ$sp;

   public long[] srcIds$mcJ$sp() {
      return this.srcIds$mcJ$sp;
   }

   public long[] srcIds() {
      return this.srcIds$mcJ$sp();
   }

   public ALS.InBlock compress() {
      return this.compress$mcJ$sp();
   }

   public ALS.InBlock compress$mcJ$sp() {
      int sz = this.length();
      .MODULE$.assert(sz > 0, () -> "Empty in-link block should not exist.");
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlock$$sort();
      ArrayBuilder uniqueSrcIdsBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlock$$evidence$7);
      ArrayBuilder dstCountsBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
      long preSrcId = this.srcIds()[0];
      uniqueSrcIdsBuilder.$plus$eq(BoxesRunTime.boxToLong(preSrcId));
      int curCount = 1;

      for(int i = 1; i < sz; ++i) {
         long srcId = this.srcIds()[i];
         if (srcId != preSrcId) {
            uniqueSrcIdsBuilder.$plus$eq(BoxesRunTime.boxToLong(srcId));
            dstCountsBuilder.$plus$eq(BoxesRunTime.boxToInteger(curCount));
            preSrcId = srcId;
            curCount = 0;
         }

         ++curCount;
      }

      dstCountsBuilder.$plus$eq(BoxesRunTime.boxToInteger(curCount));
      long[] uniqueSrcIds = (long[])uniqueSrcIdsBuilder.result();
      int numUniqueSrdIds = uniqueSrcIds.length;
      int[] dstCounts = (int[])dstCountsBuilder.result();
      int[] dstPtrs = new int[numUniqueSrdIds + 1];
      int sum = 0;

      for(int var15 = 0; var15 < numUniqueSrdIds; dstPtrs[var15] = sum) {
         sum += dstCounts[var15];
         ++var15;
      }

      return new ALS$InBlock$mcJ$sp(uniqueSrcIds, dstPtrs, this.dstEncodedIndices(), this.ratings(), this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlock$$evidence$7);
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$UncompressedInBlock$mcJ$sp(final long[] srcIds$mcJ$sp, final int[] dstEncodedIndices, final float[] ratings, final ClassTag evidence$7, final Ordering ord) {
      super((Object)null, dstEncodedIndices, ratings, evidence$7, ord);
      this.srcIds$mcJ$sp = srcIds$mcJ$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
