package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.clustering.KMeansModel;
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
   bytes = "\u0006\u0005\u0005eg!B\u0011#\u0001\tb\u0003\u0002C\u001d\u0001\u0005\u000b\u0007I\u0011A\u001e\t\u0011\u0001\u0003!\u0011!Q\u0001\nqB\u0001\"\u0011\u0001\u0003\u0006\u0004%\tA\u0011\u0005\t#\u0002\u0011\t\u0011)A\u0005\u0007\"A!\u000b\u0001BC\u0002\u0013\u00051\u000b\u0003\u0005Y\u0001\t\u0005\t\u0015!\u0003U\u0011!I\u0006A!b\u0001\n\u0003Q\u0006\u0002\u00030\u0001\u0005\u0003\u0005\u000b\u0011B.\t\u000b}\u0003A\u0011\u00021\t\u000f\u001d\u0004!\u0019!C\u0005Q\"1q\u000e\u0001Q\u0001\n%D\u0001\u0002\u001d\u0001\t\u0006\u0004%\t!\u001d\u0005\tm\u0002A)\u0019!C\u0001o\"A1\u0010\u0001EC\u0002\u0013\u0005A\u0010C\u0005\u0002\u001e\u0001A)\u0019!C\u0001o\"9\u0011q\u0004\u0001\u0005\u0002\u0005\u0005\u0002bBA\u0014\u0001\u0011\u0005\u0011\u0011\u0006\u0005\b\u0003\u001f\u0002A\u0011IA)\u000f!\tIF\tE\u0001E\u0005mcaB\u0011#\u0011\u0003\u0011\u0013Q\f\u0005\u0007?R!\t!!\u001a\t\u000f\u0005\u001dD\u0003\"\u0001\u0002j!9\u0011\u0011\u0012\u000b\u0005B\u0005-\u0005bBAJ)\u0011\u0005\u0013Q\u0013\u0004\u0007\u00037#\u0002!!(\t\u0013\u0005}\u0015D!A!\u0002\u0013\t\u0007BB0\u001a\t\u0003\t\t\u000bC\u0004\u0002*f!\t&a+\u0007\r\u0005UF\u0003AA\\\u0011\u0019yV\u0004\"\u0001\u0002:\"9\u00111S\u000f\u0005B\u0005u\u0006\"CAa)E\u0005I\u0011BAb\u00055YU*Z1og^\u0013\u0018\r\u001d9fe*\u00111\u0005J\u0001\u0002e*\u0011QEJ\u0001\u0003[2T!a\n\u0015\u0002\u000bM\u0004\u0018M]6\u000b\u0005%R\u0013AB1qC\u000eDWMC\u0001,\u0003\ry'oZ\n\u0004\u00015\u001a\u0004C\u0001\u00182\u001b\u0005y#\"\u0001\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iz#AB!osJ+g\r\u0005\u00025o5\tQG\u0003\u00027I\u0005!Q\u000f^5m\u0013\tATG\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f\u0001\u0002]5qK2Lg.Z\u0002\u0001+\u0005a\u0004CA\u001f?\u001b\u0005!\u0013BA %\u00055\u0001\u0016\u000e]3mS:,Wj\u001c3fY\u0006I\u0001/\u001b9fY&tW\rI\u0001\tM\u0016\fG/\u001e:fgV\t1\tE\u0002/\t\u001aK!!R\u0018\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u001dseB\u0001%M!\tIu&D\u0001K\u0015\tY%(\u0001\u0004=e>|GOP\u0005\u0003\u001b>\na\u0001\u0015:fI\u00164\u0017BA(Q\u0005\u0019\u0019FO]5oO*\u0011QjL\u0001\nM\u0016\fG/\u001e:fg\u0002\nAa]5{KV\tA\u000bE\u0002/\tV\u0003\"A\f,\n\u0005]{#\u0001\u0002'p]\u001e\fQa]5{K\u0002\n\u0001\"[:M_\u0006$W\rZ\u000b\u00027B\u0011a\u0006X\u0005\u0003;>\u0012qAQ8pY\u0016\fg.A\u0005jg2{\u0017\rZ3eA\u00051A(\u001b8jiz\"R!Y2eK\u001a\u0004\"A\u0019\u0001\u000e\u0003\tBQ!O\u0005A\u0002qBQ!Q\u0005A\u0002\rCQAU\u0005A\u0002QCq!W\u0005\u0011\u0002\u0003\u00071,A\u0006l\u001b\u0016\fgn]'pI\u0016dW#A5\u0011\u0005)lW\"A6\u000b\u00051$\u0013AC2mkN$XM]5oO&\u0011an\u001b\u0002\f\u00176+\u0017M\\:N_\u0012,G.\u0001\u0007l\u001b\u0016\fgn]'pI\u0016d\u0007%\u0001\u0007d_\u00164g-[2jK:$8/F\u0001s!\rqCi\u001d\t\u0003]QL!!^\u0018\u0003\r\u0011{WO\u00197f\u0003\u0005YW#\u0001=\u0011\u00059J\u0018B\u0001>0\u0005\rIe\u000e^\u0001\bG2,8\u000f^3s+\u0005i\bc\u0001@\u0002\u00189\u0019q0!\u0005\u000f\t\u0005\u0005\u0011Q\u0002\b\u0005\u0003\u0007\tYA\u0004\u0003\u0002\u0006\u0005%abA%\u0002\b%\t1&\u0003\u0002*U%\u0011q\u0005K\u0005\u0004\u0003\u001f1\u0013aA:rY&!\u00111CA\u000b\u0003\u001d\u0001\u0018mY6bO\u0016T1!a\u0004'\u0013\u0011\tI\"a\u0007\u0003\u0013\u0011\u000bG/\u0019$sC6,'\u0002BA\n\u0003+\t1b\u00197vgR,'oU5{K\u00061a-\u001b;uK\u0012$2!`A\u0012\u0011\u0019\t)\u0003\u0005a\u0001\r\u00061Q.\u001a;i_\u0012\f\u0011\u0002\u001e:b]N4wN]7\u0015\u0007u\fY\u0003C\u0004\u0002.E\u0001\r!a\f\u0002\u000f\u0011\fG/Y:fiB\"\u0011\u0011GA\u001f!\u0019\t\u0019$!\u000e\u0002:5\u0011\u0011QC\u0005\u0005\u0003o\t)BA\u0004ECR\f7/\u001a;\u0011\t\u0005m\u0012Q\b\u0007\u0001\t1\ty$a\u000b\u0002\u0002\u0003\u0005)\u0011AA!\u0005\ryF%M\t\u0005\u0003\u0007\nI\u0005E\u0002/\u0003\u000bJ1!a\u00120\u0005\u001dqu\u000e\u001e5j]\u001e\u00042ALA&\u0013\r\tie\f\u0002\u0004\u0003:L\u0018!B<sSR,WCAA*!\r!\u0014QK\u0005\u0004\u0003/*$\u0001C'M/JLG/\u001a:\u0002\u001b-kU-\u00198t/J\f\u0007\u000f]3s!\t\u0011Gc\u0005\u0003\u0015[\u0005}\u0003\u0003\u0002\u001b\u0002b\u0005L1!a\u00196\u0005)iEJU3bI\u0006\u0014G.\u001a\u000b\u0003\u00037\n1AZ5u)E\t\u00171NA8\u0003g\n)(!\u001f\u0002~\u0005\u0005\u0015Q\u0011\u0005\u0007\u0003[2\u0002\u0019A?\u0002\t\u0011\fG/\u0019\u0005\u0007\u0003c2\u0002\u0019\u0001$\u0002\u000f\u0019|'/\\;mC\")aO\u0006a\u0001q\"1\u0011q\u000f\fA\u0002a\fq!\\1y\u0013R,'\u000f\u0003\u0004\u0002|Y\u0001\rAR\u0001\tS:LG/T8eK\"1\u0011q\u0010\fA\u0002\u0019\u000bAa]3fI\"1\u00111\u0011\fA\u0002a\f\u0011\"\u001b8jiN#X\r]:\t\r\u0005\u001de\u00031\u0001t\u0003\r!x\u000e\\\u0001\u0005e\u0016\fG-\u0006\u0002\u0002\u000eB!A'a$b\u0013\r\t\t*\u000e\u0002\t\u001b2\u0013V-\u00193fe\u0006!An\\1e)\r\t\u0017q\u0013\u0005\u0007\u00033C\u0002\u0019\u0001$\u0002\tA\fG\u000f\u001b\u0002\u0014\u00176+\u0017M\\:Xe\u0006\u0004\b/\u001a:Xe&$XM]\n\u00043\u0005M\u0013\u0001C5ogR\fgnY3\u0015\t\u0005\r\u0016q\u0015\t\u0004\u0003KKR\"\u0001\u000b\t\r\u0005}5\u00041\u0001b\u0003!\u0019\u0018M^3J[BdG\u0003BAW\u0003g\u00032ALAX\u0013\r\t\tl\f\u0002\u0005+:LG\u000f\u0003\u0004\u0002\u001ar\u0001\rA\u0012\u0002\u0014\u00176+\u0017M\\:Xe\u0006\u0004\b/\u001a:SK\u0006$WM]\n\u0004;\u00055ECAA^!\r\t)+\b\u000b\u0004C\u0006}\u0006BBAM?\u0001\u0007a)A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003\u000bT3aWAdW\t\tI\r\u0005\u0003\u0002L\u0006UWBAAg\u0015\u0011\ty-!5\u0002\u0013Ut7\r[3dW\u0016$'bAAj_\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005]\u0017Q\u001a\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class KMeansWrapper implements MLWritable {
   private double[] coefficients;
   private int k;
   private Dataset cluster;
   private int clusterSize;
   private final PipelineModel pipeline;
   private final String[] features;
   private final long[] size;
   private final boolean isLoaded;
   private final KMeansModel kMeansModel;
   private volatile byte bitmap$0;

   public static KMeansWrapper load(final String path) {
      return KMeansWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return KMeansWrapper$.MODULE$.read();
   }

   public static KMeansWrapper fit(final Dataset data, final String formula, final int k, final int maxIter, final String initMode, final String seed, final int initSteps, final double tol) {
      return KMeansWrapper$.MODULE$.fit(data, formula, k, maxIter, initMode, seed, initSteps, tol);
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

   private KMeansModel kMeansModel() {
      return this.kMeansModel;
   }

   private double[] coefficients$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.coefficients = (double[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.kMeansModel().clusterCenters()), (x$1) -> x$1.toArray(), (xs) -> scala.Predef..MODULE$.wrapDoubleArray(xs), scala.reflect.ClassTag..MODULE$.Double());
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
            this.k = this.kMeansModel().getK();
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
            this.cluster = this.kMeansModel().summary().cluster();
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

   private int clusterSize$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.clusterSize = this.kMeansModel().clusterCenters().length;
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.clusterSize;
   }

   public int clusterSize() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.clusterSize$lzycompute() : this.clusterSize;
   }

   public Dataset fitted(final String method) {
      String var2 = "centers";
      if (method == null) {
         if (var2 == null) {
            return this.kMeansModel().summary().predictions().drop(this.kMeansModel().getFeaturesCol());
         }
      } else if (method.equals(var2)) {
         return this.kMeansModel().summary().predictions().drop(this.kMeansModel().getFeaturesCol());
      }

      String var3 = "classes";
      if (method == null) {
         if (var3 == null) {
            return this.kMeansModel().summary().cluster();
         }
      } else if (method.equals(var3)) {
         return this.kMeansModel().summary().cluster();
      }

      throw new UnsupportedOperationException("Method (centers or classes) required but " + method + " found.");
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.kMeansModel().getFeaturesCol());
   }

   public MLWriter write() {
      return new KMeansWrapperWriter(this);
   }

   public KMeansWrapper(final PipelineModel pipeline, final String[] features, final long[] size, final boolean isLoaded) {
      this.pipeline = pipeline;
      this.features = features;
      this.size = size;
      this.isLoaded = isLoaded;
      MLWritable.$init$(this);
      this.kMeansModel = (KMeansModel)pipeline.stages()[1];
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class KMeansWrapperWriter extends MLWriter {
      private final KMeansWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("size"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.size()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$saveImpl$6(BoxesRunTime.unboxToLong(x)))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(KMeansWrapperWriter.class.getClassLoader());

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

      public KMeansWrapperWriter(final KMeansWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class KMeansWrapperReader extends MLReader {
      public KMeansWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         long[] size = (long[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "size")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.Long()));
         return new KMeansWrapper(pipeline, features, size, true);
      }
   }
}
