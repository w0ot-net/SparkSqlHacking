package org.apache.spark.ml.clustering;

import breeze.linalg.DenseMatrix;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.clustering.LDAOptimizer;
import org.apache.spark.mllib.clustering.LDAUtils$;
import org.apache.spark.mllib.clustering.OnlineLDAOptimizer$;
import org.apache.spark.mllib.linalg.VectorImplicits$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.functions.;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t-a!B\r\u001b\u0003\u0003)\u0003\u0002C\u001e\u0001\u0005\u000b\u0007I\u0011\t\u001f\t\u0011M\u0003!\u0011!Q\u0001\nuB\u0001\"\u0016\u0001\u0003\u0006\u0004%\tA\u0016\u0005\t9\u0002\u0011\t\u0011)A\u0005/\"Ia\f\u0001BC\u0002\u0013\u0005Ad\u0018\u0005\tO\u0002\u0011\t\u0011)A\u0005A\"1Q\u000e\u0001C\u000199Da!\u001e\u0001\u0007\u0002i1\bbB@\u0001\r\u0003Q\u0012\u0011\u0001\u0005\t\u0003\u0013\u0001A\u0011\u0001\u000f\u0002\f!A\u0011\u0011\u0004\u0001\u0005\u0002q\tY\u0002C\u0004\u0002\u001e\u0001!\t!a\b\t\u000f\u0005%\u0002\u0001\"\u0001\u0002,!9\u0011Q\u0007\u0001\u0005\u0002\u0005]\u0002bBA\"\u0001\u0011\u0005\u0013Q\t\u0005\b\u0003\u001b\u0003A\u0011BAH\u0011\u001d\t\u0019\u000b\u0001C!\u0003KCq!!/\u0001\t\u0003\tY\fC\u0004\u0002@\u0002!\t!!1\t\u000f\u0005-\u0007A\"\u0001\u0002N\"9\u0011q\u001b\u0001\u0005\u0002\u0005e\u0007bBAu\u0001\u0011\u0005\u00111\u001e\u0005\b\u0003w\u0004A\u0011AA\u007f\u0011\u001d\tY\u0010\u0001C\u0001\u0005\u000b\u0011\u0001\u0002\u0014#B\u001b>$W\r\u001c\u0006\u00037q\t!b\u00197vgR,'/\u001b8h\u0015\tib$\u0001\u0002nY*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u00011CfL\u001b\u0011\u0007\u001dB#&D\u0001\u001d\u0013\tICDA\u0003N_\u0012,G\u000e\u0005\u0002,\u00015\t!\u0004\u0005\u0002,[%\u0011aF\u0007\u0002\n\u0019\u0012\u000b\u0005+\u0019:b[N\u0004\"\u0001M\u001a\u000e\u0003ER!A\r\u0010\u0002\u0011%tG/\u001a:oC2L!\u0001N\u0019\u0003\u000f1{wmZ5oOB\u0011a'O\u0007\u0002o)\u0011\u0001\bH\u0001\u0005kRLG.\u0003\u0002;o\tQQ\nT,sSR\f'\r\\3\u0002\u0007ULG-F\u0001>!\tqtI\u0004\u0002@\u000bB\u0011\u0001iQ\u0007\u0002\u0003*\u0011!\tJ\u0001\u0007yI|w\u000e\u001e \u000b\u0003\u0011\u000bQa]2bY\u0006L!AR\"\u0002\rA\u0013X\rZ3g\u0013\tA\u0015J\u0001\u0004TiJLgn\u001a\u0006\u0003\r\u000eC3!A&R!\tau*D\u0001N\u0015\tqe$\u0001\u0006b]:|G/\u0019;j_:L!\u0001U'\u0003\u000bMKgnY3\"\u0003I\u000bQ!\r\u00187]A\nA!^5eA!\u001a!aS)\u0002\u0013Y|7-\u00192TSj,W#A,\u0011\u0005aKV\"A\"\n\u0005i\u001b%aA%oi\"\u001a1aS)\u0002\u0015Y|7-\u00192TSj,\u0007\u0005K\u0002\u0005\u0017F\u000bAb\u001d9be.\u001cVm]:j_:,\u0012\u0001\u0019\t\u0003C\u0012l\u0011A\u0019\u0006\u0003Gz\t1a]9m\u0013\t)'M\u0001\u0007Ta\u0006\u00148nU3tg&|g\u000eK\u0002\u0006\u0017F\u000bQb\u001d9be.\u001cVm]:j_:\u0004\u0003f\u0001\u0004L#\"\u0012aA\u001b\t\u00031.L!\u0001\\\"\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018A\u0002\u001fj]&$h\b\u0006\u0003+_F\u001c\b\"B\u001e\b\u0001\u0004i\u0004fA8L#\")Qk\u0002a\u0001/\"\u001a\u0011oS)\t\u000by;\u0001\u0019\u00011)\u0007M\\\u0015+A\u0007pY\u0012dunY1m\u001b>$W\r\\\u000b\u0002oB\u0011\u0001\u0010`\u0007\u0002s*\u00111D\u001f\u0006\u0003wz\tQ!\u001c7mS\nL!!`=\u0003\u001b1{7-\u00197M\t\u0006ku\u000eZ3mQ\rA1*U\u0001\tO\u0016$Xj\u001c3fYV\u0011\u00111\u0001\t\u0004q\u0006\u0015\u0011BA\rzQ\rI1*U\u0001\u001dO\u0016$XI\u001a4fGRLg/\u001a#pG\u000e{gnY3oiJ\fG/[8o+\t\ti\u0001E\u0003Y\u0003\u001f\t\u0019\"C\u0002\u0002\u0012\r\u0013Q!\u0011:sCf\u00042\u0001WA\u000b\u0013\r\t9b\u0011\u0002\u0007\t>,(\r\\3\u0002=\u001d,G/\u00124gK\u000e$\u0018N^3U_BL7mQ8oG\u0016tGO]1uS>tWCAA\n\u00039\u0019X\r\u001e$fCR,(/Z:D_2$B!!\t\u0002$5\t\u0001\u0001\u0003\u0004\u0002&1\u0001\r!P\u0001\u0006m\u0006dW/\u001a\u0015\u0004\u0019-\u000b\u0016aF:fiR{\u0007/[2ESN$(/\u001b2vi&|gnQ8m)\u0011\t\t#!\f\t\r\u0005\u0015R\u00021\u0001>Q\u0011i1*!\r\"\u0005\u0005M\u0012!\u0002\u001a/e9\u0002\u0014aB:fiN+W\r\u001a\u000b\u0005\u0003C\tI\u0004C\u0004\u0002&9\u0001\r!a\u000f\u0011\u0007a\u000bi$C\u0002\u0002@\r\u0013A\u0001T8oO\"\u001aabS)\u0002\u0013Q\u0014\u0018M\\:g_JlG\u0003BA$\u0003K\u0002B!!\u0013\u0002`9!\u00111JA.\u001d\u0011\ti%!\u0017\u000f\t\u0005=\u0013q\u000b\b\u0005\u0003#\n)FD\u0002A\u0003'J\u0011aI\u0005\u0003C\tJ!a\b\u0011\n\u0005\rt\u0012bAA/E\u00069\u0001/Y2lC\u001e,\u0017\u0002BA1\u0003G\u0012\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0007\u0005u#\rC\u0004\u0002h=\u0001\r!!\u001b\u0002\u000f\u0011\fG/Y:fiB\"\u00111NA;!\u0015\t\u0017QNA9\u0013\r\tyG\u0019\u0002\b\t\u0006$\u0018m]3u!\u0011\t\u0019(!\u001e\r\u0001\u0011a\u0011qOA3\u0003\u0003\u0005\tQ!\u0001\u0002z\t\u0019q\fJ\u0019\u0012\t\u0005m\u0014\u0011\u0011\t\u00041\u0006u\u0014bAA@\u0007\n9aj\u001c;iS:<\u0007c\u0001-\u0002\u0004&\u0019\u0011QQ\"\u0003\u0007\u0005s\u0017\u0010\u000b\u0003\u0010\u0017\u0006%\u0015EAAF\u0003\u0015\u0011d\u0006\r\u00181\u0003i9W\r\u001e+pa&\u001cG)[:ue&\u0014W\u000f^5p]6+G\u000f[8e+\t\t\t\nE\u0004Y\u0003'\u000b9*a&\n\u0007\u0005U5IA\u0005Gk:\u001cG/[8ocA!\u0011\u0011TAP\u001b\t\tYJC\u0002\u0002\u001er\ta\u0001\\5oC2<\u0017\u0002BAQ\u00037\u0013aAV3di>\u0014\u0018a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005\u001d\u00161\u0017\t\u0005\u0003S\u000by+\u0004\u0002\u0002,*\u0019\u0011Q\u00162\u0002\u000bQL\b/Z:\n\t\u0005E\u00161\u0016\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA[#\u0001\u0007\u0011qU\u0001\u0007g\u000eDW-\\1)\u0007EY\u0015+A\rfgRLW.\u0019;fI\u0012{7mQ8oG\u0016tGO]1uS>tWCAALQ\u0011\u00112*!#\u0002\u0019Q|\u0007/[2t\u001b\u0006$(/\u001b=\u0016\u0005\u0005\r\u0007\u0003BAM\u0003\u000bLA!a2\u0002\u001c\n1Q*\u0019;sSbDCaE&\u0002\n\u0006i\u0011n\u001d#jgR\u0014\u0018NY;uK\u0012,\"!a4\u0011\u0007a\u000b\t.C\u0002\u0002T\u000e\u0013qAQ8pY\u0016\fg\u000eK\u0002\u0015\u0017F\u000bQ\u0002\\8h\u0019&\\W\r\\5i_>$G\u0003BA\n\u00037Dq!a\u001a\u0016\u0001\u0004\ti\u000e\r\u0003\u0002`\u0006\r\b#B1\u0002n\u0005\u0005\b\u0003BA:\u0003G$A\"!:\u0002\\\u0006\u0005\t\u0011!B\u0001\u0003s\u00121a\u0018\u00133Q\u0011)2*!#\u0002\u001b1|w\rU3sa2,\u00070\u001b;z)\u0011\t\u0019\"!<\t\u000f\u0005\u001dd\u00031\u0001\u0002pB\"\u0011\u0011_A{!\u0015\t\u0017QNAz!\u0011\t\u0019(!>\u0005\u0019\u0005]\u0018Q^A\u0001\u0002\u0003\u0015\t!!\u001f\u0003\u0007}#3\u0007\u000b\u0003\u0017\u0017\u0006%\u0015A\u00043fg\u000e\u0014\u0018NY3U_BL7m\u001d\u000b\u0005\u0003\u000f\ny\u0010\u0003\u0004\u0003\u0002]\u0001\raV\u0001\u0011[\u0006DH+\u001a:ngB+'\u000fV8qS\u000eD3aF&R)\t\t9\u0005K\u0002\u0019\u0017FC3\u0001A&R\u0001"
)
public abstract class LDAModel extends Model implements LDAParams, MLWritable {
   private final String uid;
   private final int vocabSize;
   private final transient SparkSession sparkSession;
   private IntParam k;
   private DoubleArrayParam docConcentration;
   private DoubleParam topicConcentration;
   private String[] supportedOptimizers;
   private Param optimizer;
   private Param topicDistributionCol;
   private DoubleParam learningOffset;
   private DoubleParam learningDecay;
   private DoubleParam subsamplingRate;
   private BooleanParam optimizeDocConcentration;
   private BooleanParam keepLastCheckpoint;
   private IntParam checkpointInterval;
   private LongParam seed;
   private IntParam maxIter;
   private Param featuresCol;

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getK() {
      return LDAParams.getK$(this);
   }

