package org.apache.spark.rdd;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.Partitioner;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.serializer.DeserializationStream;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.util.SerializableConfiguration;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class ReliableCheckpointRDD$ implements Logging, Serializable {
   public static final ReliableCheckpointRDD$ MODULE$ = new ReliableCheckpointRDD$();
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

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public String org$apache$spark$rdd$ReliableCheckpointRDD$$checkpointFileName(final int partitionIndex) {
      return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("part-%05d"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(partitionIndex)}));
   }

   private String checkpointPartitionerFileName() {
      return "_partitioner";
   }

   public ReliableCheckpointRDD writeRDDToCheckpointDirectory(final RDD originalRDD, final String checkpointDir, final int blockSize, final ClassTag evidence$2) {
      long checkpointStartTimeNs = System.nanoTime();
      SparkContext sc = originalRDD.sparkContext();
      Path checkpointDirPath = new Path(checkpointDir);
      FileSystem fs = checkpointDirPath.getFileSystem(sc.hadoopConfiguration());
      if (!fs.mkdirs(checkpointDirPath)) {
         throw SparkCoreErrors$.MODULE$.failToCreateCheckpointPathError(checkpointDirPath);
      } else {
         Broadcast broadcastedConf = sc.broadcast(new SerializableConfiguration(sc.hadoopConfiguration()), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
         String var11 = checkpointDirPath.toString();
         int var12 = this.writePartitionToCheckpointFile$default$3();
         sc.runJob(originalRDD, (Function2)((ctx, iterator) -> {
            $anonfun$writeRDDToCheckpointDirectory$1(var11, broadcastedConf, var12, evidence$2, ctx, iterator);
            return BoxedUnit.UNIT;
         }), scala.reflect.ClassTag..MODULE$.Unit());
         if (originalRDD.partitioner().nonEmpty()) {
            this.writePartitionerToCheckpointDir(sc, (Partitioner)originalRDD.partitioner().get(), checkpointDirPath);
         }

         long checkpointDurationMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - checkpointStartTimeNs);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Checkpointing took ", " ms."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(checkpointDurationMs))})))));
         ReliableCheckpointRDD newRDD = new ReliableCheckpointRDD(sc, checkpointDirPath.toString(), originalRDD.partitioner(), evidence$2);
         if (newRDD.partitions().length != originalRDD.partitions().length) {
            throw SparkCoreErrors$.MODULE$.checkpointRDDHasDifferentNumberOfPartitionsFromOriginalRDDError(originalRDD.id(), originalRDD.partitions().length, newRDD.id(), newRDD.partitions().length);
         } else {
            return newRDD;
         }
      }
   }

   public int writeRDDToCheckpointDirectory$default$3() {
      return -1;
   }

   public void writePartitionToCheckpointFile(final String path, final Broadcast broadcastedConf, final int blockSize, final TaskContext ctx, final Iterator iterator, final ClassTag evidence$3) {
      SparkEnv env = SparkEnv$.MODULE$.get();
      Path outputDir = new Path(path);
      FileSystem fs = outputDir.getFileSystem(((SerializableConfiguration)broadcastedConf.value()).value());
      String finalOutputName = this.org$apache$spark$rdd$ReliableCheckpointRDD$$checkpointFileName(ctx.partitionId());
      Path finalOutputPath = new Path(outputDir, finalOutputName);
      Path tempOutputPath = new Path(outputDir, "." + finalOutputName + "-attempt-" + ctx.taskAttemptId());
      int bufferSize = BoxesRunTime.unboxToInt(env.conf().get(org.apache.spark.internal.config.package$.MODULE$.BUFFER_SIZE()));
      Object var10000;
      if (blockSize < 0) {
         FSDataOutputStream fileStream = fs.create(tempOutputPath, false, bufferSize);
         var10000 = BoxesRunTime.unboxToBoolean(env.conf().get(org.apache.spark.internal.config.package$.MODULE$.CHECKPOINT_COMPRESS())) ? CompressionCodec$.MODULE$.createCodec(env.conf()).compressedOutputStream(fileStream) : fileStream;
      } else {
         var10000 = fs.create(tempOutputPath, false, bufferSize, fs.getDefaultReplication(fs.getWorkingDirectory()), (long)blockSize);
      }

      OutputStream fileOutputStream = (OutputStream)var10000;
      SerializerInstance serializer = env.serializer().newInstance();
      SerializationStream serializeStream = serializer.serializeStream(fileOutputStream);
      Utils$.MODULE$.tryWithSafeFinallyAndFailureCallbacks(() -> serializeStream.writeAll(iterator, evidence$3), (JFunction0.mcV.sp)() -> {
         boolean deleted = fs.delete(tempOutputPath, false);
         if (!deleted) {
            MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to delete tempOutputPath ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TEMP_OUTPUT_PATH..MODULE$, tempOutputPath)})))));
         }
      }, (JFunction0.mcV.sp)() -> serializeStream.close());
      if (!fs.rename(tempOutputPath, finalOutputPath)) {
         if (!fs.exists(finalOutputPath)) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting tempOutputPath ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TEMP_OUTPUT_PATH..MODULE$, tempOutputPath)})))));
            fs.delete(tempOutputPath, false);
            throw SparkCoreErrors$.MODULE$.checkpointFailedToSaveError(ctx.attemptNumber(), finalOutputPath);
         } else {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Final output path"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " already exists; not overwriting it"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FINAL_OUTPUT_PATH..MODULE$, finalOutputPath)}))))));
            if (!fs.delete(tempOutputPath, false)) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, tempOutputPath)})))));
            }
         }
      }
   }

   public int writePartitionToCheckpointFile$default$3() {
      return -1;
   }

   private void writePartitionerToCheckpointDir(final SparkContext sc, final Partitioner partitioner, final Path checkpointDirPath) {
      try {
         Path partitionerFilePath = new Path(checkpointDirPath, this.checkpointPartitionerFileName());
         int bufferSize = BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.BUFFER_SIZE()));
         FileSystem fs = partitionerFilePath.getFileSystem(sc.hadoopConfiguration());
         FSDataOutputStream fileOutputStream = fs.create(partitionerFilePath, false, bufferSize);
         SerializerInstance serializer = SparkEnv$.MODULE$.get().serializer().newInstance();
         SerializationStream serializeStream = serializer.serializeStream(fileOutputStream);
         Utils$.MODULE$.tryWithSafeFinally(() -> serializeStream.writeObject(partitioner, scala.reflect.ClassTag..MODULE$.apply(Partitioner.class)), (JFunction0.mcV.sp)() -> serializeStream.close());
         this.logDebug((Function0)(() -> "Written partitioner to " + partitionerFilePath));
      } catch (Throwable var14) {
         if (var14 == null || !scala.util.control.NonFatal..MODULE$.apply(var14)) {
            throw var14;
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error writing partitioner ", " to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PARTITIONER..MODULE$, partitioner)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, checkpointDirPath)}))))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public Option org$apache$spark$rdd$ReliableCheckpointRDD$$readCheckpointedPartitionerFile(final SparkContext sc, final String checkpointDirPath) {
      Object var10000;
      try {
         int bufferSize = BoxesRunTime.unboxToInt(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.BUFFER_SIZE()));
         Path partitionerFilePath = new Path(checkpointDirPath, this.checkpointPartitionerFileName());
         FileSystem fs = partitionerFilePath.getFileSystem(sc.hadoopConfiguration());
         FSDataInputStream fileInputStream = fs.open(partitionerFilePath, bufferSize);
         SerializerInstance serializer = SparkEnv$.MODULE$.get().serializer().newInstance();
         Partitioner partitioner = (Partitioner)Utils$.MODULE$.tryWithSafeFinally(() -> {
            DeserializationStream deserializeStream = serializer.deserializeStream(fileInputStream);
            return (Partitioner)Utils$.MODULE$.tryWithSafeFinally(() -> (Partitioner)deserializeStream.readObject(scala.reflect.ClassTag..MODULE$.apply(Partitioner.class)), (JFunction0.mcV.sp)() -> deserializeStream.close());
         }, (JFunction0.mcV.sp)() -> fileInputStream.close());
         this.logDebug((Function0)(() -> "Read partitioner from " + partitionerFilePath));
         var10000 = new Some(partitioner);
      } catch (Throwable var14) {
         if (var14 instanceof FileNotFoundException var12) {
            this.logDebug((Function0)(() -> "No partitioner file"), var12);
            var10000 = .MODULE$;
         } else {
            if (var14 == null || !scala.util.control.NonFatal..MODULE$.apply(var14)) {
               throw var14;
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error reading partitioner from ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, checkpointDirPath)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"partitioner will not be recovered which may lead to performance loss"})))).log(scala.collection.immutable.Nil..MODULE$))), var14);
            var10000 = .MODULE$;
         }
      }

      return (Option)var10000;
   }

   public Iterator readCheckpointFile(final Path path, final Broadcast broadcastedConf, final TaskContext context) {
      SparkEnv env = SparkEnv$.MODULE$.get();
      FileSystem fs = path.getFileSystem(((SerializableConfiguration)broadcastedConf.value()).value());
      int bufferSize = BoxesRunTime.unboxToInt(env.conf().get(org.apache.spark.internal.config.package$.MODULE$.BUFFER_SIZE()));
      FSDataInputStream fileStream = fs.open(path, bufferSize);
      InputStream fileInputStream = (InputStream)(BoxesRunTime.unboxToBoolean(env.conf().get(org.apache.spark.internal.config.package$.MODULE$.CHECKPOINT_COMPRESS())) ? CompressionCodec$.MODULE$.createCodec(env.conf()).compressedInputStream(fileStream) : fileStream);
      SerializerInstance serializer = env.serializer().newInstance();
      DeserializationStream deserializeStream = serializer.deserializeStream(fileInputStream);
      context.addTaskCompletionListener((Function1)((contextx) -> {
         $anonfun$readCheckpointFile$1(deserializeStream, contextx);
         return BoxedUnit.UNIT;
      }));
      return deserializeStream.asIterator();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ReliableCheckpointRDD$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$writeRDDToCheckpointDirectory$1(final String eta$0$1$1, final Broadcast broadcastedConf$1, final int eta$1$1$1, final ClassTag evidence$2$1, final TaskContext ctx, final Iterator iterator) {
      MODULE$.writePartitionToCheckpointFile(eta$0$1$1, broadcastedConf$1, eta$1$1$1, ctx, iterator, evidence$2$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$readCheckpointFile$1(final DeserializationStream deserializeStream$2, final TaskContext context) {
      deserializeStream$2.close();
   }

   private ReliableCheckpointRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
