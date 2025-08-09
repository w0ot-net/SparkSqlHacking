package org.apache.spark.ml.regression;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.attribute.NumericAttribute;
import org.apache.spark.ml.attribute.NumericAttribute$;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d\u0001\u0003\u000b\u0016!\u0003\r\t!F\u0010\t\u000b)\u0003A\u0011\u0001'\t\u000fA\u0003!\u0019!C\u0003#\")!\u000e\u0001C\u0001W\"9Q\u000e\u0001b\u0001\n\u000bq\u0007\"B;\u0001\t\u00031\bbB>\u0001\u0005\u0004%)!\u0015\u0005\u0006{\u0002!\ta\u001b\u0005\b\u007f\u0002\u0011\r\u0011\"\u0002o\u0011\u0019\t\u0019\u0001\u0001C\u0001m\"A\u0011q\u0001\u0001C\u0002\u0013\u0015\u0011\u000b\u0003\u0004\u0002\f\u0001!\ta\u001b\u0005\t\u0003\u001f\u0001!\u0019!C\u0003#\"1\u0011q\u0003\u0001\u0005\u0002-D\u0001\"a\u0007\u0001\t\u0003)\u0012Q\u0004\u0005\t\u0003K\u0001A\u0011A\u000b\u0002\u001e!A\u0011q\u0005\u0001\u0005\u0002U\ti\u0002\u0003\u0005\u0002*\u0001\u0011\r\u0011\"\u0012R\u0011\u001d\ti\u0003\u0001C!\u0003_Aa\"!\u0016\u0001!\u0003\r\t\u0011!C\u0005\u0003/\nyFA\u0010HK:,'/\u00197ju\u0016$G*\u001b8fCJ\u0014Vm\u001a:fgNLwN\u001c\"bg\u0016T!AF\f\u0002\u0015I,wM]3tg&|gN\u0003\u0002\u00193\u0005\u0011Q\u000e\u001c\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sONY\u0001\u0001\t\u0014+eUB4HP!E!\t\tC%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u0019\te.\u001f*fMB\u0011q\u0005K\u0007\u0002/%\u0011\u0011f\u0006\u0002\u0010!J,G-[2u_J\u0004\u0016M]1ngB\u00111\u0006M\u0007\u0002Y)\u0011QFL\u0001\u0007g\"\f'/\u001a3\u000b\u0005=:\u0012!\u00029be\u0006l\u0017BA\u0019-\u0005=A\u0015m\u001d$ji&sG/\u001a:dKB$\bCA\u00164\u0013\t!DF\u0001\u0006ICNl\u0015\r_%uKJ\u0004\"a\u000b\u001c\n\u0005]b#A\u0002%bgR{G\u000e\u0005\u0002,s%\u0011!\b\f\u0002\f\u0011\u0006\u001c(+Z4QCJ\fW\u000e\u0005\u0002,y%\u0011Q\b\f\u0002\r\u0011\u0006\u001cx+Z5hQR\u001cu\u000e\u001c\t\u0003W}J!\u0001\u0011\u0017\u0003\u0013!\u000b7oU8mm\u0016\u0014\bCA\u0016C\u0013\t\u0019EFA\nICN\fum\u001a:fO\u0006$\u0018n\u001c8EKB$\b\u000e\u0005\u0002F\u00116\taI\u0003\u0002H3\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002J\r\n9Aj\\4hS:<\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u00035\u0003\"!\t(\n\u0005=\u0013#\u0001B+oSR\faAZ1nS2LX#\u0001*\u0011\u0007M#f+D\u0001/\u0013\t)fFA\u0003QCJ\fW\u000e\u0005\u0002X=:\u0011\u0001\f\u0018\t\u00033\nj\u0011A\u0017\u0006\u00037.\u000ba\u0001\u0010:p_Rt\u0014BA/#\u0003\u0019\u0001&/\u001a3fM&\u0011q\f\u0019\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005u\u0013\u0003f\u0001\u0002cQB\u00111MZ\u0007\u0002I*\u0011Q-G\u0001\u000bC:tw\u000e^1uS>t\u0017BA4e\u0005\u0015\u0019\u0016N\\2fC\u0005I\u0017!\u0002\u001a/a9\u0002\u0014!C4fi\u001a\u000bW.\u001b7z+\u00051\u0006fA\u0002cQ\u0006ia/\u0019:jC:\u001cW\rU8xKJ,\u0012a\u001c\t\u0003'BL!!\u001d\u0018\u0003\u0017\u0011{WO\u00197f!\u0006\u0014\u0018-\u001c\u0015\u0004\t\t\u001c\u0018%\u0001;\u0002\u000bIr#G\f\u0019\u0002!\u001d,GOV1sS\u0006t7-\u001a)po\u0016\u0014X#A<\u0011\u0005\u0005B\u0018BA=#\u0005\u0019!u.\u001e2mK\"\u001aQAY:\u0002\t1Lgn\u001b\u0015\u0004\r\tD\u0017aB4fi2Kgn\u001b\u0015\u0004\u000f\tD\u0017!\u00037j].\u0004vn^3sQ\rA!m]\u0001\rO\u0016$H*\u001b8l!><XM\u001d\u0015\u0004\u0013\t\u001c\u0018!\u00057j].\u0004&/\u001a3jGRLwN\\\"pY\"\u001a!B\u00195\u0002)\u001d,G\u000fT5oWB\u0013X\rZ5di&|gnQ8mQ\rY!\r[\u0001\n_\u001a47/\u001a;D_2DC\u0001\u00042\u0002\u0014\u0005\u0012\u0011QC\u0001\u0006e9\u001ad\u0006M\u0001\rO\u0016$xJ\u001a4tKR\u001cu\u000e\u001c\u0015\u0005\u001b\t\f\u0019\"\u0001\u0007iCN<V-[4ii\u000e{G.\u0006\u0002\u0002 A\u0019\u0011%!\t\n\u0007\u0005\r\"EA\u0004C_>dW-\u00198\u0002\u0019!\f7o\u00144gg\u0016$8i\u001c7\u0002)!\f7\u000fT5oWB\u0013X\rZ5di&|gnQ8m\u0003\u0019\u0019x\u000e\u001c<fe\"\u001a\u0011C\u00195\u00025Y\fG.\u001b3bi\u0016\fe\u000e\u001a+sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\u0011\u0005E\u0012\u0011IA#\u0003\u0013\u0002B!a\r\u0002>5\u0011\u0011Q\u0007\u0006\u0005\u0003o\tI$A\u0003usB,7OC\u0002\u0002<e\t1a]9m\u0013\u0011\ty$!\u000e\u0003\u0015M#(/^2u)f\u0004X\rC\u0004\u0002DI\u0001\r!!\r\u0002\rM\u001c\u0007.Z7b\u0011\u001d\t9E\u0005a\u0001\u0003?\tqAZ5ui&tw\rC\u0004\u0002LI\u0001\r!!\u0014\u0002!\u0019,\u0017\r^;sKN$\u0015\r^1UsB,\u0007\u0003BA\u001a\u0003\u001fJA!!\u0015\u00026\tAA)\u0019;b)f\u0004X\rK\u0002\u0013E\"\f\u0001e];qKJ$c/\u00197jI\u0006$X-\u00118e)J\fgn\u001d4pe6\u001c6\r[3nCRA\u0011\u0011GA-\u00037\ni\u0006C\u0004\u0002DM\u0001\r!!\r\t\u000f\u0005\u001d3\u00031\u0001\u0002 !9\u00111J\nA\u0002\u00055\u0013bAA\u0017Q\u0001"
)
public interface GeneralizedLinearRegressionBase extends PredictorParams, HasFitIntercept, HasMaxIter, HasTol, HasRegParam, HasWeightCol, HasSolver, HasAggregationDepth, Logging {
   void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$family_$eq(final Param x$1);

