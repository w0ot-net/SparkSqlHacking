package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.dstream.DStream;
import org.apache.spark.streaming.dstream.PairDStreamFunctions;
import org.apache.spark.util.random.XORShiftRandom;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.collection.mutable.ArraySeq;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005e\u0001B\u0013'\u0001EB\u0001B\u0013\u0001\u0003\u0002\u0004%\ta\u0013\u0005\t1\u0002\u0011\t\u0019!C\u00013\"A\u0001\r\u0001B\u0001B\u0003&A\n\u0003\u0005c\u0001\t\u0005\r\u0011\"\u0001d\u0011!A\u0007A!a\u0001\n\u0003I\u0007\u0002\u00037\u0001\u0005\u0003\u0005\u000b\u0015\u00023\t\u00119\u0004!\u00111A\u0005\u0002=D\u0001\"\u001f\u0001\u0003\u0002\u0004%\tA\u001f\u0005\t{\u0002\u0011\t\u0011)Q\u0005a\"1q\u0010\u0001C\u0001\u0003\u0003Aaa \u0001\u0005\u0002\u0005U\u0001\"CA\r\u0001\u0001\u0007I\u0011CA\u000e\u0011%\t\u0019\u0003\u0001a\u0001\n#\t)\u0003\u0003\u0005\u0002*\u0001\u0001\u000b\u0015BA\u000f\u0011\u001d\tY\u0003\u0001C\u0001\u0003[Aq!!\u000e\u0001\t\u0003\t9\u0004C\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005-\u0003\u0001\"\u0001\u0002N!9\u0011Q\u000e\u0001\u0005\u0002\u0005=\u0004\"CAC\u0001E\u0005I\u0011AAD\u0011\u001d\tY\n\u0001C\u0001\u0003;Cq!!)\u0001\t\u0003\t\u0019\u000bC\u0004\u0002\"\u0002!\t!a/\t\u000f\u0005U\u0007\u0001\"\u0001\u0002X\"9\u0011Q\u001b\u0001\u0005\u0002\u0005}\u0007bBA{\u0001\u0011\u0005\u0011q\u001f\u0005\b\u0003k\u0004A\u0011\u0001B\u001a\u0011!\u00119\u0005\u0001Q\u0005\n\t%s\u0001\u0003B'M!\u0005aEa\u0014\u0007\u000f\u00152\u0003\u0012\u0001\u0014\u0003R!1qP\bC\u0001\u0005;B\u0011Ba\u0018\u001f\u0005\u0004%)A!\u0019\t\u0011\t%d\u0004)A\u0007\u0005GB\u0011Ba\u001b\u001f\u0005\u0004%)A!\u001c\t\u0011\tUd\u0004)A\u0007\u0005_B\u0011Ba\u001e\u001f\u0003\u0003%IA!\u001f\u0003\u001fM#(/Z1nS:<7*T3b]NT!a\n\u0015\u0002\u0015\rdWo\u001d;fe&twM\u0003\u0002*U\u0005)Q\u000e\u001c7jE*\u00111\u0006L\u0001\u0006gB\f'o\u001b\u0006\u0003[9\na!\u00199bG\",'\"A\u0018\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001\u0011\u0004H\u0010\t\u0003gYj\u0011\u0001\u000e\u0006\u0002k\u0005)1oY1mC&\u0011q\u0007\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\u0005ebT\"\u0001\u001e\u000b\u0005mR\u0013\u0001C5oi\u0016\u0014h.\u00197\n\u0005uR$a\u0002'pO\u001eLgn\u001a\t\u0003\u007f\u001ds!\u0001Q#\u000f\u0005\u0005#U\"\u0001\"\u000b\u0005\r\u0003\u0014A\u0002\u001fs_>$h(C\u00016\u0013\t1E'A\u0004qC\u000e\\\u0017mZ3\n\u0005!K%\u0001D*fe&\fG.\u001b>bE2,'B\u0001$5\u0003\u0005YW#\u0001'\u0011\u0005Mj\u0015B\u0001(5\u0005\rIe\u000e\u001e\u0015\u0004\u0003A3\u0006CA)U\u001b\u0005\u0011&BA*+\u0003)\tgN\\8uCRLwN\\\u0005\u0003+J\u0013QaU5oG\u0016\f\u0013aV\u0001\u0006c9\u0012d\u0006M\u0001\u0006W~#S-\u001d\u000b\u00035v\u0003\"aM.\n\u0005q#$\u0001B+oSRDqA\u0018\u0002\u0002\u0002\u0003\u0007A*A\u0002yIEB3A\u0001)W\u0003\tY\u0007\u0005K\u0002\u0004!Z\u000b1\u0002Z3dCf4\u0015m\u0019;peV\tA\r\u0005\u00024K&\u0011a\r\u000e\u0002\u0007\t>,(\r\\3)\u0007\u0011\u0001f+A\beK\u000e\f\u0017PR1di>\u0014x\fJ3r)\tQ&\u000eC\u0004_\u000b\u0005\u0005\t\u0019\u00013)\u0007\u0015\u0001f+\u0001\u0007eK\u000e\f\u0017PR1di>\u0014\b\u0005K\u0002\u0007!Z\u000b\u0001\u0002^5nKVs\u0017\u000e^\u000b\u0002aB\u0011\u0011/\u001e\b\u0003eN\u0004\"!\u0011\u001b\n\u0005Q$\u0014A\u0002)sK\u0012,g-\u0003\u0002wo\n11\u000b\u001e:j]\u001eT!\u0001\u001e\u001b)\u0007\u001d\u0001f+\u0001\u0007uS6,WK\\5u?\u0012*\u0017\u000f\u0006\u0002[w\"9a\fCA\u0001\u0002\u0004\u0001\bf\u0001\u0005Q-\u0006IA/[7f+:LG\u000f\t\u0015\u0004\u0013A3\u0016A\u0002\u001fj]&$h\b\u0006\u0005\u0002\u0004\u0005\u001d\u00111BA\b!\r\t)\u0001A\u0007\u0002M!)!J\u0003a\u0001\u0019\"\"\u0011q\u0001)W\u0011\u0015\u0011'\u00021\u0001eQ\u0011\tY\u0001\u0015,\t\u000b9T\u0001\u0019\u00019)\t\u0005=\u0001K\u0016\u0015\u0004\u0015A3FCAA\u0002Q\rY\u0001KV\u0001\u0006[>$W\r\\\u000b\u0003\u0003;\u0001B!!\u0002\u0002 %\u0019\u0011\u0011\u0005\u0014\u0003)M#(/Z1nS:<7*T3b]Nlu\u000eZ3m\u0003%iw\u000eZ3m?\u0012*\u0017\u000fF\u0002[\u0003OA\u0001BX\u0007\u0002\u0002\u0003\u0007\u0011QD\u0001\u0007[>$W\r\u001c\u0011\u0002\tM,Go\u0013\u000b\u0005\u0003_\t\t$D\u0001\u0001\u0011\u0015Qu\u00021\u0001MQ\ry\u0001KV\u0001\u000fg\u0016$H)Z2bs\u001a\u000b7\r^8s)\u0011\ty#!\u000f\t\r\u0005m\u0002\u00031\u0001e\u0003\u0005\t\u0007f\u0001\tQ-\u0006Y1/\u001a;IC24G*\u001b4f)\u0019\ty#a\u0011\u0002H!1\u0011QI\tA\u0002\u0011\f\u0001\u0002[1mM2Kg-\u001a\u0005\u0006]F\u0001\r\u0001\u001d\u0015\u0004#A3\u0016!E:fi&s\u0017\u000e^5bY\u000e+g\u000e^3sgR1\u0011qFA(\u0003KBq!!\u0015\u0013\u0001\u0004\t\u0019&A\u0004dK:$XM]:\u0011\u000bM\n)&!\u0017\n\u0007\u0005]CGA\u0003BeJ\f\u0017\u0010\u0005\u0003\u0002\\\u0005\u0005TBAA/\u0015\r\ty\u0006K\u0001\u0007Y&t\u0017\r\\4\n\t\u0005\r\u0014Q\f\u0002\u0007-\u0016\u001cGo\u001c:\t\u000f\u0005\u001d$\u00031\u0001\u0002j\u00059q/Z5hQR\u001c\b\u0003B\u001a\u0002V\u0011D3A\u0005)W\u0003A\u0019X\r\u001e*b]\u0012|WnQ3oi\u0016\u00148\u000f\u0006\u0005\u00020\u0005E\u0014QOA=\u0011\u0019\t\u0019h\u0005a\u0001\u0019\u0006\u0019A-[7\t\r\u0005]4\u00031\u0001e\u0003\u00199X-[4ii\"I\u00111P\n\u0011\u0002\u0003\u0007\u0011QP\u0001\u0005g\u0016,G\rE\u00024\u0003\u007fJ1!!!5\u0005\u0011auN\\4)\u0007M\u0001f+\u0001\u000etKR\u0014\u0016M\u001c3p[\u000e+g\u000e^3sg\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\n*\"\u0011QPAFW\t\ti\t\u0005\u0003\u0002\u0010\u0006]UBAAI\u0015\u0011\t\u0019*!&\u0002\u0013Ut7\r[3dW\u0016$'BA*5\u0013\u0011\tI*!%\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0006mCR,7\u000f^'pI\u0016dGCAA\u000fQ\r)\u0002KV\u0001\biJ\f\u0017N\\(o)\rQ\u0016Q\u0015\u0005\b\u0003O3\u0002\u0019AAU\u0003\u0011!\u0017\r^1\u0011\r\u0005-\u0016QWA-\u001b\t\tiK\u0003\u0003\u00020\u0006E\u0016a\u00023tiJ,\u0017-\u001c\u0006\u0004\u0003gS\u0013!C:ue\u0016\fW.\u001b8h\u0013\u0011\t9,!,\u0003\u000f\u0011\u001bFO]3b[\"\u001aa\u0003\u0015,\u0015\u0007i\u000bi\fC\u0004\u0002(^\u0001\r!a0\u0011\r\u0005\u0005\u00171ZA-\u001b\t\t\u0019M\u0003\u0003\u0002F\u0006\u001d\u0017\u0001\u00026bm\u0006TA!!3\u00022\u0006\u0019\u0011\r]5\n\t\u00055\u00171\u0019\u0002\f\u0015\u00064\u0018\rR*ue\u0016\fW\u000e\u000b\u0003\u0018!\u0006E\u0017EAAj\u0003\u0015\td\u0006\u000e\u00181\u0003%\u0001(/\u001a3jGR|e\u000e\u0006\u0003\u0002Z\u0006m\u0007#BAV\u0003kc\u0005bBAT1\u0001\u0007\u0011\u0011\u0016\u0015\u00041A3F\u0003BAq\u0003c\u0004b!!1\u0002L\u0006\r\b\u0003BAs\u0003[l!!a:\u000b\t\u0005%\u00181^\u0001\u0005Y\u0006twM\u0003\u0002\u0002F&!\u0011q^At\u0005\u001dIe\u000e^3hKJDq!a*\u001a\u0001\u0004\ty\f\u000b\u0003\u001a!\u0006E\u0017a\u00049sK\u0012L7\r^(o-\u0006dW/Z:\u0016\t\u0005e(\u0011\u0002\u000b\u0005\u0003w\u0014Y\u0003\u0006\u0003\u0002~\nm\u0001CBAV\u0003k\u000by\u0010\u0005\u00044\u0005\u0003\u0011)\u0001T\u0005\u0004\u0005\u0007!$A\u0002+va2,'\u0007\u0005\u0003\u0003\b\t%A\u0002\u0001\u0003\b\u0005\u0017Q\"\u0019\u0001B\u0007\u0005\u0005Y\u0015\u0003\u0002B\b\u0005+\u00012a\rB\t\u0013\r\u0011\u0019\u0002\u000e\u0002\b\u001d>$\b.\u001b8h!\r\u0019$qC\u0005\u0004\u00053!$aA!os\"I!Q\u0004\u000e\u0002\u0002\u0003\u000f!qD\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004C\u0002B\u0011\u0005O\u0011)!\u0004\u0002\u0003$)\u0019!Q\u0005\u001b\u0002\u000fI,g\r\\3di&!!\u0011\u0006B\u0012\u0005!\u0019E.Y:t)\u0006<\u0007bBAT5\u0001\u0007!Q\u0006\t\u0007\u0003W\u000b)La\f\u0011\u000fM\u0012\tA!\u0002\u0002Z!\u001a!\u0004\u0015,\u0016\t\tU\"q\b\u000b\u0005\u0005o\u0011\t\u0005\u0005\u0005\u0002B\ne\"QHAr\u0013\u0011\u0011Y$a1\u0003\u001f)\u000bg/\u0019)bSJ$5\u000b\u001e:fC6\u0004BAa\u0002\u0003@\u00119!1B\u000eC\u0002\t5\u0001bBAT7\u0001\u0007!1\t\t\t\u0003\u0003\u0014ID!\u0010\u0002Z!\"1\u0004UAi\u0003E\t7o]3si&s\u0017\u000e^5bY&TX\r\u001a\u000b\u00025\"\u001a\u0001\u0001\u0015,\u0002\u001fM#(/Z1nS:<7*T3b]N\u00042!!\u0002\u001f'\u0011q\"Ga\u0015\u0011\t\tU#1L\u0007\u0003\u0005/RAA!\u0017\u0002l\u0006\u0011\u0011n\\\u0005\u0004\u0011\n]CC\u0001B(\u0003\u001d\u0011\u0015\tV\"I\u000bN+\"Aa\u0019\u0010\u0005\t\u0015\u0014E\u0001B4\u0003\u001d\u0011\u0017\r^2iKN\f\u0001BQ!U\u0007\"+5\u000bI\u0001\u0007!>Ke\nV*\u0016\u0005\t=tB\u0001B9C\t\u0011\u0019(\u0001\u0004q_&tGo]\u0001\b!>Ke\nV*!\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011Y\b\u0005\u0003\u0002f\nu\u0014\u0002\u0002B@\u0003O\u0014aa\u00142kK\u000e$\b"
)
public class StreamingKMeans implements Logging, Serializable {
   private int k;
   private double decayFactor;
   private String timeUnit;
   private StreamingKMeansModel model;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String POINTS() {
      return StreamingKMeans$.MODULE$.POINTS();
   }

