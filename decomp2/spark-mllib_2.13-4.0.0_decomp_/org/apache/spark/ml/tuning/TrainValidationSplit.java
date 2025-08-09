package org.apache.spark.ml.tuning;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.evaluation.Evaluator;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
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
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import scala.Array;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tub\u0001B\u0010!\u0001-B\u0001B\u0014\u0001\u0003\u0006\u0004%\te\u0014\u0005\tM\u0002\u0011\t\u0011)A\u0005!\")\u0001\u000e\u0001C\u0001S\")\u0001\u000e\u0001C\u0001]\")\u0001\u000f\u0001C\u0001c\"9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-\u0001bBA\u0010\u0001\u0011\u0005\u0011\u0011\u0005\u0005\b\u0003g\u0001A\u0011AA\u001b\u0011\u001d\t\t\u0005\u0001C\u0001\u0003\u0007Bq!a\u0015\u0001\t\u0003\t)\u0006C\u0004\u0002f\u0001!\t!a\u001a\t\u000f\u0005M\u0004\u0001\"\u0011\u0002v!9\u0011\u0011\u0013\u0001\u0005B\u0005M\u0005bBAT\u0001\u0011\u0005\u0013\u0011\u0016\u0005\b\u0003c\u0003A\u0011IAZ\u000f\u001d\ty\f\tE\u0001\u0003\u00034aa\b\u0011\t\u0002\u0005\r\u0007B\u00025\u0012\t\u0003\t\t\u000fC\u0004\u0002dF!\t%!:\t\u000f\u0005=\u0018\u0003\"\u0011\u0002r\u001a9\u0011\u0011`\t\u0001#\u0005m\b\"CA\u007f+\t\u0005\t\u0015!\u0003k\u0011\u0019AW\u0003\"\u0001\u0002\u0000\"9!qA\u000b\u0005R\t%aA\u0002B\n#\u0011\u0011)\u0002\u0003\u0004i3\u0011\u0005!q\u0003\u0005\n\u00057I\"\u0019!C\u0005\u0005;A\u0001B!\u000b\u001aA\u0003%!q\u0004\u0005\b\u0003_LB\u0011\tB\u0016\u0011%\u0011y#EA\u0001\n\u0013\u0011\tD\u0001\u000bUe\u0006LgNV1mS\u0012\fG/[8o'Bd\u0017\u000e\u001e\u0006\u0003C\t\na\u0001^;oS:<'BA\u0012%\u0003\tiGN\u0003\u0002&M\u0005)1\u000f]1sW*\u0011q\u0005K\u0001\u0007CB\f7\r[3\u000b\u0003%\n1a\u001c:h\u0007\u0001\u0019r\u0001\u0001\u00175o}\u0012\u0005\nE\u0002.]Aj\u0011AI\u0005\u0003_\t\u0012\u0011\"R:uS6\fGo\u001c:\u0011\u0005E\u0012T\"\u0001\u0011\n\u0005M\u0002#!\u0007+sC&tg+\u00197jI\u0006$\u0018n\u001c8Ta2LG/T8eK2\u0004\"!M\u001b\n\u0005Y\u0002#A\u0007+sC&tg+\u00197jI\u0006$\u0018n\u001c8Ta2LG\u000fU1sC6\u001c\bC\u0001\u001d>\u001b\u0005I$B\u0001\u001e<\u0003\u0019\u0019\b.\u0019:fI*\u0011AHI\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003}e\u0012a\u0002S1t!\u0006\u0014\u0018\r\u001c7fY&\u001cX\u000e\u0005\u00029\u0001&\u0011\u0011)\u000f\u0002\u0014\u0011\u0006\u001c8i\u001c7mK\u000e$8+\u001e2N_\u0012,Gn\u001d\t\u0003\u0007\u001ak\u0011\u0001\u0012\u0006\u0003\u000b\n\nA!\u001e;jY&\u0011q\t\u0012\u0002\u000b\u001b2;&/\u001b;bE2,\u0007CA%M\u001b\u0005Q%BA&%\u0003!Ig\u000e^3s]\u0006d\u0017BA'K\u0005\u001daunZ4j]\u001e\f1!^5e+\u0005\u0001\u0006CA)[\u001d\t\u0011\u0006\f\u0005\u0002T-6\tAK\u0003\u0002VU\u00051AH]8pizR\u0011aV\u0001\u0006g\u000e\fG.Y\u0005\u00033Z\u000ba\u0001\u0015:fI\u00164\u0017BA.]\u0005\u0019\u0019FO]5oO*\u0011\u0011L\u0016\u0015\u0004\u0003y#\u0007CA0c\u001b\u0005\u0001'BA1%\u0003)\tgN\\8uCRLwN\\\u0005\u0003G\u0002\u0014QaU5oG\u0016\f\u0013!Z\u0001\u0006c9*d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003=\u0012\fa\u0001P5oSRtDC\u00016l!\t\t\u0004\u0001C\u0003O\u0007\u0001\u0007\u0001\u000bK\u0002l=\u0012D3a\u00010e)\u0005Q\u0007f\u0001\u0003_I\u0006a1/\u001a;FgRLW.\u0019;peR\u0011!o]\u0007\u0002\u0001!)A/\u0002a\u0001k\u0006)a/\u00197vKB\u0012a/\u001f\t\u0004[9:\bC\u0001=z\u0019\u0001!\u0011B_:\u0002\u0002\u0003\u0005)\u0011A>\u0003\u0007}#\u0013'E\u0002}\u0003\u0003\u0001\"! @\u000e\u0003YK!a ,\u0003\u000f9{G\u000f[5oOB\u0019Q0a\u0001\n\u0007\u0005\u0015aKA\u0002B]fD3!\u00020e\u0003U\u0019X\r^#ti&l\u0017\r^8s!\u0006\u0014\u0018-\\'baN$2A]A\u0007\u0011\u0019!h\u00011\u0001\u0002\u0010A)Q0!\u0005\u0002\u0016%\u0019\u00111\u0003,\u0003\u000b\u0005\u0013(/Y=\u0011\t\u0005]\u0011\u0011D\u0007\u0002w%\u0019\u00111D\u001e\u0003\u0011A\u000b'/Y7NCBD3A\u00020e\u00031\u0019X\r^#wC2,\u0018\r^8s)\r\u0011\u00181\u0005\u0005\u0007i\u001e\u0001\r!!\n\u0011\t\u0005\u001d\u0012QF\u0007\u0003\u0003SQ1!a\u000b#\u0003))g/\u00197vCRLwN\\\u0005\u0005\u0003_\tICA\u0005Fm\u0006dW/\u0019;pe\"\u001aqA\u00183\u0002\u001bM,G\u000f\u0016:bS:\u0014\u0016\r^5p)\r\u0011\u0018q\u0007\u0005\u0007i\"\u0001\r!!\u000f\u0011\u0007u\fY$C\u0002\u0002>Y\u0013a\u0001R8vE2,\u0007f\u0001\u0005_I\u000691/\u001a;TK\u0016$Gc\u0001:\u0002F!1A/\u0003a\u0001\u0003\u000f\u00022!`A%\u0013\r\tYE\u0016\u0002\u0005\u0019>tw\r\u000b\u0003\n=\u0006=\u0013EAA)\u0003\u0015\u0011d\u0006\r\u00181\u00039\u0019X\r\u001e)be\u0006dG.\u001a7jg6$2A]A,\u0011\u0019!(\u00021\u0001\u0002ZA\u0019Q0a\u0017\n\u0007\u0005ucKA\u0002J]RDCA\u00030\u0002b\u0005\u0012\u00111M\u0001\u0006e9\u001ad\u0006M\u0001\u0014g\u0016$8i\u001c7mK\u000e$8+\u001e2N_\u0012,Gn\u001d\u000b\u0004e\u0006%\u0004B\u0002;\f\u0001\u0004\tY\u0007E\u0002~\u0003[J1!a\u001cW\u0005\u001d\u0011un\u001c7fC:DCa\u00030\u0002b\u0005\u0019a-\u001b;\u0015\u0007A\n9\bC\u0004\u0002z1\u0001\r!a\u001f\u0002\u000f\u0011\fG/Y:fiB\"\u0011QPAF!\u0019\ty(!\"\u0002\n6\u0011\u0011\u0011\u0011\u0006\u0004\u0003\u0007#\u0013aA:rY&!\u0011qQAA\u0005\u001d!\u0015\r^1tKR\u00042\u0001_AF\t-\ti)a\u001e\u0002\u0002\u0003\u0005)\u0011A>\u0003\u0007}##\u0007\u000b\u0003\r=\u0006=\u0013a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005U\u0015\u0011\u0015\t\u0005\u0003/\u000bi*\u0004\u0002\u0002\u001a*!\u00111TAA\u0003\u0015!\u0018\u0010]3t\u0013\u0011\ty*!'\u0003\u0015M#(/^2u)f\u0004X\rC\u0004\u0002$6\u0001\r!!&\u0002\rM\u001c\u0007.Z7bQ\ria\fZ\u0001\u0005G>\u0004\u0018\u0010F\u0002k\u0003WCq!!,\u000f\u0001\u0004\t)\"A\u0003fqR\u0014\u0018\rK\u0002\u000f=\u0012\fQa\u001e:ji\u0016,\"!!.\u0011\u0007\r\u000b9,C\u0002\u0002:\u0012\u0013\u0001\"\u0014'Xe&$XM\u001d\u0015\u0005\u001fy\u000by\u0005K\u0002\u0001=\u0012\fA\u0003\u0016:bS:4\u0016\r\\5eCRLwN\\*qY&$\bCA\u0019\u0012'\u001d\t\u0012QYAf\u0003#\u00042!`Ad\u0013\r\tIM\u0016\u0002\u0007\u0003:L(+\u001a4\u0011\t\r\u000biM[\u0005\u0004\u0003\u001f$%AC'M%\u0016\fG-\u00192mKB!\u00111[Ao\u001b\t\t)N\u0003\u0003\u0002X\u0006e\u0017AA5p\u0015\t\tY.\u0001\u0003kCZ\f\u0017\u0002BAp\u0003+\u0014AbU3sS\u0006d\u0017N_1cY\u0016$\"!!1\u0002\tI,\u0017\rZ\u000b\u0003\u0003O\u0004BaQAuU&\u0019\u00111\u001e#\u0003\u00115c%+Z1eKJDCa\u00050\u0002P\u0005!An\\1e)\rQ\u00171\u001f\u0005\u0007\u0003k$\u0002\u0019\u0001)\u0002\tA\fG\u000f\u001b\u0015\u0005)y\u000byE\u0001\u000eUe\u0006LgNV1mS\u0012\fG/[8o'Bd\u0017\u000e^,sSR,'oE\u0002\u0016\u0003k\u000b\u0001\"\u001b8ti\u0006t7-\u001a\u000b\u0005\u0005\u0003\u0011)\u0001E\u0002\u0003\u0004Ui\u0011!\u0005\u0005\u0007\u0003{<\u0002\u0019\u00016\u0002\u0011M\fg/Z%na2$BAa\u0003\u0003\u0012A\u0019QP!\u0004\n\u0007\t=aK\u0001\u0003V]&$\bBBA{1\u0001\u0007\u0001K\u0001\u000eUe\u0006LgNV1mS\u0012\fG/[8o'Bd\u0017\u000e\u001e*fC\u0012,'oE\u0002\u001a\u0003O$\"A!\u0007\u0011\u0007\t\r\u0011$A\u0005dY\u0006\u001c8OT1nKV\u0011!q\u0004\t\u0005\u0005C\u00119#\u0004\u0002\u0003$)!!QEAm\u0003\u0011a\u0017M\\4\n\u0007m\u0013\u0019#\u0001\u0006dY\u0006\u001c8OT1nK\u0002\"2A\u001bB\u0017\u0011\u0019\t)0\ba\u0001!\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u0007\t\u0005\u0005C\u0011)$\u0003\u0003\u00038\t\r\"AB(cU\u0016\u001cG\u000f\u000b\u0003\u0012=\u0006=\u0003\u0006\u0002\t_\u0003\u001f\u0002"
)
public class TrainValidationSplit extends Estimator implements TrainValidationSplitParams, HasParallelism, HasCollectSubModels, MLWritable {
   private final String uid;
   private BooleanParam collectSubModels;
   private IntParam parallelism;
   private DoubleParam trainRatio;
   private Param estimator;
   private Param estimatorParamMaps;
   private Param evaluator;
   private LongParam seed;

