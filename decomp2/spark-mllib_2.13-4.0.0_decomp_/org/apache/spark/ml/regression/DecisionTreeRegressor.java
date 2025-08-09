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
import org.apache.spark.ml.param.shared.HasVarianceCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.DecisionTreeRegressorParams;
import org.apache.spark.ml.tree.HasVarianceImpurity;
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
   bytes = "\u0006\u0005\t\ra\u0001B\u000e\u001d\u0001\u001dB\u0001B\u0011\u0001\u0003\u0006\u0004%\te\u0011\u0005\t5\u0002\u0011\t\u0011)A\u0005\t\")A\f\u0001C\u0001;\")A\f\u0001C\u0001C\")1\r\u0001C\u0001I\")Q\u000e\u0001C\u0001]\")\u0011\u000f\u0001C\u0001e\")Q\u000f\u0001C\u0001m\")a\u0010\u0001C\u0001\u007f\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!a\u000b\u0001\t\u0003\ti\u0003C\u0004\u0002>\u0001!\t!a\u0010\t\u000f\u0005%\u0003\u0001\"\u0001\u0002L!9\u0011\u0011\u000b\u0001\u0005R\u0005M\u0003\u0002CA?\u0001\u0011\u0005a$a \t\u000f\u0005u\u0005\u0001\"\u0011\u0002 \u001e9\u0011Q\u0017\u000f\t\u0002\u0005]fAB\u000e\u001d\u0011\u0003\tI\f\u0003\u0004]+\u0011\u0005\u0011q\u001b\u0005\n\u00033,\"\u0019!C\u0003\u00037D\u0001\"a9\u0016A\u00035\u0011Q\u001c\u0005\b\u0003K,B\u0011IAt\u0011%\ty/FA\u0001\n\u0013\t\tPA\u000bEK\u000eL7/[8o)J,WMU3he\u0016\u001c8o\u001c:\u000b\u0005uq\u0012A\u0003:fOJ,7o]5p]*\u0011q\u0004I\u0001\u0003[2T!!\t\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\r\"\u0013AB1qC\u000eDWMC\u0001&\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0001F\u000e\u001f\u0011\u000b%RCFM\u001a\u000e\u0003qI!a\u000b\u000f\u0003\u0013I+wM]3tg>\u0014\bCA\u00171\u001b\u0005q#BA\u0018\u001f\u0003\u0019a\u0017N\\1mO&\u0011\u0011G\f\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u0005%\u0002\u0001CA\u00155\u0013\t)DDA\u000eEK\u000eL7/[8o)J,WMU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003oij\u0011\u0001\u000f\u0006\u0003sy\tA\u0001\u001e:fK&\u00111\b\u000f\u0002\u001c\t\u0016\u001c\u0017n]5p]R\u0013X-\u001a*fOJ,7o]8s!\u0006\u0014\u0018-\\:\u0011\u0005u\u0002U\"\u0001 \u000b\u0005}r\u0012\u0001B;uS2L!!\u0011 \u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003\u0011\u0003\"!\u0012(\u000f\u0005\u0019c\u0005CA$K\u001b\u0005A%BA%'\u0003\u0019a$o\\8u})\t1*A\u0003tG\u0006d\u0017-\u0003\u0002N\u0015\u00061\u0001K]3eK\u001aL!a\u0014)\u0003\rM#(/\u001b8h\u0015\ti%\nK\u0002\u0002%b\u0003\"a\u0015,\u000e\u0003QS!!\u0016\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002X)\n)1+\u001b8dK\u0006\n\u0011,A\u00032]Qr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002S1\u00061A(\u001b8jiz\"\"A\r0\t\u000b\t\u001b\u0001\u0019\u0001#)\u0007y\u0013\u0006\fK\u0002\u0004%b#\u0012A\r\u0015\u0004\tIC\u0016aC:fi6\u000b\u0007\u0010R3qi\"$\"!\u001a4\u000e\u0003\u0001AQaZ\u0003A\u0002!\fQA^1mk\u0016\u0004\"!\u001b6\u000e\u0003)K!a\u001b&\u0003\u0007%sG\u000fK\u0002\u0006%b\u000b!b]3u\u001b\u0006D()\u001b8t)\t)w\u000eC\u0003h\r\u0001\u0007\u0001\u000eK\u0002\u0007%b\u000bac]3u\u001b&t\u0017J\\:uC:\u001cWm\u001d)fe:{G-\u001a\u000b\u0003KNDQaZ\u0004A\u0002!D3a\u0002*Y\u0003m\u0019X\r^'j]^+\u0017n\u001a5u\rJ\f7\r^5p]B+'OT8eKR\u0011Qm\u001e\u0005\u0006O\"\u0001\r\u0001\u001f\t\u0003SfL!A\u001f&\u0003\r\u0011{WO\u00197fQ\rA!\u000b`\u0011\u0002{\u0006)1G\f\u0019/a\u0005q1/\u001a;NS:LeNZ8HC&tGcA3\u0002\u0002!)q-\u0003a\u0001q\"\u001a\u0011B\u0015-\u0002!M,G/T1y\u001b\u0016lwN]=J]6\u0013EcA3\u0002\n!)qM\u0003a\u0001Q\"\u001a!B\u0015-\u0002\u001fM,GoQ1dQ\u0016tu\u000eZ3JIN$2!ZA\t\u0011\u001997\u00021\u0001\u0002\u0014A\u0019\u0011.!\u0006\n\u0007\u0005]!JA\u0004C_>dW-\u00198)\u0007-\u0011\u0006,A\u000btKR\u001c\u0005.Z2la>Lg\u000e^%oi\u0016\u0014h/\u00197\u0015\u0007\u0015\fy\u0002C\u0003h\u0019\u0001\u0007\u0001\u000eK\u0002\r%b\u000b1b]3u\u00136\u0004XO]5usR\u0019Q-a\n\t\u000b\u001dl\u0001\u0019\u0001#)\u00075\u0011\u0006,A\u0004tKR\u001cV-\u001a3\u0015\u0007\u0015\fy\u0003\u0003\u0004h\u001d\u0001\u0007\u0011\u0011\u0007\t\u0004S\u0006M\u0012bAA\u001b\u0015\n!Aj\u001c8hQ\u0011q!+!\u000f\"\u0005\u0005m\u0012!B\u0019/m9\u0002\u0014AD:fiZ\u000b'/[1oG\u0016\u001cu\u000e\u001c\u000b\u0004K\u0006\u0005\u0003\"B4\u0010\u0001\u0004!\u0005\u0006B\bS\u0003\u000b\n#!a\u0012\u0002\u000bIr\u0003G\f\u0019\u0002\u0019M,GoV3jO\"$8i\u001c7\u0015\u0007\u0015\fi\u0005C\u0003h!\u0001\u0007A\tK\u0002\u0011%r\fQ\u0001\u001e:bS:$2aMA+\u0011\u001d\t9&\u0005a\u0001\u00033\nq\u0001Z1uCN,G\u000f\r\u0003\u0002\\\u0005-\u0004CBA/\u0003G\n9'\u0004\u0002\u0002`)\u0019\u0011\u0011\r\u0011\u0002\u0007M\fH.\u0003\u0003\u0002f\u0005}#a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003S\nY\u0007\u0004\u0001\u0005\u0019\u00055\u0014QKA\u0001\u0002\u0003\u0015\t!a\u001c\u0003\u0007}#\u0013'\u0005\u0003\u0002r\u0005]\u0004cA5\u0002t%\u0019\u0011Q\u000f&\u0003\u000f9{G\u000f[5oOB\u0019\u0011.!\u001f\n\u0007\u0005m$JA\u0002B]f\fabZ3u\u001f2$7\u000b\u001e:bi\u0016<\u0017\u0010\u0006\u0003\u0002\u0002\u0006M\u0005\u0003BAB\u0003\u001fk!!!\"\u000b\t\u0005\u001d\u0015\u0011R\u0001\u000eG>tg-[4ve\u0006$\u0018n\u001c8\u000b\u0007e\nYIC\u0002\u0002\u000e\u0002\nQ!\u001c7mS\nLA!!%\u0002\u0006\nA1\u000b\u001e:bi\u0016<\u0017\u0010C\u0004\u0002\u0016J\u0001\r!a&\u0002'\r\fG/Z4pe&\u001c\u0017\r\u001c$fCR,(/Z:\u0011\u000b\u0015\u000bI\n\u001b5\n\u0007\u0005m\u0005KA\u0002NCB\fAaY8qsR\u0019!'!)\t\u000f\u0005\r6\u00031\u0001\u0002&\u0006)Q\r\u001f;sCB!\u0011qUAW\u001b\t\tIKC\u0002\u0002,z\tQ\u0001]1sC6LA!a,\u0002*\nA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\u0014%bC3\u0001\u0001*Y\u0003U!UmY5tS>tGK]3f%\u0016<'/Z:t_J\u0004\"!K\u000b\u0014\u000fU\tY,!1\u0002HB\u0019\u0011.!0\n\u0007\u0005}&J\u0001\u0004B]f\u0014VM\u001a\t\u0005{\u0005\r''C\u0002\u0002Fz\u0012Q\u0003R3gCVdG\u000fU1sC6\u001c(+Z1eC\ndW\r\u0005\u0003\u0002J\u0006MWBAAf\u0015\u0011\ti-a4\u0002\u0005%|'BAAi\u0003\u0011Q\u0017M^1\n\t\u0005U\u00171\u001a\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003o\u000b1c];qa>\u0014H/\u001a3J[B,(/\u001b;jKN,\"!!8\u0011\t%\fy\u000eR\u0005\u0004\u0003CT%!B!se\u0006L\u0018\u0001F:vaB|'\u000f^3e\u00136\u0004XO]5uS\u0016\u001c\b%\u0001\u0003m_\u0006$Gc\u0001\u001a\u0002j\"1\u00111^\rA\u0002\u0011\u000bA\u0001]1uQ\"\"\u0011DUA#\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\u0010\u0005\u0003\u0002v\u0006mXBAA|\u0015\u0011\tI0a4\u0002\t1\fgnZ\u0005\u0005\u0003{\f9P\u0001\u0004PE*,7\r\u001e\u0015\u0004+IC\u0006f\u0001\u000bS1\u0002"
)
public class DecisionTreeRegressor extends Regressor implements DecisionTreeRegressorParams, DefaultParamsWritable {
   private final String uid;
   private Param varianceCol;
   private Param impurity;
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

