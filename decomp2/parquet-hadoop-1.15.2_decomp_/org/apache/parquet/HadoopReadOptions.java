package org.apache.parquet;

import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.crypto.DecryptionPropertiesFactory;
import org.apache.parquet.crypto.FileDecryptionProperties;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.ParquetMetricsCallback;

public class HadoopReadOptions extends ParquetReadOptions {
   private final Configuration conf;

   private HadoopReadOptions(boolean useSignedStringMinMax, boolean useStatsFilter, boolean useDictionaryFilter, boolean useRecordFilter, boolean useColumnIndexFilter, boolean usePageChecksumVerification, boolean useBloomFilter, boolean useOffHeapDecryptBuffer, boolean useHadoopVectoredIo, FilterCompat.Filter recordFilter, ParquetMetadataConverter.MetadataFilter metadataFilter, CompressionCodecFactory codecFactory, ByteBufferAllocator allocator, int maxAllocationSize, Map properties, Configuration conf, FileDecryptionProperties fileDecryptionProperties, ParquetMetricsCallback metricsCallback) {
      super(useSignedStringMinMax, useStatsFilter, useDictionaryFilter, useRecordFilter, useColumnIndexFilter, usePageChecksumVerification, useBloomFilter, useOffHeapDecryptBuffer, useHadoopVectoredIo, recordFilter, metadataFilter, codecFactory, allocator, maxAllocationSize, properties, fileDecryptionProperties, metricsCallback, new HadoopParquetConfiguration(conf));
      this.conf = conf;
   }

   public String getProperty(String property) {
      String value = super.getProperty(property);
      return value != null ? value : this.conf.get(property);
   }

   public Configuration getConf() {
      return this.conf;
   }

   public static Builder builder(Configuration conf) {
      return new Builder(conf);
   }

   public static Builder builder(Configuration conf, Path filePath) {
      return new Builder(conf, filePath);
   }

   private static FileDecryptionProperties createDecryptionProperties(Path file, Configuration hadoopConfig) {
      DecryptionPropertiesFactory cryptoFactory = DecryptionPropertiesFactory.loadFactory(hadoopConfig);
      return null == cryptoFactory ? null : cryptoFactory.getFileDecryptionProperties(hadoopConfig, file);
   }

   public static class Builder extends ParquetReadOptions.Builder {
      private final Configuration conf;
      private final Path filePath;

      public Builder(Configuration conf) {
         this(conf, (Path)null);
      }

      public Builder(Configuration conf, Path filePath) {
         super(new HadoopParquetConfiguration(conf));
         this.conf = conf;
         this.filePath = filePath;
      }

      public ParquetReadOptions build() {
         if (null == this.fileDecryptionProperties) {
            this.fileDecryptionProperties = HadoopReadOptions.createDecryptionProperties(this.filePath, this.conf);
         }

         return new HadoopReadOptions(this.useSignedStringMinMax, this.useStatsFilter, this.useDictionaryFilter, this.useRecordFilter, this.useColumnIndexFilter, this.usePageChecksumVerification, this.useBloomFilter, this.useOffHeapDecryptBuffer, this.useHadoopVectoredIo, this.recordFilter, this.metadataFilter, this.codecFactory, this.allocator, this.maxAllocationSize, this.properties, this.conf, this.fileDecryptionProperties, this.metricsCallback);
      }
   }
}
