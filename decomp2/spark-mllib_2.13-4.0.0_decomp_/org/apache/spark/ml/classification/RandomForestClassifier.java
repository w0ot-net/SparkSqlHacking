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
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.RandomForestClassifierParams;
import org.apache.spark.ml.tree.RandomForestParams;
import org.apache.spark.ml.tree.TreeClassifierParams;
import org.apache.spark.ml.tree.TreeEnsembleClassifierParams;
import org.apache.spark.ml.tree.TreeEnsembleParams;
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
import scala.Tuple3;
import scala.Predef.;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\re\u0001B\u0011#\u00015B\u0001\u0002\u0013\u0001\u0003\u0006\u0004%\t%\u0013\u0005\tA\u0002\u0011\t\u0011)A\u0005\u0015\")!\r\u0001C\u0001G\")!\r\u0001C\u0001O\")\u0011\u000e\u0001C\u0001U\")1\u000f\u0001C\u0001i\")q\u000f\u0001C\u0001q\")1\u0010\u0001C\u0001y\"9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-\u0001bBA\t\u0001\u0011\u0005\u00111\u0003\u0005\b\u00033\u0001A\u0011AA\u000e\u0011\u001d\t9\u0003\u0001C\u0001\u0003SAq!a\f\u0001\t\u0003\t\t\u0004C\u0004\u00028\u0001!\t!!\u000f\t\u000f\u0005}\u0002\u0001\"\u0001\u0002B!9\u0011Q\n\u0001\u0005\u0002\u0005=\u0003bBA+\u0001\u0011\u0005\u0011q\u000b\u0005\b\u0003;\u0002A\u0011AA0\u0011\u001d\t)\u0007\u0001C\u0001\u0003OBq!!\u001c\u0001\t#\ny\u0007C\u0004\u0002\u001a\u0002!I!a'\t\u000f\u0005\u0005\u0007\u0001\"\u0011\u0002D\"q\u00111\u001c\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002^\n}qa\u0002B\u0015E!\u0005!1\u0006\u0004\u0007C\tB\tA!\f\t\r\tLB\u0011\u0001B&\u0011%\u0011i%\u0007b\u0001\n\u000b\u0011y\u0005\u0003\u0005\u0003Ve\u0001\u000bQ\u0002B)\u0011%\u0011I&\u0007b\u0001\n\u000b\u0011y\u0005\u0003\u0005\u0003^e\u0001\u000bQ\u0002B)\u0011\u001d\u0011\t'\u0007C!\u0005GB\u0011Ba\u001c\u001a\u0003\u0003%IA!\u001d\u0003-I\u000bg\u000eZ8n\r>\u0014Xm\u001d;DY\u0006\u001c8/\u001b4jKJT!a\t\u0013\u0002\u001d\rd\u0017m]:jM&\u001c\u0017\r^5p]*\u0011QEJ\u0001\u0003[2T!a\n\u0015\u0002\u000bM\u0004\u0018M]6\u000b\u0005%R\u0013AB1qC\u000eDWMC\u0001,\u0003\ry'oZ\u0002\u0001'\u0011\u0001a\u0006\u0010\"\u0011\u000b=\u0002$\u0007O\u001d\u000e\u0003\tJ!!\r\u0012\u0003/A\u0013xNY1cS2L7\u000f^5d\u00072\f7o]5gS\u0016\u0014\bCA\u001a7\u001b\u0005!$BA\u001b%\u0003\u0019a\u0017N\\1mO&\u0011q\u0007\u000e\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u0005=\u0002\u0001CA\u0018;\u0013\tY$EA\u0010SC:$w.\u001c$pe\u0016\u001cHo\u00117bgNLg-[2bi&|g.T8eK2\u0004\"!\u0010!\u000e\u0003yR!a\u0010\u0013\u0002\tQ\u0014X-Z\u0005\u0003\u0003z\u0012ADU1oI>lgi\u001c:fgR\u001cE.Y:tS\u001aLWM\u001d)be\u0006l7\u000f\u0005\u0002D\r6\tAI\u0003\u0002FI\u0005!Q\u000f^5m\u0013\t9EIA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn],sSR\f'\r\\3\u0002\u0007ULG-F\u0001K!\tYEK\u0004\u0002M%B\u0011Q\nU\u0007\u0002\u001d*\u0011q\nL\u0001\u0007yI|w\u000e\u001e \u000b\u0003E\u000bQa]2bY\u0006L!a\u0015)\u0002\rA\u0013X\rZ3g\u0013\t)fK\u0001\u0004TiJLgn\u001a\u0006\u0003'BC3!\u0001-_!\tIF,D\u0001[\u0015\tYf%\u0001\u0006b]:|G/\u0019;j_:L!!\u0018.\u0003\u000bMKgnY3\"\u0003}\u000bQ!\r\u00185]A\nA!^5eA!\u001a!\u0001\u00170\u0002\rqJg.\u001b;?)\tAD\rC\u0003I\u0007\u0001\u0007!\nK\u0002e1zC3a\u0001-_)\u0005A\u0004f\u0001\u0003Y=\u0006Y1/\u001a;NCb$U\r\u001d;i)\tYG.D\u0001\u0001\u0011\u0015iW\u00011\u0001o\u0003\u00151\u0018\r\\;f!\ty\u0007/D\u0001Q\u0013\t\t\bKA\u0002J]RD3!\u0002-_\u0003)\u0019X\r^'bq\nKgn\u001d\u000b\u0003WVDQ!\u001c\u0004A\u00029D3A\u0002-_\u0003Y\u0019X\r^'j]&s7\u000f^1oG\u0016\u001c\b+\u001a:O_\u0012,GCA6z\u0011\u0015iw\u00011\u0001oQ\r9\u0001LX\u0001\u001cg\u0016$X*\u001b8XK&<\u0007\u000e\u001e$sC\u000e$\u0018n\u001c8QKJtu\u000eZ3\u0015\u0005-l\b\"B7\t\u0001\u0004q\bCA8\u0000\u0013\r\t\t\u0001\u0015\u0002\u0007\t>,(\r\\3)\t!A\u0016QA\u0011\u0003\u0003\u000f\tQa\r\u00181]A\nab]3u\u001b&t\u0017J\u001c4p\u000f\u0006Lg\u000eF\u0002l\u0003\u001bAQ!\\\u0005A\u0002yD3!\u0003-_\u0003A\u0019X\r^'bq6+Wn\u001c:z\u0013:l%\tF\u0002l\u0003+AQ!\u001c\u0006A\u00029D3A\u0003-_\u0003=\u0019X\r^\"bG\",gj\u001c3f\u0013\u0012\u001cHcA6\u0002\u001e!1Qn\u0003a\u0001\u0003?\u00012a\\A\u0011\u0013\r\t\u0019\u0003\u0015\u0002\b\u0005>|G.Z1oQ\rY\u0001LX\u0001\u0016g\u0016$8\t[3dWB|\u0017N\u001c;J]R,'O^1m)\rY\u00171\u0006\u0005\u0006[2\u0001\rA\u001c\u0015\u0004\u0019as\u0016aC:fi&k\u0007/\u001e:jif$2a[A\u001a\u0011\u0015iW\u00021\u0001KQ\ri\u0001LX\u0001\u0013g\u0016$8+\u001e2tC6\u0004H.\u001b8h%\u0006$X\rF\u0002l\u0003wAQ!\u001c\bA\u0002yD3A\u0004-_\u0003\u001d\u0019X\r^*fK\u0012$2a[A\"\u0011\u0019iw\u00021\u0001\u0002FA\u0019q.a\u0012\n\u0007\u0005%\u0003K\u0001\u0003M_:<\u0007fA\bY=\u0006Y1/\u001a;Ok6$&/Z3t)\rY\u0017\u0011\u000b\u0005\u0006[B\u0001\rA\u001c\u0015\u0004!as\u0016\u0001D:fi\n{w\u000e^:ue\u0006\u0004HcA6\u0002Z!1Q.\u0005a\u0001\u0003?AC!\u0005-\u0002\u0006\u0005A2/\u001a;GK\u0006$XO]3Tk\n\u001cX\r^*ue\u0006$XmZ=\u0015\u0007-\f\t\u0007C\u0003n%\u0001\u0007!\nK\u0002\u00131z\u000bAb]3u/\u0016Lw\r\u001b;D_2$2a[A5\u0011\u0015i7\u00031\u0001KQ\u0011\u0019\u0002,!\u0002\u0002\u000bQ\u0014\u0018-\u001b8\u0015\u0007e\n\t\bC\u0004\u0002tQ\u0001\r!!\u001e\u0002\u000f\u0011\fG/Y:fiB\"\u0011qOAD!\u0019\tI(a \u0002\u00046\u0011\u00111\u0010\u0006\u0004\u0003{2\u0013aA:rY&!\u0011\u0011QA>\u0005\u001d!\u0015\r^1tKR\u0004B!!\"\u0002\b2\u0001A\u0001DAE\u0003c\n\t\u0011!A\u0003\u0002\u0005-%aA0%cE!\u0011QRAJ!\ry\u0017qR\u0005\u0004\u0003#\u0003&a\u0002(pi\"Lgn\u001a\t\u0004_\u0006U\u0015bAAL!\n\u0019\u0011I\\=\u0002\u0017\r\u0014X-\u0019;f\u001b>$W\r\u001c\u000b\ns\u0005u\u0015\u0011VA]\u0003{Cq!a\u001d\u0016\u0001\u0004\ty\n\r\u0003\u0002\"\u0006\u0015\u0006CBA=\u0003\u007f\n\u0019\u000b\u0005\u0003\u0002\u0006\u0006\u0015F\u0001DAT\u0003;\u000b\t\u0011!A\u0003\u0002\u0005-%aA0%e!9\u00111V\u000bA\u0002\u00055\u0016!\u0002;sK\u0016\u001c\b#B8\u00020\u0006M\u0016bAAY!\n)\u0011I\u001d:bsB\u0019q&!.\n\u0007\u0005]&EA\u0010EK\u000eL7/[8o)J,Wm\u00117bgNLg-[2bi&|g.T8eK2Da!a/\u0016\u0001\u0004q\u0017a\u00038v[\u001a+\u0017\r^;sKNDa!a0\u0016\u0001\u0004q\u0017A\u00038v[\u000ec\u0017m]:fg\u0006!1m\u001c9z)\rA\u0014Q\u0019\u0005\b\u0003\u000f4\u0002\u0019AAe\u0003\u0015)\u0007\u0010\u001e:b!\u0011\tY-!5\u000e\u0005\u00055'bAAhI\u0005)\u0001/\u0019:b[&!\u00111[Ag\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\b\u0006\u0002\fY\u0003/\f#!!7\u0002\u000bErCGL\u0019\u0002)M,\b/\u001a:%O\u0016$x\n\u001c3TiJ\fG/Z4z))\ty.!=\u0002|\u0006u(q\u0002\t\u0005\u0003C\fi/\u0004\u0002\u0002d*!\u0011Q]At\u00035\u0019wN\u001c4jOV\u0014\u0018\r^5p]*\u0019q(!;\u000b\u0007\u0005-h%A\u0003nY2L'-\u0003\u0003\u0002p\u0006\r(\u0001C*ue\u0006$XmZ=\t\u000f\u0005Mx\u00031\u0001\u0002v\u0006\u00192-\u0019;fO>\u0014\u0018nY1m\r\u0016\fG/\u001e:fgB)1*a>o]&\u0019\u0011\u0011 ,\u0003\u00075\u000b\u0007\u000f\u0003\u0004\u0002@^\u0001\rA\u001c\u0005\b\u0003\u007f<\u0002\u0019\u0001B\u0001\u0003\u001dyG\u000eZ!mO>\u0004BAa\u0001\u0003\n9!\u0011\u0011\u001dB\u0003\u0013\u0011\u00119!a9\u0002\t\u0005cwm\\\u0005\u0005\u0005\u0017\u0011iA\u0001\u0003BY\u001e|'\u0002\u0002B\u0004\u0003GDqA!\u0005\u0018\u0001\u0004\u0011\u0019\"A\u0006pY\u0012LU\u000e];sSRL\b\u0003\u0002B\u000b\u00057i!Aa\u0006\u000b\t\te\u0011q]\u0001\tS6\u0004XO]5us&!!Q\u0004B\f\u0005!IU\u000e];sSRL\u0018\u0002\u0002B\u0011\u0005G\tabZ3u\u001f2$7\u000b\u001e:bi\u0016<\u00170C\u0002\u0003&y\u0012!\u0003\u0016:fK\u0016s7/Z7cY\u0016\u0004\u0016M]1ng\"\u001a\u0001\u0001\u00170\u0002-I\u000bg\u000eZ8n\r>\u0014Xm\u001d;DY\u0006\u001c8/\u001b4jKJ\u0004\"aL\r\u0014\u000fe\u0011yC!\u000e\u0003<A\u0019qN!\r\n\u0007\tM\u0002K\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0007\n]\u0002(C\u0002\u0003:\u0011\u0013Q\u0003R3gCVdG\u000fU1sC6\u001c(+Z1eC\ndW\r\u0005\u0003\u0003>\t\u001dSB\u0001B \u0015\u0011\u0011\tEa\u0011\u0002\u0005%|'B\u0001B#\u0003\u0011Q\u0017M^1\n\t\t%#q\b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0005W\t1c];qa>\u0014H/\u001a3J[B,(/\u001b;jKN,\"A!\u0015\u0011\t=\fyK\u0013\u0015\u00047as\u0016\u0001F:vaB|'\u000f^3e\u00136\u0004XO]5uS\u0016\u001c\b\u0005K\u0002\u001d1z\u000b\u0001e];qa>\u0014H/\u001a3GK\u0006$XO]3Tk\n\u001cX\r^*ue\u0006$XmZ5fg\"\u001aQ\u0004\u00170\u0002CM,\b\u000f]8si\u0016$g)Z1ukJ,7+\u001e2tKR\u001cFO]1uK\u001eLWm\u001d\u0011)\u0007yAf,\u0001\u0003m_\u0006$Gc\u0001\u001d\u0003f!1!qM\u0010A\u0002)\u000bA\u0001]1uQ\"\"q\u0004\u0017B6C\t\u0011i'A\u00033]Ar\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003tA!!Q\u000fB>\u001b\t\u00119H\u0003\u0003\u0003z\t\r\u0013\u0001\u00027b]\u001eLAA! \u0003x\t1qJ\u00196fGRD3!\u0007-_Q\rA\u0002L\u0018"
)
public class RandomForestClassifier extends ProbabilisticClassifier implements RandomForestClassifierParams, DefaultParamsWritable {
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