   public static DecisionTreeRegressor load(final String path) {
      return DecisionTreeRegressor$.MODULE$.load(path);
   }

   public static String[] supportedImpurities() {
      return DecisionTreeRegressor$.MODULE$.supportedImpurities();
   }

   public static MLReader read() {
      return DecisionTreeRegressor$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$DecisionTreeRegressorParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return DecisionTreeRegressorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final String getVarianceCol() {
      return HasVarianceCol.getVarianceCol$(this);
   }

   public final String getImpurity() {
      return HasVarianceImpurity.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return HasVarianceImpurity.getOldImpurity$(this);
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

   public final Param varianceCol() {
      return this.varianceCol;
   }

   public final void org$apache$spark$ml$param$shared$HasVarianceCol$_setter_$varianceCol_$eq(final Param x$1) {
      this.varianceCol = x$1;
   }

   public final Param impurity() {
      return this.impurity;
   }

   public final void org$apache$spark$ml$tree$HasVarianceImpurity$_setter_$impurity_$eq(final Param x$1) {
      this.impurity = x$1;
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

   public String uid() {
      return this.uid;
   }

   public DecisionTreeRegressor setMaxDepth(final int value) {
      return (DecisionTreeRegressor)this.set(this.maxDepth(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeRegressor setMaxBins(final int value) {
      return (DecisionTreeRegressor)this.set(this.maxBins(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeRegressor setMinInstancesPerNode(final int value) {
      return (DecisionTreeRegressor)this.set(this.minInstancesPerNode(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeRegressor setMinWeightFractionPerNode(final double value) {
      return (DecisionTreeRegressor)this.set(this.minWeightFractionPerNode(), BoxesRunTime.boxToDouble(value));
   }

   public DecisionTreeRegressor setMinInfoGain(final double value) {
      return (DecisionTreeRegressor)this.set(this.minInfoGain(), BoxesRunTime.boxToDouble(value));
   }

   public DecisionTreeRegressor setMaxMemoryInMB(final int value) {
      return (DecisionTreeRegressor)this.set(this.maxMemoryInMB(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeRegressor setCacheNodeIds(final boolean value) {
      return (DecisionTreeRegressor)this.set(this.cacheNodeIds(), BoxesRunTime.boxToBoolean(value));
   }

   public DecisionTreeRegressor setCheckpointInterval(final int value) {
      return (DecisionTreeRegressor)this.set(this.checkpointInterval(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeRegressor setImpurity(final String value) {
      return (DecisionTreeRegressor)this.set(this.impurity(), value);
   }

   public DecisionTreeRegressor setSeed(final long value) {
      return (DecisionTreeRegressor)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public DecisionTreeRegressor setVarianceCol(final String value) {
      return (DecisionTreeRegressor)this.set(this.varianceCol(), value);
   }

   public DecisionTreeRegressor setWeightCol(final String value) {
      return (DecisionTreeRegressor)this.set(this.weightCol(), value);
   }

   public DecisionTreeRegressionModel train(final Dataset dataset) {
      return (DecisionTreeRegressionModel)Instrumentation$.MODULE$.instrumented((instr) -> {
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
         Strategy strategy = this.getOldStrategy(categoricalFeatures);
         scala.Predef..MODULE$.require(!strategy.bootstrap(), () -> "DecisionTreeRegressor does not need bootstrap sampling");
         instr.logPipelineStage(this);
         instr.logDataset(instances);
         instr.logParams(this, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.params()).toImmutableArraySeq());
         int x$3 = 1;
         String x$4 = "all";
         long x$5 = BoxesRunTime.unboxToLong(this.$(this.seed()));
         Some x$6 = new Some(instr);
         Some x$7 = new Some(this.uid());
         boolean x$8 = RandomForest$.MODULE$.run$default$7();
         DecisionTreeModel[] trees = RandomForest$.MODULE$.run(instances, strategy, 1, "all", x$5, x$6, x$8, x$7);
         return (DecisionTreeRegressionModel)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(trees));
      });
   }

   public Strategy getOldStrategy(final Map categoricalFeatures) {
      return DecisionTreeParams.getOldStrategy$(this, categoricalFeatures, 0, Algo$.MODULE$.Regression(), this.getOldImpurity(), (double)1.0F);
   }

   public DecisionTreeRegressor copy(final ParamMap extra) {
      return (DecisionTreeRegressor)this.defaultCopy(extra);
   }

   public DecisionTreeRegressor(final String uid) {
      this.uid = uid;
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      HasVarianceImpurity.$init$(this);
      HasVarianceCol.$init$(this);
      DecisionTreeRegressorParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public DecisionTreeRegressor() {
      this(Identifiable$.MODULE$.randomUID("dtr"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
