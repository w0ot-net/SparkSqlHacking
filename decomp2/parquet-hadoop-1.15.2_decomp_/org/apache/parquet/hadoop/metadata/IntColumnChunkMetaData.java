package org.apache.parquet.hadoop.metadata;

import java.util.Set;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.EncodingStats;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.schema.PrimitiveType;

class IntColumnChunkMetaData extends ColumnChunkMetaData {
   private final int firstDataPage;
   private final int dictionaryPageOffset;
   private final int valueCount;
   private final int totalSize;
   private final int totalUncompressedSize;
   private final Statistics statistics;
   private final SizeStatistics sizeStatistics;

   IntColumnChunkMetaData(ColumnPath path, PrimitiveType type, CompressionCodecName codec, EncodingStats encodingStats, Set encodings, Statistics statistics, long firstDataPage, long dictionaryPageOffset, long valueCount, long totalSize, long totalUncompressedSize, SizeStatistics sizeStatistics) {
      super(encodingStats, ColumnChunkProperties.get(path, type, codec, encodings));
      this.firstDataPage = this.positiveLongToInt(firstDataPage);
      this.dictionaryPageOffset = this.positiveLongToInt(dictionaryPageOffset);
      this.valueCount = this.positiveLongToInt(valueCount);
      this.totalSize = this.positiveLongToInt(totalSize);
      this.totalUncompressedSize = this.positiveLongToInt(totalUncompressedSize);
      this.statistics = statistics;
      this.sizeStatistics = sizeStatistics;
   }

   private int positiveLongToInt(long value) {
      if (!ColumnChunkMetaData.positiveLongFitsInAnInt(value)) {
         throw new IllegalArgumentException("value should be positive and fit in an int: " + value);
      } else {
         return (int)(value + -2147483648L);
      }
   }

   private long intToPositiveLong(int value) {
      return (long)value - -2147483648L;
   }

   public long getFirstDataPageOffset() {
      return this.intToPositiveLong(this.firstDataPage);
   }

   public long getDictionaryPageOffset() {
      return this.intToPositiveLong(this.dictionaryPageOffset);
   }

   public long getValueCount() {
      return this.intToPositiveLong(this.valueCount);
   }

   public long getTotalUncompressedSize() {
      return this.intToPositiveLong(this.totalUncompressedSize);
   }

   public long getTotalSize() {
      return this.intToPositiveLong(this.totalSize);
   }

   public Statistics getStatistics() {
      return this.statistics;
   }

   public SizeStatistics getSizeStatistics() {
      return this.sizeStatistics;
   }
}
