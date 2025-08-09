package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.mutable.Map;
import scala.collection.mutable.Queue;
import scala.collection.mutable.Queue.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ua!B\u000b\u0017\u0001aq\u0002\u0002C\u0016\u0001\u0005\u0003\u0005\u000b\u0011B\u0017\t\u0011E\u0002!Q1A\u0005\u0002IB\u0001\"\u000f\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\u0006u\u0001!\ta\u000f\u0005\b\u0001\u0002\u0011\r\u0011\"\u0003B\u0011\u0019)\u0005\u0001)A\u0005\u0005\"9a\t\u0001b\u0001\n\u00139\u0005B\u00020\u0001A\u0003%\u0001\nC\u0004`\u0001\t\u0007I\u0011\u00021\t\r\u0005\u0004\u0001\u0015!\u0003\\\u0011\u0015\u0011\u0007\u0001\"\u0003d\u0011\u0015I\u0007\u0001\"\u0001k\u0011\u0015Y\u0007\u0001\"\u0001m\u0011\u0015\u0011\b\u0001\"\u0001t\u0011\u0015!\b\u0001\"\u0001v\u000f\u00159h\u0003#\u0001y\r\u0015)b\u0003#\u0001z\u0011\u0015Q\u0014\u0003\"\u0001{\u0011\u0015Y\u0018\u0003\"\u0001}\u0011\u001dq\u0018#%A\u0005\u0002}\u0014a#\u0012=fGV$xN\u001d$bS2,(/\u001a+sC\u000e\\WM\u001d\u0006\u0003/a\ta\u0001Z3qY>L(BA\r\u001b\u0003\u0015\u0019\b/\u0019:l\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\u0014\u0007\u0001yR\u0005\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013E\u0001\u0004B]f\u0014VM\u001a\t\u0003M%j\u0011a\n\u0006\u0003Qa\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003U\u001d\u0012q\u0001T8hO&tw-A\u0005ta\u0006\u00148nQ8oM\u000e\u0001\u0001C\u0001\u00180\u001b\u0005A\u0012B\u0001\u0019\u0019\u0005%\u0019\u0006/\u0019:l\u0007>tg-A\u0003dY>\u001c7.F\u00014!\t!t'D\u00016\u0015\t1\u0004$\u0001\u0003vi&d\u0017B\u0001\u001d6\u0005\u0015\u0019En\\2l\u0003\u0019\u0019Gn\\2lA\u00051A(\u001b8jiz\"2\u0001\u0010 @!\ti\u0004!D\u0001\u0017\u0011\u0015YC\u00011\u0001.\u0011\u001d\tD\u0001%AA\u0002M\n\u0001%\u001a=fGV$xN\u001d$bS2,(/Z:WC2LG-\u001b;z\u0013:$XM\u001d<bYV\t!\t\u0005\u0002!\u0007&\u0011A)\t\u0002\u0005\u0019>tw-A\u0011fq\u0016\u001cW\u000f^8s\r\u0006LG.\u001e:fgZ\u000bG.\u001b3jifLe\u000e^3sm\u0006d\u0007%\u0001\u0011gC&dW\rZ#yK\u000e,Ho\u001c:t)&lWm\u0015;b[B\u001c\b+\u001a:I_N$X#\u0001%\u0011\t%s\u0005kW\u0007\u0002\u0015*\u00111\nT\u0001\b[V$\u0018M\u00197f\u0015\ti\u0015%\u0001\u0006d_2dWm\u0019;j_:L!a\u0014&\u0003\u00075\u000b\u0007\u000f\u0005\u0002R1:\u0011!K\u0016\t\u0003'\u0006j\u0011\u0001\u0016\u0006\u0003+2\na\u0001\u0010:p_Rt\u0014BA,\"\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011L\u0017\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005]\u000b\u0003cA%]\u0005&\u0011QL\u0013\u0002\u0006#V,W/Z\u0001\"M\u0006LG.\u001a3Fq\u0016\u001cW\u000f^8sgRKW.Z*uC6\u00048\u000fU3s\u0011>\u001cH\u000fI\u0001\u001aM\u0006LG.\u001a3Fq\u0016\u001cW\u000f^8sgRKW.Z*uC6\u00048/F\u0001\\\u0003i1\u0017-\u001b7fI\u0016CXmY;u_J\u001cH+[7f'R\fW\u000e]:!\u0003Y)\b\u000fZ1uK\u0006sGmQ8v]R4\u0015-\u001b7ve\u0016\u001cHC\u00013h!\t\u0001S-\u0003\u0002gC\t\u0019\u0011J\u001c;\t\u000b!\\\u0001\u0019A.\u0002;\u0019\f\u0017\u000e\\3e\u000bb,7-\u001e;peN<\u0016\u000e\u001e5US6,7\u000b^1naN\f!C\\;n\r\u0006LG.\u001a3Fq\u0016\u001cW\u000f^8sgV\tA-A\u000bsK\u001eL7\u000f^3s\r\u0006LG.\u001e:f\u001f:Dun\u001d;\u0015\u00055\u0004\bC\u0001\u0011o\u0013\ty\u0017E\u0001\u0003V]&$\b\"B9\u000e\u0001\u0004\u0001\u0016\u0001\u00035pgRt\u0017-\\3\u0002/I,w-[:uKJ,\u00050Z2vi>\u0014h)Y5mkJ,G#A7\u0002#9,XNR1jYV\u0014Xm](o\u0011>\u001cH\u000f\u0006\u0002em\")\u0011o\u0004a\u0001!\u00061R\t_3dkR|'OR1jYV\u0014X\r\u0016:bG.,'\u000f\u0005\u0002>#M\u0011\u0011c\b\u000b\u0002q\u00061R.\u0019=Ok6,\u00050Z2vi>\u0014h)Y5mkJ,7\u000f\u0006\u0002e{\")1f\u0005a\u0001[\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII*\"!!\u0001+\u0007M\n\u0019a\u000b\u0002\u0002\u0006A!\u0011qAA\t\u001b\t\tIA\u0003\u0003\u0002\f\u00055\u0011!C;oG\",7m[3e\u0015\r\ty!I\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\n\u0003\u0013\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0001"
)
public class ExecutorFailureTracker implements Logging {
   private final Clock clock;
   private final long executorFailuresValidityInterval;
   private final Map failedExecutorsTimeStampsPerHost;
   private final Queue failedExecutorsTimeStamps;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Clock $lessinit$greater$default$2() {
      return ExecutorFailureTracker$.MODULE$.$lessinit$greater$default$2();
   }

