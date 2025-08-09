package org.apache.spark.ml.classification;

import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005I3AAC\u0006\u0005-!A\u0011\u0005\u0001BC\u0002\u0013\u0005#\u0005\u0003\u00058\u0001\t\u0005\t\u0015!\u0003$\u0011!a\u0004A!b\u0001\n\u0003j\u0004\u0002\u0003$\u0001\u0005\u0003\u0005\u000b\u0011\u0002 \t\u0011\u001d\u0003!Q1A\u0005BuB\u0001\u0002\u0013\u0001\u0003\u0002\u0003\u0006IA\u0010\u0005\t\u0013\u0002\u0011)\u0019!C!{!A!\n\u0001B\u0001B\u0003%a\bC\u0003L\u0001\u0011\u0005AJA\u0017Nk2$\u0018\u000e\\1zKJ\u0004VM]2faR\u0014xN\\\"mCN\u001c\u0018NZ5dCRLwN\\*v[6\f'/_%na2T!\u0001D\u0007\u0002\u001d\rd\u0017m]:jM&\u001c\u0017\r^5p]*\u0011abD\u0001\u0003[2T!\u0001E\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u0019\u0012AB1qC\u000eDWMC\u0001\u0015\u0003\ry'oZ\u0002\u0001'\r\u0001q#\b\t\u00031mi\u0011!\u0007\u0006\u00025\u0005)1oY1mC&\u0011A$\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005yyR\"A\u0006\n\u0005\u0001Z!!K'vYRLG.Y=feB+'oY3qiJ|gn\u00117bgNLg-[2bi&|gnU;n[\u0006\u0014\u00180A\u0006qe\u0016$\u0017n\u0019;j_:\u001cX#A\u0012\u0011\u0005\u0011\"dBA\u00132\u001d\t1sF\u0004\u0002(]9\u0011\u0001&\f\b\u0003S1j\u0011A\u000b\u0006\u0003WU\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000b\n\u0005I\u0019\u0012B\u0001\t\u0012\u0013\t\u0001t\"A\u0002tc2L!AM\u001a\u0002\u000fA\f7m[1hK*\u0011\u0001gD\u0005\u0003kY\u0012\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0005I\u001a\u0014\u0001\u00049sK\u0012L7\r^5p]N\u0004\u0003F\u0001\u0002:!\tA\"(\u0003\u0002<3\tIAO]1og&,g\u000e^\u0001\u000eaJ,G-[2uS>t7i\u001c7\u0016\u0003y\u0002\"aP\"\u000f\u0005\u0001\u000b\u0005CA\u0015\u001a\u0013\t\u0011\u0015$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\t\u0016\u0013aa\u0015;sS:<'B\u0001\"\u001a\u00039\u0001(/\u001a3jGRLwN\\\"pY\u0002\n\u0001\u0002\\1cK2\u001cu\u000e\\\u0001\nY\u0006\u0014W\r\\\"pY\u0002\n\u0011b^3jO\"$8i\u001c7\u0002\u0015],\u0017n\u001a5u\u0007>d\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0006\u001b:{\u0005+\u0015\t\u0003=\u0001AQ!I\u0005A\u0002\rBQ\u0001P\u0005A\u0002yBQaR\u0005A\u0002yBQ!S\u0005A\u0002y\u0002"
)
public class MultilayerPerceptronClassificationSummaryImpl implements MultilayerPerceptronClassificationSummary {
   private final transient Dataset predictions;
   private final String predictionCol;
   private final String labelCol;
   private final String weightCol;
   private transient MulticlassMetrics org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics;

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

   public MultilayerPerceptronClassificationSummaryImpl(final Dataset predictions, final String predictionCol, final String labelCol, final String weightCol) {
      this.predictions = predictions;
      this.predictionCol = predictionCol;
      this.labelCol = labelCol;
      this.weightCol = weightCol;
      ClassificationSummary.$init$(this);
      Statics.releaseFence();
   }
}
