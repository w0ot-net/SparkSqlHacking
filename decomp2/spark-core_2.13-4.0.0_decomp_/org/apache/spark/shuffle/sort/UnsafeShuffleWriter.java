package org.apache.spark.shuffle.sort;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import javax.annotation.Nullable;
import org.apache.spark.Partitioner;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.Private;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PATH.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.io.CompressionCodec;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.io.NioBufferedFileInputStream;
import org.apache.spark.memory.TaskMemoryManager;
import org.apache.spark.network.shuffle.checksum.ShuffleChecksumHelper;
import org.apache.spark.network.util.LimitedInputStream;
import org.apache.spark.scheduler.MapStatus;
import org.apache.spark.scheduler.MapStatus$;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;
import org.apache.spark.shuffle.ShuffleWriter;
import org.apache.spark.shuffle.api.ShuffleExecutorComponents;
import org.apache.spark.shuffle.api.ShuffleMapOutputWriter;
import org.apache.spark.shuffle.api.ShufflePartitionWriter;
import org.apache.spark.shuffle.api.SingleSpillShuffleMapOutputWriter;
import org.apache.spark.shuffle.api.WritableByteChannelWrapper;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.TimeTrackingOutputStream;
import org.apache.spark.unsafe.Platform;
import org.apache.spark.util.Utils;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.io.ByteStreams;
import org.sparkproject.guava.io.Closeables;
import scala.Option;
import scala.Product2;
import scala.jdk.javaapi.CollectionConverters;
import scala.reflect.ClassTag;

@Private
public class UnsafeShuffleWriter extends ShuffleWriter {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(UnsafeShuffleWriter.class);
   private static final ClassTag OBJECT_CLASS_TAG;
   @VisibleForTesting
   static final int DEFAULT_INITIAL_SER_BUFFER_SIZE = 1048576;
   private final BlockManager blockManager;
   private final TaskMemoryManager memoryManager;
   private final SerializerInstance serializer;
   private final Partitioner partitioner;
   private final ShuffleWriteMetricsReporter writeMetrics;
   private final ShuffleExecutorComponents shuffleExecutorComponents;
   private final int shuffleId;
   private final long mapId;
   private final TaskContext taskContext;
   private final SparkConf sparkConf;
   private final boolean transferToEnabled;
   private final int initialSortBufferSize;
   private final int mergeBufferSizeInBytes;
   @Nullable
   private MapStatus mapStatus;
   @Nullable
   private ShuffleExternalSorter sorter;
   @Nullable
   private long[] partitionLengths;
   private long peakMemoryUsedBytes = 0L;
   private MyByteArrayOutputStream serBuffer;
   private SerializationStream serOutputStream;
   private boolean stopping = false;

   public UnsafeShuffleWriter(BlockManager blockManager, TaskMemoryManager memoryManager, SerializedShuffleHandle handle, long mapId, TaskContext taskContext, SparkConf sparkConf, ShuffleWriteMetricsReporter writeMetrics, ShuffleExecutorComponents shuffleExecutorComponents) throws SparkException {
      int numPartitions = handle.dependency().partitioner().numPartitions();
      if (numPartitions > SortShuffleManager.MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE()) {
         throw new IllegalArgumentException("UnsafeShuffleWriter can only be used for shuffles with at most " + SortShuffleManager.MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE() + " reduce partitions");
      } else {
         this.blockManager = blockManager;
         this.memoryManager = memoryManager;
         this.mapId = mapId;
         ShuffleDependency<K, V, V> dep = handle.dependency();
         this.shuffleId = dep.shuffleId();
         this.serializer = dep.serializer().newInstance();
         this.partitioner = dep.partitioner();
         this.writeMetrics = writeMetrics;
         this.shuffleExecutorComponents = shuffleExecutorComponents;
         this.taskContext = taskContext;
         this.sparkConf = sparkConf;
         this.transferToEnabled = (Boolean)sparkConf.get(package$.MODULE$.SHUFFLE_MERGE_PREFER_NIO());
         this.initialSortBufferSize = (int)(Long)sparkConf.get(package$.MODULE$.SHUFFLE_SORT_INIT_BUFFER_SIZE());
         this.mergeBufferSizeInBytes = (int)(Long)sparkConf.get(package$.MODULE$.SHUFFLE_FILE_MERGE_BUFFER_SIZE()) * 1024;
         this.open();
      }
   }

   private void updatePeakMemoryUsed() {
      if (this.sorter != null) {
         long mem = this.sorter.getPeakMemoryUsedBytes();
         if (mem > this.peakMemoryUsedBytes) {
            this.peakMemoryUsedBytes = mem;
         }
      }

   }

