package org.apache.spark.ml.classification;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasElasticNetParam;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasStandardization;
import org.apache.spark.ml.param.shared.HasThreshold;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Predef.;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015e\u0001\u0003\u000b\u0016!\u0003\r\t!F\u0010\t\u000b5\u0003A\u0011A(\t\u000bM\u0003A\u0011\u0001+\t\u000fm\u0003!\u0019!C\u00039\")Q\u000f\u0001C\u0001m\")\u0001\u0010\u0001C!s\")!\u0010\u0001C\u0001w\"9\u0011\u0011\u0001\u0001\u0005B\u0005\r\u0001BBA\u0003\u0001\u0011Eq\nC\u0005\u0002\b\u0001\u0011\r\u0011\"\u0001\u0002\n!9\u0011q\u0004\u0001\u0005\u0002\u0005\u0005\u0002\"CA\u0013\u0001\t\u0007I\u0011AA\u0005\u0011\u001d\tI\u0003\u0001C\u0001\u0003CA\u0011\"!\f\u0001\u0005\u0004%\t!a\f\t\u000f\u0005m\u0002\u0001\"\u0001\u0002>!I\u0011\u0011\t\u0001C\u0002\u0013\u0005\u0011q\u0006\u0005\b\u0003\u000b\u0002A\u0011AA\u001f\u0011\u001d\tI\u0005\u0001C\t\u0003\u0017Bq!a\u0015\u0001\t#\n)\u0006\u0003\b\u0002z\u0001\u0001\n1!A\u0001\n\u0013\tY(a!\u000311{w-[:uS\u000e\u0014Vm\u001a:fgNLwN\u001c)be\u0006l7O\u0003\u0002\u0017/\u0005q1\r\\1tg&4\u0017nY1uS>t'B\u0001\r\u001a\u0003\tiGN\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h'5\u0001\u0001E\n\u00163kaZd(\u0011#H\u0015B\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\u0004\"a\n\u0015\u000e\u0003UI!!K\u000b\u0003;A\u0013xNY1cS2L7\u000f^5d\u00072\f7o]5gS\u0016\u0014\b+\u0019:b[N\u0004\"a\u000b\u0019\u000e\u00031R!!\f\u0018\u0002\rMD\u0017M]3e\u0015\tys#A\u0003qCJ\fW.\u0003\u00022Y\tY\u0001*Y:SK\u001e\u0004\u0016M]1n!\tY3'\u0003\u00025Y\t\u0011\u0002*Y:FY\u0006\u001cH/[2OKR\u0004\u0016M]1n!\tYc'\u0003\u00028Y\tQ\u0001*Y:NCbLE/\u001a:\u0011\u0005-J\u0014B\u0001\u001e-\u0005=A\u0015m\u001d$ji&sG/\u001a:dKB$\bCA\u0016=\u0013\tiDF\u0001\u0004ICN$v\u000e\u001c\t\u0003W}J!\u0001\u0011\u0017\u0003%!\u000b7o\u0015;b]\u0012\f'\u000fZ5{CRLwN\u001c\t\u0003W\tK!a\u0011\u0017\u0003\u0019!\u000b7oV3jO\"$8i\u001c7\u0011\u0005-*\u0015B\u0001$-\u00051A\u0015m\u001d+ie\u0016\u001c\bn\u001c7e!\tY\u0003*\u0003\u0002JY\t\u0019\u0002*Y:BO\u001e\u0014XmZ1uS>tG)\u001a9uQB\u00111fS\u0005\u0003\u00192\u00121\u0003S1t\u001b\u0006D(\t\\8dWNK'0Z%o\u001b\n\u000ba\u0001J5oSR$3\u0001\u0001\u000b\u0002!B\u0011\u0011%U\u0005\u0003%\n\u0012A!\u00168ji\u0006a1/\u001a;UQJ,7\u000f[8mIR\u0011QKV\u0007\u0002\u0001!)qK\u0001a\u00011\u0006)a/\u00197vKB\u0011\u0011%W\u0005\u00035\n\u0012a\u0001R8vE2,\u0017A\u00024b[&d\u00170F\u0001^!\rqv,Y\u0007\u0002]%\u0011\u0001M\f\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u0003E&t!aY4\u0011\u0005\u0011\u0014S\"A3\u000b\u0005\u0019t\u0015A\u0002\u001fs_>$h(\u0003\u0002iE\u00051\u0001K]3eK\u001aL!A[6\u0003\rM#(/\u001b8h\u0015\tA'\u0005K\u0002\u0004[N\u0004\"A\\9\u000e\u0003=T!\u0001]\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002s_\n)1+\u001b8dK\u0006\nA/A\u00033]Er\u0003'A\u0005hKR4\u0015-\\5msV\t\u0011\rK\u0002\u0005[N\fAbZ3u)\"\u0014Xm\u001d5pY\u0012,\u0012\u0001W\u0001\u000eg\u0016$H\u000b\u001b:fg\"|G\u000eZ:\u0015\u0005Uc\b\"B,\u0007\u0001\u0004i\bcA\u0011\u007f1&\u0011qP\t\u0002\u0006\u0003J\u0014\u0018-_\u0001\u000eO\u0016$H\u000b\u001b:fg\"|G\u000eZ:\u0016\u0003u\f\u0011d\u00195fG.$\u0006N]3tQ>dGmQ8og&\u001cH/\u001a8ds\u0006IBn\\<fe\n{WO\u001c3t\u001f:\u001cu.\u001a4gS\u000eLWM\u001c;t+\t\tY\u0001\u0005\u0003_?\u00065\u0001\u0003BA\b\u0003+i!!!\u0005\u000b\u0007\u0005Mq#\u0001\u0004mS:\fGnZ\u0005\u0005\u0003/\t\tB\u0001\u0004NCR\u0014\u0018\u000e\u001f\u0015\u0005\u00135\fY\"\t\u0002\u0002\u001e\u0005)!G\f\u001a/a\u0005ar-\u001a;M_^,'OQ8v]\u0012\u001cxJ\\\"pK\u001a4\u0017nY5f]R\u001cXCAA\u0007Q\u0011QQ.a\u0007\u00023U\u0004\b/\u001a:C_VtGm](o\u0007>,gMZ5dS\u0016tGo\u001d\u0015\u0005\u00175\fY\"\u0001\u000fhKR,\u0006\u000f]3s\u0005>,h\u000eZ:P]\u000e{WM\u001a4jG&,g\u000e^:)\t1i\u00171D\u0001\u0018Y><XM\u001d\"pk:$7o\u00148J]R,'oY3qiN,\"!!\r\u0011\ty{\u00161\u0007\t\u0005\u0003\u001f\t)$\u0003\u0003\u00028\u0005E!A\u0002,fGR|'\u000f\u000b\u0003\u000e[\u0006m\u0011AG4fi2{w/\u001a:C_VtGm](o\u0013:$XM]2faR\u001cXCAA\u001aQ\u0011qQ.a\u0007\u0002/U\u0004\b/\u001a:C_VtGm](o\u0013:$XM]2faR\u001c\b\u0006B\bn\u00037\t!dZ3u+B\u0004XM\u001d\"pk:$7o\u00148J]R,'oY3qiNDC\u0001E7\u0002\u001c\u0005\tSo]5oO\n{WO\u001c3D_:\u001cHO]1j]\u0016$w\n\u001d;j[&T\u0018\r^5p]V\u0011\u0011Q\n\t\u0004C\u0005=\u0013bAA)E\t9!i\\8mK\u0006t\u0017A\u0007<bY&$\u0017\r^3B]\u0012$&/\u00198tM>\u0014XnU2iK6\fG\u0003CA,\u0003O\nY'a\u001c\u0011\t\u0005e\u00131M\u0007\u0003\u00037RA!!\u0018\u0002`\u0005)A/\u001f9fg*\u0019\u0011\u0011M\r\u0002\u0007M\fH.\u0003\u0003\u0002f\u0005m#AC*ueV\u001cG\u000fV=qK\"9\u0011\u0011\u000e\nA\u0002\u0005]\u0013AB:dQ\u0016l\u0017\rC\u0004\u0002nI\u0001\r!!\u0014\u0002\u000f\u0019LG\u000f^5oO\"9\u0011\u0011\u000f\nA\u0002\u0005M\u0014\u0001\u00054fCR,(/Z:ECR\fG+\u001f9f!\u0011\tI&!\u001e\n\t\u0005]\u00141\f\u0002\t\t\u0006$\u0018\rV=qK\u0006\u00013/\u001e9fe\u00122\u0018\r\\5eCR,\u0017I\u001c3Ue\u0006t7OZ8s[N\u001b\u0007.Z7b)!\t9&! \u0002\u0000\u0005\u0005\u0005bBA5'\u0001\u0007\u0011q\u000b\u0005\b\u0003[\u001a\u0002\u0019AA'\u0011\u001d\t\th\u0005a\u0001\u0003gJ1!a\u0015)\u0001"
)
public interface LogisticRegressionParams extends ProbabilisticClassifierParams, HasRegParam, HasElasticNetParam, HasMaxIter, HasFitIntercept, HasTol, HasStandardization, HasWeightCol, HasThreshold, HasAggregationDepth, HasMaxBlockSizeInMB {
   void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$family_$eq(final Param x$1);

