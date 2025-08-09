package org.apache.spark.ml.classification;

import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005q3AAD\b\u00055!AQ\u0005\u0001BC\u0002\u0013\u0005c\u0005\u0003\u0005<\u0001\t\u0005\t\u0015!\u0003(\u0011!\u0001\u0005A!b\u0001\n\u0003\n\u0005\u0002\u0003&\u0001\u0005\u0003\u0005\u000b\u0011\u0002\"\t\u0011-\u0003!Q1A\u0005B\u0005C\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006IA\u0011\u0005\t\u001b\u0002\u0011)\u0019!C!\u0003\"Aa\n\u0001B\u0001B\u0003%!\t\u0003\u0005P\u0001\t\u0015\r\u0011\"\u0011B\u0011!\u0001\u0006A!A!\u0002\u0013\u0011\u0005\u0002C)\u0001\u0005\u000b\u0007I\u0011I!\t\u0011I\u0003!\u0011!Q\u0001\n\tCQa\u0015\u0001\u0005\u0002Q\u0013Q\u0004T8hSN$\u0018n\u0019*fOJ,7o]5p]N+X.\\1ss&k\u0007\u000f\u001c\u0006\u0003!E\tab\u00197bgNLg-[2bi&|gN\u0003\u0002\u0013'\u0005\u0011Q\u000e\u001c\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sO\u000e\u00011c\u0001\u0001\u001cCA\u0011AdH\u0007\u0002;)\ta$A\u0003tG\u0006d\u0017-\u0003\u0002!;\t1\u0011I\\=SK\u001a\u0004\"AI\u0012\u000e\u0003=I!\u0001J\b\u000331{w-[:uS\u000e\u0014Vm\u001a:fgNLwN\\*v[6\f'/_\u0001\faJ,G-[2uS>t7/F\u0001(!\tA\u0003H\u0004\u0002*k9\u0011!f\r\b\u0003WIr!\u0001L\u0019\u000f\u00055\u0002T\"\u0001\u0018\u000b\u0005=J\u0012A\u0002\u001fs_>$h(C\u0001\u0019\u0013\t1r#\u0003\u0002\u0015+%\u0011AgE\u0001\u0004gFd\u0017B\u0001\u001c8\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001N\n\n\u0005eR$!\u0003#bi\u00064%/Y7f\u0015\t1t'\u0001\u0007qe\u0016$\u0017n\u0019;j_:\u001c\b\u0005\u000b\u0002\u0003{A\u0011ADP\u0005\u0003\u007fu\u0011\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u001dA\u0014xNY1cS2LG/_\"pYV\t!\t\u0005\u0002D\u000f:\u0011A)\u0012\t\u0003[uI!AR\u000f\u0002\rA\u0013X\rZ3g\u0013\tA\u0015J\u0001\u0004TiJLgn\u001a\u0006\u0003\rv\tq\u0002\u001d:pE\u0006\u0014\u0017\u000e\\5us\u000e{G\u000eI\u0001\u000eaJ,G-[2uS>t7i\u001c7\u0002\u001dA\u0014X\rZ5di&|gnQ8mA\u0005AA.\u00192fY\u000e{G.A\u0005mC\n,GnQ8mA\u0005Ya-Z1ukJ,7oQ8m\u000311W-\u0019;ve\u0016\u001c8i\u001c7!\u0003%9X-[4ii\u000e{G.\u0001\u0006xK&<\u0007\u000e^\"pY\u0002\na\u0001P5oSRtDcB+W/bK&l\u0017\t\u0003E\u0001AQ!J\u0007A\u0002\u001dBQ\u0001Q\u0007A\u0002\tCQaS\u0007A\u0002\tCQ!T\u0007A\u0002\tCQaT\u0007A\u0002\tCQ!U\u0007A\u0002\t\u0003"
)
public class LogisticRegressionSummaryImpl implements LogisticRegressionSummary {
   private final transient Dataset predictions;
   private final String probabilityCol;
   private final String predictionCol;
   private final String labelCol;
   private final String featuresCol;
   private final String weightCol;
   private transient MulticlassMetrics org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics;

   public BinaryLogisticRegressionSummary asBinary() {
      return LogisticRegressionSummary.asBinary$(this);
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

   public String probabilityCol() {
      return this.probabilityCol;
   }

   public String predictionCol() {
      return this.predictionCol;
   }

   public String labelCol() {
      return this.labelCol;
   }

   public String featuresCol() {
      return this.featuresCol;
   }

   public String weightCol() {
      return this.weightCol;
   }

   public LogisticRegressionSummaryImpl(final Dataset predictions, final String probabilityCol, final String predictionCol, final String labelCol, final String featuresCol, final String weightCol) {
      this.predictions = predictions;
      this.probabilityCol = probabilityCol;
      this.predictionCol = predictionCol;
      this.labelCol = labelCol;
      this.featuresCol = featuresCol;
      this.weightCol = weightCol;
      ClassificationSummary.$init$(this);
      LogisticRegressionSummary.$init$(this);
      Statics.releaseFence();
   }
}
