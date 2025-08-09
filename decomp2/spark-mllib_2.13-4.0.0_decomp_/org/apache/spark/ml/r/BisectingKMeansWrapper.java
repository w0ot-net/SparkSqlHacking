package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.clustering.BisectingKMeansModel;
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
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055g!\u0002\u0011\"\u0001\u0005Z\u0003\u0002\u0003\u001d\u0001\u0005\u000b\u0007I\u0011\u0001\u001e\t\u0011}\u0002!\u0011!Q\u0001\nmB\u0001\u0002\u0011\u0001\u0003\u0006\u0004%\t!\u0011\u0005\t!\u0002\u0011\t\u0011)A\u0005\u0005\"A\u0011\u000b\u0001BC\u0002\u0013\u0005!\u000b\u0003\u0005X\u0001\t\u0005\t\u0015!\u0003T\u0011!A\u0006A!b\u0001\n\u0003I\u0006\u0002C/\u0001\u0005\u0003\u0005\u000b\u0011\u0002.\t\u000by\u0003A\u0011B0\t\u000f\u0019\u0004!\u0019!C\u0005O\"1a\u000e\u0001Q\u0001\n!D\u0001b\u001c\u0001\t\u0006\u0004%\t\u0001\u001d\u0005\tk\u0002A)\u0019!C\u0001m\"A!\u0010\u0001EC\u0002\u0013\u00051\u0010C\u0004\u0002\u001c\u0001!\t!!\b\t\u000f\u0005\r\u0002\u0001\"\u0001\u0002&!9\u00111\n\u0001\u0005B\u00055s\u0001CA+C!\u0005\u0011%a\u0016\u0007\u000f\u0001\n\u0003\u0012A\u0011\u0002Z!1al\u0005C\u0001\u0003CBq!a\u0019\u0014\t\u0003\t)\u0007C\u0004\u0002~M!\t%a \t\u000f\u0005\u001d5\u0003\"\u0011\u0002\n\u001a1\u0011qR\n\u0001\u0003#C\u0011\"a%\u0019\u0005\u0003\u0005\u000b\u0011\u00021\t\ryCB\u0011AAK\u0011\u001d\ti\n\u0007C)\u0003?3a!!+\u0014\u0001\u0005-\u0006B\u00020\u001d\t\u0003\ti\u000bC\u0004\u0002\br!\t%!-\t\u0013\u0005U6#%A\u0005\n\u0005]&A\u0006\"jg\u0016\u001cG/\u001b8h\u00176+\u0017M\\:Xe\u0006\u0004\b/\u001a:\u000b\u0005\t\u001a\u0013!\u0001:\u000b\u0005\u0011*\u0013AA7m\u0015\t1s%A\u0003ta\u0006\u00148N\u0003\u0002)S\u00051\u0011\r]1dQ\u0016T\u0011AK\u0001\u0004_J<7c\u0001\u0001-eA\u0011Q\u0006M\u0007\u0002])\tq&A\u0003tG\u0006d\u0017-\u0003\u00022]\t1\u0011I\\=SK\u001a\u0004\"a\r\u001c\u000e\u0003QR!!N\u0012\u0002\tU$\u0018\u000e\\\u0005\u0003oQ\u0012!\"\u0014'Xe&$\u0018M\u00197f\u0003!\u0001\u0018\u000e]3mS:,7\u0001A\u000b\u0002wA\u0011A(P\u0007\u0002G%\u0011ah\t\u0002\u000e!&\u0004X\r\\5oK6{G-\u001a7\u0002\u0013AL\u0007/\u001a7j]\u0016\u0004\u0013\u0001\u00034fCR,(/Z:\u0016\u0003\t\u00032!L\"F\u0013\t!eFA\u0003BeJ\f\u0017\u0010\u0005\u0002G\u001b:\u0011qi\u0013\t\u0003\u0011:j\u0011!\u0013\u0006\u0003\u0015f\na\u0001\u0010:p_Rt\u0014B\u0001'/\u0003\u0019\u0001&/\u001a3fM&\u0011aj\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051s\u0013!\u00034fCR,(/Z:!\u0003\u0011\u0019\u0018N_3\u0016\u0003M\u00032!L\"U!\tiS+\u0003\u0002W]\t!Aj\u001c8h\u0003\u0015\u0019\u0018N_3!\u0003!I7\u000fT8bI\u0016$W#\u0001.\u0011\u00055Z\u0016B\u0001//\u0005\u001d\u0011un\u001c7fC:\f\u0011\"[:M_\u0006$W\r\u001a\u0011\u0002\rqJg.\u001b;?)\u0015\u0001'm\u00193f!\t\t\u0007!D\u0001\"\u0011\u0015A\u0014\u00021\u0001<\u0011\u0015\u0001\u0015\u00021\u0001C\u0011\u0015\t\u0016\u00021\u0001T\u0011\u001dA\u0016\u0002%AA\u0002i\u000bACY5tK\u000e$\u0018N\\4L[\u0016\fgn]'pI\u0016dW#\u00015\u0011\u0005%dW\"\u00016\u000b\u0005-\u001c\u0013AC2mkN$XM]5oO&\u0011QN\u001b\u0002\u0015\u0005&\u001cXm\u0019;j]\u001e\\U*Z1og6{G-\u001a7\u0002+\tL7/Z2uS:<7*\\3b]Nlu\u000eZ3mA\u0005a1m\\3gM&\u001c\u0017.\u001a8ugV\t\u0011\u000fE\u0002.\u0007J\u0004\"!L:\n\u0005Qt#A\u0002#pk\ndW-A\u0001l+\u00059\bCA\u0017y\u0013\tIhFA\u0002J]R\fqa\u00197vgR,'/F\u0001}!\ri\u0018Q\u0003\b\u0004}\u0006=abA@\u0002\f9!\u0011\u0011AA\u0005\u001d\u0011\t\u0019!a\u0002\u000f\u0007!\u000b)!C\u0001+\u0013\tA\u0013&\u0003\u0002'O%\u0019\u0011QB\u0013\u0002\u0007M\fH.\u0003\u0003\u0002\u0012\u0005M\u0011a\u00029bG.\fw-\u001a\u0006\u0004\u0003\u001b)\u0013\u0002BA\f\u00033\u0011\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\t\u0005E\u00111C\u0001\u0007M&$H/\u001a3\u0015\u0007q\fy\u0002\u0003\u0004\u0002\"=\u0001\r!R\u0001\u0007[\u0016$\bn\u001c3\u0002\u0013Q\u0014\u0018M\\:g_JlGc\u0001?\u0002(!9\u0011\u0011\u0006\tA\u0002\u0005-\u0012a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003[\tI\u0004\u0005\u0004\u00020\u0005E\u0012QG\u0007\u0003\u0003'IA!a\r\u0002\u0014\t9A)\u0019;bg\u0016$\b\u0003BA\u001c\u0003sa\u0001\u0001\u0002\u0007\u0002<\u0005\u001d\u0012\u0011!A\u0001\u0006\u0003\tiDA\u0002`IE\nB!a\u0010\u0002FA\u0019Q&!\u0011\n\u0007\u0005\rcFA\u0004O_RD\u0017N\\4\u0011\u00075\n9%C\u0002\u0002J9\u00121!\u00118z\u0003\u00159(/\u001b;f+\t\ty\u0005E\u00024\u0003#J1!a\u00155\u0005!iEj\u0016:ji\u0016\u0014\u0018A\u0006\"jg\u0016\u001cG/\u001b8h\u00176+\u0017M\\:Xe\u0006\u0004\b/\u001a:\u0011\u0005\u0005\u001c2\u0003B\n-\u00037\u0002BaMA/A&\u0019\u0011q\f\u001b\u0003\u00155c%+Z1eC\ndW\r\u0006\u0002\u0002X\u0005\u0019a-\u001b;\u0015\u001b\u0001\f9'a\u001b\u0002p\u0005E\u0014QOA=\u0011\u0019\tI'\u0006a\u0001y\u0006!A-\u0019;b\u0011\u0019\ti'\u0006a\u0001\u000b\u00069am\u001c:nk2\f\u0007\"B;\u0016\u0001\u00049\bBBA:+\u0001\u0007q/A\u0004nCbLE/\u001a:\t\r\u0005]T\u00031\u0001F\u0003\u0011\u0019X-\u001a3\t\r\u0005mT\u00031\u0001s\u0003]i\u0017N\u001c#jm&\u001c\u0018N\u00197f\u00072,8\u000f^3s'&TX-\u0001\u0003sK\u0006$WCAAA!\u0011\u0019\u00141\u00111\n\u0007\u0005\u0015EG\u0001\u0005N\u0019J+\u0017\rZ3s\u0003\u0011aw.\u00193\u0015\u0007\u0001\fY\t\u0003\u0004\u0002\u000e^\u0001\r!R\u0001\u0005a\u0006$\bN\u0001\u000fCSN,7\r^5oO.kU-\u00198t/J\f\u0007\u000f]3s/JLG/\u001a:\u0014\u0007a\ty%\u0001\u0005j]N$\u0018M\\2f)\u0011\t9*a'\u0011\u0007\u0005e\u0005$D\u0001\u0014\u0011\u0019\t\u0019J\u0007a\u0001A\u0006A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0002\"\u0006\u001d\u0006cA\u0017\u0002$&\u0019\u0011Q\u0015\u0018\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003\u001b[\u0002\u0019A#\u00039\tK7/Z2uS:<7*T3b]N<&/\u00199qKJ\u0014V-\u00193feN\u0019A$!!\u0015\u0005\u0005=\u0006cAAM9Q\u0019\u0001-a-\t\r\u00055e\u00041\u0001F\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011\u0011\u0018\u0016\u00045\u0006m6FAA_!\u0011\ty,!3\u000e\u0005\u0005\u0005'\u0002BAb\u0003\u000b\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u001dg&\u0001\u0006b]:|G/\u0019;j_:LA!a3\u0002B\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public class BisectingKMeansWrapper implements MLWritable {
   private double[] coefficients;
   private int k;
   private Dataset cluster;
   private final PipelineModel pipeline;
   private final String[] features;
   private final long[] size;
   private final boolean isLoaded;
   private final BisectingKMeansModel bisectingKmeansModel;
   private volatile byte bitmap$0;

   public static BisectingKMeansWrapper load(final String path) {
      return BisectingKMeansWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return BisectingKMeansWrapper$.MODULE$.read();
   }

   public static BisectingKMeansWrapper fit(final Dataset data, final String formula, final int k, final int maxIter, final String seed, final double minDivisibleClusterSize) {
      return BisectingKMeansWrapper$.MODULE$.fit(data, formula, k, maxIter, seed, minDivisibleClusterSize);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public PipelineModel pipeline() {
      return this.pipeline;
   }

   public String[] features() {
      return this.features;
   }

   public long[] size() {
      return this.size;
   }

   public boolean isLoaded() {
      return this.isLoaded;
   }

   private BisectingKMeansModel bisectingKmeansModel() {
      return this.bisectingKmeansModel;
   }

   private double[] coefficients$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.coefficients = (double[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.bisectingKmeansModel().clusterCenters()), (x$1) -> x$1.toArray(), (xs) -> scala.Predef..MODULE$.wrapDoubleArray(xs), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.coefficients;
   }

   public double[] coefficients() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.coefficients$lzycompute() : this.coefficients;
   }

   private int k$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.k = this.bisectingKmeansModel().getK();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.k;
   }

   public int k() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.k$lzycompute() : this.k;
   }

   private Dataset cluster$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.cluster = this.bisectingKmeansModel().summary().cluster();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.cluster;
   }

   public Dataset cluster() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.cluster$lzycompute() : this.cluster;
   }

   public Dataset fitted(final String method) {
      String var2 = "centers";
      if (method == null) {
         if (var2 == null) {
            return this.bisectingKmeansModel().summary().predictions().drop(this.bisectingKmeansModel().getFeaturesCol());
         }
      } else if (method.equals(var2)) {
         return this.bisectingKmeansModel().summary().predictions().drop(this.bisectingKmeansModel().getFeaturesCol());
      }

      String var3 = "classes";
      if (method == null) {
         if (var3 == null) {
            return this.bisectingKmeansModel().summary().cluster();
         }
      } else if (method.equals(var3)) {
         return this.bisectingKmeansModel().summary().cluster();
      }

      throw new UnsupportedOperationException("Method (centers or classes) required but " + method + " found.");
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.bisectingKmeansModel().getFeaturesCol());
   }

   public MLWriter write() {
      return new BisectingKMeansWrapperWriter(this);
   }

   public BisectingKMeansWrapper(final PipelineModel pipeline, final String[] features, final long[] size, final boolean isLoaded) {
      this.pipeline = pipeline;
      this.features = features;
      this.size = size;
      this.isLoaded = isLoaded;
      MLWritable.$init$(this);
      this.bisectingKmeansModel = (BisectingKMeansModel).MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps(pipeline.stages()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class BisectingKMeansWrapperWriter extends MLWriter {
      private final BisectingKMeansWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("size"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.size()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$saveImpl$6(BoxesRunTime.unboxToLong(x)))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(BisectingKMeansWrapperWriter.class.getClassLoader());

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

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$6(final long x) {
         return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
      }

      public BisectingKMeansWrapperWriter(final BisectingKMeansWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class BisectingKMeansWrapperReader extends MLReader {
      public BisectingKMeansWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         long[] size = (long[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "size")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.Long()));
         return new BisectingKMeansWrapper(pipeline, features, size, true);
      }
   }
}
