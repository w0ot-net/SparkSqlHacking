package breeze.linalg;

import breeze.generic.UFunc$InPlaceImpl2$mcI$sp;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.runtime.BoxedUnit;

public class SliceMatrixOps$SMOpSetInPlace$mcIII$sp extends SliceMatrixOps.SMOpSetInPlace implements UFunc$InPlaceImpl2$mcI$sp {
   public void apply(final SliceMatrix a, final int b) {
      this.apply$mcIII$sp(a, b);
   }

   public void apply$mcIII$sp(final SliceMatrix a, final int b) {
      a.keysIterator().foreach((k) -> {
         $anonfun$apply$7(a, b, k);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public SliceMatrixOps breeze$linalg$SliceMatrixOps$SMOpSetInPlace$mcIII$sp$$$outer() {
      return this.$outer;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$7(final SliceMatrix a$4, final int b$4, final Tuple2 k) {
      a$4.update$mcI$sp(k, b$4);
   }

   public SliceMatrixOps$SMOpSetInPlace$mcIII$sp(final SliceMatrixOps $outer) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
