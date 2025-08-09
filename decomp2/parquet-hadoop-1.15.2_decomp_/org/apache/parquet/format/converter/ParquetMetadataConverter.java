package org.apache.parquet.format.converter;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.CorruptStatistics;
import org.apache.parquet.ParquetReadOptions;
import org.apache.parquet.Preconditions;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.EncodingStats;
import org.apache.parquet.column.statistics.BinaryStatistics;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.column.values.bloomfilter.BloomFilter.Algorithm;
import org.apache.parquet.column.values.bloomfilter.BloomFilter.Compression;
import org.apache.parquet.column.values.bloomfilter.BloomFilter.HashStrategy;
import org.apache.parquet.crypto.AesCipher;
import org.apache.parquet.crypto.AesGcmEncryptor;
import org.apache.parquet.crypto.InternalColumnEncryptionSetup;
import org.apache.parquet.crypto.InternalFileDecryptor;
import org.apache.parquet.crypto.InternalFileEncryptor;
import org.apache.parquet.crypto.ModuleCipherFactory;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import org.apache.parquet.crypto.TagVerificationException;
import org.apache.parquet.format.BlockCipher;
import org.apache.parquet.format.BloomFilterAlgorithm;
import org.apache.parquet.format.BloomFilterCompression;
import org.apache.parquet.format.BloomFilterHash;
import org.apache.parquet.format.BloomFilterHeader;
import org.apache.parquet.format.BoundaryOrder;
import org.apache.parquet.format.BsonType;
import org.apache.parquet.format.ColumnChunk;
import org.apache.parquet.format.ColumnCryptoMetaData;
import org.apache.parquet.format.ColumnIndex;
import org.apache.parquet.format.ColumnMetaData;
import org.apache.parquet.format.ColumnOrder;
import org.apache.parquet.format.CompressionCodec;
import org.apache.parquet.format.ConvertedType;
import org.apache.parquet.format.DataPageHeader;
import org.apache.parquet.format.DataPageHeaderV2;
import org.apache.parquet.format.DateType;
import org.apache.parquet.format.DecimalType;
import org.apache.parquet.format.DictionaryPageHeader;
import org.apache.parquet.format.EncryptionWithColumnKey;
import org.apache.parquet.format.EnumType;
import org.apache.parquet.format.FieldRepetitionType;
import org.apache.parquet.format.FileMetaData;
import org.apache.parquet.format.Float16Type;
import org.apache.parquet.format.IntType;
import org.apache.parquet.format.JsonType;
import org.apache.parquet.format.KeyValue;
import org.apache.parquet.format.ListType;
import org.apache.parquet.format.LogicalType;
import org.apache.parquet.format.MapType;
import org.apache.parquet.format.MicroSeconds;
import org.apache.parquet.format.MilliSeconds;
import org.apache.parquet.format.NanoSeconds;
import org.apache.parquet.format.NullType;
import org.apache.parquet.format.OffsetIndex;
import org.apache.parquet.format.PageEncodingStats;
import org.apache.parquet.format.PageHeader;
import org.apache.parquet.format.PageLocation;
import org.apache.parquet.format.PageType;
import org.apache.parquet.format.RowGroup;
import org.apache.parquet.format.SchemaElement;
import org.apache.parquet.format.SplitBlockAlgorithm;
import org.apache.parquet.format.Statistics;
import org.apache.parquet.format.StringType;
import org.apache.parquet.format.TimeType;
import org.apache.parquet.format.TimeUnit;
import org.apache.parquet.format.TimestampType;
import org.apache.parquet.format.TypeDefinedOrder;
import org.apache.parquet.format.UUIDType;
import org.apache.parquet.format.Uncompressed;
import org.apache.parquet.format.Util;
import org.apache.parquet.format.XxHash;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.internal.column.columnindex.BinaryTruncator;
import org.apache.parquet.internal.column.columnindex.ColumnIndexBuilder;
import org.apache.parquet.internal.column.columnindex.OffsetIndexBuilder;
import org.apache.parquet.internal.hadoop.metadata.IndexReference;
import org.apache.parquet.io.InvalidFileOffsetException;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.LogicalTypeAnnotation;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.OriginalType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;
import org.apache.parquet.schema.TypeVisitor;
import org.apache.parquet.schema.Types;
import org.apache.parquet.schema.ColumnOrder.ColumnOrderName;
import org.apache.parquet.schema.LogicalTypeAnnotation.IntervalLogicalTypeAnnotation;
import org.apache.parquet.schema.LogicalTypeAnnotation.MapKeyValueTypeAnnotation;
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName;
import org.apache.parquet.schema.Type.Repetition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParquetMetadataConverter {
   private static final TypeDefinedOrder TYPE_DEFINED_ORDER = new TypeDefinedOrder();
   public static final MetadataFilter NO_FILTER = new NoFilter();
   public static final MetadataFilter SKIP_ROW_GROUPS = new SkipMetadataFilter();
   public static final long MAX_STATS_SIZE = 4096L;
   private static final Logger LOG = LoggerFactory.getLogger(ParquetMetadataConverter.class);
   private static final LogicalTypeConverterVisitor LOGICAL_TYPE_ANNOTATION_VISITOR = new LogicalTypeConverterVisitor();
   private static final ConvertedTypeConverterVisitor CONVERTED_TYPE_CONVERTER_VISITOR = new ConvertedTypeConverterVisitor();
   private final int statisticsTruncateLength;
   private final boolean useSignedStringMinMax;
   private static final ConcurrentHashMap cachedEncodingSets = new ConcurrentHashMap();
   private static final Set STRING_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(LogicalTypeAnnotation.StringLogicalTypeAnnotation.class, LogicalTypeAnnotation.EnumLogicalTypeAnnotation.class, LogicalTypeAnnotation.JsonLogicalTypeAnnotation.class, LogicalTypeAnnotation.Float16LogicalTypeAnnotation.class)));

   public ParquetMetadataConverter() {
      this(false);
   }

   public ParquetMetadataConverter(int statisticsTruncateLength) {
      this(false, statisticsTruncateLength);
   }

   /** @deprecated */
   @Deprecated
   public ParquetMetadataConverter(Configuration conf) {
      this(conf.getBoolean("parquet.strings.signed-min-max.enabled", false));
   }

   public ParquetMetadataConverter(ParquetReadOptions options) {
      this(options.useSignedStringMinMax());
   }

   private ParquetMetadataConverter(boolean useSignedStringMinMax) {
      this(useSignedStringMinMax, Integer.MAX_VALUE);
   }

   private ParquetMetadataConverter(boolean useSignedStringMinMax, int statisticsTruncateLength) {
      if (statisticsTruncateLength <= 0) {
         throw new IllegalArgumentException("Truncate length should be greater than 0");
      } else {
         this.useSignedStringMinMax = useSignedStringMinMax;
         this.statisticsTruncateLength = statisticsTruncateLength;
      }
   }

   public FileMetaData toParquetMetadata(int currentVersion, ParquetMetadata parquetMetadata) {
      return this.toParquetMetadata(currentVersion, parquetMetadata, (InternalFileEncryptor)null);
   }

   public FileMetaData toParquetMetadata(int currentVersion, ParquetMetadata parquetMetadata, InternalFileEncryptor fileEncryptor) {
      List<BlockMetaData> blocks = parquetMetadata.getBlocks();
      List<RowGroup> rowGroups = new ArrayList();
      long numRows = 0L;
      long preBlockStartPos = 0L;
      long preBlockCompressedSize = 0L;

      for(BlockMetaData block : blocks) {
         numRows += block.getRowCount();
         long blockStartPos = block.getStartingPos();
         if (blockStartPos == 4L) {
            preBlockStartPos = 0L;
            preBlockCompressedSize = 0L;
         }

         if (preBlockStartPos != 0L) {
            Preconditions.checkState(blockStartPos >= preBlockStartPos + preBlockCompressedSize, "Invalid block starting position: %s", blockStartPos);
         }

         preBlockStartPos = blockStartPos;
         preBlockCompressedSize = block.getCompressedSize();
         this.addRowGroup(parquetMetadata, rowGroups, block, fileEncryptor);
      }

      FileMetaData fileMetaData = new FileMetaData(currentVersion, this.toParquetSchema(parquetMetadata.getFileMetaData().getSchema()), numRows, rowGroups);

      for(Map.Entry keyValue : parquetMetadata.getFileMetaData().getKeyValueMetaData().entrySet()) {
         addKeyValue(fileMetaData, (String)keyValue.getKey(), (String)keyValue.getValue());
      }

      fileMetaData.setCreated_by(parquetMetadata.getFileMetaData().getCreatedBy());
      fileMetaData.setColumn_orders(this.getColumnOrders(parquetMetadata.getFileMetaData().getSchema()));
      return fileMetaData;
   }

   private List getColumnOrders(MessageType schema) {
      List<ColumnOrder> columnOrders = new ArrayList();
      int i = 0;

      for(int n = schema.getPaths().size(); i < n; ++i) {
         ColumnOrder columnOrder = new ColumnOrder();
         columnOrder.setTYPE_ORDER(TYPE_DEFINED_ORDER);
         columnOrders.add(columnOrder);
      }

      return columnOrders;
   }

   List toParquetSchema(MessageType schema) {
      List<SchemaElement> result = new ArrayList();
      this.addToList(result, schema);
      return result;
   }

   private void addToList(final List result, Type field) {
      field.accept(new TypeVisitor() {
         public void visit(PrimitiveType primitiveType) {
            SchemaElement element = new SchemaElement(primitiveType.getName());
            element.setRepetition_type(ParquetMetadataConverter.this.toParquetRepetition(primitiveType.getRepetition()));
            element.setType(ParquetMetadataConverter.this.getType(primitiveType.getPrimitiveTypeName()));
            if (primitiveType.getLogicalTypeAnnotation() != null) {
               element.setConverted_type(ParquetMetadataConverter.this.convertToConvertedType(primitiveType.getLogicalTypeAnnotation()));
               element.setLogicalType(ParquetMetadataConverter.this.convertToLogicalType(primitiveType.getLogicalTypeAnnotation()));
            }

            if (primitiveType.getDecimalMetadata() != null) {
               element.setPrecision(primitiveType.getDecimalMetadata().getPrecision());
               element.setScale(primitiveType.getDecimalMetadata().getScale());
            }

            if (primitiveType.getTypeLength() > 0) {
               element.setType_length(primitiveType.getTypeLength());
            }

            if (primitiveType.getId() != null) {
               element.setField_id(primitiveType.getId().intValue());
            }

            result.add(element);
         }

         public void visit(MessageType messageType) {
            SchemaElement element = new SchemaElement(messageType.getName());
            if (messageType.getId() != null) {
               element.setField_id(messageType.getId().intValue());
            }

            this.visitChildren(result, messageType.asGroupType(), element);
         }

         public void visit(GroupType groupType) {
            SchemaElement element = new SchemaElement(groupType.getName());
            element.setRepetition_type(ParquetMetadataConverter.this.toParquetRepetition(groupType.getRepetition()));
            if (groupType.getLogicalTypeAnnotation() != null) {
               element.setConverted_type(ParquetMetadataConverter.this.convertToConvertedType(groupType.getLogicalTypeAnnotation()));
               element.setLogicalType(ParquetMetadataConverter.this.convertToLogicalType(groupType.getLogicalTypeAnnotation()));
            }

            if (groupType.getId() != null) {
               element.setField_id(groupType.getId().intValue());
            }

            this.visitChildren(result, groupType, element);
         }

         private void visitChildren(List resultx, GroupType groupType, SchemaElement element) {
            element.setNum_children(groupType.getFieldCount());
            result.add(element);

            for(Type field : groupType.getFields()) {
               ParquetMetadataConverter.this.addToList(result, field);
            }

         }
      });
   }

   LogicalType convertToLogicalType(LogicalTypeAnnotation logicalTypeAnnotation) {
      return (LogicalType)logicalTypeAnnotation.accept(LOGICAL_TYPE_ANNOTATION_VISITOR).orElse((Object)null);
   }

   ConvertedType convertToConvertedType(LogicalTypeAnnotation logicalTypeAnnotation) {
      return (ConvertedType)logicalTypeAnnotation.accept(CONVERTED_TYPE_CONVERTER_VISITOR).orElse((Object)null);
   }

   static TimeUnit convertUnit(LogicalTypeAnnotation.TimeUnit unit) {
      switch (unit) {
         case MICROS:
            return TimeUnit.MICROS(new MicroSeconds());
         case MILLIS:
            return TimeUnit.MILLIS(new MilliSeconds());
         case NANOS:
            return TimeUnit.NANOS(new NanoSeconds());
         default:
            throw new RuntimeException("Unknown time unit " + unit);
      }
   }

   private void addRowGroup(ParquetMetadata parquetMetadata, List rowGroups, BlockMetaData block, InternalFileEncryptor fileEncryptor) {
      List<ColumnChunkMetaData> columns = block.getColumns();
      List<ColumnChunk> parquetColumns = new ArrayList();
      int rowGroupOrdinal = rowGroups.size();
      int columnOrdinal = -1;
      ByteArrayOutputStream tempOutStream = null;

      for(ColumnChunkMetaData columnMetaData : columns) {
         ColumnChunk columnChunk = new ColumnChunk(0L);
         columnChunk.file_path = block.getPath();
         InternalColumnEncryptionSetup columnSetup = null;
         boolean writeCryptoMetadata = false;
         boolean encryptMetaData = false;
         ColumnPath path = columnMetaData.getPath();
         if (null != fileEncryptor) {
            ++columnOrdinal;
            columnSetup = fileEncryptor.getColumnSetup(path, false, columnOrdinal);
            writeCryptoMetadata = columnSetup.isEncrypted();
            encryptMetaData = fileEncryptor.encryptColumnMetaData(columnSetup);
         }

         ColumnMetaData metaData = new ColumnMetaData(this.getType(columnMetaData.getType()), this.toFormatEncodings(columnMetaData.getEncodings()), columnMetaData.getPath().toList(), this.toFormatCodec(columnMetaData.getCodec()), columnMetaData.getValueCount(), columnMetaData.getTotalUncompressedSize(), columnMetaData.getTotalSize(), columnMetaData.getFirstDataPageOffset());
         if (columnMetaData.getEncodingStats() != null && columnMetaData.getEncodingStats().hasDictionaryPages() || columnMetaData.hasDictionaryPage()) {
            metaData.setDictionary_page_offset(columnMetaData.getDictionaryPageOffset());
         }

         long bloomFilterOffset = columnMetaData.getBloomFilterOffset();
         if (bloomFilterOffset >= 0L) {
            metaData.setBloom_filter_offset(bloomFilterOffset);
         }

         int bloomFilterLength = columnMetaData.getBloomFilterLength();
         if (bloomFilterLength >= 0) {
            metaData.setBloom_filter_length(bloomFilterLength);
         }

         if (columnMetaData.getStatistics() != null && !columnMetaData.getStatistics().isEmpty()) {
            metaData.setStatistics(toParquetStatistics(columnMetaData.getStatistics(), this.statisticsTruncateLength));
         }

         if (columnMetaData.getEncodingStats() != null) {
            metaData.setEncoding_stats(this.convertEncodingStats(columnMetaData.getEncodingStats()));
         }

         if (columnMetaData.getSizeStatistics() != null && columnMetaData.getSizeStatistics().isValid()) {
            metaData.setSize_statistics(toParquetSizeStatistics(columnMetaData.getSizeStatistics()));
         }

         if (!encryptMetaData) {
            columnChunk.setMeta_data(metaData);
         } else {
            byte[] columnMetaDataAAD = AesCipher.createModuleAAD(fileEncryptor.getFileAAD(), ModuleCipherFactory.ModuleType.ColumnMetaData, rowGroupOrdinal, columnSetup.getOrdinal(), -1);
            if (null == tempOutStream) {
               tempOutStream = new ByteArrayOutputStream();
            } else {
               tempOutStream.reset();
            }

            try {
               Util.writeColumnMetaData(metaData, tempOutStream, columnSetup.getMetaDataEncryptor(), columnMetaDataAAD);
            } catch (IOException e) {
               throw new ParquetCryptoRuntimeException("Failed to serialize and encrypt ColumnMetadata for " + columnMetaData.getPath(), e);
            }

            columnChunk.setEncrypted_column_metadata(tempOutStream.toByteArray());
            if (!fileEncryptor.isFooterEncrypted()) {
               ColumnMetaData metaDataRedacted = metaData.deepCopy();
               if (metaDataRedacted.isSetStatistics()) {
                  metaDataRedacted.unsetStatistics();
               }

               if (metaDataRedacted.isSetEncoding_stats()) {
                  metaDataRedacted.unsetEncoding_stats();
               }

               columnChunk.setMeta_data(metaDataRedacted);
            }
         }

         if (writeCryptoMetadata) {
            columnChunk.setCrypto_metadata(columnSetup.getColumnCryptoMetaData());
         }

         IndexReference columnIndexRef = columnMetaData.getColumnIndexReference();
         if (columnIndexRef != null) {
            columnChunk.setColumn_index_offset(columnIndexRef.getOffset());
            columnChunk.setColumn_index_length(columnIndexRef.getLength());
         }

         IndexReference offsetIndexRef = columnMetaData.getOffsetIndexReference();
         if (offsetIndexRef != null) {
            columnChunk.setOffset_index_offset(offsetIndexRef.getOffset());
            columnChunk.setOffset_index_length(offsetIndexRef.getLength());
         }

         parquetColumns.add(columnChunk);
      }

      RowGroup rowGroup = new RowGroup(parquetColumns, block.getTotalByteSize(), block.getRowCount());
      rowGroup.setFile_offset(block.getStartingPos());
      rowGroup.setTotal_compressed_size(block.getCompressedSize());
      rowGroup.setOrdinal((short)rowGroupOrdinal);
      rowGroups.add(rowGroup);
   }

   private List toFormatEncodings(Set encodings) {
      List<org.apache.parquet.format.Encoding> converted = new ArrayList(encodings.size());

      for(Encoding encoding : encodings) {
         converted.add(this.getEncoding(encoding));
      }

      return converted;
   }

   Set fromFormatEncodings(List encodings) {
      Set<Encoding> converted = new HashSet();

      for(org.apache.parquet.format.Encoding encoding : encodings) {
         converted.add(this.getEncoding(encoding));
      }

      converted = Collections.unmodifiableSet(converted);
      Set<Encoding> cached = (Set)cachedEncodingSets.putIfAbsent(converted, converted);
      if (cached == null) {
         cached = converted;
      }

      return cached;
   }

   private CompressionCodecName fromFormatCodec(CompressionCodec codec) {
      return CompressionCodecName.valueOf(codec.toString());
   }

   private CompressionCodec toFormatCodec(CompressionCodecName codec) {
      return CompressionCodec.valueOf(codec.toString());
   }

   public Encoding getEncoding(org.apache.parquet.format.Encoding encoding) {
      return Encoding.valueOf(encoding.name());
   }

   public org.apache.parquet.format.Encoding getEncoding(Encoding encoding) {
      return org.apache.parquet.format.Encoding.valueOf(encoding.name());
   }

   public EncodingStats convertEncodingStats(List stats) {
      if (stats == null) {
         return null;
      } else {
         EncodingStats.Builder builder = new EncodingStats.Builder();

         for(PageEncodingStats stat : stats) {
            switch (stat.getPage_type()) {
               case DATA_PAGE_V2:
                  builder.withV2Pages();
               case DATA_PAGE:
                  builder.addDataEncoding(this.getEncoding(stat.getEncoding()), stat.getCount());
                  break;
               case DICTIONARY_PAGE:
                  builder.addDictEncoding(this.getEncoding(stat.getEncoding()), stat.getCount());
            }
         }

         return builder.build();
      }
   }

   public List convertEncodingStats(EncodingStats stats) {
      if (stats == null) {
         return null;
      } else {
         List<PageEncodingStats> formatStats = new ArrayList();

         for(Encoding encoding : stats.getDictionaryEncodings()) {
            formatStats.add(new PageEncodingStats(PageType.DICTIONARY_PAGE, this.getEncoding(encoding), stats.getNumDictionaryPagesEncodedAs(encoding)));
         }

         PageType dataPageType = stats.usesV2Pages() ? PageType.DATA_PAGE_V2 : PageType.DATA_PAGE;

         for(Encoding encoding : stats.getDataEncodings()) {
            formatStats.add(new PageEncodingStats(dataPageType, this.getEncoding(encoding), stats.getNumDataPagesEncodedAs(encoding)));
         }

         return formatStats;
      }
   }

   public static Statistics toParquetStatistics(org.apache.parquet.column.statistics.Statistics stats) {
      return toParquetStatistics(stats, Integer.MAX_VALUE);
   }

   public static Statistics toParquetStatistics(org.apache.parquet.column.statistics.Statistics stats, int truncateLength) {
      Statistics formatStats = new Statistics();
      if (!stats.isEmpty() && withinLimit(stats, truncateLength)) {
         formatStats.setNull_count(stats.getNumNulls());
         if (stats.hasNonNullValue()) {
            byte[] min;
            byte[] max;
            if (stats instanceof BinaryStatistics && truncateLength != Integer.MAX_VALUE) {
               BinaryTruncator truncator = BinaryTruncator.getTruncator(stats.type());
               min = tuncateMin(truncator, truncateLength, stats.getMinBytes());
               max = tuncateMax(truncator, truncateLength, stats.getMaxBytes());
            } else {
               min = stats.getMinBytes();
               max = stats.getMaxBytes();
            }

            if (sortOrder(stats.type()) == ParquetMetadataConverter.SortOrder.SIGNED || Arrays.equals(min, max)) {
               formatStats.setMin(min);
               formatStats.setMax(max);
            }

            if (isMinMaxStatsSupported(stats.type()) || Arrays.equals(min, max)) {
               formatStats.setMin_value(min);
               formatStats.setMax_value(max);
            }
         }
      }

      return formatStats;
   }

   private static boolean withinLimit(org.apache.parquet.column.statistics.Statistics stats, int truncateLength) {
      if (stats.isSmallerThan(4096L)) {
         return true;
      } else if (!(stats instanceof BinaryStatistics)) {
         return false;
      } else {
         BinaryStatistics binaryStatistics = (BinaryStatistics)stats;
         return binaryStatistics.isSmallerThanWithTruncation(4096L, truncateLength);
      }
   }

   private static byte[] tuncateMin(BinaryTruncator truncator, int truncateLength, byte[] input) {
      return truncator.truncateMin(Binary.fromConstantByteArray(input), truncateLength).getBytes();
   }

   private static byte[] tuncateMax(BinaryTruncator truncator, int truncateLength, byte[] input) {
      return truncator.truncateMax(Binary.fromConstantByteArray(input), truncateLength).getBytes();
   }

   private static boolean isMinMaxStatsSupported(PrimitiveType type) {
      return type.columnOrder().getColumnOrderName() == ColumnOrderName.TYPE_DEFINED_ORDER;
   }

   /** @deprecated */
   @Deprecated
   public static org.apache.parquet.column.statistics.Statistics fromParquetStatistics(Statistics statistics, PrimitiveType.PrimitiveTypeName type) {
      return fromParquetStatistics((String)null, statistics, (PrimitiveType.PrimitiveTypeName)type);
   }

   /** @deprecated */
   @Deprecated
   public static org.apache.parquet.column.statistics.Statistics fromParquetStatistics(String createdBy, Statistics statistics, PrimitiveType.PrimitiveTypeName type) {
      return fromParquetStatisticsInternal(createdBy, statistics, new PrimitiveType(Repetition.OPTIONAL, type, "fake_type"), defaultSortOrder(type));
   }

   static org.apache.parquet.column.statistics.Statistics fromParquetStatisticsInternal(String createdBy, Statistics formatStats, PrimitiveType type, SortOrder typeSortOrder) {
      org.apache.parquet.column.statistics.Statistics.Builder statsBuilder = org.apache.parquet.column.statistics.Statistics.getBuilderForReading(type);
      if (formatStats != null) {
         if (formatStats.isSetMin_value() && formatStats.isSetMax_value()) {
            byte[] min = formatStats.min_value.array();
            byte[] max = formatStats.max_value.array();
            if (isMinMaxStatsSupported(type) || Arrays.equals(min, max)) {
               statsBuilder.withMin(min);
               statsBuilder.withMax(max);
            }
         } else {
            boolean isSet = formatStats.isSetMax() && formatStats.isSetMin();
            boolean maxEqualsMin = isSet ? Arrays.equals(formatStats.getMin(), formatStats.getMax()) : false;
            boolean sortOrdersMatch = ParquetMetadataConverter.SortOrder.SIGNED == typeSortOrder;
            if (!CorruptStatistics.shouldIgnoreStatistics(createdBy, type.getPrimitiveTypeName()) && (sortOrdersMatch || maxEqualsMin) && isSet) {
               statsBuilder.withMin(formatStats.min.array());
               statsBuilder.withMax(formatStats.max.array());
            }
         }

         if (formatStats.isSetNull_count()) {
            statsBuilder.withNumNulls(formatStats.null_count);
         }
      }

      return statsBuilder.build();
   }

   public org.apache.parquet.column.statistics.Statistics fromParquetStatistics(String createdBy, Statistics statistics, PrimitiveType type) {
      SortOrder expectedOrder = this.overrideSortOrderToSigned(type) ? ParquetMetadataConverter.SortOrder.SIGNED : sortOrder(type);
      return fromParquetStatisticsInternal(createdBy, statistics, type, expectedOrder);
   }

   private boolean overrideSortOrderToSigned(PrimitiveType type) {
      LogicalTypeAnnotation annotation = type.getLogicalTypeAnnotation();
      return this.useSignedStringMinMax && PrimitiveTypeName.BINARY == type.getPrimitiveTypeName() && (annotation == null || STRING_TYPES.contains(annotation.getClass()));
   }

   private static SortOrder defaultSortOrder(PrimitiveType.PrimitiveTypeName primitive) {
      switch (primitive) {
         case BOOLEAN:
         case INT32:
         case INT64:
         case FLOAT:
         case DOUBLE:
            return ParquetMetadataConverter.SortOrder.SIGNED;
         case BINARY:
         case FIXED_LEN_BYTE_ARRAY:
            return ParquetMetadataConverter.SortOrder.UNSIGNED;
         default:
            return ParquetMetadataConverter.SortOrder.UNKNOWN;
      }
   }

   private static SortOrder sortOrder(PrimitiveType primitive) {
      LogicalTypeAnnotation annotation = primitive.getLogicalTypeAnnotation();
      return annotation != null ? (SortOrder)annotation.accept(new LogicalTypeAnnotation.LogicalTypeAnnotationVisitor() {
         public Optional visit(LogicalTypeAnnotation.IntLogicalTypeAnnotation intLogicalType) {
            return intLogicalType.isSigned() ? Optional.of(ParquetMetadataConverter.SortOrder.SIGNED) : Optional.of(ParquetMetadataConverter.SortOrder.UNSIGNED);
         }

         public Optional visit(LogicalTypeAnnotation.IntervalLogicalTypeAnnotation intervalLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNKNOWN);
         }

         public Optional visit(LogicalTypeAnnotation.DateLogicalTypeAnnotation dateLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.SIGNED);
         }

         public Optional visit(LogicalTypeAnnotation.EnumLogicalTypeAnnotation enumLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNSIGNED);
         }

         public Optional visit(LogicalTypeAnnotation.BsonLogicalTypeAnnotation bsonLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNSIGNED);
         }

         public Optional visit(LogicalTypeAnnotation.UUIDLogicalTypeAnnotation uuidLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNSIGNED);
         }

         public Optional visit(LogicalTypeAnnotation.JsonLogicalTypeAnnotation jsonLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNSIGNED);
         }

         public Optional visit(LogicalTypeAnnotation.StringLogicalTypeAnnotation stringLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNSIGNED);
         }

         public Optional visit(LogicalTypeAnnotation.Float16LogicalTypeAnnotation float16LogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.SIGNED);
         }

         public Optional visit(LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNKNOWN);
         }

         public Optional visit(LogicalTypeAnnotation.MapKeyValueTypeAnnotation mapKeyValueLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNKNOWN);
         }

         public Optional visit(LogicalTypeAnnotation.MapLogicalTypeAnnotation mapLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNKNOWN);
         }

         public Optional visit(LogicalTypeAnnotation.ListLogicalTypeAnnotation listLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.UNKNOWN);
         }

         public Optional visit(LogicalTypeAnnotation.TimeLogicalTypeAnnotation timeLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.SIGNED);
         }

         public Optional visit(LogicalTypeAnnotation.TimestampLogicalTypeAnnotation timestampLogicalType) {
            return Optional.of(ParquetMetadataConverter.SortOrder.SIGNED);
         }
      }).orElse(defaultSortOrder(primitive.getPrimitiveTypeName())) : defaultSortOrder(primitive.getPrimitiveTypeName());
   }

   public PrimitiveType.PrimitiveTypeName getPrimitive(org.apache.parquet.format.Type type) {
      switch (type) {
         case BYTE_ARRAY:
            return PrimitiveTypeName.BINARY;
         case INT64:
            return PrimitiveTypeName.INT64;
         case INT32:
            return PrimitiveTypeName.INT32;
         case BOOLEAN:
            return PrimitiveTypeName.BOOLEAN;
         case FLOAT:
            return PrimitiveTypeName.FLOAT;
         case DOUBLE:
            return PrimitiveTypeName.DOUBLE;
         case INT96:
            return PrimitiveTypeName.INT96;
         case FIXED_LEN_BYTE_ARRAY:
            return PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY;
         default:
            throw new RuntimeException("Unknown type " + type);
      }
   }

   org.apache.parquet.format.Type getType(PrimitiveType.PrimitiveTypeName type) {
      switch (type) {
         case BOOLEAN:
            return org.apache.parquet.format.Type.BOOLEAN;
         case INT32:
            return org.apache.parquet.format.Type.INT32;
         case INT64:
            return org.apache.parquet.format.Type.INT64;
         case FLOAT:
            return org.apache.parquet.format.Type.FLOAT;
         case DOUBLE:
            return org.apache.parquet.format.Type.DOUBLE;
         case BINARY:
            return org.apache.parquet.format.Type.BYTE_ARRAY;
         case FIXED_LEN_BYTE_ARRAY:
            return org.apache.parquet.format.Type.FIXED_LEN_BYTE_ARRAY;
         case INT96:
            return org.apache.parquet.format.Type.INT96;
         default:
            throw new RuntimeException("Unknown primitive type " + type);
      }
   }

   LogicalTypeAnnotation getLogicalTypeAnnotation(ConvertedType type, SchemaElement schemaElement) {
      switch (type) {
         case UTF8:
            return LogicalTypeAnnotation.stringType();
         case MAP:
            return LogicalTypeAnnotation.mapType();
         case MAP_KEY_VALUE:
            return MapKeyValueTypeAnnotation.getInstance();
         case LIST:
            return LogicalTypeAnnotation.listType();
         case ENUM:
            return LogicalTypeAnnotation.enumType();
         case DECIMAL:
            int scale = schemaElement == null ? 0 : schemaElement.scale;
            int precision = schemaElement == null ? 0 : schemaElement.precision;
            return LogicalTypeAnnotation.decimalType(scale, precision);
         case DATE:
            return LogicalTypeAnnotation.dateType();
         case TIME_MILLIS:
            return LogicalTypeAnnotation.timeType(true, org.apache.parquet.schema.LogicalTypeAnnotation.TimeUnit.MILLIS);
         case TIME_MICROS:
            return LogicalTypeAnnotation.timeType(true, org.apache.parquet.schema.LogicalTypeAnnotation.TimeUnit.MICROS);
         case TIMESTAMP_MILLIS:
            return LogicalTypeAnnotation.timestampType(true, org.apache.parquet.schema.LogicalTypeAnnotation.TimeUnit.MILLIS);
         case TIMESTAMP_MICROS:
            return LogicalTypeAnnotation.timestampType(true, org.apache.parquet.schema.LogicalTypeAnnotation.TimeUnit.MICROS);
         case INTERVAL:
            return IntervalLogicalTypeAnnotation.getInstance();
         case INT_8:
            return LogicalTypeAnnotation.intType(8, true);
         case INT_16:
            return LogicalTypeAnnotation.intType(16, true);
         case INT_32:
            return LogicalTypeAnnotation.intType(32, true);
         case INT_64:
            return LogicalTypeAnnotation.intType(64, true);
         case UINT_8:
            return LogicalTypeAnnotation.intType(8, false);
         case UINT_16:
            return LogicalTypeAnnotation.intType(16, false);
         case UINT_32:
            return LogicalTypeAnnotation.intType(32, false);
         case UINT_64:
            return LogicalTypeAnnotation.intType(64, false);
         case JSON:
            return LogicalTypeAnnotation.jsonType();
         case BSON:
            return LogicalTypeAnnotation.bsonType();
         default:
            throw new RuntimeException("Can't convert converted type to logical type, unknown converted type " + type);
      }
   }

   LogicalTypeAnnotation getLogicalTypeAnnotation(LogicalType type) {
      switch ((LogicalType._Fields)type.getSetField()) {
         case MAP:
            return LogicalTypeAnnotation.mapType();
         case BSON:
            return LogicalTypeAnnotation.bsonType();
         case DATE:
            return LogicalTypeAnnotation.dateType();
         case ENUM:
            return LogicalTypeAnnotation.enumType();
         case JSON:
            return LogicalTypeAnnotation.jsonType();
         case LIST:
            return LogicalTypeAnnotation.listType();
         case TIME:
            TimeType time = type.getTIME();
            return LogicalTypeAnnotation.timeType(time.isAdjustedToUTC, this.convertTimeUnit(time.unit));
         case STRING:
            return LogicalTypeAnnotation.stringType();
         case DECIMAL:
            DecimalType decimal = type.getDECIMAL();
            return LogicalTypeAnnotation.decimalType(decimal.scale, decimal.precision);
         case INTEGER:
            IntType integer = type.getINTEGER();
            return LogicalTypeAnnotation.intType(integer.bitWidth, integer.isSigned);
         case UNKNOWN:
            return null;
         case TIMESTAMP:
            TimestampType timestamp = type.getTIMESTAMP();
            return LogicalTypeAnnotation.timestampType(timestamp.isAdjustedToUTC, this.convertTimeUnit(timestamp.unit));
         case UUID:
            return LogicalTypeAnnotation.uuidType();
         case FLOAT16:
            return LogicalTypeAnnotation.float16Type();
         default:
            throw new RuntimeException("Unknown logical type " + type);
      }
   }

   private LogicalTypeAnnotation.TimeUnit convertTimeUnit(TimeUnit unit) {
      switch ((TimeUnit._Fields)unit.getSetField()) {
         case MICROS:
            return org.apache.parquet.schema.LogicalTypeAnnotation.TimeUnit.MICROS;
         case MILLIS:
            return org.apache.parquet.schema.LogicalTypeAnnotation.TimeUnit.MILLIS;
         case NANOS:
            return org.apache.parquet.schema.LogicalTypeAnnotation.TimeUnit.NANOS;
         default:
            throw new RuntimeException("Unknown time unit " + unit);
      }
   }

   private static void addKeyValue(FileMetaData fileMetaData, String key, String value) {
      KeyValue keyValue = new KeyValue(key);
      keyValue.value = value;
      fileMetaData.addToKey_value_metadata(keyValue);
   }

   public static MetadataFilter range(long startOffset, long endOffset) {
      return new RangeMetadataFilter(startOffset, endOffset);
   }

   public static MetadataFilter offsets(long... offsets) {
      Set<Long> set = new HashSet();

      for(long offset : offsets) {
         set.add(offset);
      }

      return new OffsetMetadataFilter(set);
   }

   /** @deprecated */
   @Deprecated
   public ParquetMetadata readParquetMetadata(InputStream from) throws IOException {
      return this.readParquetMetadata(from, NO_FILTER);
   }

   static FileMetaData filterFileMetaDataByMidpoint(FileMetaData metaData, RangeMetadataFilter filter) {
      List<RowGroup> rowGroups = metaData.getRow_groups();
      List<RowGroup> newRowGroups = new ArrayList();
      long preStartIndex = 0L;
      long preCompressedSize = 0L;
      boolean firstColumnWithMetadata = true;
      if (rowGroups != null && !rowGroups.isEmpty()) {
         firstColumnWithMetadata = ((ColumnChunk)((RowGroup)rowGroups.get(0)).getColumns().get(0)).isSetMeta_data();
      }

      for(RowGroup rowGroup : rowGroups) {
         long totalSize = 0L;
         ColumnChunk columnChunk = (ColumnChunk)rowGroup.getColumns().get(0);
         long startIndex;
         if (firstColumnWithMetadata) {
            startIndex = getOffset(columnChunk);
         } else {
            assert rowGroup.isSetFile_offset();

            assert rowGroup.isSetTotal_compressed_size();

            startIndex = rowGroup.getFile_offset();
            if (invalidFileOffset(startIndex, preStartIndex, preCompressedSize)) {
               if (preStartIndex == 0L) {
                  startIndex = 4L;
               } else {
                  startIndex = preStartIndex + preCompressedSize;
               }
            }

            preStartIndex = startIndex;
            preCompressedSize = rowGroup.getTotal_compressed_size();
         }

         if (rowGroup.isSetTotal_compressed_size()) {
            totalSize = rowGroup.getTotal_compressed_size();
         } else {
            for(ColumnChunk col : rowGroup.getColumns()) {
               totalSize += col.getMeta_data().getTotal_compressed_size();
            }
         }

         long midPoint = startIndex + totalSize / 2L;
         if (filter.contains(midPoint)) {
            newRowGroups.add(rowGroup);
         }
      }

      metaData.setRow_groups(newRowGroups);
      return metaData;
   }

   private static boolean invalidFileOffset(long startIndex, long preStartIndex, long preCompressedSize) {
      boolean invalid = false;

      assert preStartIndex <= startIndex;

      if (preStartIndex == 0L && startIndex != 4L) {
         invalid = true;
         return invalid;
      } else {
         long minStartIndex = preStartIndex + preCompressedSize;
         if (startIndex < minStartIndex) {
            invalid = true;
         }

         return invalid;
      }
   }

   static FileMetaData filterFileMetaDataByStart(FileMetaData metaData, OffsetMetadataFilter filter) {
      List<RowGroup> rowGroups = metaData.getRow_groups();
      List<RowGroup> newRowGroups = new ArrayList();
      long preStartIndex = 0L;
      long preCompressedSize = 0L;
      boolean firstColumnWithMetadata = true;
      if (rowGroups != null && !rowGroups.isEmpty()) {
         firstColumnWithMetadata = ((ColumnChunk)((RowGroup)rowGroups.get(0)).getColumns().get(0)).isSetMeta_data();
      }

      for(RowGroup rowGroup : rowGroups) {
         ColumnChunk columnChunk = (ColumnChunk)rowGroup.getColumns().get(0);
         long startIndex;
         if (firstColumnWithMetadata) {
            startIndex = getOffset(columnChunk);
         } else {
            assert rowGroup.isSetFile_offset();

            assert rowGroup.isSetTotal_compressed_size();

            startIndex = rowGroup.getFile_offset();
            if (invalidFileOffset(startIndex, preStartIndex, preCompressedSize)) {
               if (preStartIndex != 0L) {
                  throw new InvalidFileOffsetException("corrupted RowGroup.file_offset found, please use file range instead of block offset for split.");
               }

               startIndex = 4L;
            }

            preStartIndex = startIndex;
            preCompressedSize = rowGroup.getTotal_compressed_size();
         }

         if (filter.contains(startIndex)) {
            newRowGroups.add(rowGroup);
         }
      }

      metaData.setRow_groups(newRowGroups);
      return metaData;
   }

   static long getOffset(RowGroup rowGroup) {
      return rowGroup.isSetFile_offset() ? rowGroup.getFile_offset() : getOffset((ColumnChunk)rowGroup.getColumns().get(0));
   }

   static long getOffset(ColumnChunk columnChunk) {
      ColumnMetaData md = columnChunk.getMeta_data();
      long offset = md.getData_page_offset();
      if (md.isSetDictionary_page_offset() && offset > md.getDictionary_page_offset()) {
         offset = md.getDictionary_page_offset();
      }

      return offset;
   }

   private static void verifyFooterIntegrity(InputStream from, InternalFileDecryptor fileDecryptor, int combinedFooterLength) throws IOException {
      byte[] nonce = new byte[12];
      from.read(nonce);
      byte[] gcmTag = new byte[16];
      from.read(gcmTag);
      AesGcmEncryptor footerSigner = fileDecryptor.createSignedFooterEncryptor();
      int footerSignatureLength = 28;
      byte[] serializedFooter = new byte[combinedFooterLength - footerSignatureLength];
      from.reset();
      from.read(serializedFooter);
      byte[] signedFooterAAD = AesCipher.createFooterAAD(fileDecryptor.getFileAAD());
      byte[] encryptedFooterBytes = footerSigner.encrypt(false, serializedFooter, nonce, signedFooterAAD);
      byte[] calculatedTag = new byte[16];
      System.arraycopy(encryptedFooterBytes, encryptedFooterBytes.length - 16, calculatedTag, 0, 16);
      if (!Arrays.equals(gcmTag, calculatedTag)) {
         throw new TagVerificationException("Signature mismatch in plaintext footer");
      }
   }

   public ParquetMetadata readParquetMetadata(InputStream from, MetadataFilter filter) throws IOException {
      return this.readParquetMetadata(from, filter, (InternalFileDecryptor)null, false, 0);
   }

   private Map generateRowGroupOffsets(FileMetaData metaData) {
      Map<RowGroup, Long> rowGroupOrdinalToRowIdx = new HashMap();
      List<RowGroup> rowGroups = metaData.getRow_groups();
      if (rowGroups != null) {
         long rowIdxSum = 0L;

         for(int i = 0; i < rowGroups.size(); ++i) {
            rowGroupOrdinalToRowIdx.put(rowGroups.get(i), rowIdxSum);
            rowIdxSum += ((RowGroup)rowGroups.get(i)).getNum_rows();
         }
      }

      return rowGroupOrdinalToRowIdx;
   }

   public ParquetMetadata readParquetMetadata(InputStream fromInputStream, MetadataFilter filter, InternalFileDecryptor fileDecryptor, boolean encryptedFooter, int combinedFooterLength) throws IOException {
      final BlockCipher.Decryptor footerDecryptor = encryptedFooter ? fileDecryptor.fetchFooterDecryptor() : null;
      final byte[] encryptedFooterAAD = encryptedFooter ? AesCipher.createFooterAAD(fileDecryptor.getFileAAD()) : null;
      final InputStream from;
      if (fileDecryptor != null && fileDecryptor.checkFooterIntegrity()) {
         if (!fromInputStream.markSupported()) {
            from = new BufferedInputStream(fromInputStream, combinedFooterLength);
         } else {
            from = fromInputStream;
         }

         from.mark(combinedFooterLength);
      } else {
         from = fromInputStream;
      }

      FileMetaDataAndRowGroupOffsetInfo fileMetaDataAndRowGroupInfo = (FileMetaDataAndRowGroupOffsetInfo)filter.accept(new MetadataFilterVisitor() {
         public FileMetaDataAndRowGroupOffsetInfo visit(NoFilter filter) throws IOException {
            FileMetaData fileMetadata = Util.readFileMetaData(from, footerDecryptor, encryptedFooterAAD);
            return ParquetMetadataConverter.this.new FileMetaDataAndRowGroupOffsetInfo(fileMetadata, ParquetMetadataConverter.this.generateRowGroupOffsets(fileMetadata));
         }

         public FileMetaDataAndRowGroupOffsetInfo visit(SkipMetadataFilter filter) throws IOException {
            FileMetaData fileMetadata = Util.readFileMetaData(from, true, footerDecryptor, encryptedFooterAAD);
            return ParquetMetadataConverter.this.new FileMetaDataAndRowGroupOffsetInfo(fileMetadata, ParquetMetadataConverter.this.generateRowGroupOffsets(fileMetadata));
         }

         public FileMetaDataAndRowGroupOffsetInfo visit(OffsetMetadataFilter filter) throws IOException {
            FileMetaData fileMetadata = Util.readFileMetaData(from, footerDecryptor, encryptedFooterAAD);
            Map<RowGroup, Long> rowGroupToRowIndexOffsetMap = ParquetMetadataConverter.this.generateRowGroupOffsets(fileMetadata);
            FileMetaData filteredFileMetadata = ParquetMetadataConverter.filterFileMetaDataByStart(fileMetadata, filter);
            return ParquetMetadataConverter.this.new FileMetaDataAndRowGroupOffsetInfo(filteredFileMetadata, rowGroupToRowIndexOffsetMap);
         }

         public FileMetaDataAndRowGroupOffsetInfo visit(RangeMetadataFilter filter) throws IOException {
            FileMetaData fileMetadata = Util.readFileMetaData(from, footerDecryptor, encryptedFooterAAD);
            Map<RowGroup, Long> rowGroupToRowIndexOffsetMap = ParquetMetadataConverter.this.generateRowGroupOffsets(fileMetadata);
            FileMetaData filteredFileMetadata = ParquetMetadataConverter.filterFileMetaDataByMidpoint(fileMetadata, filter);
            return ParquetMetadataConverter.this.new FileMetaDataAndRowGroupOffsetInfo(filteredFileMetadata, rowGroupToRowIndexOffsetMap);
         }
      });
      FileMetaData fileMetaData = fileMetaDataAndRowGroupInfo.fileMetadata;
      Map<RowGroup, Long> rowGroupToRowIndexOffsetMap = fileMetaDataAndRowGroupInfo.rowGroupToRowIndexOffsetMap;
      LOG.debug("{}", fileMetaData);
      if (!encryptedFooter && null != fileDecryptor) {
         if (!fileMetaData.isSetEncryption_algorithm()) {
            fileDecryptor.setPlaintextFile();
            if (!fileDecryptor.plaintextFilesAllowed()) {
               throw new ParquetCryptoRuntimeException("Applying decryptor on plaintext file");
            }
         } else {
            fileDecryptor.setFileCryptoMetaData(fileMetaData.getEncryption_algorithm(), false, fileMetaData.getFooter_signing_key_metadata());
            if (fileDecryptor.checkFooterIntegrity()) {
               verifyFooterIntegrity(from, fileDecryptor, combinedFooterLength);
            }
         }
      }

      ParquetMetadata parquetMetadata = this.fromParquetMetadata(fileMetaData, fileDecryptor, encryptedFooter, rowGroupToRowIndexOffsetMap);
      if (LOG.isDebugEnabled()) {
         LOG.debug(ParquetMetadata.toPrettyJSON(parquetMetadata));
      }

      return parquetMetadata;
   }

   public ColumnChunkMetaData buildColumnChunkMetaData(ColumnMetaData metaData, ColumnPath columnPath, PrimitiveType type, String createdBy) {
      return ColumnChunkMetaData.get(columnPath, type, this.fromFormatCodec(metaData.codec), this.convertEncodingStats(metaData.getEncoding_stats()), this.fromFormatEncodings(metaData.encodings), this.fromParquetStatistics(createdBy, metaData.statistics, type), metaData.data_page_offset, metaData.dictionary_page_offset, metaData.num_values, metaData.total_compressed_size, metaData.total_uncompressed_size, fromParquetSizeStatistics(metaData.size_statistics, type));
   }

   public ParquetMetadata fromParquetMetadata(FileMetaData parquetMetadata) throws IOException {
      return this.fromParquetMetadata(parquetMetadata, (InternalFileDecryptor)null, false);
   }

   public ParquetMetadata fromParquetMetadata(FileMetaData parquetMetadata, InternalFileDecryptor fileDecryptor, boolean encryptedFooter) throws IOException {
      return this.fromParquetMetadata(parquetMetadata, fileDecryptor, encryptedFooter, new HashMap());
   }

   public ParquetMetadata fromParquetMetadata(FileMetaData parquetMetadata, InternalFileDecryptor fileDecryptor, boolean encryptedFooter, Map rowGroupToRowIndexOffsetMap) throws IOException {
      MessageType messageType = this.fromParquetSchema(parquetMetadata.getSchema(), parquetMetadata.getColumn_orders());
      List<BlockMetaData> blocks = new ArrayList();
      List<RowGroup> row_groups = parquetMetadata.getRow_groups();
      if (row_groups != null) {
         for(RowGroup rowGroup : row_groups) {
            BlockMetaData blockMetaData = new BlockMetaData();
            blockMetaData.setRowCount(rowGroup.getNum_rows());
            blockMetaData.setTotalByteSize(rowGroup.getTotal_byte_size());
            if (rowGroupToRowIndexOffsetMap.containsKey(rowGroup)) {
               blockMetaData.setRowIndexOffset((Long)rowGroupToRowIndexOffsetMap.get(rowGroup));
            }

            if (rowGroup.isSetOrdinal()) {
               blockMetaData.setOrdinal(rowGroup.getOrdinal());
            }

            List<ColumnChunk> columns = rowGroup.getColumns();
            String filePath = ((ColumnChunk)columns.get(0)).getFile_path();
            int columnOrdinal = -1;

            for(ColumnChunk columnChunk : columns) {
               ++columnOrdinal;
               if (filePath == null && columnChunk.getFile_path() != null || filePath != null && !filePath.equals(columnChunk.getFile_path())) {
                  throw new ParquetDecodingException("all column chunks of the same row group must be in the same file for now");
               }

               ColumnMetaData metaData = columnChunk.meta_data;
               ColumnCryptoMetaData cryptoMetaData = columnChunk.getCrypto_metadata();
               ColumnChunkMetaData column = null;
               ColumnPath columnPath = null;
               boolean lazyMetadataDecryption = false;
               if (null == cryptoMetaData) {
                  columnPath = getPath(metaData);
                  if (null != fileDecryptor && !fileDecryptor.plaintextFile()) {
                     fileDecryptor.setColumnCryptoMetadata(columnPath, false, false, (byte[])null, columnOrdinal);
                  }
               } else {
                  boolean encryptedWithFooterKey = cryptoMetaData.isSetENCRYPTION_WITH_FOOTER_KEY();
                  if (encryptedWithFooterKey) {
                     if (null == fileDecryptor) {
                        throw new ParquetCryptoRuntimeException("Column encrypted with footer key: No keys available");
                     }

                     if (null == metaData) {
                        throw new ParquetCryptoRuntimeException("ColumnMetaData not set in Encryption with Footer key");
                     }

                     columnPath = getPath(metaData);
                     if (!encryptedFooter) {
                        ByteArrayInputStream tempInputStream = new ByteArrayInputStream(columnChunk.getEncrypted_column_metadata());
                        byte[] columnMetaDataAAD = AesCipher.createModuleAAD(fileDecryptor.getFileAAD(), ModuleCipherFactory.ModuleType.ColumnMetaData, rowGroup.getOrdinal(), columnOrdinal, -1);

                        try {
                           metaData = Util.readColumnMetaData(tempInputStream, fileDecryptor.fetchFooterDecryptor(), columnMetaDataAAD);
                        } catch (IOException e) {
                           throw new ParquetCryptoRuntimeException(columnPath + ". Failed to decrypt column metadata", e);
                        }
                     }

                     fileDecryptor.setColumnCryptoMetadata(columnPath, true, true, (byte[])null, columnOrdinal);
                  } else {
                     lazyMetadataDecryption = true;
                  }
               }

               String createdBy = parquetMetadata.getCreated_by();
               if (!lazyMetadataDecryption) {
                  column = this.buildColumnChunkMetaData(metaData, columnPath, messageType.getType(columnPath.toArray()).asPrimitiveType(), createdBy);
                  column.setRowGroupOrdinal(rowGroup.getOrdinal());
                  if (metaData.isSetBloom_filter_offset()) {
                     column.setBloomFilterOffset(metaData.getBloom_filter_offset());
                  }

                  if (metaData.isSetBloom_filter_length()) {
                     column.setBloomFilterLength(metaData.getBloom_filter_length());
                  }
               } else {
                  EncryptionWithColumnKey columnKeyStruct = cryptoMetaData.getENCRYPTION_WITH_COLUMN_KEY();
                  List<String> pathList = columnKeyStruct.getPath_in_schema();
                  byte[] columnKeyMetadata = columnKeyStruct.getKey_metadata();
                  columnPath = ColumnPath.get((String[])pathList.toArray(new String[pathList.size()]));
                  byte[] encryptedMetadataBuffer = columnChunk.getEncrypted_column_metadata();
                  column = ColumnChunkMetaData.getWithEncryptedMetadata(this, columnPath, messageType.getType(columnPath.toArray()).asPrimitiveType(), encryptedMetadataBuffer, columnKeyMetadata, fileDecryptor, rowGroup.getOrdinal(), columnOrdinal, createdBy);
               }

               column.setColumnIndexReference(toColumnIndexReference(columnChunk));
               column.setOffsetIndexReference(toOffsetIndexReference(columnChunk));
               blockMetaData.addColumn(column);
            }

            blockMetaData.setPath(filePath);
            blocks.add(blockMetaData);
         }
      }

      Map<String, String> keyValueMetaData = new HashMap();
      List<KeyValue> key_value_metadata = parquetMetadata.getKey_value_metadata();
      if (key_value_metadata != null) {
         for(KeyValue keyValue : key_value_metadata) {
            keyValueMetaData.put(keyValue.key, keyValue.value);
         }
      }

      org.apache.parquet.hadoop.metadata.FileMetaData.EncryptionType encryptionType;
      if (encryptedFooter) {
         encryptionType = org.apache.parquet.hadoop.metadata.FileMetaData.EncryptionType.ENCRYPTED_FOOTER;
      } else if (parquetMetadata.isSetEncryption_algorithm()) {
         encryptionType = org.apache.parquet.hadoop.metadata.FileMetaData.EncryptionType.PLAINTEXT_FOOTER;
      } else {
         encryptionType = org.apache.parquet.hadoop.metadata.FileMetaData.EncryptionType.UNENCRYPTED;
      }

      return new ParquetMetadata(new org.apache.parquet.hadoop.metadata.FileMetaData(messageType, keyValueMetaData, parquetMetadata.getCreated_by(), encryptionType, fileDecryptor), blocks);
   }

   private static IndexReference toColumnIndexReference(ColumnChunk columnChunk) {
      return columnChunk.isSetColumn_index_offset() && columnChunk.isSetColumn_index_length() ? new IndexReference(columnChunk.getColumn_index_offset(), columnChunk.getColumn_index_length()) : null;
   }

   private static IndexReference toOffsetIndexReference(ColumnChunk columnChunk) {
      return columnChunk.isSetOffset_index_offset() && columnChunk.isSetOffset_index_length() ? new IndexReference(columnChunk.getOffset_index_offset(), columnChunk.getOffset_index_length()) : null;
   }

   private static ColumnPath getPath(ColumnMetaData metaData) {
      String[] path = (String[])metaData.path_in_schema.toArray(new String[0]);
      return ColumnPath.get(path);
   }

   MessageType fromParquetSchema(List schema, List columnOrders) {
      Iterator<SchemaElement> iterator = schema.iterator();
      SchemaElement root = (SchemaElement)iterator.next();
      Types.MessageTypeBuilder builder = Types.buildMessage();
      if (root.isSetField_id()) {
         builder.id(root.field_id);
      }

      this.buildChildren(builder, iterator, root.getNum_children(), columnOrders, 0);
      return builder.named(root.name);
   }

   private void buildChildren(Types.GroupBuilder builder, Iterator schema, int childrenCount, List columnOrders, int columnCount) {
      for(int i = 0; i < childrenCount; ++i) {
         SchemaElement schemaElement = (SchemaElement)schema.next();
         Types.Builder childBuilder;
         if (schemaElement.type != null) {
            Types.PrimitiveBuilder primitiveBuilder = builder.primitive(this.getPrimitive(schemaElement.type), this.fromParquetRepetition(schemaElement.repetition_type));
            if (schemaElement.isSetType_length()) {
               primitiveBuilder.length(schemaElement.type_length);
            }

            if (schemaElement.isSetPrecision()) {
               primitiveBuilder.precision(schemaElement.precision);
            }

            if (schemaElement.isSetScale()) {
               primitiveBuilder.scale(schemaElement.scale);
            }

            if (columnOrders != null) {
               org.apache.parquet.schema.ColumnOrder columnOrder = fromParquetColumnOrder((ColumnOrder)columnOrders.get(columnCount));
               if (columnOrder.getColumnOrderName() == ColumnOrderName.TYPE_DEFINED_ORDER && (schemaElement.type == org.apache.parquet.format.Type.INT96 || schemaElement.converted_type == ConvertedType.INTERVAL)) {
                  columnOrder = org.apache.parquet.schema.ColumnOrder.undefined();
               }

               primitiveBuilder.columnOrder(columnOrder);
            }

            childBuilder = primitiveBuilder;
         } else {
            childBuilder = builder.group(this.fromParquetRepetition(schemaElement.repetition_type));
            this.buildChildren((Types.GroupBuilder)childBuilder, schema, schemaElement.num_children, columnOrders, columnCount);
         }

         if (schemaElement.isSetLogicalType()) {
            childBuilder.as(this.getLogicalTypeAnnotation(schemaElement.logicalType));
         }

         if (schemaElement.isSetConverted_type()) {
            OriginalType originalType = this.getLogicalTypeAnnotation(schemaElement.converted_type, schemaElement).toOriginalType();
            OriginalType newOriginalType = schemaElement.isSetLogicalType() && this.getLogicalTypeAnnotation(schemaElement.logicalType) != null ? this.getLogicalTypeAnnotation(schemaElement.logicalType).toOriginalType() : null;
            if (!originalType.equals(newOriginalType)) {
               if (newOriginalType != null) {
                  LOG.warn("Converted type and logical type metadata mismatch (convertedType: {}, logical type: {}). Using value in converted type.", schemaElement.converted_type, schemaElement.logicalType);
               }

               childBuilder.as(originalType);
            }
         }

         if (schemaElement.isSetField_id()) {
            childBuilder.id(schemaElement.field_id);
         }

         childBuilder.named(schemaElement.name);
         ++columnCount;
      }

   }

   FieldRepetitionType toParquetRepetition(Type.Repetition repetition) {
      return FieldRepetitionType.valueOf(repetition.name());
   }

   Type.Repetition fromParquetRepetition(FieldRepetitionType repetition) {
      return Repetition.valueOf(repetition.name());
   }

   private static org.apache.parquet.schema.ColumnOrder fromParquetColumnOrder(ColumnOrder columnOrder) {
      return columnOrder.isSetTYPE_ORDER() ? org.apache.parquet.schema.ColumnOrder.typeDefined() : org.apache.parquet.schema.ColumnOrder.undefined();
   }

   /** @deprecated */
   @Deprecated
   public void writeDataPageHeader(int uncompressedSize, int compressedSize, int valueCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, OutputStream to) throws IOException {
      Util.writePageHeader(this.newDataPageHeader(uncompressedSize, compressedSize, valueCount, rlEncoding, dlEncoding, valuesEncoding), to);
   }

   /** @deprecated */
   @Deprecated
   public void writeDataPageHeader(int uncompressedSize, int compressedSize, int valueCount, org.apache.parquet.column.statistics.Statistics statistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, OutputStream to) throws IOException {
      Util.writePageHeader(this.newDataPageHeader(uncompressedSize, compressedSize, valueCount, rlEncoding, dlEncoding, valuesEncoding), to);
   }

   private PageHeader newDataPageHeader(int uncompressedSize, int compressedSize, int valueCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) {
      PageHeader pageHeader = new PageHeader(PageType.DATA_PAGE, uncompressedSize, compressedSize);
      pageHeader.setData_page_header(new DataPageHeader(valueCount, this.getEncoding(valuesEncoding), this.getEncoding(dlEncoding), this.getEncoding(rlEncoding)));
      return pageHeader;
   }

   private PageHeader newDataPageHeader(int uncompressedSize, int compressedSize, int valueCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, int crc) {
      PageHeader pageHeader = new PageHeader(PageType.DATA_PAGE, uncompressedSize, compressedSize);
      pageHeader.setCrc(crc);
      pageHeader.setData_page_header(new DataPageHeader(valueCount, this.getEncoding(valuesEncoding), this.getEncoding(dlEncoding), this.getEncoding(rlEncoding)));
      return pageHeader;
   }

   /** @deprecated */
   @Deprecated
   public void writeDataPageV2Header(int uncompressedSize, int compressedSize, int valueCount, int nullCount, int rowCount, org.apache.parquet.column.statistics.Statistics statistics, Encoding dataEncoding, int rlByteLength, int dlByteLength, OutputStream to) throws IOException {
      Util.writePageHeader(this.newDataPageV2Header(uncompressedSize, compressedSize, valueCount, nullCount, rowCount, dataEncoding, rlByteLength, dlByteLength), to);
   }

   public void writeDataPageV1Header(int uncompressedSize, int compressedSize, int valueCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, OutputStream to) throws IOException {
      this.writeDataPageV1Header(uncompressedSize, compressedSize, valueCount, rlEncoding, dlEncoding, valuesEncoding, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public void writeDataPageV1Header(int uncompressedSize, int compressedSize, int valueCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, OutputStream to, BlockCipher.Encryptor blockEncryptor, byte[] pageHeaderAAD) throws IOException {
      Util.writePageHeader(this.newDataPageHeader(uncompressedSize, compressedSize, valueCount, rlEncoding, dlEncoding, valuesEncoding), to, blockEncryptor, pageHeaderAAD);
   }

   public void writeDataPageV1Header(int uncompressedSize, int compressedSize, int valueCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, int crc, OutputStream to) throws IOException {
      this.writeDataPageV1Header(uncompressedSize, compressedSize, valueCount, rlEncoding, dlEncoding, valuesEncoding, crc, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public void writeDataPageV1Header(int uncompressedSize, int compressedSize, int valueCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, int crc, OutputStream to, BlockCipher.Encryptor blockEncryptor, byte[] pageHeaderAAD) throws IOException {
      Util.writePageHeader(this.newDataPageHeader(uncompressedSize, compressedSize, valueCount, rlEncoding, dlEncoding, valuesEncoding, crc), to, blockEncryptor, pageHeaderAAD);
   }

   public void writeDataPageV2Header(int uncompressedSize, int compressedSize, int valueCount, int nullCount, int rowCount, Encoding dataEncoding, int rlByteLength, int dlByteLength, OutputStream to) throws IOException {
      this.writeDataPageV2Header(uncompressedSize, compressedSize, valueCount, nullCount, rowCount, dataEncoding, rlByteLength, dlByteLength, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public void writeDataPageV2Header(int uncompressedSize, int compressedSize, int valueCount, int nullCount, int rowCount, Encoding dataEncoding, int rlByteLength, int dlByteLength, OutputStream to, BlockCipher.Encryptor blockEncryptor, byte[] pageHeaderAAD) throws IOException {
      Util.writePageHeader(this.newDataPageV2Header(uncompressedSize, compressedSize, valueCount, nullCount, rowCount, dataEncoding, rlByteLength, dlByteLength), to, blockEncryptor, pageHeaderAAD);
   }

   private PageHeader newDataPageV2Header(int uncompressedSize, int compressedSize, int valueCount, int nullCount, int rowCount, Encoding dataEncoding, int rlByteLength, int dlByteLength) {
      DataPageHeaderV2 dataPageHeaderV2 = new DataPageHeaderV2(valueCount, nullCount, rowCount, this.getEncoding(dataEncoding), dlByteLength, rlByteLength);
      PageHeader pageHeader = new PageHeader(PageType.DATA_PAGE_V2, uncompressedSize, compressedSize);
      pageHeader.setData_page_header_v2(dataPageHeaderV2);
      return pageHeader;
   }

   public void writeDataPageV2Header(int uncompressedSize, int compressedSize, int valueCount, int nullCount, int rowCount, Encoding dataEncoding, int rlByteLength, int dlByteLength, int crc, OutputStream to, BlockCipher.Encryptor blockEncryptor, byte[] pageHeaderAAD) throws IOException {
      Util.writePageHeader(this.newDataPageV2Header(uncompressedSize, compressedSize, valueCount, nullCount, rowCount, dataEncoding, rlByteLength, dlByteLength, crc), to, blockEncryptor, pageHeaderAAD);
   }

   private PageHeader newDataPageV2Header(int uncompressedSize, int compressedSize, int valueCount, int nullCount, int rowCount, Encoding dataEncoding, int rlByteLength, int dlByteLength, int crc) {
      DataPageHeaderV2 dataPageHeaderV2 = new DataPageHeaderV2(valueCount, nullCount, rowCount, this.getEncoding(dataEncoding), dlByteLength, rlByteLength);
      PageHeader pageHeader = new PageHeader(PageType.DATA_PAGE_V2, uncompressedSize, compressedSize);
      pageHeader.setData_page_header_v2(dataPageHeaderV2);
      pageHeader.setCrc(crc);
      return pageHeader;
   }

   public void writeDictionaryPageHeader(int uncompressedSize, int compressedSize, int valueCount, Encoding valuesEncoding, OutputStream to) throws IOException {
      this.writeDictionaryPageHeader(uncompressedSize, compressedSize, valueCount, valuesEncoding, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public void writeDictionaryPageHeader(int uncompressedSize, int compressedSize, int valueCount, Encoding valuesEncoding, OutputStream to, BlockCipher.Encryptor blockEncryptor, byte[] pageHeaderAAD) throws IOException {
      PageHeader pageHeader = new PageHeader(PageType.DICTIONARY_PAGE, uncompressedSize, compressedSize);
      pageHeader.setDictionary_page_header(new DictionaryPageHeader(valueCount, this.getEncoding(valuesEncoding)));
      Util.writePageHeader(pageHeader, to, blockEncryptor, pageHeaderAAD);
   }

   public void writeDictionaryPageHeader(int uncompressedSize, int compressedSize, int valueCount, Encoding valuesEncoding, int crc, OutputStream to) throws IOException {
      this.writeDictionaryPageHeader(uncompressedSize, compressedSize, valueCount, valuesEncoding, crc, to, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public void writeDictionaryPageHeader(int uncompressedSize, int compressedSize, int valueCount, Encoding valuesEncoding, int crc, OutputStream to, BlockCipher.Encryptor blockEncryptor, byte[] pageHeaderAAD) throws IOException {
      PageHeader pageHeader = new PageHeader(PageType.DICTIONARY_PAGE, uncompressedSize, compressedSize);
      pageHeader.setCrc(crc);
      pageHeader.setDictionary_page_header(new DictionaryPageHeader(valueCount, this.getEncoding(valuesEncoding)));
      Util.writePageHeader(pageHeader, to, blockEncryptor, pageHeaderAAD);
   }

   private static BoundaryOrder toParquetBoundaryOrder(org.apache.parquet.internal.column.columnindex.BoundaryOrder boundaryOrder) {
      switch (boundaryOrder) {
         case ASCENDING:
            return BoundaryOrder.ASCENDING;
         case DESCENDING:
            return BoundaryOrder.DESCENDING;
         case UNORDERED:
            return BoundaryOrder.UNORDERED;
         default:
            throw new IllegalArgumentException("Unsupported boundary order: " + boundaryOrder);
      }
   }

   private static org.apache.parquet.internal.column.columnindex.BoundaryOrder fromParquetBoundaryOrder(BoundaryOrder boundaryOrder) {
      switch (boundaryOrder) {
         case ASCENDING:
            return org.apache.parquet.internal.column.columnindex.BoundaryOrder.ASCENDING;
         case DESCENDING:
            return org.apache.parquet.internal.column.columnindex.BoundaryOrder.DESCENDING;
         case UNORDERED:
            return org.apache.parquet.internal.column.columnindex.BoundaryOrder.UNORDERED;
         default:
            throw new IllegalArgumentException("Unsupported boundary order: " + boundaryOrder);
      }
   }

   public static ColumnIndex toParquetColumnIndex(PrimitiveType type, org.apache.parquet.internal.column.columnindex.ColumnIndex columnIndex) {
      if (isMinMaxStatsSupported(type) && columnIndex != null) {
         ColumnIndex parquetColumnIndex = new ColumnIndex(columnIndex.getNullPages(), columnIndex.getMinValues(), columnIndex.getMaxValues(), toParquetBoundaryOrder(columnIndex.getBoundaryOrder()));
         parquetColumnIndex.setNull_counts(columnIndex.getNullCounts());
         List<Long> repLevelHistogram = columnIndex.getRepetitionLevelHistogram();
         if (repLevelHistogram != null && !repLevelHistogram.isEmpty()) {
            parquetColumnIndex.setRepetition_level_histograms(repLevelHistogram);
         }

         List<Long> defLevelHistogram = columnIndex.getDefinitionLevelHistogram();
         if (defLevelHistogram != null && !defLevelHistogram.isEmpty()) {
            parquetColumnIndex.setDefinition_level_histograms(defLevelHistogram);
         }

         return parquetColumnIndex;
      } else {
         return null;
      }
   }

   public static org.apache.parquet.internal.column.columnindex.ColumnIndex fromParquetColumnIndex(PrimitiveType type, ColumnIndex parquetColumnIndex) {
      return !isMinMaxStatsSupported(type) ? null : ColumnIndexBuilder.build(type, fromParquetBoundaryOrder(parquetColumnIndex.getBoundary_order()), parquetColumnIndex.getNull_pages(), parquetColumnIndex.getNull_counts(), parquetColumnIndex.getMin_values(), parquetColumnIndex.getMax_values(), parquetColumnIndex.getRepetition_level_histograms(), parquetColumnIndex.getDefinition_level_histograms());
   }

   public static OffsetIndex toParquetOffsetIndex(org.apache.parquet.internal.column.columnindex.OffsetIndex offsetIndex) {
      List<PageLocation> pageLocations = new ArrayList(offsetIndex.getPageCount());
      List<Long> unencodedByteArrayDataBytes = new ArrayList(offsetIndex.getPageCount());
      int i = 0;

      for(int n = offsetIndex.getPageCount(); i < n; ++i) {
         pageLocations.add(new PageLocation(offsetIndex.getOffset(i), offsetIndex.getCompressedPageSize(i), offsetIndex.getFirstRowIndex(i)));
         Optional<Long> unencodedByteArrayDataType = offsetIndex.getUnencodedByteArrayDataBytes(i);
         if (unencodedByteArrayDataType.isPresent() && unencodedByteArrayDataBytes.size() == i) {
            unencodedByteArrayDataBytes.add(unencodedByteArrayDataType.get());
         }
      }

      OffsetIndex parquetOffsetIndex = new OffsetIndex(pageLocations);
      if (unencodedByteArrayDataBytes.size() == pageLocations.size()) {
         parquetOffsetIndex.setUnencoded_byte_array_data_bytes(unencodedByteArrayDataBytes);
      }

      return parquetOffsetIndex;
   }

   public static org.apache.parquet.internal.column.columnindex.OffsetIndex fromParquetOffsetIndex(OffsetIndex parquetOffsetIndex) {
      boolean hasUnencodedByteArrayDataBytes = parquetOffsetIndex.isSetUnencoded_byte_array_data_bytes() && parquetOffsetIndex.unencoded_byte_array_data_bytes.size() == parquetOffsetIndex.page_locations.size();
      OffsetIndexBuilder builder = OffsetIndexBuilder.getBuilder();

      for(int i = 0; i < parquetOffsetIndex.page_locations.size(); ++i) {
         PageLocation pageLocation = (PageLocation)parquetOffsetIndex.page_locations.get(i);
         Optional<Long> unencodedByteArrayDataBytes = hasUnencodedByteArrayDataBytes ? Optional.of(parquetOffsetIndex.unencoded_byte_array_data_bytes.get(i)) : Optional.empty();
         builder.add(pageLocation.getOffset(), pageLocation.getCompressed_page_size(), pageLocation.getFirst_row_index(), unencodedByteArrayDataBytes);
      }

      return builder.build();
   }

   public static BloomFilterHeader toBloomFilterHeader(BloomFilter bloomFilter) {
      BloomFilterAlgorithm algorithm = null;
      BloomFilterHash hashStrategy = null;
      BloomFilterCompression compression = null;
      if (bloomFilter.getAlgorithm() == Algorithm.BLOCK) {
         algorithm = BloomFilterAlgorithm.BLOCK(new SplitBlockAlgorithm());
      }

      if (bloomFilter.getHashStrategy() == HashStrategy.XXH64) {
         hashStrategy = BloomFilterHash.XXHASH(new XxHash());
      }

      if (bloomFilter.getCompression() == Compression.UNCOMPRESSED) {
         compression = BloomFilterCompression.UNCOMPRESSED(new Uncompressed());
      }

      if (algorithm != null && hashStrategy != null && compression != null) {
         return new BloomFilterHeader(bloomFilter.getBitsetSize(), algorithm, hashStrategy, compression);
      } else {
         throw new IllegalArgumentException(String.format("Failed to build thrift structure for BloomFilterHeader,algorithm=%s, hash=%s, compression=%s", bloomFilter.getAlgorithm(), bloomFilter.getHashStrategy(), bloomFilter.getCompression()));
      }
   }

   public static SizeStatistics fromParquetSizeStatistics(org.apache.parquet.format.SizeStatistics statistics, PrimitiveType type) {
      return statistics == null ? null : new SizeStatistics(type, statistics.getUnencoded_byte_array_data_bytes(), statistics.getRepetition_level_histogram(), statistics.getDefinition_level_histogram());
   }

   public static org.apache.parquet.format.SizeStatistics toParquetSizeStatistics(SizeStatistics stats) {
      if (stats == null) {
         return null;
      } else {
         org.apache.parquet.format.SizeStatistics formatStats = new org.apache.parquet.format.SizeStatistics();
         if (stats.getUnencodedByteArrayDataBytes().isPresent()) {
            formatStats.setUnencoded_byte_array_data_bytes((Long)stats.getUnencodedByteArrayDataBytes().get());
         }

         List<Long> repLevelHistogram = stats.getRepetitionLevelHistogram();
         if (repLevelHistogram != null && !repLevelHistogram.isEmpty()) {
            formatStats.setRepetition_level_histogram(repLevelHistogram);
         }

         List<Long> defLevelHistogram = stats.getDefinitionLevelHistogram();
         if (defLevelHistogram != null && !defLevelHistogram.isEmpty()) {
            formatStats.setDefinition_level_histogram(defLevelHistogram);
         }

         return formatStats;
      }
   }

   private static class ConvertedTypeConverterVisitor implements LogicalTypeAnnotation.LogicalTypeAnnotationVisitor {
      private ConvertedTypeConverterVisitor() {
      }

      public Optional visit(LogicalTypeAnnotation.StringLogicalTypeAnnotation stringLogicalType) {
         return Optional.of(ConvertedType.UTF8);
      }

      public Optional visit(LogicalTypeAnnotation.MapLogicalTypeAnnotation mapLogicalType) {
         return Optional.of(ConvertedType.MAP);
      }

      public Optional visit(LogicalTypeAnnotation.ListLogicalTypeAnnotation listLogicalType) {
         return Optional.of(ConvertedType.LIST);
      }

      public Optional visit(LogicalTypeAnnotation.EnumLogicalTypeAnnotation enumLogicalType) {
         return Optional.of(ConvertedType.ENUM);
      }

      public Optional visit(LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalLogicalType) {
         return Optional.of(ConvertedType.DECIMAL);
      }

      public Optional visit(LogicalTypeAnnotation.DateLogicalTypeAnnotation dateLogicalType) {
         return Optional.of(ConvertedType.DATE);
      }

      public Optional visit(LogicalTypeAnnotation.TimeLogicalTypeAnnotation timeLogicalType) {
         switch (timeLogicalType.getUnit()) {
            case MICROS:
               return Optional.of(ConvertedType.TIME_MICROS);
            case MILLIS:
               return Optional.of(ConvertedType.TIME_MILLIS);
            case NANOS:
               return Optional.empty();
            default:
               throw new RuntimeException("Unknown converted type for " + timeLogicalType.toOriginalType());
         }
      }

      public Optional visit(LogicalTypeAnnotation.TimestampLogicalTypeAnnotation timestampLogicalType) {
         switch (timestampLogicalType.getUnit()) {
            case MICROS:
               return Optional.of(ConvertedType.TIMESTAMP_MICROS);
            case MILLIS:
               return Optional.of(ConvertedType.TIMESTAMP_MILLIS);
            case NANOS:
               return Optional.empty();
            default:
               throw new RuntimeException("Unknown converted type for " + timestampLogicalType.toOriginalType());
         }
      }

      public Optional visit(LogicalTypeAnnotation.IntLogicalTypeAnnotation intLogicalType) {
         boolean signed = intLogicalType.isSigned();
         switch (intLogicalType.getBitWidth()) {
            case 8:
               return Optional.of(signed ? ConvertedType.INT_8 : ConvertedType.UINT_8);
            case 16:
               return Optional.of(signed ? ConvertedType.INT_16 : ConvertedType.UINT_16);
            case 32:
               return Optional.of(signed ? ConvertedType.INT_32 : ConvertedType.UINT_32);
            case 64:
               return Optional.of(signed ? ConvertedType.INT_64 : ConvertedType.UINT_64);
            default:
               throw new RuntimeException("Unknown original type " + intLogicalType.toOriginalType());
         }
      }

      public Optional visit(LogicalTypeAnnotation.JsonLogicalTypeAnnotation jsonLogicalType) {
         return Optional.of(ConvertedType.JSON);
      }

      public Optional visit(LogicalTypeAnnotation.BsonLogicalTypeAnnotation bsonLogicalType) {
         return Optional.of(ConvertedType.BSON);
      }

      public Optional visit(LogicalTypeAnnotation.IntervalLogicalTypeAnnotation intervalLogicalType) {
         return Optional.of(ConvertedType.INTERVAL);
      }

      public Optional visit(LogicalTypeAnnotation.MapKeyValueTypeAnnotation mapKeyValueLogicalType) {
         return Optional.of(ConvertedType.MAP_KEY_VALUE);
      }
   }

   private static class LogicalTypeConverterVisitor implements LogicalTypeAnnotation.LogicalTypeAnnotationVisitor {
      private LogicalTypeConverterVisitor() {
      }

      public Optional visit(LogicalTypeAnnotation.StringLogicalTypeAnnotation stringLogicalType) {
         return Optional.of(LogicalType.STRING(new StringType()));
      }

      public Optional visit(LogicalTypeAnnotation.MapLogicalTypeAnnotation mapLogicalType) {
         return Optional.of(LogicalType.MAP(new MapType()));
      }

      public Optional visit(LogicalTypeAnnotation.ListLogicalTypeAnnotation listLogicalType) {
         return Optional.of(LogicalType.LIST(new ListType()));
      }

      public Optional visit(LogicalTypeAnnotation.EnumLogicalTypeAnnotation enumLogicalType) {
         return Optional.of(LogicalType.ENUM(new EnumType()));
      }

      public Optional visit(LogicalTypeAnnotation.DecimalLogicalTypeAnnotation decimalLogicalType) {
         return Optional.of(LogicalType.DECIMAL(new DecimalType(decimalLogicalType.getScale(), decimalLogicalType.getPrecision())));
      }

      public Optional visit(LogicalTypeAnnotation.DateLogicalTypeAnnotation dateLogicalType) {
         return Optional.of(LogicalType.DATE(new DateType()));
      }

      public Optional visit(LogicalTypeAnnotation.TimeLogicalTypeAnnotation timeLogicalType) {
         return Optional.of(LogicalType.TIME(new TimeType(timeLogicalType.isAdjustedToUTC(), ParquetMetadataConverter.convertUnit(timeLogicalType.getUnit()))));
      }

      public Optional visit(LogicalTypeAnnotation.TimestampLogicalTypeAnnotation timestampLogicalType) {
         return Optional.of(LogicalType.TIMESTAMP(new TimestampType(timestampLogicalType.isAdjustedToUTC(), ParquetMetadataConverter.convertUnit(timestampLogicalType.getUnit()))));
      }

      public Optional visit(LogicalTypeAnnotation.IntLogicalTypeAnnotation intLogicalType) {
         return Optional.of(LogicalType.INTEGER(new IntType((byte)intLogicalType.getBitWidth(), intLogicalType.isSigned())));
      }

      public Optional visit(LogicalTypeAnnotation.JsonLogicalTypeAnnotation jsonLogicalType) {
         return Optional.of(LogicalType.JSON(new JsonType()));
      }

      public Optional visit(LogicalTypeAnnotation.BsonLogicalTypeAnnotation bsonLogicalType) {
         return Optional.of(LogicalType.BSON(new BsonType()));
      }

      public Optional visit(LogicalTypeAnnotation.UUIDLogicalTypeAnnotation uuidLogicalType) {
         return Optional.of(LogicalType.UUID(new UUIDType()));
      }

      public Optional visit(LogicalTypeAnnotation.Float16LogicalTypeAnnotation float16LogicalType) {
         return Optional.of(LogicalType.FLOAT16(new Float16Type()));
      }

      public Optional visit(LogicalTypeAnnotation.IntervalLogicalTypeAnnotation intervalLogicalType) {
         return Optional.of(LogicalType.UNKNOWN(new NullType()));
      }
   }

   static enum SortOrder {
      SIGNED,
      UNSIGNED,
      UNKNOWN;
   }

   public abstract static class MetadataFilter {
      private MetadataFilter() {
      }

      abstract Object accept(MetadataFilterVisitor var1) throws Throwable;
   }

   private static final class NoFilter extends MetadataFilter {
      private NoFilter() {
      }

      Object accept(MetadataFilterVisitor visitor) throws Throwable {
         return visitor.visit(this);
      }

      public String toString() {
         return "NO_FILTER";
      }
   }

   private static final class SkipMetadataFilter extends MetadataFilter {
      private SkipMetadataFilter() {
      }

      Object accept(MetadataFilterVisitor visitor) throws Throwable {
         return visitor.visit(this);
      }

      public String toString() {
         return "SKIP_ROW_GROUPS";
      }
   }

   static final class RangeMetadataFilter extends MetadataFilter {
      final long startOffset;
      final long endOffset;

      RangeMetadataFilter(long startOffset, long endOffset) {
         this.startOffset = startOffset;
         this.endOffset = endOffset;
      }

      Object accept(MetadataFilterVisitor visitor) throws Throwable {
         return visitor.visit(this);
      }

      public boolean contains(long offset) {
         return offset >= this.startOffset && offset < this.endOffset;
      }

      public String toString() {
         return "range(s:" + this.startOffset + ", e:" + this.endOffset + ")";
      }
   }

   static final class OffsetMetadataFilter extends MetadataFilter {
      private final Set offsets;

      public OffsetMetadataFilter(Set offsets) {
         this.offsets = offsets;
      }

      public boolean contains(long offset) {
         return this.offsets.contains(offset);
      }

      Object accept(MetadataFilterVisitor visitor) throws Throwable {
         return visitor.visit(this);
      }
   }

   private class FileMetaDataAndRowGroupOffsetInfo {
      final FileMetaData fileMetadata;
      final Map rowGroupToRowIndexOffsetMap;

      public FileMetaDataAndRowGroupOffsetInfo(FileMetaData fileMetadata, Map rowGroupToRowIndexOffsetMap) {
         this.fileMetadata = fileMetadata;
         this.rowGroupToRowIndexOffsetMap = rowGroupToRowIndexOffsetMap;
      }
   }

   private interface MetadataFilterVisitor {
      Object visit(NoFilter var1) throws Throwable;

      Object visit(SkipMetadataFilter var1) throws Throwable;

      Object visit(RangeMetadataFilter var1) throws Throwable;

      Object visit(OffsetMetadataFilter var1) throws Throwable;
   }
}
