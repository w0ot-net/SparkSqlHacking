package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.processor.core.AbstractBeanListProcessor;

public class BeanListProcessor extends AbstractBeanListProcessor implements RowProcessor {
   public BeanListProcessor(Class beanType) {
      super(beanType);
   }

   public BeanListProcessor(Class beanType, int expectedBeanCount) {
      super(beanType, expectedBeanCount);
   }
}
