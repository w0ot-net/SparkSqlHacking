package org.apache.parquet.column;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.bytes.CapacityByteArrayOutputStream;
import org.apache.parquet.bytes.HeapByteBufferAllocator;
import org.apache.parquet.column.impl.ColumnWriteStoreV1;
import org.apache.parquet.column.impl.ColumnWriteStoreV2;
import org.apache.parquet.column.page.PageWriteStore;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.bitpacking.DevNullValuesWriter;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriteStore;
import org.apache.parquet.column.values.factory.DefaultValuesWriterFactory;
import org.apache.parquet.column.values.factory.ValuesWriterFactory;
import org.apache.parquet.column.values.rle.RunLengthBitPackingHybridEncoder;
import org.apache.parquet.column.values.rle.RunLengthBitPackingHybridValuesWriter;
import org.apache.parquet.schema.MessageType;

public class ParquetProperties {
   public static final int DEFAULT_PAGE_SIZE = 1048576;
   public static final int DEFAULT_DICTIONARY_PAGE_SIZE = 1048576;
   public static final boolean DEFAULT_IS_DICTIONARY_ENABLED = true;
   public static final boolean DEFAULT_IS_BYTE_STREAM_SPLIT_ENABLED = false;
   public static final WriterVersion DEFAULT_WRITER_VERSION;
   public static final boolean DEFAULT_ESTIMATE_ROW_COUNT_FOR_PAGE_SIZE_CHECK = true;
   public static final int DEFAULT_MINIMUM_RECORD_COUNT_FOR_CHECK = 100;
   public static final int DEFAULT_MAXIMUM_RECORD_COUNT_FOR_CHECK = 10000;
   public static final int DEFAULT_PAGE_VALUE_COUNT_THRESHOLD = 1073741823;
   public static final int DEFAULT_COLUMN_INDEX_TRUNCATE_LENGTH = 64;
   public static final int DEFAULT_STATISTICS_TRUNCATE_LENGTH = Integer.MAX_VALUE;
   public static final int DEFAULT_PAGE_ROW_COUNT_LIMIT = 20000;
   public static final int DEFAULT_MAX_BLOOM_FILTER_BYTES = 1048576;
   public static final boolean DEFAULT_BLOOM_FILTER_ENABLED = false;
   public static final double DEFAULT_BLOOM_FILTER_FPP = 0.01;
   public static final boolean DEFAULT_ADAPTIVE_BLOOM_FILTER_ENABLED = false;
   public static final int DEFAULT_BLOOM_FILTER_CANDIDATES_NUMBER = 5;
   public static final boolean DEFAULT_STATISTICS_ENABLED = true;
   public static final boolean DEFAULT_SIZE_STATISTICS_ENABLED = true;
   public static final boolean DEFAULT_PAGE_WRITE_CHECKSUM_ENABLED = true;
   public static final ValuesWriterFactory DEFAULT_VALUES_WRITER_FACTORY;
   private static final int MIN_SLAB_SIZE = 64;
   private final int initialSlabSize;
   private final int pageSizeThreshold;
   private final int pageValueCountThreshold;
   private final int dictionaryPageSizeThreshold;
   private final WriterVersion writerVersion;
   private final ColumnProperty dictionaryEnabled;
   private final int minRowCountForPageSizeCheck;
   private final int maxRowCountForPageSizeCheck;
   private final boolean estimateNextSizeCheck;
   private final ByteBufferAllocator allocator;
   private final ValuesWriterFactory valuesWriterFactory;
   private final int columnIndexTruncateLength;
   private final int statisticsTruncateLength;
   private final boolean statisticsEnabled;
   private final boolean sizeStatisticsEnabled;
   private final ColumnProperty bloomFilterNDVs;
   private final ColumnProperty bloomFilterFPPs;
   private final int maxBloomFilterBytes;
   private final ColumnProperty bloomFilterEnabled;
   private final ColumnProperty adaptiveBloomFilterEnabled;
   private final ColumnProperty numBloomFilterCandidates;
   private final int pageRowCountLimit;
   private final boolean pageWriteChecksumEnabled;
   private final ColumnProperty byteStreamSplitEnabled;
   private final Map extraMetaData;
   private final ColumnProperty statistics;
   private final ColumnProperty sizeStatistics;

