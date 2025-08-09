package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractMultiBeanListProcessor;

public class MultiBeanListProcessor extends AbstractMultiBeanListProcessor implements RowProcessor {
   public MultiBeanListProcessor(int expectedBeanCount, Class... beanTypes) {
      super(expectedBeanCount, beanTypes);
   }

   public MultiBeanListProcessor(Class... beanTypes) {
      super(beanTypes);
   }
}
