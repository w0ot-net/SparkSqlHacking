package org.apache.spark.ml.classification;

import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005=3A\u0001C\u0005\u0005)!IA\u0004\u0001B\u0001B\u0003%Q$\r\u0005\ne\u0001\u0011\t\u0011)A\u0005guB\u0011B\u0010\u0001\u0003\u0002\u0003\u0006IaM \t\u0013\u0001\u0003!\u0011!Q\u0001\nM\n\u0005\"\u0003\"\u0001\u0005\u0003\u0005\u000b\u0011B\u001aD\u0011%!\u0005A!A!\u0002\u0013\u0019T\tC\u0003G\u0001\u0011\u0005qIA\u0012CS:\f'/\u001f'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:\u001cV/\\7befLU\u000e\u001d7\u000b\u0005)Y\u0011AD2mCN\u001c\u0018NZ5dCRLwN\u001c\u0006\u0003\u00195\t!!\u001c7\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001+e\u0001\"AF\f\u000e\u0003%I!\u0001G\u0005\u0003;1{w-[:uS\u000e\u0014Vm\u001a:fgNLwN\\*v[6\f'/_%na2\u0004\"A\u0006\u000e\n\u0005mI!a\b\"j]\u0006\u0014\u0018\u0010T8hSN$\u0018n\u0019*fOJ,7o]5p]N+X.\\1ss\u0006Y\u0001O]3eS\u000e$\u0018n\u001c8t!\tqbF\u0004\u0002 W9\u0011\u0001%\u000b\b\u0003C!r!AI\u0014\u000f\u0005\r2S\"\u0001\u0013\u000b\u0005\u0015\u001a\u0012A\u0002\u001fs_>$h(C\u0001\u0013\u0013\t\u0001\u0012#\u0003\u0002\u000f\u001f%\u0011!&D\u0001\u0004gFd\u0017B\u0001\u0017.\u0003\u001d\u0001\u0018mY6bO\u0016T!AK\u0007\n\u0005=\u0002$!\u0003#bi\u00064%/Y7f\u0015\taS&\u0003\u0002\u001d/\u0005q\u0001O]8cC\nLG.\u001b;z\u0007>d\u0007C\u0001\u001b;\u001d\t)\u0004\b\u0005\u0002$m)\tq'A\u0003tG\u0006d\u0017-\u0003\u0002:m\u00051\u0001K]3eK\u001aL!a\u000f\u001f\u0003\rM#(/\u001b8h\u0015\tId'\u0003\u00023/\u0005i\u0001O]3eS\u000e$\u0018n\u001c8D_2L!AP\f\u0002\u00111\f'-\u001a7D_2L!\u0001Q\f\u0002\u0017\u0019,\u0017\r^;sKN\u001cu\u000e\\\u0005\u0003\u0005^\t\u0011b^3jO\"$8i\u001c7\n\u0005\u0011;\u0012A\u0002\u001fj]&$h\bF\u0004I\u0013*[E*\u0014(\u0011\u0005Y\u0001\u0001\"\u0002\u000f\b\u0001\u0004i\u0002\"\u0002\u001a\b\u0001\u0004\u0019\u0004\"\u0002 \b\u0001\u0004\u0019\u0004\"\u0002!\b\u0001\u0004\u0019\u0004\"\u0002\"\b\u0001\u0004\u0019\u0004\"\u0002#\b\u0001\u0004\u0019\u0004"
)
public class BinaryLogisticRegressionSummaryImpl extends LogisticRegressionSummaryImpl implements BinaryLogisticRegressionSummary {
   private SparkSession org$apache$spark$ml$classification$BinaryClassificationSummary$$sparkSession;
   private transient BinaryClassificationMetrics org$apache$spark$ml$classification$BinaryClassificationSummary$$binaryMetrics;
   private transient Dataset roc;
   private double areaUnderROC;
   private transient Dataset pr;
   private transient Dataset fMeasureByThreshold;
   private transient Dataset precisionByThreshold;
   private transient Dataset recallByThreshold;
   private volatile boolean bitmap$0;
   private transient volatile byte bitmap$trans$0;

   public String scoreCol() {
      return BinaryLogisticRegressionSummary.scoreCol$(this);
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

   public BinaryLogisticRegressionSummaryImpl(final Dataset predictions, final String probabilityCol, final String predictionCol, final String labelCol, final String featuresCol, final String weightCol) {
      super(predictions, probabilityCol, predictionCol, labelCol, featuresCol, weightCol);
      BinaryClassificationSummary.$init$(this);
      BinaryLogisticRegressionSummary.$init$(this);
      Statics.releaseFence();
   }
}
