package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.regression.GBTRegressionModel;
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
   bytes = "\u0006\u0005\u0005-g!B\u0010!\u0001\u0001R\u0003\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011A\u001d\t\u0011y\u0002!\u0011!Q\u0001\niB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005\u0003\"AQ\n\u0001BC\u0002\u0013\u0005a\n\u0003\u0005S\u0001\t\u0005\t\u0015!\u0003P\u0011\u0015\u0019\u0006\u0001\"\u0003U\u0011\u001dQ\u0006A1A\u0005\nmCaA\u0019\u0001!\u0002\u0013a\u0006\u0002C2\u0001\u0011\u000b\u0007I\u0011\u00013\t\u0011!\u0004\u0001R1A\u0005\u0002%D\u0001\u0002\u001d\u0001\t\u0006\u0004%\t\u0001\u001a\u0005\tc\u0002A)\u0019!C\u0001e\"Aq\u000f\u0001EC\u0002\u0013\u0005A\rC\u0003y\u0001\u0011\u0005\u0001\tC\u0003z\u0001\u0011\u0005!\u0010C\u0004\u0002>\u0001!\t%a\u0010\b\u0011\u0005\u001d\u0003\u0005#\u0001!\u0003\u00132qa\b\u0011\t\u0002\u0001\nY\u0005\u0003\u0004T'\u0011\u0005\u00111\u000b\u0005\b\u0003+\u001aB\u0011AA,\u0011\u001d\t\u0019j\u0005C!\u0003+Cq!!(\u0014\t\u0003\nyJ\u0002\u0004\u0002&N\u0001\u0011q\u0015\u0005\n\u0003SC\"\u0011!Q\u0001\nUCaa\u0015\r\u0005\u0002\u0005-\u0006bBAZ1\u0011E\u0013Q\u0017\u0004\u0007\u0003\u007f\u001b\u0002!!1\t\rMcB\u0011AAb\u0011\u001d\ti\n\bC!\u0003\u000f\u00141c\u0012\"U%\u0016<'/Z:t_J<&/\u00199qKJT!!\t\u0012\u0002\u0003IT!a\t\u0013\u0002\u00055d'BA\u0013'\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0003&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002S\u0005\u0019qN]4\u0014\u0007\u0001Y\u0013\u0007\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TF\u0001\u0004B]f\u0014VM\u001a\t\u0003eUj\u0011a\r\u0006\u0003i\t\nA!\u001e;jY&\u0011ag\r\u0002\u000b\u001b2;&/\u001b;bE2,\u0017\u0001\u00039ja\u0016d\u0017N\\3\u0004\u0001U\t!\b\u0005\u0002<y5\t!%\u0003\u0002>E\ti\u0001+\u001b9fY&tW-T8eK2\f\u0011\u0002]5qK2Lg.\u001a\u0011\u0002\u000f\u0019|'/\\;mCV\t\u0011\t\u0005\u0002C\u0013:\u00111i\u0012\t\u0003\t6j\u0011!\u0012\u0006\u0003\rb\na\u0001\u0010:p_Rt\u0014B\u0001%.\u0003\u0019\u0001&/\u001a3fM&\u0011!j\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!k\u0013\u0001\u00034pe6,H.\u0019\u0011\u0002\u0011\u0019,\u0017\r^;sKN,\u0012a\u0014\t\u0004YA\u000b\u0015BA).\u0005\u0015\t%O]1z\u0003%1W-\u0019;ve\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0005+^C\u0016\f\u0005\u0002W\u00015\t\u0001\u0005C\u00038\u000f\u0001\u0007!\bC\u0003@\u000f\u0001\u0007\u0011\tC\u0003N\u000f\u0001\u0007q*A\u0005hER\u0014Xj\u001c3fYV\tA\f\u0005\u0002^A6\taL\u0003\u0002`E\u0005Q!/Z4sKN\u001c\u0018n\u001c8\n\u0005\u0005t&AE$C)J+wM]3tg&|g.T8eK2\f!b\u001a2ue6{G-\u001a7!\u0003-qW/\u001c$fCR,(/Z:\u0016\u0003\u0015\u0004\"\u0001\f4\n\u0005\u001dl#aA%oi\u0006\u0011b-Z1ukJ,\u0017*\u001c9peR\fgnY3t+\u0005Q\u0007CA6o\u001b\u0005a'BA7#\u0003\u0019a\u0017N\\1mO&\u0011q\u000e\u001c\u0002\u0007-\u0016\u001cGo\u001c:\u0002\u00119,X\u000e\u0016:fKN\f1\u0002\u001e:fK^+\u0017n\u001a5ugV\t1\u000fE\u0002-!R\u0004\"\u0001L;\n\u0005Yl#A\u0002#pk\ndW-\u0001\u0005nCb$U\r\u001d;i\u0003\u001d\u0019X/\\7bef\f\u0011\u0002\u001e:b]N4wN]7\u0015\u0007m\fI\u0002E\u0002}\u0003'q1!`A\u0007\u001d\rq\u0018\u0011\u0002\b\u0004\u007f\u0006\u001da\u0002BA\u0001\u0003\u000bq1\u0001RA\u0002\u0013\u0005I\u0013BA\u0014)\u0013\t)c%C\u0002\u0002\f\u0011\n1a]9m\u0013\u0011\ty!!\u0005\u0002\u000fA\f7m[1hK*\u0019\u00111\u0002\u0013\n\t\u0005U\u0011q\u0003\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!a\u0004\u0002\u0012!9\u00111\u0004\tA\u0002\u0005u\u0011a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003?\tY\u0003\u0005\u0004\u0002\"\u0005\r\u0012qE\u0007\u0003\u0003#IA!!\n\u0002\u0012\t9A)\u0019;bg\u0016$\b\u0003BA\u0015\u0003Wa\u0001\u0001\u0002\u0007\u0002.\u0005e\u0011\u0011!A\u0001\u0006\u0003\tyCA\u0002`IE\nB!!\r\u00028A\u0019A&a\r\n\u0007\u0005URFA\u0004O_RD\u0017N\\4\u0011\u00071\nI$C\u0002\u0002<5\u00121!\u00118z\u0003\u00159(/\u001b;f+\t\t\t\u0005E\u00023\u0003\u0007J1!!\u00124\u0005!iEj\u0016:ji\u0016\u0014\u0018aE$C)J+wM]3tg>\u0014xK]1qa\u0016\u0014\bC\u0001,\u0014'\u0011\u00192&!\u0014\u0011\tI\ny%V\u0005\u0004\u0003#\u001a$AC'M%\u0016\fG-\u00192mKR\u0011\u0011\u0011J\u0001\u0004M&$H#H+\u0002Z\u0005u\u0013qLA1\u0003K\nI'!\u001c\u0002r\u0005U\u0014\u0011PA?\u0003\u0003\u000b))!#\t\r\u0005mS\u00031\u0001|\u0003\u0011!\u0017\r^1\t\u000b}*\u0002\u0019A!\t\u000b],\u0002\u0019A3\t\r\u0005\rT\u00031\u0001f\u0003\u001di\u0017\r\u001f\"j]NDa!a\u001a\u0016\u0001\u0004)\u0017aB7bq&#XM\u001d\u0005\u0007\u0003W*\u0002\u0019\u0001;\u0002\u0011M$X\r]*ju\u0016Da!a\u001c\u0016\u0001\u0004)\u0017aE7j]&s7\u000f^1oG\u0016\u001c\b+\u001a:O_\u0012,\u0007BBA:+\u0001\u0007A/A\u0006nS:LeNZ8HC&t\u0007BBA<+\u0001\u0007Q-\u0001\ndQ\u0016\u001c7\u000e]8j]RLe\u000e^3sm\u0006d\u0007BBA>+\u0001\u0007\u0011)\u0001\u0005m_N\u001cH+\u001f9f\u0011\u0019\ty(\u0006a\u0001\u0003\u0006!1/Z3e\u0011\u0019\t\u0019)\u0006a\u0001i\u0006y1/\u001e2tC6\u0004H.\u001b8h%\u0006$X\r\u0003\u0004\u0002\bV\u0001\r!Z\u0001\u000e[\u0006DX*Z7pefLe.\u0014\"\t\u000f\u0005-U\u00031\u0001\u0002\u000e\u0006a1-Y2iK:{G-Z%egB\u0019A&a$\n\u0007\u0005EUFA\u0004C_>dW-\u00198\u0002\tI,\u0017\rZ\u000b\u0003\u0003/\u0003BAMAM+&\u0019\u00111T\u001a\u0003\u00115c%+Z1eKJ\fA\u0001\\8bIR\u0019Q+!)\t\r\u0005\rv\u00031\u0001B\u0003\u0011\u0001\u0018\r\u001e5\u00033\u001d\u0013EKU3he\u0016\u001c8o\u001c:Xe\u0006\u0004\b/\u001a:Xe&$XM]\n\u00041\u0005\u0005\u0013\u0001C5ogR\fgnY3\u0015\t\u00055\u0016\u0011\u0017\t\u0004\u0003_CR\"A\n\t\r\u0005%&\u00041\u0001V\u0003!\u0019\u0018M^3J[BdG\u0003BA\\\u0003{\u00032\u0001LA]\u0013\r\tY,\f\u0002\u0005+:LG\u000f\u0003\u0004\u0002$n\u0001\r!\u0011\u0002\u001a\u000f\n#&+Z4sKN\u001cxN],sCB\u0004XM\u001d*fC\u0012,'oE\u0002\u001d\u0003/#\"!!2\u0011\u0007\u0005=F\u0004F\u0002V\u0003\u0013Da!a)\u001f\u0001\u0004\t\u0005"
)
public class GBTRegressorWrapper implements MLWritable {
   private int numFeatures;
   private Vector featureImportances;
   private int numTrees;
   private double[] treeWeights;
   private int maxDepth;
   private final PipelineModel pipeline;
   private final String formula;
   private final String[] features;
   private final GBTRegressionModel gbtrModel;
   private volatile byte bitmap$0;

