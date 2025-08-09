package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import scala.Enumeration;
import scala.None.;

public final class EnumerationSerializerResolver$ extends Serializers.Base {
   public static final EnumerationSerializerResolver$ MODULE$ = new EnumerationSerializerResolver$();
   private static final Class EnumClass = Enumeration.Value.class;

   private Class EnumClass() {
      return EnumClass;
   }

   public JsonSerializer findSerializer(final SerializationConfig config, final JavaType javaType, final BeanDescription beanDescription) {
      Class clazz = javaType.getRawClass();
      return (JsonSerializer)(this.EnumClass().isAssignableFrom(clazz) ? new EnumerationSerializer() : (JsonSerializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()));
   }

   private EnumerationSerializerResolver$() {
   }
}
