package com.univocity.parsers.common.processor;

import com.univocity.parsers.annotations.helpers.MethodFilter;
import com.univocity.parsers.common.processor.core.AbstractBeanProcessor;

public abstract class BeanProcessor extends AbstractBeanProcessor implements RowProcessor {
   public BeanProcessor(Class beanType) {
      super(beanType, MethodFilter.ONLY_SETTERS);
   }
}
