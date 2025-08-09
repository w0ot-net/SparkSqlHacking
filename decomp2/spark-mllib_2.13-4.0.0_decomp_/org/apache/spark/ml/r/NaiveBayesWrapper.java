package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.classification.NaiveBayesModel;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Tuple1;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee!B\u0010!\u0001\u0001R\u0003\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011A\u001d\t\u0011y\u0002!\u0011!Q\u0001\niB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\u001f\u0002\u0011\t\u0011)A\u0005\u0003\"A\u0001\u000b\u0001BC\u0002\u0013\u0005\u0001\t\u0003\u0005R\u0001\t\u0005\t\u0015!\u0003B\u0011\u0015\u0011\u0006\u0001\"\u0003T\u0011\u001dI\u0006A1A\u0005\niCa!\u0019\u0001!\u0002\u0013Y\u0006\u0002\u00032\u0001\u0011\u000b\u0007I\u0011A2\t\u0011!\u0004\u0001R1A\u0005\u0002\rDQ!\u001b\u0001\u0005\u0002)Dq!!\b\u0001\t\u0003\nyb\u0002\u0005\u0002(\u0001B\t\u0001IA\u0015\r\u001dy\u0002\u0005#\u0001!\u0003WAaAU\b\u0005\u0002\u0005M\u0002\"CA\u001b\u001f\t\u0007I\u0011AA\u001c\u0011!\t9e\u0004Q\u0001\n\u0005e\u0002\"CA%\u001f\t\u0007I\u0011AA\u001c\u0011!\tYe\u0004Q\u0001\n\u0005e\u0002bBA'\u001f\u0011\u0005\u0011q\n\u0005\b\u0003CzA\u0011IA2\u0011\u001d\tYg\u0004C!\u0003[2a!a\u001d\u0010\u0001\u0005U\u0004\"CA<1\t\u0005\t\u0015!\u0003U\u0011\u0019\u0011\u0006\u0004\"\u0001\u0002z!9\u0011\u0011\u0011\r\u0005R\u0005\reABAG\u001f\u0001\ty\t\u0003\u0004S9\u0011\u0005\u0011\u0011\u0013\u0005\b\u0003WbB\u0011IAK\u0005Eq\u0015-\u001b<f\u0005\u0006LXm],sCB\u0004XM\u001d\u0006\u0003C\t\n\u0011A\u001d\u0006\u0003G\u0011\n!!\u001c7\u000b\u0005\u00152\u0013!B:qCJ\\'BA\u0014)\u0003\u0019\t\u0007/Y2iK*\t\u0011&A\u0002pe\u001e\u001c2\u0001A\u00162!\tas&D\u0001.\u0015\u0005q\u0013!B:dC2\f\u0017B\u0001\u0019.\u0005\u0019\te.\u001f*fMB\u0011!'N\u0007\u0002g)\u0011AGI\u0001\u0005kRLG.\u0003\u00027g\tQQ\nT,sSR\f'\r\\3\u0002\u0011AL\u0007/\u001a7j]\u0016\u001c\u0001!F\u0001;!\tYD(D\u0001#\u0013\ti$EA\u0007QSB,G.\u001b8f\u001b>$W\r\\\u0001\na&\u0004X\r\\5oK\u0002\na\u0001\\1cK2\u001cX#A!\u0011\u00071\u0012E)\u0003\u0002D[\t)\u0011I\u001d:bsB\u0011Q\t\u0014\b\u0003\r*\u0003\"aR\u0017\u000e\u0003!S!!\u0013\u001d\u0002\rq\u0012xn\u001c;?\u0013\tYU&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001b:\u0013aa\u0015;sS:<'BA&.\u0003\u001da\u0017MY3mg\u0002\n\u0001BZ3biV\u0014Xm]\u0001\nM\u0016\fG/\u001e:fg\u0002\na\u0001P5oSRtD\u0003\u0002+W/b\u0003\"!\u0016\u0001\u000e\u0003\u0001BQaN\u0004A\u0002iBQaP\u0004A\u0002\u0005CQ\u0001U\u0004A\u0002\u0005\u000bqB\\1jm\u0016\u0014\u0015-_3t\u001b>$W\r\\\u000b\u00027B\u0011AlX\u0007\u0002;*\u0011aLI\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0013\t\u0001WLA\bOC&4XMQ1zKNlu\u000eZ3m\u0003Aq\u0017-\u001b<f\u0005\u0006LXm]'pI\u0016d\u0007%A\u0004baJLwN]5\u0016\u0003\u0011\u00042\u0001\f\"f!\tac-\u0003\u0002h[\t1Ai\\;cY\u0016\fa\u0001^1cY\u0016\u001c\u0018!\u0003;sC:\u001chm\u001c:n)\tYG\u0010\u0005\u0002ms:\u0011QN\u001e\b\u0003]Rt!a\\:\u000f\u0005A\u0014hBA$r\u0013\u0005I\u0013BA\u0014)\u0013\t)c%\u0003\u0002vI\u0005\u00191/\u001d7\n\u0005]D\u0018a\u00029bG.\fw-\u001a\u0006\u0003k\u0012J!A_>\u0003\u0013\u0011\u000bG/\u0019$sC6,'BA<y\u0011\u0015iH\u00021\u0001\u007f\u0003\u001d!\u0017\r^1tKR\u00044a`A\u0006!\u0019\t\t!a\u0001\u0002\b5\t\u00010C\u0002\u0002\u0006a\u0014q\u0001R1uCN,G\u000f\u0005\u0003\u0002\n\u0005-A\u0002\u0001\u0003\f\u0003\u001ba\u0018\u0011!A\u0001\u0006\u0003\tyAA\u0002`IE\nB!!\u0005\u0002\u0018A\u0019A&a\u0005\n\u0007\u0005UQFA\u0004O_RD\u0017N\\4\u0011\u00071\nI\"C\u0002\u0002\u001c5\u00121!\u00118z\u0003\u00159(/\u001b;f+\t\t\t\u0003E\u00023\u0003GI1!!\n4\u0005!iEj\u0016:ji\u0016\u0014\u0018!\u0005(bSZ,')Y=fg^\u0013\u0018\r\u001d9feB\u0011QkD\n\u0005\u001f-\ni\u0003\u0005\u00033\u0003_!\u0016bAA\u0019g\tQQ\n\u0014*fC\u0012\f'\r\\3\u0015\u0005\u0005%\u0012!\u0007)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0013:#U\tW0D\u001f2+\"!!\u000f\u0011\t\u0005m\u0012QI\u0007\u0003\u0003{QA!a\u0010\u0002B\u0005!A.\u00198h\u0015\t\t\u0019%\u0001\u0003kCZ\f\u0017bA'\u0002>\u0005Q\u0002KU#E\u0013\u000e#V\tR0M\u0003\n+EjX%O\t\u0016CvlQ(MA\u0005\u0019\u0002KU#E\u0013\u000e#V\tR0M\u0003\n+EjX\"P\u0019\u0006!\u0002KU#E\u0013\u000e#V\tR0M\u0003\n+EjX\"P\u0019\u0002\n1AZ5u)%!\u0016\u0011KA+\u00033\ni\u0006\u0003\u0004\u0002TU\u0001\r\u0001R\u0001\bM>\u0014X.\u001e7b\u0011\u0019\t9&\u0006a\u0001W\u0006!A-\u0019;b\u0011\u0019\tY&\u0006a\u0001K\u0006I1/\\8pi\"Lgn\u001a\u0005\u0007\u0003?*\u0002\u0019\u0001#\u0002\u001b!\fg\u000e\u001a7f\u0013:4\u0018\r\\5e\u0003\u0011\u0011X-\u00193\u0016\u0005\u0005\u0015\u0004\u0003\u0002\u001a\u0002hQK1!!\u001b4\u0005!iEJU3bI\u0016\u0014\u0018\u0001\u00027pC\u0012$2\u0001VA8\u0011\u0019\t\th\u0006a\u0001\t\u0006!\u0001/\u0019;i\u0005]q\u0015-\u001b<f\u0005\u0006LXm],sCB\u0004XM],sSR,'oE\u0002\u0019\u0003C\t\u0001\"\u001b8ti\u0006t7-\u001a\u000b\u0005\u0003w\ny\bE\u0002\u0002~ai\u0011a\u0004\u0005\u0007\u0003oR\u0002\u0019\u0001+\u0002\u0011M\fg/Z%na2$B!!\"\u0002\fB\u0019A&a\"\n\u0007\u0005%UF\u0001\u0003V]&$\bBBA97\u0001\u0007AIA\fOC&4XMQ1zKN<&/\u00199qKJ\u0014V-\u00193feN\u0019A$!\u001a\u0015\u0005\u0005M\u0005cAA?9Q\u0019A+a&\t\r\u0005Ed\u00041\u0001E\u0001"
)
public class NaiveBayesWrapper implements MLWritable {
   private double[] apriori;
   private double[] tables;
   private final PipelineModel pipeline;
   private final String[] labels;
   private final String[] features;
   private final NaiveBayesModel naiveBayesModel;
   private volatile byte bitmap$0;

