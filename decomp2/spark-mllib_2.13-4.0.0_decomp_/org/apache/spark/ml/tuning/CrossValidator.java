package org.apache.spark.ml.tuning;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.evaluation.Evaluator;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasCollectSubModels;
import org.apache.spark.ml.param.shared.HasParallelism;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.IntegerType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.IterableOnceOps;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t5c\u0001\u0002\u0011\"\u00011B\u0001b\u0014\u0001\u0003\u0006\u0004%\t\u0005\u0015\u0005\tO\u0002\u0011\t\u0011)A\u0005#\")\u0011\u000e\u0001C\u0001U\")\u0011\u000e\u0001C\u0001c\")1\u000f\u0001C\u0001i\"9\u0011q\u0002\u0001\u0005\u0002\u0005E\u0001bBA\u0013\u0001\u0011\u0005\u0011q\u0005\u0005\b\u0003s\u0001A\u0011AA\u001e\u0011\u001d\t9\u0005\u0001C\u0001\u0003\u0013Bq!!\u0017\u0001\t\u0003\tY\u0006C\u0004\u0002f\u0001!\t!a\u001a\t\u000f\u0005E\u0004\u0001\"\u0001\u0002t!9\u0011q\u0010\u0001\u0005B\u0005\u0005\u0005bBAO\u0001\u0011\u0005\u0013q\u0014\u0005\b\u0003g\u0003A\u0011IA[\u0011\u001d\ti\f\u0001C!\u0003\u007f;q!a4\"\u0011\u0003\t\tN\u0002\u0004!C!\u0005\u00111\u001b\u0005\u0007SJ!\t!!=\t\u000f\u0005M(\u0003\"\u0011\u0002v\"9\u0011q \n\u0005B\t\u0005aa\u0002B\u0005%\u0001\u0011\"1\u0002\u0005\n\u0005\u001b1\"\u0011!Q\u0001\n-Da!\u001b\f\u0005\u0002\t=\u0001b\u0002B\f-\u0011E#\u0011\u0004\u0004\u0007\u0005G\u0011BA!\n\t\r%TB\u0011\u0001B\u0014\u0011%\u0011YC\u0007b\u0001\n\u0013\u0011i\u0003\u0003\u0005\u0003:i\u0001\u000b\u0011\u0002B\u0018\u0011\u001d\tyP\u0007C!\u0005wA\u0011Ba\u0010\u0013\u0003\u0003%IA!\u0011\u0003\u001d\r\u0013xn]:WC2LG-\u0019;pe*\u0011!eI\u0001\u0007iVt\u0017N\\4\u000b\u0005\u0011*\u0013AA7m\u0015\t1s%A\u0003ta\u0006\u00148N\u0003\u0002)S\u00051\u0011\r]1dQ\u0016T\u0011AK\u0001\u0004_J<7\u0001A\n\b\u00015*\u0004\bQ\"J!\rqs&M\u0007\u0002G%\u0011\u0001g\t\u0002\n\u000bN$\u0018.\\1u_J\u0004\"AM\u001a\u000e\u0003\u0005J!\u0001N\u0011\u0003'\r\u0013xn]:WC2LG-\u0019;pe6{G-\u001a7\u0011\u0005I2\u0014BA\u001c\"\u0005Q\u0019%o\\:t-\u0006d\u0017\u000eZ1u_J\u0004\u0016M]1ngB\u0011\u0011HP\u0007\u0002u)\u00111\bP\u0001\u0007g\"\f'/\u001a3\u000b\u0005u\u001a\u0013!\u00029be\u0006l\u0017BA ;\u00059A\u0015m\u001d)be\u0006dG.\u001a7jg6\u0004\"!O!\n\u0005\tS$a\u0005%bg\u000e{G\u000e\\3diN+(-T8eK2\u001c\bC\u0001#H\u001b\u0005)%B\u0001$$\u0003\u0011)H/\u001b7\n\u0005!+%AC'M/JLG/\u00192mKB\u0011!*T\u0007\u0002\u0017*\u0011A*J\u0001\tS:$XM\u001d8bY&\u0011aj\u0013\u0002\b\u0019><w-\u001b8h\u0003\r)\u0018\u000eZ\u000b\u0002#B\u0011!k\u0017\b\u0003'f\u0003\"\u0001V,\u000e\u0003US!AV\u0016\u0002\rq\u0012xn\u001c;?\u0015\u0005A\u0016!B:dC2\f\u0017B\u0001.X\u0003\u0019\u0001&/\u001a3fM&\u0011A,\u0018\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005i;\u0006fA\u0001`KB\u0011\u0001mY\u0007\u0002C*\u0011!-J\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00013b\u0005\u0015\u0019\u0016N\\2fC\u00051\u0017!B\u0019/i9\u0002\u0014\u0001B;jI\u0002B3AA0f\u0003\u0019a\u0014N\\5u}Q\u00111\u000e\u001c\t\u0003e\u0001AQaT\u0002A\u0002EC3\u0001\\0fQ\r\u0019ql\\\u0011\u0002a\u0006)\u0011G\f\u001a/aQ\t1\u000eK\u0002\u0005?>\fAb]3u\u000bN$\u0018.\\1u_J$\"!\u001e<\u000e\u0003\u0001AQa^\u0003A\u0002a\fQA^1mk\u0016\u0004$!\u001f?\u0011\u00079z#\u0010\u0005\u0002|y2\u0001A!C?w\u0003\u0003\u0005\tQ!\u0001\u007f\u0005\ryF%M\t\u0004\u007f\u0006\u001d\u0001\u0003BA\u0001\u0003\u0007i\u0011aV\u0005\u0004\u0003\u000b9&a\u0002(pi\"Lgn\u001a\t\u0005\u0003\u0003\tI!C\u0002\u0002\f]\u00131!\u00118zQ\r)ql\\\u0001\u0016g\u0016$Xi\u001d;j[\u0006$xN\u001d)be\u0006lW*\u00199t)\r)\u00181\u0003\u0005\u0007o\u001a\u0001\r!!\u0006\u0011\r\u0005\u0005\u0011qCA\u000e\u0013\r\tIb\u0016\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0005\u0003;\ty\"D\u0001=\u0013\r\t\t\u0003\u0010\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001aaaX8\u0002\u0019M,G/\u0012<bYV\fGo\u001c:\u0015\u0007U\fI\u0003\u0003\u0004x\u000f\u0001\u0007\u00111\u0006\t\u0005\u0003[\t\u0019$\u0004\u0002\u00020)\u0019\u0011\u0011G\u0012\u0002\u0015\u00154\u0018\r\\;bi&|g.\u0003\u0003\u00026\u0005=\"!C#wC2,\u0018\r^8sQ\r9ql\\\u0001\fg\u0016$h*^7G_2$7\u000fF\u0002v\u0003{Aaa\u001e\u0005A\u0002\u0005}\u0002\u0003BA\u0001\u0003\u0003J1!a\u0011X\u0005\rIe\u000e\u001e\u0015\u0004\u0011}{\u0017aB:fiN+W\r\u001a\u000b\u0004k\u0006-\u0003BB<\n\u0001\u0004\ti\u0005\u0005\u0003\u0002\u0002\u0005=\u0013bAA)/\n!Aj\u001c8hQ\u0011Iq,!\u0016\"\u0005\u0005]\u0013!\u0002\u001a/a9\u0002\u0014AC:fi\u001a{G\u000eZ\"pYR\u0019Q/!\u0018\t\u000b]T\u0001\u0019A))\t)y\u0016\u0011M\u0011\u0003\u0003G\nQa\r\u00182]A\nab]3u!\u0006\u0014\u0018\r\u001c7fY&\u001cX\u000eF\u0002v\u0003SBaa^\u0006A\u0002\u0005}\u0002\u0006B\u0006`\u0003[\n#!a\u001c\u0002\u000bIr3G\f\u0019\u0002'M,GoQ8mY\u0016\u001cGoU;c\u001b>$W\r\\:\u0015\u0007U\f)\b\u0003\u0004x\u0019\u0001\u0007\u0011q\u000f\t\u0005\u0003\u0003\tI(C\u0002\u0002|]\u0013qAQ8pY\u0016\fg\u000e\u000b\u0003\r?\u00065\u0014a\u00014jiR\u0019\u0011'a!\t\u000f\u0005\u0015U\u00021\u0001\u0002\b\u00069A-\u0019;bg\u0016$\b\u0007BAE\u0003/\u0003b!a#\u0002\u0012\u0006UUBAAG\u0015\r\ty)J\u0001\u0004gFd\u0017\u0002BAJ\u0003\u001b\u0013q\u0001R1uCN,G\u000fE\u0002|\u0003/#1\"!'\u0002\u0004\u0006\u0005\t\u0011!B\u0001}\n\u0019q\f\n\u001a)\t5y\u0016QK\u0001\u0010iJ\fgn\u001d4pe6\u001c6\r[3nCR!\u0011\u0011UAW!\u0011\t\u0019+!+\u000e\u0005\u0005\u0015&\u0002BAT\u0003\u001b\u000bQ\u0001^=qKNLA!a+\u0002&\nQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000f\u0005=f\u00021\u0001\u0002\"\u000611o\u00195f[\u0006D3AD0f\u0003\u0011\u0019w\u000e]=\u0015\u0007-\f9\fC\u0004\u0002:>\u0001\r!a\u0007\u0002\u000b\u0015DHO]1)\u0007=yV-A\u0003xe&$X-\u0006\u0002\u0002BB\u0019A)a1\n\u0007\u0005\u0015WI\u0001\u0005N\u0019^\u0013\u0018\u000e^3sQ\u0011\u0001r,!3\"\u0005\u0005-\u0017!B\u0019/m9\u0002\u0004f\u0001\u0001`_\u0006q1I]8tgZ\u000bG.\u001b3bi>\u0014\bC\u0001\u001a\u0013'\u001d\u0011\u0012Q[An\u0003C\u0004B!!\u0001\u0002X&\u0019\u0011\u0011\\,\u0003\r\u0005s\u0017PU3g!\u0011!\u0015Q\\6\n\u0007\u0005}WI\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004B!a9\u0002n6\u0011\u0011Q\u001d\u0006\u0005\u0003O\fI/\u0001\u0002j_*\u0011\u00111^\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002p\u0006\u0015(\u0001D*fe&\fG.\u001b>bE2,GCAAi\u0003\u0011\u0011X-\u00193\u0016\u0005\u0005]\b\u0003\u0002#\u0002z.L1!a?F\u0005!iEJU3bI\u0016\u0014\b\u0006\u0002\u000b`\u0003\u0013\fA\u0001\\8bIR\u00191Na\u0001\t\r\t\u0015Q\u00031\u0001R\u0003\u0011\u0001\u0018\r\u001e5)\tUy\u0016\u0011\u001a\u0002\u0015\u0007J|7o\u001d,bY&$\u0017\r^8s/JLG/\u001a:\u0014\u0007Y\t\t-\u0001\u0005j]N$\u0018M\\2f)\u0011\u0011\tB!\u0006\u0011\u0007\tMa#D\u0001\u0013\u0011\u0019\u0011i\u0001\u0007a\u0001W\u0006A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0003\u001c\t\u0005\u0002\u0003BA\u0001\u0005;I1Aa\bX\u0005\u0011)f.\u001b;\t\r\t\u0015\u0011\u00041\u0001R\u0005Q\u0019%o\\:t-\u0006d\u0017\u000eZ1u_J\u0014V-\u00193feN\u0019!$a>\u0015\u0005\t%\u0002c\u0001B\n5\u0005I1\r\\1tg:\u000bW.Z\u000b\u0003\u0005_\u0001BA!\r\u000385\u0011!1\u0007\u0006\u0005\u0005k\tI/\u0001\u0003mC:<\u0017b\u0001/\u00034\u0005Q1\r\\1tg:\u000bW.\u001a\u0011\u0015\u0007-\u0014i\u0004\u0003\u0004\u0003\u0006y\u0001\r!U\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005\u0007\u0002BA!\r\u0003F%!!q\tB\u001a\u0005\u0019y%M[3di\"\"!cXAeQ\u0011\tr,!3"
)
public class CrossValidator extends Estimator implements CrossValidatorParams, HasParallelism, HasCollectSubModels, MLWritable {
   private final String uid;
   private BooleanParam collectSubModels;
   private IntParam parallelism;
   private IntParam numFolds;
   private Param foldCol;
   private Param estimator;
   private Param estimatorParamMaps;
   private Param evaluator;
   private LongParam seed;

