package org.apache.parquet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.HeapByteBufferAllocator;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.crypto.FileDecryptionProperties;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.ParquetInputFormat;
import org.apache.parquet.hadoop.ParquetMetricsCallback;
import org.apache.parquet.hadoop.util.HadoopCodecs;

public class ParquetReadOptions {
   private static final String ALLOCATION_SIZE = "parquet.read.allocation.size";
   private static final boolean RECORD_FILTERING_ENABLED_DEFAULT = true;
   private static final boolean STATS_FILTERING_ENABLED_DEFAULT = true;
   private static final boolean DICTIONARY_FILTERING_ENABLED_DEFAULT = true;
   private static final boolean COLUMN_INDEX_FILTERING_ENABLED_DEFAULT = true;
   private static final boolean HADOOP_VECTORED_IO_ENABLED_DEFAULT = false;
   private static final int ALLOCATION_SIZE_DEFAULT = 8388608;
   private static final boolean PAGE_VERIFY_CHECKSUM_ENABLED_DEFAULT = false;
   private static final boolean BLOOM_FILTER_ENABLED_DEFAULT = true;
   private static final boolean USE_OFF_HEAP_DECRYPT_BUFFER_DEFAULT = false;
   private final boolean useSignedStringMinMax;
   private final boolean useStatsFilter;
   private final boolean useDictionaryFilter;
   private final boolean useRecordFilter;
   private final boolean useColumnIndexFilter;
   private final boolean usePageChecksumVerification;
   private final boolean useBloomFilter;
   private final boolean useOffHeapDecryptBuffer;
   private final boolean useHadoopVectoredIo;
   private final FilterCompat.Filter recordFilter;
   private final ParquetMetadataConverter.MetadataFilter metadataFilter;
   private final CompressionCodecFactory codecFactory;
   private final ByteBufferAllocator allocator;
   private final int maxAllocationSize;
   private final Map properties;
   private final FileDecryptionProperties fileDecryptionProperties;
   private final ParquetConfiguration conf;
   private final ParquetMetricsCallback metricsCallback;

   ParquetReadOptions(boolean useSignedStringMinMax, boolean useStatsFilter, boolean useDictionaryFilter, boolean useRecordFilter, boolean useColumnIndexFilter, boolean usePageChecksumVerification, boolean useBloomFilter, boolean useOffHeapDecryptBuffer, boolean useHadoopVectoredIo, FilterCompat.Filter recordFilter, ParquetMetadataConverter.MetadataFilter metadataFilter, CompressionCodecFactory codecFactory, ByteBufferAllocator allocator, int maxAllocationSize, Map properties, FileDecryptionProperties fileDecryptionProperties, ParquetMetricsCallback metricsCallback) {
      this(useSignedStringMinMax, useStatsFilter, useDictionaryFilter, useRecordFilter, useColumnIndexFilter, usePageChecksumVerification, useBloomFilter, useOffHeapDecryptBuffer, useHadoopVectoredIo, recordFilter, metadataFilter, codecFactory, allocator, maxAllocationSize, properties, fileDecryptionProperties, metricsCallback, new HadoopParquetConfiguration());
   }

