package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.classification.LogisticRegressionModel;
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
import scala.Tuple1;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ug!B\u0010!\u0001\u0001R\u0003\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011A\u001d\t\u0011y\u0002!\u0011!Q\u0001\niB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\u001f\u0002\u0011\t\u0011)A\u0005\u0003\"A\u0001\u000b\u0001BC\u0002\u0013\u0005\u0001\t\u0003\u0005R\u0001\t\u0005\t\u0015!\u0003B\u0011\u0015\u0011\u0006\u0001\"\u0003T\u0011\u001dI\u0006A1A\u0005\niCa!\u0019\u0001!\u0002\u0013Y\u0006\u0002\u00032\u0001\u0011\u000b\u0007I\u0011\u0001!\t\u0011\r\u0004\u0001R1A\u0005\u0002\u0011DQ!\u001b\u0001\u0005\u0002)Dq!!\b\u0001\t\u0003\nyb\u0002\u0005\u0002(\u0001B\t\u0001IA\u0015\r\u001dy\u0002\u0005#\u0001!\u0003WAaAU\b\u0005\u0002\u0005M\u0002\"CA\u001b\u001f\t\u0007I\u0011AA\u001c\u0011!\t9e\u0004Q\u0001\n\u0005e\u0002\"CA%\u001f\t\u0007I\u0011AA\u001c\u0011!\tYe\u0004Q\u0001\n\u0005e\u0002bBA'\u001f\u0011\u0005\u0011q\n\u0005\b\u0003K{A\u0011IAT\u0011\u001d\tyk\u0004C!\u0003c3a!a.\u0010\u0001\u0005e\u0006\"CA^1\t\u0005\t\u0015!\u0003U\u0011\u0019\u0011\u0006\u0004\"\u0001\u0002>\"9\u0011Q\u0019\r\u0005R\u0005\u001dgABAi\u001f\u0001\t\u0019\u000e\u0003\u0004S9\u0011\u0005\u0011Q\u001b\u0005\b\u0003_cB\u0011IAm\u0005eaunZ5ti&\u001c'+Z4sKN\u001c\u0018n\u001c8Xe\u0006\u0004\b/\u001a:\u000b\u0005\u0005\u0012\u0013!\u0001:\u000b\u0005\r\"\u0013AA7m\u0015\t)c%A\u0003ta\u0006\u00148N\u0003\u0002(Q\u00051\u0011\r]1dQ\u0016T\u0011!K\u0001\u0004_J<7c\u0001\u0001,cA\u0011AfL\u0007\u0002[)\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\t1\u0011I\\=SK\u001a\u0004\"AM\u001b\u000e\u0003MR!\u0001\u000e\u0012\u0002\tU$\u0018\u000e\\\u0005\u0003mM\u0012!\"\u0014'Xe&$\u0018M\u00197f\u0003!\u0001\u0018\u000e]3mS:,7\u0001A\u000b\u0002uA\u00111\bP\u0007\u0002E%\u0011QH\t\u0002\u000e!&\u0004X\r\\5oK6{G-\u001a7\u0002\u0013AL\u0007/\u001a7j]\u0016\u0004\u0013\u0001\u00034fCR,(/Z:\u0016\u0003\u0005\u00032\u0001\f\"E\u0013\t\u0019UFA\u0003BeJ\f\u0017\u0010\u0005\u0002F\u0019:\u0011aI\u0013\t\u0003\u000f6j\u0011\u0001\u0013\u0006\u0003\u0013b\na\u0001\u0010:p_Rt\u0014BA&.\u0003\u0019\u0001&/\u001a3fM&\u0011QJ\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005-k\u0013!\u00034fCR,(/Z:!\u0003\u0019a\u0017MY3mg\u00069A.\u00192fYN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003U-^C\u0006CA+\u0001\u001b\u0005\u0001\u0003\"B\u001c\b\u0001\u0004Q\u0004\"B \b\u0001\u0004\t\u0005\"\u0002)\b\u0001\u0004\t\u0015a\u00027s\u001b>$W\r\\\u000b\u00027B\u0011AlX\u0007\u0002;*\u0011aLI\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0013\t\u0001WLA\fM_\u001eL7\u000f^5d%\u0016<'/Z:tS>tWj\u001c3fY\u0006AAN]'pI\u0016d\u0007%A\u0005s\r\u0016\fG/\u001e:fg\u0006i!oQ8fM\u001aL7-[3oiN,\u0012!\u001a\t\u0004Y\t3\u0007C\u0001\u0017h\u0013\tAWF\u0001\u0004E_V\u0014G.Z\u0001\niJ\fgn\u001d4pe6$\"a\u001b?\u0011\u00051LhBA7w\u001d\tqGO\u0004\u0002pg:\u0011\u0001O\u001d\b\u0003\u000fFL\u0011!K\u0005\u0003O!J!!\n\u0014\n\u0005U$\u0013aA:rY&\u0011q\u000f_\u0001\ba\u0006\u001c7.Y4f\u0015\t)H%\u0003\u0002{w\nIA)\u0019;b\rJ\fW.\u001a\u0006\u0003obDQ! \u0007A\u0002y\fq\u0001Z1uCN,G\u000fM\u0002\u0000\u0003\u0017\u0001b!!\u0001\u0002\u0004\u0005\u001dQ\"\u0001=\n\u0007\u0005\u0015\u0001PA\u0004ECR\f7/\u001a;\u0011\t\u0005%\u00111\u0002\u0007\u0001\t-\ti\u0001`A\u0001\u0002\u0003\u0015\t!a\u0004\u0003\u0007}#\u0013'\u0005\u0003\u0002\u0012\u0005]\u0001c\u0001\u0017\u0002\u0014%\u0019\u0011QC\u0017\u0003\u000f9{G\u000f[5oOB\u0019A&!\u0007\n\u0007\u0005mQFA\u0002B]f\fQa\u001e:ji\u0016,\"!!\t\u0011\u0007I\n\u0019#C\u0002\u0002&M\u0012\u0001\"\u0014'Xe&$XM]\u0001\u001a\u0019><\u0017n\u001d;jGJ+wM]3tg&|gn\u0016:baB,'\u000f\u0005\u0002V\u001fM!qbKA\u0017!\u0011\u0011\u0014q\u0006+\n\u0007\u0005E2G\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016$\"!!\u000b\u00023A\u0013V\tR%D)\u0016#u\fT!C\u000b2{\u0016J\u0014#F1~\u001bu\nT\u000b\u0003\u0003s\u0001B!a\u000f\u0002F5\u0011\u0011Q\b\u0006\u0005\u0003\u007f\t\t%\u0001\u0003mC:<'BAA\"\u0003\u0011Q\u0017M^1\n\u00075\u000bi$\u0001\u000eQ%\u0016#\u0015j\u0011+F\t~c\u0015IQ#M?&sE)\u0012-`\u0007>c\u0005%A\nQ%\u0016#\u0015j\u0011+F\t~c\u0015IQ#M?\u000e{E*\u0001\u000bQ%\u0016#\u0015j\u0011+F\t~c\u0015IQ#M?\u000e{E\nI\u0001\u0004M&$H#\n+\u0002R\u0005U\u0013\u0011LA/\u0003C\nY'a\u001c\u0002t\u0005u\u0014\u0011QAC\u0003\u0013\u000bi)!%\u0002\u0016\u0006e\u0015QTAQ\u0011\u0019\t\u0019&\u0006a\u0001W\u0006!A-\u0019;b\u0011\u0019\t9&\u0006a\u0001\t\u00069am\u001c:nk2\f\u0007BBA.+\u0001\u0007a-\u0001\u0005sK\u001e\u0004\u0016M]1n\u0011\u0019\ty&\u0006a\u0001M\u0006yQ\r\\1ti&\u001cg*\u001a;QCJ\fW\u000eC\u0004\u0002dU\u0001\r!!\u001a\u0002\u000f5\f\u00070\u0013;feB\u0019A&a\u001a\n\u0007\u0005%TFA\u0002J]RDa!!\u001c\u0016\u0001\u00041\u0017a\u0001;pY\"1\u0011\u0011O\u000bA\u0002\u0011\u000baAZ1nS2L\bbBA;+\u0001\u0007\u0011qO\u0001\u0010gR\fg\u000eZ1sI&T\u0018\r^5p]B\u0019A&!\u001f\n\u0007\u0005mTFA\u0004C_>dW-\u00198\t\r\u0005}T\u00031\u0001f\u0003)!\bN]3tQ>dGm\u001d\u0005\u0007\u0003\u0007+\u0002\u0019\u0001#\u0002\u0013],\u0017n\u001a5u\u0007>d\u0007bBAD+\u0001\u0007\u0011QM\u0001\u0011C\u001e<'/Z4bi&|g\u000eR3qi\"Dq!a#\u0016\u0001\u0004\t)'A\u000fok6\u0014vn^:PM\n{WO\u001c3t\u001f:\u001cu.\u001a4gS\u000eLWM\u001c;t\u0011\u001d\ty)\u0006a\u0001\u0003K\nQD\\;n\u0007>d7o\u00144C_VtGm](o\u0007>,gMZ5dS\u0016tGo\u001d\u0005\u0007\u0003'+\u0002\u0019A3\u000231|w/\u001a:C_VtGm](o\u0007>,gMZ5dS\u0016tGo\u001d\u0005\u0007\u0003/+\u0002\u0019A3\u00023U\u0004\b/\u001a:C_VtGm](o\u0007>,gMZ5dS\u0016tGo\u001d\u0005\u0007\u00037+\u0002\u0019A3\u0002/1|w/\u001a:C_VtGm](o\u0013:$XM]2faR\u001c\bBBAP+\u0001\u0007Q-A\fvaB,'OQ8v]\u0012\u001cxJ\\%oi\u0016\u00148-\u001a9ug\"1\u00111U\u000bA\u0002\u0011\u000bQ\u0002[1oI2,\u0017J\u001c<bY&$\u0017\u0001\u0002:fC\u0012,\"!!+\u0011\tI\nY\u000bV\u0005\u0004\u0003[\u001b$\u0001C'M%\u0016\fG-\u001a:\u0002\t1|\u0017\r\u001a\u000b\u0004)\u0006M\u0006BBA[/\u0001\u0007A)\u0001\u0003qCRD'a\b'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:<&/\u00199qKJ<&/\u001b;feN\u0019\u0001$!\t\u0002\u0011%t7\u000f^1oG\u0016$B!a0\u0002DB\u0019\u0011\u0011\u0019\r\u000e\u0003=Aa!a/\u001b\u0001\u0004!\u0016\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\u0005%\u0017q\u001a\t\u0004Y\u0005-\u0017bAAg[\t!QK\\5u\u0011\u0019\t)l\u0007a\u0001\t\nyBj\\4jgRL7MU3he\u0016\u001c8/[8o/J\f\u0007\u000f]3s%\u0016\fG-\u001a:\u0014\u0007q\tI\u000b\u0006\u0002\u0002XB\u0019\u0011\u0011\u0019\u000f\u0015\u0007Q\u000bY\u000e\u0003\u0004\u00026z\u0001\r\u0001\u0012"
)
public class LogisticRegressionWrapper implements MLWritable {
   private String[] rFeatures;
   private double[] rCoefficients;
   private final PipelineModel pipeline;
   private final String[] features;
   private final String[] labels;
   private final LogisticRegressionModel lrModel;
   private volatile byte bitmap$0;

