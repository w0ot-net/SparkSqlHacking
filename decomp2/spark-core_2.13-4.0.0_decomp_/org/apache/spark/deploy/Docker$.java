package org.apache.spark.deploy;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.StringOps.;
import scala.runtime.BoxedUnit;
import scala.runtime.ObjectRef;
import scala.sys.process.ProcessBuilder;

public final class Docker$ implements Logging {
   public static final Docker$ MODULE$ = new Docker$();
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

   public ProcessBuilder makeRunCmd(final String imageTag, final String args, final String mountDir) {
      String var10000;
      label17: {
         label16: {
            String var5 = "";
            if (mountDir == null) {
               if (var5 != null) {
                  break label16;
               }
            } else if (!mountDir.equals(var5)) {
               break label16;
            }

            var10000 = "";
            break label17;
         }

         var10000 = " -v " + mountDir;
      }

      String mountCmd = var10000;
      String cmd = .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("docker run -privileged %s %s %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{mountCmd, imageTag, args}));
      this.logDebug((Function0)(() -> "Run command: " + cmd));
      return scala.sys.process.package..MODULE$.stringToProcess(cmd);
   }

   public String makeRunCmd$default$2() {
      return "";
   }

   public String makeRunCmd$default$3() {
      return "";
   }

   public void kill(final DockerId dockerId) {
      scala.sys.process.package..MODULE$.stringToProcess(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("docker kill %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{dockerId.id()}))).$bang();
   }

   public DockerId getLastProcessId() {
      ObjectRef id = ObjectRef.create((Object)null);
      scala.sys.process.package..MODULE$.stringToProcess("docker ps -l -q").$bang(scala.sys.process.ProcessLogger..MODULE$.apply((line) -> {
         $anonfun$getLastProcessId$1(id, line);
         return BoxedUnit.UNIT;
      }));
      return new DockerId((String)id.elem);
   }

   // $FF: synthetic method
   public static final void $anonfun$getLastProcessId$1(final ObjectRef id$1, final String line) {
      id$1.elem = line;
   }

   private Docker$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
