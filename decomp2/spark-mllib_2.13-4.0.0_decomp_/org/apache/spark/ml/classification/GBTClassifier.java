package org.apache.spark.ml.classification;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasValidationIndicatorCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.regression.DecisionTreeRegressionModel;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.GBTClassifierParams;
import org.apache.spark.ml.tree.GBTParams;
import org.apache.spark.ml.tree.HasVarianceImpurity;
import org.apache.spark.ml.tree.TreeEnsembleClassifierParams;
import org.apache.spark.ml.tree.TreeEnsembleParams;
import org.apache.spark.ml.tree.impl.GradientBoostedTrees$;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.MetadataUtils$;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.loss.ClassificationLoss;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Enumeration;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dd\u0001\u0002\u0011\"\u00011B\u0001\"\u0014\u0001\u0003\u0006\u0004%\tE\u0014\u0005\tK\u0002\u0011\t\u0011)A\u0005\u001f\")q\r\u0001C\u0001Q\")q\r\u0001C\u0001Y\")a\u000e\u0001C\u0001_\")\u0001\u0010\u0001C\u0001s\")A\u0010\u0001C\u0001{\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!!\r\u0001\t\u0003\t\u0019\u0004C\u0004\u0002:\u0001!\t!a\u000f\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002D!9\u0011\u0011\n\u0001\u0005\u0002\u0005-\u0003bBA,\u0001\u0011\u0005\u0011\u0011\f\u0005\b\u0003?\u0002A\u0011AA1\u0011\u001d\t9\u0007\u0001C\u0001\u0003SBq!a\u001d\u0001\t\u0003\t)\bC\u0004\u0002|\u0001!\t!! \t\u000f\u0005\u001d\u0005\u0001\"\u0001\u0002\n\"9\u0011q\u0012\u0001\u0005R\u0005E\u0005bBA^\u0001\u0011\u0005\u0013Q\u0018\u0005\u000f\u0003+\u0004\u0001\u0013aA\u0001\u0002\u0013%\u0011q\u001bB\u0004\u000f\u001d\u0011\t\"\tE\u0001\u0005'1a\u0001I\u0011\t\u0002\tU\u0001BB4\u001b\t\u0003\u0011\u0019\u0004C\u0005\u00036i\u0011\r\u0011\"\u0002\u00038!A!\u0011\t\u000e!\u0002\u001b\u0011I\u0004C\u0004\u0003Fi!\tEa\u0012\t\u0013\tM#$!A\u0005\n\tU#!D$C)\u000ec\u0017m]:jM&,'O\u0003\u0002#G\u0005q1\r\\1tg&4\u0017nY1uS>t'B\u0001\u0013&\u0003\tiGN\u0003\u0002'O\u0005)1\u000f]1sW*\u0011\u0001&K\u0001\u0007CB\f7\r[3\u000b\u0003)\n1a\u001c:h\u0007\u0001\u0019R\u0001A\u0017<\u0003\u001e\u0003RAL\u00182oaj\u0011!I\u0005\u0003a\u0005\u0012q\u0003\u0015:pE\u0006\u0014\u0017\u000e\\5ti&\u001c7\t\\1tg&4\u0017.\u001a:\u0011\u0005I*T\"A\u001a\u000b\u0005Q\u001a\u0013A\u00027j]\u0006dw-\u0003\u00027g\t1a+Z2u_J\u0004\"A\f\u0001\u0011\u00059J\u0014B\u0001\u001e\"\u0005Y9%\tV\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d\u0007C\u0001\u001f@\u001b\u0005i$B\u0001 $\u0003\u0011!(/Z3\n\u0005\u0001k$aE$C)\u000ec\u0017m]:jM&,'\u000fU1sC6\u001c\bC\u0001\"F\u001b\u0005\u0019%B\u0001#$\u0003\u0011)H/\u001b7\n\u0005\u0019\u001b%!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7o\u0016:ji\u0006\u0014G.\u001a\t\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015\u0016\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u0019&\u0013q\u0001T8hO&tw-A\u0002vS\u0012,\u0012a\u0014\t\u0003!fs!!U,\u0011\u0005I+V\"A*\u000b\u0005Q[\u0013A\u0002\u001fs_>$hHC\u0001W\u0003\u0015\u00198-\u00197b\u0013\tAV+\u0001\u0004Qe\u0016$WMZ\u0005\u00035n\u0013aa\u0015;sS:<'B\u0001-VQ\r\tQl\u0019\t\u0003=\u0006l\u0011a\u0018\u0006\u0003A\u0016\n!\"\u00198o_R\fG/[8o\u0013\t\u0011wLA\u0003TS:\u001cW-I\u0001e\u0003\u0015\td\u0006\u000e\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\ti6-\u0001\u0004=S:LGO\u0010\u000b\u0003o%DQ!T\u0002A\u0002=C3![/dQ\r\u0019Ql\u0019\u000b\u0002o!\u001aA!X2\u0002\u0017M,G/T1y\t\u0016\u0004H\u000f\u001b\u000b\u0003aFl\u0011\u0001\u0001\u0005\u0006e\u0016\u0001\ra]\u0001\u0006m\u0006dW/\u001a\t\u0003iVl\u0011!V\u0005\u0003mV\u00131!\u00138uQ\r)QlY\u0001\u000bg\u0016$X*\u0019=CS:\u001cHC\u00019{\u0011\u0015\u0011h\u00011\u0001tQ\r1QlY\u0001\u0017g\u0016$X*\u001b8J]N$\u0018M\\2fgB+'OT8eKR\u0011\u0001O \u0005\u0006e\u001e\u0001\ra\u001d\u0015\u0004\u000fu\u001b\u0017aG:fi6KgnV3jO\"$hI]1di&|g\u000eU3s\u001d>$W\rF\u0002q\u0003\u000bAaA\u001d\u0005A\u0002\u0005\u001d\u0001c\u0001;\u0002\n%\u0019\u00111B+\u0003\r\u0011{WO\u00197fQ\u0011AQ,a\u0004\"\u0005\u0005E\u0011!B\u001a/a9\u0002\u0014AD:fi6Kg.\u00138g_\u001e\u000b\u0017N\u001c\u000b\u0004a\u0006]\u0001B\u0002:\n\u0001\u0004\t9\u0001K\u0002\n;\u000e\f\u0001c]3u\u001b\u0006DX*Z7pefLe.\u0014\"\u0015\u0007A\fy\u0002C\u0003s\u0015\u0001\u00071\u000fK\u0002\u000b;\u000e\fqb]3u\u0007\u0006\u001c\u0007.\u001a(pI\u0016LEm\u001d\u000b\u0004a\u0006\u001d\u0002B\u0002:\f\u0001\u0004\tI\u0003E\u0002u\u0003WI1!!\fV\u0005\u001d\u0011un\u001c7fC:D3aC/d\u0003U\u0019X\r^\"iK\u000e\\\u0007o\\5oi&sG/\u001a:wC2$2\u0001]A\u001b\u0011\u0015\u0011H\u00021\u0001tQ\raQlY\u0001\fg\u0016$\u0018*\u001c9ve&$\u0018\u0010F\u0002q\u0003{AQA]\u0007A\u0002=C3!D/d\u0003I\u0019X\r^*vEN\fW\u000e\u001d7j]\u001e\u0014\u0016\r^3\u0015\u0007A\f)\u0005\u0003\u0004s\u001d\u0001\u0007\u0011q\u0001\u0015\u0004\u001du\u001b\u0017aB:fiN+W\r\u001a\u000b\u0004a\u00065\u0003B\u0002:\u0010\u0001\u0004\ty\u0005E\u0002u\u0003#J1!a\u0015V\u0005\u0011auN\\4)\u0007=i6-\u0001\u0006tKRl\u0015\r_%uKJ$2\u0001]A.\u0011\u0015\u0011\b\u00031\u0001tQ\r\u0001RlY\u0001\fg\u0016$8\u000b^3q'&TX\rF\u0002q\u0003GBaA]\tA\u0002\u0005\u001d\u0001fA\t^G\u0006A2/\u001a;GK\u0006$XO]3Tk\n\u001cX\r^*ue\u0006$XmZ=\u0015\u0007A\fY\u0007C\u0003s%\u0001\u0007q\n\u000b\u0003\u0013;\u0006=\u0014EAA9\u0003\u0015\u0011df\r\u00181\u0003-\u0019X\r\u001e'pgN$\u0016\u0010]3\u0015\u0007A\f9\bC\u0003s'\u0001\u0007q\nK\u0002\u0014;\u000e\f\u0011d]3u-\u0006d\u0017\u000eZ1uS>t\u0017J\u001c3jG\u0006$xN]\"pYR\u0019\u0001/a \t\u000bI$\u0002\u0019A()\tQi\u00161Q\u0011\u0003\u0003\u000b\u000bQA\r\u00185]A\nAb]3u/\u0016Lw\r\u001b;D_2$2\u0001]AF\u0011\u0015\u0011X\u00031\u0001PQ\u0011)R,a\u0004\u0002\u000bQ\u0014\u0018-\u001b8\u0015\u0007a\n\u0019\nC\u0004\u0002\u0016Z\u0001\r!a&\u0002\u000f\u0011\fG/Y:fiB\"\u0011\u0011TAU!\u0019\tY*!)\u0002&6\u0011\u0011Q\u0014\u0006\u0004\u0003?+\u0013aA:rY&!\u00111UAO\u0005\u001d!\u0015\r^1tKR\u0004B!a*\u0002*2\u0001A\u0001DAV\u0003'\u000b\t\u0011!A\u0003\u0002\u00055&aA0%cE!\u0011qVA[!\r!\u0018\u0011W\u0005\u0004\u0003g+&a\u0002(pi\"Lgn\u001a\t\u0004i\u0006]\u0016bAA]+\n\u0019\u0011I\\=\u0002\t\r|\u0007/\u001f\u000b\u0004o\u0005}\u0006bBAa/\u0001\u0007\u00111Y\u0001\u0006Kb$(/\u0019\t\u0005\u0003\u000b\fY-\u0004\u0002\u0002H*\u0019\u0011\u0011Z\u0012\u0002\u000bA\f'/Y7\n\t\u00055\u0017q\u0019\u0002\t!\u0006\u0014\u0018-\\'ba\"\"q#XAiC\t\t\u0019.A\u00032]Qr\u0013'\u0001\u000ftkB,'\u000fJ4fi>cGMQ8pgRLgnZ*ue\u0006$XmZ=\u0015\r\u0005e\u00171^A{!\u0011\tY.a:\u000e\u0005\u0005u'\u0002BAp\u0003C\fQbY8oM&<WO]1uS>t'b\u0001 \u0002d*\u0019\u0011Q]\u0013\u0002\u000b5dG.\u001b2\n\t\u0005%\u0018Q\u001c\u0002\u0011\u0005>|7\u000f^5oON#(/\u0019;fOfDq!!<\u0019\u0001\u0004\ty/A\ndCR,wm\u001c:jG\u0006dg)Z1ukJ,7\u000fE\u0003Q\u0003c\u001c8/C\u0002\u0002tn\u00131!T1q\u0011\u001d\t9\u0010\u0007a\u0001\u0003s\fqa\u001c7e\u00032<w\u000e\u0005\u0003\u0002|\n\u0005a\u0002BAn\u0003{LA!a@\u0002^\u0006!\u0011\t\\4p\u0013\u0011\u0011\u0019A!\u0002\u0003\t\u0005cwm\u001c\u0006\u0005\u0003\u007f\fi.\u0003\u0003\u0003\n\t-\u0011AF4fi>cGMQ8pgRLgnZ*ue\u0006$XmZ=\n\u0007\t5QHA\u0005H\u0005R\u0003\u0016M]1ng\"\u001a\u0001!X2\u0002\u001b\u001d\u0013Ek\u00117bgNLg-[3s!\tq#dE\u0004\u001b\u0005/\u0011iBa\t\u0011\u0007Q\u0014I\"C\u0002\u0003\u001cU\u0013a!\u00118z%\u00164\u0007\u0003\u0002\"\u0003 ]J1A!\tD\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004BA!\n\u000305\u0011!q\u0005\u0006\u0005\u0005S\u0011Y#\u0001\u0002j_*\u0011!QF\u0001\u0005U\u00064\u0018-\u0003\u0003\u00032\t\u001d\"\u0001D*fe&\fG.\u001b>bE2,GC\u0001B\n\u0003I\u0019X\u000f\u001d9peR,G\rT8tgRK\b/Z:\u0016\u0005\te\u0002\u0003\u0002;\u0003<=K1A!\u0010V\u0005\u0015\t%O]1zQ\raRlY\u0001\u0014gV\u0004\bo\u001c:uK\u0012dun]:UsB,7\u000f\t\u0015\u0004;u\u001b\u0017\u0001\u00027pC\u0012$2a\u000eB%\u0011\u0019\u0011YE\ba\u0001\u001f\u0006!\u0001/\u0019;iQ\u0011qRLa\u0014\"\u0005\tE\u0013!\u0002\u001a/a9\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B,!\u0011\u0011IFa\u0018\u000e\u0005\tm#\u0002\u0002B/\u0005W\tA\u0001\\1oO&!!\u0011\rB.\u0005\u0019y%M[3di\"\u001a!$X2)\u0007ei6\r"
)
public class GBTClassifier extends ProbabilisticClassifier implements GBTClassifierParams, DefaultParamsWritable {
   private final String uid;
   private Param lossType;
   private Param impurity;
   private DoubleParam validationTol;
   private DoubleParam stepSize;
   private Param validationIndicatorCol;
   private IntParam maxIter;
   private DoubleParam subsamplingRate;
   private Param featureSubsetStrategy;
   private Param leafCol;
   private IntParam maxDepth;
   private IntParam maxBins;
   private IntParam minInstancesPerNode;
   private DoubleParam minWeightFractionPerNode;
   private DoubleParam minInfoGain;
   private IntParam maxMemoryInMB;
   private BooleanParam cacheNodeIds;
   private Param weightCol;
   private LongParam seed;
   private IntParam checkpointInterval;

