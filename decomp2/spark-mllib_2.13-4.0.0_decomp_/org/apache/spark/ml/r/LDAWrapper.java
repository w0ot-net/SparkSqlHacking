package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.clustering.DistributedLDAModel;
import org.apache.spark.ml.clustering.LDAModel;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Function1;
import scala.Tuple1;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rb!B\u0017/\u00019B\u0004\u0002C#\u0001\u0005\u000b\u0007I\u0011A$\t\u00111\u0003!\u0011!Q\u0001\n!C\u0001\"\u0014\u0001\u0003\u0006\u0004%\tA\u0014\u0005\t%\u0002\u0011\t\u0011)A\u0005\u001f\"A1\u000b\u0001BC\u0002\u0013\u0005a\n\u0003\u0005U\u0001\t\u0005\t\u0015!\u0003P\u0011!)\u0006A!b\u0001\n\u00031\u0006\u0002C3\u0001\u0005\u0003\u0005\u000b\u0011B,\t\u000b\u0019\u0004A\u0011B4\t\u000f9\u0004!\u0019!C\u0005_\"1a\u000f\u0001Q\u0001\nAD\u0001b\u001e\u0001\t\u0006\u0004%I\u0001\u001f\u0005\ty\u0002A)\u0019!C\u0001\u001d\"AQ\u0010\u0001EC\u0002\u0013\u0005a\nC\u0004\u007f\u0001\t\u0007I\u0011B$\t\r}\u0004\u0001\u0015!\u0003I\u0011\u001d\t\t\u0001\u0001C\u0001\u0003\u0007Aq!a\u0013\u0001\t\u0003\ti\u0005C\u0004\u0002\\\u0001!\t!!\u0018\t\u0015\u0005%\u0004\u0001#b\u0001\n\u0003\tY\u0007\u0003\u0006\u0002t\u0001A)\u0019!C\u0001\u0003kB!\"a\u001e\u0001\u0011\u000b\u0007I\u0011AA=\u0011%\ti\b\u0001EC\u0002\u0013\u0005a\nC\u0004\u0002\u0000\u0001!\t%!!\b\u0011\u0005%e\u0006#\u0001/\u0003\u00173q!\f\u0018\t\u00029\ni\t\u0003\u0004g5\u0011\u0005\u0011Q\u0013\u0005\n\u0003/S\"\u0019!C\u0001\u00033C\u0001\"!+\u001bA\u0003%\u00111\u0014\u0005\n\u0003WS\"\u0019!C\u0001\u00033C\u0001\"!,\u001bA\u0003%\u00111\u0014\u0005\n\u0003_S\"\u0019!C\u0001\u00033C\u0001\"!-\u001bA\u0003%\u00111\u0014\u0005\b\u0003gSB\u0011BA[\u0011\u001d\tYM\u0007C\u0001\u0003\u001bDq!a;\u001b\t\u0003\ni\u000fC\u0004\u0002vj!\t%a>\u0007\r\u0005u(\u0004AA\u0000\u0011%\u0011\tA\nB\u0001B\u0003%\u0001\u000e\u0003\u0004gM\u0011\u0005!1\u0001\u0005\b\u0005\u00171C\u0011\u000bB\u0007\r\u0019\u00119B\u0007\u0001\u0003\u001a!1aM\u000bC\u0001\u00057Aq!!>+\t\u0003\u0012yB\u0001\u0006M\t\u0006;&/\u00199qKJT!a\f\u0019\u0002\u0003IT!!\r\u001a\u0002\u00055d'BA\u001a5\u0003\u0015\u0019\b/\u0019:l\u0015\t)d'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002o\u0005\u0019qN]4\u0014\u0007\u0001It\b\u0005\u0002;{5\t1HC\u0001=\u0003\u0015\u00198-\u00197b\u0013\tq4H\u0001\u0004B]f\u0014VM\u001a\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005B\nA!\u001e;jY&\u0011A)\u0011\u0002\u000b\u001b2;&/\u001b;bE2,\u0017\u0001\u00039ja\u0016d\u0017N\\3\u0004\u0001U\t\u0001\n\u0005\u0002J\u00156\t\u0001'\u0003\u0002La\ti\u0001+\u001b9fY&tW-T8eK2\f\u0011\u0002]5qK2Lg.\u001a\u0011\u0002\u001b1|w\rT5lK2L\u0007n\\8e+\u0005y\u0005C\u0001\u001eQ\u0013\t\t6H\u0001\u0004E_V\u0014G.Z\u0001\u000fY><G*[6fY&Dwn\u001c3!\u00035awn\u001a)feBdW\r_5us\u0006qAn\\4QKJ\u0004H.\u001a=jif\u0004\u0013A\u0003<pG\u0006\u0014W\u000f\\1ssV\tq\u000bE\u0002;1jK!!W\u001e\u0003\u000b\u0005\u0013(/Y=\u0011\u0005m\u0013gB\u0001/a!\ti6(D\u0001_\u0015\tyf)\u0001\u0004=e>|GOP\u0005\u0003Cn\na\u0001\u0015:fI\u00164\u0017BA2e\u0005\u0019\u0019FO]5oO*\u0011\u0011mO\u0001\fm>\u001c\u0017MY;mCJL\b%\u0001\u0004=S:LGO\u0010\u000b\u0006Q*\\G.\u001c\t\u0003S\u0002i\u0011A\f\u0005\u0006\u000b&\u0001\r\u0001\u0013\u0005\u0006\u001b&\u0001\ra\u0014\u0005\u0006'&\u0001\ra\u0014\u0005\u0006+&\u0001\raV\u0001\u0004Y\u0012\fW#\u00019\u0011\u0005E$X\"\u0001:\u000b\u0005M\u0004\u0014AC2mkN$XM]5oO&\u0011QO\u001d\u0002\t\u0019\u0012\u000bUj\u001c3fY\u0006!A\u000eZ1!\u0003A!\u0017n\u001d;sS\n,H/\u001a3N_\u0012,G.F\u0001z!\t\t(0\u0003\u0002|e\n\u0019B)[:ue&\u0014W\u000f^3e\u0019\u0012\u000bUj\u001c3fY\u0006)BO]1j]&tw\rT8h\u0019&\\W\r\\5i_>$\u0017\u0001\u00037pOB\u0013\u0018n\u001c:\u0002\u0019A\u0014X\r\u001d:pG\u0016\u001c8o\u001c:\u0002\u001bA\u0014X\r\u001d:pG\u0016\u001c8o\u001c:!\u0003%!(/\u00198tM>\u0014X\u000e\u0006\u0003\u0002\u0006\u0005\u001d\u0002\u0003BA\u0004\u0003CqA!!\u0003\u0002\u001c9!\u00111BA\f\u001d\u0011\ti!!\u0006\u000f\t\u0005=\u00111\u0003\b\u0004;\u0006E\u0011\"A\u001c\n\u0005U2\u0014BA\u001a5\u0013\r\tIBM\u0001\u0004gFd\u0017\u0002BA\u000f\u0003?\tq\u0001]1dW\u0006<WMC\u0002\u0002\u001aIJA!a\t\u0002&\tIA)\u0019;b\rJ\fW.\u001a\u0006\u0005\u0003;\ty\u0002C\u0004\u0002*E\u0001\r!a\u000b\u0002\t\u0011\fG/\u0019\u0019\u0005\u0003[\tI\u0004\u0005\u0004\u00020\u0005E\u0012QG\u0007\u0003\u0003?IA!a\r\u0002 \t9A)\u0019;bg\u0016$\b\u0003BA\u001c\u0003sa\u0001\u0001\u0002\u0007\u0002<\u0005\u001d\u0012\u0011!A\u0001\u0006\u0003\tiDA\u0002`IE\nB!a\u0010\u0002FA\u0019!(!\u0011\n\u0007\u0005\r3HA\u0004O_RD\u0017N\\4\u0011\u0007i\n9%C\u0002\u0002Jm\u00121!\u00118z\u0003Q\u0019w.\u001c9vi\u0016dun\u001a)feBdW\r_5usR\u0019q*a\u0014\t\u000f\u0005%\"\u00031\u0001\u0002RA\"\u00111KA,!\u0019\ty#!\r\u0002VA!\u0011qGA,\t1\tI&a\u0014\u0002\u0002\u0003\u0005)\u0011AA\u001f\u0005\ryFEM\u0001\u0007i>\u0004\u0018nY:\u0015\t\u0005\u0015\u0011q\f\u0005\b\u0003C\u001a\u0002\u0019AA2\u0003Ai\u0017\r\u001f+fe6\u001c\b+\u001a:U_BL7\rE\u0002;\u0003KJ1!a\u001a<\u0005\rIe\u000e^\u0001\u000eSN$\u0015n\u001d;sS\n,H/\u001a3\u0016\u0005\u00055\u0004c\u0001\u001e\u0002p%\u0019\u0011\u0011O\u001e\u0003\u000f\t{w\u000e\\3b]\u0006Iao\\2bENK'0Z\u000b\u0003\u0003G\n\u0001\u0003Z8d\u0007>t7-\u001a8ue\u0006$\u0018n\u001c8\u0016\u0005\u0005m\u0004c\u0001\u001eY\u001f\u0006\u0011Bo\u001c9jG\u000e{gnY3oiJ\fG/[8o\u0003\u00159(/\u001b;f+\t\t\u0019\tE\u0002A\u0003\u000bK1!a\"B\u0005!iEj\u0016:ji\u0016\u0014\u0018A\u0003'E\u0003^\u0013\u0018\r\u001d9feB\u0011\u0011NG\n\u00055e\ny\t\u0005\u0003A\u0003#C\u0017bAAJ\u0003\nQQ\n\u0014*fC\u0012\f'\r\\3\u0015\u0005\u0005-\u0015!\u0004+P\u0017\u0016s\u0015JW#S?\u000e{E*\u0006\u0002\u0002\u001cB!\u0011QTAT\u001b\t\tyJ\u0003\u0003\u0002\"\u0006\r\u0016\u0001\u00027b]\u001eT!!!*\u0002\t)\fg/Y\u0005\u0004G\u0006}\u0015A\u0004+P\u0017\u0016s\u0015JW#S?\u000e{E\nI\u0001\u0016'R{\u0005kV(S\tN{&+R'P-\u0016\u0013vlQ(M\u0003Y\u0019Fk\u0014)X\u001fJ#5k\u0018*F\u001b>3VIU0D\u001f2\u0003\u0013\u0001E\"P+:#vLV#D)>\u0013vlQ(M\u0003E\u0019u*\u0016(U?Z+5\tV(S?\u000e{E\nI\u0001\rO\u0016$\bK]3Ti\u0006<Wm\u001d\u000b\t\u0003o\u000by,a1\u0002HB!!\bWA]!\rI\u00151X\u0005\u0004\u0003{\u0003$!\u0004)ja\u0016d\u0017N\\3Ti\u0006<W\r\u0003\u0004\u0002B\n\u0002\rAW\u0001\tM\u0016\fG/\u001e:fg\"1\u0011Q\u0019\u0012A\u0002]\u000b1cY;ti>l\u0017N_3e'R|\u0007oV8sINDq!!3#\u0001\u0004\t\u0019'\u0001\u0007nCb4vnY1c'&TX-A\u0002gSR$R\u0003[Ah\u0003#\f\u0019.a6\u0002\\\u0006}\u00171]As\u0003O\fI\u000fC\u0004\u0002*\r\u0002\r!!\u0002\t\r\u0005\u00057\u00051\u0001[\u0011\u001d\t)n\ta\u0001\u0003G\n\u0011a\u001b\u0005\b\u00033\u001c\u0003\u0019AA2\u0003\u001di\u0017\r_%uKJDa!!8$\u0001\u0004Q\u0016!C8qi&l\u0017N_3s\u0011\u0019\t\to\ta\u0001\u001f\u0006y1/\u001e2tC6\u0004H.\u001b8h%\u0006$X\r\u0003\u0004\u0002~\r\u0002\ra\u0014\u0005\b\u0003o\u001a\u0003\u0019AA>\u0011\u0019\t)m\ta\u0001/\"9\u0011\u0011Z\u0012A\u0002\u0005\r\u0014\u0001\u0002:fC\u0012,\"!a<\u0011\t\u0001\u000b\t\u0010[\u0005\u0004\u0003g\f%\u0001C'M%\u0016\fG-\u001a:\u0002\t1|\u0017\r\u001a\u000b\u0004Q\u0006e\bBBA~K\u0001\u0007!,\u0001\u0003qCRD'\u0001\u0005'E\u0003^\u0013\u0018\r\u001d9fe^\u0013\u0018\u000e^3s'\r1\u00131Q\u0001\tS:\u001cH/\u00198dKR!!Q\u0001B\u0005!\r\u00119AJ\u0007\u00025!1!\u0011\u0001\u0015A\u0002!\f\u0001b]1wK&k\u0007\u000f\u001c\u000b\u0005\u0005\u001f\u0011)\u0002E\u0002;\u0005#I1Aa\u0005<\u0005\u0011)f.\u001b;\t\r\u0005m\u0018\u00061\u0001[\u0005AaE)Q,sCB\u0004XM\u001d*fC\u0012,'oE\u0002+\u0003_$\"A!\b\u0011\u0007\t\u001d!\u0006F\u0002i\u0005CAa!a?-\u0001\u0004Q\u0006"
)
public class LDAWrapper implements MLWritable {
   private DistributedLDAModel distributedModel;
   private double trainingLogLikelihood;
   private double logPrior;
   private boolean isDistributed;
   private int vocabSize;
   private double[] docConcentration;
   private double topicConcentration;
   private final PipelineModel pipeline;
   private final double logLikelihood;
   private final double logPerplexity;
   private final String[] vocabulary;
   private final LDAModel lda;
   private final PipelineModel preprocessor;
   private volatile byte bitmap$0;

