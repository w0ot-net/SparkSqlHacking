package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.regression.DecisionTreeRegressionModel;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import org.json4s.JsonListAssoc.;
import scala.Tuple1;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mf!B\u000f\u001f\u0001yA\u0003\u0002C\u001b\u0001\u0005\u000b\u0007I\u0011A\u001c\t\u0011q\u0002!\u0011!Q\u0001\naB\u0001\"\u0010\u0001\u0003\u0006\u0004%\tA\u0010\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005\u007f!A1\n\u0001BC\u0002\u0013\u0005A\n\u0003\u0005Q\u0001\t\u0005\t\u0015!\u0003N\u0011\u0015\t\u0006\u0001\"\u0003S\u0011\u001dA\u0006A1A\u0005\neCa\u0001\u0019\u0001!\u0002\u0013Q\u0006\u0002C1\u0001\u0011\u000b\u0007I\u0011\u00012\t\u0011\u0019\u0004\u0001R1A\u0005\u0002\u001dD\u0001B\u001c\u0001\t\u0006\u0004%\tA\u0019\u0005\u0006_\u0002!\tA\u0010\u0005\u0006a\u0002!\t!\u001d\u0005\b\u0003W\u0001A\u0011IA\u0017\u000f!\t)D\bE\u0001=\u0005]baB\u000f\u001f\u0011\u0003q\u0012\u0011\b\u0005\u0007#F!\t!!\u0011\t\u000f\u0005\r\u0013\u0003\"\u0001\u0002F!9\u00111P\t\u0005B\u0005u\u0004bBAC#\u0011\u0005\u0013q\u0011\u0004\u0007\u0003\u001b\u000b\u0002!a$\t\u0013\u0005EeC!A!\u0002\u0013\u0019\u0006BB)\u0017\t\u0003\t\u0019\nC\u0004\u0002\u001cZ!\t&!(\u0007\r\u0005\u001d\u0016\u0003AAU\u0011\u0019\t&\u0004\"\u0001\u0002,\"9\u0011Q\u0011\u000e\u0005B\u0005=&\u0001\b#fG&\u001c\u0018n\u001c8Ue\u0016,'+Z4sKN\u001cxN],sCB\u0004XM\u001d\u0006\u0003?\u0001\n\u0011A\u001d\u0006\u0003C\t\n!!\u001c7\u000b\u0005\r\"\u0013!B:qCJ\\'BA\u0013'\u0003\u0019\t\u0007/Y2iK*\tq%A\u0002pe\u001e\u001c2\u0001A\u00150!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0019\te.\u001f*fMB\u0011\u0001gM\u0007\u0002c)\u0011!\u0007I\u0001\u0005kRLG.\u0003\u00025c\tQQ\nT,sSR\f'\r\\3\u0002\u0011AL\u0007/\u001a7j]\u0016\u001c\u0001!F\u00019!\tI$(D\u0001!\u0013\tY\u0004EA\u0007QSB,G.\u001b8f\u001b>$W\r\\\u0001\na&\u0004X\r\\5oK\u0002\nqAZ8s[Vd\u0017-F\u0001@!\t\u0001uI\u0004\u0002B\u000bB\u0011!iK\u0007\u0002\u0007*\u0011AIN\u0001\u0007yI|w\u000e\u001e \n\u0005\u0019[\u0013A\u0002)sK\u0012,g-\u0003\u0002I\u0013\n11\u000b\u001e:j]\u001eT!AR\u0016\u0002\u0011\u0019|'/\\;mC\u0002\n\u0001BZ3biV\u0014Xm]\u000b\u0002\u001bB\u0019!FT \n\u0005=[#!B!se\u0006L\u0018!\u00034fCR,(/Z:!\u0003\u0019a\u0014N\\5u}Q!1+\u0016,X!\t!\u0006!D\u0001\u001f\u0011\u0015)t\u00011\u00019\u0011\u0015it\u00011\u0001@\u0011\u0015Yu\u00011\u0001N\u0003!!GO]'pI\u0016dW#\u0001.\u0011\u0005msV\"\u0001/\u000b\u0005u\u0003\u0013A\u0003:fOJ,7o]5p]&\u0011q\f\u0018\u0002\u001c\t\u0016\u001c\u0017n]5p]R\u0013X-\u001a*fOJ,7o]5p]6{G-\u001a7\u0002\u0013\u0011$(/T8eK2\u0004\u0013a\u00038v[\u001a+\u0017\r^;sKN,\u0012a\u0019\t\u0003U\u0011L!!Z\u0016\u0003\u0007%sG/\u0001\ngK\u0006$XO]3J[B|'\u000f^1oG\u0016\u001cX#\u00015\u0011\u0005%dW\"\u00016\u000b\u0005-\u0004\u0013A\u00027j]\u0006dw-\u0003\u0002nU\n1a+Z2u_J\f\u0001\"\\1y\t\u0016\u0004H\u000f[\u0001\bgVlW.\u0019:z\u0003%!(/\u00198tM>\u0014X\u000eF\u0002s\u0003\u000f\u00012a]A\u0001\u001d\t!XP\u0004\u0002vw:\u0011aO\u001f\b\u0003oft!A\u0011=\n\u0003\u001dJ!!\n\u0014\n\u0005\r\"\u0013B\u0001?#\u0003\r\u0019\u0018\u000f\\\u0005\u0003}~\fq\u0001]1dW\u0006<WM\u0003\u0002}E%!\u00111AA\u0003\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0002\u007f\u007f\"9\u0011\u0011\u0002\bA\u0002\u0005-\u0011a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003\u001b\tI\u0002\u0005\u0004\u0002\u0010\u0005E\u0011QC\u0007\u0002\u007f&\u0019\u00111C@\u0003\u000f\u0011\u000bG/Y:fiB!\u0011qCA\r\u0019\u0001!A\"a\u0007\u0002\b\u0005\u0005\t\u0011!B\u0001\u0003;\u00111a\u0018\u00132#\u0011\ty\"!\n\u0011\u0007)\n\t#C\u0002\u0002$-\u0012qAT8uQ&tw\rE\u0002+\u0003OI1!!\u000b,\u0005\r\te._\u0001\u0006oJLG/Z\u000b\u0003\u0003_\u00012\u0001MA\u0019\u0013\r\t\u0019$\r\u0002\t\u001b2;&/\u001b;fe\u0006aB)Z2jg&|g\u000e\u0016:fKJ+wM]3tg>\u0014xK]1qa\u0016\u0014\bC\u0001+\u0012'\u0011\t\u0012&a\u000f\u0011\tA\nidU\u0005\u0004\u0003\u007f\t$AC'M%\u0016\fG-\u00192mKR\u0011\u0011qG\u0001\u0004M&$HcF*\u0002H\u0005-\u0013QJA(\u0003'\n9&a\u0017\u0002f\u0005%\u0014QNA9\u0011\u0019\tIe\u0005a\u0001e\u0006!A-\u0019;b\u0011\u0015i4\u00031\u0001@\u0011\u0015q7\u00031\u0001d\u0011\u0019\t\tf\u0005a\u0001G\u00069Q.\u0019=CS:\u001c\bBBA+'\u0001\u0007q(\u0001\u0005j[B,(/\u001b;z\u0011\u0019\tIf\u0005a\u0001G\u0006\u0019R.\u001b8J]N$\u0018M\\2fgB+'OT8eK\"9\u0011QL\nA\u0002\u0005}\u0013aC7j]&sgm\\$bS:\u00042AKA1\u0013\r\t\u0019g\u000b\u0002\u0007\t>,(\r\\3\t\r\u0005\u001d4\u00031\u0001d\u0003I\u0019\u0007.Z2la>Lg\u000e^%oi\u0016\u0014h/\u00197\t\r\u0005-4\u00031\u0001@\u0003\u0011\u0019X-\u001a3\t\r\u0005=4\u00031\u0001d\u00035i\u0017\r_'f[>\u0014\u00180\u00138N\u0005\"9\u00111O\nA\u0002\u0005U\u0014\u0001D2bG\",gj\u001c3f\u0013\u0012\u001c\bc\u0001\u0016\u0002x%\u0019\u0011\u0011P\u0016\u0003\u000f\t{w\u000e\\3b]\u0006!!/Z1e+\t\ty\b\u0005\u00031\u0003\u0003\u001b\u0016bAABc\tAQ\n\u0014*fC\u0012,'/\u0001\u0003m_\u0006$GcA*\u0002\n\"1\u00111R\u000bA\u0002}\nA\u0001]1uQ\n\u0011C)Z2jg&|g\u000e\u0016:fKJ+wM]3tg>\u0014xK]1qa\u0016\u0014xK]5uKJ\u001c2AFA\u0018\u0003!Ign\u001d;b]\u000e,G\u0003BAK\u00033\u00032!a&\u0017\u001b\u0005\t\u0002BBAI1\u0001\u00071+\u0001\u0005tCZ,\u0017*\u001c9m)\u0011\ty*!*\u0011\u0007)\n\t+C\u0002\u0002$.\u0012A!\u00168ji\"1\u00111R\rA\u0002}\u0012!\u0005R3dSNLwN\u001c+sK\u0016\u0014Vm\u001a:fgN|'o\u0016:baB,'OU3bI\u0016\u00148c\u0001\u000e\u0002\u0000Q\u0011\u0011Q\u0016\t\u0004\u0003/SBcA*\u00022\"1\u00111\u0012\u000fA\u0002}\u0002"
)
public class DecisionTreeRegressorWrapper implements MLWritable {
   private int numFeatures;
   private Vector featureImportances;
   private int maxDepth;
   private final PipelineModel pipeline;
   private final String formula;
   private final String[] features;
   private final DecisionTreeRegressionModel dtrModel;
   private volatile byte bitmap$0;

