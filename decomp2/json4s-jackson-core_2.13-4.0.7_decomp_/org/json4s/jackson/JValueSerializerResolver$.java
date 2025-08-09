package org.json4s.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.json4s.JValue;

public final class JValueSerializerResolver$ extends Serializers.Base {
   public static final JValueSerializerResolver$ MODULE$ = new JValueSerializerResolver$();
   private static final Class JVALUE = JValue.class;

   public JValueSerializer findSerializer(final SerializationConfig config, final JavaType theType, final BeanDescription beanDesc) {
      return !JVALUE.isAssignableFrom(theType.getRawClass()) ? null : new JValueSerializer();
   }

   private JValueSerializerResolver$() {
   }
}
