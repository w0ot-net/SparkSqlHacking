package org.apache.spark.util.logging;

import java.lang.invoke.SerializedLambda;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]a!B\f\u0019\u0001q\u0011\u0003\u0002C\u001a\u0001\u0005\u0003\u0007I\u0011A\u001b\t\u0011e\u0002!\u00111A\u0005\u0002iB\u0001\u0002\u0011\u0001\u0003\u0002\u0003\u0006KA\u000e\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005\u0005\"AQ\n\u0001B\u0001B\u0003%a\nC\u0003R\u0001\u0011\u0005!\u000bC\u0004X\u0001\u0001\u0007I\u0011B\u001b\t\u000fa\u0003\u0001\u0019!C\u00053\"11\f\u0001Q!\nYBq\u0001\u0019\u0001C\u0002\u0013%\u0011\r\u0003\u0004k\u0001\u0001\u0006IA\u0019\u0005\u0006W\u0002!\t\u0001\u001c\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006c\u0002!\tA\u001d\u0005\u0006k\u0002!IA\u001e\u0005\u0006o\u0002!\t\u0001_\u0004\u0007sbA\t\u0001\b>\u0007\r]A\u0002\u0012\u0001\u000f|\u0011\u0015\t&\u0003\"\u0001}\u0011\u001di(C1A\u0005\u0002UBaA \n!\u0002\u00131\u0004\u0002C@\u0013#\u0003%\t!!\u0001\u0003-QKW.\u001a\"bg\u0016$'k\u001c7mS:<\u0007k\u001c7jGfT!!\u0007\u000e\u0002\u000f1|wmZ5oO*\u00111\u0004H\u0001\u0005kRLGN\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h'\u0011\u00011%K\u0017\u0011\u0005\u0011:S\"A\u0013\u000b\u0003\u0019\nQa]2bY\u0006L!\u0001K\u0013\u0003\r\u0005s\u0017PU3g!\tQ3&D\u0001\u0019\u0013\ta\u0003DA\u0007S_2d\u0017N\\4Q_2L7-\u001f\t\u0003]Ej\u0011a\f\u0006\u0003aq\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003e=\u0012q\u0001T8hO&tw-\u0001\fs_2dwN^3s\u0013:$XM\u001d<bY6KG\u000e\\5t\u0007\u0001)\u0012A\u000e\t\u0003I]J!\u0001O\u0013\u0003\t1{gnZ\u0001\u001be>dGn\u001c<fe&sG/\u001a:wC2l\u0015\u000e\u001c7jg~#S-\u001d\u000b\u0003wy\u0002\"\u0001\n\u001f\n\u0005u*#\u0001B+oSRDqa\u0010\u0002\u0002\u0002\u0003\u0007a'A\u0002yIE\nqC]8mY>4XM]%oi\u0016\u0014h/\u00197NS2d\u0017n\u001d\u0011\u00021I|G\u000e\\5oO\u001aKG.Z*vM\u001aL\u0007\u0010U1ui\u0016\u0014h\u000e\u0005\u0002D\u0015:\u0011A\t\u0013\t\u0003\u000b\u0016j\u0011A\u0012\u0006\u0003\u000fR\na\u0001\u0010:p_Rt\u0014BA%&\u0003\u0019\u0001&/\u001a3fM&\u00111\n\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005%+\u0013aF2iK\u000e\\\u0017J\u001c;feZ\fGnQ8ogR\u0014\u0018-\u001b8u!\t!s*\u0003\u0002QK\t9!i\\8mK\u0006t\u0017A\u0002\u001fj]&$h\b\u0006\u0003T)V3\u0006C\u0001\u0016\u0001\u0011\u0015\u0019d\u00011\u00017\u0011\u0015\te\u00011\u0001C\u0011\u001die\u0001%AA\u00029\u000b\u0001C\\3yiJ{G\u000e\\8wKJ$\u0016.\\3\u0002)9,\u0007\u0010\u001e*pY2|g/\u001a:US6,w\fJ3r)\tY$\fC\u0004@\u0011\u0005\u0005\t\u0019\u0001\u001c\u0002#9,\u0007\u0010\u001e*pY2|g/\u001a:US6,\u0007\u0005\u000b\u0002\n;B\u0011AEX\u0005\u0003?\u0016\u0012\u0001B^8mCRLG.Z\u0001\nM>\u0014X.\u0019;uKJ,\u0012A\u0019\t\u0003G\"l\u0011\u0001\u001a\u0006\u0003K\u001a\fA\u0001^3yi*\tq-\u0001\u0003kCZ\f\u0017BA5e\u0005A\u0019\u0016.\u001c9mK\u0012\u000bG/\u001a$pe6\fG/\u0001\u0006g_Jl\u0017\r\u001e;fe\u0002\nab\u001d5pk2$'k\u001c7m_Z,'\u000f\u0006\u0002O[\")a\u000e\u0004a\u0001m\u0005\u0001\"-\u001f;fgR{')Z,sSR$XM\\\u0001\u000be>dG.\u001a3Pm\u0016\u0014H#A\u001e\u0002\u0019\tLH/Z:Xe&$H/\u001a8\u0015\u0005m\u001a\b\"\u0002;\u000f\u0001\u00041\u0014!\u00022zi\u0016\u001c\u0018!G2bY\u000e,H.\u0019;f\u001d\u0016DHOU8mY>4XM\u001d+j[\u0016$\u0012AN\u0001\u001dO\u0016tWM]1uKJ{G\u000e\\3e\u001fZ,'OR5mKN+hMZ5y)\u0005\u0011\u0015A\u0006+j[\u0016\u0014\u0015m]3e%>dG.\u001b8h!>d\u0017nY=\u0011\u0005)\u00122C\u0001\n$)\u0005Q\u0018\u0001G'J\u001d&kU+T0J\u001dR+%KV!M?N+5i\u0014(E'\u0006IR*\u0013(J\u001bVku,\u0013(U\u000bJ3\u0016\tT0T\u000b\u000e{e\nR*!\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u00111\u0001\u0016\u0004\u001d\u0006\u00151FAA\u0004!\u0011\tI!a\u0005\u000e\u0005\u0005-!\u0002BA\u0007\u0003\u001f\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005EQ%\u0001\u0006b]:|G/\u0019;j_:LA!!\u0006\u0002\f\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public class TimeBasedRollingPolicy implements RollingPolicy, Logging {
   private long rolloverIntervalMillis;
   private volatile long nextRolloverTime;
   private final SimpleDateFormat formatter;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean $lessinit$greater$default$3() {
      return TimeBasedRollingPolicy$.MODULE$.$lessinit$greater$default$3();
   }

   public static long MINIMUM_INTERVAL_SECONDS() {
      return TimeBasedRollingPolicy$.MODULE$.MINIMUM_INTERVAL_SECONDS();
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

   public long rolloverIntervalMillis() {
      return this.rolloverIntervalMillis;
   }

   public void rolloverIntervalMillis_$eq(final long x$1) {
      this.rolloverIntervalMillis = x$1;
   }

   private long nextRolloverTime() {
      return this.nextRolloverTime;
   }

   private void nextRolloverTime_$eq(final long x$1) {
      this.nextRolloverTime = x$1;
   }

   private SimpleDateFormat formatter() {
      return this.formatter;
   }

   public boolean shouldRollover(final long bytesToBeWritten) {
      return System.currentTimeMillis() > this.nextRolloverTime();
   }

   public void rolledOver() {
      this.nextRolloverTime_$eq(this.calculateNextRolloverTime());
      this.logDebug((Function0)(() -> {
         long var10000 = System.currentTimeMillis();
         return "Current time: " + var10000 + ", next rollover time: " + this.nextRolloverTime();
      }));
   }

   public void bytesWritten(final long bytes) {
   }

   private long calculateNextRolloverTime() {
      long now = System.currentTimeMillis();
      long targetTime = (long)(.MODULE$.ceil((double)now / (double)this.rolloverIntervalMillis()) * (double)this.rolloverIntervalMillis());
      this.logDebug((Function0)(() -> "Next rollover time is " + targetTime));
      return targetTime;
   }

   public String generateRolledOverFileSuffix() {
      return this.formatter().format(Calendar.getInstance().getTime());
   }

   public TimeBasedRollingPolicy(final long rolloverIntervalMillis, final String rollingFileSuffixPattern, final boolean checkIntervalConstraint) {
      this.rolloverIntervalMillis = rolloverIntervalMillis;
      super();
      Logging.$init$(this);
      if (checkIntervalConstraint && this.rolloverIntervalMillis() < TimeBasedRollingPolicy$.MODULE$.MINIMUM_INTERVAL_SECONDS() * 1000L) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rolling interval [", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, BoxesRunTime.boxToLong(this.rolloverIntervalMillis()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ms] is too small. Setting the interval to the acceptable minimum of "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_TIME..MODULE$, BoxesRunTime.boxToLong(TimeBasedRollingPolicy$.MODULE$.MINIMUM_INTERVAL_SECONDS() * 1000L))}))))));
         this.rolloverIntervalMillis_$eq(TimeBasedRollingPolicy$.MODULE$.MINIMUM_INTERVAL_SECONDS() * 1000L);
      }

      this.nextRolloverTime = this.calculateNextRolloverTime();
      this.formatter = new SimpleDateFormat(rollingFileSuffixPattern, Locale.US);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
