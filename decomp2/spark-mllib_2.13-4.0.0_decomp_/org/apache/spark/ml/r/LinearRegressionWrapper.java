package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.regression.LinearRegressionModel;
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
   bytes = "\u0006\u0005\u0005}e!B\r\u001b\u0001i!\u0003\u0002C\u0019\u0001\u0005\u000b\u0007I\u0011A\u001a\t\u0011a\u0002!\u0011!Q\u0001\nQB\u0001\"\u000f\u0001\u0003\u0006\u0004%\tA\u000f\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005w!)!\n\u0001C\u0005\u0017\"9\u0001\u000b\u0001b\u0001\n\u0013\t\u0006B\u0002-\u0001A\u0003%!\u000b\u0003\u0005Z\u0001!\u0015\r\u0011\"\u0001;\u0011!Q\u0006\u0001#b\u0001\n\u0003Y\u0006\u0002\u00031\u0001\u0011\u000b\u0007I\u0011A1\t\u000b\u0015\u0004A\u0011\u00014\t\u000f\u0005U\u0001\u0001\"\u0011\u0002\u0018\u001dA\u0011q\u0004\u000e\t\u0002i\t\tCB\u0004\u001a5!\u0005!$a\t\t\r)sA\u0011AA\u0016\u0011\u001d\tiC\u0004C\u0001\u0003_Aq!a\u001b\u000f\t\u0003\niG\u0002\u0004\u0002v9\u0001\u0011q\u000f\u0005\n\u0003s\u0012\"\u0011!Q\u0001\n1CaA\u0013\n\u0005\u0002\u0005m\u0004bBAB%\u0011E\u0013Q\u0011\u0004\u0007\u0003#s\u0001!a%\t\r)3B\u0011AAK\u0011\u001d\tIJ\u0006C!\u00037\u0013q\u0003T5oK\u0006\u0014(+Z4sKN\u001c\u0018n\u001c8Xe\u0006\u0004\b/\u001a:\u000b\u0005ma\u0012!\u0001:\u000b\u0005uq\u0012AA7m\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7c\u0001\u0001&WA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t1\u0011I\\=SK\u001a\u0004\"\u0001L\u0018\u000e\u00035R!A\f\u000f\u0002\tU$\u0018\u000e\\\u0005\u0003a5\u0012!\"\u0014'Xe&$\u0018M\u00197f\u0003!\u0001\u0018\u000e]3mS:,7\u0001A\u000b\u0002iA\u0011QGN\u0007\u00029%\u0011q\u0007\b\u0002\u000e!&\u0004X\r\\5oK6{G-\u001a7\u0002\u0013AL\u0007/\u001a7j]\u0016\u0004\u0013\u0001\u00034fCR,(/Z:\u0016\u0003m\u00022A\n\u001f?\u0013\titEA\u0003BeJ\f\u0017\u0010\u0005\u0002@\r:\u0011\u0001\t\u0012\t\u0003\u0003\u001ej\u0011A\u0011\u0006\u0003\u0007J\na\u0001\u0010:p_Rt\u0014BA#(\u0003\u0019\u0001&/\u001a3fM&\u0011q\t\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0015;\u0013!\u00034fCR,(/Z:!\u0003\u0019a\u0014N\\5u}Q\u0019AJT(\u0011\u00055\u0003Q\"\u0001\u000e\t\u000bE*\u0001\u0019\u0001\u001b\t\u000be*\u0001\u0019A\u001e\u0002+1Lg.Z1s%\u0016<'/Z:tS>tWj\u001c3fYV\t!\u000b\u0005\u0002T-6\tAK\u0003\u0002V9\u0005Q!/Z4sKN\u001c\u0018n\u001c8\n\u0005]#&!\u0006'j]\u0016\f'OU3he\u0016\u001c8/[8o\u001b>$W\r\\\u0001\u0017Y&tW-\u0019:SK\u001e\u0014Xm]:j_:lu\u000eZ3mA\u0005I!OR3biV\u0014Xm]\u0001\u000ee\u000e{WM\u001a4jG&,g\u000e^:\u0016\u0003q\u00032A\n\u001f^!\t1c,\u0003\u0002`O\t1Ai\\;cY\u0016\f1B\\;n\r\u0016\fG/\u001e:fgV\t!\r\u0005\u0002'G&\u0011Am\n\u0002\u0004\u0013:$\u0018!\u0003;sC:\u001chm\u001c:n)\t9\u0007\u0010\u0005\u0002ik:\u0011\u0011N\u001d\b\u0003UBt!a[8\u000f\u00051tgBA!n\u0013\u0005\u0019\u0013BA\u0011#\u0013\ty\u0002%\u0003\u0002r=\u0005\u00191/\u001d7\n\u0005M$\u0018a\u00029bG.\fw-\u001a\u0006\u0003czI!A^<\u0003\u0013\u0011\u000bG/\u0019$sC6,'BA:u\u0011\u0015I8\u00021\u0001{\u0003\u001d!\u0017\r^1tKR\u00044a_A\u0002!\raXp`\u0007\u0002i&\u0011a\u0010\u001e\u0002\b\t\u0006$\u0018m]3u!\u0011\t\t!a\u0001\r\u0001\u0011Y\u0011Q\u0001=\u0002\u0002\u0003\u0005)\u0011AA\u0004\u0005\ryF%M\t\u0005\u0003\u0013\ty\u0001E\u0002'\u0003\u0017I1!!\u0004(\u0005\u001dqu\u000e\u001e5j]\u001e\u00042AJA\t\u0013\r\t\u0019b\n\u0002\u0004\u0003:L\u0018!B<sSR,WCAA\r!\ra\u00131D\u0005\u0004\u0003;i#\u0001C'M/JLG/\u001a:\u0002/1Kg.Z1s%\u0016<'/Z:tS>twK]1qa\u0016\u0014\bCA'\u000f'\u0011qQ%!\n\u0011\t1\n9\u0003T\u0005\u0004\u0003Si#AC'M%\u0016\fG-\u00192mKR\u0011\u0011\u0011E\u0001\u0004M&$Hc\u0007'\u00022\u0005U\u0012\u0011HA\u001f\u0003\u0003\n)%!\u0013\u0002T\u0005]\u00131LA0\u0003G\n9\u0007\u0003\u0004\u00024A\u0001\raZ\u0001\u0005I\u0006$\u0018\r\u0003\u0004\u00028A\u0001\rAP\u0001\bM>\u0014X.\u001e7b\u0011\u0019\tY\u0004\u0005a\u0001E\u00069Q.\u0019=Ji\u0016\u0014\bBBA !\u0001\u0007Q,\u0001\u0005sK\u001e\u0004\u0016M]1n\u0011\u0019\t\u0019\u0005\u0005a\u0001;\u0006yQ\r\\1ti&\u001cg*\u001a;QCJ\fW\u000e\u0003\u0004\u0002HA\u0001\r!X\u0001\u0004i>d\u0007bBA&!\u0001\u0007\u0011QJ\u0001\u0010gR\fg\u000eZ1sI&T\u0018\r^5p]B\u0019a%a\u0014\n\u0007\u0005EsEA\u0004C_>dW-\u00198\t\r\u0005U\u0003\u00031\u0001?\u0003\u0019\u0019x\u000e\u001c<fe\"1\u0011\u0011\f\tA\u0002y\n\u0011b^3jO\"$8i\u001c7\t\r\u0005u\u0003\u00031\u0001c\u0003A\twm\u001a:fO\u0006$\u0018n\u001c8EKB$\b\u000e\u0003\u0004\u0002bA\u0001\rAP\u0001\u0005Y>\u001c8\u000f\u0003\u0004\u0002fA\u0001\r!X\u0001\bKB\u001c\u0018\u000e\\8o\u0011\u0019\tI\u0007\u0005a\u0001}\u000512\u000f\u001e:j]\u001eLe\u000eZ3yKJ|%\u000fZ3s)f\u0004X-\u0001\u0003sK\u0006$WCAA8!\u0011a\u0013\u0011\u000f'\n\u0007\u0005MTF\u0001\u0005N\u0019J+\u0017\rZ3s\u0005ua\u0015N\\3beJ+wM]3tg&|gn\u0016:baB,'o\u0016:ji\u0016\u00148c\u0001\n\u0002\u001a\u0005A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0002~\u0005\u0005\u0005cAA@%5\ta\u0002\u0003\u0004\u0002zQ\u0001\r\u0001T\u0001\tg\u00064X-S7qYR!\u0011qQAG!\r1\u0013\u0011R\u0005\u0004\u0003\u0017;#\u0001B+oSRDa!a$\u0016\u0001\u0004q\u0014\u0001\u00029bi\"\u0014Q\u0004T5oK\u0006\u0014(+Z4sKN\u001c\u0018n\u001c8Xe\u0006\u0004\b/\u001a:SK\u0006$WM]\n\u0004-\u0005=DCAAL!\r\tyHF\u0001\u0005Y>\fG\rF\u0002M\u0003;Ca!a$\u0019\u0001\u0004q\u0004"
)
public class LinearRegressionWrapper implements MLWritable {
   private String[] rFeatures;
   private double[] rCoefficients;
   private int numFeatures;
   private final PipelineModel pipeline;
   private final String[] features;
   private final LinearRegressionModel linearRegressionModel;
   private volatile byte bitmap$0;