   public long getPeakMemoryUsedBytes() {
      this.updatePeakMemoryUsed();
      return this.peakMemoryUsedBytes;
   }

   @VisibleForTesting
   public void write(Iterator records) throws IOException {
      this.write(CollectionConverters.asScala(records));
   }

   public void write(scala.collection.Iterator records) throws IOException {
      boolean success = false;

      try {
         while(records.hasNext()) {
            this.insertRecordIntoSorter((Product2)records.next());
         }

         this.closeAndWriteOutput();
         success = true;
      } finally {
         if (this.sorter != null) {
            try {
               this.sorter.cleanupResources();
            } catch (Exception e) {
               if (success) {
                  throw e;
               }

               logger.error("In addition to a failure during writing, we failed during cleanup.", e);
            }
         }

      }

   }

   private void open() throws SparkException {
      assert this.sorter == null;

      this.sorter = new ShuffleExternalSorter(this.memoryManager, this.blockManager, this.taskContext, this.initialSortBufferSize, this.partitioner.numPartitions(), this.sparkConf, this.writeMetrics);
      this.serBuffer = new MyByteArrayOutputStream(1048576);
      this.serOutputStream = this.serializer.serializeStream(this.serBuffer);
   }

   @VisibleForTesting
   void closeAndWriteOutput() throws IOException {
      assert this.sorter != null;

      this.updatePeakMemoryUsed();
      this.serBuffer = null;
      this.serOutputStream = null;
      SpillInfo[] spills = this.sorter.closeAndGetSpills();
      boolean var12 = false;

      try {
         var12 = true;
         this.partitionLengths = this.mergeSpills(spills);
         var12 = false;
      } finally {
         if (var12) {
            this.sorter = null;
            SpillInfo[] var7 = spills;
            int var8 = spills.length;
            int var9 = 0;

            while(true) {
               if (var9 >= var8) {
                  ;
               } else {
                  SpillInfo spill = var7[var9];
                  if (spill.file.exists() && !spill.file.delete()) {
                     logger.error("Error while deleting spill file {}", new MDC[]{MDC.of(.MODULE$, spill.file.getPath())});
                  }

                  ++var9;
               }
            }
         }
      }

      this.sorter = null;

      for(SpillInfo spill : spills) {
         if (spill.file.exists() && !spill.file.delete()) {
            logger.error("Error while deleting spill file {}", new MDC[]{MDC.of(.MODULE$, spill.file.getPath())});
         }
      }

      this.mapStatus = MapStatus$.MODULE$.apply(this.blockManager.shuffleServerId(), this.partitionLengths, this.mapId);
   }

   @VisibleForTesting
   void insertRecordIntoSorter(Product2 record) throws IOException {
      assert this.sorter != null;

      K key = (K)record._1();
      int partitionId = this.partitioner.getPartition(key);
      this.serBuffer.reset();
      this.serOutputStream.writeKey(key, OBJECT_CLASS_TAG);
      this.serOutputStream.writeValue(record._2(), OBJECT_CLASS_TAG);
      this.serOutputStream.flush();
      int serializedRecordSize = this.serBuffer.size();

      assert serializedRecordSize > 0;

      this.sorter.insertRecord(this.serBuffer.getBuf(), (long)Platform.BYTE_ARRAY_OFFSET, serializedRecordSize, partitionId);
   }

   @VisibleForTesting
   void forceSorterToSpill() throws IOException {
      assert this.sorter != null;

      this.sorter.spill();
   }

   private long[] mergeSpills(SpillInfo[] spills) throws IOException {
      if (spills.length == 0) {
         ShuffleMapOutputWriter mapWriter = this.shuffleExecutorComponents.createMapOutputWriter(this.shuffleId, this.mapId, this.partitioner.numPartitions());
         return mapWriter.commitAllPartitions(ShuffleChecksumHelper.EMPTY_CHECKSUM_VALUE).getPartitionLengths();
      } else {
         long[] partitionLengths;
         if (spills.length == 1) {
            Optional<SingleSpillShuffleMapOutputWriter> maybeSingleFileWriter = this.shuffleExecutorComponents.createSingleFileMapOutputWriter(this.shuffleId, this.mapId);
            if (maybeSingleFileWriter.isPresent()) {
               partitionLengths = spills[0].partitionLengths;
               logger.debug("Merge shuffle spills for mapId {} with length {}", this.mapId, partitionLengths.length);
               ((SingleSpillShuffleMapOutputWriter)maybeSingleFileWriter.get()).transferMapSpillFile(spills[0].file, partitionLengths, this.sorter.getChecksums());
            } else {
               partitionLengths = this.mergeSpillsUsingStandardWriter(spills);
            }
         } else {
            partitionLengths = this.mergeSpillsUsingStandardWriter(spills);
         }

         return partitionLengths;
      }
   }

