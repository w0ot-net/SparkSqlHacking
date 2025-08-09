package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.regression.IsotonicRegressionModel;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.JsonAssoc.;
import scala.Tuple1;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d!B\r\u001b\u0001i!\u0003\u0002C\u0019\u0001\u0005\u000b\u0007I\u0011A\u001a\t\u0011a\u0002!\u0011!Q\u0001\nQB\u0001\"\u000f\u0001\u0003\u0006\u0004%\tA\u000f\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005w!)!\n\u0001C\u0005\u0017\"9\u0001\u000b\u0001b\u0001\n\u0013\t\u0006B\u0002-\u0001A\u0003%!\u000b\u0003\u0005Z\u0001!\u0015\r\u0011\"\u0001[\u0011!y\u0006\u0001#b\u0001\n\u0003Q\u0006\"\u00021\u0001\t\u0003\t\u0007bBA\u0006\u0001\u0011\u0005\u0013QB\u0004\t\u0003+Q\u0002\u0012\u0001\u000e\u0002\u0018\u00199\u0011D\u0007E\u00015\u0005e\u0001B\u0002&\u000e\t\u0003\t\t\u0003C\u0004\u0002$5!\t!!\n\t\u000f\u0005\u001dS\u0002\"\u0011\u0002J!9\u0011\u0011K\u0007\u0005B\u0005McABA-\u001b\u0001\tY\u0006C\u0005\u0002^I\u0011\t\u0011)A\u0005\u0019\"1!J\u0005C\u0001\u0003?Bq!a\u001a\u0013\t#\nIG\u0002\u0004\u0002t5\u0001\u0011Q\u000f\u0005\u0007\u0015Z!\t!a\u001e\t\u000f\u0005Ec\u0003\"\u0011\u0002|\tI\u0012j]8u_:L7MU3he\u0016\u001c8/[8o/J\f\u0007\u000f]3s\u0015\tYB$A\u0001s\u0015\tib$\u0001\u0002nY*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xmE\u0002\u0001K-\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0007C\u0001\u00170\u001b\u0005i#B\u0001\u0018\u001d\u0003\u0011)H/\u001b7\n\u0005Aj#AC'M/JLG/\u00192mK\u0006A\u0001/\u001b9fY&tWm\u0001\u0001\u0016\u0003Q\u0002\"!\u000e\u001c\u000e\u0003qI!a\u000e\u000f\u0003\u001bAK\u0007/\u001a7j]\u0016lu\u000eZ3m\u0003%\u0001\u0018\u000e]3mS:,\u0007%\u0001\u0005gK\u0006$XO]3t+\u0005Y\u0004c\u0001\u0014=}%\u0011Qh\n\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003\u007f\u0019s!\u0001\u0011#\u0011\u0005\u0005;S\"\u0001\"\u000b\u0005\r\u0013\u0014A\u0002\u001fs_>$h(\u0003\u0002FO\u00051\u0001K]3eK\u001aL!a\u0012%\u0003\rM#(/\u001b8h\u0015\t)u%A\u0005gK\u0006$XO]3tA\u00051A(\u001b8jiz\"2\u0001\u0014(P!\ti\u0005!D\u0001\u001b\u0011\u0015\tT\u00011\u00015\u0011\u0015IT\u00011\u0001<\u0003]I7o\u001c;p]&\u001c'+Z4sKN\u001c\u0018n\u001c8N_\u0012,G.F\u0001S!\t\u0019f+D\u0001U\u0015\t)F$\u0001\u0006sK\u001e\u0014Xm]:j_:L!a\u0016+\u0003/%\u001bx\u000e^8oS\u000e\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0017\u0001G5t_R|g.[2SK\u001e\u0014Xm]:j_:lu\u000eZ3mA\u0005Q!m\\;oI\u0006\u0014\u0018.Z:\u0016\u0003m\u00032A\n\u001f]!\t1S,\u0003\u0002_O\t1Ai\\;cY\u0016\f1\u0002\u001d:fI&\u001cG/[8og\u0006IAO]1og\u001a|'/\u001c\u000b\u0003EN\u0004\"a\u00199\u000f\u0005\u0011lgBA3l\u001d\t1'N\u0004\u0002hS:\u0011\u0011\t[\u0005\u0002G%\u0011\u0011EI\u0005\u0003?\u0001J!\u0001\u001c\u0010\u0002\u0007M\fH.\u0003\u0002o_\u00069\u0001/Y2lC\u001e,'B\u00017\u001f\u0013\t\t(OA\u0005ECR\fgI]1nK*\u0011an\u001c\u0005\u0006i*\u0001\r!^\u0001\bI\u0006$\u0018m]3ua\t1H\u0010E\u0002xqjl\u0011a\\\u0005\u0003s>\u0014q\u0001R1uCN,G\u000f\u0005\u0002|y2\u0001A!C?t\u0003\u0003\u0005\tQ!\u0001\u007f\u0005\ryF%M\t\u0004\u007f\u0006\u0015\u0001c\u0001\u0014\u0002\u0002%\u0019\u00111A\u0014\u0003\u000f9{G\u000f[5oOB\u0019a%a\u0002\n\u0007\u0005%qEA\u0002B]f\fQa\u001e:ji\u0016,\"!a\u0004\u0011\u00071\n\t\"C\u0002\u0002\u00145\u0012\u0001\"\u0014'Xe&$XM]\u0001\u001a\u0013N|Go\u001c8jGJ+wM]3tg&|gn\u0016:baB,'\u000f\u0005\u0002N\u001bM!Q\"JA\u000e!\u0011a\u0013Q\u0004'\n\u0007\u0005}QF\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016$\"!a\u0006\u0002\u0007\u0019LG\u000fF\u0006M\u0003O\tY#a\f\u0002:\u0005\r\u0003BBA\u0015\u001f\u0001\u0007!-\u0001\u0003eCR\f\u0007BBA\u0017\u001f\u0001\u0007a(A\u0004g_JlW\u000f\\1\t\u000f\u0005Er\u00021\u0001\u00024\u0005A\u0011n]8u_:L7\rE\u0002'\u0003kI1!a\u000e(\u0005\u001d\u0011un\u001c7fC:Dq!a\u000f\u0010\u0001\u0004\ti$\u0001\u0007gK\u0006$XO]3J]\u0012,\u0007\u0010E\u0002'\u0003\u007fI1!!\u0011(\u0005\rIe\u000e\u001e\u0005\u0007\u0003\u000bz\u0001\u0019\u0001 \u0002\u0013],\u0017n\u001a5u\u0007>d\u0017\u0001\u0002:fC\u0012,\"!a\u0013\u0011\t1\ni\u0005T\u0005\u0004\u0003\u001fj#\u0001C'M%\u0016\fG-\u001a:\u0002\t1|\u0017\r\u001a\u000b\u0004\u0019\u0006U\u0003BBA,#\u0001\u0007a(\u0001\u0003qCRD'aH%t_R|g.[2SK\u001e\u0014Xm]:j_:<&/\u00199qKJ<&/\u001b;feN\u0019!#a\u0004\u0002\u0011%t7\u000f^1oG\u0016$B!!\u0019\u0002fA\u0019\u00111\r\n\u000e\u00035Aa!!\u0018\u0015\u0001\u0004a\u0015\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\u0005-\u0014\u0011\u000f\t\u0004M\u00055\u0014bAA8O\t!QK\\5u\u0011\u0019\t9&\u0006a\u0001}\ty\u0012j]8u_:L7MU3he\u0016\u001c8/[8o/J\f\u0007\u000f]3s%\u0016\fG-\u001a:\u0014\u0007Y\tY\u0005\u0006\u0002\u0002zA\u0019\u00111\r\f\u0015\u00071\u000bi\b\u0003\u0004\u0002Xa\u0001\rA\u0010"
)
public class IsotonicRegressionWrapper implements MLWritable {
   private double[] boundaries;
   private double[] predictions;
   private final PipelineModel pipeline;
   private final String[] features;
   private final IsotonicRegressionModel isotonicRegressionModel;
   private volatile byte bitmap$0;