   ParquetReadOptions(boolean useSignedStringMinMax, boolean useStatsFilter, boolean useDictionaryFilter, boolean useRecordFilter, boolean useColumnIndexFilter, boolean usePageChecksumVerification, boolean useBloomFilter, boolean useOffHeapDecryptBuffer, boolean useHadoopVectoredIo, FilterCompat.Filter recordFilter, ParquetMetadataConverter.MetadataFilter metadataFilter, CompressionCodecFactory codecFactory, ByteBufferAllocator allocator, int maxAllocationSize, Map properties, FileDecryptionProperties fileDecryptionProperties, ParquetMetricsCallback metricsCallback, ParquetConfiguration conf) {
      this.useSignedStringMinMax = useSignedStringMinMax;
      this.useStatsFilter = useStatsFilter;
      this.useDictionaryFilter = useDictionaryFilter;
      this.useRecordFilter = useRecordFilter;
      this.useColumnIndexFilter = useColumnIndexFilter;
      this.usePageChecksumVerification = usePageChecksumVerification;
      this.useBloomFilter = useBloomFilter;
      this.useOffHeapDecryptBuffer = useOffHeapDecryptBuffer;
      this.useHadoopVectoredIo = useHadoopVectoredIo;
      this.recordFilter = recordFilter;
      this.metadataFilter = metadataFilter;
      this.codecFactory = codecFactory;
      this.allocator = allocator;
      this.maxAllocationSize = maxAllocationSize;
      this.properties = Collections.unmodifiableMap(properties);
      this.fileDecryptionProperties = fileDecryptionProperties;
      this.metricsCallback = metricsCallback;
      this.conf = conf;
   }

   public boolean useSignedStringMinMax() {
      return this.useSignedStringMinMax;
   }

   public boolean useStatsFilter() {
      return this.useStatsFilter;
   }

   public boolean useDictionaryFilter() {
      return this.useDictionaryFilter;
   }

   public boolean useRecordFilter() {
      return this.useRecordFilter;
   }

   public boolean useColumnIndexFilter() {
      return this.useColumnIndexFilter;
   }

   public boolean useBloomFilter() {
      return this.useBloomFilter;
   }

   public boolean useOffHeapDecryptBuffer() {
      return this.useOffHeapDecryptBuffer;
   }

   public boolean usePageChecksumVerification() {
      return this.usePageChecksumVerification;
   }

   public boolean useHadoopVectoredIo() {
      return this.useHadoopVectoredIo;
   }

   public FilterCompat.Filter getRecordFilter() {
      return this.recordFilter;
   }

   public ParquetMetadataConverter.MetadataFilter getMetadataFilter() {
      return this.metadataFilter;
   }

   public CompressionCodecFactory getCodecFactory() {
      return this.codecFactory;
   }

   public ByteBufferAllocator getAllocator() {
      return this.allocator;
   }

   public int getMaxAllocationSize() {
      return this.maxAllocationSize;
   }

   public Set getPropertyNames() {
      return this.properties.keySet();
   }

   public String getProperty(String property) {
      return (String)this.properties.get(property);
   }

   public FileDecryptionProperties getDecryptionProperties() {
      return this.fileDecryptionProperties;
   }

   public ParquetMetricsCallback getMetricsCallback() {
      return this.metricsCallback;
   }

   public boolean isEnabled(String property, boolean defaultValue) {
      Optional<String> propValue = Optional.ofNullable(this.properties.get(property));
      return (Boolean)propValue.map(Boolean::parseBoolean).orElse(defaultValue);
   }

   public ParquetConfiguration getConfiguration() {
      return this.conf;
   }

   public static Builder builder() {
      return new Builder();
   }

   public static Builder builder(ParquetConfiguration conf) {
      return new Builder(conf);
   }

   public static class Builder {
      protected boolean useSignedStringMinMax;
      protected boolean useStatsFilter;
      protected boolean useDictionaryFilter;
      protected boolean useRecordFilter;
      protected boolean useHadoopVectoredIo;
      protected boolean useColumnIndexFilter;
      protected boolean usePageChecksumVerification;
      protected boolean useBloomFilter;
      protected boolean useOffHeapDecryptBuffer;
      protected FilterCompat.Filter recordFilter;
      protected ParquetMetadataConverter.MetadataFilter metadataFilter;
      protected CompressionCodecFactory codecFactory;
      protected ByteBufferAllocator allocator;
      protected int maxAllocationSize;
      protected Map properties;
      protected FileDecryptionProperties fileDecryptionProperties;
      protected ParquetConfiguration conf;
      protected ParquetMetricsCallback metricsCallback;

      public Builder() {
         this(new HadoopParquetConfiguration());
      }