   private ParquetProperties(Builder builder) {
      this.pageSizeThreshold = builder.pageSize;
      this.pageValueCountThreshold = builder.pageValueCountThreshold;
      this.initialSlabSize = CapacityByteArrayOutputStream.initialSlabSizeHeuristic(64, this.pageSizeThreshold, 10);
      this.dictionaryPageSizeThreshold = builder.dictPageSize;
      this.writerVersion = builder.writerVersion;
      this.dictionaryEnabled = builder.enableDict.build();
      this.minRowCountForPageSizeCheck = builder.minRowCountForPageSizeCheck;
      this.maxRowCountForPageSizeCheck = builder.maxRowCountForPageSizeCheck;
      this.estimateNextSizeCheck = builder.estimateNextSizeCheck;
      this.allocator = builder.allocator;
      this.valuesWriterFactory = builder.valuesWriterFactory;
      this.columnIndexTruncateLength = builder.columnIndexTruncateLength;
      this.statisticsTruncateLength = builder.statisticsTruncateLength;
      this.statisticsEnabled = builder.statisticsEnabled;
      this.sizeStatisticsEnabled = builder.sizeStatisticsEnabled;
      this.bloomFilterNDVs = builder.bloomFilterNDVs.build();
      this.bloomFilterFPPs = builder.bloomFilterFPPs.build();
      this.bloomFilterEnabled = builder.bloomFilterEnabled.build();
      this.maxBloomFilterBytes = builder.maxBloomFilterBytes;
      this.adaptiveBloomFilterEnabled = builder.adaptiveBloomFilterEnabled.build();
      this.numBloomFilterCandidates = builder.numBloomFilterCandidates.build();
      this.pageRowCountLimit = builder.pageRowCountLimit;
      this.pageWriteChecksumEnabled = builder.pageWriteChecksumEnabled;
      this.byteStreamSplitEnabled = builder.byteStreamSplitEnabled.build();
      this.extraMetaData = builder.extraMetaData;
      this.statistics = builder.statistics.build();
      this.sizeStatistics = builder.sizeStatistics.build();
   }

   public static Builder builder() {
      return new Builder();
   }

   public static Builder copy(ParquetProperties toCopy) {
      return new Builder(toCopy);
   }

   public ValuesWriter newRepetitionLevelWriter(ColumnDescriptor path) {
      return this.newColumnDescriptorValuesWriter(path.getMaxRepetitionLevel());
   }

   public ValuesWriter newDefinitionLevelWriter(ColumnDescriptor path) {
      return this.newColumnDescriptorValuesWriter(path.getMaxDefinitionLevel());
   }

   private ValuesWriter newColumnDescriptorValuesWriter(int maxLevel) {
      return (ValuesWriter)(maxLevel == 0 ? new DevNullValuesWriter() : new RunLengthBitPackingHybridValuesWriter(BytesUtils.getWidthFromMaxInt(maxLevel), 64, this.pageSizeThreshold, this.allocator));
   }

   public RunLengthBitPackingHybridEncoder newRepetitionLevelEncoder(ColumnDescriptor path) {
      return this.newLevelEncoder(path.getMaxRepetitionLevel());
   }

   public RunLengthBitPackingHybridEncoder newDefinitionLevelEncoder(ColumnDescriptor path) {
      return this.newLevelEncoder(path.getMaxDefinitionLevel());
   }

   private RunLengthBitPackingHybridEncoder newLevelEncoder(int maxLevel) {
      return new RunLengthBitPackingHybridEncoder(BytesUtils.getWidthFromMaxInt(maxLevel), 64, this.pageSizeThreshold, this.allocator);
   }

   public ValuesWriter newValuesWriter(ColumnDescriptor path) {
      return this.valuesWriterFactory.newValuesWriter(path);
   }

