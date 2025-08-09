package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.ConversionProcessor;
import com.univocity.parsers.common.fields.FieldSet;
import com.univocity.parsers.conversions.Conversion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMultiBeanProcessor implements Processor, ConversionProcessor {
   private final AbstractBeanProcessor[] beanProcessors;
   private final Map processorMap = new HashMap();

   public AbstractMultiBeanProcessor(Class... beanTypes) {
      // $FF: Couldn't be decompiled
   }

   public final Class[] getBeanClasses() {
      Class[] classes = new Class[this.beanProcessors.length];

      for(int i = 0; i < this.beanProcessors.length; ++i) {
         classes[i] = this.beanProcessors[i].beanClass;
      }

      return classes;
   }

   public AbstractBeanProcessor getProcessorOfType(Class type) {
      AbstractBeanProcessor<T, C> processor = (AbstractBeanProcessor)this.processorMap.get(type);
      if (processor == null) {
         throw new IllegalArgumentException("No processor of type '" + type.getName() + "' is available. Supported types are: " + this.processorMap.keySet());
      } else {
         return processor;
      }
   }

   public abstract void beanProcessed(Class var1, Object var2, Context var3);

   public void processStarted(Context context) {
      for(int i = 0; i < this.beanProcessors.length; ++i) {
         this.beanProcessors[i].processStarted(context);
      }

   }

   public final void rowProcessed(String[] row, Context context) {
      for(int i = 0; i < this.beanProcessors.length; ++i) {
         this.beanProcessors[i].rowProcessed(row, context);
      }

   }

   public void processEnded(Context context) {
      for(int i = 0; i < this.beanProcessors.length; ++i) {
         this.beanProcessors[i].processEnded(context);
      }

   }

   public FieldSet convertIndexes(Conversion... conversions) {
      List<FieldSet<Integer>> sets = new ArrayList(this.beanProcessors.length);

      for(int i = 0; i < this.beanProcessors.length; ++i) {
         sets.add(this.beanProcessors[i].convertIndexes(conversions));
      }

      return new FieldSet(sets);
   }

   public void convertAll(Conversion... conversions) {
      for(int i = 0; i < this.beanProcessors.length; ++i) {
         this.beanProcessors[i].convertAll(conversions);
      }

   }

   public FieldSet convertFields(Conversion... conversions) {
      List<FieldSet<String>> sets = new ArrayList(this.beanProcessors.length);

      for(int i = 0; i < this.beanProcessors.length; ++i) {
         sets.add(this.beanProcessors[i].convertFields(conversions));
      }

      return new FieldSet(sets);
   }

   public void convertType(Class type, Conversion... conversions) {
      for(int i = 0; i < this.beanProcessors.length; ++i) {
         this.beanProcessors[i].convertType(type, conversions);
      }

   }
}
