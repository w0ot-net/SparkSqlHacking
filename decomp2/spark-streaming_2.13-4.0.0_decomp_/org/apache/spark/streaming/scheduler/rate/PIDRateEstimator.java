package org.apache.spark.streaming.scheduler.rate;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.collection.StringOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichDouble.;

@ScalaSignature(
   bytes = "\u0006\u0005E4Q\u0001F\u000b\u00013\u0005B\u0001B\r\u0001\u0003\u0002\u0003\u0006I\u0001\u000e\u0005\to\u0001\u0011\t\u0011)A\u0005q!A1\b\u0001B\u0001B\u0003%\u0001\b\u0003\u0005=\u0001\t\u0005\t\u0015!\u00039\u0011!i\u0004A!A!\u0002\u0013A\u0004\"\u0002 \u0001\t\u0003y\u0004b\u0002$\u0001\u0001\u0004%Ia\u0012\u0005\b\u0017\u0002\u0001\r\u0011\"\u0003M\u0011\u0019\u0011\u0006\u0001)Q\u0005\u0011\"91\u000b\u0001a\u0001\n\u0013!\u0006bB+\u0001\u0001\u0004%IA\u0016\u0005\u00071\u0002\u0001\u000b\u0015\u0002\u001b\t\u000fe\u0003\u0001\u0019!C\u00055\"91\f\u0001a\u0001\n\u0013a\u0006B\u00020\u0001A\u0003&\u0001\bC\u0004`\u0001\u0001\u0007I\u0011\u0002.\t\u000f\u0001\u0004\u0001\u0019!C\u0005C\"11\r\u0001Q!\naBQ\u0001\u001a\u0001\u0005\u0002\u0015\u0014\u0001\u0003U%E%\u0006$X-R:uS6\fGo\u001c:\u000b\u0005Y9\u0012\u0001\u0002:bi\u0016T!\u0001G\r\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\u000e\u001c\u0003%\u0019HO]3b[&twM\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h'\u0011\u0001!\u0005\u000b\u0017\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g!\tI#&D\u0001\u0016\u0013\tYSCA\u0007SCR,Wi\u001d;j[\u0006$xN\u001d\t\u0003[Aj\u0011A\f\u0006\u0003_m\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003c9\u0012q\u0001T8hO&tw-A\ncCR\u001c\u0007.\u00138uKJ4\u0018\r\\'jY2L7o\u0001\u0001\u0011\u0005\r*\u0014B\u0001\u001c%\u0005\u0011auN\\4\u0002\u0019A\u0014x\u000e]8si&|g.\u00197\u0011\u0005\rJ\u0014B\u0001\u001e%\u0005\u0019!u.\u001e2mK\u0006A\u0011N\u001c;fOJ\fG.\u0001\u0006eKJLg/\u0019;jm\u0016\fq!\\5o%\u0006$X-\u0001\u0004=S:LGO\u0010\u000b\u0007\u0001\u0006\u00135\tR#\u0011\u0005%\u0002\u0001\"\u0002\u001a\u0007\u0001\u0004!\u0004\"B\u001c\u0007\u0001\u0004A\u0004\"B\u001e\u0007\u0001\u0004A\u0004\"\u0002\u001f\u0007\u0001\u0004A\u0004\"B\u001f\u0007\u0001\u0004A\u0014\u0001\u00034jeN$(+\u001e8\u0016\u0003!\u0003\"aI%\n\u0005)##a\u0002\"p_2,\u0017M\\\u0001\rM&\u00148\u000f\u001e*v]~#S-\u001d\u000b\u0003\u001bB\u0003\"a\t(\n\u0005=##\u0001B+oSRDq!\u0015\u0005\u0002\u0002\u0003\u0007\u0001*A\u0002yIE\n\u0011BZ5sgR\u0014VO\u001c\u0011\u0002\u00151\fG/Z:u)&lW-F\u00015\u00039a\u0017\r^3tiRKW.Z0%KF$\"!T,\t\u000fE[\u0011\u0011!a\u0001i\u0005YA.\u0019;fgR$\u0016.\\3!\u0003)a\u0017\r^3tiJ\u000bG/Z\u000b\u0002q\u0005qA.\u0019;fgR\u0014\u0016\r^3`I\u0015\fHCA'^\u0011\u001d\tf\"!AA\u0002a\n1\u0002\\1uKN$(+\u0019;fA\u0005YA.\u0019;fgR,%O]8s\u0003=a\u0017\r^3ti\u0016\u0013(o\u001c:`I\u0015\fHCA'c\u0011\u001d\t\u0016#!AA\u0002a\nA\u0002\\1uKN$XI\u001d:pe\u0002\nqaY8naV$X\rF\u0003gS.lw\u000eE\u0002$ObJ!\u0001\u001b\u0013\u0003\r=\u0003H/[8o\u0011\u0015Q7\u00031\u00015\u0003\u0011!\u0018.\\3\t\u000b1\u001c\u0002\u0019\u0001\u001b\u0002\u00179,X.\u00127f[\u0016tGo\u001d\u0005\u0006]N\u0001\r\u0001N\u0001\u0010aJ|7-Z:tS:<G)\u001a7bs\")\u0001o\u0005a\u0001i\u0005y1o\u00195fIVd\u0017N\\4EK2\f\u0017\u0010"
)
public class PIDRateEstimator implements RateEstimator, Logging {
   private final long batchIntervalMillis;
   private final double proportional;
   private final double integral;
   private final double derivative;
   private final double minRate;
   private boolean firstRun;
   private long latestTime;
   private double latestRate;
   private double latestError;
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

