package org.apache.spark.ml.classification;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
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
import org.apache.spark.ml.tree.DecisionTreeClassifierParams;
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.TreeClassifierParams;
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
import scala.Predef.;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015a\u0001\u0002\u000e\u001c\u0001\u0019B\u0001\"\u0011\u0001\u0003\u0006\u0004%\tE\u0011\u0005\t3\u0002\u0011\t\u0011)A\u0005\u0007\")1\f\u0001C\u00019\")1\f\u0001C\u0001A\")!\r\u0001C\u0001G\")A\u000e\u0001C\u0001[\")\u0001\u000f\u0001C\u0001c\")A\u000f\u0001C\u0001k\")Q\u0010\u0001C\u0001}\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\u0006\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u00033\u0001A\u0011AA\u000e\u0011\u001d\t\t\u0003\u0001C\u0001\u0003GAq!!\u000b\u0001\t\u0003\tY\u0003C\u0004\u0002<\u0001!\t!!\u0010\t\u000f\u0005\r\u0003\u0001\"\u0015\u0002F!A\u0011q\u000e\u0001\u0005\u0002u\t\t\bC\u0004\u0002\u0014\u0002!\t%!&\b\u000f\u0005=6\u0004#\u0001\u00022\u001a1!d\u0007E\u0001\u0003gCaa\u0017\u000b\u0005\u0002\u0005E\u0007\"CAj)\t\u0007IQAAk\u0011!\ty\u000e\u0006Q\u0001\u000e\u0005]\u0007bBAr)\u0011\u0005\u0013Q\u001d\u0005\n\u0003c$\u0012\u0011!C\u0005\u0003g\u0014a\u0003R3dSNLwN\u001c+sK\u0016\u001cE.Y:tS\u001aLWM\u001d\u0006\u00039u\tab\u00197bgNLg-[2bi&|gN\u0003\u0002\u001f?\u0005\u0011Q\u000e\u001c\u0006\u0003A\u0005\nQa\u001d9be.T!AI\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0013aA8sO\u000e\u00011\u0003\u0002\u0001(km\u0002R\u0001K\u0015,cIj\u0011aG\u0005\u0003Um\u0011q\u0003\u0015:pE\u0006\u0014\u0017\u000e\\5ti&\u001c7\t\\1tg&4\u0017.\u001a:\u0011\u00051zS\"A\u0017\u000b\u00059j\u0012A\u00027j]\u0006dw-\u0003\u00021[\t1a+Z2u_J\u0004\"\u0001\u000b\u0001\u0011\u0005!\u001a\u0014B\u0001\u001b\u001c\u0005}!UmY5tS>tGK]3f\u00072\f7o]5gS\u000e\fG/[8o\u001b>$W\r\u001c\t\u0003mej\u0011a\u000e\u0006\u0003qu\tA\u0001\u001e:fK&\u0011!h\u000e\u0002\u001d\t\u0016\u001c\u0017n]5p]R\u0013X-Z\"mCN\u001c\u0018NZ5feB\u000b'/Y7t!\tat(D\u0001>\u0015\tqT$\u0001\u0003vi&d\u0017B\u0001!>\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005\u0019\u0005C\u0001#N\u001d\t)5\n\u0005\u0002G\u00136\tqI\u0003\u0002IK\u00051AH]8pizR\u0011AS\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0019&\u000ba\u0001\u0015:fI\u00164\u0017B\u0001(P\u0005\u0019\u0019FO]5oO*\u0011A*\u0013\u0015\u0004\u0003E;\u0006C\u0001*V\u001b\u0005\u0019&B\u0001+ \u0003)\tgN\\8uCRLwN\\\u0005\u0003-N\u0013QaU5oG\u0016\f\u0013\u0001W\u0001\u0006c9\"d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003#^\u000ba\u0001P5oSRtDCA\u0019^\u0011\u0015\t5\u00011\u0001DQ\ri\u0016k\u0016\u0015\u0004\u0007E;F#A\u0019)\u0007\u0011\tv+A\u0006tKRl\u0015\r\u001f#faRDGC\u00013f\u001b\u0005\u0001\u0001\"\u00024\u0006\u0001\u00049\u0017!\u0002<bYV,\u0007C\u00015j\u001b\u0005I\u0015B\u00016J\u0005\rIe\u000e\u001e\u0015\u0004\u000bE;\u0016AC:fi6\u000b\u0007PQ5ogR\u0011AM\u001c\u0005\u0006M\u001a\u0001\ra\u001a\u0015\u0004\rE;\u0016AF:fi6Kg.\u00138ti\u0006t7-Z:QKJtu\u000eZ3\u0015\u0005\u0011\u0014\b\"\u00024\b\u0001\u00049\u0007fA\u0004R/\u0006Y2/\u001a;NS:<V-[4ii\u001a\u0013\u0018m\u0019;j_:\u0004VM\u001d(pI\u0016$\"\u0001\u001a<\t\u000b\u0019D\u0001\u0019A<\u0011\u0005!D\u0018BA=J\u0005\u0019!u.\u001e2mK\"\u001a\u0001\"U>\"\u0003q\fQa\r\u00181]A\nab]3u\u001b&t\u0017J\u001c4p\u000f\u0006Lg\u000e\u0006\u0002e\u007f\")a-\u0003a\u0001o\"\u001a\u0011\"U,\u0002!M,G/T1y\u001b\u0016lwN]=J]6\u0013Ec\u00013\u0002\b!)aM\u0003a\u0001O\"\u001a!\"U,\u0002\u001fM,GoQ1dQ\u0016tu\u000eZ3JIN$2\u0001ZA\b\u0011\u001917\u00021\u0001\u0002\u0012A\u0019\u0001.a\u0005\n\u0007\u0005U\u0011JA\u0004C_>dW-\u00198)\u0007-\tv+A\u000btKR\u001c\u0005.Z2la>Lg\u000e^%oi\u0016\u0014h/\u00197\u0015\u0007\u0011\fi\u0002C\u0003g\u0019\u0001\u0007q\rK\u0002\r#^\u000b1b]3u\u00136\u0004XO]5usR\u0019A-!\n\t\u000b\u0019l\u0001\u0019A\")\u00075\tv+A\u0004tKR\u001cV-\u001a3\u0015\u0007\u0011\fi\u0003\u0003\u0004g\u001d\u0001\u0007\u0011q\u0006\t\u0004Q\u0006E\u0012bAA\u001a\u0013\n!Aj\u001c8hQ\u0011q\u0011+a\u000e\"\u0005\u0005e\u0012!B\u0019/m9\u0002\u0014\u0001D:fi^+\u0017n\u001a5u\u0007>dGc\u00013\u0002@!)am\u0004a\u0001\u0007\"\u001aq\"U>\u0002\u000bQ\u0014\u0018-\u001b8\u0015\u0007I\n9\u0005C\u0004\u0002JA\u0001\r!a\u0013\u0002\u000f\u0011\fG/Y:fiB\"\u0011QJA/!\u0019\ty%!\u0016\u0002Z5\u0011\u0011\u0011\u000b\u0006\u0004\u0003'z\u0012aA:rY&!\u0011qKA)\u0005\u001d!\u0015\r^1tKR\u0004B!a\u0017\u0002^1\u0001A\u0001DA0\u0003\u000f\n\t\u0011!A\u0003\u0002\u0005\u0005$aA0%cE!\u00111MA5!\rA\u0017QM\u0005\u0004\u0003OJ%a\u0002(pi\"Lgn\u001a\t\u0004Q\u0006-\u0014bAA7\u0013\n\u0019\u0011I\\=\u0002\u001d\u001d,Go\u00147e'R\u0014\u0018\r^3hsR1\u00111OAC\u0003\u001f\u0003B!!\u001e\u0002\u00026\u0011\u0011q\u000f\u0006\u0005\u0003s\nY(A\u0007d_:4\u0017nZ;sCRLwN\u001c\u0006\u0004q\u0005u$bAA@?\u0005)Q\u000e\u001c7jE&!\u00111QA<\u0005!\u0019FO]1uK\u001eL\bbBAD#\u0001\u0007\u0011\u0011R\u0001\u0014G\u0006$XmZ8sS\u000e\fGNR3biV\u0014Xm\u001d\t\u0006\t\u0006-umZ\u0005\u0004\u0003\u001b{%aA'ba\"1\u0011\u0011S\tA\u0002\u001d\f!B\\;n\u00072\f7o]3t\u0003\u0011\u0019w\u000e]=\u0015\u0007E\n9\nC\u0004\u0002\u001aJ\u0001\r!a'\u0002\u000b\u0015DHO]1\u0011\t\u0005u\u00151U\u0007\u0003\u0003?S1!!)\u001e\u0003\u0015\u0001\u0018M]1n\u0013\u0011\t)+a(\u0003\u0011A\u000b'/Y7NCBDCAE)\u0002*\u0006\u0012\u00111V\u0001\u0006c9\"d&\r\u0015\u0004\u0001E;\u0016A\u0006#fG&\u001c\u0018n\u001c8Ue\u0016,7\t\\1tg&4\u0017.\u001a:\u0011\u0005!\"2c\u0002\u000b\u00026\u0006m\u0016\u0011\u0019\t\u0004Q\u0006]\u0016bAA]\u0013\n1\u0011I\\=SK\u001a\u0004B\u0001PA_c%\u0019\u0011qX\u001f\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u00111YAg\u001b\t\t)M\u0003\u0003\u0002H\u0006%\u0017AA5p\u0015\t\tY-\u0001\u0003kCZ\f\u0017\u0002BAh\u0003\u000b\u0014AbU3sS\u0006d\u0017N_1cY\u0016$\"!!-\u0002'M,\b\u000f]8si\u0016$\u0017*\u001c9ve&$\u0018.Z:\u0016\u0005\u0005]\u0007\u0003\u00025\u0002Z\u000eK1!a7J\u0005\u0015\t%O]1zQ\r1\u0012kV\u0001\u0015gV\u0004\bo\u001c:uK\u0012LU\u000e];sSRLWm\u001d\u0011)\u0007]\tv+\u0001\u0003m_\u0006$GcA\u0019\u0002h\"1\u0011\u0011\u001e\rA\u0002\r\u000bA\u0001]1uQ\"\"\u0001$UAwC\t\ty/A\u00033]Ar\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002vB!\u0011q_A\u007f\u001b\t\tIP\u0003\u0003\u0002|\u0006%\u0017\u0001\u00027b]\u001eLA!a@\u0002z\n1qJ\u00196fGRD3\u0001F)XQ\r\u0019\u0012k\u0016"
)
public class DecisionTreeClassifier extends ProbabilisticClassifier implements DecisionTreeClassifierParams, DefaultParamsWritable {
   private final String uid;
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

