package org.apache.spark.ml.classification;

import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u000553A\u0001C\u0005\u0005)!IA\u0004\u0001B\u0001B\u0003%Q$\r\u0005\te\u0001\u0011)\u0019!C!g!Aa\b\u0001B\u0001B\u0003%A\u0007C\u0005@\u0001\t\u0005\t\u0015!\u00035\u0001\"I\u0011\t\u0001B\u0001B\u0003%AG\u0011\u0005\n\u0007\u0002\u0011\t\u0011)A\u0005i\u0011CQ!\u0012\u0001\u0005\u0002\u0019\u00131FQ5oCJL(+\u00198e_64uN]3ti\u000ec\u0017m]:jM&\u001c\u0017\r^5p]N+X.\\1ss&k\u0007\u000f\u001c\u0006\u0003\u0015-\tab\u00197bgNLg-[2bi&|gN\u0003\u0002\r\u001b\u0005\u0011Q\u000e\u001c\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sO\u000e\u00011c\u0001\u0001\u00163A\u0011acF\u0007\u0002\u0013%\u0011\u0001$\u0003\u0002&%\u0006tGm\\7G_J,7\u000f^\"mCN\u001c\u0018NZ5dCRLwN\\*v[6\f'/_%na2\u0004\"A\u0006\u000e\n\u0005mI!a\n\"j]\u0006\u0014\u0018PU1oI>lgi\u001c:fgR\u001cE.Y:tS\u001aL7-\u0019;j_:\u001cV/\\7bef\f1\u0002\u001d:fI&\u001cG/[8ogB\u0011aD\f\b\u0003?-r!\u0001I\u0015\u000f\u0005\u0005BcB\u0001\u0012(\u001d\t\u0019c%D\u0001%\u0015\t)3#\u0001\u0004=e>|GOP\u0005\u0002%%\u0011\u0001#E\u0005\u0003\u001d=I!AK\u0007\u0002\u0007M\fH.\u0003\u0002-[\u00059\u0001/Y2lC\u001e,'B\u0001\u0016\u000e\u0013\ty\u0003GA\u0005ECR\fgI]1nK*\u0011A&L\u0005\u00039]\t\u0001b]2pe\u0016\u001cu\u000e\\\u000b\u0002iA\u0011Qg\u000f\b\u0003me\u0002\"aI\u001c\u000b\u0003a\nQa]2bY\u0006L!AO\u001c\u0002\rA\u0013X\rZ3g\u0013\taTH\u0001\u0004TiJLgn\u001a\u0006\u0003u]\n\u0011b]2pe\u0016\u001cu\u000e\u001c\u0011\u0002\u001bA\u0014X\rZ5di&|gnQ8m\u0013\tyt#\u0001\u0005mC\n,GnQ8m\u0013\t\tu#A\u0005xK&<\u0007\u000e^\"pY&\u00111iF\u0001\u0007y%t\u0017\u000e\u001e \u0015\r\u001dC\u0015JS&M!\t1\u0002\u0001C\u0003\u001d\u000f\u0001\u0007Q\u0004C\u00033\u000f\u0001\u0007A\u0007C\u0003@\u000f\u0001\u0007A\u0007C\u0003B\u000f\u0001\u0007A\u0007C\u0003D\u000f\u0001\u0007A\u0007"
)
public class BinaryRandomForestClassificationSummaryImpl extends RandomForestClassificationSummaryImpl implements BinaryRandomForestClassificationSummary {
   private final String scoreCol;
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

   public String scoreCol() {
      return this.scoreCol;
   }

   public BinaryRandomForestClassificationSummaryImpl(final Dataset predictions, final String scoreCol, final String predictionCol, final String labelCol, final String weightCol) {
      super(predictions, predictionCol, labelCol, weightCol);
      this.scoreCol = scoreCol;
      BinaryClassificationSummary.$init$(this);
      Statics.releaseFence();
   }
}
