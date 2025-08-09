package org.apache.spark.ml.tuning;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Locale;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.evaluation.Evaluator;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rg\u0001B\u0013'\u0001EB\u0001\"\u0011\u0001\u0003\u0006\u0004%\tE\u0011\u0005\t3\u0002\u0011\t\u0011)A\u0005\u0007\"A1\f\u0001BC\u0002\u0013\u0005A\f\u0003\u0005d\u0001\t\u0005\t\u0015!\u0003^\u0011!q\u0007A!b\u0001\n\u0003y\u0007\u0002C<\u0001\u0005\u0003\u0005\u000b\u0011\u00029\t\re\u0004A\u0011\u0001\u0015{\u0011\u001dI\b\u0001\"\u0001)\u0003\u0017A\u0011\"a\u000b\u0001\u0001\u0004%I!!\f\t\u0013\u0005\r\u0003\u00011A\u0005\n\u0005\u0015\u0003\u0002CA!\u0001\u0001\u0006K!a\f\t\u0011\u0005u\u0003\u0001\"\u0001'\u0003?B\u0001\"!\u0018\u0001\t\u00031\u00131\u000f\u0005\b\u0003G\u0002A\u0011AAB\u0011\u001d\t9\n\u0001C\u0001\u00033Cq!a)\u0001\t\u0003\n)\u000bC\u0004\u0002d\u0002!\t%!:\t\u000f\u0005e\b\u0001\"\u0011\u0002|\"9!q\u0002\u0001\u0005B\tE\u0001b\u0002B\\\u0001\u0011\u0005#\u0011X\u0004\b\u0005/1\u0003\u0012\u0001B\r\r\u0019)c\u0005#\u0001\u0003\u001c!1\u0011P\u0006C\u0001\u0005kA\u0001Ba\u000e\u0017\t\u00031\"\u0011\b\u0005\b\u000532B\u0011\tB.\u0011\u001d\u0011)G\u0006C!\u0005O2aAa\u001c\u0017\u0005\tE\u0004\"\u0003B=7\t\u0005\t\u0015!\u00037\u0011\u001dI8\u0004\"\u0001'\u0005wBqAa!\u001c\t#\u0012)I\u0002\u0004\u0003\fZ!!Q\u0012\u0005\u0007s~!\tAa$\t\u0013\tMuD1A\u0005\n\tU\u0005\u0002\u0003BQ?\u0001\u0006IAa&\t\u000f\t\u0015t\u0004\"\u0011\u0003$\"I!q\u0015\f\u0002\u0002\u0013%!\u0011\u0016\u0002\u001a)J\f\u0017N\u001c,bY&$\u0017\r^5p]N\u0003H.\u001b;N_\u0012,GN\u0003\u0002(Q\u00051A/\u001e8j]\u001eT!!\u000b\u0016\u0002\u00055d'BA\u0016-\u0003\u0015\u0019\b/\u0019:l\u0015\tic&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002_\u0005\u0019qN]4\u0004\u0001M!\u0001A\r\u001d<!\r\u0019DGN\u0007\u0002Q%\u0011Q\u0007\u000b\u0002\u0006\u001b>$W\r\u001c\t\u0003o\u0001i\u0011A\n\t\u0003oeJ!A\u000f\u0014\u00035Q\u0013\u0018-\u001b8WC2LG-\u0019;j_:\u001c\u0006\u000f\\5u!\u0006\u0014\u0018-\\:\u0011\u0005qzT\"A\u001f\u000b\u0005yB\u0013\u0001B;uS2L!\u0001Q\u001f\u0003\u00155cuK]5uC\ndW-A\u0002vS\u0012,\u0012a\u0011\t\u0003\t6s!!R&\u0011\u0005\u0019KU\"A$\u000b\u0005!\u0003\u0014A\u0002\u001fs_>$hHC\u0001K\u0003\u0015\u00198-\u00197b\u0013\ta\u0015*\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001d>\u0013aa\u0015;sS:<'B\u0001'JQ\r\t\u0011k\u0016\t\u0003%Vk\u0011a\u0015\u0006\u0003)*\n!\"\u00198o_R\fG/[8o\u0013\t16KA\u0003TS:\u001cW-I\u0001Y\u0003\u0015\td&\u000e\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\t\tv+A\u0005cKN$Xj\u001c3fYV\tQ\f\r\u0002_CB\u00191\u0007N0\u0011\u0005\u0001\fG\u0002\u0001\u0003\nE\u0012\t\t\u0011!A\u0003\u0002\u0015\u00141a\u0018\u00138\u0003)\u0011Wm\u001d;N_\u0012,G\u000e\t\u0015\u0004\tE;\u0016C\u00014k!\t9\u0007.D\u0001J\u0013\tI\u0017JA\u0004O_RD\u0017N\\4\u0011\u0005\u001d\\\u0017B\u00017J\u0005\r\te.\u001f\u0015\u0004\u0007E;\u0016!\u0005<bY&$\u0017\r^5p]6+GO]5dgV\t\u0001\u000fE\u0002hcNL!A]%\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u001d$\u0018BA;J\u0005\u0019!u.\u001e2mK\"\u001aQ!U,\u0002%Y\fG.\u001b3bi&|g.T3ue&\u001c7\u000f\t\u0015\u0004\rE;\u0016A\u0002\u001fj]&$h\bF\u00037wv\f9\u0001C\u0003B\u000f\u0001\u00071\tK\u0002|#^CQaW\u0004A\u0002y\u00044a`A\u0002!\u0011\u0019D'!\u0001\u0011\u0007\u0001\f\u0019\u0001B\u0005c{\u0006\u0005\t\u0011!B\u0001K\"\u001aQ0U,\t\u000b9<\u0001\u0019\u00019)\t\u0005\u001d\u0011k\u0016\u000b\bm\u00055\u0011qBA\u000e\u0011\u0015\t\u0005\u00021\u0001D\u0011\u0019Y\u0006\u00021\u0001\u0002\u0012A\"\u00111CA\f!\u0011\u0019D'!\u0006\u0011\u0007\u0001\f9\u0002B\u0006\u0002\u001a\u0005=\u0011\u0011!A\u0001\u0006\u0003)'aA0%q!1a\u000e\u0003a\u0001\u0003;\u0001R!a\b\u0002(Ml!!!\t\u000b\u0007y\n\u0019C\u0003\u0002\u0002&\u0005!!.\u0019<b\u0013\u0011\tI#!\t\u0003\t1K7\u000f^\u0001\u000b?N,(-T8eK2\u001cXCAA\u0018!\u00159\u0017\u0011GA\u001b\u0013\r\t\u0019$\u0013\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\u001d\f\u0018q\u0007\u0019\u0005\u0003s\ti\u0004\u0005\u00034i\u0005m\u0002c\u00011\u0002>\u0011Q\u0011qH\u0006\u0002\u0002\u0003\u0005)\u0011A3\u0003\u0007}#\u0013(A\u0006`gV\u0014Wj\u001c3fYN\u0004\u0013AD0tk\nlu\u000eZ3mg~#S-\u001d\u000b\u0005\u0003\u000f\ni\u0005E\u0002h\u0003\u0013J1!a\u0013J\u0005\u0011)f.\u001b;\t\u0013\u0005=#\"!AA\u0002\u0005E\u0013a\u0001=%cA)q-!\r\u0002TA!q-]A+a\u0011\t9&a\u0017\u0011\tM\"\u0014\u0011\f\t\u0004A\u0006mCaCA \u0003\u001b\n\t\u0011!A\u0003\u0002\u0015\fAb]3u'V\u0014Wj\u001c3fYN$2ANA1\u0011\u001d\t\u0019\u0007\u0004a\u0001\u0003K\n\u0011b];c\u001b>$W\r\\:\u0011\u000b\u001d\f\t$a\u001a\u0011\t\u001d\f\u0018\u0011\u000e\u0019\u0005\u0003W\ny\u0007\u0005\u00034i\u00055\u0004c\u00011\u0002p\u0011Y\u0011\u0011OA1\u0003\u0003\u0005\tQ!\u0001f\u0005\u0011yF%\r\u0019\u0015\u0007Y\n)\bC\u0004\u0002d5\u0001\r!a\u001e\u0011\r\u0005}\u0011qEA=a\u0011\tY(a \u0011\tM\"\u0014Q\u0010\t\u0004A\u0006}DaCAA\u0003k\n\t\u0011!A\u0003\u0002\u0015\u0014Aa\u0018\u00132cU\u0011\u0011Q\u0011\t\u0005OF\f9\t\r\u0003\u0002\n\u00065\u0005\u0003B\u001a5\u0003\u0017\u00032\u0001YAG\t)\tyIDA\u0001\u0002\u0003\u0015\t!\u001a\u0002\u0005?\u0012\n$\u0007\u000b\u0003\u000f#\u0006M\u0015EAAK\u0003\u0015\u0011df\r\u00181\u00031A\u0017m]*vE6{G-\u001a7t+\t\tY\nE\u0002h\u0003;K1!a(J\u0005\u001d\u0011un\u001c7fC:DCaD)\u0002\u0014\u0006IAO]1og\u001a|'/\u001c\u000b\u0005\u0003O\u000bI\r\u0005\u0003\u0002*\u0006\rg\u0002BAV\u0003{sA!!,\u0002::!\u0011qVA\\\u001d\u0011\t\t,!.\u000f\u0007\u0019\u000b\u0019,C\u00010\u0013\tic&\u0003\u0002,Y%\u0019\u00111\u0018\u0016\u0002\u0007M\fH.\u0003\u0003\u0002@\u0006\u0005\u0017a\u00029bG.\fw-\u001a\u0006\u0004\u0003wS\u0013\u0002BAc\u0003\u000f\u0014\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\t\u0005}\u0016\u0011\u0019\u0005\b\u0003\u0017\u0004\u0002\u0019AAg\u0003\u001d!\u0017\r^1tKR\u0004D!a4\u0002ZB1\u0011\u0011[Aj\u0003/l!!!1\n\t\u0005U\u0017\u0011\u0019\u0002\b\t\u0006$\u0018m]3u!\r\u0001\u0017\u0011\u001c\u0003\f\u00037\fI-!A\u0001\u0002\u000b\u0005QM\u0001\u0003`IE\u001a\u0004\u0006\u0002\tR\u0003?\f#!!9\u0002\u000bIr\u0003G\f\u0019\u0002\u001fQ\u0014\u0018M\\:g_Jl7k\u00195f[\u0006$B!a:\u0002tB!\u0011\u0011^Ax\u001b\t\tYO\u0003\u0003\u0002n\u0006\u0005\u0017!\u0002;za\u0016\u001c\u0018\u0002BAy\u0003W\u0014!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\t)0\u0005a\u0001\u0003O\faa]2iK6\f\u0007fA\tR/\u0006!1m\u001c9z)\r1\u0014Q \u0005\b\u0003\u007f\u0014\u0002\u0019\u0001B\u0001\u0003\u0015)\u0007\u0010\u001e:b!\u0011\u0011\u0019A!\u0003\u000e\u0005\t\u0015!b\u0001B\u0004Q\u0005)\u0001/\u0019:b[&!!1\u0002B\u0003\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\bf\u0001\nR/\u0006)qO]5uKV\u0011!1\u0003\t\u0004\u0005+YbBA\u001c\u0016\u0003e!&/Y5o-\u0006d\u0017\u000eZ1uS>t7\u000b\u001d7ji6{G-\u001a7\u0011\u0005]22c\u0002\f\u0003\u001e\t\r\"\u0011\u0006\t\u0004O\n}\u0011b\u0001B\u0011\u0013\n1\u0011I\\=SK\u001a\u0004B\u0001\u0010B\u0013m%\u0019!qE\u001f\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0003,\tERB\u0001B\u0017\u0015\u0011\u0011y#a\t\u0002\u0005%|\u0017\u0002\u0002B\u001a\u0005[\u0011AbU3sS\u0006d\u0017N_1cY\u0016$\"A!\u0007\u0002\u001b\r|\u0007/_*vE6{G-\u001a7t)\u0011\u0011YD!\u0013\u0011\u000b\u001d\f\tD!\u0010\u0011\t\u001d\f(q\b\u0019\u0005\u0005\u0003\u0012)\u0005\u0005\u00034i\t\r\u0003c\u00011\u0003F\u0011Q!q\t\r\u0002\u0002\u0003\u0005)\u0011A3\u0003\t}#\u0013G\u000e\u0005\b\u0003GB\u0002\u0019\u0001B&!\u00159\u0017\u0011\u0007B'!\u00119\u0017Oa\u00141\t\tE#Q\u000b\t\u0005gQ\u0012\u0019\u0006E\u0002a\u0005+\"1Ba\u0016\u0003J\u0005\u0005\t\u0011!B\u0001K\n!q\fJ\u00196\u0003\u0011\u0011X-\u00193\u0016\u0005\tu\u0003\u0003\u0002\u001f\u0003`YJ1A!\u0019>\u0005!iEJU3bI\u0016\u0014\b\u0006B\rR\u0003?\fA\u0001\\8bIR\u0019aG!\u001b\t\r\t-$\u00041\u0001D\u0003\u0011\u0001\u0018\r\u001e5)\ti\t\u0016q\u001c\u0002 )J\f\u0017N\u001c,bY&$\u0017\r^5p]N\u0003H.\u001b;N_\u0012,Gn\u0016:ji\u0016\u00148cA\u000e\u0003tA\u0019AH!\u001e\n\u0007\t]TH\u0001\u0005N\u0019^\u0013\u0018\u000e^3s\u0003!Ign\u001d;b]\u000e,G\u0003\u0002B?\u0005\u0003\u00032Aa \u001c\u001b\u00051\u0002B\u0002B=;\u0001\u0007a'\u0001\u0005tCZ,\u0017*\u001c9m)\u0011\t9Ea\"\t\r\t-d\u00041\u0001DQ\u0011Y\u0012+a%\u0003?Q\u0013\u0018-\u001b8WC2LG-\u0019;j_:\u001c\u0006\u000f\\5u\u001b>$W\r\u001c*fC\u0012,'oE\u0002 \u0005;\"\"A!%\u0011\u0007\t}t$A\u0005dY\u0006\u001c8OT1nKV\u0011!q\u0013\t\u0005\u00053\u0013y*\u0004\u0002\u0003\u001c*!!QTA\u0012\u0003\u0011a\u0017M\\4\n\u00079\u0013Y*\u0001\u0006dY\u0006\u001c8OT1nK\u0002\"2A\u000eBS\u0011\u0019\u0011Yg\ta\u0001\u0007\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u0016\t\u0005\u00053\u0013i+\u0003\u0003\u00030\nm%AB(cU\u0016\u001cG\u000f\u000b\u0003\u0017#\u0006}\u0007\u0006B\u000bR\u0003?DCaE)\u0002`\u0006AAo\\*ue&tw\rF\u0001DQ\u0011!\u0012K!0\"\u0005\t}\u0016!B\u001a/a9\u0002\u0004f\u0001\u0001R/\u0002"
)
public class TrainValidationSplitModel extends Model implements TrainValidationSplitParams, MLWritable {
   private final String uid;
   private final Model bestModel;
   private final double[] validationMetrics;
   private Option _subModels;
   private DoubleParam trainRatio;
   private Param estimator;
   private Param estimatorParamMaps;
   private Param evaluator;
   private LongParam seed;

