package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.Map;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u000593A\u0001C\u0005\u0001%!AQ\u0004\u0001B\u0001B\u0003%a\u0004C\u0003#\u0001\u0011\u00051\u0005C\u0004'\u0001\t\u0007I\u0011A\u0014\t\re\u0002\u0001\u0015!\u0003)\u0011\u001dQ\u0004A1A\u0005\u0002mBaA\u0011\u0001!\u0002\u0013a\u0004\"B\"\u0001\t\u0003\"%a\u0006$jY\u0016\u0014\u0015m]3e)>\u0004x\u000e\\8hs6\u000b\u0007\u000f]3s\u0015\tQ1\"A\u0004ti>\u0014\u0018mZ3\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001']\u0001\"\u0001F\u000b\u000e\u0003%I!AF\u0005\u0003\u001dQ{\u0007o\u001c7pOfl\u0015\r\u001d9feB\u0011\u0001dG\u0007\u00023)\u0011!dC\u0001\tS:$XM\u001d8bY&\u0011A$\u0007\u0002\b\u0019><w-\u001b8h\u0003\u0011\u0019wN\u001c4\u0011\u0005}\u0001S\"A\u0006\n\u0005\u0005Z!!C*qCJ\\7i\u001c8g\u0003\u0019a\u0014N\\5u}Q\u0011A%\n\t\u0003)\u0001AQ!\b\u0002A\u0002y\tA\u0002^8q_2|w-\u001f$jY\u0016,\u0012\u0001\u000b\t\u0004S1rS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\r=\u0003H/[8o!\tycG\u0004\u00021iA\u0011\u0011GK\u0007\u0002e)\u00111'E\u0001\u0007yI|w\u000e\u001e \n\u0005UR\u0013A\u0002)sK\u0012,g-\u0003\u00028q\t11\u000b\u001e:j]\u001eT!!\u000e\u0016\u0002\u001bQ|\u0007o\u001c7pOf4\u0015\u000e\\3!\u0003-!x\u000e]8m_\u001eLX*\u00199\u0016\u0003q\u0002B!\u0010!/]5\taH\u0003\u0002@U\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0005s$aA'ba\u0006aAo\u001c9pY><\u00170T1qA\u0005\u0011r-\u001a;U_B|Gn\\4z\r>\u0014\bj\\:u)\tAS\tC\u0003G\u000f\u0001\u0007a&\u0001\u0005i_N$h.Y7fQ\t\u0001\u0001\n\u0005\u0002J\u00196\t!J\u0003\u0002L\u0017\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00055S%\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class FileBasedTopologyMapper extends TopologyMapper implements Logging {
   private final Option topologyFile;
   private final Map topologyMap;
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public Option topologyFile() {
      return this.topologyFile;
   }

   public Map topologyMap() {
      return this.topologyMap;
   }

   public Option getTopologyForHost(final String hostname) {
      Option topology = this.topologyMap().get(hostname);
      if (topology.isDefined()) {
         this.logDebug((Function0)(() -> hostname + " -> " + topology.get()));
      } else {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " does not have any topology information"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, hostname)})))));
      }

      return topology;
   }

   public FileBasedTopologyMapper(final SparkConf conf) {
      super(conf);
      Logging.$init$(this);
      this.topologyFile = (Option)conf.get((ConfigEntry)package$.MODULE$.STORAGE_REPLICATION_TOPOLOGY_FILE());
      scala.Predef..MODULE$.require(this.topologyFile().isDefined(), () -> "Please specify topology file via spark.storage.replication.topologyFile for FileBasedTopologyMapper.");
      this.topologyMap = Utils$.MODULE$.getPropertiesFromFile((String)this.topologyFile().get());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
