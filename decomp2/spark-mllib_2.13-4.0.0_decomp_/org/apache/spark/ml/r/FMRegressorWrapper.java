package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.regression.FMRegressionModel;
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

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f!B\u000e\u001d\u0001q1\u0003\u0002C\u001a\u0001\u0005\u000b\u0007I\u0011A\u001b\t\u0011i\u0002!\u0011!Q\u0001\nYB\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005{!)A\n\u0001C\u0005\u001b\"9!\u000b\u0001b\u0001\n\u0013\u0019\u0006B\u0002.\u0001A\u0003%A\u000b\u0003\u0005\\\u0001!\u0015\r\u0011\"\u0001=\u0011!a\u0006\u0001#b\u0001\n\u0003i\u0006\u0002\u00032\u0001\u0011\u000b\u0007I\u0011A/\t\u0011\r\u0004\u0001R1A\u0005\u0002\u0011D\u0001\u0002\u001b\u0001\t\u0006\u0004%\t\u0001\u001a\u0005\u0006S\u0002!\tA\u001b\u0005\b\u0003;\u0001A\u0011IA\u0010\u000f!\t9\u0003\bE\u00019\u0005%baB\u000e\u001d\u0011\u0003a\u00121\u0006\u0005\u0007\u0019B!\t!a\r\t\u000f\u0005U\u0002\u0003\"\u0001\u00028!9\u0011\u0011\u000f\t\u0005B\u0005MdABA>!\u0001\ti\bC\u0005\u0002\u0000Q\u0011\t\u0011)A\u0005\u001d\"1A\n\u0006C\u0001\u0003\u0003Cq!!#\u0015\t#\nYI\u0002\u0004\u0002\u0018B\u0001\u0011\u0011\u0014\u0005\u0007\u0019b!\t!a'\t\u000f\u0005}\u0005\u0004\"\u0011\u0002\"\n\u0011b)\u0014*fOJ,7o]8s/J\f\u0007\u000f]3s\u0015\tib$A\u0001s\u0015\ty\u0002%\u0001\u0002nY*\u0011\u0011EI\u0001\u0006gB\f'o\u001b\u0006\u0003G\u0011\na!\u00199bG\",'\"A\u0013\u0002\u0007=\u0014xmE\u0002\u0001O5\u0002\"\u0001K\u0016\u000e\u0003%R\u0011AK\u0001\u0006g\u000e\fG.Y\u0005\u0003Y%\u0012a!\u00118z%\u00164\u0007C\u0001\u00182\u001b\u0005y#B\u0001\u0019\u001f\u0003\u0011)H/\u001b7\n\u0005Iz#AC'M/JLG/\u00192mK\u0006A\u0001/\u001b9fY&tWm\u0001\u0001\u0016\u0003Y\u0002\"a\u000e\u001d\u000e\u0003yI!!\u000f\u0010\u0003\u001bAK\u0007/\u001a7j]\u0016lu\u000eZ3m\u0003%\u0001\u0018\u000e]3mS:,\u0007%\u0001\u0005gK\u0006$XO]3t+\u0005i\u0004c\u0001\u0015?\u0001&\u0011q(\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003\u0003\"s!A\u0011$\u0011\u0005\rKS\"\u0001#\u000b\u0005\u0015#\u0014A\u0002\u001fs_>$h(\u0003\u0002HS\u00051\u0001K]3eK\u001aL!!\u0013&\u0003\rM#(/\u001b8h\u0015\t9\u0015&A\u0005gK\u0006$XO]3tA\u00051A(\u001b8jiz\"2A\u0014)R!\ty\u0005!D\u0001\u001d\u0011\u0015\u0019T\u00011\u00017\u0011\u0015YT\u00011\u0001>\u0003E1WNU3he\u0016\u001c8/[8o\u001b>$W\r\\\u000b\u0002)B\u0011Q\u000bW\u0007\u0002-*\u0011qKH\u0001\u000be\u0016<'/Z:tS>t\u0017BA-W\u0005E1UJU3he\u0016\u001c8/[8o\u001b>$W\r\\\u0001\u0013M6\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0007%A\u0005s\r\u0016\fG/\u001e:fg\u0006i!oQ8fM\u001aL7-[3oiN,\u0012A\u0018\t\u0004Qyz\u0006C\u0001\u0015a\u0013\t\t\u0017F\u0001\u0004E_V\u0014G.Z\u0001\te\u001a\u000b7\r^8sg\u0006Ya.^7GK\u0006$XO]3t+\u0005)\u0007C\u0001\u0015g\u0013\t9\u0017FA\u0002J]R\f!BZ1di>\u00148+\u001b>f\u0003%!(/\u00198tM>\u0014X\u000e\u0006\u0002lyB\u0011A.\u001f\b\u0003[Zt!A\u001c;\u000f\u0005=\u001chB\u00019s\u001d\t\u0019\u0015/C\u0001&\u0013\t\u0019C%\u0003\u0002\"E%\u0011Q\u000fI\u0001\u0004gFd\u0017BA<y\u0003\u001d\u0001\u0018mY6bO\u0016T!!\u001e\u0011\n\u0005i\\(!\u0003#bi\u00064%/Y7f\u0015\t9\b\u0010C\u0003~\u001b\u0001\u0007a0A\u0004eCR\f7/\u001a;1\u0007}\fY\u0001\u0005\u0004\u0002\u0002\u0005\r\u0011qA\u0007\u0002q&\u0019\u0011Q\u0001=\u0003\u000f\u0011\u000bG/Y:fiB!\u0011\u0011BA\u0006\u0019\u0001!1\"!\u0004}\u0003\u0003\u0005\tQ!\u0001\u0002\u0010\t\u0019q\fJ\u0019\u0012\t\u0005E\u0011q\u0003\t\u0004Q\u0005M\u0011bAA\u000bS\t9aj\u001c;iS:<\u0007c\u0001\u0015\u0002\u001a%\u0019\u00111D\u0015\u0003\u0007\u0005s\u00170A\u0003xe&$X-\u0006\u0002\u0002\"A\u0019a&a\t\n\u0007\u0005\u0015rF\u0001\u0005N\u0019^\u0013\u0018\u000e^3s\u0003I1UJU3he\u0016\u001c8o\u001c:Xe\u0006\u0004\b/\u001a:\u0011\u0005=\u00032\u0003\u0002\t(\u0003[\u0001BALA\u0018\u001d&\u0019\u0011\u0011G\u0018\u0003\u00155c%+Z1eC\ndW\r\u0006\u0002\u0002*\u0005\u0019a-\u001b;\u001579\u000bI$!\u0010\u0002B\u0005\r\u0013QJA)\u0003+\nI&!\u0018\u0002b\u0005\u0015\u0014\u0011NA7\u0011\u0019\tYD\u0005a\u0001W\u0006!A-\u0019;b\u0011\u0019\tyD\u0005a\u0001\u0001\u00069am\u001c:nk2\f\u0007\"\u00025\u0013\u0001\u0004)\u0007bBA#%\u0001\u0007\u0011qI\u0001\nM&$H*\u001b8fCJ\u00042\u0001KA%\u0013\r\tY%\u000b\u0002\b\u0005>|G.Z1o\u0011\u0019\tyE\u0005a\u0001?\u0006A!/Z4QCJ\fW\u000e\u0003\u0004\u0002TI\u0001\raX\u0001\u0012[&t\u0017NQ1uG\"4%/Y2uS>t\u0007BBA,%\u0001\u0007q,A\u0004j]&$8\u000b\u001e3\t\r\u0005m#\u00031\u0001f\u0003\u001di\u0017\r_%uKJDa!a\u0018\u0013\u0001\u0004y\u0016\u0001C:uKB\u001c\u0016N_3\t\r\u0005\r$\u00031\u0001`\u0003\r!x\u000e\u001c\u0005\u0007\u0003O\u0012\u0002\u0019\u0001!\u0002\rM|GN^3s\u0011\u0019\tYG\u0005a\u0001\u0001\u0006!1/Z3e\u0011\u0019\tyG\u0005a\u0001\u0001\u000612\u000f\u001e:j]\u001eLe\u000eZ3yKJ|%\u000fZ3s)f\u0004X-\u0001\u0003sK\u0006$WCAA;!\u0011q\u0013q\u000f(\n\u0007\u0005etF\u0001\u0005N\u0019J+\u0017\rZ3s\u0005a1UJU3he\u0016\u001c8o\u001c:Xe\u0006\u0004\b/\u001a:Xe&$XM]\n\u0004)\u0005\u0005\u0012\u0001C5ogR\fgnY3\u0015\t\u0005\r\u0015q\u0011\t\u0004\u0003\u000b#R\"\u0001\t\t\r\u0005}d\u00031\u0001O\u0003!\u0019\u0018M^3J[BdG\u0003BAG\u0003'\u00032\u0001KAH\u0013\r\t\t*\u000b\u0002\u0005+:LG\u000f\u0003\u0004\u0002\u0016^\u0001\r\u0001Q\u0001\u0005a\u0006$\bN\u0001\rG\u001bJ+wM]3tg>\u0014xK]1qa\u0016\u0014(+Z1eKJ\u001c2\u0001GA;)\t\ti\nE\u0002\u0002\u0006b\tA\u0001\\8bIR\u0019a*a)\t\r\u0005U%\u00041\u0001A\u0001"
)
public class FMRegressorWrapper implements MLWritable {
   private String[] rFeatures;
   private double[] rCoefficients;
   private double[] rFactors;
   private int numFeatures;
   private int factorSize;
   private final PipelineModel pipeline;
   private final String[] features;
   private final FMRegressionModel fmRegressionModel;
   private volatile byte bitmap$0;

