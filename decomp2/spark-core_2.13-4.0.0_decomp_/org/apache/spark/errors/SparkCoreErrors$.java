package org.apache.spark.errors;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkException;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.SparkRuntimeException;
import org.apache.spark.SparkUnsupportedOperationException;
import org.apache.spark.TaskNotSerializableException;
import org.apache.spark.internal.config.package$;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.memory.SparkOutOfMemoryError;
import org.apache.spark.scheduler.BarrierJobRunWithDynamicAllocationException;
import org.apache.spark.scheduler.BarrierJobSlotsNumberCheckFailed;
import org.apache.spark.scheduler.BarrierJobUnsupportedRDDChainException;
import org.apache.spark.shuffle.FetchFailedException;
import org.apache.spark.shuffle.ShuffleManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.BlockNotFoundException;
import org.apache.spark.storage.BlockSavedOnDecommissionedBlockManagerException;
import org.apache.spark.storage.RDDBlockId;
import org.apache.spark.storage.UnrecognizedBlockId;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;

public final class SparkCoreErrors$ {
   public static final SparkCoreErrors$ MODULE$ = new SparkCoreErrors$();

   public Throwable unexpectedPy4JServerError(final Object other) {
      return new SparkRuntimeException("_LEGACY_ERROR_TEMP_3000", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("class"), String.valueOf(other.getClass()))}))), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkRuntimeException..MODULE$.$lessinit$greater$default$5());
   }

   public Throwable eofExceptionWhileReadPortNumberError(final String daemonModule, final Option daemonExitValue) {
      return new SparkException("_LEGACY_ERROR_TEMP_3001", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("daemonModule"), daemonModule), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("additionalMessage"), daemonExitValue.map((v) -> $anonfun$eofExceptionWhileReadPortNumberError$1(BoxesRunTime.unboxToInt(v))).getOrElse(() -> ""))}))), (Throwable)null);
   }

   public Option eofExceptionWhileReadPortNumberError$default$2() {
      return scala.None..MODULE$;
   }

   public Throwable unsupportedDataTypeError(final Object other) {
      return new SparkException("_LEGACY_ERROR_TEMP_3002", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("other"), String.valueOf(other))}))), (Throwable)null);
   }

   public Throwable rddBlockNotFoundError(final BlockId blockId, final int id) {
      return new SparkException("_LEGACY_ERROR_TEMP_3003", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockId"), String.valueOf(blockId)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("id"), String.valueOf(BoxesRunTime.boxToInteger(id)))}))), (Throwable)null);
   }

   public Throwable blockHaveBeenRemovedError(final String string) {
      return new SparkException("_LEGACY_ERROR_TEMP_3004", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("string"), string)}))), (Throwable)null);
   }

   public Throwable histogramOnEmptyRDDOrContainingInfinityOrNaNError() {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_3005");
   }

   public Throwable emptyRDDError() {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_3006");
   }

   public Throwable pathNotSupportedError(final String path) {
      return new IOException("Path: " + path + " is a directory, which is not supported by the record reader when `mapreduce.input.fileinputformat.input.dir.recursive` is false.");
   }

   public Throwable checkpointRDDBlockIdNotFoundError(final RDDBlockId rddBlockId) {
      return new SparkException("CHECKPOINT_RDD_BLOCK_ID_NOT_FOUND", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("rddBlockId"), String.valueOf(rddBlockId))}))), (Throwable)null);
   }

   public Throwable endOfStreamError() {
      return new NoSuchElementException("End of stream");
   }

   public Throwable cannotUseMapSideCombiningWithArrayKeyError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3008", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable hashPartitionerCannotPartitionArrayKeyError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3009", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable reduceByKeyLocallyNotSupportArrayKeysError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3010", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable rddLacksSparkContextError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3011", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable cannotChangeStorageLevelError() {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_3012");
   }

   public Throwable canOnlyZipRDDsWithSamePartitionSizeError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3013", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable emptyCollectionError() {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_3014");
   }

   public Throwable countByValueApproxNotSupportArraysError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3015", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable checkpointDirectoryHasNotBeenSetInSparkContextError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3016", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable invalidCheckpointFileError(final Path path) {
      return new SparkException("_LEGACY_ERROR_TEMP_3017", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("path"), String.valueOf(path))}))), (Throwable)null);
   }

   public Throwable failToCreateCheckpointPathError(final Path checkpointDirPath) {
      return new SparkException("_LEGACY_ERROR_TEMP_3018", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("checkpointDirPath"), String.valueOf(checkpointDirPath))}))), (Throwable)null);
   }

   public Throwable checkpointRDDHasDifferentNumberOfPartitionsFromOriginalRDDError(final int originalRDDId, final int originalRDDLength, final int newRDDId, final int newRDDLength) {
      return new SparkException("_LEGACY_ERROR_TEMP_3019", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("originalRDDId"), String.valueOf(BoxesRunTime.boxToInteger(originalRDDId))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("originalRDDLength"), String.valueOf(BoxesRunTime.boxToInteger(originalRDDLength))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("newRDDId"), String.valueOf(BoxesRunTime.boxToInteger(newRDDId))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("newRDDLength"), String.valueOf(BoxesRunTime.boxToInteger(newRDDLength)))}))), (Throwable)null);
   }

   public Throwable checkpointFailedToSaveError(final int task, final Path path) {
      return new IOException("Checkpoint failed: failed to save output of task: " + task + " and final output path does not exist: " + path);
   }

   public Throwable mustSpecifyCheckpointDirError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3020", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable askStandaloneSchedulerToShutDownExecutorsError(final Exception e) {
      return new SparkException("_LEGACY_ERROR_TEMP_3021", .MODULE$.Map().empty(), e);
   }

   public Throwable stopStandaloneSchedulerDriverEndpointError(final Exception e) {
      return new SparkException("_LEGACY_ERROR_TEMP_3022", .MODULE$.Map().empty(), e);
   }

   public Throwable noExecutorIdleError(final String id) {
      return new NoSuchElementException(id);
   }

   public SparkException sparkJobCancelled(final int jobId, final String reason, final Exception e) {
      return new SparkException("SPARK_JOB_CANCELLED", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("jobId"), Integer.toString(jobId)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("reason"), reason)}))), e);
   }

   public SparkException sparkJobCancelledAsPartOfJobGroupError(final int jobId, final String jobGroupId) {
      return this.sparkJobCancelled(jobId, "part of cancelled job group " + jobGroupId, (Exception)null);
   }

   public Throwable barrierStageWithRDDChainPatternError() {
      return new BarrierJobUnsupportedRDDChainException();
   }

   public Throwable barrierStageWithDynamicAllocationError() {
      return new BarrierJobRunWithDynamicAllocationException();
   }

   public Throwable numPartitionsGreaterThanMaxNumConcurrentTasksError(final int numPartitions, final int maxNumConcurrentTasks) {
      return new BarrierJobSlotsNumberCheckFailed(numPartitions, maxNumConcurrentTasks);
   }

   public Throwable cannotRunSubmitMapStageOnZeroPartitionRDDError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3023", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable accessNonExistentAccumulatorError(final long id) {
      return new SparkException("_LEGACY_ERROR_TEMP_3024", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("id"), String.valueOf(BoxesRunTime.boxToLong(id)))}))), (Throwable)null);
   }

   public Throwable sendResubmittedTaskStatusForShuffleMapStagesOnlyError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3025", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable nonEmptyEventQueueAfterTimeoutError(final long timeoutMillis) {
      return new TimeoutException("The event queue is not empty after " + timeoutMillis + " ms.");
   }

   public Throwable durationCalledOnUnfinishedTaskError() {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_3026");
   }

   public Throwable unrecognizedSchedulerModePropertyError(final String schedulerModeProperty, final String schedulingModeConf) {
      return new SparkException("_LEGACY_ERROR_TEMP_3027", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("schedulerModeProperty"), schedulerModeProperty), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("schedulingModeConf"), schedulingModeConf)}))), (Throwable)null);
   }

   public Throwable sparkError(final String errorMsg) {
      return new SparkException("_LEGACY_ERROR_TEMP_3028", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("errorMsg"), errorMsg)}))), (Throwable)null);
   }

   public Throwable clusterSchedulerError(final String message) {
      return new SparkException("_LEGACY_ERROR_TEMP_3029", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("message"), message)}))), (Throwable)null);
   }

   public Throwable failToSerializeTaskError(final Throwable e) {
      return new TaskNotSerializableException(e);
   }

   public Throwable unrecognizedBlockIdError(final String name) {
      return new UnrecognizedBlockId(name);
   }

   public Throwable taskHasNotLockedBlockError(final long currentTaskAttemptId, final BlockId blockId) {
      return new SparkException("_LEGACY_ERROR_TEMP_3030", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("currentTaskAttemptId"), String.valueOf(BoxesRunTime.boxToLong(currentTaskAttemptId))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockId"), String.valueOf(blockId))}))), (Throwable)null);
   }

   public Throwable blockDoesNotExistError(final BlockId blockId) {
      return new SparkException("_LEGACY_ERROR_TEMP_3031", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockId"), String.valueOf(blockId))}))), (Throwable)null);
   }

   public Throwable cannotSaveBlockOnDecommissionedExecutorError(final BlockId blockId) {
      return new BlockSavedOnDecommissionedBlockManagerException(blockId);
   }

   public Throwable waitingForReplicationToFinishError(final Throwable e) {
      return new SparkException("_LEGACY_ERROR_TEMP_3032", .MODULE$.Map().empty(), e);
   }

   public Throwable unableToRegisterWithExternalShuffleServerError(final Throwable e) {
      return new SparkException("_LEGACY_ERROR_TEMP_3033", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("message"), e.getMessage())}))), e);
   }

   public Throwable waitingForAsyncReregistrationError(final Throwable e) {
      return new SparkException("_LEGACY_ERROR_TEMP_3034", .MODULE$.Map().empty(), e);
   }

   public Throwable unexpectedShuffleBlockWithUnsupportedResolverError(final ShuffleManager shuffleManager, final BlockId blockId) {
      return new SparkException("_LEGACY_ERROR_TEMP_3035", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockId"), String.valueOf(blockId)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("shuffleBlockResolver"), String.valueOf(shuffleManager.shuffleBlockResolver()))}))), (Throwable)null);
   }

   public Throwable failToStoreBlockOnBlockManagerError(final BlockManagerId blockManagerId, final BlockId blockId) {
      return new SparkException("_LEGACY_ERROR_TEMP_3036", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockId"), String.valueOf(blockId)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockManagerId"), String.valueOf(blockManagerId))}))), (Throwable)null);
   }

   public Throwable readLockedBlockNotFoundError(final BlockId blockId) {
      return new SparkException("_LEGACY_ERROR_TEMP_3037", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockId"), String.valueOf(blockId))}))), (Throwable)null);
   }

   public Throwable failToGetBlockWithLockError(final BlockId blockId) {
      return new SparkException("_LEGACY_ERROR_TEMP_3038", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockId"), String.valueOf(blockId))}))), (Throwable)null);
   }

   public Throwable blockNotFoundError(final BlockId blockId) {
      return new BlockNotFoundException(blockId.toString());
   }

   public Throwable interruptedError() {
      return new InterruptedException();
   }

   public Throwable blockStatusQueryReturnedNullError(final BlockId blockId) {
      return new SparkException("_LEGACY_ERROR_TEMP_3039", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockId"), String.valueOf(blockId))}))), (Throwable)null);
   }

   public Throwable unexpectedBlockManagerMasterEndpointResultError() {
      return new SparkException("_LEGACY_ERROR_TEMP_3040", .MODULE$.Map().empty(), (Throwable)null);
   }

   public Throwable failToCreateDirectoryError(final String path, final int maxAttempts) {
      return new IOException("Failed to create directory " + path + " with permission 770 after " + maxAttempts + " attempts!");
   }

   public Throwable unsupportedOperationError() {
      return new SparkUnsupportedOperationException("_LEGACY_ERROR_TEMP_3041");
   }

   public Throwable noSuchElementError() {
      return new NoSuchElementException();
   }

   public Throwable fetchFailedError(final BlockManagerId bmAddress, final int shuffleId, final long mapId, final int mapIndex, final int reduceId, final String message, final Throwable cause) {
      return new FetchFailedException(bmAddress, shuffleId, mapId, mapIndex, reduceId, message, cause);
   }

   public Throwable fetchFailedError$default$7() {
      return null;
   }

   public Throwable failToGetNonShuffleBlockError(final BlockId blockId, final Throwable e) {
      return new SparkException("_LEGACY_ERROR_TEMP_3042", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("blockId"), String.valueOf(blockId))}))), e);
   }

   public Throwable graphiteSinkInvalidProtocolError(final String invalidProtocol) {
      return new SparkException("GRAPHITE_SINK_INVALID_PROTOCOL", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("protocol"), invalidProtocol)}))), (Throwable)null);
   }

   public Throwable graphiteSinkPropertyMissingError(final String missingProperty) {
      return new SparkException("GRAPHITE_SINK_PROPERTY_MISSING", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("property"), missingProperty)}))), (Throwable)null);
   }

   public OutOfMemoryError outOfMemoryError(final long requestedBytes, final long receivedBytes) {
      return new SparkOutOfMemoryError("UNABLE_TO_ACQUIRE_MEMORY", scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("requestedBytes"), Long.toString(requestedBytes)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("receivedBytes"), Long.toString(receivedBytes))})))).asJava());
   }

   public Throwable failedRenameTempFileError(final File srcFile, final File dstFile) {
      return new SparkException("FAILED_RENAME_TEMP_FILE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("srcPath"), srcFile.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("dstPath"), dstFile.toString())}))), (Throwable)null);
   }

   public Throwable addLocalDirectoryError(final Path path) {
      return new SparkException("UNSUPPORTED_ADD_FILE.LOCAL_DIRECTORY", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("path"), path.toString())}))), (Throwable)null);
   }

   public Throwable addDirectoryError(final Path path) {
      return new SparkException("UNSUPPORTED_ADD_FILE.DIRECTORY", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("path"), path.toString())}))), (Throwable)null);
   }

   public Throwable codecNotAvailableError(final String codecName) {
      return new SparkIllegalArgumentException("CODEC_NOT_AVAILABLE.WITH_CONF_SUGGESTION", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("codecName"), codecName), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("configKey"), this.toConf(package$.MODULE$.IO_COMPRESSION_CODEC().key())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("configVal"), this.toConfVal(CompressionCodec$.MODULE$.FALLBACK_COMPRESSION_CODEC()))}))));
   }

   public Throwable tooManyArrayElementsError(final long numElements, final int maxRoundedArrayLength) {
      return new SparkIllegalArgumentException("COLLECTION_SIZE_LIMIT_EXCEEDED.INITIALIZE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("numberOfElements"), Long.toString(numElements)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("maxRoundedArrayLength"), Integer.toString(maxRoundedArrayLength))}))));
   }

   private String quoteByDefault(final String elem) {
      return "\"" + elem + "\"";
   }

   public String toConf(final String conf) {
      return this.quoteByDefault(conf);
   }

   public String toConfVal(final String conf) {
      return this.quoteByDefault(conf);
   }

   // $FF: synthetic method
   public static final String $anonfun$eofExceptionWhileReadPortNumberError$1(final int v) {
      return " and terminated with code: " + v + ".";
   }

   private SparkCoreErrors$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
