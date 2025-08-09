package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractMultiBeanProcessor;

public abstract class MultiBeanProcessor extends AbstractMultiBeanProcessor implements RowProcessor {
   public MultiBeanProcessor(Class... beanTypes) {
      super(beanTypes);
   }
}
