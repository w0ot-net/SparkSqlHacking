package org.apache.parquet.hadoop;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.crypto.FileEncryptionProperties;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.hadoop.util.HadoopOutputFile;
import org.apache.parquet.io.OutputFile;
import org.apache.parquet.schema.MessageType;

public class ParquetWriter implements Closeable {
   public static final int DEFAULT_BLOCK_SIZE = 134217728;
   public static final int DEFAULT_PAGE_SIZE = 1048576;
   public static final CompressionCodecName DEFAULT_COMPRESSION_CODEC_NAME;
   public static final boolean DEFAULT_IS_DICTIONARY_ENABLED = true;
   public static final boolean DEFAULT_IS_VALIDATING_ENABLED = false;
   public static final ParquetProperties.WriterVersion DEFAULT_WRITER_VERSION;
   public static final String OBJECT_MODEL_NAME_PROP = "writer.model.name";
   public static final int MAX_PADDING_SIZE_DEFAULT = 8388608;
   private final InternalParquetRecordWriter writer;
   private final CompressionCodecFactory codecFactory;

   /** @deprecated */
   @Deprecated
   public ParquetWriter(Path file, WriteSupport writeSupport, CompressionCodecName compressionCodecName, int blockSize, int pageSize) throws IOException {
      this(file, writeSupport, compressionCodecName, blockSize, pageSize, true, false);
   }

   /** @deprecated */
   @Deprecated
   public ParquetWriter(Path file, WriteSupport writeSupport, CompressionCodecName compressionCodecName, int blockSize, int pageSize, boolean enableDictionary, boolean validating) throws IOException {
      this(file, writeSupport, compressionCodecName, blockSize, pageSize, pageSize, enableDictionary, validating);
   }

   /** @deprecated */
   @Deprecated
   public ParquetWriter(Path file, WriteSupport writeSupport, CompressionCodecName compressionCodecName, int blockSize, int pageSize, int dictionaryPageSize, boolean enableDictionary, boolean validating) throws IOException {
      this(file, writeSupport, compressionCodecName, blockSize, pageSize, dictionaryPageSize, enableDictionary, validating, DEFAULT_WRITER_VERSION);
   }

   /** @deprecated */
   @Deprecated
   public ParquetWriter(Path file, WriteSupport writeSupport, CompressionCodecName compressionCodecName, int blockSize, int pageSize, int dictionaryPageSize, boolean enableDictionary, boolean validating, ParquetProperties.WriterVersion writerVersion) throws IOException {
      this(file, writeSupport, compressionCodecName, blockSize, pageSize, dictionaryPageSize, enableDictionary, validating, writerVersion, new Configuration());
   }

   /** @deprecated */
   @Deprecated
   public ParquetWriter(Path file, WriteSupport writeSupport, CompressionCodecName compressionCodecName, int blockSize, int pageSize, int dictionaryPageSize, boolean enableDictionary, boolean validating, ParquetProperties.WriterVersion writerVersion, Configuration conf) throws IOException {
      this(file, ParquetFileWriter.Mode.CREATE, writeSupport, compressionCodecName, blockSize, pageSize, dictionaryPageSize, enableDictionary, validating, writerVersion, conf);
   }

   /** @deprecated */
   @Deprecated
   public ParquetWriter(Path file, ParquetFileWriter.Mode mode, WriteSupport writeSupport, CompressionCodecName compressionCodecName, int blockSize, int pageSize, int dictionaryPageSize, boolean enableDictionary, boolean validating, ParquetProperties.WriterVersion writerVersion, Configuration conf) throws IOException {
      this(HadoopOutputFile.fromPath(file, conf), mode, writeSupport, compressionCodecName, (long)blockSize, validating, (Configuration)conf, 8388608, ParquetProperties.builder().withPageSize(pageSize).withDictionaryPageSize(dictionaryPageSize).withDictionaryEncoding(enableDictionary).withWriterVersion(writerVersion).build(), (FileEncryptionProperties)null);
   }

   /** @deprecated */
   @Deprecated
   public ParquetWriter(Path file, WriteSupport writeSupport) throws IOException {
      this(file, writeSupport, DEFAULT_COMPRESSION_CODEC_NAME, 134217728, 1048576);
   }