   public static IsotonicRegressionWrapper load(final String path) {
      return IsotonicRegressionWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return IsotonicRegressionWrapper$.MODULE$.read();
   }

   public static IsotonicRegressionWrapper fit(final Dataset data, final String formula, final boolean isotonic, final int featureIndex, final String weightCol) {
      return IsotonicRegressionWrapper$.MODULE$.fit(data, formula, isotonic, featureIndex, weightCol);
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

   private IsotonicRegressionModel isotonicRegressionModel() {
      return this.isotonicRegressionModel;
   }

   private double[] boundaries$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.boundaries = this.isotonicRegressionModel().boundaries().toArray();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.boundaries;
   }

   public double[] boundaries() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.boundaries$lzycompute() : this.boundaries;
   }

   private double[] predictions$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.predictions = this.isotonicRegressionModel().predictions().toArray();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.predictions;
   }

   public double[] predictions() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.predictions$lzycompute() : this.predictions;
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.isotonicRegressionModel().getFeaturesCol());
   }

   public MLWriter write() {
      return new IsotonicRegressionWrapperWriter(this);
   }

   public IsotonicRegressionWrapper(final PipelineModel pipeline, final String[] features) {
      this.pipeline = pipeline;
      this.features = features;
      MLWritable.$init$(this);
      this.isotonicRegressionModel = (IsotonicRegressionModel)pipeline.stages()[1];
   }

   public static class IsotonicRegressionWrapperWriter extends MLWriter {
      private final IsotonicRegressionWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(IsotonicRegressionWrapperWriter.class.getClassLoader());

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

      public IsotonicRegressionWrapperWriter(final IsotonicRegressionWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class IsotonicRegressionWrapperReader extends MLReader {
      public IsotonicRegressionWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new IsotonicRegressionWrapper(pipeline, features);
      }
   }
}