   public static LogisticRegressionWrapper load(final String path) {
      return LogisticRegressionWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LogisticRegressionWrapper$.MODULE$.read();
   }

   public static LogisticRegressionWrapper fit(final Dataset data, final String formula, final double regParam, final double elasticNetParam, final int maxIter, final double tol, final String family, final boolean standardization, final double[] thresholds, final String weightCol, final int aggregationDepth, final int numRowsOfBoundsOnCoefficients, final int numColsOfBoundsOnCoefficients, final double[] lowerBoundsOnCoefficients, final double[] upperBoundsOnCoefficients, final double[] lowerBoundsOnIntercepts, final double[] upperBoundsOnIntercepts, final String handleInvalid) {
      return LogisticRegressionWrapper$.MODULE$.fit(data, formula, regParam, elasticNetParam, maxIter, tol, family, standardization, thresholds, weightCol, aggregationDepth, numRowsOfBoundsOnCoefficients, numColsOfBoundsOnCoefficients, lowerBoundsOnCoefficients, upperBoundsOnCoefficients, lowerBoundsOnIntercepts, upperBoundsOnIntercepts, handleInvalid);
   }

   public static String PREDICTED_LABEL_COL() {
      return LogisticRegressionWrapper$.MODULE$.PREDICTED_LABEL_COL();
   }

