package shaded.parquet.com.fasterxml.jackson.databind.module;

import java.io.Serializable;
import java.util.HashMap;
import shaded.parquet.com.fasterxml.jackson.databind.BeanDescription;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.KeyDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.KeyDeserializers;
import shaded.parquet.com.fasterxml.jackson.databind.type.ClassKey;

public class SimpleKeyDeserializers implements KeyDeserializers, Serializable {
   private static final long serialVersionUID = 1L;
   protected HashMap _classMappings = null;

   public SimpleKeyDeserializers addDeserializer(Class forClass, KeyDeserializer deser) {
      if (this._classMappings == null) {
         this._classMappings = new HashMap();
      }

      this._classMappings.put(new ClassKey(forClass), deser);
      return this;
   }

   public KeyDeserializer findKeyDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) {
      return this._classMappings == null ? null : (KeyDeserializer)this._classMappings.get(new ClassKey(type.getRawClass()));
   }
}