      public Builder(ParquetConfiguration conf) {
         this.useSignedStringMinMax = false;
         this.useStatsFilter = true;
         this.useDictionaryFilter = true;
         this.useRecordFilter = true;
         this.useHadoopVectoredIo = false;
         this.useColumnIndexFilter = true;
         this.usePageChecksumVerification = false;
         this.useBloomFilter = true;
         this.useOffHeapDecryptBuffer = false;
         this.recordFilter = null;
         this.metadataFilter = ParquetMetadataConverter.NO_FILTER;
         this.codecFactory = null;
         this.allocator = new HeapByteBufferAllocator();
         this.maxAllocationSize = 8388608;
         this.properties = new HashMap();
         this.fileDecryptionProperties = null;
         this.conf = conf;
         this.useSignedStringMinMax(conf.getBoolean("parquet.strings.signed-min-max.enabled", false));
         this.useDictionaryFilter(conf.getBoolean("parquet.filter.dictionary.enabled", true));
         this.useStatsFilter(conf.getBoolean("parquet.filter.stats.enabled", true));
         this.useRecordFilter(conf.getBoolean("parquet.filter.record-level.enabled", true));
         this.useColumnIndexFilter(conf.getBoolean("parquet.filter.columnindex.enabled", true));
         this.usePageChecksumVerification(conf.getBoolean("parquet.page.verify-checksum.enabled", this.usePageChecksumVerification));
         this.useBloomFilter(conf.getBoolean("parquet.filter.bloom.enabled", true));
         this.useOffHeapDecryptBuffer(conf.getBoolean("parquet.decrypt.off-heap.buffer.enabled", false));
         this.withCodecFactory(HadoopCodecs.newFactory((ParquetConfiguration)conf, 0));
         this.withRecordFilter(ParquetInputFormat.getFilter(conf));
         this.withMaxAllocationInBytes(conf.getInt("parquet.read.allocation.size", 8388608));
         this.withUseHadoopVectoredIo(conf.getBoolean("parquet.hadoop.vectored.io.enabled", false));
         String badRecordThresh = conf.get("parquet.read.bad.record.threshold");
         if (badRecordThresh != null) {
            this.set("parquet.read.bad.record.threshold", badRecordThresh);
         }

      }

      public Builder useSignedStringMinMax(boolean useSignedStringMinMax) {
         this.useSignedStringMinMax = useSignedStringMinMax;
         return this;
      }

      public Builder useSignedStringMinMax() {
         this.useSignedStringMinMax = true;
         return this;
      }

      public Builder useStatsFilter(boolean useStatsFilter) {
         this.useStatsFilter = useStatsFilter;
         return this;
      }

      public Builder useStatsFilter() {
         this.useStatsFilter = true;
         return this;
      }

      public Builder useDictionaryFilter(boolean useDictionaryFilter) {
         this.useDictionaryFilter = useDictionaryFilter;
         return this;
      }

      public Builder useDictionaryFilter() {
         this.useDictionaryFilter = true;
         return this;
      }

      public Builder useRecordFilter(boolean useRecordFilter) {
         this.useRecordFilter = useRecordFilter;
         return this;
      }

      public Builder useRecordFilter() {
         this.useRecordFilter = true;
         return this;
      }

      public Builder withUseHadoopVectoredIo(boolean useHadoopVectoredIo) {
         this.useHadoopVectoredIo = useHadoopVectoredIo;
         return this;
      }

      public Builder useColumnIndexFilter(boolean useColumnIndexFilter) {
         this.useColumnIndexFilter = useColumnIndexFilter;
         return this;
      }

      public Builder useColumnIndexFilter() {
         return this.useColumnIndexFilter(true);
      }

      public Builder usePageChecksumVerification(boolean usePageChecksumVerification) {
         this.usePageChecksumVerification = usePageChecksumVerification;
         return this;
      }

      public Builder usePageChecksumVerification() {
         return this.usePageChecksumVerification(true);
      }

