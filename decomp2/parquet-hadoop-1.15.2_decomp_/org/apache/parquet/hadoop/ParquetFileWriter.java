package org.apache.parquet.hadoop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.zip.CRC32;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.ParquetSizeOverflowException;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.ByteBufferReleaser;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.bytes.HeapByteBufferAllocator;
import org.apache.parquet.bytes.ReusingByteBufferAllocator;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.EncodingStats;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.crypto.AesCipher;
import org.apache.parquet.crypto.ColumnEncryptionProperties;
import org.apache.parquet.crypto.FileEncryptionProperties;
import org.apache.parquet.crypto.InternalColumnEncryptionSetup;
import org.apache.parquet.crypto.InternalFileEncryptor;
import org.apache.parquet.crypto.ModuleCipherFactory;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import org.apache.parquet.format.BlockCipher;
import org.apache.parquet.format.Util;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.metadata.FileMetaData;
import org.apache.parquet.hadoop.metadata.GlobalMetaData;
import org.apache.parquet.hadoop.metadata.KeyValueMetadataMergeStrategy;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.hadoop.metadata.StrictKeyValueMetadataMergeStrategy;
import org.apache.parquet.hadoop.util.HadoopOutputFile;
import org.apache.parquet.hadoop.util.HadoopStreams;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.ColumnIndexBuilder;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndexBuilder;
import org.apache.parquet.internal.hadoop.metadata.IndexReference;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.OutputFile;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.io.PositionOutputStream;
import org.apache.parquet.io.SeekableInputStream;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.TypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParquetFileWriter implements AutoCloseable {
   private static final Logger LOG = LoggerFactory.getLogger(ParquetFileWriter.class);
   private final ParquetMetadataConverter metadataConverter;
   public static final String PARQUET_METADATA_FILE = "_metadata";
   public static final String MAGIC_STR = "PAR1";
   public static final byte[] MAGIC;
   public static final String EF_MAGIC_STR = "PARE";
   public static final byte[] EFMAGIC;
   public static final String PARQUET_COMMON_METADATA_FILE = "_common_metadata";
   public static final int CURRENT_VERSION = 1;
   protected final PositionOutputStream out;
   private final MessageType schema;
   private final AlignmentStrategy alignment;
   private final int columnIndexTruncateLength;
   private final List blocks;
   private final List columnIndexes;
   private final List offsetIndexes;
   private final List bloomFilters;
   private final InternalFileEncryptor fileEncryptor;
   private BlockMetaData currentBlock;
   private List currentColumnIndexes;
   private List currentOffsetIndexes;
   private Map currentBloomFilters;
   private long currentRecordCount;
   private final EncodingStats.Builder encodingStatsBuilder;
   private Set currentEncodings;
   private long uncompressedLength;
   private long compressedLength;
   private Statistics currentStatistics;
   private SizeStatistics currentSizeStatistics;
   private ColumnIndexBuilder columnIndexBuilder;
   private OffsetIndexBuilder offsetIndexBuilder;
   private CompressionCodecName currentChunkCodec;
   private ColumnPath currentChunkPath;
   private PrimitiveType currentChunkType;
   private long currentChunkValueCount;
   private long currentChunkFirstDataPage;
   private long currentChunkDictionaryPageOffset;
   private ParquetMetadata footer;
   private boolean closed;
   private final CRC32 crc;
   private final ReusingByteBufferAllocator crcAllocator;
   private final boolean pageWriteChecksumEnabled;
   private STATE state;
   private static final ThreadLocal COPY_BUFFER;

   /** @deprecated */
   @Deprecated
   public ParquetFileWriter(Configuration configuration, MessageType schema, Path file) throws IOException {
      this(HadoopOutputFile.fromPath(file, configuration), schema, ParquetFileWriter.Mode.CREATE, 134217728L, 8388608);
   }

   /** @deprecated */
   @Deprecated
   public ParquetFileWriter(Configuration configuration, MessageType schema, Path file, Mode mode) throws IOException {
      this(HadoopOutputFile.fromPath(file, configuration), schema, mode, 134217728L, 8388608);
   }

   /** @deprecated */
   @Deprecated
   public ParquetFileWriter(Configuration configuration, MessageType schema, Path file, Mode mode, long rowGroupSize, int maxPaddingSize) throws IOException {
      this(HadoopOutputFile.fromPath(file, configuration), schema, mode, rowGroupSize, maxPaddingSize);
   }

   /** @deprecated */
   @Deprecated
   public ParquetFileWriter(OutputFile file, MessageType schema, Mode mode, long rowGroupSize, int maxPaddingSize) throws IOException {
      this(file, schema, mode, rowGroupSize, maxPaddingSize, 64, Integer.MAX_VALUE, true);
   }

   public ParquetFileWriter(OutputFile file, MessageType schema, Mode mode, long rowGroupSize, int maxPaddingSize, int columnIndexTruncateLength, int statisticsTruncateLength, boolean pageWriteChecksumEnabled) throws IOException {
      this(file, schema, mode, rowGroupSize, maxPaddingSize, columnIndexTruncateLength, statisticsTruncateLength, pageWriteChecksumEnabled, (FileEncryptionProperties)null, (InternalFileEncryptor)null, (ByteBufferAllocator)null);
   }

   public ParquetFileWriter(OutputFile file, MessageType schema, Mode mode, long rowGroupSize, int maxPaddingSize, int columnIndexTruncateLength, int statisticsTruncateLength, boolean pageWriteChecksumEnabled, FileEncryptionProperties encryptionProperties) throws IOException {
      this(file, schema, mode, rowGroupSize, maxPaddingSize, columnIndexTruncateLength, statisticsTruncateLength, pageWriteChecksumEnabled, encryptionProperties, (InternalFileEncryptor)null, (ByteBufferAllocator)null);
   }

   public ParquetFileWriter(OutputFile file, MessageType schema, Mode mode, long rowGroupSize, int maxPaddingSize, FileEncryptionProperties encryptionProperties, ParquetProperties props) throws IOException {
      this(file, schema, mode, rowGroupSize, maxPaddingSize, props.getColumnIndexTruncateLength(), props.getStatisticsTruncateLength(), props.getPageWriteChecksumEnabled(), encryptionProperties, (InternalFileEncryptor)null, props.getAllocator());
   }

   /** @deprecated */
   @Deprecated
   public ParquetFileWriter(OutputFile file, MessageType schema, Mode mode, long rowGroupSize, int maxPaddingSize, int columnIndexTruncateLength, int statisticsTruncateLength, boolean pageWriteChecksumEnabled, InternalFileEncryptor encryptor) throws IOException {
      this(file, schema, mode, rowGroupSize, maxPaddingSize, columnIndexTruncateLength, statisticsTruncateLength, pageWriteChecksumEnabled, (FileEncryptionProperties)null, encryptor, (ByteBufferAllocator)null);
   }

   private ParquetFileWriter(OutputFile file, MessageType schema, Mode mode, long rowGroupSize, int maxPaddingSize, int columnIndexTruncateLength, int statisticsTruncateLength, boolean pageWriteChecksumEnabled, FileEncryptionProperties encryptionProperties, InternalFileEncryptor encryptor, ByteBufferAllocator allocator) throws IOException {
      this.blocks = new ArrayList();
      this.columnIndexes = new ArrayList();
      this.offsetIndexes = new ArrayList();
      this.bloomFilters = new ArrayList();
      this.footer = null;
      this.state = ParquetFileWriter.STATE.NOT_STARTED;
      TypeUtil.checkValidWriteSchema(schema);
      this.schema = schema;
      long blockSize = rowGroupSize;
      if (file.supportsBlockSize()) {
         blockSize = Math.max(file.defaultBlockSize(), rowGroupSize);
         this.alignment = ParquetFileWriter.PaddingAlignment.get(blockSize, rowGroupSize, maxPaddingSize);
      } else {
         this.alignment = ParquetFileWriter.NoAlignment.get(rowGroupSize);
      }

      if (mode == ParquetFileWriter.Mode.OVERWRITE) {
         this.out = file.createOrOverwrite(blockSize);
      } else {
         this.out = file.create(blockSize);
      }

      this.encodingStatsBuilder = new EncodingStats.Builder();
      this.columnIndexTruncateLength = columnIndexTruncateLength;
      this.pageWriteChecksumEnabled = pageWriteChecksumEnabled;
      this.crc = pageWriteChecksumEnabled ? new CRC32() : null;
      this.crcAllocator = pageWriteChecksumEnabled ? ReusingByteBufferAllocator.strict((ByteBufferAllocator)(allocator == null ? new HeapByteBufferAllocator() : allocator)) : null;
      this.metadataConverter = new ParquetMetadataConverter(statisticsTruncateLength);
      if (null == encryptionProperties && null == encryptor) {
         this.fileEncryptor = null;
      } else {
         if (null == encryptionProperties) {
            encryptionProperties = encryptor.getEncryptionProperties();
         }

         Map<ColumnPath, ColumnEncryptionProperties> columnEncryptionProperties = encryptionProperties.getEncryptedColumns();
         if (null != columnEncryptionProperties) {
            for(Map.Entry entry : columnEncryptionProperties.entrySet()) {
               String[] path = ((ColumnPath)entry.getKey()).toArray();
               if (!schema.containsPath(path)) {
                  StringBuilder columnList = new StringBuilder();
                  columnList.append("[");

                  for(String[] columnPath : schema.getPaths()) {
                     columnList.append(ColumnPath.get(columnPath).toDotString()).append("], [");
                  }

                  throw new ParquetCryptoRuntimeException("Encrypted column [" + ((ColumnPath)entry.getKey()).toDotString() + "] not in file schema column list: " + columnList.substring(0, columnList.length() - 3));
               }
            }
         }

         if (null == encryptor) {
            this.fileEncryptor = new InternalFileEncryptor(encryptionProperties);
         } else {
            this.fileEncryptor = encryptor;
         }

      }
   }

   ParquetFileWriter(Configuration configuration, MessageType schema, Path file, long rowAndBlockSize, int maxPaddingSize, int columnIndexTruncateLength, ByteBufferAllocator allocator) throws IOException {
      this.blocks = new ArrayList();
      this.columnIndexes = new ArrayList();
      this.offsetIndexes = new ArrayList();
      this.bloomFilters = new ArrayList();
      this.footer = null;
      this.state = ParquetFileWriter.STATE.NOT_STARTED;
      FileSystem fs = file.getFileSystem(configuration);
      this.schema = schema;
      this.alignment = ParquetFileWriter.PaddingAlignment.get(rowAndBlockSize, rowAndBlockSize, maxPaddingSize);
      this.out = HadoopStreams.wrap(fs.create(file, true, 8192, fs.getDefaultReplication(file), rowAndBlockSize));
      this.encodingStatsBuilder = new EncodingStats.Builder();
      this.columnIndexTruncateLength = columnIndexTruncateLength;
      this.pageWriteChecksumEnabled = ParquetOutputFormat.getPageWriteChecksumEnabled(configuration);
      this.crc = this.pageWriteChecksumEnabled ? new CRC32() : null;
      this.crcAllocator = this.pageWriteChecksumEnabled ? ReusingByteBufferAllocator.strict((ByteBufferAllocator)(allocator == null ? new HeapByteBufferAllocator() : allocator)) : null;
      this.metadataConverter = new ParquetMetadataConverter(Integer.MAX_VALUE);
      this.fileEncryptor = null;
   }

   public void start() throws IOException {
      this.state = this.state.start();
      LOG.debug("{}: start", this.out.getPos());
      byte[] magic = MAGIC;
      if (null != this.fileEncryptor && this.fileEncryptor.isFooterEncrypted()) {
         magic = EFMAGIC;
      }

      this.out.write(magic);
   }

   public InternalFileEncryptor getEncryptor() {
      return this.fileEncryptor;
   }

   public void startBlock(long recordCount) throws IOException {
      this.state = this.state.startBlock();
      LOG.debug("{}: start block", this.out.getPos());
      this.alignment.alignForRowGroup(this.out);
      this.currentBlock = new BlockMetaData();
      this.currentRecordCount = recordCount;
      this.currentColumnIndexes = new ArrayList();
      this.currentOffsetIndexes = new ArrayList();
      this.currentBloomFilters = new HashMap();
   }

   public void startColumn(ColumnDescriptor descriptor, long valueCount, CompressionCodecName compressionCodecName) throws IOException {
      this.state = this.state.startColumn();
      this.encodingStatsBuilder.clear();
      this.currentEncodings = new HashSet();
      this.currentChunkPath = ColumnPath.get(descriptor.getPath());
      this.currentChunkType = descriptor.getPrimitiveType();
      this.currentChunkCodec = compressionCodecName;
      this.currentChunkValueCount = valueCount;
      this.currentChunkFirstDataPage = -1L;
      this.compressedLength = 0L;
      this.uncompressedLength = 0L;
      this.currentStatistics = null;
      this.currentSizeStatistics = SizeStatistics.newBuilder(descriptor.getPrimitiveType(), descriptor.getMaxRepetitionLevel(), descriptor.getMaxDefinitionLevel()).build();
      this.columnIndexBuilder = ColumnIndexBuilder.getBuilder(this.currentChunkType, this.columnIndexTruncateLength);
      this.offsetIndexBuilder = OffsetIndexBuilder.getBuilder();
   }

   public void writeDictionaryPage(DictionaryPage dictionaryPage) throws IOException {
      this.writeDictionaryPage(dictionaryPage, (BlockCipher.Encryptor)null, (byte[])null);
   }

   public void writeDictionaryPage(DictionaryPage dictionaryPage, BlockCipher.Encryptor headerBlockEncryptor, byte[] AAD) throws IOException {
      this.state = this.state.write();
      LOG.debug("{}: write dictionary page: {} values", this.out.getPos(), dictionaryPage.getDictionarySize());
      this.currentChunkDictionaryPageOffset = this.out.getPos();
      int uncompressedSize = dictionaryPage.getUncompressedSize();
      int compressedPageSize = Math.toIntExact(dictionaryPage.getBytes().size());
      if (this.pageWriteChecksumEnabled) {
         this.crc.reset();
         this.crcUpdate(dictionaryPage.getBytes());
         this.metadataConverter.writeDictionaryPageHeader(uncompressedSize, compressedPageSize, dictionaryPage.getDictionarySize(), dictionaryPage.getEncoding(), (int)this.crc.getValue(), this.out, headerBlockEncryptor, AAD);
      } else {
         this.metadataConverter.writeDictionaryPageHeader(uncompressedSize, compressedPageSize, dictionaryPage.getDictionarySize(), dictionaryPage.getEncoding(), this.out, headerBlockEncryptor, AAD);
      }

      long headerSize = this.out.getPos() - this.currentChunkDictionaryPageOffset;
      this.uncompressedLength += (long)uncompressedSize + headerSize;
      this.compressedLength += (long)compressedPageSize + headerSize;
      LOG.debug("{}: write dictionary page content {}", this.out.getPos(), compressedPageSize);
      dictionaryPage.getBytes().writeAllTo(this.out);
      this.encodingStatsBuilder.addDictEncoding(dictionaryPage.getEncoding());
      this.currentEncodings.add(dictionaryPage.getEncoding());
   }

   /** @deprecated */
   @Deprecated
   public void writeDataPage(int valueCount, int uncompressedPageSize, BytesInput bytes, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) throws IOException {
      this.state = this.state.write();
      this.offsetIndexBuilder = OffsetIndexBuilder.getNoOpBuilder();
      this.columnIndexBuilder = ColumnIndexBuilder.getNoOpBuilder();
      long beforeHeader = this.out.getPos();
      LOG.debug("{}: write data page: {} values", beforeHeader, valueCount);
      int compressedPageSize = toIntWithCheck(bytes.size(), "page");
      this.metadataConverter.writeDataPageV1Header(uncompressedPageSize, compressedPageSize, valueCount, rlEncoding, dlEncoding, valuesEncoding, this.out);
      long headerSize = this.out.getPos() - beforeHeader;
      this.uncompressedLength += (long)uncompressedPageSize + headerSize;
      this.compressedLength += (long)compressedPageSize + headerSize;
      LOG.debug("{}: write data page content {}", this.out.getPos(), compressedPageSize);
      bytes.writeAllTo(this.out);
      this.encodingStatsBuilder.addDataEncoding(valuesEncoding);
      this.currentEncodings.add(rlEncoding);
      this.currentEncodings.add(dlEncoding);
      this.currentEncodings.add(valuesEncoding);
      if (this.currentChunkFirstDataPage < 0L) {
         this.currentChunkFirstDataPage = beforeHeader;
      }

   }

   /** @deprecated */
   @Deprecated
   public void writeDataPage(int valueCount, int uncompressedPageSize, BytesInput bytes, Statistics statistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) throws IOException {
      this.offsetIndexBuilder = OffsetIndexBuilder.getNoOpBuilder();
      this.columnIndexBuilder = ColumnIndexBuilder.getNoOpBuilder();
      this.innerWriteDataPage(valueCount, uncompressedPageSize, bytes, statistics, rlEncoding, dlEncoding, valuesEncoding, (BlockCipher.Encryptor)null, (byte[])null, (SizeStatistics)null);
   }

   public void writeDataPage(int valueCount, int uncompressedPageSize, BytesInput bytes, Statistics statistics, long rowCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) throws IOException {
      this.writeDataPage(valueCount, uncompressedPageSize, bytes, statistics, rowCount, rlEncoding, dlEncoding, valuesEncoding, (BlockCipher.Encryptor)null, (byte[])null, (SizeStatistics)null);
   }

   public void writeDataPage(int valueCount, int uncompressedPageSize, BytesInput bytes, Statistics statistics, long rowCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, BlockCipher.Encryptor metadataBlockEncryptor, byte[] pageHeaderAAD) throws IOException {
      this.writeDataPage(valueCount, uncompressedPageSize, bytes, statistics, rowCount, rlEncoding, dlEncoding, valuesEncoding, metadataBlockEncryptor, pageHeaderAAD, (SizeStatistics)null);
   }

   public void writeDataPage(int valueCount, int uncompressedPageSize, BytesInput bytes, Statistics statistics, long rowCount, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, BlockCipher.Encryptor metadataBlockEncryptor, byte[] pageHeaderAAD, SizeStatistics sizeStatistics) throws IOException {
      long beforeHeader = this.out.getPos();
      this.innerWriteDataPage(valueCount, uncompressedPageSize, bytes, statistics, rlEncoding, dlEncoding, valuesEncoding, metadataBlockEncryptor, pageHeaderAAD, sizeStatistics);
      this.offsetIndexBuilder.add(toIntWithCheck(this.out.getPos() - beforeHeader, "page"), rowCount, sizeStatistics != null ? sizeStatistics.getUnencodedByteArrayDataBytes() : Optional.empty());
   }

   private void innerWriteDataPage(int valueCount, int uncompressedPageSize, BytesInput bytes, Statistics statistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, BlockCipher.Encryptor metadataBlockEncryptor, byte[] pageHeaderAAD, SizeStatistics sizeStatistics) throws IOException {
      this.writeDataPage(valueCount, uncompressedPageSize, bytes, statistics, rlEncoding, dlEncoding, valuesEncoding, metadataBlockEncryptor, pageHeaderAAD, sizeStatistics);
   }

   public void writeDataPage(int valueCount, int uncompressedPageSize, BytesInput bytes, Statistics statistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, BlockCipher.Encryptor metadataBlockEncryptor, byte[] pageHeaderAAD) throws IOException {
      this.writeDataPage(valueCount, uncompressedPageSize, bytes, statistics, rlEncoding, dlEncoding, valuesEncoding, metadataBlockEncryptor, pageHeaderAAD, (SizeStatistics)null);
   }

   public void writeDataPage(int valueCount, int uncompressedPageSize, BytesInput bytes, Statistics statistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding, BlockCipher.Encryptor metadataBlockEncryptor, byte[] pageHeaderAAD, SizeStatistics sizeStatistics) throws IOException {
      this.state = this.state.write();
      long beforeHeader = this.out.getPos();
      if (this.currentChunkFirstDataPage < 0L) {
         this.currentChunkFirstDataPage = beforeHeader;
      }

      LOG.debug("{}: write data page: {} values", beforeHeader, valueCount);
      int compressedPageSize = toIntWithCheck(bytes.size(), "page");
      if (this.pageWriteChecksumEnabled) {
         this.crc.reset();
         this.crcUpdate(bytes);
         this.metadataConverter.writeDataPageV1Header(uncompressedPageSize, compressedPageSize, valueCount, rlEncoding, dlEncoding, valuesEncoding, (int)this.crc.getValue(), this.out, metadataBlockEncryptor, pageHeaderAAD);
      } else {
         this.metadataConverter.writeDataPageV1Header(uncompressedPageSize, compressedPageSize, valueCount, rlEncoding, dlEncoding, valuesEncoding, this.out, metadataBlockEncryptor, pageHeaderAAD);
      }

      long headerSize = this.out.getPos() - beforeHeader;
      this.uncompressedLength += (long)uncompressedPageSize + headerSize;
      this.compressedLength += (long)compressedPageSize + headerSize;
      LOG.debug("{}: write data page content {}", this.out.getPos(), compressedPageSize);
      bytes.writeAllTo(this.out);
      this.mergeColumnStatistics(statistics, sizeStatistics);
      this.encodingStatsBuilder.addDataEncoding(valuesEncoding);
      this.currentEncodings.add(rlEncoding);
      this.currentEncodings.add(dlEncoding);
      this.currentEncodings.add(valuesEncoding);
   }

   public void addBloomFilter(String column, BloomFilter bloomFilter) {
      this.currentBloomFilters.put(column, bloomFilter);
   }

   public void writeDataPageV2(int rowCount, int nullCount, int valueCount, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput compressedData, int uncompressedDataSize, Statistics statistics) throws IOException {
      this.writeDataPageV2(rowCount, nullCount, valueCount, repetitionLevels, definitionLevels, dataEncoding, compressedData, uncompressedDataSize, statistics, (BlockCipher.Encryptor)null, (byte[])null, (SizeStatistics)null);
   }

   public void writeDataPageV2(int rowCount, int nullCount, int valueCount, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput compressedData, int uncompressedDataSize, Statistics statistics, BlockCipher.Encryptor metadataBlockEncryptor, byte[] pageHeaderAAD) throws IOException {
      this.writeDataPageV2(rowCount, nullCount, valueCount, repetitionLevels, definitionLevels, dataEncoding, compressedData, uncompressedDataSize, statistics, metadataBlockEncryptor, pageHeaderAAD, (SizeStatistics)null);
   }

   public void writeDataPageV2(int rowCount, int nullCount, int valueCount, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput compressedData, int uncompressedDataSize, Statistics statistics, BlockCipher.Encryptor metadataBlockEncryptor, byte[] pageHeaderAAD, SizeStatistics sizeStatistics) throws IOException {
      this.state = this.state.write();
      int rlByteLength = toIntWithCheck(repetitionLevels.size(), "page repetition levels");
      int dlByteLength = toIntWithCheck(definitionLevels.size(), "page definition levels");
      int compressedSize = toIntWithCheck(compressedData.size() + repetitionLevels.size() + definitionLevels.size(), "page");
      int uncompressedSize = toIntWithCheck((long)uncompressedDataSize + repetitionLevels.size() + definitionLevels.size(), "page");
      long beforeHeader = this.out.getPos();
      if (this.currentChunkFirstDataPage < 0L) {
         this.currentChunkFirstDataPage = beforeHeader;
      }

      if (this.pageWriteChecksumEnabled) {
         this.crc.reset();
         if (repetitionLevels.size() > 0L) {
            this.crcUpdate(repetitionLevels);
         }

         if (definitionLevels.size() > 0L) {
            this.crcUpdate(definitionLevels);
         }

         if (compressedData.size() > 0L) {
            this.crcUpdate(compressedData);
         }

         this.metadataConverter.writeDataPageV2Header(uncompressedSize, compressedSize, valueCount, nullCount, rowCount, dataEncoding, rlByteLength, dlByteLength, (int)this.crc.getValue(), this.out, metadataBlockEncryptor, pageHeaderAAD);
      } else {
         this.metadataConverter.writeDataPageV2Header(uncompressedSize, compressedSize, valueCount, nullCount, rowCount, dataEncoding, rlByteLength, dlByteLength, this.out, metadataBlockEncryptor, pageHeaderAAD);
      }

      long headersSize = this.out.getPos() - beforeHeader;
      this.uncompressedLength += (long)uncompressedSize + headersSize;
      this.compressedLength += (long)compressedSize + headersSize;
      this.mergeColumnStatistics(statistics, sizeStatistics);
      this.currentEncodings.add(dataEncoding);
      this.encodingStatsBuilder.addDataEncoding(dataEncoding);
      BytesInput.concat(new BytesInput[]{repetitionLevels, definitionLevels, compressedData}).writeAllTo(this.out);
      this.offsetIndexBuilder.add(toIntWithCheck(this.out.getPos() - beforeHeader, "page"), (long)rowCount, sizeStatistics != null ? sizeStatistics.getUnencodedByteArrayDataBytes() : Optional.empty());
   }

   private void crcUpdate(BytesInput bytes) {
      ByteBufferReleaser releaser = this.crcAllocator.getReleaser();
      Throwable var3 = null;

      try {
         this.crc.update(bytes.toByteBuffer(releaser));
      } catch (Throwable var12) {
         var3 = var12;
         throw var12;
      } finally {
         if (releaser != null) {
            if (var3 != null) {
               try {
                  releaser.close();
               } catch (Throwable var11) {
                  var3.addSuppressed(var11);
               }
            } else {
               releaser.close();
            }
         }

      }

   }

   void writeColumnChunk(ColumnDescriptor descriptor, long valueCount, CompressionCodecName compressionCodecName, DictionaryPage dictionaryPage, BytesInput bytes, long uncompressedTotalPageSize, long compressedTotalPageSize, Statistics totalStats, SizeStatistics totalSizeStats, ColumnIndexBuilder columnIndexBuilder, OffsetIndexBuilder offsetIndexBuilder, BloomFilter bloomFilter, Set rlEncodings, Set dlEncodings, List dataEncodings) throws IOException {
      this.writeColumnChunk(descriptor, valueCount, compressionCodecName, dictionaryPage, bytes, uncompressedTotalPageSize, compressedTotalPageSize, totalStats, totalSizeStats, columnIndexBuilder, offsetIndexBuilder, bloomFilter, rlEncodings, dlEncodings, dataEncodings, (BlockCipher.Encryptor)null, 0, 0, (byte[])null);
   }

   void writeColumnChunk(ColumnDescriptor descriptor, long valueCount, CompressionCodecName compressionCodecName, DictionaryPage dictionaryPage, BytesInput bytes, long uncompressedTotalPageSize, long compressedTotalPageSize, Statistics totalStats, SizeStatistics totalSizeStats, ColumnIndexBuilder columnIndexBuilder, OffsetIndexBuilder offsetIndexBuilder, BloomFilter bloomFilter, Set rlEncodings, Set dlEncodings, List dataEncodings, BlockCipher.Encryptor headerBlockEncryptor, int rowGroupOrdinal, int columnOrdinal, byte[] fileAAD) throws IOException {
      this.startColumn(descriptor, valueCount, compressionCodecName);
      this.state = this.state.write();
      if (dictionaryPage != null) {
         byte[] dictonaryPageHeaderAAD = null;
         if (null != headerBlockEncryptor) {
            dictonaryPageHeaderAAD = AesCipher.createModuleAAD(fileAAD, ModuleCipherFactory.ModuleType.DictionaryPageHeader, rowGroupOrdinal, columnOrdinal, -1);
         }

         this.writeDictionaryPage(dictionaryPage, headerBlockEncryptor, dictonaryPageHeaderAAD);
      }

      if (bloomFilter != null) {
         boolean isWriteBloomFilter = false;

         for(Encoding encoding : dataEncodings) {
            if (encoding != Encoding.PLAIN_DICTIONARY && encoding != Encoding.RLE_DICTIONARY) {
               isWriteBloomFilter = true;
               break;
            }
         }

         if (isWriteBloomFilter) {
            this.currentBloomFilters.put(String.join(".", descriptor.getPath()), bloomFilter);
         } else {
            LOG.info("No need to write bloom filter because column {} data pages are all encoded as dictionary.", descriptor.getPath());
         }
      }

      LOG.debug("{}: write data pages", this.out.getPos());
      long headersSize = bytes.size() - compressedTotalPageSize;
      this.uncompressedLength += uncompressedTotalPageSize + headersSize;
      this.compressedLength += compressedTotalPageSize + headersSize;
      LOG.debug("{}: write data pages content", this.out.getPos());
      this.currentChunkFirstDataPage = this.out.getPos();
      bytes.writeAllTo(this.out);
      this.encodingStatsBuilder.addDataEncodings(dataEncodings);
      if (rlEncodings.isEmpty()) {
         this.encodingStatsBuilder.withV2Pages();
      }

      this.currentEncodings.addAll(rlEncodings);
      this.currentEncodings.addAll(dlEncodings);
      this.currentEncodings.addAll(dataEncodings);
      this.currentStatistics = totalStats;
      this.currentSizeStatistics = totalSizeStats;
      this.columnIndexBuilder = columnIndexBuilder;
      this.offsetIndexBuilder = offsetIndexBuilder;
      this.endColumn();
   }

   public void invalidateStatistics(Statistics totalStatistics) {
      Preconditions.checkArgument(totalStatistics != null, "Column total statistics can not be null");
      this.currentStatistics = totalStatistics;
      this.columnIndexBuilder = ColumnIndexBuilder.getNoOpBuilder();
   }

   public void endColumn() throws IOException {
      this.state = this.state.endColumn();
      LOG.debug("{}: end column", this.out.getPos());
      if (this.columnIndexBuilder.getMinMaxSize() > (long)this.columnIndexBuilder.getPageCount() * 4096L) {
         this.currentColumnIndexes.add((Object)null);
      } else {
         this.currentColumnIndexes.add(this.columnIndexBuilder.build());
      }

      this.currentOffsetIndexes.add(this.offsetIndexBuilder.build(this.currentChunkFirstDataPage));
      this.currentBlock.addColumn(ColumnChunkMetaData.get(this.currentChunkPath, this.currentChunkType, this.currentChunkCodec, this.encodingStatsBuilder.build(), this.currentEncodings, this.currentStatistics, this.currentChunkFirstDataPage, this.currentChunkDictionaryPageOffset, this.currentChunkValueCount, this.compressedLength, this.uncompressedLength, this.currentSizeStatistics));
      this.currentBlock.setTotalByteSize(this.currentBlock.getTotalByteSize() + this.uncompressedLength);
      this.uncompressedLength = 0L;
      this.compressedLength = 0L;
      this.currentChunkDictionaryPageOffset = 0L;
      this.columnIndexBuilder = null;
      this.offsetIndexBuilder = null;
   }

   public void endBlock() throws IOException {
      if (this.currentRecordCount == 0L) {
         throw new ParquetEncodingException("End block with zero record");
      } else {
         this.state = this.state.endBlock();
         LOG.debug("{}: end block", this.out.getPos());
         this.currentBlock.setRowCount(this.currentRecordCount);
         this.currentBlock.setOrdinal(this.blocks.size());
         this.blocks.add(this.currentBlock);
         this.columnIndexes.add(this.currentColumnIndexes);
         this.offsetIndexes.add(this.currentOffsetIndexes);
         this.bloomFilters.add(this.currentBloomFilters);
         this.currentColumnIndexes = null;
         this.currentOffsetIndexes = null;
         this.currentBloomFilters = null;
         this.currentBlock = null;
      }
   }

   /** @deprecated */
   @Deprecated
   public void appendFile(Configuration conf, Path file) throws IOException {
      ParquetFileReader reader = ParquetFileReader.open(conf, file);
      Throwable var4 = null;

      try {
         reader.appendTo(this);
      } catch (Throwable var13) {
         var4 = var13;
         throw var13;
      } finally {
         if (reader != null) {
            if (var4 != null) {
               try {
                  reader.close();
               } catch (Throwable var12) {
                  var4.addSuppressed(var12);
               }
            } else {
               reader.close();
            }
         }

      }

   }

   public void appendFile(InputFile file) throws IOException {
      ParquetFileReader reader = ParquetFileReader.open(file);
      Throwable var3 = null;

      try {
         reader.appendTo(this);
      } catch (Throwable var12) {
         var3 = var12;
         throw var12;
      } finally {
         if (reader != null) {
            if (var3 != null) {
               try {
                  reader.close();
               } catch (Throwable var11) {
                  var3.addSuppressed(var11);
               }
            } else {
               reader.close();
            }
         }

      }

   }

   /** @deprecated */
   @Deprecated
   public void appendRowGroups(FSDataInputStream file, List rowGroups, boolean dropColumns) throws IOException {
      this.appendRowGroups(HadoopStreams.wrap(file), rowGroups, dropColumns);
   }

   public void appendRowGroups(SeekableInputStream file, List rowGroups, boolean dropColumns) throws IOException {
      for(BlockMetaData block : rowGroups) {
         this.appendRowGroup(file, block, dropColumns);
      }

   }

   /** @deprecated */
   @Deprecated
   public void appendRowGroup(FSDataInputStream from, BlockMetaData rowGroup, boolean dropColumns) throws IOException {
      this.appendRowGroup(HadoopStreams.wrap(from), rowGroup, dropColumns);
   }

   public void appendRowGroup(SeekableInputStream from, BlockMetaData rowGroup, boolean dropColumns) throws IOException {
      this.startBlock(rowGroup.getRowCount());
      Map<String, ColumnChunkMetaData> columnsToCopy = new HashMap();

      for(ColumnChunkMetaData chunk : rowGroup.getColumns()) {
         columnsToCopy.put(chunk.getPath().toDotString(), chunk);
      }

      List<ColumnChunkMetaData> columnsInOrder = new ArrayList();

      for(ColumnDescriptor descriptor : this.schema.getColumns()) {
         String path = ColumnPath.get(descriptor.getPath()).toDotString();
         ColumnChunkMetaData chunk = (ColumnChunkMetaData)columnsToCopy.remove(path);
         if (chunk == null) {
            throw new IllegalArgumentException(String.format("Missing column '%s', cannot copy row group: %s", path, rowGroup));
         }

         columnsInOrder.add(chunk);
      }

      if (!dropColumns && !columnsToCopy.isEmpty()) {
         throw new IllegalArgumentException(String.format("Columns cannot be copied (missing from target schema): %s", String.join(", ", columnsToCopy.keySet())));
      } else {
         long start = -1L;
         long length = 0L;
         long blockUncompressedSize = 0L;

         for(int i = 0; i < columnsInOrder.size(); ++i) {
            ColumnChunkMetaData chunk = (ColumnChunkMetaData)columnsInOrder.get(i);
            long newChunkStart = this.out.getPos() + length;
            if (start < 0L) {
               start = chunk.getStartingPos();
            }

            length += chunk.getTotalSize();
            if (i + 1 == columnsInOrder.size() || ((ColumnChunkMetaData)columnsInOrder.get(i + 1)).getStartingPos() != start + length) {
               copy(from, this.out, start, length);
               start = -1L;
               length = 0L;
            }

            this.currentColumnIndexes.add((Object)null);
            this.currentOffsetIndexes.add((Object)null);
            Offsets offsets = Offsets.getOffsets(from, chunk, newChunkStart);
            this.currentBlock.addColumn(ColumnChunkMetaData.get(chunk.getPath(), chunk.getPrimitiveType(), chunk.getCodec(), chunk.getEncodingStats(), chunk.getEncodings(), chunk.getStatistics(), offsets.firstDataPageOffset, offsets.dictionaryPageOffset, chunk.getValueCount(), chunk.getTotalSize(), chunk.getTotalUncompressedSize()));
            blockUncompressedSize += chunk.getTotalUncompressedSize();
         }

         this.currentBlock.setTotalByteSize(blockUncompressedSize);
         this.endBlock();
      }
   }

   public void appendColumnChunk(ColumnDescriptor descriptor, SeekableInputStream from, ColumnChunkMetaData chunk, BloomFilter bloomFilter, ColumnIndex columnIndex, OffsetIndex offsetIndex) throws IOException {
      long start = chunk.getStartingPos();
      long length = chunk.getTotalSize();
      long newChunkStart = this.out.getPos();
      if (offsetIndex != null && newChunkStart != start) {
         offsetIndex = OffsetIndexBuilder.getBuilder().fromOffsetIndex(offsetIndex).build(newChunkStart - start);
      }

      copy(from, this.out, start, length);
      this.currentBloomFilters.put(String.join(".", descriptor.getPath()), bloomFilter);
      this.currentColumnIndexes.add(columnIndex);
      this.currentOffsetIndexes.add(offsetIndex);
      Offsets offsets = Offsets.getOffsets(from, chunk, newChunkStart);
      this.currentBlock.addColumn(ColumnChunkMetaData.get(chunk.getPath(), chunk.getPrimitiveType(), chunk.getCodec(), chunk.getEncodingStats(), chunk.getEncodings(), chunk.getStatistics(), offsets.firstDataPageOffset, offsets.dictionaryPageOffset, chunk.getValueCount(), chunk.getTotalSize(), chunk.getTotalUncompressedSize()));
      this.currentBlock.setTotalByteSize(this.currentBlock.getTotalByteSize() + chunk.getTotalUncompressedSize());
   }

   private static void copy(SeekableInputStream from, PositionOutputStream to, long start, long length) throws IOException {
      LOG.debug("Copying {} bytes at {} to {}", new Object[]{length, start, to.getPos()});
      from.seek(start);
      long bytesCopied = 0L;

      int bytesRead;
      for(byte[] buffer = (byte[])COPY_BUFFER.get(); bytesCopied < length; bytesCopied += (long)bytesRead) {
         int bytesLeft = Math.toIntExact(length - bytesCopied);
         bytesRead = from.read(buffer, 0, Math.min(buffer.length, bytesLeft));
         if (bytesRead < 0) {
            throw new IllegalArgumentException("Unexpected end of input file at " + start + bytesCopied);
         }

         to.write(buffer, 0, bytesRead);
      }

   }

   public void end(Map extraMetaData) throws IOException {
      try {
         this.state = this.state.end();
         serializeColumnIndexes(this.columnIndexes, this.blocks, this.out, this.fileEncryptor);
         serializeOffsetIndexes(this.offsetIndexes, this.blocks, this.out, this.fileEncryptor);
         serializeBloomFilters(this.bloomFilters, this.blocks, this.out, this.fileEncryptor);
         LOG.debug("{}: end", this.out.getPos());
         this.footer = new ParquetMetadata(new FileMetaData(this.schema, extraMetaData, "parquet-mr version 1.15.2 (build 859eac165b08f927fa14590c33bc5f476405fb68)"), this.blocks);
         serializeFooter(this.footer, this.out, this.fileEncryptor, this.metadataConverter);
      } finally {
         this.close();
      }

   }

   public void close() throws IOException {
      if (!this.closed) {
         try {
            PositionOutputStream temp = this.out;
            Throwable var2 = null;

            try {
               temp.flush();
               if (this.crcAllocator != null) {
                  this.crcAllocator.close();
               }
            } catch (Throwable var18) {
               var2 = var18;
               throw var18;
            } finally {
               if (temp != null) {
                  if (var2 != null) {
                     try {
                        temp.close();
                     } catch (Throwable var17) {
                        var2.addSuppressed(var17);
                     }
                  } else {
                     temp.close();
                  }
               }

            }
         } finally {
            this.closed = true;
         }

      }
   }

   private static void serializeColumnIndexes(List columnIndexes, List blocks, PositionOutputStream out, InternalFileEncryptor fileEncryptor) throws IOException {
      LOG.debug("{}: column indexes", out.getPos());
      int bIndex = 0;

      for(int bSize = blocks.size(); bIndex < bSize; ++bIndex) {
         BlockMetaData block = (BlockMetaData)blocks.get(bIndex);
         List<ColumnChunkMetaData> columns = block.getColumns();
         List<ColumnIndex> blockColumnIndexes = (List)columnIndexes.get(bIndex);
         int cIndex = 0;

         for(int cSize = columns.size(); cIndex < cSize; ++cIndex) {
            ColumnChunkMetaData column = (ColumnChunkMetaData)columns.get(cIndex);
            org.apache.parquet.format.ColumnIndex columnIndex = ParquetMetadataConverter.toParquetColumnIndex(column.getPrimitiveType(), (ColumnIndex)blockColumnIndexes.get(cIndex));
            if (columnIndex != null) {
               BlockCipher.Encryptor columnIndexEncryptor = null;
               byte[] columnIndexAAD = null;
               if (null != fileEncryptor) {
                  InternalColumnEncryptionSetup columnEncryptionSetup = fileEncryptor.getColumnSetup(column.getPath(), false, cIndex);
                  if (columnEncryptionSetup.isEncrypted()) {
                     columnIndexEncryptor = columnEncryptionSetup.getMetaDataEncryptor();
                     columnIndexAAD = AesCipher.createModuleAAD(fileEncryptor.getFileAAD(), ModuleCipherFactory.ModuleType.ColumnIndex, block.getOrdinal(), columnEncryptionSetup.getOrdinal(), -1);
                  }
               }

               long offset = out.getPos();
               Util.writeColumnIndex(columnIndex, out, columnIndexEncryptor, columnIndexAAD);
               column.setColumnIndexReference(new IndexReference(offset, toIntWithCheck(out.getPos() - offset, "page")));
            }
         }
      }

   }

   private static int toIntWithCheck(long size, String obj) {
      if ((long)((int)size) != size) {
         throw new ParquetSizeOverflowException(String.format("Cannot write %s larger than %s bytes: %s", obj, Integer.MAX_VALUE, size));
      } else {
         return (int)size;
      }
   }

   private void mergeColumnStatistics(Statistics statistics, SizeStatistics sizeStatistics) {
      Preconditions.checkState(this.currentSizeStatistics != null, "Aggregate size statistics should not be null");
      this.currentSizeStatistics.mergeStatistics(sizeStatistics);
      if (!this.currentSizeStatistics.isValid()) {
         sizeStatistics = null;
      }

      if (this.currentStatistics == null || !this.currentStatistics.isEmpty()) {
         if (statistics != null && !statistics.isEmpty()) {
            if (this.currentStatistics == null) {
               this.currentStatistics = statistics.copy();
               this.columnIndexBuilder.add(statistics, sizeStatistics);
            } else {
               this.currentStatistics.mergeStatistics(statistics);
               this.columnIndexBuilder.add(statistics, sizeStatistics);
            }
         } else {
            this.currentStatistics = Statistics.getBuilderForReading(this.currentChunkType).build();
            this.columnIndexBuilder = ColumnIndexBuilder.getNoOpBuilder();
         }

      }
   }

   private static void serializeOffsetIndexes(List offsetIndexes, List blocks, PositionOutputStream out, InternalFileEncryptor fileEncryptor) throws IOException {
      LOG.debug("{}: offset indexes", out.getPos());
      int bIndex = 0;

      for(int bSize = blocks.size(); bIndex < bSize; ++bIndex) {
         BlockMetaData block = (BlockMetaData)blocks.get(bIndex);
         List<ColumnChunkMetaData> columns = block.getColumns();
         List<OffsetIndex> blockOffsetIndexes = (List)offsetIndexes.get(bIndex);
         int cIndex = 0;

         for(int cSize = columns.size(); cIndex < cSize; ++cIndex) {
            OffsetIndex offsetIndex = (OffsetIndex)blockOffsetIndexes.get(cIndex);
            if (offsetIndex != null) {
               ColumnChunkMetaData column = (ColumnChunkMetaData)columns.get(cIndex);
               BlockCipher.Encryptor offsetIndexEncryptor = null;
               byte[] offsetIndexAAD = null;
               if (null != fileEncryptor) {
                  InternalColumnEncryptionSetup columnEncryptionSetup = fileEncryptor.getColumnSetup(column.getPath(), false, cIndex);
                  if (columnEncryptionSetup.isEncrypted()) {
                     offsetIndexEncryptor = columnEncryptionSetup.getMetaDataEncryptor();
                     offsetIndexAAD = AesCipher.createModuleAAD(fileEncryptor.getFileAAD(), ModuleCipherFactory.ModuleType.OffsetIndex, block.getOrdinal(), columnEncryptionSetup.getOrdinal(), -1);
                  }
               }

               long offset = out.getPos();
               Util.writeOffsetIndex(ParquetMetadataConverter.toParquetOffsetIndex(offsetIndex), out, offsetIndexEncryptor, offsetIndexAAD);
               column.setOffsetIndexReference(new IndexReference(offset, toIntWithCheck(out.getPos() - offset, "page")));
            }
         }
      }

   }

   private static void serializeBloomFilters(List bloomFilters, List blocks, PositionOutputStream out, InternalFileEncryptor fileEncryptor) throws IOException {
      LOG.debug("{}: bloom filters", out.getPos());
      int bIndex = 0;

      for(int bSize = blocks.size(); bIndex < bSize; ++bIndex) {
         BlockMetaData block = (BlockMetaData)blocks.get(bIndex);
         List<ColumnChunkMetaData> columns = block.getColumns();
         Map<String, BloomFilter> blockBloomFilters = (Map)bloomFilters.get(bIndex);
         if (!blockBloomFilters.isEmpty()) {
            int cIndex = 0;

            for(int cSize = columns.size(); cIndex < cSize; ++cIndex) {
               ColumnChunkMetaData column = (ColumnChunkMetaData)columns.get(cIndex);
               BloomFilter bloomFilter = (BloomFilter)blockBloomFilters.get(column.getPath().toDotString());
               if (bloomFilter != null) {
                  long offset = out.getPos();
                  column.setBloomFilterOffset(offset);
                  BlockCipher.Encryptor bloomFilterEncryptor = null;
                  byte[] bloomFilterHeaderAAD = null;
                  byte[] bloomFilterBitsetAAD = null;
                  if (null != fileEncryptor) {
                     InternalColumnEncryptionSetup columnEncryptionSetup = fileEncryptor.getColumnSetup(column.getPath(), false, cIndex);
                     if (columnEncryptionSetup.isEncrypted()) {
                        bloomFilterEncryptor = columnEncryptionSetup.getMetaDataEncryptor();
                        int columnOrdinal = columnEncryptionSetup.getOrdinal();
                        bloomFilterHeaderAAD = AesCipher.createModuleAAD(fileEncryptor.getFileAAD(), ModuleCipherFactory.ModuleType.BloomFilterHeader, block.getOrdinal(), columnOrdinal, -1);
                        bloomFilterBitsetAAD = AesCipher.createModuleAAD(fileEncryptor.getFileAAD(), ModuleCipherFactory.ModuleType.BloomFilterBitset, block.getOrdinal(), columnOrdinal, -1);
                     }
                  }

                  Util.writeBloomFilterHeader(ParquetMetadataConverter.toBloomFilterHeader(bloomFilter), out, bloomFilterEncryptor, bloomFilterHeaderAAD);
                  ByteArrayOutputStream tempOutStream = new ByteArrayOutputStream();
                  bloomFilter.writeTo(tempOutStream);
                  byte[] serializedBitset = tempOutStream.toByteArray();
                  if (null != bloomFilterEncryptor) {
                     serializedBitset = bloomFilterEncryptor.encrypt(serializedBitset, bloomFilterBitsetAAD);
                  }

                  out.write(serializedBitset);
                  int length = Math.toIntExact(out.getPos() - offset);
                  column.setBloomFilterLength(length);
               }
            }
         }
      }

   }

   private static void serializeFooter(ParquetMetadata footer, PositionOutputStream out, InternalFileEncryptor fileEncryptor, ParquetMetadataConverter metadataConverter) throws IOException {
      if (null == fileEncryptor) {
         long footerIndex = out.getPos();
         org.apache.parquet.format.FileMetaData parquetMetadata = metadataConverter.toParquetMetadata(1, footer);
         Util.writeFileMetaData(parquetMetadata, out);
         LOG.debug("{}: footer length = {}", out.getPos(), out.getPos() - footerIndex);
         BytesUtils.writeIntLittleEndian(out, toIntWithCheck(out.getPos() - footerIndex, "footer"));
         out.write(MAGIC);
      } else {
         org.apache.parquet.format.FileMetaData parquetMetadata = metadataConverter.toParquetMetadata(1, footer, fileEncryptor);
         if (!fileEncryptor.isFooterEncrypted()) {
            long footerIndex = out.getPos();
            parquetMetadata.setEncryption_algorithm(fileEncryptor.getEncryptionAlgorithm());
            byte[] footerSigningKeyMetaData = fileEncryptor.getFooterSigningKeyMetaData();
            if (null != footerSigningKeyMetaData) {
               parquetMetadata.setFooter_signing_key_metadata(footerSigningKeyMetaData);
            }

            ByteArrayOutputStream tempOutStream = new ByteArrayOutputStream();
            Util.writeFileMetaData(parquetMetadata, tempOutStream);
            byte[] serializedFooter = tempOutStream.toByteArray();
            byte[] footerAAD = AesCipher.createFooterAAD(fileEncryptor.getFileAAD());
            byte[] encryptedFooter = fileEncryptor.getSignedFooterEncryptor().encrypt(serializedFooter, footerAAD);
            byte[] signature = new byte[28];
            System.arraycopy(encryptedFooter, 4, signature, 0, 12);
            System.arraycopy(encryptedFooter, encryptedFooter.length - 16, signature, 12, 16);
            out.write(serializedFooter);
            out.write(signature);
            LOG.debug("{}: footer and signature length = {}", out.getPos(), out.getPos() - footerIndex);
            BytesUtils.writeIntLittleEndian(out, toIntWithCheck(out.getPos() - footerIndex, "page"));
            out.write(MAGIC);
         } else {
            long cryptoFooterIndex = out.getPos();
            Util.writeFileCryptoMetaData(fileEncryptor.getFileCryptoMetaData(), out);
            byte[] footerAAD = AesCipher.createFooterAAD(fileEncryptor.getFileAAD());
            Util.writeFileMetaData(parquetMetadata, out, fileEncryptor.getFooterEncryptor(), footerAAD);
            int combinedMetaDataLength = toIntWithCheck(out.getPos() - cryptoFooterIndex, "page");
            LOG.debug("{}: crypto metadata and footer length = {}", out.getPos(), combinedMetaDataLength);
            BytesUtils.writeIntLittleEndian(out, combinedMetaDataLength);
            out.write(EFMAGIC);
         }
      }
   }

   public ParquetMetadata getFooter() {
      Preconditions.checkState(this.state == ParquetFileWriter.STATE.ENDED, "Cannot return unfinished footer.");
      return this.footer;
   }

   /** @deprecated */
   @Deprecated
   public static ParquetMetadata mergeMetadataFiles(List files, Configuration conf) throws IOException {
      return mergeMetadataFiles(files, conf, new StrictKeyValueMetadataMergeStrategy());
   }

   /** @deprecated */
   @Deprecated
   public static ParquetMetadata mergeMetadataFiles(List files, Configuration conf, KeyValueMetadataMergeStrategy keyValueMetadataMergeStrategy) throws IOException {
      Preconditions.checkArgument(!files.isEmpty(), "Cannot merge an empty list of metadata");
      GlobalMetaData globalMetaData = null;
      List<BlockMetaData> blocks = new ArrayList();

      for(Path p : files) {
         ParquetMetadata pmd = ParquetFileReader.readFooter(conf, p, ParquetMetadataConverter.NO_FILTER);
         FileMetaData fmd = pmd.getFileMetaData();
         globalMetaData = mergeInto(fmd, globalMetaData, true);
         blocks.addAll(pmd.getBlocks());
      }

      return new ParquetMetadata(globalMetaData.merge(keyValueMetadataMergeStrategy), blocks);
   }

   /** @deprecated */
   @Deprecated
   public static void writeMergedMetadataFile(List files, Path outputPath, Configuration conf) throws IOException {
      ParquetMetadata merged = mergeMetadataFiles(files, conf);
      writeMetadataFile(outputPath, merged, outputPath.getFileSystem(conf));
   }

   /** @deprecated */
   @Deprecated
   public static void writeMetadataFile(Configuration configuration, Path outputPath, List footers) throws IOException {
      writeMetadataFile(configuration, outputPath, footers, ParquetOutputFormat.JobSummaryLevel.ALL);
   }

   /** @deprecated */
   @Deprecated
   public static void writeMetadataFile(Configuration configuration, Path outputPath, List footers, ParquetOutputFormat.JobSummaryLevel level) throws IOException {
      Preconditions.checkArgument(level == ParquetOutputFormat.JobSummaryLevel.ALL || level == ParquetOutputFormat.JobSummaryLevel.COMMON_ONLY, "Unsupported level: %s", level);
      FileSystem fs = outputPath.getFileSystem(configuration);
      outputPath = outputPath.makeQualified(fs);
      ParquetMetadata metadataFooter = mergeFooters(outputPath, footers);
      if (level == ParquetOutputFormat.JobSummaryLevel.ALL) {
         writeMetadataFile(outputPath, metadataFooter, fs, "_metadata");
      }

      metadataFooter.getBlocks().clear();
      writeMetadataFile(outputPath, metadataFooter, fs, "_common_metadata");
   }

   /** @deprecated */
   @Deprecated
   private static void writeMetadataFile(Path outputPathRoot, ParquetMetadata metadataFooter, FileSystem fs, String parquetMetadataFile) throws IOException {
      Path metaDataPath = new Path(outputPathRoot, parquetMetadataFile);
      writeMetadataFile(metaDataPath, metadataFooter, fs);
   }

   /** @deprecated */
   @Deprecated
   private static void writeMetadataFile(Path outputPath, ParquetMetadata metadataFooter, FileSystem fs) throws IOException {
      PositionOutputStream metadata = HadoopStreams.wrap(fs.create(outputPath));
      Throwable var4 = null;

      try {
         metadata.write(MAGIC);
         serializeFooter(metadataFooter, metadata, (InternalFileEncryptor)null, new ParquetMetadataConverter());
         metadata.flush();
      } catch (Throwable var13) {
         var4 = var13;
         throw var13;
      } finally {
         if (metadata != null) {
            if (var4 != null) {
               try {
                  metadata.close();
               } catch (Throwable var12) {
                  var4.addSuppressed(var12);
               }
            } else {
               metadata.close();
            }
         }

      }

   }

   static ParquetMetadata mergeFooters(Path root, List footers) {
      return mergeFooters(root, footers, new StrictKeyValueMetadataMergeStrategy());
   }

   static ParquetMetadata mergeFooters(Path root, List footers, KeyValueMetadataMergeStrategy keyValueMergeStrategy) {
      String rootPath = root.toUri().getPath();
      GlobalMetaData fileMetaData = null;
      List<BlockMetaData> blocks = new ArrayList();

      for(Footer footer : footers) {
         String footerPath = footer.getFile().toUri().getPath();
         if (!footerPath.startsWith(rootPath)) {
            throw new ParquetEncodingException(footerPath + " invalid: all the files must be contained in the root " + root);
         }

         for(footerPath = footerPath.substring(rootPath.length()); footerPath.startsWith("/"); footerPath = footerPath.substring(1)) {
         }

         fileMetaData = mergeInto(footer.getParquetMetadata().getFileMetaData(), fileMetaData);

         for(BlockMetaData block : footer.getParquetMetadata().getBlocks()) {
            block.setPath(footerPath);
            blocks.add(block);
         }
      }

      return new ParquetMetadata(fileMetaData.merge(keyValueMergeStrategy), blocks);
   }

   public long getPos() throws IOException {
      return this.out.getPos();
   }

   public long getNextRowGroupSize() throws IOException {
      return this.alignment.nextRowGroupSize(this.out);
   }

   static GlobalMetaData getGlobalMetaData(List footers) {
      return getGlobalMetaData(footers, true);
   }

   static GlobalMetaData getGlobalMetaData(List footers, boolean strict) {
      GlobalMetaData fileMetaData = null;

      for(Footer footer : footers) {
         ParquetMetadata currentMetadata = footer.getParquetMetadata();
         fileMetaData = mergeInto(currentMetadata.getFileMetaData(), fileMetaData, strict);
      }

      return fileMetaData;
   }

   static GlobalMetaData mergeInto(FileMetaData toMerge, GlobalMetaData mergedMetadata) {
      return mergeInto(toMerge, mergedMetadata, true);
   }

   static GlobalMetaData mergeInto(FileMetaData toMerge, GlobalMetaData mergedMetadata, boolean strict) {
      MessageType schema = null;
      Map<String, Set<String>> newKeyValues = new HashMap();
      Set<String> createdBy = new HashSet();
      if (mergedMetadata != null) {
         schema = mergedMetadata.getSchema();
         newKeyValues.putAll(mergedMetadata.getKeyValueMetaData());
         createdBy.addAll(mergedMetadata.getCreatedBy());
      }

      if (schema == null && toMerge.getSchema() != null || schema != null && !schema.equals(toMerge.getSchema())) {
         schema = mergeInto(toMerge.getSchema(), schema, strict);
      }

      for(Map.Entry entry : toMerge.getKeyValueMetaData().entrySet()) {
         Set<String> values = (Set)newKeyValues.computeIfAbsent(entry.getKey(), (k) -> new LinkedHashSet());
         values.add(entry.getValue());
      }

      createdBy.add(toMerge.getCreatedBy());
      return new GlobalMetaData(schema, newKeyValues, createdBy);
   }

   static MessageType mergeInto(MessageType toMerge, MessageType mergedSchema) {
      return mergeInto(toMerge, mergedSchema, true);
   }

   static MessageType mergeInto(MessageType toMerge, MessageType mergedSchema, boolean strict) {
      return mergedSchema == null ? toMerge : mergedSchema.union(toMerge, strict);
   }

   static {
      MAGIC = "PAR1".getBytes(StandardCharsets.US_ASCII);
      EFMAGIC = "PARE".getBytes(StandardCharsets.US_ASCII);
      COPY_BUFFER = ThreadLocal.withInitial(() -> new byte[8192]);
   }

   public static enum Mode {
      CREATE,
      OVERWRITE;
   }

   private static enum STATE {
      NOT_STARTED {
         STATE start() {
            return STARTED;
         }
      },
      STARTED {
         STATE startBlock() {
            return BLOCK;
         }

         STATE end() {
            return ENDED;
         }
      },
      BLOCK {
         STATE startColumn() {
            return COLUMN;
         }

         STATE endBlock() {
            return STARTED;
         }
      },
      COLUMN {
         STATE endColumn() {
            return BLOCK;
         }

         STATE write() {
            return this;
         }
      },
      ENDED;

      private STATE() {
      }

      STATE start() throws IOException {
         return this.error();
      }

      STATE startBlock() throws IOException {
         return this.error();
      }

      STATE startColumn() throws IOException {
         return this.error();
      }

      STATE write() throws IOException {
         return this.error();
      }

      STATE endColumn() throws IOException {
         return this.error();
      }

      STATE endBlock() throws IOException {
         return this.error();
      }

      STATE end() throws IOException {
         return this.error();
      }

      private final STATE error() throws IOException {
         throw new IOException("The file being written is in an invalid state. Probably caused by an error thrown previously. Current state: " + this.name());
      }
   }

   private static class NoAlignment implements AlignmentStrategy {
      private final long rowGroupSize;

      public static NoAlignment get(long rowGroupSize) {
         return new NoAlignment(rowGroupSize);
      }

      private NoAlignment(long rowGroupSize) {
         this.rowGroupSize = rowGroupSize;
      }

      public void alignForRowGroup(PositionOutputStream out) {
      }

      public long nextRowGroupSize(PositionOutputStream out) {
         return this.rowGroupSize;
      }
   }

   private static class PaddingAlignment implements AlignmentStrategy {
      private static final byte[] zeros = new byte[4096];
      protected final long dfsBlockSize;
      protected final long rowGroupSize;
      protected final int maxPaddingSize;

      public static PaddingAlignment get(long dfsBlockSize, long rowGroupSize, int maxPaddingSize) {
         return new PaddingAlignment(dfsBlockSize, rowGroupSize, maxPaddingSize);
      }

      private PaddingAlignment(long dfsBlockSize, long rowGroupSize, int maxPaddingSize) {
         this.dfsBlockSize = dfsBlockSize;
         this.rowGroupSize = rowGroupSize;
         this.maxPaddingSize = maxPaddingSize;
      }

      public void alignForRowGroup(PositionOutputStream out) throws IOException {
         long remaining = this.dfsBlockSize - out.getPos() % this.dfsBlockSize;
         if (this.isPaddingNeeded(remaining)) {
            ParquetFileWriter.LOG.debug("Adding {} bytes of padding (row group size={}B, block size={}B)", new Object[]{remaining, this.rowGroupSize, this.dfsBlockSize});

            while(remaining > 0L) {
               out.write(zeros, 0, (int)Math.min((long)zeros.length, remaining));
               remaining -= (long)zeros.length;
            }
         }

      }

      public long nextRowGroupSize(PositionOutputStream out) throws IOException {
         if (this.maxPaddingSize <= 0) {
            return this.rowGroupSize;
         } else {
            long remaining = this.dfsBlockSize - out.getPos() % this.dfsBlockSize;
            return this.isPaddingNeeded(remaining) ? this.rowGroupSize : Math.min(remaining, this.rowGroupSize);
         }
      }

      protected boolean isPaddingNeeded(long remaining) {
         return remaining <= (long)this.maxPaddingSize;
      }
   }

   private interface AlignmentStrategy {
      void alignForRowGroup(PositionOutputStream var1) throws IOException;

      long nextRowGroupSize(PositionOutputStream var1) throws IOException;
   }
}
