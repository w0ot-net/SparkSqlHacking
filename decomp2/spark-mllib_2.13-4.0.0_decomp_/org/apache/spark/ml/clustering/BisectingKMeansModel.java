package org.apache.spark.ml.clustering;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasDistanceMeasure;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.HasTrainingSummary;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.VectorImplicits$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.Option;
import scala.collection.ArrayOps.;
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
   bytes = "\u0006\u0005\t\u001dd\u0001B\u0012%\u0001=B\u0001\"\u0012\u0001\u0003\u0006\u0004%\tE\u0012\u0005\t;\u0002\u0011\t\u0011)A\u0005\u000f\"Aq\f\u0001BC\u0002\u0013%\u0001\r\u0003\u0005h\u0001\t\u0005\t\u0015!\u0003b\u0011\u0019A\u0007\u0001\"\u0001'S\"1\u0001\u000e\u0001C\u0001M5D\u0001B\u001c\u0001\t\u0006\u0004%\ta\u001c\u0005\u0006o\u0002!\t\u0005\u001f\u0005\b\u0003\u000b\u0001A\u0011AA\u0004\u0011\u001d\t)\u0002\u0001C\u0001\u0003/Aq!!\b\u0001\t\u0003\ny\u0002C\u0004\u0002j\u0001!\t%a\u001b\t\u000f\u0005}\u0004\u0001\"\u0001\u0002\u0002\"9\u0011Q\u0013\u0001\u0005\u0002\u0005]\u0005\u0002CAQ\u0001\u0011\u0005a%a)\t\u000f\u0005-\u0006\u0001\"\u0001\u0002.\"9\u00111\u001b\u0001\u0005B\u0005U\u0007bBAp\u0001\u0011\u0005\u0013\u0011\u001d\u0005\b\u0003K\u0004A\u0011IAt\u000f\u001d\ti\u000f\nE\u0001\u0003_4aa\t\u0013\t\u0002\u0005E\bB\u00025\u0016\t\u0003\u0011y\u0001C\u0004\u0003\u0012U!\tEa\u0005\t\u000f\tuQ\u0003\"\u0011\u0003 \u00199!qE\u000b\u0001+\t%\u0002\"\u0003B\u00163\t\u0005\t\u0015!\u00035\u0011\u0019A\u0017\u0004\"\u0001\u0003.!9!QG\r\u0005R\t]bA\u0002B!+\u0011\u0011\u0019\u0005\u0003\u0004i;\u0011\u0005!Q\t\u0005\n\u0005\u0013j\"\u0019!C\u0005\u0005\u0017B\u0001Ba\u0016\u001eA\u0003%!Q\n\u0005\b\u0005;iB\u0011\tB-\u0011%\u0011i&FA\u0001\n\u0013\u0011yF\u0001\u000bCSN,7\r^5oO.kU-\u00198t\u001b>$W\r\u001c\u0006\u0003K\u0019\n!b\u00197vgR,'/\u001b8h\u0015\t9\u0003&\u0001\u0002nY*\u0011\u0011FK\u0001\u0006gB\f'o\u001b\u0006\u0003W1\na!\u00199bG\",'\"A\u0017\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001\u0001d'O \u0011\u0007E\u0012D'D\u0001'\u0013\t\u0019dEA\u0003N_\u0012,G\u000e\u0005\u00026\u00015\tA\u0005\u0005\u00026o%\u0011\u0001\b\n\u0002\u0016\u0005&\u001cXm\u0019;j]\u001e\\U*Z1ogB\u000b'/Y7t!\tQT(D\u0001<\u0015\tad%\u0001\u0003vi&d\u0017B\u0001 <\u0005)iEj\u0016:ji\u0006\u0014G.\u001a\t\u0004u\u0001\u0013\u0015BA!<\u0005IA\u0015m\u001d+sC&t\u0017N\\4Tk6l\u0017M]=\u0011\u0005U\u001a\u0015B\u0001#%\u0005Y\u0011\u0015n]3di&twmS'fC:\u001c8+^7nCJL\u0018aA;jIV\tq\t\u0005\u0002I#:\u0011\u0011j\u0014\t\u0003\u00156k\u0011a\u0013\u0006\u0003\u0019:\na\u0001\u0010:p_Rt$\"\u0001(\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ak\u0015A\u0002)sK\u0012,g-\u0003\u0002S'\n11\u000b\u001e:j]\u001eT!\u0001U')\u0007\u0005)6\f\u0005\u0002W36\tqK\u0003\u0002YQ\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005i;&!B*j]\u000e,\u0017%\u0001/\u0002\u000bIr\u0003G\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005U[\u0016a\u00039be\u0016tG/T8eK2,\u0012!\u0019\t\u0003E\u001al\u0011a\u0019\u0006\u0003K\u0011T!!\u001a\u0015\u0002\u000b5dG.\u001b2\n\u0005\r\u001a\u0017\u0001\u00049be\u0016tG/T8eK2\u0004\u0013A\u0002\u001fj]&$h\bF\u00025U2DQ!R\u0003A\u0002\u001dC3A[+\\\u0011\u0015yV\u00011\u0001b)\u0005!\u0014a\u00038v[\u001a+\u0017\r^;sKN,\u0012\u0001\u001d\t\u0003cJl\u0011!T\u0005\u0003g6\u00131!\u00138uQ\r9Q+^\u0011\u0002m\u0006)1G\f\u0019/a\u0005!1m\u001c9z)\t!\u0014\u0010C\u0003{\u0011\u0001\u000710A\u0003fqR\u0014\u0018\r\u0005\u0002}\u007f6\tQP\u0003\u0002\u007fM\u0005)\u0001/\u0019:b[&\u0019\u0011\u0011A?\u0003\u0011A\u000b'/Y7NCBD3\u0001C+\\\u00039\u0019X\r\u001e$fCR,(/Z:D_2$B!!\u0003\u0002\f5\t\u0001\u0001\u0003\u0004\u0002\u000e%\u0001\raR\u0001\u0006m\u0006dW/\u001a\u0015\u0005\u0013U\u000b\t\"\t\u0002\u0002\u0014\u0005)!GL\u0019/a\u0005\u00012/\u001a;Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\u001c\u000b\u0005\u0003\u0013\tI\u0002\u0003\u0004\u0002\u000e)\u0001\ra\u0012\u0015\u0005\u0015U\u000b\t\"A\u0005ue\u0006t7OZ8s[R!\u0011\u0011EA\"!\u0011\t\u0019#!\u0010\u000f\t\u0005\u0015\u0012q\u0007\b\u0005\u0003O\t\u0019D\u0004\u0003\u0002*\u0005Eb\u0002BA\u0016\u0003_q1ASA\u0017\u0013\u0005i\u0013BA\u0016-\u0013\tI#&C\u0002\u00026!\n1a]9m\u0013\u0011\tI$a\u000f\u0002\u000fA\f7m[1hK*\u0019\u0011Q\u0007\u0015\n\t\u0005}\u0012\u0011\t\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!!\u000f\u0002<!9\u0011QI\u0006A\u0002\u0005\u001d\u0013a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003\u0013\n)\u0006\u0005\u0004\u0002L\u00055\u0013\u0011K\u0007\u0003\u0003wIA!a\u0014\u0002<\t9A)\u0019;bg\u0016$\b\u0003BA*\u0003+b\u0001\u0001\u0002\u0007\u0002X\u0005\r\u0013\u0011!A\u0001\u0006\u0003\tIFA\u0002`IE\nB!a\u0017\u0002bA\u0019\u0011/!\u0018\n\u0007\u0005}SJA\u0004O_RD\u0017N\\4\u0011\u0007E\f\u0019'C\u0002\u0002f5\u00131!\u00118zQ\rYQkW\u0001\u0010iJ\fgn\u001d4pe6\u001c6\r[3nCR!\u0011QNA=!\u0011\ty'!\u001e\u000e\u0005\u0005E$\u0002BA:\u0003w\tQ\u0001^=qKNLA!a\u001e\u0002r\tQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000f\u0005mD\u00021\u0001\u0002n\u000511o\u00195f[\u0006D3\u0001D+\\\u0003\u001d\u0001(/\u001a3jGR$2\u0001]AB\u0011\u001d\t))\u0004a\u0001\u0003\u000f\u000b\u0001BZ3biV\u0014Xm\u001d\t\u0005\u0003\u0013\u000by)\u0004\u0002\u0002\f*\u0019\u0011Q\u0012\u0014\u0002\r1Lg.\u00197h\u0013\u0011\t\t*a#\u0003\rY+7\r^8sQ\riQ+^\u0001\u000fG2,8\u000f^3s\u0007\u0016tG/\u001a:t+\t\tI\nE\u0003r\u00037\u000b9)C\u0002\u0002\u001e6\u0013Q!\u0011:sCfD3AD+\\\u0003M\u0019G.^:uKJ\u001cUM\u001c;fe6\u000bGO]5y+\t\t)\u000b\u0005\u0003\u0002\n\u0006\u001d\u0016\u0002BAU\u0003\u0017\u0013a!T1ue&D\u0018aC2p[B,H/Z\"pgR$B!a,\u00026B\u0019\u0011/!-\n\u0007\u0005MVJ\u0001\u0004E_V\u0014G.\u001a\u0005\b\u0003\u000b\u0002\u0002\u0019AA\\a\u0011\tI,!0\u0011\r\u0005-\u0013QJA^!\u0011\t\u0019&!0\u0005\u0019\u0005}\u0016QWA\u0001\u0002\u0003\u0015\t!!\u0017\u0003\u0007}##\u0007K\u0002\u0011+nC#\u0002EAc\u0003\u0017\fi-!5v!\r\t\u0018qY\u0005\u0004\u0003\u0013l%A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017EAAh\u0003\u0005%C\u000b[5tA5,G\u000f[8eA%\u001c\b\u0005Z3qe\u0016\u001c\u0017\r^3eA\u0005tG\rI<jY2\u0004#-\u001a\u0011sK6|g/\u001a3!S:\u0004c-\u001e;ve\u0016\u0004c/\u001a:tS>t7O\f\u0011Vg\u0016\u00043\t\\;ti\u0016\u0014\u0018N\\4Fm\u0006dW/\u0019;pe\u0002Jgn\u001d;fC\u0012t\u0003%W8vA\r\fg\u000eI1mg>\u0004s-\u001a;!i\",\u0007eY8ti\u0002zg\u000e\t;iK\u0002\"(/Y5oS:<\u0007\u0005Z1uCN,G\u000fI5oAQDW\rI:v[6\f'/\u001f\u0018\u0002\u000bMLgnY3\u0002\u000b]\u0014\u0018\u000e^3\u0016\u0005\u0005]\u0007c\u0001\u001e\u0002Z&\u0019\u00111\\\u001e\u0003\u00115cuK]5uKJD3!E+\\\u0003!!xn\u0015;sS:<G#A$)\u0007I)V/A\u0004tk6l\u0017M]=\u0016\u0003\tCCaE+\u0002\u0012!\u001a\u0001!V.\u0002)\tK7/Z2uS:<7*T3b]Nlu\u000eZ3m!\t)TcE\u0004\u0016\u0003g\fI0a@\u0011\u0007E\f)0C\u0002\u0002x6\u0013a!\u00118z%\u00164\u0007\u0003\u0002\u001e\u0002|RJ1!!@<\u0005)iEJU3bI\u0006\u0014G.\u001a\t\u0005\u0005\u0003\u0011Y!\u0004\u0002\u0003\u0004)!!Q\u0001B\u0004\u0003\tIwN\u0003\u0002\u0003\n\u0005!!.\u0019<b\u0013\u0011\u0011iAa\u0001\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005=\u0018\u0001\u0002:fC\u0012,\"A!\u0006\u0011\ti\u00129\u0002N\u0005\u0004\u00053Y$\u0001C'M%\u0016\fG-\u001a:)\u0007])6,\u0001\u0003m_\u0006$Gc\u0001\u001b\u0003\"!1!1\u0005\rA\u0002\u001d\u000bA\u0001]1uQ\"\u001a\u0001$V.\u00035\tK7/Z2uS:<7*T3b]Nlu\u000eZ3m/JLG/\u001a:\u0014\u0007e\t9.\u0001\u0005j]N$\u0018M\\2f)\u0011\u0011yCa\r\u0011\u0007\tE\u0012$D\u0001\u0016\u0011\u0019\u0011Yc\u0007a\u0001i\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0003:\t}\u0002cA9\u0003<%\u0019!QH'\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0005Ga\u0002\u0019A$\u00035\tK7/Z2uS:<7*T3b]Nlu\u000eZ3m%\u0016\fG-\u001a:\u0014\u0007u\u0011)\u0002\u0006\u0002\u0003HA\u0019!\u0011G\u000f\u0002\u0013\rd\u0017m]:OC6,WC\u0001B'!\u0011\u0011yE!\u0016\u000e\u0005\tE#\u0002\u0002B*\u0005\u000f\tA\u0001\\1oO&\u0019!K!\u0015\u0002\u0015\rd\u0017m]:OC6,\u0007\u0005F\u00025\u00057BaAa\t\"\u0001\u00049\u0015\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B1!\u0011\u0011yEa\u0019\n\t\t\u0015$\u0011\u000b\u0002\u0007\u001f\nTWm\u0019;"
)
public class BisectingKMeansModel extends Model implements BisectingKMeansParams, MLWritable, HasTrainingSummary {
   private int numFeatures;
   private final String uid;
   private final org.apache.spark.mllib.clustering.BisectingKMeansModel org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel;
   private Option trainingSummary;
   private IntParam k;
   private DoubleParam minDivisibleClusterSize;
   private Param weightCol;
   private Param distanceMeasure;
   private Param predictionCol;
   private LongParam seed;
   private Param featuresCol;
   private IntParam maxIter;
   private volatile boolean bitmap$0;