   /** @deprecated */
   @Deprecated
   public ParquetWriter(Path file, Configuration conf, WriteSupport writeSupport) throws IOException {
      this(file, writeSupport, DEFAULT_COMPRESSION_CODEC_NAME, 134217728, 1048576, 1048576, true, false, DEFAULT_WRITER_VERSION, conf);
   }

   ParquetWriter(OutputFile file, ParquetFileWriter.Mode mode, WriteSupport writeSupport, CompressionCodecName compressionCodecName, long rowGroupSize, boolean validating, Configuration conf, int maxPaddingSize, ParquetProperties encodingProps, FileEncryptionProperties encryptionProperties) throws IOException {
      this(file, mode, writeSupport, compressionCodecName, rowGroupSize, validating, (ParquetConfiguration)(new HadoopParquetConfiguration(conf)), maxPaddingSize, encodingProps, encryptionProperties);
   }

   ParquetWriter(OutputFile file, ParquetFileWriter.Mode mode, WriteSupport writeSupport, CompressionCodecName compressionCodecName, long rowGroupSize, boolean validating, ParquetConfiguration conf, int maxPaddingSize, ParquetProperties encodingProps, FileEncryptionProperties encryptionProperties) throws IOException {
      this(file, mode, writeSupport, compressionCodecName, new CodecFactory(conf, encodingProps.getPageSizeThreshold()), rowGroupSize, validating, conf, maxPaddingSize, encodingProps, encryptionProperties);
   }

   ParquetWriter(OutputFile file, ParquetFileWriter.Mode mode, WriteSupport writeSupport, CompressionCodecName compressionCodecName, CompressionCodecFactory codecFactory, long rowGroupSize, boolean validating, ParquetConfiguration conf, int maxPaddingSize, ParquetProperties encodingProps, FileEncryptionProperties encryptionProperties) throws IOException {
      WriteSupport.WriteContext writeContext = writeSupport.init(conf);
      MessageType schema = writeContext.getSchema();
      if (encryptionProperties == null) {
         encryptionProperties = EncryptionPropertiesHelper.createEncryptionProperties(conf, file, writeContext);
      }

      ParquetFileWriter fileWriter = new ParquetFileWriter(file, schema, mode, rowGroupSize, maxPaddingSize, encryptionProperties, encodingProps);
      fileWriter.start();
      this.codecFactory = codecFactory;
      CompressionCodecFactory.BytesInputCompressor compressor = codecFactory.getCompressor(compressionCodecName);
      Map<String, String> extraMetadata;
      if (encodingProps.getExtraMetaData() != null && !encodingProps.getExtraMetaData().isEmpty()) {
         extraMetadata = new HashMap(writeContext.getExtraMetaData());
         encodingProps.getExtraMetaData().forEach((metadataKey, metadataValue) -> {
            if (metadataKey.equals("writer.model.name")) {
               throw new IllegalArgumentException("Cannot overwrite metadata key writer.model.name. Please use another key name.");
            } else if (extraMetadata.put(metadataKey, metadataValue) != null) {
               throw new IllegalArgumentException("Duplicate metadata key " + metadataKey + ". Please use another key name.");
            }
         });
      } else {
         extraMetadata = writeContext.getExtraMetaData();
      }

      this.writer = new InternalParquetRecordWriter(fileWriter, writeSupport, schema, extraMetadata, rowGroupSize, compressor, validating, encodingProps);
   }

   public void write(Object object) throws IOException {
      try {
         this.writer.write(object);
      } catch (InterruptedException e) {
         throw new IOException(e);
      }
   }

   public void close() throws IOException {
      try {
         this.writer.close();
      } catch (InterruptedException e) {
         throw new IOException(e);
      } finally {
         this.codecFactory.release();
      }

   }

   public ParquetMetadata getFooter() {
      return this.writer.getFooter();
   }

   public long getDataSize() {
      return this.writer.getDataSize();
   }

   static {
      DEFAULT_COMPRESSION_CODEC_NAME = CompressionCodecName.UNCOMPRESSED;
      DEFAULT_WRITER_VERSION = ParquetProperties.DEFAULT_WRITER_VERSION;
   }

