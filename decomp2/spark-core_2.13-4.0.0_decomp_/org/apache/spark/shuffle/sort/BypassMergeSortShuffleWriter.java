package org.apache.spark.shuffle.sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Optional;
import java.util.zip.Checksum;
import javax.annotation.Nullable;
import org.apache.spark.Partitioner;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PARTITION_ID.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.shuffle.checksum.ShuffleChecksumHelper;
import org.apache.spark.scheduler.MapStatus;
import org.apache.spark.scheduler.MapStatus$;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;
import org.apache.spark.shuffle.ShuffleWriter;
import org.apache.spark.shuffle.api.ShuffleExecutorComponents;
import org.apache.spark.shuffle.api.ShuffleMapOutputWriter;
import org.apache.spark.shuffle.api.ShufflePartitionWriter;
import org.apache.spark.shuffle.api.WritableByteChannelWrapper;
import org.apache.spark.shuffle.checksum.ShuffleChecksumSupport;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.DiskBlockObjectWriter;
import org.apache.spark.storage.FileSegment;
import org.apache.spark.storage.TempShuffleBlockId;
import org.apache.spark.util.Utils;
import org.sparkproject.guava.io.Closeables;
import scala.Option;
import scala.Product2;
import scala.Tuple2;
import scala.collection.Iterator;

final class BypassMergeSortShuffleWriter extends ShuffleWriter implements ShuffleChecksumSupport {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(BypassMergeSortShuffleWriter.class);
   private final int fileBufferSize;
   private final boolean transferToEnabled;
   private final int numPartitions;
   private final BlockManager blockManager;
   private final Partitioner partitioner;
   private final ShuffleWriteMetricsReporter writeMetrics;
   private final int shuffleId;
   private final long mapId;
   private final Serializer serializer;
   private final ShuffleExecutorComponents shuffleExecutorComponents;
   private DiskBlockObjectWriter[] partitionWriters;
   private FileSegment[] partitionWriterSegments;
   @Nullable
   private MapStatus mapStatus;
   private long[] partitionLengths;
   private final Checksum[] partitionChecksums;
   private boolean stopping = false;

   BypassMergeSortShuffleWriter(BlockManager blockManager, BypassMergeSortShuffleHandle handle, long mapId, SparkConf conf, ShuffleWriteMetricsReporter writeMetrics, ShuffleExecutorComponents shuffleExecutorComponents) throws SparkException {
      this.fileBufferSize = (int)(Long)conf.get(package$.MODULE$.SHUFFLE_FILE_BUFFER_SIZE()) * 1024;
      this.transferToEnabled = (Boolean)conf.get(package$.MODULE$.SHUFFLE_MERGE_PREFER_NIO());
      this.blockManager = blockManager;
      ShuffleDependency<K, V, V> dep = handle.dependency();
      this.mapId = mapId;
      this.shuffleId = dep.shuffleId();
      this.partitioner = dep.partitioner();
      this.numPartitions = this.partitioner.numPartitions();
      this.writeMetrics = writeMetrics;
      this.serializer = dep.serializer();
      this.shuffleExecutorComponents = shuffleExecutorComponents;
      this.partitionChecksums = this.createPartitionChecksums(this.numPartitions, conf);
   }

   public void write(Iterator records) throws IOException {
      assert this.partitionWriters == null;

      ShuffleMapOutputWriter mapOutputWriter = this.shuffleExecutorComponents.createMapOutputWriter(this.shuffleId, this.mapId, this.numPartitions);

      try {
         if (!records.hasNext()) {
            this.partitionLengths = mapOutputWriter.commitAllPartitions(ShuffleChecksumHelper.EMPTY_CHECKSUM_VALUE).getPartitionLengths();
            this.mapStatus = MapStatus$.MODULE$.apply(this.blockManager.shuffleServerId(), this.partitionLengths, this.mapId);
         } else {
            SerializerInstance serInstance = this.serializer.newInstance();
            long openStartTime = System.nanoTime();
            this.partitionWriters = new DiskBlockObjectWriter[this.numPartitions];
            this.partitionWriterSegments = new FileSegment[this.numPartitions];

            for(int i = 0; i < this.numPartitions; ++i) {
               Tuple2<TempShuffleBlockId, File> tempShuffleBlockIdPlusFile = this.blockManager.diskBlockManager().createTempShuffleBlock();
               File file = (File)tempShuffleBlockIdPlusFile._2();
               BlockId blockId = (BlockId)tempShuffleBlockIdPlusFile._1();
               DiskBlockObjectWriter writer = this.blockManager.getDiskWriter(blockId, file, serInstance, this.fileBufferSize, this.writeMetrics);
               if (this.partitionChecksums.length > 0) {
                  writer.setChecksum(this.partitionChecksums[i]);
               }

               this.partitionWriters[i] = writer;
            }

            this.writeMetrics.incWriteTime(System.nanoTime() - openStartTime);

            while(records.hasNext()) {
               Product2<K, V> record = (Product2)records.next();
               K key = (K)record._1();
               this.partitionWriters[this.partitioner.getPartition(key)].write(key, record._2());
            }

            for(int i = 0; i < this.numPartitions; ++i) {
               DiskBlockObjectWriter writer = this.partitionWriters[i];

               try {
                  this.partitionWriterSegments[i] = writer.commitAndGet();
               } catch (Throwable var13) {
                  if (writer != null) {
                     try {
                        writer.close();
                     } catch (Throwable var12) {
                        var13.addSuppressed(var12);
                     }
                  }

                  throw var13;
               }

               if (writer != null) {
                  writer.close();
               }
            }

            this.partitionLengths = this.writePartitionedData(mapOutputWriter);
            this.mapStatus = MapStatus$.MODULE$.apply(this.blockManager.shuffleServerId(), this.partitionLengths, this.mapId);
         }
      } catch (Exception var14) {
         Exception e = var14;

         try {
            mapOutputWriter.abort(e);
         } catch (Exception e2) {
            logger.error("Failed to abort the writer after failing to write map output.", e2);
            var14.addSuppressed(e2);
         }

         throw var14;
      }
   }

