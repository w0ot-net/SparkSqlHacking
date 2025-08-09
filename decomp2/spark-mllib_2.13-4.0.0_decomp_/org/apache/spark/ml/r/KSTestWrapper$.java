package org.apache.spark.ml.r;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.stat.Statistics$;
import org.apache.spark.mllib.stat.test.KolmogorovSmirnovTestResult;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import scala.MatchError;
import scala.Some;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.runtime.BoxesRunTime;

public final class KSTestWrapper$ {
   public static final KSTestWrapper$ MODULE$ = new KSTestWrapper$();

   public KSTestWrapper test(final Dataset data, final String featureName, final String distName, final double[] distParams) {
      RDD rddData = data.select(featureName, .MODULE$).rdd().map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$test$1(x0$1)), scala.reflect.ClassTag..MODULE$.Double());
      KolmogorovSmirnovTestResult ksTestResult = Statistics$.MODULE$.kolmogorovSmirnovTest((RDD)rddData, distName, (Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(distParams).toImmutableArraySeq());
      return new KSTestWrapper(ksTestResult, distName, distParams);
   }

   // $FF: synthetic method
   public static final double $anonfun$test$1(final Row x0$1) {
      if (x0$1 != null) {
         Some var4 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
         if (!var4.isEmpty() && var4.get() != null && ((SeqOps)var4.get()).lengthCompare(1) == 0) {
            Object feature = ((SeqOps)var4.get()).apply(0);
            if (feature instanceof Double) {
               double var6 = BoxesRunTime.unboxToDouble(feature);
               return var6;
            }
         }
      }

      throw new MatchError(x0$1);
   }

   private KSTestWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