   public abstract static class Builder {
      private OutputFile file = null;
      private Path path = null;
      private FileEncryptionProperties encryptionProperties = null;
      private ParquetConfiguration conf = null;
      private ParquetFileWriter.Mode mode;
      private CompressionCodecFactory codecFactory = null;
      private CompressionCodecName codecName;
      private long rowGroupSize;
      private int maxPaddingSize;
      private boolean enableValidation;
      private ParquetProperties.Builder encodingPropsBuilder;

      protected Builder(Path path) {
         this.codecName = ParquetWriter.DEFAULT_COMPRESSION_CODEC_NAME;
         this.rowGroupSize = 134217728L;
         this.maxPaddingSize = 8388608;
         this.enableValidation = false;
         this.encodingPropsBuilder = ParquetProperties.builder();
         this.path = path;
      }

      protected Builder(OutputFile path) {
         this.codecName = ParquetWriter.DEFAULT_COMPRESSION_CODEC_NAME;
         this.rowGroupSize = 134217728L;
         this.maxPaddingSize = 8388608;
         this.enableValidation = false;
         this.encodingPropsBuilder = ParquetProperties.builder();
         this.file = path;
      }

      protected abstract Builder self();

      /** @deprecated */
      @Deprecated
      protected abstract WriteSupport getWriteSupport(Configuration var1);

      protected WriteSupport getWriteSupport(ParquetConfiguration conf) {
         return this.getWriteSupport(ConfigurationUtil.createHadoopConfiguration(conf));
      }

      public Builder withConf(Configuration conf) {
         this.conf = new HadoopParquetConfiguration(conf);
         return this.self();
      }

      public Builder withConf(ParquetConfiguration conf) {
         this.conf = conf;
         return this.self();
      }

      public Builder withWriteMode(ParquetFileWriter.Mode mode) {
         this.mode = mode;
         return this.self();
      }

      public Builder withCompressionCodec(CompressionCodecName codecName) {
         this.codecName = codecName;
         return this.self();
      }

      public Builder withCodecFactory(CompressionCodecFactory codecFactory) {
         this.codecFactory = codecFactory;
         return this.self();
      }

      public Builder withEncryption(FileEncryptionProperties encryptionProperties) {
         this.encryptionProperties = encryptionProperties;
         return this.self();
      }

      /** @deprecated */
      @Deprecated
      public Builder withRowGroupSize(int rowGroupSize) {
         return this.withRowGroupSize((long)rowGroupSize);
      }

      public Builder withRowGroupSize(long rowGroupSize) {
         this.rowGroupSize = rowGroupSize;
         return this.self();
      }

      public Builder withPageSize(int pageSize) {
         this.encodingPropsBuilder.withPageSize(pageSize);
         return this.self();
      }

      public Builder withPageRowCountLimit(int rowCount) {
         this.encodingPropsBuilder.withPageRowCountLimit(rowCount);
         return this.self();
      }

      public Builder withDictionaryPageSize(int dictionaryPageSize) {
         this.encodingPropsBuilder.withDictionaryPageSize(dictionaryPageSize);
         return this.self();
      }

      public Builder withMaxPaddingSize(int maxPaddingSize) {
         this.maxPaddingSize = maxPaddingSize;
         return this.self();
      }

      public Builder enableDictionaryEncoding() {
         this.encodingPropsBuilder.withDictionaryEncoding(true);
         return this.self();
      }

      public Builder withDictionaryEncoding(boolean enableDictionary) {
         this.encodingPropsBuilder.withDictionaryEncoding(enableDictionary);
         return this.self();
      }

      public Builder withByteStreamSplitEncoding(boolean enableByteStreamSplit) {
         this.encodingPropsBuilder.withByteStreamSplitEncoding(enableByteStreamSplit);
         return this.self();
      }

      public Builder withDictionaryEncoding(String columnPath, boolean enableDictionary) {
         this.encodingPropsBuilder.withDictionaryEncoding(columnPath, enableDictionary);
         return this.self();
      }

      public Builder enableValidation() {
         this.enableValidation = true;
         return this.self();
      }

      public Builder withValidation(boolean enableValidation) {
         this.enableValidation = enableValidation;
         return this.self();
      }

      public Builder withWriterVersion(ParquetProperties.WriterVersion version) {
         this.encodingPropsBuilder.withWriterVersion(version);
         return this.self();
      }

      public Builder enablePageWriteChecksum() {
         this.encodingPropsBuilder.withPageWriteChecksumEnabled(true);
         return this.self();
      }

