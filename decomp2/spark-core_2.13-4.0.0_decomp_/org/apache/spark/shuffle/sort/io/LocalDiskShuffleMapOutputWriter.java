package org.apache.spark.shuffle.sort.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Optional;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PATH.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.shuffle.IndexShuffleBlockResolver;
import org.apache.spark.shuffle.api.ShuffleMapOutputWriter;
import org.apache.spark.shuffle.api.ShufflePartitionWriter;
import org.apache.spark.shuffle.api.WritableByteChannelWrapper;
import org.apache.spark.shuffle.api.metadata.MapOutputCommitMessage;

public class LocalDiskShuffleMapOutputWriter implements ShuffleMapOutputWriter {
   private static final SparkLogger log = SparkLoggerFactory.getLogger(LocalDiskShuffleMapOutputWriter.class);
   private final int shuffleId;
   private final long mapId;
   private final IndexShuffleBlockResolver blockResolver;
   private final long[] partitionLengths;
   private final int bufferSize;
   private int lastPartitionId = -1;
   private long currChannelPosition;
   private long bytesWrittenToMergedFile = 0L;
   private final File outputFile;
   private File outputTempFile;
   private FileOutputStream outputFileStream;
   private FileChannel outputFileChannel;
   private BufferedOutputStream outputBufferedFileStream;

   public LocalDiskShuffleMapOutputWriter(int shuffleId, long mapId, int numPartitions, IndexShuffleBlockResolver blockResolver, SparkConf sparkConf) {
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.blockResolver = blockResolver;
      this.bufferSize = (int)(Long)sparkConf.get(package$.MODULE$.SHUFFLE_LOCAL_DISK_FILE_OUTPUT_BUFFER_SIZE()) * 1024;
      this.partitionLengths = new long[numPartitions];
      this.outputFile = blockResolver.getDataFile(shuffleId, mapId);
      this.outputTempFile = null;
   }

   public ShufflePartitionWriter getPartitionWriter(int reducePartitionId) throws IOException {
      if (reducePartitionId <= this.lastPartitionId) {
         throw new IllegalArgumentException("Partitions should be requested in increasing order.");
      } else {
         this.lastPartitionId = reducePartitionId;
         if (this.outputTempFile == null) {
            this.outputTempFile = this.blockResolver.createTempFile(this.outputFile);
         }

         if (this.outputFileChannel != null) {
            this.currChannelPosition = this.outputFileChannel.position();
         } else {
            this.currChannelPosition = 0L;
         }

         return new LocalDiskShufflePartitionWriter(reducePartitionId);
      }
   }

   public MapOutputCommitMessage commitAllPartitions(long[] checksums) throws IOException {
      if (this.outputFileChannel != null && this.outputFileChannel.position() != this.bytesWrittenToMergedFile) {
         long var10002 = this.outputFileChannel.position();
         throw new IOException("Current position " + var10002 + " does not equal expected position " + this.bytesWrittenToMergedFile + " after transferTo. Please check your  kernel version to see if it is 2.6.32, as there is a kernel bug which will lead to unexpected behavior when using transferTo. You can set spark.file.transferTo=false to disable this NIO feature.");
      } else {
         this.cleanUp();
         File resolvedTmp = this.outputTempFile != null && this.outputTempFile.isFile() ? this.outputTempFile : null;
         log.debug("Writing shuffle index file for mapId {} with length {}", this.mapId, this.partitionLengths.length);
         this.blockResolver.writeMetadataFileAndCommit(this.shuffleId, this.mapId, this.partitionLengths, checksums, resolvedTmp);
         return MapOutputCommitMessage.of(this.partitionLengths);
      }
   }

   public void abort(Throwable error) throws IOException {
      this.cleanUp();
      if (this.outputTempFile != null && this.outputTempFile.exists() && !this.outputTempFile.delete()) {
         log.warn("Failed to delete temporary shuffle file at {}", new MDC[]{MDC.of(.MODULE$, this.outputTempFile.getAbsolutePath())});
      }

   }

   private void cleanUp() throws IOException {
      if (this.outputBufferedFileStream != null) {
         this.outputBufferedFileStream.close();
      }

      if (this.outputFileChannel != null) {
         this.outputFileChannel.close();
      }

      if (this.outputFileStream != null) {
         this.outputFileStream.close();
      }

   }

