package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.annotations.helpers.MethodFilter;
import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.NormalizedString;

public abstract class AbstractBeanProcessor extends BeanConversionProcessor implements Processor {
   public AbstractBeanProcessor(Class beanType, MethodFilter methodFilter) {
      super(beanType, methodFilter);
   }

   public final void rowProcessed(String[] row, Context context) {
      T instance = (T)this.createBean(row, context);
      if (instance != null) {
         this.beanProcessed(instance, context);
      }

   }

   public abstract void beanProcessed(Object var1, Context var2);

   public void processStarted(Context context) {
      super.initialize(NormalizedString.toArray(context.headers()));
   }

   public void processEnded(Context context) {
   }
}
