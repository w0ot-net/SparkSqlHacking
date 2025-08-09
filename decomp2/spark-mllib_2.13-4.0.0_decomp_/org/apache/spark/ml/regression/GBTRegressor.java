package org.apache.spark.ml.regression;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.PredictorParams;
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
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.GBTParams;
import org.apache.spark.ml.tree.GBTRegressorParams;
import org.apache.spark.ml.tree.HasVarianceImpurity;
import org.apache.spark.ml.tree.TreeEnsembleParams;
import org.apache.spark.ml.tree.TreeEnsembleRegressorParams;
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
import org.apache.spark.mllib.tree.loss.Loss;
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
   bytes = "\u0006\u0005\t\rd\u0001\u0002\u0011\"\u00011B\u0001\"\u0014\u0001\u0003\u0006\u0004%\tE\u0014\u0005\tK\u0002\u0011\t\u0011)A\u0005\u001f\")q\r\u0001C\u0001Q\")q\r\u0001C\u0001Y\")a\u000e\u0001C\u0001_\")\u0001\u0010\u0001C\u0001s\")A\u0010\u0001C\u0001{\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!!\r\u0001\t\u0003\t\u0019\u0004C\u0004\u0002:\u0001!\t!a\u000f\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002D!9\u0011\u0011\n\u0001\u0005\u0002\u0005-\u0003bBA,\u0001\u0011\u0005\u0011\u0011\f\u0005\b\u0003?\u0002A\u0011AA1\u0011\u001d\t9\u0007\u0001C\u0001\u0003SBq!a\u001c\u0001\t\u0003\t\t\bC\u0004\u0002|\u0001!\t!! \t\u000f\u0005\u001d\u0005\u0001\"\u0001\u0002\n\"9\u0011q\u0012\u0001\u0005R\u0005E\u0005bBA^\u0001\u0011\u0005\u0013Q\u0018\u0005\u000f\u0003#\u0004\u0001\u0013aA\u0001\u0002\u0013%\u00111\u001bB\u0002\u000f\u001d\u0011i!\tE\u0001\u0005\u001f1a\u0001I\u0011\t\u0002\tE\u0001BB4\u001b\t\u0003\u0011y\u0003C\u0005\u00032i\u0011\r\u0011\"\u0002\u00034!A!Q\b\u000e!\u0002\u001b\u0011)\u0004C\u0004\u0003Bi!\tEa\u0011\t\u0013\t=#$!A\u0005\n\tE#\u0001D$C)J+wM]3tg>\u0014(B\u0001\u0012$\u0003)\u0011Xm\u001a:fgNLwN\u001c\u0006\u0003I\u0015\n!!\u001c7\u000b\u0005\u0019:\u0013!B:qCJ\\'B\u0001\u0015*\u0003\u0019\t\u0007/Y2iK*\t!&A\u0002pe\u001e\u001c\u0001aE\u0003\u0001[m\nu\tE\u0003/_E:\u0004(D\u0001\"\u0013\t\u0001\u0014EA\u0005SK\u001e\u0014Xm]:peB\u0011!'N\u0007\u0002g)\u0011AgI\u0001\u0007Y&t\u0017\r\\4\n\u0005Y\u001a$A\u0002,fGR|'\u000f\u0005\u0002/\u0001A\u0011a&O\u0005\u0003u\u0005\u0012!c\u0012\"U%\u0016<'/Z:tS>tWj\u001c3fYB\u0011AhP\u0007\u0002{)\u0011ahI\u0001\u0005iJ,W-\u0003\u0002A{\t\u0011rI\u0011+SK\u001e\u0014Xm]:peB\u000b'/Y7t!\t\u0011U)D\u0001D\u0015\t!5%\u0001\u0003vi&d\u0017B\u0001$D\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\u0004\"\u0001S&\u000e\u0003%S!AS\u0013\u0002\u0011%tG/\u001a:oC2L!\u0001T%\u0003\u000f1{wmZ5oO\u0006\u0019Q/\u001b3\u0016\u0003=\u0003\"\u0001U-\u000f\u0005E;\u0006C\u0001*V\u001b\u0005\u0019&B\u0001+,\u0003\u0019a$o\\8u})\ta+A\u0003tG\u0006d\u0017-\u0003\u0002Y+\u00061\u0001K]3eK\u001aL!AW.\u0003\rM#(/\u001b8h\u0015\tAV\u000bK\u0002\u0002;\u000e\u0004\"AX1\u000e\u0003}S!\u0001Y\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002c?\n)1+\u001b8dK\u0006\nA-A\u00032]Qr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002^G\u00061A(\u001b8jiz\"\"aN5\t\u000b5\u001b\u0001\u0019A()\u0007%l6\rK\u0002\u0004;\u000e$\u0012a\u000e\u0015\u0004\tu\u001b\u0017aC:fi6\u000b\u0007\u0010R3qi\"$\"\u0001]9\u000e\u0003\u0001AQA]\u0003A\u0002M\fQA^1mk\u0016\u0004\"\u0001^;\u000e\u0003UK!A^+\u0003\u0007%sG\u000fK\u0002\u0006;\u000e\f!b]3u\u001b\u0006D()\u001b8t)\t\u0001(\u0010C\u0003s\r\u0001\u00071\u000fK\u0002\u0007;\u000e\fac]3u\u001b&t\u0017J\\:uC:\u001cWm\u001d)fe:{G-\u001a\u000b\u0003azDQA]\u0004A\u0002MD3aB/d\u0003m\u0019X\r^'j]^+\u0017n\u001a5u\rJ\f7\r^5p]B+'OT8eKR\u0019\u0001/!\u0002\t\rID\u0001\u0019AA\u0004!\r!\u0018\u0011B\u0005\u0004\u0003\u0017)&A\u0002#pk\ndW\r\u000b\u0003\t;\u0006=\u0011EAA\t\u0003\u0015\u0019d\u0006\r\u00181\u00039\u0019X\r^'j]&sgm\\$bS:$2\u0001]A\f\u0011\u0019\u0011\u0018\u00021\u0001\u0002\b!\u001a\u0011\"X2\u0002!M,G/T1y\u001b\u0016lwN]=J]6\u0013Ec\u00019\u0002 !)!O\u0003a\u0001g\"\u001a!\"X2\u0002\u001fM,GoQ1dQ\u0016tu\u000eZ3JIN$2\u0001]A\u0014\u0011\u0019\u00118\u00021\u0001\u0002*A\u0019A/a\u000b\n\u0007\u00055RKA\u0004C_>dW-\u00198)\u0007-i6-A\u000btKR\u001c\u0005.Z2la>Lg\u000e^%oi\u0016\u0014h/\u00197\u0015\u0007A\f)\u0004C\u0003s\u0019\u0001\u00071\u000fK\u0002\r;\u000e\f1b]3u\u00136\u0004XO]5usR\u0019\u0001/!\u0010\t\u000bIl\u0001\u0019A()\u00075i6-\u0001\ntKR\u001cVOY:b[Bd\u0017N\\4SCR,Gc\u00019\u0002F!1!O\u0004a\u0001\u0003\u000fA3AD/d\u0003\u001d\u0019X\r^*fK\u0012$2\u0001]A'\u0011\u0019\u0011x\u00021\u0001\u0002PA\u0019A/!\u0015\n\u0007\u0005MSK\u0001\u0003M_:<\u0007fA\b^G\u0006Q1/\u001a;NCbLE/\u001a:\u0015\u0007A\fY\u0006C\u0003s!\u0001\u00071\u000fK\u0002\u0011;\u000e\f1b]3u'R,\u0007oU5{KR\u0019\u0001/a\u0019\t\rI\f\u0002\u0019AA\u0004Q\r\tRlY\u0001\fg\u0016$Hj\\:t)f\u0004X\rF\u0002q\u0003WBQA\u001d\nA\u0002=C3AE/d\u0003a\u0019X\r\u001e$fCR,(/Z*vEN,Go\u0015;sCR,w-\u001f\u000b\u0004a\u0006M\u0004\"\u0002:\u0014\u0001\u0004y\u0005\u0006B\n^\u0003o\n#!!\u001f\u0002\u000bIr3G\f\u0019\u00023M,GOV1mS\u0012\fG/[8o\u0013:$\u0017nY1u_J\u001cu\u000e\u001c\u000b\u0004a\u0006}\u0004\"\u0002:\u0015\u0001\u0004y\u0005\u0006\u0002\u000b^\u0003\u0007\u000b#!!\"\u0002\u000bIrCG\f\u0019\u0002\u0019M,GoV3jO\"$8i\u001c7\u0015\u0007A\fY\tC\u0003s+\u0001\u0007q\n\u000b\u0003\u0016;\u0006=\u0011!\u0002;sC&tGc\u0001\u001d\u0002\u0014\"9\u0011Q\u0013\fA\u0002\u0005]\u0015a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u00033\u000bI\u000b\u0005\u0004\u0002\u001c\u0006\u0005\u0016QU\u0007\u0003\u0003;S1!a(&\u0003\r\u0019\u0018\u000f\\\u0005\u0005\u0003G\u000biJA\u0004ECR\f7/\u001a;\u0011\t\u0005\u001d\u0016\u0011\u0016\u0007\u0001\t1\tY+a%\u0002\u0002\u0003\u0005)\u0011AAW\u0005\ryF%M\t\u0005\u0003_\u000b)\fE\u0002u\u0003cK1!a-V\u0005\u001dqu\u000e\u001e5j]\u001e\u00042\u0001^A\\\u0013\r\tI,\u0016\u0002\u0004\u0003:L\u0018\u0001B2paf$2aNA`\u0011\u001d\t\tm\u0006a\u0001\u0003\u0007\fQ!\u001a=ue\u0006\u0004B!!2\u0002L6\u0011\u0011q\u0019\u0006\u0004\u0003\u0013\u001c\u0013!\u00029be\u0006l\u0017\u0002BAg\u0003\u000f\u0014\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0004/u\u001b\u0017\u0001H:va\u0016\u0014HeZ3u\u001f2$'i\\8ti&twm\u0015;sCR,w-\u001f\u000b\u0007\u0003+\f9/!=\u0011\t\u0005]\u00171]\u0007\u0003\u00033TA!a7\u0002^\u0006i1m\u001c8gS\u001e,(/\u0019;j_:T1APAp\u0015\r\t\t/J\u0001\u0006[2d\u0017NY\u0005\u0005\u0003K\fIN\u0001\tC_>\u001cH/\u001b8h'R\u0014\u0018\r^3hs\"9\u0011\u0011\u001e\rA\u0002\u0005-\u0018aE2bi\u0016<wN]5dC24U-\u0019;ve\u0016\u001c\b#\u0002)\u0002nN\u001c\u0018bAAx7\n\u0019Q*\u00199\t\u000f\u0005M\b\u00041\u0001\u0002v\u00069q\u000e\u001c3BY\u001e|\u0007\u0003BA|\u0003{tA!a6\u0002z&!\u00111`Am\u0003\u0011\tEnZ8\n\t\u0005}(\u0011\u0001\u0002\u0005\u00032<wN\u0003\u0003\u0002|\u0006e\u0017\u0002\u0002B\u0003\u0005\u000f\tacZ3u\u001f2$'i\\8ti&twm\u0015;sCR,w-_\u0005\u0004\u0005\u0013i$!C$C)B\u000b'/Y7tQ\r\u0001QlY\u0001\r\u000f\n#&+Z4sKN\u001cxN\u001d\t\u0003]i\u0019rA\u0007B\n\u00053\u0011y\u0002E\u0002u\u0005+I1Aa\u0006V\u0005\u0019\te.\u001f*fMB!!Ia\u00078\u0013\r\u0011ib\u0011\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\u0011\tCa\u000b\u000e\u0005\t\r\"\u0002\u0002B\u0013\u0005O\t!![8\u000b\u0005\t%\u0012\u0001\u00026bm\u0006LAA!\f\u0003$\ta1+\u001a:jC2L'0\u00192mKR\u0011!qB\u0001\u0013gV\u0004\bo\u001c:uK\u0012dun]:UsB,7/\u0006\u0002\u00036A!AOa\u000eP\u0013\r\u0011I$\u0016\u0002\u0006\u0003J\u0014\u0018-\u001f\u0015\u00049u\u001b\u0017aE:vaB|'\u000f^3e\u0019>\u001c8\u000fV=qKN\u0004\u0003fA\u000f^G\u0006!An\\1e)\r9$Q\t\u0005\u0007\u0005\u000fr\u0002\u0019A(\u0002\tA\fG\u000f\u001b\u0015\u0005=u\u0013Y%\t\u0002\u0003N\u0005)!G\f\u0019/a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u000b\t\u0005\u0005+\u0012Y&\u0004\u0002\u0003X)!!\u0011\fB\u0014\u0003\u0011a\u0017M\\4\n\t\tu#q\u000b\u0002\u0007\u001f\nTWm\u0019;)\u0007ii6\rK\u0002\u001a;\u000e\u0004"
)
public class GBTRegressor extends Regressor implements GBTRegressorParams, DefaultParamsWritable {
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