   public static BisectingKMeansModel load(final String path) {
      return BisectingKMeansModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return BisectingKMeansModel$.MODULE$.read();
   }

   public boolean hasSummary() {
      return HasTrainingSummary.hasSummary$(this);
   }

   public HasTrainingSummary setSummary(final Option summary) {
      return HasTrainingSummary.setSummary$(this, summary);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getK() {
      return BisectingKMeansParams.getK$(this);
   }

   public double getMinDivisibleClusterSize() {
      return BisectingKMeansParams.getMinDivisibleClusterSize$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return BisectingKMeansParams.validateAndTransformSchema$(this, schema);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final String getDistanceMeasure() {
      return HasDistanceMeasure.getDistanceMeasure$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final Option trainingSummary() {
      return this.trainingSummary;
   }

   public final void trainingSummary_$eq(final Option x$1) {
      this.trainingSummary = x$1;
   }

   public final IntParam k() {
      return this.k;
   }

   public final DoubleParam minDivisibleClusterSize() {
      return this.minDivisibleClusterSize;
   }

   public final void org$apache$spark$ml$clustering$BisectingKMeansParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
   }

   public final void org$apache$spark$ml$clustering$BisectingKMeansParams$_setter_$minDivisibleClusterSize_$eq(final DoubleParam x$1) {
      this.minDivisibleClusterSize = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final Param distanceMeasure() {
      return this.distanceMeasure;
   }

   public final void org$apache$spark$ml$param$shared$HasDistanceMeasure$_setter_$distanceMeasure_$eq(final Param x$1) {
      this.distanceMeasure = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public org.apache.spark.mllib.clustering.BisectingKMeansModel org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel() {
      return this.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.numFeatures = ((Vector).MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(this.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel().clusterCenters()))).size();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numFeatures;
   }

   public int numFeatures() {
      return !this.bitmap$0 ? this.numFeatures$lzycompute() : this.numFeatures;
   }

   public BisectingKMeansModel copy(final ParamMap extra) {
      BisectingKMeansModel copied = (BisectingKMeansModel)this.copyValues(new BisectingKMeansModel(this.uid(), this.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel()), extra);
      return (BisectingKMeansModel)((Model)copied.setSummary(this.trainingSummary())).setParent(this.parent());
   }

   public BisectingKMeansModel setFeaturesCol(final String value) {
      return (BisectingKMeansModel)this.set(this.featuresCol(), value);
   }

   public BisectingKMeansModel setPredictionCol(final String value) {
      return (BisectingKMeansModel)this.set(this.predictionCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (vector) -> BoxesRunTime.boxToInteger($anonfun$transform$1(this, vector));
      TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Int();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(BisectingKMeansModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      UserDefinedFunction predictUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
      return dataset.withColumn((String)this.$(this.predictionCol()), predictUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.columnToVector(dataset, this.getFeaturesCol())}))), outputSchema.apply((String)this.$(this.predictionCol())).metadata());
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateNumValues(outputSchema, (String)this.$(this.predictionCol()), this.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel().k());
      }

      return outputSchema;
   }

