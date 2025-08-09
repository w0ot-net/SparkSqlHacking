package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.regression.RandomForestRegressionModel;
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
   bytes = "\u0006\u0005\u00055g!B\u0010!\u0001\u0001R\u0003\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011A\u001d\t\u0011y\u0002!\u0011!Q\u0001\niB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005\u0003\"AQ\n\u0001BC\u0002\u0013\u0005a\n\u0003\u0005S\u0001\t\u0005\t\u0015!\u0003P\u0011\u0015\u0019\u0006\u0001\"\u0003U\u0011\u001dQ\u0006A1A\u0005\nmCaA\u0019\u0001!\u0002\u0013a\u0006\u0002C2\u0001\u0011\u000b\u0007I\u0011\u00013\t\u0011!\u0004\u0001R1A\u0005\u0002%D\u0001\u0002\u001d\u0001\t\u0006\u0004%\t\u0001\u001a\u0005\tc\u0002A)\u0019!C\u0001e\"Aq\u000f\u0001EC\u0002\u0013\u0005A\rC\u0003y\u0001\u0011\u0005\u0001\tC\u0003z\u0001\u0011\u0005!\u0010C\u0004\u0002>\u0001!\t%a\u0010\b\u0011\u0005\u001d\u0003\u0005#\u0001!\u0003\u00132qa\b\u0011\t\u0002\u0001\nY\u0005\u0003\u0004T'\u0011\u0005\u00111\u000b\u0005\b\u0003+\u001aB\u0011AA,\u0011\u001d\t)j\u0005C!\u0003/Cq!a(\u0014\t\u0003\n\tK\u0002\u0004\u0002(N\u0001\u0011\u0011\u0016\u0005\n\u0003WC\"\u0011!Q\u0001\nUCaa\u0015\r\u0005\u0002\u00055\u0006bBA[1\u0011E\u0013q\u0017\u0004\u0007\u0003\u0003\u001c\u0002!a1\t\rMcB\u0011AAc\u0011\u001d\ty\n\bC!\u0003\u0013\u0014ADU1oI>lgi\u001c:fgR\u0014Vm\u001a:fgN|'o\u0016:baB,'O\u0003\u0002\"E\u0005\t!O\u0003\u0002$I\u0005\u0011Q\u000e\u001c\u0006\u0003K\u0019\nQa\u001d9be.T!a\n\u0015\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0013aA8sON\u0019\u0001aK\u0019\u0011\u00051zS\"A\u0017\u000b\u00039\nQa]2bY\u0006L!\u0001M\u0017\u0003\r\u0005s\u0017PU3g!\t\u0011T'D\u00014\u0015\t!$%\u0001\u0003vi&d\u0017B\u0001\u001c4\u0005)iEj\u0016:ji\u0006\u0014G.Z\u0001\ta&\u0004X\r\\5oK\u000e\u0001Q#\u0001\u001e\u0011\u0005mbT\"\u0001\u0012\n\u0005u\u0012#!\u0004)ja\u0016d\u0017N\\3N_\u0012,G.A\u0005qSB,G.\u001b8fA\u00059am\u001c:nk2\fW#A!\u0011\u0005\tKeBA\"H!\t!U&D\u0001F\u0015\t1\u0005(\u0001\u0004=e>|GOP\u0005\u0003\u00116\na\u0001\u0015:fI\u00164\u0017B\u0001&L\u0005\u0019\u0019FO]5oO*\u0011\u0001*L\u0001\tM>\u0014X.\u001e7bA\u0005Aa-Z1ukJ,7/F\u0001P!\ra\u0003+Q\u0005\u0003#6\u0012Q!\u0011:sCf\f\u0011BZ3biV\u0014Xm\u001d\u0011\u0002\rqJg.\u001b;?)\u0011)v\u000bW-\u0011\u0005Y\u0003Q\"\u0001\u0011\t\u000b]:\u0001\u0019\u0001\u001e\t\u000b}:\u0001\u0019A!\t\u000b5;\u0001\u0019A(\u0002\u0011I4'/T8eK2,\u0012\u0001\u0018\t\u0003;\u0002l\u0011A\u0018\u0006\u0003?\n\n!B]3he\u0016\u001c8/[8o\u0013\t\tgLA\u000eSC:$w.\u001c$pe\u0016\u001cHOU3he\u0016\u001c8/[8o\u001b>$W\r\\\u0001\ne\u001a\u0014Xj\u001c3fY\u0002\n1B\\;n\r\u0016\fG/\u001e:fgV\tQ\r\u0005\u0002-M&\u0011q-\f\u0002\u0004\u0013:$\u0018A\u00054fCR,(/Z%na>\u0014H/\u00198dKN,\u0012A\u001b\t\u0003W:l\u0011\u0001\u001c\u0006\u0003[\n\na\u0001\\5oC2<\u0017BA8m\u0005\u00191Vm\u0019;pe\u0006Aa.^7Ue\u0016,7/A\u0006ue\u0016,w+Z5hQR\u001cX#A:\u0011\u00071\u0002F\u000f\u0005\u0002-k&\u0011a/\f\u0002\u0007\t>,(\r\\3\u0002\u00115\f\u0007\u0010R3qi\"\fqa];n[\u0006\u0014\u00180A\u0005ue\u0006t7OZ8s[R\u001910!\u0007\u0011\u0007q\f\u0019BD\u0002~\u0003\u001bq1A`A\u0005\u001d\ry\u0018q\u0001\b\u0005\u0003\u0003\t)AD\u0002E\u0003\u0007I\u0011!K\u0005\u0003O!J!!\n\u0014\n\u0007\u0005-A%A\u0002tc2LA!a\u0004\u0002\u0012\u00059\u0001/Y2lC\u001e,'bAA\u0006I%!\u0011QCA\f\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0003\u0002\u0010\u0005E\u0001bBA\u000e!\u0001\u0007\u0011QD\u0001\bI\u0006$\u0018m]3ua\u0011\ty\"a\u000b\u0011\r\u0005\u0005\u00121EA\u0014\u001b\t\t\t\"\u0003\u0003\u0002&\u0005E!a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003S\tY\u0003\u0004\u0001\u0005\u0019\u00055\u0012\u0011DA\u0001\u0002\u0003\u0015\t!a\f\u0003\u0007}#\u0013'\u0005\u0003\u00022\u0005]\u0002c\u0001\u0017\u00024%\u0019\u0011QG\u0017\u0003\u000f9{G\u000f[5oOB\u0019A&!\u000f\n\u0007\u0005mRFA\u0002B]f\fQa\u001e:ji\u0016,\"!!\u0011\u0011\u0007I\n\u0019%C\u0002\u0002FM\u0012\u0001\"\u0014'Xe&$XM]\u0001\u001d%\u0006tGm\\7G_J,7\u000f\u001e*fOJ,7o]8s/J\f\u0007\u000f]3s!\t16c\u0005\u0003\u0014W\u00055\u0003\u0003\u0002\u001a\u0002PUK1!!\u00154\u0005)iEJU3bI\u0006\u0014G.\u001a\u000b\u0003\u0003\u0013\n1AZ5u)})\u0016\u0011LA/\u0003?\n\t'!\u001a\u0002h\u0005-\u0014qNA:\u0003o\nY(a \u0002\u0004\u0006\u001d\u0015\u0011\u0013\u0005\u0007\u00037*\u0002\u0019A>\u0002\t\u0011\fG/\u0019\u0005\u0006\u007fU\u0001\r!\u0011\u0005\u0006oV\u0001\r!\u001a\u0005\u0007\u0003G*\u0002\u0019A3\u0002\u000f5\f\u0007PQ5og\")\u0001/\u0006a\u0001K\"1\u0011\u0011N\u000bA\u0002\u0005\u000b\u0001\"[7qkJLG/\u001f\u0005\u0007\u0003[*\u0002\u0019A3\u0002'5Lg.\u00138ti\u0006t7-Z:QKJtu\u000eZ3\t\r\u0005ET\u00031\u0001u\u0003-i\u0017N\\%oM><\u0015-\u001b8\t\r\u0005UT\u00031\u0001f\u0003I\u0019\u0007.Z2la>Lg\u000e^%oi\u0016\u0014h/\u00197\t\r\u0005eT\u00031\u0001B\u0003U1W-\u0019;ve\u0016\u001cVOY:fiN#(/\u0019;fOfDa!! \u0016\u0001\u0004\t\u0015\u0001B:fK\u0012Da!!!\u0016\u0001\u0004!\u0018aD:vEN\fW\u000e\u001d7j]\u001e\u0014\u0016\r^3\t\r\u0005\u0015U\u00031\u0001f\u00035i\u0017\r_'f[>\u0014\u00180\u00138N\u0005\"9\u0011\u0011R\u000bA\u0002\u0005-\u0015\u0001D2bG\",gj\u001c3f\u0013\u0012\u001c\bc\u0001\u0017\u0002\u000e&\u0019\u0011qR\u0017\u0003\u000f\t{w\u000e\\3b]\"9\u00111S\u000bA\u0002\u0005-\u0015!\u00032p_R\u001cHO]1q\u0003\u0011\u0011X-\u00193\u0016\u0005\u0005e\u0005\u0003\u0002\u001a\u0002\u001cVK1!!(4\u0005!iEJU3bI\u0016\u0014\u0018\u0001\u00027pC\u0012$2!VAR\u0011\u0019\t)k\u0006a\u0001\u0003\u0006!\u0001/\u0019;i\u0005\t\u0012\u0016M\u001c3p[\u001a{'/Z:u%\u0016<'/Z:t_J<&/\u00199qKJ<&/\u001b;feN\u0019\u0001$!\u0011\u0002\u0011%t7\u000f^1oG\u0016$B!a,\u00024B\u0019\u0011\u0011\u0017\r\u000e\u0003MAa!a+\u001b\u0001\u0004)\u0016\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\u0005e\u0016q\u0018\t\u0004Y\u0005m\u0016bAA_[\t!QK\\5u\u0011\u0019\t)k\u0007a\u0001\u0003\n\u0011#+\u00198e_64uN]3tiJ+wM]3tg>\u0014xK]1qa\u0016\u0014(+Z1eKJ\u001c2\u0001HAM)\t\t9\rE\u0002\u00022r!2!VAf\u0011\u0019\t)K\ba\u0001\u0003\u0002"
)
public class RandomForestRegressorWrapper implements MLWritable {
   private int numFeatures;
   private Vector featureImportances;
   private int numTrees;
   private double[] treeWeights;
   private int maxDepth;
   private final PipelineModel pipeline;
   private final String formula;
   private final String[] features;
   private final RandomForestRegressionModel rfrModel;
   private volatile byte bitmap$0;

