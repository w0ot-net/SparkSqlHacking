package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.executor.ExecutorExitCode$;
import org.apache.spark.executor.KilledByTaskReaperException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogKeys.THREAD.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Predef;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u000554Q\u0001D\u0007\u0001\u001fUA\u0001b\u000b\u0001\u0003\u0006\u0004%\t!\f\u0005\ti\u0001\u0011\t\u0011)A\u0005]!)Q\u0007\u0001C\u0001m!9!\b\u0001b\u0001\n\u0013Y\u0004BB \u0001A\u0003%A\bC\u0003A\u0001\u0011\u0005\u0013\tC\u0003A\u0001\u0011\u0005\u0001l\u0002\u0005[\u001b\u0005\u0005\t\u0012A\b\\\r!aQ\"!A\t\u0002=a\u0006\"B\u001b\n\t\u0003\u0001\u0007bB1\n#\u0003%\tA\u0019\u0002\u001e'B\f'o[+oG\u0006,x\r\u001b;Fq\u000e,\u0007\u000f^5p]\"\u000bg\u000e\u001a7fe*\u0011abD\u0001\u0005kRLGN\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h'\u0011\u0001aCH\u0013\u0011\u0005]aR\"\u0001\r\u000b\u0005eQ\u0012\u0001\u00027b]\u001eT\u0011aG\u0001\u0005U\u00064\u0018-\u0003\u0002\u001e1\t1qJ\u00196fGR\u0004\"a\b\u0012\u000f\u0005]\u0001\u0013BA\u0011\u0019\u0003\u0019!\u0006N]3bI&\u00111\u0005\n\u0002\u0019+:\u001c\u0017-^4ii\u0016C8-\u001a9uS>t\u0007*\u00198eY\u0016\u0014(BA\u0011\u0019!\t1\u0013&D\u0001(\u0015\tAs\"\u0001\u0005j]R,'O\\1m\u0013\tQsEA\u0004M_\u001e<\u0017N\\4\u0002/\u0015D\u0018\u000e^(o+:\u001c\u0017-^4ii\u0016C8-\u001a9uS>t7\u0001A\u000b\u0002]A\u0011qFM\u0007\u0002a)\t\u0011'A\u0003tG\u0006d\u0017-\u0003\u00024a\t9!i\\8mK\u0006t\u0017\u0001G3ySR|e.\u00168dCV<\u0007\u000e^#yG\u0016\u0004H/[8oA\u00051A(\u001b8jiz\"\"aN\u001d\u0011\u0005a\u0002Q\"A\u0007\t\u000f-\u001a\u0001\u0013!a\u0001]\u0005)2.\u001b7m\u001f:4\u0015\r^1m\u000bJ\u0014xN\u001d#faRDW#\u0001\u001f\u0011\u0005=j\u0014B\u0001 1\u0005\rIe\u000e^\u0001\u0017W&dGn\u00148GCR\fG.\u0012:s_J$U\r\u001d;iA\u0005\tRO\\2bk\u001eDG/\u0012=dKB$\u0018n\u001c8\u0015\u0007\t+%\n\u0005\u00020\u0007&\u0011A\t\r\u0002\u0005+:LG\u000fC\u0003G\r\u0001\u0007q)\u0001\u0004uQJ,\u0017\r\u001a\t\u0003/!K!!\u0013\r\u0003\rQC'/Z1e\u0011\u0015Ye\u00011\u0001M\u0003%)\u0007pY3qi&|g\u000e\u0005\u0002N+:\u0011aj\u0015\b\u0003\u001fJk\u0011\u0001\u0015\u0006\u0003#2\na\u0001\u0010:p_Rt\u0014\"A\u0019\n\u0005Q\u0003\u0014a\u00029bG.\fw-Z\u0005\u0003-^\u0013\u0011\u0002\u00165s_^\f'\r\\3\u000b\u0005Q\u0003DC\u0001\"Z\u0011\u0015Yu\u00011\u0001M\u0003u\u0019\u0006/\u0019:l+:\u001c\u0017-^4ii\u0016C8-\u001a9uS>t\u0007*\u00198eY\u0016\u0014\bC\u0001\u001d\n'\tIQ\f\u0005\u00020=&\u0011q\f\r\u0002\u0007\u0003:L(+\u001a4\u0015\u0003m\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\nT#A2+\u00059\"7&A3\u0011\u0005\u0019\\W\"A4\u000b\u0005!L\u0017!C;oG\",7m[3e\u0015\tQ\u0007'\u0001\u0006b]:|G/\u0019;j_:L!\u0001\\4\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r"
)
public class SparkUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler, Logging {
   private final boolean exitOnUncaughtException;
   private final int killOnFatalErrorDepth;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean $lessinit$greater$default$1() {
      return SparkUncaughtExceptionHandler$.MODULE$.$lessinit$greater$default$1();
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

   public boolean exitOnUncaughtException() {
      return this.exitOnUncaughtException;
   }

   private int killOnFatalErrorDepth() {
      return this.killOnFatalErrorDepth;
   }

   public void uncaughtException(final Thread thread, final Throwable exception) {
      try {
         MDC mdc = new MDC(.MODULE$, thread);
         if (ShutdownHookManager$.MODULE$.inShutdown()) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"[Container in shutdown] Uncaught exception in thread ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{mdc})))), exception);
         } else {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Uncaught exception in thread ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{mdc})))), exception);
         }

         if (!ShutdownHookManager$.MODULE$.inShutdown()) {
            Throwable currentException = exception;

            for(int depth = 0; currentException != null && depth < this.killOnFatalErrorDepth(); ++depth) {
               if (currentException instanceof OutOfMemoryError) {
                  System.exit(SparkExitCode$.MODULE$.OOM());
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  label78: {
                     if (currentException instanceof SparkFatalException) {
                        SparkFatalException var8 = (SparkFatalException)currentException;
                        if (var8.throwable() instanceof OutOfMemoryError) {
                           System.exit(SparkExitCode$.MODULE$.OOM());
                           BoxedUnit var17 = BoxedUnit.UNIT;
                           break label78;
                        }
                     }

                     if (currentException instanceof KilledByTaskReaperException && this.exitOnUncaughtException()) {
                        System.exit(ExecutorExitCode$.MODULE$.KILLED_BY_TASK_REAPER());
                        BoxedUnit var16 = BoxedUnit.UNIT;
                     } else {
                        BoxedUnit var15 = BoxedUnit.UNIT;
                     }
                  }
               }

               currentException = currentException.getCause();
            }

            if (this.exitOnUncaughtException()) {
               System.exit(SparkExitCode$.MODULE$.UNCAUGHT_EXCEPTION());
            }
         }
      } catch (OutOfMemoryError var13) {
         OutOfMemoryError oom = var13;

         try {
            this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Uncaught OutOfMemoryError in thread ", ", process halted."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(.MODULE$, thread)})))), oom);
         } catch (Throwable var12) {
         }

         Runtime.getRuntime().halt(SparkExitCode$.MODULE$.OOM());
      } catch (Throwable var14) {
         Throwable t = var14;

         try {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Another uncaught exception in thread ", ", process halted."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(.MODULE$, thread)})))), t);
         } catch (Throwable var11) {
         }

         Runtime.getRuntime().halt(SparkExitCode$.MODULE$.UNCAUGHT_EXCEPTION_TWICE());
      }

   }

   public void uncaughtException(final Throwable exception) {
      this.uncaughtException(Thread.currentThread(), exception);
   }

   // $FF: synthetic method
   public static final int $anonfun$killOnFatalErrorDepth$1(final SparkEnv x$2) {
      return BoxesRunTime.unboxToInt(x$2.conf().get(org.apache.spark.internal.config.package$.MODULE$.KILL_ON_FATAL_ERROR_DEPTH()));
   }

   public SparkUncaughtExceptionHandler(final boolean exitOnUncaughtException) {
      this.exitOnUncaughtException = exitOnUncaughtException;
      Logging.$init$(this);
      Predef var10000 = scala.Predef..MODULE$;
      int var4 = SparkExitCode$.MODULE$.OOM();
      BoxedUnit var3 = BoxedUnit.UNIT;
      var10000.locally(BoxedUnit.UNIT);
      this.killOnFatalErrorDepth = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$killOnFatalErrorDepth$1(x$2))).getOrElse((JFunction0.mcI.sp)() -> 5));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
