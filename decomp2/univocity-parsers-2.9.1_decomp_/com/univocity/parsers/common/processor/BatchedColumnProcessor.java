package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractBatchedColumnProcessor;

public abstract class BatchedColumnProcessor extends AbstractBatchedColumnProcessor implements RowProcessor {
   public BatchedColumnProcessor(int rowsPerBatch) {
      super(rowsPerBatch);
   }
}
