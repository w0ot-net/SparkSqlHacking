package org.apache.spark.shuffle;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.commons.io.FileExistsException;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.ShuffleDataBlockId;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.UnrecognizedBlockId;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;

public final class KubernetesLocalDiskShuffleExecutorComponents$ implements Logging {
   public static final KubernetesLocalDiskShuffleExecutorComponents$ MODULE$ = new KubernetesLocalDiskShuffleExecutorComponents$();
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

   public void recoverDiskStore(final SparkConf conf, final BlockManager bm) {
      Tuple2 var6 = .MODULE$.partition$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])org.apache.spark.util.Utils..MODULE$.getConfiguredLocalDirs(conf)), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$recoverDiskStore$1(x$2)))), (s) -> new File((new File((new File(s)).getParent())).getParent()), scala.reflect.ClassTag..MODULE$.apply(File.class))), (dir) -> {
         File[] oldDirs = (File[]).MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])dir.listFiles()), (f) -> BoxesRunTime.boxToBoolean($anonfun$recoverDiskStore$4(f)));
         File[] files = (File[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])oldDirs), (x$3) -> x$3.listFiles(), (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(File.class))), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$recoverDiskStore$7(x$4)))), (x$5) -> x$5.listFiles(), (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(File.class))), (x$6) -> BoxesRunTime.boxToBoolean($anonfun$recoverDiskStore$10(x$6)))), (x$7) -> x$7.listFiles(), (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(File.class))), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$recoverDiskStore$13(x$8)))), (x$9) -> x$9.listFiles(), (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(File.class));
         return (Seq)(files != null ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(files).toImmutableArraySeq() : (Seq)scala.package..MODULE$.Seq().empty());
      }, scala.reflect.ClassTag..MODULE$.apply(File.class))), (x$10) -> BoxesRunTime.boxToBoolean($anonfun$recoverDiskStore$16(x$10)));
      if (var6 != null) {
         File[] checksumFiles = (File[])var6._1();
         File[] files = (File[])var6._2();
         Tuple2 var5 = new Tuple2(checksumFiles, files);
         File[] checksumFiles = (File[])var5._1();
         File[] files = (File[])var5._2();
         Tuple2 var12 = .MODULE$.partition$extension(scala.Predef..MODULE$.refArrayOps((Object[])files), (x$12) -> BoxesRunTime.boxToBoolean($anonfun$recoverDiskStore$17(x$12)));
         if (var12 != null) {
            File[] indexFiles = (File[])var12._1();
            File[] dataFiles = (File[])var12._2();
            Tuple2 var11 = new Tuple2(indexFiles, dataFiles);
            File[] indexFiles = (File[])var11._1();
            File[] dataFiles = (File[])var11._2();
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Found ", " data files, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_DATA_FILE..MODULE$, BoxesRunTime.boxToInteger(dataFiles.length))}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " index files, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_INDEX_FILE..MODULE$, BoxesRunTime.boxToInteger(indexFiles.length))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"and ", " checksum files."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_CHECKSUM_FILE..MODULE$, BoxesRunTime.boxToInteger(checksumFiles.length))}))))));
            HashMap checksumFileMap = new HashMap();
            String algorithm = (String)conf.get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_CHECKSUM_ALGORITHM());
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])checksumFiles), (f) -> {
               MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " -> "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, f.getName())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_ABSOLUTE_PATH..MODULE$, f.getAbsolutePath())}))))));
               return checksumFileMap.put(f.getName(), f);
            });
            HashMap indexFileMap = new HashMap();
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])indexFiles), (f) -> {
               MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " -> "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, f.getName().replace(".index", ".data"))}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_ABSOLUTE_PATH..MODULE$, f.getAbsolutePath())}))))));
               return indexFileMap.put(f.getName().replace(".index", ".data"), f);
            });
            ClassTag classTag = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.Object());
            StorageLevel level = org.apache.spark.storage.StorageLevel..MODULE$.DISK_ONLY();
            boolean checksumDisabled = !BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_CHECKSUM_ENABLED()));
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])dataFiles), indexFiles, scala.reflect.ClassTag..MODULE$.apply(File.class))), (f) -> {
               MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Try to recover ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_ABSOLUTE_PATH..MODULE$, f.getAbsolutePath())})))));

               Object var10000;
               try {
                  BlockId id = org.apache.spark.storage.BlockId..MODULE$.apply(f.getName());
                  if (id.isShuffle()) {
                     boolean skipVerification = checksumDisabled || f.getName().endsWith(".index");
                     File checksumFile = (File)checksumFileMap.getOrElse(org.apache.spark.shuffle.ShuffleChecksumUtils..MODULE$.getChecksumFileName(id, algorithm), () -> null);
                     File indexFile = (File)indexFileMap.getOrElse(f.getName(), () -> null);
                     if (!skipVerification && !MODULE$.verifyChecksum(algorithm, id, checksumFile, indexFile, f)) {
                        MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignore ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_ABSOLUTE_PATH..MODULE$, f.getAbsolutePath())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"due to the verification failure."})))).log(scala.collection.immutable.Nil..MODULE$))));
                        var10000 = BoxedUnit.UNIT;
                     } else {
                        long decryptedSize = f.length();
                        var10000 = BoxesRunTime.boxToBoolean((new BlockManager.TempFileBasedBlockStoreUpdater(bm, id, level, classTag, f, decryptedSize, bm.TempFileBasedBlockStoreUpdater().apply$default$6(), bm.TempFileBasedBlockStoreUpdater().apply$default$7())).save());
                     }
                  } else {
                     MODULE$.logInfo((Function0)(() -> "Ignore a non-shuffle block file."));
                     var10000 = BoxedUnit.UNIT;
                  }
               } catch (UnrecognizedBlockId var14) {
                  MODULE$.logInfo((Function0)(() -> "Skip due to UnrecognizedBlockId."));
                  var10000 = BoxedUnit.UNIT;
               } catch (FileExistsException var15) {
                  MODULE$.logInfo((Function0)(() -> "Ignore due to FileExistsException."));
                  var10000 = BoxedUnit.UNIT;
               }

               return var10000;
            });
         } else {
            throw new MatchError(var12);
         }
      } else {
         throw new MatchError(var6);
      }
   }

   public boolean verifyChecksum(final String algorithm, final BlockId blockId, final File checksumFile, final File indexFile, final File dataFile) {
      if (blockId instanceof ShuffleDataBlockId) {
         if (dataFile != null && dataFile.exists()) {
            if (checksumFile != null && checksumFile.exists()) {
               if (checksumFile.length() != 0L && checksumFile.length() % 8L == 0L) {
                  if (indexFile != null && indexFile.exists()) {
                     if (indexFile.length() == 0L) {
                        return false;
                     } else {
                        int numPartition = (int)(checksumFile.length() / 8L);
                        return org.apache.spark.shuffle.ShuffleChecksumUtils..MODULE$.compareChecksums(numPartition, algorithm, checksumFile, dataFile, indexFile);
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return true;
            }
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$recoverDiskStore$1(final String x$2) {
      return x$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$recoverDiskStore$4(final File f) {
      return f.isDirectory() && f.getName().startsWith("spark-");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$recoverDiskStore$7(final File x$4) {
      return x$4.isDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$recoverDiskStore$10(final File x$6) {
      return x$6.isDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$recoverDiskStore$13(final File x$8) {
      return x$8.isDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$recoverDiskStore$16(final File x$10) {
      return x$10.getName().contains(".checksum");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$recoverDiskStore$17(final File x$12) {
      return x$12.getName().endsWith(".index");
   }

   private KubernetesLocalDiskShuffleExecutorComponents$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
