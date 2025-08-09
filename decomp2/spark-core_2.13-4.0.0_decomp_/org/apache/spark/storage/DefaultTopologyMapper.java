package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.None.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005y2A\u0001B\u0003\u0001\u001d!A\u0011\u0004\u0001B\u0001B\u0003%!\u0004C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003#\u0001\u0011\u00053EA\u000bEK\u001a\fW\u000f\u001c;U_B|Gn\\4z\u001b\u0006\u0004\b/\u001a:\u000b\u0005\u00199\u0011aB:u_J\fw-\u001a\u0006\u0003\u0011%\tQa\u001d9be.T!AC\u0006\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0011aA8sO\u000e\u00011c\u0001\u0001\u0010'A\u0011\u0001#E\u0007\u0002\u000b%\u0011!#\u0002\u0002\u000f)>\u0004x\u000e\\8hs6\u000b\u0007\u000f]3s!\t!r#D\u0001\u0016\u0015\t1r!\u0001\u0005j]R,'O\\1m\u0013\tARCA\u0004M_\u001e<\u0017N\\4\u0002\t\r|gN\u001a\t\u00037qi\u0011aB\u0005\u0003;\u001d\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\t\u0001\u0013\u0005\u0005\u0002\u0011\u0001!)\u0011D\u0001a\u00015\u0005\u0011r-\u001a;U_B|Gn\\4z\r>\u0014\bj\\:u)\t!S\u0007E\u0002&Q)j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005-\u0012dB\u0001\u00171!\tic%D\u0001/\u0015\tyS\"\u0001\u0004=e>|GOP\u0005\u0003c\u0019\na\u0001\u0015:fI\u00164\u0017BA\u001a5\u0005\u0019\u0019FO]5oO*\u0011\u0011G\n\u0005\u0006m\r\u0001\rAK\u0001\tQ>\u001cHO\\1nK\"\u0012\u0001\u0001\u000f\t\u0003sqj\u0011A\u000f\u0006\u0003w\u001d\t!\"\u00198o_R\fG/[8o\u0013\ti$H\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public class DefaultTopologyMapper extends TopologyMapper implements Logging {
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

   public Option getTopologyForHost(final String hostname) {
      this.logDebug((Function0)(() -> "Got a request for " + hostname));
      return .MODULE$;
   }

   public DefaultTopologyMapper(final SparkConf conf) {
      super(conf);
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
