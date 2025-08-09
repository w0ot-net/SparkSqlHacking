package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractMasterDetailListProcessor;
import com.univocity.parsers.common.processor.core.AbstractObjectListProcessor;

public abstract class MasterDetailListProcessor extends AbstractMasterDetailListProcessor implements RowProcessor {
   public MasterDetailListProcessor(RowPlacement rowPlacement, AbstractObjectListProcessor detailProcessor) {
      super(rowPlacement, detailProcessor);
   }

   public MasterDetailListProcessor(AbstractObjectListProcessor detailProcessor) {
      super(detailProcessor);
   }
}
