package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.clustering.GaussianMixtureModel;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Function1;
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
   bytes = "\u0006\u0005\u00055g!\u0002\u0012$\u0001\rj\u0003\u0002\u0003\u001e\u0001\u0005\u000b\u0007I\u0011\u0001\u001f\t\u0011\u0005\u0003!\u0011!Q\u0001\nuB\u0001B\u0011\u0001\u0003\u0006\u0004%\ta\u0011\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005\t\"A\u0001\n\u0001BC\u0002\u0013\u0005\u0011\n\u0003\u0005N\u0001\t\u0005\t\u0015!\u0003K\u0011!q\u0005A!b\u0001\n\u0003y\u0005\u0002C*\u0001\u0005\u0003\u0005\u000b\u0011\u0002)\t\u000bQ\u0003A\u0011B+\t\u000fq\u0003!\u0019!C\u0005;\"1A\r\u0001Q\u0001\nyC\u0001\"\u001a\u0001\t\u0006\u0004%\ta\u0011\u0005\tM\u0002A)\u0019!C\u0001O\"A1\u000e\u0001EC\u0002\u0013\u0005q\r\u0003\u0005m\u0001!\u0015\r\u0011\"\u0001h\u0011!i\u0007\u0001#b\u0001\n\u0003q\u0007\u0002C<\u0001\u0011\u000b\u0007I\u0011\u0001=\t\u000f\u0005]\u0001\u0001\"\u0001\u0002\u001a!9\u0011q\b\u0001\u0005B\u0005\u0005s\u0001CA%G!\u00051%a\u0013\u0007\u000f\t\u001a\u0003\u0012A\u0012\u0002N!1A+\u0006C\u0001\u0003+Bq!a\u0016\u0016\t\u0003\tI\u0006C\u0004\u0002~U!\t%a \t\u000f\u0005\u001dU\u0003\"\u0011\u0002\n\u001a1\u0011qR\u000b\u0001\u0003#C\u0011\"a%\u001b\u0005\u0003\u0005\u000b\u0011\u0002,\t\rQSB\u0011AAK\u0011\u001d\tiJ\u0007C)\u0003?3a!!+\u0016\u0001\u0005-\u0006B\u0002+\u001f\t\u0003\ti\u000bC\u0004\u0002\bz!\t%!-\t\u0013\u0005UV#%A\u0005\n\u0005]&AF$bkN\u001c\u0018.\u00198NSb$XO]3Xe\u0006\u0004\b/\u001a:\u000b\u0005\u0011*\u0013!\u0001:\u000b\u0005\u0019:\u0013AA7m\u0015\tA\u0013&A\u0003ta\u0006\u00148N\u0003\u0002+W\u00051\u0011\r]1dQ\u0016T\u0011\u0001L\u0001\u0004_J<7c\u0001\u0001/iA\u0011qFM\u0007\u0002a)\t\u0011'A\u0003tG\u0006d\u0017-\u0003\u00024a\t1\u0011I\\=SK\u001a\u0004\"!\u000e\u001d\u000e\u0003YR!aN\u0013\u0002\tU$\u0018\u000e\\\u0005\u0003sY\u0012!\"\u0014'Xe&$\u0018M\u00197f\u0003!\u0001\u0018\u000e]3mS:,7\u0001A\u000b\u0002{A\u0011ahP\u0007\u0002K%\u0011\u0001)\n\u0002\u000e!&\u0004X\r\\5oK6{G-\u001a7\u0002\u0013AL\u0007/\u001a7j]\u0016\u0004\u0013a\u00013j[V\tA\t\u0005\u00020\u000b&\u0011a\t\r\u0002\u0004\u0013:$\u0018\u0001\u00023j[\u0002\nQ\u0002\\8h\u0019&\\W\r\\5i_>$W#\u0001&\u0011\u0005=Z\u0015B\u0001'1\u0005\u0019!u.\u001e2mK\u0006qAn\\4MS.,G.\u001b5p_\u0012\u0004\u0013\u0001C5t\u0019>\fG-\u001a3\u0016\u0003A\u0003\"aL)\n\u0005I\u0003$a\u0002\"p_2,\u0017M\\\u0001\nSNdu.\u00193fI\u0002\na\u0001P5oSRtD#\u0002,Y3j[\u0006CA,\u0001\u001b\u0005\u0019\u0003\"\u0002\u001e\n\u0001\u0004i\u0004\"\u0002\"\n\u0001\u0004!\u0005\"\u0002%\n\u0001\u0004Q\u0005b\u0002(\n!\u0003\u0005\r\u0001U\u0001\u0004O6lW#\u00010\u0011\u0005}\u0013W\"\u00011\u000b\u0005\u0005,\u0013AC2mkN$XM]5oO&\u00111\r\u0019\u0002\u0015\u000f\u0006,8o]5b]6K\u0007\u0010^;sK6{G-\u001a7\u0002\t\u001dlW\u000eI\u0001\u0002W\u00061A.Y7cI\u0006,\u0012\u0001\u001b\t\u0004_%T\u0015B\u000161\u0005\u0015\t%O]1z\u0003\tiW/A\u0003tS\u001el\u0017-A\u0007wK\u000e$xN\u001d+p\u0003J\u0014\u0018-_\u000b\u0002_B\u0011\u0001/^\u0007\u0002c*\u0011!o]\u0001\fKb\u0004(/Z:tS>t7O\u0003\u0002uO\u0005\u00191/\u001d7\n\u0005Y\f(aE+tKJ$UMZ5oK\u00124UO\\2uS>t\u0017!\u00039pgR,'/[8s+\u0005I\bc\u0001>\u0002\u00129\u001910!\u0004\u000f\u0007q\fYAD\u0002~\u0003\u0013q1A`A\u0004\u001d\ry\u0018QA\u0007\u0003\u0003\u0003Q1!a\u0001<\u0003\u0019a$o\\8u}%\tA&\u0003\u0002+W%\u0011\u0001&K\u0005\u0003i\u001eJ1!a\u0004t\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u0005\u0002\u0016\tIA)\u0019;b\rJ\fW.\u001a\u0006\u0004\u0003\u001f\u0019\u0018!\u0003;sC:\u001chm\u001c:n)\rI\u00181\u0004\u0005\b\u0003;\u0011\u0002\u0019AA\u0010\u0003\u001d!\u0017\r^1tKR\u0004D!!\t\u0002.A1\u00111EA\u0013\u0003Si\u0011a]\u0005\u0004\u0003O\u0019(a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003W\ti\u0003\u0004\u0001\u0005\u0019\u0005=\u00121DA\u0001\u0002\u0003\u0015\t!!\r\u0003\u0007}#\u0013'\u0005\u0003\u00024\u0005e\u0002cA\u0018\u00026%\u0019\u0011q\u0007\u0019\u0003\u000f9{G\u000f[5oOB\u0019q&a\u000f\n\u0007\u0005u\u0002GA\u0002B]f\fQa\u001e:ji\u0016,\"!a\u0011\u0011\u0007U\n)%C\u0002\u0002HY\u0012\u0001\"\u0014'Xe&$XM]\u0001\u0017\u000f\u0006,8o]5b]6K\u0007\u0010^;sK^\u0013\u0018\r\u001d9feB\u0011q+F\n\u0005+9\ny\u0005\u0005\u00036\u0003#2\u0016bAA*m\tQQ\n\u0014*fC\u0012\f'\r\\3\u0015\u0005\u0005-\u0013a\u00014jiRYa+a\u0017\u0002`\u0005M\u0014QOA=\u0011\u0019\tif\u0006a\u0001s\u0006!A-\u0019;b\u0011\u001d\t\tg\u0006a\u0001\u0003G\nqAZ8s[Vd\u0017\r\u0005\u0003\u0002f\u00055d\u0002BA4\u0003S\u0002\"a \u0019\n\u0007\u0005-\u0004'\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003_\n\tH\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003W\u0002\u0004\"B3\u0018\u0001\u0004!\u0005BBA</\u0001\u0007A)A\u0004nCbLE/\u001a:\t\r\u0005mt\u00031\u0001K\u0003\r!x\u000e\\\u0001\u0005e\u0016\fG-\u0006\u0002\u0002\u0002B!Q'a!W\u0013\r\t)I\u000e\u0002\t\u001b2\u0013V-\u00193fe\u0006!An\\1e)\r1\u00161\u0012\u0005\b\u0003\u001bK\u0002\u0019AA2\u0003\u0011\u0001\u0018\r\u001e5\u00039\u001d\u000bWo]:jC:l\u0015\u000e\u001f;ve\u0016<&/\u00199qKJ<&/\u001b;feN\u0019!$a\u0011\u0002\u0011%t7\u000f^1oG\u0016$B!a&\u0002\u001cB\u0019\u0011\u0011\u0014\u000e\u000e\u0003UAa!a%\u001d\u0001\u00041\u0016\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\u0005\u0005\u0016q\u0015\t\u0004_\u0005\r\u0016bAASa\t!QK\\5u\u0011\u001d\ti)\ba\u0001\u0003G\u0012AdR1vgNL\u0017M\\'jqR,(/Z,sCB\u0004XM\u001d*fC\u0012,'oE\u0002\u001f\u0003\u0003#\"!a,\u0011\u0007\u0005ee\u0004F\u0002W\u0003gCq!!$!\u0001\u0004\t\u0019'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003sS3\u0001UA^W\t\ti\f\u0005\u0003\u0002@\u0006%WBAAa\u0015\u0011\t\u0019-!2\u0002\u0013Ut7\r[3dW\u0016$'bAAda\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005-\u0017\u0011\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class GaussianMixtureWrapper implements MLWritable {
   private int k;
   private double[] lambda;
   private double[] mu;
   private double[] sigma;
   private UserDefinedFunction vectorToArray;
   private Dataset posterior;
   private final PipelineModel pipeline;
   private final int dim;
   private final double logLikelihood;
   private final boolean isLoaded;
   private final GaussianMixtureModel gmm;
   private volatile byte bitmap$0;

   public static GaussianMixtureWrapper load(final String path) {
      return GaussianMixtureWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GaussianMixtureWrapper$.MODULE$.read();
   }

   public static GaussianMixtureWrapper fit(final Dataset data, final String formula, final int k, final int maxIter, final double tol) {
      return GaussianMixtureWrapper$.MODULE$.fit(data, formula, k, maxIter, tol);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public PipelineModel pipeline() {
      return this.pipeline;
   }

   public int dim() {
      return this.dim;
   }

   public double logLikelihood() {
      return this.logLikelihood;
   }

   public boolean isLoaded() {
      return this.isLoaded;
   }

   private GaussianMixtureModel gmm() {
      return this.gmm;
   }

   private int k$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.k = this.gmm().getK();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.k;
   }

   public int k() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.k$lzycompute() : this.k;
   }

   private double[] lambda$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.lambda = this.gmm().weights();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.lambda;
   }

   public double[] lambda() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.lambda$lzycompute() : this.lambda;
   }

   private double[] mu$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.mu = (double[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.gmm().gaussians()), (x$1) -> x$1.mean().toArray(), (xs) -> scala.Predef..MODULE$.wrapDoubleArray(xs), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.mu;
   }

   public double[] mu() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.mu$lzycompute() : this.mu;
   }

   private double[] sigma$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.sigma = (double[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.gmm().gaussians()), (x$2) -> x$2.cov().toArray(), (xs) -> scala.Predef..MODULE$.wrapDoubleArray(xs), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.sigma;
   }

   public double[] sigma() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.sigma$lzycompute() : this.sigma;
   }

   private UserDefinedFunction vectorToArray$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            functions var10001 = org.apache.spark.sql.functions..MODULE$;
            Function1 var10002 = (probability) -> probability.toArray();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixtureWrapper.class.getClassLoader());
            TypeTags.TypeTag var10003 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator1$1());
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixtureWrapper.class.getClassLoader());
            this.vectorToArray = var10001.udf(var10002, var10003, ((TypeTags)$u).TypeTag().apply((Mirror)$m, null.new $typecreator2$1()));
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.vectorToArray;
   }

   public UserDefinedFunction vectorToArray() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.vectorToArray$lzycompute() : this.vectorToArray;
   }

   private Dataset posterior$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 32) == 0) {
            this.posterior = this.gmm().summary().probability().withColumn("posterior", this.vectorToArray().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(this.gmm().summary().probabilityCol())})))).drop(this.gmm().summary().probabilityCol());
            this.bitmap$0 = (byte)(this.bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.posterior;
   }

   public Dataset posterior() {
      return (byte)(this.bitmap$0 & 32) == 0 ? this.posterior$lzycompute() : this.posterior;
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.gmm().getFeaturesCol());
   }

   public MLWriter write() {
      return new GaussianMixtureWrapperWriter(this);
   }

   public GaussianMixtureWrapper(final PipelineModel pipeline, final int dim, final double logLikelihood, final boolean isLoaded) {
      this.pipeline = pipeline;
      this.dim = dim;
      this.logLikelihood = logLikelihood;
      this.isLoaded = isLoaded;
      MLWritable.$init$(this);
      this.gmm = (GaussianMixtureModel)pipeline.stages()[1];
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public final class $typecreator1$1 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
      }
   }

   public final class $typecreator2$1 extends TypeCreator {
      public Types.TypeApi apply(final Mirror $m$untyped) {
         Universe $u = $m$untyped.universe();
         return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
      }
   }

   public static class GaussianMixtureWrapperWriter extends MLWriter {
      private final GaussianMixtureWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("dim"), BoxesRunTime.boxToInteger(this.instance.dim())), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> $anonfun$saveImpl$3(BoxesRunTime.unboxToInt(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("logLikelihood"), BoxesRunTime.boxToDouble(this.instance.logLikelihood())), (x) -> $anonfun$saveImpl$4(BoxesRunTime.unboxToDouble(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixtureWrapperWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$2() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).write().text(rMetadataPath);
         this.instance.pipeline().save(pipelinePath);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$3(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$4(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      public GaussianMixtureWrapperWriter(final GaussianMixtureWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class GaussianMixtureWrapperReader extends MLReader {
      public GaussianMixtureWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         int dim = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "dim")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
         double logLikelihood = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "logLikelihood")), format, scala.reflect.ManifestFactory..MODULE$.Double()));
         return new GaussianMixtureWrapper(pipeline, dim, logLikelihood, true);
      }
   }
}
