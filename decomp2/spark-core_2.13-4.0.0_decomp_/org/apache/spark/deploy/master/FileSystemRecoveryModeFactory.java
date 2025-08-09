package org.apache.spark.deploy.master;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Deploy$;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.serializer.Serializer;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553Q\u0001C\u0005\u0001\u0013MA\u0001B\b\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\tI\u0001\u0011\t\u0011)A\u0005K!)!\u0006\u0001C\u0001W!9q\u0006\u0001b\u0001\n\u0003\u0001\u0004B\u0002 \u0001A\u0003%\u0011\u0007C\u0003@\u0001\u0011\u0005\u0001\tC\u0003E\u0001\u0011\u0005QIA\u000fGS2,7+_:uK6\u0014VmY8wKJLXj\u001c3f\r\u0006\u001cGo\u001c:z\u0015\tQ1\"\u0001\u0004nCN$XM\u001d\u0006\u0003\u00195\ta\u0001Z3qY>L(B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0014\u0007\u0001!\u0002\u0004\u0005\u0002\u0016-5\t\u0011\"\u0003\u0002\u0018\u0013\ti2\u000b^1oI\u0006dwN\\3SK\u000e|g/\u001a:z\u001b>$WMR1di>\u0014\u0018\u0010\u0005\u0002\u001a95\t!D\u0003\u0002\u001c\u001b\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002\u001e5\t9Aj\\4hS:<\u0017\u0001B2p]\u001a\u001c\u0001\u0001\u0005\u0002\"E5\tQ\"\u0003\u0002$\u001b\tI1\u000b]1sW\u000e{gNZ\u0001\u000bg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bC\u0001\u0014)\u001b\u00059#B\u0001\u0013\u000e\u0013\tIsE\u0001\u0006TKJL\u0017\r\\5{KJ\fa\u0001P5oSRtDc\u0001\u0017.]A\u0011Q\u0003\u0001\u0005\u0006=\r\u0001\r\u0001\t\u0005\u0006I\r\u0001\r!J\u0001\fe\u0016\u001cwN^3ss\u0012K'/F\u00012!\t\u00114H\u0004\u00024sA\u0011AgN\u0007\u0002k)\u0011agH\u0001\u0007yI|w\u000e\u001e \u000b\u0003a\nQa]2bY\u0006L!AO\u001c\u0002\rA\u0013X\rZ3g\u0013\taTH\u0001\u0004TiJLgn\u001a\u0006\u0003u]\nAB]3d_Z,'/\u001f#je\u0002\nqc\u0019:fCR,\u0007+\u001a:tSN$XM\\2f\u000b:<\u0017N\\3\u0015\u0003\u0005\u0003\"!\u0006\"\n\u0005\rK!!\u0005)feNL7\u000f^3oG\u0016,enZ5oK\u0006I2M]3bi\u0016dU-\u00193fe\u0016cWm\u0019;j_:\fu-\u001a8u)\t1\u0015\n\u0005\u0002\u0016\u000f&\u0011\u0001*\u0003\u0002\u0014\u0019\u0016\fG-\u001a:FY\u0016\u001cG/[8o\u0003\u001e,g\u000e\u001e\u0005\u0006\u0015\u001d\u0001\rA\u0013\t\u0003+-K!\u0001T\u0005\u0003\u001f1+\u0017\rZ3s\u000b2,7\r^1cY\u0016\u0004"
)
public class FileSystemRecoveryModeFactory extends StandaloneRecoveryModeFactory implements Logging {
   private final SparkConf conf;
   private final Serializer serializer;
   private final String recoveryDir;
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

   public String recoveryDir() {
      return this.recoveryDir;
   }

   public PersistenceEngine createPersistenceEngine() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Persisting recovery state to directory: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.recoveryDir())})))));
      Option codec = ((Option)this.conf.get((ConfigEntry)Deploy$.MODULE$.RECOVERY_COMPRESSION_CODEC())).map((c) -> CompressionCodec$.MODULE$.createCodec(this.conf, c));
      return new FileSystemPersistenceEngine(this.recoveryDir(), this.serializer, codec);
   }

   public LeaderElectionAgent createLeaderElectionAgent(final LeaderElectable master) {
      return new MonarchyLeaderAgent(master);
   }

   public FileSystemRecoveryModeFactory(final SparkConf conf, final Serializer serializer) {
      super(conf, serializer);
      this.conf = conf;
      this.serializer = serializer;
      Logging.$init$(this);
      this.recoveryDir = (String)conf.get(Deploy$.MODULE$.RECOVERY_DIRECTORY());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
