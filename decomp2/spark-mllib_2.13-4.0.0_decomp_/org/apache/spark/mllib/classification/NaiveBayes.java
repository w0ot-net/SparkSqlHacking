package org.apache.spark.mllib.classification;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001\u0002\u000f\u001e\u0001!B\u0001\"\u0011\u0001\u0003\u0002\u0004%IA\u0011\u0005\t\r\u0002\u0011\t\u0019!C\u0005\u000f\"AQ\n\u0001B\u0001B\u0003&1\t\u0003\u0005O\u0001\t\u0005\r\u0011\"\u0003P\u0011!A\u0006A!a\u0001\n\u0013I\u0006\u0002C.\u0001\u0005\u0003\u0005\u000b\u0015\u0002)\t\u000bq\u0003A\u0011B/\t\u000bq\u0003A\u0011\u00012\t\u000bq\u0003A\u0011A7\t\u000bE\u0004A\u0011\u0001:\t\u000bU\u0004A\u0011\u0001\"\t\u000b]\u0004A\u0011\u0001=\t\u000bm\u0004A\u0011A(\t\u000bu\u0004A\u0011\u0001@\b\u000f\u0005\u0015R\u0004#\u0001\u0002(\u00191A$\bE\u0001\u0003SAa\u0001\u0018\t\u0005\u0002\u0005e\u0002\"CA\u001e!\t\u0007I\u0011A\u000fP\u0011\u001d\ti\u0004\u0005Q\u0001\nAC\u0011\"a\u0010\u0011\u0005\u0004%\t!H(\t\u000f\u0005\u0005\u0003\u0003)A\u0005!\"Q\u00111\t\tC\u0002\u0013\u0005Q$!\u0012\t\u0011\u0005]\u0003\u0003)A\u0005\u0003\u000fBq!!\u0017\u0011\t\u0003\tY\u0006C\u0004\u0002ZA!\t!a\u0019\t\u000f\u0005e\u0003\u0003\"\u0001\u0002l!I\u0011Q\u000f\t\u0002\u0002\u0013%\u0011q\u000f\u0002\u000b\u001d\u0006Lg/\u001a\"bs\u0016\u001c(B\u0001\u0010 \u00039\u0019G.Y:tS\u001aL7-\u0019;j_:T!\u0001I\u0011\u0002\u000b5dG.\u001b2\u000b\u0005\t\u001a\u0013!B:qCJ\\'B\u0001\u0013&\u0003\u0019\t\u0007/Y2iK*\ta%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001S=Z\u0004C\u0001\u0016.\u001b\u0005Y#\"\u0001\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u00059Z#AB!osJ+g\r\u0005\u00021q9\u0011\u0011G\u000e\b\u0003eUj\u0011a\r\u0006\u0003i\u001d\na\u0001\u0010:p_Rt\u0014\"\u0001\u0017\n\u0005]Z\u0013a\u00029bG.\fw-Z\u0005\u0003si\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aN\u0016\u0011\u0005qzT\"A\u001f\u000b\u0005y\n\u0013\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0001k$a\u0002'pO\u001eLgnZ\u0001\u0007Y\u0006l'\rZ1\u0016\u0003\r\u0003\"A\u000b#\n\u0005\u0015[#A\u0002#pk\ndW-\u0001\u0006mC6\u0014G-Y0%KF$\"\u0001S&\u0011\u0005)J\u0015B\u0001&,\u0005\u0011)f.\u001b;\t\u000f1\u0013\u0011\u0011!a\u0001\u0007\u0006\u0019\u0001\u0010J\u0019\u0002\u000f1\fWN\u00193bA\u0005IQn\u001c3fYRK\b/Z\u000b\u0002!B\u0011\u0011+\u0016\b\u0003%N\u0003\"AM\u0016\n\u0005Q[\u0013A\u0002)sK\u0012,g-\u0003\u0002W/\n11\u000b\u001e:j]\u001eT!\u0001V\u0016\u0002\u001b5|G-\u001a7UsB,w\fJ3r)\tA%\fC\u0004M\u000b\u0005\u0005\t\u0019\u0001)\u0002\u00155|G-\u001a7UsB,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004=\u0002\f\u0007CA0\u0001\u001b\u0005i\u0002\"B!\b\u0001\u0004\u0019\u0005\"\u0002(\b\u0001\u0004\u0001FC\u00010d\u0011\u0015\t\u0005\u00021\u0001DQ\rAQm\u001b\t\u0003M&l\u0011a\u001a\u0006\u0003Q\u0006\n!\"\u00198o_R\fG/[8o\u0013\tQwMA\u0003TS:\u001cW-I\u0001m\u0003\u0015\td\u0006\u000e\u00181)\u0005q\u0006fA\u0005f_\u0006\n\u0001/A\u00031]er\u0003'A\u0005tKRd\u0015-\u001c2eCR\u0011al\u001d\u0005\u0006\u0003*\u0001\ra\u0011\u0015\u0004\u0015\u0015|\u0017!C4fi2\u000bWN\u00193bQ\rYQm[\u0001\rg\u0016$Xj\u001c3fYRK\b/\u001a\u000b\u0003=fDQA\u0014\u0007A\u0002AC3\u0001D3l\u000319W\r^'pI\u0016dG+\u001f9fQ\riQm[\u0001\u0004eVtGcA@\u0002\u0006A\u0019q,!\u0001\n\u0007\u0005\rQDA\bOC&4XMQ1zKNlu\u000eZ3m\u0011\u001d\t9A\u0004a\u0001\u0003\u0013\tA\u0001Z1uCB1\u00111BA\t\u0003+i!!!\u0004\u000b\u0007\u0005=\u0011%A\u0002sI\u0012LA!a\u0005\u0002\u000e\t\u0019!\u000b\u0012#\u0011\t\u0005]\u0011QD\u0007\u0003\u00033Q1!a\u0007 \u0003)\u0011Xm\u001a:fgNLwN\\\u0005\u0005\u0003?\tIB\u0001\u0007MC\n,G.\u001a3Q_&tG\u000fK\u0002\u000fK>D3\u0001A3p\u0003)q\u0015-\u001b<f\u0005\u0006LXm\u001d\t\u0003?B\u0019B\u0001E\u0015\u0002,A!\u0011QFA\u001c\u001b\t\tyC\u0003\u0003\u00022\u0005M\u0012AA5p\u0015\t\t)$\u0001\u0003kCZ\f\u0017bA\u001d\u00020Q\u0011\u0011qE\u0001\f\u001bVdG/\u001b8p[&\fG.\u0001\u0007Nk2$\u0018N\\8nS\u0006d\u0007%A\u0005CKJtw.\u001e7mS\u0006Q!)\u001a:o_VdG.\u001b\u0011\u0002'M,\b\u000f]8si\u0016$Wj\u001c3fYRK\b/Z:\u0016\u0005\u0005\u001d\u0003#BA%\u0003'\u0002VBAA&\u0015\u0011\ti%a\u0014\u0002\u0013%lW.\u001e;bE2,'bAA)W\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005U\u00131\n\u0002\u0004'\u0016$\u0018\u0001F:vaB|'\u000f^3e\u001b>$W\r\u001c+za\u0016\u001c\b%A\u0003ue\u0006Lg\u000eF\u0002\u0000\u0003;Bq!a\u0018\u0019\u0001\u0004\tI!A\u0003j]B,H\u000fK\u0002\u0019K>$Ra`A3\u0003OBq!a\u0018\u001a\u0001\u0004\tI\u0001C\u0003B3\u0001\u00071\tK\u0002\u001aK>$ra`A7\u0003_\n\t\bC\u0004\u0002`i\u0001\r!!\u0003\t\u000b\u0005S\u0002\u0019A\"\t\u000b9S\u0002\u0019\u0001))\u0007i)7.\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002zA!\u00111PAA\u001b\t\tiH\u0003\u0003\u0002\u0000\u0005M\u0012\u0001\u00027b]\u001eLA!a!\u0002~\t1qJ\u00196fGRD3\u0001E3pQ\ryQm\u001c"
)
public class NaiveBayes implements Serializable, Logging {
   private double lambda;
   private String modelType;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static NaiveBayesModel train(final RDD input, final double lambda, final String modelType) {
      return NaiveBayes$.MODULE$.train(input, lambda, modelType);
   }

