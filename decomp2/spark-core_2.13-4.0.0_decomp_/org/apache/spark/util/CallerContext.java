package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyInt;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]qA\u0002\u000f\u001e\u0011\u0003iRE\u0002\u0004(;!\u0005Q\u0004\u000b\u0005\u0006k\u0005!\ta\u000e\u0005\bq\u0005\u0011\r\u0011\"\u0001:\u0011\u0019i\u0014\u0001)A\u0005u!9a(AI\u0001\n\u0003y\u0004b\u0002-\u0002#\u0003%\ta\u0010\u0005\b3\u0006\t\n\u0011\"\u0001@\u0011\u001dQ\u0016!%A\u0005\u0002mCq!Y\u0001\u0012\u0002\u0013\u00051\fC\u0004c\u0003E\u0005I\u0011A.\t\u000f\r\f\u0011\u0013!C\u0001I\"9!.AI\u0001\n\u0003Yf!B\u0014\u001e\u0001}Y\u0007\u0002\u00037\u000e\u0005\u0003\u0005\u000b\u0011\u0002#\t\u00115l!\u0011!Q\u0001\n\u0005C\u0001B\\\u0007\u0003\u0002\u0003\u0006I!\u0011\u0005\t_6\u0011\t\u0011)A\u0005\u0003\"A\u0001/\u0004B\u0001B\u0003%Q\f\u0003\u0005r\u001b\t\u0005\t\u0015!\u0003^\u0011!\u0011XB!A!\u0002\u0013i\u0006\u0002C:\u000e\u0005\u0003\u0005\u000b\u0011\u00024\t\u0011Ql!\u0011!Q\u0001\nuCQ!N\u0007\u0005\u0002UD\u0011\"!\u0001\u000e\u0005\u0004%I!a\u0001\t\u000f\u0005\u0015Q\u0002)A\u0005\t\"9\u0011qA\u0007\u0005\n\u0005%\u0001bBA\u0007\u001b\u0011\u0005\u0011qB\u0001\u000e\u0007\u0006dG.\u001a:D_:$X\r\u001f;\u000b\u0005yy\u0012\u0001B;uS2T!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'o\u001a\t\u0003M\u0005i\u0011!\b\u0002\u000e\u0007\u0006dG.\u001a:D_:$X\r\u001f;\u0014\u0007\u0005Is\u0006\u0005\u0002+[5\t1FC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3F\u0001\u0004B]f\u0014VM\u001a\t\u0003aMj\u0011!\r\u0006\u0003e}\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003iE\u0012q\u0001T8hO&tw-\u0001\u0004=S:LGOP\u0002\u0001)\u0005)\u0013\u0001F2bY2,'oQ8oi\u0016DH/\u00128bE2,G-F\u0001;!\tQ3(\u0003\u0002=W\t9!i\\8mK\u0006t\u0017!F2bY2,'oQ8oi\u0016DH/\u00128bE2,G\rI\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003\u0001S#!Q(\u0011\u0007)\u0012E)\u0003\u0002DW\t1q\n\u001d;j_:\u0004\"!\u0012'\u000f\u0005\u0019S\u0005CA$,\u001b\u0005A%BA%7\u0003\u0019a$o\\8u}%\u00111jK\u0001\u0007!J,G-\u001a4\n\u00055s%AB*ue&twM\u0003\u0002LW-\n\u0001\u000b\u0005\u0002R-6\t!K\u0003\u0002T)\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003+.\n!\"\u00198o_R\fG/[8o\u0013\t9&KA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001a\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u000b\u00029*\u0012Ql\u0014\t\u0004U\ts\u0006C\u0001\u0016`\u0013\t\u00017FA\u0002J]R\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u00122\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$s'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005O\u000b\u0002K*\u0012am\u0014\t\u0004U\t;\u0007C\u0001\u0016i\u0013\tI7F\u0001\u0003M_:<\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013hE\u0002\u000eS=\nAA\u001a:p[\u0006)R\u000f]:ue\u0016\fWnQ1mY\u0016\u00148i\u001c8uKb$\u0018!B1qa&#\u0017\u0001D1qa\u0006#H/Z7qi&#\u0017!\u00026pE&#\u0017aB:uC\u001e,\u0017\nZ\u0001\u000fgR\fw-Z!ui\u0016l\u0007\u000f^%e\u0003\u0019!\u0018m]6JI\u0006\tB/Y:l\u0003R$X-\u001c9u\u001dVl'-\u001a:\u0015\u0015Y<\b0\u001f>|yvtx\u0010\u0005\u0002'\u001b!)An\u0006a\u0001\t\"9Qn\u0006I\u0001\u0002\u0004\t\u0005b\u00028\u0018!\u0003\u0005\r!\u0011\u0005\b_^\u0001\n\u00111\u0001B\u0011\u001d\u0001x\u0003%AA\u0002uCq!]\f\u0011\u0002\u0003\u0007Q\fC\u0004s/A\u0005\t\u0019A/\t\u000fM<\u0002\u0013!a\u0001M\"9Ao\u0006I\u0001\u0002\u0004i\u0016aB2p]R,\u0007\u0010^\u000b\u0002\t\u0006A1m\u001c8uKb$\b%\u0001\bqe\u0016\u0004\u0018M]3D_:$X\r\u001f;\u0015\u0007\u0011\u000bY\u0001\u0003\u0004\u0002\u0002i\u0001\r\u0001R\u0001\u0012g\u0016$8)\u001e:sK:$8i\u001c8uKb$HCAA\t!\rQ\u00131C\u0005\u0004\u0003+Y#\u0001B+oSR\u0004"
)
public class CallerContext implements Logging {
   private final String context;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Option $lessinit$greater$default$9() {
      return CallerContext$.MODULE$.$lessinit$greater$default$9();
   }

