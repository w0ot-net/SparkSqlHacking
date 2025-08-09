package com.univocity.parsers.common.processor.core;

interface BatchedColumnReader extends ColumnReader {
   int getRowsPerBatch();

   int getBatchesProcessed();

   void batchProcessed(int var1);
}
