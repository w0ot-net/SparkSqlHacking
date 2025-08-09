package org.supercsv.io;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvReflectionException;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.BeanInterfaceProxy;
import org.supercsv.util.MethodCache;

public class CsvBeanReader extends AbstractCsvReader implements ICsvBeanReader {
   private final List processedColumns = new ArrayList();
   private final MethodCache cache = new MethodCache();

   public CsvBeanReader(Reader reader, CsvPreference preferences) {
      super(reader, preferences);
   }

   public CsvBeanReader(ITokenizer tokenizer, CsvPreference preferences) {
      super(tokenizer, preferences);
   }

   private static Object instantiateBean(Class clazz) {
      T bean;
      if (clazz.isInterface()) {
         bean = (T)BeanInterfaceProxy.createProxy(clazz);
      } else {
         try {
            bean = (T)clazz.newInstance();
         } catch (InstantiationException e) {
            throw new SuperCsvReflectionException(String.format("error instantiating bean, check that %s has a default no-args constructor", clazz.getName()), e);
         } catch (IllegalAccessException e) {
            throw new SuperCsvReflectionException("error instantiating bean", e);
         }
      }

      return bean;
   }

   private static void invokeSetter(Object bean, Method setMethod, Object fieldValue) {
      try {
         setMethod.invoke(bean, fieldValue);
      } catch (Exception e) {
         throw new SuperCsvReflectionException(String.format("error invoking method %s()", setMethod.getName()), e);
      }
   }

   private Object populateBean(Object resultBean, String[] nameMapping) {
      for(int i = 0; i < nameMapping.length; ++i) {
         Object fieldValue = this.processedColumns.get(i);
         if (nameMapping[i] != null && fieldValue != null) {
            Method setMethod = this.cache.getSetMethod(resultBean, nameMapping[i], fieldValue.getClass());
            invokeSetter(resultBean, setMethod, fieldValue);
         }
      }

      return resultBean;
   }

   public Object read(Class clazz, String... nameMapping) throws IOException {
      if (clazz == null) {
         throw new NullPointerException("clazz should not be null");
      } else if (nameMapping == null) {
         throw new NullPointerException("nameMapping should not be null");
      } else {
         return this.readIntoBean(instantiateBean(clazz), nameMapping, (CellProcessor[])null);
      }
   }

   public Object read(Class clazz, String[] nameMapping, CellProcessor... processors) throws IOException {
      if (clazz == null) {
         throw new NullPointerException("clazz should not be null");
      } else if (nameMapping == null) {
         throw new NullPointerException("nameMapping should not be null");
      } else if (processors == null) {
         throw new NullPointerException("processors should not be null");
      } else {
         return this.readIntoBean(instantiateBean(clazz), nameMapping, processors);
      }
   }

   public Object read(Object bean, String... nameMapping) throws IOException {
      if (bean == null) {
         throw new NullPointerException("bean should not be null");
      } else if (nameMapping == null) {
         throw new NullPointerException("nameMapping should not be null");
      } else {
         return this.readIntoBean(bean, nameMapping, (CellProcessor[])null);
      }
   }

   public Object read(Object bean, String[] nameMapping, CellProcessor... processors) throws IOException {
      if (bean == null) {
         throw new NullPointerException("bean should not be null");
      } else if (nameMapping == null) {
         throw new NullPointerException("nameMapping should not be null");
      } else if (processors == null) {
         throw new NullPointerException("processors should not be null");
      } else {
         return this.readIntoBean(bean, nameMapping, processors);
      }
   }

   private Object readIntoBean(Object bean, String[] nameMapping, CellProcessor[] processors) throws IOException {
      if (this.readRow()) {
         if (nameMapping.length != this.length()) {
            throw new IllegalArgumentException(String.format("the nameMapping array and the number of columns read should be the same size (nameMapping length = %d, columns = %d)", nameMapping.length, this.length()));
         } else {
            if (processors == null) {
               this.processedColumns.clear();
               this.processedColumns.addAll(this.getColumns());
            } else {
               this.executeProcessors(this.processedColumns, processors);
            }

            return this.populateBean(bean, nameMapping);
         }
      } else {
         return null;
      }
   }
}
