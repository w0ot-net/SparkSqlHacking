package org.apache.spark.ml.tuning;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Locale;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.evaluation.Evaluator;
import org.apache.spark.ml.param.IntParam;
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
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tug\u0001B\u0013'\u0001EB\u0001\"\u0011\u0001\u0003\u0006\u0004%\tE\u0011\u0005\t3\u0002\u0011\t\u0011)A\u0005\u0007\"A1\f\u0001BC\u0002\u0013\u0005A\f\u0003\u0005d\u0001\t\u0005\t\u0015!\u0003^\u0011!\u0001\bA!b\u0001\n\u0003\t\b\u0002C>\u0001\u0005\u0003\u0005\u000b\u0011\u0002:\t\ru\u0004A\u0011\u0001\u0015\u007f\u0011\u001di\b\u0001\"\u0001)\u0003'A\u0011\"a\r\u0001\u0001\u0004%I!!\u000e\t\u0013\u00055\u0003\u00011A\u0005\n\u0005=\u0003\u0002CA&\u0001\u0001\u0006K!a\u000e\t\u0011\u0005%\u0004\u0001\"\u0001'\u0003WB\u0001\"!\u001b\u0001\t\u00031\u0013\u0011\u0011\u0005\b\u0003_\u0002A\u0011AAJ\u0011\u001d\tI\u000b\u0001C\u0001\u0003WCq!!.\u0001\t\u0003\n9\fC\u0004\u0002v\u0002!\t%a>\t\u000f\t-\u0001\u0001\"\u0011\u0003\u000e!9!\u0011\u0005\u0001\u0005B\t\r\u0002b\u0002Bi\u0001\u0011\u0005#1[\u0004\b\u0005S1\u0003\u0012\u0001B\u0016\r\u0019)c\u0005#\u0001\u0003.!1QP\u0006C\u0001\u0005\u000fB\u0001B!\u0013\u0017\t\u00031\"1\n\u0005\b\u0005_2B\u0011\tB9\u0011\u001d\u0011yH\u0006C!\u0005\u00033aA!#\u0017\u0005\t-\u0005\"\u0003BJ7\t\u0005\t\u0015!\u00037\u0011\u001di8\u0004\"\u0001'\u0005+CqA!(\u001c\t#\u0012yJ\u0002\u0004\u0003&Z!!q\u0015\u0005\u0007{~!\tA!+\t\u0013\t5vD1A\u0005\n\t=\u0006\u0002\u0003B^?\u0001\u0006IA!-\t\u000f\t}t\u0004\"\u0011\u0003>\"I!\u0011\u0019\f\u0002\u0002\u0013%!1\u0019\u0002\u0014\u0007J|7o\u001d,bY&$\u0017\r^8s\u001b>$W\r\u001c\u0006\u0003O!\na\u0001^;oS:<'BA\u0015+\u0003\tiGN\u0003\u0002,Y\u0005)1\u000f]1sW*\u0011QFL\u0001\u0007CB\f7\r[3\u000b\u0003=\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u001a9wA\u00191\u0007\u000e\u001c\u000e\u0003!J!!\u000e\u0015\u0003\u000b5{G-\u001a7\u0011\u0005]\u0002Q\"\u0001\u0014\u0011\u0005]J\u0014B\u0001\u001e'\u0005Q\u0019%o\\:t-\u0006d\u0017\u000eZ1u_J\u0004\u0016M]1ngB\u0011AhP\u0007\u0002{)\u0011a\bK\u0001\u0005kRLG.\u0003\u0002A{\tQQ\nT,sSR\f'\r\\3\u0002\u0007ULG-F\u0001D!\t!UJ\u0004\u0002F\u0017B\u0011a)S\u0007\u0002\u000f*\u0011\u0001\nM\u0001\u0007yI|w\u000e\u001e \u000b\u0003)\u000bQa]2bY\u0006L!\u0001T%\u0002\rA\u0013X\rZ3g\u0013\tquJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0019&C3!A)X!\t\u0011V+D\u0001T\u0015\t!&&\u0001\u0006b]:|G/\u0019;j_:L!AV*\u0003\u000bMKgnY3\"\u0003a\u000bQ!\r\u00185]A\nA!^5eA!\u001a!!U,\u0002\u0013\t,7\u000f^'pI\u0016dW#A/1\u0005y\u000b\u0007cA\u001a5?B\u0011\u0001-\u0019\u0007\u0001\t%\u0011G!!A\u0001\u0002\u000b\u0005qMA\u0002`I]\n!BY3ti6{G-\u001a7!Q\r!\u0011+Z\u0011\u0002M\u0006)\u0011G\f\u001a/aE\u0011\u0001\u000e\u001c\t\u0003S*l\u0011!S\u0005\u0003W&\u0013qAT8uQ&tw\r\u0005\u0002j[&\u0011a.\u0013\u0002\u0004\u0003:L\bfA\u0002RK\u0006Q\u0011M^4NKR\u0014\u0018nY:\u0016\u0003I\u00042![:v\u0013\t!\u0018JA\u0003BeJ\f\u0017\u0010\u0005\u0002jm&\u0011q/\u0013\u0002\u0007\t>,(\r\\3)\u0007\u0015\t\u00160I\u0001{\u0003\u0015\td&\u000e\u00181\u0003-\tgoZ'fiJL7m\u001d\u0011)\u0007\u0019\t\u00160\u0001\u0004=S:LGO\u0010\u000b\u0007m}\f\u0019!a\u0004\t\u000b\u0005;\u0001\u0019A\")\u0007}\fv\u000b\u0003\u0004\\\u000f\u0001\u0007\u0011Q\u0001\u0019\u0005\u0003\u000f\tY\u0001\u0005\u00034i\u0005%\u0001c\u00011\u0002\f\u0011Q!-a\u0001\u0002\u0002\u0003\u0005)\u0011A4)\t\u0005\r\u0011+\u001a\u0005\u0006a\u001e\u0001\rA\u001d\u0015\u0005\u0003\u001f\t\u0016\u0010F\u00047\u0003+\t9\"a\t\t\u000b\u0005C\u0001\u0019A\"\t\rmC\u0001\u0019AA\ra\u0011\tY\"a\b\u0011\tM\"\u0014Q\u0004\t\u0004A\u0006}AaCA\u0011\u0003/\t\t\u0011!A\u0003\u0002\u001d\u00141a\u0018\u00139\u0011\u0019\u0001\b\u00021\u0001\u0002&A)\u0011qEA\u0018k6\u0011\u0011\u0011\u0006\u0006\u0004}\u0005-\"BAA\u0017\u0003\u0011Q\u0017M^1\n\t\u0005E\u0012\u0011\u0006\u0002\u0005\u0019&\u001cH/\u0001\u0006`gV\u0014Wj\u001c3fYN,\"!a\u000e\u0011\u000b%\fI$!\u0010\n\u0007\u0005m\u0012J\u0001\u0004PaRLwN\u001c\t\u0005SN\fy\u0004\u0005\u0003jg\u0006\u0005\u0003\u0007BA\"\u0003\u000f\u0002Ba\r\u001b\u0002FA\u0019\u0001-a\u0012\u0005\u0015\u0005%3\"!A\u0001\u0002\u000b\u0005qMA\u0002`Ie\n1bX:vE6{G-\u001a7tA\u0005qql];c\u001b>$W\r\\:`I\u0015\fH\u0003BA)\u0003/\u00022![A*\u0013\r\t)&\u0013\u0002\u0005+:LG\u000fC\u0005\u0002Z)\t\t\u00111\u0001\u0002\\\u0005\u0019\u0001\u0010J\u0019\u0011\u000b%\fI$!\u0018\u0011\t%\u001c\u0018q\f\t\u0005SN\f\t\u0007\r\u0003\u0002d\u0005\u001d\u0004\u0003B\u001a5\u0003K\u00022\u0001YA4\t-\tI%a\u0016\u0002\u0002\u0003\u0005)\u0011A4\u0002\u0019M,GoU;c\u001b>$W\r\\:\u0015\u0007Y\ni\u0007C\u0004\u0002p1\u0001\r!!\u001d\u0002\u0013M,(-T8eK2\u001c\b#B5\u0002:\u0005M\u0004\u0003B5t\u0003k\u0002B![:\u0002xA\"\u0011\u0011PA?!\u0011\u0019D'a\u001f\u0011\u0007\u0001\fi\bB\u0006\u0002\u0000\u00055\u0014\u0011!A\u0001\u0006\u00039'\u0001B0%cA\"2ANAB\u0011\u001d\ty'\u0004a\u0001\u0003\u000b\u0003b!a\n\u00020\u0005\u001d\u0005CBA\u0014\u0003_\tI\t\r\u0003\u0002\f\u0006=\u0005\u0003B\u001a5\u0003\u001b\u00032\u0001YAH\t-\t\t*a!\u0002\u0002\u0003\u0005)\u0011A4\u0003\t}#\u0013'M\u000b\u0003\u0003+\u0003B![:\u0002\u0018B!\u0011n]AMa\u0011\tY*a(\u0011\tM\"\u0014Q\u0014\t\u0004A\u0006}EACAQ\u001d\u0005\u0005\t\u0011!B\u0001O\n!q\fJ\u00193Q\u0011q\u0011+!*\"\u0005\u0005\u001d\u0016!\u0002\u001a/g9\u0002\u0014\u0001\u00045bgN+(-T8eK2\u001cXCAAW!\rI\u0017qV\u0005\u0004\u0003cK%a\u0002\"p_2,\u0017M\u001c\u0015\u0005\u001fE\u000b)+A\u0005ue\u0006t7OZ8s[R!\u0011\u0011XAn!\u0011\tY,!6\u000f\t\u0005u\u0016q\u001a\b\u0005\u0003\u007f\u000bYM\u0004\u0003\u0002B\u0006%g\u0002BAb\u0003\u000ft1ARAc\u0013\u0005y\u0013BA\u0017/\u0013\tYC&C\u0002\u0002N*\n1a]9m\u0013\u0011\t\t.a5\u0002\u000fA\f7m[1hK*\u0019\u0011Q\u001a\u0016\n\t\u0005]\u0017\u0011\u001c\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!!5\u0002T\"9\u0011Q\u001c\tA\u0002\u0005}\u0017a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003C\fY\u000f\u0005\u0004\u0002d\u0006\u0015\u0018\u0011^\u0007\u0003\u0003'LA!a:\u0002T\n9A)\u0019;bg\u0016$\bc\u00011\u0002l\u0012Y\u0011Q^An\u0003\u0003\u0005\tQ!\u0001h\u0005\u0011yF%M\u001a)\tA\t\u0016\u0011_\u0011\u0003\u0003g\fQA\r\u00181]A\nq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0005\u0003s\u0014)\u0001\u0005\u0003\u0002|\n\u0005QBAA\u007f\u0015\u0011\ty0a5\u0002\u000bQL\b/Z:\n\t\t\r\u0011Q \u0002\u000b'R\u0014Xo\u0019;UsB,\u0007b\u0002B\u0004#\u0001\u0007\u0011\u0011`\u0001\u0007g\u000eDW-\\1)\u0007E\tv+\u0001\u0003d_BLHc\u0001\u001c\u0003\u0010!9!\u0011\u0003\nA\u0002\tM\u0011!B3yiJ\f\u0007\u0003\u0002B\u000b\u00057i!Aa\u0006\u000b\u0007\te\u0001&A\u0003qCJ\fW.\u0003\u0003\u0003\u001e\t]!\u0001\u0003)be\u0006lW*\u00199)\u0007I\tv+A\u0003xe&$X-\u0006\u0002\u0003&A\u0019!qE\u000e\u000f\u0005]*\u0012aE\"s_N\u001ch+\u00197jI\u0006$xN]'pI\u0016d\u0007CA\u001c\u0017'\u001d1\"q\u0006B\u001b\u0005w\u00012!\u001bB\u0019\u0013\r\u0011\u0019$\u0013\u0002\u0007\u0003:L(+\u001a4\u0011\tq\u00129DN\u0005\u0004\u0005si$AC'M%\u0016\fG-\u00192mKB!!Q\bB\"\u001b\t\u0011yD\u0003\u0003\u0003B\u0005-\u0012AA5p\u0013\u0011\u0011)Ea\u0010\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\t-\u0012!D2paf\u001cVOY'pI\u0016d7\u000f\u0006\u0003\u0003N\tu\u0003#B5\u0002:\t=\u0003\u0003B5t\u0005#\u0002B![:\u0003TA\"!Q\u000bB-!\u0011\u0019DGa\u0016\u0011\u0007\u0001\u0014I\u0006\u0002\u0006\u0003\\a\t\t\u0011!A\u0003\u0002\u001d\u0014Aa\u0018\u00132m!9\u0011q\u000e\rA\u0002\t}\u0003#B5\u0002:\t\u0005\u0004\u0003B5t\u0005G\u0002B![:\u0003fA\"!q\rB6!\u0011\u0019DG!\u001b\u0011\u0007\u0001\u0014Y\u0007B\u0006\u0003n\tu\u0013\u0011!A\u0001\u0006\u00039'\u0001B0%cU\nAA]3bIV\u0011!1\u000f\t\u0005y\tUd'C\u0002\u0003xu\u0012\u0001\"\u0014'SK\u0006$WM\u001d\u0015\u00053E\u0013Y(\t\u0002\u0003~\u0005)\u0011G\f\u001c/a\u0005!An\\1e)\r1$1\u0011\u0005\u0007\u0005\u000bS\u0002\u0019A\"\u0002\tA\fG\u000f\u001b\u0015\u00055E\u0013YHA\rDe>\u001c8OV1mS\u0012\fGo\u001c:N_\u0012,Gn\u0016:ji\u0016\u00148cA\u000e\u0003\u000eB\u0019AHa$\n\u0007\tEUH\u0001\u0005N\u0019^\u0013\u0018\u000e^3s\u0003!Ign\u001d;b]\u000e,G\u0003\u0002BL\u00057\u00032A!'\u001c\u001b\u00051\u0002B\u0002BJ;\u0001\u0007a'\u0001\u0005tCZ,\u0017*\u001c9m)\u0011\t\tF!)\t\r\t\u0015e\u00041\u0001DQ\u0011Y\u0012+!*\u00033\r\u0013xn]:WC2LG-\u0019;pe6{G-\u001a7SK\u0006$WM]\n\u0004?\tMDC\u0001BV!\r\u0011IjH\u0001\nG2\f7o\u001d(b[\u0016,\"A!-\u0011\t\tM&\u0011X\u0007\u0003\u0005kSAAa.\u0002,\u0005!A.\u00198h\u0013\rq%QW\u0001\u000bG2\f7o\u001d(b[\u0016\u0004Cc\u0001\u001c\u0003@\"1!QQ\u0012A\u0002\r\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!2\u0011\t\tM&qY\u0005\u0005\u0005\u0013\u0014)L\u0001\u0004PE*,7\r\u001e\u0015\u0005-E\u0013Y\b\u000b\u0003\u0016#\nm\u0004\u0006B\nR\u0005w\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u0007\"\"A#\u0015BlC\t\u0011I.A\u00034]Ar\u0003\u0007K\u0002\u0001#\u0016\u0004"
)
public class CrossValidatorModel extends Model implements CrossValidatorParams, MLWritable {
   private final String uid;
   private final Model bestModel;
   private final double[] avgMetrics;
   private Option _subModels;
   private IntParam numFolds;
   private Param foldCol;
   private Param estimator;
   private Param estimatorParamMaps;
   private Param evaluator;
   private LongParam seed;

