package org.apache.spark.shuffle;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Optional;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv.;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.shuffle.api.ShuffleExecutorComponents;
import org.apache.spark.shuffle.api.ShuffleMapOutputWriter;
import org.apache.spark.shuffle.sort.io.LocalDiskShuffleExecutorComponents;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d\u0001\u0002\t\u0012\u0001iA\u0001b\f\u0001\u0003\u0002\u0003\u0006I\u0001\r\u0005\u0006i\u0001!\t!\u000e\u0005\bs\u0001\u0011\r\u0011\"\u0003;\u0011\u0019\u0019\u0005\u0001)A\u0005w!IA\t\u0001a\u0001\u0002\u0004%I!\u0012\u0005\n\u0019\u0002\u0001\r\u00111A\u0005\n5C\u0011B\u0016\u0001A\u0002\u0003\u0005\u000b\u0015\u0002$\t\u000b]\u0003A\u0011\t-\t\u000bA\u0004A\u0011I9\t\u000f\u0005\r\u0001\u0001\"\u0011\u0002\u0006\u001d9\u0011\u0011D\t\t\u0002\u0005maA\u0002\t\u0012\u0011\u0003\ti\u0002\u0003\u00045\u0019\u0011\u0005\u0011Q\u0005\u0005\b\u0003OaA\u0011AA\u0015\u0011\u001d\t\u0019\u0004\u0004C\u0001\u0003k\u0011AfS;cKJtW\r^3t\u0019>\u001c\u0017\r\u001c#jg.\u001c\u0006.\u001e4gY\u0016,\u00050Z2vi>\u00148i\\7q_:,g\u000e^:\u000b\u0005I\u0019\u0012aB:ik\u001a4G.\u001a\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sO\u000e\u00011\u0003\u0002\u0001\u001cG%\u0002\"\u0001H\u0011\u000e\u0003uQ!AH\u0010\u0002\t1\fgn\u001a\u0006\u0002A\u0005!!.\u0019<b\u0013\t\u0011SD\u0001\u0004PE*,7\r\u001e\t\u0003I\u001dj\u0011!\n\u0006\u0003ME\t1!\u00199j\u0013\tASEA\rTQV4g\r\\3Fq\u0016\u001cW\u000f^8s\u0007>l\u0007o\u001c8f]R\u001c\bC\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u0014\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0018,\u0005\u001daunZ4j]\u001e\f\u0011b\u001d9be.\u001cuN\u001c4\u0011\u0005E\u0012T\"A\n\n\u0005M\u001a\"!C*qCJ\\7i\u001c8g\u0003\u0019a\u0014N\\5u}Q\u0011a\u0007\u000f\t\u0003o\u0001i\u0011!\u0005\u0005\u0006_\t\u0001\r\u0001M\u0001\tI\u0016dWmZ1uKV\t1\b\u0005\u0002=\u00036\tQH\u0003\u0002?\u007f\u0005\u0011\u0011n\u001c\u0006\u0003\u0001F\tAa]8si&\u0011!)\u0010\u0002#\u0019>\u001c\u0017\r\u001c#jg.\u001c\u0006.\u001e4gY\u0016,\u00050Z2vi>\u00148i\\7q_:,g\u000e^:\u0002\u0013\u0011,G.Z4bi\u0016\u0004\u0013\u0001\u00042m_\u000e\\W*\u00198bO\u0016\u0014X#\u0001$\u0011\u0005\u001dSU\"\u0001%\u000b\u0005%\u001b\u0012aB:u_J\fw-Z\u0005\u0003\u0017\"\u0013AB\u00117pG.l\u0015M\\1hKJ\f\u0001C\u00197pG.l\u0015M\\1hKJ|F%Z9\u0015\u00059#\u0006CA(S\u001b\u0005\u0001&\"A)\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0003&\u0001B+oSRDq!\u0016\u0004\u0002\u0002\u0003\u0007a)A\u0002yIE\nQB\u00197pG.l\u0015M\\1hKJ\u0004\u0013AE5oSRL\u0017\r\\5{K\u0016CXmY;u_J$BAT-gQ\")!\f\u0003a\u00017\u0006)\u0011\r\u001d9JIB\u0011Al\u0019\b\u0003;\u0006\u0004\"A\u0018)\u000e\u0003}S!\u0001Y\r\u0002\rq\u0012xn\u001c;?\u0013\t\u0011\u0007+\u0001\u0004Qe\u0016$WMZ\u0005\u0003I\u0016\u0014aa\u0015;sS:<'B\u00012Q\u0011\u00159\u0007\u00021\u0001\\\u0003\u0019)\u00070Z2JI\")\u0011\u000e\u0003a\u0001U\u0006aQ\r\u001f;sC\u000e{gNZ5hgB!1N\\.\\\u001b\u0005a'BA7 \u0003\u0011)H/\u001b7\n\u0005=d'aA'ba\u0006)2M]3bi\u0016l\u0015\r](viB,Ho\u0016:ji\u0016\u0014H\u0003\u0002:vu~\u0004\"\u0001J:\n\u0005Q,#AF*ik\u001a4G.Z'ba>+H\u000f];u/JLG/\u001a:\t\u000bYL\u0001\u0019A<\u0002\u0013MDWO\u001a4mK&#\u0007CA(y\u0013\tI\bKA\u0002J]RDQa_\u0005A\u0002q\f\u0011\"\\1q)\u0006\u001c8.\u00133\u0011\u0005=k\u0018B\u0001@Q\u0005\u0011auN\\4\t\r\u0005\u0005\u0011\u00021\u0001x\u00035qW/\u001c)beRLG/[8og\u0006y2M]3bi\u0016\u001c\u0016N\\4mK\u001aKG.Z'ba>+H\u000f];u/JLG/\u001a:\u0015\r\u0005\u001d\u00111CA\u000b!\u0015Y\u0017\u0011BA\u0007\u0013\r\tY\u0001\u001c\u0002\t\u001fB$\u0018n\u001c8bYB\u0019A%a\u0004\n\u0007\u0005EQEA\u0011TS:<G.Z*qS2d7\u000b[;gM2,W*\u00199PkR\u0004X\u000f^,sSR,'\u000fC\u0003w\u0015\u0001\u0007q\u000f\u0003\u0004\u0002\u0018)\u0001\r\u0001`\u0001\u0006[\u0006\u0004\u0018\nZ\u0001-\u0017V\u0014WM\u001d8fi\u0016\u001cHj\\2bY\u0012K7o[*ik\u001a4G.Z#yK\u000e,Ho\u001c:D_6\u0004xN\\3oiN\u0004\"a\u000e\u0007\u0014\t1\ty\"\u000b\t\u0004\u001f\u0006\u0005\u0012bAA\u0012!\n1\u0011I\\=SK\u001a$\"!a\u0007\u0002!I,7m\u001c<fe\u0012K7o[*u_J,G#\u0002(\u0002,\u0005=\u0002BBA\u0017\u001d\u0001\u0007\u0001'\u0001\u0003d_:4\u0007BBA\u0019\u001d\u0001\u0007a)\u0001\u0002c[\u0006qa/\u001a:jMf\u001c\u0005.Z2lgVlG\u0003DA\u001c\u0003{\t\t%a\u0013\u0002Z\u0005u\u0003cA(\u0002:%\u0019\u00111\b)\u0003\u000f\t{w\u000e\\3b]\"1\u0011qH\bA\u0002m\u000b\u0011\"\u00197h_JLG\u000f[7\t\u000f\u0005\rs\u00021\u0001\u0002F\u00059!\r\\8dW&#\u0007cA$\u0002H%\u0019\u0011\u0011\n%\u0003\u000f\tcwnY6JI\"9\u0011QJ\bA\u0002\u0005=\u0013\u0001D2iK\u000e\\7/^7GS2,\u0007\u0003BA)\u0003+j!!a\u0015\u000b\u0005yz\u0012\u0002BA,\u0003'\u0012AAR5mK\"9\u00111L\bA\u0002\u0005=\u0013!C5oI\u0016Dh)\u001b7f\u0011\u001d\tyf\u0004a\u0001\u0003\u001f\n\u0001\u0002Z1uC\u001aKG.\u001a"
)
public class KubernetesLocalDiskShuffleExecutorComponents implements ShuffleExecutorComponents, Logging {
   private final SparkConf sparkConf;
   private final LocalDiskShuffleExecutorComponents delegate;
   private BlockManager blockManager;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean verifyChecksum(final String algorithm, final BlockId blockId, final File checksumFile, final File indexFile, final File dataFile) {
      return KubernetesLocalDiskShuffleExecutorComponents$.MODULE$.verifyChecksum(algorithm, blockId, checksumFile, indexFile, dataFile);
   }