   public static GBTRegressor load(final String path) {
      return GBTRegressor$.MODULE$.load(path);
   }

   public static String[] supportedLossTypes() {
      return GBTRegressor$.MODULE$.supportedLossTypes();
   }

   public static MLReader read() {
      return GBTRegressor$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getLossType() {
      return GBTRegressorParams.getLossType$(this);
   }

   public Loss getOldLossType() {
      return GBTRegressorParams.getOldLossType$(this);
   }

   public Loss convertToOldLossType(final String loss) {
      return GBTRegressorParams.convertToOldLossType$(this, loss);
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

   public void org$apache$spark$ml$tree$GBTRegressorParams$_setter_$lossType_$eq(final Param x$1) {
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

   public GBTRegressor setMaxDepth(final int value) {
      return (GBTRegressor)this.set(this.maxDepth(), BoxesRunTime.boxToInteger(value));
   }

   public GBTRegressor setMaxBins(final int value) {
      return (GBTRegressor)this.set(this.maxBins(), BoxesRunTime.boxToInteger(value));
   }

   public GBTRegressor setMinInstancesPerNode(final int value) {
      return (GBTRegressor)this.set(this.minInstancesPerNode(), BoxesRunTime.boxToInteger(value));
   }

   public GBTRegressor setMinWeightFractionPerNode(final double value) {
      return (GBTRegressor)this.set(this.minWeightFractionPerNode(), BoxesRunTime.boxToDouble(value));
   }

   public GBTRegressor setMinInfoGain(final double value) {
      return (GBTRegressor)this.set(this.minInfoGain(), BoxesRunTime.boxToDouble(value));
   }

   public GBTRegressor setMaxMemoryInMB(final int value) {
      return (GBTRegressor)this.set(this.maxMemoryInMB(), BoxesRunTime.boxToInteger(value));
   }

   public GBTRegressor setCacheNodeIds(final boolean value) {
      return (GBTRegressor)this.set(this.cacheNodeIds(), BoxesRunTime.boxToBoolean(value));
   }

   public GBTRegressor setCheckpointInterval(final int value) {
      return (GBTRegressor)this.set(this.checkpointInterval(), BoxesRunTime.boxToInteger(value));
   }

   public GBTRegressor setImpurity(final String value) {
      this.logWarning(() -> "GBTRegressor.setImpurity should NOT be used");
      return this;
   }

   public GBTRegressor setSubsamplingRate(final double value) {
      return (GBTRegressor)this.set(this.subsamplingRate(), BoxesRunTime.boxToDouble(value));
   }

   public GBTRegressor setSeed(final long value) {
      return (GBTRegressor)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public GBTRegressor setMaxIter(final int value) {
      return (GBTRegressor)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public GBTRegressor setStepSize(final double value) {
      return (GBTRegressor)this.set(this.stepSize(), BoxesRunTime.boxToDouble(value));
   }

   public GBTRegressor setLossType(final String value) {
      return (GBTRegressor)this.set(this.lossType(), value);
   }

   public GBTRegressor setFeatureSubsetStrategy(final String value) {
      return (GBTRegressor)this.set(this.featureSubsetStrategy(), value);
   }

   public GBTRegressor setValidationIndicatorCol(final String value) {
      return (GBTRegressor)this.set(this.validationIndicatorCol(), value);
   }

   public GBTRegressor setWeightCol(final String value) {
      return (GBTRegressor)this.set(this.weightCol(), value);
   }

   public GBTRegressionModel train(final Dataset dataset) {
      return (GBTRegressionModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         boolean withValidation = this.isDefined(this.validationIndicatorCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.validationIndicatorCol())));
         Tuple2 var7 = withValidation ? new Tuple2(DatasetUtils$.MODULE$.extractInstances(this, dataset.filter(org.apache.spark.sql.functions..MODULE$.not(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.validationIndicatorCol())))), DatasetUtils$.MODULE$.extractInstances$default$3()), DatasetUtils$.MODULE$.extractInstances(this, dataset.filter(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.validationIndicatorCol()))), DatasetUtils$.MODULE$.extractInstances$default$3())) : new Tuple2(DatasetUtils$.MODULE$.extractInstances(this, dataset, DatasetUtils$.MODULE$.extractInstances$default$3()), (Object)null);
         if (var7 != null) {
            RDD trainDataset = (RDD)var7._1();
            RDD validationDataset = (RDD)var7._2();
            Tuple2 var6 = new Tuple2(trainDataset, validationDataset);
            RDD trainDatasetx = (RDD)var6._1();
            RDD validationDataset = (RDD)var6._2();
            instr.logPipelineStage(this);
            instr.logDataset(dataset);
            instr.logParams(this, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.predictionCol(), this.leafCol(), this.weightCol(), this.impurity(), this.lossType(), this.maxDepth(), this.maxBins(), this.maxIter(), this.maxMemoryInMB(), this.minInfoGain(), this.minInstancesPerNode(), this.minWeightFractionPerNode(), this.seed(), this.stepSize(), this.subsamplingRate(), this.cacheNodeIds(), this.checkpointInterval(), this.featureSubsetStrategy(), this.validationIndicatorCol(), this.validationTol()}));
            Map categoricalFeatures = MetadataUtils$.MODULE$.getCategoricalFeatures(dataset.schema().apply((String)this.$(this.featuresCol())));
            BoostingStrategy boostingStrategy = this.super$getOldBoostingStrategy(categoricalFeatures, Algo$.MODULE$.Regression());
            Tuple2 var15 = withValidation ? GradientBoostedTrees$.MODULE$.runWithValidation(trainDatasetx, validationDataset, boostingStrategy, BoxesRunTime.unboxToLong(this.$(this.seed())), (String)this.$(this.featureSubsetStrategy()), new Some(instr)) : GradientBoostedTrees$.MODULE$.run(trainDatasetx, boostingStrategy, BoxesRunTime.unboxToLong(this.$(this.seed())), (String)this.$(this.featureSubsetStrategy()), new Some(instr));
            if (var15 != null) {
               DecisionTreeRegressionModel[] baseLearners = (DecisionTreeRegressionModel[])var15._1();
               double[] learnerWeights = (double[])var15._2();
               Tuple2 var14 = new Tuple2(baseLearners, learnerWeights);
               DecisionTreeRegressionModel[] baseLearnersx = (DecisionTreeRegressionModel[])var14._1();
               double[] learnerWeightsx = (double[])var14._2();
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(baseLearnersx), (x$3) -> (DecisionTreeRegressionModel)this.copyValues(x$3, this.copyValues$default$2()));
               int numFeatures = ((DecisionTreeRegressionModel)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(baseLearnersx))).numFeatures();
               instr.logNumFeatures((long)numFeatures);
               return new GBTRegressionModel(this.uid(), baseLearnersx, learnerWeightsx, numFeatures);
            } else {
               throw new MatchError(var15);
            }
         } else {
            throw new MatchError(var7);
         }
      });
   }

   public GBTRegressor copy(final ParamMap extra) {
      return (GBTRegressor)this.defaultCopy(extra);
   }

   public GBTRegressor(final String uid) {
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
      TreeEnsembleRegressorParams.$init$(this);
      HasVarianceImpurity.$init$(this);
      GBTRegressorParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public GBTRegressor() {
      this(Identifiable$.MODULE$.randomUID("gbtr"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
