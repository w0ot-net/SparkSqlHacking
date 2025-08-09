package breeze.linalg;

import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.runtime.BoxedUnit;

public class SliceMatrixOps$SMOpSetInPlace$mcIIJ$sp extends SliceMatrixOps.SMOpSetInPlace {
   public void apply(final SliceMatrix a, final long b) {
      this.apply$mcIIJ$sp(a, b);
   }

   public void apply$mcIIJ$sp(final SliceMatrix a, final long b) {
      a.keysIterator().foreach((k) -> {
         $anonfun$apply$8(a, b, k);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public SliceMatrixOps breeze$linalg$SliceMatrixOps$SMOpSetInPlace$mcIIJ$sp$$$outer() {
      return this.$outer;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$8(final SliceMatrix a$5, final long b$5, final Tuple2 k) {
      a$5.update$mcJ$sp(k, b$5);
   }

   public SliceMatrixOps$SMOpSetInPlace$mcIIJ$sp(final SliceMatrixOps $outer) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
