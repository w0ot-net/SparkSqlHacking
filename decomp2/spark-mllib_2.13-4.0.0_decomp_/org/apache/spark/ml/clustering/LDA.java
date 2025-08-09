package org.apache.spark.ml.clustering;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.clustering.LDAOptimizer;
import org.apache.spark.mllib.linalg.VectorImplicits$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tmc\u0001B\u0012%\u0001=B\u0001\"\u0011\u0001\u0003\u0006\u0004%\tE\u0011\u0005\t3\u0002\u0011\t\u0011)A\u0005\u0007\")1\f\u0001C\u00019\")1\f\u0001C\u0001C\")1\r\u0001C\u0001I\")\u0011\u000e\u0001C\u0001U\")\u0011\u000f\u0001C\u0001e\")\u0001\u0010\u0001C\u0001s\")A\u0010\u0001C\u0001{\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r\u0001bBA\u0001\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!a\u000b\u0001\t\u0003\ti\u0003C\u0004\u00024\u0001!\t!!\u000e\t\u000f\u0005m\u0002\u0001\"\u0001\u0002>!9\u00111\t\u0001\u0005\u0002\u0005\u0015\u0003bBA&\u0001\u0011\u0005\u0011Q\n\u0005\b\u00033\u0002A\u0011AA.\u0011\u001d\t)\u0007\u0001C!\u0003OBq!a\u001f\u0001\t\u0003\ni\bC\u0004\u0002*\u0002!\t%a+\b\u000f\u0005\u0005G\u0005#\u0001\u0002D\u001a11\u0005\nE\u0001\u0003\u000bDaa\u0017\r\u0005\u0002\u0005\r\b\u0002CAs1\u0011\u0005A%a:\u0007\r\tm\u0001\u0004\u0002B\u000f\u0011\u0019Y6\u0004\"\u0001\u0003&!I!1F\u000eC\u0002\u0013%!Q\u0006\u0005\t\u0005sY\u0002\u0015!\u0003\u00030!9!1H\u000e\u0005B\tu\u0002b\u0002B\"1\u0011\u0005#Q\t\u0005\b\u0005wAB\u0011\tB$\u0011%\u0011i\u0005GA\u0001\n\u0013\u0011yEA\u0002M\t\u0006S!!\n\u0014\u0002\u0015\rdWo\u001d;fe&twM\u0003\u0002(Q\u0005\u0011Q\u000e\u001c\u0006\u0003S)\nQa\u001d9be.T!a\u000b\u0017\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0013aA8sO\u000e\u00011\u0003\u0002\u00011qm\u00022!\r\u001a5\u001b\u00051\u0013BA\u001a'\u0005%)5\u000f^5nCR|'\u000f\u0005\u00026m5\tA%\u0003\u00028I\tAA\nR!N_\u0012,G\u000e\u0005\u00026s%\u0011!\b\n\u0002\n\u0019\u0012\u000b\u0005+\u0019:b[N\u0004\"\u0001P \u000e\u0003uR!A\u0010\u0014\u0002\tU$\u0018\u000e\\\u0005\u0003\u0001v\u0012Q\u0003R3gCVdG\u000fU1sC6\u001cxK]5uC\ndW-A\u0002vS\u0012,\u0012a\u0011\t\u0003\t6s!!R&\u0011\u0005\u0019KU\"A$\u000b\u0005!s\u0013A\u0002\u001fs_>$hHC\u0001K\u0003\u0015\u00198-\u00197b\u0013\ta\u0015*\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001d>\u0013aa\u0015;sS:<'B\u0001'JQ\r\t\u0011k\u0016\t\u0003%Vk\u0011a\u0015\u0006\u0003)\"\n!\"\u00198o_R\fG/[8o\u0013\t16KA\u0003TS:\u001cW-I\u0001Y\u0003\u0015\tdF\u000e\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\t\tv+\u0001\u0004=S:LGO\u0010\u000b\u0003;z\u0003\"!\u000e\u0001\t\u000b\u0005\u001b\u0001\u0019A\")\u0007y\u000bv\u000bK\u0002\u0004#^#\u0012!\u0018\u0015\u0004\tE;\u0016AD:fi\u001a+\u0017\r^;sKN\u001cu\u000e\u001c\u000b\u0003K\u001al\u0011\u0001\u0001\u0005\u0006O\u0016\u0001\raQ\u0001\u0006m\u0006dW/\u001a\u0015\u0004\u000bE;\u0016AC:fi6\u000b\u00070\u0013;feR\u0011Qm\u001b\u0005\u0006O\u001a\u0001\r\u0001\u001c\t\u0003[:l\u0011!S\u0005\u0003_&\u00131!\u00138uQ\r1\u0011kV\u0001\bg\u0016$8+Z3e)\t)7\u000fC\u0003h\u000f\u0001\u0007A\u000f\u0005\u0002nk&\u0011a/\u0013\u0002\u0005\u0019>tw\rK\u0002\b#^\u000bQc]3u\u0007\",7m\u001b9pS:$\u0018J\u001c;feZ\fG\u000e\u0006\u0002fu\")q\r\u0003a\u0001Y\"\u001a\u0001\"U,\u0002\tM,Go\u0013\u000b\u0003KzDQaZ\u0005A\u00021D3!C)X\u0003M\u0019X\r\u001e#pG\u000e{gnY3oiJ\fG/[8o)\r)\u0017Q\u0001\u0005\u0007O*\u0001\r!a\u0002\u0011\u000b5\fI!!\u0004\n\u0007\u0005-\u0011JA\u0003BeJ\f\u0017\u0010E\u0002n\u0003\u001fI1!!\u0005J\u0005\u0019!u.\u001e2mK\"\u001a!\"U,\u0015\u0007\u0015\f9\u0002\u0003\u0004h\u0017\u0001\u0007\u0011Q\u0002\u0015\u0004\u0017E;\u0016!F:fiR{\u0007/[2D_:\u001cWM\u001c;sCRLwN\u001c\u000b\u0004K\u0006}\u0001BB4\r\u0001\u0004\ti\u0001K\u0002\r#^\u000bAb]3u\u001fB$\u0018.\\5{KJ$2!ZA\u0014\u0011\u00159W\u00021\u0001DQ\ri\u0011kV\u0001\u0018g\u0016$Hk\u001c9jG\u0012K7\u000f\u001e:jEV$\u0018n\u001c8D_2$2!ZA\u0018\u0011\u00159g\u00021\u0001DQ\rq\u0011kV\u0001\u0012g\u0016$H*Z1s]&twm\u00144gg\u0016$HcA3\u00028!1qm\u0004a\u0001\u0003\u001bA3aD)X\u0003A\u0019X\r\u001e'fCJt\u0017N\\4EK\u000e\f\u0017\u0010F\u0002f\u0003\u007fAaa\u001a\tA\u0002\u00055\u0001f\u0001\tR/\u0006\u00112/\u001a;Tk\n\u001c\u0018-\u001c9mS:<'+\u0019;f)\r)\u0017q\t\u0005\u0007OF\u0001\r!!\u0004)\u0007E\tv+A\u000etKR|\u0005\u000f^5nSj,Gi\\2D_:\u001cWM\u001c;sCRLwN\u001c\u000b\u0004K\u0006=\u0003BB4\u0013\u0001\u0004\t\t\u0006E\u0002n\u0003'J1!!\u0016J\u0005\u001d\u0011un\u001c7fC:D3AE)X\u0003U\u0019X\r^&fKBd\u0015m\u001d;DQ\u0016\u001c7\u000e]8j]R$2!ZA/\u0011\u001997\u00031\u0001\u0002R!\"1#UA1C\t\t\u0019'A\u00033]Ar\u0003'\u0001\u0003d_BLHcA/\u0002j!9\u00111\u000e\u000bA\u0002\u00055\u0014!B3yiJ\f\u0007\u0003BA8\u0003kj!!!\u001d\u000b\u0007\u0005Md%A\u0003qCJ\fW.\u0003\u0003\u0002x\u0005E$\u0001\u0003)be\u0006lW*\u00199)\u0007Q\tv+A\u0002gSR$2\u0001NA@\u0011\u001d\t\t)\u0006a\u0001\u0003\u0007\u000bq\u0001Z1uCN,G\u000f\r\u0003\u0002\u0006\u0006U\u0005CBAD\u0003\u001b\u000b\t*\u0004\u0002\u0002\n*\u0019\u00111\u0012\u0015\u0002\u0007M\fH.\u0003\u0003\u0002\u0010\u0006%%a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003'\u000b)\n\u0004\u0001\u0005\u0019\u0005]\u0015qPA\u0001\u0002\u0003\u0015\t!!'\u0003\u0007}#C'\u0005\u0003\u0002\u001c\u0006\u0005\u0006cA7\u0002\u001e&\u0019\u0011qT%\u0003\u000f9{G\u000f[5oOB\u0019Q.a)\n\u0007\u0005\u0015\u0016JA\u0002B]fDC!F)\u0002b\u0005yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002.\u0006e\u0006\u0003BAX\u0003kk!!!-\u000b\t\u0005M\u0016\u0011R\u0001\u0006if\u0004Xm]\u0005\u0005\u0003o\u000b\tL\u0001\u0006TiJ,8\r\u001e+za\u0016Dq!a/\u0017\u0001\u0004\ti+\u0001\u0004tG\",W.\u0019\u0015\u0004-E;\u0006f\u0001\u0001R/\u0006\u0019A\nR!\u0011\u0005UB2c\u0002\r\u0002H\u00065\u00171\u001b\t\u0004[\u0006%\u0017bAAf\u0013\n1\u0011I\\=SK\u001a\u0004B\u0001PAh;&\u0019\u0011\u0011[\u001f\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0002V\u0006}WBAAl\u0015\u0011\tI.a7\u0002\u0005%|'BAAo\u0003\u0011Q\u0017M^1\n\t\u0005\u0005\u0018q\u001b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003\u0007\fQbZ3u\u001f2$G)\u0019;bg\u0016$HCBAu\u0005\u0017\u00119\u0002\u0005\u0004\u0002l\u0006E\u0018Q_\u0007\u0003\u0003[T1!a<)\u0003\r\u0011H\rZ\u0005\u0005\u0003g\fiOA\u0002S\t\u0012\u0003b!\\A|i\u0006m\u0018bAA}\u0013\n1A+\u001e9mKJ\u0002B!!@\u0003\b5\u0011\u0011q \u0006\u0005\u0005\u0003\u0011\u0019!\u0001\u0004mS:\fGn\u001a\u0006\u0004\u0005\u000bA\u0013!B7mY&\u0014\u0017\u0002\u0002B\u0005\u0003\u007f\u0014aAV3di>\u0014\bbBAA5\u0001\u0007!Q\u0002\u0019\u0005\u0005\u001f\u0011\u0019\u0002\u0005\u0004\u0002\b\u00065%\u0011\u0003\t\u0005\u0003'\u0013\u0019\u0002\u0002\u0007\u0003\u0016\t-\u0011\u0011!A\u0001\u0006\u0003\tIJA\u0002`IUBaA!\u0007\u001b\u0001\u0004\u0019\u0015a\u00034fCR,(/Z:D_2\u0014\u0011\u0002\u0014#B%\u0016\fG-\u001a:\u0014\u0007m\u0011y\u0002\u0005\u0003=\u0005Ci\u0016b\u0001B\u0012{\tAQ\n\u0014*fC\u0012,'\u000f\u0006\u0002\u0003(A\u0019!\u0011F\u000e\u000e\u0003a\t\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\t=\u0002\u0003\u0002B\u0019\u0005oi!Aa\r\u000b\t\tU\u00121\\\u0001\u0005Y\u0006tw-C\u0002O\u0005g\t!b\u00197bgNt\u0015-\\3!\u0003\u0011aw.\u00193\u0015\u0007u\u0013y\u0004\u0003\u0004\u0003B}\u0001\raQ\u0001\u0005a\u0006$\b.\u0001\u0003sK\u0006$WC\u0001B\u0010)\ri&\u0011\n\u0005\u0007\u0005\u0003\n\u0003\u0019A\")\t\u0005\n\u0016\u0011M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005#\u0002BA!\r\u0003T%!!Q\u000bB\u001a\u0005\u0019y%M[3di\"\"\u0001$UA1Q\u00119\u0012+!\u0019"
)
public class LDA extends Estimator implements LDAParams, DefaultParamsWritable {
   private final String uid;
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