   public static CrossValidator load(final String path) {
      return CrossValidator$.MODULE$.load(path);
   }

   public static MLReader read() {
      return CrossValidator$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final boolean getCollectSubModels() {
      return HasCollectSubModels.getCollectSubModels$(this);
   }

   public int getParallelism() {
      return HasParallelism.getParallelism$(this);
   }

   public ExecutionContext getExecutionContext() {
      return HasParallelism.getExecutionContext$(this);
   }

   public int getNumFolds() {
      return CrossValidatorParams.getNumFolds$(this);
   }

   public String getFoldCol() {
      return CrossValidatorParams.getFoldCol$(this);
   }

   public Estimator getEstimator() {
      return ValidatorParams.getEstimator$(this);
   }

   public ParamMap[] getEstimatorParamMaps() {
      return ValidatorParams.getEstimatorParamMaps$(this);
   }

   public Evaluator getEvaluator() {
      return ValidatorParams.getEvaluator$(this);
   }

   public StructType transformSchemaImpl(final StructType schema) {
      return ValidatorParams.transformSchemaImpl$(this, schema);
   }

   public void logTuningParams(final Instrumentation instrumentation) {
      ValidatorParams.logTuningParams$(this, instrumentation);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final BooleanParam collectSubModels() {
      return this.collectSubModels;
   }

   public final void org$apache$spark$ml$param$shared$HasCollectSubModels$_setter_$collectSubModels_$eq(final BooleanParam x$1) {
      this.collectSubModels = x$1;
   }

   public IntParam parallelism() {
      return this.parallelism;
   }

   public void org$apache$spark$ml$param$shared$HasParallelism$_setter_$parallelism_$eq(final IntParam x$1) {
      this.parallelism = x$1;
   }

   public IntParam numFolds() {
      return this.numFolds;
   }

   public Param foldCol() {
      return this.foldCol;
   }

   public void org$apache$spark$ml$tuning$CrossValidatorParams$_setter_$numFolds_$eq(final IntParam x$1) {
      this.numFolds = x$1;
   }

   public void org$apache$spark$ml$tuning$CrossValidatorParams$_setter_$foldCol_$eq(final Param x$1) {
      this.foldCol = x$1;
   }

   public Param estimator() {
      return this.estimator;
   }

   public Param estimatorParamMaps() {
      return this.estimatorParamMaps;
   }

   public Param evaluator() {
      return this.evaluator;
   }

   public void org$apache$spark$ml$tuning$ValidatorParams$_setter_$estimator_$eq(final Param x$1) {
      this.estimator = x$1;
   }

   public void org$apache$spark$ml$tuning$ValidatorParams$_setter_$estimatorParamMaps_$eq(final Param x$1) {
      this.estimatorParamMaps = x$1;
   }

   public void org$apache$spark$ml$tuning$ValidatorParams$_setter_$evaluator_$eq(final Param x$1) {
      this.evaluator = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public CrossValidator setEstimator(final Estimator value) {
      return (CrossValidator)this.set(this.estimator(), value);
   }

   public CrossValidator setEstimatorParamMaps(final ParamMap[] value) {
      return (CrossValidator)this.set(this.estimatorParamMaps(), value);
   }

   public CrossValidator setEvaluator(final Evaluator value) {
      return (CrossValidator)this.set(this.evaluator(), value);
   }

   public CrossValidator setNumFolds(final int value) {
      return (CrossValidator)this.set(this.numFolds(), BoxesRunTime.boxToInteger(value));
   }

   public CrossValidator setSeed(final long value) {
      return (CrossValidator)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public CrossValidator setFoldCol(final String value) {
      return (CrossValidator)this.set(this.foldCol(), value);
   }

   public CrossValidator setParallelism(final int value) {
      return (CrossValidator)this.set(this.parallelism(), BoxesRunTime.boxToInteger(value));
   }

   public CrossValidator setCollectSubModels(final boolean value) {
      return (CrossValidator)this.set(this.collectSubModels(), BoxesRunTime.boxToBoolean(value));
   }

   public CrossValidatorModel fit(final Dataset dataset) {
      return (CrossValidatorModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         SparkSession sparkSession;
         Estimator est;
         Evaluator eval;
         ParamMap[] epm;
         ExecutionContext executionContext;
         boolean collectSubModelsParam;
         Option subModels;
         Tuple2 var31;
         label37: {
            StructType schema;
            label36: {
               schema = dataset.schema();
               this.transformSchema(schema, true);
               sparkSession = dataset.sparkSession();
               est = (Estimator)this.$(this.estimator());
               eval = (Evaluator)this.$(this.evaluator());
               epm = (ParamMap[])this.$(this.estimatorParamMaps());
               executionContext = this.getExecutionContext();
               instr.logPipelineStage(this);
               instr.logDataset(dataset);
               instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.numFolds(), this.seed(), this.parallelism(), this.foldCol()}));
               this.logTuningParams(instr);
               collectSubModelsParam = BoxesRunTime.unboxToBoolean(this.$(this.collectSubModels()));
               subModels = (Option)(collectSubModelsParam ? new Some(scala.Array..MODULE$.fill(BoxesRunTime.unboxToInt(this.$(this.numFolds())), () -> (Model[])scala.Array..MODULE$.ofDim(epm.length, scala.reflect.ClassTag..MODULE$.apply(Model.class)), scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Model.class)))) : scala.None..MODULE$);
               Object var10000 = this.$(this.foldCol());
               String var15 = "";
               if (var10000 == null) {
                  if (var15 == null) {
                     break label36;
                  }
               } else if (var10000.equals(var15)) {
                  break label36;
               }

               StructType filteredSchema = new StructType((StructField[])((IterableOnceOps)schema.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$fit$3(this, x$1)))).toArray(scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
               var31 = new Tuple2(MLUtils$.MODULE$.kFold(dataset.toDF(), BoxesRunTime.unboxToInt(this.$(this.numFolds())), (String)this.$(this.foldCol())), filteredSchema);
               break label37;
            }

            var31 = new Tuple2(MLUtils$.MODULE$.kFold(dataset.toDF().rdd(), BoxesRunTime.unboxToInt(this.$(this.numFolds())), BoxesRunTime.unboxToLong(this.$(this.seed())), scala.reflect.ClassTag..MODULE$.apply(Row.class)), schema);
         }

         Tuple2 var14 = var31;
         if (var14 != null) {
            Tuple2[] splits = (Tuple2[])var14._1();
            StructType schemaWithoutFold = (StructType)var14._2();
            Tuple2 var13 = new Tuple2(splits, schemaWithoutFold);
            Tuple2[] splitsx = (Tuple2[])var13._1();
            StructType schemaWithoutFoldx = (StructType)var13._2();
            double[] metrics = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.transpose$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])splitsx))), (x0$1) -> {
               if (x0$1 != null) {
                  Tuple2 var12 = (Tuple2)x0$1._1();
                  int splitIndex = x0$1._2$mcI$sp();
                  if (var12 != null) {
                     RDD training = (RDD)var12._1();
                     RDD validation = (RDD)var12._2();
                     Dataset trainingDataset = sparkSession.createDataFrame(training, schemaWithoutFoldx).cache();
                     Dataset validationDataset = sparkSession.createDataFrame(validation, schemaWithoutFoldx).cache();
                     instr.logDebug((Function0)(() -> "Train split " + splitIndex + " with multiple sets of parameters."));
                     Future[] foldMetricFutures = (Future[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(epm))), (x0$2) -> {
                        if (x0$2 != null) {
                           ParamMap paramMap = (ParamMap)x0$2._1();
                           int paramIndex = x0$2._2$mcI$sp();
                           return scala.concurrent.Future..MODULE$.apply((JFunction0.mcD.sp)() -> {
                              Model model = est.fit(trainingDataset, paramMap);
                              if (collectSubModelsParam) {
                                 ((Model[][])subModels.get())[splitIndex][paramIndex] = model;
                              }

                              double metric = eval.evaluate(model.transform(validationDataset, paramMap));
                              instr.logDebug((Function0)(() -> "Got metric " + metric + " for model trained with " + paramMap + "."));
                              return metric;
                           }, executionContext);
                        } else {
                           throw new MatchError(x0$2);
                        }
                     }, scala.reflect.ClassTag..MODULE$.apply(Future.class));
                     double[] foldMetrics = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])foldMetricFutures), (x$3) -> BoxesRunTime.boxToDouble($anonfun$fit$9(x$3)), scala.reflect.ClassTag..MODULE$.Double());
                     trainingDataset.unpersist();
                     validationDataset.unpersist();
                     return foldMetrics;
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Double.TYPE)))), scala.Predef..MODULE$.$conforms())), (x$4) -> BoxesRunTime.boxToDouble($anonfun$fit$10(this, x$4)), scala.reflect.ClassTag..MODULE$.Double());
            instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Average cross-validation metrics: ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CROSS_VALIDATION_METRICS..MODULE$, scala.Predef..MODULE$.wrapDoubleArray(metrics).mkString("[", ", ", "]"))})))));
            Tuple2 var23 = eval.isLargerBetter() ? (Tuple2)scala.Predef..MODULE$.wrapRefArray((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(metrics))).maxBy((x$5) -> BoxesRunTime.boxToDouble($anonfun$fit$12(x$5)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$) : (Tuple2)scala.Predef..MODULE$.wrapRefArray((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(metrics))).minBy((x$6) -> BoxesRunTime.boxToDouble($anonfun$fit$13(x$6)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
            if (var23 != null) {
               double bestMetric = var23._1$mcD$sp();
               int bestIndex = var23._2$mcI$sp();
               Tuple2.mcDI.sp var22 = new Tuple2.mcDI.sp(bestMetric, bestIndex);
               double bestMetricx = ((Tuple2)var22)._1$mcD$sp();
               int bestIndexx = ((Tuple2)var22)._2$mcI$sp();
               instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Best set of parameters:\\n", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ESTIMATOR_PARAM_MAP..MODULE$, epm[bestIndexx])})))));
               instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Best cross-validation metric: ", "."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CROSS_VALIDATION_METRIC..MODULE$, BoxesRunTime.boxToDouble(bestMetricx))})))));
               Model bestModel = est.fit(dataset, epm[bestIndexx]);
               return (CrossValidatorModel)this.copyValues((new CrossValidatorModel(this.uid(), bestModel, metrics)).setSubModels(subModels).setParent(this), this.copyValues$default$2());
            } else {
               throw new MatchError(var23);
            }
         } else {
            throw new MatchError(var14);
         }
      });
   }

   public StructType transformSchema(final StructType schema) {
      Object var10000 = this.$(this.foldCol());
      String var2 = "";
      if (var10000 == null) {
         if (var2 == null) {
            return this.transformSchemaImpl(schema);
         }
      } else if (var10000.equals(var2)) {
         return this.transformSchemaImpl(schema);
      }

      DataType foldColDt = schema.apply((String)this.$(this.foldCol())).dataType();
      scala.Predef..MODULE$.require(foldColDt instanceof IntegerType, () -> {
         Object var10000 = this.$(this.foldCol());
         return "The specified `foldCol` column " + var10000 + " must be integer type, but got " + foldColDt + ".";
      });
      return this.transformSchemaImpl(schema);
   }

   public CrossValidator copy(final ParamMap extra) {
      CrossValidator copied = (CrossValidator)this.defaultCopy(extra);
      if (copied.isDefined(this.estimator())) {
         copied.setEstimator(copied.getEstimator().copy(extra));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (copied.isDefined(this.evaluator())) {
         copied.setEvaluator(copied.getEvaluator().copy(extra));
      } else {
         BoxedUnit var3 = BoxedUnit.UNIT;
      }

      return copied;
   }

   public MLWriter write() {
      return new CrossValidatorWriter(this);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fit$3(final CrossValidator $this, final StructField x$1) {
      boolean var3;
      label23: {
         String var10000 = x$1.name();
         Object var2 = $this.$($this.foldCol());
         if (var10000 == null) {
            if (var2 != null) {
               break label23;
            }
         } else if (!var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$9(final Future x$3) {
      return BoxesRunTime.unboxToDouble(org.apache.spark.util.ThreadUtils..MODULE$.awaitResult(x$3, scala.concurrent.duration.Duration..MODULE$.Inf()));
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$10(final CrossValidator $this, final double[] x$4) {
      return BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(x$4).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) / (double)BoxesRunTime.unboxToInt($this.$($this.numFolds()));
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$12(final Tuple2 x$5) {
      return x$5._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$13(final Tuple2 x$6) {
      return x$6._1$mcD$sp();
   }

   public CrossValidator(final String uid) {
      this.uid = uid;
      HasSeed.$init$(this);
      ValidatorParams.$init$(this);
      CrossValidatorParams.$init$(this);
      HasParallelism.$init$(this);
      HasCollectSubModels.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public CrossValidator() {
      this(Identifiable$.MODULE$.randomUID("cv"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class CrossValidatorWriter extends MLWriter {
      private final CrossValidator instance;

      public void saveImpl(final String path) {
         ValidatorParams$.MODULE$.saveImpl(path, this.instance, this.sparkSession(), ValidatorParams$.MODULE$.saveImpl$default$4());
      }

      public CrossValidatorWriter(final CrossValidator instance) {
         this.instance = instance;
         ValidatorParams$.MODULE$.validateParams(instance);
      }
   }

   private static class CrossValidatorReader extends MLReader {
      private final String className = CrossValidator.class.getName();

      private String className() {
         return this.className;
      }

      public CrossValidator load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         Tuple4 var5 = ValidatorParams$.MODULE$.loadImpl(path, this.sparkSession(), this.className());
         if (var5 != null) {
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var5._1();
            Estimator estimator = (Estimator)var5._2();
            Evaluator evaluator = (Evaluator)var5._3();
            ParamMap[] estimatorParamMaps = (ParamMap[])var5._4();
            Tuple4 var4 = new Tuple4(metadata, estimator, evaluator, estimatorParamMaps);
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var4._1();
            Estimator estimator = (Estimator)var4._2();
            Evaluator evaluator = (Evaluator)var4._3();
            ParamMap[] estimatorParamMaps = (ParamMap[])var4._4();
            CrossValidator cv = (new CrossValidator(metadata.uid())).setEstimator(estimator).setEvaluator(evaluator).setEstimatorParamMaps(estimatorParamMaps);
            metadata.getAndSetParams(cv, scala.Option..MODULE$.apply(new scala.collection.immutable..colon.colon("estimatorParamMaps", scala.collection.immutable.Nil..MODULE$)));
            return cv;
         } else {
            throw new MatchError(var5);
         }
      }

      public CrossValidatorReader() {
      }
   }
}
