package org.apache.spark.ml.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.util.Summary;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import scala.MatchError;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001C\u000b\u0017!\u0003\r\tA\u0007\u0011\t\u000bi\u0002A\u0011A\u001e\t\u000b}\u0002a\u0011\u0001!\t\u000bi\u0003a\u0011A.\t\u000b\u0015\u0004a\u0011A.\t\u000b\u001d\u0004a\u0011A.\t\u000f%\u0004!\u0019!C\u0005U\")q\u000f\u0001C\u0001q\"1\u0011\u0011\u0001\u0001\u0005\u0002aDa!!\u0002\u0001\t\u0003A\bBBA\u0005\u0001\u0011\u0005\u0001\u0010\u0003\u0004\u0002\u000e\u0001!\t\u0001\u001f\u0005\b\u0003#\u0001A\u0011AA\n\u0011\u0019\t\t\u0002\u0001C\u0001q\"9\u0011Q\u0004\u0001\u0005\u0002\u0005}\u0001bBA\u0012\u0001\u0011\u0005\u0011q\u0004\u0005\b\u0003O\u0001A\u0011AA\u0010\u0011\u001d\tY\u0003\u0001C\u0001\u0003?Aq!a\f\u0001\t\u0003\ty\u0002C\u0004\u00024\u0001!\t!!\u000e\t\u000f\u0005M\u0002\u0001\"\u0001\u0002 \t)2\t\\1tg&4\u0017nY1uS>t7+^7nCJL(BA\f\u0019\u00039\u0019G.Y:tS\u001aL7-\u0019;j_:T!!\u0007\u000e\u0002\u00055d'BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0014\t\u0001\ts%\f\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005!ZS\"A\u0015\u000b\u0005)B\u0012\u0001B;uS2L!\u0001L\u0015\u0003\u000fM+X.\\1ssB\u0011af\u000e\b\u0003_Ur!\u0001\r\u001b\u000e\u0003ER!AM\u001a\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001J\u0005\u0003m\r\nq\u0001]1dW\u0006<W-\u0003\u00029s\ta1+\u001a:jC2L'0\u00192mK*\u0011agI\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003q\u0002\"AI\u001f\n\u0005y\u001a#\u0001B+oSR\f1\u0002\u001d:fI&\u001cG/[8ogV\t\u0011\t\u0005\u0002C\u001d:\u00111\t\u0014\b\u0003\t*s!!R%\u000f\u0005\u0019CeB\u0001\u0019H\u0013\u0005y\u0012BA\u000f\u001f\u0013\tYB$\u0003\u0002L5\u0005\u00191/\u001d7\n\u0005Yj%BA&\u001b\u0013\ty\u0005KA\u0005ECR\fgI]1nK*\u0011a'\u0014\u0015\u0004\u0005IC\u0006CA*W\u001b\u0005!&BA+\u001b\u0003)\tgN\\8uCRLwN\\\u0005\u0003/R\u0013QaU5oG\u0016\f\u0013!W\u0001\u0006g9\nd\u0006M\u0001\u000eaJ,G-[2uS>t7i\u001c7\u0016\u0003q\u0003\"!X1\u000f\u0005y{\u0006C\u0001\u0019$\u0013\t\u00017%\u0001\u0004Qe\u0016$WMZ\u0005\u0003E\u000e\u0014aa\u0015;sS:<'B\u00011$Q\r\u0019!\u000bW\u0001\tY\u0006\u0014W\r\\\"pY\"\u001aAA\u0015-\u0002\u0013],\u0017n\u001a5u\u0007>d\u0007fA\u0003S1\u0006\tR.\u001e7uS\u000ed\u0017m]:NKR\u0014\u0018nY:\u0016\u0003-\u0004\"\u0001\\9\u000e\u00035T!A\\8\u0002\u0015\u00154\u0018\r\\;bi&|gN\u0003\u0002q5\u0005)Q\u000e\u001c7jE&\u0011!/\u001c\u0002\u0012\u001bVdG/[2mCN\u001cX*\u001a;sS\u000e\u001c\bF\u0001\u0004u!\t\u0011S/\u0003\u0002wG\tIAO]1og&,g\u000e^\u0001\u0007Y\u0006\u0014W\r\\:\u0016\u0003e\u00042A\t>}\u0013\tY8EA\u0003BeJ\f\u0017\u0010\u0005\u0002#{&\u0011ap\t\u0002\u0007\t>,(\r\\3)\u0007\u001d\u0011\u0006,A\fueV,\u0007k\\:ji&4XMU1uK\nKH*\u00192fY\"\u001a\u0001B\u0015-\u00021\u0019\fGn]3Q_NLG/\u001b<f%\u0006$XMQ=MC\n,G\u000eK\u0002\n%b\u000b\u0001\u0003\u001d:fG&\u001c\u0018n\u001c8Cs2\u000b'-\u001a7)\u0007)\u0011\u0006,A\u0007sK\u000e\fG\u000e\u001c\"z\u0019\u0006\u0014W\r\u001c\u0015\u0004\u0017IC\u0016a\u00044NK\u0006\u001cXO]3Cs2\u000b'-\u001a7\u0015\u0007e\f)\u0002\u0003\u0004\u0002\u00181\u0001\r\u0001`\u0001\u0005E\u0016$\u0018\rK\u0002\r%bC3!\u0004*Y\u0003!\t7mY;sC\u000eLX#\u0001?)\u00079\u0011\u0006,\u0001\rxK&<\u0007\u000e^3e)J,X\rU8tSRLg/\u001a*bi\u0016D3a\u0004*Y\u0003e9X-[4ii\u0016$g)\u00197tKB{7/\u001b;jm\u0016\u0014\u0016\r^3)\u0007A\u0011\u0006,\u0001\bxK&<\u0007\u000e^3e%\u0016\u001c\u0017\r\u001c7)\u0007E\u0011\u0006,A\txK&<\u0007\u000e^3e!J,7-[:j_:D3A\u0005*Y\u0003A9X-[4ii\u0016$g)T3bgV\u0014X\rF\u0002}\u0003oAa!a\u0006\u0014\u0001\u0004a\bfA\nS1\"\u001aAC\u0015-"
)
public interface ClassificationSummary extends Summary, Serializable {
   void org$apache$spark$ml$classification$ClassificationSummary$_setter_$org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics_$eq(final MulticlassMetrics x$1);

