package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.classification.FMClassificationModel;
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
   bytes = "\u0006\u0005\u0005]g!\u0002\u0012$\u0001\rj\u0003\u0002\u0003\u001e\u0001\u0005\u000b\u0007I\u0011\u0001\u001f\t\u0011\u0005\u0003!\u0011!Q\u0001\nuB\u0001B\u0011\u0001\u0003\u0006\u0004%\ta\u0011\u0005\t%\u0002\u0011\t\u0011)A\u0005\t\"A1\u000b\u0001BC\u0002\u0013\u00051\t\u0003\u0005U\u0001\t\u0005\t\u0015!\u0003E\u0011\u0015)\u0006\u0001\"\u0003W\u0011\u001da\u0006A1A\u0005\nuCa\u0001\u001a\u0001!\u0002\u0013q\u0006\u0002C3\u0001\u0011\u000b\u0007I\u0011A\"\t\u0011\u0019\u0004\u0001R1A\u0005\u0002\u001dD\u0001\u0002\u001c\u0001\t\u0006\u0004%\ta\u001a\u0005\t[\u0002A)\u0019!C\u0001]\"A!\u000f\u0001EC\u0002\u0013\u0005a\u000e\u0003\u0005t\u0001!\u0015\r\u0011\"\u0001o\u0011\u0015!\b\u0001\"\u0001v\u0011\u001d\t\u0019\u0004\u0001C!\u0003k9\u0001\"!\u0010$\u0011\u0003\u0019\u0013q\b\u0004\bE\rB\taIA!\u0011\u0019)6\u0003\"\u0001\u0002J!I\u00111J\nC\u0002\u0013\u0005\u0011Q\n\u0005\t\u0003;\u001a\u0002\u0015!\u0003\u0002P!I\u0011qL\nC\u0002\u0013\u0005\u0011Q\n\u0005\t\u0003C\u001a\u0002\u0015!\u0003\u0002P!9\u00111M\n\u0005\u0002\u0005\u0015\u0004bBAR'\u0011\u0005\u0013Q\u0015\u0004\u0007\u0003[\u001b\u0002!a,\t\u0013\u0005E6D!A!\u0002\u00139\u0006BB+\u001c\t\u0003\t\u0019\fC\u0004\u0002<n!\t&!0\u0007\r\u0005%7\u0003AAf\u0011\u0019)v\u0004\"\u0001\u0002N\"9\u0011\u0011[\u0010\u0005B\u0005M'a\u0005$N\u00072\f7o]5gS\u0016\u0014xK]1qa\u0016\u0014(B\u0001\u0013&\u0003\u0005\u0011(B\u0001\u0014(\u0003\tiGN\u0003\u0002)S\u0005)1\u000f]1sW*\u0011!fK\u0001\u0007CB\f7\r[3\u000b\u00031\n1a\u001c:h'\r\u0001a\u0006\u000e\t\u0003_Ij\u0011\u0001\r\u0006\u0002c\u0005)1oY1mC&\u00111\u0007\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005UBT\"\u0001\u001c\u000b\u0005]*\u0013\u0001B;uS2L!!\u000f\u001c\u0003\u00155cuK]5uC\ndW-\u0001\u0005qSB,G.\u001b8f\u0007\u0001)\u0012!\u0010\t\u0003}}j\u0011!J\u0005\u0003\u0001\u0016\u0012Q\u0002U5qK2Lg.Z'pI\u0016d\u0017!\u00039ja\u0016d\u0017N\\3!\u0003!1W-\u0019;ve\u0016\u001cX#\u0001#\u0011\u0007=*u)\u0003\u0002Ga\t)\u0011I\u001d:bsB\u0011\u0001j\u0014\b\u0003\u00136\u0003\"A\u0013\u0019\u000e\u0003-S!\u0001T\u001e\u0002\rq\u0012xn\u001c;?\u0013\tq\u0005'\u0001\u0004Qe\u0016$WMZ\u0005\u0003!F\u0013aa\u0015;sS:<'B\u0001(1\u0003%1W-\u0019;ve\u0016\u001c\b%\u0001\u0004mC\n,Gn]\u0001\bY\u0006\u0014W\r\\:!\u0003\u0019a\u0014N\\5u}Q!q+\u0017.\\!\tA\u0006!D\u0001$\u0011\u0015Qt\u00011\u0001>\u0011\u0015\u0011u\u00011\u0001E\u0011\u0015\u0019v\u00011\u0001E\u0003U1Wn\u00117bgNLg-[2bi&|g.T8eK2,\u0012A\u0018\t\u0003?\nl\u0011\u0001\u0019\u0006\u0003C\u0016\nab\u00197bgNLg-[2bi&|g.\u0003\u0002dA\n)b)T\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d\u0017A\u00064n\u00072\f7o]5gS\u000e\fG/[8o\u001b>$W\r\u001c\u0011\u0002\u0013I4U-\u0019;ve\u0016\u001c\u0018!\u0004:D_\u00164g-[2jK:$8/F\u0001i!\ryS)\u001b\t\u0003_)L!a\u001b\u0019\u0003\r\u0011{WO\u00197f\u0003!\u0011h)Y2u_J\u001c\u0018A\u00038v[\u000ec\u0017m]:fgV\tq\u000e\u0005\u00020a&\u0011\u0011\u000f\r\u0002\u0004\u0013:$\u0018a\u00038v[\u001a+\u0017\r^;sKN\f!BZ1di>\u00148+\u001b>f\u0003%!(/\u00198tM>\u0014X\u000eF\u0002w\u0003\u001f\u00012a^A\u0005\u001d\rA\u00181\u0001\b\u0003s~t!A\u001f@\u000f\u0005mlhB\u0001&}\u0013\u0005a\u0013B\u0001\u0016,\u0013\tA\u0013&C\u0002\u0002\u0002\u001d\n1a]9m\u0013\u0011\t)!a\u0002\u0002\u000fA\f7m[1hK*\u0019\u0011\u0011A\u0014\n\t\u0005-\u0011Q\u0002\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!!\u0002\u0002\b!9\u0011\u0011\u0003\tA\u0002\u0005M\u0011a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003+\t\t\u0003\u0005\u0004\u0002\u0018\u0005e\u0011QD\u0007\u0003\u0003\u000fIA!a\u0007\u0002\b\t9A)\u0019;bg\u0016$\b\u0003BA\u0010\u0003Ca\u0001\u0001\u0002\u0007\u0002$\u0005=\u0011\u0011!A\u0001\u0006\u0003\t)CA\u0002`IE\nB!a\n\u0002.A\u0019q&!\u000b\n\u0007\u0005-\u0002GA\u0004O_RD\u0017N\\4\u0011\u0007=\ny#C\u0002\u00022A\u00121!\u00118z\u0003\u00159(/\u001b;f+\t\t9\u0004E\u00026\u0003sI1!a\u000f7\u0005!iEj\u0016:ji\u0016\u0014\u0018a\u0005$N\u00072\f7o]5gS\u0016\u0014xK]1qa\u0016\u0014\bC\u0001-\u0014'\u0011\u0019b&a\u0011\u0011\tU\n)eV\u0005\u0004\u0003\u000f2$AC'M%\u0016\fG-\u00192mKR\u0011\u0011qH\u0001\u001a!J+E)S\"U\u000b\u0012{F*\u0011\"F\u0019~Ke\nR#Y?\u000e{E*\u0006\u0002\u0002PA!\u0011\u0011KA.\u001b\t\t\u0019F\u0003\u0003\u0002V\u0005]\u0013\u0001\u00027b]\u001eT!!!\u0017\u0002\t)\fg/Y\u0005\u0004!\u0006M\u0013A\u0007)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0013:#U\tW0D\u001f2\u0003\u0013a\u0005)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0007>c\u0015\u0001\u0006)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0007>c\u0005%A\u0002gSR$RdVA4\u0003W\ny'!\u001d\u0002|\u0005}\u00141QAD\u0003\u0017\u000by)a%\u0002\u0018\u0006m\u0015q\u0014\u0005\u0007\u0003SJ\u0002\u0019\u0001<\u0002\t\u0011\fG/\u0019\u0005\u0007\u0003[J\u0002\u0019A$\u0002\u000f\u0019|'/\\;mC\")1/\u0007a\u0001_\"9\u00111O\rA\u0002\u0005U\u0014!\u00034ji2Kg.Z1s!\ry\u0013qO\u0005\u0004\u0003s\u0002$a\u0002\"p_2,\u0017M\u001c\u0005\u0007\u0003{J\u0002\u0019A5\u0002\u0011I,w\rU1sC6Da!!!\u001a\u0001\u0004I\u0017!E7j]&\u0014\u0015\r^2i\rJ\f7\r^5p]\"1\u0011QQ\rA\u0002%\fq!\u001b8jiN#H\r\u0003\u0004\u0002\nf\u0001\ra\\\u0001\b[\u0006D\u0018\n^3s\u0011\u0019\ti)\u0007a\u0001S\u0006A1\u000f^3q'&TX\r\u0003\u0004\u0002\u0012f\u0001\r![\u0001\u0004i>d\u0007BBAK3\u0001\u0007q)\u0001\u0004t_24XM\u001d\u0005\u0007\u00033K\u0002\u0019A$\u0002\tM,W\r\u001a\u0005\u0007\u0003;K\u0002\u0019\u00015\u0002\u0015QD'/Z:i_2$7\u000f\u0003\u0004\u0002\"f\u0001\raR\u0001\u000eQ\u0006tG\r\\3J]Z\fG.\u001b3\u0002\tI,\u0017\rZ\u000b\u0003\u0003O\u0003B!NAU/&\u0019\u00111\u0016\u001c\u0003\u00115c%+Z1eKJ\u0014\u0011DR'DY\u0006\u001c8/\u001b4jKJ<&/\u00199qKJ<&/\u001b;feN\u00191$a\u000e\u0002\u0011%t7\u000f^1oG\u0016$B!!.\u0002:B\u0019\u0011qW\u000e\u000e\u0003MAa!!-\u001e\u0001\u00049\u0016\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\u0005}\u0016Q\u0019\t\u0004_\u0005\u0005\u0017bAAba\t!QK\\5u\u0011\u0019\t9M\ba\u0001\u000f\u0006!\u0001/\u0019;i\u0005e1Uj\u00117bgNLg-[3s/J\f\u0007\u000f]3s%\u0016\fG-\u001a:\u0014\u0007}\t9\u000b\u0006\u0002\u0002PB\u0019\u0011qW\u0010\u0002\t1|\u0017\r\u001a\u000b\u0004/\u0006U\u0007BBAdC\u0001\u0007q\t"
)
public class FMClassifierWrapper implements MLWritable {
   private String[] rFeatures;
   private double[] rCoefficients;
   private double[] rFactors;
   private int numClasses;
   private int numFeatures;
   private int factorSize;
   private final PipelineModel pipeline;
   private final String[] features;
   private final String[] labels;
   private final FMClassificationModel fmClassificationModel;
   private volatile byte bitmap$0;

