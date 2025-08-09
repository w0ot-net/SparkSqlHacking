package org.apache.spark.mllib.regression;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.dstream.DStream;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d!\u0002\u0007\u000e\u0003\u0003A\u0002\"\u0002\u0014\u0001\t\u00039\u0003b\u0002\u001f\u0001\u0001\u00045\t\"\u0010\u0005\b\u0003\u0002\u0001\rQ\"\u0005C\u0011\u001dA\u0005A1A\u0007\u0012%CQA\u0013\u0001\u0005\u0002-CQ!\u0016\u0001\u0005\u0002YCQ!\u0016\u0001\u0005\u0002\u0015DQA\u001d\u0001\u0005\u0002MDaA\u001d\u0001\u0005\u0002\u0005\r\u0001bBA\r\u0001\u0011\u0005\u00111\u0004\u0005\b\u00033\u0001A\u0011AA(\u0005a\u0019FO]3b[&tw\rT5oK\u0006\u0014\u0018\t\\4pe&$\b.\u001c\u0006\u0003\u001d=\t!B]3he\u0016\u001c8/[8o\u0015\t\u0001\u0012#A\u0003nY2L'M\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h\u0007\u0001)2!\u0007\u00177'\r\u0001!\u0004\t\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\r\n\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0015\u0012#a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003!\u0002B!\u000b\u0001+k5\tQ\u0002\u0005\u0002,Y1\u0001A!B\u0017\u0001\u0005\u0004q#!A'\u0012\u0005=\u0012\u0004CA\u000e1\u0013\t\tDDA\u0004O_RD\u0017N\\4\u0011\u0005%\u001a\u0014B\u0001\u001b\u000e\u0005Y9UM\\3sC2L'0\u001a3MS:,\u0017M]'pI\u0016d\u0007CA\u00167\t\u00159\u0004A1\u00019\u0005\u0005\t\u0015CA\u0018:!\rI#HK\u0005\u0003w5\u0011!dR3oKJ\fG.\u001b>fI2Kg.Z1s\u00032<wN]5uQ6\fQ!\\8eK2,\u0012A\u0010\t\u00047}R\u0013B\u0001!\u001d\u0005\u0019y\u0005\u000f^5p]\u0006IQn\u001c3fY~#S-\u001d\u000b\u0003\u0007\u001a\u0003\"a\u0007#\n\u0005\u0015c\"\u0001B+oSRDqaR\u0002\u0002\u0002\u0003\u0007a(A\u0002yIE\n\u0011\"\u00197h_JLG\u000f[7\u0016\u0003U\n1\u0002\\1uKN$Xj\u001c3fYR\t!\u0006K\u0002\u0006\u001bN\u0003\"AT)\u000e\u0003=S!\u0001U\t\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002S\u001f\n)1+\u001b8dK\u0006\nA+A\u00032]Er\u0003'A\u0004ue\u0006Lgn\u00148\u0015\u0005\r;\u0006\"\u0002-\u0007\u0001\u0004I\u0016\u0001\u00023bi\u0006\u00042AW0b\u001b\u0005Y&B\u0001/^\u0003\u001d!7\u000f\u001e:fC6T!AX\t\u0002\u0013M$(/Z1nS:<\u0017B\u00011\\\u0005\u001d!5\u000b\u001e:fC6\u0004\"!\u000b2\n\u0005\rl!\u0001\u0004'bE\u0016dW\r\u001a)pS:$\bf\u0001\u0004N'R\u00111I\u001a\u0005\u00061\u001e\u0001\ra\u001a\t\u0004Q6\fW\"A5\u000b\u0005)\\\u0017\u0001\u00026bm\u0006T!\u0001\\/\u0002\u0007\u0005\u0004\u0018.\u0003\u0002oS\nY!*\u0019<b\tN#(/Z1nQ\r9Q\n]\u0011\u0002c\u0006)\u0011GL\u001a/a\u0005I\u0001O]3eS\u000e$xJ\u001c\u000b\u0003ib\u00042AW0v!\tYb/\u0003\u0002x9\t1Ai\\;cY\u0016DQ\u0001\u0017\u0005A\u0002e\u00042AW0{!\tYh0D\u0001}\u0015\tix\"\u0001\u0004mS:\fGnZ\u0005\u0003\u007fr\u0014aAV3di>\u0014\bf\u0001\u0005N'R!\u0011QAA\n!\u0011AW.a\u0002\u0011\t\u0005%\u0011\u0011C\u0007\u0003\u0003\u0017QA!!\u0004\u0002\u0010\u0005!A.\u00198h\u0015\u0005Q\u0017bA<\u0002\f!1\u0001,\u0003a\u0001\u0003+\u00012\u0001[7{Q\rIQ\n]\u0001\u0010aJ,G-[2u\u001f:4\u0016\r\\;fgV!\u0011QDA\u0016)\u0011\ty\"a\u0012\u0015\t\u0005\u0005\u0012q\u0007\t\u00055~\u000b\u0019\u0003\u0005\u0004\u001c\u0003K\tI#^\u0005\u0004\u0003Oa\"A\u0002+va2,'\u0007E\u0002,\u0003W!q!!\f\u000b\u0005\u0004\tyCA\u0001L#\ry\u0013\u0011\u0007\t\u00047\u0005M\u0012bAA\u001b9\t\u0019\u0011I\\=\t\u0013\u0005e\"\"!AA\u0004\u0005m\u0012AC3wS\u0012,gnY3%cA1\u0011QHA\"\u0003Si!!a\u0010\u000b\u0007\u0005\u0005C$A\u0004sK\u001adWm\u0019;\n\t\u0005\u0015\u0013q\b\u0002\t\u00072\f7o\u001d+bO\"1\u0001L\u0003a\u0001\u0003\u0013\u0002BAW0\u0002LA11$!\n\u0002*iD3AC'T+\u0011\t\t&a\u0017\u0015\t\u0005M\u0013Q\f\t\bQ\u0006U\u0013\u0011LA\u0004\u0013\r\t9&\u001b\u0002\u0010\u0015\u00064\u0018\rU1je\u0012\u001bFO]3b[B\u00191&a\u0017\u0005\u000f\u000552B1\u0001\u00020!1\u0001l\u0003a\u0001\u0003?\u0002b\u0001[A+\u00033R\bfA\u0006Na\"\u001a\u0001!T*"
)
public abstract class StreamingLinearAlgorithm implements Logging {
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   public abstract Option model();

