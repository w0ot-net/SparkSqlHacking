package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractColumnProcessor;

public class ColumnProcessor extends AbstractColumnProcessor implements RowProcessor {
   public ColumnProcessor() {
      super(1000);
   }

   public ColumnProcessor(int expectedRowCount) {
      super(expectedRowCount);
   }
}
