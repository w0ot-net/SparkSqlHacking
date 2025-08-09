package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractMultiBeanRowProcessor;

public abstract class MultiBeanRowProcessor extends AbstractMultiBeanRowProcessor implements RowProcessor {
   public MultiBeanRowProcessor(Class... beanTypes) {
      super(beanTypes);
   }
}
