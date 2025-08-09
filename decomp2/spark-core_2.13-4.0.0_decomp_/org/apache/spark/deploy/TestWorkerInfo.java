package org.apache.spark.deploy;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.json4s.Formats;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e3A\u0001D\u0007\u0005-!A1\u0005\u0001BC\u0002\u0013\u0005A\u0005\u0003\u00051\u0001\t\u0005\t\u0015!\u0003&\u0011!\t\u0004A!b\u0001\n\u0003\u0011\u0004\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011B\u001a\t\u0011a\u0002!Q1A\u0005\u0002eB\u0001B\u0011\u0001\u0003\u0002\u0003\u0006IA\u000f\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\b\u0013\u0002\u0011\r\u0011b\u0001K\u0011\u0019\t\u0006\u0001)A\u0005\u0017\")!\u000b\u0001C\u0001'\")q\u000b\u0001C!1\nqA+Z:u/>\u00148.\u001a:J]\u001a|'B\u0001\b\u0010\u0003\u0019!W\r\u001d7ps*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u00019R\u0004\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VM\u001a\t\u0003=\u0005j\u0011a\b\u0006\u0003A=\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003E}\u0011q\u0001T8hO&tw-\u0001\u0002jaV\tQ\u0005\u0005\u0002'[9\u0011qe\u000b\t\u0003Qei\u0011!\u000b\u0006\u0003UU\ta\u0001\u0010:p_Rt\u0014B\u0001\u0017\u001a\u0003\u0019\u0001&/\u001a3fM&\u0011af\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051J\u0012aA5qA\u0005AAm\\2lKJLE-F\u00014!\t!T'D\u0001\u000e\u0013\t1TB\u0001\u0005E_\u000e\\WM]%e\u0003%!wnY6fe&#\u0007%A\u0004m_\u001e4\u0015\u000e\\3\u0016\u0003i\u0002\"a\u000f!\u000e\u0003qR!!\u0010 \u0002\u0005%|'\"A \u0002\t)\fg/Y\u0005\u0003\u0003r\u0012AAR5mK\u0006AAn\\4GS2,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005\u000b\u001a;\u0005\n\u0005\u00025\u0001!)1e\u0002a\u0001K!)\u0011g\u0002a\u0001g!)\u0001h\u0002a\u0001u\u00059am\u001c:nCR\u001cX#A&\u0011\u00051{U\"A'\u000b\u00059\u001b\u0012A\u00026t_:$4/\u0003\u0002Q\u001b\n9ai\u001c:nCR\u001c\u0018\u0001\u00034pe6\fGo\u001d\u0011\u0002\t-LG\u000e\u001c\u000b\u0002)B\u0011\u0001$V\u0005\u0003-f\u0011A!\u00168ji\u0006AAo\\*ue&tw\rF\u0001&\u0001"
)
public class TestWorkerInfo implements Logging {
   private final String ip;
   private final DockerId dockerId;
   private final File logFile;
   private final Formats formats;
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

   public String ip() {
      return this.ip;
   }

   public DockerId dockerId() {
      return this.dockerId;
   }

   public File logFile() {
      return this.logFile;
   }

   public Formats formats() {
      return this.formats;
   }

   public void kill() {
      Docker$.MODULE$.kill(this.dockerId());
   }

   public String toString() {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("[ip=%s, id=%s, logFile=%s]"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.ip(), this.dockerId(), this.logFile().getAbsolutePath()}));
   }

   public TestWorkerInfo(final String ip, final DockerId dockerId, final File logFile) {
      this.ip = ip;
      this.dockerId = dockerId;
      this.logFile = logFile;
      Logging.$init$(this);
      this.formats = org.json4s.DefaultFormats..MODULE$;
      this.logDebug((Function0)(() -> "Created worker: " + this));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