   public int getPageSizeThreshold() {
      return this.pageSizeThreshold;
   }

   public int getPageValueCountThreshold() {
      return this.pageValueCountThreshold;
   }

   public int getInitialSlabSize() {
      return this.initialSlabSize;
   }

   public int getDictionaryPageSizeThreshold() {
      return this.dictionaryPageSizeThreshold;
   }

   public WriterVersion getWriterVersion() {
      return this.writerVersion;
   }

   /** @deprecated */
   @Deprecated
   public boolean isEnableDictionary() {
      return (Boolean)this.dictionaryEnabled.getDefaultValue();
   }

   public boolean isDictionaryEnabled(ColumnDescriptor column) {
      return (Boolean)this.dictionaryEnabled.getValue(column);
   }

   /** @deprecated */
   @Deprecated
   public boolean isByteStreamSplitEnabled() {
      return this.byteStreamSplitEnabled.getDefaultValue() != ParquetProperties.ByteStreamSplitMode.NONE;
   }

   public boolean isByteStreamSplitEnabled(ColumnDescriptor column) {
      switch (column.getPrimitiveType().getPrimitiveTypeName()) {
         case FLOAT:
         case DOUBLE:
            return this.byteStreamSplitEnabled.getValue(column) != ParquetProperties.ByteStreamSplitMode.NONE;
         case INT32:
         case INT64:
         case FIXED_LEN_BYTE_ARRAY:
            return this.byteStreamSplitEnabled.getValue(column) == ParquetProperties.ByteStreamSplitMode.EXTENDED;
         default:
            return false;
      }
   }

   public ByteBufferAllocator getAllocator() {
      return this.allocator;
   }

   public ColumnWriteStore newColumnWriteStore(MessageType schema, PageWriteStore pageStore) {
      switch (this.writerVersion) {
         case PARQUET_1_0:
            return new ColumnWriteStoreV1(schema, pageStore, this);
         case PARQUET_2_0:
            return new ColumnWriteStoreV2(schema, pageStore, this);
         default:
            throw new IllegalArgumentException("unknown version " + this.writerVersion);
      }
   }

   public ColumnWriteStore newColumnWriteStore(MessageType schema, PageWriteStore pageStore, BloomFilterWriteStore bloomFilterWriteStore) {
      switch (this.writerVersion) {
         case PARQUET_1_0:
            return new ColumnWriteStoreV1(schema, pageStore, bloomFilterWriteStore, this);
         case PARQUET_2_0:
            return new ColumnWriteStoreV2(schema, pageStore, bloomFilterWriteStore, this);
         default:
            throw new IllegalArgumentException("unknown version " + this.writerVersion);
      }
   }

   public int getMinRowCountForPageSizeCheck() {
      return this.minRowCountForPageSizeCheck;
   }

   public int getMaxRowCountForPageSizeCheck() {
      return this.maxRowCountForPageSizeCheck;
   }

   public ValuesWriterFactory getValuesWriterFactory() {
      return this.valuesWriterFactory;
   }

   public int getColumnIndexTruncateLength() {
      return this.columnIndexTruncateLength;
   }

   public int getStatisticsTruncateLength() {
      return this.statisticsTruncateLength;
   }

   public boolean estimateNextSizeCheck() {
      return this.estimateNextSizeCheck;
   }

   public int getPageRowCountLimit() {
      return this.pageRowCountLimit;
   }

   public boolean getPageWriteChecksumEnabled() {
      return this.pageWriteChecksumEnabled;
   }

   public OptionalLong getBloomFilterNDV(ColumnDescriptor column) {
      Long ndv = (Long)this.bloomFilterNDVs.getValue(column);
      return ndv == null ? OptionalLong.empty() : OptionalLong.of(ndv);
   }

   public OptionalDouble getBloomFilterFPP(ColumnDescriptor column) {
      Double fpp = (Double)this.bloomFilterFPPs.getValue(column);
      return fpp == null ? OptionalDouble.empty() : OptionalDouble.of(fpp);
   }

