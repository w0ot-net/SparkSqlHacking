package org.apache.parquet.column.page;

import java.util.Optional;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.statistics.Statistics;

public class DataPageV1 extends DataPage {
   private final BytesInput bytes;
   private final Statistics statistics;
   private final Encoding rlEncoding;
   private final Encoding dlEncoding;
   private final Encoding valuesEncoding;
   private final int indexRowCount;

   public DataPageV1(BytesInput bytes, int valueCount, int uncompressedSize, Statistics statistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) {
      super(Math.toIntExact(bytes.size()), uncompressedSize, valueCount);
      this.bytes = bytes;
      this.statistics = statistics;
      this.rlEncoding = rlEncoding;
      this.dlEncoding = dlEncoding;
      this.valuesEncoding = valuesEncoding;
      this.indexRowCount = -1;
   }

   public DataPageV1(BytesInput bytes, int valueCount, int uncompressedSize, long firstRowIndex, int rowCount, Statistics statistics, Encoding rlEncoding, Encoding dlEncoding, Encoding valuesEncoding) {
      super(Math.toIntExact(bytes.size()), uncompressedSize, valueCount, firstRowIndex);
      this.bytes = bytes;
      this.statistics = statistics;
      this.rlEncoding = rlEncoding;
      this.dlEncoding = dlEncoding;
      this.valuesEncoding = valuesEncoding;
      this.indexRowCount = rowCount;
   }

   public BytesInput getBytes() {
      return this.bytes;
   }

   public Statistics getStatistics() {
      return this.statistics;
   }

   public Encoding getDlEncoding() {
      return this.dlEncoding;
   }

   public Encoding getRlEncoding() {
      return this.rlEncoding;
   }

   public Encoding getValueEncoding() {
      return this.valuesEncoding;
   }

   public String toString() {
      return "Page [bytes.size=" + this.bytes.size() + ", valueCount=" + this.getValueCount() + ", uncompressedSize=" + this.getUncompressedSize() + "]";
   }

   public Object accept(DataPage.Visitor visitor) {
      return visitor.visit(this);
   }

   public Optional getIndexRowCount() {
      return this.indexRowCount < 0 ? Optional.empty() : Optional.of(this.indexRowCount);
   }
}
