package breeze.linalg;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Tuple2;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.BoxesRunTime;

public interface QuasiTensor$mcIF$sp extends QuasiTensor {
   // $FF: synthetic method
   static IndexedSeq findAll$(final QuasiTensor$mcIF$sp $this, final Function1 f) {
      return $this.findAll(f);
   }

   default IndexedSeq findAll(final Function1 f) {
      return this.findAll$mcF$sp(f);
   }

   // $FF: synthetic method
   static IndexedSeq findAll$mcF$sp$(final QuasiTensor$mcIF$sp $this, final Function1 f) {
      return $this.findAll$mcF$sp(f);
   }

   default IndexedSeq findAll$mcF$sp(final Function1 f) {
      return this.activeIterator().filter((p) -> BoxesRunTime.boxToBoolean($anonfun$findAll$5(f, p))).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$findAll$6(x$1))).toIndexedSeq();
   }

   // $FF: synthetic method
   static boolean $anonfun$findAll$5(final Function1 f$3, final Tuple2 p) {
      return f$3.apply$mcZF$sp(BoxesRunTime.unboxToFloat(p._2()));
   }

   // $FF: synthetic method
   static int $anonfun$findAll$6(final Tuple2 x$1) {
      return x$1._1$mcI$sp();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