   Dataset predictions();

   String predictionCol();

   String labelCol();

   String weightCol();

   MulticlassMetrics org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics();

   // $FF: synthetic method
   static double[] labels$(final ClassificationSummary $this) {
      return $this.labels();
   }

   default double[] labels() {
      return this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().labels();
   }

   // $FF: synthetic method
   static double[] truePositiveRateByLabel$(final ClassificationSummary $this) {
      return $this.truePositiveRateByLabel();
   }

   default double[] truePositiveRateByLabel() {
      return this.recallByLabel();
   }

   // $FF: synthetic method
   static double[] falsePositiveRateByLabel$(final ClassificationSummary $this) {
      return $this.falsePositiveRateByLabel();
   }

   default double[] falsePositiveRateByLabel() {
      return (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().labels()), (JFunction1.mcDD.sp)(label) -> this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().falsePositiveRate(label), scala.reflect.ClassTag..MODULE$.Double());
   }

   // $FF: synthetic method
   static double[] precisionByLabel$(final ClassificationSummary $this) {
      return $this.precisionByLabel();
   }

   default double[] precisionByLabel() {
      return (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().labels()), (JFunction1.mcDD.sp)(label) -> this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().precision(label), scala.reflect.ClassTag..MODULE$.Double());
   }

   // $FF: synthetic method
   static double[] recallByLabel$(final ClassificationSummary $this) {
      return $this.recallByLabel();
   }

   default double[] recallByLabel() {
      return (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().labels()), (JFunction1.mcDD.sp)(label) -> this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().recall(label), scala.reflect.ClassTag..MODULE$.Double());
   }

   // $FF: synthetic method
   static double[] fMeasureByLabel$(final ClassificationSummary $this, final double beta) {
      return $this.fMeasureByLabel(beta);
   }

   default double[] fMeasureByLabel(final double beta) {
      return (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().labels()), (JFunction1.mcDD.sp)(label) -> this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().fMeasure(label, beta), scala.reflect.ClassTag..MODULE$.Double());
   }

   // $FF: synthetic method
   static double[] fMeasureByLabel$(final ClassificationSummary $this) {
      return $this.fMeasureByLabel();
   }

   default double[] fMeasureByLabel() {
      return this.fMeasureByLabel((double)1.0F);
   }

   // $FF: synthetic method
   static double accuracy$(final ClassificationSummary $this) {
      return $this.accuracy();
   }

   default double accuracy() {
      return this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().accuracy();
   }

   // $FF: synthetic method
   static double weightedTruePositiveRate$(final ClassificationSummary $this) {
      return $this.weightedTruePositiveRate();
   }

   default double weightedTruePositiveRate() {
      return this.weightedRecall();
   }

   // $FF: synthetic method
   static double weightedFalsePositiveRate$(final ClassificationSummary $this) {
      return $this.weightedFalsePositiveRate();
   }

   default double weightedFalsePositiveRate() {
      return this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().weightedFalsePositiveRate();
   }

   // $FF: synthetic method
   static double weightedRecall$(final ClassificationSummary $this) {
      return $this.weightedRecall();
   }

   default double weightedRecall() {
      return this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().weightedRecall();
   }

   // $FF: synthetic method
   static double weightedPrecision$(final ClassificationSummary $this) {
      return $this.weightedPrecision();
   }

   default double weightedPrecision() {
      return this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().weightedPrecision();
   }

   // $FF: synthetic method
   static double weightedFMeasure$(final ClassificationSummary $this, final double beta) {
      return $this.weightedFMeasure(beta);
   }

   default double weightedFMeasure(final double beta) {
      return this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().weightedFMeasure(beta);
   }

   // $FF: synthetic method
   static double weightedFMeasure$(final ClassificationSummary $this) {
      return $this.weightedFMeasure();
   }

   default double weightedFMeasure() {
      return this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics().weightedFMeasure((double)1.0F);
   }

   static void $init$(final ClassificationSummary $this) {
      Column weightColumn = .MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])$this.predictions().schema().fieldNames()), $this.weightCol()) ? org.apache.spark.sql.functions..MODULE$.col($this.weightCol()).cast(org.apache.spark.sql.types.DoubleType..MODULE$) : org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F));
      $this.org$apache$spark$ml$classification$ClassificationSummary$_setter_$org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics_$eq(new MulticlassMetrics($this.predictions().select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col($this.predictionCol()), org.apache.spark.sql.functions..MODULE$.col($this.labelCol()).cast(org.apache.spark.sql.types.DoubleType..MODULE$), weightColumn}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
               Object prediction = ((SeqOps)var3.get()).apply(0);
               Object label = ((SeqOps)var3.get()).apply(1);
               Object weight = ((SeqOps)var3.get()).apply(2);
               if (prediction instanceof Double) {
                  double var7 = BoxesRunTime.unboxToDouble(prediction);
                  if (label instanceof Double) {
                     double var9 = BoxesRunTime.unboxToDouble(label);
                     if (weight instanceof Double) {
                        double var11 = BoxesRunTime.unboxToDouble(weight);
                        return new Tuple3(BoxesRunTime.boxToDouble(var7), BoxesRunTime.boxToDouble(var9), BoxesRunTime.boxToDouble(var11));
                     }
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Product.class))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
