package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AbstractMultiBeanListProcessor extends AbstractMultiBeanRowProcessor {
   private final Class[] beanTypes;
   private final List[] beans;
   private String[] headers;
   private final int expectedBeanCount;

   public AbstractMultiBeanListProcessor(int expectedBeanCount, Class... beanTypes) {
      super(beanTypes);
      this.beanTypes = beanTypes;
      this.beans = new List[beanTypes.length];
      this.expectedBeanCount = expectedBeanCount <= 0 ? 10000 : expectedBeanCount;
   }

   public AbstractMultiBeanListProcessor(Class... beanTypes) {
      this(0, beanTypes);
   }

   public final void processStarted(Context context) {
      super.processStarted(context);

      for(int i = 0; i < this.beanTypes.length; ++i) {
         this.beans[i] = new ArrayList(this.expectedBeanCount);
      }

   }

   protected final void rowProcessed(Map row, Context context) {
      for(int i = 0; i < this.beanTypes.length; ++i) {
         Object bean = row.get(this.beanTypes[i]);
         this.beans[i].add(bean);
      }

   }

   public final void processEnded(Context context) {
      this.headers = context.headers();
      super.processEnded(context);
   }

   public final String[] getHeaders() {
      return this.headers;
   }

   public List getBeans(Class beanType) {
      int index = ArgumentUtils.indexOf(this.beanTypes, beanType);
      if (index == -1) {
         throw new IllegalArgumentException("Unknown bean type '" + beanType.getSimpleName() + "'. Available types are: " + Arrays.toString(this.beanTypes));
      } else {
         return this.beans[index];
      }
   }

   public Map getBeans() {
      LinkedHashMap<Class<?>, List<?>> out = new LinkedHashMap();

      for(int i = 0; i < this.beanTypes.length; ++i) {
         out.put(this.beanTypes[i], this.beans[i]);
      }

      return out;
   }
}
