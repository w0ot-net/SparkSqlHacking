package org.apache.spark.shuffle.sort;

import java.util.Map;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.shuffle.ShuffleDataIOUtils$;
import org.apache.spark.shuffle.api.ShuffleExecutorComponents;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;

public final class SortShuffleManager$ implements Logging {
   public static final SortShuffleManager$ MODULE$ = new SortShuffleManager$();
   private static final int MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE;
   private static final String FETCH_SHUFFLE_BLOCKS_IN_BATCH_ENABLED_KEY;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE = PackedRecordPointer.MAXIMUM_PARTITION_ID + 1;
      FETCH_SHUFFLE_BLOCKS_IN_BATCH_ENABLED_KEY = "__fetch_continuous_blocks_in_batch_enabled";
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

   public int MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE() {
      return MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE;
   }

   public String FETCH_SHUFFLE_BLOCKS_IN_BATCH_ENABLED_KEY() {
      return FETCH_SHUFFLE_BLOCKS_IN_BATCH_ENABLED_KEY;
   }

   public boolean canUseBatchFetch(final int startPartition, final int endPartition, final TaskContext context) {
      boolean var6;
      label33: {
         boolean fetchMultiPartitions = endPartition - startPartition > 1;
         if (fetchMultiPartitions) {
            String var10000 = context.getLocalProperty(this.FETCH_SHUFFLE_BLOCKS_IN_BATCH_ENABLED_KEY());
            String var5 = "true";
            if (var10000 == null) {
               if (var5 == null) {
                  break label33;
               }
            } else if (var10000.equals(var5)) {
               break label33;
            }
         }

         var6 = false;
         return var6;
      }

      var6 = true;
      return var6;
   }

   public boolean canUseSerializedShuffle(final ShuffleDependency dependency) {
      int shufId = dependency.shuffleId();
      int numPartitions = dependency.partitioner().numPartitions();
      if (!dependency.serializer().supportsRelocationOfSerializedObjects()) {
         this.log().debug("Can't use serialized shuffle for shuffle " + shufId + " because the serializer, " + dependency.serializer().getClass().getName() + ", does not support object relocation");
         return false;
      } else if (dependency.mapSideCombine()) {
         this.log().debug("Can't use serialized shuffle for shuffle " + shufId + " because we need to do map-side aggregation");
         return false;
      } else if (numPartitions > this.MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE()) {
         this.log().debug("Can't use serialized shuffle for shuffle " + shufId + " because it has more than " + this.MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE() + " partitions");
         return false;
      } else {
         this.log().debug("Can use serialized shuffle for shuffle " + shufId);
         return true;
      }
   }

   public ShuffleExecutorComponents org$apache$spark$shuffle$sort$SortShuffleManager$$loadShuffleExecutorComponents(final SparkConf conf) {
      ShuffleExecutorComponents executorComponents = ShuffleDataIOUtils$.MODULE$.loadShuffleDataIO(conf).executor();
      scala.collection.immutable.Map extraConfigs = .MODULE$.wrapRefArray((Object[])conf.getAllWithPrefix(ShuffleDataIOUtils$.MODULE$.SHUFFLE_SPARK_CONF_PREFIX())).toMap(scala..less.colon.less..MODULE$.refl());
      executorComponents.initializeExecutor(conf.getAppId(), SparkEnv$.MODULE$.get().executorId(), scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(extraConfigs).asJava());
      return executorComponents;
   }

   private SortShuffleManager$() {
   }
}
