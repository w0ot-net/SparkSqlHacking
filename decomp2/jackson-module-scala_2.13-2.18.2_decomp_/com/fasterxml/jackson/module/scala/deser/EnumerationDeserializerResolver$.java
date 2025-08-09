package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.Deserializers;
import scala.Enumeration;
import scala.None.;

public final class EnumerationDeserializerResolver$ extends Deserializers.Base {
   public static final EnumerationDeserializerResolver$ MODULE$ = new EnumerationDeserializerResolver$();
   private static final Class ENUMERATION = Enumeration.Value.class;

   private Class ENUMERATION() {
      return ENUMERATION;
   }

   public EnumerationDeserializer findBeanDeserializer(final JavaType javaType, final DeserializationConfig config, final BeanDescription beanDesc) {
      Class clazz = javaType.getRawClass();
      return this.ENUMERATION().isAssignableFrom(clazz) ? new EnumerationDeserializer(javaType) : (EnumerationDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
   }

   private EnumerationDeserializerResolver$() {
   }
}
