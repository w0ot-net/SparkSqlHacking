package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.regression.GeneralizedLinearRegressionModel;
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
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t-b!B\u0017/\u00019B\u0004\u0002C#\u0001\u0005\u000b\u0007I\u0011A$\t\u00111\u0003!\u0011!Q\u0001\n!C\u0001\"\u0014\u0001\u0003\u0006\u0004%\tA\u0014\u0005\t;\u0002\u0011\t\u0011)A\u0005\u001f\"Aa\f\u0001BC\u0002\u0013\u0005q\f\u0003\u0005e\u0001\t\u0005\t\u0015!\u0003a\u0011!)\u0007A!b\u0001\n\u00031\u0007\u0002C4\u0001\u0005\u0003\u0005\u000b\u0011B1\t\u0011!\u0004!Q1A\u0005\u0002\u0019D\u0001\"\u001b\u0001\u0003\u0002\u0003\u0006I!\u0019\u0005\tU\u0002\u0011)\u0019!C\u0001M\"A1\u000e\u0001B\u0001B\u0003%\u0011\r\u0003\u0005m\u0001\t\u0015\r\u0011\"\u0001n\u0011!\t\bA!A!\u0002\u0013q\u0007\u0002\u0003:\u0001\u0005\u000b\u0007I\u0011A7\t\u0011M\u0004!\u0011!Q\u0001\n9D\u0001\u0002\u001e\u0001\u0003\u0006\u0004%\tA\u001a\u0005\tk\u0002\u0011\t\u0011)A\u0005C\"Aa\u000f\u0001BC\u0002\u0013\u0005q\u000f\u0003\u0005|\u0001\t\u0005\t\u0015!\u0003y\u0011!a\bA!b\u0001\n\u0003i\b\"CA\u0002\u0001\t\u0005\t\u0015!\u0003\u007f\u0011\u001d\t)\u0001\u0001C\u0005\u0003\u000fA\u0011\"a\t\u0001\u0005\u0004%I!!\n\t\u0011\u0005M\u0002\u0001)A\u0005\u0003OA!\"!\u000e\u0001\u0011\u000b\u0007I\u0011AA\u001c\u0011)\tY\u0006\u0001EC\u0002\u0013\u0005\u0011Q\f\u0005\b\u0003?\u0002A\u0011AA1\u0011\u001d\t9\u0007\u0001C\u0001\u0003SBq!a$\u0001\t\u0003\n\tj\u0002\u0005\u0002\u001a:B\tALAN\r\u001dic\u0006#\u0001/\u0003;Cq!!\u0002!\t\u0003\t)\u000bC\u0004\u0002(\u0002\"\t!!+\t\u000f\u0005m\u0007\u0005\"\u0011\u0002^\"9\u0011Q\u001d\u0011\u0005B\u0005\u001dhABAwA\u0001\ty\u000f\u0003\u0006\u0002r\u0016\u0012\t\u0011)A\u0005\u0003\u0013Aq!!\u0002&\t\u0003\t\u0019\u0010C\u0004\u0002|\u0016\"\t&!@\u0007\r\t\u001d\u0001\u0005\u0001B\u0005\u0011\u001d\t)!\u000bC\u0001\u0005\u0017Aq!!:*\t\u0003\u0012y\u0001C\u0005\u0003\u0014\u0001\n\n\u0011\"\u0003\u0003\u0016\t\u0011s)\u001a8fe\u0006d\u0017N_3e\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:<&/\u00199qKJT!a\f\u0019\u0002\u0003IT!!\r\u001a\u0002\u00055d'BA\u001a5\u0003\u0015\u0019\b/\u0019:l\u0015\t)d'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002o\u0005\u0019qN]4\u0014\u0007\u0001It\b\u0005\u0002;{5\t1HC\u0001=\u0003\u0015\u00198-\u00197b\u0013\tq4H\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005B\nA!\u001e;jY&\u0011A)\u0011\u0002\u000b\u001b2;&/\u001b;bE2,\u0017\u0001\u00039ja\u0016d\u0017N\\3\u0004\u0001U\t\u0001\n\u0005\u0002J\u00156\t\u0001'\u0003\u0002La\ti\u0001+\u001b9fY&tW-T8eK2\f\u0011\u0002]5qK2Lg.\u001a\u0011\u0002\u0013I4U-\u0019;ve\u0016\u001cX#A(\u0011\u0007i\u0002&+\u0003\u0002Rw\t)\u0011I\u001d:bsB\u00111K\u0017\b\u0003)b\u0003\"!V\u001e\u000e\u0003YS!a\u0016$\u0002\rq\u0012xn\u001c;?\u0013\tI6(\u0001\u0004Qe\u0016$WMZ\u0005\u00037r\u0013aa\u0015;sS:<'BA-<\u0003)\u0011h)Z1ukJ,7\u000fI\u0001\u000ee\u000e{WM\u001a4jG&,g\u000e^:\u0016\u0003\u0001\u00042A\u000f)b!\tQ$-\u0003\u0002dw\t1Ai\\;cY\u0016\faB]\"pK\u001a4\u0017nY5f]R\u001c\b%A\u0006s\t&\u001c\b/\u001a:tS>tW#A1\u0002\u0019I$\u0015n\u001d9feNLwN\u001c\u0011\u0002\u001bItU\u000f\u001c7EKZL\u0017M\\2f\u00039\u0011h*\u001e7m\t\u00164\u0018.\u00198dK\u0002\n\u0011B\u001d#fm&\fgnY3\u0002\u0015I$UM^5b]\u000e,\u0007%\u0001\u000fs%\u0016\u001c\u0018\u000eZ;bY\u0012+wM]3f\u001f\u001a4%/Z3e_6tU\u000f\u001c7\u0016\u00039\u0004\"AO8\n\u0005A\\$\u0001\u0002'p]\u001e\fQD\u001d*fg&$W/\u00197EK\u001e\u0014X-Z(g\rJ,W\rZ8n\u001dVdG\u000eI\u0001\u0019eJ+7/\u001b3vC2$Um\u001a:fK>3gI]3fI>l\u0017!\u0007:SKNLG-^1m\t\u0016<'/Z3PM\u001a\u0013X-\u001a3p[\u0002\nAA]!jG\u0006)!/Q5dA\u0005q!OT;n\u0013R,'/\u0019;j_:\u001cX#\u0001=\u0011\u0005iJ\u0018B\u0001><\u0005\rIe\u000e^\u0001\u0010e:+X.\u0013;fe\u0006$\u0018n\u001c8tA\u0005A\u0011n\u001d'pC\u0012,G-F\u0001\u007f!\tQt0C\u0002\u0002\u0002m\u0012qAQ8pY\u0016\fg.A\u0005jg2{\u0017\rZ3eA\u00051A(\u001b8jiz\"\u0002$!\u0003\u0002\u000e\u0005=\u0011\u0011CA\n\u0003+\t9\"!\u0007\u0002\u001c\u0005u\u0011qDA\u0011!\r\tY\u0001A\u0007\u0002]!)Qi\u0006a\u0001\u0011\")Qj\u0006a\u0001\u001f\")al\u0006a\u0001A\")Qm\u0006a\u0001C\")\u0001n\u0006a\u0001C\")!n\u0006a\u0001C\")An\u0006a\u0001]\")!o\u0006a\u0001]\")Ao\u0006a\u0001C\")ao\u0006a\u0001q\"9Ap\u0006I\u0001\u0002\u0004q\u0018aA4m[V\u0011\u0011q\u0005\t\u0005\u0003S\ty#\u0004\u0002\u0002,)\u0019\u0011Q\u0006\u0019\u0002\u0015I,wM]3tg&|g.\u0003\u0003\u00022\u0005-\"\u0001I$f]\u0016\u0014\u0018\r\\5{K\u0012d\u0015N\\3beJ+wM]3tg&|g.T8eK2\fAa\u001a7nA\u0005\u0011\"\u000fR3wS\u0006t7-\u001a*fg&$W/\u00197t+\t\tI\u0004\u0005\u0003\u0002<\u0005Uc\u0002BA\u001f\u0003\u001frA!a\u0010\u0002L9!\u0011\u0011IA%\u001d\u0011\t\u0019%a\u0012\u000f\u0007U\u000b)%C\u00018\u0013\t)d'\u0003\u00024i%\u0019\u0011Q\n\u001a\u0002\u0007M\fH.\u0003\u0003\u0002R\u0005M\u0013a\u00029bG.\fw-\u001a\u0006\u0004\u0003\u001b\u0012\u0014\u0002BA,\u00033\u0012\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\t\u0005E\u00131K\u0001\be\u001a\u000bW.\u001b7z+\u0005\u0011\u0016!\u0003:fg&$W/\u00197t)\u0011\tI$a\u0019\t\r\u0005\u0015D\u00041\u0001S\u00035\u0011Xm]5ek\u0006d7\u000fV=qK\u0006IAO]1og\u001a|'/\u001c\u000b\u0005\u0003s\tY\u0007C\u0004\u0002nu\u0001\r!a\u001c\u0002\u000f\u0011\fG/Y:fiB\"\u0011\u0011OA?!\u0019\t\u0019(!\u001e\u0002z5\u0011\u00111K\u0005\u0005\u0003o\n\u0019FA\u0004ECR\f7/\u001a;\u0011\t\u0005m\u0014Q\u0010\u0007\u0001\t1\ty(a\u001b\u0002\u0002\u0003\u0005)\u0011AAA\u0005\ryF%M\t\u0005\u0003\u0007\u000bI\tE\u0002;\u0003\u000bK1!a\"<\u0005\u001dqu\u000e\u001e5j]\u001e\u00042AOAF\u0013\r\tii\u000f\u0002\u0004\u0003:L\u0018!B<sSR,WCAAJ!\r\u0001\u0015QS\u0005\u0004\u0003/\u000b%\u0001C'M/JLG/\u001a:\u0002E\u001d+g.\u001a:bY&TX\r\u001a'j]\u0016\f'OU3he\u0016\u001c8/[8o/J\f\u0007\u000f]3s!\r\tY\u0001I\n\u0005Ae\ny\nE\u0003A\u0003C\u000bI!C\u0002\u0002$\u0006\u0013!\"\u0014'SK\u0006$\u0017M\u00197f)\t\tY*A\u0002gSR$\"$!\u0003\u0002,\u0006=\u00161WA\\\u0003w\u000by,a1\u0002H\u0006-\u0017qZAj\u0003/Da!!,#\u0001\u0004\u0011\u0016a\u00024pe6,H.\u0019\u0005\b\u0003c\u0013\u0003\u0019AA\u001d\u0003\u0011!\u0017\r^1\t\r\u0005U&\u00051\u0001S\u0003\u00191\u0017-\\5ms\"1\u0011\u0011\u0018\u0012A\u0002I\u000bA\u0001\\5oW\"1\u0011Q\u0018\u0012A\u0002\u0005\f1\u0001^8m\u0011\u0019\t\tM\ta\u0001q\u00069Q.\u0019=Ji\u0016\u0014\bBBAcE\u0001\u0007!+A\u0005xK&<\u0007\u000e^\"pY\"1\u0011\u0011\u001a\u0012A\u0002\u0005\f\u0001B]3h!\u0006\u0014\u0018-\u001c\u0005\u0007\u0003\u001b\u0014\u0003\u0019A1\u0002\u001bY\f'/[1oG\u0016\u0004vn^3s\u0011\u0019\t\tN\ta\u0001C\u0006IA.\u001b8l!><XM\u001d\u0005\u0007\u0003+\u0014\u0003\u0019\u0001*\u0002-M$(/\u001b8h\u0013:$W\r_3s\u001fJ$WM\u001d+za\u0016Da!!7#\u0001\u0004\u0011\u0016!C8gMN,GoQ8m\u0003\u0011\u0011X-\u00193\u0016\u0005\u0005}\u0007#\u0002!\u0002b\u0006%\u0011bAAr\u0003\nAQ\n\u0014*fC\u0012,'/\u0001\u0003m_\u0006$G\u0003BA\u0005\u0003SDa!a;%\u0001\u0004\u0011\u0016\u0001\u00029bi\"\u0014\u0001fR3oKJ\fG.\u001b>fI2Kg.Z1s%\u0016<'/Z:tS>twK]1qa\u0016\u0014xK]5uKJ\u001c2!JAJ\u0003!Ign\u001d;b]\u000e,G\u0003BA{\u0003s\u00042!a>&\u001b\u0005\u0001\u0003bBAyO\u0001\u0007\u0011\u0011B\u0001\tg\u00064X-S7qYR!\u0011q B\u0003!\rQ$\u0011A\u0005\u0004\u0005\u0007Y$\u0001B+oSRDa!a;)\u0001\u0004\u0011&\u0001K$f]\u0016\u0014\u0018\r\\5{K\u0012d\u0015N\\3beJ+wM]3tg&|gn\u0016:baB,'OU3bI\u0016\u00148cA\u0015\u0002`R\u0011!Q\u0002\t\u0004\u0003oLC\u0003BA\u0005\u0005#Aa!a;,\u0001\u0004\u0011\u0016\u0001\b\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'M\u000b\u0003\u0005/Q3A B\rW\t\u0011Y\u0002\u0005\u0003\u0003\u001e\t\u001dRB\u0001B\u0010\u0015\u0011\u0011\tCa\t\u0002\u0013Ut7\r[3dW\u0016$'b\u0001B\u0013w\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t%\"q\u0004\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class GeneralizedLinearRegressionWrapper implements MLWritable {
   private Dataset rDevianceResiduals;
   private String rFamily;
   private final PipelineModel pipeline;
   private final String[] rFeatures;
   private final double[] rCoefficients;
   private final double rDispersion;
   private final double rNullDeviance;
   private final double rDeviance;
   private final long rResidualDegreeOfFreedomNull;
   private final long rResidualDegreeOfFreedom;
   private final double rAic;
   private final int rNumIterations;
   private final boolean isLoaded;
   private final GeneralizedLinearRegressionModel glm;
   private volatile byte bitmap$0;

   public static GeneralizedLinearRegressionWrapper load(final String path) {
      return GeneralizedLinearRegressionWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GeneralizedLinearRegressionWrapper$.MODULE$.read();
   }

   public static GeneralizedLinearRegressionWrapper fit(final String formula, final Dataset data, final String family, final String link, final double tol, final int maxIter, final String weightCol, final double regParam, final double variancePower, final double linkPower, final String stringIndexerOrderType, final String offsetCol) {
      return GeneralizedLinearRegressionWrapper$.MODULE$.fit(formula, data, family, link, tol, maxIter, weightCol, regParam, variancePower, linkPower, stringIndexerOrderType, offsetCol);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public PipelineModel pipeline() {
      return this.pipeline;
   }

   public String[] rFeatures() {
      return this.rFeatures;
   }

   public double[] rCoefficients() {
      return this.rCoefficients;
   }

   public double rDispersion() {
      return this.rDispersion;
   }

   public double rNullDeviance() {
      return this.rNullDeviance;
   }

   public double rDeviance() {
      return this.rDeviance;
   }

   public long rResidualDegreeOfFreedomNull() {
      return this.rResidualDegreeOfFreedomNull;
   }

   public long rResidualDegreeOfFreedom() {
      return this.rResidualDegreeOfFreedom;
   }

   public double rAic() {
      return this.rAic;
   }

   public int rNumIterations() {
      return this.rNumIterations;
   }

   public boolean isLoaded() {
      return this.isLoaded;
   }

   private GeneralizedLinearRegressionModel glm() {
      return this.glm;
   }

   private Dataset rDevianceResiduals$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.rDevianceResiduals = this.glm().summary().residuals();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rDevianceResiduals;
   }

   public Dataset rDevianceResiduals() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.rDevianceResiduals$lzycompute() : this.rDevianceResiduals;
   }

   private String rFamily$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.rFamily = this.glm().getFamily();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rFamily;
   }

   public String rFamily() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.rFamily$lzycompute() : this.rFamily;
   }

   public Dataset residuals(final String residualsType) {
      return this.glm().summary().residuals(residualsType);
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.glm().getFeaturesCol());
   }

   public MLWriter write() {
      return new GeneralizedLinearRegressionWrapperWriter(this);
   }

   public GeneralizedLinearRegressionWrapper(final PipelineModel pipeline, final String[] rFeatures, final double[] rCoefficients, final double rDispersion, final double rNullDeviance, final double rDeviance, final long rResidualDegreeOfFreedomNull, final long rResidualDegreeOfFreedom, final double rAic, final int rNumIterations, final boolean isLoaded) {
      this.pipeline = pipeline;
      this.rFeatures = rFeatures;
      this.rCoefficients = rCoefficients;
      this.rDispersion = rDispersion;
      this.rNullDeviance = rNullDeviance;
      this.rDeviance = rDeviance;
      this.rResidualDegreeOfFreedomNull = rResidualDegreeOfFreedomNull;
      this.rResidualDegreeOfFreedom = rResidualDegreeOfFreedom;
      this.rAic = rAic;
      this.rNumIterations = rNumIterations;
      this.isLoaded = isLoaded;
      MLWritable.$init$(this);
      this.glm = (GeneralizedLinearRegressionModel)pipeline.stages()[1];
   }

   public static class GeneralizedLinearRegressionWrapperWriter extends MLWriter {
      private final GeneralizedLinearRegressionWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rFeatures"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.rFeatures()).toImmutableArraySeq()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rCoefficients"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.rCoefficients()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$saveImpl$6(BoxesRunTime.unboxToDouble(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rDispersion"), BoxesRunTime.boxToDouble(this.instance.rDispersion())), (x) -> $anonfun$saveImpl$7(BoxesRunTime.unboxToDouble(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rNullDeviance"), BoxesRunTime.boxToDouble(this.instance.rNullDeviance())), (x) -> $anonfun$saveImpl$8(BoxesRunTime.unboxToDouble(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rDeviance"), BoxesRunTime.boxToDouble(this.instance.rDeviance())), (x) -> $anonfun$saveImpl$9(BoxesRunTime.unboxToDouble(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rResidualDegreeOfFreedomNull"), BoxesRunTime.boxToLong(this.instance.rResidualDegreeOfFreedomNull())), (x) -> $anonfun$saveImpl$10(BoxesRunTime.unboxToLong(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rResidualDegreeOfFreedom"), BoxesRunTime.boxToLong(this.instance.rResidualDegreeOfFreedom())), (x) -> $anonfun$saveImpl$11(BoxesRunTime.unboxToLong(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rAic"), BoxesRunTime.boxToDouble(this.instance.rAic())), (x) -> $anonfun$saveImpl$12(BoxesRunTime.unboxToDouble(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("rNumIterations"), BoxesRunTime.boxToInteger(this.instance.rNumIterations())), (x) -> $anonfun$saveImpl$13(BoxesRunTime.unboxToInt(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GeneralizedLinearRegressionWrapperWriter.class.getClassLoader());

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

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$6(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$7(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$8(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$9(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$10(final long x) {
         return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$11(final long x) {
         return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$12(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$13(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      public GeneralizedLinearRegressionWrapperWriter(final GeneralizedLinearRegressionWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class GeneralizedLinearRegressionWrapperReader extends MLReader {
      public GeneralizedLinearRegressionWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         String[] rFeatures = (String[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "rFeatures")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.classType(String.class)));
         double[] rCoefficients = (double[])org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "rCoefficients")), format, scala.reflect.ManifestFactory..MODULE$.arrayType(scala.reflect.ManifestFactory..MODULE$.Double()));
         double rDispersion = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "rDispersion")), format, scala.reflect.ManifestFactory..MODULE$.Double()));
         double rNullDeviance = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "rNullDeviance")), format, scala.reflect.ManifestFactory..MODULE$.Double()));
         double rDeviance = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "rDeviance")), format, scala.reflect.ManifestFactory..MODULE$.Double()));
         long rResidualDegreeOfFreedomNull = BoxesRunTime.unboxToLong(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "rResidualDegreeOfFreedomNull")), format, scala.reflect.ManifestFactory..MODULE$.Long()));
         long rResidualDegreeOfFreedom = BoxesRunTime.unboxToLong(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "rResidualDegreeOfFreedom")), format, scala.reflect.ManifestFactory..MODULE$.Long()));
         double rAic = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "rAic")), format, scala.reflect.ManifestFactory..MODULE$.Double()));
         int rNumIterations = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "rNumIterations")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new GeneralizedLinearRegressionWrapper(pipeline, rFeatures, rCoefficients, rDispersion, rNullDeviance, rDeviance, rResidualDegreeOfFreedomNull, rResidualDegreeOfFreedom, rAic, rNumIterations, true);
      }
   }
}
