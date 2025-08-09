package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.classification.LinearSVCModel;
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
   bytes = "\u0006\u0005\u0005\u001dg!B\u0011#\u0001\tb\u0003\u0002C\u001d\u0001\u0005\u000b\u0007I\u0011A\u001e\t\u0011\u0001\u0003!\u0011!Q\u0001\nqB\u0001\"\u0011\u0001\u0003\u0006\u0004%\tA\u0011\u0005\t#\u0002\u0011\t\u0011)A\u0005\u0007\"A!\u000b\u0001BC\u0002\u0013\u0005!\t\u0003\u0005T\u0001\t\u0005\t\u0015!\u0003D\u0011\u0015!\u0006\u0001\"\u0003V\u0011\u001dY\u0006A1A\u0005\nqCaa\u0019\u0001!\u0002\u0013i\u0006\u0002\u00033\u0001\u0011\u000b\u0007I\u0011\u0001\"\t\u0011\u0015\u0004\u0001R1A\u0005\u0002\u0019D\u0001b\u001b\u0001\t\u0006\u0004%\t\u0001\u001c\u0005\ta\u0002A)\u0019!C\u0001Y\")\u0011\u000f\u0001C\u0001e\"9\u0011Q\u0006\u0001\u0005B\u0005=r\u0001CA\u001cE!\u0005!%!\u000f\u0007\u000f\u0005\u0012\u0003\u0012\u0001\u0012\u0002<!1A+\u0005C\u0001\u0003\u0007B\u0011\"!\u0012\u0012\u0005\u0004%\t!a\u0012\t\u0011\u0005]\u0013\u0003)A\u0005\u0003\u0013B\u0011\"!\u0017\u0012\u0005\u0004%\t!a\u0012\t\u0011\u0005m\u0013\u0003)A\u0005\u0003\u0013Bq!!\u0018\u0012\t\u0003\ty\u0006C\u0004\u0002\u0010F!\t%!%\t\u000f\u0005e\u0015\u0003\"\u0011\u0002\u001c\u001a1\u0011\u0011U\t\u0001\u0003GC\u0011\"!*\u001b\u0005\u0003\u0005\u000b\u0011\u0002,\t\rQSB\u0011AAT\u0011\u001d\tyK\u0007C)\u0003c3a!a/\u0012\u0001\u0005u\u0006B\u0002+\u001f\t\u0003\ty\fC\u0004\u0002\u001az!\t%a1\u0003!1Kg.Z1s'Z\u001buK]1qa\u0016\u0014(BA\u0012%\u0003\u0005\u0011(BA\u0013'\u0003\tiGN\u0003\u0002(Q\u0005)1\u000f]1sW*\u0011\u0011FK\u0001\u0007CB\f7\r[3\u000b\u0003-\n1a\u001c:h'\r\u0001Qf\r\t\u0003]Ej\u0011a\f\u0006\u0002a\u0005)1oY1mC&\u0011!g\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q:T\"A\u001b\u000b\u0005Y\"\u0013\u0001B;uS2L!\u0001O\u001b\u0003\u00155cuK]5uC\ndW-\u0001\u0005qSB,G.\u001b8f\u0007\u0001)\u0012\u0001\u0010\t\u0003{yj\u0011\u0001J\u0005\u0003\u007f\u0011\u0012Q\u0002U5qK2Lg.Z'pI\u0016d\u0017!\u00039ja\u0016d\u0017N\\3!\u0003!1W-\u0019;ve\u0016\u001cX#A\"\u0011\u00079\"e)\u0003\u0002F_\t)\u0011I\u001d:bsB\u0011qI\u0014\b\u0003\u00112\u0003\"!S\u0018\u000e\u0003)S!a\u0013\u001e\u0002\rq\u0012xn\u001c;?\u0013\tiu&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001fB\u0013aa\u0015;sS:<'BA'0\u0003%1W-\u0019;ve\u0016\u001c\b%\u0001\u0004mC\n,Gn]\u0001\bY\u0006\u0014W\r\\:!\u0003\u0019a\u0014N\\5u}Q!a\u000bW-[!\t9\u0006!D\u0001#\u0011\u0015It\u00011\u0001=\u0011\u0015\tu\u00011\u0001D\u0011\u0015\u0011v\u00011\u0001D\u0003!\u0019hoY'pI\u0016dW#A/\u0011\u0005y\u000bW\"A0\u000b\u0005\u0001$\u0013AD2mCN\u001c\u0018NZ5dCRLwN\\\u0005\u0003E~\u0013a\u0002T5oK\u0006\u00148KV\"N_\u0012,G.A\u0005tm\u000elu\u000eZ3mA\u0005I!OR3biV\u0014Xm]\u0001\u000ee\u000e{WM\u001a4jG&,g\u000e^:\u0016\u0003\u001d\u00042A\f#i!\tq\u0013.\u0003\u0002k_\t1Ai\\;cY\u0016\f!B\\;n\u00072\f7o]3t+\u0005i\u0007C\u0001\u0018o\u0013\tywFA\u0002J]R\f1B\\;n\r\u0016\fG/\u001e:fg\u0006IAO]1og\u001a|'/\u001c\u000b\u0004g\u0006%\u0001c\u0001;\u0002\u00049\u0011QO \b\u0003mrt!a^>\u000f\u0005aThBA%z\u0013\u0005Y\u0013BA\u0015+\u0013\t9\u0003&\u0003\u0002~M\u0005\u00191/\u001d7\n\u0007}\f\t!A\u0004qC\u000e\\\u0017mZ3\u000b\u0005u4\u0013\u0002BA\u0003\u0003\u000f\u0011\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0007}\f\t\u0001C\u0004\u0002\f9\u0001\r!!\u0004\u0002\u000f\u0011\fG/Y:fiB\"\u0011qBA\u000e!\u0019\t\t\"a\u0005\u0002\u00185\u0011\u0011\u0011A\u0005\u0005\u0003+\t\tAA\u0004ECR\f7/\u001a;\u0011\t\u0005e\u00111\u0004\u0007\u0001\t1\ti\"!\u0003\u0002\u0002\u0003\u0005)\u0011AA\u0010\u0005\ryF%M\t\u0005\u0003C\t9\u0003E\u0002/\u0003GI1!!\n0\u0005\u001dqu\u000e\u001e5j]\u001e\u00042ALA\u0015\u0013\r\tYc\f\u0002\u0004\u0003:L\u0018!B<sSR,WCAA\u0019!\r!\u00141G\u0005\u0004\u0003k)$\u0001C'M/JLG/\u001a:\u0002!1Kg.Z1s'Z\u001buK]1qa\u0016\u0014\bCA,\u0012'\u0011\tR&!\u0010\u0011\tQ\nyDV\u0005\u0004\u0003\u0003*$AC'M%\u0016\fG-\u00192mKR\u0011\u0011\u0011H\u0001\u001a!J+E)S\"U\u000b\u0012{F*\u0011\"F\u0019~Ke\nR#Y?\u000e{E*\u0006\u0002\u0002JA!\u00111JA+\u001b\t\tiE\u0003\u0003\u0002P\u0005E\u0013\u0001\u00027b]\u001eT!!a\u0015\u0002\t)\fg/Y\u0005\u0004\u001f\u00065\u0013A\u0007)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0013:#U\tW0D\u001f2\u0003\u0013a\u0005)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0007>c\u0015\u0001\u0006)S\u000b\u0012K5\tV#E?2\u000b%)\u0012'`\u0007>c\u0005%A\u0002gSR$RCVA1\u0003K\nI'!\u001c\u0002r\u0005U\u0014qPAB\u0003\u000f\u000bY\t\u0003\u0004\u0002d]\u0001\ra]\u0001\u0005I\u0006$\u0018\r\u0003\u0004\u0002h]\u0001\rAR\u0001\bM>\u0014X.\u001e7b\u0011\u0019\tYg\u0006a\u0001Q\u0006A!/Z4QCJ\fW\u000e\u0003\u0004\u0002p]\u0001\r!\\\u0001\b[\u0006D\u0018\n^3s\u0011\u0019\t\u0019h\u0006a\u0001Q\u0006\u0019Ao\u001c7\t\u000f\u0005]t\u00031\u0001\u0002z\u0005y1\u000f^1oI\u0006\u0014H-\u001b>bi&|g\u000eE\u0002/\u0003wJ1!! 0\u0005\u001d\u0011un\u001c7fC:Da!!!\u0018\u0001\u0004A\u0017!\u0003;ie\u0016\u001c\bn\u001c7e\u0011\u0019\t)i\u0006a\u0001\r\u0006Iq/Z5hQR\u001cu\u000e\u001c\u0005\u0007\u0003\u0013;\u0002\u0019A7\u0002!\u0005<wM]3hCRLwN\u001c#faRD\u0007BBAG/\u0001\u0007a)A\u0007iC:$G.Z%om\u0006d\u0017\u000eZ\u0001\u0005e\u0016\fG-\u0006\u0002\u0002\u0014B!A'!&W\u0013\r\t9*\u000e\u0002\t\u001b2\u0013V-\u00193fe\u0006!An\\1e)\r1\u0016Q\u0014\u0005\u0007\u0003?K\u0002\u0019\u0001$\u0002\tA\fG\u000f\u001b\u0002\u0017\u0019&tW-\u0019:T-\u000e;&/\u00199qKJ<&/\u001b;feN\u0019!$!\r\u0002\u0011%t7\u000f^1oG\u0016$B!!+\u0002.B\u0019\u00111\u0016\u000e\u000e\u0003EAa!!*\u001d\u0001\u00041\u0016\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\u0005M\u0016\u0011\u0018\t\u0004]\u0005U\u0016bAA\\_\t!QK\\5u\u0011\u0019\ty*\ba\u0001\r\n1B*\u001b8fCJ\u001cfkQ,sCB\u0004XM\u001d*fC\u0012,'oE\u0002\u001f\u0003'#\"!!1\u0011\u0007\u0005-f\u0004F\u0002W\u0003\u000bDa!a(!\u0001\u00041\u0005"
)
public class LinearSVCWrapper implements MLWritable {
   private String[] rFeatures;
   private double[] rCoefficients;
   private int numClasses;
   private int numFeatures;
   private final PipelineModel pipeline;
   private final String[] features;
   private final String[] labels;
   private final LinearSVCModel svcModel;
   private volatile byte bitmap$0;

