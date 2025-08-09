package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.classification.RandomForestClassificationModel;
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
   bytes = "\u0006\u0005\u0005Eh!B\u0012%\u0001\u0011r\u0003\u0002C\u001e\u0001\u0005\u000b\u0007I\u0011A\u001f\t\u0011\t\u0003!\u0011!Q\u0001\nyB\u0001b\u0011\u0001\u0003\u0006\u0004%\t\u0001\u0012\u0005\t!\u0002\u0011\t\u0011)A\u0005\u000b\"A\u0011\u000b\u0001BC\u0002\u0013\u0005!\u000b\u0003\u0005W\u0001\t\u0005\t\u0015!\u0003T\u0011\u00159\u0006\u0001\"\u0003Y\u0011\u001dq\u0006A1A\u0005\n}CaA\u001a\u0001!\u0002\u0013\u0001\u0007\u0002C4\u0001\u0011\u000b\u0007I\u0011\u00015\t\u00111\u0004\u0001R1A\u0005\u00025D\u0001\u0002\u001e\u0001\t\u0006\u0004%\t\u0001\u001b\u0005\tk\u0002A)\u0019!C\u0001m\"A1\u0010\u0001EC\u0002\u0013\u0005\u0001\u000eC\u0003}\u0001\u0011\u0005A\tC\u0003~\u0001\u0011\u0005a\u0010C\u0004\u0002F\u0001!\t%a\u0012\b\u0011\u0005=C\u0005#\u0001%\u0003#2qa\t\u0013\t\u0002\u0011\n\u0019\u0006\u0003\u0004X'\u0011\u0005\u00111\f\u0005\n\u0003;\u001a\"\u0019!C\u0001\u0003?B\u0001\"a\u001c\u0014A\u0003%\u0011\u0011\r\u0005\n\u0003c\u001a\"\u0019!C\u0001\u0003?B\u0001\"a\u001d\u0014A\u0003%\u0011\u0011\r\u0005\b\u0003k\u001aB\u0011AA<\u0011\u001d\tIl\u0005C!\u0003wCq!a1\u0014\t\u0003\n)M\u0002\u0004\u0002LN\u0001\u0011Q\u001a\u0005\n\u0003\u001fd\"\u0011!Q\u0001\neCaa\u0016\u000f\u0005\u0002\u0005E\u0007bBAm9\u0011E\u00131\u001c\u0004\u0007\u0003K\u001c\u0002!a:\t\r]\u0003C\u0011AAu\u0011\u001d\t\u0019\r\tC!\u0003[\u0014QDU1oI>lgi\u001c:fgR\u001cE.Y:tS\u001aLWM],sCB\u0004XM\u001d\u0006\u0003K\u0019\n\u0011A\u001d\u0006\u0003O!\n!!\u001c7\u000b\u0005%R\u0013!B:qCJ\\'BA\u0016-\u0003\u0019\t\u0007/Y2iK*\tQ&A\u0002pe\u001e\u001c2\u0001A\u00186!\t\u00014'D\u00012\u0015\u0005\u0011\u0014!B:dC2\f\u0017B\u0001\u001b2\u0005\u0019\te.\u001f*fMB\u0011a'O\u0007\u0002o)\u0011\u0001HJ\u0001\u0005kRLG.\u0003\u0002;o\tQQ\nT,sSR\f'\r\\3\u0002\u0011AL\u0007/\u001a7j]\u0016\u001c\u0001!F\u0001?!\ty\u0004)D\u0001'\u0013\t\teEA\u0007QSB,G.\u001b8f\u001b>$W\r\\\u0001\na&\u0004X\r\\5oK\u0002\nqAZ8s[Vd\u0017-F\u0001F!\t1UJ\u0004\u0002H\u0017B\u0011\u0001*M\u0007\u0002\u0013*\u0011!\nP\u0001\u0007yI|w\u000e\u001e \n\u00051\u000b\u0014A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n11\u000b\u001e:j]\u001eT!\u0001T\u0019\u0002\u0011\u0019|'/\\;mC\u0002\n\u0001BZ3biV\u0014Xm]\u000b\u0002'B\u0019\u0001\u0007V#\n\u0005U\u000b$!B!se\u0006L\u0018!\u00034fCR,(/Z:!\u0003\u0019a\u0014N\\5u}Q!\u0011l\u0017/^!\tQ\u0006!D\u0001%\u0011\u0015Yt\u00011\u0001?\u0011\u0015\u0019u\u00011\u0001F\u0011\u0015\tv\u00011\u0001T\u0003!\u0011hmY'pI\u0016dW#\u00011\u0011\u0005\u0005$W\"\u00012\u000b\u0005\r4\u0013AD2mCN\u001c\u0018NZ5dCRLwN\\\u0005\u0003K\n\u0014qDU1oI>lgi\u001c:fgR\u001cE.Y:tS\u001aL7-\u0019;j_:lu\u000eZ3m\u0003%\u0011hmY'pI\u0016d\u0007%A\u0006ok64U-\u0019;ve\u0016\u001cX#A5\u0011\u0005AR\u0017BA62\u0005\rIe\u000e^\u0001\u0013M\u0016\fG/\u001e:f\u00136\u0004xN\u001d;b]\u000e,7/F\u0001o!\ty'/D\u0001q\u0015\t\th%\u0001\u0004mS:\fGnZ\u0005\u0003gB\u0014aAV3di>\u0014\u0018\u0001\u00038v[R\u0013X-Z:\u0002\u0017Q\u0014X-Z,fS\u001eDGo]\u000b\u0002oB\u0019\u0001\u0007\u0016=\u0011\u0005AJ\u0018B\u0001>2\u0005\u0019!u.\u001e2mK\u0006AQ.\u0019=EKB$\b.A\u0004tk6l\u0017M]=\u0002\u0013Q\u0014\u0018M\\:g_JlGcA@\u0002\"A!\u0011\u0011AA\u000e\u001d\u0011\t\u0019!!\u0006\u000f\t\u0005\u0015\u0011\u0011\u0003\b\u0005\u0003\u000f\tyA\u0004\u0003\u0002\n\u00055ab\u0001%\u0002\f%\tQ&\u0003\u0002,Y%\u0011\u0011FK\u0005\u0004\u0003'A\u0013aA:rY&!\u0011qCA\r\u0003\u001d\u0001\u0018mY6bO\u0016T1!a\u0005)\u0013\u0011\ti\"a\b\u0003\u0013\u0011\u000bG/\u0019$sC6,'\u0002BA\f\u00033Aq!a\t\u0011\u0001\u0004\t)#A\u0004eCR\f7/\u001a;1\t\u0005\u001d\u00121\u0007\t\u0007\u0003S\tY#a\f\u000e\u0005\u0005e\u0011\u0002BA\u0017\u00033\u0011q\u0001R1uCN,G\u000f\u0005\u0003\u00022\u0005MB\u0002\u0001\u0003\r\u0003k\t\t#!A\u0001\u0002\u000b\u0005\u0011q\u0007\u0002\u0004?\u0012\n\u0014\u0003BA\u001d\u0003\u007f\u00012\u0001MA\u001e\u0013\r\ti$\r\u0002\b\u001d>$\b.\u001b8h!\r\u0001\u0014\u0011I\u0005\u0004\u0003\u0007\n$aA!os\u0006)qO]5uKV\u0011\u0011\u0011\n\t\u0004m\u0005-\u0013bAA'o\tAQ\nT,sSR,'/A\u000fSC:$w.\u001c$pe\u0016\u001cHo\u00117bgNLg-[3s/J\f\u0007\u000f]3s!\tQ6c\u0005\u0003\u0014_\u0005U\u0003\u0003\u0002\u001c\u0002XeK1!!\u00178\u0005)iEJU3bI\u0006\u0014G.\u001a\u000b\u0003\u0003#\n\u0011\u0004\u0015*F\t&\u001bE+\u0012#`\u0019\u0006\u0013U\tT0J\u001d\u0012+\u0005lX\"P\u0019V\u0011\u0011\u0011\r\t\u0005\u0003G\ni'\u0004\u0002\u0002f)!\u0011qMA5\u0003\u0011a\u0017M\\4\u000b\u0005\u0005-\u0014\u0001\u00026bm\u0006L1ATA3\u0003i\u0001&+\u0012#J\u0007R+Ei\u0018'B\u0005\u0016cu,\u0013(E\u000bb{6i\u0014'!\u0003M\u0001&+\u0012#J\u0007R+Ei\u0018'B\u0005\u0016culQ(M\u0003Q\u0001&+\u0012#J\u0007R+Ei\u0018'B\u0005\u0016culQ(MA\u0005\u0019a-\u001b;\u0015Ce\u000bI(! \u0002\u0000\u0005\u0005\u0015QQAD\u0003\u0017\u000by)a%\u0002\u0018\u0006m\u0015qTAR\u0003O\u000b\t,!.\t\r\u0005m\u0014\u00041\u0001\u0000\u0003\u0011!\u0017\r^1\t\u000b\rK\u0002\u0019A#\t\u000bmL\u0002\u0019A5\t\r\u0005\r\u0015\u00041\u0001j\u0003\u001di\u0017\r\u001f\"j]NDQ\u0001^\rA\u0002%Da!!#\u001a\u0001\u0004)\u0015\u0001C5naV\u0014\u0018\u000e^=\t\r\u00055\u0015\u00041\u0001j\u0003Mi\u0017N\\%ogR\fgnY3t!\u0016\u0014hj\u001c3f\u0011\u0019\t\t*\u0007a\u0001q\u0006YQ.\u001b8J]\u001a|w)Y5o\u0011\u0019\t)*\u0007a\u0001S\u0006\u00112\r[3dWB|\u0017N\u001c;J]R,'O^1m\u0011\u0019\tI*\u0007a\u0001\u000b\u0006)b-Z1ukJ,7+\u001e2tKR\u001cFO]1uK\u001eL\bBBAO3\u0001\u0007Q)\u0001\u0003tK\u0016$\u0007BBAQ3\u0001\u0007\u00010A\btk\n\u001c\u0018-\u001c9mS:<'+\u0019;f\u0011\u0019\t)+\u0007a\u0001S\u0006iQ.\u0019=NK6|'/_%o\u001b\nCq!!+\u001a\u0001\u0004\tY+\u0001\u0007dC\u000eDWMT8eK&#7\u000fE\u00021\u0003[K1!a,2\u0005\u001d\u0011un\u001c7fC:Da!a-\u001a\u0001\u0004)\u0015!\u00045b]\u0012dW-\u00138wC2LG\rC\u0004\u00028f\u0001\r!a+\u0002\u0013\t|w\u000e^:ue\u0006\u0004\u0018\u0001\u0002:fC\u0012,\"!!0\u0011\tY\ny,W\u0005\u0004\u0003\u0003<$\u0001C'M%\u0016\fG-\u001a:\u0002\t1|\u0017\r\u001a\u000b\u00043\u0006\u001d\u0007BBAe7\u0001\u0007Q)\u0001\u0003qCRD'a\t*b]\u0012|WNR8sKN$8\t\\1tg&4\u0017.\u001a:Xe\u0006\u0004\b/\u001a:Xe&$XM]\n\u00049\u0005%\u0013\u0001C5ogR\fgnY3\u0015\t\u0005M\u0017q\u001b\t\u0004\u0003+dR\"A\n\t\r\u0005=g\u00041\u0001Z\u0003!\u0019\u0018M^3J[BdG\u0003BAo\u0003G\u00042\u0001MAp\u0013\r\t\t/\r\u0002\u0005+:LG\u000f\u0003\u0004\u0002J~\u0001\r!\u0012\u0002$%\u0006tGm\\7G_J,7\u000f^\"mCN\u001c\u0018NZ5fe^\u0013\u0018\r\u001d9feJ+\u0017\rZ3s'\r\u0001\u0013Q\u0018\u000b\u0003\u0003W\u00042!!6!)\rI\u0016q\u001e\u0005\u0007\u0003\u0013\u0014\u0003\u0019A#"
)
public class RandomForestClassifierWrapper implements MLWritable {
   private int numFeatures;
   private Vector featureImportances;
   private int numTrees;
   private double[] treeWeights;
   private int maxDepth;
   private final PipelineModel pipeline;
   private final String formula;
   private final String[] features;
   private final RandomForestClassificationModel rfcModel;
   private volatile byte bitmap$0;

