package org.apache.parquet.hadoop.metadata;

import java.util.Set;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.EncodingStats;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.schema.PrimitiveType;

class LongColumnChunkMetaData extends ColumnChunkMetaData {
   private final long firstDataPageOffset;
   private final long dictionaryPageOffset;
   private final long valueCount;
   private final long totalSize;
   private final long totalUncompressedSize;
   private final Statistics statistics;
   private final SizeStatistics sizeStatistics;

   LongColumnChunkMetaData(ColumnPath path, PrimitiveType type, CompressionCodecName codec, EncodingStats encodingStats, Set encodings, Statistics statistics, long firstDataPageOffset, long dictionaryPageOffset, long valueCount, long totalSize, long totalUncompressedSize, SizeStatistics sizeStatistics) {
      super(encodingStats, ColumnChunkProperties.get(path, type, codec, encodings));
      this.firstDataPageOffset = firstDataPageOffset;
      this.dictionaryPageOffset = dictionaryPageOffset;
      this.valueCount = valueCount;
      this.totalSize = totalSize;
      this.totalUncompressedSize = totalUncompressedSize;
      this.statistics = statistics;
      this.sizeStatistics = sizeStatistics;
   }

   public long getFirstDataPageOffset() {
      return this.firstDataPageOffset;
   }

   public long getDictionaryPageOffset() {
      return this.dictionaryPageOffset;
   }

   public long getValueCount() {
      return this.valueCount;
   }

   public long getTotalUncompressedSize() {
      return this.totalUncompressedSize;
   }

   public long getTotalSize() {
      return this.totalSize;
   }

   public Statistics getStatistics() {
      return this.statistics;
   }

   public SizeStatistics getSizeStatistics() {
      return this.sizeStatistics;
   }
}