   public abstract void model_$eq(final Option x$1);

   public abstract GeneralizedLinearAlgorithm algorithm();

   public GeneralizedLinearModel latestModel() {
      return (GeneralizedLinearModel)this.model().get();
   }

   public void trainOn(final DStream data) {
      if (this.model().isEmpty()) {
         throw new IllegalArgumentException("Model must be initialized before starting training.");
      } else {
         data.foreachRDD((rdd, time) -> {
            $anonfun$trainOn$1(this, rdd, time);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void trainOn(final JavaDStream data) {
      this.trainOn(data.dstream());
   }

   public DStream predictOn(final DStream data) {
      if (this.model().isEmpty()) {
         throw new IllegalArgumentException("Model must be initialized before starting prediction.");
      } else {
         return data.map((x) -> BoxesRunTime.boxToDouble($anonfun$predictOn$1(this, x)), .MODULE$.Double());
      }
   }

   public JavaDStream predictOn(final JavaDStream data) {
      return org.apache.spark.streaming.api.java.JavaDStream..MODULE$.fromDStream(this.predictOn(data.dstream()), .MODULE$.apply(Double.class));
   }

   public DStream predictOnValues(final DStream data, final ClassTag evidence$1) {
      if (this.model().isEmpty()) {
         throw new IllegalArgumentException("Model must be initialized before starting prediction");
      } else {
         ClassTag x$3 = .MODULE$.apply(Vector.class);
         Null x$4 = org.apache.spark.streaming.dstream.DStream..MODULE$.toPairDStreamFunctions$default$4(data);
         return org.apache.spark.streaming.dstream.DStream..MODULE$.toPairDStreamFunctions(data, evidence$1, x$3, (Ordering)null).mapValues((x) -> BoxesRunTime.boxToDouble($anonfun$predictOnValues$1(this, x)), .MODULE$.Double());
      }
   }

   public JavaPairDStream predictOnValues(final JavaPairDStream data) {
      ClassTag tag = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return org.apache.spark.streaming.api.java.JavaPairDStream..MODULE$.fromPairDStream(this.predictOnValues(data.dstream(), tag), tag, .MODULE$.apply(Double.class));
   }

   // $FF: synthetic method
   public static final void $anonfun$trainOn$1(final StreamingLinearAlgorithm $this, final RDD rdd, final Time time) {
      if (!rdd.isEmpty()) {
         $this.model_$eq(new Some($this.algorithm().run(rdd, ((GeneralizedLinearModel)$this.model().get()).weights())));
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Model updated at time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)})))));
         int var4 = ((GeneralizedLinearModel)$this.model().get()).weights().size();
         switch (var4) {
            default:
               String display = var4 > 100 ? scala.Predef..MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.doubleArrayOps(((GeneralizedLinearModel)$this.model().get()).weights().toArray()), 100)).mkString("[", ",", "...") : scala.Predef..MODULE$.wrapDoubleArray(((GeneralizedLinearModel)$this.model().get()).weights().toArray()).mkString("[", ",", "]");
               $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Current model: weights, ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MODEL_WEIGHTS..MODULE$, display)})))));
         }
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$predictOn$1(final StreamingLinearAlgorithm $this, final Vector x) {
      return ((GeneralizedLinearModel)$this.model().get()).predict(x);
   }

   // $FF: synthetic method
   public static final double $anonfun$predictOnValues$1(final StreamingLinearAlgorithm $this, final Vector x) {
      return ((GeneralizedLinearModel)$this.model().get()).predict(x);
   }

   public StreamingLinearAlgorithm() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
