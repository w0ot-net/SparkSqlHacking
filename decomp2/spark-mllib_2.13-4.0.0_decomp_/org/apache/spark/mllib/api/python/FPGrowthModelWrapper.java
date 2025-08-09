package org.apache.spark.mllib.api.python;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.fpm.FPGrowthModel;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A2Q\u0001B\u0003\u0001\u000bEA\u0001B\b\u0001\u0003\u0002\u0003\u0006IA\u0005\u0005\u0006A\u0001!\t!\t\u0005\u0006K\u0001!\tA\n\u0002\u0015\rB;%o\\<uQ6{G-\u001a7Xe\u0006\u0004\b/\u001a:\u000b\u0005\u00199\u0011A\u00029zi\"|gN\u0003\u0002\t\u0013\u0005\u0019\u0011\r]5\u000b\u0005)Y\u0011!B7mY&\u0014'B\u0001\u0007\u000e\u0003\u0015\u0019\b/\u0019:l\u0015\tqq\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002!\u0005\u0019qN]4\u0014\u0005\u0001\u0011\u0002cA\n\u001715\tAC\u0003\u0002\u0016\u0013\u0005\u0019a\r]7\n\u0005]!\"!\u0004$Q\u000fJ|w\u000f\u001e5N_\u0012,G\u000e\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"DA\u0002B]f\fQ!\\8eK2\u001c\u0001!\u0001\u0004=S:LGO\u0010\u000b\u0003E\u0011\u0002\"a\t\u0001\u000e\u0003\u0015AQA\b\u0002A\u0002I\tqbZ3u\rJ,\u0017/\u0013;f[N,Go]\u000b\u0002OA\u0019\u0001fK\u0017\u000e\u0003%R!AK\u0006\u0002\u0007I$G-\u0003\u0002-S\t\u0019!\u000b\u0012#\u0011\u0007eq\u0003$\u0003\u000205\t)\u0011I\u001d:bs\u0002"
)
public class FPGrowthModelWrapper extends FPGrowthModel {
   private final FPGrowthModel model;

   public RDD getFreqItemsets() {
      return SerDe$.MODULE$.fromTuple2RDD(this.model.freqItemsets().map((x) -> new Tuple2(x.javaItems(), BoxesRunTime.boxToLong(x.freq())), .MODULE$.apply(Tuple2.class)));
   }

   public FPGrowthModelWrapper(final FPGrowthModel model) {
      super(model.freqItemsets(), .MODULE$.Any());
      this.model = model;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