   public boolean isBloomFilterEnabled(ColumnDescriptor column) {
      return (Boolean)this.bloomFilterEnabled.getValue(column);
   }

   public int getMaxBloomFilterBytes() {
      return this.maxBloomFilterBytes;
   }

   public boolean getAdaptiveBloomFilterEnabled(ColumnDescriptor column) {
      return (Boolean)this.adaptiveBloomFilterEnabled.getValue(column);
   }

   public int getBloomFilterCandidatesCount(ColumnDescriptor column) {
      return (Integer)this.numBloomFilterCandidates.getValue(column);
   }

   public Map getExtraMetaData() {
      return this.extraMetaData;
   }

   public boolean getStatisticsEnabled(ColumnDescriptor column) {
      Boolean columnSetting = (Boolean)this.statistics.getValue(column);
      return columnSetting != null ? columnSetting : this.statisticsEnabled;
   }

   public boolean getSizeStatisticsEnabled(ColumnDescriptor column) {
      Boolean columnSetting = (Boolean)this.sizeStatistics.getValue(column);
      return columnSetting != null ? columnSetting : this.sizeStatisticsEnabled;
   }

   public String toString() {
      return "Parquet page size to " + this.getPageSizeThreshold() + '\n' + "Parquet dictionary page size to " + this.getDictionaryPageSizeThreshold() + '\n' + "Dictionary is " + this.dictionaryEnabled + '\n' + "Writer version is: " + this.getWriterVersion() + '\n' + "Page size checking is: " + (this.estimateNextSizeCheck() ? "estimated" : "constant") + '\n' + "Min row count for page size check is: " + this.getMinRowCountForPageSizeCheck() + '\n' + "Max row count for page size check is: " + this.getMaxRowCountForPageSizeCheck() + '\n' + "Truncate length for column indexes is: " + this.getColumnIndexTruncateLength() + '\n' + "Truncate length for statistics min/max  is: " + this.getStatisticsTruncateLength() + '\n' + "Bloom filter enabled: " + this.bloomFilterEnabled + '\n' + "Max Bloom filter size for a column is " + this.getMaxBloomFilterBytes() + '\n' + "Bloom filter expected number of distinct values are: " + this.bloomFilterNDVs + '\n' + "Bloom filter false positive probabilities are: " + this.bloomFilterFPPs + '\n' + "Page row count limit to " + this.getPageRowCountLimit() + '\n' + "Writing page checksums is: " + (this.getPageWriteChecksumEnabled() ? "on" : "off") + '\n' + "Statistics enabled: " + this.statisticsEnabled + '\n' + "Size statistics enabled: " + this.sizeStatisticsEnabled;
   }

   static {
      DEFAULT_WRITER_VERSION = ParquetProperties.WriterVersion.PARQUET_1_0;
      DEFAULT_VALUES_WRITER_FACTORY = new DefaultValuesWriterFactory();
   }

   private static enum ByteStreamSplitMode {
      NONE,
      FLOATING_POINT,
      EXTENDED;
   }

   public static enum WriterVersion {
      PARQUET_1_0("v1"),
      PARQUET_2_0("v2");

      private final String shortName;

      private WriterVersion(String shortname) {
         this.shortName = shortname;
      }

      public static WriterVersion fromString(String name) {
         for(WriterVersion v : values()) {
            if (v.shortName.equals(name)) {
               return v;
            }
         }

         return valueOf(name);
      }
   }

