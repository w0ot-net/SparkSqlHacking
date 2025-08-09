package shaded.parquet.com.fasterxml.jackson.databind.module;

import java.io.Serializable;
import java.util.HashMap;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiators;
import shaded.parquet.com.fasterxml.jackson.databind.type.ClassKey;

public class SimpleValueInstantiators extends ValueInstantiators.Base implements Serializable {
   private static final long serialVersionUID = -8929386427526115130L;
   protected HashMap _classMappings = new HashMap();

   public SimpleValueInstantiators addValueInstantiator(Class forType, ValueInstantiator inst) {
      this._classMappings.put(new ClassKey(forType), inst);
      return this;
   }

   public ValueInstantiator findValueInstantiator(DeserializationConfig config, BeanDescription beanDesc, ValueInstantiator defaultInstantiator) {
      ValueInstantiator inst = (ValueInstantiator)this._classMappings.get(new ClassKey(beanDesc.getBeanClass()));
      return inst == null ? defaultInstantiator : inst;
   }
}
