package org.apache.spark.deploy.master;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.Deploy$;
import org.apache.spark.serializer.Serializer;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2QAB\u0004\u0001\u000fEA\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\tE\u0001\u0011\t\u0011)A\u0005G!)\u0001\u0006\u0001C\u0001S!)Q\u0006\u0001C\u0001]!)!\u0007\u0001C\u0001g\tQ\"k\\2lg\u0012\u0013%+Z2pm\u0016\u0014\u00180T8eK\u001a\u000b7\r^8ss*\u0011\u0001\"C\u0001\u0007[\u0006\u001cH/\u001a:\u000b\u0005)Y\u0011A\u00023fa2|\u0017P\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\r\u0001!C\u0006\t\u0003'Qi\u0011aB\u0005\u0003+\u001d\u0011Qd\u0015;b]\u0012\fGn\u001c8f%\u0016\u001cwN^3ss6{G-\u001a$bGR|'/\u001f\t\u0003/ii\u0011\u0001\u0007\u0006\u00033-\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u00037a\u0011q\u0001T8hO&tw-\u0001\u0003d_:47\u0001\u0001\t\u0003?\u0001j\u0011aC\u0005\u0003C-\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\u0015M,'/[1mSj,'\u000f\u0005\u0002%M5\tQE\u0003\u0002#\u0017%\u0011q%\n\u0002\u000b'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018A\u0002\u001fj]&$h\bF\u0002+W1\u0002\"a\u0005\u0001\t\u000bq\u0019\u0001\u0019\u0001\u0010\t\u000b\t\u001a\u0001\u0019A\u0012\u0002/\r\u0014X-\u0019;f!\u0016\u00148/[:uK:\u001cW-\u00128hS:,G#A\u0018\u0011\u0005M\u0001\u0014BA\u0019\b\u0005E\u0001VM]:jgR,gnY3F]\u001eLg.Z\u0001\u001aGJ,\u0017\r^3MK\u0006$WM]#mK\u000e$\u0018n\u001c8BO\u0016tG\u000f\u0006\u00025oA\u00111#N\u0005\u0003m\u001d\u00111\u0003T3bI\u0016\u0014X\t\\3di&|g.Q4f]RDQ\u0001C\u0003A\u0002a\u0002\"aE\u001d\n\u0005i:!a\u0004'fC\u0012,'/\u00127fGR\f'\r\\3"
)
public class RocksDBRecoveryModeFactory extends StandaloneRecoveryModeFactory implements Logging {
   private final SparkConf conf;
   private final Serializer serializer;
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

   public PersistenceEngine createPersistenceEngine() {
      String recoveryDir = (String)this.conf.get(Deploy$.MODULE$.RECOVERY_DIRECTORY());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Persisting recovery state to directory: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, recoveryDir)}))))));
      return new RocksDBPersistenceEngine(recoveryDir, this.serializer);
   }

   public LeaderElectionAgent createLeaderElectionAgent(final LeaderElectable master) {
      return new MonarchyLeaderAgent(master);
   }

   public RocksDBRecoveryModeFactory(final SparkConf conf, final Serializer serializer) {
      super(conf, serializer);
      this.conf = conf;
      this.serializer = serializer;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
