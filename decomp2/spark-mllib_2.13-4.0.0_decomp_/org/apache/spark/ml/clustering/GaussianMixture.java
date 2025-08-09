package org.apache.spark.ml.clustering;

import breeze.linalg.ImmutableNumericOps;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasProbabilityCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.stat.distribution.MultivariateGaussian;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.DoubleAccumulator;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005d\u0001B\u0010!\u0001-B\u0001\"\u0010\u0001\u0003\u0006\u0004%\tE\u0010\u0005\t+\u0002\u0011\t\u0011)A\u0005\u007f!)q\u000b\u0001C\u00011\")Q\f\u0001C!=\")q\u000b\u0001C\u0001Q\")!\u000e\u0001C\u0001W\")\u0001\u000f\u0001C\u0001c\")A\u000f\u0001C\u0001k\")\u0001\u0010\u0001C\u0001s\")a\u0010\u0001C\u0001\u007f\"9\u0011Q\u0002\u0001\u0005\u0002\u0005=\u0001bBA\u000b\u0001\u0011\u0005\u0011q\u0003\u0005\b\u0003G\u0001A\u0011AA\u0013\u0011\u001d\t\t\u0004\u0001C\u0001\u0003gA\u0011\"!\u000f\u0001\u0005\u0004%I!a\u000f\t\u0011\u0005u\u0002\u0001)A\u0005\u0003\u0007Aq!a\u0010\u0001\t\u0003\n\t\u0005C\u0004\u0002n\u0001!I!a\u001c\t\u000f\u0005m\u0006\u0001\"\u0011\u0002>\"9\u0011\u0011\u001b\u0001\u0005\n\u0005MwaBAqA!\u0005\u00111\u001d\u0004\u0007?\u0001B\t!!:\t\r]3B\u0011\u0001B\u0002\u0011)\u0011)A\u0006b\u0001\n\u0003\u0001\u00131\b\u0005\t\u0005\u000f1\u0002\u0015!\u0003\u0002\u0004!9!\u0011\u0002\f\u0005B\t-\u0001\u0002\u0003B\n-\u0011\u0005\u0001E!\u0006\t\u000f\t\u0015b\u0003\"\u0003\u0003(!A!q\u0007\f\u0005\u0002\u0001\u0012I\u0004C\u0005\u0003NY\t\t\u0011\"\u0003\u0003P\tyq)Y;tg&\fg.T5yiV\u0014XM\u0003\u0002\"E\u0005Q1\r\\;ti\u0016\u0014\u0018N\\4\u000b\u0005\r\"\u0013AA7m\u0015\t)c%A\u0003ta\u0006\u00148N\u0003\u0002(Q\u00051\u0011\r]1dQ\u0016T\u0011!K\u0001\u0004_J<7\u0001A\n\u0005\u00011\"t\u0007E\u0002.]Aj\u0011AI\u0005\u0003_\t\u0012\u0011\"R:uS6\fGo\u001c:\u0011\u0005E\u0012T\"\u0001\u0011\n\u0005M\u0002#\u0001F$bkN\u001c\u0018.\u00198NSb$XO]3N_\u0012,G\u000e\u0005\u00022k%\u0011a\u0007\t\u0002\u0016\u000f\u0006,8o]5b]6K\u0007\u0010^;sKB\u000b'/Y7t!\tA4(D\u0001:\u0015\tQ$%\u0001\u0003vi&d\u0017B\u0001\u001f:\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005y\u0004C\u0001!J\u001d\t\tu\t\u0005\u0002C\u000b6\t1I\u0003\u0002EU\u00051AH]8pizR\u0011AR\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0011\u0016\u000ba\u0001\u0015:fI\u00164\u0017B\u0001&L\u0005\u0019\u0019FO]5oO*\u0011\u0001*\u0012\u0015\u0004\u00035\u001b\u0006C\u0001(R\u001b\u0005y%B\u0001)%\u0003)\tgN\\8uCRLwN\\\u0005\u0003%>\u0013QaU5oG\u0016\f\u0013\u0001V\u0001\u0006e9\u0002d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003\u001bN\u000ba\u0001P5oSRtDCA-[!\t\t\u0004\u0001C\u0003>\u0007\u0001\u0007q\bK\u0002[\u001bNC3aA'T\u0003\u0011\u0019w\u000e]=\u0015\u0005e{\u0006\"\u00021\u0005\u0001\u0004\t\u0017!B3yiJ\f\u0007C\u00012f\u001b\u0005\u0019'B\u00013#\u0003\u0015\u0001\u0018M]1n\u0013\t17M\u0001\u0005QCJ\fW.T1qQ\r!Qj\u0015\u000b\u00023\"\u001aQ!T*\u0002\u001dM,GOR3biV\u0014Xm]\"pYR\u0011A.\\\u0007\u0002\u0001!)aN\u0002a\u0001\u007f\u0005)a/\u00197vK\"\u001aa!T*\u0002!M,G\u000f\u0015:fI&\u001cG/[8o\u0007>dGC\u00017s\u0011\u0015qw\u00011\u0001@Q\r9QjU\u0001\u0012g\u0016$\bK]8cC\nLG.\u001b;z\u0007>dGC\u00017w\u0011\u0015q\u0007\u00021\u0001@Q\rAQjU\u0001\rg\u0016$x+Z5hQR\u001cu\u000e\u001c\u000b\u0003YjDQA\\\u0005A\u0002}B3!C'}C\u0005i\u0018!B\u001a/a9\u0002\u0014\u0001B:fi.#2\u0001\\A\u0001\u0011\u0019q'\u00021\u0001\u0002\u0004A!\u0011QAA\u0004\u001b\u0005)\u0015bAA\u0005\u000b\n\u0019\u0011J\u001c;)\u0007)i5+\u0001\u0006tKRl\u0015\r_%uKJ$2\u0001\\A\t\u0011\u0019q7\u00021\u0001\u0002\u0004!\u001a1\"T*\u0002\rM,G\u000fV8m)\ra\u0017\u0011\u0004\u0005\u0007]2\u0001\r!a\u0007\u0011\t\u0005\u0015\u0011QD\u0005\u0004\u0003?)%A\u0002#pk\ndW\rK\u0002\r\u001bN\u000bqa]3u'\u0016,G\rF\u0002m\u0003OAaA\\\u0007A\u0002\u0005%\u0002\u0003BA\u0003\u0003WI1!!\fF\u0005\u0011auN\\4)\u00075i5+A\ntKR\fum\u001a:fO\u0006$\u0018n\u001c8EKB$\b\u000eF\u0002m\u0003kAaA\u001c\bA\u0002\u0005\r\u0001f\u0001\bNy\u0006Qa.^7TC6\u0004H.Z:\u0016\u0005\u0005\r\u0011a\u00038v[N\u000bW\u000e\u001d7fg\u0002\n1AZ5u)\r\u0001\u00141\t\u0005\b\u0003\u000b\n\u0002\u0019AA$\u0003\u001d!\u0017\r^1tKR\u0004D!!\u0013\u0002ZA1\u00111JA)\u0003+j!!!\u0014\u000b\u0007\u0005=C%A\u0002tc2LA!a\u0015\u0002N\t9A)\u0019;bg\u0016$\b\u0003BA,\u00033b\u0001\u0001\u0002\u0007\u0002\\\u0005\r\u0013\u0011!A\u0001\u0006\u0003\tiFA\u0002`II\nB!a\u0018\u0002fA!\u0011QAA1\u0013\r\t\u0019'\u0012\u0002\b\u001d>$\b.\u001b8h!\u0011\t)!a\u001a\n\u0007\u0005%TIA\u0002B]fD3!E'T\u0003%!(/Y5o\u00136\u0004H\u000e\u0006\u0007\u0002r\u0005]\u0014QSAP\u0003[\u000b\t\f\u0005\u0005\u0002\u0006\u0005M\u00141DA\u0002\u0013\r\t)(\u0012\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\u0005e$\u00031\u0001\u0002|\u0005I\u0011N\\:uC:\u001cWm\u001d\t\u0007\u0003{\n\u0019)a\"\u000e\u0005\u0005}$bAAAI\u0005\u0019!\u000f\u001a3\n\t\u0005\u0015\u0015q\u0010\u0002\u0004%\u0012#\u0005\u0003CA\u0003\u0003g\nI)a\u0007\u0011\t\u0005-\u0015\u0011S\u0007\u0003\u0003\u001bS1!a$#\u0003\u0019a\u0017N\\1mO&!\u00111SAG\u0005\u00191Vm\u0019;pe\"9\u0011q\u0013\nA\u0002\u0005e\u0015aB<fS\u001eDGo\u001d\t\u0007\u0003\u000b\tY*a\u0007\n\u0007\u0005uUIA\u0003BeJ\f\u0017\u0010C\u0004\u0002\"J\u0001\r!a)\u0002\u0013\u001d\fWo]:jC:\u001c\bCBA\u0003\u00037\u000b)\u000b\u0005\u0005\u0002\u0006\u0005M\u0014qUAT!\u0011\tY)!+\n\t\u0005-\u0016Q\u0012\u0002\f\t\u0016t7/\u001a,fGR|'\u000fC\u0004\u00020J\u0001\r!a\u0001\u0002\u00179,XNR3biV\u0014Xm\u001d\u0005\b\u0003g\u0013\u0002\u0019AA[\u0003\u0015Ign\u001d;s!\rA\u0014qW\u0005\u0004\u0003sK$aD%ogR\u0014X/\\3oi\u0006$\u0018n\u001c8\u0002\u001fQ\u0014\u0018M\\:g_Jl7k\u00195f[\u0006$B!a0\u0002LB!\u0011\u0011YAd\u001b\t\t\u0019M\u0003\u0003\u0002F\u00065\u0013!\u0002;za\u0016\u001c\u0018\u0002BAe\u0003\u0007\u0014!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\tim\u0005a\u0001\u0003\u007f\u000baa]2iK6\f\u0007fA\nN'\u0006Q\u0011N\\5u%\u0006tGm\\7\u0015\u0011\u0005U\u0017q[Am\u0003;\u0004\u0002\"!\u0002\u0002t\u0005e\u00151\u0015\u0005\b\u0003s\"\u0002\u0019AA>\u0011\u001d\tY\u000e\u0006a\u0001\u0003\u0007\t1B\\;n\u00072,8\u000f^3sg\"9\u0011q\u0016\u000bA\u0002\u0005\r\u0001f\u0001\u0001N'\u0006yq)Y;tg&\fg.T5yiV\u0014X\r\u0005\u00022-M9a#a:\u0002n\u0006M\b\u0003BA\u0003\u0003SL1!a;F\u0005\u0019\te.\u001f*fMB!\u0001(a<Z\u0013\r\t\t0\u000f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\t)0a@\u000e\u0005\u0005](\u0002BA}\u0003w\f!![8\u000b\u0005\u0005u\u0018\u0001\u00026bm\u0006LAA!\u0001\u0002x\na1+\u001a:jC2L'0\u00192mKR\u0011\u00111]\u0001\u0011\u001b\u0006CvLT+N?\u001a+\u0015\tV+S\u000bN\u000b\u0011#T!Y?:+Vj\u0018$F\u0003R+&+R*!\u0003\u0011aw.\u00193\u0015\u0007e\u0013i\u0001\u0003\u0004\u0003\u0010i\u0001\raP\u0001\u0005a\u0006$\b\u000eK\u0002\u001b\u001bN\u000b1$\u001e8qC\u000e\\W\u000b\u001d9feR\u0013\u0018.\u00198hk2\f'/T1ue&DHC\u0002B\f\u0005;\u0011\t\u0003\u0005\u0003\u0002\f\ne\u0011\u0002\u0002B\u000e\u0003\u001b\u00131\u0002R3og\u0016l\u0015\r\u001e:jq\"9!qD\u000eA\u0002\u0005\r\u0011!\u00018\t\u000f\t\r2\u00041\u0001\u0002\u001a\u0006\u0001BO]5b]\u001e,H.\u0019:WC2,Xm]\u0001\u0012[\u0016\u0014x-Z,fS\u001eDGo]'fC:\u001cHC\u0002B\u0015\u0005_\u0011\u0019\u0004\u0005\u0007\u0002\u0006\t-\u0012qUAT\u00037\tY\"C\u0002\u0003.\u0015\u0013a\u0001V;qY\u0016$\u0004b\u0002B\u00199\u0001\u0007!\u0011F\u0001\u0002C\"9!Q\u0007\u000fA\u0002\t%\u0012!\u00012\u00023U\u0004H-\u0019;f/\u0016Lw\r\u001b;t\u0003:$w)Y;tg&\fgn\u001d\u000b\u000b\u0005w\u0011iD!\u0011\u0003F\t%\u0003\u0003CA\u0003\u0003g\nY\"!*\t\u000f\t}R\u00041\u0001\u0002(\u0006!Q.Z1o\u0011\u001d\u0011\u0019%\ba\u0001\u0003O\u000b1aY8w\u0011\u001d\u00119%\ba\u0001\u00037\taa^3jO\"$\bb\u0002B&;\u0001\u0007\u00111D\u0001\u000bgVlw+Z5hQR\u001c\u0018\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B)!\u0011\u0011\u0019F!\u0017\u000e\u0005\tU#\u0002\u0002B,\u0003w\fA\u0001\\1oO&!!1\fB+\u0005\u0019y%M[3di\"\u001aa#T*)\u0007Ui5\u000b"
)
public class GaussianMixture extends Estimator implements GaussianMixtureParams, DefaultParamsWritable {
   private final String uid;
   private final int numSamples;
   private IntParam k;
   private IntParam aggregationDepth;
   private DoubleParam tol;
   private Param probabilityCol;
   private Param weightCol;
   private Param predictionCol;
   private LongParam seed;
   private Param featuresCol;
   private IntParam maxIter;

