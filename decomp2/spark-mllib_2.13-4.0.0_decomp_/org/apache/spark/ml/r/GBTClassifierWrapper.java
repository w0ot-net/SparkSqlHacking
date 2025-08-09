package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.classification.GBTClassificationModel;
import org.apache.spark.ml.linalg.Vector;
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
   bytes = "\u0006\u0005\u0005=h!B\u0012%\u0001\u0011r\u0003\u0002C\u001e\u0001\u0005\u000b\u0007I\u0011A\u001f\t\u0011\t\u0003!\u0011!Q\u0001\nyB\u0001b\u0011\u0001\u0003\u0006\u0004%\t\u0001\u0012\u0005\t!\u0002\u0011\t\u0011)A\u0005\u000b\"A\u0011\u000b\u0001BC\u0002\u0013\u0005!\u000b\u0003\u0005W\u0001\t\u0005\t\u0015!\u0003T\u0011\u00159\u0006\u0001\"\u0003Y\u0011\u001dq\u0006A1A\u0005\n}CaA\u001a\u0001!\u0002\u0013\u0001\u0007\u0002C4\u0001\u0011\u000b\u0007I\u0011\u00015\t\u00111\u0004\u0001R1A\u0005\u00025D\u0001\u0002\u001e\u0001\t\u0006\u0004%\t\u0001\u001b\u0005\tk\u0002A)\u0019!C\u0001m\"A1\u0010\u0001EC\u0002\u0013\u0005\u0001\u000eC\u0003}\u0001\u0011\u0005A\tC\u0003~\u0001\u0011\u0005a\u0010C\u0004\u0002F\u0001!\t%a\u0012\b\u0011\u0005=C\u0005#\u0001%\u0003#2qa\t\u0013\t\u0002\u0011\n\u0019\u0006\u0003\u0004X'\u0011\u0005\u00111\f\u0005\n\u0003;\u001a\"\u0019!C\u0001\u0003?B\u0001\"a\u001c\u0014A\u0003%\u0011\u0011\r\u0005\n\u0003c\u001a\"\u0019!C\u0001\u0003?B\u0001\"a\u001d\u0014A\u0003%\u0011\u0011\r\u0005\b\u0003k\u001aB\u0011AA<\u0011\u001d\t9l\u0005C!\u0003sCq!!1\u0014\t\u0003\n\u0019M\u0002\u0004\u0002JN\u0001\u00111\u001a\u0005\n\u0003\u001bd\"\u0011!Q\u0001\neCaa\u0016\u000f\u0005\u0002\u0005=\u0007bBAl9\u0011E\u0013\u0011\u001c\u0004\u0007\u0003G\u001c\u0002!!:\t\r]\u0003C\u0011AAt\u0011\u001d\t\t\r\tC!\u0003W\u0014Ac\u0012\"U\u00072\f7o]5gS\u0016\u0014xK]1qa\u0016\u0014(BA\u0013'\u0003\u0005\u0011(BA\u0014)\u0003\tiGN\u0003\u0002*U\u0005)1\u000f]1sW*\u00111\u0006L\u0001\u0007CB\f7\r[3\u000b\u00035\n1a\u001c:h'\r\u0001q&\u000e\t\u0003aMj\u0011!\r\u0006\u0002e\u0005)1oY1mC&\u0011A'\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005YJT\"A\u001c\u000b\u0005a2\u0013\u0001B;uS2L!AO\u001c\u0003\u00155cuK]5uC\ndW-\u0001\u0005qSB,G.\u001b8f\u0007\u0001)\u0012A\u0010\t\u0003\u007f\u0001k\u0011AJ\u0005\u0003\u0003\u001a\u0012Q\u0002U5qK2Lg.Z'pI\u0016d\u0017!\u00039ja\u0016d\u0017N\\3!\u0003\u001d1wN]7vY\u0006,\u0012!\u0012\t\u0003\r6s!aR&\u0011\u0005!\u000bT\"A%\u000b\u0005)c\u0014A\u0002\u001fs_>$h(\u0003\u0002Mc\u00051\u0001K]3eK\u001aL!AT(\u0003\rM#(/\u001b8h\u0015\ta\u0015'\u0001\u0005g_JlW\u000f\\1!\u0003!1W-\u0019;ve\u0016\u001cX#A*\u0011\u0007A\"V)\u0003\u0002Vc\t)\u0011I\u001d:bs\u0006Ia-Z1ukJ,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\te[F,\u0018\t\u00035\u0002i\u0011\u0001\n\u0005\u0006w\u001d\u0001\rA\u0010\u0005\u0006\u0007\u001e\u0001\r!\u0012\u0005\u0006#\u001e\u0001\raU\u0001\nO\n$8-T8eK2,\u0012\u0001\u0019\t\u0003C\u0012l\u0011A\u0019\u0006\u0003G\u001a\nab\u00197bgNLg-[2bi&|g.\u0003\u0002fE\n1rI\u0011+DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8N_\u0012,G.\u0001\u0006hER\u001cWj\u001c3fY\u0002\n1B\\;n\r\u0016\fG/\u001e:fgV\t\u0011\u000e\u0005\u00021U&\u00111.\r\u0002\u0004\u0013:$\u0018A\u00054fCR,(/Z%na>\u0014H/\u00198dKN,\u0012A\u001c\t\u0003_Jl\u0011\u0001\u001d\u0006\u0003c\u001a\na\u0001\\5oC2<\u0017BA:q\u0005\u00191Vm\u0019;pe\u0006Aa.^7Ue\u0016,7/A\u0006ue\u0016,w+Z5hQR\u001cX#A<\u0011\u0007A\"\u0006\u0010\u0005\u00021s&\u0011!0\r\u0002\u0007\t>,(\r\\3\u0002\u00115\f\u0007\u0010R3qi\"\fqa];n[\u0006\u0014\u00180A\u0005ue\u0006t7OZ8s[R\u0019q0!\t\u0011\t\u0005\u0005\u00111\u0004\b\u0005\u0003\u0007\t)B\u0004\u0003\u0002\u0006\u0005Ea\u0002BA\u0004\u0003\u001fqA!!\u0003\u0002\u000e9\u0019\u0001*a\u0003\n\u00035J!a\u000b\u0017\n\u0005%R\u0013bAA\nQ\u0005\u00191/\u001d7\n\t\u0005]\u0011\u0011D\u0001\ba\u0006\u001c7.Y4f\u0015\r\t\u0019\u0002K\u0005\u0005\u0003;\tyBA\u0005ECR\fgI]1nK*!\u0011qCA\r\u0011\u001d\t\u0019\u0003\u0005a\u0001\u0003K\tq\u0001Z1uCN,G\u000f\r\u0003\u0002(\u0005M\u0002CBA\u0015\u0003W\ty#\u0004\u0002\u0002\u001a%!\u0011QFA\r\u0005\u001d!\u0015\r^1tKR\u0004B!!\r\u000241\u0001A\u0001DA\u001b\u0003C\t\t\u0011!A\u0003\u0002\u0005]\"aA0%cE!\u0011\u0011HA !\r\u0001\u00141H\u0005\u0004\u0003{\t$a\u0002(pi\"Lgn\u001a\t\u0004a\u0005\u0005\u0013bAA\"c\t\u0019\u0011I\\=\u0002\u000b]\u0014\u0018\u000e^3\u0016\u0005\u0005%\u0003c\u0001\u001c\u0002L%\u0019\u0011QJ\u001c\u0003\u00115cuK]5uKJ\fAc\u0012\"U\u00072\f7o]5gS\u0016\u0014xK]1qa\u0016\u0014\bC\u0001.\u0014'\u0011\u0019r&!\u0016\u0011\tY\n9&W\u0005\u0004\u00033:$AC'M%\u0016\fG-\u00192mKR\u0011\u0011\u0011K\u0001\u001a!J+E)S\"U\u000b\u0012{F*\u0011\"F\u0019~Ke\nR#Y?\u000e{E*\u0006\u0002\u0002bA!\u00111MA7\u001b\t\t)G\u0003\u0003\u0002h\u0005%\u0014\u0001\u00027b]\u001eT!!a\u001b\u0002\t)\fg/Y\u0005\u0004\u001d\u0006\u0015\u0014A\u0007)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0013:#U\tW0D\u001f2\u0003\u0013a\u0005)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0007>c\u0015\u0001\u0006)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0007>c\u0005%A\u0002gSR$r$WA=\u0003{\ny(!!\u0002\u0006\u0006%\u0015QRAI\u0003+\u000bI*!(\u0002\"\u0006\u0015\u0016\u0011VAZ\u0011\u0019\tY(\u0007a\u0001\u007f\u0006!A-\u0019;b\u0011\u0015\u0019\u0015\u00041\u0001F\u0011\u0015Y\u0018\u00041\u0001j\u0011\u0019\t\u0019)\u0007a\u0001S\u00069Q.\u0019=CS:\u001c\bBBAD3\u0001\u0007\u0011.A\u0004nCbLE/\u001a:\t\r\u0005-\u0015\u00041\u0001y\u0003!\u0019H/\u001a9TSj,\u0007BBAH3\u0001\u0007\u0011.A\nnS:Len\u001d;b]\u000e,7\u000fU3s\u001d>$W\r\u0003\u0004\u0002\u0014f\u0001\r\u0001_\u0001\f[&t\u0017J\u001c4p\u000f\u0006Lg\u000e\u0003\u0004\u0002\u0018f\u0001\r![\u0001\u0013G\",7m\u001b9pS:$\u0018J\u001c;feZ\fG\u000e\u0003\u0004\u0002\u001cf\u0001\r!R\u0001\tY>\u001c8\u000fV=qK\"1\u0011qT\rA\u0002\u0015\u000bAa]3fI\"1\u00111U\rA\u0002a\fqb];cg\u0006l\u0007\u000f\\5oOJ\u000bG/\u001a\u0005\u0007\u0003OK\u0002\u0019A5\u0002\u001b5\f\u00070T3n_JL\u0018J\\'C\u0011\u001d\tY+\u0007a\u0001\u0003[\u000bAbY1dQ\u0016tu\u000eZ3JIN\u00042\u0001MAX\u0013\r\t\t,\r\u0002\b\u0005>|G.Z1o\u0011\u0019\t),\u0007a\u0001\u000b\u0006i\u0001.\u00198eY\u0016LeN^1mS\u0012\fAA]3bIV\u0011\u00111\u0018\t\u0005m\u0005u\u0016,C\u0002\u0002@^\u0012\u0001\"\u0014'SK\u0006$WM]\u0001\u0005Y>\fG\rF\u0002Z\u0003\u000bDa!a2\u001c\u0001\u0004)\u0015\u0001\u00029bi\"\u0014!d\u0012\"U\u00072\f7o]5gS\u0016\u0014xK]1qa\u0016\u0014xK]5uKJ\u001c2\u0001HA%\u0003!Ign\u001d;b]\u000e,G\u0003BAi\u0003+\u00042!a5\u001d\u001b\u0005\u0019\u0002BBAg=\u0001\u0007\u0011,\u0001\u0005tCZ,\u0017*\u001c9m)\u0011\tY.!9\u0011\u0007A\ni.C\u0002\u0002`F\u0012A!\u00168ji\"1\u0011qY\u0010A\u0002\u0015\u0013!d\u0012\"U\u00072\f7o]5gS\u0016\u0014xK]1qa\u0016\u0014(+Z1eKJ\u001c2\u0001IA^)\t\tI\u000fE\u0002\u0002T\u0002\"2!WAw\u0011\u0019\t9M\ta\u0001\u000b\u0002"
)
public class GBTClassifierWrapper implements MLWritable {
   private int numFeatures;
   private Vector featureImportances;
   private int numTrees;
   private double[] treeWeights;
   private int maxDepth;
   private final PipelineModel pipeline;
   private final String formula;
   private final String[] features;
   private final GBTClassificationModel gbtcModel;
   private volatile byte bitmap$0;