      public Builder useBloomFilter() {
         this.useBloomFilter = true;
         return this;
      }

      public Builder useOffHeapDecryptBuffer() {
         return this.useOffHeapDecryptBuffer(true);
      }

      public Builder useOffHeapDecryptBuffer(boolean useOffHeapDecryptBuffer) {
         this.useOffHeapDecryptBuffer = useOffHeapDecryptBuffer;
         return this;
      }

      public Builder useBloomFilter(boolean useBloomFilter) {
         this.useBloomFilter = useBloomFilter;
         return this;
      }

      public Builder withRecordFilter(FilterCompat.Filter rowGroupFilter) {
         this.recordFilter = rowGroupFilter;
         return this;
      }

      public Builder withRange(long start, long end) {
         this.metadataFilter = ParquetMetadataConverter.range(start, end);
         return this;
      }

      public Builder withOffsets(long... rowGroupOffsets) {
         this.metadataFilter = ParquetMetadataConverter.offsets(rowGroupOffsets);
         return this;
      }

      public Builder withMetadataFilter(ParquetMetadataConverter.MetadataFilter metadataFilter) {
         this.metadataFilter = metadataFilter;
         return this;
      }

      public Builder withCodecFactory(CompressionCodecFactory codecFactory) {
         this.codecFactory = codecFactory;
         return this;
      }

      public Builder withAllocator(ByteBufferAllocator allocator) {
         this.allocator = allocator;
         return this;
      }

      public Builder withMaxAllocationInBytes(int allocationSizeInBytes) {
         this.maxAllocationSize = allocationSizeInBytes;
         return this;
      }

      public Builder withPageChecksumVerification(boolean val) {
         this.usePageChecksumVerification = val;
         return this;
      }

      public Builder withDecryption(FileDecryptionProperties fileDecryptionProperties) {
         this.fileDecryptionProperties = fileDecryptionProperties;
         return this;
      }

      public Builder withMetricsCallback(ParquetMetricsCallback metricsCallback) {
         this.metricsCallback = metricsCallback;
         return this;
      }

      public Builder set(String key, String value) {
         this.properties.put(key, value);
         return this;
      }

      public Builder copy(ParquetReadOptions options) {
         this.useSignedStringMinMax(options.useSignedStringMinMax);
         this.useStatsFilter(options.useStatsFilter);
         this.useDictionaryFilter(options.useDictionaryFilter);
         this.useRecordFilter(options.useRecordFilter);
         this.withRecordFilter(options.recordFilter);
         this.withUseHadoopVectoredIo(options.useHadoopVectoredIo);
         this.withMetadataFilter(options.metadataFilter);
         this.withCodecFactory(options.codecFactory);
         this.withAllocator(options.allocator);
         this.withPageChecksumVerification(options.usePageChecksumVerification);
         this.withDecryption(options.fileDecryptionProperties);
         this.withMetricsCallback(options.metricsCallback);
         this.conf = options.conf;

         for(Map.Entry keyValue : options.properties.entrySet()) {
            this.set((String)keyValue.getKey(), (String)keyValue.getValue());
         }

         return this;
      }

      public ParquetReadOptions build() {
         if (this.codecFactory == null) {
            if (this.conf == null) {
               this.codecFactory = HadoopCodecs.newFactory(0);
            } else {
               this.codecFactory = HadoopCodecs.newFactory((ParquetConfiguration)this.conf, 0);
            }
         }

         return new ParquetReadOptions(this.useSignedStringMinMax, this.useStatsFilter, this.useDictionaryFilter, this.useRecordFilter, this.useColumnIndexFilter, this.usePageChecksumVerification, this.useBloomFilter, this.useOffHeapDecryptBuffer, this.useHadoopVectoredIo, this.recordFilter, this.metadataFilter, this.codecFactory, this.allocator, this.maxAllocationSize, this.properties, this.fileDecryptionProperties, this.metricsCallback, this.conf);
      }
   }
}