   private long[] mergeSpillsUsingStandardWriter(SpillInfo[] spills) throws IOException {
      boolean compressionEnabled = (Boolean)this.sparkConf.get(package$.MODULE$.SHUFFLE_COMPRESS());
      CompressionCodec compressionCodec = CompressionCodec$.MODULE$.createCodec(this.sparkConf);
      boolean fastMergeEnabled = (Boolean)this.sparkConf.get(package$.MODULE$.SHUFFLE_UNSAFE_FAST_MERGE_ENABLE());
      boolean fastMergeIsSupported = !compressionEnabled || CompressionCodec$.MODULE$.supportsConcatenationOfSerializedStreams(compressionCodec);
      boolean encryptionEnabled = this.blockManager.serializerManager().encryptionEnabled();
      ShuffleMapOutputWriter mapWriter = this.shuffleExecutorComponents.createMapOutputWriter(this.shuffleId, this.mapId, this.partitioner.numPartitions());

      try {
         if (fastMergeEnabled && fastMergeIsSupported) {
            if (this.transferToEnabled && !encryptionEnabled) {
               logger.debug("Using transferTo-based fast merge");
               this.mergeSpillsWithTransferTo(spills, mapWriter);
            } else {
               logger.debug("Using fileStream-based fast merge");
               this.mergeSpillsWithFileStream(spills, mapWriter, (CompressionCodec)null);
            }
         } else {
            logger.debug("Using slow merge");
            this.mergeSpillsWithFileStream(spills, mapWriter, compressionCodec);
         }

         long[] partitionLengths = mapWriter.commitAllPartitions(this.sorter.getChecksums()).getPartitionLengths();
         return partitionLengths;
      } catch (Exception var12) {
         Exception e = var12;

         try {
            mapWriter.abort(e);
         } catch (Exception e2) {
            logger.warn("Failed to abort writing the map output.", e2);
            var12.addSuppressed(e2);
         }

         throw var12;
      }
   }

   private void mergeSpillsWithFileStream(SpillInfo[] spills, ShuffleMapOutputWriter mapWriter, @Nullable CompressionCodec compressionCodec) throws IOException {
      logger.debug("Merge shuffle spills with FileStream for mapId {}", this.mapId);
      int numPartitions = this.partitioner.numPartitions();
      InputStream[] spillInputStreams = new InputStream[spills.length];
      boolean threwException = true;
      boolean var26 = false;

      try {
         var26 = true;

         for(int i = 0; i < spills.length; ++i) {
            spillInputStreams[i] = new NioBufferedFileInputStream(spills[i].file, this.mergeBufferSizeInBytes);
            if (logger.isDebugEnabled()) {
               logger.debug("Partition lengths for mapId {} in Spill {}: {}", new Object[]{this.mapId, i, Arrays.toString(spills[i].partitionLengths)});
            }
         }

         for(int partition = 0; partition < numPartitions; ++partition) {
            boolean copyThrewException = true;
            ShufflePartitionWriter writer = mapWriter.getPartitionWriter(partition);
            OutputStream partitionOutput = writer.openStream();

            try {
               OutputStream stream = new TimeTrackingOutputStream(this.writeMetrics, partitionOutput);
               partitionOutput = this.blockManager.serializerManager().wrapForEncryption((OutputStream)stream);
               if (compressionCodec != null) {
                  partitionOutput = compressionCodec.compressedOutputStream(partitionOutput);
               }

               for(int i = 0; i < spills.length; ++i) {
                  long partitionLengthInSpill = spills[i].partitionLengths[partition];
                  if (partitionLengthInSpill > 0L) {
                     InputStream partitionInputStream = null;
                     boolean copySpillThrewException = true;

                     try {
                        InputStream var43 = new LimitedInputStream(spillInputStreams[i], partitionLengthInSpill, false);
                        partitionInputStream = this.blockManager.serializerManager().wrapForEncryption((InputStream)var43);
                        if (compressionCodec != null) {
                           partitionInputStream = compressionCodec.compressedInputStream(partitionInputStream);
                        }

                        ByteStreams.copy(partitionInputStream, partitionOutput);
                        copySpillThrewException = false;
                     } finally {
                        Closeables.close(partitionInputStream, copySpillThrewException);
                     }
                  }
               }

               copyThrewException = false;
            } finally {
               Closeables.close(partitionOutput, copyThrewException);
            }

            long var42 = writer.getNumBytesWritten();
            this.writeMetrics.incBytesWritten(var42);
         }

         threwException = false;
         var26 = false;
      } finally {
         if (var26) {
            for(InputStream stream : spillInputStreams) {
               Closeables.close(stream, threwException);
            }

         }
      }

      for(InputStream stream : spillInputStreams) {
         Closeables.close(stream, threwException);
      }

   }

