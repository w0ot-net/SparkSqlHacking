package org.apache.spark.ml.regression;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.HasVarianceImpurity;
import org.apache.spark.ml.tree.RandomForestParams;
import org.apache.spark.ml.tree.RandomForestRegressorParams;
import org.apache.spark.ml.tree.TreeEnsembleParams;
import org.apache.spark.ml.tree.TreeEnsembleRegressorParams;
import org.apache.spark.ml.tree.impl.RandomForest$;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.MetadataUtils$;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Enumeration;
import scala.MatchError;
import scala.Some;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tmc\u0001\u0002\u0011\"\u00011B\u0001b\u0012\u0001\u0003\u0006\u0004%\t\u0005\u0013\u0005\t?\u0002\u0011\t\u0011)A\u0005\u0013\")\u0011\r\u0001C\u0001E\")\u0011\r\u0001C\u0001M\")\u0001\u000e\u0001C\u0001S\")!\u000f\u0001C\u0001g\")a\u000f\u0001C\u0001o\")!\u0010\u0001C\u0001w\"9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\b\u0001\u0011\u0005\u0011\u0011\u0003\u0005\b\u0003/\u0001A\u0011AA\r\u0011\u001d\t)\u0003\u0001C\u0001\u0003OAq!!\f\u0001\t\u0003\ty\u0003C\u0004\u00026\u0001!\t!a\u000e\t\u000f\u0005u\u0002\u0001\"\u0001\u0002@!9\u00111\n\u0001\u0005\u0002\u00055\u0003bBA*\u0001\u0011\u0005\u0011Q\u000b\u0005\b\u00037\u0002A\u0011AA/\u0011\u001d\t\u0019\u0007\u0001C\u0001\u0003KBq!a\u001b\u0001\t#\ni\u0007C\u0004\u0002\u0018\u0002!\t%!'\t\u001d\u00055\u0006\u0001%A\u0002\u0002\u0003%I!a,\u0002t\u001e9\u0011Q`\u0011\t\u0002\u0005}hA\u0002\u0011\"\u0011\u0003\u0011\t\u0001\u0003\u0004b1\u0011\u0005!q\u0004\u0005\n\u0005CA\"\u0019!C\u0003\u0005GA\u0001B!\f\u0019A\u00035!Q\u0005\u0005\n\u0005cA\"\u0019!C\u0003\u0005GA\u0001B!\u000e\u0019A\u00035!Q\u0005\u0005\b\u0005sAB\u0011\tB\u001e\u0011%\u00119\u0005GA\u0001\n\u0013\u0011IEA\u000bSC:$w.\u001c$pe\u0016\u001cHOU3he\u0016\u001c8o\u001c:\u000b\u0005\t\u001a\u0013A\u0003:fOJ,7o]5p]*\u0011A%J\u0001\u0003[2T!AJ\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005!J\u0013AB1qC\u000eDWMC\u0001+\u0003\ry'oZ\u0002\u0001'\u0011\u0001QfO!\u0011\u000b9z\u0013g\u000e\u001d\u000e\u0003\u0005J!\u0001M\u0011\u0003\u0013I+wM]3tg>\u0014\bC\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b$\u0003\u0019a\u0017N\\1mO&\u0011ag\r\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u00059\u0002\u0001C\u0001\u0018:\u0013\tQ\u0014EA\u000eSC:$w.\u001c$pe\u0016\u001cHOU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003y}j\u0011!\u0010\u0006\u0003}\r\nA\u0001\u001e:fK&\u0011\u0001)\u0010\u0002\u001c%\u0006tGm\\7G_J,7\u000f\u001e*fOJ,7o]8s!\u0006\u0014\u0018-\\:\u0011\u0005\t+U\"A\"\u000b\u0005\u0011\u001b\u0013\u0001B;uS2L!AR\"\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003%\u0003\"AS*\u000f\u0005-\u000b\u0006C\u0001'P\u001b\u0005i%B\u0001(,\u0003\u0019a$o\\8u})\t\u0001+A\u0003tG\u0006d\u0017-\u0003\u0002S\u001f\u00061\u0001K]3eK\u001aL!\u0001V+\u0003\rM#(/\u001b8h\u0015\t\u0011v\nK\u0002\u0002/v\u0003\"\u0001W.\u000e\u0003eS!AW\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002]3\n)1+\u001b8dK\u0006\na,A\u00032]Qr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002X;\u00061A(\u001b8jiz\"\"aN2\t\u000b\u001d\u001b\u0001\u0019A%)\u0007\r<V\fK\u0002\u0004/v#\u0012a\u000e\u0015\u0004\t]k\u0016aC:fi6\u000b\u0007\u0010R3qi\"$\"A[6\u000e\u0003\u0001AQ\u0001\\\u0003A\u00025\fQA^1mk\u0016\u0004\"A\\8\u000e\u0003=K!\u0001](\u0003\u0007%sG\u000fK\u0002\u0006/v\u000b!b]3u\u001b\u0006D()\u001b8t)\tQG\u000fC\u0003m\r\u0001\u0007Q\u000eK\u0002\u0007/v\u000bac]3u\u001b&t\u0017J\\:uC:\u001cWm\u001d)fe:{G-\u001a\u000b\u0003UbDQ\u0001\\\u0004A\u00025D3aB,^\u0003m\u0019X\r^'j]^+\u0017n\u001a5u\rJ\f7\r^5p]B+'OT8eKR\u0011!\u000e \u0005\u0006Y\"\u0001\r! \t\u0003]zL!a`(\u0003\r\u0011{WO\u00197fQ\u0011Aq+a\u0001\"\u0005\u0005\u0015\u0011!B\u001a/a9\u0002\u0014AD:fi6Kg.\u00138g_\u001e\u000b\u0017N\u001c\u000b\u0004U\u0006-\u0001\"\u00027\n\u0001\u0004i\bfA\u0005X;\u0006\u00012/\u001a;NCblU-\\8ss&sWJ\u0011\u000b\u0004U\u0006M\u0001\"\u00027\u000b\u0001\u0004i\u0007f\u0001\u0006X;\u0006y1/\u001a;DC\u000eDWMT8eK&#7\u000fF\u0002k\u00037Aa\u0001\\\u0006A\u0002\u0005u\u0001c\u00018\u0002 %\u0019\u0011\u0011E(\u0003\u000f\t{w\u000e\\3b]\"\u001a1bV/\u0002+M,Go\u00115fG.\u0004x.\u001b8u\u0013:$XM\u001d<bYR\u0019!.!\u000b\t\u000b1d\u0001\u0019A7)\u000719V,A\u0006tKRLU\u000e];sSRLHc\u00016\u00022!)A.\u0004a\u0001\u0013\"\u001aQbV/\u0002%M,GoU;cg\u0006l\u0007\u000f\\5oOJ\u000bG/\u001a\u000b\u0004U\u0006e\u0002\"\u00027\u000f\u0001\u0004i\bf\u0001\bX;\u000691/\u001a;TK\u0016$Gc\u00016\u0002B!1An\u0004a\u0001\u0003\u0007\u00022A\\A#\u0013\r\t9e\u0014\u0002\u0005\u0019>tw\rK\u0002\u0010/v\u000b1b]3u\u001dVlGK]3fgR\u0019!.a\u0014\t\u000b1\u0004\u0002\u0019A7)\u0007A9V,\u0001\u0007tKR\u0014un\u001c;tiJ\f\u0007\u000fF\u0002k\u0003/Ba\u0001\\\tA\u0002\u0005u\u0001\u0006B\tX\u0003\u0007\t\u0001d]3u\r\u0016\fG/\u001e:f'V\u00147/\u001a;TiJ\fG/Z4z)\rQ\u0017q\f\u0005\u0006YJ\u0001\r!\u0013\u0015\u0004%]k\u0016\u0001D:fi^+\u0017n\u001a5u\u0007>dGc\u00016\u0002h!)An\u0005a\u0001\u0013\"\"1cVA\u0002\u0003\u0015!(/Y5o)\rA\u0014q\u000e\u0005\b\u0003c\"\u0002\u0019AA:\u0003\u001d!\u0017\r^1tKR\u0004D!!\u001e\u0002\u0006B1\u0011qOA?\u0003\u0003k!!!\u001f\u000b\u0007\u0005mT%A\u0002tc2LA!a \u0002z\t9A)\u0019;bg\u0016$\b\u0003BAB\u0003\u000bc\u0001\u0001\u0002\u0007\u0002\b\u0006=\u0014\u0011!A\u0001\u0006\u0003\tIIA\u0002`IE\nB!a#\u0002\u0012B\u0019a.!$\n\u0007\u0005=uJA\u0004O_RD\u0017N\\4\u0011\u00079\f\u0019*C\u0002\u0002\u0016>\u00131!\u00118z\u0003\u0011\u0019w\u000e]=\u0015\u0007]\nY\nC\u0004\u0002\u001eV\u0001\r!a(\u0002\u000b\u0015DHO]1\u0011\t\u0005\u0005\u0016qU\u0007\u0003\u0003GS1!!*$\u0003\u0015\u0001\u0018M]1n\u0013\u0011\tI+a)\u0003\u0011A\u000b'/Y7NCBD3!F,^\u0003Q\u0019X\u000f]3sI\u001d,Go\u00147e'R\u0014\u0018\r^3hsRQ\u0011\u0011WAb\u0003\u001b\f\t.a9\u0011\t\u0005M\u0016qX\u0007\u0003\u0003kSA!a.\u0002:\u0006i1m\u001c8gS\u001e,(/\u0019;j_:T1APA^\u0015\r\ti,J\u0001\u0006[2d\u0017NY\u0005\u0005\u0003\u0003\f)L\u0001\u0005TiJ\fG/Z4z\u0011\u001d\t)M\u0006a\u0001\u0003\u000f\f1cY1uK\u001e|'/[2bY\u001a+\u0017\r^;sKN\u0004RASAe[6L1!a3V\u0005\ri\u0015\r\u001d\u0005\u0007\u0003\u001f4\u0002\u0019A7\u0002\u00159,Xn\u00117bgN,7\u000fC\u0004\u0002TZ\u0001\r!!6\u0002\u000f=dG-\u00117h_B!\u0011q[Ao\u001d\u0011\t\u0019,!7\n\t\u0005m\u0017QW\u0001\u0005\u00032<w.\u0003\u0003\u0002`\u0006\u0005(\u0001B!mO>TA!a7\u00026\"9\u0011Q\u001d\fA\u0002\u0005\u001d\u0018aC8mI&k\u0007/\u001e:jif\u0004B!!;\u0002p6\u0011\u00111\u001e\u0006\u0005\u0003[\fI,\u0001\u0005j[B,(/\u001b;z\u0013\u0011\t\t0a;\u0003\u0011%k\u0007/\u001e:jifLA!!>\u0002x\u0006qq-\u001a;PY\u0012\u001cFO]1uK\u001eL\u0018bAA}{\t\u0011BK]3f\u000b:\u001cX-\u001c2mKB\u000b'/Y7tQ\r\u0001q+X\u0001\u0016%\u0006tGm\\7G_J,7\u000f\u001e*fOJ,7o]8s!\tq\u0003dE\u0004\u0019\u0005\u0007\u0011IAa\u0004\u0011\u00079\u0014)!C\u0002\u0003\b=\u0013a!\u00118z%\u00164\u0007\u0003\u0002\"\u0003\f]J1A!\u0004D\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004BA!\u0005\u0003\u001c5\u0011!1\u0003\u0006\u0005\u0005+\u00119\"\u0001\u0002j_*\u0011!\u0011D\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003\u001e\tM!\u0001D*fe&\fG.\u001b>bE2,GCAA\u0000\u0003M\u0019X\u000f\u001d9peR,G-S7qkJLG/[3t+\t\u0011)\u0003\u0005\u0003o\u0005OI\u0015b\u0001B\u0015\u001f\n)\u0011I\u001d:bs\"\u001a!dV/\u0002)M,\b\u000f]8si\u0016$\u0017*\u001c9ve&$\u0018.Z:!Q\rYr+X\u0001!gV\u0004\bo\u001c:uK\u00124U-\u0019;ve\u0016\u001cVOY:fiN#(/\u0019;fO&,7\u000fK\u0002\u001d/v\u000b\u0011e];qa>\u0014H/\u001a3GK\u0006$XO]3Tk\n\u001cX\r^*ue\u0006$XmZ5fg\u0002B3!H,^\u0003\u0011aw.\u00193\u0015\u0007]\u0012i\u0004\u0003\u0004\u0003@y\u0001\r!S\u0001\u0005a\u0006$\b\u000e\u000b\u0003\u001f/\n\r\u0013E\u0001B#\u0003\u0015\u0011d\u0006\r\u00181\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011Y\u0005\u0005\u0003\u0003N\tMSB\u0001B(\u0015\u0011\u0011\tFa\u0006\u0002\t1\fgnZ\u0005\u0005\u0005+\u0012yE\u0001\u0004PE*,7\r\u001e\u0015\u00041]k\u0006fA\fX;\u0002"
)
public class RandomForestRegressor extends Regressor implements RandomForestRegressorParams, DefaultParamsWritable {
   private final String uid;
   private Param impurity;
   private IntParam numTrees;
   private BooleanParam bootstrap;
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