      public Builder withPageWriteChecksumEnabled(boolean enablePageWriteChecksum) {
         this.encodingPropsBuilder.withPageWriteChecksumEnabled(enablePageWriteChecksum);
         return this.self();
      }

      public Builder withMaxBloomFilterBytes(int maxBloomFilterBytes) {
         this.encodingPropsBuilder.withMaxBloomFilterBytes(maxBloomFilterBytes);
         return this.self();
      }

      public Builder withBloomFilterNDV(String columnPath, long ndv) {
         this.encodingPropsBuilder.withBloomFilterNDV(columnPath, ndv);
         return this.self();
      }

      public Builder withBloomFilterFPP(String columnPath, double fpp) {
         this.encodingPropsBuilder.withBloomFilterFPP(columnPath, fpp);
         return this.self();
      }

      public Builder withAdaptiveBloomFilterEnabled(boolean enabled) {
         this.encodingPropsBuilder.withAdaptiveBloomFilterEnabled(enabled);
         return this.self();
      }

      public Builder withBloomFilterCandidateNumber(String columnPath, int number) {
         this.encodingPropsBuilder.withBloomFilterCandidatesNumber(columnPath, number);
         return this.self();
      }

      public Builder withBloomFilterEnabled(boolean enabled) {
         this.encodingPropsBuilder.withBloomFilterEnabled(enabled);
         return this.self();
      }

      public Builder withBloomFilterEnabled(String columnPath, boolean enabled) {
         this.encodingPropsBuilder.withBloomFilterEnabled(columnPath, enabled);
         return this.self();
      }

      public Builder withMinRowCountForPageSizeCheck(int min) {
         this.encodingPropsBuilder.withMinRowCountForPageSizeCheck(min);
         return this.self();
      }

      public Builder withMaxRowCountForPageSizeCheck(int max) {
         this.encodingPropsBuilder.withMaxRowCountForPageSizeCheck(max);
         return this.self();
      }

      public Builder withColumnIndexTruncateLength(int length) {
         this.encodingPropsBuilder.withColumnIndexTruncateLength(length);
         return this.self();
      }

      public Builder withStatisticsTruncateLength(int length) {
         this.encodingPropsBuilder.withStatisticsTruncateLength(length);
         return this.self();
      }

      public Builder withExtraMetaData(Map extraMetaData) {
         this.encodingPropsBuilder.withExtraMetaData(extraMetaData);
         return this.self();
      }

      public Builder withAllocator(ByteBufferAllocator allocator) {
         this.encodingPropsBuilder.withAllocator(allocator);
         return this.self();
      }

      public Builder config(String property, String value) {
         if (this.conf == null) {
            this.conf = new HadoopParquetConfiguration();
         }

         this.conf.set(property, value);
         return this.self();
      }

      public Builder withStatisticsEnabled(String columnPath, boolean enabled) {
         this.encodingPropsBuilder.withStatisticsEnabled(columnPath, enabled);
         return this.self();
      }

      public Builder withStatisticsEnabled(boolean enabled) {
         this.encodingPropsBuilder.withStatisticsEnabled(enabled);
         return this.self();
      }

      public Builder withSizeStatisticsEnabled(String columnPath, boolean enabled) {
         this.encodingPropsBuilder.withSizeStatisticsEnabled(columnPath, enabled);
         return this.self();
      }

      public Builder withSizeStatisticsEnabled(boolean enabled) {
         this.encodingPropsBuilder.withSizeStatisticsEnabled(enabled);
         return this.self();
      }

      public ParquetWriter build() throws IOException {
         if (this.conf == null) {
            this.conf = new HadoopParquetConfiguration();
         }

         ParquetProperties encodingProps = this.encodingPropsBuilder.build();
         if (this.codecFactory == null) {
            this.codecFactory = new CodecFactory(this.conf, encodingProps.getPageSizeThreshold());
         }

         return new ParquetWriter((OutputFile)(this.file != null ? this.file : HadoopOutputFile.fromPath(this.path, ConfigurationUtil.createHadoopConfiguration(this.conf))), this.mode, this.getWriteSupport(this.conf), this.codecName, this.codecFactory, this.rowGroupSize, this.enableValidation, this.conf, this.maxPaddingSize, encodingProps, this.encryptionProperties);
      }
   }
}