   public static String BATCHES() {
      return StreamingKMeans$.MODULE$.BATCHES();
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

   public int k() {
      return this.k;
   }

   public void k_$eq(final int x$1) {
      this.k = x$1;
   }

   public double decayFactor() {
      return this.decayFactor;
   }

   public void decayFactor_$eq(final double x$1) {
      this.decayFactor = x$1;
   }

   public String timeUnit() {
      return this.timeUnit;
   }

   public void timeUnit_$eq(final String x$1) {
      this.timeUnit = x$1;
   }

   public StreamingKMeansModel model() {
      return this.model;
   }

   public void model_$eq(final StreamingKMeansModel x$1) {
      this.model = x$1;
   }

   public StreamingKMeans setK(final int k) {
      .MODULE$.require(k > 0, () -> "Number of clusters must be positive but got " + k);
      this.k_$eq(k);
      return this;
   }

   public StreamingKMeans setDecayFactor(final double a) {
      .MODULE$.require(a >= (double)0, () -> "Decay factor must be nonnegative but got " + a);
      this.decayFactor_$eq(a);
      return this;
   }

   public StreamingKMeans setHalfLife(final double halfLife, final String timeUnit) {
      label27: {
         .MODULE$.require(halfLife > (double)0, () -> "Half life must be positive but got " + halfLife);
         String var4 = "batches";
         if (timeUnit == null) {
            if (var4 == null) {
               break label27;
            }
         } else if (timeUnit.equals(var4)) {
            break label27;
         }

         String var5 = "points";
         if (timeUnit == null) {
            if (var5 != null) {
               throw new IllegalArgumentException("Invalid time unit for decay: " + timeUnit);
            }
         } else if (!timeUnit.equals(var5)) {
            throw new IllegalArgumentException("Invalid time unit for decay: " + timeUnit);
         }
      }

      this.decayFactor_$eq(scala.math.package..MODULE$.exp(scala.math.package..MODULE$.log((double)0.5F) / halfLife));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Setting decay factor to: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VALUE..MODULE$, BoxesRunTime.boxToDouble(this.decayFactor()))})))));
      this.timeUnit_$eq(timeUnit);
      return this;
   }

   public StreamingKMeans setInitialCenters(final Vector[] centers, final double[] weights) {
      .MODULE$.require(centers.length == weights.length, () -> "Number of initial centers must be equal to number of weights");
      .MODULE$.require(centers.length == this.k(), () -> {
         int var10000 = this.k();
         return "Number of initial centers must be " + var10000 + " but got " + centers.length;
      });
      .MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.doubleArrayOps(weights), (JFunction1.mcZD.sp)(x$5) -> x$5 >= (double)0), () -> {
         ArraySeq.ofDouble var10000 = .MODULE$.wrapDoubleArray(weights);
         return "Weight for each initial center must be nonnegative but got [" + var10000.mkString(" ") + "]";
      });
      this.model_$eq(new StreamingKMeansModel(centers, weights));
      return this;
   }

   public StreamingKMeans setRandomCenters(final int dim, final double weight, final long seed) {
      .MODULE$.require(dim > 0, () -> "Number of dimensions must be positive but got " + dim);
      .MODULE$.require(weight >= (double)0, () -> "Weight for each center must be nonnegative but got " + weight);
      XORShiftRandom random = new XORShiftRandom(seed);
      Vector[] centers = (Vector[])scala.Array..MODULE$.fill(this.k(), () -> Vectors$.MODULE$.dense((double[])scala.Array..MODULE$.fill(dim, (JFunction0.mcD.sp)() -> random.nextGaussian(), scala.reflect.ClassTag..MODULE$.Double())), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
      double[] weights = (double[])scala.Array..MODULE$.fill(this.k(), (JFunction0.mcD.sp)() -> weight, scala.reflect.ClassTag..MODULE$.Double());
      this.model_$eq(new StreamingKMeansModel(centers, weights));
      return this;
   }

   public long setRandomCenters$default$3() {
      return org.apache.spark.util.Utils..MODULE$.random().nextLong();
   }

   public StreamingKMeansModel latestModel() {
      return this.model();
   }

   public void trainOn(final DStream data) {
      this.assertInitialized();
      data.foreachRDD((rdd, time) -> {
         $anonfun$trainOn$1(this, rdd, time);
         return BoxedUnit.UNIT;
      });
   }

   public void trainOn(final JavaDStream data) {
      this.trainOn(data.dstream());
   }

   public DStream predictOn(final DStream data) {
      this.assertInitialized();
      StreamingKMeansModel var2 = this.model();
      return data.map((point) -> BoxesRunTime.boxToInteger($anonfun$predictOn$1(var2, point)), scala.reflect.ClassTag..MODULE$.Int());
   }

   public JavaDStream predictOn(final JavaDStream data) {
      return org.apache.spark.streaming.api.java.JavaDStream..MODULE$.fromDStream(this.predictOn(data.dstream()), scala.reflect.ClassTag..MODULE$.apply(Integer.class));
   }

   public DStream predictOnValues(final DStream data, final ClassTag evidence$1) {
      this.assertInitialized();
      ClassTag x$3 = scala.reflect.ClassTag..MODULE$.apply(Vector.class);
      Null x$4 = org.apache.spark.streaming.dstream.DStream..MODULE$.toPairDStreamFunctions$default$4(data);
      PairDStreamFunctions var10000 = org.apache.spark.streaming.dstream.DStream..MODULE$.toPairDStreamFunctions(data, evidence$1, x$3, (Ordering)null);
      StreamingKMeansModel var7 = this.model();
      return var10000.mapValues((point) -> BoxesRunTime.boxToInteger($anonfun$predictOnValues$1(var7, point)), scala.reflect.ClassTag..MODULE$.Int());
   }

   public JavaPairDStream predictOnValues(final JavaPairDStream data) {
      ClassTag tag = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return org.apache.spark.streaming.api.java.JavaPairDStream..MODULE$.fromPairDStream(this.predictOnValues(data.dstream(), tag), tag, scala.reflect.ClassTag..MODULE$.apply(Integer.class));
   }

   private void assertInitialized() {
      if (this.model().clusterCenters() == null) {
         throw new IllegalStateException("Initial cluster centers must be set before starting predictions");
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$trainOn$1(final StreamingKMeans $this, final RDD rdd, final Time time) {
      $this.model_$eq($this.model().update(rdd, $this.decayFactor(), $this.timeUnit()));
   }

   // $FF: synthetic method
   public static final int $anonfun$predictOn$1(final StreamingKMeansModel eta$0$1$1, final Vector point) {
      return eta$0$1$1.predict(point);
   }

   // $FF: synthetic method
   public static final int $anonfun$predictOnValues$1(final StreamingKMeansModel eta$0$1$2, final Vector point) {
      return eta$0$1$2.predict(point);
   }

   public StreamingKMeans(final int k, final double decayFactor, final String timeUnit) {
      this.k = k;
      this.decayFactor = decayFactor;
      this.timeUnit = timeUnit;
      super();
      Logging.$init$(this);
      this.model = new StreamingKMeansModel((Vector[])null, (double[])null);
   }

   public StreamingKMeans() {
      this(2, (double)1.0F, "batches");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
