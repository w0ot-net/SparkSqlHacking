package org.apache.spark.util.collection.unsafe.sort;

import java.io.File;
import java.io.IOException;
import org.apache.spark.SparkConf;
import org.apache.spark.executor.ShuffleWriteMetrics;
import org.apache.spark.internal.config.package$;
import org.apache.spark.serializer.DummySerializerInstance;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.DiskBlockObjectWriter;
import org.apache.spark.storage.TempLocalBlockId;
import org.apache.spark.unsafe.Platform;
import scala.Tuple2;

public final class UnsafeSorterSpillWriter {
   private final SparkConf conf = new SparkConf();
   private final int diskWriteBufferSize;
   private byte[] writeBuffer;
   private final File file;
   private final BlockId blockId;
   private final int numRecordsToWrite;
   private DiskBlockObjectWriter writer;
   private int numRecordsSpilled;

   public UnsafeSorterSpillWriter(BlockManager blockManager, int fileBufferSize, ShuffleWriteMetrics writeMetrics, int numRecordsToWrite) throws IOException {
      this.diskWriteBufferSize = (int)(Long)this.conf.get(package$.MODULE$.SHUFFLE_DISK_WRITE_BUFFER_SIZE());
      this.writeBuffer = new byte[this.diskWriteBufferSize];
      this.numRecordsSpilled = 0;
      Tuple2<TempLocalBlockId, File> spilledFileInfo = blockManager.diskBlockManager().createTempLocalBlock();
      this.file = (File)spilledFileInfo._2();
      this.blockId = (BlockId)spilledFileInfo._1();
      this.numRecordsToWrite = numRecordsToWrite;
      this.writer = blockManager.getDiskWriter(this.blockId, this.file, DummySerializerInstance.INSTANCE, fileBufferSize, writeMetrics);
      this.writeIntToBuffer(numRecordsToWrite, 0);
      this.writer.write(this.writeBuffer, 0, 4);
   }

   private void writeLongToBuffer(long v, int offset) {
      this.writeBuffer[offset + 0] = (byte)((int)(v >>> 56));
      this.writeBuffer[offset + 1] = (byte)((int)(v >>> 48));
      this.writeBuffer[offset + 2] = (byte)((int)(v >>> 40));
      this.writeBuffer[offset + 3] = (byte)((int)(v >>> 32));
      this.writeBuffer[offset + 4] = (byte)((int)(v >>> 24));
      this.writeBuffer[offset + 5] = (byte)((int)(v >>> 16));
      this.writeBuffer[offset + 6] = (byte)((int)(v >>> 8));
      this.writeBuffer[offset + 7] = (byte)((int)(v >>> 0));
   }

   private void writeIntToBuffer(int v, int offset) {
      this.writeBuffer[offset + 0] = (byte)(v >>> 24);
      this.writeBuffer[offset + 1] = (byte)(v >>> 16);
      this.writeBuffer[offset + 2] = (byte)(v >>> 8);
      this.writeBuffer[offset + 3] = (byte)(v >>> 0);
   }

   public void write(Object baseObject, long baseOffset, int recordLength, long keyPrefix) throws IOException {
      if (this.numRecordsSpilled == this.numRecordsToWrite) {
         throw new IllegalStateException("Number of records written exceeded numRecordsToWrite = " + this.numRecordsToWrite);
      } else {
         ++this.numRecordsSpilled;
         this.writeIntToBuffer(recordLength, 0);
         this.writeLongToBuffer(keyPrefix, 4);
         int dataRemaining = recordLength;
         int freeSpaceInWriteBuffer = this.diskWriteBufferSize - 4 - 8;

         for(long recordReadPosition = baseOffset; dataRemaining > 0; freeSpaceInWriteBuffer = this.diskWriteBufferSize) {
            int toTransfer = Math.min(freeSpaceInWriteBuffer, dataRemaining);
            Platform.copyMemory(baseObject, recordReadPosition, this.writeBuffer, (long)(Platform.BYTE_ARRAY_OFFSET + (this.diskWriteBufferSize - freeSpaceInWriteBuffer)), (long)toTransfer);
            this.writer.write(this.writeBuffer, 0, this.diskWriteBufferSize - freeSpaceInWriteBuffer + toTransfer);
            recordReadPosition += (long)toTransfer;
            dataRemaining -= toTransfer;
         }

         if (freeSpaceInWriteBuffer < this.diskWriteBufferSize) {
            this.writer.write(this.writeBuffer, 0, this.diskWriteBufferSize - freeSpaceInWriteBuffer);
         }

         this.writer.recordWritten();
      }
   }

   public void close() throws IOException {
      this.writer.commitAndGet();
      this.writer.close();
      this.writer = null;
      this.writeBuffer = null;
   }

   public File getFile() {
      return this.file;
   }

   public UnsafeSorterSpillReader getReader(SerializerManager serializerManager) throws IOException {
      return new UnsafeSorterSpillReader(serializerManager, this.file, this.blockId);
   }

   public int recordsSpilled() {
      return this.numRecordsSpilled;
   }
}