   public static RandomForestRegressorWrapper load(final String path) {
      return RandomForestRegressorWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RandomForestRegressorWrapper$.MODULE$.read();
   }

   public static RandomForestRegressorWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final int numTrees, final String impurity, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String featureSubsetStrategy, final String seed, final double subsamplingRate, final int maxMemoryInMB, final boolean cacheNodeIds, final boolean bootstrap) {
      return RandomForestRegressorWrapper$.MODULE$.fit(data, formula, maxDepth, maxBins, numTrees, impurity, minInstancesPerNode, minInfoGain, checkpointInterval, featureSubsetStrategy, seed, subsamplingRate, maxMemoryInMB, cacheNodeIds, bootstrap);
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

   private RandomForestRegressionModel rfrModel() {
      return this.rfrModel;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.numFeatures = this.rfrModel().numFeatures();
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
            this.featureImportances = this.rfrModel().featureImportances();
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
            this.numTrees = this.rfrModel().getNumTrees();
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
            this.treeWeights = this.rfrModel().treeWeights();
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
            this.maxDepth = this.rfrModel().getMaxDepth();
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
      return this.rfrModel().toDebugString();
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.rfrModel().getFeaturesCol());
   }

   public MLWriter write() {
      return new RandomForestRegressorWrapperWriter(this);
   }

   public RandomForestRegressorWrapper(final PipelineModel pipeline, final String formula, final String[] features) {
      this.pipeline = pipeline;
      this.formula = formula;
      this.features = features;
      MLWritable.$init$(this);
      this.rfrModel = (RandomForestRegressionModel)pipeline.stages()[1];
   }

   public static class RandomForestRegressorWrapperWriter extends MLWriter {
      private final RandomForestRegressorWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("formula"), this.instance.formula()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RandomForestRegressorWrapperWriter.class.getClassLoader());

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

      public RandomForestRegressorWrapperWriter(final RandomForestRegressorWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class RandomForestRegressorWrapperReader extends MLReader {
      public RandomForestRegressorWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String formula = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "formula")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         return new RandomForestRegressorWrapper(pipeline, formula, features);
      }
   }
}
