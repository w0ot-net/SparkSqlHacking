package org.apache.spark.storage;

import java.io.DataInputStream;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NioManagedBuffer;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.shuffle.IndexShuffleBlockResolver$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple4;
import scala.None.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class FallbackStorage$ implements Logging {
   public static final FallbackStorage$ MODULE$ = new FallbackStorage$();
   private static final BlockManagerId FALLBACK_BLOCK_MANAGER_ID;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      FALLBACK_BLOCK_MANAGER_ID = BlockManagerId$.MODULE$.apply("fallback", "remote", 7337, BlockManagerId$.MODULE$.apply$default$4());
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

   public BlockManagerId FALLBACK_BLOCK_MANAGER_ID() {
      return FALLBACK_BLOCK_MANAGER_ID;
   }

   public Option getFallbackStorage(final SparkConf conf) {
      return (Option)(((Option)conf.get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH())).isDefined() ? new Some(new FallbackStorage(conf)) : .MODULE$);
   }

   public void registerBlockManagerIfNeeded(final BlockManagerMaster master, final SparkConf conf) {
      if (((Option)conf.get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH())).isDefined()) {
         master.registerBlockManager(this.FALLBACK_BLOCK_MANAGER_ID(), (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class)), 0L, 0L, new NoopRpcEndpointRef(conf), master.registerBlockManager$default$6());
      }
   }

   public void cleanUp(final SparkConf conf, final Configuration hadoopConf) {
      if (((Option)conf.get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH())).isDefined() && BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_CLEANUP())) && conf.contains("spark.app.id")) {
         Path fallbackPath = new Path((String)((Option)conf.get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH())).get(), conf.getAppId());
         URI fallbackUri = fallbackPath.toUri();
         FileSystem fallbackFileSystem = FileSystem.get(fallbackUri, hadoopConf);
         if (fallbackFileSystem.exists(fallbackPath)) {
            if (fallbackFileSystem.delete(fallbackPath, true)) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Succeed to clean up: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URI..MODULE$, fallbackUri)})))));
            } else {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to clean up: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URI..MODULE$, fallbackUri)})))));
            }
         }
      }
   }

   public boolean org$apache$spark$storage$FallbackStorage$$reportBlockStatus(final BlockManager blockManager, final BlockId blockId, final long dataLength) {
      scala.Predef..MODULE$.assert(blockManager.master() != null);
      return blockManager.master().updateBlockInfo(this.FALLBACK_BLOCK_MANAGER_ID(), blockId, org.apache.spark.storage.StorageLevel..MODULE$.DISK_ONLY(), 0L, dataLength);
   }

   public ManagedBuffer read(final SparkConf conf, final BlockId blockId) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Read ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)})))));
      Path fallbackPath = new Path((String)((Option)conf.get((ConfigEntry)package$.MODULE$.STORAGE_DECOMMISSION_FALLBACK_STORAGE_PATH())).get());
      Configuration hadoopConf = SparkHadoopUtil$.MODULE$.get().newConfiguration(conf);
      FileSystem fallbackFileSystem = FileSystem.get(fallbackPath.toUri(), hadoopConf);
      String appId = conf.getAppId();
      Tuple4 var10000;
      if (blockId instanceof ShuffleBlockId var12) {
         var10000 = new Tuple4(BoxesRunTime.boxToInteger(var12.shuffleId()), BoxesRunTime.boxToLong(var12.mapId()), BoxesRunTime.boxToInteger(var12.reduceId()), BoxesRunTime.boxToInteger(var12.reduceId() + 1));
      } else {
         if (!(blockId instanceof ShuffleBlockBatchId)) {
            throw org.apache.spark.SparkException..MODULE$.internalError("unexpected shuffle block id format: " + blockId, "STORAGE");
         }

         ShuffleBlockBatchId var13 = (ShuffleBlockBatchId)blockId;
         var10000 = new Tuple4(BoxesRunTime.boxToInteger(var13.shuffleId()), BoxesRunTime.boxToLong(var13.mapId()), BoxesRunTime.boxToInteger(var13.startReduceId()), BoxesRunTime.boxToInteger(var13.endReduceId()));
      }

      Tuple4 var10 = var10000;
      if (var10 != null) {
         int shuffleId = BoxesRunTime.unboxToInt(var10._1());
         long mapId = BoxesRunTime.unboxToLong(var10._2());
         int startReduceId = BoxesRunTime.unboxToInt(var10._3());
         int endReduceId = BoxesRunTime.unboxToInt(var10._4());
         Tuple4 var9 = new Tuple4(BoxesRunTime.boxToInteger(shuffleId), BoxesRunTime.boxToLong(mapId), BoxesRunTime.boxToInteger(startReduceId), BoxesRunTime.boxToInteger(endReduceId));
         int shuffleId = BoxesRunTime.unboxToInt(var9._1());
         long mapId = BoxesRunTime.unboxToLong(var9._2());
         int startReduceId = BoxesRunTime.unboxToInt(var9._3());
         int endReduceId = BoxesRunTime.unboxToInt(var9._4());
         String name = (new ShuffleIndexBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID())).name();
         int hash = JavaUtils.nonNegativeHash(name);
         Path indexFile = new Path(fallbackPath, appId + "/" + shuffleId + "/" + hash + "/" + name);
         long start = (long)startReduceId * 8L;
         long end = (long)endReduceId * 8L;
         return (ManagedBuffer)Utils$.MODULE$.tryWithResource(() -> fallbackFileSystem.open(indexFile), (inputStream) -> (NioManagedBuffer)Utils$.MODULE$.tryWithResource(() -> new DataInputStream(inputStream), (index) -> {
               index.skip(start);
               long offset = index.readLong();
               index.skip(end - (start + 8L));
               long nextOffset = index.readLong();
               String name = (new ShuffleDataBlockId(shuffleId, mapId, IndexShuffleBlockResolver$.MODULE$.NOOP_REDUCE_ID())).name();
               int hash = JavaUtils.nonNegativeHash(name);
               Path dataFile = new Path(fallbackPath, appId + "/" + shuffleId + "/" + hash + "/" + name);
               long size = nextOffset - offset;
               MODULE$.logDebug((Function0)(() -> "To byte array " + size));
               byte[] array = new byte[(int)size];
               long startTimeNs = System.nanoTime();
               Utils$.MODULE$.tryWithResource(() -> fallbackFileSystem.open(dataFile), (f) -> {
                  $anonfun$read$8(offset, array, startTimeNs, f);
                  return BoxedUnit.UNIT;
               });
               return new NioManagedBuffer(ByteBuffer.wrap(array));
            }));
      } else {
         throw new MatchError(var10);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$read$8(final long offset$1, final byte[] array$1, final long startTimeNs$1, final FSDataInputStream f) {
      f.seek(offset$1);
      f.readFully(array$1);
      MODULE$.logDebug((Function0)(() -> {
         long var10000 = System.nanoTime() - startTimeNs$1;
         return "Took " + var10000 / 1000000L + "ms";
      }));
   }

   private FallbackStorage$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
