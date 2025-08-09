package org.apache.spark.ml.clustering;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasDistanceMeasure;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%f\u0001B\u000b\u0017\u0001\u0005B\u0001b\r\u0001\u0003\u0006\u0004%\t\u0005\u000e\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005k!)Q\n\u0001C\u0001\u001d\")1\u000b\u0001C!)\")Q\n\u0001C\u0001=\")\u0001\r\u0001C\u0001C\")a\r\u0001C\u0001O\")!\u000e\u0001C\u0001W\")!\u000f\u0001C\u0001g\")a\u000f\u0001C\u0001o\")Q\u0010\u0001C\u0001}\"9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-\u0001bBA\u000b\u0001\u0011\u0005\u0011q\u0003\u0005\b\u0003C\u0001A\u0011IA\u0012\u0011\u001d\ty\u0005\u0001C!\u0003#:q!a\u001a\u0017\u0011\u0003\tIG\u0002\u0004\u0016-!\u0005\u00111\u000e\u0005\u0007\u001bF!\t!!#\t\u000f\u0005-\u0015\u0003\"\u0011\u0002\u000e\"I\u0011QS\t\u0002\u0002\u0013%\u0011q\u0013\u0002\u0010\u0005&\u001cXm\u0019;j]\u001e\\U*Z1og*\u0011q\u0003G\u0001\u000bG2,8\u000f^3sS:<'BA\r\u001b\u0003\tiGN\u0003\u0002\u001c9\u0005)1\u000f]1sW*\u0011QDH\u0001\u0007CB\f7\r[3\u000b\u0003}\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0012+[A\u00191\u0005\n\u0014\u000e\u0003aI!!\n\r\u0003\u0013\u0015\u001bH/[7bi>\u0014\bCA\u0014)\u001b\u00051\u0012BA\u0015\u0017\u0005Q\u0011\u0015n]3di&twmS'fC:\u001cXj\u001c3fYB\u0011qeK\u0005\u0003YY\u0011QCQ5tK\u000e$\u0018N\\4L\u001b\u0016\fgn\u001d)be\u0006l7\u000f\u0005\u0002/c5\tqF\u0003\u000211\u0005!Q\u000f^5m\u0013\t\u0011tFA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn],sSR\f'\r\\3\u0002\u0007ULG-F\u00016!\t1tH\u0004\u00028{A\u0011\u0001hO\u0007\u0002s)\u0011!\bI\u0001\u0007yI|w\u000e\u001e \u000b\u0003q\nQa]2bY\u0006L!AP\u001e\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0015I\u0001\u0004TiJLgn\u001a\u0006\u0003}mB3!A\"J!\t!u)D\u0001F\u0015\t1%$\u0001\u0006b]:|G/\u0019;j_:L!\u0001S#\u0003\u000bMKgnY3\"\u0003)\u000bQA\r\u00181]A\nA!^5eA!\u001a!aQ%\u0002\rqJg.\u001b;?)\ty\u0005\u000b\u0005\u0002(\u0001!)1g\u0001a\u0001k!\u001a\u0001kQ%)\u0007\r\u0019\u0015*\u0001\u0003d_BLHCA(V\u0011\u00151F\u00011\u0001X\u0003\u0015)\u0007\u0010\u001e:b!\tA6,D\u0001Z\u0015\tQ\u0006$A\u0003qCJ\fW.\u0003\u0002]3\nA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\u0005\u0007&#\u0012a\u0014\u0015\u0004\u000b\rK\u0015AD:fi\u001a+\u0017\r^;sKN\u001cu\u000e\u001c\u000b\u0003E\u000el\u0011\u0001\u0001\u0005\u0006I\u001a\u0001\r!N\u0001\u0006m\u0006dW/\u001a\u0015\u0004\r\rK\u0015\u0001E:fiB\u0013X\rZ5di&|gnQ8m)\t\u0011\u0007\u000eC\u0003e\u000f\u0001\u0007Q\u0007K\u0002\b\u0007&\u000bAa]3u\u0017R\u0011!\r\u001c\u0005\u0006I\"\u0001\r!\u001c\t\u0003]>l\u0011aO\u0005\u0003an\u00121!\u00138uQ\rA1)S\u0001\u000bg\u0016$X*\u0019=Ji\u0016\u0014HC\u00012u\u0011\u0015!\u0017\u00021\u0001nQ\rI1)S\u0001\bg\u0016$8+Z3e)\t\u0011\u0007\u0010C\u0003e\u0015\u0001\u0007\u0011\u0010\u0005\u0002ou&\u00111p\u000f\u0002\u0005\u0019>tw\rK\u0002\u000b\u0007&\u000b!d]3u\u001b&tG)\u001b<jg&\u0014G.Z\"mkN$XM]*ju\u0016$\"AY@\t\r\u0011\\\u0001\u0019AA\u0001!\rq\u00171A\u0005\u0004\u0003\u000bY$A\u0002#pk\ndW\rK\u0002\f\u0007&\u000b!c]3u\t&\u001cH/\u00198dK6+\u0017m];sKR\u0019!-!\u0004\t\u000b\u0011d\u0001\u0019A\u001b)\t1\u0019\u0015\u0011C\u0011\u0003\u0003'\tQA\r\u00185]A\nAb]3u/\u0016Lw\r\u001b;D_2$2AYA\r\u0011\u0015!W\u00021\u00016Q\u0011i1)!\b\"\u0005\u0005}\u0011!B\u001a/a9\u0002\u0014a\u00014jiR\u0019a%!\n\t\u000f\u0005\u001db\u00021\u0001\u0002*\u00059A-\u0019;bg\u0016$\b\u0007BA\u0016\u0003w\u0001b!!\f\u00024\u0005]RBAA\u0018\u0015\r\t\tDG\u0001\u0004gFd\u0017\u0002BA\u001b\u0003_\u0011q\u0001R1uCN,G\u000f\u0005\u0003\u0002:\u0005mB\u0002\u0001\u0003\r\u0003{\t)#!A\u0001\u0002\u000b\u0005\u0011q\b\u0002\u0004?\u0012\u001a\u0014\u0003BA!\u0003\u000f\u00022A\\A\"\u0013\r\t)e\u000f\u0002\b\u001d>$\b.\u001b8h!\rq\u0017\u0011J\u0005\u0004\u0003\u0017Z$aA!os\"\u001aabQ%\u0002\u001fQ\u0014\u0018M\\:g_Jl7k\u00195f[\u0006$B!a\u0015\u0002`A!\u0011QKA.\u001b\t\t9F\u0003\u0003\u0002Z\u0005=\u0012!\u0002;za\u0016\u001c\u0018\u0002BA/\u0003/\u0012!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\t\tg\u0004a\u0001\u0003'\naa]2iK6\f\u0007fA\bD\u0013\"\u001a\u0001aQ%\u0002\u001f\tK7/Z2uS:<7*T3b]N\u0004\"aJ\t\u0014\u000fE\ti'a\u001d\u0002zA\u0019a.a\u001c\n\u0007\u0005E4H\u0001\u0004B]f\u0014VM\u001a\t\u0005]\u0005Ut*C\u0002\u0002x=\u0012Q\u0003R3gCVdG\u000fU1sC6\u001c(+Z1eC\ndW\r\u0005\u0003\u0002|\u0005\u0015UBAA?\u0015\u0011\ty(!!\u0002\u0005%|'BAAB\u0003\u0011Q\u0017M^1\n\t\u0005\u001d\u0015Q\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003S\nA\u0001\\8bIR\u0019q*a$\t\r\u0005E5\u00031\u00016\u0003\u0011\u0001\u0018\r\u001e5)\u0007M\u0019\u0015*\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u001aB!\u00111TAQ\u001b\t\tiJ\u0003\u0003\u0002 \u0006\u0005\u0015\u0001\u00027b]\u001eLA!a)\u0002\u001e\n1qJ\u00196fGRD3!E\"JQ\r\u00012)\u0013"
)
public class BisectingKMeans extends Estimator implements BisectingKMeansParams, DefaultParamsWritable {
   private final String uid;
   private IntParam k;
   private DoubleParam minDivisibleClusterSize;
   private Param weightCol;
   private Param distanceMeasure;
   private Param predictionCol;
   private LongParam seed;
   private Param featuresCol;
   private IntParam maxIter;