   void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$variancePower_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$link_$eq(final Param x$1);

   void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$linkPower_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$linkPredictionCol_$eq(final Param x$1);

   void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$offsetCol_$eq(final Param x$1);

   void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$solver_$eq(final Param x$1);

   // $FF: synthetic method
   StructType org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType);

   Param family();

   // $FF: synthetic method
   static String getFamily$(final GeneralizedLinearRegressionBase $this) {
      return $this.getFamily();
   }

   default String getFamily() {
      return (String)this.$(this.family());
   }

   DoubleParam variancePower();

   // $FF: synthetic method
   static double getVariancePower$(final GeneralizedLinearRegressionBase $this) {
      return $this.getVariancePower();
   }

   default double getVariancePower() {
      return BoxesRunTime.unboxToDouble(this.$(this.variancePower()));
   }

   Param link();

   // $FF: synthetic method
   static String getLink$(final GeneralizedLinearRegressionBase $this) {
      return $this.getLink();
   }

   default String getLink() {
      return (String)this.$(this.link());
   }

   DoubleParam linkPower();

   // $FF: synthetic method
   static double getLinkPower$(final GeneralizedLinearRegressionBase $this) {
      return $this.getLinkPower();
   }

   default double getLinkPower() {
      return BoxesRunTime.unboxToDouble(this.$(this.linkPower()));
   }

   Param linkPredictionCol();

   // $FF: synthetic method
   static String getLinkPredictionCol$(final GeneralizedLinearRegressionBase $this) {
      return $this.getLinkPredictionCol();
   }

   default String getLinkPredictionCol() {
      return (String)this.$(this.linkPredictionCol());
   }

   Param offsetCol();

   // $FF: synthetic method
   static String getOffsetCol$(final GeneralizedLinearRegressionBase $this) {
      return $this.getOffsetCol();
   }

   default String getOffsetCol() {
      return (String)this.$(this.offsetCol());
   }

   // $FF: synthetic method
   static boolean hasWeightCol$(final GeneralizedLinearRegressionBase $this) {
      return $this.hasWeightCol();
   }

   default boolean hasWeightCol() {
      return this.isSet(this.weightCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.weightCol())));
   }

   // $FF: synthetic method
   static boolean hasOffsetCol$(final GeneralizedLinearRegressionBase $this) {
      return $this.hasOffsetCol();
   }

   default boolean hasOffsetCol() {
      return this.isSet(this.offsetCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.offsetCol())));
   }

   // $FF: synthetic method
   static boolean hasLinkPredictionCol$(final GeneralizedLinearRegressionBase $this) {
      return $this.hasLinkPredictionCol();
   }

   default boolean hasLinkPredictionCol() {
      return this.isDefined(this.linkPredictionCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.linkPredictionCol())));
   }

   Param solver();

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final GeneralizedLinearRegressionBase $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      label41: {
         label37: {
            String var10000 = ((String)this.$(this.family())).toLowerCase(Locale.ROOT);
            String var4 = "tweedie";
            if (var10000 == null) {
               if (var4 != null) {
                  break label37;
               }
            } else if (!var10000.equals(var4)) {
               break label37;
            }

            if (this.isSet(this.link())) {
               this.logWarning(() -> "When family is tweedie, use param linkPower to specify link function. Setting param link will take no effect.");
            }
            break label41;
         }

         if (this.isSet(this.variancePower())) {
            this.logWarning(() -> "When family is not tweedie, setting param variancePower will take no effect.");
         }

         if (this.isSet(this.linkPower())) {
            this.logWarning(() -> "When family is not tweedie, use param link to specify link function. Setting param linkPower will take no effect.");
         }

         if (this.isSet(this.link())) {
            scala.Predef..MODULE$.require(GeneralizedLinearRegression$.MODULE$.supportedFamilyAndLinkPairs().contains(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(GeneralizedLinearRegression.Family$.MODULE$.fromParams(this)), GeneralizedLinearRegression.Link$.MODULE$.fromParams(this))), () -> {
               Object var10000 = this.$(this.family());
               return "Generalized Linear Regression with " + var10000 + " family does not support " + this.$(this.link()) + " link function.";
            });
         }
      }

      StructType newSchema = this.org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
      if (this.hasOffsetCol()) {
         SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.offsetCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      }

      if (this.hasLinkPredictionCol()) {
         NumericAttribute attr = NumericAttribute$.MODULE$.defaultAttr().withName((String)this.$(this.linkPredictionCol()));
         return SchemaUtils$.MODULE$.appendColumn(newSchema, attr.toStructField());
      } else {
         return newSchema;
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$family$1(final String value) {
      return scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])GeneralizedLinearRegression$.MODULE$.supportedFamilyNames()), value.toLowerCase(Locale.ROOT));
   }

   // $FF: synthetic method
   static boolean $anonfun$link$1(final String value) {
      return scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])GeneralizedLinearRegression$.MODULE$.supportedLinkNames()), value.toLowerCase(Locale.ROOT));
   }

   static void $init$(final GeneralizedLinearRegressionBase $this) {
      $this.org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$family_$eq(new Param($this, "family", "The name of family which is a description of the error distribution to be used in the model. Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])GeneralizedLinearRegression$.MODULE$.supportedFamilyNames()).mkString(", ") + ".", (value) -> BoxesRunTime.boxToBoolean($anonfun$family$1(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$variancePower_$eq(new DoubleParam($this, "variancePower", "The power in the variance function of the Tweedie distribution which characterizes the relationship between the variance and mean of the distribution. Only applicable to the Tweedie family. Supported values: 0 and [1, Inf).", (JFunction1.mcZD.sp)(x) -> x >= (double)1.0F || x == (double)0.0F));
      $this.org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$link_$eq(new Param($this, "link", "The name of link function which provides the relationship between the linear predictor and the mean of the distribution function. Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])GeneralizedLinearRegression$.MODULE$.supportedLinkNames()).mkString(", "), (value) -> BoxesRunTime.boxToBoolean($anonfun$link$1(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$linkPower_$eq(new DoubleParam($this, "linkPower", "The index in the power link function. Only applicable to the Tweedie family."));
      $this.org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$linkPredictionCol_$eq(new Param($this, "linkPredictionCol", "link prediction (linear predictor) column name", scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$offsetCol_$eq(new Param($this, "offsetCol", "The offset column name. If this is not set or empty, we treat all instance offsets as 0.0", scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$solver_$eq(new Param($this, "solver", "The solver algorithm for optimization. Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])GeneralizedLinearRegression$.MODULE$.supportedSolvers()).mkString(", ") + ". (Default irls)", ParamValidators$.MODULE$.inArray((Object)GeneralizedLinearRegression$.MODULE$.supportedSolvers()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.family().$minus$greater(GeneralizedLinearRegression.Gaussian$.MODULE$.name()), $this.variancePower().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(25)), $this.tol().$minus$greater(BoxesRunTime.boxToDouble(1.0E-6)), $this.regParam().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.solver().$minus$greater(GeneralizedLinearRegression$.MODULE$.IRLS())}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