   public int predict(final org.apache.spark.ml.linalg.Vector features) {
      return this.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel().predict(VectorImplicits$.MODULE$.mlVectorToMLlibVector(features));
   }

   public org.apache.spark.ml.linalg.Vector[] clusterCenters() {
      return (org.apache.spark.ml.linalg.Vector[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel().clusterCenters()), (x$1) -> x$1.asML(), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.ml.linalg.Vector.class));
   }

   public Matrix clusterCenterMatrix() {
      return org.apache.spark.ml.linalg.Matrices..MODULE$.fromVectors(.MODULE$.toSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.clusterCenters())));
   }

   /** @deprecated */
   public double computeCost(final Dataset dataset) {
      SchemaUtils$.MODULE$.validateVectorCompatibleColumn(dataset.schema(), this.getFeaturesCol());
      RDD data = DatasetUtils$.MODULE$.columnToOldVector(dataset, this.getFeaturesCol());
      return this.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel().computeCost(data);
   }

   public MLWriter write() {
      return new BisectingKMeansModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "BisectingKMeansModel: uid=" + var10000 + ", k=" + this.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel().k() + ", distanceMeasure=" + this.$(this.distanceMeasure()) + ", numFeatures=" + this.numFeatures();
   }

   public BisectingKMeansSummary summary() {
      return (BisectingKMeansSummary)HasTrainingSummary.summary$(this);
   }

   // $FF: synthetic method
   public static final int $anonfun$transform$1(final BisectingKMeansModel $this, final org.apache.spark.ml.linalg.Vector vector) {
      return $this.predict(vector);
   }

   public BisectingKMeansModel(final String uid, final org.apache.spark.mllib.clustering.BisectingKMeansModel parentModel) {
      this.uid = uid;
      this.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel = parentModel;
      HasMaxIter.$init$(this);
      HasFeaturesCol.$init$(this);
      HasSeed.$init$(this);
      HasPredictionCol.$init$(this);
      HasDistanceMeasure.$init$(this);
      HasWeightCol.$init$(this);
      BisectingKMeansParams.$init$(this);
      MLWritable.$init$(this);
      HasTrainingSummary.$init$(this);
      Statics.releaseFence();
   }

   public BisectingKMeansModel() {
      this("", (org.apache.spark.mllib.clustering.BisectingKMeansModel)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class BisectingKMeansModelWriter extends MLWriter {
      private final BisectingKMeansModel instance;

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         String dataPath = (new Path(path, "data")).toString();
         this.instance.org$apache$spark$ml$clustering$BisectingKMeansModel$$parentModel().save(this.sc(), dataPath);
      }

      public BisectingKMeansModelWriter(final BisectingKMeansModel instance) {
         this.instance = instance;
      }
   }

   private static class BisectingKMeansModelReader extends MLReader {
      private final String className = BisectingKMeansModel.class.getName();

      private String className() {
         return this.className;
      }

      public BisectingKMeansModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         org.apache.spark.mllib.clustering.BisectingKMeansModel mllibModel = org.apache.spark.mllib.clustering.BisectingKMeansModel$.MODULE$.load(this.sc(), dataPath);
         BisectingKMeansModel model = new BisectingKMeansModel(metadata.uid(), mllibModel);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public BisectingKMeansModelReader() {
      }
   }
}