   public static LDA load(final String path) {
      return LDA$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LDA$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

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

   public LDA setFeaturesCol(final String value) {
      return (LDA)this.set(this.featuresCol(), value);
   }

   public LDA setMaxIter(final int value) {
      return (LDA)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public LDA setSeed(final long value) {
      return (LDA)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public LDA setCheckpointInterval(final int value) {
      return (LDA)this.set(this.checkpointInterval(), BoxesRunTime.boxToInteger(value));
   }

   public LDA setK(final int value) {
      return (LDA)this.set(this.k(), BoxesRunTime.boxToInteger(value));
   }

   public LDA setDocConcentration(final double[] value) {
      return (LDA)this.set(this.docConcentration(), value);
   }

   public LDA setDocConcentration(final double value) {
      return (LDA)this.set(this.docConcentration(), new double[]{value});
   }

   public LDA setTopicConcentration(final double value) {
      return (LDA)this.set(this.topicConcentration(), BoxesRunTime.boxToDouble(value));
   }

   public LDA setOptimizer(final String value) {
      return (LDA)this.set(this.optimizer(), value);
   }

   public LDA setTopicDistributionCol(final String value) {
      return (LDA)this.set(this.topicDistributionCol(), value);
   }

   public LDA setLearningOffset(final double value) {
      return (LDA)this.set(this.learningOffset(), BoxesRunTime.boxToDouble(value));
   }

   public LDA setLearningDecay(final double value) {
      return (LDA)this.set(this.learningDecay(), BoxesRunTime.boxToDouble(value));
   }

   public LDA setSubsamplingRate(final double value) {
      return (LDA)this.set(this.subsamplingRate(), BoxesRunTime.boxToDouble(value));
   }

   public LDA setOptimizeDocConcentration(final boolean value) {
      return (LDA)this.set(this.optimizeDocConcentration(), BoxesRunTime.boxToBoolean(value));
   }

   public LDA setKeepLastCheckpoint(final boolean value) {
      return (LDA)this.set(this.keepLastCheckpoint(), BoxesRunTime.boxToBoolean(value));
   }

   public LDA copy(final ParamMap extra) {
      return (LDA)this.defaultCopy(extra);
   }

   public LDAModel fit(final Dataset dataset) {
      return (LDAModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         RDD oldData;
         boolean var15;
         label47: {
            label46: {
               label45: {
                  this.transformSchema(dataset.schema(), true);
                  instr.logPipelineStage(this);
                  instr.logDataset(dataset);
                  instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.featuresCol(), this.topicDistributionCol(), this.k(), this.maxIter(), this.subsamplingRate(), this.checkpointInterval(), this.keepLastCheckpoint(), this.optimizeDocConcentration(), this.topicConcentration(), this.learningDecay(), this.optimizer(), this.learningOffset(), this.seed()}));
                  oldData = LDA$.MODULE$.getOldDataset(dataset, (String)this.$(this.featuresCol())).setName("training instances");
                  StorageLevel var10000 = dataset.storageLevel();
                  StorageLevel var6 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label45;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label45;
                  }

                  String var14 = this.getOptimizer().toLowerCase(Locale.ROOT);
                  String var7 = "online";
                  if (var14 == null) {
                     if (var7 == null) {
                        break label46;
                     }
                  } else if (var14.equals(var7)) {
                     break label46;
                  }
               }

               var15 = false;
               break label47;
            }

            var15 = true;
         }

         boolean handlePersistence = var15;
         if (handlePersistence) {
            oldData.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         } else {
            BoxedUnit var16 = BoxedUnit.UNIT;
         }

         org.apache.spark.mllib.clustering.LDA oldLDA = (new org.apache.spark.mllib.clustering.LDA()).setK(BoxesRunTime.unboxToInt(this.$(this.k()))).setDocConcentration(VectorImplicits$.MODULE$.mlVectorToMLlibVector(this.getOldDocConcentration())).setTopicConcentration(this.getOldTopicConcentration()).setMaxIterations(BoxesRunTime.unboxToInt(this.$(this.maxIter()))).setSeed(BoxesRunTime.unboxToLong(this.$(this.seed()))).setCheckpointInterval(BoxesRunTime.unboxToInt(this.$(this.checkpointInterval()))).setOptimizer(this.getOldOptimizer());
         org.apache.spark.mllib.clustering.LDAModel oldModel = oldLDA.run(oldData);
         Object var17;
         if (oldModel instanceof org.apache.spark.mllib.clustering.LocalLDAModel var12) {
            var17 = new LocalLDAModel(this.uid(), var12.vocabSize(), var12, dataset.sparkSession());
         } else {
            if (!(oldModel instanceof org.apache.spark.mllib.clustering.DistributedLDAModel)) {
               throw new MatchError(oldModel);
            }

            org.apache.spark.mllib.clustering.DistributedLDAModel var13 = (org.apache.spark.mllib.clustering.DistributedLDAModel)oldModel;
            var17 = new DistributedLDAModel(this.uid(), var13.vocabSize(), var13, dataset.sparkSession(), scala.None..MODULE$);
         }

         LDAModel newModel = (LDAModel)var17;
         if (handlePersistence) {
            oldData.unpersist(oldData.unpersist$default$1());
         } else {
            BoxedUnit var18 = BoxedUnit.UNIT;
         }

         instr.logNumFeatures((long)newModel.vocabSize());
         return (LDAModel)((Model)this.copyValues(newModel, this.copyValues$default$2())).setParent(this);
      });
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public LDA(final String uid) {
      this.uid = uid;
      HasFeaturesCol.$init$(this);
      HasMaxIter.$init$(this);
      HasSeed.$init$(this);
      HasCheckpointInterval.$init$(this);
      LDAParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public LDA() {
      this(Identifiable$.MODULE$.randomUID("lda"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class LDAReader extends MLReader {
      private final String className = LDA.class.getName();

      private String className() {
         return this.className;
      }

      public LDA load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         LDA model = new LDA(metadata.uid());
         LDAParams$.MODULE$.getAndSetParams(model, metadata);
         return model;
      }

      public LDAReader() {
      }
   }
}