   public static class Builder {
      private int pageSize;
      private int dictPageSize;
      private final ColumnProperty.Builder enableDict;
      private WriterVersion writerVersion;
      private int minRowCountForPageSizeCheck;
      private int maxRowCountForPageSizeCheck;
      private int pageValueCountThreshold;
      private boolean estimateNextSizeCheck;
      private ByteBufferAllocator allocator;
      private ValuesWriterFactory valuesWriterFactory;
      private int columnIndexTruncateLength;
      private int statisticsTruncateLength;
      private boolean statisticsEnabled;
      private boolean sizeStatisticsEnabled;
      private final ColumnProperty.Builder bloomFilterNDVs;
      private final ColumnProperty.Builder bloomFilterFPPs;
      private int maxBloomFilterBytes;
      private final ColumnProperty.Builder adaptiveBloomFilterEnabled;
      private final ColumnProperty.Builder numBloomFilterCandidates;
      private final ColumnProperty.Builder bloomFilterEnabled;
      private int pageRowCountLimit;
      private boolean pageWriteChecksumEnabled;
      private final ColumnProperty.Builder byteStreamSplitEnabled;
      private Map extraMetaData;
      private final ColumnProperty.Builder statistics;
      private final ColumnProperty.Builder sizeStatistics;

      private Builder() {
         this.pageSize = 1048576;
         this.dictPageSize = 1048576;
         this.writerVersion = ParquetProperties.DEFAULT_WRITER_VERSION;
         this.minRowCountForPageSizeCheck = 100;
         this.maxRowCountForPageSizeCheck = 10000;
         this.pageValueCountThreshold = 1073741823;
         this.estimateNextSizeCheck = true;
         this.allocator = new HeapByteBufferAllocator();
         this.valuesWriterFactory = ParquetProperties.DEFAULT_VALUES_WRITER_FACTORY;
         this.columnIndexTruncateLength = 64;
         this.statisticsTruncateLength = Integer.MAX_VALUE;
         this.statisticsEnabled = true;
         this.sizeStatisticsEnabled = true;
         this.maxBloomFilterBytes = 1048576;
         this.pageRowCountLimit = 20000;
         this.pageWriteChecksumEnabled = true;
         this.extraMetaData = new HashMap();
         this.enableDict = ColumnProperty.builder().withDefaultValue(true);
         this.byteStreamSplitEnabled = ColumnProperty.builder().withDefaultValue(ParquetProperties.ByteStreamSplitMode.NONE);
         this.bloomFilterEnabled = ColumnProperty.builder().withDefaultValue(false);
         this.bloomFilterNDVs = ColumnProperty.builder().withDefaultValue((Object)null);
         this.bloomFilterFPPs = ColumnProperty.builder().withDefaultValue(0.01);
         this.adaptiveBloomFilterEnabled = ColumnProperty.builder().withDefaultValue(false);
         this.numBloomFilterCandidates = ColumnProperty.builder().withDefaultValue(5);
         this.statistics = ColumnProperty.builder().withDefaultValue(true);
         this.sizeStatistics = ColumnProperty.builder().withDefaultValue(true);
      }

      private Builder(ParquetProperties toCopy) {
         this.pageSize = 1048576;
         this.dictPageSize = 1048576;
         this.writerVersion = ParquetProperties.DEFAULT_WRITER_VERSION;
         this.minRowCountForPageSizeCheck = 100;
         this.maxRowCountForPageSizeCheck = 10000;
         this.pageValueCountThreshold = 1073741823;
         this.estimateNextSizeCheck = true;
         this.allocator = new HeapByteBufferAllocator();
         this.valuesWriterFactory = ParquetProperties.DEFAULT_VALUES_WRITER_FACTORY;
         this.columnIndexTruncateLength = 64;
         this.statisticsTruncateLength = Integer.MAX_VALUE;
         this.statisticsEnabled = true;
         this.sizeStatisticsEnabled = true;
         this.maxBloomFilterBytes = 1048576;
         this.pageRowCountLimit = 20000;
         this.pageWriteChecksumEnabled = true;
         this.extraMetaData = new HashMap();
         this.pageSize = toCopy.pageSizeThreshold;
         this.enableDict = ColumnProperty.builder(toCopy.dictionaryEnabled);
         this.dictPageSize = toCopy.dictionaryPageSizeThreshold;
         this.writerVersion = toCopy.writerVersion;
         this.minRowCountForPageSizeCheck = toCopy.minRowCountForPageSizeCheck;
         this.maxRowCountForPageSizeCheck = toCopy.maxRowCountForPageSizeCheck;
         this.estimateNextSizeCheck = toCopy.estimateNextSizeCheck;
         this.valuesWriterFactory = toCopy.valuesWriterFactory;
         this.allocator = toCopy.allocator;
         this.pageRowCountLimit = toCopy.pageRowCountLimit;
         this.pageWriteChecksumEnabled = toCopy.pageWriteChecksumEnabled;
         this.bloomFilterNDVs = ColumnProperty.builder(toCopy.bloomFilterNDVs);
         this.bloomFilterFPPs = ColumnProperty.builder(toCopy.bloomFilterFPPs);
         this.bloomFilterEnabled = ColumnProperty.builder(toCopy.bloomFilterEnabled);
         this.adaptiveBloomFilterEnabled = ColumnProperty.builder(toCopy.adaptiveBloomFilterEnabled);
         this.numBloomFilterCandidates = ColumnProperty.builder(toCopy.numBloomFilterCandidates);
         this.maxBloomFilterBytes = toCopy.maxBloomFilterBytes;
         this.byteStreamSplitEnabled = ColumnProperty.builder(toCopy.byteStreamSplitEnabled);
         this.extraMetaData = toCopy.extraMetaData;
         this.statistics = ColumnProperty.builder(toCopy.statistics);
         this.sizeStatistics = ColumnProperty.builder(toCopy.sizeStatistics);
      }

