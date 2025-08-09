package breeze.linalg;

import breeze.generic.UFunc$InPlaceImpl2$mcF$sp;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.runtime.BoxedUnit;

public class SliceMatrixOps$SMOpSetInPlace$mcIIF$sp extends SliceMatrixOps.SMOpSetInPlace implements UFunc$InPlaceImpl2$mcF$sp {
   public void apply(final SliceMatrix a, final float b) {
      this.apply$mcIIF$sp(a, b);
   }

   public void apply$mcIIF$sp(final SliceMatrix a, final float b) {
      a.keysIterator().foreach((k) -> {
         $anonfun$apply$6(a, b, k);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public SliceMatrixOps breeze$linalg$SliceMatrixOps$SMOpSetInPlace$mcIIF$sp$$$outer() {
      return this.$outer;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$6(final SliceMatrix a$3, final float b$3, final Tuple2 k) {
      a$3.update$mcF$sp(k, b$3);
   }

   public SliceMatrixOps$SMOpSetInPlace$mcIIF$sp(final SliceMatrixOps $outer) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
