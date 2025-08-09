package org.apache.parquet.hadoop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.zip.CRC32;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.ByteBufferReleaser;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.ConcatenatingByteBufferCollector;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.page.PageWriteStore;
import org.apache.parquet.column.page.PageWriter;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriteStore;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriter;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.crypto.AesCipher;
import org.apache.parquet.crypto.InternalColumnEncryptionSetup;
import org.apache.parquet.crypto.InternalFileEncryptor;
import org.apache.parquet.crypto.ModuleCipherFactory;
import org.apache.parquet.format.BlockCipher;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.internal.column.columnindex.ColumnIndexBuilder;
import org.apache.parquet.internal.column.columnindex.OffsetIndexBuilder;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.util.AutoCloseables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnChunkPageWriteStore implements PageWriteStore, BloomFilterWriteStore {
   private static final Logger LOG = LoggerFactory.getLogger(ColumnChunkPageWriteStore.class);
   private static ParquetMetadataConverter parquetMetadataConverter = new ParquetMetadataConverter();
   private final Map writers;
   private final MessageType schema;

   /** @deprecated */
   @Deprecated
   public ColumnChunkPageWriteStore(CodecFactory.BytesCompressor compressor, MessageType schema, ByteBufferAllocator allocator, int columnIndexTruncateLength) {
      this((CompressionCodecFactory.BytesInputCompressor)compressor, schema, allocator, columnIndexTruncateLength, true);
   }

   public ColumnChunkPageWriteStore(CompressionCodecFactory.BytesInputCompressor compressor, MessageType schema, ByteBufferAllocator allocator, int columnIndexTruncateLength) {
      this(compressor, schema, allocator, columnIndexTruncateLength, true);
   }

   /** @deprecated */
   @Deprecated
   public ColumnChunkPageWriteStore(CodecFactory.BytesCompressor compressor, MessageType schema, ByteBufferAllocator allocator, int columnIndexTruncateLength, boolean pageWriteChecksumEnabled) {
      this((CompressionCodecFactory.BytesInputCompressor)compressor, schema, allocator, columnIndexTruncateLength, pageWriteChecksumEnabled);
   }

   public ColumnChunkPageWriteStore(CompressionCodecFactory.BytesInputCompressor compressor, MessageType schema, ByteBufferAllocator allocator, int columnIndexTruncateLength, boolean pageWriteChecksumEnabled) {
      this.writers = new HashMap();
      this.schema = schema;

      for(ColumnDescriptor path : schema.getColumns()) {
         this.writers.put(path, new ColumnChunkPageWriter(path, compressor, allocator, columnIndexTruncateLength, pageWriteChecksumEnabled, (BlockCipher.Encryptor)null, (BlockCipher.Encryptor)null, (byte[])null, -1, -1));
      }

   }

   /** @deprecated */
   @Deprecated
   public ColumnChunkPageWriteStore(CodecFactory.BytesCompressor compressor, MessageType schema, ByteBufferAllocator allocator, int columnIndexTruncateLength, boolean pageWriteChecksumEnabled, InternalFileEncryptor fileEncryptor, int rowGroupOrdinal) {
      this((CompressionCodecFactory.BytesInputCompressor)compressor, schema, allocator, columnIndexTruncateLength, pageWriteChecksumEnabled, fileEncryptor, rowGroupOrdinal);
   }

   public ColumnChunkPageWriteStore(CompressionCodecFactory.BytesInputCompressor compressor, MessageType schema, ByteBufferAllocator allocator, int columnIndexTruncateLength, boolean pageWriteChecksumEnabled, InternalFileEncryptor fileEncryptor, int rowGroupOrdinal) {
      this.writers = new HashMap();
      this.schema = schema;
      if (null != fileEncryptor) {
         int columnOrdinal = -1;
         byte[] fileAAD = fileEncryptor.getFileAAD();

         for(ColumnDescriptor path : schema.getColumns()) {
            ++columnOrdinal;
            BlockCipher.Encryptor headerBlockEncryptor = null;
            BlockCipher.Encryptor pageBlockEncryptor = null;
            ColumnPath columnPath = ColumnPath.get(path.getPath());
            InternalColumnEncryptionSetup columnSetup = fileEncryptor.getColumnSetup(columnPath, true, columnOrdinal);
            if (columnSetup.isEncrypted()) {
               headerBlockEncryptor = columnSetup.getMetaDataEncryptor();
               pageBlockEncryptor = columnSetup.getDataEncryptor();
            }

            this.writers.put(path, new ColumnChunkPageWriter(path, compressor, allocator, columnIndexTruncateLength, pageWriteChecksumEnabled, headerBlockEncryptor, pageBlockEncryptor, fileAAD, rowGroupOrdinal, columnOrdinal));
         }

      } else {
         for(ColumnDescriptor path : schema.getColumns()) {
            this.writers.put(path, new ColumnChunkPageWriter(path, compressor, allocator, columnIndexTruncateLength, pageWriteChecksumEnabled, (BlockCipher.Encryptor)null, (BlockCipher.Encryptor)null, (byte[])null, -1, -1));
         }

      }
   }

   public PageWriter getPageWriter(ColumnDescriptor path) {
      return (PageWriter)this.writers.get(path);
   }

   public void close() {
      AutoCloseables.uncheckedClose(this.writers.values());
      this.writers.clear();
   }

   public BloomFilterWriter getBloomFilterWriter(ColumnDescriptor path) {
      return (BloomFilterWriter)this.writers.get(path);
   }

   public void flushToFileWriter(ParquetFileWriter writer) throws IOException {
      for(ColumnDescriptor path : this.schema.getColumns()) {
         ColumnChunkPageWriter pageWriter = (ColumnChunkPageWriter)this.writers.get(path);
         pageWriter.writeToFileWriter(writer);
      }

   }

   private static final class ColumnChunkPageWriter implements PageWriter, BloomFilterWriter {
      private final ColumnDescriptor path;
      private final CompressionCodecFactory.BytesInputCompressor compressor;
      private final ByteArrayOutputStream tempOutputStream;
      private final ConcatenatingByteBufferCollector buf;
      private DictionaryPage dictionaryPage;
      private long uncompressedLength;
      private long compressedLength;
      private long totalValueCount;
      private int pageCount;
      private Set rlEncodings;
      private Set dlEncodings;
      private List dataEncodings;
      private BloomFilter bloomFilter;
      private ColumnIndexBuilder columnIndexBuilder;
      private OffsetIndexBuilder offsetIndexBuilder;
      private Statistics totalStatistics;
      private final SizeStatistics totalSizeStatistics;
      private final ByteBufferReleaser releaser;
      private final CRC32 crc;
      boolean pageWriteChecksumEnabled;
      private final BlockCipher.Encryptor headerBlockEncryptor;
      private final BlockCipher.Encryptor pageBlockEncryptor;
      private final int rowGroupOrdinal;
      private final int columnOrdinal;
      private int pageOrdinal;
      private final byte[] dataPageAAD;
      private final byte[] dataPageHeaderAAD;
      private final byte[] fileAAD;

      private ColumnChunkPageWriter(ColumnDescriptor path, CompressionCodecFactory.BytesInputCompressor compressor, ByteBufferAllocator allocator, int columnIndexTruncateLength, boolean pageWriteChecksumEnabled, BlockCipher.Encryptor headerBlockEncryptor, BlockCipher.Encryptor pageBlockEncryptor, byte[] fileAAD, int rowGroupOrdinal, int columnOrdinal) {
         this.tempOutputStream = new ByteArrayOutputStream();
         this.rlEncodings = new HashSet();
         this.dlEncodings = new HashSet();
         this.dataEncodings = new ArrayList();
         this.path = path;
         this.compressor = compressor;
         this.releaser = new ByteBufferReleaser(allocator);
         this.buf = new ConcatenatingByteBufferCollector(allocator);
         this.columnIndexBuilder = ColumnIndexBuilder.getBuilder(path.getPrimitiveType(), columnIndexTruncateLength);
         this.offsetIndexBuilder = OffsetIndexBuilder.getBuilder();
         this.totalSizeStatistics = SizeStatistics.newBuilder(path.getPrimitiveType(), path.getMaxRepetitionLevel(), path.getMaxDefinitionLevel()).build();
         this.pageWriteChecksumEnabled = pageWriteChecksumEnabled;
         this.crc = pageWriteChecksumEnabled ? new CRC32() : null;
         this.headerBlockEncryptor = headerBlockEncryptor;
         this.pageBlockEncryptor = pageBlockEncryptor;
         this.fileAAD = fileAAD;
         this.rowGroupOrdinal = rowGroupOrdinal;
         this.columnOrdinal = columnOrdinal;
         this.pageOrdinal = -1;
         if (null != headerBlockEncryptor) {
            this.dataPageHeaderAAD = AesCipher.createModuleAAD(fileAAD, ModuleCipherFactory.ModuleType.DataPageHeader, rowGroupOrdinal, columnOrdinal, 0);
         } else {
            this.dataPageHeaderAAD = null;
         }

         if (null != pageBlockEncryptor) {
            this.dataPageAAD = AesCipher.createModuleAAD(fileAAD, ModuleCipherFactory.ModuleType.DataPage, rowGroupOrdinal, columnOrdinal, 0);
         } else {
            this.dataPageAAD = null;
         }

      }

      /** @deprecated */
      @Deprecated
      public void writePage(BytesInput bytesInput, int valueCount, Statistics statistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) throws IOException {
         this.columnIndexBuilder = ColumnIndexBuilder.getNoOpBuilder();
         this.offsetIndexBuilder = OffsetIndexBuilder.getNoOpBuilder();
         this.writePage(bytesInput, valueCount, -1, statistics, rlEncoding, dlEncoding, valuesEncoding);
      }

      public void writePage(BytesInput bytes, int valueCount, int rowCount, Statistics statistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) throws IOException {
         this.writePage(bytes, valueCount, rowCount, statistics, (SizeStatistics)null, rlEncoding, dlEncoding, valuesEncoding);
      }

      public void writePage(BytesInput bytes, int valueCount, int rowCount, Statistics statistics, SizeStatistics sizeStatistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) throws IOException {
         ++this.pageOrdinal;
         long uncompressedSize = bytes.size();
         if (uncompressedSize <= 2147483647L && uncompressedSize >= 0L) {
            BytesInput compressedBytes = this.compressor.compress(bytes);
            if (null != this.pageBlockEncryptor) {
               AesCipher.quickUpdatePageAAD(this.dataPageAAD, this.pageOrdinal);
               compressedBytes = BytesInput.from(this.pageBlockEncryptor.encrypt(compressedBytes.toByteArray(), this.dataPageAAD));
            }

            long compressedSize = compressedBytes.size();
            if (compressedSize > 2147483647L) {
               throw new ParquetEncodingException("Cannot write compressed page larger than Integer.MAX_VALUE bytes: " + compressedSize);
            } else {
               this.tempOutputStream.reset();
               if (null != this.headerBlockEncryptor) {
                  AesCipher.quickUpdatePageAAD(this.dataPageHeaderAAD, this.pageOrdinal);
               }

               if (this.pageWriteChecksumEnabled) {
                  this.crc.reset();
                  this.crc.update(compressedBytes.toByteArray());
                  ColumnChunkPageWriteStore.parquetMetadataConverter.writeDataPageV1Header((int)uncompressedSize, (int)compressedSize, valueCount, rlEncoding, dlEncoding, valuesEncoding, (int)this.crc.getValue(), this.tempOutputStream, this.headerBlockEncryptor, this.dataPageHeaderAAD);
               } else {
                  ColumnChunkPageWriteStore.parquetMetadataConverter.writeDataPageV1Header((int)uncompressedSize, (int)compressedSize, valueCount, rlEncoding, dlEncoding, valuesEncoding, this.tempOutputStream, this.headerBlockEncryptor, this.dataPageHeaderAAD);
               }

               this.uncompressedLength += uncompressedSize;
               this.compressedLength += compressedSize;
               this.totalValueCount += (long)valueCount;
               ++this.pageCount;
               this.mergeColumnStatistics(statistics, sizeStatistics);
               this.offsetIndexBuilder.add(this.toIntWithCheck((long)this.tempOutputStream.size() + compressedSize), (long)rowCount, sizeStatistics != null ? sizeStatistics.getUnencodedByteArrayDataBytes() : Optional.empty());
               this.buf.collect(BytesInput.concat(new BytesInput[]{BytesInput.from(this.tempOutputStream), compressedBytes}));
               this.rlEncodings.add(rlEncoding);
               this.dlEncodings.add(dlEncoding);
               this.dataEncodings.add(valuesEncoding);
            }
         } else {
            throw new ParquetEncodingException("Cannot write page larger than Integer.MAX_VALUE or negative bytes: " + uncompressedSize);
         }
      }

      public void writePageV2(int rowCount, int nullCount, int valueCount, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput data, Statistics statistics) throws IOException {
         this.writePageV2(rowCount, nullCount, valueCount, repetitionLevels, definitionLevels, dataEncoding, data, statistics, (SizeStatistics)null);
      }

      public void writePageV2(int rowCount, int nullCount, int valueCount, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput data, Statistics statistics, SizeStatistics sizeStatistics) throws IOException {
         ++this.pageOrdinal;
         int rlByteLength = this.toIntWithCheck(repetitionLevels.size());
         int dlByteLength = this.toIntWithCheck(definitionLevels.size());
         int uncompressedSize = this.toIntWithCheck(data.size() + repetitionLevels.size() + definitionLevels.size());
         BytesInput compressedData = this.compressor.compress(data);
         if (null != this.pageBlockEncryptor) {
            AesCipher.quickUpdatePageAAD(this.dataPageAAD, this.pageOrdinal);
            compressedData = BytesInput.from(this.pageBlockEncryptor.encrypt(compressedData.toByteArray(), this.dataPageAAD));
         }

         int compressedSize = this.toIntWithCheck(compressedData.size() + repetitionLevels.size() + definitionLevels.size());
         this.tempOutputStream.reset();
         if (null != this.headerBlockEncryptor) {
            AesCipher.quickUpdatePageAAD(this.dataPageHeaderAAD, this.pageOrdinal);
         }

         if (this.pageWriteChecksumEnabled) {
            this.crc.reset();
            if (repetitionLevels.size() > 0L) {
               this.crc.update(repetitionLevels.toByteArray());
            }

            if (definitionLevels.size() > 0L) {
               this.crc.update(definitionLevels.toByteArray());
            }

            if (compressedData.size() > 0L) {
               this.crc.update(compressedData.toByteArray());
            }

            ColumnChunkPageWriteStore.parquetMetadataConverter.writeDataPageV2Header(uncompressedSize, compressedSize, valueCount, nullCount, rowCount, dataEncoding, rlByteLength, dlByteLength, (int)this.crc.getValue(), this.tempOutputStream, this.headerBlockEncryptor, this.dataPageHeaderAAD);
         } else {
            ColumnChunkPageWriteStore.parquetMetadataConverter.writeDataPageV2Header(uncompressedSize, compressedSize, valueCount, nullCount, rowCount, dataEncoding, rlByteLength, dlByteLength, this.tempOutputStream, this.headerBlockEncryptor, this.dataPageHeaderAAD);
         }

         this.uncompressedLength += (long)uncompressedSize;
         this.compressedLength += (long)compressedSize;
         this.totalValueCount += (long)valueCount;
         ++this.pageCount;
         this.mergeColumnStatistics(statistics, sizeStatistics);
         this.offsetIndexBuilder.add(this.toIntWithCheck((long)this.tempOutputStream.size() + (long)compressedSize), (long)rowCount, sizeStatistics != null ? sizeStatistics.getUnencodedByteArrayDataBytes() : Optional.empty());
         this.buf.collect(BytesInput.concat(new BytesInput[]{BytesInput.from(this.tempOutputStream), repetitionLevels, definitionLevels, compressedData}));
         this.dataEncodings.add(dataEncoding);
      }

      private int toIntWithCheck(long size) {
         if (size > 2147483647L) {
            throw new ParquetEncodingException("Cannot write page larger than 2147483647 bytes: " + size);
         } else {
            return (int)size;
         }
      }

      private void mergeColumnStatistics(Statistics statistics, SizeStatistics sizeStatistics) {
         this.totalSizeStatistics.mergeStatistics(sizeStatistics);
         if (!this.totalSizeStatistics.isValid()) {
            sizeStatistics = null;
         }

         if (this.totalStatistics == null || !this.totalStatistics.isEmpty()) {
            if (statistics != null && !statistics.isEmpty()) {
               if (this.totalStatistics == null) {
                  this.totalStatistics = statistics.copy();
                  this.columnIndexBuilder.add(statistics, sizeStatistics);
               } else {
                  this.totalStatistics.mergeStatistics(statistics);
                  this.columnIndexBuilder.add(statistics, sizeStatistics);
               }
            } else {
               this.totalStatistics = Statistics.getBuilderForReading(this.path.getPrimitiveType()).build();
               this.columnIndexBuilder = ColumnIndexBuilder.getNoOpBuilder();
            }

         }
      }

      public long getMemSize() {
         return this.buf.size();
      }

      public void writeToFileWriter(ParquetFileWriter writer) throws IOException {
         if (null == this.headerBlockEncryptor) {
            writer.writeColumnChunk(this.path, this.totalValueCount, this.compressor.getCodecName(), this.dictionaryPage, this.buf, this.uncompressedLength, this.compressedLength, this.totalStatistics, this.totalSizeStatistics, this.columnIndexBuilder, this.offsetIndexBuilder, this.bloomFilter, this.rlEncodings, this.dlEncodings, this.dataEncodings);
         } else {
            writer.writeColumnChunk(this.path, this.totalValueCount, this.compressor.getCodecName(), this.dictionaryPage, this.buf, this.uncompressedLength, this.compressedLength, this.totalStatistics, this.totalSizeStatistics, this.columnIndexBuilder, this.offsetIndexBuilder, this.bloomFilter, this.rlEncodings, this.dlEncodings, this.dataEncodings, this.headerBlockEncryptor, this.rowGroupOrdinal, this.columnOrdinal, this.fileAAD);
         }

         if (ColumnChunkPageWriteStore.LOG.isDebugEnabled()) {
            ColumnChunkPageWriteStore.LOG.debug(String.format("written %,dB for %s: %,d values, %,dB raw, %,dB comp, %d pages, encodings: %s", this.buf.size(), this.path, this.totalValueCount, this.uncompressedLength, this.compressedLength, this.pageCount, new HashSet(this.dataEncodings)) + (this.dictionaryPage != null ? String.format(", dic { %,d entries, %,dB raw, %,dB comp}", this.dictionaryPage.getDictionarySize(), this.dictionaryPage.getUncompressedSize(), this.dictionaryPage.getDictionarySize()) : ""));
         }

         this.rlEncodings.clear();
         this.dlEncodings.clear();
         this.dataEncodings.clear();
         this.pageCount = 0;
         this.pageOrdinal = -1;
      }

      public long allocatedSize() {
         return this.buf.size();
      }

      public void writeDictionaryPage(DictionaryPage dictionaryPage) throws IOException {
         if (this.dictionaryPage != null) {
            throw new ParquetEncodingException("Only one dictionary page is allowed");
         } else {
            BytesInput dictionaryBytes = dictionaryPage.getBytes();
            int uncompressedSize = (int)dictionaryBytes.size();
            BytesInput compressedBytes = this.compressor.compress(dictionaryBytes);
            if (null != this.pageBlockEncryptor) {
               byte[] dictonaryPageAAD = AesCipher.createModuleAAD(this.fileAAD, ModuleCipherFactory.ModuleType.DictionaryPage, this.rowGroupOrdinal, this.columnOrdinal, -1);
               compressedBytes = BytesInput.from(this.pageBlockEncryptor.encrypt(compressedBytes.toByteArray(), dictonaryPageAAD));
            }

            this.dictionaryPage = new DictionaryPage(compressedBytes.copy(this.releaser), uncompressedSize, dictionaryPage.getDictionarySize(), dictionaryPage.getEncoding());
         }
      }

      public String memUsageString(String prefix) {
         return this.buf.memUsageString(prefix + " ColumnChunkPageWriter");
      }

      public void close() {
         AutoCloseables.uncheckedClose(new AutoCloseable[]{this.buf, this.releaser});
      }

      public void writeBloomFilter(BloomFilter bloomFilter) {
         this.bloomFilter = bloomFilter;
      }
   }
}