   public static NaiveBayesModel train(final RDD input, final double lambda) {
      return NaiveBayes$.MODULE$.train(input, lambda);
   }

   public static NaiveBayesModel train(final RDD input) {
      return NaiveBayes$.MODULE$.train(input);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private double lambda() {
      return this.lambda;
   }

   private void lambda_$eq(final double x$1) {
      this.lambda = x$1;
   }

   private String modelType() {
      return this.modelType;
   }

   private void modelType_$eq(final String x$1) {
      this.modelType = x$1;
   }

   public NaiveBayes setLambda(final double lambda) {
      .MODULE$.require(lambda >= (double)0, () -> "Smoothing parameter must be nonnegative but got " + lambda);
      this.lambda_$eq(lambda);
      return this;
   }

   public double getLambda() {
      return this.lambda();
   }

   public NaiveBayes setModelType(final String modelType) {
      .MODULE$.require(NaiveBayes$.MODULE$.supportedModelTypes().contains(modelType), () -> "NaiveBayes was created with an unknown modelType: " + modelType + ".");
      this.modelType_$eq(modelType);
      return this;
   }

   public String getModelType() {
      return this.modelType();
   }

   public NaiveBayesModel run(final RDD data) {
      SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(data.context()).getOrCreate();
      org.apache.spark.ml.classification.NaiveBayes nb = (new org.apache.spark.ml.classification.NaiveBayes()).setModelType(this.modelType()).setSmoothing(this.lambda());
      SQLImplicits var10000 = spark.implicits();
      RDD var10001 = data.map((x0$1) -> {
         if (x0$1 != null) {
            double label = x0$1.label();
            Vector features = x0$1.features();
            return new Tuple2(BoxesRunTime.boxToDouble(label), features.asML());
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      SQLImplicits var10002 = spark.implicits();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(NaiveBayes.class.getClassLoader());

      final class $typecreator10$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator10$1() {
         }
      }

      Dataset dataset = var10000.rddToDatasetHolder(var10001, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"label", "features"})));
      org.apache.spark.ml.classification.NaiveBayesModel newModel = nb.trainWithLabelCheck(dataset, false);
      double[] pi = newModel.pi().toArray();
      double[][] theta = (double[][])scala.Array..MODULE$.ofDim(newModel.numClasses(), newModel.numFeatures(), scala.reflect.ClassTag..MODULE$.Double());
      newModel.theta().foreachActive((x0$2, x1$1, x2$1) -> {
         $anonfun$run$2(theta, BoxesRunTime.unboxToInt(x0$2), BoxesRunTime.unboxToInt(x1$1), BoxesRunTime.unboxToDouble(x2$1));
         return BoxedUnit.UNIT;
      });
      .MODULE$.assert(newModel.oldLabels() != null, () -> "The underlying ML NaiveBayes training does not produce labels.");
      return new NaiveBayesModel(newModel.oldLabels(), pi, theta, this.modelType());
   }

   // $FF: synthetic method
   public static final void $anonfun$run$2(final double[][] theta$1, final int x0$2, final int x1$1, final double x2$1) {
      Tuple3 var6 = new Tuple3(BoxesRunTime.boxToInteger(x0$2), BoxesRunTime.boxToInteger(x1$1), BoxesRunTime.boxToDouble(x2$1));
      if (var6 != null) {
         int i = BoxesRunTime.unboxToInt(var6._1());
         int j = BoxesRunTime.unboxToInt(var6._2());
         double v = BoxesRunTime.unboxToDouble(var6._3());
         theta$1[i][j] = v;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var6);
      }
   }

   public NaiveBayes(final double lambda, final String modelType) {
      this.lambda = lambda;
      this.modelType = modelType;
      super();
      Logging.$init$(this);
   }

   public NaiveBayes(final double lambda) {
      this(lambda, NaiveBayes$.MODULE$.Multinomial());
   }

   public NaiveBayes() {
      this((double)1.0F, NaiveBayes$.MODULE$.Multinomial());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
