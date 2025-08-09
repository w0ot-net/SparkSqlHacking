package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.classification.DecisionTreeClassificationModel;
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
   bytes = "\u0006\u0005\u0005]g!B\u0011#\u0001\tb\u0003\u0002C\u001d\u0001\u0005\u000b\u0007I\u0011A\u001e\t\u0011\u0001\u0003!\u0011!Q\u0001\nqB\u0001\"\u0011\u0001\u0003\u0006\u0004%\tA\u0011\u0005\t\u001d\u0002\u0011\t\u0011)A\u0005\u0007\"Aq\n\u0001BC\u0002\u0013\u0005\u0001\u000b\u0003\u0005U\u0001\t\u0005\t\u0015!\u0003R\u0011\u0015)\u0006\u0001\"\u0003W\u0011\u001da\u0006A1A\u0005\nuCa\u0001\u001a\u0001!\u0002\u0013q\u0006\u0002C3\u0001\u0011\u000b\u0007I\u0011\u00014\t\u0011)\u0004\u0001R1A\u0005\u0002-D\u0001B\u001d\u0001\t\u0006\u0004%\tA\u001a\u0005\u0006g\u0002!\tA\u0011\u0005\u0006i\u0002!\t!\u001e\u0005\b\u0003g\u0001A\u0011IA\u001b\u000f!\tiD\tE\u0001E\u0005}baB\u0011#\u0011\u0003\u0011\u0013\u0011\t\u0005\u0007+F!\t!!\u0013\t\u0013\u0005-\u0013C1A\u0005\u0002\u00055\u0003\u0002CA/#\u0001\u0006I!a\u0014\t\u0013\u0005}\u0013C1A\u0005\u0002\u00055\u0003\u0002CA1#\u0001\u0006I!a\u0014\t\u000f\u0005\r\u0014\u0003\"\u0001\u0002f!9\u0011qT\t\u0005B\u0005\u0005\u0006bBAU#\u0011\u0005\u00131\u0016\u0004\u0007\u0003c\u000b\u0002!a-\t\u0013\u0005U&D!A!\u0002\u00139\u0006BB+\u001b\t\u0003\t9\fC\u0004\u0002@j!\t&!1\u0007\r\u0005-\u0017\u0003AAg\u0011\u0019)f\u0004\"\u0001\u0002P\"9\u0011\u0011\u0016\u0010\u0005B\u0005M'!\b#fG&\u001c\u0018n\u001c8Ue\u0016,7\t\\1tg&4\u0017.\u001a:Xe\u0006\u0004\b/\u001a:\u000b\u0005\r\"\u0013!\u0001:\u000b\u0005\u00152\u0013AA7m\u0015\t9\u0003&A\u0003ta\u0006\u00148N\u0003\u0002*U\u00051\u0011\r]1dQ\u0016T\u0011aK\u0001\u0004_J<7c\u0001\u0001.gA\u0011a&M\u0007\u0002_)\t\u0001'A\u0003tG\u0006d\u0017-\u0003\u00023_\t1\u0011I\\=SK\u001a\u0004\"\u0001N\u001c\u000e\u0003UR!A\u000e\u0013\u0002\tU$\u0018\u000e\\\u0005\u0003qU\u0012!\"\u0014'Xe&$\u0018M\u00197f\u0003!\u0001\u0018\u000e]3mS:,7\u0001A\u000b\u0002yA\u0011QHP\u0007\u0002I%\u0011q\b\n\u0002\u000e!&\u0004X\r\\5oK6{G-\u001a7\u0002\u0013AL\u0007/\u001a7j]\u0016\u0004\u0013a\u00024pe6,H.Y\u000b\u0002\u0007B\u0011Ai\u0013\b\u0003\u000b&\u0003\"AR\u0018\u000e\u0003\u001dS!\u0001\u0013\u001e\u0002\rq\u0012xn\u001c;?\u0013\tQu&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&0\u0003!1wN]7vY\u0006\u0004\u0013\u0001\u00034fCR,(/Z:\u0016\u0003E\u00032A\f*D\u0013\t\u0019vFA\u0003BeJ\f\u00170A\u0005gK\u0006$XO]3tA\u00051A(\u001b8jiz\"BaV-[7B\u0011\u0001\fA\u0007\u0002E!)\u0011h\u0002a\u0001y!)\u0011i\u0002a\u0001\u0007\")qj\u0002a\u0001#\u0006AA\r^2N_\u0012,G.F\u0001_!\ty&-D\u0001a\u0015\t\tG%\u0001\bdY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8\n\u0005\r\u0004'a\b#fG&\u001c\u0018n\u001c8Ue\u0016,7\t\\1tg&4\u0017nY1uS>tWj\u001c3fY\u0006IA\r^2N_\u0012,G\u000eI\u0001\f]Vlg)Z1ukJ,7/F\u0001h!\tq\u0003.\u0003\u0002j_\t\u0019\u0011J\u001c;\u0002%\u0019,\u0017\r^;sK&k\u0007o\u001c:uC:\u001cWm]\u000b\u0002YB\u0011Q\u000e]\u0007\u0002]*\u0011q\u000eJ\u0001\u0007Y&t\u0017\r\\4\n\u0005Et'A\u0002,fGR|'/\u0001\u0005nCb$U\r\u001d;i\u0003\u001d\u0019X/\\7bef\f\u0011\u0002\u001e:b]N4wN]7\u0015\u0007Y\fy\u0001E\u0002x\u0003\u0013q1\u0001_A\u0002\u001d\tIxP\u0004\u0002{}:\u001110 \b\u0003\rrL\u0011aK\u0005\u0003S)J!a\n\u0015\n\u0007\u0005\u0005a%A\u0002tc2LA!!\u0002\u0002\b\u00059\u0001/Y2lC\u001e,'bAA\u0001M%!\u00111BA\u0007\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0003\u0002\u0006\u0005\u001d\u0001bBA\t\u001d\u0001\u0007\u00111C\u0001\bI\u0006$\u0018m]3ua\u0011\t)\"!\t\u0011\r\u0005]\u0011\u0011DA\u000f\u001b\t\t9!\u0003\u0003\u0002\u001c\u0005\u001d!a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003?\t\t\u0003\u0004\u0001\u0005\u0019\u0005\r\u0012qBA\u0001\u0002\u0003\u0015\t!!\n\u0003\u0007}#\u0013'\u0005\u0003\u0002(\u00055\u0002c\u0001\u0018\u0002*%\u0019\u00111F\u0018\u0003\u000f9{G\u000f[5oOB\u0019a&a\f\n\u0007\u0005ErFA\u0002B]f\fQa\u001e:ji\u0016,\"!a\u000e\u0011\u0007Q\nI$C\u0002\u0002<U\u0012\u0001\"\u0014'Xe&$XM]\u0001\u001e\t\u0016\u001c\u0017n]5p]R\u0013X-Z\"mCN\u001c\u0018NZ5fe^\u0013\u0018\r\u001d9feB\u0011\u0001,E\n\u0005#5\n\u0019\u0005\u0005\u00035\u0003\u000b:\u0016bAA$k\tQQ\n\u0014*fC\u0012\f'\r\\3\u0015\u0005\u0005}\u0012!\u0007)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0013:#U\tW0D\u001f2+\"!a\u0014\u0011\t\u0005E\u00131L\u0007\u0003\u0003'RA!!\u0016\u0002X\u0005!A.\u00198h\u0015\t\tI&\u0001\u0003kCZ\f\u0017b\u0001'\u0002T\u0005Q\u0002KU#E\u0013\u000e#V\tR0M\u0003\n+EjX%O\t\u0016CvlQ(MA\u0005\u0019\u0002KU#E\u0013\u000e#V\tR0M\u0003\n+EjX\"P\u0019\u0006!\u0002KU#E\u0013\u000e#V\tR0M\u0003\n+EjX\"P\u0019\u0002\n1AZ5u)e9\u0016qMA6\u0003[\ny'a\u001d\u0002x\u0005m\u0014QQAE\u0003\u001b\u000b\t*a'\t\r\u0005%t\u00031\u0001w\u0003\u0011!\u0017\r^1\t\u000b\u0005;\u0002\u0019A\"\t\u000bI<\u0002\u0019A4\t\r\u0005Et\u00031\u0001h\u0003\u001di\u0017\r\u001f\"j]NDa!!\u001e\u0018\u0001\u0004\u0019\u0015\u0001C5naV\u0014\u0018\u000e^=\t\r\u0005et\u00031\u0001h\u0003Mi\u0017N\\%ogR\fgnY3t!\u0016\u0014hj\u001c3f\u0011\u001d\tih\u0006a\u0001\u0003\u007f\n1\"\\5o\u0013:4wnR1j]B\u0019a&!!\n\u0007\u0005\ruF\u0001\u0004E_V\u0014G.\u001a\u0005\u0007\u0003\u000f;\u0002\u0019A4\u0002%\rDWmY6q_&tG/\u00138uKJ4\u0018\r\u001c\u0005\u0007\u0003\u0017;\u0002\u0019A\"\u0002\tM,W\r\u001a\u0005\u0007\u0003\u001f;\u0002\u0019A4\u0002\u001b5\f\u00070T3n_JL\u0018J\\'C\u0011\u001d\t\u0019j\u0006a\u0001\u0003+\u000bAbY1dQ\u0016tu\u000eZ3JIN\u00042ALAL\u0013\r\tIj\f\u0002\b\u0005>|G.Z1o\u0011\u0019\tij\u0006a\u0001\u0007\u0006i\u0001.\u00198eY\u0016LeN^1mS\u0012\fAA]3bIV\u0011\u00111\u0015\t\u0005i\u0005\u0015v+C\u0002\u0002(V\u0012\u0001\"\u0014'SK\u0006$WM]\u0001\u0005Y>\fG\rF\u0002X\u0003[Ca!a,\u001a\u0001\u0004\u0019\u0015\u0001\u00029bi\"\u00141\u0005R3dSNLwN\u001c+sK\u0016\u001cE.Y:tS\u001aLWM],sCB\u0004XM],sSR,'oE\u0002\u001b\u0003o\t\u0001\"\u001b8ti\u0006t7-\u001a\u000b\u0005\u0003s\u000bi\fE\u0002\u0002<ji\u0011!\u0005\u0005\u0007\u0003kc\u0002\u0019A,\u0002\u0011M\fg/Z%na2$B!a1\u0002JB\u0019a&!2\n\u0007\u0005\u001dwF\u0001\u0003V]&$\bBBAX;\u0001\u00071IA\u0012EK\u000eL7/[8o)J,Wm\u00117bgNLg-[3s/J\f\u0007\u000f]3s%\u0016\fG-\u001a:\u0014\u0007y\t\u0019\u000b\u0006\u0002\u0002RB\u0019\u00111\u0018\u0010\u0015\u0007]\u000b)\u000e\u0003\u0004\u00020\u0002\u0002\ra\u0011"
)
public class DecisionTreeClassifierWrapper implements MLWritable {
   private int numFeatures;
   private Vector featureImportances;
   private int maxDepth;
   private final PipelineModel pipeline;
   private final String formula;
   private final String[] features;
   private final DecisionTreeClassificationModel dtcModel;
   private volatile byte bitmap$0;

