package breeze.linalg;

import breeze.generic.UFunc$InPlaceImpl2$mcD$sp;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.runtime.BoxedUnit;

public class SliceMatrixOps$SMOpSetInPlace$mcIID$sp extends SliceMatrixOps.SMOpSetInPlace implements UFunc$InPlaceImpl2$mcD$sp {
   public void apply(final SliceMatrix a, final double b) {
      this.apply$mcIID$sp(a, b);
   }

   public void apply$mcIID$sp(final SliceMatrix a, final double b) {
      a.keysIterator().foreach((k) -> {
         $anonfun$apply$5(a, b, k);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public SliceMatrixOps breeze$linalg$SliceMatrixOps$SMOpSetInPlace$mcIID$sp$$$outer() {
      return this.$outer;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$5(final SliceMatrix a$2, final double b$2, final Tuple2 k) {
      a$2.update$mcD$sp(k, b$2);
   }

   public SliceMatrixOps$SMOpSetInPlace$mcIID$sp(final SliceMatrixOps $outer) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
