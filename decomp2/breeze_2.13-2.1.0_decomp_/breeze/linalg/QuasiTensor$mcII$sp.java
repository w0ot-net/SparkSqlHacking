package breeze.linalg;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Tuple2;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.BoxesRunTime;

public interface QuasiTensor$mcII$sp extends QuasiTensor {
   // $FF: synthetic method
   static IndexedSeq findAll$(final QuasiTensor$mcII$sp $this, final Function1 f) {
      return $this.findAll(f);
   }

   default IndexedSeq findAll(final Function1 f) {
      return this.findAll$mcI$sp(f);
   }

   // $FF: synthetic method
   static IndexedSeq findAll$mcI$sp$(final QuasiTensor$mcII$sp $this, final Function1 f) {
      return $this.findAll$mcI$sp(f);
   }

   default IndexedSeq findAll$mcI$sp(final Function1 f) {
      return this.activeIterator().filter((p) -> BoxesRunTime.boxToBoolean($anonfun$findAll$7(f, p))).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$findAll$8(x$1))).toIndexedSeq();
   }

   // $FF: synthetic method
   static boolean $anonfun$findAll$7(final Function1 f$4, final Tuple2 p) {
      return f$4.apply$mcZI$sp(p._2$mcI$sp());
   }

   // $FF: synthetic method
   static int $anonfun$findAll$8(final Tuple2 x$1) {
      return x$1._1$mcI$sp();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