   public static RandomForestRegressor load(final String path) {
      return RandomForestRegressor$.MODULE$.load(path);
   }

   public static String[] supportedFeatureSubsetStrategies() {
      return RandomForestRegressor$.MODULE$.supportedFeatureSubsetStrategies();
   }

   public static String[] supportedImpurities() {
      return RandomForestRegressor$.MODULE$.supportedImpurities();
   }

   public static MLReader read() {
      return RandomForestRegressor$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getImpurity() {
      return HasVarianceImpurity.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return HasVarianceImpurity.getOldImpurity$(this);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$TreeEnsembleRegressorParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return TreeEnsembleRegressorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final int getNumTrees() {
      return RandomForestParams.getNumTrees$(this);
   }

   public final boolean getBootstrap() {
      return RandomForestParams.getBootstrap$(this);
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

   public final Param impurity() {
      return this.impurity;
   }

   public final void org$apache$spark$ml$tree$HasVarianceImpurity$_setter_$impurity_$eq(final Param x$1) {
      this.impurity = x$1;
   }

   public final IntParam numTrees() {
      return this.numTrees;
   }

   public final BooleanParam bootstrap() {
      return this.bootstrap;
   }

   public final void org$apache$spark$ml$tree$RandomForestParams$_setter_$numTrees_$eq(final IntParam x$1) {
      this.numTrees = x$1;
   }

   public final void org$apache$spark$ml$tree$RandomForestParams$_setter_$bootstrap_$eq(final BooleanParam x$1) {
      this.bootstrap = x$1;
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
   private Strategy super$getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity) {
      return TreeEnsembleParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity);
   }

   public String uid() {
      return this.uid;
   }

   public RandomForestRegressor setMaxDepth(final int value) {
      return (RandomForestRegressor)this.set(this.maxDepth(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestRegressor setMaxBins(final int value) {
      return (RandomForestRegressor)this.set(this.maxBins(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestRegressor setMinInstancesPerNode(final int value) {
      return (RandomForestRegressor)this.set(this.minInstancesPerNode(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestRegressor setMinWeightFractionPerNode(final double value) {
      return (RandomForestRegressor)this.set(this.minWeightFractionPerNode(), BoxesRunTime.boxToDouble(value));
   }

   public RandomForestRegressor setMinInfoGain(final double value) {
      return (RandomForestRegressor)this.set(this.minInfoGain(), BoxesRunTime.boxToDouble(value));
   }

   public RandomForestRegressor setMaxMemoryInMB(final int value) {
      return (RandomForestRegressor)this.set(this.maxMemoryInMB(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestRegressor setCacheNodeIds(final boolean value) {
      return (RandomForestRegressor)this.set(this.cacheNodeIds(), BoxesRunTime.boxToBoolean(value));
   }

   public RandomForestRegressor setCheckpointInterval(final int value) {
      return (RandomForestRegressor)this.set(this.checkpointInterval(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestRegressor setImpurity(final String value) {
      return (RandomForestRegressor)this.set(this.impurity(), value);
   }

   public RandomForestRegressor setSubsamplingRate(final double value) {
      return (RandomForestRegressor)this.set(this.subsamplingRate(), BoxesRunTime.boxToDouble(value));
   }

   public RandomForestRegressor setSeed(final long value) {
      return (RandomForestRegressor)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public RandomForestRegressor setNumTrees(final int value) {
      return (RandomForestRegressor)this.set(this.numTrees(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestRegressor setBootstrap(final boolean value) {
      return (RandomForestRegressor)this.set(this.bootstrap(), BoxesRunTime.boxToBoolean(value));
   }

   public RandomForestRegressor setFeatureSubsetStrategy(final String value) {
      return (RandomForestRegressor)this.set(this.featureSubsetStrategy(), value);
   }

   public RandomForestRegressor setWeightCol(final String value) {
      return (RandomForestRegressor)this.set(this.weightCol(), value);
   }

   public RandomForestRegressionModel train(final Dataset dataset) {
      return (RandomForestRegressionModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         Map categoricalFeatures = MetadataUtils$.MODULE$.getCategoricalFeatures(dataset.schema().apply((String)this.$(this.featuresCol())));
         RDD instances = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkRegressionLabels((String)this.$(this.labelCol())), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol())), DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))}))).rdd().map((x0$1) -> {
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
         }, scala.reflect.ClassTag..MODULE$.apply(Instance.class)).setName("training instances");
         Strategy strategy = this.super$getOldStrategy(categoricalFeatures, 0, Algo$.MODULE$.Regression(), this.getOldImpurity());
         strategy.bootstrap_$eq(BoxesRunTime.unboxToBoolean(this.$(this.bootstrap())));
         instr.logPipelineStage(this);
         instr.logDataset(instances);
         instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.weightCol(), this.predictionCol(), this.leafCol(), this.impurity(), this.numTrees(), this.featureSubsetStrategy(), this.maxDepth(), this.maxBins(), this.maxMemoryInMB(), this.minInfoGain(), this.minInstancesPerNode(), this.minWeightFractionPerNode(), this.seed(), this.subsamplingRate(), this.cacheNodeIds(), this.checkpointInterval(), this.bootstrap()}));
         DecisionTreeRegressionModel[] trees = (DecisionTreeRegressionModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(RandomForest$.MODULE$.run(instances, strategy, this.getNumTrees(), this.getFeatureSubsetStrategy(), this.getSeed(), new Some(instr), RandomForest$.MODULE$.run$default$7(), RandomForest$.MODULE$.run$default$8())), (x$1) -> (DecisionTreeRegressionModel)x$1, scala.reflect.ClassTag..MODULE$.apply(DecisionTreeRegressionModel.class));
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(trees), (x$2) -> (DecisionTreeRegressionModel)this.copyValues(x$2, this.copyValues$default$2()));
         int numFeatures = ((DecisionTreeRegressionModel)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(trees))).numFeatures();
         instr.logNumFeatures((long)numFeatures);
         return new RandomForestRegressionModel(this.uid(), trees, numFeatures);
      });
   }

   public RandomForestRegressor copy(final ParamMap extra) {
      return (RandomForestRegressor)this.defaultCopy(extra);
   }

   public RandomForestRegressor(final String uid) {
      this.uid = uid;
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      TreeEnsembleParams.$init$(this);
      RandomForestParams.$init$(this);
      TreeEnsembleRegressorParams.$init$(this);
      HasVarianceImpurity.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public RandomForestRegressor() {
      this(Identifiable$.MODULE$.randomUID("rfr"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
