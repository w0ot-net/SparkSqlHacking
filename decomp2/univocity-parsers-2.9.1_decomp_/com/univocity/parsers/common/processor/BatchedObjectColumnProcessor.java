package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractBatchedObjectColumnProcessor;

public abstract class BatchedObjectColumnProcessor extends AbstractBatchedObjectColumnProcessor implements RowProcessor {
   public BatchedObjectColumnProcessor(int rowsPerBatch) {
      super(rowsPerBatch);
   }
}
