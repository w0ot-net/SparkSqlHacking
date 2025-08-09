package breeze.linalg;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Tuple2;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.BoxesRunTime;

public interface QuasiTensor$mcIJ$sp extends QuasiTensor {
   // $FF: synthetic method
   static IndexedSeq findAll$(final QuasiTensor$mcIJ$sp $this, final Function1 f) {
      return $this.findAll(f);
   }

   default IndexedSeq findAll(final Function1 f) {
      return this.findAll$mcJ$sp(f);
   }

   // $FF: synthetic method
   static IndexedSeq findAll$mcJ$sp$(final QuasiTensor$mcIJ$sp $this, final Function1 f) {
      return $this.findAll$mcJ$sp(f);
   }

   default IndexedSeq findAll$mcJ$sp(final Function1 f) {
      return this.activeIterator().filter((p) -> BoxesRunTime.boxToBoolean($anonfun$findAll$9(f, p))).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$findAll$10(x$1))).toIndexedSeq();
   }

   // $FF: synthetic method
   static boolean $anonfun$findAll$9(final Function1 f$5, final Tuple2 p) {
      return f$5.apply$mcZJ$sp(p._2$mcJ$sp());
   }

   // $FF: synthetic method
   static int $anonfun$findAll$10(final Tuple2 x$1) {
      return x$1._1$mcI$sp();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