   public static LDAWrapper load(final String path) {
      return LDAWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LDAWrapper$.MODULE$.read();
   }

   public static LDAWrapper fit(final Dataset data, final String features, final int k, final int maxIter, final String optimizer, final double subsamplingRate, final double topicConcentration, final double[] docConcentration, final String[] customizedStopWords, final int maxVocabSize) {
      return LDAWrapper$.MODULE$.fit(data, features, k, maxIter, optimizer, subsamplingRate, topicConcentration, docConcentration, customizedStopWords, maxVocabSize);
   }

   public static String COUNT_VECTOR_COL() {
      return LDAWrapper$.MODULE$.COUNT_VECTOR_COL();
   }

   public static String STOPWORDS_REMOVER_COL() {
      return LDAWrapper$.MODULE$.STOPWORDS_REMOVER_COL();
   }

   public static String TOKENIZER_COL() {
      return LDAWrapper$.MODULE$.TOKENIZER_COL();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public PipelineModel pipeline() {
      return this.pipeline;
   }

   public double logLikelihood() {
      return this.logLikelihood;
   }

   public double logPerplexity() {
      return this.logPerplexity;
   }

   public String[] vocabulary() {
      return this.vocabulary;
   }

   private LDAModel lda() {
      return this.lda;
   }

   private DistributedLDAModel distributedModel$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.distributedModel = (DistributedLDAModel).MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps(this.pipeline().stages()));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.distributedModel;
   }

   private DistributedLDAModel distributedModel() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.distributedModel$lzycompute() : this.distributedModel;
   }

   private double trainingLogLikelihood$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.trainingLogLikelihood = this.distributedModel().trainingLogLikelihood();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.trainingLogLikelihood;
   }

   public double trainingLogLikelihood() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.trainingLogLikelihood$lzycompute() : this.trainingLogLikelihood;
   }

   private double logPrior$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.logPrior = this.distributedModel().logPrior();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.logPrior;
   }

   public double logPrior() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.logPrior$lzycompute() : this.logPrior;
   }

   private PipelineModel preprocessor() {
      return this.preprocessor;
   }

   public Dataset transform(final Dataset data) {
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (vec) -> vec.toArray();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LDAWrapper.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LDAWrapper.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction vec2ary = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      String outputCol = this.lda().getTopicDistributionCol();
      String tempCol = String.valueOf(Identifiable$.MODULE$.randomUID(outputCol));
      Dataset preprocessed = this.preprocessor().transform(data);
      return this.lda().transform(preprocessed, new ParamPair(this.lda().topicDistributionCol(), tempCol), scala.collection.immutable.Nil..MODULE$).withColumn(outputCol, vec2ary.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(tempCol)})))).drop(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{LDAWrapper$.MODULE$.TOKENIZER_COL(), LDAWrapper$.MODULE$.STOPWORDS_REMOVER_COL(), LDAWrapper$.MODULE$.COUNT_VECTOR_COL(), tempCol})));
   }

   public double computeLogPerplexity(final Dataset data) {
      return this.lda().logPerplexity(this.preprocessor().transform(data));
   }

   public Dataset topics(final int maxTermsPerTopic) {
      Dataset topicIndices = this.lda().describeTopics(maxTermsPerTopic);
      if (!.MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.vocabulary())) && this.vocabulary().length >= this.vocabSize()) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (indices) -> (ArraySeq)indices.map((i) -> $anonfun$topics$2(this, BoxesRunTime.unboxToInt(i)));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LDAWrapper.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection.mutable").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.mutable.ArraySeq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$2() {
            }
         }

         TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LDAWrapper.class.getClassLoader());

         final class $typecreator2$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection.mutable").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.mutable.ArraySeq"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator2$2() {
            }
         }

         UserDefinedFunction index2term = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2()));
         return topicIndices.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col("topic"), index2term.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col("termIndices")}))).as("term"), org.apache.spark.sql.functions..MODULE$.col("termWeights")})));
      } else {
         return topicIndices;
      }
   }

   private boolean isDistributed$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.isDistributed = this.lda().isDistributed();
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.isDistributed;
   }

   public boolean isDistributed() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.isDistributed$lzycompute() : this.isDistributed;
   }

   private int vocabSize$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.vocabSize = this.lda().vocabSize();
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.vocabSize;
   }

   public int vocabSize() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.vocabSize$lzycompute() : this.vocabSize;
   }

   private double[] docConcentration$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 32) == 0) {
            this.docConcentration = this.lda().getEffectiveDocConcentration();
            this.bitmap$0 = (byte)(this.bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.docConcentration;
   }

   public double[] docConcentration() {
      return (byte)(this.bitmap$0 & 32) == 0 ? this.docConcentration$lzycompute() : this.docConcentration;
   }

   private double topicConcentration$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 64) == 0) {
            this.topicConcentration = this.lda().getEffectiveTopicConcentration();
            this.bitmap$0 = (byte)(this.bitmap$0 | 64);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.topicConcentration;
   }

   public double topicConcentration() {
      return (byte)(this.bitmap$0 & 64) == 0 ? this.topicConcentration$lzycompute() : this.topicConcentration;
   }

   public MLWriter write() {
      return new LDAWrapperWriter(this);
   }

   // $FF: synthetic method
   public static final String $anonfun$topics$2(final LDAWrapper $this, final int i) {
      return $this.vocabulary()[i];
   }

   public LDAWrapper(final PipelineModel pipeline, final double logLikelihood, final double logPerplexity, final String[] vocabulary) {
      this.pipeline = pipeline;
      this.logLikelihood = logLikelihood;
      this.logPerplexity = logPerplexity;
      this.vocabulary = vocabulary;
      MLWritable.$init$(this);
      this.lda = (LDAModel).MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps(pipeline.stages()));
      this.preprocessor = new PipelineModel(String.valueOf(Identifiable$.MODULE$.randomUID(pipeline.uid())), (Transformer[]).MODULE$.dropRight$extension(scala.Predef..MODULE$.refArrayOps(pipeline.stages()), 1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class LDAWrapperWriter extends MLWriter {
      private final LDAWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         JObject rMetadata = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("logLikelihood"), BoxesRunTime.boxToDouble(this.instance.logLikelihood())), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> $anonfun$saveImpl$3(BoxesRunTime.unboxToDouble(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("logPerplexity"), BoxesRunTime.boxToDouble(this.instance.logPerplexity())), (x) -> $anonfun$saveImpl$4(BoxesRunTime.unboxToDouble(x))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("vocabulary"), scala.Predef..MODULE$.wrapRefArray((Object[])this.instance.vocabulary()).toList()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))));
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(rMetadata, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LDAWrapperWriter.class.getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$3() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).write().text(rMetadataPath);
         this.instance.pipeline().save(pipelinePath);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$3(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$4(final double x) {
         return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
      }

      public LDAWrapperWriter(final LDAWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class LDAWrapperReader extends MLReader {
      public LDAWrapper load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         String rMetadataStr = ((Row)this.sparkSession().read().text(rMetadataPath).first()).getString(0);
         JValue rMetadata = org.json4s.jackson.JsonMethods..MODULE$.parse(rMetadataStr, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
         double logLikelihood = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "logLikelihood")), format, scala.reflect.ManifestFactory..MODULE$.Double()));
         double logPerplexity = BoxesRunTime.unboxToDouble(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "logPerplexity")), format, scala.reflect.ManifestFactory..MODULE$.Double()));
         String[] vocabulary = (String[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(rMetadata), "vocabulary")), format, scala.reflect.ManifestFactory..MODULE$.classType(List.class, scala.reflect.ManifestFactory..MODULE$.classType(String.class), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new LDAWrapper(pipeline, logLikelihood, logPerplexity, vocabulary);
      }
   }
}
