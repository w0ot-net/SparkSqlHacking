package org.apache.spark.ml.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.classification.ClassifierParams;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.functions.;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DoubleType;
import org.apache.spark.sql.types.FloatType;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.SeqOps;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class DatasetUtils$ implements Logging {
   public static final DatasetUtils$ MODULE$ = new DatasetUtils$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Column checkNonNanValues(final String colName, final String displayed) {
      Column casted = .MODULE$.col(colName).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
      return .MODULE$.when(casted.isNull().$bar$bar(casted.isNaN()), .MODULE$.raise_error(.MODULE$.lit(displayed + " MUST NOT be Null or NaN"))).when(casted.$eq$eq$eq(BoxesRunTime.boxToDouble(Double.NEGATIVE_INFINITY)).$bar$bar(casted.$eq$eq$eq(BoxesRunTime.boxToDouble(Double.POSITIVE_INFINITY))), .MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit(displayed + " MUST NOT be Infinity, but got "), casted}))))).otherwise(casted);
   }

   public Column checkRegressionLabels(final String labelCol) {
      return this.checkNonNanValues(labelCol, "Labels");
   }

   public Column checkClassificationLabels(final String labelCol, final Option numClasses) {
      Column casted = .MODULE$.col(labelCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
      if (numClasses instanceof Some var6) {
         int var7 = BoxesRunTime.unboxToInt(var6.value());
         if (2 == var7) {
            return .MODULE$.when(casted.isNull().$bar$bar(casted.isNaN()), .MODULE$.raise_error(.MODULE$.lit("Labels MUST NOT be Null or NaN"))).when(casted.$eq$bang$eq(BoxesRunTime.boxToInteger(0)).$amp$amp(casted.$eq$bang$eq(BoxesRunTime.boxToInteger(1))), .MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit("Labels MUST be in {0, 1}, but got "), casted}))))).otherwise(casted);
         }
      }

      int n = BoxesRunTime.unboxToInt(numClasses.getOrElse((JFunction0.mcI.sp)() -> Integer.MAX_VALUE));
      scala.Predef..MODULE$.require(0 < n && n <= Integer.MAX_VALUE);
      return .MODULE$.when(casted.isNull().$bar$bar(casted.isNaN()), .MODULE$.raise_error(.MODULE$.lit("Labels MUST NOT be Null or NaN"))).when(casted.$less(BoxesRunTime.boxToInteger(0)).$bar$bar(casted.$greater$eq(BoxesRunTime.boxToInteger(n))), .MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit("Labels MUST be in [0, " + n + "), but got "), casted}))))).when(casted.$eq$bang$eq(casted.cast(org.apache.spark.sql.types.IntegerType..MODULE$)), .MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit("Labels MUST be Integers, but got "), casted}))))).otherwise(casted);
   }

   public Column checkNonNegativeWeights(final String weightCol) {
      Column casted = .MODULE$.col(weightCol).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
      return .MODULE$.when(casted.isNull().$bar$bar(casted.isNaN()), .MODULE$.raise_error(.MODULE$.lit("Weights MUST NOT be Null or NaN"))).when(casted.$less(BoxesRunTime.boxToInteger(0)).$bar$bar(casted.$eq$eq$eq(BoxesRunTime.boxToDouble(Double.POSITIVE_INFINITY))), .MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit("Weights MUST NOT be Negative or Infinity, but got "), casted}))))).otherwise(casted);
   }

   public Column checkNonNegativeWeights(final Option weightCol) {
      if (weightCol instanceof Some var4) {
         String w = (String)var4.value();
         if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(w))) {
            return this.checkNonNegativeWeights(w);
         }
      }

      return .MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F));
   }

   public Column checkNonNanVectors(final Column vectorCol) {
      return .MODULE$.when(vectorCol.isNull(), .MODULE$.raise_error(.MODULE$.lit("Vectors MUST NOT be Null"))).when(.MODULE$.exists(.MODULE$.unwrap_udt(vectorCol).getField("values"), (v) -> v.isNaN().$bar$bar(v.$eq$eq$eq(BoxesRunTime.boxToDouble(Double.NEGATIVE_INFINITY))).$bar$bar(v.$eq$eq$eq(BoxesRunTime.boxToDouble(Double.POSITIVE_INFINITY)))), .MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit("Vector values MUST NOT be NaN or Infinity, but got "), vectorCol.cast(org.apache.spark.sql.types.StringType..MODULE$)}))))).otherwise(vectorCol);
   }

   public Column checkNonNanVectors(final String vectorCol) {
      return this.checkNonNanVectors(.MODULE$.col(vectorCol));
   }

   public RDD extractInstances(final PredictorParams p, final Dataset df, final Option numClasses) {
      Column var10000;
      if (p instanceof ClassifierParams var8) {
         var10000 = this.checkClassificationLabels(var8.getLabelCol(), numClasses);
      } else {
         var10000 = this.checkRegressionLabels(p.getLabelCol());
      }

      Column labelCol = var10000;
      Column weightCol = p instanceof HasWeightCol ? this.checkNonNegativeWeights(p.get(((HasWeightCol)p).weightCol())) : .MODULE$.lit(BoxesRunTime.boxToDouble((double)1.0F));
      return df.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{labelCol, weightCol, this.checkNonNanVectors(p.getFeaturesCol())}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
               Object l = ((SeqOps)var3.get()).apply(0);
               Object w = ((SeqOps)var3.get()).apply(1);
               Object v = ((SeqOps)var3.get()).apply(2);
               if (l instanceof Double) {
                  double var7 = BoxesRunTime.unboxToDouble(l);
                  if (w instanceof Double) {
                     double var9 = BoxesRunTime.unboxToDouble(w);
                     if (v instanceof Vector) {
                        Vector var11 = (Vector)v;
                        return new Instance(var7, var9, var11);
                     }
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Instance.class));
   }

   public Option extractInstances$default$3() {
      return scala.None..MODULE$;
   }

   public Column columnToVector(final Dataset dataset, final String colName) {
      DataType columnDataType = dataset.schema().apply(colName).dataType();
      if (columnDataType instanceof VectorUDT) {
         return .MODULE$.col(colName);
      } else if (columnDataType instanceof ArrayType) {
         ArrayType var7 = (ArrayType)columnDataType;
         DataType var9 = var7.elementType();
         UserDefinedFunction var18;
         if (var9 instanceof FloatType) {
            functions var10000 = .MODULE$;
            Function1 var10001 = (vector) -> {
               double[] inputArray = (double[])scala.Array..MODULE$.ofDim(vector.size(), scala.reflect.ClassTag..MODULE$.Double());
               vector.indices().foreach$mVc$sp((JFunction1.mcVI.sp)(idx) -> inputArray[idx] = (double)BoxesRunTime.unboxToFloat(vector.apply(idx)));
               return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(inputArray);
            };
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator1$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator1$1() {
               }
            }

            TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator2$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Float").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
               }

               public $typecreator2$1() {
               }
            }

            var18 = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
         } else {
            if (!(var9 instanceof DoubleType)) {
               throw new IllegalArgumentException("Array[" + var9 + "] column cannot be cast to Vector");
            }

            functions var19 = .MODULE$;
            Function1 var20 = (vector) -> org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])vector.toArray(scala.reflect.ClassTag..MODULE$.Double()));
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator3$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator3$1() {
               }
            }

            TypeTags.TypeTag var21 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1());
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator4$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
               }

               public $typecreator4$1() {
               }
            }

            var18 = var19.udf(var20, var21, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator4$1()));
         }

         UserDefinedFunction transferUDF = var18;
         return transferUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col(colName)})));
      } else {
         throw new IllegalArgumentException(columnDataType + " column cannot be cast to Vector");
      }
   }

   public RDD columnToOldVector(final Dataset dataset, final String colName) {
      return dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.columnToVector(dataset, colName)}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(1) == 0) {
               Object point = ((SeqOps)var3.get()).apply(0);
               if (point instanceof Vector) {
                  Vector var5 = (Vector)point;
                  return Vectors$.MODULE$.fromML(var5);
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.linalg.Vector.class));
   }

   public int getNumClasses(final Dataset dataset, final String labelCol, final int maxNumClasses) {
      Option var5 = MetadataUtils$.MODULE$.getNumClasses(dataset.schema().apply(labelCol));
      if (var5 instanceof Some var6) {
         int n = BoxesRunTime.unboxToInt(var6.value());
         if (true) {
            return n;
         }
      }

      if (scala.None..MODULE$.equals(var5)) {
         Row[] maxLabelRow = (Row[])dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.max(this.checkClassificationLabels(labelCol, new Some(BoxesRunTime.boxToInteger(maxNumClasses))))}))).take(1);
         if (!scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])maxLabelRow)) && maxLabelRow[0].get(0) != null) {
            double maxDoubleLabel = ((Row)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])maxLabelRow))).getDouble(0);
            scala.Predef..MODULE$.require(scala.runtime.RichDouble..MODULE$.isValidInt$extension(scala.Predef..MODULE$.doubleWrapper(maxDoubleLabel + (double)1)), () -> "Classifier found max label value = " + maxDoubleLabel + " but requires integers in range [0, ... " + Integer.MAX_VALUE + ")");
            int numClasses = (int)maxDoubleLabel + 1;
            scala.Predef..MODULE$.require(numClasses <= maxNumClasses, () -> "Classifier inferred " + numClasses + " from label values in column " + labelCol + ", but this exceeded the max numClasses (" + maxNumClasses + ") allowed to be inferred from values.  To avoid this error for labels with > " + maxNumClasses + " classes, specify numClasses explicitly in the metadata; this can be done by applying StringIndexer to the label column.");
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " inferred ", " classes for labelCol=", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, MODULE$.getClass().getCanonicalName()), new MDC(org.apache.spark.internal.LogKeys.NUM_CLASSES..MODULE$, BoxesRunTime.boxToInteger(numClasses)), new MDC(org.apache.spark.internal.LogKeys.LABEL_COLUMN..MODULE$, labelCol)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" since numClasses was not specified in the column metadata."})))).log(scala.collection.immutable.Nil..MODULE$))));
            return numClasses;
         } else {
            throw new SparkException("ML algorithm was given empty dataset.");
         }
      } else {
         throw new MatchError(var5);
      }
   }

   public int getNumClasses$default$3() {
      return 100;
   }

   public int getNumFeatures(final Dataset dataset, final String vectorCol) {
      return BoxesRunTime.unboxToInt(MetadataUtils$.MODULE$.getNumFeatures(dataset.schema().apply(vectorCol)).getOrElse((JFunction0.mcI.sp)() -> ((Vector)((Row)dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{MODULE$.columnToVector(dataset, vectorCol)}))).head()).getAs(0)).size()));
   }

   private DatasetUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