   private void mergeSpillsWithTransferTo(SpillInfo[] spills, ShuffleMapOutputWriter mapWriter) throws IOException {
      logger.debug("Merge shuffle spills with TransferTo for mapId {}", this.mapId);
      int numPartitions = this.partitioner.numPartitions();
      FileChannel[] spillInputChannels = new FileChannel[spills.length];
      long[] spillInputChannelPositions = new long[spills.length];
      boolean threwException = true;
      boolean var22 = false;

      try {
         var22 = true;

         for(int i = 0; i < spills.length; ++i) {
            spillInputChannels[i] = (new FileInputStream(spills[i].file)).getChannel();
            if (logger.isDebugEnabled()) {
               logger.debug("Partition lengths for mapId {} in Spill {}: {}", new Object[]{this.mapId, i, Arrays.toString(spills[i].partitionLengths)});
            }
         }

         for(int partition = 0; partition < numPartitions; ++partition) {
            boolean copyThrewException = true;
            ShufflePartitionWriter writer = mapWriter.getPartitionWriter(partition);
            WritableByteChannelWrapper resolvedChannel = (WritableByteChannelWrapper)writer.openChannelWrapper().orElseGet(() -> new StreamFallbackChannelWrapper(openStreamUnchecked(writer)));

            try {
               for(int i = 0; i < spills.length; ++i) {
                  long partitionLengthInSpill = spills[i].partitionLengths[partition];
                  FileChannel spillInputChannel = spillInputChannels[i];
                  long writeStartTime = System.nanoTime();
                  Utils.copyFileStreamNIO(spillInputChannel, resolvedChannel.channel(), spillInputChannelPositions[i], partitionLengthInSpill);
                  copyThrewException = false;
                  spillInputChannelPositions[i] += partitionLengthInSpill;
                  this.writeMetrics.incWriteTime(System.nanoTime() - writeStartTime);
               }
            } finally {
               Closeables.close(resolvedChannel, copyThrewException);
            }

            long var29 = writer.getNumBytesWritten();
            this.writeMetrics.incBytesWritten(var29);
         }

         threwException = false;
         var22 = false;
      } finally {
         if (var22) {
            int i = 0;

            while(true) {
               if (i >= spills.length) {
                  ;
               } else {
                  assert spillInputChannelPositions[i] == spills[i].file.length();

                  Closeables.close(spillInputChannels[i], threwException);
                  ++i;
               }
            }
         }
      }

      for(int i = 0; i < spills.length; ++i) {
         assert spillInputChannelPositions[i] == spills[i].file.length();

         Closeables.close(spillInputChannels[i], threwException);
      }

   }

   public Option stop(boolean success) {
      Option var2;
      try {
         this.taskContext.taskMetrics().incPeakExecutionMemory(this.getPeakMemoryUsedBytes());
         if (!this.stopping) {
            this.stopping = true;
            if (!success) {
               var2 = Option.apply((Object)null);
               return var2;
            }

            if (this.mapStatus == null) {
               throw new IllegalStateException("Cannot call stop(true) without having called write()");
            }

            var2 = Option.apply(this.mapStatus);
            return var2;
         }

         var2 = Option.apply((Object)null);
      } finally {
         if (this.sorter != null) {
            this.sorter.cleanupResources();
         }

      }

      return var2;
   }

   private static OutputStream openStreamUnchecked(ShufflePartitionWriter writer) {
      try {
         return writer.openStream();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public long[] getPartitionLengths() {
      return this.partitionLengths;
   }

   static {
      OBJECT_CLASS_TAG = scala.reflect.ClassTag..MODULE$.Object();
   }

   private static final class MyByteArrayOutputStream extends ByteArrayOutputStream {
      MyByteArrayOutputStream(int size) {
         super(size);
      }

      public byte[] getBuf() {
         return this.buf;
      }
   }

   private static final class StreamFallbackChannelWrapper implements WritableByteChannelWrapper {
      private final WritableByteChannel channel;

      StreamFallbackChannelWrapper(OutputStream fallbackStream) {
         this.channel = Channels.newChannel(fallbackStream);
      }

      public WritableByteChannel channel() {
         return this.channel;
      }

      public void close() throws IOException {
         this.channel.close();
      }
   }
}