   public static CrossValidatorModel load(final String path) {
      return CrossValidatorModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return CrossValidatorModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getNumFolds() {
      return CrossValidatorParams.getNumFolds$(this);
   }

   public String getFoldCol() {
      return CrossValidatorParams.getFoldCol$(this);
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

   public IntParam numFolds() {
      return this.numFolds;
   }

   public Param foldCol() {
      return this.foldCol;
   }

   public void org$apache$spark$ml$tuning$CrossValidatorParams$_setter_$numFolds_$eq(final IntParam x$1) {
      this.numFolds = x$1;
   }

   public void org$apache$spark$ml$tuning$CrossValidatorParams$_setter_$foldCol_$eq(final Param x$1) {
      this.foldCol = x$1;
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

   public double[] avgMetrics() {
      return this.avgMetrics;
   }

   private Option _subModels() {
      return this._subModels;
   }

   private void _subModels_$eq(final Option x$1) {
      this._subModels = x$1;
   }

   public CrossValidatorModel setSubModels(final Option subModels) {
      this._subModels_$eq(subModels);
      return this;
   }

   public CrossValidatorModel setSubModels(final List subModels) {
      this._subModels_$eq((Option)(subModels != null ? new Some(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(subModels).asScala().toArray(scala.reflect.ClassTag..MODULE$.apply(List.class))), (x$9) -> (Model[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(x$9).asScala().toArray(scala.reflect.ClassTag..MODULE$.apply(Model.class)), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Model.class)))) : scala.None..MODULE$));
      return this;
   }

   public Model[][] subModels() {
      scala.Predef..MODULE$.require(this._subModels().isDefined(), () -> "subModels not available, To retrieve subModels, make sure to set collectSubModels to true before fitting.");
      return (Model[][])this._subModels().get();
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

   public CrossValidatorModel copy(final ParamMap extra) {
      CrossValidatorModel copied = (new CrossValidatorModel(this.uid(), this.bestModel().copy(extra), (double[])this.avgMetrics().clone())).setSubModels(CrossValidatorModel$.MODULE$.copySubModels(this._subModels()));
      return (CrossValidatorModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public CrossValidatorModelWriter write() {
      return new CrossValidatorModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "CrossValidatorModel: uid=" + var10000 + ", bestModel=" + this.bestModel() + ", numFolds=" + this.$(this.numFolds());
   }

   public CrossValidatorModel(final String uid, final Model bestModel, final double[] avgMetrics) {
      this.uid = uid;
      this.bestModel = bestModel;
      this.avgMetrics = avgMetrics;
      HasSeed.$init$(this);
      ValidatorParams.$init$(this);
      CrossValidatorParams.$init$(this);
      MLWritable.$init$(this);
      this._subModels = scala.None..MODULE$;
      Statics.releaseFence();
   }

   public CrossValidatorModel(final String uid, final Model bestModel, final List avgMetrics) {
      this(uid, bestModel, (double[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(avgMetrics).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static final class CrossValidatorModelWriter extends MLWriter {
      private final CrossValidatorModel instance;

      public void saveImpl(final String path) {
         String persistSubModelsParam = (String)this.optionMap().getOrElse("persistsubmodels", () -> this.instance.hasSubModels() ? "true" : "false");
         scala.Predef..MODULE$.require(.MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"true", "false"})), persistSubModelsParam.toLowerCase(Locale.ROOT)), () -> "persistSubModels option value " + persistSubModelsParam + " is invalid, the possible values are \"true\" or \"false\"");
         boolean persistSubModels = scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(persistSubModelsParam));
         JObject extraMetadata = org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("avgMetrics"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.avgMetrics()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$saveImpl$4(BoxesRunTime.unboxToDouble(x)))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("persistSubModels"), BoxesRunTime.boxToBoolean(persistSubModels)), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$saveImpl$6(BoxesRunTime.unboxToDouble(x))), (x) -> $anonfun$saveImpl$7(BoxesRunTime.unboxToBoolean(x)));
         ValidatorParams$.MODULE$.saveImpl(path, this.instance, this.sparkSession(), new Some(extraMetadata));
         String bestModelPath = (new Path(path, "bestModel")).toString();
         ((MLWritable)this.instance.bestModel()).save(bestModelPath);
         if (persistSubModels) {
            scala.Predef..MODULE$.require(this.instance.hasSubModels(), () -> "When persisting tuning models, you can only set persistSubModels to true if the tuning was done with collectSubModels set to true. To save the sub-models, try rerunning fitting with collectSubModels set to true.");
            Path subModelsPath = new Path(path, "subModels");
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.instance.getNumFolds()).foreach$mVc$sp((JFunction1.mcVI.sp)(splitIndex) -> {
               Path splitPath = new Path(subModelsPath, "fold" + Integer.toString(splitIndex));
               .MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(this.instance.getEstimatorParamMaps())).foreach$mVc$sp((JFunction1.mcVI.sp)(paramIndex) -> {
                  String modelPath = (new Path(splitPath, Integer.toString(paramIndex))).toString();
                  ((MLWritable)this.instance.subModels()[splitIndex][paramIndex]).save(modelPath);
               });
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

      public CrossValidatorModelWriter(final CrossValidatorModel instance) {
         this.instance = instance;
         ValidatorParams$.MODULE$.validateParams(instance);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class CrossValidatorModelReader extends MLReader {
      private final String className = CrossValidatorModel.class.getName();

      private String className() {
         return this.className;
      }

      public CrossValidatorModel load(final String path) {
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
            int numFolds = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.params()), "numFolds")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
            String bestModelPath = (new Path(path, "bestModel")).toString();
            Model bestModel = (Model)DefaultParamsReader$.MODULE$.loadParamsInstance(bestModelPath, this.sparkSession());
            double[] avgMetrics = (double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "avgMetrics")), format, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            boolean persistSubModels = BoxesRunTime.unboxToBoolean(org.json4s.ExtractableJsonAstNode..MODULE$.extractOrElse$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "persistSubModels")), (JFunction0.mcZ.sp)() -> false, format, scala.reflect.ManifestFactory..MODULE$.Boolean()));
            Object var10000;
            if (persistSubModels) {
               Path subModelsPath = new Path(path, "subModels");
               Model[][] _subModels = (Model[][])scala.Array..MODULE$.fill(numFolds, () -> (Model[])scala.Array..MODULE$.ofDim(estimatorParamMaps.length, scala.reflect.ClassTag..MODULE$.apply(Model.class)), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Model.class)));
               scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numFolds).foreach$mVc$sp((JFunction1.mcVI.sp)(splitIndex) -> {
                  Path splitPath = new Path(subModelsPath, "fold" + Integer.toString(splitIndex));
                  .MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(estimatorParamMaps)).foreach$mVc$sp((JFunction1.mcVI.sp)(paramIndex) -> {
                     String modelPath = (new Path(splitPath, Integer.toString(paramIndex))).toString();
                     _subModels[splitIndex][paramIndex] = (Model)DefaultParamsReader$.MODULE$.loadParamsInstance(modelPath, this.sparkSession());
                  });
               });
               var10000 = new Some(_subModels);
            } else {
               var10000 = scala.None..MODULE$;
            }

            Option subModels = (Option)var10000;
            CrossValidatorModel model = (new CrossValidatorModel(metadata.uid(), bestModel, avgMetrics)).setSubModels(subModels);
            model.set(model.estimator(), estimator).set((Param)model.evaluator(), evaluator).set((Param)model.estimatorParamMaps(), estimatorParamMaps);
            metadata.getAndSetParams(model, scala.Option..MODULE$.apply(new scala.collection.immutable..colon.colon("estimatorParamMaps", scala.collection.immutable.Nil..MODULE$)));
            return model;
         } else {
            throw new MatchError(var5);
         }
      }

      public CrossValidatorModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