   public static void recoverDiskStore(final SparkConf conf, final BlockManager bm) {
      KubernetesLocalDiskShuffleExecutorComponents$.MODULE$.recoverDiskStore(conf, bm);
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

   private LocalDiskShuffleExecutorComponents delegate() {
      return this.delegate;
   }

   private BlockManager blockManager() {
      return this.blockManager;
   }

   private void blockManager_$eq(final BlockManager x$1) {
      this.blockManager = x$1;
   }

   public void initializeExecutor(final String appId, final String execId, final Map extraConfigs) {
      this.delegate().initializeExecutor(appId, execId, extraConfigs);
      this.blockManager_$eq(.MODULE$.get().blockManager());
      if (this.sparkConf.getBoolean(Config$.MODULE$.KUBERNETES_DRIVER_REUSE_PVC().key(), false)) {
         this.logInfo((Function0)(() -> "Try to recover shuffle data."));
         this.blockManager().diskBlockManager().deleteFilesOnStop_$eq(false);
         org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> KubernetesLocalDiskShuffleExecutorComponents$.MODULE$.recoverDiskStore(this.sparkConf, this.blockManager()));
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skip recovery because ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, Config$.MODULE$.KUBERNETES_DRIVER_REUSE_PVC().key())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is disabled."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }
   }

   public ShuffleMapOutputWriter createMapOutputWriter(final int shuffleId, final long mapTaskId, final int numPartitions) {
      return this.delegate().createMapOutputWriter(shuffleId, mapTaskId, numPartitions);
   }

   public Optional createSingleFileMapOutputWriter(final int shuffleId, final long mapId) {
      return this.delegate().createSingleFileMapOutputWriter(shuffleId, mapId);
   }

   public KubernetesLocalDiskShuffleExecutorComponents(final SparkConf sparkConf) {
      this.sparkConf = sparkConf;
      Logging.$init$(this);
      this.delegate = new LocalDiskShuffleExecutorComponents(sparkConf);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
