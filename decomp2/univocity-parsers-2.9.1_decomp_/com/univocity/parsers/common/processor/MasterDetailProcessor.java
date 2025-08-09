package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractMasterDetailProcessor;

public abstract class MasterDetailProcessor extends AbstractMasterDetailProcessor {
   public MasterDetailProcessor(RowPlacement rowPlacement, ObjectRowListProcessor detailProcessor) {
      super(rowPlacement, detailProcessor);
   }

   public MasterDetailProcessor(ObjectRowListProcessor detailProcessor) {
      super(RowPlacement.TOP, detailProcessor);
   }
}
