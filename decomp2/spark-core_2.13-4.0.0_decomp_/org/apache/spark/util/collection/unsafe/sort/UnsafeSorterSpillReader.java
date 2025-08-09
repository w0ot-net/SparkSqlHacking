package org.apache.spark.util.collection.unsafe.sort;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.spark.SparkEnv;
import org.apache.spark.TaskContext;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.io.NioBufferedFileInputStream;
import org.apache.spark.io.ReadAheadInputStream;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.unsafe.Platform;
import org.apache.spark.util.TaskCompletionListener;
import org.sparkproject.guava.io.ByteStreams;
import org.sparkproject.guava.io.Closeables;

public final class UnsafeSorterSpillReader extends UnsafeSorterIterator implements Closeable {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(UnsafeSorterSpillReader.class);
   public static final int MAX_BUFFER_SIZE_BYTES = 16777216;
   private InputStream in;
   private DataInputStream din;
   private int recordLength;
   private long keyPrefix;
   private int numRecords;
   private int numRecordsRemaining;
   private byte[] arr = new byte[1048576];
   private Object baseObject;
   private final TaskContext taskContext;

   public UnsafeSorterSpillReader(SerializerManager serializerManager, File file, BlockId blockId) throws IOException {
      this.baseObject = this.arr;
      this.taskContext = TaskContext.get();

      assert file.length() > 0L;

      ConfigEntry<Object> bufferSizeConfigEntry = package$.MODULE$.UNSAFE_SORTER_SPILL_READER_BUFFER_SIZE();
      int DEFAULT_BUFFER_SIZE_BYTES = ((Long)bufferSizeConfigEntry.defaultValue().get()).intValue();
      int bufferSizeBytes = SparkEnv.get() == null ? DEFAULT_BUFFER_SIZE_BYTES : ((Long)SparkEnv.get().conf().get(bufferSizeConfigEntry)).intValue();
      boolean readAheadEnabled = SparkEnv.get() != null && (Boolean)SparkEnv.get().conf().get(package$.MODULE$.UNSAFE_SORTER_SPILL_READ_AHEAD_ENABLED());
      InputStream bs = new NioBufferedFileInputStream(file, bufferSizeBytes);

      try {
         if (readAheadEnabled) {
            this.in = new ReadAheadInputStream(serializerManager.wrapStream(blockId, bs), bufferSizeBytes);
         } else {
            this.in = serializerManager.wrapStream(blockId, bs);
         }

         this.din = new DataInputStream(this.in);
         this.numRecords = this.numRecordsRemaining = this.din.readInt();
      } catch (IOException e) {
         Closeables.close(bs, true);
         throw e;
      }

      if (this.taskContext != null) {
         this.taskContext.addTaskCompletionListener((TaskCompletionListener)((context) -> {
            try {
               this.close();
            } catch (IOException e) {
               logger.info("error while closing UnsafeSorterSpillReader", e);
            }

         }));
      }

   }

   public int getNumRecords() {
      return this.numRecords;
   }

   public long getCurrentPageNumber() {
      throw new UnsupportedOperationException();
   }

   public boolean hasNext() {
      return this.numRecordsRemaining > 0;
   }

   public void loadNext() throws IOException {
      if (this.taskContext != null) {
         this.taskContext.killTaskIfInterrupted();
      }

      this.recordLength = this.din.readInt();
      this.keyPrefix = this.din.readLong();
      if (this.recordLength > this.arr.length) {
         this.arr = new byte[this.recordLength];
         this.baseObject = this.arr;
      }

      ByteStreams.readFully(this.in, this.arr, 0, this.recordLength);
      --this.numRecordsRemaining;
      if (this.numRecordsRemaining == 0) {
         this.close();
      }

   }

   public Object getBaseObject() {
      return this.baseObject;
   }

   public long getBaseOffset() {
      return (long)Platform.BYTE_ARRAY_OFFSET;
   }

   public int getRecordLength() {
      return this.recordLength;
   }

   public long getKeyPrefix() {
      return this.keyPrefix;
   }

   public void close() throws IOException {
      if (this.in != null) {
         try {
            this.in.close();
         } finally {
            this.in = null;
            this.din = null;
         }
      }

   }
}
