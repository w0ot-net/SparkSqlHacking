package org.json4s.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.json4s.JValue;

public final class JValueDeserializerResolver$ extends Deserializers.Base {
   public static final JValueDeserializerResolver$ MODULE$ = new JValueDeserializerResolver$();
   private static final Class J_VALUE = JValue.class;

   public JValueDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      return !J_VALUE.isAssignableFrom(javaType.getRawClass()) ? null : new JValueDeserializer(javaType.getRawClass());
   }

   private JValueDeserializerResolver$() {
   }
}
