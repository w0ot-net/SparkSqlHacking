package org.apache.parquet.hadoop.metadata;

import java.util.Set;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.EncodingStats;
import org.apache.parquet.column.statistics.BooleanStatistics;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.crypto.InternalFileDecryptor;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.internal.hadoop.metadata.IndexReference;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class ColumnChunkMetaData {
   protected int rowGroupOrdinal;
   EncodingStats encodingStats;
   ColumnChunkProperties properties;
   private IndexReference columnIndexReference;
   private IndexReference offsetIndexReference;
   private long bloomFilterOffset;
   private int bloomFilterLength;

   /** @deprecated */
   @Deprecated
   public static ColumnChunkMetaData get(ColumnPath path, PrimitiveType.PrimitiveTypeName type, CompressionCodecName codec, Set encodings, long firstDataPage, long dictionaryPageOffset, long valueCount, long totalSize, long totalUncompressedSize) {
      return get(path, (PrimitiveType.PrimitiveTypeName)type, codec, (EncodingStats)null, encodings, new BooleanStatistics(), firstDataPage, dictionaryPageOffset, valueCount, totalSize, totalUncompressedSize);
   }

   /** @deprecated */
   @Deprecated
   public static ColumnChunkMetaData get(ColumnPath path, PrimitiveType.PrimitiveTypeName type, CompressionCodecName codec, Set encodings, Statistics statistics, long firstDataPage, long dictionaryPageOffset, long valueCount, long totalSize, long totalUncompressedSize) {
      return get(path, (PrimitiveType.PrimitiveTypeName)type, codec, (EncodingStats)null, encodings, statistics, firstDataPage, dictionaryPageOffset, valueCount, totalSize, totalUncompressedSize);
   }

   /** @deprecated */
   @Deprecated
   public static ColumnChunkMetaData get(ColumnPath path, PrimitiveType.PrimitiveTypeName type, CompressionCodecName codec, EncodingStats encodingStats, Set encodings, Statistics statistics, long firstDataPage, long dictionaryPageOffset, long valueCount, long totalSize, long totalUncompressedSize) {
      return get(path, (PrimitiveType)Types.optional(type).named("fake_type"), codec, encodingStats, encodings, statistics, firstDataPage, dictionaryPageOffset, valueCount, totalSize, totalUncompressedSize);
   }

   public static ColumnChunkMetaData get(ColumnPath path, PrimitiveType type, CompressionCodecName codec, EncodingStats encodingStats, Set encodings, Statistics statistics, long firstDataPage, long dictionaryPageOffset, long valueCount, long totalSize, long totalUncompressedSize) {
      return get(path, type, codec, encodingStats, encodings, statistics, firstDataPage, dictionaryPageOffset, valueCount, totalSize, totalUncompressedSize, (SizeStatistics)null);
   }

   public static ColumnChunkMetaData get(ColumnPath path, PrimitiveType type, CompressionCodecName codec, EncodingStats encodingStats, Set encodings, Statistics statistics, long firstDataPage, long dictionaryPageOffset, long valueCount, long totalSize, long totalUncompressedSize, SizeStatistics sizeStatistics) {
      return (ColumnChunkMetaData)(positiveLongFitsInAnInt(firstDataPage) && positiveLongFitsInAnInt(dictionaryPageOffset) && positiveLongFitsInAnInt(valueCount) && positiveLongFitsInAnInt(totalSize) && positiveLongFitsInAnInt(totalUncompressedSize) ? new IntColumnChunkMetaData(path, type, codec, encodingStats, encodings, statistics, firstDataPage, dictionaryPageOffset, valueCount, totalSize, totalUncompressedSize, sizeStatistics) : new LongColumnChunkMetaData(path, type, codec, encodingStats, encodings, statistics, firstDataPage, dictionaryPageOffset, valueCount, totalSize, totalUncompressedSize, sizeStatistics));
   }

   public static ColumnChunkMetaData getWithEncryptedMetadata(ParquetMetadataConverter parquetMetadataConverter, ColumnPath path, PrimitiveType type, byte[] encryptedMetadata, byte[] columnKeyMetadata, InternalFileDecryptor fileDecryptor, int rowGroupOrdinal, int columnOrdinal, String createdBy) {
      return new EncryptedColumnChunkMetaData(parquetMetadataConverter, path, type, encryptedMetadata, columnKeyMetadata, fileDecryptor, rowGroupOrdinal, columnOrdinal, createdBy);
   }

   public void setRowGroupOrdinal(int rowGroupOrdinal) {
      this.rowGroupOrdinal = rowGroupOrdinal;
   }

   public int getRowGroupOrdinal() {
      return this.rowGroupOrdinal;
   }

   public long getStartingPos() {
      this.decryptIfNeeded();
      long dictionaryPageOffset = this.getDictionaryPageOffset();
      long firstDataPageOffset = this.getFirstDataPageOffset();
      return dictionaryPageOffset > 0L && dictionaryPageOffset < firstDataPageOffset ? dictionaryPageOffset : firstDataPageOffset;
   }

   protected static boolean positiveLongFitsInAnInt(long value) {
      return value >= 0L && value + -2147483648L <= 2147483647L;
   }

   protected ColumnChunkMetaData(ColumnChunkProperties columnChunkProperties) {
      this((EncodingStats)null, columnChunkProperties);
   }

   protected ColumnChunkMetaData(EncodingStats encodingStats, ColumnChunkProperties columnChunkProperties) {
      this.rowGroupOrdinal = -1;
      this.bloomFilterOffset = -1L;
      this.bloomFilterLength = -1;
      this.encodingStats = encodingStats;
      this.properties = columnChunkProperties;
   }

   protected void decryptIfNeeded() {
   }

   public CompressionCodecName getCodec() {
      this.decryptIfNeeded();
      return this.properties.getCodec();
   }

   public ColumnPath getPath() {
      return this.properties.getPath();
   }

   /** @deprecated */
   @Deprecated
   @JsonIgnore
   public PrimitiveType.PrimitiveTypeName getType() {
      this.decryptIfNeeded();
      return this.properties.getType();
   }

   public PrimitiveType getPrimitiveType() {
      this.decryptIfNeeded();
      return this.properties.getPrimitiveType();
   }

   public abstract long getFirstDataPageOffset();

   public abstract long getDictionaryPageOffset();

   public abstract long getValueCount();

   public abstract long getTotalUncompressedSize();

   public abstract long getTotalSize();

   @JsonIgnore
   public abstract Statistics getStatistics();

   @JsonIgnore
   public SizeStatistics getSizeStatistics() {
      throw new UnsupportedOperationException("SizeStatistics is not implemented");
   }

   public IndexReference getColumnIndexReference() {
      this.decryptIfNeeded();
      return this.columnIndexReference;
   }

   public void setColumnIndexReference(IndexReference indexReference) {
      this.columnIndexReference = indexReference;
   }

   public IndexReference getOffsetIndexReference() {
      this.decryptIfNeeded();
      return this.offsetIndexReference;
   }

   public void setOffsetIndexReference(IndexReference offsetIndexReference) {
      this.offsetIndexReference = offsetIndexReference;
   }

   public void setBloomFilterOffset(long bloomFilterOffset) {
      this.bloomFilterOffset = bloomFilterOffset;
   }

   public void setBloomFilterLength(int bloomFilterLength) {
      this.bloomFilterLength = bloomFilterLength;
   }

   public long getBloomFilterOffset() {
      this.decryptIfNeeded();
      return this.bloomFilterOffset;
   }

   public int getBloomFilterLength() {
      this.decryptIfNeeded();
      return this.bloomFilterLength;
   }

   public Set getEncodings() {
      this.decryptIfNeeded();
      return this.properties.getEncodings();
   }

   public EncodingStats getEncodingStats() {
      this.decryptIfNeeded();
      return this.encodingStats;
   }

   public String toString() {
      this.decryptIfNeeded();
      return "ColumnMetaData{" + this.properties.toString() + ", " + this.getFirstDataPageOffset() + "}";
   }

   public boolean hasDictionaryPage() {
      EncodingStats stats = this.getEncodingStats();
      if (stats != null) {
         return stats.hasDictionaryPages() && stats.hasDictionaryEncodedPages();
      } else {
         Set<Encoding> encodings = this.getEncodings();
         return encodings.contains(Encoding.PLAIN_DICTIONARY) || encodings.contains(Encoding.RLE_DICTIONARY);
      }
   }

   public boolean isEncrypted() {
      return false;
   }
}
