package org.apache.spark.ml.classification;

import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005]3A\u0001D\u0007\u00051!A1\u0005\u0001BC\u0002\u0013\u0005C\u0005\u0003\u0005:\u0001\t\u0005\t\u0015!\u0003&\u0011!q\u0004A!b\u0001\n\u0003z\u0004\u0002\u0003%\u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u0011%\u0003!Q1A\u0005B}B\u0001B\u0013\u0001\u0003\u0002\u0003\u0006I\u0001\u0011\u0005\t\u0017\u0002\u0011)\u0019!C!\u007f!AA\n\u0001B\u0001B\u0003%\u0001\t\u0003\u0005N\u0001\t\u0015\r\u0011\"\u0011@\u0011!q\u0005A!A!\u0002\u0013\u0001\u0005\"B(\u0001\t\u0003\u0001&a\u0007$N\u00072\f7o]5gS\u000e\fG/[8o'VlW.\u0019:z\u00136\u0004HN\u0003\u0002\u000f\u001f\u0005q1\r\\1tg&4\u0017nY1uS>t'B\u0001\t\u0012\u0003\tiGN\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h\u0007\u0001\u00192\u0001A\r !\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fMB\u0011\u0001%I\u0007\u0002\u001b%\u0011!%\u0004\u0002\u0018\r6\u001bE.Y:tS\u001aL7-\u0019;j_:\u001cV/\\7bef\f1\u0002\u001d:fI&\u001cG/[8ogV\tQ\u0005\u0005\u0002'm9\u0011qe\r\b\u0003QEr!!\u000b\u0019\u000f\u0005)zcBA\u0016/\u001b\u0005a#BA\u0017\u0018\u0003\u0019a$o\\8u}%\ta#\u0003\u0002\u0015+%\u0011!cE\u0005\u0003eE\t1a]9m\u0013\t!T'A\u0004qC\u000e\\\u0017mZ3\u000b\u0005I\n\u0012BA\u001c9\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u00025k\u0005a\u0001O]3eS\u000e$\u0018n\u001c8tA!\u0012!a\u000f\t\u00035qJ!!P\u000e\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018\u0001C:d_J,7i\u001c7\u0016\u0003\u0001\u0003\"!Q#\u000f\u0005\t\u001b\u0005CA\u0016\u001c\u0013\t!5$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\r\u001e\u0013aa\u0015;sS:<'B\u0001#\u001c\u0003%\u00198m\u001c:f\u0007>d\u0007%A\u0007qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\\\u0001\u000faJ,G-[2uS>t7i\u001c7!\u0003!a\u0017MY3m\u0007>d\u0017!\u00037bE\u0016d7i\u001c7!\u0003%9X-[4ii\u000e{G.\u0001\u0006xK&<\u0007\u000e^\"pY\u0002\na\u0001P5oSRtDCB)S'R+f\u000b\u0005\u0002!\u0001!)1e\u0003a\u0001K!)ah\u0003a\u0001\u0001\")\u0011j\u0003a\u0001\u0001\")1j\u0003a\u0001\u0001\")Qj\u0003a\u0001\u0001\u0002"
)
public class FMClassificationSummaryImpl implements FMClassificationSummary {
   private final transient Dataset predictions;
   private final String scoreCol;
   private final String predictionCol;
   private final String labelCol;
   private final String weightCol;
   private SparkSession org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession;
   private transient BinaryClassificationMetrics org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics;
   private transient Dataset roc;
   private double areaUnderROC;
   private transient Dataset pr;
   private transient Dataset fMeasureByThreshold;
   private transient Dataset precisionByThreshold;
   private transient Dataset recallByThreshold;
   private transient MulticlassMetrics org$apache$spark$ml$classification$ClassificationSummary$$multiclassMetrics;
   private volatile boolean bitmap$0;
   private transient volatile byte bitmap$trans$0;

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

   public SparkSession org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession() {
      return this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession;
   }

   public BinaryClassificationMetrics org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics() {
      return this.org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics;
   }

   private Dataset roc$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.roc = BinaryClassificationSummary.roc$(this);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.roc;
   }

   public Dataset roc() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.roc$lzycompute() : this.roc;
   }

   private double areaUnderROC$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.areaUnderROC = BinaryClassificationSummary.areaUnderROC$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.areaUnderROC;
   }

   public double areaUnderROC() {
      return !this.bitmap$0 ? this.areaUnderROC$lzycompute() : this.areaUnderROC;
   }

   private Dataset pr$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.pr = BinaryClassificationSummary.pr$(this);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.pr;
   }

   public Dataset pr() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.pr$lzycompute() : this.pr;
   }

   private Dataset fMeasureByThreshold$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.fMeasureByThreshold = BinaryClassificationSummary.fMeasureByThreshold$(this);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.fMeasureByThreshold;
   }

   public Dataset fMeasureByThreshold() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.fMeasureByThreshold$lzycompute() : this.fMeasureByThreshold;
   }

   private Dataset precisionByThreshold$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            this.precisionByThreshold = BinaryClassificationSummary.precisionByThreshold$(this);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.precisionByThreshold;
   }

   public Dataset precisionByThreshold() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this.precisionByThreshold$lzycompute() : this.precisionByThreshold;
   }

   private Dataset recallByThreshold$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 16) == 0) {
            this.recallByThreshold = BinaryClassificationSummary.recallByThreshold$(this);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.recallByThreshold;
   }

   public Dataset recallByThreshold() {
      return (byte)(this.bitmap$trans$0 & 16) == 0 ? this.recallByThreshold$lzycompute() : this.recallByThreshold;
   }

   public final void org$apache$spark$ml$classification$BinaryClassificationSummary$_setter_$org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession_$eq(final SparkSession x$1) {
      this.org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession = x$1;
   }

   public final void org$apache$spark$ml$classification$BinaryClassificationSummary$_setter_$org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics_$eq(final BinaryClassificationMetrics x$1) {
      this.org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics = x$1;
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

   public String scoreCol() {
      return this.scoreCol;
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

   public FMClassificationSummaryImpl(final Dataset predictions, final String scoreCol, final String predictionCol, final String labelCol, final String weightCol) {
      this.predictions = predictions;
      this.scoreCol = scoreCol;
      this.predictionCol = predictionCol;
      this.labelCol = labelCol;
      this.weightCol = weightCol;
      ClassificationSummary.$init$(this);
      BinaryClassificationSummary.$init$(this);
      Statics.releaseFence();
   }
}