   public static MLReader read() {
      return LinearRegressionWrapper$.MODULE$.read();
   }

   public static LinearRegressionWrapper fit(final Dataset data, final String formula, final int maxIter, final double regParam, final double elasticNetParam, final double tol, final boolean standardization, final String solver, final String weightCol, final int aggregationDepth, final String loss, final double epsilon, final String stringIndexerOrderType) {
      return LinearRegressionWrapper$.MODULE$.fit(data, formula, maxIter, regParam, elasticNetParam, tol, standardization, solver, weightCol, aggregationDepth, loss, epsilon, stringIndexerOrderType);
   }

   public static Object load(final String path) {
      return LinearRegressionWrapper$.MODULE$.load(path);
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

   private LinearRegressionModel linearRegressionModel() {
      return this.linearRegressionModel;
   }

   private String[] rFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.rFeatures = this.linearRegressionModel().getFitIntercept() ? (String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"(Intercept)"})), this.features(), scala.reflect.ClassTag..MODULE$.apply(String.class)) : this.features();
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
            this.rCoefficients = this.linearRegressionModel().getFitIntercept() ? (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps(new double[]{this.linearRegressionModel().intercept()}), this.linearRegressionModel().coefficients().toArray(), scala.reflect.ClassTag..MODULE$.Double()) : this.linearRegressionModel().coefficients().toArray();
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

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.numFeatures = this.linearRegressionModel().numFeatures();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numFeatures;
   }

   public int numFeatures() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.numFeatures$lzycompute() : this.numFeatures;
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.linearRegressionModel().getFeaturesCol());
   }

   public MLWriter write() {
      return new LinearRegressionWrapperWriter(this);
   }

   public LinearRegressionWrapper(final PipelineModel pipeline, final String[] features) {
      this.pipeline = pipeline;
      this.features = features;
      MLWritable.$init$(this);
      this.linearRegressionModel = (LinearRegressionModel)pipeline.stages()[1];
   }

   public static class LinearRegressionWrapperWriter extends MLWriter {
      private final LinearRegressionWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LinearRegressionWrapperWriter.class.getClassLoader());

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

      public LinearRegressionWrapperWriter(final LinearRegressionWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class LinearRegressionWrapperReader extends MLReader {
      public LinearRegressionWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new LinearRegressionWrapper(pipeline, features);
      }
   }
}