   public static Option $lessinit$greater$default$8() {
      return CallerContext$.MODULE$.$lessinit$greater$default$8();
   }

   public static Option $lessinit$greater$default$7() {
      return CallerContext$.MODULE$.$lessinit$greater$default$7();
   }

   public static Option $lessinit$greater$default$6() {
      return CallerContext$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option $lessinit$greater$default$5() {
      return CallerContext$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return CallerContext$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return CallerContext$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return CallerContext$.MODULE$.$lessinit$greater$default$2();
   }

   public static boolean callerContextEnabled() {
      return CallerContext$.MODULE$.callerContextEnabled();
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

   private String context() {
      return this.context;
   }

   private String prepareContext(final String context) {
      LazyInt len$lzy = new LazyInt();
      if (context != null && context.length() > len$1(len$lzy)) {
         String finalContext = context.substring(0, len$1(len$lzy));
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Truncated Spark caller context from ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTEXT..MODULE$, context)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FINAL_CONTEXT..MODULE$, finalContext)}))))));
         return finalContext;
      } else {
         return context;
      }
   }

   public void setCurrentContext() {
      if (CallerContext$.MODULE$.callerContextEnabled()) {
         org.apache.hadoop.ipc.CallerContext hdfsContext = (new org.apache.hadoop.ipc.CallerContext.Builder(this.context())).build();
         org.apache.hadoop.ipc.CallerContext.setCurrent(hdfsContext);
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$context$5(final int x$32) {
      return "_JId_" + x$32;
   }

   // $FF: synthetic method
   public static final String $anonfun$context$7(final int x$33) {
      return "_SId_" + x$33;
   }

   // $FF: synthetic method
   public static final String $anonfun$context$9(final int x$34) {
      return "_" + x$34;
   }

   // $FF: synthetic method
   public static final String $anonfun$context$11(final long x$35) {
      return "_TId_" + x$35;
   }

   // $FF: synthetic method
   public static final String $anonfun$context$13(final int x$36) {
      return "_" + x$36;
   }

   // $FF: synthetic method
   private static final int len$lzycompute$1(final LazyInt len$lzy$1) {
      synchronized(len$lzy$1){}

      int var2;
      try {
         var2 = len$lzy$1.initialized() ? len$lzy$1.value() : len$lzy$1.initialize(SparkHadoopUtil$.MODULE$.get().conf().getInt("hadoop.caller.context.max.size", 128));
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private static final int len$1(final LazyInt len$lzy$1) {
      return len$lzy$1.initialized() ? len$lzy$1.value() : len$lzycompute$1(len$lzy$1);
   }

   public CallerContext(final String from, final Option upstreamCallerContext, final Option appId, final Option appAttemptId, final Option jobId, final Option stageId, final Option stageAttemptId, final Option taskId, final Option taskAttemptNumber) {
      Logging.$init$(this);
      this.context = this.prepareContext("SPARK_" + from + appId.map((x$30) -> "_" + x$30).getOrElse(() -> "") + appAttemptId.map((x$31) -> "_" + x$31).getOrElse(() -> "") + jobId.map((x$32) -> $anonfun$context$5(BoxesRunTime.unboxToInt(x$32))).getOrElse(() -> "") + stageId.map((x$33) -> $anonfun$context$7(BoxesRunTime.unboxToInt(x$33))).getOrElse(() -> "") + stageAttemptId.map((x$34) -> $anonfun$context$9(BoxesRunTime.unboxToInt(x$34))).getOrElse(() -> "") + taskId.map((x$35) -> $anonfun$context$11(BoxesRunTime.unboxToLong(x$35))).getOrElse(() -> "") + taskAttemptNumber.map((x$36) -> $anonfun$context$13(BoxesRunTime.unboxToInt(x$36))).getOrElse(() -> "") + upstreamCallerContext.map((x$37) -> "_" + x$37).getOrElse(() -> ""));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
