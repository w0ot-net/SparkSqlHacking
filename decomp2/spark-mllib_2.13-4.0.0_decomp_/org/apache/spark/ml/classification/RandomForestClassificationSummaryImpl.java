package org.apache.spark.ml.classification;

import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005I3AAC\u0006\u0005-!A\u0011\u0005\u0001BC\u0002\u0013\u0005#\u0005\u0003\u00058\u0001\t\u0005\t\u0015!\u0003$\u0011!a\u0004A!b\u0001\n\u0003j\u0004\u0002\u0003$\u0001\u0005\u0003\u0005\u000b\u0011\u0002 \t\u0011\u001d\u0003!Q1A\u0005BuB\u0001\u0002\u0013\u0001\u0003\u0002\u0003\u0006IA\u0010\u0005\t\u0013\u0002\u0011)\u0019!C!{!A!\n\u0001B\u0001B\u0003%a\bC\u0003L\u0001\u0011\u0005AJA\u0013SC:$w.\u001c$pe\u0016\u001cHo\u00117bgNLg-[2bi&|gnU;n[\u0006\u0014\u00180S7qY*\u0011A\"D\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\tqq\"\u0001\u0002nY*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u00019R\u0004\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VM\u001a\t\u0003=}i\u0011aC\u0005\u0003A-\u0011\u0011EU1oI>lgi\u001c:fgR\u001cE.Y:tS\u001aL7-\u0019;j_:\u001cV/\\7bef\f1\u0002\u001d:fI&\u001cG/[8ogV\t1\u0005\u0005\u0002%i9\u0011Q%\r\b\u0003M=r!a\n\u0018\u000f\u0005!jcBA\u0015-\u001b\u0005Q#BA\u0016\u0016\u0003\u0019a$o\\8u}%\tA#\u0003\u0002\u0013'%\u0011\u0001#E\u0005\u0003a=\t1a]9m\u0013\t\u00114'A\u0004qC\u000e\\\u0017mZ3\u000b\u0005Az\u0011BA\u001b7\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u00023g\u0005a\u0001O]3eS\u000e$\u0018n\u001c8tA!\u0012!!\u000f\t\u00031iJ!aO\r\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018!\u00049sK\u0012L7\r^5p]\u000e{G.F\u0001?!\ty4I\u0004\u0002A\u0003B\u0011\u0011&G\u0005\u0003\u0005f\ta\u0001\u0015:fI\u00164\u0017B\u0001#F\u0005\u0019\u0019FO]5oO*\u0011!)G\u0001\u000faJ,G-[2uS>t7i\u001c7!\u0003!a\u0017MY3m\u0007>d\u0017!\u00037bE\u0016d7i\u001c7!\u0003%9X-[4ii\u000e{G.\u0001\u0006xK&<\u0007\u000e^\"pY\u0002\na\u0001P5oSRtD#B'O\u001fB\u000b\u0006C\u0001\u0010\u0001\u0011\u0015\t\u0013\u00021\u0001$\u0011\u0015a\u0014\u00021\u0001?\u0011\u00159\u0015\u00021\u0001?\u0011\u0015I\u0015\u00021\u0001?\u0001"
)
public class RandomForestClassificationSummaryImpl implements RandomForestClassificationSummary {
   private final transient Dataset predictions;
   private final String predictionCol;
   private final String labelCol;
   private final String weightCol;
   private transient MulticlassMetrics org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics;

   public BinaryRandomForestClassificationSummary asBinary() {
      return RandomForestClassificationSummary.asBinary$(this);
   }

   public double[] labels() {
      return ClassificationSummary.labels$(this);
   }

   public double[] truePositiveRateByLabel() {
      return ClassificationSummary.truePositiveRateByLabel$(this);
   }

   public double[] falsePositiveRateByLabel() {
      return ClassificationSummary.falsePositiveRateByLabel$(this);
   }

   public double[] precisionByLabel() {
      return ClassificationSummary.precisionByLabel$(this);
   }

   public double[] recallByLabel() {
      return ClassificationSummary.recallByLabel$(this);
   }

   public double[] fMeasureByLabel(final double beta) {
      return ClassificationSummary.fMeasureByLabel$(this, beta);
   }

   public double[] fMeasureByLabel() {
      return ClassificationSummary.fMeasureByLabel$(this);
   }

   public double accuracy() {
      return ClassificationSummary.accuracy$(this);
   }

   public double weightedTruePositiveRate() {
      return ClassificationSummary.weightedTruePositiveRate$(this);
   }

   public double weightedFalsePositiveRate() {
      return ClassificationSummary.weightedFalsePositiveRate$(this);
   }

   public double weightedRecall() {
      return ClassificationSummary.weightedRecall$(this);
   }

   public double weightedPrecision() {
      return ClassificationSummary.weightedPrecision$(this);
   }

   public double weightedFMeasure(final double beta) {
      return ClassificationSummary.weightedFMeasure$(this, beta);
   }

   public double weightedFMeasure() {
      return ClassificationSummary.weightedFMeasure$(this);
   }

   public MulticlassMetrics org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics() {
      return this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics;
   }

   public final void org$apache$spark$ml$classification$ClassificationSummary$_setter_$org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics_$eq(final MulticlassMetrics x$1) {
      this.org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics = x$1;
   }

   public Dataset predictions() {
      return this.predictions;
   }

   public String predictionCol() {
      return this.predictionCol;
   }

   public String labelCol() {
      return this.labelCol;
   }

   public String weightCol() {
      return this.weightCol;
   }

   public RandomForestClassificationSummaryImpl(final Dataset predictions, final String predictionCol, final String labelCol, final String weightCol) {
      this.predictions = predictions;
      this.predictionCol = predictionCol;
      this.labelCol = labelCol;
      this.weightCol = weightCol;
      ClassificationSummary.$init$(this);
      RandomForestClassificationSummary.$init$(this);
      Statics.releaseFence();
   }
}
