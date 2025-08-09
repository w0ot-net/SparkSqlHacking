package shaded.parquet.com.fasterxml.jackson.databind.deser;

import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;

public interface ValueInstantiators {
   ValueInstantiator findValueInstantiator(DeserializationConfig var1, BeanDescription var2, ValueInstantiator var3);

   public static class Base implements ValueInstantiators {
      public ValueInstantiator findValueInstantiator(DeserializationConfig config, BeanDescription beanDesc, ValueInstantiator defaultInstantiator) {
         return defaultInstantiator;
      }
   }
}