   public static GBTRegressorWrapper load(final String path) {
      return GBTRegressorWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GBTRegressorWrapper$.MODULE$.read();
   }

   public static GBTRegressorWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final int maxIter, final double stepSize, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String lossType, final String seed, final double subsamplingRate, final int maxMemoryInMB, final boolean cacheNodeIds) {
      return GBTRegressorWrapper$.MODULE$.fit(data, formula, maxDepth, maxBins, maxIter, stepSize, minInstancesPerNode, minInfoGain, checkpointInterval, lossType, seed, subsamplingRate, maxMemoryInMB, cacheNodeIds);
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

   private GBTRegressionModel gbtrModel() {
      return this.gbtrModel;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.numFeatures = this.gbtrModel().numFeatures();
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
            this.featureImportances = this.gbtrModel().featureImportances();
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

   private int numTrees$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.numTrees = this.gbtrModel().getNumTrees();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numTrees;
   }

   public int numTrees() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.numTrees$lzycompute() : this.numTrees;
   }

   private double[] treeWeights$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.treeWeights = this.gbtrModel().treeWeights();
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.treeWeights;
   }

   public double[] treeWeights() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.treeWeights$lzycompute() : this.treeWeights;
   }

   private int maxDepth$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.maxDepth = this.gbtrModel().getMaxDepth();
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.maxDepth;
   }

   public int maxDepth() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.maxDepth$lzycompute() : this.maxDepth;
   }

   public String summary() {
      return this.gbtrModel().toDebugString();
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.gbtrModel().getFeaturesCol());
   }

   public MLWriter write() {
      return new GBTRegressorWrapperWriter(this);
   }

   public GBTRegressorWrapper(final PipelineModel pipeline, final String formula, final String[] features) {
      this.pipeline = pipeline;
      this.formula = formula;
      this.features = features;
      MLWritable.$init$(this);
      this.gbtrModel = (GBTRegressionModel)pipeline.stages()[1];
   }

   public static class GBTRegressorWrapperWriter extends MLWriter {
      private final GBTRegressorWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("formula"), this.instance.formula()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GBTRegressorWrapperWriter.class.getClassLoader());

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

      public GBTRegressorWrapperWriter(final GBTRegressorWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class GBTRegressorWrapperReader extends MLReader {
      public GBTRegressorWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String formula = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "formula")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         return new GBTRegressorWrapper(pipeline, formula, features);
      }
   }
}