   public static GaussianMixture load(final String path) {
      return GaussianMixture$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GaussianMixture$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getK() {
      return GaussianMixtureParams.getK$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return GaussianMixtureParams.validateAndTransformSchema$(this, schema);
   }

   public final int getAggregationDepth() {
      return HasAggregationDepth.getAggregationDepth$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final String getProbabilityCol() {
      return HasProbabilityCol.getProbabilityCol$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
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

   public final void org$apache$spark$ml$clustering$GaussianMixtureParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
   }

   public final IntParam aggregationDepth() {
      return this.aggregationDepth;
   }

   public final void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1) {
      this.aggregationDepth = x$1;
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public final Param probabilityCol() {
      return this.probabilityCol;
   }

   public final void org$apache$spark$ml$param$shared$HasProbabilityCol$_setter_$probabilityCol_$eq(final Param x$1) {
      this.probabilityCol = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
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

   public GaussianMixture copy(final ParamMap extra) {
      return (GaussianMixture)this.defaultCopy(extra);
   }

   public GaussianMixture setFeaturesCol(final String value) {
      return (GaussianMixture)this.set(this.featuresCol(), value);
   }

   public GaussianMixture setPredictionCol(final String value) {
      return (GaussianMixture)this.set(this.predictionCol(), value);
   }

   public GaussianMixture setProbabilityCol(final String value) {
      return (GaussianMixture)this.set(this.probabilityCol(), value);
   }

   public GaussianMixture setWeightCol(final String value) {
      return (GaussianMixture)this.set(this.weightCol(), value);
   }

   public GaussianMixture setK(final int value) {
      return (GaussianMixture)this.set(this.k(), BoxesRunTime.boxToInteger(value));
   }

   public GaussianMixture setMaxIter(final int value) {
      return (GaussianMixture)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public GaussianMixture setTol(final double value) {
      return (GaussianMixture)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public GaussianMixture setSeed(final long value) {
      return (GaussianMixture)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public GaussianMixture setAggregationDepth(final int value) {
      return (GaussianMixture)this.set(this.aggregationDepth(), BoxesRunTime.boxToInteger(value));
   }

   private int numSamples() {
      return this.numSamples;
   }

   public GaussianMixtureModel fit(final Dataset dataset) {
      return (GaussianMixtureModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         int numFeatures;
         RDD instances;
         boolean var30;
         label43: {
            label42: {
               this.transformSchema(dataset.schema(), true);
               SparkSession spark = dataset.sparkSession();
               numFeatures = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.featuresCol()));
               .MODULE$.require(numFeatures < GaussianMixture$.MODULE$.MAX_NUM_FEATURES(), () -> "GaussianMixture cannot handle more than " + GaussianMixture$.MODULE$.MAX_NUM_FEATURES() + " features because the size of the covariance matrix is quadratic in the number of features.");
               instr.logPipelineStage(this);
               instr.logDataset(dataset);
               instr.logParams(this, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Param[]{this.featuresCol(), this.predictionCol(), this.probabilityCol(), this.weightCol(), this.k(), this.maxIter(), this.seed(), this.tol(), this.aggregationDepth()}));
               instr.logNumFeatures((long)numFeatures);
               Dataset var10000 = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkNonNanVectors(DatasetUtils$.MODULE$.columnToVector(dataset, (String)this.$(this.featuresCol()))), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()))})));
               SQLImplicits var10001 = spark.implicits();
               JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
               JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixture.class.getClassLoader());

