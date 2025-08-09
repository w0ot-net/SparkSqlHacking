package org.apache.parquet.column.page;

import java.util.Optional;

public abstract class DataPage extends Page {
   private final int valueCount;
   private final long firstRowIndex;

   DataPage(int compressedSize, int uncompressedSize, int valueCount) {
      this(compressedSize, uncompressedSize, valueCount, -1L);
   }

   DataPage(int compressedSize, int uncompressedSize, int valueCount, long firstRowIndex) {
      super(compressedSize, uncompressedSize);
      this.valueCount = valueCount;
      this.firstRowIndex = firstRowIndex;
   }

   public int getValueCount() {
      return this.valueCount;
   }

   public Optional getFirstRowIndex() {
      return this.firstRowIndex < 0L ? Optional.empty() : Optional.of(this.firstRowIndex);
   }

   public abstract Optional getIndexRowCount();

   public abstract Object accept(Visitor var1);

   public interface Visitor {
      Object visit(DataPageV1 var1);

      Object visit(DataPageV2 var1);
   }
}