   public static RandomForestClassifier load(final String path) {
      return RandomForestClassifier$.MODULE$.load(path);
   }

   public static String[] supportedFeatureSubsetStrategies() {
      return RandomForestClassifier$.MODULE$.supportedFeatureSubsetStrategies();
   }

   public static String[] supportedImpurities() {
      return RandomForestClassifier$.MODULE$.supportedImpurities();
   }

   public static MLReader read() {
      return RandomForestClassifier$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getImpurity() {
      return TreeClassifierParams.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return TreeClassifierParams.getOldImpurity$(this);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$TreeEnsembleClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ProbabilisticClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return TreeEnsembleClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
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

   public final void org$apache$spark$ml$tree$TreeClassifierParams$_setter_$impurity_$eq(final Param x$1) {
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

   public RandomForestClassifier setMaxDepth(final int value) {
      return (RandomForestClassifier)this.set(this.maxDepth(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestClassifier setMaxBins(final int value) {
      return (RandomForestClassifier)this.set(this.maxBins(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestClassifier setMinInstancesPerNode(final int value) {
      return (RandomForestClassifier)this.set(this.minInstancesPerNode(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestClassifier setMinWeightFractionPerNode(final double value) {
      return (RandomForestClassifier)this.set(this.minWeightFractionPerNode(), BoxesRunTime.boxToDouble(value));
   }

   public RandomForestClassifier setMinInfoGain(final double value) {
      return (RandomForestClassifier)this.set(this.minInfoGain(), BoxesRunTime.boxToDouble(value));
   }

   public RandomForestClassifier setMaxMemoryInMB(final int value) {
      return (RandomForestClassifier)this.set(this.maxMemoryInMB(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestClassifier setCacheNodeIds(final boolean value) {
      return (RandomForestClassifier)this.set(this.cacheNodeIds(), BoxesRunTime.boxToBoolean(value));
   }

   public RandomForestClassifier setCheckpointInterval(final int value) {
      return (RandomForestClassifier)this.set(this.checkpointInterval(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestClassifier setImpurity(final String value) {
      return (RandomForestClassifier)this.set(this.impurity(), value);
   }

   public RandomForestClassifier setSubsamplingRate(final double value) {
      return (RandomForestClassifier)this.set(this.subsamplingRate(), BoxesRunTime.boxToDouble(value));
   }

   public RandomForestClassifier setSeed(final long value) {
      return (RandomForestClassifier)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public RandomForestClassifier setNumTrees(final int value) {
      return (RandomForestClassifier)this.set(this.numTrees(), BoxesRunTime.boxToInteger(value));
   }

   public RandomForestClassifier setBootstrap(final boolean value) {
      return (RandomForestClassifier)this.set(this.bootstrap(), BoxesRunTime.boxToBoolean(value));
   }

   public RandomForestClassifier setFeatureSubsetStrategy(final String value) {
      return (RandomForestClassifier)this.set(this.featureSubsetStrategy(), value);
   }

   public RandomForestClassifier setWeightCol(final String value) {
      return (RandomForestClassifier)this.set(this.weightCol(), value);
   }

   public RandomForestClassificationModel train(final Dataset dataset) {
      return (RandomForestClassificationModel)Instrumentation$.MODULE$.instrumented((instr) -> {
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
         Strategy strategy = this.super$getOldStrategy(categoricalFeatures, numClasses, Algo$.MODULE$.Classification(), this.getOldImpurity());
         strategy.bootstrap_$eq(BoxesRunTime.unboxToBoolean(this.$(this.bootstrap())));
         instr.logParams(this, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.weightCol(), this.predictionCol(), this.probabilityCol(), this.rawPredictionCol(), this.leafCol(), this.impurity(), this.numTrees(), this.featureSubsetStrategy(), this.maxDepth(), this.maxBins(), this.maxMemoryInMB(), this.minInfoGain(), this.minInstancesPerNode(), this.minWeightFractionPerNode(), this.seed(), this.subsamplingRate(), this.thresholds(), this.cacheNodeIds(), this.checkpointInterval(), this.bootstrap()}));
         DecisionTreeClassificationModel[] trees = (DecisionTreeClassificationModel[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(RandomForest$.MODULE$.run(instances, strategy, this.getNumTrees(), this.getFeatureSubsetStrategy(), this.getSeed(), new Some(instr), RandomForest$.MODULE$.run$default$7(), RandomForest$.MODULE$.run$default$8())), (x$1) -> (DecisionTreeClassificationModel)x$1, scala.reflect.ClassTag..MODULE$.apply(DecisionTreeClassificationModel.class));
         scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps(trees), (x$2) -> (DecisionTreeClassificationModel)this.copyValues(x$2, this.copyValues$default$2()));
         int numFeatures = ((DecisionTreeClassificationModel)scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.refArrayOps(trees))).numFeatures();
         instr.logNumClasses((long)numClasses);
         instr.logNumFeatures((long)numFeatures);
         return this.createModel(dataset, trees, numFeatures, numClasses);
      });
   }

   private RandomForestClassificationModel createModel(final Dataset dataset, final DecisionTreeClassificationModel[] trees, final int numFeatures, final int numClasses) {
      RandomForestClassificationModel model = (RandomForestClassificationModel)this.copyValues(new RandomForestClassificationModel(this.uid(), trees, numFeatures, numClasses), this.copyValues$default$2());
      String weightColName = !this.isDefined(this.weightCol()) ? "weightCol" : (String)this.$(this.weightCol());
      Tuple3 var9 = model.findSummaryModel();
      if (var9 != null) {
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var9._1();
         String probabilityColName = (String)var9._2();
         String predictionColName = (String)var9._3();
         Tuple3 var8 = new Tuple3(summaryModel, probabilityColName, predictionColName);
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var8._1();
         String probabilityColName = (String)var8._2();
         String predictionColName = (String)var8._3();
         RandomForestClassificationSummaryImpl rfSummary = (RandomForestClassificationSummaryImpl)(numClasses <= 2 ? new BinaryRandomForestClassificationTrainingSummaryImpl(summaryModel.transform(dataset), probabilityColName, predictionColName, (String)this.$(this.labelCol()), weightColName, new double[]{(double)0.0F}) : new RandomForestClassificationTrainingSummaryImpl(summaryModel.transform(dataset), predictionColName, (String)this.$(this.labelCol()), weightColName, new double[]{(double)0.0F}));
         return (RandomForestClassificationModel)model.setSummary(new Some(rfSummary));
      } else {
         throw new MatchError(var9);
      }
   }

   public RandomForestClassifier copy(final ParamMap extra) {
      return (RandomForestClassifier)this.defaultCopy(extra);
   }

   public RandomForestClassifier(final String uid) {
      this.uid = uid;
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      TreeEnsembleParams.$init$(this);
      RandomForestParams.$init$(this);
      TreeEnsembleClassifierParams.$init$(this);
      TreeClassifierParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public RandomForestClassifier() {
      this(Identifiable$.MODULE$.randomUID("rfc"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