   public static NaiveBayesWrapper load(final String path) {
      return NaiveBayesWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return NaiveBayesWrapper$.MODULE$.read();
   }

   public static NaiveBayesWrapper fit(final String formula, final Dataset data, final double smoothing, final String handleInvalid) {
      return NaiveBayesWrapper$.MODULE$.fit(formula, data, smoothing, handleInvalid);
   }

   public static String PREDICTED_LABEL_COL() {
      return NaiveBayesWrapper$.MODULE$.PREDICTED_LABEL_COL();
   }

   public static String PREDICTED_LABEL_INDEX_COL() {
      return NaiveBayesWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public PipelineModel pipeline() {
      return this.pipeline;
   }

   public String[] labels() {
      return this.labels;
   }

   public String[] features() {
      return this.features;
   }

   private NaiveBayesModel naiveBayesModel() {
      return this.naiveBayesModel;
   }

   private double[] apriori$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.apriori = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.naiveBayesModel().pi().toArray()), (JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.exp(x), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.apriori;
   }

   public double[] apriori() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.apriori$lzycompute() : this.apriori;
   }

   private double[] tables$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.tables = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.naiveBayesModel().theta().toArray()), (JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.exp(x), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.tables;
   }

   public double[] tables() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.tables$lzycompute() : this.tables;
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(NaiveBayesWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL()).drop(this.naiveBayesModel().getFeaturesCol()).drop(this.naiveBayesModel().getLabelCol());
   }

   public MLWriter write() {
      return new NaiveBayesWrapperWriter(this);
   }

   public NaiveBayesWrapper(final PipelineModel pipeline, final String[] labels, final String[] features) {
      this.pipeline = pipeline;
      this.labels = labels;
      this.features = features;
      MLWritable.$init$(this);
      this.naiveBayesModel = (NaiveBayesModel)pipeline.stages()[1];
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class NaiveBayesWrapperWriter extends MLWriter {
      private final NaiveBayesWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("labels"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.labels()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(NaiveBayesWrapperWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(rMetadataPath);
         this.instance.pipeline().save(pipelinePath);
      }

      public NaiveBayesWrapperWriter(final NaiveBayesWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class NaiveBayesWrapperReader extends MLReader {
      public NaiveBayesWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] labels = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "labels")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new NaiveBayesWrapper(pipeline, labels, features);
      }
   }
}