   public static int maxNumExecutorFailures(final SparkConf sparkConf) {
      return ExecutorFailureTracker$.MODULE$.maxNumExecutorFailures(sparkConf);
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public Clock clock() {
      return this.clock;
   }

   private long executorFailuresValidityInterval() {
      return this.executorFailuresValidityInterval;
   }

   private Map failedExecutorsTimeStampsPerHost() {
      return this.failedExecutorsTimeStampsPerHost;
   }

   private Queue failedExecutorsTimeStamps() {
      return this.failedExecutorsTimeStamps;
   }

   private int updateAndCountFailures(final Queue failedExecutorsWithTimeStamps) {
      long endTime = this.clock().getTimeMillis();

      while(this.executorFailuresValidityInterval() > 0L && failedExecutorsWithTimeStamps.nonEmpty() && BoxesRunTime.unboxToLong(failedExecutorsWithTimeStamps.head()) < endTime - this.executorFailuresValidityInterval()) {
         failedExecutorsWithTimeStamps.dequeue();
      }

      return failedExecutorsWithTimeStamps.size();
   }

   public synchronized int numFailedExecutors() {
      return this.updateAndCountFailures(this.failedExecutorsTimeStamps());
   }

   public void registerFailureOnHost(final String hostname) {
      synchronized(this){}

      try {
         long timeMillis = this.clock().getTimeMillis();
         this.failedExecutorsTimeStamps().enqueue(BoxesRunTime.boxToLong(timeMillis));
         Queue failedExecutorsOnHost = (Queue)this.failedExecutorsTimeStampsPerHost().getOrElse(hostname, () -> {
            Queue failureOnHost = (Queue).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            this.failedExecutorsTimeStampsPerHost().put(hostname, failureOnHost);
            return failureOnHost;
         });
         failedExecutorsOnHost.enqueue(BoxesRunTime.boxToLong(timeMillis));
      } catch (Throwable var7) {
         throw var7;
      }

   }

   public void registerExecutorFailure() {
      synchronized(this){}

      try {
         long timeMillis = this.clock().getTimeMillis();
         this.failedExecutorsTimeStamps().enqueue(BoxesRunTime.boxToLong(timeMillis));
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public int numFailuresOnHost(final String hostname) {
      return BoxesRunTime.unboxToInt(this.failedExecutorsTimeStampsPerHost().get(hostname).map((failedExecutorsOnHost) -> BoxesRunTime.boxToInteger($anonfun$numFailuresOnHost$1(this, failedExecutorsOnHost))).getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   // $FF: synthetic method
   public static final int $anonfun$numFailuresOnHost$1(final ExecutorFailureTracker $this, final Queue failedExecutorsOnHost) {
      return $this.updateAndCountFailures(failedExecutorsOnHost);
   }

   public ExecutorFailureTracker(final SparkConf sparkConf, final Clock clock) {
      this.clock = clock;
      Logging.$init$(this);
      this.executorFailuresValidityInterval = BoxesRunTime.unboxToLong(((Option)sparkConf.get((ConfigEntry)package$.MODULE$.EXECUTOR_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS())).getOrElse((JFunction0.mcJ.sp)() -> -1L));
      this.failedExecutorsTimeStampsPerHost = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.failedExecutorsTimeStamps = new Queue(.MODULE$.$lessinit$greater$default$1());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
