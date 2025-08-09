package org.apache.spark.mllib.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.MatrixUDT;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.VectorUDT;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LabeledPoint$;
import org.apache.spark.rdd.PartitionwiseSampledRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.execution.datasources.DataSource;
import org.apache.spark.sql.execution.datasources.text.TextFileFormat;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.random.BernoulliCellSampler;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ClassTag;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class MLUtils$ implements Logging {
   public static final MLUtils$ MODULE$ = new MLUtils$();
   private static double EPSILON;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

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

   public Dataset convertVectorColumnsToML(final Dataset dataset, final String... cols) {
      return this.convertVectorColumnsToML(dataset, (Seq).MODULE$.wrapRefArray((Object[])cols));
   }

   public Dataset convertVectorColumnsFromML(final Dataset dataset, final String... cols) {
      return this.convertVectorColumnsFromML(dataset, (Seq).MODULE$.wrapRefArray((Object[])cols));
   }

   public Dataset convertMatrixColumnsToML(final Dataset dataset, final String... cols) {
      return this.convertMatrixColumnsToML(dataset, (Seq).MODULE$.wrapRefArray((Object[])cols));
   }

   public Dataset convertMatrixColumnsFromML(final Dataset dataset, final String... cols) {
      return this.convertMatrixColumnsFromML(dataset, (Seq).MODULE$.wrapRefArray((Object[])cols));
   }

   private double EPSILON$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            double eps;
            for(eps = (double)1.0F; (double)1.0F + eps / (double)2.0F != (double)1.0F; eps /= (double)2.0F) {
            }

            EPSILON = eps;
            bitmap$0 = true;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return EPSILON;
   }

   public double EPSILON() {
      return !bitmap$0 ? this.EPSILON$lzycompute() : EPSILON;
   }

   public RDD loadLibSVMFile(final SparkContext sc, final String path, final int numFeatures, final int minPartitions) {
      RDD parsed = this.parseLibSVMFile(sc, path, minPartitions);
      int var10000;
      if (numFeatures > 0) {
         var10000 = numFeatures;
      } else {
         parsed.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY());
         var10000 = this.computeNumFeatures(parsed);
      }

      int d = var10000;
      return parsed.map((x0$1) -> {
         if (x0$1 != null) {
            double label = BoxesRunTime.unboxToDouble(x0$1._1());
            int[] indices = (int[])x0$1._2();
            double[] values = (double[])x0$1._3();
            return new LabeledPoint(label, Vectors$.MODULE$.sparse(d, indices, values));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
   }

   public int computeNumFeatures(final RDD rdd) {
      return BoxesRunTime.unboxToInt(rdd.map((x0$1) -> BoxesRunTime.boxToInteger($anonfun$computeNumFeatures$1(x0$1)), scala.reflect.ClassTag..MODULE$.Int()).reduce((JFunction2.mcIII.sp)(x, y) -> scala.math.package..MODULE$.max(x, y))) + 1;
   }

   public RDD parseLibSVMFile(final SparkContext sc, final String path, final int minPartitions) {
      return sc.textFile(path, minPartitions).map((x$1) -> x$1.trim(), scala.reflect.ClassTag..MODULE$.apply(String.class)).filter((line) -> BoxesRunTime.boxToBoolean($anonfun$parseLibSVMFile$2(line))).map((line) -> MODULE$.parseLibSVMRecord(line), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
   }

   public RDD parseLibSVMFile(final SparkSession sparkSession, final Seq paths, final scala.collection.immutable.Map options) {
      String x$3 = TextFileFormat.class.getName();
      scala.collection.immutable.Map x$4 = (scala.collection.immutable.Map)options.$plus$plus((IterableOnce)scala.Predef..MODULE$.Map().apply(.MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.sql.execution.datasources.DataSource..MODULE$.GLOB_PATHS_KEY()), "false")}))));
      Option x$5 = org.apache.spark.sql.execution.datasources.DataSource..MODULE$.apply$default$4();
      Seq x$6 = org.apache.spark.sql.execution.datasources.DataSource..MODULE$.apply$default$5();
      Option x$7 = org.apache.spark.sql.execution.datasources.DataSource..MODULE$.apply$default$6();
      Option x$8 = org.apache.spark.sql.execution.datasources.DataSource..MODULE$.apply$default$8();
      Dataset lines = sparkSession.baseRelationToDataFrame((new DataSource(sparkSession, x$3, paths, x$5, x$6, x$7, x$4, x$8)).resolveRelation(false)).select("value", scala.collection.immutable.Nil..MODULE$);
      return lines.select(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.trim(lines.sparkSession().implicits().StringToColumn(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"value"})))).$(scala.collection.immutable.Nil..MODULE$)).as("line")}))).filter(org.apache.spark.sql.functions..MODULE$.not(org.apache.spark.sql.functions..MODULE$.length(lines.sparkSession().implicits().StringToColumn(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"line"})))).$(scala.collection.immutable.Nil..MODULE$)).$eq$eq$eq(BoxesRunTime.boxToInteger(0)).or(lines.sparkSession().implicits().StringToColumn(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"line"})))).$(scala.collection.immutable.Nil..MODULE$).startsWith("#")))).as(lines.sparkSession().implicits().newStringEncoder()).rdd().map((line) -> MODULE$.parseLibSVMRecord(line), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
   }

   public Tuple3 parseLibSVMRecord(final String line) {
      String[] items = scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(line), ' ');
      double label = scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString((String)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])items))));
      Tuple2 var7 = scala.collection.ArrayOps..MODULE$.unzip$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.tail$extension(scala.Predef..MODULE$.refArrayOps((Object[])items))), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$parseLibSVMRecord$1(x$2)))), (item) -> {
         String[] indexAndValue = scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(item), ':');
         int index = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(indexAndValue[0])) - 1;
         double value = scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(indexAndValue[1]));
         return new Tuple2.mcID.sp(index, value);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Double());
      if (var7 != null) {
         int[] indices = (int[])var7._1();
         double[] values = (double[])var7._2();
         Tuple2 var6 = new Tuple2(indices, values);
         int[] indices = (int[])var6._1();
         double[] values = (double[])var6._2();
         IntRef previous = IntRef.create(-1);
         int i = 0;

         for(int indicesLength = indices.length; i < indicesLength; ++i) {
            int current = indices[i];
            scala.Predef..MODULE$.require(current > previous.elem, () -> "indices should be one-based and in ascending order; found current=" + current + ", previous=" + previous.elem + "; line=\"" + line + "\"");
            previous.elem = current;
         }

         return new Tuple3(BoxesRunTime.boxToDouble(label), indices, values);
      } else {
         throw new MatchError(var7);
      }
   }

   public RDD loadLibSVMFile(final SparkContext sc, final String path, final int numFeatures) {
      return this.loadLibSVMFile(sc, path, numFeatures, sc.defaultMinPartitions());
   }

   public RDD loadLibSVMFile(final SparkContext sc, final String path) {
      return this.loadLibSVMFile(sc, path, -1);
   }

   public void saveAsLibSVMFile(final RDD data, final String dir) {
      RDD dataStr = data.map((x0$1) -> {
         if (x0$1 != null) {
            double label = x0$1.label();
            Vector features = x0$1.features();
            StringBuilder sb = new StringBuilder(Double.toString(label));
            features.foreachActive((JFunction2.mcVID.sp)(x0$2, x1$1) -> {
               Tuple2.mcID.sp var5 = new Tuple2.mcID.sp(x0$2, x1$1);
               if (var5 != null) {
                  int i = ((Tuple2)var5)._1$mcI$sp();
                  double v = ((Tuple2)var5)._2$mcD$sp();
                  sb.$plus$eq(BoxesRunTime.boxToCharacter(' '));
                  sb.$plus$plus$eq(i + 1 + ":" + v);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  throw new MatchError(var5);
               }
            });
            return sb.mkString();
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class));
      dataStr.saveAsTextFile(dir);
   }

   public RDD loadVectors(final SparkContext sc, final String path, final int minPartitions) {
      return sc.textFile(path, minPartitions).map((s) -> Vectors$.MODULE$.parse(s), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public RDD loadVectors(final SparkContext sc, final String path) {
      return sc.textFile(path, sc.defaultMinPartitions()).map((s) -> Vectors$.MODULE$.parse(s), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public RDD loadLabeledPoints(final SparkContext sc, final String path, final int minPartitions) {
      return sc.textFile(path, minPartitions).map((s) -> LabeledPoint$.MODULE$.parse(s), scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
   }

   public RDD loadLabeledPoints(final SparkContext sc, final String dir) {
      return this.loadLabeledPoints(sc, dir, sc.defaultMinPartitions());
   }

   public Tuple2[] kFold(final RDD rdd, final int numFolds, final int seed, final ClassTag evidence$1) {
      return this.kFold(rdd, numFolds, (long)seed, evidence$1);
   }

   public Tuple2[] kFold(final RDD rdd, final int numFolds, final long seed, final ClassTag evidence$2) {
      float numFoldsF = (float)numFolds;
      return (Tuple2[])scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), numFolds).map((fold) -> $anonfun$kFold$1(numFoldsF, rdd, seed, evidence$2, BoxesRunTime.unboxToInt(fold))).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public Tuple2[] kFold(final Dataset df, final int numFolds, final String foldColName) {
      Column foldCol = df.col(foldColName);
      UserDefinedFunction checker = org.apache.spark.sql.functions..MODULE$.udf((JFunction1.mcZI.sp)(foldNum) -> {
         if (foldNum >= 0 && foldNum < numFolds) {
            return true;
         } else {
            throw new SparkException("Fold number must be in range [0, " + numFolds + "), but got " + foldNum + ".");
         }
      }, ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Boolean(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Int());
      return (Tuple2[])scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numFolds).map((fold) -> $anonfun$kFold$3(df, checker, foldCol, foldColName, BoxesRunTime.unboxToInt(fold))).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public Vector appendBias(final Vector vector) {
      if (vector instanceof DenseVector var4) {
         double[] inputValues = var4.values();
         int inputLength = inputValues.length;
         double[] outputValues = (double[])scala.Array..MODULE$.ofDim(inputLength + 1, scala.reflect.ClassTag..MODULE$.Double());
         System.arraycopy(inputValues, 0, outputValues, 0, inputLength);
         outputValues[inputLength] = (double)1.0F;
         return Vectors$.MODULE$.dense(outputValues);
      } else if (vector instanceof SparseVector var8) {
         double[] inputValues = var8.values();
         int[] inputIndices = var8.indices();
         int inputValuesLength = inputValues.length;
         int dim = var8.size();
         double[] outputValues = (double[])scala.Array..MODULE$.ofDim(inputValuesLength + 1, scala.reflect.ClassTag..MODULE$.Double());
         int[] outputIndices = (int[])scala.Array..MODULE$.ofDim(inputValuesLength + 1, scala.reflect.ClassTag..MODULE$.Int());
         System.arraycopy(inputValues, 0, outputValues, 0, inputValuesLength);
         System.arraycopy(inputIndices, 0, outputIndices, 0, inputValuesLength);
         outputValues[inputValuesLength] = (double)1.0F;
         outputIndices[inputValuesLength] = dim;
         return Vectors$.MODULE$.sparse(dim + 1, outputIndices, outputValues);
      } else {
         throw new IllegalArgumentException("Do not support vector type " + vector.getClass());
      }
   }

   public Dataset convertVectorColumnsToML(final Dataset dataset, final Seq cols) {
      StructType schema = dataset.schema();
      Set colSet = cols.nonEmpty() ? ((IterableOnceOps)cols.flatMap((c) -> {
         DataType dataType = schema.apply(c).dataType();
         Class var10000 = dataType.getClass();
         Class var3 = VectorUDT.class;
         if (var10000 == null) {
            if (var3 == null) {
               return new Some(c);
            }
         } else if (var10000.equals(var3)) {
            return new Some(c);
         }

         boolean var6;
         label22: {
            label21: {
               var5 = scala.Predef..MODULE$;
               Class var10001 = dataType.getClass();
               Class var4 = org.apache.spark.ml.linalg.VectorUDT.class;
               if (var10001 == null) {
                  if (var4 == null) {
                     break label21;
                  }
               } else if (var10001.equals(var4)) {
                  break label21;
               }

               var6 = false;
               break label22;
            }

            var6 = true;
         }

         var5.require(var6, () -> "Column " + c + " must be old Vector type to be converted to new type but got " + dataType + ".");
         return scala.None..MODULE$;
      })).toSet() : scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$convertVectorColumnsToML$3(x$4)))), (x$5) -> x$5.name(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toSet();
      if (colSet.isEmpty()) {
         return dataset.toDF();
      } else {
         this.logWarning((Function0)(() -> "Vector column conversion has serialization overhead. Please migrate your datasets and workflows to use the spark.ml package."));
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (v) -> v.asML();
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
               return $m$untyped.staticClass("org.apache.spark.mllib.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         UserDefinedFunction convertToML = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
         Column[] exprs = (Column[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (field) -> {
            String c = field.name();
            return colSet.contains(c) ? convertToML.apply(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(c)}))).as(c, field.metadata()) : org.apache.spark.sql.functions..MODULE$.col(c);
         }, scala.reflect.ClassTag..MODULE$.apply(Column.class));
         return dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(exprs).toImmutableArraySeq());
      }
   }

   public Dataset convertVectorColumnsFromML(final Dataset dataset, final Seq cols) {
      StructType schema = dataset.schema();
      Set colSet = cols.nonEmpty() ? ((IterableOnceOps)cols.flatMap((c) -> {
         DataType dataType = schema.apply(c).dataType();
         Class var10000 = dataType.getClass();
         Class var3 = org.apache.spark.ml.linalg.VectorUDT.class;
         if (var10000 == null) {
            if (var3 == null) {
               return new Some(c);
            }
         } else if (var10000.equals(var3)) {
            return new Some(c);
         }

         boolean var6;
         label22: {
            label21: {
               var5 = scala.Predef..MODULE$;
               Class var10001 = dataType.getClass();
               Class var4 = VectorUDT.class;
               if (var10001 == null) {
                  if (var4 == null) {
                     break label21;
                  }
               } else if (var10001.equals(var4)) {
                  break label21;
               }

               var6 = false;
               break label22;
            }

            var6 = true;
         }

         var5.require(var6, () -> "Column " + c + " must be new Vector type to be converted to old type but got " + dataType + ".");
         return scala.None..MODULE$;
      })).toSet() : scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (x$6) -> BoxesRunTime.boxToBoolean($anonfun$convertVectorColumnsFromML$3(x$6)))), (x$7) -> x$7.name(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toSet();
      if (colSet.isEmpty()) {
         return dataset.toDF();
      } else {
         this.logWarning((Function0)(() -> "Vector column conversion has serialization overhead. Please migrate your datasets and workflows to use the spark.ml package."));
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (v) -> Vectors$.MODULE$.fromML(v);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$2() {
            }
         }

         TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator2$2() {
            }
         }

         UserDefinedFunction convertFromML = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2()));
         Column[] exprs = (Column[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (field) -> {
            String c = field.name();
            return colSet.contains(c) ? convertFromML.apply(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(c)}))).as(c, field.metadata()) : org.apache.spark.sql.functions..MODULE$.col(c);
         }, scala.reflect.ClassTag..MODULE$.apply(Column.class));
         return dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(exprs).toImmutableArraySeq());
      }
   }

   public Dataset convertMatrixColumnsToML(final Dataset dataset, final Seq cols) {
      StructType schema = dataset.schema();
      Set colSet = cols.nonEmpty() ? ((IterableOnceOps)cols.flatMap((c) -> {
         DataType dataType = schema.apply(c).dataType();
         Class var10000 = dataType.getClass();
         Class var3 = MatrixUDT.class;
         if (var10000 == null) {
            if (var3 == null) {
               return new Some(c);
            }
         } else if (var10000.equals(var3)) {
            return new Some(c);
         }

         boolean var6;
         label22: {
            label21: {
               var5 = scala.Predef..MODULE$;
               Class var10001 = dataType.getClass();
               Class var4 = org.apache.spark.ml.linalg.MatrixUDT.class;
               if (var10001 == null) {
                  if (var4 == null) {
                     break label21;
                  }
               } else if (var10001.equals(var4)) {
                  break label21;
               }

               var6 = false;
               break label22;
            }

            var6 = true;
         }

         var5.require(var6, () -> "Column " + c + " must be old Matrix type to be converted to new type but got " + dataType + ".");
         return scala.None..MODULE$;
      })).toSet() : scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$convertMatrixColumnsToML$3(x$8)))), (x$9) -> x$9.name(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toSet();
      if (colSet.isEmpty()) {
         return dataset.toDF();
      } else {
         this.logWarning((Function0)(() -> "Matrix column conversion has serialization overhead. Please migrate your datasets and workflows to use the spark.ml package."));
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (v) -> v.asML();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Matrix").asType().toTypeConstructor();
            }

            public $typecreator1$3() {
            }
         }

         TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.linalg.Matrix").asType().toTypeConstructor();
            }

            public $typecreator2$3() {
            }
         }

         UserDefinedFunction convertToML = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$3()));
         Column[] exprs = (Column[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (field) -> {
            String c = field.name();
            return colSet.contains(c) ? convertToML.apply(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(c)}))).as(c, field.metadata()) : org.apache.spark.sql.functions..MODULE$.col(c);
         }, scala.reflect.ClassTag..MODULE$.apply(Column.class));
         return dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(exprs).toImmutableArraySeq());
      }
   }

   public Dataset convertMatrixColumnsFromML(final Dataset dataset, final Seq cols) {
      StructType schema = dataset.schema();
      Set colSet = cols.nonEmpty() ? ((IterableOnceOps)cols.flatMap((c) -> {
         DataType dataType = schema.apply(c).dataType();
         Class var10000 = dataType.getClass();
         Class var3 = org.apache.spark.ml.linalg.MatrixUDT.class;
         if (var10000 == null) {
            if (var3 == null) {
               return new Some(c);
            }
         } else if (var10000.equals(var3)) {
            return new Some(c);
         }

         boolean var6;
         label22: {
            label21: {
               var5 = scala.Predef..MODULE$;
               Class var10001 = dataType.getClass();
               Class var4 = MatrixUDT.class;
               if (var10001 == null) {
                  if (var4 == null) {
                     break label21;
                  }
               } else if (var10001.equals(var4)) {
                  break label21;
               }

               var6 = false;
               break label22;
            }

            var6 = true;
         }

         var5.require(var6, () -> "Column " + c + " must be new Matrix type to be converted to old type but got " + dataType + ".");
         return scala.None..MODULE$;
      })).toSet() : scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (x$10) -> BoxesRunTime.boxToBoolean($anonfun$convertMatrixColumnsFromML$3(x$10)))), (x$11) -> x$11.name(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toSet();
      if (colSet.isEmpty()) {
         return dataset.toDF();
      } else {
         this.logWarning((Function0)(() -> "Matrix column conversion has serialization overhead. Please migrate your datasets and workflows to use the spark.ml package."));
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (m) -> Matrices$.MODULE$.fromML(m);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$4 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.linalg.Matrix").asType().toTypeConstructor();
            }

            public $typecreator1$4() {
            }
         }

         TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$4());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$4 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Matrix").asType().toTypeConstructor();
            }

            public $typecreator2$4() {
            }
         }

         UserDefinedFunction convertFromML = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$4()));
         Column[] exprs = (Column[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (field) -> {
            String c = field.name();
            return colSet.contains(c) ? convertFromML.apply(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(c)}))).as(c, field.metadata()) : org.apache.spark.sql.functions..MODULE$.col(c);
         }, scala.reflect.ClassTag..MODULE$.apply(Column.class));
         return dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(exprs).toImmutableArraySeq());
      }
   }

   public double fastSquaredDistance(final Vector v1, final double norm1, final Vector v2, final double norm2, final double precision) {
      int n = v1.size();
      scala.Predef..MODULE$.require(v2.size() == n, () -> "Both vectors should have same length, found v1 is " + n + " while v2 is " + v2.size());
      scala.Predef..MODULE$.require(norm1 >= (double)0.0F && norm2 >= (double)0.0F, () -> "Both norms should be greater or equal to 0.0, found norm1=" + norm1 + ", norm2=" + norm2);
      double sqDist = (double)0.0F;
      if (v1 instanceof DenseVector && v2 instanceof DenseVector) {
         sqDist = Vectors$.MODULE$.sqdist(v1, v2);
      } else {
         double sumSquaredNorm = norm1 * norm1 + norm2 * norm2;
         double normDiff = norm1 - norm2;
         double precisionBound1 = (double)2.0F * this.EPSILON() * sumSquaredNorm / (normDiff * normDiff + this.EPSILON());
         if (precisionBound1 < precision) {
            sqDist = sumSquaredNorm - (double)2.0F * BLAS$.MODULE$.dot(v1, v2);
         } else {
            double dotValue = BLAS$.MODULE$.dot(v1, v2);
            sqDist = scala.math.package..MODULE$.max(sumSquaredNorm - (double)2.0F * dotValue, (double)0.0F);
            double precisionBound2 = this.EPSILON() * (sumSquaredNorm + (double)2.0F * scala.math.package..MODULE$.abs(dotValue)) / (sqDist + this.EPSILON());
            if (precisionBound2 > precision) {
               sqDist = Vectors$.MODULE$.sqdist(v1, v2);
            }
         }
      }

      return sqDist;
   }

   public double fastSquaredDistance$default$5() {
      return 1.0E-6;
   }

   public double log1pExp(final double x) {
      return x > (double)0 ? x + scala.math.package..MODULE$.log1p(scala.math.package..MODULE$.exp(-x)) : scala.math.package..MODULE$.log1p(scala.math.package..MODULE$.exp(x));
   }

   public void optimizerFailed(final Instrumentation instr, final Class optimizerClass) {
      MessageWithContext msg = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", " failed."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.OPTIMIZER_CLASS_NAME..MODULE$, optimizerClass.getName())})));
      instr.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msg));
      throw new SparkException(msg.message());
   }

   // $FF: synthetic method
   public static final int $anonfun$computeNumFeatures$1(final Tuple3 x0$1) {
      if (x0$1 != null) {
         int[] indices = (int[])x0$1._2();
         return BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.lastOption$extension(scala.Predef..MODULE$.intArrayOps(indices)).getOrElse((JFunction0.mcI.sp)() -> 0));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseLibSVMFile$2(final String line) {
      return !line.isEmpty() && !line.startsWith("#");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parseLibSVMRecord$1(final String x$2) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$2));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$kFold$1(final float numFoldsF$1, final RDD rdd$1, final long seed$1, final ClassTag evidence$2$1, final int fold) {
      BernoulliCellSampler sampler = new BernoulliCellSampler((double)((float)(fold - 1) / numFoldsF$1), (double)((float)fold / numFoldsF$1), false);
      PartitionwiseSampledRDD validation = new PartitionwiseSampledRDD(rdd$1, sampler, true, seed$1, evidence$2$1, evidence$2$1);
      PartitionwiseSampledRDD training = new PartitionwiseSampledRDD(rdd$1, sampler.cloneComplement(), true, seed$1, evidence$2$1, evidence$2$1);
      return new Tuple2(training, validation);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$kFold$3(final Dataset df$1, final UserDefinedFunction checker$1, final Column foldCol$1, final String foldColName$1, final int fold) {
      RDD training = df$1.filter(checker$1.apply(.MODULE$.wrapRefArray((Object[])(new Column[]{foldCol$1}))).$amp$amp(foldCol$1.$eq$bang$eq(BoxesRunTime.boxToInteger(fold)))).drop(foldColName$1).rdd();
      RDD validation = df$1.filter(checker$1.apply(.MODULE$.wrapRefArray((Object[])(new Column[]{foldCol$1}))).$amp$amp(foldCol$1.$eq$eq$eq(BoxesRunTime.boxToInteger(fold)))).drop(foldColName$1).rdd();
      if (training.isEmpty()) {
         throw new SparkException("The training data at fold " + fold + " is empty.");
      } else if (validation.isEmpty()) {
         throw new SparkException("The validation data at fold " + fold + " is empty.");
      } else {
         return new Tuple2(training, validation);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertVectorColumnsToML$3(final StructField x$4) {
      boolean var2;
      label23: {
         Class var10000 = x$4.dataType().getClass();
         Class var1 = VectorUDT.class;
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertVectorColumnsFromML$3(final StructField x$6) {
      boolean var2;
      label23: {
         Class var10000 = x$6.dataType().getClass();
         Class var1 = org.apache.spark.ml.linalg.VectorUDT.class;
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertMatrixColumnsToML$3(final StructField x$8) {
      boolean var2;
      label23: {
         Class var10000 = x$8.dataType().getClass();
         Class var1 = MatrixUDT.class;
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertMatrixColumnsFromML$3(final StructField x$10) {
      boolean var2;
      label23: {
         Class var10000 = x$10.dataType().getClass();
         Class var1 = org.apache.spark.ml.linalg.MatrixUDT.class;
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   private MLUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