   private void initStream() throws IOException {
      if (this.outputFileStream == null) {
         this.outputFileStream = new FileOutputStream(this.outputTempFile, true);
      }

      if (this.outputBufferedFileStream == null) {
         this.outputBufferedFileStream = new BufferedOutputStream(this.outputFileStream, this.bufferSize);
      }

   }

   private void initChannel() throws IOException {
      if (this.outputFileChannel == null) {
         this.outputFileChannel = (new FileOutputStream(this.outputTempFile, true)).getChannel();
      }

   }

   private class LocalDiskShufflePartitionWriter implements ShufflePartitionWriter {
      private final int partitionId;
      private PartitionWriterStream partStream = null;
      private PartitionWriterChannel partChannel = null;

      private LocalDiskShufflePartitionWriter(int partitionId) {
         this.partitionId = partitionId;
      }

      public OutputStream openStream() throws IOException {
         if (this.partStream == null) {
            if (LocalDiskShuffleMapOutputWriter.this.outputFileChannel != null) {
               throw new IllegalStateException("Requested an output channel for a previous write but now an output stream has been requested. Should not be using both channels and streams to write.");
            }

            LocalDiskShuffleMapOutputWriter.this.initStream();
            this.partStream = LocalDiskShuffleMapOutputWriter.this.new PartitionWriterStream(this.partitionId);
         }

         return this.partStream;
      }

      public Optional openChannelWrapper() throws IOException {
         if (this.partChannel == null) {
            if (this.partStream != null) {
               throw new IllegalStateException("Requested an output stream for a previous write but now an output channel has been requested. Should not be using both channels and streams to write.");
            }

            LocalDiskShuffleMapOutputWriter.this.initChannel();
            this.partChannel = LocalDiskShuffleMapOutputWriter.this.new PartitionWriterChannel(this.partitionId);
         }

         return Optional.of(this.partChannel);
      }

      public long getNumBytesWritten() {
         if (this.partChannel != null) {
            try {
               return this.partChannel.getCount();
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
         } else {
            return this.partStream != null ? this.partStream.getCount() : 0L;
         }
      }
   }

   private class PartitionWriterStream extends OutputStream {
      private final int partitionId;
      private long count = 0L;
      private boolean isClosed = false;

      PartitionWriterStream(int partitionId) {
         this.partitionId = partitionId;
      }

      public long getCount() {
         return this.count;
      }

      public void write(int b) throws IOException {
         this.verifyNotClosed();
         LocalDiskShuffleMapOutputWriter.this.outputBufferedFileStream.write(b);
         ++this.count;
      }

      public void write(byte[] buf, int pos, int length) throws IOException {
         this.verifyNotClosed();
         LocalDiskShuffleMapOutputWriter.this.outputBufferedFileStream.write(buf, pos, length);
         this.count += (long)length;
      }

      public void close() {
         this.isClosed = true;
         LocalDiskShuffleMapOutputWriter.this.partitionLengths[this.partitionId] = this.count;
         LocalDiskShuffleMapOutputWriter var10000 = LocalDiskShuffleMapOutputWriter.this;
         var10000.bytesWrittenToMergedFile += this.count;
      }

      private void verifyNotClosed() {
         if (this.isClosed) {
            throw new IllegalStateException("Attempting to write to a closed block output stream.");
         }
      }
   }

   private class PartitionWriterChannel implements WritableByteChannelWrapper {
      private final int partitionId;

      PartitionWriterChannel(int partitionId) {
         this.partitionId = partitionId;
      }

      public long getCount() throws IOException {
         long writtenPosition = LocalDiskShuffleMapOutputWriter.this.outputFileChannel.position();
         return writtenPosition - LocalDiskShuffleMapOutputWriter.this.currChannelPosition;
      }

      public WritableByteChannel channel() {
         return LocalDiskShuffleMapOutputWriter.this.outputFileChannel;
      }

      public void close() throws IOException {
         LocalDiskShuffleMapOutputWriter.this.partitionLengths[this.partitionId] = this.getCount();
         LocalDiskShuffleMapOutputWriter var10000 = LocalDiskShuffleMapOutputWriter.this;
         var10000.bytesWrittenToMergedFile += LocalDiskShuffleMapOutputWriter.this.partitionLengths[this.partitionId];
      }
   }
}