   void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$lowerBoundsOnCoefficients_$eq(final Param x$1);

   void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$upperBoundsOnCoefficients_$eq(final Param x$1);

   void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$lowerBoundsOnIntercepts_$eq(final Param x$1);

   void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$upperBoundsOnIntercepts_$eq(final Param x$1);

   // $FF: synthetic method
   StructType org$apache$spark$ml$classification$LogisticRegressionParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType);

   // $FF: synthetic method
   static LogisticRegressionParams setThreshold$(final LogisticRegressionParams $this, final double value) {
      return $this.setThreshold(value);
   }

   default LogisticRegressionParams setThreshold(final double value) {
      if (this.isSet(this.thresholds())) {
         this.clear(this.thresholds());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return (LogisticRegressionParams)this.set(this.threshold(), BoxesRunTime.boxToDouble(value));
   }

   Param family();

   // $FF: synthetic method
   static String getFamily$(final LogisticRegressionParams $this) {
      return $this.getFamily();
   }

   default String getFamily() {
      return (String)this.$(this.family());
   }

   // $FF: synthetic method
   static double getThreshold$(final LogisticRegressionParams $this) {
      return $this.getThreshold();
   }

   default double getThreshold() {
      this.checkThresholdConsistency();
      if (this.isSet(this.thresholds())) {
         double[] ts = (double[])this.$(this.thresholds());
         .MODULE$.require(ts.length == 2, () -> {
            ArraySeq.ofDouble var10000 = .MODULE$.wrapDoubleArray(ts);
            return "Logistic Regression getThreshold only applies to binary classification, but thresholds has length != 2.  thresholds: " + var10000.mkString(",");
         });
         return (double)1.0F / ((double)1.0F + ts[0] / ts[1]);
      } else {
         return BoxesRunTime.unboxToDouble(this.$(this.threshold()));
      }
   }

   // $FF: synthetic method
   static LogisticRegressionParams setThresholds$(final LogisticRegressionParams $this, final double[] value) {
      return $this.setThresholds(value);
   }

   default LogisticRegressionParams setThresholds(final double[] value) {
      if (this.isSet(this.threshold())) {
         this.clear(this.threshold());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return (LogisticRegressionParams)this.set(this.thresholds(), value);
   }

   // $FF: synthetic method
   static double[] getThresholds$(final LogisticRegressionParams $this) {
      return $this.getThresholds();
   }

   default double[] getThresholds() {
      this.checkThresholdConsistency();
      if (!this.isSet(this.thresholds()) && this.isSet(this.threshold())) {
         double t = BoxesRunTime.unboxToDouble(this.$(this.threshold()));
         return new double[]{(double)1 - t, t};
      } else {
         return (double[])this.$(this.thresholds());
      }
   }

   // $FF: synthetic method
   static void checkThresholdConsistency$(final LogisticRegressionParams $this) {
      $this.checkThresholdConsistency();
   }

   default void checkThresholdConsistency() {
      if (this.isSet(this.threshold()) && this.isSet(this.thresholds())) {
         double[] ts = (double[])this.$(this.thresholds());
         .MODULE$.require(ts.length == 2, () -> {
            Object var10000 = this.$(this.threshold());
            return "Logistic Regression found inconsistent values for threshold and thresholds.  Param threshold is set (" + var10000 + "), indicating binary classification, but Param thresholds is set with length " + ts.length + ". Clear one Param value to fix this problem.";
         });
         double t = (double)1.0F / ((double)1.0F + ts[0] / ts[1]);
         .MODULE$.require(scala.math.package..MODULE$.abs(BoxesRunTime.unboxToDouble(this.$(this.threshold())) - t) < 1.0E-5, () -> {
            Object var10000 = this.$(this.threshold());
            return "Logistic Regression getThreshold found inconsistent values for threshold (" + var10000 + ") and thresholds (equivalent to " + t + ")";
         });
      }
   }

   Param lowerBoundsOnCoefficients();

   // $FF: synthetic method
   static Matrix getLowerBoundsOnCoefficients$(final LogisticRegressionParams $this) {
      return $this.getLowerBoundsOnCoefficients();
   }

   default Matrix getLowerBoundsOnCoefficients() {
      return (Matrix)this.$(this.lowerBoundsOnCoefficients());
   }

   Param upperBoundsOnCoefficients();

   // $FF: synthetic method
   static Matrix getUpperBoundsOnCoefficients$(final LogisticRegressionParams $this) {
      return $this.getUpperBoundsOnCoefficients();
   }

   default Matrix getUpperBoundsOnCoefficients() {
      return (Matrix)this.$(this.upperBoundsOnCoefficients());
   }

   Param lowerBoundsOnIntercepts();

   // $FF: synthetic method
   static Vector getLowerBoundsOnIntercepts$(final LogisticRegressionParams $this) {
      return $this.getLowerBoundsOnIntercepts();
   }

   default Vector getLowerBoundsOnIntercepts() {
      return (Vector)this.$(this.lowerBoundsOnIntercepts());
   }

   Param upperBoundsOnIntercepts();

   // $FF: synthetic method
   static Vector getUpperBoundsOnIntercepts$(final LogisticRegressionParams $this) {
      return $this.getUpperBoundsOnIntercepts();
   }

   default Vector getUpperBoundsOnIntercepts() {
      return (Vector)this.$(this.upperBoundsOnIntercepts());
   }

   // $FF: synthetic method
   static boolean usingBoundConstrainedOptimization$(final LogisticRegressionParams $this) {
      return $this.usingBoundConstrainedOptimization();
   }

   default boolean usingBoundConstrainedOptimization() {
      return this.isSet(this.lowerBoundsOnCoefficients()) || this.isSet(this.upperBoundsOnCoefficients()) || this.isSet(this.lowerBoundsOnIntercepts()) || this.isSet(this.upperBoundsOnIntercepts());
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final LogisticRegressionParams $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      this.checkThresholdConsistency();
      if (this.usingBoundConstrainedOptimization()) {
         .MODULE$.require(BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam())) == (double)0.0F, () -> "Fitting under bound constrained optimization only supports L2 regularization, but got elasticNetParam = " + this.getElasticNetParam() + ".");
      }

      if (!BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()))) {
         .MODULE$.require(!this.isSet(this.lowerBoundsOnIntercepts()) && !this.isSet(this.upperBoundsOnIntercepts()), () -> "Please don't set bounds on intercepts if fitting without intercept.");
      }

      return this.org$apache$spark$ml$classification$LogisticRegressionParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   // $FF: synthetic method
   static boolean $anonfun$family$1(final String value) {
      return scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])LogisticRegression$.MODULE$.supportedFamilyNames()), value.toLowerCase(Locale.ROOT));
   }

   static void $init$(final LogisticRegressionParams $this) {
      $this.org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$family_$eq(new Param($this, "family", "The name of family which is a description of the label distribution to be used in the model. Supported options: " + .MODULE$.wrapRefArray((Object[])LogisticRegression$.MODULE$.supportedFamilyNames()).mkString(", ") + ".", (value) -> BoxesRunTime.boxToBoolean($anonfun$family$1(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$lowerBoundsOnCoefficients_$eq(new Param($this, "lowerBoundsOnCoefficients", "The lower bounds on coefficients if fitting under bound constrained optimization.", scala.reflect.ClassTag..MODULE$.apply(Matrix.class)));
      $this.org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$upperBoundsOnCoefficients_$eq(new Param($this, "upperBoundsOnCoefficients", "The upper bounds on coefficients if fitting under bound constrained optimization.", scala.reflect.ClassTag..MODULE$.apply(Matrix.class)));
      $this.org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$lowerBoundsOnIntercepts_$eq(new Param($this, "lowerBoundsOnIntercepts", "The lower bounds on intercepts if fitting under bound constrained optimization.", scala.reflect.ClassTag..MODULE$.apply(Vector.class)));
      $this.org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$upperBoundsOnIntercepts_$eq(new Param($this, "upperBoundsOnIntercepts", "The upper bounds on intercepts if fitting under bound constrained optimization.", scala.reflect.ClassTag..MODULE$.apply(Vector.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.regParam().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.elasticNetParam().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(100)), $this.tol().$minus$greater(BoxesRunTime.boxToDouble(1.0E-6)), $this.fitIntercept().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.family().$minus$greater("auto"), $this.standardization().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.threshold().$minus$greater(BoxesRunTime.boxToDouble((double)0.5F)), $this.aggregationDepth().$minus$greater(BoxesRunTime.boxToInteger(2)), $this.maxBlockSizeInMB().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
