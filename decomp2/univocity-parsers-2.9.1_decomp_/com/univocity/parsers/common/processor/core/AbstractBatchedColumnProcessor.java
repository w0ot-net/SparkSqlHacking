package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import java.util.List;
import java.util.Map;

public abstract class AbstractBatchedColumnProcessor implements Processor, BatchedColumnReader {
   private final ColumnSplitter splitter;
   private final int rowsPerBatch;
   private int batchCount;
   private int batchesProcessed;

   public AbstractBatchedColumnProcessor(int rowsPerBatch) {
      this.splitter = new ColumnSplitter(rowsPerBatch);
      this.rowsPerBatch = rowsPerBatch;
   }

   public void processStarted(Context context) {
      this.splitter.reset();
      this.batchCount = 0;
      this.batchesProcessed = 0;
   }

   public void rowProcessed(String[] row, Context context) {
      this.splitter.addValuesToColumns(row, context);
      ++this.batchCount;
      if (this.batchCount >= this.rowsPerBatch) {
         this.batchProcessed(this.batchCount);
         this.batchCount = 0;
         this.splitter.clearValues();
         ++this.batchesProcessed;
      }

   }

   public void processEnded(Context context) {
      if (this.batchCount > 0) {
         this.batchProcessed(this.batchCount);
      }

   }

   public final String[] getHeaders() {
      return this.splitter.getHeaders();
   }

   public final List getColumnValuesAsList() {
      return this.splitter.getColumnValues();
   }

   public final void putColumnValuesInMapOfNames(Map map) {
      this.splitter.putColumnValuesInMapOfNames(map);
   }

   public final void putColumnValuesInMapOfIndexes(Map map) {
      this.splitter.putColumnValuesInMapOfIndexes(map);
   }

   public final Map getColumnValuesAsMapOfNames() {
      return this.splitter.getColumnValuesAsMapOfNames();
   }

   public final Map getColumnValuesAsMapOfIndexes() {
      return this.splitter.getColumnValuesAsMapOfIndexes();
   }

   public List getColumn(String columnName) {
      return this.splitter.getColumnValues(columnName, String.class);
   }

   public List getColumn(int columnIndex) {
      return this.splitter.getColumnValues(columnIndex, String.class);
   }

   public int getRowsPerBatch() {
      return this.rowsPerBatch;
   }

   public int getBatchesProcessed() {
      return this.batchesProcessed;
   }

   public abstract void batchProcessed(int var1);
}
