package org.apache.spark.mllib.stat.test;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaDStream.;
import org.apache.spark.streaming.dstream.DStream;
import org.apache.spark.util.StatCounter;
import org.slf4j.Logger;
import scala.Function0;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001B\n\u0015\u0001\u0005BQA\u000f\u0001\u0005\u0002mBqa\u0012\u0001A\u0002\u0013%\u0001\nC\u0004M\u0001\u0001\u0007I\u0011B'\t\rM\u0003\u0001\u0015)\u0003J\u0011\u001d!\u0006\u00011A\u0005\n!Cq!\u0016\u0001A\u0002\u0013%a\u000b\u0003\u0004Y\u0001\u0001\u0006K!\u0013\u0005\b3\u0002\u0001\r\u0011\"\u0003[\u0011\u001dq\u0006\u00011A\u0005\n}Ca!\u0019\u0001!B\u0013Y\u0006\"\u00022\u0001\t\u0003\u0019\u0007\"B4\u0001\t\u0003A\u0007\"B6\u0001\t\u0003a\u0007\"\u0002=\u0001\t\u0003I\bB\u0002=\u0001\t\u0003\tI\u0002\u0003\u0005\u00022\u0001!\tAFA\u001a\u0011!\t9\u0004\u0001C\u0001-\u0005e\u0002\u0002CA,\u0001\u0011\u0005a#!\u0017\u0003\u001bM#(/Z1nS:<G+Z:u\u0015\t)b#\u0001\u0003uKN$(BA\f\u0019\u0003\u0011\u0019H/\u0019;\u000b\u0005eQ\u0012!B7mY&\u0014'BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0004\u0001M!\u0001A\t\u0015/!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fMB\u0011\u0011\u0006L\u0007\u0002U)\u00111FG\u0001\tS:$XM\u001d8bY&\u0011QF\u000b\u0002\b\u0019><w-\u001b8h!\tysG\u0004\u00021k9\u0011\u0011\u0007N\u0007\u0002e)\u00111\u0007I\u0001\u0007yI|w\u000e\u001e \n\u0003\u0015J!A\u000e\u0013\u0002\u000fA\f7m[1hK&\u0011\u0001(\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003m\u0011\na\u0001P5oSRtD#\u0001\u001f\u0011\u0005u\u0002Q\"\u0001\u000b)\u0007\u0005yT\t\u0005\u0002A\u00076\t\u0011I\u0003\u0002C5\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0011\u000b%!B*j]\u000e,\u0017%\u0001$\u0002\u000bErcG\f\u0019\u0002\u0017A,\u0017mY3QKJLw\u000eZ\u000b\u0002\u0013B\u00111ES\u0005\u0003\u0017\u0012\u00121!\u00138u\u0003=\u0001X-Y2f!\u0016\u0014\u0018n\u001c3`I\u0015\fHC\u0001(R!\t\u0019s*\u0003\u0002QI\t!QK\\5u\u0011\u001d\u00116!!AA\u0002%\u000b1\u0001\u001f\u00132\u00031\u0001X-Y2f!\u0016\u0014\u0018n\u001c3!\u0003)9\u0018N\u001c3poNK'0Z\u0001\u000fo&tGm\\<TSj,w\fJ3r)\tqu\u000bC\u0004S\r\u0005\u0005\t\u0019A%\u0002\u0017]Lg\u000eZ8x'&TX\rI\u0001\u000bi\u0016\u001cH/T3uQ>$W#A.\u0011\u0005ub\u0016BA/\u0015\u0005M\u0019FO]3b[&tw\rV3ti6+G\u000f[8e\u00039!Xm\u001d;NKRDw\u000eZ0%KF$\"A\u00141\t\u000fIK\u0011\u0011!a\u00017\u0006YA/Z:u\u001b\u0016$\bn\u001c3!\u00039\u0019X\r\u001e)fC\u000e,\u0007+\u001a:j_\u0012$\"\u0001Z3\u000e\u0003\u0001AQaR\u0006A\u0002%C3aC F\u00035\u0019X\r^,j]\u0012|woU5{KR\u0011A-\u001b\u0005\u0006)2\u0001\r!\u0013\u0015\u0004\u0019}*\u0015!D:fiR+7\u000f^'fi\"|G\r\u0006\u0002e[\")a.\u0004a\u0001_\u00061Q.\u001a;i_\u0012\u0004\"\u0001\u001d;\u000f\u0005E\u0014\bCA\u0019%\u0013\t\u0019H%\u0001\u0004Qe\u0016$WMZ\u0005\u0003kZ\u0014aa\u0015;sS:<'BA:%Q\riq(R\u0001\u000fe\u0016<\u0017n\u001d;feN#(/Z1n)\rQ\u00181\u0002\t\u0006w\u0006\u0005\u0011QA\u0007\u0002y*\u0011QP`\u0001\bIN$(/Z1n\u0015\ty($A\u0005tiJ,\u0017-\\5oO&\u0019\u00111\u0001?\u0003\u000f\u0011\u001bFO]3b[B\u0019Q(a\u0002\n\u0007\u0005%ACA\nTiJ,\u0017-\\5oOR+7\u000f\u001e*fgVdG\u000fC\u0004\u0002\u000e9\u0001\r!a\u0004\u0002\t\u0011\fG/\u0019\t\u0006w\u0006\u0005\u0011\u0011\u0003\t\u0004{\u0005M\u0011bAA\u000b)\ta!)\u001b8bef\u001c\u0016-\u001c9mK\"\u001aabP#\u0015\t\u0005m\u00111\u0006\t\u0007\u0003;\t9#!\u0002\u000e\u0005\u0005}!\u0002BA\u0011\u0003G\tAA[1wC*\u0019\u0011Q\u0005@\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002*\u0005}!a\u0003&bm\u0006$5\u000b\u001e:fC6Dq!!\u0004\u0010\u0001\u0004\ti\u0003\u0005\u0004\u0002\u001e\u0005\u001d\u0012\u0011\u0003\u0015\u0004\u001f}*\u0015a\u00043s_B\u0004V-Y2f!\u0016\u0014\u0018n\u001c3\u0015\t\u0005=\u0011Q\u0007\u0005\b\u0003\u001b\u0001\u0002\u0019AA\b\u0003]\u0019X/\\7be&TXMQ=LKf\fe\u000eZ,j]\u0012|w\u000f\u0006\u0003\u0002<\u0005U\u0003#B>\u0002\u0002\u0005u\u0002cB\u0012\u0002@\u0005\r\u0013\u0011J\u0005\u0004\u0003\u0003\"#A\u0002+va2,'\u0007E\u0002$\u0003\u000bJ1!a\u0012%\u0005\u001d\u0011un\u001c7fC:\u0004B!a\u0013\u0002R5\u0011\u0011Q\n\u0006\u0004\u0003\u001fR\u0012\u0001B;uS2LA!a\u0015\u0002N\tY1\u000b^1u\u0007>,h\u000e^3s\u0011\u001d\ti!\u0005a\u0001\u0003\u001f\tQ\u0002]1jeN+X.\\1sS\u0016\u001cH\u0003BA.\u0003?\u0002Ra_A\u0001\u0003;\u0002raIA \u0003\u0013\nI\u0005C\u0004\u0002bI\u0001\r!a\u000f\u0002\u001dM,X.\\1sSj,G\rR1uC\"\u001a\u0001aP#"
)
public class StreamingTest implements Logging, Serializable {
   private int peacePeriod;
   private int windowSize;
   private StreamingTestMethod testMethod;
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

   private int peacePeriod() {
      return this.peacePeriod;
   }

   private void peacePeriod_$eq(final int x$1) {
      this.peacePeriod = x$1;
   }

   private int windowSize() {
      return this.windowSize;
   }

   private void windowSize_$eq(final int x$1) {
      this.windowSize = x$1;
   }

   private StreamingTestMethod testMethod() {
      return this.testMethod;
   }

   private void testMethod_$eq(final StreamingTestMethod x$1) {
      this.testMethod = x$1;
   }

   public StreamingTest setPeacePeriod(final int peacePeriod) {
      this.peacePeriod_$eq(peacePeriod);
      return this;
   }

   public StreamingTest setWindowSize(final int windowSize) {
      this.windowSize_$eq(windowSize);
      return this;
   }

   public StreamingTest setTestMethod(final String method) {
      this.testMethod_$eq(StreamingTestMethod$.MODULE$.getTestMethodFromName(method));
      return this;
   }

   public DStream registerStream(final DStream data) {
      DStream dataAfterPeacePeriod = this.dropPeacePeriod(data);
      DStream summarizedData = this.summarizeByKeyAndWindow(dataAfterPeacePeriod);
      DStream pairedSummaries = this.pairSummaries(summarizedData);
      return this.testMethod().doTest(pairedSummaries);
   }

   public JavaDStream registerStream(final JavaDStream data) {
      return .MODULE$.fromDStream(this.registerStream(data.dstream()), scala.reflect.ClassTag..MODULE$.apply(StreamingTestResult.class));
   }

   public DStream dropPeacePeriod(final DStream data) {
      return data.transform((rdd, time) -> {
         if (time.milliseconds() > data.slideDuration().milliseconds() * (long)this.peacePeriod()) {
            return rdd;
         } else {
            SparkContext qual$1 = data.context().sparkContext();
            Seq x$1 = (Seq)scala.package..MODULE$.Seq().empty();
            int x$2 = qual$1.parallelize$default$2();
            return qual$1.parallelize(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(BinarySample.class));
         }
      }, scala.reflect.ClassTag..MODULE$.apply(BinarySample.class));
   }

   public DStream summarizeByKeyAndWindow(final DStream data) {
      DStream categoryValuePair = data.map((sample) -> new Tuple2.mcZD.sp(sample.isExperiment(), sample.value()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      if (this.windowSize() == 0) {
         return org.apache.spark.streaming.dstream.DStream..MODULE$.toPairDStreamFunctions(categoryValuePair, scala.reflect.ClassTag..MODULE$.Boolean(), scala.reflect.ClassTag..MODULE$.Double(), scala.math.Ordering.Boolean..MODULE$).updateStateByKey((newValues, oldSummary) -> {
            StatCounter newSummary = (StatCounter)oldSummary.getOrElse(() -> new StatCounter());
            newSummary.merge(newValues);
            return new Some(newSummary);
         }, scala.reflect.ClassTag..MODULE$.apply(StatCounter.class));
      } else {
         Duration windowDuration = data.slideDuration().$times(this.windowSize());
         return org.apache.spark.streaming.dstream.DStream..MODULE$.toPairDStreamFunctions(org.apache.spark.streaming.dstream.DStream..MODULE$.toPairDStreamFunctions(categoryValuePair, scala.reflect.ClassTag..MODULE$.Boolean(), scala.reflect.ClassTag..MODULE$.Double(), scala.math.Ordering.Boolean..MODULE$).groupByKeyAndWindow(windowDuration), scala.reflect.ClassTag..MODULE$.Boolean(), scala.reflect.ClassTag..MODULE$.apply(Iterable.class), scala.math.Ordering.Boolean..MODULE$).mapValues((values) -> {
            StatCounter summary = new StatCounter();
            values.foreach((value) -> $anonfun$summarizeByKeyAndWindow$5(summary, BoxesRunTime.unboxToDouble(value)));
            return summary;
         }, scala.reflect.ClassTag..MODULE$.apply(StatCounter.class));
      }
   }

   public DStream pairSummaries(final DStream summarizedData) {
      return org.apache.spark.streaming.dstream.DStream..MODULE$.toPairDStreamFunctions(summarizedData.map((x) -> new Tuple2(BoxesRunTime.boxToInteger(0), x._2()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(StatCounter.class), scala.math.Ordering.Int..MODULE$).groupByKey().map((x) -> new Tuple2(((IterableOps)x._2()).head(), ((IterableOps)x._2()).last()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   // $FF: synthetic method
   public static final StatCounter $anonfun$summarizeByKeyAndWindow$5(final StatCounter summary$1, final double value) {
      return summary$1.merge(value);
   }

   public StreamingTest() {
      Logging.$init$(this);
      this.peacePeriod = 0;
      this.windowSize = 0;
      this.testMethod = WelchTTest$.MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
