package org.apache.spark.deploy.history;

import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.util.JsonProtocol$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.control.NonFatal.;

public final class EventFilter$ implements Logging {
   public static final EventFilter$ MODULE$ = new EventFilter$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public void applyFilterToFile(final FileSystem fs, final Seq filters, final Path path, final Function2 onAccepted, final Function2 onRejected, final Function1 onUnidentified) {
      Utils$.MODULE$.tryWithResource(() -> EventLogFileReader$.MODULE$.openEventLog(path, fs), (in) -> {
         $anonfun$applyFilterToFile$2(onUnidentified, filters, onRejected, onAccepted, path, in);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$applyFilterToFile$6(final boolean x$2) {
      return !x$2;
   }

   // $FF: synthetic method
   public static final void $anonfun$applyFilterToFile$4(final Seq filters$1, final Function2 onRejected$1, final String line$1, final Function2 onAccepted$1, final SparkListenerEvent e) {
      Seq results = (Seq)filters$1.flatMap((x$1) -> (Option)x$1.acceptFn().lift().apply(e));
      if (results.nonEmpty() && results.forall((x$2) -> BoxesRunTime.boxToBoolean($anonfun$applyFilterToFile$6(BoxesRunTime.unboxToBoolean(x$2))))) {
         onRejected$1.apply(line$1, e);
      } else {
         onAccepted$1.apply(line$1, e);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$applyFilterToFile$3(final Function1 onUnidentified$1, final Seq filters$1, final Function2 onRejected$1, final Function2 onAccepted$1, final Path path$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String line = (String)x0$1._1();
         int lineNum = x0$1._2$mcI$sp();

         try {
            Object var10000;
            try {
               var10000 = new Some(JsonProtocol$.MODULE$.sparkEventFromJson(line));
            } catch (Throwable var16) {
               label38: {
                  if (var16 != null) {
                     Option var14 = .MODULE$.unapply(var16);
                     if (!var14.isEmpty()) {
                        onUnidentified$1.apply(line);
                        var10000 = scala.None..MODULE$;
                        break label38;
                     }
                  }

                  throw var16;
               }
            }

            Option event = (Option)var10000;
            event.foreach((e) -> {
               $anonfun$applyFilterToFile$4(filters$1, onRejected$1, line, onAccepted$1, e);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var18 = BoxedUnit.UNIT;
         } catch (Exception var17) {
            MODULE$.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception parsing Spark event log: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path$1.getName())})))), var17);
            MODULE$.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Malformed line #", ": ", "\\n"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LINE_NUM..MODULE$, BoxesRunTime.boxToInteger(lineNum)), new MDC(org.apache.spark.internal.LogKeys.LINE..MODULE$, line)})))));
            throw var17;
         }

      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$applyFilterToFile$2(final Function1 onUnidentified$1, final Seq filters$1, final Function2 onRejected$1, final Function2 onAccepted$1, final Path path$1, final InputStream in) {
      Iterator lines = scala.io.Source..MODULE$.fromInputStream(in, scala.io.Codec..MODULE$.UTF8()).getLines();
      lines.zipWithIndex().foreach((x0$1) -> {
         $anonfun$applyFilterToFile$3(onUnidentified$1, filters$1, onRejected$1, onAccepted$1, path$1, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   private EventFilter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