   public long[] getPartitionLengths() {
      return this.partitionLengths;
   }

   private long[] writePartitionedData(ShuffleMapOutputWriter mapOutputWriter) throws IOException {
      if (this.partitionWriters != null) {
         long writeStartTime = System.nanoTime();

         try {
            for(int i = 0; i < this.numPartitions; ++i) {
               File file = this.partitionWriterSegments[i].file();
               ShufflePartitionWriter writer = mapOutputWriter.getPartitionWriter(i);
               if (file.exists()) {
                  if (this.transferToEnabled) {
                     Optional<WritableByteChannelWrapper> maybeOutputChannel = writer.openChannelWrapper();
                     if (maybeOutputChannel.isPresent()) {
                        this.writePartitionedDataWithChannel(file, (WritableByteChannelWrapper)maybeOutputChannel.get());
                     } else {
                        this.writePartitionedDataWithStream(file, writer);
                     }
                  } else {
                     this.writePartitionedDataWithStream(file, writer);
                  }

                  if (!file.delete()) {
                     logger.error("Unable to delete file for partition {}", new MDC[]{MDC.of(.MODULE$, i)});
                  }
               }
            }
         } finally {
            this.writeMetrics.incWriteTime(System.nanoTime() - writeStartTime);
         }

         this.partitionWriters = null;
      }

      return mapOutputWriter.commitAllPartitions(this.getChecksumValues(this.partitionChecksums)).getPartitionLengths();
   }

   private void writePartitionedDataWithChannel(File file, WritableByteChannelWrapper outputChannel) throws IOException {
      boolean copyThrewException = true;

      try {
         FileInputStream in = new FileInputStream(file);

         try {
            FileChannel inputChannel = in.getChannel();

            try {
               Utils.copyFileStreamNIO(inputChannel, outputChannel.channel(), 0L, inputChannel.size());
               copyThrewException = false;
            } catch (Throwable var19) {
               if (inputChannel != null) {
                  try {
                     inputChannel.close();
                  } catch (Throwable var18) {
                     var19.addSuppressed(var18);
                  }
               }

               throw var19;
            }

            if (inputChannel != null) {
               inputChannel.close();
            }
         } finally {
            Closeables.close(in, copyThrewException);
         }
      } finally {
         Closeables.close(outputChannel, copyThrewException);
      }

   }

   private void writePartitionedDataWithStream(File file, ShufflePartitionWriter writer) throws IOException {
      boolean copyThrewException = true;
      FileInputStream in = new FileInputStream(file);

      try {
         OutputStream outputStream = writer.openStream();

         try {
            Utils.copyStream(in, outputStream, false, false);
            copyThrewException = false;
         } finally {
            Closeables.close(outputStream, copyThrewException);
         }
      } finally {
         Closeables.close(in, copyThrewException);
      }

   }

   public Option stop(boolean success) {
      if (this.stopping) {
         return scala.None..empty();
      } else {
         this.stopping = true;
         if (success) {
            if (this.mapStatus == null) {
               throw new IllegalStateException("Cannot call stop(true) without having called write()");
            } else {
               return Option.apply(this.mapStatus);
            }
         } else {
            if (this.partitionWriters != null) {
               try {
                  for(DiskBlockObjectWriter writer : this.partitionWriters) {
                     writer.closeAndDelete();
                  }
               } finally {
                  this.partitionWriters = null;
               }
            }

            return scala.None..empty();
         }
      }
   }
}