   public static GBTClassifier load(final String path) {
      return GBTClassifier$.MODULE$.load(path);
   }

   public static String[] supportedLossTypes() {
      return GBTClassifier$.MODULE$.supportedLossTypes();
   }

   public static MLReader read() {
      return GBTClassifier$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getLossType() {
      return GBTClassifierParams.getLossType$(this);
   }

   public ClassificationLoss getOldLossType() {
      return GBTClassifierParams.getOldLossType$(this);
   }

   public final String getImpurity() {
      return HasVarianceImpurity.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return HasVarianceImpurity.getOldImpurity$(this);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$TreeEnsembleClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ProbabilisticClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return TreeEnsembleClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   // $FF: synthetic method
   public Strategy org$apache$spark$ml$tree$GBTParams$$super$getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity) {
      return TreeEnsembleParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity);
   }

   public final double getValidationTol() {
      return GBTParams.getValidationTol$(this);
   }

   public BoostingStrategy getOldBoostingStrategy(final Map categoricalFeatures, final Enumeration.Value oldAlgo) {
      return GBTParams.getOldBoostingStrategy$(this, categoricalFeatures, oldAlgo);
   }

   public final String getValidationIndicatorCol() {
      return HasValidationIndicatorCol.getValidationIndicatorCol$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   // $FF: synthetic method
   public Strategy org$apache$spark$ml$tree$TreeEnsembleParams$$super$getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity, final double subsamplingRate) {
      return DecisionTreeParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity, subsamplingRate);
   }

   public final double getSubsamplingRate() {
      return TreeEnsembleParams.getSubsamplingRate$(this);
   }

   public Strategy getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity) {
      return TreeEnsembleParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity);
   }

   public final String getFeatureSubsetStrategy() {
      return TreeEnsembleParams.getFeatureSubsetStrategy$(this);
   }

   public final DecisionTreeParams setLeafCol(final String value) {
      return DecisionTreeParams.setLeafCol$(this, value);
   }

   public final String getLeafCol() {
      return DecisionTreeParams.getLeafCol$(this);
   }

   public final int getMaxDepth() {
      return DecisionTreeParams.getMaxDepth$(this);
   }

   public final int getMaxBins() {
      return DecisionTreeParams.getMaxBins$(this);
   }

   public final int getMinInstancesPerNode() {
      return DecisionTreeParams.getMinInstancesPerNode$(this);
   }

   public final double getMinWeightFractionPerNode() {
      return DecisionTreeParams.getMinWeightFractionPerNode$(this);
   }

   public final double getMinInfoGain() {
      return DecisionTreeParams.getMinInfoGain$(this);
   }

   public final int getMaxMemoryInMB() {
      return DecisionTreeParams.getMaxMemoryInMB$(this);
   }

   public final boolean getCacheNodeIds() {
      return DecisionTreeParams.getCacheNodeIds$(this);
   }

   public Strategy getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity, final double subsamplingRate) {
      return DecisionTreeParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity, subsamplingRate);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final int getCheckpointInterval() {
      return HasCheckpointInterval.getCheckpointInterval$(this);
   }

   public Param lossType() {
      return this.lossType;
   }

   public void org$apache$spark$ml$tree$GBTClassifierParams$_setter_$lossType_$eq(final Param x$1) {
      this.lossType = x$1;
   }

   public final Param impurity() {
      return this.impurity;
   }

   public final void org$apache$spark$ml$tree$HasVarianceImpurity$_setter_$impurity_$eq(final Param x$1) {
      this.impurity = x$1;
   }

   public final DoubleParam validationTol() {
      return this.validationTol;
   }

   public final DoubleParam stepSize() {
      return this.stepSize;
   }

   public final void org$apache$spark$ml$tree$GBTParams$_setter_$validationTol_$eq(final DoubleParam x$1) {
      this.validationTol = x$1;
   }

   public final void org$apache$spark$ml$tree$GBTParams$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
   }

   public final Param validationIndicatorCol() {
      return this.validationIndicatorCol;
   }

   public final void org$apache$spark$ml$param$shared$HasValidationIndicatorCol$_setter_$validationIndicatorCol_$eq(final Param x$1) {
      this.validationIndicatorCol = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public final DoubleParam subsamplingRate() {
      return this.subsamplingRate;
   }

   public final Param featureSubsetStrategy() {
      return this.featureSubsetStrategy;
   }

   public final void org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$subsamplingRate_$eq(final DoubleParam x$1) {
      this.subsamplingRate = x$1;
   }

   public final void org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$featureSubsetStrategy_$eq(final Param x$1) {
      this.featureSubsetStrategy = x$1;
   }

   public final Param leafCol() {
      return this.leafCol;
   }

   public final IntParam maxDepth() {
      return this.maxDepth;
   }

   public final IntParam maxBins() {
      return this.maxBins;
   }

   public final IntParam minInstancesPerNode() {
      return this.minInstancesPerNode;
   }

   public final DoubleParam minWeightFractionPerNode() {
      return this.minWeightFractionPerNode;
   }

   public final DoubleParam minInfoGain() {
      return this.minInfoGain;
   }

   public final IntParam maxMemoryInMB() {
      return this.maxMemoryInMB;
   }

   public final BooleanParam cacheNodeIds() {
      return this.cacheNodeIds;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$leafCol_$eq(final Param x$1) {
      this.leafCol = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxDepth_$eq(final IntParam x$1) {
      this.maxDepth = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxBins_$eq(final IntParam x$1) {
      this.maxBins = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInstancesPerNode_$eq(final IntParam x$1) {
      this.minInstancesPerNode = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minWeightFractionPerNode_$eq(final DoubleParam x$1) {
      this.minWeightFractionPerNode = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInfoGain_$eq(final DoubleParam x$1) {
      this.minInfoGain = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxMemoryInMB_$eq(final IntParam x$1) {
      this.maxMemoryInMB = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$cacheNodeIds_$eq(final BooleanParam x$1) {
      this.cacheNodeIds = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public final IntParam checkpointInterval() {
      return this.checkpointInterval;
   }

   public final void org$apache$spark$ml$param$shared$HasCheckpointInterval$_setter_$checkpointInterval_$eq(final IntParam x$1) {
      this.checkpointInterval = x$1;
   }

   // $FF: synthetic method
   private BoostingStrategy super$getOldBoostingStrategy(final Map categoricalFeatures, final Enumeration.Value oldAlgo) {
      return GBTParams.getOldBoostingStrategy$(this, categoricalFeatures, oldAlgo);
   }

   public String uid() {
      return this.uid;
   }

   public GBTClassifier setMaxDepth(final int value) {
      return (GBTClassifier)this.set(this.maxDepth(), BoxesRunTime.boxToInteger(value));
   }

   public GBTClassifier setMaxBins(final int value) {
      return (GBTClassifier)this.set(this.maxBins(), BoxesRunTime.boxToInteger(value));
   }

   public GBTClassifier setMinInstancesPerNode(final int value) {
      return (GBTClassifier)this.set(this.minInstancesPerNode(), BoxesRunTime.boxToInteger(value));
   }

   public GBTClassifier setMinWeightFractionPerNode(final double value) {
      return (GBTClassifier)this.set(this.minWeightFractionPerNode(), BoxesRunTime.boxToDouble(value));
   }

   public GBTClassifier setMinInfoGain(final double value) {
      return (GBTClassifier)this.set(this.minInfoGain(), BoxesRunTime.boxToDouble(value));
   }

   public GBTClassifier setMaxMemoryInMB(final int value) {
      return (GBTClassifier)this.set(this.maxMemoryInMB(), BoxesRunTime.boxToInteger(value));
   }

   public GBTClassifier setCacheNodeIds(final boolean value) {
      return (GBTClassifier)this.set(this.cacheNodeIds(), BoxesRunTime.boxToBoolean(value));
   }

   public GBTClassifier setCheckpointInterval(final int value) {
      return (GBTClassifier)this.set(this.checkpointInterval(), BoxesRunTime.boxToInteger(value));
   }

   public GBTClassifier setImpurity(final String value) {
      this.logWarning(() -> "GBTClassifier.setImpurity should NOT be used");
      return this;
   }

   public GBTClassifier setSubsamplingRate(final double value) {
      return (GBTClassifier)this.set(this.subsamplingRate(), BoxesRunTime.boxToDouble(value));
   }

   public GBTClassifier setSeed(final long value) {
      return (GBTClassifier)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public GBTClassifier setMaxIter(final int value) {
      return (GBTClassifier)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public GBTClassifier setStepSize(final double value) {
      return (GBTClassifier)this.set(this.stepSize(), BoxesRunTime.boxToDouble(value));
   }

   public GBTClassifier setFeatureSubsetStrategy(final String value) {
      return (GBTClassifier)this.set(this.featureSubsetStrategy(), value);
   }

   public GBTClassifier setLossType(final String value) {
      return (GBTClassifier)this.set(this.lossType(), value);
   }

   public GBTClassifier setValidationIndicatorCol(final String value) {
      return (GBTClassifier)this.set(this.validationIndicatorCol(), value);
   }

   public GBTClassifier setWeightCol(final String value) {
      return (GBTClassifier)this.set(this.weightCol(), value);
   }

   public GBTClassificationModel train(final Dataset dataset) {
      return (GBTClassificationModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         boolean withValidation = this.isDefined(this.validationIndicatorCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.validationIndicatorCol())));
         Tuple2 var7 = withValidation ? new Tuple2(DatasetUtils$.MODULE$.extractInstances(this, dataset.filter(org.apache.spark.sql.functions..MODULE$.not(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.validationIndicatorCol())))), new Some(BoxesRunTime.boxToInteger(2))), DatasetUtils$.MODULE$.extractInstances(this, dataset.filter(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.validationIndicatorCol()))), new Some(BoxesRunTime.boxToInteger(2)))) : new Tuple2(DatasetUtils$.MODULE$.extractInstances(this, dataset, new Some(BoxesRunTime.boxToInteger(2))), (Object)null);
         if (var7 != null) {
            RDD trainDataset = (RDD)var7._1();
            RDD validationDataset = (RDD)var7._2();
            Tuple2 var6 = new Tuple2(trainDataset, validationDataset);
            RDD trainDatasetx = (RDD)var6._1();
            RDD validationDataset = (RDD)var6._2();
            byte numClasses = 2;
            if (this.isDefined(this.thresholds())) {
               scala.Predef..MODULE$.require(((double[])this.$(this.thresholds())).length == numClasses, () -> this.getClass().getSimpleName() + ".train() called with non-matching numClasses and thresholds.length. numClasses=" + numClasses + ", but thresholds has length " + ((double[])this.$(this.thresholds())).length);
            }

            instr.logPipelineStage(this);
            instr.logDataset(dataset);
            instr.logParams(this, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.weightCol(), this.featuresCol(), this.predictionCol(), this.leafCol(), this.impurity(), this.lossType(), this.maxDepth(), this.maxBins(), this.maxIter(), this.maxMemoryInMB(), this.minInfoGain(), this.minInstancesPerNode(), this.minWeightFractionPerNode(), this.seed(), this.stepSize(), this.subsamplingRate(), this.cacheNodeIds(), this.checkpointInterval(), this.featureSubsetStrategy(), this.validationIndicatorCol(), this.validationTol(), this.thresholds()}));
            instr.logNumClasses((long)numClasses);
            Map categoricalFeatures = MetadataUtils$.MODULE$.getCategoricalFeatures(dataset.schema().apply((String)this.$(this.featuresCol())));
            BoostingStrategy boostingStrategy = this.super$getOldBoostingStrategy(categoricalFeatures, Algo$.MODULE$.Classification());
            Tuple2 var16 = withValidation ? GradientBoostedTrees$.MODULE$.runWithValidation(trainDatasetx, validationDataset, boostingStrategy, BoxesRunTime.unboxToLong(this.$(this.seed())), (String)this.$(this.featureSubsetStrategy()), new Some(instr)) : GradientBoostedTrees$.MODULE$.run(trainDatasetx, boostingStrategy, BoxesRunTime.unboxToLong(this.$(this.seed())), (String)this.$(this.featureSubsetStrategy()), new Some(instr));
            if (var16 != null) {
               DecisionTreeRegressionModel[] baseLearners = (DecisionTreeRegressionModel[])var16._1();
               double[] learnerWeights = (double[])var16._2();
               Tuple2 var15 = new Tuple2(baseLearners, learnerWeights);
               DecisionTreeRegressionModel[] baseLearnersx = (DecisionTreeRegressionModel[])var15._1();
               double[] learnerWeightsx = (double[])var15._2();
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(baseLearnersx), (x$3) -> (DecisionTreeRegressionModel)this.copyValues(x$3, this.copyValues$default$2()));
               int numFeatures = ((DecisionTreeRegressionModel)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(baseLearnersx))).numFeatures();
               instr.logNumFeatures((long)numFeatures);
               return new GBTClassificationModel(this.uid(), baseLearnersx, learnerWeightsx, numFeatures);
            } else {
               throw new MatchError(var16);
            }
         } else {
            throw new MatchError(var7);
         }
      });
   }

   public GBTClassifier copy(final ParamMap extra) {
      return (GBTClassifier)this.defaultCopy(extra);
   }

   public GBTClassifier(final String uid) {
      this.uid = uid;
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      TreeEnsembleParams.$init$(this);
      HasMaxIter.$init$(this);
      HasStepSize.$init$(this);
      HasValidationIndicatorCol.$init$(this);
      GBTParams.$init$(this);
      TreeEnsembleClassifierParams.$init$(this);
      HasVarianceImpurity.$init$(this);
      GBTClassifierParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public GBTClassifier() {
      this(Identifiable$.MODULE$.randomUID("gbtc"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
