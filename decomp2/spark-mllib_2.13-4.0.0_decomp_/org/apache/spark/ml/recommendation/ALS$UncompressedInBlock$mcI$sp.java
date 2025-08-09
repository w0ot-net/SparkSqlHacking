package org.apache.spark.ml.recommendation;

import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.collection.mutable.ArrayBuilder;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class ALS$UncompressedInBlock$mcI$sp extends ALS.UncompressedInBlock {
   public final int[] srcIds$mcI$sp;

   public int[] srcIds$mcI$sp() {
      return this.srcIds$mcI$sp;
   }

   public int[] srcIds() {
      return this.srcIds$mcI$sp();
   }

   public ALS.InBlock compress() {
      return this.compress$mcI$sp();
   }

   public ALS.InBlock compress$mcI$sp() {
      int sz = this.length();
      .MODULE$.assert(sz > 0, () -> "Empty in-link block should not exist.");
      this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlock$$sort();
      ArrayBuilder uniqueSrcIdsBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlock$$evidence$7);
      ArrayBuilder dstCountsBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
      int preSrcId = this.srcIds()[0];
      uniqueSrcIdsBuilder.$plus$eq(BoxesRunTime.boxToInteger(preSrcId));
      int curCount = 1;

      for(int i = 1; i < sz; ++i) {
         int srcId = this.srcIds()[i];
         if (srcId != preSrcId) {
            uniqueSrcIdsBuilder.$plus$eq(BoxesRunTime.boxToInteger(srcId));
            dstCountsBuilder.$plus$eq(BoxesRunTime.boxToInteger(curCount));
            preSrcId = srcId;
            curCount = 0;
         }

         ++curCount;
      }

      dstCountsBuilder.$plus$eq(BoxesRunTime.boxToInteger(curCount));
      int[] uniqueSrcIds = (int[])uniqueSrcIdsBuilder.result();
      int numUniqueSrdIds = uniqueSrcIds.length;
      int[] dstCounts = (int[])dstCountsBuilder.result();
      int[] dstPtrs = new int[numUniqueSrdIds + 1];
      int sum = 0;

      for(int var13 = 0; var13 < numUniqueSrdIds; dstPtrs[var13] = sum) {
         sum += dstCounts[var13];
         ++var13;
      }

      return new ALS$InBlock$mcI$sp(uniqueSrcIds, dstPtrs, this.dstEncodedIndices(), this.ratings(), this.org$apache$spark$ml$recommendation$ALS$UncompressedInBlock$$evidence$7);
   }

   public boolean specInstance$() {
      return true;
   }

   public ALS$UncompressedInBlock$mcI$sp(final int[] srcIds$mcI$sp, final int[] dstEncodedIndices, final float[] ratings, final ClassTag evidence$7, final Ordering ord) {
      super((Object)null, dstEncodedIndices, ratings, evidence$7, ord);
      this.srcIds$mcI$sp = srcIds$mcI$sp;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