   public static TrainValidationSplit load(final String path) {
      return TrainValidationSplit$.MODULE$.load(path);
   }

   public static MLReader read() {
      return TrainValidationSplit$.MODULE$.read();
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

   public double getTrainRatio() {
      return TrainValidationSplitParams.getTrainRatio$(this);
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

   public DoubleParam trainRatio() {
      return this.trainRatio;
   }

   public void org$apache$spark$ml$tuning$TrainValidationSplitParams$_setter_$trainRatio_$eq(final DoubleParam x$1) {
      this.trainRatio = x$1;
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

   public TrainValidationSplit setEstimator(final Estimator value) {
      return (TrainValidationSplit)this.set(this.estimator(), value);
   }

   public TrainValidationSplit setEstimatorParamMaps(final ParamMap[] value) {
      return (TrainValidationSplit)this.set(this.estimatorParamMaps(), value);
   }

   public TrainValidationSplit setEvaluator(final Evaluator value) {
      return (TrainValidationSplit)this.set(this.evaluator(), value);
   }

   public TrainValidationSplit setTrainRatio(final double value) {
      return (TrainValidationSplit)this.set(this.trainRatio(), BoxesRunTime.boxToDouble(value));
   }

   public TrainValidationSplit setSeed(final long value) {
      return (TrainValidationSplit)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public TrainValidationSplit setParallelism(final int value) {
      return (TrainValidationSplit)this.set(this.parallelism(), BoxesRunTime.boxToInteger(value));
   }

   public TrainValidationSplit setCollectSubModels(final boolean value) {
      return (TrainValidationSplit)this.set(this.collectSubModels(), BoxesRunTime.boxToBoolean(value));
   }

   public TrainValidationSplitModel fit(final Dataset dataset) {
      return (TrainValidationSplitModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         StructType schema = dataset.schema();
         this.transformSchema(schema, true);
         Estimator est = (Estimator)this.$(this.estimator());
         Evaluator eval = (Evaluator)this.$(this.evaluator());
         ParamMap[] epm = (ParamMap[])this.$(this.estimatorParamMaps());
         ExecutionContext executionContext = this.getExecutionContext();
         instr.logPipelineStage(this);
         instr.logDataset(dataset);
         instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.trainRatio(), this.seed(), this.parallelism()}));
         this.logTuningParams(instr);
         Dataset[] var11 = dataset.randomSplit(new double[]{BoxesRunTime.unboxToDouble(this.$(this.trainRatio())), (double)1 - BoxesRunTime.unboxToDouble(this.$(this.trainRatio()))}, BoxesRunTime.unboxToLong(this.$(this.seed())));
         if (var11 instanceof Dataset[]) {
            Object var13 = scala.Array..MODULE$.unapplySeq(var11);
            if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var13) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var13)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var13), 2) == 0) {
               Dataset trainingDataset = (Dataset)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var13), 0);
               Dataset validationDataset = (Dataset)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var13), 1);
               Tuple2 var10 = new Tuple2(trainingDataset, validationDataset);
               Dataset trainingDatasetx = (Dataset)var10._1();
               Dataset validationDatasetx = (Dataset)var10._2();
               trainingDatasetx.cache();
               validationDatasetx.cache();
               boolean collectSubModelsParam = BoxesRunTime.unboxToBoolean(this.$(this.collectSubModels()));
               Option subModels = (Option)(collectSubModelsParam ? new Some(scala.Array..MODULE$.ofDim(epm.length, scala.reflect.ClassTag..MODULE$.apply(Model.class))) : scala.None..MODULE$);
               instr.logDebug((Function0)(() -> "Train split with multiple sets of parameters."));
               Future[] metricFutures = (Future[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(epm))), (x0$1) -> {
                  if (x0$1 != null) {
                     ParamMap paramMap = (ParamMap)x0$1._1();
                     int paramIndex = x0$1._2$mcI$sp();
                     return scala.concurrent.Future..MODULE$.apply((JFunction0.mcD.sp)() -> {
                        Model model = est.fit(trainingDatasetx, paramMap);
                        if (collectSubModelsParam) {
                           ((Model[])subModels.get())[paramIndex] = model;
                        }

                        double metric = eval.evaluate(model.transform(validationDatasetx, paramMap));
                        instr.logDebug((Function0)(() -> "Got metric " + metric + " for model trained with " + paramMap + "."));
                        return metric;
                     }, executionContext);
                  } else {
                     throw new MatchError(x0$1);
                  }
               }, scala.reflect.ClassTag..MODULE$.apply(Future.class));
               double[] metrics = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])metricFutures), (x$2) -> BoxesRunTime.boxToDouble($anonfun$fit$6(x$2)), scala.reflect.ClassTag..MODULE$.Double());
               trainingDatasetx.unpersist();
               validationDatasetx.unpersist();
               instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Train validation split metrics: ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TRAIN_VALIDATION_SPLIT_METRICS..MODULE$, scala.Predef..MODULE$.wrapDoubleArray(metrics).mkString("[", ", ", "]"))})))));
               Tuple2 var23 = eval.isLargerBetter() ? (Tuple2)scala.Predef..MODULE$.wrapRefArray((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(metrics))).maxBy((x$3) -> BoxesRunTime.boxToDouble($anonfun$fit$8(x$3)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$) : (Tuple2)scala.Predef..MODULE$.wrapRefArray((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(metrics))).minBy((x$4) -> BoxesRunTime.boxToDouble($anonfun$fit$9(x$4)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
               if (var23 != null) {
                  double bestMetric = var23._1$mcD$sp();
                  int bestIndex = var23._2$mcI$sp();
                  Tuple2.mcDI.sp var22 = new Tuple2.mcDI.sp(bestMetric, bestIndex);
                  double bestMetricx = ((Tuple2)var22)._1$mcD$sp();
                  int bestIndexx = ((Tuple2)var22)._2$mcI$sp();
                  instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Best set of parameters:\\n", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ESTIMATOR_PARAM_MAP..MODULE$, epm[bestIndexx])})))));
                  instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Best train validation split metric: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TRAIN_VALIDATION_SPLIT_METRIC..MODULE$, BoxesRunTime.boxToDouble(bestMetricx))}))))));
                  Model bestModel = est.fit(dataset, epm[bestIndexx]);
                  return (TrainValidationSplitModel)this.copyValues((new TrainValidationSplitModel(this.uid(), bestModel, metrics)).setSubModels(subModels).setParent(this), this.copyValues$default$2());
               }

               throw new MatchError(var23);
            }
         }

         throw new MatchError(var11);
      });
   }

   public StructType transformSchema(final StructType schema) {
      return this.transformSchemaImpl(schema);
   }

   public TrainValidationSplit copy(final ParamMap extra) {
      TrainValidationSplit copied = (TrainValidationSplit)this.defaultCopy(extra);
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
      return new TrainValidationSplitWriter(this);
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$6(final Future x$2) {
      return BoxesRunTime.unboxToDouble(org.apache.spark.util.ThreadUtils..MODULE$.awaitResult(x$2, scala.concurrent.duration.Duration..MODULE$.Inf()));
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$8(final Tuple2 x$3) {
      return x$3._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$fit$9(final Tuple2 x$4) {
      return x$4._1$mcD$sp();
   }

   public TrainValidationSplit(final String uid) {
      this.uid = uid;
      HasSeed.$init$(this);
      ValidatorParams.$init$(this);
      TrainValidationSplitParams.$init$(this);
      HasParallelism.$init$(this);
      HasCollectSubModels.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public TrainValidationSplit() {
      this(Identifiable$.MODULE$.randomUID("tvs"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class TrainValidationSplitWriter extends MLWriter {
      private final TrainValidationSplit instance;

      public void saveImpl(final String path) {
         ValidatorParams$.MODULE$.saveImpl(path, this.instance, this.sparkSession(), ValidatorParams$.MODULE$.saveImpl$default$4());
      }

      public TrainValidationSplitWriter(final TrainValidationSplit instance) {
         this.instance = instance;
         ValidatorParams$.MODULE$.validateParams(instance);
      }
   }

   private static class TrainValidationSplitReader extends MLReader {
      private final String className = TrainValidationSplit.class.getName();

      private String className() {
         return this.className;
      }

      public TrainValidationSplit load(final String path) {
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
            TrainValidationSplit tvs = (new TrainValidationSplit(metadata.uid())).setEstimator(estimator).setEvaluator(evaluator).setEstimatorParamMaps(estimatorParamMaps);
            metadata.getAndSetParams(tvs, scala.Option..MODULE$.apply(new scala.collection.immutable..colon.colon("estimatorParamMaps", scala.collection.immutable.Nil..MODULE$)));
            return tvs;
         } else {
            throw new MatchError(var5);
         }
      }

      public TrainValidationSplitReader() {
      }
   }
}
