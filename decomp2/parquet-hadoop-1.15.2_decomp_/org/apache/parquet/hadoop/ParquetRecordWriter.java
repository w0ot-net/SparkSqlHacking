package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;

public class ParquetRecordWriter extends RecordWriter {
   private final InternalParquetRecordWriter internalWriter;
   private final MemoryManager memoryManager;
   private final CodecFactory codecFactory;

   /** @deprecated */
   @Deprecated
   public ParquetRecordWriter(ParquetFileWriter w, WriteSupport writeSupport, MessageType schema, Map extraMetaData, int blockSize, int pageSize, CodecFactory.BytesCompressor compressor, int dictionaryPageSize, boolean enableDictionary, boolean validating, ParquetProperties.WriterVersion writerVersion) {
      this(w, writeSupport, schema, extraMetaData, blockSize, pageSize, (CompressionCodecFactory.BytesInputCompressor)compressor, dictionaryPageSize, enableDictionary, validating, writerVersion);
   }

   /** @deprecated */
   @Deprecated
   public ParquetRecordWriter(ParquetFileWriter w, WriteSupport writeSupport, MessageType schema, Map extraMetaData, int blockSize, int pageSize, CompressionCodecFactory.BytesInputCompressor compressor, int dictionaryPageSize, boolean enableDictionary, boolean validating, ParquetProperties.WriterVersion writerVersion) {
      ParquetProperties props = ParquetProperties.builder().withPageSize(pageSize).withDictionaryPageSize(dictionaryPageSize).withDictionaryEncoding(enableDictionary).withWriterVersion(writerVersion).build();
      this.internalWriter = new InternalParquetRecordWriter(w, writeSupport, schema, extraMetaData, (long)blockSize, compressor, validating, props);
      this.memoryManager = null;
      this.codecFactory = null;
   }

   /** @deprecated */
   @Deprecated
   public ParquetRecordWriter(ParquetFileWriter w, WriteSupport writeSupport, MessageType schema, Map extraMetaData, long blockSize, int pageSize, CodecFactory.BytesCompressor compressor, int dictionaryPageSize, boolean enableDictionary, boolean validating, ParquetProperties.WriterVersion writerVersion, MemoryManager memoryManager) {
      this(w, writeSupport, schema, extraMetaData, blockSize, pageSize, (CompressionCodecFactory.BytesInputCompressor)compressor, dictionaryPageSize, enableDictionary, validating, writerVersion, memoryManager);
   }

   /** @deprecated */
   @Deprecated
   public ParquetRecordWriter(ParquetFileWriter w, WriteSupport writeSupport, MessageType schema, Map extraMetaData, long blockSize, int pageSize, CompressionCodecFactory.BytesInputCompressor compressor, int dictionaryPageSize, boolean enableDictionary, boolean validating, ParquetProperties.WriterVersion writerVersion, MemoryManager memoryManager) {
      ParquetProperties props = ParquetProperties.builder().withPageSize(pageSize).withDictionaryPageSize(dictionaryPageSize).withDictionaryEncoding(enableDictionary).withWriterVersion(writerVersion).build();
      this.internalWriter = new InternalParquetRecordWriter(w, writeSupport, schema, extraMetaData, blockSize, compressor, validating, props);
      this.memoryManager = (MemoryManager)Objects.requireNonNull(memoryManager, "memoryManager cannot be null");
      memoryManager.addWriter(this.internalWriter, blockSize);
      this.codecFactory = null;
   }

   ParquetRecordWriter(ParquetFileWriter w, WriteSupport writeSupport, MessageType schema, Map extraMetaData, long blockSize, CompressionCodecName codec, boolean validating, ParquetProperties props, MemoryManager memoryManager, Configuration conf) {
      this.codecFactory = new CodecFactory(conf, props.getPageSizeThreshold());
      this.internalWriter = new InternalParquetRecordWriter(w, writeSupport, schema, extraMetaData, blockSize, this.codecFactory.getCompressor(codec), validating, props);
      this.memoryManager = (MemoryManager)Objects.requireNonNull(memoryManager, "memoryManager cannot be null");
      memoryManager.addWriter(this.internalWriter, blockSize);
   }

   public void close(TaskAttemptContext context) throws IOException, InterruptedException {
      try {
         this.internalWriter.close();
      } finally {
         if (this.codecFactory != null) {
            this.codecFactory.release();
         }

         if (this.memoryManager != null) {
            this.memoryManager.removeWriter(this.internalWriter);
         }

      }

   }

   public void write(Void key, Object value) throws IOException, InterruptedException {
      this.internalWriter.write(value);
   }
}