   public static RandomForestClassifierWrapper load(final String path) {
      return RandomForestClassifierWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RandomForestClassifierWrapper$.MODULE$.read();
   }

   public static RandomForestClassifierWrapper fit(final Dataset data, final String formula, final int maxDepth, final int maxBins, final int numTrees, final String impurity, final int minInstancesPerNode, final double minInfoGain, final int checkpointInterval, final String featureSubsetStrategy, final String seed, final double subsamplingRate, final int maxMemoryInMB, final boolean cacheNodeIds, final String handleInvalid, final boolean bootstrap) {
      return RandomForestClassifierWrapper$.MODULE$.fit(data, formula, maxDepth, maxBins, numTrees, impurity, minInstancesPerNode, minInfoGain, checkpointInterval, featureSubsetStrategy, seed, subsamplingRate, maxMemoryInMB, cacheNodeIds, handleInvalid, bootstrap);
   }

   public static String PREDICTED_LABEL_COL() {
      return RandomForestClassifierWrapper$.MODULE$.PREDICTED_LABEL_COL();
   }

   public static String PREDICTED_LABEL_INDEX_COL() {
      return RandomForestClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL();
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

   private RandomForestClassificationModel rfcModel() {
      return this.rfcModel;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.numFeatures = this.rfcModel().numFeatures();
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
            this.featureImportances = this.rfcModel().featureImportances();
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
            this.numTrees = this.rfcModel().getNumTrees();
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
            this.treeWeights = this.rfcModel().treeWeights();
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
            this.maxDepth = this.rfcModel().getMaxDepth();
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
      return this.rfcModel().toDebugString();
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(RandomForestClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL()).drop(this.rfcModel().getFeaturesCol()).drop(this.rfcModel().getLabelCol());
   }

   public MLWriter write() {
      return new RandomForestClassifierWrapperWriter(this);
   }

   public RandomForestClassifierWrapper(final PipelineModel pipeline, final String formula, final String[] features) {
      this.pipeline = pipeline;
      this.formula = formula;
      this.features = features;
      MLWritable.$init$(this);
      this.rfcModel = (RandomForestClassificationModel)pipeline.stages()[1];
   }

   public static class RandomForestClassifierWrapperWriter extends MLWriter {
      private final RandomForestClassifierWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("formula"), this.instance.formula()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RandomForestClassifierWrapperWriter.class.getClassLoader());

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

      public RandomForestClassifierWrapperWriter(final RandomForestClassifierWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class RandomForestClassifierWrapperReader extends MLReader {
      public RandomForestClassifierWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String formula = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "formula")), format, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         return new RandomForestClassifierWrapper(pipeline, formula, features);
      }
   }
}