   public static MLReader read() {
      return FMRegressorWrapper$.MODULE$.read();
   }

   public static FMRegressorWrapper fit(final Dataset data, final String formula, final int factorSize, final boolean fitLinear, final double regParam, final double miniBatchFraction, final double initStd, final int maxIter, final double stepSize, final double tol, final String solver, final String seed, final String stringIndexerOrderType) {
      return FMRegressorWrapper$.MODULE$.fit(data, formula, factorSize, fitLinear, regParam, miniBatchFraction, initStd, maxIter, stepSize, tol, solver, seed, stringIndexerOrderType);
   }

   public static Object load(final String path) {
      return FMRegressorWrapper$.MODULE$.load(path);
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

   private FMRegressionModel fmRegressionModel() {
      return this.fmRegressionModel;
   }

   private String[] rFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.rFeatures = this.fmRegressionModel().getFitIntercept() ? (String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"(Intercept)"})), this.features(), scala.reflect.ClassTag..MODULE$.apply(String.class)) : this.features();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rFeatures;
   }

   public String[] rFeatures() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.rFeatures$lzycompute() : this.rFeatures;
   }

   private double[] rCoefficients$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.rCoefficients = this.fmRegressionModel().getFitIntercept() ? (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps(new double[]{this.fmRegressionModel().intercept()}), this.fmRegressionModel().linear().toArray(), scala.reflect.ClassTag..MODULE$.Double()) : this.fmRegressionModel().linear().toArray();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rCoefficients;
   }

   public double[] rCoefficients() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.rCoefficients$lzycompute() : this.rCoefficients;
   }

   private double[] rFactors$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.rFactors = this.fmRegressionModel().factors().toArray();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rFactors;
   }

   public double[] rFactors() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.rFactors$lzycompute() : this.rFactors;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.numFeatures = this.fmRegressionModel().numFeatures();
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numFeatures;
   }

   public int numFeatures() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.numFeatures$lzycompute() : this.numFeatures;
   }

   private int factorSize$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.factorSize = this.fmRegressionModel().getFactorSize();
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.factorSize;
   }

   public int factorSize() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.factorSize$lzycompute() : this.factorSize;
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.fmRegressionModel().getFeaturesCol());
   }

   public MLWriter write() {
      return new FMRegressorWrapperWriter(this);
   }

   public FMRegressorWrapper(final PipelineModel pipeline, final String[] features) {
      this.pipeline = pipeline;
      this.features = features;
      MLWritable.$init$(this);
      this.fmRegressionModel = (FMRegressionModel)pipeline.stages()[1];
   }

   public static class FMRegressorWrapperWriter extends MLWriter {
      private final FMRegressorWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(FMRegressorWrapperWriter.class.getClassLoader());

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

      public FMRegressorWrapperWriter(final FMRegressorWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class FMRegressorWrapperReader extends MLReader {
      public FMRegressorWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new FMRegressorWrapper(pipeline, features);
      }
   }
}