   public static BisectingKMeans load(final String path) {
      return BisectingKMeans$.MODULE$.load(path);
   }

   public static MLReader read() {
      return BisectingKMeans$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getK() {
      return BisectingKMeansParams.getK$(this);
   }

   public double getMinDivisibleClusterSize() {
      return BisectingKMeansParams.getMinDivisibleClusterSize$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return BisectingKMeansParams.validateAndTransformSchema$(this, schema);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final String getDistanceMeasure() {
      return HasDistanceMeasure.getDistanceMeasure$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final IntParam k() {
      return this.k;
   }

   public final DoubleParam minDivisibleClusterSize() {
      return this.minDivisibleClusterSize;
   }

   public final void org$apache$spark$ml$clustering$BisectingKMeansParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
   }

   public final void org$apache$spark$ml$clustering$BisectingKMeansParams$_setter_$minDivisibleClusterSize_$eq(final DoubleParam x$1) {
      this.minDivisibleClusterSize = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final Param distanceMeasure() {
      return this.distanceMeasure;
   }

   public final void org$apache$spark$ml$param$shared$HasDistanceMeasure$_setter_$distanceMeasure_$eq(final Param x$1) {
      this.distanceMeasure = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public BisectingKMeans copy(final ParamMap extra) {
      return (BisectingKMeans)this.defaultCopy(extra);
   }

   public BisectingKMeans setFeaturesCol(final String value) {
      return (BisectingKMeans)this.set(this.featuresCol(), value);
   }

   public BisectingKMeans setPredictionCol(final String value) {
      return (BisectingKMeans)this.set(this.predictionCol(), value);
   }

   public BisectingKMeans setK(final int value) {
      return (BisectingKMeans)this.set(this.k(), BoxesRunTime.boxToInteger(value));
   }

   public BisectingKMeans setMaxIter(final int value) {
      return (BisectingKMeans)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public BisectingKMeans setSeed(final long value) {
      return (BisectingKMeans)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public BisectingKMeans setMinDivisibleClusterSize(final double value) {
      return (BisectingKMeans)this.set(this.minDivisibleClusterSize(), BoxesRunTime.boxToDouble(value));
   }

   public BisectingKMeans setDistanceMeasure(final String value) {
      return (BisectingKMeans)this.set(this.distanceMeasure(), value);
   }

   public BisectingKMeans setWeightCol(final String value) {
      return (BisectingKMeans)this.set(this.weightCol(), value);
   }

   public BisectingKMeansModel fit(final Dataset dataset) {
      return (BisectingKMeansModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         org.apache.spark.mllib.clustering.BisectingKMeans bkm;
         RDD instances;
         boolean var10;
         label17: {
            label16: {
               this.transformSchema(dataset.schema(), true);
               instr.logPipelineStage(this);
               instr.logDataset(dataset);
               instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.featuresCol(), this.predictionCol(), this.k(), this.maxIter(), this.seed(), this.minDivisibleClusterSize(), this.distanceMeasure(), this.weightCol()}));
               bkm = (new org.apache.spark.mllib.clustering.BisectingKMeans()).setK(BoxesRunTime.unboxToInt(this.$(this.k()))).setMaxIterations(BoxesRunTime.unboxToInt(this.$(this.maxIter()))).setMinDivisibleClusterSize(BoxesRunTime.unboxToDouble(this.$(this.minDivisibleClusterSize()))).setSeed(BoxesRunTime.unboxToLong(this.$(this.seed()))).setDistanceMeasure((String)this.$(this.distanceMeasure()));
               instances = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkNonNanVectors(DatasetUtils$.MODULE$.columnToVector(dataset, (String)this.$(this.featuresCol()))), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()))}))).rdd().map((x0$1) -> {
                  if (x0$1 != null) {
                     Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
                     if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                        Object f = ((SeqOps)var3.get()).apply(0);
                        Object w = ((SeqOps)var3.get()).apply(1);
                        if (f instanceof Vector) {
                           Vector var6 = (Vector)f;
                           if (w instanceof Double) {
                              double var7 = BoxesRunTime.unboxToDouble(w);
                              return new Tuple2(Vectors$.MODULE$.fromML(var6), BoxesRunTime.boxToDouble(var7));
                           }
                        }
                     }
                  }

                  throw new MatchError(x0$1);
               }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).setName("training instances");
               StorageLevel var10000 = dataset.storageLevel();
               StorageLevel var6 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var10000 == null) {
                  if (var6 == null) {
                     break label16;
                  }
               } else if (var10000.equals(var6)) {
                  break label16;
               }

               var10 = false;
               break label17;
            }

            var10 = true;
         }

         boolean handlePersistence = var10;
         org.apache.spark.mllib.clustering.BisectingKMeansModel parentModel = bkm.runWithWeight(instances, handlePersistence, new Some(instr));
         BisectingKMeansModel model = (BisectingKMeansModel)this.copyValues((new BisectingKMeansModel(this.uid(), parentModel)).setParent(this), this.copyValues$default$2());
         BisectingKMeansSummary summary = new BisectingKMeansSummary(model.transform(dataset), (String)this.$(this.predictionCol()), (String)this.$(this.featuresCol()), BoxesRunTime.unboxToInt(this.$(this.k())), BoxesRunTime.unboxToInt(this.$(this.maxIter())), parentModel.trainingCost());
         instr.logNamedValue("clusterSizes", summary.clusterSizes());
         instr.logNumFeatures((long)((Vector)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])model.clusterCenters()))).size());
         return (BisectingKMeansModel)model.setSummary(new Some(summary));
      });
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public BisectingKMeans(final String uid) {
      this.uid = uid;
      HasMaxIter.$init$(this);
      HasFeaturesCol.$init$(this);
      HasSeed.$init$(this);
      HasPredictionCol.$init$(this);
      HasDistanceMeasure.$init$(this);
      HasWeightCol.$init$(this);
      BisectingKMeansParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public BisectingKMeans() {
      this(Identifiable$.MODULE$.randomUID("bisecting-kmeans"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