      public Builder withPageSize(int pageSize) {
         Preconditions.checkArgument(pageSize > 0, "Invalid page size (negative): %s", pageSize);
         this.pageSize = pageSize;
         return this;
      }

      public Builder withDictionaryEncoding(boolean enableDictionary) {
         this.enableDict.withDefaultValue(enableDictionary);
         return this;
      }

      public Builder withDictionaryEncoding(String columnPath, boolean enableDictionary) {
         this.enableDict.withValue((String)columnPath, enableDictionary);
         return this;
      }

      public Builder withByteStreamSplitEncoding(boolean enable) {
         this.byteStreamSplitEnabled.withDefaultValue(enable ? ParquetProperties.ByteStreamSplitMode.FLOATING_POINT : ParquetProperties.ByteStreamSplitMode.NONE);
         return this;
      }

      public Builder withByteStreamSplitEncoding(String columnPath, boolean enable) {
         this.byteStreamSplitEnabled.withValue((String)columnPath, enable ? ParquetProperties.ByteStreamSplitMode.EXTENDED : ParquetProperties.ByteStreamSplitMode.NONE);
         return this;
      }

      public Builder withExtendedByteStreamSplitEncoding(boolean enable) {
         this.byteStreamSplitEnabled.withDefaultValue(enable ? ParquetProperties.ByteStreamSplitMode.EXTENDED : ParquetProperties.ByteStreamSplitMode.NONE);
         return this;
      }

      public Builder withDictionaryPageSize(int dictionaryPageSize) {
         Preconditions.checkArgument(dictionaryPageSize > 0, "Invalid dictionary page size (negative): %s", dictionaryPageSize);
         this.dictPageSize = dictionaryPageSize;
         return this;
      }

      public Builder withWriterVersion(WriterVersion version) {
         this.writerVersion = version;
         return this;
      }

      public Builder withMinRowCountForPageSizeCheck(int min) {
         Preconditions.checkArgument(min > 0, "Invalid row count for page size check (negative): %s", min);
         this.minRowCountForPageSizeCheck = min;
         return this;
      }

      public Builder withMaxRowCountForPageSizeCheck(int max) {
         Preconditions.checkArgument(max > 0, "Invalid row count for page size check (negative): %s", max);
         this.maxRowCountForPageSizeCheck = max;
         return this;
      }

      public Builder withPageValueCountThreshold(int value) {
         Preconditions.checkArgument(value > 0, "Invalid page value count threshold (negative): %s", value);
         this.pageValueCountThreshold = value;
         return this;
      }