   public static LinearSVCWrapper load(final String path) {
      return LinearSVCWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LinearSVCWrapper$.MODULE$.read();
   }

   public static LinearSVCWrapper fit(final Dataset data, final String formula, final double regParam, final int maxIter, final double tol, final boolean standardization, final double threshold, final String weightCol, final int aggregationDepth, final String handleInvalid) {
      return LinearSVCWrapper$.MODULE$.fit(data, formula, regParam, maxIter, tol, standardization, threshold, weightCol, aggregationDepth, handleInvalid);
   }

   public static String PREDICTED_LABEL_COL() {
      return LinearSVCWrapper$.MODULE$.PREDICTED_LABEL_COL();
   }

   public static String PREDICTED_LABEL_INDEX_COL() {
      return LinearSVCWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL();
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

   private LinearSVCModel svcModel() {
      return this.svcModel;
   }

   private String[] rFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.rFeatures = this.svcModel().getFitIntercept() ? (String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"(Intercept)"})), this.features(), scala.reflect.ClassTag..MODULE$.apply(String.class)) : this.features();
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
            this.rCoefficients = this.svcModel().getFitIntercept() ? (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps(new double[]{this.svcModel().intercept()}), this.svcModel().coefficients().toArray(), scala.reflect.ClassTag..MODULE$.Double()) : this.svcModel().coefficients().toArray();
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

   private int numClasses$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.numClasses = this.svcModel().numClasses();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numClasses;
   }

   public int numClasses() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.numClasses$lzycompute() : this.numClasses;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.numFeatures = this.svcModel().numFeatures();
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

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(LinearSVCWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL()).drop(this.svcModel().getFeaturesCol()).drop(this.svcModel().getLabelCol());
   }

   public MLWriter write() {
      return new LinearSVCWrapperWriter(this);
   }

   public LinearSVCWrapper(final PipelineModel pipeline, final String[] features, final String[] labels) {
      this.pipeline = pipeline;
      this.features = features;
      this.labels = labels;
      MLWritable.$init$(this);
      this.svcModel = (LinearSVCModel)pipeline.stages()[1];
   }

   public static class LinearSVCWrapperWriter extends MLWriter {
      private final LinearSVCWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("labels"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.labels()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LinearSVCWrapperWriter.class.getClassLoader());

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

      public LinearSVCWrapperWriter(final LinearSVCWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class LinearSVCWrapperReader extends MLReader {
      public LinearSVCWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         String[] labels = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "labels")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new LinearSVCWrapper(pipeline, features, labels);
      }
   }
}
