package org.apache.spark.ml.util;

import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005]4Q\u0001E\t\u0001+mA\u0001\u0002\u000b\u0001\u0003\u0006\u0004%\tA\u000b\u0005\te\u0001\u0011\t\u0011)A\u0005W!A1\u0007\u0001BC\u0002\u0013\u0005A\u0007\u0003\u0005A\u0001\t\u0005\t\u0015!\u00036\u0011\u0015\t\u0005\u0001\"\u0003C\u0011\u00151\u0005\u0001\"\u00155\u0011\u00159\u0005\u0001\"\u0011I\u0011\u00159\u0005\u0001\"\u0011R\u0011\u00159\u0006\u0001\"\u0011Y\u0011\u0015Q\u0006\u0001\"\u0011\\\u000f\u0019i\u0016\u0003#\u0001\u0016=\u001a1\u0001#\u0005E\u0001+}CQ!\u0011\u0007\u0005\u0002\u0001DQ!\u0019\u0007\u0005\u0002\tDQ!\u0019\u0007\u0005\u0002\u0015\u0014qc\u00149uS>t\u0017\r\\%ogR\u0014X/\\3oi\u0006$\u0018n\u001c8\u000b\u0005I\u0019\u0012\u0001B;uS2T!\u0001F\u000b\u0002\u00055d'B\u0001\f\u0018\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0012$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00025\u0005\u0019qN]4\u0014\u0007\u0001a\"\u0005\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VM\u001a\t\u0003G\u0019j\u0011\u0001\n\u0006\u0003KU\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003O\u0011\u0012q\u0001T8hO&tw-A\bj]N$(/^7f]R\fG/[8o\u0007\u0001)\u0012a\u000b\t\u0004;1r\u0013BA\u0017\u001f\u0005\u0019y\u0005\u000f^5p]B\u0011q\u0006M\u0007\u0002#%\u0011\u0011'\u0005\u0002\u0010\u0013:\u001cHO];nK:$\u0018\r^5p]\u0006\u0001\u0012N\\:ueVlWM\u001c;bi&|g\u000eI\u0001\nG2\f7o\u001d(b[\u0016,\u0012!\u000e\t\u0003mur!aN\u001e\u0011\u0005arR\"A\u001d\u000b\u0005iJ\u0013A\u0002\u001fs_>$h(\u0003\u0002==\u00051\u0001K]3eK\u001aL!AP \u0003\rM#(/\u001b8h\u0015\tad$\u0001\u0006dY\u0006\u001c8OT1nK\u0002\na\u0001P5oSRtDcA\"E\u000bB\u0011q\u0006\u0001\u0005\u0006Q\u0015\u0001\ra\u000b\u0005\u0006g\u0015\u0001\r!N\u0001\bY><g*Y7f\u0003\u001dawnZ%oM>$\"!\u0013'\u0011\u0005uQ\u0015BA&\u001f\u0005\u0011)f.\u001b;\t\r5;A\u00111\u0001O\u0003\ri7o\u001a\t\u0004;=+\u0014B\u0001)\u001f\u0005!a$-\u001f8b[\u0016tDCA%S\u0011\u0015\u0019\u0006\u00021\u0001U\u0003!awnZ#oiJL\bCA\u0012V\u0013\t1FE\u0001\u0005M_\u001e,e\u000e\u001e:z\u0003)awnZ,be:Lgn\u001a\u000b\u0003\u0013fCa!T\u0005\u0005\u0002\u0004q\u0015\u0001\u00037pO\u0016\u0013(o\u001c:\u0015\u0005%c\u0006BB'\u000b\t\u0003\u0007a*A\fPaRLwN\\1m\u0013:\u001cHO];nK:$\u0018\r^5p]B\u0011q\u0006D\n\u0003\u0019q!\u0012AX\u0001\u0007GJ,\u0017\r^3\u0015\u0005\r\u001b\u0007\"\u00023\u000f\u0001\u0004q\u0013!B5ogR\u0014HCA\"g\u0011\u00159w\u00021\u0001i\u0003\u0015\u0019G.\u0019>{a\tIg\u000eE\u00027U2L!a[ \u0003\u000b\rc\u0017m]:\u0011\u00055tG\u0002\u0001\u0003\n_\u001a\f\t\u0011!A\u0003\u0002A\u00141a\u0018\u00135#\t\tH\u000f\u0005\u0002\u001ee&\u00111O\b\u0002\b\u001d>$\b.\u001b8h!\tiR/\u0003\u0002w=\t\u0019\u0011I\\="
)
public class OptionalInstrumentation implements Logging {
   private final Option instrumentation;
   private final String className;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static OptionalInstrumentation create(final Class clazz) {
      return OptionalInstrumentation$.MODULE$.create(clazz);
   }

   public static OptionalInstrumentation create(final Instrumentation instr) {
      return OptionalInstrumentation$.MODULE$.create(instr);
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

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
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

   public Option instrumentation() {
      return this.instrumentation;
   }

   public String className() {
      return this.className;
   }

   public String logName() {
      return this.className();
   }

   public void logInfo(final Function0 msg) {
      Option var3 = this.instrumentation();
      if (var3 instanceof Some var4) {
         Instrumentation instr = (Instrumentation)var4.value();
         instr.logInfo(msg);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(var3)) {
         Logging.logInfo$(this, msg);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   public void logInfo(final LogEntry logEntry) {
      Option var3 = this.instrumentation();
      if (var3 instanceof Some var4) {
         Instrumentation instr = (Instrumentation)var4.value();
         instr.logInfo(logEntry);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(var3)) {
         Logging.logInfo$(this, logEntry);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   public void logWarning(final Function0 msg) {
      Option var3 = this.instrumentation();
      if (var3 instanceof Some var4) {
         Instrumentation instr = (Instrumentation)var4.value();
         instr.logWarning(msg);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(var3)) {
         Logging.logWarning$(this, msg);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   public void logError(final Function0 msg) {
      Option var3 = this.instrumentation();
      if (var3 instanceof Some var4) {
         Instrumentation instr = (Instrumentation)var4.value();
         instr.logError(msg);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(var3)) {
         Logging.logError$(this, msg);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   public OptionalInstrumentation(final Option instrumentation, final String className) {
      this.instrumentation = instrumentation;
      this.className = className;
      Logging.$init$(this);
   }
}
