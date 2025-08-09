package org.apache.spark.ml.classification;

import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.shared.HasProbabilityCol;
import org.apache.spark.ml.param.shared.HasThresholds;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u000553Q\u0001B\u0003\u0002\u0002AAQa\r\u0001\u0005\u0002QBQ!\u000e\u0001\u0005\u0002YBQ\u0001\u0012\u0001\u0005\u0002\u0015\u0013q\u0003\u0015:pE\u0006\u0014\u0017\u000e\\5ti&\u001c7\t\\1tg&4\u0017.\u001a:\u000b\u0005\u00199\u0011AD2mCN\u001c\u0018NZ5dCRLwN\u001c\u0006\u0003\u0011%\t!!\u001c7\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\u0001!\u0006\u0003\u00121\u0015R3c\u0001\u0001\u0013aA)1\u0003\u0006\f%S5\tQ!\u0003\u0002\u0016\u000b\tQ1\t\\1tg&4\u0017.\u001a:\u0011\u0005]AB\u0002\u0001\u0003\u00063\u0001\u0011\rA\u0007\u0002\r\r\u0016\fG/\u001e:fgRK\b/Z\t\u00037\u0005\u0002\"\u0001H\u0010\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011qAT8uQ&tw\r\u0005\u0002\u001dE%\u00111%\b\u0002\u0004\u0003:L\bCA\f&\t\u00151\u0003A1\u0001(\u0005\u0005)\u0015CA\u000e)!\u0015\u0019\u0002A\u0006\u0013*!\t9\"\u0006B\u0003,\u0001\t\u0007AFA\u0001N#\tYR\u0006\u0005\u0003\u0014]YI\u0013BA\u0018\u0006\u0005\u0001\u0002&o\u001c2bE&d\u0017n\u001d;jG\u000ec\u0017m]:jM&\u001c\u0017\r^5p]6{G-\u001a7\u0011\u0005M\t\u0014B\u0001\u001a\u0006\u0005u\u0001&o\u001c2bE&d\u0017n\u001d;jG\u000ec\u0017m]:jM&,'\u000fU1sC6\u001c\u0018A\u0002\u001fj]&$h\bF\u0001)\u0003E\u0019X\r\u001e)s_\n\f'-\u001b7jif\u001cu\u000e\u001c\u000b\u0003I]BQ\u0001\u000f\u0002A\u0002e\nQA^1mk\u0016\u0004\"AO!\u000f\u0005mz\u0004C\u0001\u001f\u001e\u001b\u0005i$B\u0001 \u0010\u0003\u0019a$o\\8u}%\u0011\u0001)H\u0001\u0007!J,G-\u001a4\n\u0005\t\u001b%AB*ue&twM\u0003\u0002A;\u0005i1/\u001a;UQJ,7\u000f[8mIN$\"\u0001\n$\t\u000ba\u001a\u0001\u0019A$\u0011\u0007qA%*\u0003\u0002J;\t)\u0011I\u001d:bsB\u0011AdS\u0005\u0003\u0019v\u0011a\u0001R8vE2,\u0007"
)
public abstract class ProbabilisticClassifier extends Classifier implements ProbabilisticClassifierParams {
   private DoubleArrayParam thresholds;
   private Param probabilityCol;

   // $FF: synthetic method
   public StructType org$apache$spark$ml$classification$ProbabilisticClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ProbabilisticClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public double[] getThresholds() {
      return HasThresholds.getThresholds$(this);
   }

   public final String getProbabilityCol() {
      return HasProbabilityCol.getProbabilityCol$(this);
   }

   public DoubleArrayParam thresholds() {
      return this.thresholds;
   }

   public void org$apache$spark$ml$param$shared$HasThresholds$_setter_$thresholds_$eq(final DoubleArrayParam x$1) {
      this.thresholds = x$1;
   }

   public final Param probabilityCol() {
      return this.probabilityCol;
   }

   public final void org$apache$spark$ml$param$shared$HasProbabilityCol$_setter_$probabilityCol_$eq(final Param x$1) {
      this.probabilityCol = x$1;
   }

   public ProbabilisticClassifier setProbabilityCol(final String value) {
      return (ProbabilisticClassifier)this.set(this.probabilityCol(), value);
   }

   public ProbabilisticClassifier setThresholds(final double[] value) {
      return (ProbabilisticClassifier)this.set(this.thresholds(), value);
   }

   public ProbabilisticClassifier() {
      HasProbabilityCol.$init$(this);
      HasThresholds.$init$(this);
      ProbabilisticClassifierParams.$init$(this);
      Statics.releaseFence();
   }
}
