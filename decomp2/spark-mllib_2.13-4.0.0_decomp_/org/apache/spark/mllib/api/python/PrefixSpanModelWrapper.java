package org.apache.spark.mllib.api.python;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.fpm.PrefixSpanModel;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A2Q\u0001B\u0003\u0001\u000bEA\u0001B\b\u0001\u0003\u0002\u0003\u0006IA\u0005\u0005\u0006A\u0001!\t!\t\u0005\u0006K\u0001!\tA\n\u0002\u0017!J,g-\u001b=Ta\u0006tWj\u001c3fY^\u0013\u0018\r\u001d9fe*\u0011aaB\u0001\u0007af$\bn\u001c8\u000b\u0005!I\u0011aA1qS*\u0011!bC\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0011\u0001A\u0005\t\u0004'YAR\"\u0001\u000b\u000b\u0005UI\u0011a\u00014q[&\u0011q\u0003\u0006\u0002\u0010!J,g-\u001b=Ta\u0006tWj\u001c3fYB\u0011\u0011\u0004H\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\t\u0019\u0011I\\=\u0002\u000b5|G-\u001a7\u0004\u0001\u00051A(\u001b8jiz\"\"A\t\u0013\u0011\u0005\r\u0002Q\"A\u0003\t\u000by\u0011\u0001\u0019\u0001\n\u0002!\u001d,GO\u0012:fcN+\u0017/^3oG\u0016\u001cX#A\u0014\u0011\u0007!ZS&D\u0001*\u0015\tQ3\"A\u0002sI\u0012L!\u0001L\u0015\u0003\u0007I#E\tE\u0002\u001a]aI!a\f\u000e\u0003\u000b\u0005\u0013(/Y="
)
public class PrefixSpanModelWrapper extends PrefixSpanModel {
   private final PrefixSpanModel model;

   public RDD getFreqSequences() {
      return SerDe$.MODULE$.fromTuple2RDD(this.model.freqSequences().map((x) -> new Tuple2(x.javaSequence(), BoxesRunTime.boxToLong(x.freq())), .MODULE$.apply(Tuple2.class)));
   }

   public PrefixSpanModelWrapper(final PrefixSpanModel model) {
      super(model.freqSequences());
      this.model = model;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