   public double[] getDocConcentration() {
      return LDAParams.getDocConcentration$(this);
   }

   public Vector getOldDocConcentration() {
      return LDAParams.getOldDocConcentration$(this);
   }

   public double getTopicConcentration() {
      return LDAParams.getTopicConcentration$(this);
   }

   public double getOldTopicConcentration() {
      return LDAParams.getOldTopicConcentration$(this);
   }

   public String getOptimizer() {
      return LDAParams.getOptimizer$(this);
   }

   public String getTopicDistributionCol() {
      return LDAParams.getTopicDistributionCol$(this);
   }

   public double getLearningOffset() {
      return LDAParams.getLearningOffset$(this);
   }

   public double getLearningDecay() {
      return LDAParams.getLearningDecay$(this);
   }

   public double getSubsamplingRate() {
      return LDAParams.getSubsamplingRate$(this);
   }

   public boolean getOptimizeDocConcentration() {
      return LDAParams.getOptimizeDocConcentration$(this);
   }

   public boolean getKeepLastCheckpoint() {
      return LDAParams.getKeepLastCheckpoint$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return LDAParams.validateAndTransformSchema$(this, schema);
   }

   public LDAOptimizer getOldOptimizer() {
      return LDAParams.getOldOptimizer$(this);
   }

   public final int getCheckpointInterval() {
      return HasCheckpointInterval.getCheckpointInterval$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final IntParam k() {
      return this.k;
   }

   public final DoubleArrayParam docConcentration() {
      return this.docConcentration;
   }

   public final DoubleParam topicConcentration() {
      return this.topicConcentration;
   }

   public final String[] supportedOptimizers() {
      return this.supportedOptimizers;
   }

   public final Param optimizer() {
      return this.optimizer;
   }

   public final Param topicDistributionCol() {
      return this.topicDistributionCol;
   }

   public final DoubleParam learningOffset() {
      return this.learningOffset;
   }

   public final DoubleParam learningDecay() {
      return this.learningDecay;
   }

   public final DoubleParam subsamplingRate() {
      return this.subsamplingRate;
   }

   public final BooleanParam optimizeDocConcentration() {
      return this.optimizeDocConcentration;
   }

   public final BooleanParam keepLastCheckpoint() {
      return this.keepLastCheckpoint;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$docConcentration_$eq(final DoubleArrayParam x$1) {
      this.docConcentration = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$topicConcentration_$eq(final DoubleParam x$1) {
      this.topicConcentration = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$supportedOptimizers_$eq(final String[] x$1) {
      this.supportedOptimizers = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$optimizer_$eq(final Param x$1) {
      this.optimizer = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$topicDistributionCol_$eq(final Param x$1) {
      this.topicDistributionCol = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$learningOffset_$eq(final DoubleParam x$1) {
      this.learningOffset = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$learningDecay_$eq(final DoubleParam x$1) {
      this.learningDecay = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$subsamplingRate_$eq(final DoubleParam x$1) {
      this.subsamplingRate = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$optimizeDocConcentration_$eq(final BooleanParam x$1) {
      this.optimizeDocConcentration = x$1;
   }

   public final void org$apache$spark$ml$clustering$LDAParams$_setter_$keepLastCheckpoint_$eq(final BooleanParam x$1) {
      this.keepLastCheckpoint = x$1;
   }

   public final IntParam checkpointInterval() {
      return this.checkpointInterval;
   }

   public final void org$apache$spark$ml$param$shared$HasCheckpointInterval$_setter_$checkpointInterval_$eq(final IntParam x$1) {
      this.checkpointInterval = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public int vocabSize() {
      return this.vocabSize;
   }

   public SparkSession sparkSession() {
      return this.sparkSession;
   }

   public abstract org.apache.spark.mllib.clustering.LocalLDAModel oldLocalModel();

   public abstract org.apache.spark.mllib.clustering.LDAModel getModel();

   public double[] getEffectiveDocConcentration() {
      return this.getModel().docConcentration().toArray();
   }

   public double getEffectiveTopicConcentration() {
      return this.getModel().topicConcentration();
   }

   public LDAModel setFeaturesCol(final String value) {
      return (LDAModel)this.set(this.featuresCol(), value);
   }

   public LDAModel setTopicDistributionCol(final String value) {
      return (LDAModel)this.set(this.topicDistributionCol(), value);
   }

   public LDAModel setSeed(final long value) {
      return (LDAModel)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Function1 func = this.getTopicDistributionMethod();
      functions var10000 = .MODULE$;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LDAModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LDAModel.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction transformer = var10000.udf(func, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.topicDistributionCol()), transformer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.columnToVector(dataset, this.getFeaturesCol())}))), outputSchema.apply((String)this.$(this.topicDistributionCol())).metadata());
   }

   private Function1 getTopicDistributionMethod() {
      DenseMatrix expElogbeta = (DenseMatrix)breeze.numerics.package.exp..MODULE$.apply(LDAUtils$.MODULE$.dirichletExpectation((DenseMatrix)this.topicsMatrix().asBreeze().toDenseMatrix$mcD$sp(scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM())).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM()), breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseMatrix..MODULE$.scalarOf(), breeze.numerics.package.exp.expDoubleImpl..MODULE$, breeze.linalg.operators.HasOps..MODULE$.canMapValues_DM$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())));
      org.apache.spark.mllib.clustering.LocalLDAModel oldModel = this.oldLocalModel();
      breeze.linalg.Vector docConcentrationBrz = oldModel.docConcentration().asBreeze();
      double gammaShape = oldModel.gammaShape();
      int k = oldModel.k();
      long gammaSeed = oldModel.seed();
      return (vector) -> {
         if (vector.numNonzeros() == 0) {
            return org.apache.spark.ml.linalg.Vectors..MODULE$.zeros(k);
         } else {
            Tuple2 var10000;
            if (vector instanceof DenseVector) {
               DenseVector var14 = (DenseVector)vector;
               var10000 = new Tuple2(scala.package..MODULE$.List().range(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(var14.size()), scala.math.Numeric.IntIsIntegral..MODULE$), var14.values());
            } else {
               if (!(vector instanceof SparseVector)) {
                  throw new UnsupportedOperationException("Only sparse and dense vectors are supported but got " + vector.getClass() + ".");
               }

               SparseVector var15 = (SparseVector)vector;
               var10000 = new Tuple2(scala.Predef..MODULE$.wrapIntArray(var15.indices()).toList(), var15.values());
            }

            Tuple2 var12 = var10000;
            if (var12 != null) {
               List ids = (List)var12._1();
               double[] cts = (double[])var12._2();
               if (ids != null && cts != null) {
                  Tuple2 var11 = new Tuple2(ids, cts);
                  List idsx = (List)var11._1();
                  double[] ctsx = (double[])var11._2();
                  Tuple3 var23 = OnlineLDAOptimizer$.MODULE$.variationalTopicInference(idsx, ctsx, expElogbeta, docConcentrationBrz, gammaShape, k, gammaSeed);
                  if (var23 != null) {
                     breeze.linalg.DenseVector gamma = (breeze.linalg.DenseVector)var23._1();
                     return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(((breeze.linalg.DenseVector)breeze.linalg.normalize..MODULE$.apply(gamma, BoxesRunTime.boxToDouble((double)1.0F), breeze.linalg.normalize..MODULE$.normalizeDoubleImpl(breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv(), breeze.linalg.norm..MODULE$.canNorm(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues(), breeze.linalg.norm..MODULE$.scalarNorm_Double())))).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()));
                  }

                  throw new MatchError(var23);
               }
            }

            throw new MatchError(var12);
         }
      };
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.topicDistributionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.topicDistributionCol()), this.oldLocalModel().k());
      }

