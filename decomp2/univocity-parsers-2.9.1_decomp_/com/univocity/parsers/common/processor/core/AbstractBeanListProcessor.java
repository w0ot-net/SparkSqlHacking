package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.annotations.helpers.MethodFilter;
import com.univocity.parsers.common.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractBeanListProcessor extends AbstractBeanProcessor {
   private List beans;
   private String[] headers;
   private final int expectedBeanCount;

   public AbstractBeanListProcessor(Class beanType) {
      this(beanType, 0);
   }

   public AbstractBeanListProcessor(Class beanType, int expectedBeanCount) {
      super(beanType, MethodFilter.ONLY_SETTERS);
      this.expectedBeanCount = expectedBeanCount <= 0 ? 10000 : expectedBeanCount;
   }

   public void beanProcessed(Object bean, Context context) {
      this.beans.add(bean);
   }

   public List getBeans() {
      return this.beans == null ? Collections.emptyList() : this.beans;
   }

   public void processStarted(Context context) {
      super.processStarted(context);
      this.beans = new ArrayList(this.expectedBeanCount);
   }

   public void processEnded(Context context) {
      this.headers = context.headers();
      super.processEnded(context);
   }

   public String[] getHeaders() {
      return this.headers;
   }
}