   public static MLReader read() {
      return FMClassifierWrapper$.MODULE$.read();
   }

   public static FMClassifierWrapper fit(final Dataset data, final String formula, final int factorSize, final boolean fitLinear, final double regParam, final double miniBatchFraction, final double initStd, final int maxIter, final double stepSize, final double tol, final String solver, final String seed, final double[] thresholds, final String handleInvalid) {
      return FMClassifierWrapper$.MODULE$.fit(data, formula, factorSize, fitLinear, regParam, miniBatchFraction, initStd, maxIter, stepSize, tol, solver, seed, thresholds, handleInvalid);
   }

   public static String PREDICTED_LABEL_COL() {
      return FMClassifierWrapper$.MODULE$.PREDICTED_LABEL_COL();
   }

   public static String PREDICTED_LABEL_INDEX_COL() {
      return FMClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL();
   }

   public static Object load(final String path) {
      return FMClassifierWrapper$.MODULE$.load(path);
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

   public String[] labels() {
      return this.labels;
   }

   private FMClassificationModel fmClassificationModel() {
      return this.fmClassificationModel;
   }

   private String[] rFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.rFeatures = this.fmClassificationModel().getFitIntercept() ? (String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"(Intercept)"})), this.features(), scala.reflect.ClassTag..MODULE$.apply(String.class)) : this.features();
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
            this.rCoefficients = this.fmClassificationModel().getFitIntercept() ? (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps(new double[]{this.fmClassificationModel().intercept()}), this.fmClassificationModel().linear().toArray(), scala.reflect.ClassTag..MODULE$.Double()) : this.fmClassificationModel().linear().toArray();
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
            this.rFactors = this.fmClassificationModel().factors().toArray();
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

   private int numClasses$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.numClasses = this.fmClassificationModel().numClasses();
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numClasses;
   }

   public int numClasses() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.numClasses$lzycompute() : this.numClasses;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.numFeatures = this.fmClassificationModel().numFeatures();
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numFeatures;
   }

   public int numFeatures() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.numFeatures$lzycompute() : this.numFeatures;
   }

   private int factorSize$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 32) == 0) {
            this.factorSize = this.fmClassificationModel().getFactorSize();
            this.bitmap$0 = (byte)(this.bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.factorSize;
   }

   public int factorSize() {
      return (byte)(this.bitmap$0 & 32) == 0 ? this.factorSize$lzycompute() : this.factorSize;
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(FMClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL()).drop(this.fmClassificationModel().getFeaturesCol()).drop(this.fmClassificationModel().getLabelCol());
   }

   public MLWriter write() {
      return new FMClassifierWrapperWriter(this);
   }

   public FMClassifierWrapper(final PipelineModel pipeline, final String[] features, final String[] labels) {
      this.pipeline = pipeline;
      this.features = features;
      this.labels = labels;
      MLWritable.$init$(this);
      this.fmClassificationModel = (FMClassificationModel)pipeline.stages()[1];
   }

   public static class FMClassifierWrapperWriter extends MLWriter {
      private final FMClassifierWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("labels"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.labels()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(FMClassifierWrapperWriter.class.getClassLoader());

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

      public FMClassifierWrapperWriter(final FMClassifierWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class FMClassifierWrapperReader extends MLReader {
      public FMClassifierWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         String[] labels = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "labels")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new FMClassifierWrapper(pipeline, features, labels);
      }
   }
}