      return outputSchema;
   }

   public Vector estimatedDocConcentration() {
      return VectorImplicits$.MODULE$.mllibVectorToMLVector(this.getModel().docConcentration());
   }

   public Matrix topicsMatrix() {
      return this.oldLocalModel().topicsMatrix().asML();
   }

   public abstract boolean isDistributed();

   public double logLikelihood(final Dataset dataset) {
      RDD oldDataset = LDA$.MODULE$.getOldDataset(dataset, (String)this.$(this.featuresCol()));
      return this.oldLocalModel().logLikelihood(oldDataset);
   }

   public double logPerplexity(final Dataset dataset) {
      RDD oldDataset = LDA$.MODULE$.getOldDataset(dataset, (String)this.$(this.featuresCol()));
      return this.oldLocalModel().logPerplexity(oldDataset);
   }

   public Dataset describeTopics(final int maxTermsPerTopic) {
      Tuple3[] topics = (Tuple3[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.getModel().describeTopics(maxTermsPerTopic)))), (x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var3 = (Tuple2)x0$1._1();
            int topic = x0$1._2$mcI$sp();
            if (var3 != null) {
               int[] termIndices = (int[])var3._1();
               double[] termWeights = (double[])var3._2();
               return new Tuple3(BoxesRunTime.boxToInteger(topic), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(termIndices).toImmutableArraySeq(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(termWeights).toImmutableArraySeq());
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      SparkSession var10000 = this.sparkSession();
      ArraySeq var10001 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(topics).toImmutableArraySeq();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LDAModel.class.getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple3"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection.immutable").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.immutable.ArraySeq"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection.immutable").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.immutable.ArraySeq"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))));
         }

         public $typecreator1$2() {
         }
      }

      return var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"topic", "termIndices", "termWeights"})));
   }

   public Dataset describeTopics() {
      return this.describeTopics(10);
   }

   public LDAModel(final String uid, final int vocabSize, final SparkSession sparkSession) {
      this.uid = uid;
      this.vocabSize = vocabSize;
      this.sparkSession = sparkSession;
      HasFeaturesCol.$init$(this);
      HasMaxIter.$init$(this);
      HasSeed.$init$(this);
      HasCheckpointInterval.$init$(this);
      LDAParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
