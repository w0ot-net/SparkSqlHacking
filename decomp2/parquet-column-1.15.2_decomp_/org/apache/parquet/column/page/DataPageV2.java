package org.apache.parquet.column.page;

import java.util.Optional;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.statistics.Statistics;

public class DataPageV2 extends DataPage {
   private final int rowCount;
   private final int nullCount;
   private final BytesInput repetitionLevels;
   private final BytesInput definitionLevels;
   private final Encoding dataEncoding;
   private final BytesInput data;
   private final Statistics statistics;
   private final boolean isCompressed;

   public static DataPageV2 uncompressed(int rowCount, int nullCount, int valueCount, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput data, Statistics statistics) {
      return new DataPageV2(rowCount, nullCount, valueCount, repetitionLevels, definitionLevels, dataEncoding, data, Math.toIntExact(repetitionLevels.size() + definitionLevels.size() + data.size()), statistics, false);
   }

   public static DataPageV2 uncompressed(int rowCount, int nullCount, int valueCount, long firstRowIndex, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput data, Statistics statistics) {
      return new DataPageV2(rowCount, nullCount, valueCount, firstRowIndex, repetitionLevels, definitionLevels, dataEncoding, data, Math.toIntExact(repetitionLevels.size() + definitionLevels.size() + data.size()), statistics, false);
   }

   public static DataPageV2 compressed(int rowCount, int nullCount, int valueCount, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput data, int uncompressedSize, Statistics statistics) {
      return new DataPageV2(rowCount, nullCount, valueCount, repetitionLevels, definitionLevels, dataEncoding, data, uncompressedSize, statistics, true);
   }

   public DataPageV2(int rowCount, int nullCount, int valueCount, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput data, int uncompressedSize, Statistics statistics, boolean isCompressed) {
      super(Math.toIntExact(repetitionLevels.size() + definitionLevels.size() + data.size()), uncompressedSize, valueCount);
      this.rowCount = rowCount;
      this.nullCount = nullCount;
      this.repetitionLevels = repetitionLevels;
      this.definitionLevels = definitionLevels;
      this.dataEncoding = dataEncoding;
      this.data = data;
      this.statistics = statistics;
      this.isCompressed = isCompressed;
   }

   private DataPageV2(int rowCount, int nullCount, int valueCount, long firstRowIndex, BytesInput repetitionLevels, BytesInput definitionLevels, Encoding dataEncoding, BytesInput data, int uncompressedSize, Statistics statistics, boolean isCompressed) {
      super(Math.toIntExact(repetitionLevels.size() + definitionLevels.size() + data.size()), uncompressedSize, valueCount, firstRowIndex);
      this.rowCount = rowCount;
      this.nullCount = nullCount;
      this.repetitionLevels = repetitionLevels;
      this.definitionLevels = definitionLevels;
      this.dataEncoding = dataEncoding;
      this.data = data;
      this.statistics = statistics;
      this.isCompressed = isCompressed;
   }

   public int getRowCount() {
      return this.rowCount;
   }

   public int getNullCount() {
      return this.nullCount;
   }

   public BytesInput getRepetitionLevels() {
      return this.repetitionLevels;
   }

   public BytesInput getDefinitionLevels() {
      return this.definitionLevels;
   }

   public Encoding getDataEncoding() {
      return this.dataEncoding;
   }

   public BytesInput getData() {
      return this.data;
   }

   public Statistics getStatistics() {
      return this.statistics;
   }

   public boolean isCompressed() {
      return this.isCompressed;
   }

   public Optional getIndexRowCount() {
      return Optional.of(this.rowCount);
   }

   public Object accept(DataPage.Visitor visitor) {
      return visitor.visit(this);
   }

   public String toString() {
      return "Page V2 [dl size=" + this.definitionLevels.size() + ", rl size=" + this.repetitionLevels.size() + ", data size=" + this.data.size() + ", data enc=" + this.dataEncoding + ", valueCount=" + this.getValueCount() + ", rowCount=" + this.getRowCount() + ", is compressed=" + this.isCompressed + ", uncompressedSize=" + this.getUncompressedSize() + "]";
   }
}