   private boolean firstRun() {
      return this.firstRun;
   }

   private void firstRun_$eq(final boolean x$1) {
      this.firstRun = x$1;
   }

   private long latestTime() {
      return this.latestTime;
   }

   private void latestTime_$eq(final long x$1) {
      this.latestTime = x$1;
   }

   private double latestRate() {
      return this.latestRate;
   }

   private void latestRate_$eq(final double x$1) {
      this.latestRate = x$1;
   }

   private double latestError() {
      return this.latestError;
   }

   private void latestError_$eq(final double x$1) {
      this.latestError = x$1;
   }

   public Option compute(final long time, final long numElements, final long processingDelay, final long schedulingDelay) {
      this.logTrace((Function0)(() -> "\ntime = " + time + ", # records = " + numElements + ", processing time = " + processingDelay + ", scheduling delay = " + schedulingDelay));
      synchronized(this){}

      Object var10;
      try {
         Object var10000;
         if (time > this.latestTime() && numElements > 0L && processingDelay > 0L) {
            double delaySinceUpdate = (double)(time - this.latestTime()) / (double)1000;
            double processingRate = (double)numElements / (double)processingDelay * (double)1000;
            double error = this.latestRate() - processingRate;
            double historicalError = (double)schedulingDelay * processingRate / (double)this.batchIntervalMillis;
            double dError = (error - this.latestError()) / delaySinceUpdate;
            double newRate = .MODULE$.max$extension(scala.Predef..MODULE$.doubleWrapper(this.latestRate() - this.proportional * error - this.integral * historicalError - this.derivative * dError), this.minRate);
            this.logTrace((Function0)(() -> {
               StringOps var10000 = scala.collection.StringOps..MODULE$;
               Predef var10001 = scala.Predef..MODULE$;
               double var10002 = this.latestRate();
               return var10000.stripMargin$extension(var10001.augmentString("\n            | latestRate = " + var10002 + ", error = " + error + "\n            | latestError = " + this.latestError() + ", historicalError = " + historicalError + "\n            | delaySinceUpdate = " + delaySinceUpdate + ", dError = " + dError + "\n            "));
            }));
            this.latestTime_$eq(time);
            if (this.firstRun()) {
               this.latestRate_$eq(processingRate);
               this.latestError_$eq((double)0.0F);
               this.firstRun_$eq(false);
               this.logTrace((Function0)(() -> "First run, rate estimation skipped"));
               var10000 = scala.None..MODULE$;
            } else {
               this.latestRate_$eq(newRate);
               this.latestError_$eq(error);
               this.logTrace((Function0)(() -> "New rate = " + newRate));
               var10000 = new Some(BoxesRunTime.boxToDouble(newRate));
            }
         } else {
            this.logTrace((Function0)(() -> "Rate estimation skipped"));
            var10000 = scala.None..MODULE$;
         }

         var10 = var10000;
      } catch (Throwable var24) {
         throw var24;
      }

      return (Option)var10;
   }

   public PIDRateEstimator(final long batchIntervalMillis, final double proportional, final double integral, final double derivative, final double minRate) {
      this.batchIntervalMillis = batchIntervalMillis;
      this.proportional = proportional;
      this.integral = integral;
      this.derivative = derivative;
      this.minRate = minRate;
      Logging.$init$(this);
      this.firstRun = true;
      this.latestTime = -1L;
      this.latestRate = (double)-1.0F;
      this.latestError = (double)-1.0F;
      scala.Predef..MODULE$.require(batchIntervalMillis > 0L, () -> "Specified batch interval " + this.batchIntervalMillis + " in PIDRateEstimator is invalid.");
      scala.Predef..MODULE$.require(proportional >= (double)0, () -> "Proportional term " + this.proportional + " in PIDRateEstimator should be >= 0.");
      scala.Predef..MODULE$.require(integral >= (double)0, () -> "Integral term " + this.integral + " in PIDRateEstimator should be >= 0.");
      scala.Predef..MODULE$.require(derivative >= (double)0, () -> "Derivative term " + this.derivative + " in PIDRateEstimator should be >= 0.");
      scala.Predef..MODULE$.require(minRate > (double)0, () -> "Minimum rate in PIDRateEstimator should be > 0");
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Created PIDRateEstimator with proportional = "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", integral = "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PROPORTIONAL..MODULE$, BoxesRunTime.boxToDouble(this.proportional))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", derivative = "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INTEGRAL..MODULE$, BoxesRunTime.boxToDouble(this.integral))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", min rate = "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DERIVATIVE..MODULE$, BoxesRunTime.boxToDouble(this.derivative))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_RATE..MODULE$, BoxesRunTime.boxToDouble(this.minRate))}))))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