   public static String PREDICTED_LABEL_INDEX_COL() {
      return LogisticRegressionWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL();
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

   private LogisticRegressionModel lrModel() {
      return this.lrModel;
   }

   private String[] rFeatures$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.rFeatures = this.lrModel().getFitIntercept() ? (String[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new String[]{"(Intercept)"})), this.features(), scala.reflect.ClassTag..MODULE$.apply(String.class)) : this.features();
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
            int numRows = this.lrModel().coefficientMatrix().numRows();
            int numCols = this.lrModel().coefficientMatrix().numCols();
            int numColsWithIntercept = this.lrModel().getFitIntercept() ? numCols + 1 : numCols;
            double[] coefficients = new double[numRows * numColsWithIntercept];
            Seq coefficientVectors = this.lrModel().coefficientMatrix().rowIter().toSeq();
            int i = 0;
            if (this.lrModel().getFitIntercept()) {
               while(i < numRows) {
                  coefficients[i * numColsWithIntercept] = this.lrModel().interceptVector().apply(i);
                  System.arraycopy(((Vector)coefficientVectors.apply(i)).toArray(), 0, coefficients, i * numColsWithIntercept + 1, numCols);
                  ++i;
               }
            } else {
               while(i < numRows) {
                  System.arraycopy(((Vector)coefficientVectors.apply(i)).toArray(), 0, coefficients, i * numColsWithIntercept, numCols);
                  ++i;
               }
            }

            this.rCoefficients = coefficients;
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var9) {
         throw var9;
      }

      return this.rCoefficients;
   }

   public double[] rCoefficients() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.rCoefficients$lzycompute() : this.rCoefficients;
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(LogisticRegressionWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL()).drop(this.lrModel().getFeaturesCol()).drop(this.lrModel().getLabelCol());
   }

   public MLWriter write() {
      return new LogisticRegressionWrapperWriter(this);
   }

   public LogisticRegressionWrapper(final PipelineModel pipeline, final String[] features, final String[] labels) {
      this.pipeline = pipeline;
      this.features = features;
      this.labels = labels;
      MLWritable.$init$(this);
      this.lrModel = (LogisticRegressionModel)pipeline.stages()[1];
   }

   public static class LogisticRegressionWrapperWriter extends MLWriter {
      private final LogisticRegressionWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("features"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.features()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("labels"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.labels()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LogisticRegressionWrapperWriter.class.getClassLoader());

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

      public LogisticRegressionWrapperWriter(final LogisticRegressionWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class LogisticRegressionWrapperReader extends MLReader {
      public LogisticRegressionWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] features = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "features")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         String[] labels = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "labels")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new LogisticRegressionWrapper(pipeline, features, labels);
      }
   }
}
