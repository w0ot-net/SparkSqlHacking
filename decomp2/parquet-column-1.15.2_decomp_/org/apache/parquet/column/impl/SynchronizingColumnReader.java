package org.apache.parquet.column.impl;

import java.util.PrimitiveIterator;
import org.apache.parquet.VersionParser;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.page.DataPage;
import org.apache.parquet.column.page.PageReader;
import org.apache.parquet.io.api.PrimitiveConverter;

class SynchronizingColumnReader extends ColumnReaderBase {
   private final PrimitiveIterator.OfLong rowIndexes;
   private long currentRow;
   private long targetRow;
   private long lastRowInPage;
   private int valuesReadFromPage;

   SynchronizingColumnReader(ColumnDescriptor path, PageReader pageReader, PrimitiveConverter converter, VersionParser.ParsedVersion writerVersion, PrimitiveIterator.OfLong rowIndexes) {
      super(path, pageReader, converter, writerVersion);
      this.rowIndexes = rowIndexes;
      this.targetRow = Long.MIN_VALUE;
      this.consume();
   }

   boolean isPageFullyConsumed() {
      return this.getPageValueCount() <= this.valuesReadFromPage || this.lastRowInPage < this.targetRow;
   }

   boolean isFullyConsumed() {
      return !this.rowIndexes.hasNext();
   }

   boolean skipRL(int rl) {
      ++this.valuesReadFromPage;
      if (rl == 0) {
         ++this.currentRow;
         if (this.currentRow > this.targetRow) {
            this.targetRow = this.rowIndexes.hasNext() ? this.rowIndexes.nextLong() : Long.MAX_VALUE;
         }
      }

      return this.currentRow < this.targetRow;
   }

   protected void newPageInitialized(DataPage page) {
      long firstRowIndex = (Long)page.getFirstRowIndex().orElseThrow(() -> new IllegalArgumentException("Missing firstRowIndex for synchronizing values"));
      int rowCount = (Integer)page.getIndexRowCount().orElseThrow(() -> new IllegalArgumentException("Missing rowCount for synchronizing values"));
      this.currentRow = firstRowIndex - 1L;
      this.lastRowInPage = firstRowIndex + (long)rowCount - 1L;
      this.valuesReadFromPage = 0;
   }
}