   public static DecisionTreeRegressorWrapper load(final String path) {
      return DecisionTreeRegressorWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return DecisionTreeRegressorWrapper$.MODULE$.read();
   }

   public static DecisionTreeRegressorWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final String impurity, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String seed, final int maxMemoryInMB, final boolean cacheNodeIds) {
      return DecisionTreeRegressorWrapper$.MODULE$.fit(data, formula, maxDepth, maxBins, impurity, minInstancesPerNode, minInfoGain, checkpointInterval, seed, maxMemoryInMB, cacheNodeIds);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public PipelineModel pipeline() {
      return this.pipeline;
   }

   public String formula() {
      return this.formula;
   }

   public String[] features() {
      return this.features;
   }

   private DecisionTreeRegressionModel dtrModel() {
      return this.dtrModel;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.numFeatures = this.dtrModel().numFeatures();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numFeatures;
   }

   public int numFeatures() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.numFeatures$lzycompute() : this.numFeatures;
   }

   private Vector featureImportances$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.featureImportances = this.dtrModel().featureImportances();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.featureImportances;
   }

   public Vector featureImportances() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.featureImportances$lzycompute() : this.featureImportances;
   }

   private int maxDepth$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.maxDepth = this.dtrModel().getMaxDepth();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.maxDepth;
   }

   public int maxDepth() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.maxDepth$lzycompute() : this.maxDepth;
   }

   public String summary() {
      return this.dtrModel().toDebugString();
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.dtrModel().getFeaturesCol());
   }

   public MLWriter write() {
      return new DecisionTreeRegressorWrapperWriter(this);
   }

   public DecisionTreeRegressorWrapper(final PipelineModel pipeline, final String formula, final String[] features) {
      this.pipeline = pipeline;
      this.formula = formula;
      this.features = features;
      MLWritable.$init$(this);
      this.dtrModel = (DecisionTreeRegressionModel)pipeline.stages()[1];
   }

   public static class DecisionTreeRegressorWrapperWriter extends MLWriter {
      private final DecisionTreeRegressorWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("formula"), this.instance.formula()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DecisionTreeRegressorWrapperWriter.class.getClassLoader());

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

      public DecisionTreeRegressorWrapperWriter(final DecisionTreeRegressorWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class DecisionTreeRegressorWrapperReader extends MLReader {
      public DecisionTreeRegressorWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String formula = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "formula")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         return new DecisionTreeRegressorWrapper(pipeline, formula, features);
      }
   }
}
