package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.parquet.column.ColumnWriteStore;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriteStore;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.crypto.InternalFileEncryptor;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.io.ColumnIOFactory;
import org.apache.parquet.io.MessageColumnIO;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.util.AutoCloseables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class InternalParquetRecordWriter {
   private static final Logger LOG = LoggerFactory.getLogger(InternalParquetRecordWriter.class);
   private final ParquetFileWriter parquetFileWriter;
   private final WriteSupport writeSupport;
   private final MessageType schema;
   private final Map extraMetaData;
   private final long rowGroupSize;
   private long rowGroupSizeThreshold;
   private long nextRowGroupSize;
   private final CompressionCodecFactory.BytesInputCompressor compressor;
   private final boolean validating;
   private final ParquetProperties props;
   private boolean closed;
   private long recordCount = 0L;
   private long recordCountForNextMemCheck;
   private long lastRowGroupEndPos = 0L;
   private ColumnWriteStore columnStore;
   private ColumnChunkPageWriteStore pageStore;
   private BloomFilterWriteStore bloomFilterWriteStore;
   private RecordConsumer recordConsumer;
   private InternalFileEncryptor fileEncryptor;
   private int rowGroupOrdinal;
   private boolean aborted;

   public InternalParquetRecordWriter(ParquetFileWriter parquetFileWriter, WriteSupport writeSupport, MessageType schema, Map extraMetaData, long rowGroupSize, CompressionCodecFactory.BytesInputCompressor compressor, boolean validating, ParquetProperties props) {
      this.parquetFileWriter = parquetFileWriter;
      this.writeSupport = (WriteSupport)Objects.requireNonNull(writeSupport, "writeSupport cannot be null");
      this.schema = schema;
      this.extraMetaData = extraMetaData;
      this.rowGroupSize = rowGroupSize;
      this.rowGroupSizeThreshold = rowGroupSize;
      this.nextRowGroupSize = this.rowGroupSizeThreshold;
      this.compressor = compressor;
      this.validating = validating;
      this.props = props;
      this.fileEncryptor = parquetFileWriter.getEncryptor();
      this.rowGroupOrdinal = 0;
      this.initStore();
      this.recordCountForNextMemCheck = (long)props.getMinRowCountForPageSizeCheck();
   }

   public ParquetMetadata getFooter() {
      return this.parquetFileWriter.getFooter();
   }

   private void initStore() {
      ColumnChunkPageWriteStore columnChunkPageWriteStore = new ColumnChunkPageWriteStore(this.compressor, this.schema, this.props.getAllocator(), this.props.getColumnIndexTruncateLength(), this.props.getPageWriteChecksumEnabled(), this.fileEncryptor, this.rowGroupOrdinal);
      this.pageStore = columnChunkPageWriteStore;
      this.bloomFilterWriteStore = columnChunkPageWriteStore;
      this.columnStore = this.props.newColumnWriteStore(this.schema, this.pageStore, this.bloomFilterWriteStore);
      MessageColumnIO columnIO = (new ColumnIOFactory(this.validating)).getColumnIO(this.schema);
      this.recordConsumer = columnIO.getRecordWriter(this.columnStore);
      this.writeSupport.prepareForWrite(this.recordConsumer);
   }

   public void close() throws IOException, InterruptedException {
      if (!this.closed) {
         try {
            if (!this.aborted) {
               this.flushRowGroupToStore();
               WriteSupport.FinalizedWriteContext finalWriteContext = this.writeSupport.finalizeWrite();
               Map<String, String> finalMetadata = new HashMap(this.extraMetaData);
               String modelName = this.writeSupport.getName();
               if (modelName != null) {
                  finalMetadata.put("writer.model.name", modelName);
               }

               finalMetadata.putAll(finalWriteContext.getExtraMetaData());
               this.parquetFileWriter.end(finalMetadata);
               return;
            }
         } finally {
            AutoCloseables.uncheckedClose(new AutoCloseable[]{this.columnStore, this.pageStore, this.bloomFilterWriteStore, this.parquetFileWriter});
            this.closed = true;
         }

      }
   }

   public void write(Object value) throws IOException, InterruptedException {
      try {
         this.writeSupport.write(value);
         ++this.recordCount;
         this.checkBlockSizeReached();
      } catch (Throwable t) {
         this.aborted = true;
         throw t;
      }
   }

   public long getDataSize() {
      return this.lastRowGroupEndPos + this.columnStore.getBufferedSize();
   }

   private void checkBlockSizeReached() throws IOException {
      if (this.recordCount >= this.recordCountForNextMemCheck) {
         long memSize = this.columnStore.getBufferedSize();
         long recordSize = memSize / this.recordCount;
         if (memSize > this.nextRowGroupSize - 2L * recordSize) {
            LOG.debug("mem size {} > {}: flushing {} records to disk.", new Object[]{memSize, this.nextRowGroupSize, this.recordCount});
            this.flushRowGroupToStore();
            this.initStore();
            this.recordCountForNextMemCheck = Math.min(Math.max((long)this.props.getMinRowCountForPageSizeCheck(), this.recordCount / 2L), (long)this.props.getMaxRowCountForPageSizeCheck());
            this.lastRowGroupEndPos = this.parquetFileWriter.getPos();
         } else {
            this.recordCountForNextMemCheck = Math.min(Math.max((long)this.props.getMinRowCountForPageSizeCheck(), (this.recordCount + (long)((float)this.nextRowGroupSize / (float)recordSize)) / 2L), this.recordCount + (long)this.props.getMaxRowCountForPageSizeCheck());
            LOG.debug("Checked mem at {} will check again at: {}", this.recordCount, this.recordCountForNextMemCheck);
         }
      }

   }

   private void flushRowGroupToStore() throws IOException {
      try {
         this.recordConsumer.flush();
         LOG.debug("Flushing mem columnStore to file. allocated memory: {}", this.columnStore.getAllocatedSize());
         if (this.columnStore.getAllocatedSize() > 3L * this.rowGroupSizeThreshold) {
            LOG.warn("Too much memory used: {}", this.columnStore.memUsageString());
         }

         if (this.recordCount > 0L) {
            ++this.rowGroupOrdinal;
            this.parquetFileWriter.startBlock(this.recordCount);
            this.columnStore.flush();
            this.pageStore.flushToFileWriter(this.parquetFileWriter);
            this.recordCount = 0L;
            this.parquetFileWriter.endBlock();
            this.nextRowGroupSize = Math.min(this.parquetFileWriter.getNextRowGroupSize(), this.rowGroupSizeThreshold);
         }
      } finally {
         AutoCloseables.uncheckedClose(new AutoCloseable[]{this.columnStore, this.pageStore, this.bloomFilterWriteStore});
         this.columnStore = null;
         this.pageStore = null;
         this.bloomFilterWriteStore = null;
      }

   }

   long getRowGroupSizeThreshold() {
      return this.rowGroupSizeThreshold;
   }

   void setRowGroupSizeThreshold(long rowGroupSizeThreshold) {
      this.rowGroupSizeThreshold = rowGroupSizeThreshold;
   }

   MessageType getSchema() {
      return this.schema;
   }
}
