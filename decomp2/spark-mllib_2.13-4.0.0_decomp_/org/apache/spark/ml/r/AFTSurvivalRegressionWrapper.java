package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.regression.AFTSurvivalRegressionModel;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json4s.Formats;
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
   bytes = "\u0006\u0005\u0005me!\u0002\u000f\u001e\u0001u9\u0003\u0002\u0003\u001b\u0001\u0005\u000b\u0007I\u0011\u0001\u001c\t\u0011m\u0002!\u0011!Q\u0001\n]B\u0001\u0002\u0010\u0001\u0003\u0006\u0004%\t!\u0010\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005}!)Q\n\u0001C\u0005\u001d\"91\u000b\u0001b\u0001\n\u0013!\u0006BB.\u0001A\u0003%Q\u000b\u0003\u0005]\u0001!\u0015\r\u0011\"\u0001^\u0011!\u0011\u0007\u0001#b\u0001\n\u0003i\u0004\"B2\u0001\t\u0003!\u0007bBA\t\u0001\u0011\u0005\u00131C\u0004\t\u00037i\u0002\u0012A\u000f\u0002\u001e\u00199A$\bE\u0001;\u0005}\u0001BB'\u000e\t\u0003\t9\u0003C\u0005\u0002*5\u0011\r\u0011\"\u0003\u0002,!A\u00111H\u0007!\u0002\u0013\ti\u0003C\u0004\u0002>5!I!a\u0010\t\u000f\u0005-S\u0002\"\u0001\u0002N!9\u00111M\u0007\u0005B\u0005\u0015\u0004bBA7\u001b\u0011\u0005\u0013q\u000e\u0004\u0007\u0003kj\u0001!a\u001e\t\u0013\u0005eTC!A!\u0002\u0013y\u0005BB'\u0016\t\u0003\tY\bC\u0004\u0002\u0004V!\t&!\"\u0007\r\u0005=U\u0002AAI\u0011\u0019i\u0015\u0004\"\u0001\u0002\u0014\"9\u0011QN\r\u0005B\u0005]%\u0001H!G)N+(O^5wC2\u0014Vm\u001a:fgNLwN\\,sCB\u0004XM\u001d\u0006\u0003=}\t\u0011A\u001d\u0006\u0003A\u0005\n!!\u001c7\u000b\u0005\t\u001a\u0013!B:qCJ\\'B\u0001\u0013&\u0003\u0019\t\u0007/Y2iK*\ta%A\u0002pe\u001e\u001c2\u0001\u0001\u0015/!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u0019\te.\u001f*fMB\u0011qFM\u0007\u0002a)\u0011\u0011gH\u0001\u0005kRLG.\u0003\u00024a\tQQ\nT,sSR\f'\r\\3\u0002\u0011AL\u0007/\u001a7j]\u0016\u001c\u0001!F\u00018!\tA\u0014(D\u0001 \u0013\tQtDA\u0007QSB,G.\u001b8f\u001b>$W\r\\\u0001\na&\u0004X\r\\5oK\u0002\n\u0001BZ3biV\u0014Xm]\u000b\u0002}A\u0019\u0011fP!\n\u0005\u0001S#!B!se\u0006L\bC\u0001\"J\u001d\t\u0019u\t\u0005\u0002EU5\tQI\u0003\u0002Gk\u00051AH]8pizJ!\u0001\u0013\u0016\u0002\rA\u0013X\rZ3g\u0013\tQ5J\u0001\u0004TiJLgn\u001a\u0006\u0003\u0011*\n\u0011BZ3biV\u0014Xm\u001d\u0011\u0002\rqJg.\u001b;?)\ry\u0015K\u0015\t\u0003!\u0002i\u0011!\b\u0005\u0006i\u0015\u0001\ra\u000e\u0005\u0006y\u0015\u0001\rAP\u0001\tC\u001a$Xj\u001c3fYV\tQ\u000b\u0005\u0002W36\tqK\u0003\u0002Y?\u0005Q!/Z4sKN\u001c\u0018n\u001c8\n\u0005i;&AG!G)N+(O^5wC2\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0017!C1gi6{G-\u001a7!\u00035\u00118i\\3gM&\u001c\u0017.\u001a8ugV\ta\fE\u0002*\u007f}\u0003\"!\u000b1\n\u0005\u0005T#A\u0002#pk\ndW-A\u0005s\r\u0016\fG/\u001e:fg\u0006IAO]1og\u001a|'/\u001c\u000b\u0003KZ\u0004\"AZ:\u000f\u0005\u001d\u0004hB\u00015o\u001d\tIWN\u0004\u0002kY:\u0011Ai[\u0005\u0002M%\u0011A%J\u0005\u0003E\rJ!a\\\u0011\u0002\u0007M\fH.\u0003\u0002re\u00069\u0001/Y2lC\u001e,'BA8\"\u0013\t!XOA\u0005ECR\fgI]1nK*\u0011\u0011O\u001d\u0005\u0006o*\u0001\r\u0001_\u0001\bI\u0006$\u0018m]3ua\tIx\u0010E\u0002{wvl\u0011A]\u0005\u0003yJ\u0014q\u0001R1uCN,G\u000f\u0005\u0002\u007f\u007f2\u0001AaCA\u0001m\u0006\u0005\t\u0011!B\u0001\u0003\u0007\u00111a\u0018\u00132#\u0011\t)!a\u0003\u0011\u0007%\n9!C\u0002\u0002\n)\u0012qAT8uQ&tw\rE\u0002*\u0003\u001bI1!a\u0004+\u0005\r\te._\u0001\u0006oJLG/Z\u000b\u0003\u0003+\u00012aLA\f\u0013\r\tI\u0002\r\u0002\t\u001b2;&/\u001b;fe\u0006a\u0012I\u0012+TkJ4\u0018N^1m%\u0016<'/Z:tS>twK]1qa\u0016\u0014\bC\u0001)\u000e'\u0011i\u0001&!\t\u0011\t=\n\u0019cT\u0005\u0004\u0003K\u0001$AC'M%\u0016\fG-\u00192mKR\u0011\u0011QD\u0001\u000f\r>\u0013V*\u0016'B?J+u)\u0012-Q+\t\ti\u0003\u0005\u0003\u00020\u0005]RBAA\u0019\u0015\u0011\t\u0019$!\u000e\u0002\u00115\fGo\u00195j]\u001eT!!\r\u0016\n\t\u0005e\u0012\u0011\u0007\u0002\u0006%\u0016<W\r_\u0001\u0010\r>\u0013V*\u0016'B?J+u)\u0012-QA\u0005qam\u001c:nk2\f'+Z<sSR,G\u0003BA!\u0003\u000f\u0002R!KA\"\u0003\u0006K1!!\u0012+\u0005\u0019!V\u000f\u001d7fe!1\u0011\u0011J\tA\u0002\u0005\u000bqAZ8s[Vd\u0017-A\u0002gSR$\u0012bTA(\u0003#\n)&a\u0018\t\r\u0005%#\u00031\u0001B\u0011\u0019\t\u0019F\u0005a\u0001K\u0006!A-\u0019;b\u0011\u001d\t9F\u0005a\u0001\u00033\n\u0001#Y4he\u0016<\u0017\r^5p]\u0012+\u0007\u000f\u001e5\u0011\u0007%\nY&C\u0002\u0002^)\u00121!\u00138u\u0011\u0019\t\tG\u0005a\u0001\u0003\u000612\u000f\u001e:j]\u001eLe\u000eZ3yKJ|%\u000fZ3s)f\u0004X-\u0001\u0003sK\u0006$WCAA4!\u0011y\u0013\u0011N(\n\u0007\u0005-\u0004G\u0001\u0005N\u0019J+\u0017\rZ3s\u0003\u0011aw.\u00193\u0015\u0007=\u000b\t\b\u0003\u0004\u0002tQ\u0001\r!Q\u0001\u0005a\u0006$\bN\u0001\u0012B\rR\u001bVO\u001d<jm\u0006d'+Z4sKN\u001c\u0018n\u001c8Xe\u0006\u0004\b/\u001a:Xe&$XM]\n\u0004+\u0005U\u0011\u0001C5ogR\fgnY3\u0015\t\u0005u\u0014\u0011\u0011\t\u0004\u0003\u007f*R\"A\u0007\t\r\u0005et\u00031\u0001P\u0003!\u0019\u0018M^3J[BdG\u0003BAD\u0003\u001b\u00032!KAE\u0013\r\tYI\u000b\u0002\u0005+:LG\u000f\u0003\u0004\u0002ta\u0001\r!\u0011\u0002#\u0003\u001a#6+\u001e:wSZ\fGNU3he\u0016\u001c8/[8o/J\f\u0007\u000f]3s%\u0016\fG-\u001a:\u0014\u0007e\t9\u0007\u0006\u0002\u0002\u0016B\u0019\u0011qP\r\u0015\u0007=\u000bI\n\u0003\u0004\u0002tm\u0001\r!\u0011"
)
public class AFTSurvivalRegressionWrapper implements MLWritable {
   private double[] rCoefficients;
   private String[] rFeatures;
   private final PipelineModel pipeline;
   private final String[] features;
   private final AFTSurvivalRegressionModel aftModel;
   private volatile byte bitmap$0;

