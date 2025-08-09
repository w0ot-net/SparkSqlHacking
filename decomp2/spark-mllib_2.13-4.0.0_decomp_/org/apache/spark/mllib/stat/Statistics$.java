package org.apache.spark.mllib.stat;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.stat.Summarizer$;
import org.apache.spark.ml.stat.SummarizerBuffer;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.mllib.stat.correlation.Correlations$;
import org.apache.spark.mllib.stat.test.ChiSqTest$;
import org.apache.spark.mllib.stat.test.ChiSqTestResult;
import org.apache.spark.mllib.stat.test.KolmogorovSmirnovTest$;
import org.apache.spark.mllib.stat.test.KolmogorovSmirnovTestResult;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.runtime.ScalaRunTime.;

public final class Statistics$ {
   public static final Statistics$ MODULE$ = new Statistics$();

   public KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final RDD data, final String distName, final double... params) {
      return this.kolmogorovSmirnovTest((RDD)data, distName, (Seq).MODULE$.wrapDoubleArray(params));
   }

   public KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final JavaDoubleRDD data, final String distName, final double... params) {
      return this.kolmogorovSmirnovTest((JavaDoubleRDD)data, distName, (Seq).MODULE$.wrapDoubleArray(params));
   }

   public MultivariateStatisticalSummary colStats(final RDD X) {
      return (new RowMatrix(X)).computeColumnSummaryStatistics();
   }

   public SummarizerBuffer colStats(final RDD X, final Seq requested) {
      return (SummarizerBuffer)X.treeAggregate(Summarizer$.MODULE$.createSummarizerBuffer(requested), (x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            SummarizerBuffer c = (SummarizerBuffer)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var5 != null) {
               Vector v = (Vector)var5._1();
               double w = var5._2$mcD$sp();
               return c.add(v.nonZeroIterator(), v.size(), w);
            }
         }

         throw new MatchError(var3);
      }, (x0$2, x1$2) -> {
         Tuple2 var3 = new Tuple2(x0$2, x1$2);
         if (var3 != null) {
            SummarizerBuffer c1 = (SummarizerBuffer)var3._1();
            SummarizerBuffer c2 = (SummarizerBuffer)var3._2();
            return c1.merge(c2);
         } else {
            throw new MatchError(var3);
         }
      }, 2, scala.reflect.ClassTag..MODULE$.apply(SummarizerBuffer.class));
   }

   public Matrix corr(final RDD X) {
      return Correlations$.MODULE$.corrMatrix(X, Correlations$.MODULE$.corrMatrix$default$2());
   }

   public Matrix corr(final RDD X, final String method) {
      return Correlations$.MODULE$.corrMatrix(X, method);
   }

   public double corr(final RDD x, final RDD y) {
      return Correlations$.MODULE$.corr(x, y, Correlations$.MODULE$.corr$default$3());
   }

   public double corr(final JavaRDD x, final JavaRDD y) {
      return this.corr(x.rdd(), y.rdd());
   }

   public double corr(final RDD x, final RDD y, final String method) {
      return Correlations$.MODULE$.corr(x, y, method);
   }

   public double corr(final JavaRDD x, final JavaRDD y, final String method) {
      return this.corr(x.rdd(), y.rdd(), method);
   }

   public ChiSqTestResult chiSqTest(final Vector observed, final Vector expected) {
      return ChiSqTest$.MODULE$.chiSquared(observed, expected, ChiSqTest$.MODULE$.chiSquared$default$3());
   }

   public ChiSqTestResult chiSqTest(final Vector observed) {
      return ChiSqTest$.MODULE$.chiSquared(observed, ChiSqTest$.MODULE$.chiSquared$default$2(), ChiSqTest$.MODULE$.chiSquared$default$3());
   }

   public ChiSqTestResult chiSqTest(final Matrix observed) {
      return ChiSqTest$.MODULE$.chiSquaredMatrix(observed, ChiSqTest$.MODULE$.chiSquaredMatrix$default$2());
   }

   public ChiSqTestResult[] chiSqTest(final RDD data) {
      return ChiSqTest$.MODULE$.chiSquaredFeatures(data, ChiSqTest$.MODULE$.chiSquaredFeatures$default$2());
   }

   public ChiSqTestResult[] chiSqTest(final JavaRDD data) {
      return this.chiSqTest(data.rdd());
   }

   public KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final RDD data, final Function1 cdf) {
      return KolmogorovSmirnovTest$.MODULE$.testOneSample(data, cdf);
   }

   public KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final RDD data, final String distName, final Seq params) {
      return KolmogorovSmirnovTest$.MODULE$.testOneSample(data, distName, params);
   }

   public KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final JavaDoubleRDD data, final String distName, final Seq params) {
      return this.kolmogorovSmirnovTest(data.rdd(), distName, params);
   }

   private Statistics$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