   public static TrainValidationSplitModel load(final String path) {
      return TrainValidationSplitModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return TrainValidationSplitModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public double getTrainRatio() {
      return TrainValidationSplitParams.getTrainRatio$(this);
   }

   public Estimator getEstimator() {
      return ValidatorParams.getEstimator$(this);
   }

   public ParamMap[] getEstimatorParamMaps() {
      return ValidatorParams.getEstimatorParamMaps$(this);
   }

   public Evaluator getEvaluator() {
      return ValidatorParams.getEvaluator$(this);
   }

   public StructType transformSchemaImpl(final StructType schema) {
      return ValidatorParams.transformSchemaImpl$(this, schema);
   }

   public void logTuningParams(final Instrumentation instrumentation) {
      ValidatorParams.logTuningParams$(this, instrumentation);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public DoubleParam trainRatio() {
      return this.trainRatio;
   }

   public void org$apache$spark$ml$tuning$TrainValidationSplitParams$_setter_$trainRatio_$eq(final DoubleParam x$1) {
      this.trainRatio = x$1;
   }

   public Param estimator() {
      return this.estimator;
   }

   public Param estimatorParamMaps() {
      return this.estimatorParamMaps;
   }

   public Param evaluator() {
      return this.evaluator;
   }

   public void org$apache$spark$ml$tuning$ValidatorParams$_setter_$estimator_$eq(final Param x$1) {
      this.estimator = x$1;
   }

   public void org$apache$spark$ml$tuning$ValidatorParams$_setter_$estimatorParamMaps_$eq(final Param x$1) {
      this.estimatorParamMaps = x$1;
   }

   public void org$apache$spark$ml$tuning$ValidatorParams$_setter_$evaluator_$eq(final Param x$1) {
      this.evaluator = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public Model bestModel() {
      return this.bestModel;
   }

   public double[] validationMetrics() {
      return this.validationMetrics;
   }

   private Option _subModels() {
      return this._subModels;
   }

   private void _subModels_$eq(final Option x$1) {
      this._subModels = x$1;
   }

   public TrainValidationSplitModel setSubModels(final Option subModels) {
      this._subModels_$eq(subModels);
      return this;
   }

   public TrainValidationSplitModel setSubModels(final List subModels) {
      this._subModels_$eq((Option)(subModels != null ? new Some(.MODULE$.ListHasAsScala(subModels).asScala().toArray(scala.reflect.ClassTag..MODULE$.apply(Model.class))) : scala.None..MODULE$));
      return this;
   }

   public Model[] subModels() {
      scala.Predef..MODULE$.require(this._subModels().isDefined(), () -> "subModels not available, To retrieve subModels, make sure to set collectSubModels to true before fitting.");
      return (Model[])this._subModels().get();
   }

   public boolean hasSubModels() {
      return this._subModels().isDefined();
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      return this.bestModel().transform(dataset);
   }

   public StructType transformSchema(final StructType schema) {
      return this.bestModel().transformSchema(schema);
   }

   public TrainValidationSplitModel copy(final ParamMap extra) {
      TrainValidationSplitModel copied = (new TrainValidationSplitModel(this.uid(), this.bestModel().copy(extra), (double[])this.validationMetrics().clone())).setSubModels(TrainValidationSplitModel$.MODULE$.copySubModels(this._subModels()));
      return (TrainValidationSplitModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public TrainValidationSplitModelWriter write() {
      return new TrainValidationSplitModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "TrainValidationSplitModel: uid=" + var10000 + ", bestModel=" + this.bestModel() + ", trainRatio=" + this.$(this.trainRatio());
   }

   public TrainValidationSplitModel(final String uid, final Model bestModel, final double[] validationMetrics) {
      this.uid = uid;
      this.bestModel = bestModel;
      this.validationMetrics = validationMetrics;
      HasSeed.$init$(this);
      ValidatorParams.$init$(this);
      TrainValidationSplitParams.$init$(this);
      MLWritable.$init$(this);
      this._subModels = scala.None..MODULE$;
      Statics.releaseFence();
   }

   public TrainValidationSplitModel(final String uid, final Model bestModel, final List validationMetrics) {
      this(uid, bestModel, (double[]).MODULE$.ListHasAsScala(validationMetrics).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static final class TrainValidationSplitModelWriter extends MLWriter {
      private final TrainValidationSplitModel instance;

      public void saveImpl(final String path) {
         String persistSubModelsParam = (String)this.optionMap().getOrElse("persistsubmodels", () -> this.instance.hasSubModels() ? "true" : "false");
         scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"true", "false"})), persistSubModelsParam.toLowerCase(Locale.ROOT)), () -> "persistSubModels option value " + persistSubModelsParam + " is invalid, the possible values are \"true\" or \"false\"");
         boolean persistSubModels = scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(persistSubModelsParam));
         JObject extraMetadata = org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("validationMetrics"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.validationMetrics()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$saveImpl$4(BoxesRunTime.unboxToDouble(x)))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("persistSubModels"), BoxesRunTime.boxToBoolean(persistSubModels)), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$saveImpl$6(BoxesRunTime.unboxToDouble(x))), (x) -> $anonfun$saveImpl$7(BoxesRunTime.unboxToBoolean(x)));
         ValidatorParams$.MODULE$.saveImpl(path, this.instance, this.sparkSession(), new Some(extraMetadata));
         String bestModelPath = (new Path(path, "bestModel")).toString();
         ((MLWritable)this.instance.bestModel()).save(bestModelPath);
         if (persistSubModels) {
            scala.Predef..MODULE$.require(this.instance.hasSubModels(), () -> "When persisting tuning models, you can only set persistSubModels to true if the tuning was done with collectSubModels set to true. To save the sub-models, try rerunning fitting with collectSubModels set to true.");
            Path subModelsPath = new Path(path, "subModels");
            scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(this.instance.getEstimatorParamMaps())).foreach$mVc$sp((JFunction1.mcVI.sp)(paramIndex) -> {
               String modelPath = (new Path(subModelsPath, Integer.toString(paramIndex))).toString();
               ((MLWritable)this.instance.subModels()[paramIndex]).save(modelPath);
            });
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$4(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$6(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$7(final boolean x) {
         return org.json4s.JsonDSL..MODULE$.boolean2jvalue(x);
      }

      public TrainValidationSplitModelWriter(final TrainValidationSplitModel instance) {
         this.instance = instance;
         ValidatorParams$.MODULE$.validateParams(instance);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class TrainValidationSplitModelReader extends MLReader {
      private final String className = TrainValidationSplitModel.class.getName();

      private String className() {
         return this.className;
      }

      public TrainValidationSplitModel load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         Tuple4 var5 = ValidatorParams$.MODULE$.loadImpl(path, this.sparkSession(), this.className());
         if (var5 != null) {
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var5._1();
            Estimator estimator = (Estimator)var5._2();
            Evaluator evaluator = (Evaluator)var5._3();
            ParamMap[] estimatorParamMaps = (ParamMap[])var5._4();
            Tuple4 var4 = new Tuple4(metadata, estimator, evaluator, estimatorParamMaps);
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var4._1();
            Estimator estimator = (Estimator)var4._2();
            Evaluator evaluator = (Evaluator)var4._3();
            ParamMap[] estimatorParamMaps = (ParamMap[])var4._4();
            String bestModelPath = (new Path(path, "bestModel")).toString();
            Model bestModel = (Model)DefaultParamsReader$.MODULE$.loadParamsInstance(bestModelPath, this.sparkSession());
            double[] validationMetrics = (double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "validationMetrics")), format, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            boolean persistSubModels = BoxesRunTime.unboxToBoolean(org.json4s.ExtractableJsonAstNode..MODULE$.extractOrElse$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "persistSubModels")), (JFunction0.mcZ.sp)() -> false, format, scala.reflect.ManifestFactory..MODULE$.Boolean()));
            Object var10000;
            if (persistSubModels) {
               Path subModelsPath = new Path(path, "subModels");
               Model[] _subModels = (Model[])scala.Array..MODULE$.ofDim(estimatorParamMaps.length, scala.reflect.ClassTag..MODULE$.apply(Model.class));
               scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(estimatorParamMaps)).foreach$mVc$sp((JFunction1.mcVI.sp)(paramIndex) -> {
                  String modelPath = (new Path(subModelsPath, Integer.toString(paramIndex))).toString();
                  _subModels[paramIndex] = (Model)DefaultParamsReader$.MODULE$.loadParamsInstance(modelPath, this.sparkSession());
               });
               var10000 = new Some(_subModels);
            } else {
               var10000 = scala.None..MODULE$;
            }

            Option subModels = (Option)var10000;
            TrainValidationSplitModel model = (new TrainValidationSplitModel(metadata.uid(), bestModel, validationMetrics)).setSubModels(subModels);
            model.set(model.estimator(), estimator).set((Param)model.evaluator(), evaluator).set((Param)model.estimatorParamMaps(), estimatorParamMaps);
            metadata.getAndSetParams(model, scala.Option..MODULE$.apply(new scala.collection.immutable..colon.colon("estimatorParamMaps", scala.collection.immutable.Nil..MODULE$)));
            return model;
         } else {
            throw new MatchError(var5);
         }
      }

      public TrainValidationSplitModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
