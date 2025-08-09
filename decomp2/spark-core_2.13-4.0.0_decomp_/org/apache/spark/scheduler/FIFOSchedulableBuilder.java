package org.apache.spark.scheduler;

import java.util.Map;
import java.util.Properties;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053QAB\u0004\u0001\u0013=A\u0001\u0002\t\u0001\u0003\u0006\u0004%\tA\t\u0005\tM\u0001\u0011\t\u0011)A\u0005G!)q\u0005\u0001C\u0001Q!)1\u0006\u0001C!Y!)\u0001\u0007\u0001C!c\t1b)\u0013$P'\u000eDW\rZ;mC\ndWMQ;jY\u0012,'O\u0003\u0002\t\u0013\u0005I1o\u00195fIVdWM\u001d\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sON!\u0001\u0001\u0005\f\u001b!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0011q\u0003G\u0007\u0002\u000f%\u0011\u0011d\u0002\u0002\u0013'\u000eDW\rZ;mC\ndWMQ;jY\u0012,'\u000f\u0005\u0002\u001c=5\tAD\u0003\u0002\u001e\u0013\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002 9\t9Aj\\4hS:<\u0017\u0001\u0003:p_R\u0004vn\u001c7\u0004\u0001U\t1\u0005\u0005\u0002\u0018I%\u0011Qe\u0002\u0002\u0005!>|G.A\u0005s_>$\bk\\8mA\u00051A(\u001b8jiz\"\"!\u000b\u0016\u0011\u0005]\u0001\u0001\"\u0002\u0011\u0004\u0001\u0004\u0019\u0013A\u00032vS2$\u0007k\\8mgR\tQ\u0006\u0005\u0002\u0012]%\u0011qF\u0005\u0002\u0005+:LG/A\tbI\u0012$\u0016m]6TKRl\u0015M\\1hKJ$2!\f\u001a8\u0011\u0015\u0019T\u00011\u00015\u0003\u001di\u0017M\\1hKJ\u0004\"aF\u001b\n\u0005Y:!aC*dQ\u0016$W\u000f\\1cY\u0016DQ\u0001O\u0003A\u0002e\n!\u0002\u001d:pa\u0016\u0014H/[3t!\tQt(D\u0001<\u0015\taT(\u0001\u0003vi&d'\"\u0001 \u0002\t)\fg/Y\u0005\u0003\u0001n\u0012!\u0002\u0015:pa\u0016\u0014H/[3t\u0001"
)
public class FIFOSchedulableBuilder implements SchedulableBuilder, Logging {
   private final Pool rootPool;
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

   public Pool rootPool() {
      return this.rootPool;
   }

   public void buildPools() {
   }

   public void addTaskSetManager(final Schedulable manager, final Properties properties) {
      this.rootPool().addSchedulable(manager);
   }

   public FIFOSchedulableBuilder(final Pool rootPool) {
      this.rootPool = rootPool;
      Logging.$init$(this);
   }
}