   public static GBTClassifierWrapper load(final String path) {
      return GBTClassifierWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GBTClassifierWrapper$.MODULE$.read();
   }

   public static GBTClassifierWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final int maxIter, final double stepSize, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String lossType, final String seed, final double subsamplingRate, final int maxMemoryInMB, final boolean cacheNodeIds, final String handleInvalid) {
      return GBTClassifierWrapper$.MODULE$.fit(data, formula, maxDepth, maxBins, maxIter, stepSize, minInstancesPerNode, minInfoGain, checkpointInterval, lossType, seed, subsamplingRate, maxMemoryInMB, cacheNodeIds, handleInvalid);
   }

   public static String PREDICTED_LABEL_COL() {
      return GBTClassifierWrapper$.MODULE$.PREDICTED_LABEL_COL();
   }

   public static String PREDICTED_LABEL_INDEX_COL() {
      return GBTClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL();
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

   private GBTClassificationModel gbtcModel() {
      return this.gbtcModel;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.numFeatures = this.gbtcModel().numFeatures();
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
            this.featureImportances = this.gbtcModel().featureImportances();
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
            this.numTrees = this.gbtcModel().getNumTrees();
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
            this.treeWeights = this.gbtcModel().treeWeights();
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
            this.maxDepth = this.gbtcModel().getMaxDepth();
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
      return this.gbtcModel().toDebugString();
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(GBTClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL()).drop(this.gbtcModel().getFeaturesCol()).drop(this.gbtcModel().getLabelCol());
   }

   public MLWriter write() {
      return new GBTClassifierWrapperWriter(this);
   }

   public GBTClassifierWrapper(final PipelineModel pipeline, final String formula, final String[] features) {
      this.pipeline = pipeline;
      this.formula = formula;
      this.features = features;
      MLWritable.$init$(this);
      this.gbtcModel = (GBTClassificationModel)pipeline.stages()[1];
   }

   public static class GBTClassifierWrapperWriter extends MLWriter {
      private final GBTClassifierWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("formula"), this.instance.formula()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GBTClassifierWrapperWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).repartition(1).write().text(rMetadataPath);
         this.instance.pipeline().save(pipelinePath);
      }

      public GBTClassifierWrapperWriter(final GBTClassifierWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class GBTClassifierWrapperReader extends MLReader {
      public GBTClassifierWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String formula = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "formula")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         return new GBTClassifierWrapper(pipeline, formula, features);
      }
   }
}