   public static DecisionTreeClassifierWrapper load(final String path) {
      return DecisionTreeClassifierWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return DecisionTreeClassifierWrapper$.MODULE$.read();
   }

   public static DecisionTreeClassifierWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final String impurity, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String seed, final int maxMemoryInMB, final boolean cacheNodeIds, final String handleInvalid) {
      return DecisionTreeClassifierWrapper$.MODULE$.fit(data, formula, maxDepth, maxBins, impurity, minInstancesPerNode, minInfoGain, checkpointInterval, seed, maxMemoryInMB, cacheNodeIds, handleInvalid);
   }

   public static String PREDICTED_LABEL_COL() {
      return DecisionTreeClassifierWrapper$.MODULE$.PREDICTED_LABEL_COL();
   }

   public static String PREDICTED_LABEL_INDEX_COL() {
      return DecisionTreeClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL();
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

   private DecisionTreeClassificationModel dtcModel() {
      return this.dtcModel;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.numFeatures = this.dtcModel().numFeatures();
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
            this.featureImportances = this.dtcModel().featureImportances();
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
            this.maxDepth = this.dtcModel().getMaxDepth();
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
      return this.dtcModel().toDebugString();
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(DecisionTreeClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL()).drop(this.dtcModel().getFeaturesCol()).drop(this.dtcModel().getLabelCol());
   }

   public MLWriter write() {
      return new DecisionTreeClassifierWrapperWriter(this);
   }

   public DecisionTreeClassifierWrapper(final PipelineModel pipeline, final String formula, final String[] features) {
      this.pipeline = pipeline;
      this.formula = formula;
      this.features = features;
      MLWritable.$init$(this);
      this.dtcModel = (DecisionTreeClassificationModel)pipeline.stages()[1];
   }

   public static class DecisionTreeClassifierWrapperWriter extends MLWriter {
      private final DecisionTreeClassifierWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("formula"), this.instance.formula()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DecisionTreeClassifierWrapperWriter.class.getClassLoader());

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

      public DecisionTreeClassifierWrapperWriter(final DecisionTreeClassifierWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class DecisionTreeClassifierWrapperReader extends MLReader {
      public DecisionTreeClassifierWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String formula = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "formula")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         return new DecisionTreeClassifierWrapper(pipeline, formula, features);
      }
   }
}