      public Builder estimateRowCountForPageSizeCheck(boolean estimateNextSizeCheck) {
         this.estimateNextSizeCheck = estimateNextSizeCheck;
         return this;
      }

      public Builder withAllocator(ByteBufferAllocator allocator) {
         this.allocator = (ByteBufferAllocator)Objects.requireNonNull(allocator, "ByteBufferAllocator cannot be null");
         return this;
      }

      public Builder withValuesWriterFactory(ValuesWriterFactory factory) {
         this.valuesWriterFactory = (ValuesWriterFactory)Objects.requireNonNull(factory, "ValuesWriterFactory cannot be null");
         return this;
      }

      public Builder withColumnIndexTruncateLength(int length) {
         Preconditions.checkArgument(length > 0, "Invalid column index min/max truncate length (negative or zero) : %s", length);
         this.columnIndexTruncateLength = length;
         return this;
      }

      public Builder withStatisticsTruncateLength(int length) {
         Preconditions.checkArgument(length > 0, "Invalid statistics min/max truncate length (negative or zero) : %s", length);
         this.statisticsTruncateLength = length;
         return this;
      }

      public Builder withMaxBloomFilterBytes(int maxBloomFilterBytes) {
         this.maxBloomFilterBytes = maxBloomFilterBytes;
         return this;
      }

      public Builder withBloomFilterNDV(String columnPath, long ndv) {
         Preconditions.checkArgument(ndv > 0L, "Invalid NDV for column \"%s\": %s", columnPath, ndv);
         this.bloomFilterNDVs.withValue((String)columnPath, ndv);
         this.bloomFilterEnabled.withValue((String)columnPath, true);
         return this;
      }

      public Builder withBloomFilterFPP(String columnPath, double fpp) {
         Preconditions.checkArgument(fpp > (double)0.0F && fpp < (double)1.0F, "Invalid FPP for column \"%s\": %s", columnPath, fpp);
         this.bloomFilterFPPs.withValue((String)columnPath, fpp);
         return this;
      }

      public Builder withBloomFilterEnabled(boolean enabled) {
         this.bloomFilterEnabled.withDefaultValue(enabled);
         return this;
      }

      public Builder withAdaptiveBloomFilterEnabled(boolean enabled) {
         this.adaptiveBloomFilterEnabled.withDefaultValue(enabled);
         return this;
      }

      public Builder withBloomFilterCandidatesNumber(String columnPath, int number) {
         Preconditions.checkArgument(number > 0, "Invalid candidates number for column \"%s\": %d", columnPath, number);
         this.numBloomFilterCandidates.withDefaultValue(number);
         return this;
      }

      public Builder withBloomFilterEnabled(String columnPath, boolean enabled) {
         this.bloomFilterEnabled.withValue((String)columnPath, enabled);
         return this;
      }

      public Builder withPageRowCountLimit(int rowCount) {
         Preconditions.checkArgument(rowCount > 0, "Invalid row count limit for pages: %s", rowCount);
         this.pageRowCountLimit = rowCount;
         return this;
      }

      public Builder withPageWriteChecksumEnabled(boolean val) {
         this.pageWriteChecksumEnabled = val;
         return this;
      }

      public Builder withExtraMetaData(Map extraMetaData) {
         this.extraMetaData = extraMetaData;
         return this;
      }

      public Builder withStatisticsEnabled(String columnPath, boolean enabled) {
         this.statistics.withValue((String)columnPath, enabled);
         return this;
      }

      public Builder withStatisticsEnabled(boolean enabled) {
         this.statisticsEnabled = enabled;
         return this;
      }

      public Builder withSizeStatisticsEnabled(boolean enabled) {
         this.sizeStatistics.withDefaultValue(enabled);
         return this;
      }

      public Builder withSizeStatisticsEnabled(String columnPath, boolean enabled) {
         this.sizeStatistics.withValue((String)columnPath, enabled);
         return this;
      }

      public ParquetProperties build() {
         ParquetProperties properties = new ParquetProperties(this);
         this.valuesWriterFactory.initialize(properties);
         return properties;
      }
   }
}