   public static AFTSurvivalRegressionWrapper load(final String path) {
      return AFTSurvivalRegressionWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return AFTSurvivalRegressionWrapper$.MODULE$.read();
   }

   public static AFTSurvivalRegressionWrapper fit(final String formula, final Dataset data, final int aggregationDepth, final String stringIndexerOrderType) {
      return AFTSurvivalRegressionWrapper$.MODULE$.fit(formula, data, aggregationDepth, stringIndexerOrderType);
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

   private AFTSurvivalRegressionModel aftModel() {
      return this.aftModel;
   }

   private double[] rCoefficients$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.rCoefficients = this.aftModel().getFitIntercept() ? (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps(new double[]{this.aftModel().intercept()}), this.aftModel().coefficients().toArray(), scala.reflect.ClassTag..MODULE$.Double())), new double[]{scala.math.package..MODULE$.log(this.aftModel().scale())}, scala.reflect.ClassTag..MODULE$.Double()) : (double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps(this.aftModel().coefficients().toArray()), new double[]{scala.math.package..MODULE$.log(this.aftModel().scale())}, scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rCoefficients;
   }

   public double[] rCoefficients() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.rCoefficients$lzycompute() : this.rCoefficients;
   }

   private String[] rFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.rFeatures = this.aftModel().getFitIntercept() ? (String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"(Intercept)"})), this.features(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (Object[])(new String[]{"Log(scale)"}), scala.reflect.ClassTag..MODULE$.apply(String.class)) : (String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.features()), (Object[])(new String[]{"Log(scale)"}), scala.reflect.ClassTag..MODULE$.apply(String.class));
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rFeatures;
   }

   public String[] rFeatures() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.rFeatures$lzycompute() : this.rFeatures;
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.aftModel().getFeaturesCol());
   }

   public MLWriter write() {
      return new AFTSurvivalRegressionWrapperWriter(this);
   }

   public AFTSurvivalRegressionWrapper(final PipelineModel pipeline, final String[] features) {
      this.pipeline = pipeline;
      this.features = features;
      MLWritable.$init$(this);
      this.aftModel = (AFTSurvivalRegressionModel)pipeline.stages()[1];
   }

   public static class AFTSurvivalRegressionWrapperWriter extends MLWriter {
      private final AFTSurvivalRegressionWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(AFTSurvivalRegressionWrapperWriter.class.getClassLoader());

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

      public AFTSurvivalRegressionWrapperWriter(final AFTSurvivalRegressionWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class AFTSurvivalRegressionWrapperReader extends MLReader {
      public AFTSurvivalRegressionWrapper load(final String path) {
         Formats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new AFTSurvivalRegressionWrapper(pipeline, features);
      }
   }
}