   public static DecisionTreeClassifier load(final String path) {
      return DecisionTreeClassifier$.MODULE$.load(path);
   }

   public static String[] supportedImpurities() {
      return DecisionTreeClassifier$.MODULE$.supportedImpurities();
   }

   public static MLReader read() {
      return DecisionTreeClassifier$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$DecisionTreeClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ProbabilisticClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return DecisionTreeClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final String getImpurity() {
      return TreeClassifierParams.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return TreeClassifierParams.getOldImpurity$(this);
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

   public final void org$apache$spark$ml$tree$TreeClassifierParams$_setter_$impurity_$eq(final Param x$1) {
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

   public DecisionTreeClassifier setMaxDepth(final int value) {
      return (DecisionTreeClassifier)this.set(this.maxDepth(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeClassifier setMaxBins(final int value) {
      return (DecisionTreeClassifier)this.set(this.maxBins(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeClassifier setMinInstancesPerNode(final int value) {
      return (DecisionTreeClassifier)this.set(this.minInstancesPerNode(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeClassifier setMinWeightFractionPerNode(final double value) {
      return (DecisionTreeClassifier)this.set(this.minWeightFractionPerNode(), BoxesRunTime.boxToDouble(value));
   }

   public DecisionTreeClassifier setMinInfoGain(final double value) {
      return (DecisionTreeClassifier)this.set(this.minInfoGain(), BoxesRunTime.boxToDouble(value));
   }

   public DecisionTreeClassifier setMaxMemoryInMB(final int value) {
      return (DecisionTreeClassifier)this.set(this.maxMemoryInMB(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeClassifier setCacheNodeIds(final boolean value) {
      return (DecisionTreeClassifier)this.set(this.cacheNodeIds(), BoxesRunTime.boxToBoolean(value));
   }

   public DecisionTreeClassifier setCheckpointInterval(final int value) {
      return (DecisionTreeClassifier)this.set(this.checkpointInterval(), BoxesRunTime.boxToInteger(value));
   }

   public DecisionTreeClassifier setImpurity(final String value) {
      return (DecisionTreeClassifier)this.set(this.impurity(), value);
   }

   public DecisionTreeClassifier setSeed(final long value) {
      return (DecisionTreeClassifier)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public DecisionTreeClassifier setWeightCol(final String value) {
      return (DecisionTreeClassifier)this.set(this.weightCol(), value);
   }

   public DecisionTreeClassificationModel train(final Dataset dataset) {
      return (DecisionTreeClassificationModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         instr.logPipelineStage(this);
         instr.logDataset(dataset);
         Map categoricalFeatures = MetadataUtils$.MODULE$.getCategoricalFeatures(dataset.schema().apply((String)this.$(this.featuresCol())));
         int numClasses = this.getNumClasses(dataset, this.getNumClasses$default$2());
         if (this.isDefined(this.thresholds())) {
            .MODULE$.require(((double[])this.$(this.thresholds())).length == numClasses, () -> this.getClass().getSimpleName() + ".train() called with non-matching numClasses and thresholds.length. numClasses=" + numClasses + ", but thresholds has length " + ((double[])this.$(this.thresholds())).length);
         }

         RDD instances = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkClassificationLabels((String)this.$(this.labelCol()), new Some(BoxesRunTime.boxToInteger(numClasses))), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol())), DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))}))).rdd().map((x0$1) -> {
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
         Strategy strategy = this.getOldStrategy(categoricalFeatures, numClasses);
         .MODULE$.require(!strategy.bootstrap(), () -> "DecisionTreeClassifier does not need bootstrap sampling");
         instr.logNumClasses((long)numClasses);
         instr.logParams(this, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.predictionCol(), this.rawPredictionCol(), this.probabilityCol(), this.leafCol(), this.maxDepth(), this.maxBins(), this.minInstancesPerNode(), this.minInfoGain(), this.maxMemoryInMB(), this.cacheNodeIds(), this.checkpointInterval(), this.impurity(), this.seed(), this.thresholds()}));
         int x$3 = 1;
         String x$4 = "all";
         long x$5 = BoxesRunTime.unboxToLong(this.$(this.seed()));
         Some x$6 = new Some(instr);
         Some x$7 = new Some(this.uid());
         boolean x$8 = RandomForest$.MODULE$.run$default$7();
         DecisionTreeModel[] trees = RandomForest$.MODULE$.run(instances, strategy, 1, "all", x$5, x$6, x$8, x$7);
         return (DecisionTreeClassificationModel)scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.refArrayOps(trees));
      });
   }

   public Strategy getOldStrategy(final Map categoricalFeatures, final int numClasses) {
      return DecisionTreeParams.getOldStrategy$(this, categoricalFeatures, numClasses, Algo$.MODULE$.Classification(), this.getOldImpurity(), (double)1.0F);
   }

   public DecisionTreeClassifier copy(final ParamMap extra) {
      return (DecisionTreeClassifier)this.defaultCopy(extra);
   }

   public DecisionTreeClassifier(final String uid) {
      this.uid = uid;
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      TreeClassifierParams.$init$(this);
      DecisionTreeClassifierParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public DecisionTreeClassifier() {
      this(Identifiable$.MODULE$.randomUID("dtc"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