               final class $typecreator5$1 extends TypeCreator {
                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     Universe $u = $m$untyped.universe();
                     return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
                  }

                  public $typecreator5$1() {
                  }
               }

               instances = var10000.as(var10001.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1()))).rdd().setName("training instances");
               StorageLevel var29 = dataset.storageLevel();
               StorageLevel var11 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var29 == null) {
                  if (var11 == null) {
                     break label42;
                  }
               } else if (var29.equals(var11)) {
                  break label42;
               }

               var30 = false;
               break label43;
            }

            var30 = true;
         }

         boolean handlePersistence = var30;
         if (handlePersistence) {
            instances.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         } else {
            BoxedUnit var31 = BoxedUnit.UNIT;
         }

         Tuple2 var13 = this.initRandom(instances, BoxesRunTime.unboxToInt(this.$(this.k())), numFeatures);
         if (var13 != null) {
            double[] weights = (double[])var13._1();
            Tuple2[] gaussians = (Tuple2[])var13._2();
            Tuple2 var12 = new Tuple2(weights, gaussians);
            double[] weightsx = (double[])var12._1();
            Tuple2[] gaussiansx = (Tuple2[])var12._2();
            Tuple2 var19 = this.trainImpl(instances, weightsx, gaussiansx, numFeatures, instr);
            if (var19 != null) {
               double logLikelihood = var19._1$mcD$sp();
               int iteration = var19._2$mcI$sp();
               Tuple2.mcDI.sp var18 = new Tuple2.mcDI.sp(logLikelihood, iteration);
               double logLikelihood = ((Tuple2)var18)._1$mcD$sp();
               int iterationx = ((Tuple2)var18)._2$mcI$sp();
               if (handlePersistence) {
                  instances.unpersist(instances.unpersist$default$1());
               } else {
                  BoxedUnit var32 = BoxedUnit.UNIT;
               }

               MultivariateGaussian[] gaussianDists = (MultivariateGaussian[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])gaussiansx), (x0$1) -> {
                  if (x0$1 != null) {
                     DenseVector mean = (DenseVector)x0$1._1();
                     DenseVector covVec = (DenseVector)x0$1._2();
                     DenseMatrix cov = GaussianMixture$.MODULE$.unpackUpperTriangularMatrix(numFeatures, covVec.values());
                     return new MultivariateGaussian(mean, cov);
                  } else {
                     throw new MatchError(x0$1);
                  }
               }, scala.reflect.ClassTag..MODULE$.apply(MultivariateGaussian.class));
               GaussianMixtureModel model = (GaussianMixtureModel)((Model)this.copyValues(new GaussianMixtureModel(this.uid(), weightsx, gaussianDists), this.copyValues$default$2())).setParent(this);
               GaussianMixtureSummary summary = new GaussianMixtureSummary(model.transform(dataset), (String)this.$(this.predictionCol()), (String)this.$(this.probabilityCol()), (String)this.$(this.featuresCol()), BoxesRunTime.unboxToInt(this.$(this.k())), logLikelihood, iterationx);
               instr.logNamedValue("logLikelihood", logLikelihood);
               instr.logNamedValue("clusterSizes", summary.clusterSizes());
               return (GaussianMixtureModel)model.setSummary(new Some(summary));
            } else {
               throw new MatchError(var19);
            }
         } else {
            throw new MatchError(var13);
         }
      });
   }

   private Tuple2 trainImpl(final RDD instances, final double[] weights, final Tuple2[] gaussians, final int numFeatures, final Instrumentation instr) {
      SparkContext sc = instances.sparkContext();
      double logLikelihood = -Double.MAX_VALUE;
      double logLikelihoodPrev = (double)0.0F;

      IntRef iteration;
      for(iteration = IntRef.create(0); iteration.elem < BoxesRunTime.unboxToInt(this.$(this.maxIter())) && scala.math.package..MODULE$.abs(logLikelihood - logLikelihoodPrev) > BoxesRunTime.unboxToDouble(this.$(this.tol())); ++iteration.elem) {
         DoubleAccumulator weightSumAccum = iteration.elem == 0 ? sc.doubleAccumulator() : null;
         DoubleAccumulator logLikelihoodAccum = sc.doubleAccumulator();
         Broadcast bcWeights = sc.broadcast(weights, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
         Broadcast bcGaussians = sc.broadcast(gaussians, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Tuple2.class)));
         scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(instances.mapPartitions((iter) -> {
            if (!iter.nonEmpty()) {
               return scala.package..MODULE$.Iterator().empty();
            } else {
               ExpectationAggregator agg = new ExpectationAggregator(numFeatures, bcWeights, bcGaussians);

               while(iter.hasNext()) {
                  agg.add((Tuple2)iter.next());
               }

               double ws = BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(agg.weights()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
               if (iteration.elem == 0) {
                  weightSumAccum.add(ws);
               }

               logLikelihoodAccum.add(agg.logLikelihood());
               return scala.package..MODULE$.Iterator().tabulate(((double[])bcWeights.value()).length, (i) -> $anonfun$trainImpl$2(agg, ws, BoxesRunTime.unboxToInt(i)));
            }
         }, instances.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class), scala.math.Ordering.Int..MODULE$).reduceByKey((a, b) -> GaussianMixture$.MODULE$.org$apache$spark$ml$clustering$GaussianMixture$$mergeWeightsMeans(a, b)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class), scala.math.Ordering.Int..MODULE$).mapValues((x0$1) -> {
            if (x0$1 != null) {
               DenseVector mean = (DenseVector)x0$1._1();
               DenseVector cov = (DenseVector)x0$1._2();
               double w = BoxesRunTime.unboxToDouble(x0$1._3());
               double ws = BoxesRunTime.unboxToDouble(x0$1._4());
               return GaussianMixture$.MODULE$.updateWeightsAndGaussians(mean, cov, w, ws);
            } else {
               throw new MatchError(x0$1);
            }
         }).collect()), (x0$2) -> {
            $anonfun$trainImpl$5(weights, gaussians, x0$2);
            return BoxedUnit.UNIT;
         });
         bcWeights.destroy();
         bcGaussians.destroy();
         if (iteration.elem == 0) {
            instr.logNumExamples(weightSumAccum.count());
            instr.logSumOfWeights(.MODULE$.Double2double(weightSumAccum.value()));
         }

         logLikelihoodPrev = logLikelihood;
         logLikelihood = .MODULE$.Double2double(logLikelihoodAccum.value());
         instr.logNamedValue("logLikelihood@iter" + iteration.elem, logLikelihood);
      }

      return new Tuple2.mcDI.sp(logLikelihood, iteration.elem);
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   private Tuple2 initRandom(final RDD instances, final int numClusters, final int numFeatures) {
      Tuple2 var6 = scala.collection.ArrayOps..MODULE$.unzip$extension(.MODULE$.refArrayOps(instances.takeSample(true, numClusters * this.numSamples(), BoxesRunTime.unboxToLong(this.$(this.seed())))), .MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.apply(Vector.class), scala.reflect.ClassTag..MODULE$.Double());
      if (var6 != null) {
         Vector[] samples = (Vector[])var6._1();
         double[] sampleWeights = (double[])var6._2();
         Tuple2 var5 = new Tuple2(samples, sampleWeights);
         Vector[] samples = (Vector[])var5._1();
         double[] sampleWeights = (double[])var5._2();
         double[] weights = new double[numClusters];
         double weightSum = BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(sampleWeights).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
         Tuple2[] gaussians = (Tuple2[])scala.Array..MODULE$.tabulate(numClusters, (i) -> $anonfun$initRandom$1(this, samples, sampleWeights, weights, weightSum, numFeatures, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         return new Tuple2(weights, gaussians);
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$trainImpl$2(final ExpectationAggregator agg$1, final double ws$1, final int i) {
      return new Tuple2(BoxesRunTime.boxToInteger(i), new Tuple4(agg$1.means()[i], agg$1.covs()[i], BoxesRunTime.boxToDouble(agg$1.weights()[i]), BoxesRunTime.boxToDouble(ws$1)));
   }

   // $FF: synthetic method
   public static final void $anonfun$trainImpl$5(final double[] weights$1, final Tuple2[] gaussians$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         int i = x0$2._1$mcI$sp();
         Tuple2 var6 = (Tuple2)x0$2._2();
         if (var6 != null) {
            double weight = var6._1$mcD$sp();
            Tuple2 gaussian = (Tuple2)var6._2();
            weights$1[i] = weight;
            gaussians$1[i] = gaussian;
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$2);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$initRandom$1(final GaussianMixture $this, final Vector[] samples$1, final double[] sampleWeights$1, final double[] weights$2, final double weightSum$1, final int numFeatures$3, final int i) {
      int start = i * $this.numSamples();
      int end = start + $this.numSamples();
      Vector[] sampleSlice = (Vector[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.refArrayOps((Object[])samples$1), start, end);
      double[] weightSlice = (double[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.doubleArrayOps(sampleWeights$1), start, end);
      double localWeightSum = BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(weightSlice).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      weights$2[i] = localWeightSum / weightSum$1;
      DenseVector v = new DenseVector(new double[numFeatures$3]);

      for(int j = 0; j < $this.numSamples(); ++j) {
         org.apache.spark.ml.linalg.BLAS..MODULE$.axpy(weightSlice[j], sampleSlice[j], v);
      }

      org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / localWeightSum, v);
      DenseVector mean = v;
      breeze.linalg.Vector ss = (new DenseVector(new double[numFeatures$3])).asBreeze();

      for(int j = 0; j < $this.numSamples(); ++j) {
         breeze.linalg.Vector v = (breeze.linalg.Vector)sampleSlice[j].asBreeze().$minus(mean.asBreeze(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpSub());
         ss.$plus$eq(((ImmutableNumericOps)v.$times(v, breeze.linalg.operators.HasOps..MODULE$.impl_T_S_eq_U_from_ZipMap(breeze.linalg.Vector..MODULE$.scalarOf(), breeze.linalg.operators.OpMulMatrix..MODULE$.opMulMatrixFromSemiring(breeze.math.Semiring..MODULE$.semiringD()), breeze.linalg.operators.HasOps..MODULE$.canZipMapValues_V(scala.reflect.ClassTag..MODULE$.Double())))).$times(BoxesRunTime.boxToDouble(weightSlice[j]), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_S_eq_V_Double_OpMulMatrix()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_V_V_Idempotent_Double_OpAdd());
      }

      Vector diagVec = org.apache.spark.ml.linalg.Vectors..MODULE$.fromBreeze(ss);
      org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / localWeightSum, diagVec);
      DenseVector covVec = new DenseVector((double[])scala.Array..MODULE$.ofDim(numFeatures$3 * (numFeatures$3 + 1) / 2, scala.reflect.ClassTag..MODULE$.Double()));
      diagVec.foreach((JFunction2.mcVID.sp)(ix, vx) -> covVec.values()[ix + ix * (ix + 1) / 2] = vx);
      return new Tuple2(mean, covVec);
   }

   public GaussianMixture(final String uid) {
      this.uid = uid;
      HasMaxIter.$init$(this);
      HasFeaturesCol.$init$(this);
      HasSeed.$init$(this);
      HasPredictionCol.$init$(this);
      HasWeightCol.$init$(this);
      HasProbabilityCol.$init$(this);
      HasTol.$init$(this);
      HasAggregationDepth.$init$(this);
      GaussianMixtureParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.numSamples = 5;
      Statics.releaseFence();
   }

   public GaussianMixture() {
      this(Identifiable$.MODULE$.randomUID("GaussianMixture"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
