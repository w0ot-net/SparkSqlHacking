package org.apache.parquet.hadoop.rewrite;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.ParquetReadOptions;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ColumnReader;
import org.apache.parquet.column.ColumnWriteStore;
import org.apache.parquet.column.ColumnWriter;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.ParquetProperties.WriterVersion;
import org.apache.parquet.column.impl.ColumnReadStoreImpl;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.crypto.AesCipher;
import org.apache.parquet.crypto.InternalColumnEncryptionSetup;
import org.apache.parquet.crypto.InternalFileEncryptor;
import org.apache.parquet.crypto.ModuleCipherFactory;
import org.apache.parquet.format.BlockCipher;
import org.apache.parquet.format.DataPageHeader;
import org.apache.parquet.format.DataPageHeaderV2;
import org.apache.parquet.format.DictionaryPageHeader;
import org.apache.parquet.format.PageHeader;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.CodecFactory;
import org.apache.parquet.hadoop.ColumnChunkPageWriteStore;
import org.apache.parquet.hadoop.IndexCache;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.hadoop.util.CompressionConverter;
import org.apache.parquet.hadoop.util.HadoopCodecs;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.OutputFile;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.io.api.PrimitiveConverter;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.InvalidSchemaException;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;
import org.apache.parquet.schema.Type.Repetition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParquetRewriter implements Closeable {
   public static final String ORIGINAL_CREATED_BY_KEY = "original.created.by";
   private static final Logger LOG = LoggerFactory.getLogger(ParquetRewriter.class);
   private final int pageBufferSize = 2097152;
   private final byte[] pageBuffer = new byte[2097152];
   private final CompressionCodecName newCodecName;
   private Map maskColumns = null;
   private Set encryptColumns = null;
   private boolean encryptMode = false;
   private final Map extraMetaData;
   private final ParquetFileWriter writer;
   private int numBlocksRewritten = 0;
   private final Queue inputFiles = new LinkedList();
   private final Queue inputFilesToJoin = new LinkedList();
   private final MessageType outSchema;
   private final IndexCache.CacheStrategy indexCacheStrategy;
   private final boolean overwriteInputWithJoinColumns;
   private final InternalFileEncryptor nullColumnEncryptor;
   private final Map renamedColumns;

   public ParquetRewriter(RewriteOptions options) throws IOException {
      this.newCodecName = options.getNewCodecName();
      this.indexCacheStrategy = options.getIndexCacheStrategy();
      this.overwriteInputWithJoinColumns = options.getOverwriteInputWithJoinColumns();
      this.renamedColumns = options.getRenameColumns();
      ParquetConfiguration conf = options.getParquetConfiguration();
      this.inputFiles.addAll(this.getFileReaders(options.getParquetInputFiles(), conf));
      this.inputFilesToJoin.addAll(this.getFileReaders(options.getParquetInputFilesToJoin(), conf));
      this.outSchema = this.pruneColumnsInSchema(this.getSchema(), options.getPruneColumns());
      this.extraMetaData = this.getExtraMetadata(options);
      this.ensureSameSchema(this.inputFiles);
      this.ensureSameSchema(this.inputFilesToJoin);
      this.ensureRowCount();
      this.ensureRenamingCorrectness(this.outSchema, this.renamedColumns);
      OutputFile out = options.getParquetOutputFile();
      LOG.info("Start rewriting {} input file(s) {} to {}", new Object[]{this.inputFiles.size() + this.inputFilesToJoin.size(), Stream.concat(options.getParquetInputFiles().stream(), options.getParquetInputFilesToJoin().stream()).collect(Collectors.toList()), out});
      if (options.getMaskColumns() != null) {
         this.maskColumns = new HashMap();

         for(Map.Entry col : options.getMaskColumns().entrySet()) {
            this.maskColumns.put(ColumnPath.fromDotString((String)col.getKey()), col.getValue());
         }
      }

      if (options.getEncryptColumns() != null && options.getFileEncryptionProperties() != null) {
         this.encryptColumns = this.convertToColumnPaths(options.getEncryptColumns());
         this.encryptMode = true;
      }

      ParquetFileWriter.Mode writerMode = ParquetFileWriter.Mode.CREATE;
      this.writer = new ParquetFileWriter(out, this.renamedColumns.isEmpty() ? this.outSchema : this.getSchemaWithRenamedColumns(this.outSchema), writerMode, 134217728L, 8388608, 64, Integer.MAX_VALUE, true, options.getFileEncryptionProperties());
      this.writer.start();
      if (options.getFileEncryptionProperties() == null) {
         this.nullColumnEncryptor = null;
      } else {
         this.nullColumnEncryptor = new InternalFileEncryptor(options.getFileEncryptionProperties());
         List<ColumnDescriptor> columns = this.getSchemaWithRenamedColumns(this.outSchema).getColumns();

         for(int i = 0; i < columns.size(); ++i) {
            this.writer.getEncryptor().getColumnSetup(ColumnPath.get(((ColumnDescriptor)columns.get(i)).getPath()), true, i);
         }
      }

   }

   public ParquetRewriter(CompressionConverter.TransParquetFileReader reader, ParquetFileWriter writer, ParquetMetadata meta, MessageType outSchema, String originalCreatedBy, CompressionCodecName codecName, List maskColumns, MaskMode maskMode) {
      this.writer = writer;
      this.outSchema = outSchema;
      this.newCodecName = codecName;
      this.extraMetaData = new HashMap(meta.getFileMetaData().getKeyValueMetaData());
      this.extraMetaData.put("original.created.by", originalCreatedBy != null ? originalCreatedBy : meta.getFileMetaData().getCreatedBy());
      if (maskColumns != null && maskMode != null) {
         this.maskColumns = new HashMap();

         for(String col : maskColumns) {
            this.maskColumns.put(ColumnPath.fromDotString(col), maskMode);
         }
      }

      this.inputFiles.add(reader);
      this.indexCacheStrategy = IndexCache.CacheStrategy.NONE;
      this.overwriteInputWithJoinColumns = false;
      this.nullColumnEncryptor = null;
      this.renamedColumns = new HashMap();
   }

   private MessageType getSchema() {
      MessageType schemaMain = ((CompressionConverter.TransParquetFileReader)this.inputFiles.peek()).getFooter().getFileMetaData().getSchema();
      if (this.inputFilesToJoin.isEmpty()) {
         return schemaMain;
      } else {
         Map<String, Type> fieldNames = new LinkedHashMap();
         schemaMain.getFields().forEach((x) -> {
            Type var10000 = (Type)fieldNames.put(x.getName(), x);
         });
         ((CompressionConverter.TransParquetFileReader)this.inputFilesToJoin.peek()).getFooter().getFileMetaData().getSchema().getFields().forEach((x) -> {
            if (!fieldNames.containsKey(x.getName())) {
               fieldNames.put(x.getName(), x);
            } else if (this.overwriteInputWithJoinColumns) {
               LOG.info("Column {} in inputFiles is overwritten by inputFilesToJoin side", x.getName());
               fieldNames.put(x.getName(), x);
            }

         });
         return new MessageType(schemaMain.getName(), new ArrayList(fieldNames.values()));
      }
   }

   private MessageType getSchemaWithRenamedColumns(MessageType schema) {
      List<Type> fields = (List)schema.getFields().stream().map((type) -> {
         if (!this.renamedColumns.containsKey(type.getName())) {
            return type;
         } else {
            return (Type)(type.isPrimitive() ? new PrimitiveType(type.getRepetition(), type.asPrimitiveType().getPrimitiveTypeName(), (String)this.renamedColumns.get(type.getName())) : new GroupType(type.getRepetition(), (String)this.renamedColumns.get(type.getName()), type.asGroupType().getFields()));
         }
      }).collect(Collectors.toList());
      return new MessageType(schema.getName(), fields);
   }

   private Map getExtraMetadata(RewriteOptions options) {
      List<CompressionConverter.TransParquetFileReader> allFiles;
      if (options.getIgnoreJoinFilesMetadata()) {
         allFiles = new ArrayList(this.inputFiles);
      } else {
         allFiles = (List)Stream.concat(this.inputFiles.stream(), this.inputFilesToJoin.stream()).collect(Collectors.toList());
      }

      Map<String, String> result = new HashMap();
      result.put("original.created.by", ((Set)allFiles.stream().map((x) -> x.getFooter().getFileMetaData().getCreatedBy()).collect(Collectors.toSet())).stream().reduce((a, b) -> a + "\n" + b).orElse(""));
      allFiles.forEach((x) -> result.putAll(x.getFileMetaData().getKeyValueMetaData()));
      return result;
   }

   private void ensureRowCount() {
      if (!this.inputFilesToJoin.isEmpty()) {
         List<Long> blocksRowCountsL = (List)this.inputFiles.stream().flatMap((x) -> x.getFooter().getBlocks().stream().map(BlockMetaData::getRowCount)).collect(Collectors.toList());
         List<Long> blocksRowCountsR = (List)this.inputFilesToJoin.stream().flatMap((x) -> x.getFooter().getBlocks().stream().map(BlockMetaData::getRowCount)).collect(Collectors.toList());
         if (!blocksRowCountsL.equals(blocksRowCountsR)) {
            throw new IllegalArgumentException("The number of rows in each block must match! Left blocks row counts: " + blocksRowCountsL + ", right blocks row counts" + blocksRowCountsR + ".");
         }
      }

   }

   private Queue getFileReaders(List inputFiles, ParquetConfiguration conf) {
      LinkedList<CompressionConverter.TransParquetFileReader> inputFileReaders = new LinkedList();

      for(InputFile inputFile : inputFiles) {
         try {
            CompressionConverter.TransParquetFileReader reader = new CompressionConverter.TransParquetFileReader(inputFile, ParquetReadOptions.builder(conf).build());
            inputFileReaders.add(reader);
         } catch (IOException e) {
            throw new IllegalArgumentException("Failed to open input file: " + inputFile, e);
         }
      }

      return inputFileReaders;
   }

   private void ensureSameSchema(Queue inputFileReaders) {
      MessageType schema = null;

      for(CompressionConverter.TransParquetFileReader reader : inputFileReaders) {
         MessageType newSchema = reader.getFooter().getFileMetaData().getSchema();
         if (schema == null) {
            schema = newSchema;
         } else if (!schema.equals(newSchema)) {
            String file = reader.getFile();
            LOG.error("Input files have different schemas, expect: {}, input: {}, current file: {}", new Object[]{schema, newSchema, file});
            throw new InvalidSchemaException("Input files have different schemas, current file: " + file);
         }
      }

   }

   private void ensureRenamingCorrectness(MessageType schema, Map renameMap) {
      Set<String> columns = (Set)schema.getFields().stream().map(Type::getName).collect(Collectors.toSet());
      renameMap.forEach((src, dst) -> {
         if (!columns.contains(src)) {
            String msg = String.format("Column to rename '%s' is not found in input files schema", src);
            LOG.error(msg);
            throw new IllegalArgumentException(msg);
         } else if (columns.contains(dst)) {
            String msg = String.format("Renamed column target name '%s' is already present in a schema", dst);
            LOG.error(msg);
            throw new IllegalArgumentException(msg);
         }
      });
   }

   public void close() throws IOException {
      this.writer.end(this.extraMetaData);
   }

   public void processBlocks() throws IOException {
      CompressionConverter.TransParquetFileReader readerToJoin = null;
      IndexCache indexCacheToJoin = null;
      int blockIdxToJoin = 0;
      List<ColumnDescriptor> outColumns = this.outSchema.getColumns();

      while(!this.inputFiles.isEmpty()) {
         CompressionConverter.TransParquetFileReader reader = (CompressionConverter.TransParquetFileReader)this.inputFiles.poll();
         LOG.info("Rewriting input file: {}, remaining files: {}", reader.getFile(), this.inputFiles.size());
         ParquetMetadata meta = reader.getFooter();
         Set<ColumnPath> columnPaths = (Set)meta.getFileMetaData().getSchema().getColumns().stream().map((x) -> ColumnPath.get(x.getPath())).collect(Collectors.toSet());
         IndexCache indexCache = IndexCache.create(reader, columnPaths, this.indexCacheStrategy, true);

         for(int blockIdx = 0; blockIdx < meta.getBlocks().size(); ++blockIdx) {
            BlockMetaData blockMetaData = (BlockMetaData)meta.getBlocks().get(blockIdx);
            this.writer.startBlock(blockMetaData.getRowCount());
            indexCache.setBlockMetadata(blockMetaData);
            Map<ColumnPath, ColumnChunkMetaData> pathToChunk = (Map)blockMetaData.getColumns().stream().collect(Collectors.toMap((x) -> x.getPath(), (x) -> x));
            if (!this.inputFilesToJoin.isEmpty()) {
               label77: {
                  if (readerToJoin != null) {
                     ++blockIdxToJoin;
                     if (blockIdxToJoin != readerToJoin.getFooter().getBlocks().size()) {
                        ++blockIdxToJoin;
                        indexCacheToJoin.setBlockMetadata((BlockMetaData)readerToJoin.getFooter().getBlocks().get(blockIdxToJoin));
                        break label77;
                     }
                  }

                  if (readerToJoin != null) {
                     readerToJoin.close();
                  }

                  blockIdxToJoin = 0;
                  readerToJoin = (CompressionConverter.TransParquetFileReader)this.inputFilesToJoin.poll();
                  Set<ColumnPath> columnPathsToJoin = (Set)readerToJoin.getFileMetaData().getSchema().getColumns().stream().map((x) -> ColumnPath.get(x.getPath())).collect(Collectors.toSet());
                  if (indexCacheToJoin != null) {
                     indexCacheToJoin.clean();
                  }

                  indexCacheToJoin = IndexCache.create(readerToJoin, columnPathsToJoin, this.indexCacheStrategy, true);
                  indexCacheToJoin.setBlockMetadata((BlockMetaData)readerToJoin.getFooter().getBlocks().get(blockIdxToJoin));
               }
            }

            for(int outColumnIdx = 0; outColumnIdx < outColumns.size(); ++outColumnIdx) {
               ColumnPath colPath = ColumnPath.get(((ColumnDescriptor)outColumns.get(outColumnIdx)).getPath());
               if (readerToJoin != null) {
                  Optional<ColumnChunkMetaData> chunkToJoin = ((BlockMetaData)readerToJoin.getFooter().getBlocks().get(blockIdxToJoin)).getColumns().stream().filter((x) -> x.getPath().equals(colPath)).findFirst();
                  if (!chunkToJoin.isPresent() || !this.overwriteInputWithJoinColumns && columnPaths.contains(colPath)) {
                     this.processBlock(reader, blockIdx, outColumnIdx, indexCache, (ColumnChunkMetaData)pathToChunk.get(colPath));
                  } else {
                     this.processBlock(readerToJoin, blockIdxToJoin, outColumnIdx, indexCacheToJoin, (ColumnChunkMetaData)chunkToJoin.get());
                  }
               } else {
                  this.processBlock(reader, blockIdx, outColumnIdx, indexCache, (ColumnChunkMetaData)pathToChunk.get(colPath));
               }
            }

            this.writer.endBlock();
            indexCache.clean();
            ++this.numBlocksRewritten;
         }

         indexCache.clean();
         LOG.info("Finish rewriting input file: {}", reader.getFile());
         reader.close();
      }

      if (readerToJoin != null) {
         readerToJoin.close();
      }

   }

   private ColumnPath normalizeFieldsInPath(ColumnPath path) {
      if (this.renamedColumns.isEmpty()) {
         return path;
      } else {
         String[] pathArray = path.toArray();
         pathArray[0] = (String)this.renamedColumns.getOrDefault(pathArray[0], pathArray[0]);
         return ColumnPath.get(pathArray);
      }
   }

   private PrimitiveType normalizeNameInType(PrimitiveType type) {
      return this.renamedColumns.isEmpty() ? type : new PrimitiveType(type.getRepetition(), type.asPrimitiveType().getPrimitiveTypeName(), (String)this.renamedColumns.getOrDefault(type.getName(), type.getName()));
   }

   private void processBlock(CompressionConverter.TransParquetFileReader reader, int blockIdx, int outColumnIdx, IndexCache indexCache, ColumnChunkMetaData chunk) throws IOException {
      if (chunk.isEncrypted()) {
         throw new IOException("Column " + chunk.getPath().toDotString() + " is already encrypted");
      } else {
         ColumnChunkMetaData chunkNormalized = chunk;
         if (!this.renamedColumns.isEmpty()) {
            chunkNormalized = ColumnChunkMetaData.get(this.normalizeFieldsInPath(chunk.getPath()), this.normalizeNameInType(chunk.getPrimitiveType()), chunk.getCodec(), chunk.getEncodingStats(), chunk.getEncodings(), chunk.getStatistics(), chunk.getFirstDataPageOffset(), chunk.getDictionaryPageOffset(), chunk.getValueCount(), chunk.getTotalSize(), chunk.getTotalUncompressedSize(), chunk.getSizeStatistics());
         }

         ColumnDescriptor descriptorOriginal = (ColumnDescriptor)this.outSchema.getColumns().get(outColumnIdx);
         ColumnDescriptor descriptorRenamed = (ColumnDescriptor)this.getSchemaWithRenamedColumns(this.outSchema).getColumns().get(outColumnIdx);
         BlockMetaData blockMetaData = (BlockMetaData)reader.getFooter().getBlocks().get(blockIdx);
         String originalCreatedBy = reader.getFileMetaData().getCreatedBy();
         reader.setStreamPosition(chunk.getStartingPos());
         CompressionCodecName newCodecName = this.newCodecName == null ? chunk.getCodec() : this.newCodecName;
         boolean encryptColumn = this.encryptMode && this.encryptColumns != null && this.encryptColumns.contains(chunk.getPath());
         if (this.maskColumns != null && this.maskColumns.containsKey(chunk.getPath())) {
            MaskMode maskMode = (MaskMode)this.maskColumns.get(chunk.getPath());
            if (!maskMode.equals(MaskMode.NULLIFY)) {
               throw new UnsupportedOperationException("Only nullify is supported for now");
            }

            Type.Repetition repetition = descriptorOriginal.getPrimitiveType().getRepetition();
            if (repetition.equals(Repetition.REQUIRED)) {
               throw new IOException("Required column [" + descriptorOriginal.getPrimitiveType().getName() + "] cannot be nullified");
            }

            this.nullifyColumn(reader, blockIdx, descriptorOriginal, chunk, this.writer, newCodecName, encryptColumn, originalCreatedBy);
         } else if (!this.encryptMode && this.newCodecName == null) {
            BloomFilter bloomFilter = indexCache.getBloomFilter(chunk);
            ColumnIndex columnIndex = indexCache.getColumnIndex(chunk);
            OffsetIndex offsetIndex = indexCache.getOffsetIndex(chunk);
            this.writer.appendColumnChunk(descriptorRenamed, reader.getStream(), chunkNormalized, bloomFilter, columnIndex, offsetIndex);
         } else {
            ColumnChunkEncryptorRunTime columnChunkEncryptorRunTime = null;
            if (this.encryptMode) {
               columnChunkEncryptorRunTime = new ColumnChunkEncryptorRunTime(this.writer.getEncryptor(), chunk, this.numBlocksRewritten, outColumnIdx);
            }

            this.writer.startColumn(descriptorRenamed, chunk.getValueCount(), newCodecName);
            this.processChunk(reader, blockMetaData.getRowCount(), chunk, newCodecName, columnChunkEncryptorRunTime, encryptColumn, indexCache.getBloomFilter(chunk), indexCache.getColumnIndex(chunk), indexCache.getOffsetIndex(chunk), originalCreatedBy);
            this.writer.endColumn();
         }

      }
   }

   private void processChunk(CompressionConverter.TransParquetFileReader reader, long blockRowCount, ColumnChunkMetaData chunk, CompressionCodecName newCodecName, ColumnChunkEncryptorRunTime columnChunkEncryptorRunTime, boolean encryptColumn, BloomFilter bloomFilter, ColumnIndex columnIndex, OffsetIndex offsetIndex, String originalCreatedBy) throws IOException {
      CompressionCodecFactory codecFactory = HadoopCodecs.newFactory(0);
      CompressionCodecFactory.BytesInputDecompressor decompressor = null;
      CompressionCodecFactory.BytesInputCompressor compressor = null;
      if (!newCodecName.equals(chunk.getCodec())) {
         decompressor = codecFactory.getDecompressor(chunk.getCodec());
         compressor = codecFactory.getCompressor(newCodecName);
      }

      BlockCipher.Encryptor metaEncryptor = null;
      BlockCipher.Encryptor dataEncryptor = null;
      byte[] dictPageAAD = null;
      byte[] dataPageAAD = null;
      byte[] dictPageHeaderAAD = null;
      byte[] dataPageHeaderAAD = null;
      if (columnChunkEncryptorRunTime != null) {
         metaEncryptor = columnChunkEncryptorRunTime.getMetaDataEncryptor();
         dataEncryptor = columnChunkEncryptorRunTime.getDataEncryptor();
         dictPageAAD = columnChunkEncryptorRunTime.getDictPageAAD();
         dataPageAAD = columnChunkEncryptorRunTime.getDataPageAAD();
         dictPageHeaderAAD = columnChunkEncryptorRunTime.getDictPageHeaderAAD();
         dataPageHeaderAAD = columnChunkEncryptorRunTime.getDataPageHeaderAAD();
      }

      if (bloomFilter != null) {
         this.writer.addBloomFilter(this.normalizeFieldsInPath(chunk.getPath()).toDotString(), bloomFilter);
      }

      reader.setStreamPosition(chunk.getStartingPos());
      DictionaryPage dictionaryPage = null;
      long readValues = 0L;
      long readRows = 0L;
      Statistics<?> statistics = null;
      boolean isColumnStatisticsMalformed = false;
      ParquetMetadataConverter converter = new ParquetMetadataConverter();
      int pageOrdinal = 0;
      long totalChunkValues = chunk.getValueCount();

      while(readValues < totalChunkValues) {
         PageHeader pageHeader = reader.readPageHeader();
         int compressedPageSize = pageHeader.getCompressed_page_size();
         switch (pageHeader.type) {
            case DICTIONARY_PAGE:
               if (dictionaryPage != null) {
                  throw new IOException("has more than one dictionary page in column chunk: " + chunk);
               }

               DictionaryPageHeader dictPageHeader = pageHeader.dictionary_page_header;
               byte[] pageLoad = this.processPageLoad(reader, true, compressor, decompressor, pageHeader.getCompressed_page_size(), pageHeader.getUncompressed_page_size(), encryptColumn, dataEncryptor, dictPageAAD);
               dictionaryPage = new DictionaryPage(BytesInput.from(pageLoad), pageHeader.getUncompressed_page_size(), dictPageHeader.getNum_values(), converter.getEncoding(dictPageHeader.getEncoding()));
               this.writer.writeDictionaryPage(dictionaryPage, metaEncryptor, dictPageHeaderAAD);
               break;
            case DATA_PAGE:
               if (encryptColumn) {
                  AesCipher.quickUpdatePageAAD(dataPageHeaderAAD, pageOrdinal);
                  AesCipher.quickUpdatePageAAD(dataPageAAD, pageOrdinal);
               }

               DataPageHeader headerV1 = pageHeader.data_page_header;
               byte[] pageLoad = this.processPageLoad(reader, true, compressor, decompressor, pageHeader.getCompressed_page_size(), pageHeader.getUncompressed_page_size(), encryptColumn, dataEncryptor, dataPageAAD);
               statistics = this.convertStatistics(originalCreatedBy, this.normalizeNameInType(chunk.getPrimitiveType()), headerV1.getStatistics(), columnIndex, pageOrdinal, converter);
               if (statistics == null) {
                  isColumnStatisticsMalformed = true;
               } else {
                  Preconditions.checkState(!isColumnStatisticsMalformed, "Detected mixed null page statistics and non-null page statistics");
               }

               readValues += (long)headerV1.getNum_values();
               if (offsetIndex != null) {
                  long rowCount = 1L + offsetIndex.getLastRowIndex(pageOrdinal, blockRowCount) - offsetIndex.getFirstRowIndex(pageOrdinal);
                  readRows += rowCount;
                  this.writer.writeDataPage(this.toIntWithCheck((long)headerV1.getNum_values()), pageHeader.getUncompressed_page_size(), BytesInput.from(pageLoad), statistics, (long)this.toIntWithCheck(rowCount), converter.getEncoding(headerV1.getRepetition_level_encoding()), converter.getEncoding(headerV1.getDefinition_level_encoding()), converter.getEncoding(headerV1.getEncoding()), metaEncryptor, dataPageHeaderAAD);
               } else {
                  this.writer.writeDataPage(this.toIntWithCheck((long)headerV1.getNum_values()), pageHeader.getUncompressed_page_size(), BytesInput.from(pageLoad), statistics, converter.getEncoding(headerV1.getRepetition_level_encoding()), converter.getEncoding(headerV1.getDefinition_level_encoding()), converter.getEncoding(headerV1.getEncoding()), metaEncryptor, dataPageHeaderAAD);
               }

               ++pageOrdinal;
               break;
            case DATA_PAGE_V2:
               if (encryptColumn) {
                  AesCipher.quickUpdatePageAAD(dataPageHeaderAAD, pageOrdinal);
                  AesCipher.quickUpdatePageAAD(dataPageAAD, pageOrdinal);
               }

               DataPageHeaderV2 headerV2 = pageHeader.data_page_header_v2;
               int rlLength = headerV2.getRepetition_levels_byte_length();
               BytesInput rlLevels = this.readBlockAllocate(rlLength, reader);
               int dlLength = headerV2.getDefinition_levels_byte_length();
               BytesInput dlLevels = this.readBlockAllocate(dlLength, reader);
               int payLoadLength = pageHeader.getCompressed_page_size() - rlLength - dlLength;
               int rawDataLength = pageHeader.getUncompressed_page_size() - rlLength - dlLength;
               byte[] pageLoad = this.processPageLoad(reader, headerV2.is_compressed, compressor, decompressor, payLoadLength, rawDataLength, encryptColumn, dataEncryptor, dataPageAAD);
               statistics = this.convertStatistics(originalCreatedBy, this.normalizeNameInType(chunk.getPrimitiveType()), headerV2.getStatistics(), columnIndex, pageOrdinal, converter);
               if (statistics == null) {
                  isColumnStatisticsMalformed = true;
               } else {
                  Preconditions.checkState(!isColumnStatisticsMalformed, "Detected mixed null page statistics and non-null page statistics");
               }

               readValues += (long)headerV2.getNum_values();
               readRows += (long)headerV2.getNum_rows();
               this.writer.writeDataPageV2(headerV2.getNum_rows(), headerV2.getNum_nulls(), headerV2.getNum_values(), rlLevels, dlLevels, converter.getEncoding(headerV2.getEncoding()), BytesInput.from(pageLoad), rawDataLength, statistics, metaEncryptor, dataPageHeaderAAD);
               ++pageOrdinal;
               break;
            default:
               LOG.debug("skipping page of type {} of size {}", pageHeader.getType(), compressedPageSize);
         }
      }

      Preconditions.checkState(readRows == 0L || readRows == blockRowCount, "Read row count: %s not match with block total row count: %s", readRows, blockRowCount);
      if (isColumnStatisticsMalformed) {
         this.writer.invalidateStatistics(chunk.getStatistics());
      }

   }

   private Statistics convertStatistics(String createdBy, PrimitiveType type, org.apache.parquet.format.Statistics pageStatistics, ColumnIndex columnIndex, int pageIndex, ParquetMetadataConverter converter) throws IOException {
      if (columnIndex != null) {
         if (columnIndex.getNullPages() == null) {
            throw new IOException("columnIndex has null variable 'nullPages' which indicates corrupted data for type: " + type.getName());
         } else if (pageIndex > columnIndex.getNullPages().size()) {
            throw new IOException("There are more pages " + pageIndex + " found in the column than in the columnIndex " + columnIndex.getNullPages().size());
         } else {
            Statistics.Builder statsBuilder = Statistics.getBuilderForReading(type);
            statsBuilder.withNumNulls((Long)columnIndex.getNullCounts().get(pageIndex));
            if (!(Boolean)columnIndex.getNullPages().get(pageIndex)) {
               statsBuilder.withMin((byte[])((ByteBuffer)columnIndex.getMinValues().get(pageIndex)).array().clone());
               statsBuilder.withMax((byte[])((ByteBuffer)columnIndex.getMaxValues().get(pageIndex)).array().clone());
            }

            return statsBuilder.build();
         }
      } else {
         return pageStatistics != null ? converter.fromParquetStatistics(createdBy, pageStatistics, type) : null;
      }
   }

   private byte[] processPageLoad(CompressionConverter.TransParquetFileReader reader, boolean isCompressed, CompressionCodecFactory.BytesInputCompressor compressor, CompressionCodecFactory.BytesInputDecompressor decompressor, int payloadLength, int rawDataLength, boolean encrypt, BlockCipher.Encryptor dataEncryptor, byte[] AAD) throws IOException {
      BytesInput data = this.readBlock(payloadLength, reader);
      if (compressor != null) {
         if (isCompressed) {
            data = decompressor.decompress(data, rawDataLength);
         }

         data = compressor.compress(data);
      }

      return !encrypt ? data.toByteArray() : dataEncryptor.encrypt(data.toByteArray(), AAD);
   }

   public BytesInput readBlock(int length, CompressionConverter.TransParquetFileReader reader) throws IOException {
      byte[] data;
      if (length > 2097152) {
         data = new byte[length];
      } else {
         data = this.pageBuffer;
      }

      reader.blockRead(data, 0, length);
      return BytesInput.from(data, 0, length);
   }

   public BytesInput readBlockAllocate(int length, CompressionConverter.TransParquetFileReader reader) throws IOException {
      byte[] data = new byte[length];
      reader.blockRead(data, 0, length);
      return BytesInput.from(data, 0, length);
   }

   private int toIntWithCheck(long size) {
      if ((long)((int)size) != size) {
         throw new ParquetEncodingException("size is bigger than 2147483647 bytes: " + size);
      } else {
         return (int)size;
      }
   }

   private void getPaths(GroupType schema, List paths, String parent) {
      List<Type> fields = schema.getFields();
      String prefix = parent == null ? "" : parent + ".";

      for(Type field : fields) {
         paths.add(prefix + field.getName());
         if (field instanceof GroupType) {
            this.getPaths(field.asGroupType(), paths, prefix + field.getName());
         }
      }

   }

   private MessageType pruneColumnsInSchema(MessageType schema, List pruneColumns) {
      if (pruneColumns != null && !pruneColumns.isEmpty()) {
         List<String> paths = new ArrayList();
         this.getPaths(schema, paths, (String)null);

         for(String col : pruneColumns) {
            if (!paths.contains(col)) {
               LOG.warn("Input column name {} doesn't show up in the schema", col);
            }
         }

         Set<ColumnPath> prunePaths = this.convertToColumnPaths(pruneColumns);
         List<Type> fields = schema.getFields();
         List<String> currentPath = new ArrayList();
         List<Type> prunedFields = this.pruneColumnsInFields(fields, currentPath, prunePaths);
         return new MessageType(schema.getName(), prunedFields);
      } else {
         return schema;
      }
   }

   private List pruneColumnsInFields(List fields, List currentPath, Set prunePaths) {
      List<Type> prunedFields = new ArrayList();

      for(Type childField : fields) {
         Type prunedChildField = this.pruneColumnsInField(childField, currentPath, prunePaths);
         if (prunedChildField != null) {
            prunedFields.add(prunedChildField);
         }
      }

      return prunedFields;
   }

   private Type pruneColumnsInField(Type field, List currentPath, Set prunePaths) {
      String fieldName = field.getName();
      currentPath.add(fieldName);
      ColumnPath path = ColumnPath.get((String[])currentPath.toArray(new String[0]));
      Type prunedField = null;
      if (!prunePaths.contains(path)) {
         if (field.isPrimitive()) {
            prunedField = field;
         } else {
            List<Type> childFields = ((GroupType)field).getFields();
            List<Type> prunedFields = this.pruneColumnsInFields(childFields, currentPath, prunePaths);
            if (!prunedFields.isEmpty()) {
               prunedField = ((GroupType)field).withNewFields(prunedFields);
            }
         }
      }

      currentPath.remove(currentPath.size() - 1);
      return prunedField;
   }

   private Set convertToColumnPaths(List cols) {
      Set<ColumnPath> prunePaths = new HashSet();

      for(String col : cols) {
         prunePaths.add(ColumnPath.fromDotString(col));
      }

      return prunePaths;
   }

   private void nullifyColumn(CompressionConverter.TransParquetFileReader reader, int blockIndex, ColumnDescriptor descriptor, ColumnChunkMetaData chunk, ParquetFileWriter writer, CompressionCodecName newCodecName, boolean encryptColumn, String originalCreatedBy) throws IOException {
      if (encryptColumn) {
         Preconditions.checkArgument(writer.getEncryptor() != null, "Missing encryptor");
      }

      long totalChunkValues = chunk.getValueCount();
      int dMax = descriptor.getMaxDefinitionLevel();
      PageReadStore pageReadStore = reader.readRowGroup(blockIndex);
      ColumnReadStoreImpl crStore = new ColumnReadStoreImpl(pageReadStore, new DummyGroupConverter(), this.outSchema, originalCreatedBy);
      ColumnReader cReader = crStore.getColumnReader(descriptor);
      ParquetProperties.WriterVersion writerVersion = chunk.getEncodingStats().usesV2Pages() ? WriterVersion.PARQUET_2_0 : WriterVersion.PARQUET_1_0;
      ParquetProperties props = ParquetProperties.builder().withWriterVersion(writerVersion).build();
      CodecFactory codecFactory = new CodecFactory(new Configuration(), props.getPageSizeThreshold());
      CompressionCodecFactory.BytesInputCompressor compressor = codecFactory.getCompressor(newCodecName);
      MessageType newSchema = this.getSchemaWithRenamedColumns(this.newSchema(this.outSchema, descriptor));
      ColumnChunkPageWriteStore cPageStore = new ColumnChunkPageWriteStore(compressor, newSchema, props.getAllocator(), props.getColumnIndexTruncateLength(), props.getPageWriteChecksumEnabled(), this.nullColumnEncryptor, this.numBlocksRewritten);
      ColumnWriteStore cStore = props.newColumnWriteStore(newSchema, cPageStore);
      ColumnWriter cWriter = cStore.getColumnWriter(descriptor);

      for(int i = 0; (long)i < totalChunkValues; ++i) {
         int rlvl = cReader.getCurrentRepetitionLevel();
         int dlvl = cReader.getCurrentDefinitionLevel();
         if (dlvl == dMax) {
            if (dlvl == 0) {
               throw new IOException("definition level is detected to be 0 for column " + chunk.getPath().toDotString() + " to be nullified");
            }

            if (rlvl == 0) {
               cWriter.writeNull(rlvl, dlvl - 1);
            }
         } else {
            cWriter.writeNull(rlvl, dlvl);
         }

         cStore.endRecord();
      }

      pageReadStore.close();
      cStore.flush();
      cPageStore.flushToFileWriter(writer);
      cStore.close();
      cWriter.close();
   }

   private MessageType newSchema(MessageType schema, ColumnDescriptor descriptor) {
      String[] path = descriptor.getPath();
      Type type = schema.getType(path);
      if (path.length == 1) {
         return new MessageType(schema.getName(), new Type[]{type});
      } else {
         for(Type field : schema.getFields()) {
            if (!field.isPrimitive()) {
               Type newType = this.extractField(field.asGroupType(), type);
               if (newType != null) {
                  return new MessageType(schema.getName(), new Type[]{newType});
               }
            }
         }

         throw new RuntimeException("No field is found");
      }
   }

   private Type extractField(GroupType candidate, Type targetField) {
      if (targetField.equals(candidate)) {
         return targetField;
      } else {
         for(Type field : candidate.asGroupType().getFields()) {
            if (field.isPrimitive()) {
               if (field.equals(targetField)) {
                  return new GroupType(candidate.getRepetition(), candidate.getName(), new Type[]{targetField});
               }
            } else {
               Type tempField = this.extractField(field.asGroupType(), targetField);
               if (tempField != null) {
                  return new GroupType(candidate.getRepetition(), candidate.getName(), new Type[]{tempField});
               }
            }
         }

         return null;
      }
   }

   private static final class DummyGroupConverter extends GroupConverter {
      private DummyGroupConverter() {
      }

      public void start() {
      }

      public void end() {
      }

      public Converter getConverter(int fieldIndex) {
         return new DummyConverter();
      }
   }

   private static final class DummyConverter extends PrimitiveConverter {
      private DummyConverter() {
      }

      public GroupConverter asGroupConverter() {
         return new DummyGroupConverter();
      }
   }

   private static class ColumnChunkEncryptorRunTime {
      private final InternalColumnEncryptionSetup colEncrSetup;
      private final BlockCipher.Encryptor dataEncryptor;
      private final BlockCipher.Encryptor metaDataEncryptor;
      private final byte[] fileAAD;
      private final byte[] dataPageHeaderAAD;
      private final byte[] dataPageAAD;
      private final byte[] dictPageHeaderAAD;
      private final byte[] dictPageAAD;

      public ColumnChunkEncryptorRunTime(InternalFileEncryptor fileEncryptor, ColumnChunkMetaData chunk, int blockId, int columnId) throws IOException {
         Preconditions.checkArgument(fileEncryptor != null, "FileEncryptor is required to create ColumnChunkEncryptorRunTime");
         this.colEncrSetup = fileEncryptor.getColumnSetup(chunk.getPath(), true, columnId);
         this.dataEncryptor = this.colEncrSetup.getDataEncryptor();
         this.metaDataEncryptor = this.colEncrSetup.getMetaDataEncryptor();
         this.fileAAD = fileEncryptor.getFileAAD();
         if (this.colEncrSetup != null && this.colEncrSetup.isEncrypted()) {
            this.dataPageHeaderAAD = this.createAAD(ModuleCipherFactory.ModuleType.DataPageHeader, blockId, columnId);
            this.dataPageAAD = this.createAAD(ModuleCipherFactory.ModuleType.DataPage, blockId, columnId);
            this.dictPageHeaderAAD = this.createAAD(ModuleCipherFactory.ModuleType.DictionaryPageHeader, blockId, columnId);
            this.dictPageAAD = this.createAAD(ModuleCipherFactory.ModuleType.DictionaryPage, blockId, columnId);
         } else {
            this.dataPageHeaderAAD = null;
            this.dataPageAAD = null;
            this.dictPageHeaderAAD = null;
            this.dictPageAAD = null;
         }

      }

      private byte[] createAAD(ModuleCipherFactory.ModuleType moduleType, int blockId, int columnId) {
         return AesCipher.createModuleAAD(this.fileAAD, moduleType, blockId, columnId, 0);
      }

      public BlockCipher.Encryptor getDataEncryptor() {
         return this.dataEncryptor;
      }

      public BlockCipher.Encryptor getMetaDataEncryptor() {
         return this.metaDataEncryptor;
      }

      public byte[] getDataPageHeaderAAD() {
         return this.dataPageHeaderAAD;
      }

      public byte[] getDataPageAAD() {
         return this.dataPageAAD;
      }

      public byte[] getDictPageHeaderAAD() {
         return this.dictPageHeaderAAD;
      }

      public byte[] getDictPageAAD() {
         return this.dictPageAAD;
      }
   }
}
